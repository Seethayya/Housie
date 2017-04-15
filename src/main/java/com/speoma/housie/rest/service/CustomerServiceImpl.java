package com.speoma.housie.rest.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speoma.housie.rest.model.Customer;
import com.speoma.housie.rest.model.CustomerRoomTicket;
import com.speoma.housie.rest.model.GameType;
import com.speoma.housie.rest.model.Response;
import com.speoma.housie.rest.model.ResultType;
import com.speoma.housie.rest.model.Results;
import com.speoma.housie.rest.model.Room;
import com.speoma.housie.rest.model.Tickets;
import com.speoma.housie.rest.repository.CstomerRoomTicketsRepository;
import com.speoma.housie.rest.repository.CustomerRepository;
import com.speoma.housie.rest.repository.RoomRepository;
import com.speoma.housie.rest.repository.TicketsRepository;
import com.speoma.housie.rest.ticketgenerator.Ticket;
import com.speoma.housie.rest.ticketgenerator.TicketGenerator;
import com.speoma.housie.rest.util.Utility;

@Service
public class CustomerServiceImpl {
    private static final Logger LOGGER = Logger.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private TicketsRepository ticketsRepository;
	@Autowired
	private CstomerRoomTicketsRepository customerRoomTicketsRepository;
	@Autowired
	private TicketGenerator ticketGenerator;


	public void saveCustomer(String firstName) {
		LOGGER.debug("START CustomerServiceImpl.save()"+firstName);
		Customer customer = new Customer();
		customer.setFirstName(firstName);
		customer.setEmailId(firstName);
		customer = customerRepository.save(customer);
		LOGGER.debug("END CustomerServiceImpl.save()"+customer);
	}
	
	public Response saveCustomer(Customer customer) {
		LOGGER.debug("START CustomerServiceImpl.save customer()"+ customer);
		customer.setRegisterFrom("Web");
		customer.setCountryCode("91");
		List<Customer> customers = customerRepository.findCustomerByEmailId(customer.getEmailId());
		Response response = new Response();
		if (customers != null && !customers.isEmpty()) {
			response.setStatus("Error:Email Id already exist");
			return response;
		} 
		response.setStatus("SUCCESS");
		customer = customerRepository.save(customer);
		response.setCustomerName(Utility.getFullName(customer));
		response.setCustomerId(customer.getEmailId());
		return response;
	}
	
	public Response login(String userName, String password) {
		LOGGER.debug("START CustomerServiceImpl.login()"+ userName);
		List<Customer> customers = customerRepository.findCustomerByEmailIdAndPassword(userName, password);
		Response response = new Response();
		if (customers != null && !customers.isEmpty()) {
			response.setStatus("Successfully logged in");
			response.setCustomerName(Utility.getFullName(customers.get(0)));
			response.setCustomerId(customers.get(0).getEmailId());
		} else {
			response.setStatus("Error:UserName/Password in correct or not exist");
		}
		return response;
	}

	public List<Customer> findCustomerByFirstName(String firstName) {
		LOGGER.debug("START CustomerServiceImpl.findCustomerByFirstName()"+firstName);
		List<Customer> customer = customerRepository.findCustomerByFirstName(firstName);
		LOGGER.debug("END CustomerServiceImpl.findCustomerByFirstName()"+customer);
		return customer;
	}
	
	public String generateTicket(String emailId,int noOfTickets) {
		LOGGER.debug("START CustomerServiceImpl.generateTicket()"+emailId);
		List<Customer> customers = customerRepository.findCustomerByEmailId(emailId);
		if (customers == null || customers.isEmpty()) {
			return "No Customer Found";
		}
		Customer customer = customers.get(0);
		for (int index=1;index<=noOfTickets;index++) {
			Ticket ticket = ticketGenerator.generateTicket();
			Tickets tickets = new Tickets();
			CustomerRoomTicket customerRoomTicket = new CustomerRoomTicket();
			customerRoomTicket.setCustomer(customer);
			customerRoomTicket.setTickets(tickets);
			//tickets.setCustomerRoomTicket(customerRoomTicket);
			tickets.setUsed(false);
			tickets.setTicketNo("Test1");
			tickets.setTicket(ticket.getFormattedTicket());
			ticketsRepository.save(tickets);
			customerRoomTicketsRepository.save(customerRoomTicket);	
		}
			
		
		LOGGER.debug("END CustomerServiceImpl.generateTicket()"+emailId);
		return "SUCCESS";
	}
	
	public Response bookRoom(String emailId, String gameType) {
		LOGGER.debug("START CustomerServiceImpl.bookRoom()"+emailId);
		Response response = new Response();
		List<Customer> customers = customerRepository.findCustomerByEmailId(emailId);
		if (customers == null || customers.isEmpty()) {
			response.setStatus("No Customer Found");
			return response;
		}
		List<CustomerRoomTicket> customerRoomTickets = customerRoomTicketsRepository.findAvailableCustomerTicket(emailId);
		if (customerRoomTickets == null || customerRoomTickets.isEmpty() || customerRoomTickets.get(0).getTickets() == null) {
			response.setStatus("No Available Tickets Found");
			return response;
		}
		GameType.RoomType roomType = GameType.RoomType.valueOf(gameType);
		Tickets ticket = customerRoomTickets.get(0).getTickets();
		response.setTicket(Arrays.asList(ticket.getTicket().split("-")));
		response.setTicketId(ticket.getId().toString());
		ticket.setUsed(true);
		List<Room> availableRooms = roomRepository.findRoomByGameTypeAndGameStatusAndNoOfPlayersLessThan(roomType.name(),"NOT STARTED", 2);
		Room room = null;
		if (availableRooms == null || availableRooms.size() == 0) {
			room = new Room();
			room.setNoOfPlayers(1);
			room.setOnline(true);
			room.setGameStatus("NOT STARTED");
			room.setGameType(roomType.name());
			room.setAllNumbers(ticketGenerator.formatHousieNos());
			createResult(room, roomType);
		} else {
			room = availableRooms.get(0);
			room.setNoOfPlayers(room.getNoOfPlayers()+1);
		}
		customerRoomTickets.get(0).setRoom(room);
		room.getCustomerRoomTickets().add(customerRoomTickets.get(0));
		roomRepository.save(room);
		ticketsRepository.save(ticket);
		LOGGER.debug("END CustomerServiceImpl.bookRoom()"+emailId);
		response.setRoomId(room.getId().toString());
		return response;
	}

	/**
	 * @param room
	 * @param roomType
	 */
	public void createResult(Room room, GameType.RoomType roomType) {
		//Results creation
		List<Results> results = new ArrayList<Results>();
		room.setResults(results);
		if (roomType.getMaxFast5() > 0) {
			for (int index=0;index < roomType.getMaxFast5();index++) {
				Results resultEntity = new Results();
				resultEntity.setRoom(room);
				resultEntity.setType(ResultType.FAST5.name());
				resultEntity.setValue("20");
				results.add(resultEntity);
			}
		}
		if (roomType.getMaxLine1() > 0) {
			for (int index=0;index < roomType.getMaxLine1();index++) {
				Results resultEntity = new Results();
				resultEntity.setRoom(room);
				resultEntity.setType(ResultType.LINE1.name());
				resultEntity.setValue("10");
				results.add(resultEntity);
			}
		}
		if (roomType.getMaxLine2() > 0) {
			for (int index=0;index < roomType.getMaxLine2();index++) {
				Results resultEntity = new Results();
				resultEntity.setRoom(room);
				resultEntity.setType(ResultType.LINE2.name());
				resultEntity.setValue("10");
				results.add(resultEntity);
			}
		}
		if (roomType.getMaxLine3() > 0) {
			for (int index=0;index < roomType.getMaxLine3();index++) {
				Results resultEntity = new Results();
				resultEntity.setRoom(room);
				resultEntity.setType(ResultType.LINE3.name());
				resultEntity.setValue("10");
				results.add(resultEntity);
			}
		}
		if (roomType.getFull() > 0) {
			for (int index=0;index < roomType.getFull();index++) {
				Results resultEntity = new Results();
				resultEntity.setRoom(room);
				resultEntity.setType(ResultType.FULL.name());
				resultEntity.setValue("50");
				results.add(resultEntity);
			}
		}
	}
}
