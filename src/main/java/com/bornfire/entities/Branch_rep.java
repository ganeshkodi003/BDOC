package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Branch_rep extends JpaRepository<Branch_Entity,String> {

	@Query(value = "SELECT ISNULL(MAX(CAST(Branch_Id AS BIGINT)), 0) FROM branch_table", nativeQuery = true)
	Long findMaxBranchId();
	
	@Query(value = "select  * from branch_table ", nativeQuery = true)
	List<Branch_Entity> findAll();
	
	@Query(value = "SELECT * FROM branch_table WHERE Branch_Id = ?1", nativeQuery = true)
	Branch_Entity find_id(String id);
	
	@Query(value = "SELECT DISTINCT Branch_Id FROM branch_table", nativeQuery = true)
	List<String> find_branch_id();


	
}
