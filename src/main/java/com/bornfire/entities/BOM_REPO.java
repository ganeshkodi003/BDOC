package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BOM_REPO extends JpaRepository<BOM_ENTITY,String> {
	
	@Query(value = "SELECT * FROM BOM", nativeQuery = true)
	List<BOM_ENTITY> getbomdetails1();
	

	@Query(value = "	WITH CTE AS (\r\n" + 
			"    SELECT  \r\n" + 
			"        *,  \r\n" + 
			"        ROW_NUMBER() OVER (\r\n" + 
			"            PARTITION BY BOM_ID \r\n" + 
			"            ORDER BY \r\n" + 
			"                CAST(SUBSTRING(BOM_ID, PATINDEX('%[0-9]%', BOM_ID), LEN(BOM_ID)) AS BIGINT) DESC, \r\n" + 
			"                BOM_ID DESC\r\n" + 
			"        ) AS row_num \r\n" + 
			"    FROM  \r\n" + 
			"       BOM\r\n" + 
			"    WHERE \r\n" + 
			"       (del_flg = 'N' OR del_flg IS NULL)\r\n" + 
			")\r\n" + 
			"SELECT * \r\n" + 
			"FROM CTE\r\n" + 
			"WHERE row_num = 1\r\n" + 
			"ORDER BY \r\n" + 
			"    CAST(SUBSTRING(BOM_ID, PATINDEX('%[0-9]%', BOM_ID), LEN(BOM_ID)) AS BIGINT) DESC,\r\n" + 
			"    BOM_ID DESC;\r\n" + 
			"", nativeQuery = true)
	List<BOM_ENTITY> getbomdetails();
	
	@Query(value = "SELECT * FROM BOM where BOM_ID=?1", nativeQuery = true)
	List<Object[]> getbom(String BOM_ID);
	
	@Query(value = "SELECT * FROM BOM where BOM_ID=?1 AND Item_code IS NOT null ", nativeQuery = true)
	List<BOM_ENTITY> getbomforview(String BOM_ID);
	
	@Query(value = "SELECT * FROM BOM where BOM_ID=?1 AND Item_code IS null", nativeQuery = true)
	List<BOM_ENTITY> getbomforviewnull(String BOM_ID);
	
	
	@Query(value = "SELECT * FROM BOM where BOM_ID=?1", nativeQuery = true)
	List<BOM_ENTITY> getbomfordelete(String BOM_ID);


}
