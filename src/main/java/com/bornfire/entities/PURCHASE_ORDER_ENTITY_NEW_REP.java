package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface PURCHASE_ORDER_ENTITY_NEW_REP  extends JpaRepository<PURCHASE_ORDER_ENTITY_NEW,String> {

	
	  @Query(value =
	  "SELECT * FROM (SELECT *, ROW_NUMBER() OVER (PARTITION BY po_id ORDER BY po_id DESC) AS row_num FROM PURCHASE_ORDER_TABLE_NEW) AS subquery WHERE row_num = 1;"
	  , nativeQuery = true) List<PURCHASE_ORDER_ENTITY_NEW> getPOlist();
	 

	/* -----new query-- */
	@Query(value = "WITH CTE AS (\r\n" + 
			"    SELECT  \r\n" + 
			"        *,  \r\n" + 
			"        ROW_NUMBER() OVER (\r\n" + 
			"            PARTITION BY po_id \r\n" + 
			"            ORDER BY \r\n" + 
			"                CAST(SUBSTRING(po_id, PATINDEX('%[0-9]%', po_id), LEN(po_id)) AS BIGINT) DESC, \r\n" + 
			"                po_id DESC\r\n" + 
			"        ) AS row_num \r\n" + 
			"    FROM  \r\n" + 
			"        PURCHASE_ORDER_TABLE_NEW\r\n" + 
			")\r\n" + 
			"SELECT * \r\n" + 
			"FROM CTE\r\n" + 
			"WHERE row_num = 1\r\n" + 
			"ORDER BY \r\n" + 
			"    CAST(SUBSTRING(po_id, PATINDEX('%[0-9]%', po_id), LEN(po_id)) AS BIGINT) DESC,\r\n" + 
			"    po_id DESC;", nativeQuery = true)
    List<PURCHASE_ORDER_ENTITY_NEW> getPOlistnew();
    
	@Query(value = "SELECT * FROM (SELECT *, ROW_NUMBER() OVER (PARTITION BY po_id ORDER BY po_id) AS row_num FROM PURCHASE_ORDER_TABLE_NEW) AS subquery WHERE row_num = 1 AND APPROVED_FLG = 'Y';", nativeQuery = true)
    List<PURCHASE_ORDER_ENTITY_NEW> getPOlistbyapproval();

	@Query(value = "SELECT * FROM (SELECT *, ROW_NUMBER() OVER (PARTITION BY po_id ORDER BY po_id) AS row_num FROM PURCHASE_ORDER_TABLE_NEW) AS subquery WHERE row_num = 1;", nativeQuery = true)
    List<PURCHASE_ORDER_ENTITY_NEW> getPOlistforReturn();
    

    @Query(value = "SELECT * FROM PURCHASE_ORDER_TABLE_NEW WHERE PO_ID = ?1", nativeQuery = true)
    List<PURCHASE_ORDER_ENTITY_NEW> getbyid(String poId);


    @Query(value = "SELECT cast(count(DISTINCT po_id ) as INT) FROM PURCHASE_ORDER_TABLE_NEW", nativeQuery = true)
    int getPOcount();	
    
    @Query(value = "SELECT DISTINCT PO_ID FROM PURCHASE_ORDER_TABLE_NEW ORDER BY PO_ID", nativeQuery = true)
    List<String> getall();

    @Query(value = "WITH CTE AS (\r\n" + 
			"    SELECT  \r\n" + 
			"        *,  \r\n" + 
			"        ROW_NUMBER() OVER (\r\n" + 
			"            PARTITION BY po_id \r\n" + 
			"            ORDER BY \r\n" + 
			"                CAST(SUBSTRING(po_id, PATINDEX('%[0-9]%', po_id), LEN(po_id)) AS BIGINT) DESC, \r\n" + 
			"                po_id DESC\r\n" + 
			"        ) AS row_num \r\n" + 
			"    FROM  \r\n" + 
			"        PURCHASE_ORDER_TABLE_NEW\r\n" + 
			"WHERE BRANCH_ID= ?1\r\n"+
			")\r\n" + 
			"SELECT * \r\n" + 
			"FROM CTE\r\n" + 
			"WHERE row_num = 1\r\n" + 
			"ORDER BY \r\n" + 
			"    CAST(SUBSTRING(po_id, PATINDEX('%[0-9]%', po_id), LEN(po_id)) AS BIGINT) DESC,\r\n" + 
			"    po_id DESC;", nativeQuery = true)
    List<PURCHASE_ORDER_ENTITY_NEW> getbranch_PO(String BRANCH_ID);
}