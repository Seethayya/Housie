package com.speoma.housie.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.speoma.housie.rest.model.Tickets;

@Repository
public interface TicketsRepository extends JpaRepository<Tickets, Long> {
	
	//@Query("SELECT * FROM TICKETS WHERE USED = false and emailI")
	List<Tickets> findTicketsByNotUsedAndCustomerEmailId();
	
	List<Tickets> findTicketByCustomerAndRoom(String emailId, Long roomId);
	
}
