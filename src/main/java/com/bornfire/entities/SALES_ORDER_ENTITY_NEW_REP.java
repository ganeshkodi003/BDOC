package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SALES_ORDER_ENTITY_NEW_REP extends JpaRepository<SALES_ORDER_ENTITY_NEW,String> {

	@Query(value = "SELECT * FROM SALES_ORDER_TABLE_NEW", nativeQuery = true)
    List<SALES_ORDER_ENTITY_NEW> getPOlist();
    
    @Query(value = "\r\n" + 
    		"	WITH CTE AS (\r\n" + 
    		"    SELECT  \r\n" + 
    		"        *,  \r\n" + 
    		"        ROW_NUMBER() OVER (\r\n" + 
    		"            PARTITION BY WO_ID \r\n" + 
    		"            ORDER BY \r\n" + 
    		"                CAST(SUBSTRING(WO_ID, PATINDEX('%[0-9]%', WO_ID), LEN(WO_ID)) AS BIGINT) DESC, \r\n" + 
    		"                WO_ID DESC\r\n" + 
    		"        ) AS row_num \r\n" + 
    		"    FROM  \r\n" + 
    		"      SALES_ORDER_TABLE_NEW\r\n" + 
    		")\r\n" + 
    		"SELECT * \r\n" + 
    		"FROM CTE\r\n" + 
    		"WHERE row_num = 1\r\n" + 
    		"ORDER BY \r\n" + 
    		"    CAST(SUBSTRING(WO_ID, PATINDEX('%[0-9]%', WO_ID), LEN(WO_ID)) AS BIGINT) DESC,\r\n" + 
    		"    WO_ID DESC;\r\n" + 
    		"", nativeQuery = true)
    List<SALES_ORDER_ENTITY_NEW> getsalesdetails();
    
    @Query(value = "SELECT * FROM (SELECT *, ROW_NUMBER() OVER (PARTITION BY WO_ID ORDER BY WO_ID) AS row_num FROM SALES_ORDER_TABLE_NEW) AS subquery WHERE row_num = 1 AND APPROVED_FLG='Y';", nativeQuery = true)
    List<SALES_ORDER_ENTITY_NEW> getinvoice();
    
    @Query(value = "SELECT * FROM SALES_ORDER_TABLE_NEW where WO_ID=?1", nativeQuery = true)
    List<SALES_ORDER_ENTITY_NEW>  getparticular(String id);
    
    @Query(value = "SELECT cast(count(DISTINCT WO_ID) as INT) FROM SALES_ORDER_TABLE_NEW", nativeQuery = true)
    int getwOcount();
    
    @Query(value = "SELECT DISTINCT WO_ID  FROM SALES_ORDER_TABLE_NEW ORDER BY  WO_ID", nativeQuery = true)
    List<String>  getall();
    

    @Query(value = "\r\n" + 
    		"	WITH CTE AS (\r\n" + 
    		"    SELECT  \r\n" + 
    		"        *,  \r\n" + 
    		"        ROW_NUMBER() OVER (\r\n" + 
    		"            PARTITION BY WO_ID \r\n" + 
    		"            ORDER BY \r\n" + 
    		"                CAST(SUBSTRING(WO_ID, PATINDEX('%[0-9]%', WO_ID), LEN(WO_ID)) AS BIGINT) DESC, \r\n" + 
    		"                WO_ID DESC\r\n" + 
    		"        ) AS row_num \r\n" + 
    		"    FROM  \r\n" + 
    		"      SALES_ORDER_TABLE_NEW\r\n" + 
    		"WHERE BRANCH_ID = ?1\r\n"+
    		")\r\n" + 
    		"SELECT * \r\n" + 
    		"FROM CTE\r\n" + 
    		"WHERE row_num = 1\r\n" + 
    		"ORDER BY \r\n" + 
    		"    CAST(SUBSTRING(WO_ID, PATINDEX('%[0-9]%', WO_ID), LEN(WO_ID)) AS BIGINT) DESC,\r\n" + 
    		"    WO_ID DESC;\r\n" + 
    		"", nativeQuery = true)
    List<SALES_ORDER_ENTITY_NEW> getlistbyBranch(String BRANCH_ID);
    
    
}