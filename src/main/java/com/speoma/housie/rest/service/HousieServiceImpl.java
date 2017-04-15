/**
 * 
 */
package com.speoma.housie.rest.service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.collections4.map.PassiveExpiringMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speoma.housie.rest.model.CustomerRoomTicket;
import com.speoma.housie.rest.model.Response;
import com.speoma.housie.rest.model.ResultType;
import com.speoma.housie.rest.model.Room;
import com.speoma.housie.rest.model.Tickets;
import com.speoma.housie.rest.repository.CstomerRoomTicketsRepository;
import com.speoma.housie.rest.repository.RoomRepository;
import com.speoma.housie.rest.repository.TicketsRepository;

/**
 * @author Seethayya
 *
 */
@Service
public class HousieServiceImpl {

    private static final Logger LOGGER = Logger.getLogger(HousieServiceImpl.class);

	private PassiveExpiringMap<Long, String> housieMap = new PassiveExpiringMap<Long, String>(5, TimeUnit.SECONDS);
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private TicketsRepository ticketRepository;
	@Autowired
	private CstomerRoomTicketsRepository cstomerRoomTicketsRepository;
	@Autowired
	private ResultSeviceImpl resultSevice;
	
	public String updateTtlNextToken(Long roomId) {
		LOGGER.debug("START updateTtlNextToken::"+roomId);
		//Find next token from data base
		Room room = roomRepository.getOne(roomId);
		String [] allNumbers = room.getAllNumbers().split("\\|");
		String originalCompletedNos = room.getCompletedNumbers();
		String[] completedNos = StringUtils.isNotBlank(originalCompletedNos)? originalCompletedNos.split("\\|") : null;
		if (completedNos != null && completedNos.length >= 90) {
			return "All nos Passed";
		}
		LOGGER.debug("allNumbers:"+Arrays.asList(allNumbers)+
				" originalCompletedNos:"+ (originalCompletedNos  != null ? Arrays.asList(originalCompletedNos) : "Empty")+
				": completedNos:"+(completedNos  != null ? Arrays.asList(completedNos) : "Empty"));
		String nextToken = allNumbers[completedNos == null ? 0 : completedNos.length];
		if (StringUtils.isBlank(originalCompletedNos)) {
			originalCompletedNos = nextToken;
			room.setGameStatus("In Progress");
		} else {
			originalCompletedNos = originalCompletedNos.concat("|").concat(nextToken);
		}
		housieMap.put(roomId, nextToken);
		//Update database
		room.setCompletedNumbers(originalCompletedNos);
		LOGGER.debug(" originalCompletedNos:"+ (originalCompletedNos  != null ? Arrays.asList(originalCompletedNos) : "Empty")+
				": completedNos:"+(completedNos  != null ? Arrays.asList(completedNos) : "Empty"));
		roomRepository.save(room);
		LOGGER.debug("END updateTtlNextToken::"+roomId+":nextToken:"+nextToken);
		return nextToken;
	}
	
	public Response nextToken(Long roomId) {
		LOGGER.debug("START nextToken::roomId:"+roomId);
		Response response = new Response();
		String nextToken = housieMap.get(roomId);
		if (nextToken == null) {
			synchronized (housieMap) {
				nextToken = housieMap.get(roomId);
				if (nextToken == null)
					nextToken = updateTtlNextToken(roomId);
			}
		}
		response.setNextNo(nextToken);
		LOGGER.debug("END nextToken::roomId:"+roomId+":nextToken:"+nextToken);

		return response;
	}
	
	public String verifyResult(String emailId, Long roomId, String resultType) {
		LOGGER.debug("START verifyResult::roomId:"+roomId+":emailId:"+emailId+":resultType:"+resultType);
		List<Tickets> tickets= ticketRepository.findTicketByCustomerAndRoom(emailId, roomId);
		LOGGER.debug("tickets:"+tickets);
		if (tickets != null && !tickets.isEmpty()) {
			Tickets ticket = tickets.get(0);
			CustomerRoomTicket crt = ticket.getCustomerRoomTicket();
			LOGGER.debug("=====ticket nos:"+ticket.getTicket()+"_"+crt.getCustomer()+":"+crt.getRoom());
			return resultSevice.confirmResult(crt.getCustomer(), crt.getRoom(), ticket.getTicket(), crt.getRoom().getCompletedNumbers(), ResultType.valueOf(resultType));
		}
		/*List<CustomerRoomTicket> crts= cstomerRoomTicketsRepository.findCRTbyCustomerRoom(emailId, roomId);
		if (crts != null && !crts.isEmpty()) {
			Tickets ticket = crts.get(0).getTickets();
			LOGGER.debug("=====ticket nos:"+ticket.getTicket());
		}*/
		LOGGER.debug("END verifyResult::roomId:"+roomId);
		return "SUCCESS";
	}
	
	public String getCompletedNos(Long roomId) {
		LOGGER.debug("START getCompletedNos::roomId:"+roomId);
		Room room =  roomRepository.getOne(roomId);
		if (room != null) {
			try {
				return room.getCompletedNumbers();
			} catch (EntityNotFoundException e) {
				return "No Room Found";
			}
		}
		return "No Room Found";
	}
}
