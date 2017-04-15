package com.speoma.housie.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.speoma.housie.rest.model.CustomerRoomTicket;

@Repository
public interface CstomerRoomTicketsRepository extends JpaRepository<CustomerRoomTicket, Long> {
	
	//@Query("SELECT * FROM TICKETS WHERE USED = false and emailI")
	List<CustomerRoomTicket> findAvailableCustomerTicket(String emailId);
	
	List<CustomerRoomTicket> findCRTbyCustomerRoom(String emailId, Long roomId);
	
	
}
