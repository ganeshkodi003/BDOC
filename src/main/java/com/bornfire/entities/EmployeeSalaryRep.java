package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeSalaryRep extends CrudRepository<EmployeeSalary,String>  {
	@Query(value = "SELECT * from EmployeeSalary", nativeQuery = true)
	List<EmployeeSalary> getlist();
	
	@Query(value = "SELECT * from EmployeeSalary where MONTH= ?1 And YEAR =?2", nativeQuery = true)
	List<EmployeeSalary> getlists(String month,String year);
	
	@Query(value = "SELECT * from EmployeeSalary where EMP_ID=?1 and  MONTH= ?2 And YEAR =?3", nativeQuery = true)
	EmployeeSalary getindividualdata(String empid,String month,String year);
}
