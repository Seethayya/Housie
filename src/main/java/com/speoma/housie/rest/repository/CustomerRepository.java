package com.speoma.housie.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.speoma.housie.rest.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	
	List<Customer> findCustomerByFirstName(String firstName);
	
	List<Customer> findCustomerByEmailId(String emailId);
	
	List<Customer> findCustomerByEmailIdAndPassword(String emailId, String password);

}
