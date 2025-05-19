package com.bornfire.entities;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;



import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface Transaction_table_rep extends JpaRepository<Transaction_table, String>{
	
	@Query(value = "SELECT * FROM TRANSACTION_TABLE WHERE PO_ID = ?1 ORDER BY TRAN_ID", nativeQuery = true)
	List<Transaction_table> getbyId(String po_id);
	
	@Query(value = "SELECT * FROM TRANSACTION_TABLE WHERE tran_type='CASH'  ORDER BY ACCT_CLS_DATE, tran_id", nativeQuery = true)
	List<Transaction_table> getcashtran();
	
	@Query(value = "SELECT * FROM TRANSACTION_TABLE WHERE tran_type='Account' or tran_type='cheque' or tran_type='Rtgs' ORDER BY ACCT_CLS_DATE, tran_id", nativeQuery = true)
	List<Transaction_table> getbanktran();
	
	
	
	
	
	@Query(value = "	WITH CTE AS (\r\n" + 
			"    SELECT *, \r\n" + 
			"           ROW_NUMBER() OVER (\r\n" + 
			"               PARTITION BY TRAN_ID \r\n" + 
			"               ORDER BY CAST(SUBSTRING(TRAN_ID, PATINDEX('%[0-9]%', TRAN_ID), LEN(TRAN_ID)) AS BIGINT) DESC, TRAN_ID DESC\r\n" + 
			"           ) AS row_num \r\n" + 
			"    FROM TRANSACTION_TABLE\r\n" + 
			") \r\n" + 
			"SELECT * \r\n" + 
			"FROM CTE \r\n" + 
			"WHERE row_num = 1 \r\n" + 
			"ORDER BY CAST(SUBSTRING(TRAN_ID, PATINDEX('%[0-9]%', TRAN_ID), LEN(TRAN_ID)) AS BIGINT) DESC, TRAN_ID DESC;\r\n" + 
			"", nativeQuery = true)
	List<Transaction_table> getListoffice();

	@Query(value = "SELECT * FROM TRANSACTION_TABLE WHERE ACCT_NUM = ?1 ORDER BY CLASSIFICATION ASC", nativeQuery = true)
	List<Transaction_table> getlistbyACC_no(String ACCT_NUM);

	
	@Query(value = "SELECT * FROM TRANSACTION_TABLE WHERE ACCT_CLS_DATE BETWEEN :fromdate AND :todate ORDER BY CLASSIFICATION ASC", nativeQuery = true)
	List<Transaction_table> getTransactionsByDate(@Param("fromdate") String fromdate, @Param("todate") String todate);


}
