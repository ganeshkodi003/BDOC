package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TSK_PROCESS_REP  extends JpaRepository<TSK_PROCESS_ENTITY,String> {
	
	
	@Query(value = "SELECT * FROM (SELECT *, ROW_NUMBER() OVER (PARTITION BY PROCESS_ID ORDER BY PROCESS_ID) AS row_num FROM TSK_PROCESS_TABLE) AS subquery WHERE row_num = 1 And STATUS='work in progress';", nativeQuery = true)
    List<TSK_PROCESS_ENTITY> getprocess();
    
    @Query(value = "	WITH CTE AS (\r\n" + 
    		"    SELECT  \r\n" + 
    		"        *,  \r\n" + 
    		"        ROW_NUMBER() OVER (\r\n" + 
    		"            PARTITION BY PROCESS_ID \r\n" + 
    		"            ORDER BY \r\n" + 
    		"                CAST(SUBSTRING(PROCESS_ID, PATINDEX('%[0-9]%', PROCESS_ID), LEN(PROCESS_ID)) AS BIGINT) DESC, \r\n" + 
    		"                PROCESS_ID DESC\r\n" + 
    		"        ) AS row_num \r\n" + 
    		"    FROM  \r\n" + 
    		"      TSK_PROCESS_TABLE\r\n" + 
    		"    WHERE \r\n" + 
    		"       STATUS='work in progress'\r\n" + 
    		")\r\n" + 
    		"SELECT * \r\n" + 
    		"FROM CTE\r\n" + 
    		"WHERE row_num = 1\r\n" + 
    		"ORDER BY \r\n" + 
    		"    CAST(SUBSTRING(PROCESS_ID, PATINDEX('%[0-9]%', PROCESS_ID), LEN(PROCESS_ID)) AS BIGINT) DESC,\r\n" + 
    		"    PROCESS_ID DESC;\r\n" + 
    		"\r\n" + 
    		"", nativeQuery = true)
    List<TSK_PROCESS_ENTITY> getprocessnew();
    
    @Query(value = "WITH CTE AS (\r\n" + 
    		"    SELECT  \r\n" + 
    		"        *,  \r\n" + 
    		"        ROW_NUMBER() OVER (\r\n" + 
    		"            PARTITION BY PROCESS_ID \r\n" + 
    		"            ORDER BY \r\n" + 
    		"                CAST(SUBSTRING(PROCESS_ID, PATINDEX('%[0-9]%', PROCESS_ID), LEN(PROCESS_ID)) AS BIGINT) DESC, \r\n" + 
    		"                PROCESS_ID DESC\r\n" + 
    		"        ) AS row_num \r\n" + 
    		"    FROM  \r\n" + 
    		"      TSK_PROCESS_TABLE\r\n" + 
    		"    WHERE \r\n" + 
    		"       STATUS = 'Completed'\r\n" + 
    		")\r\n" + 
    		"SELECT * \r\n" + 
    		"FROM CTE\r\n" + 
    		"WHERE row_num = 1\r\n" + 
    		"ORDER BY \r\n" + 
    		"    CAST(SUBSTRING(PROCESS_ID, PATINDEX('%[0-9]%', PROCESS_ID), LEN(PROCESS_ID)) AS BIGINT) DESC,\r\n" + 
    		"    PROCESS_ID DESC;\r\n" + 
    		"", nativeQuery = true)
    List<TSK_PROCESS_ENTITY> getprocessCompleted();
    
    @Query(value = "SELECT * FROM (SELECT *, ROW_NUMBER() OVER (PARTITION BY PROCESS_ID ORDER BY PROCESS_ID) AS row_num FROM TSK_PROCESS_TABLE) AS subquery WHERE row_num = 1 AND useingquantity!=0.00;", nativeQuery = true)
    List<TSK_PROCESS_ENTITY> getprocessCompletedwithstock();
    
    @Query(value = "SELECT * FROM (SELECT *, ROW_NUMBER() OVER (PARTITION BY PROCESS_ID ORDER BY PROCESS_ID) AS row_num FROM TSK_PROCESS_TABLE) AS subquery WHERE row_num = 1 AND useingquantity=0.00;", nativeQuery = true)
    List<TSK_PROCESS_ENTITY> getprocessCompletedwithoutstock();
    
    
    @Query(value = "SELECT * FROM TSK_PROCESS_TABLE where process_id=?1 ", nativeQuery = true)
    List<TSK_PROCESS_ENTITY> getprocessbyid(String process_id);

	@Query(value = "SELECT * FROM TSK_PROCESS_TABLE where process_id=?1 And itemcode is not null", nativeQuery = true)
    List<TSK_PROCESS_ENTITY> getprocessbyiditem(String process_id );
    
    @Query(value = "SELECT * FROM TSK_PROCESS_TABLE where process_id=?1 And itemcode is null", nativeQuery = true)
    List<TSK_PROCESS_ENTITY> getprocessbyidexp(String process_id );
    
    
    
    @Query(value = "	WITH CTE AS (\r\n" + 
    		"    SELECT  \r\n" + 
    		"        *,  \r\n" + 
    		"        ROW_NUMBER() OVER (\r\n" + 
    		"            PARTITION BY PROCESS_ID \r\n" + 
    		"            ORDER BY \r\n" + 
    		"                CAST(SUBSTRING(PROCESS_ID, PATINDEX('%[0-9]%', PROCESS_ID), LEN(PROCESS_ID)) AS BIGINT) DESC, \r\n" + 
    		"                PROCESS_ID DESC\r\n" + 
    		"        ) AS row_num \r\n" + 
    		"    FROM  \r\n" + 
    		"      TSK_PROCESS_TABLE\r\n" + 
    		"    WHERE BRANCH_ID= ?1 AND \r\n" + 
    		"       STATUS='work in progress'\r\n" + 
    		")\r\n" + 
    		"SELECT * \r\n" + 
    		"FROM CTE\r\n" + 
    		"WHERE row_num = 1\r\n" + 
    		"ORDER BY \r\n" + 
    		"    CAST(SUBSTRING(PROCESS_ID, PATINDEX('%[0-9]%', PROCESS_ID), LEN(PROCESS_ID)) AS BIGINT) DESC,\r\n" + 
    		"    PROCESS_ID DESC;\r\n" + 
    		"\r\n" + 
    		"", nativeQuery = true)
    List<TSK_PROCESS_ENTITY> getbranch_val(String BRANCH_ID);
    
    
    

    @Query(value = "	WITH CTE AS (\r\n" + 
    		"    SELECT  \r\n" + 
    		"        *,  \r\n" + 
    		"        ROW_NUMBER() OVER (\r\n" + 
    		"            PARTITION BY PROCESS_ID \r\n" + 
    		"            ORDER BY \r\n" + 
    		"                CAST(SUBSTRING(PROCESS_ID, PATINDEX('%[0-9]%', PROCESS_ID), LEN(PROCESS_ID)) AS BIGINT) DESC, \r\n" + 
    		"                PROCESS_ID DESC\r\n" + 
    		"        ) AS row_num \r\n" + 
    		"    FROM  \r\n" + 
    		"      TSK_PROCESS_TABLE\r\n" + 
    		"    WHERE BRANCH_ID= ?1 AND \r\n" + 
    		"       STATUS='Completed'\r\n" + 
    		")\r\n" + 
    		"SELECT * \r\n" + 
    		"FROM CTE\r\n" + 
    		"WHERE row_num = 1\r\n" + 
    		"ORDER BY \r\n" + 
    		"    CAST(SUBSTRING(PROCESS_ID, PATINDEX('%[0-9]%', PROCESS_ID), LEN(PROCESS_ID)) AS BIGINT) DESC,\r\n" + 
    		"    PROCESS_ID DESC;\r\n" + 
    		"\r\n" + 
    		"", nativeQuery = true)
    List<TSK_PROCESS_ENTITY> getbranch_comp(String BRANCH_ID);
    

    @Query(value = "SELECT * FROM (SELECT *, ROW_NUMBER() OVER (PARTITION BY PROCESS_ID ORDER BY PROCESS_ID) AS row_num FROM TSK_PROCESS_TABLE) AS subquery WHERE  BRANCH_ID= ?1  AND row_num = 1 AND useingquantity!=0.00;", nativeQuery = true)
    List<TSK_PROCESS_ENTITY> getprocessCompletedwithstock_one(String BRANCH_ID);
}
