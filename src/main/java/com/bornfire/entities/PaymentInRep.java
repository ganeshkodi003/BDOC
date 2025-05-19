package com.bornfire.entities;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentInRep extends JpaRepository<PaymentIn,String> {
	@Query(value = "SELECT CAST(current_value AS BIGINT) FROM sys.sequences WHERE name ='receiptNo'", nativeQuery = true)
	BigInteger getreceiptnoCode();
	
	@Query(value = "	WITH CTE AS (\r\n" + 
			"    SELECT  \r\n" + 
			"        *,  \r\n" + 
			"        ROW_NUMBER() OVER (\r\n" + 
			"            PARTITION BY vendorid \r\n" + 
			"            ORDER BY \r\n" + 
			"                CAST(SUBSTRING(vendorid, PATINDEX('%[0-9]%', vendorid), LEN(vendorid)) AS BIGINT) DESC, \r\n" + 
			"                vendorid DESC\r\n" + 
			"        ) AS row_num \r\n" + 
			"    FROM  \r\n" + 
			"      paymentin \r\n" + 
			")\r\n" + 
			"SELECT * \r\n" + 
			"FROM CTE\r\n" + 
			"WHERE row_num = 1\r\n" + 
			"ORDER BY \r\n" + 
			"    CAST(SUBSTRING(vendorid, PATINDEX('%[0-9]%', vendorid), LEN(vendorid)) AS BIGINT) DESC,\r\n" + 
			"    vendorid DESC;", nativeQuery = true)
	List<PaymentIn>getlist();
	
	  @Query(value = "SELECT * FROM paymentin WHERE vendorid = ?1", nativeQuery = true)
	    List<PaymentIn> getlistbyid(String vendorid);
	
}
