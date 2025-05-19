package com.bornfire.entities;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SALES_invoice_TABLERep extends JpaRepository<SALES_invoice_TABLE,String> {

	@Query(value = "SELECT * FROM (SELECT *, ROW_NUMBER() OVER (PARTITION BY WO_ID ORDER BY WO_ID) AS row_num FROM SALES_invoice_TABLE) AS subquery WHERE row_num = 1 ;", nativeQuery = true)
    List<SALES_invoice_TABLE> getsalesinvoicedetails();
    
    
    
    
    @Query(value = "SELECT * FROM SALES_invoice_TABLE where WO_ID=?1", nativeQuery = true)
    List<SALES_invoice_TABLE>  getparticular(String id);
    
    @Query(value = "SELECT * FROM SALES_invoice_TABLE WHERE EXTRA_AMOUNT > 0 AND EXTRA_AMOUNT IS NOT NULL", nativeQuery = true)
    List<SALES_invoice_TABLE> getunique();

    
    
    @Query(value = "SELECT WO_ID FROM SALES_invoice_TABLE where VENDOR_ID=?1", nativeQuery = true)
    List<Object[]>  getwolist(String VENDOR_ID);

    @Query(value = "SELECT * FROM SALES_invoice_TABLE WHERE VENDOR_ID = ?1", nativeQuery = true)
    List<SALES_invoice_TABLE> getbyVENDOR(String VENDOR_ID);

    
    
    @Query(value = "select cast(sum(balance_amount)as int) from SALES_invoice_TABLE where VENDOR_ID=?1", nativeQuery = true)
    Integer getbalance(String VENDOR_ID);		
    
    
    @Query(value = "select count(*) from SALES_invoice_TABLE", nativeQuery = true)
    Integer getcountfordashboard();
    
    
	/* ---dec order and apply partition-- */
    
    @Query(value = "WITH RankedInvoices AS (\r\n" + 
    		"    SELECT *, \r\n" + 
    		"           ROW_NUMBER() OVER (\r\n" + 
    		"               PARTITION BY WO_ID \r\n" + 
    		"               ORDER BY \r\n" + 
    		"                   CAST(SUBSTRING(WO_ID, PATINDEX('%[0-9]%', WO_ID), LEN(WO_ID)) AS BIGINT) DESC, \r\n" + 
    		"                   WO_ID DESC\r\n" + 
    		"           ) AS row_num\r\n" + 
    		"    FROM SALES_invoice_TABLE\r\n" + 
    		")\r\n" + 
    		"SELECT *\r\n" + 
    		"FROM RankedInvoices\r\n" + 
    		"WHERE row_num = 1\r\n" + 
    		"ORDER BY \r\n" + 
    		"    CAST(SUBSTRING(WO_ID, PATINDEX('%[0-9]%', WO_ID), LEN(WO_ID)) AS BIGINT) DESC, \r\n" + 
    		"    WO_ID DESC;\r\n" + 
    		"", nativeQuery = true)
    List<SALES_invoice_TABLE> getsalesinvoicedetailsnew();
    
    
    
    
    @Query(value = "WITH RankedInvoices AS ("
            + "    SELECT *, "
            + "           ROW_NUMBER() OVER ("
            + "               PARTITION BY WO_ID "
            + "               ORDER BY "
            + "                   CAST(SUBSTRING(WO_ID, PATINDEX('%[0-9]%', WO_ID), LEN(WO_ID)) AS BIGINT) DESC, "
            + "                   WO_ID DESC"
            + "           ) AS row_num "
            + "    FROM SALES_invoice_TABLE "
            + "    WHERE EXTRA_AMOUNT > 0 "  // Added condition for BALANCE_AMOUNT
            + ") "
            + "SELECT * "
            + "FROM RankedInvoices "
            + "WHERE row_num = 1 "
            + "ORDER BY "
            + "    CAST(SUBSTRING(WO_ID, PATINDEX('%[0-9]%', WO_ID), LEN(WO_ID)) AS BIGINT) DESC, "
            + "    WO_ID DESC;", 
           nativeQuery = true)
List<SALES_invoice_TABLE> getsalelist();

@Query(value = "WITH RankedInvoices AS (SELECT *, ROW_NUMBER() OVER (PARTITION BY WO_ID ORDER BY CAST(SUBSTRING(WO_ID, PATINDEX('%[0-9]%', WO_ID), LEN(WO_ID)) AS BIGINT) DESC, WO_ID DESC) AS row_num FROM SALES_invoice_TABLE WHERE deliverytype='YES') SELECT * FROM RankedInvoices WHERE row_num = 1 ORDER BY CAST(SUBSTRING(WO_ID, PATINDEX('%[0-9]%', WO_ID), LEN(WO_ID)) AS BIGINT) DESC,WO_ID DESC;",nativeQuery = true)
List<SALES_invoice_TABLE> getsaledlvlist();

@Query(value = "WITH RankedInvoices AS (SELECT *, ROW_NUMBER() OVER (PARTITION BY WO_ID ORDER BY CAST(SUBSTRING(WO_ID, PATINDEX('%[0-9]%', WO_ID), LEN(WO_ID)) AS BIGINT) DESC, WO_ID DESC) AS row_num FROM SALES_invoice_TABLE WHERE deliverytype='YES' and deliverystatus='C') SELECT * FROM RankedInvoices WHERE row_num = 1 ORDER BY CAST(SUBSTRING(WO_ID, PATINDEX('%[0-9]%', WO_ID), LEN(WO_ID)) AS BIGINT) DESC,WO_ID DESC;",nativeQuery = true)
List<SALES_invoice_TABLE> getdeliverycompletedlist();

@Query(value = "SELECT DISTINCT WO_ID FROM SALES_invoice_TABLE ORDER BY  WO_ID", nativeQuery = true)
List<String>  getall();


@Query(value = "WITH RankedInvoices AS (\r\n" + 
		"    SELECT *, \r\n" + 
		"           ROW_NUMBER() OVER (\r\n" + 
		"               PARTITION BY WO_ID \r\n" + 
		"               ORDER BY \r\n" + 
		"                   CAST(SUBSTRING(WO_ID, PATINDEX('%[0-9]%', WO_ID), LEN(WO_ID)) AS BIGINT) DESC, \r\n" + 
		"                   WO_ID DESC\r\n" + 
		"           ) AS row_num\r\n" + 
		"    FROM SALES_invoice_TABLE\r\n" + 
		"WHERE BRANCH_ID= ?1\r\n"+
		")\r\n" + 
		"SELECT *\r\n" + 
		"FROM RankedInvoices\r\n" + 
		"WHERE row_num = 1\r\n" + 
		"ORDER BY \r\n" + 
		"    CAST(SUBSTRING(WO_ID, PATINDEX('%[0-9]%', WO_ID), LEN(WO_ID)) AS BIGINT) DESC, \r\n" + 
		"    WO_ID DESC;\r\n" + 
		"", nativeQuery = true)
List<SALES_invoice_TABLE> getlistbyBranch(String BRANCH_ID);

@Query(value = "WITH RankedInvoices AS (SELECT *, ROW_NUMBER() OVER (PARTITION BY WO_ID ORDER BY CAST(SUBSTRING(WO_ID, PATINDEX('%[0-9]%', WO_ID), LEN(WO_ID)) AS BIGINT) DESC, WO_ID DESC) AS row_num FROM SALES_invoice_TABLE WHERE  BRANCH_ID =?1 AND deliverytype='YES') SELECT * FROM RankedInvoices WHERE row_num = 1 ORDER BY CAST(SUBSTRING(WO_ID, PATINDEX('%[0-9]%', WO_ID), LEN(WO_ID)) AS BIGINT) DESC,WO_ID DESC;",nativeQuery = true)
List<SALES_invoice_TABLE> getbranch_devry(String BRANCH_ID);
    		

@Query(value = "WITH RankedInvoices AS (SELECT *, ROW_NUMBER() OVER (PARTITION BY WO_ID ORDER BY CAST(SUBSTRING(WO_ID, PATINDEX('%[0-9]%', WO_ID), LEN(WO_ID)) AS BIGINT) DESC, WO_ID DESC) AS row_num FROM SALES_invoice_TABLE WHERE BRANCH_ID =?1 AND deliverytype='YES' AND deliverystatus='C') SELECT * FROM RankedInvoices WHERE row_num = 1 ORDER BY CAST(SUBSTRING(WO_ID, PATINDEX('%[0-9]%', WO_ID), LEN(WO_ID)) AS BIGINT) DESC,WO_ID DESC;",nativeQuery = true)
List<SALES_invoice_TABLE> getbranchcompletedlist(String BRANCH_ID);

@Query(value = "WITH RankedInvoices AS (\r\n" + 
		"    SELECT *, \r\n" + 
		"           ROW_NUMBER() OVER (\r\n" + 
		"               PARTITION BY WO_ID \r\n" + 
		"               ORDER BY \r\n" + 
		"                   CAST(SUBSTRING(WO_ID, PATINDEX('%[0-9]%', WO_ID), LEN(WO_ID)) AS BIGINT) DESC, \r\n" + 
		"                   WO_ID DESC\r\n" + 
		"           ) AS row_num\r\n" + 
		"    FROM SALES_invoice_TABLE\r\n" +
		"WHERE BRANCH_ID= ?1\r\n"+
		")\r\n" + 
		"SELECT *\r\n" + 
		"FROM RankedInvoices\r\n" + 
		"WHERE row_num = 1\r\n" + 
		"ORDER BY \r\n" + 
		"    CAST(SUBSTRING(WO_ID, PATINDEX('%[0-9]%', WO_ID), LEN(WO_ID)) AS BIGINT) DESC, \r\n" + 
		"    WO_ID DESC;\r\n" + 
		"", nativeQuery = true)
List<SALES_invoice_TABLE> getbranch_return(String BRANCH_ID);

@Query(value = "SELECT * FROM SALES_invoice_TABLE WHERE BRANCH_ID = ?1 AND EXTRA_AMOUNT > 0 AND EXTRA_AMOUNT IS NOT NULL", nativeQuery = true)
List<SALES_invoice_TABLE> getbranch_payIn(String BRANCH_ID);

@Query(value = "SELECT * FROM SALES_invoice_TABLE WHERE ID IN (" +
        "SELECT MIN(ID) FROM SALES_invoice_TABLE " +
        "GROUP BY WO_ID)", nativeQuery = true)
List<SALES_invoice_TABLE> getsaleInvTotalAmount();
@Query(value = "SELECT * FROM SALES_invoice_TABLE WHERE ID IN (" +
        "SELECT MIN(ID) FROM SALES_invoice_TABLE " +
        "WHERE BRANCH_ID = :branchId GROUP BY WO_ID)", nativeQuery = true)
List<SALES_invoice_TABLE> getsaleInvTotalAmountee(@Param("branchId") String branchId);


@Query(value = "SELECT * FROM SALES_invoice_TABLE " +
        "WHERE approved_flg = 'S' " +
        "AND (:branchId IS NULL OR branch_id = :branchId) " +
        "AND (:fromDate IS NULL OR ORDER_DATE >= :fromDate) " +
        "AND (:toDate IS NULL OR ORDER_DATE <= :toDate)",
       nativeQuery = true)
List<SALES_invoice_TABLE> getsaleInvTotalAmount(
        @Param("branchId") String branchId,
        @Param("fromDate") Date fromDate,
        @Param("toDate") Date toDate
);

@Query(value = "SELECT * FROM SALES_invoice_TABLE " +
        "WHERE approved_flg = 'S' " +
        "AND (:vendor IS NULL OR VENDOR_ID = :vendor) " +
        "AND (:branchId IS NULL OR branch_id = :branchId) " +
        "AND (:fromDate IS NULL OR ORDER_DATE >= :fromDate) " +
        "AND (:toDate IS NULL OR ORDER_DATE <= :toDate) " +
        "ORDER BY ORDER_DATE DESC",
       nativeQuery = true)
List<SALES_invoice_TABLE> getsaleuniq(
        @Param("vendor") String vendor,
        @Param("branchId") String branchId,
        @Param("fromDate") Date fromDate,
        @Param("toDate") Date toDate
);

@Query(value = "SELECT * FROM SALES_invoice_TABLE " +
        "WHERE approved_flg = 'S' " +
        "AND (:vendor IS NULL OR VENDOR_ID = :vendor) " +
        "AND (:branchId IS NULL OR branch_id = :branchId) " +
        "ORDER BY ORDER_DATE DESC",
       nativeQuery = true)
List<SALES_invoice_TABLE> getsaleuniqss(
        @Param("vendor") String vendor,
        @Param("branchId") String branchId
);


}
