package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface BTMEmpTimeSheetRep extends JpaRepository<BTMEmpTimeSheet, String>{

	public Optional<BTMEmpTimeSheet> findById(String empId);

	@Query(value = "SELECT * FROM EMP_TIME_SHEET where entity_flg='N'", nativeQuery = true)
	List<BTMEmpTimeSheet> getTimeSheetlist();
	
	@Query(value = "SELECT * FROM EMP_TIME_SHEET where emp_id =?1 and year=?2 and month=?3", nativeQuery = true)
	BTMEmpTimeSheet getTimeSheetModify(String userid ,BigDecimal year,String month);
	
	@Query(value = "SELECT * FROM EMP_TIME_SHEET where emp_id =?1 and entity_flg='Y'", nativeQuery = true)
	List<BTMEmpTimeSheet> getTimeSheetdata(String empId);
	
	@Query(value = "SELECT * FROM EMP_TIME_SHEET where emp_id =?1 and del_flg='Y'", nativeQuery = true)
	BTMEmpTimeSheet getTimeSheetLast(String empId);
	
	@Query(value = "SELECT * FROM EMP_TIME_SHEET where emp_id =?1 and month=?2 and year=?3", nativeQuery = true)
	BTMEmpTimeSheet getTimeSheetdataView(String empId,String month,BigDecimal year);
	
	@Query(value = "SELECT * FROM EMP_TIME_SHEET where emp_id =?1 and month=?2 and year=?3", nativeQuery = true)
	List<BTMEmpTimeSheet> getTimeSheetdatasheet(String empId,String month,String year);
}
