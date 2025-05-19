package com.bornfire.entities;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InwardRep extends JpaRepository<Inward, String> {
		
	
	@Query(value = "select * from Inward_table", nativeQuery = true)
	List<Inward> getlist();
	
	
	@Query(value = "select * from Inward_table where po_number=?1", nativeQuery = true)
	Inward getvehicallist(String poid); 
	
	@Query(value = "SELECT * FROM Inward_table WHERE po_number = :ponumber", nativeQuery = true)
	Optional<Inward> findByPoNumber(@Param("ponumber") String ponumber);



}
