package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface DocumentMainRep extends JpaRepository<DocumentMaintenance, String> {
 
	@Query(value = "SELECT * FROM DOCUMENT_MAINTENANCE", nativeQuery = true)
	List<DocumentMaintenance> Documents();
}
