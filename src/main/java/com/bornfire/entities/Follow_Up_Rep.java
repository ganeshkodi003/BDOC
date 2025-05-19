package com.bornfire.entities;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface Follow_Up_Rep extends JpaRepository<Follow_Up_Entity, String> {
	
	@Query(value = "select * from FOLLOW_UP where WO_ID=?1 ", nativeQuery = true)
	Follow_Up_Entity getbycatid(String WO_ID);
	
	@Query(value = "select * from FOLLOW_UP WHERE BALANCE_AMOUNT > 0  ", nativeQuery = true)
	  List<Follow_Up_Entity> getByAll();
	  
	  
	  @Query(value = "SELECT MAX(ID) FROM FOLLOW_UP", nativeQuery = true)
	  Integer findMax();
	  
	  @Query(value = "SELECT * FROM FOLLOW_UP where VISIT_DATE=?1 ",
	  nativeQuery = true) List<Follow_Up_Entity> findbyvisitedate(LocalDate
	  tomorrow);
	  
	 

}
