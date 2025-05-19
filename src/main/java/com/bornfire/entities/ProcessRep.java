package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProcessRep extends JpaRepository<ProcessEntity, String> { 

	 @Query(value = "select PROCESS_MENU from PROCESS_TABLE", nativeQuery = true)
	 List<String> getall();
}
