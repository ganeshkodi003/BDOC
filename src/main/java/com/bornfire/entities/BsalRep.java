package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BsalRep extends JpaRepository<BsalEntity,String> {

	@Query(value = "select * from BSAL_VIEW where salary_month=?1", nativeQuery = true)
	List<BsalEntity> getData52(String salary_month);
	
}
