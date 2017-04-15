package com.speoma.housie.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "RESULTS")
@NamedQueries(
        {
        	@NamedQuery(name="Results.findResultsByTypeAndRoom", query = "select r from Results r left join r.room rm where r.type=? and rm.id=? and r.customer IS NULL"),
        	@NamedQuery(name="Results.findAllResultsByRoom", query = "select r from Results r left join r.room rm where rm.id=?")
        }
)
public class Results extends BaseDto {

    private String type;
    private String value;
    private String winner;
    private Room room;
    private Customer customer;
    
	/**
	 * @return the type
	 */
    @Column(name = "TYPE")
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the value
	 */
	@Column(name = "VALUE")
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return the winner
	 */
	@Column(name = "WINNER")
	public String getWinner() {
		return winner;
	}
	/**
	 * @param winner the winner to set
	 */
	public void setWinner(String winner) {
		this.winner = winner;
	}
	/**
	 * @return the room
	 */
	@ManyToOne()
	@JoinColumn(name = "ROOM_ID")
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
	 * @return the customer
	 */
	@ManyToOne()
	@JoinColumn(name = "CUSTOMER_ID", nullable=true)
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
