package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OutwardRep extends JpaRepository<Outward, String> {

	@Query(value = "select * from Outward_table", nativeQuery = true)
	List<Outward> getlist();

}
