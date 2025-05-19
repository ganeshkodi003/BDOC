package com.bornfire.entities;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BTMAdminExpenseReportRep extends CrudRepository<ExpenseMaster, String>{
	
	public Optional<ExpenseMaster> findById(String assId);

	@Query(value = "SELECT * FROM EXP_MASTER where del_flg='N'", nativeQuery = true)
	List<ExpenseMaster> getReportList();
	
	@Query(value = "SELECT * FROM EXP_MASTER where BRANCH_ID=?1 AND ORG_ID=?2 ", nativeQuery = true)
	List<ExpenseMaster> getexpensesclaim(String branchid,String org_id);
	
  @Query(value = "SELECT * FROM EXP_MASTER where status != 'Approved' and status!='Rejected' and del_flg='N'", nativeQuery = true)
	List<ExpenseMaster> getReportList1();
  
  @Query(value = "SELECT * FROM EXP_MASTER where BRANCH_ID=?1 AND  status != 'Approved' and status!='Rejected' and del_flg='N'", nativeQuery = true)
	List<ExpenseMaster> getReportList1new(String branchid);
	
	@Query(value = "select * from EXP_MASTER  where exp_ref_no=?1", nativeQuery = true)
	ExpenseMaster getExpenseMaster(String resId);
	
}
