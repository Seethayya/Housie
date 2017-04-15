package com.speoma.housie.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.speoma.housie.rest.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
	
	
	List<Room> findRoomByGameTypeAndGameStatusAndNoOfPlayersLessThan(String type, String gameStatus, int maxNumber);
	
}
