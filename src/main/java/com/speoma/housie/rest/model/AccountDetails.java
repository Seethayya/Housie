package com.speoma.housie.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT_DETAILS")
public class AccountDetails extends BaseDto {

    private Long availablePoints;
    private Long availableTickets;
    private String accountType;
    private Customer customer;
	/**
	 * @return the availablePoints
	 */
    @Column(name = "AVAILABLE_POINTS")
	public Long getAvailablePoints() {
		return availablePoints;
	}
	/**
	 * @param availablePoints the availablePoints to set
	 */
	public void setAvailablePoints(Long availablePoints) {
		this.availablePoints = availablePoints;
	}
	/**
	 * @return the availableTickets
	 */
    @Column(name = "AVAILABLE_TICKETS")
	public Long getAvailableTickets() {
		return availableTickets;
	}
	/**
	 * @param availableTickets the availableTickets to set
	 */
	public void setAvailableTickets(Long availableTickets) {
		this.availableTickets = availableTickets;
	}
	/**
	 * @return the accountType
	 */
    @Column(name = "ACCOUNT_TYPE")
	public String getAccountType() {
		return accountType;
	}
	/**
	 * @param accountType the accountType to set
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	/**
	 * @return the customer
	 */
	@OneToOne()
	@JoinColumn(name = "customer_id")
	public Customer getCustomer() {
		return customer;
	}
	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
