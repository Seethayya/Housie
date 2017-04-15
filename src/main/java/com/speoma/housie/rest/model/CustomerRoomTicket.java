/**
 * 
 */
package com.speoma.housie.rest.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Seethayya
 *
 */
@Entity
@Table(name = "CSTOMER_ROOM_TICKET")
@NamedQueries(
        {@NamedQuery(name="CustomerRoomTicket.findAvailableCustomerTicket", query = "select crt from CustomerRoomTicket crt left join crt.customer c left join crt.tickets t where c.emailId=? and  t.used = FALSE"),
        @NamedQuery(name="CustomerRoomTicket.findCRTbyCustomerRoom", query = "select crt from CustomerRoomTicket crt left join crt.customer c left join crt.room r where c.emailId=? and r.id=?")}
)
public class CustomerRoomTicket extends BaseDto {
	
	private Customer customer;
	private Room room;
	private Tickets tickets;
	/**
	 * @return the customer
	 */
	@ManyToOne()//, mappedBy="customer")
	@JoinColumn(name="CUSTOMER_ID", nullable=true)
	public Customer getCustomer() {
		return customer;
	}
	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	/**
	 * @return the room
	 */
	@ManyToOne()//, mappedBy="customer")
	@JoinColumn(name="ROOM_ID", nullable=true)
	public Room getRoom() {
		return room;
	}
	/**
	 * @param room the room to set
	 */
	public void setRoom(Room room) {
		this.room = room;
	}
	/**
	 * @return the tickets
	 */
	@OneToOne(cascade=CascadeType.MERGE)//, mappedBy="customer")
	@JoinColumn(name="TICKET_ID", nullable=true)
	public Tickets getTickets() {
		return tickets;
	}
	/**
	 * @param tickets the tickets to set
	 */
	public void setTickets(Tickets tickets) {
		this.tickets = tickets;
	}
	
	
	

}
