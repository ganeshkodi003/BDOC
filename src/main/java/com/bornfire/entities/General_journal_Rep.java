package com.bornfire.entities;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface General_journal_Rep extends JpaRepository<General_journal_entity, String> {
	

	@Query(value = "SELECT * FROM GENERAL_JOURNALS ORDER BY GJ_ID ASC ", nativeQuery = true)
	List<General_journal_entity> findsall();
	
	@Query(value = "SELECT * FROM GENERAL_JOURNALS where order_type='PURCHASEORDER' ORDER BY GJ_ID ASC ", nativeQuery = true)
	List<General_journal_entity> findsallp();
	
	@Query(value = "SELECT * FROM GENERAL_JOURNALS where order_type='WORKORDER' ORDER BY GJ_ID ASC ", nativeQuery = true)
	List<General_journal_entity> findsallWORKORDER();
	
	@Query(value = "select VendorId ,sum(credit) as credit ,sum(debit) as debit from GENERAL_JOURNALS where VendorId is not null group by VendorId", nativeQuery = true)
	List<Object[]> findsallvaldor();
	
	
	@Query(value = "SELECT * FROM GENERAL_JOURNALS where GJ_ID=?1 ", nativeQuery = true)
	General_journal_entity findbyid(Integer assId);
	
	@Query(value = "SELECT * FROM GENERAL_JOURNALS where VendorId=?1 And order_type=?2 ", nativeQuery = true)
	General_journal_entity findbyvandorid(String VendorId ,String order_type);
	
	@Query(value = "SELECT * FROM GENERAL_JOURNALS where order_type=:order_type  ORDER BY GJ_ID ASC ", nativeQuery = true)
	List<General_journal_entity> findPOandwo(@Param("order_type") String order_type);
	

	

}
