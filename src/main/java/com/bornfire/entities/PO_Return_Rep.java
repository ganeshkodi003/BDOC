

package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface PO_Return_Rep  extends JpaRepository<PO_Return_Entity,String> {

	@Query(value = "SELECT * FROM (SELECT *, ROW_NUMBER() OVER (PARTITION BY po_id ORDER BY po_id) AS row_num FROM PO_RETURN_TABLE) AS subquery WHERE row_num = 1;", nativeQuery = true)
    List<PO_Return_Entity> getPOlist();
    
	@Query(value = "SELECT * FROM (SELECT *, ROW_NUMBER() OVER (PARTITION BY po_id ORDER BY po_id) AS row_num FROM PO_RETURN_TABLE) AS subquery WHERE row_num = 1", nativeQuery = true)
    List<PO_Return_Entity> getPOlistbyapproval();
    
    @Query(value = "	WITH CTE AS (\r\n" + 
    		"    SELECT  \r\n" + 
    		"        *,  \r\n" + 
    		"        ROW_NUMBER() OVER (\r\n" + 
    		"            PARTITION BY po_id \r\n" + 
    		"            ORDER BY \r\n" + 
    		"                CAST(SUBSTRING(po_id, PATINDEX('%[0-9]%', po_id), LEN(po_id)) AS BIGINT) DESC, \r\n" + 
    		"                po_id DESC\r\n" + 
    		"        ) AS row_num \r\n" + 
    		"    FROM  \r\n" + 
    		"       PO_RETURN_TABLE\r\n" + 
    		")\r\n" + 
    		"SELECT * \r\n" + 
    		"FROM CTE\r\n" + 
    		"WHERE row_num = 1\r\n" + 
    		"ORDER BY \r\n" + 
    		"    CAST(SUBSTRING(po_id, PATINDEX('%[0-9]%', po_id), LEN(po_id)) AS BIGINT) DESC,\r\n" + 
    		"    po_id DESC;\r\n" + 
    		"", nativeQuery = true)
    List<PO_Return_Entity> getPOlistbyapprovalnew();
    
    @Query(value = "WITH CTE AS (SELECT *, ROW_NUMBER() OVER (PARTITION BY Po_Return_id ORDER BY CAST(SUBSTRING(Po_Return_id, PATINDEX('%[0-9]%', Po_Return_id), LEN(Po_Return_id)) AS BIGINT) DESC, Po_Return_id DESC) AS row_num FROM PO_RETURN_TABLE WHERE approved_flg = 'R') SELECT * FROM CTE WHERE row_num = 1 ORDER BY CAST(SUBSTRING(Po_Return_id, PATINDEX('%[0-9]%', Po_Return_id), LEN(po_id)) AS BIGINT) DESC, Po_Return_id DESC" , nativeQuery = true)
    List<PO_Return_Entity> getPOlistbyapprovalnew1();
    
   

    @Query(value = "SELECT * FROM PO_RETURN_TABLE WHERE PO_ID = ?1", nativeQuery = true)
    List<PO_Return_Entity> getbyid(String poId);


    @Query(value = "SELECT cast(count(DISTINCT po_id ) as INT) FROM PO_RETURN_TABLE", nativeQuery = true)
    int getPOcount();
    
    @Query(value = "SELECT DISTINCT po_id FROM PO_RETURN_TABLE ORDER BY  po_id", nativeQuery = true)
    List<String>  getreturnall();

    @Query(value = "WITH CTE AS (SELECT *, ROW_NUMBER() OVER (PARTITION BY Po_Return_id ORDER BY CAST(SUBSTRING(Po_Return_id, PATINDEX('%[0-9]%', Po_Return_id), LEN(Po_Return_id)) AS BIGINT) DESC, Po_Return_id DESC) AS row_num FROM PO_RETURN_TABLE WHERE approved_flg = 'R' AND BALANCE_AMOUNT <= 0) SELECT * FROM CTE WHERE row_num = 1 ORDER BY CAST(SUBSTRING(Po_Return_id, PATINDEX('%[0-9]%', Po_Return_id), LEN(po_id)) AS BIGINT) DESC, Po_Return_id DESC" , nativeQuery = true)
    List<PO_Return_Entity> getPOPaidlist();
    

    @Query(value = "WITH CTE AS (SELECT *, ROW_NUMBER() OVER (PARTITION BY Po_Return_id ORDER BY CAST(SUBSTRING(Po_Return_id, PATINDEX('%[0-9]%', Po_Return_id), LEN(Po_Return_id)) AS BIGINT) DESC, Po_Return_id DESC) AS row_num FROM PO_RETURN_TABLE WHERE BRANCH_ID= ?1 AND approved_flg = 'R') SELECT * FROM CTE WHERE row_num = 1 ORDER BY CAST(SUBSTRING(Po_Return_id, PATINDEX('%[0-9]%', Po_Return_id), LEN(po_id)) AS BIGINT) DESC, Po_Return_id DESC" , nativeQuery = true)
    List<PO_Return_Entity> getbranch_PORetPid(String BRANCH_ID);
	
}