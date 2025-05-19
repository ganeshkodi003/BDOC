package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Expenses_repo extends JpaRepository<Expenses_entity,String> {
	
 	@Query(value = "select * from Expenses_Table  ORDER BY  Exp_id ", nativeQuery = true)
  	 List<Expenses_entity> getEXPLIST ();

}
