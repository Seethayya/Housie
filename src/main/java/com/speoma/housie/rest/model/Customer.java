package com.speoma.housie.rest.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer extends BaseDto {

    private String firstName;
    private String lastName;
    private String middleName;
    private String emailId;
    private String password;
    private Long mobileNo;
    private String countryCode;
    private String location;
    private String registerFrom;
    private AccountDetails accountDetails;
    private List<Results> results=new ArrayList<Results>();
    private List<CustomerRoomTicket> customerRoomTickets=new ArrayList<CustomerRoomTicket>();
    
    @Column(name = "FIRST_NAME")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "MIDDLE_NAME")
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Column(name = "EMAIL_ID")
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "MOBILE_NO")
    public Long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(Long mobileNo) {
        this.mobileNo = mobileNo;
    }
    
    /**
	 * @return the countryCode
	 */
    @Column(name = "COUNTRY_CODE")
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * @return the location
	 */
	@Column(name = "LOCATION")
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the registerFrom
	 */
	@Column(name = "REGISTER_FROM")
	public String getRegisterFrom() {
		return registerFrom;
	}

	/**
	 * @param registerFrom the registerFrom to set
	 */
	public void setRegisterFrom(String registerFrom) {
		this.registerFrom = registerFrom;
	}

	/**
	 * @return the accountDetails
	 */
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy="customer")
	public AccountDetails getAccountDetails() {
		return accountDetails;
	}

	/**
	 * @param accountDetails the accountDetails to set
	 */
	public void setAccountDetails(AccountDetails accountDetails) {
		this.accountDetails = accountDetails;
	}

	/**
	 * @return the results
	 */
	@OneToMany(cascade=CascadeType.MERGE, fetch=FetchType.LAZY, mappedBy="customer")
	public List<Results> getResults() {
		return results;
	}

	/**
	 * @param results the results to set
	 */
	public void setResults(List<Results> results) {
		this.results = results;
	}

	/**
	 * @return the customerRoomTickets
	 */
	@OneToMany(cascade=CascadeType.MERGE, fetch=FetchType.LAZY, mappedBy="customer")
	public List<CustomerRoomTicket> getCustomerRoomTickets() {
		return customerRoomTickets;
	}

	/**
	 * @param customerRoomTickets the customerRoomTickets to set
	 */
	public void setCustomerRoomTickets(List<CustomerRoomTicket> customerRoomTickets) {
		this.customerRoomTickets = customerRoomTickets;
	}

/*	*//**
	 * @return the rooms
	 *//*
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE,mappedBy="customers")
	public List<Room> getRooms() {
		if (rooms == null) 
			rooms = new ArrayList<Room>();
		return rooms;
	}

	*//**
	 * @param rooms the rooms to set
	 *//*
	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	*//**
	 * @return the tickets
	 *//*
	@OneToMany(cascade=CascadeType.MERGE, fetch=FetchType.LAZY)//, mappedBy="customer")
	@JoinTable(name="CUSTOMER_ROOM_TICKET", inverseJoinColumns=@JoinColumn(name="TICKET_ID",referencedColumnName="id",nullable=true),
	joinColumns=@JoinColumn(name="CUSTOMER_ID",referencedColumnName="id",nullable=true))
	@ElementCollection(fetch=FetchType.LAZY)
	public List<Tickets> getTickets() {
		return tickets;
	}

	*//**
	 * @param tickets the tickets to set
	 *//*
	public void setTickets(List<Tickets> tickets) {
		this.tickets = tickets;
	}

	*//**
	 * @return the results
	 *//*
	@OneToMany(cascade=CascadeType.MERGE, fetch=FetchType.LAZY, mappedBy="customer")
	public List<Results> getResults() {
		return results;
	}

	*//**
	 * @param results the results to set
	 *//*
	public void setResults(List<Results> results) {
		this.results = results;
	}
*/
	
	
}
