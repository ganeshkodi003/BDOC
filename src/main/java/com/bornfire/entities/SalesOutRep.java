package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesOutRep extends JpaRepository<SalesOut,String> {

	@Query(value = "SELECT * FROM (SELECT *, ROW_NUMBER() OVER (PARTITION BY WO_ID ORDER BY WO_ID) AS row_num FROM SALES_OUT) AS subquery WHERE row_num = 1 ;", nativeQuery = true)
    List<SalesOut> getsalesinvoicedetails();
    
    
    
    
    @Query(value = "SELECT * FROM SALES_OUT where WO_ID=?1", nativeQuery = true)
    List<SalesOut>  getparticular(String id);
    
    @Query(value = "SELECT * FROM SALES_OUT WHERE EXTRA_AMOUNT > 0 AND EXTRA_AMOUNT IS NOT NULL", nativeQuery = true)
    List<SalesOut> getunique();

    
    
    @Query(value = "SELECT WO_ID FROM SALES_OUT where VENDOR_ID=?1", nativeQuery = true)
    List<Object[]>  getwolist(String VENDOR_ID);

    @Query(value = "SELECT * FROM SALES_OUT WHERE VENDOR_ID = ?1", nativeQuery = true)
    List<SalesOut> getbyVENDOR(String VENDOR_ID);

    
    
    @Query(value = "select cast(sum(balance_amount)as int) from SALES_OUT where VENDOR_ID=?1", nativeQuery = true)
    Integer getbalance(String VENDOR_ID);		
    
    
    @Query(value = "select count(*) from SALES_OUT", nativeQuery = true)
    Integer getcountfordashboard();
    
    
	/* ---dec order and apply partition-- */
    
    @Query(value = "WITH RankedInvoices AS (SELECT *, ROW_NUMBER() OVER (PARTITION BY WO_ID ORDER BY CAST(SUBSTRING(WO_ID, PATINDEX('%[0-9]%', WO_ID), LEN(WO_ID)) AS BIGINT) DESC, WO_ID DESC) AS row_num FROM SALES_OUT) SELECT * FROM RankedInvoices WHERE row_num = 1 ORDER BY CAST(SUBSTRING(WO_ID, PATINDEX('%[0-9]%', WO_ID), LEN(WO_ID)) AS BIGINT) DESC, WO_ID DESC", nativeQuery = true)

    List<SalesOut> getsalesinvoicedetailsnew();
    
    @Query(value = "WITH RankedInvoices AS (SELECT *, ROW_NUMBER() OVER (PARTITION BY WO_ID ORDER BY CAST(SUBSTRING(WO_ID, PATINDEX('%[0-9]%', WO_ID), LEN(WO_ID)) AS BIGINT) DESC, WO_ID DESC) AS row_num FROM SALES_OUT WHERE returning_flg ='N') SELECT * FROM RankedInvoices WHERE row_num = 1 ORDER BY CAST(SUBSTRING(WO_ID, PATINDEX('%[0-9]%', WO_ID), LEN(WO_ID)) AS BIGINT) DESC, WO_ID DESC", nativeQuery = true)

    List<SalesOut> getsalesindetailsnew();

    
    
    
    @Query(value = "WITH RankedInvoices AS ("
            + "    SELECT *, "
            + "           ROW_NUMBER() OVER ("
            + "               PARTITION BY WO_ID "
            + "               ORDER BY "
            + "                   CAST(SUBSTRING(WO_ID, PATINDEX('%[0-9]%', WO_ID), LEN(WO_ID)) AS BIGINT) DESC, "
            + "                   WO_ID DESC"
            + "           ) AS row_num "
            + "    FROM SALES_OUT "
            + "    WHERE EXTRA_AMOUNT > 0 "  // Added condition for BALANCE_AMOUNT
            + ") "
            + "SELECT * "
            + "FROM RankedInvoices "
            + "WHERE row_num = 1 "
            + "ORDER BY "
            + "    CAST(SUBSTRING(WO_ID, PATINDEX('%[0-9]%', WO_ID), LEN(WO_ID)) AS BIGINT) DESC, "
            + "    WO_ID DESC;", 
           nativeQuery = true)
List<SalesOut> getsalelist();

@Query(value = "WITH RankedInvoices AS (SELECT *, ROW_NUMBER() OVER (PARTITION BY WO_ID ORDER BY CAST(SUBSTRING(WO_ID, PATINDEX('%[0-9]%', WO_ID), LEN(WO_ID)) AS BIGINT) DESC, WO_ID DESC) AS row_num FROM SALES_OUT WHERE deliverytype='YES') SELECT * FROM RankedInvoices WHERE row_num = 1 ORDER BY CAST(SUBSTRING(WO_ID, PATINDEX('%[0-9]%', WO_ID), LEN(WO_ID)) AS BIGINT) DESC,WO_ID DESC;",nativeQuery = true)
List<SalesOut> getsaledlvlist();

@Query(value = "WITH RankedInvoices AS (SELECT *, ROW_NUMBER() OVER (PARTITION BY WO_ID ORDER BY CAST(SUBSTRING(WO_ID, PATINDEX('%[0-9]%', WO_ID), LEN(WO_ID)) AS BIGINT) DESC, WO_ID DESC) AS row_num FROM SALES_OUT WHERE deliverytype='YES' and deliverystatus='C') SELECT * FROM RankedInvoices WHERE row_num = 1 ORDER BY CAST(SUBSTRING(WO_ID, PATINDEX('%[0-9]%', WO_ID), LEN(WO_ID)) AS BIGINT) DESC,WO_ID DESC;",nativeQuery = true)
List<SalesOut> getdeliverycompletedlist();

@Query(value = "SELECT DISTINCT WO_ID FROM SALES_OUT ORDER BY  WO_ID", nativeQuery = true)
List<String>  getall();

@Query(value = "WITH RankedInvoices AS (SELECT *, ROW_NUMBER() OVER (PARTITION BY WO_ID ORDER BY CAST(SUBSTRING(WO_ID, PATINDEX('%[0-9]%', WO_ID), LEN(WO_ID)) AS BIGINT) DESC, WO_ID DESC) AS row_num FROM SALES_OUT WHERE BRANCH_ID = ?1) SELECT * FROM RankedInvoices WHERE row_num = 1 ORDER BY CAST(SUBSTRING(WO_ID, PATINDEX('%[0-9]%', WO_ID), LEN(WO_ID)) AS BIGINT) DESC, WO_ID DESC", nativeQuery = true)
List<SalesOut> getlistbyBranch(String BRANCH_ID);


@Query(value = "WITH RankedInvoices AS (SELECT *, ROW_NUMBER() OVER (PARTITION BY WO_ID ORDER BY CAST(SUBSTRING(WO_ID, PATINDEX('%[0-9]%', WO_ID), LEN(WO_ID)) AS BIGINT) DESC, WO_ID DESC) AS row_num FROM SALES_OUT WHERE  BRANCH_ID =?1 AND deliverytype='YES') SELECT * FROM RankedInvoices WHERE row_num = 1 ORDER BY CAST(SUBSTRING(WO_ID, PATINDEX('%[0-9]%', WO_ID), LEN(WO_ID)) AS BIGINT) DESC,WO_ID DESC;",nativeQuery = true)
List<SalesOut> getbranch_devry(String BRANCH_ID);
    		

@Query(value = "WITH RankedInvoices AS (SELECT *, ROW_NUMBER() OVER (PARTITION BY WO_ID ORDER BY CAST(SUBSTRING(WO_ID, PATINDEX('%[0-9]%', WO_ID), LEN(WO_ID)) AS BIGINT) DESC, WO_ID DESC) AS row_num FROM SalesOut WHERE BRANCH_ID =?1 AND deliverytype='YES' AND deliverystatus='C') SELECT * FROM RankedInvoices WHERE row_num = 1 ORDER BY CAST(SUBSTRING(WO_ID, PATINDEX('%[0-9]%', WO_ID), LEN(WO_ID)) AS BIGINT) DESC,WO_ID DESC;",nativeQuery = true)
List<SalesOut> getbranchcompletedlist(String BRANCH_ID);

@Query(value = "WITH RankedInvoices AS (\r\n" + 
		"    SELECT *, \r\n" + 
		"           ROW_NUMBER() OVER (\r\n" + 
		"               PARTITION BY WO_ID \r\n" + 
		"               ORDER BY \r\n" + 
		"                   CAST(SUBSTRING(WO_ID, PATINDEX('%[0-9]%', WO_ID), LEN(WO_ID)) AS BIGINT) DESC, \r\n" + 
		"                   WO_ID DESC\r\n" + 
		"           ) AS row_num\r\n" + 
		"    FROM SalesOut\r\n" +
		"WHERE BRANCH_ID= ?1\r\n"+
		")\r\n" + 
		"SELECT *\r\n" + 
		"FROM RankedInvoices\r\n" + 
		"WHERE row_num = 1\r\n" + 
		"ORDER BY \r\n" + 
		"    CAST(SUBSTRING(WO_ID, PATINDEX('%[0-9]%', WO_ID), LEN(WO_ID)) AS BIGINT) DESC, \r\n" + 
		"    WO_ID DESC;\r\n" + 
		"", nativeQuery = true)
List<SalesOut> getbranch_return(String BRANCH_ID);

@Query(value = "SELECT * FROM SalesOut WHERE BRANCH_ID = ?1 AND EXTRA_AMOUNT > 0 AND EXTRA_AMOUNT IS NOT NULL", nativeQuery = true)
List<SalesOut> getbranch_payIn(String BRANCH_ID);


}
