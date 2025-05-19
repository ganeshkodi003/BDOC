package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;




public interface LeaveTableRep extends CrudRepository<LeaveTable, BigDecimal> {

	public Optional<LeaveTable> findById(BigDecimal no_row);
	long count(); 
	 @Query(value = "SELECT * FROM LEAVE_TABLE WHERE no_row = '1' " , nativeQuery = true)
	 LeaveTable getLeaveTable(BigDecimal no_row);
	
	 @Query(value = "SELECT number_of_days FROM LEAVE_TABLE ORDER BY no_row", nativeQuery = true)
	    List<String> getAllNumberOfDays();
	 
	 @Query(value = "SELECT * FROM LEAVE_TABLE ORDER BY no_row", nativeQuery = true)
	    List<LeaveTable> get_no_days();
	 
	 @Query(value = "SELECT * FROM LEAVE_TABLE ORDER BY no_row", nativeQuery = true)
	    List<LeaveTable> getAll();
	
}