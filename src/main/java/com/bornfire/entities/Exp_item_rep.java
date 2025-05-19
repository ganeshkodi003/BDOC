package com.bornfire.entities;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Exp_item_rep extends JpaRepository<Exp_itemcreation_Entity, String> {
	
	@Query(value = "select * from EXP_ITEM_CREATION where del_flg='N'   ", nativeQuery = true)
	List<Exp_itemcreation_Entity> getexpitem();
	
	@Query(value = "SELECT * FROM EXP_ITEM_CREATION WHERE EXP_ITEM_ID = ?1", nativeQuery = true)
	List<Object[]> getSingleRowforexp(String code);




}
