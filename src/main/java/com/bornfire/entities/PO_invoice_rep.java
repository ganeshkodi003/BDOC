
package com.bornfire.entities;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PO_invoice_rep  extends JpaRepository<PO_invoice_entity,String> {

	@Query(value = "SELECT * FROM (SELECT *, ROW_NUMBER() OVER (PARTITION BY po_id ORDER BY po_id) AS row_num FROM PO_INVOICE_TABLE) AS subquery WHERE row_num = 1;", nativeQuery = true)
    List<PO_invoice_entity> getPOlist();
    
	@Query(value = "SELECT * FROM (SELECT *, ROW_NUMBER() OVER (PARTITION BY po_id ORDER BY po_id) AS row_num FROM PO_INVOICE_TABLE) AS subquery WHERE row_num = 1 AND APPROVED_FLG = 'S';", nativeQuery = true)
    List<PO_invoice_entity> getPOlistbyapproval();
    
    
    @Query(value = "SELECT item_code ,mAX(ITEM)as Name,mAX(unit) as unit, sum(useingqty) FROM PO_INVOICE_TABLE WHERE useingqty>0 and Assetcategory='B01A' group by item_code", nativeQuery = true)
    List<Object[]> BATCHWITHSTOCK();
    
    @Query(value = "SELECT * FROM PO_INVOICE_TABLE WHERE useingqty>0 and Assetcategory='B01A' And item_code=?1 ", nativeQuery = true)
    List<PO_invoice_entity> BATCHWITHSTOCKcode(String item_code);
    
    @Query(value = "SELECT * FROM PO_INVOICE_TABLE WHERE useingqty<=0 and Assetcategory='B01A' ", nativeQuery = true)
    List<PO_invoice_entity> BATCHWITHOUTSTOCK();
    
    @Query(value = "SELECT item_code ,mAX(ITEM)as Name,mAX(unit) as unit, sum(useingqty) FROM PO_INVOICE_TABLE WHERE useingqty>0 and Assetcategory='B01B' group by item_code ", nativeQuery = true)
    List<Object[]> BATCHWITHSTOCKPackingMaterials();
    
    @Query(value = "SELECT * FROM PO_INVOICE_TABLE WHERE useingqty>0 and Assetcategory='B01B' And item_code=?1 ", nativeQuery = true)
    List<PO_invoice_entity> pmWITHSTOCKcode(String item_code);
    
    
    @Query(value = "SELECT * FROM PO_INVOICE_TABLE WHERE useingqty<=0 and Assetcategory='B01B' ", nativeQuery = true)
    List<PO_invoice_entity> BATCHWITHOUTSTOCKPackingMaterials();
    
    @Query(value = "SELECT * FROM PO_INVOICE_TABLE WHERE useingqty>0 and Assetcategory='B01E' ", nativeQuery = true)
    List<PO_invoice_entity> BATCHWITHSTOCKStoresandspares();
    
    @Query(value = "SELECT * FROM PO_INVOICE_TABLE WHERE useingqty<=0 and Assetcategory='B01E' ", nativeQuery = true)
    List<PO_invoice_entity> BATCHWITHOUTSTOCKStoresandspares();
    

    @Query(value = "SELECT * FROM PO_INVOICE_TABLE WHERE PO_ID = ?1", nativeQuery = true)
    List<PO_invoice_entity> getbyid(String poId);
    
    @Query(value = "SELECT * FROM PO_INVOICE_TABLE WHERE VENDOR_ID = ?1", nativeQuery = true)
    List<PO_invoice_entity>  vendorid(String vendorId);


    @Query(value = "SELECT cast(count(DISTINCT po_id ) as INT) FROM PO_INVOICE_TABLE", nativeQuery = true)
    int getPOcount();
    
    @Query(value = "SELECT * FROM PO_INVOICE_TABLE WHERE ITEM_CODE = ?1", nativeQuery = true)
    List<PO_invoice_entity>  getdetailbatch(String itemCode);
    
    @Query(value = "select count(*) from PO_INVOICE_TABLE", nativeQuery = true)
    Integer getdetails();
    
    @Query(value = "select count(*) from PO_INVOICE_TABLE where Assetcategory='B01A' ", nativeQuery = true)
    Integer getdetailsRAWMATERIALS();
    
    @Query(value = "	select count(*) from PO_INVOICE_TABLE where Assetcategory='B01B' and approved_flg = 'S' ", nativeQuery = true)
    Integer getdetailsPACKING();
    
    @Query(value = "	select count(*) from PO_INVOICE_TABLE where Assetcategory='Finished Goods' and approved_flg = 'S' ", nativeQuery = true)
    Integer getdetailsfinished();
    
    @Query(value = "select count(*) from PO_INVOICE_TABLE where Assetcategory='B01E' and approved_flg = 'S' ", nativeQuery = true)
    Integer getdetailsSS();

    @Query(value = "SELECT * FROM PO_INVOICE_TABLE WHERE ITEM = ?1", nativeQuery = true)
    List<PO_invoice_entity>  getbatchonly(String ITEM);
    
    @Query(value = "SELECT DISTINCT ITEM FROM PO_INVOICE_TABLE", nativeQuery = true)
    List<String> getItems();

    @Query(value = "SELECT * FROM PO_INVOICE_TABLE WHERE batch = ?1", nativeQuery = true)
    PO_invoice_entity  get_twos(String batch);
    
    
	@Query(value = "WITH CTE AS (SELECT *, ROW_NUMBER() OVER (PARTITION BY po_id ORDER BY CAST(SUBSTRING(po_id, PATINDEX('%[0-9]%', po_id), LEN(po_id)) AS BIGINT) DESC, po_id DESC) AS row_num FROM PO_INVOICE_TABLE WHERE approved_flg = 'S') SELECT * FROM CTE WHERE row_num = 1 ORDER BY CAST(SUBSTRING(po_id, PATINDEX('%[0-9]%', po_id), LEN(po_id)) AS BIGINT) DESC, po_id DESC", nativeQuery = true)
    List<PO_invoice_entity> getPOlistbyapprovalnew();
    
    @Query(value = "WITH CTE AS (SELECT *, ROW_NUMBER() OVER (PARTITION BY po_id ORDER BY CAST(SUBSTRING(po_id, PATINDEX('%[0-9]%', po_id), LEN(po_id)) AS BIGINT) DESC, po_id DESC) AS row_num FROM PO_INVOICE_TABLE WHERE approved_flg = 'S' and useingqty>0) SELECT * FROM CTE WHERE row_num = 1 ORDER BY CAST(SUBSTRING(po_id, PATINDEX('%[0-9]%', po_id), LEN(po_id)) AS BIGINT) DESC, po_id DESC", nativeQuery = true)
    List<PO_invoice_entity> getPOlistbywithqnt();
    
	@Query(value = " WITH CTE AS (SELECT *, ROW_NUMBER() OVER (PARTITION BY po_id ORDER BY CAST(SUBSTRING(po_id, PATINDEX('%[0-9]%', po_id), LEN(po_id)) AS BIGINT) DESC, po_id DESC) AS row_num FROM PO_INVOICE_TABLE WHERE approved_flg='S')SELECT* FROM CTE WHERE row_num = 1 AND EXTRA_AMOUNT>0 ORDER BY CAST(SUBSTRING(po_id, PATINDEX('%[0-9]%', po_id), LEN(po_id)) AS BIGINT) DESC, po_id DESC", nativeQuery = true)
	List<PO_invoice_entity> getPOlistbyapprovalnewPPAYOuT();
	
	@Query(value = " WITH CTE AS (SELECT *, ROW_NUMBER() OVER (PARTITION BY po_id ORDER BY CAST(SUBSTRING(po_id, PATINDEX('%[0-9]%', po_id), LEN(po_id)) AS BIGINT) DESC, po_id DESC) AS row_num FROM PO_INVOICE_TABLE WHERE approved_flg='S')SELECT* FROM CTE WHERE row_num = 1 AND EXTRA_AMOUNT>0 AND VENDOR_ID=?1 ORDER BY CAST(SUBSTRING(po_id, PATINDEX('%[0-9]%', po_id), LEN(po_id)) AS BIGINT) DESC, po_id DESC", nativeQuery = true)

	List<PO_invoice_entity> getPOlistbyvendorandpayment (String VENDOR_ID);


    @Query(value = "SELECT distinct po_id FROM PO_INVOICE_TABLE ORDER BY po_id", nativeQuery = true)
    List<String> getall();

    @Query(value = "WITH CTE AS (\r\n" + 
    		"    SELECT \r\n" + 
    		"        t.po_id,\r\n" + 
    		"        t.approved_flg,\r\n" + 
    		"        t.extra_amount,\r\n" + 
    		"        -- add other columns you need explicitly here (Oracle does not support SELECT * in combination with analytic functions cleanly)\r\n" + 
    		"        ROW_NUMBER() OVER (\r\n" + 
    		"            PARTITION BY t.po_id \r\n" + 
    		"            ORDER BY TO_NUMBER(REGEXP_SUBSTR(t.po_id, '\\d+')) DESC, t.po_id DESC\r\n" + 
    		"        ) AS row_num\r\n" + 
    		"    FROM PO_INVOICE_TABLE t\r\n" + 
    		"    WHERE t.approved_flg = 'S' AND t.extra_amount > 0\r\n" + 
    		")\r\n" + 
    		"SELECT *\r\n" + 
    		"FROM CTE\r\n" + 
    		"WHERE row_num = 1\r\n" + 
    		"ORDER BY TO_NUMBER(REGEXP_SUBSTR(po_id, '\\d+')) DESC, po_id DESC\r\n" + 
    		"", 
           nativeQuery = true)
List<PO_invoice_entity> getpolist();


@Query(value = " SELECT * FROM PO_INVOICE_TABLE p WHERE p.assetcode LIKE 'C%' ", nativeQuery = true)
List<PO_invoice_entity> getimmovableasssets();



@Query(value = "WITH CTE AS (SELECT *, ROW_NUMBER() OVER (PARTITION BY po_id ORDER BY CAST(SUBSTRING(po_id, PATINDEX('%[0-9]%', po_id), LEN(po_id)) AS BIGINT) DESC, po_id DESC) AS row_num FROM PO_INVOICE_TABLE WHERE BRANCH_ID= ?1 AND approved_flg = 'S') SELECT * FROM CTE WHERE row_num = 1 ORDER BY CAST(SUBSTRING(po_id, PATINDEX('%[0-9]%', po_id), LEN(po_id)) AS BIGINT) DESC, po_id DESC", nativeQuery = true)
List<PO_invoice_entity> getbranch_POInv(String BRANCH_ID);


@Query(value = "WITH CTE AS (SELECT *, ROW_NUMBER() OVER (PARTITION BY po_id ORDER BY CAST(SUBSTRING(po_id, PATINDEX('%[0-9]%', po_id), LEN(po_id)) AS BIGINT) DESC, po_id DESC) AS row_num FROM PO_INVOICE_TABLE WHERE  BRANCH_ID= ?1 AND approved_flg = 'S' and useingqty>0) SELECT * FROM CTE WHERE row_num = 1 ORDER BY CAST(SUBSTRING(po_id, PATINDEX('%[0-9]%', po_id), LEN(po_id)) AS BIGINT) DESC, po_id DESC", nativeQuery = true)
List<PO_invoice_entity> getbranch_POReturn(String BRANCH_ID);


@Query(value = "SELECT item_code ,mAX(ITEM)as Name,mAX(unit) as unit, sum(useingqty) FROM PO_INVOICE_TABLE WHERE BRANCH_ID= ?1 AND useingqty>0 and Assetcategory='B01A' group by item_code", nativeQuery = true)
List<Object[]> BATCHWITHSTOCK_one(String BRANCH_ID);


@Query(value = "SELECT * FROM PO_INVOICE_TABLE WHERE BRANCH_ID= ?1 AND useingqty<=0 and Assetcategory='B01A' ", nativeQuery = true)
List<PO_invoice_entity> BATCHWITHOUTSTOCK_one(String BRANCH_ID);


@Query(value = "SELECT * FROM PO_INVOICE_TABLE WHERE BRANCH_ID= ?1 AND useingqty>0 and Assetcategory='B01E' ", nativeQuery = true)
List<PO_invoice_entity> BATCHWITHSTOCKStoresandspares_one(String BRANCH_ID);



@Query(value = "SELECT item_code ,mAX(ITEM)as Name,mAX(unit) as unit, sum(useingqty) FROM PO_INVOICE_TABLE WHERE BRANCH_ID= ?1 AND useingqty>0 and Assetcategory='B01B' group by item_code ", nativeQuery = true)
List<Object[]> BATCHWITHSTOCKPackingMaterials_one(String BRANCH_ID);

@Query(value = "SELECT * FROM PO_INVOICE_TABLE WHERE ID IN (" +
        "SELECT MIN(ID) FROM PO_INVOICE_TABLE " +
        "WHERE approved_flg = 'S' GROUP BY PO_ID)", nativeQuery = true)
List<PO_invoice_entity> getPOInvTotalAmount();

@Query(value = "SELECT * FROM PO_INVOICE_TABLE WHERE ID IN (" +
        "SELECT MIN(ID) FROM PO_INVOICE_TABLE " +
        "WHERE approved_flg = 'S' AND BRANCH_ID = :branchId GROUP BY PO_ID)", nativeQuery = true)
List<PO_invoice_entity> getPOInvTotalAmountee(@Param("branchId") String branchId);



@Query(value = "SELECT item_code, MAX(ITEM) as Name, MAX(unit) as unit, " +
        "SUM(useingqty) as useingqty, MAX(order_date) as orderDate " +
        "FROM PO_INVOICE_TABLE " +
        "WHERE useingqty > 0 AND Assetcategory = 'B01A' " +
        "GROUP BY item_code", 
nativeQuery = true)
List<Object[]> getStockAndDate();
@Query(value = "SELECT item_code, MAX(ITEM) as Name, MAX(unit) as unit, " +
        "SUM(useingqty) as useingqty, MAX(order_date) as orderDate " +
        "FROM PO_INVOICE_TABLE " +
        "WHERE useingqty > 0 AND Assetcategory = 'B01A' " +
        "AND BRANCH_ID = :branchId " +
        "GROUP BY item_code", 
nativeQuery = true)
List<Object[]> getStockAndDateff(@Param("branchId") String branchId);


@Query(value = "SELECT * FROM PO_INVOICE_TABLE " +
        "WHERE approved_flg = 'S' " +
        "AND (:branchId IS NULL OR branch_id = :branchId) " +
        "AND (:fromDate IS NULL OR ORDER_DATE >= :fromDate) " +
        "AND (:toDate IS NULL OR ORDER_DATE <= :toDate)",
       nativeQuery = true)
List<PO_invoice_entity> getPOInvTotalAmount(
        @Param("branchId") String branchId,
        @Param("fromDate") Date fromDate,
        @Param("toDate") Date toDate
);



@Query(value = "SELECT * FROM PO_INVOICE_TABLE " +
        "WHERE useingqty > 0 " +
        "  AND Assetcategory = 'B01A' " +
        "  AND (:branchId IS NULL OR branch_id = :branchId) " +
        "  AND (:fromDate IS NULL OR ORDER_DATE >= :fromDate) " +
        "  AND (:toDate IS NULL OR ORDER_DATE <= :toDate)",
        nativeQuery = true)
List<PO_invoice_entity> getStockAndDate(
        @Param("branchId") String branchId,
        @Param("fromDate") Date  fromDate,
        @Param("toDate") Date  toDate
);


@Query(value = "SELECT * FROM PO_INVOICE_TABLE " +
        "WHERE useingqty > 0 " +
        "  AND Assetcategory = 'B01A' " +
        "  AND (:branchId IS NULL OR branch_id = :branchId) " +
        "AND (:vendor IS NULL OR VENDOR_ID = :vendor) ",
        nativeQuery = true)
List<PO_invoice_entity> getStockAndDatess(@Param("branchId") String branchId,@Param("vendor") String vendor);

@Query(value = "SELECT * FROM PO_INVOICE_TABLE " +
        "WHERE approved_flg = 'S' " +
        "AND (:vendor IS NULL OR VENDOR_ID = :vendor) " +
        "AND (:branchId IS NULL OR branch_id = :branchId) " +
        "AND (:fromDate IS NULL OR ORDER_DATE >= :fromDate) " +
        "AND (:toDate IS NULL OR ORDER_DATE <= :toDate)",
       nativeQuery = true)
List<PO_invoice_entity> getPOuniq(
        @Param("vendor") String vendor,
        @Param("branchId") String branchId,
        @Param("fromDate") Date fromDate,
        @Param("toDate") Date toDate
);

@Query(value = "SELECT * FROM PO_INVOICE_TABLE " +
        "WHERE approved_flg = 'S' " +
        "AND (:vendor IS NULL OR VENDOR_ID = :vendor) " +
        "AND (:branchId IS NULL OR branch_id = :branchId) ",
       nativeQuery = true)
List<PO_invoice_entity> getPOuniqss(
        @Param("vendor") String vendor,
        @Param("branchId") String branchId
);

}