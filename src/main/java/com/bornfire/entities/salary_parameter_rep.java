package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface salary_parameter_rep extends CrudRepository<salary_parameter, BigDecimal> {


	@Query(value = "SELECT * from SALARY_PARAMETERS", nativeQuery = true)
	List<salary_parameter> getdata();
	
	@Query(value = "SELECT * from SALARY_PARAMETERS where srl_no = ?1 ", nativeQuery = true)
	salary_parameter getview(BigDecimal srl_no);

	 @Query(value = "SELECT DISTINCT  group_name FROM dob.SALARY_PARAMETERS WHERE [national] = ?1 AND status = 'Verified' ORDER BY group_name ASC", nativeQuery = true)
	    List<String> getDistinctCountries(String national);
	 
	 	@Query(value = "SELECT * from SALARY_PARAMETERS where group_name = ?1 ", nativeQuery = true)
		salary_parameter getgroup(String group_name);
	 	
	 	
	 	@Query(value = "SELECT * from SALARY_PARAMETERS where BRANCH_ID=?1 AND ORG_ID=?2", nativeQuery = true)
		List<salary_parameter> getdataforsalary(String branchId,String org_id);
	
}
