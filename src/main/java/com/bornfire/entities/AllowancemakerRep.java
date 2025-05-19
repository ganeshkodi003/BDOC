package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AllowancemakerRep extends JpaRepository<AllowanceMaker, String>{
	
	@Query(value = "select * from allowancemaker WHERE del_flg='N'", nativeQuery = true)
    List<AllowanceMaker> getbyall();
	
	@Query(value = "select * from allowancemaker WHERE del_flg='N' and designation=?1", nativeQuery = true)
    AllowanceMaker getbyallid(String designation);
	

	@Query(value = "select * from allowancemaker WHERE allowancemakerid=?1", nativeQuery = true)
    AllowanceMaker getbyallowancemakerid(String allowancemakerid);
}
