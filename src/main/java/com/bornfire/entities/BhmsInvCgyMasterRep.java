package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface BhmsInvCgyMasterRep extends JpaRepository<BhmsInvCgyMasterTable, String>{

	@Query(value = "select * from BHMS_INV_MEDI_CATEGORY_TABLE where del_flg='N' order by srl_no", nativeQuery = true)

	List<BhmsInvCgyMasterTable> findAllCustom();

	
	@Query(value = "SELECT NEXT VALUE FOR ID", nativeQuery = true)
	String srlnum();
	
	@Query(value = "SELECT NEXT VALUE FOR CURR_STOCK_ID", nativeQuery = true)

	String CURR_STOCK_IDval();
	
	
}
