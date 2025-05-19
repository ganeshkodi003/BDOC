package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface FINISHED_GOODS_Rep  extends JpaRepository<FINISHED_GOODS_ENTITY,String>{

	 @Query(value = "SELECT *,CAST(SUBSTRING(FG_ID, PATINDEX('%[0-9]%', FG_ID), LEN(FG_ID)) AS BIGINT)  FROM FINISHED_GOODS_TABLE WHERE del_flg = 'N'", nativeQuery = true)
	 List<FINISHED_GOODS_ENTITY>  get_list();
	 
	 @Query(value = "	WITH CTE AS (\r\n" + 
	 		"    SELECT  \r\n" + 
	 		"        *,  \r\n" + 
	 		"        ROW_NUMBER() OVER (\r\n" + 
	 		"            PARTITION BY FG_ID \r\n" + 
	 		"            ORDER BY \r\n" + 
	 		"                CAST(SUBSTRING(FG_ID, PATINDEX('%[0-9]%', FG_ID), LEN(FG_ID)) AS BIGINT) DESC, \r\n" + 
	 		"                FG_ID DESC\r\n" + 
	 		"        ) AS row_num \r\n" + 
	 		"    FROM  \r\n" + 
	 		"      FINISHED_GOODS_TABLE\r\n" + 
	 		"    WHERE \r\n" + 
	 		"      del_flg = 'N'\r\n" + 
	 		")\r\n" + 
	 		"SELECT * \r\n" + 
	 		"FROM CTE\r\n" + 
	 		"WHERE row_num = 1\r\n" + 
	 		"ORDER BY \r\n" + 
	 		"    CAST(SUBSTRING(FG_ID, PATINDEX('%[0-9]%', FG_ID), LEN(FG_ID)) AS BIGINT) DESC,\r\n" + 
	 		"    FG_ID DESC;", nativeQuery = true)
	 List<FINISHED_GOODS_ENTITY>  get_listnew();
	 
	 @Query(value = "SELECT *,CAST(SUBSTRING(FG_ID, PATINDEX('%[0-9]%', FG_ID), LEN(FG_ID)) AS BIGINT)  FROM FINISHED_GOODS_TABLE WHERE del_flg = 'N' And USING_QUANTITY!=0.00; ", nativeQuery = true)
	 List<FINISHED_GOODS_ENTITY>  get_listwithstock();
	 
	 @Query(value = "SELECT *,CAST(SUBSTRING(FG_ID, PATINDEX('%[0-9]%', FG_ID), LEN(FG_ID)) AS BIGINT)  FROM FINISHED_GOODS_TABLE WHERE del_flg = 'N' And USING_QUANTITY=0.00; ", nativeQuery = true)
	 List<FINISHED_GOODS_ENTITY>  get_listwithoutstock();
	 
	 @Query(value = "SELECT *,CAST(SUBSTRING(FG_ID, PATINDEX('%[0-9]%', FG_ID), LEN(FG_ID)) AS BIGINT)  FROM FINISHED_GOODS_TABLE WHERE del_flg = 'N' And USING_QUANTITY!=0.00 And FG_itemcode=?1 ", nativeQuery = true)
	 List<FINISHED_GOODS_ENTITY>  get_listbycode(String FG_itemcode);
	 
	 @Query(value = "SELECT *,CAST(SUBSTRING(FG_ID, PATINDEX('%[0-9]%', FG_ID), LEN(FG_ID)) AS BIGINT)  FROM FINISHED_GOODS_TABLE WHERE del_flg = 'N' And FG_ID=?1 ", nativeQuery = true)
	 FINISHED_GOODS_ENTITY  get_listbyFG_ID(String FG_ID);
	 
	 
	 
	 @Query(value = "select FG_itemcode,max(FG_name)as Name, sum(using_quantity)as Total  FROM FINISHED_GOODS_TABLE where USING_QUANTITY!=0.00  group by FG_itemcode ", nativeQuery = true)
	 List<Object[]>  get_listgroup();
	 @Query(value = "	WITH CTE AS (\r\n" + 
		 		"    SELECT  \r\n" + 
		 		"        *,  \r\n" + 
		 		"        ROW_NUMBER() OVER (\r\n" + 
		 		"            PARTITION BY FG_ID \r\n" + 
		 		"            ORDER BY \r\n" + 
		 		"                CAST(SUBSTRING(FG_ID, PATINDEX('%[0-9]%', FG_ID), LEN(FG_ID)) AS BIGINT) DESC, \r\n" + 
		 		"                FG_ID DESC\r\n" + 
		 		"        ) AS row_num \r\n" + 
		 		"    FROM  \r\n" + 
		 		"      FINISHED_GOODS_TABLE\r\n" + 
		 		"    WHERE BRANCH_ID= ?1 AND \r\n" + 
		 		"      del_flg = 'N'\r\n" + 
		 		")\r\n" + 
		 		"SELECT * \r\n" + 
		 		"FROM CTE\r\n" + 
		 		"WHERE row_num = 1\r\n" + 
		 		"ORDER BY \r\n" + 
		 		"    CAST(SUBSTRING(FG_ID, PATINDEX('%[0-9]%', FG_ID), LEN(FG_ID)) AS BIGINT) DESC,\r\n" + 
		 		"    FG_ID DESC;", nativeQuery = true)
		 List<FINISHED_GOODS_ENTITY>  getbranch_fin(String BRANCH_ID);
	 

		 @Query(value = "select FG_itemcode,max(FG_name)as Name, sum(using_quantity)as Total  FROM FINISHED_GOODS_TABLE where BRANCH_ID= ?1 AND USING_QUANTITY!=0.00  group by FG_itemcode ", nativeQuery = true)
		 List<Object[]>  get_listgroup_one(String BRANCH_ID);
}
