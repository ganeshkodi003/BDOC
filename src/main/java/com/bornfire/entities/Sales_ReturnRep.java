package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Sales_ReturnRep extends JpaRepository<Sales_Return,String> {
	
	@Query(value = "SELECT * FROM (SELECT *, ROW_NUMBER() OVER (PARTITION BY WO_ID ORDER BY WO_id) AS row_num FROM sales_return_table) AS subquery WHERE row_num = 1", nativeQuery = true)
    List<Sales_Return> getWOlistbyapproval();
    
    @Query(value = "WITH CTE AS (SELECT *, ROW_NUMBER() OVER (PARTITION BY wo_Return_id ORDER BY CAST(SUBSTRING(wo_Return_id, PATINDEX('%[0-9]%', wo_Return_id), LEN(wo_Return_id)) AS BIGINT) DESC, wo_Return_id DESC) AS row_num FROM sales_return_table WHERE approved_flg = 'R') SELECT * FROM CTE WHERE row_num = 1 ORDER BY CAST(SUBSTRING(wo_Return_id, PATINDEX('%[0-9]%', wo_Return_id), LEN(wo_Return_id)) AS BIGINT) DESC, wo_Return_id DESC" , nativeQuery = true)
    List<Sales_Return> getWOlistbyapprovalnew1();
    
    @Query(value = "SELECT * FROM sales_return_table WHERE WO_id = ?1", nativeQuery = true)
    List<Sales_Return> getbyid(String WO_id);
    
    @Query(value = "SELECT * FROM sales_return_table WHERE wo_Return_id = ?1", nativeQuery = true)
    List<Sales_Return> getbyid1(String wo_Return_id);
    
    @Query(value = "SELECT count(*) FROM sales_return_table", nativeQuery = true)
    Integer getcountfordashboard();
    
    @Query(value = "SELECT DISTINCT WO_id  FROM sales_return_table ORDER BY WO_id", nativeQuery = true)
    List<String> getsalereturnall();
    

    @Query(value = "WITH CTE AS (SELECT *, ROW_NUMBER() OVER (PARTITION BY wo_Return_id ORDER BY CAST(SUBSTRING(wo_Return_id, PATINDEX('%[0-9]%', wo_Return_id), LEN(wo_Return_id)) AS BIGINT) DESC, wo_Return_id DESC) AS row_num FROM sales_return_table WHERE BRANCH_ID= ?1 AND approved_flg = 'R') SELECT * FROM CTE WHERE row_num = 1 ORDER BY CAST(SUBSTRING(wo_Return_id, PATINDEX('%[0-9]%', wo_Return_id), LEN(wo_Return_id)) AS BIGINT) DESC, wo_Return_id DESC" , nativeQuery = true)
    List<Sales_Return> getbranch_returnlist(String BRANCH_ID);

}
