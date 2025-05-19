package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Transaction_table_demo_rep extends JpaRepository<Transaction_table_demo, String> {
	
	@Query(value = "SELECT * FROM PO_DEMO_TABLE WHERE vendor_id = ?1 ORDER BY TRAN_ID", nativeQuery = true)
	List<Transaction_table_demo> getbyvendor(String vendor_id);
	
	@Query(value = "SELECT *FROM PO_DEMO_TABLE WHERE YEAR(ACCT_CLS_DATE) = ?1 AND MONTH(ACCT_CLS_DATE) = ?2 And vendor_id=?3 ;", nativeQuery = true)
	List<Transaction_table_demo> getbyvendoryear(String year,String month,String vendor_id);
	
	
	@Query(value = "SELECT *FROM PO_DEMO_TABLE WHERE YEAR(ACCT_CLS_DATE) = ?1 AND MONTH(ACCT_CLS_DATE) <= ?2 And vendor_id=?3 ;", nativeQuery = true)
	List<Transaction_table_demo> getbyvendorpreviousmonth(String year,String month,String vendor_id);
	
	@Query(value = "SELECT ISNULL(sum(CR_AMT),0) FROM PO_DEMO_TABLE WHERE YEAR(ACCT_CLS_DATE) = ?1 AND MONTH(ACCT_CLS_DATE) <= ?2 And vendor_id=?3 ;", nativeQuery = true)
	BigDecimal totalCR_AMTprevious(String year,String month,String vendor_id);
	
	@Query(value = "SELECT ISNULL(sum(DR_AMT),0) FROM PO_DEMO_TABLE WHERE YEAR(ACCT_CLS_DATE) = ?1 AND MONTH(ACCT_CLS_DATE) <= ?2 And vendor_id=?3 ;", nativeQuery = true)
	BigDecimal totalDR_AMTprevious(String year,String month,String vendor_id);
	
}
