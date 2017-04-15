package com.speoma.housie.rest.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ROOM")
public class Room extends BaseDto {

    private String gameType;
    private String allNumbers;
    private String completedNumbers;
    private String nextNumber;
    private String gameStatus;
    private int noOfPlayers;
    private boolean online;
    private Long ttl;
    private List<Results> results=new ArrayList<Results>();
    private List<CustomerRoomTicket> customerRoomTickets=new ArrayList<CustomerRoomTicket>();

    /**
	 * @return the customerRoomTickets
	 */
	@OneToMany(cascade=CascadeType.MERGE, fetch=FetchType.LAZY, mappedBy="room")
	public List<CustomerRoomTicket> getCustomerRoomTickets() {
		return customerRoomTickets;
	}
	/**
	 * @param customerRoomTickets the customerRoomTickets to set
	 */
	public void setCustomerRoomTickets(List<CustomerRoomTicket> customerRoomTickets) {
		this.customerRoomTickets = customerRoomTickets;
	}
	/**
	 * @return the gameType
	 */
    @Column(name = "GAME_TYPE")
	public String getGameType() {
		return gameType;
	}
	/**
	 * @param gameType the gameType to set
	 */
	public void setGameType(String gameType) {
		this.gameType = gameType;
	}
	/**
	 * @return the allNumbers
	 */
    @Column(name = "ALL_NUMBERS")
	public String getAllNumbers() {
		return allNumbers;
	}
	/**
	 * @param allNumbers the allNumbers to set
	 */
	public void setAllNumbers(String allNumbers) {
		this.allNumbers = allNumbers;
	}
	/**
	 * @return the completedNumbers
	 */
    @Column(name = "COMPLETED_NUMBERS")
	public String getCompletedNumbers() {
		return completedNumbers;
	}
	/**
	 * @param completedNumbers the completedNumbers to set
	 */
	public void setCompletedNumbers(String completedNumbers) {
		this.completedNumbers = completedNumbers;
	}
	/**
	 * @return the nextNumber
	 */
    @Column(name = "NEXT_NUMBER")
	public String getNextNumber() {
		return nextNumber;
	}
	/**
	 * @param nextNumber the nextNumber to set
	 */
	public void setNextNumber(String nextNumber) {
		this.nextNumber = nextNumber;
	}
	/**
	 * @return the gameStatus
	 */
    @Column(name = "GAME_STATUS")
	public String getGameStatus() {
		return gameStatus;
	}
	/**
	 * @param gameStatus the gameStatus to set
	 */
	public void setGameStatus(String gameStatus) {
		this.gameStatus = gameStatus;
	}
	/**
	 * @return the noOfPlayers
	 */
    @Column(name = "NO_OF_PLAYERS")
	public int getNoOfPlayers() {
		return noOfPlayers;
	}
	/**
	 * @param noOfPlayers the noOfPlayers to set
	 */
	public void setNoOfPlayers(int noOfPlayers) {
		this.noOfPlayers = noOfPlayers;
	}
	/**
	 * @return the online
	 */
    @Column(name = "ONLINE")
	public boolean isOnline() {
		return online;
	}
	/**
	 * @param online the online to set
	 */
	public void setOnline(boolean online) {
		this.online = online;
	}
	/**
	 * @return the ttl
	 */
	@Column(name = "TTL")
	public Long getTtl() {
		return ttl;
	}
	/**
	 * @param ttl the ttl to set
	 */
	public void setTtl(Long ttl) {
		this.ttl = ttl;
	}
/*	*//**
	 * @return the customers
	 *//*
	@ManyToMany
	@JoinTable(name="CUSTOMER_ROOM_TICKET", 
	          joinColumns=@JoinColumn(name="ROOM_ID"),
	          inverseJoinColumns=@JoinColumn(name="CUSTOMER_ID"))
	public List<Customer> getCustomers() {
		if (customers == null)
			customers = new ArrayList<Customer>();
		return customers;
	}
	*//**
	 * @param customers the customers to set
	 *//*
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	*//**
	 * @return the tickets
	 *//*
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="room")
	public List<Tickets> getTickets() {
		if (tickets == null)
			tickets= new ArrayList<Tickets>();
		return tickets;
	}
	*//**
	 * @param tickets the tickets to set
	 *//*
	public void setTickets(List<Tickets> tickets) {
		this.tickets = tickets;
	}
*/	/**
	 * @return the results
	 */
	@OneToMany(cascade=CascadeType.PERSIST,fetch=FetchType.LAZY,mappedBy="room")
	public List<Results> getResults() {
		return results;
	}
	/**
	 * @param results the results to set
	 */
	public void setResults(List<Results> results) {
		this.results = results;
	}
}
