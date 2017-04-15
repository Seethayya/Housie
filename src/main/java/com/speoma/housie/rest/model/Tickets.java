package com.speoma.housie.rest.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TICKETS")
@NamedQueries(
        {
        	@NamedQuery(name="Tickets.findTicketsByNotUsedAndCustomerEmailId", query = "select t from Tickets t join t.customerRoomTicket crt"),
        	@NamedQuery(name="Tickets.findTicketByCustomerAndRoom", query = "select t from Tickets t left join t.customerRoomTicket crt join crt.customer c join crt.room r "
        			+ "where c.emailId=? and r.id=? ")
        }
)
public class Tickets extends BaseDto {

    private String ticket;
    private String ticketNo;
    private boolean used;
    private CustomerRoomTicket customerRoomTicket;

	/**
	 * @return the ticket
	 */
    @Column(name = "TICKET")
	public String getTicket() {
		return ticket;
	}
	/**
	 * @return the customerRoomTicket
	 */
    @OneToOne(cascade=CascadeType.MERGE, fetch=FetchType.LAZY, mappedBy="tickets")
	public CustomerRoomTicket getCustomerRoomTicket() {
		return customerRoomTicket;
	}
	/**
	 * @param customerRoomTicket the customerRoomTicket to set
	 */
	public void setCustomerRoomTicket(CustomerRoomTicket customerRoomTicket) {
		this.customerRoomTicket = customerRoomTicket;
	}
	/**
	 * @param ticket the ticket to set
	 */
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	/**
	 * @return the ticketNo
	 */
	@Column(name = "TICKET_NO")
	public String getTicketNo() {
		return ticketNo;
	}
	/**
	 * @param ticketNo the ticketNo to set
	 */
	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}
	/**
	 * @return the used
	 */
	@Column(name = "USED")
	public boolean isUsed() {
		return used;
	}
	/**
	 * @param used the used to set
	 */
	public void setUsed(boolean used) {
		this.used = used;
	}
	
}
