package com.speoma.housie.rest.model;

import java.util.List;
import java.util.Map;

public class Response {
	
	private String status;
	private String customerName;
	private List<String> ticket;
	private String roomId;
	private String customerId;
	private String ticketId;
	private String completedNos;
	private String nextNo;
	private Map<String,String> results; 

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the ticket
	 */
	public List<String> getTicket() {
		return ticket;
	}

	/**
	 * @param ticket the ticket to set
	 */
	public void setTicket(List<String> ticket) {
		this.ticket = ticket;
	}

	/**
	 * @return the roomId
	 */
	public String getRoomId() {
		return roomId;
	}

	/**
	 * @param roomId the roomId to set
	 */
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	/**
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the ticketId
	 */
	public String getTicketId() {
		return ticketId;
	}

	/**
	 * @param ticketId the ticketId to set
	 */
	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	/**
	 * @return the completedNos
	 */
	public String getCompletedNos() {
		return completedNos;
	}

	/**
	 * @param completedNos the completedNos to set
	 */
	public void setCompletedNos(String completedNos) {
		this.completedNos = completedNos;
	}

	/**
	 * @return the nextNo
	 */
	public String getNextNo() {
		return nextNo;
	}

	/**
	 * @param nextNo the nextNo to set
	 */
	public void setNextNo(String nextNo) {
		this.nextNo = nextNo;
	}

	/**
	 * @return the results
	 */
	public Map<String, String> getResults() {
		return results;
	}

	/**
	 * @param results the results to set
	 */
	public void setResults(Map<String, String> results) {
		this.results = results;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
}
