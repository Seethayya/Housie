package com.speoma.housie.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.speoma.housie.rest.model.Results;

@Repository
public interface ResultsRepository extends JpaRepository<Results, Long> {
	
	List<Results> findResultsByTypeAndRoom(String type, Long roomId);
	
	List<Results> findAllResultsByRoom(Long roomId);
	
}
