package com.bornfire.entities;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CapitalTransRep extends JpaRepository<CapitalTrans, String> {

	@Query(value = "select * from capital_trans order by Journal_Tran_Id", nativeQuery = true)
	List<CapitalTrans> getCategorylist();
	
	@Query(value = "SELECT COALESCE(SUM(Credits), 0) FROM capital_trans WHERE tran_particulars='Purchase'", nativeQuery = true)
	BigDecimal getTotalPurchase();
	
	@Query(value = "SELECT COALESCE(SUM(Debits), 0)  FROM capital_trans WHERE tran_particulars='Sale'", nativeQuery = true)
	BigDecimal getTotalSale();
	

	@Query(value = "select * from capital_trans where account_number=?1 order by Journal_Tran_Id ", nativeQuery = true)
	List<CapitalTrans> getbyaccount_number(String account_number);
	
	@Query(value = "SELECT * FROM capital_trans WHERE account_number = :accountnum AND Journal_Date BETWEEN :fromDate AND :toDate  ", nativeQuery = true)
	List<CapitalTrans> getByAccountNumberAndDateRange( String accountnum, Date fromDate,  Date toDate);
	
	@Query(value = "SELECT * FROM capital_trans WHERE account_number = :accountnum AND Journal_Date >= :fromDate", nativeQuery = true)
	List<CapitalTrans> getByAccountNumberFromDate(@Param("accountnum") String accountnum, @Param("fromDate") Date fromDate);




	@Query(value = "SELECT CAST(current_value AS BIGINT) FROM sys.sequences WHERE name ='journalentrieid'", nativeQuery = true)
	BigInteger gettrnnumber();

}
