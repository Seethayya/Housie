package com.speoma.housie.rest.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.speoma.housie.rest.model.Customer;
import com.speoma.housie.rest.model.Response;
import com.speoma.housie.rest.service.CustomerServiceImpl;
import com.speoma.housie.rest.service.HousieServiceImpl;
import com.speoma.housie.rest.service.ResultSeviceImpl;

@RestController
@RequestMapping("/api")
public class HosieController {

	private static final Logger LOGGER = Logger.getLogger(CustomerServiceImpl.class);
	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	@Autowired
	private HousieServiceImpl housieService;
	@Autowired
	private ResultSeviceImpl resultSevice;
	
	
	@RequestMapping(value = "/saveCustomer", method = RequestMethod.POST)
	@ResponseBody
    public Response saveCustomer(@RequestBody Customer customer) {
		LOGGER.debug("HosieController.saveCustomer()"+customer);
		Response response = customerServiceImpl.saveCustomer(customer);
        return response;
    }

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
    public Response login(@RequestBody Customer customer) {
		LOGGER.debug("HosieController.login:UN:"+customer.getFirstName()+" PWD :"+customer.getPassword());
		Response response = customerServiceImpl.login(customer.getFirstName(), customer.getPassword());
        return response;
    }
	
	@RequestMapping(value = "/getCustomerByName/{firstName}", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getCustomerByName(@PathVariable String firstName) {
		 LOGGER.debug("HosieController.getCustomerByName()"+firstName);
		 List<Customer> customers =  customerServiceImpl.findCustomerByFirstName(firstName);
		 for (Customer customer : customers) {
			 customer.setResults(null);
		    	customer.setCustomerRoomTickets(null);
		 }
		 return new ResponseEntity<>(customers, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/generateTicket/{emailId}/{noOfTickets}", method = RequestMethod.GET)
	@ResponseBody
    public Response generateTicket(@PathVariable String emailId, @PathVariable int noOfTickets) {
		 LOGGER.debug("HosieController.generateTicket()"+emailId);
		 String responseticket =  customerServiceImpl.generateTicket(emailId, noOfTickets);
		 Response response = new Response();
		 response.setStatus(responseticket);
		 return response;
    }
	
	@RequestMapping(value = "/bookRoom/{emailId}/{roomType}", method = RequestMethod.GET)
	@ResponseBody
    public Response bookRoom(@PathVariable String emailId, @PathVariable String roomType) {
		 LOGGER.debug("HosieController.bookRoom()"+emailId);
		 Response response =  customerServiceImpl.bookRoom(emailId, roomType);
		 return response;
    }
	
	@RequestMapping(value = "/nextToken/{roomId}", method = RequestMethod.GET)
	@ResponseBody
    public Response nextToken(@PathVariable Long roomId) {
		 LOGGER.debug("HosieController.nextToken():roomId:"+roomId);
		 Response response =  housieService.nextToken(roomId);
		 return response;
    }
	
	@RequestMapping(value = "/verifyResult/{emailId}/{roomId}/{resultType}", method = RequestMethod.GET)
	@ResponseBody
    public Response verifyResult(@PathVariable String emailId, @PathVariable Long roomId, @PathVariable String resultType) {
		 LOGGER.debug("HosieController.nextToken():roomId:"+roomId);
		 Response response = new Response();
		 response.setStatus(housieService.verifyResult(emailId, roomId, resultType));
		 return response;
	}
	
	@RequestMapping(value = "/getAllResults/{roomId}", method = RequestMethod.GET)
	@ResponseBody
    public Response getAllResults(@PathVariable Long roomId) {
		 LOGGER.debug("HosieController.nextToken():roomId:"+roomId);
		 Map<String,String> results =  resultSevice.getResultsByRoom(roomId);
		 Response response = new Response();
		 response.setStatus("SUCCESS");
		 response.setResults(results);
		 return response;
    }
	
	@RequestMapping(value = "/getCompletedNos/{roomId}", method = RequestMethod.GET)
	@ResponseBody
    public Response getCompletedNos(@PathVariable Long roomId) {
		 LOGGER.debug("HosieController.nextToken():roomId:"+roomId);
		 Response response = new Response();
		 response.setStatus("SUCCESS");
		 response.setCompletedNos(housieService.getCompletedNos(roomId));
		 return response;
    }

}
