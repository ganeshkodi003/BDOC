
package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SALES_invoice_TABLE_PAY_IN_REP extends JpaRepository<SALES_invoice_TABLE_PAY_IN,String> {

	
	
	 @Query(value = "	WITH DuplicateCheck AS (\r\n" + 
		   		"    SELECT \r\n" + 
		   		"        *, -- Includes all related columns\r\n" + 
		   		"        ROW_NUMBER() OVER (PARTITION BY WO_ID, PAID_AMOUNT ORDER BY WO_ID) AS RowNum\r\n" + 
		   		"    FROM \r\n" + 
		   		"        SALES_invoice_TABLE_PAY_IN\r\n" + 
		   		")\r\n" + 
		   		"SELECT \r\n" + 
		   		"    *\r\n" + 
		   		"FROM \r\n" + 
		   		"    DuplicateCheck\r\n" + 
		   		"WHERE \r\n" + 
		   		"    RowNum = 1;", nativeQuery = true)
		    List<SALES_invoice_TABLE_PAY_IN> getbyid();
	
		    
		    
		    

			
			 @Query(value = "	WITH DuplicateCheck AS (\r\n" + 
				   		"    SELECT \r\n" + 
				   		"        *, -- Includes all related columns\r\n" + 
				   		"        ROW_NUMBER() OVER (PARTITION BY WO_ID, PAID_AMOUNT ORDER BY WO_ID) AS RowNum\r\n" + 
				   		"    FROM \r\n" + 
				   		"        SALES_invoice_TABLE_PAY_IN\r\n" +
"WHERE BRANCH_ID= ?1\r\n"+
				   		")\r\n" + 
				   		"SELECT \r\n" + 
				   		"    *\r\n" + 
				   		"FROM \r\n" + 
				   		"    DuplicateCheck\r\n" + 
				   		"WHERE \r\n" + 
				   		"    RowNum = 1;", nativeQuery = true)
				    List<SALES_invoice_TABLE_PAY_IN> getbranch_payInHistory(String BRANCH_ID);
				    
	/*
	@Query(value = "SELECT * FROM (SELECT *, ROW_NUMBER() OVER (PARTITION BY WO_ID ORDER BY WO_ID) AS row_num FROM hrms.SALES_invoice_TABLE) AS subquery WHERE row_num = 1 ;", nativeQuery = true)
    List<SALES_invoice_TABLE> getsalesinvoicedetails();
    
    
    
    
    @Query(value = "SELECT * FROM hrms.SALES_invoice_TABLE where WO_ID=?1", nativeQuery = true)
    List<SALES_invoice_TABLE>  getparticular(String id);
    
    
    
    @Query(value = "SELECT WO_ID FROM hrms.SALES_invoice_TABLE where VENDOR_ID=?1", nativeQuery = true)
    List<Object[]>  getwolist(String VENDOR_ID);

    @Query(value = "SELECT * FROM hrms.SALES_invoice_TABLE WHERE VENDOR_ID = ?1", nativeQuery = true)
    List<SALES_invoice_TABLE> getbyVENDOR(String VENDOR_ID);

    
    
    @Query(value = "select cast(sum(balance_amount)as int) from hrms.SALES_invoice_TABLE where VENDOR_ID=?1", nativeQuery = true)
    Integer getbalance(String VENDOR_ID);		
    
    
    @Query(value = "select count(*) from hrms.SALES_invoice_TABLE", nativeQuery = true)
    Integer getcountfordashboard();
    
    
    @Query(value = "WITH RankedInvoices AS (\r\n" + 
    		"    SELECT *, \r\n" + 
    		"           ROW_NUMBER() OVER (\r\n" + 
    		"               PARTITION BY WO_ID \r\n" + 
    		"               ORDER BY \r\n" + 
    		"                   CAST(SUBSTRING(WO_ID, PATINDEX('%[0-9]%', WO_ID), LEN(WO_ID)) AS BIGINT) DESC, \r\n" + 
    		"                   WO_ID DESC\r\n" + 
    		"           ) AS row_num\r\n" + 
    		"    FROM hrms.SALES_invoice_TABLE\r\n" + 
    		")\r\n" + 
    		"SELECT *\r\n" + 
    		"FROM RankedInvoices\r\n" + 
    		"WHERE row_num = 1\r\n" + 
    		"ORDER BY \r\n" + 
    		"    CAST(SUBSTRING(WO_ID, PATINDEX('%[0-9]%', WO_ID), LEN(WO_ID)) AS BIGINT) DESC, \r\n" + 
    		"    WO_ID DESC;\r\n" + 
    		"", nativeQuery = true)
    List<SALES_invoice_TABLE> getsalesinvoicedetailsnew();*/
    		
}
