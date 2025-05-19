package com.bornfire.entities;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Erp_exp_category_rep  extends JpaRepository<Erp_exp_category, String> {
	
	@Query(value = "SELECT CAST(current_value AS BIGINT) FROM sys.sequences WHERE name ='ERP_EXP_CAT_SEQ'", nativeQuery = true)
	BigInteger getCateCode();
	
	
	@Query(value = "select * from ERP_EXPEN_CATEGORY where DEL_FLG='N'   ", nativeQuery = true)
	List<Erp_exp_category> getcategory_creationlist();
	
	@Query(value = "select * from ERP_EXPEN_CATEGORY where cat_id=?1 AND DEL_FLG='N'   ", nativeQuery = true)
	List<Object[]> getcategory_creationid(String cat_id);



}