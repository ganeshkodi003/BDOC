package com.bornfire.entities;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceMasterRep extends JpaRepository<InvoiceMaster, String> {

	//public Optional<InvoiceMaster> findById(String invoice_no);
	
	@Query(value = "select * from LOC_INVOICE_MASTER WHERE PO_ID =?1  ", nativeQuery = true) 
	 InvoiceMaster getplacementlist2(String po_id);

	@Query(value = "SELECT * FROM loc_invoice_master ORDER BY inv_no", nativeQuery = true)
	List<InvoiceMaster>  getplacementlist();

}
