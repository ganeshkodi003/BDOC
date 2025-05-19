package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface category_creationRep extends JpaRepository<category_creation, String> {
	@Query(value = "select * from categorycreation where del_flg='N' order by category_name  ", nativeQuery = true)
	List<category_creation> getcategory_creationlist();
	
	@Query(value = "select * from categorycreation where category_id=?1 ", nativeQuery = true)
	category_creation getbycatid(String category_id);


}
