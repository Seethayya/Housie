package com.speoma.housie.rest.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speoma.housie.rest.model.Customer;
import com.speoma.housie.rest.model.ResultType;
import com.speoma.housie.rest.model.Results;
import com.speoma.housie.rest.model.Room;
import com.speoma.housie.rest.repository.ResultsRepository;
import com.speoma.housie.rest.ticketgenerator.Ticket;
import com.speoma.housie.rest.util.Utility;

@Service
public class ResultSeviceImpl {

    private static final Logger LOGGER = Logger.getLogger(ResultSeviceImpl.class);

	@Autowired
	private ResultsRepository resultsRepository;
	
	public String confirmResult(Customer customer, Room room, String formattedTicket, String completedNos, ResultType resultType) {
		LOGGER.debug("--START confirmResult:formattedTicket"+formattedTicket+" completedNos:"+completedNos+" resultType:"+resultType);
		boolean result =  verifyResult(formattedTicket, completedNos, resultType);
		LOGGER.debug("result:"+result);
		if (result) {
			List<Results> results = resultsRepository.findResultsByTypeAndRoom(resultType.name(), room.getId());
			LOGGER.debug("results:"+results);
			if (results != null && !results.isEmpty()) {
				Results resultEntity = results.get(0);
				resultEntity.setCustomer(customer);
				resultsRepository.save(resultEntity);
				return "CONGRATULATIONS";
			} else {
				return "Result already done or not avaliable";
			}
		} else {
			return "You did not completed result";
		}
	}
	
	public Map<String,String> getResultsByRoom(Long roomId) {
		LOGGER.debug("--START getResultsByRoom:"+roomId);
			List<Results> results = resultsRepository.findAllResultsByRoom(roomId);
			LOGGER.debug("results:"+results);
			Map<String,String> resultsMap = new HashMap<String,String>();
			if (results != null && !results.isEmpty()) {
				for (Results resultEntity  : results) {
					resultsMap.put(resultEntity.getType(), (resultEntity.getCustomer() ==null ? StringUtils.EMPTY : Utility.getFullName(resultEntity.getCustomer())));
				}
			} else {
			}
			return resultsMap;
	}

	/**
	 * @param formattedTicket
	 * @param completedNos
	 * @param resultType
	 * @return
	 */
	public boolean verifyResult(String formattedTicket, String completedNos, ResultType resultType) {
		if (StringUtils.isBlank(completedNos)) {
			return false;	
		}
		List<Integer> allNos = new ArrayList<Integer>();
		for (String number: completedNos.split("\\|"))
			allNos.add(Integer.valueOf(number));
		Ticket ticket = new Ticket(formattedTicket);
		LOGGER.debug("---verifyResult:ticket:"+ticket);
		boolean result = false;
		
		switch (resultType) {
			case FAST5 :{
						int count = 0;
						for (int number : ticket.getNumbers()) {
							if (allNos.contains(number)) {
								count++;
							}
						}
						result = count >= 5; 	
						};break;
			case LINE1 :{
						result = allNos.containsAll(ticket.getRow1());
						};break;
			case LINE2 :{
						result = allNos.containsAll(ticket.getRow2());
						};break;
			case LINE3 :{
						result = allNos.containsAll(ticket.getRow3());
						};break;
			case FULL :{
						result = allNos.containsAll(ticket.getNumbers());
						};break;
		}
		return result;
	}
}
