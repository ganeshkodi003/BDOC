package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface Capitalrep extends CrudRepository<Capital, Long> {
	@Query(value = "select * from Capital", nativeQuery = true)
	Capital getCapitalamount();
	
	@Query(value = "select * from Capital where capitalAmount=?1", nativeQuery = true)
	Capital getCapitalamountbyid(long capitalAmount);

}
