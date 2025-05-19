
package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TSK_branch_Rep extends JpaRepository<TSK_branchEntity,String> {

	@Query(value = "SELECT ISNULL(MAX(CAST(Branch_Id AS BIGINT)), 0) FROM TSK_BRANCH_TABLE", nativeQuery = true)
	Long findMaxBranchId();
	
	@Query(value = "select  * from TSK_BRANCH_TABLE where del_flg='N'", nativeQuery = true)
	List<TSK_branchEntity> findAll();
	
	@Query(value = "SELECT * FROM TSK_BRANCH_TABLE WHERE Branch_Id = ?1", nativeQuery = true)
	TSK_branchEntity find_id(String id);
	
	@Query(value = "SELECT DISTINCT Branch_Id FROM TSK_BRANCH_TABLE", nativeQuery = true)
	List<String> find_branch_id();
	

	@Query(value = "SELECT Branch_Id FROM TSK_BRANCH_TABLE where ORG_ID=?1", nativeQuery = true)
	List<String> find_branch_ids(String ORG_ID);

	@Query(value = "SELECT Branch_Id, Branch_Name, ORG_NAME FROM TSK_BRANCH_TABLE WHERE ORG_ID = ?1", nativeQuery = true)
	List<Object[]> findBranchInfoRaw(String orgId);


	
}
