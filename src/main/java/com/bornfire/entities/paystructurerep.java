package com.bornfire.entities;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface paystructurerep extends JpaRepository<paystructureentity, String>{
	@Query(value = "SELECT * FROM BTM_SAL_PAY WHERE del_flg='N' AND emp_no IN (SELECT DISTINCT emp_no FROM HRMS.BAJ_SAL_PAY) ORDER BY emp_no", nativeQuery = true)
    List<paystructureentity> getListS();
    
	@Query(value = "SELECT * FROM BTM_SAL_PAY ", nativeQuery = true)
    List<paystructureentity> getList();
	@Query(value = "SELECT * FROM BTM_SAL_PAY WHERE del_flg='N'  ORDER BY emp_no", nativeQuery = true)
    List<paystructureentity> getpay();
    @Query(value = "SELECT * FROM BTM_SAL_PAY WHERE salary_month=?1 ", nativeQuery = true)
    List<paystructureentity> getpays(String salary_month);
    @Query(value = "SELECT * FROM BTM_SAL_PAY WHERE salary_month=?1 ", nativeQuery = true)
    List<paystructureentity> getpaysnewss(String salary_month);

	@Query(value = "SELECT * FROM BTM_SAL_PAY WHERE emp_no =?1 AND SALARY_MONTH =?1", nativeQuery = true)
	paystructureentity getaedit(String emp_no);
	
	@Query(value = "SELECT * FROM BTM_SAL_PAY WHERE EMP_NO =?1", nativeQuery = true)
	paystructureentity getsingle(String emp_no);
	
	@Query(value = "SELECT * FROM BTM_SAL_PAY WHERE salary_month =?1", nativeQuery = true)
	 List<paystructureentity> bankjobicici(String salary_month );
	
		@Query(value = "select IFSC_CODE from  BTM_SAL_PAY where SALARY_MONTH=?1 AND BANK_NAME!='ICICI'", nativeQuery = true)
		 List<paystructureentity> bjicicinotpresent(String salary_month );

	@Query(value = "SELECT * FROM BTM_SAL_PAY WHERE emp_no =?1 AND salary_month=?2 AND emp_name=?3", nativeQuery = true)
	paystructureentity getaedits(String emp_no,String salaryMonth,String empname);
	
	@Query(value = "SELECT * FROM BTM_SAL_PAY WHERE emp_no =?1 AND salary_month=?2 AND emp_name=?3", nativeQuery = true)
	paystructureentity getpaystructureedit(String emp_no,String salaryMonth,String empname);
	
	 @Query(value = "SELECT * FROM BTM_SAL_PAY WHERE del_flg='N' AND salary_month=?1", nativeQuery = true)
	    List<paystructureentity> getpayss(String Ymonth);  
	    
		/*
		 * @Query(value =
		 * "SELECT * FROM BTM_SAL_PAY WHERE del_flg='N' AND SUBSTR(salary_month, 1, 4) = ?1 AND SUBSTR(salary_month, 5, 2) = ?2"
		 * , nativeQuery = true) List<paystructureentity> getpayssdemo(String year,
		 * String month);
		 */
	    @Query(value = "SELECT * FROM BTM_SAL_PAY WHERE del_flg='N' AND SUBSTRING(salary_month, 1, 4) = ?1 AND SUBSTRING(salary_month, 5, 2) = ?2", nativeQuery = true)
	    List<paystructureentity> getpayssdemo(String year, String month);

	    
	    @Query(value = "SELECT * FROM BTM_SAL_PAY WHERE salary_month=?1", nativeQuery = true)
	    List<paystructureentity> getstc(String sal);
	    
	    @Query(value = "select  * from BTM_SAL_PAY where emp_no= ?1", nativeQuery = true)
		List<paystructureentity> getpaystructure(String ref_no);
		
		@Query(value = "SELECT DISTINCT emp_no FROM BTM_SAL_PAY ", nativeQuery = true)
		List<String> getpaystruce();

	    @Query(value = "select  * from BTM_SAL_PAY where emp_no= ?1", nativeQuery = true)
		paystructureentity getid(String emp_no);
	    
	    @Query(value = "SELECT * FROM BTM_SAL_PAY WHERE emp_no =?1 ", nativeQuery = true)
		Optional<paystructureentity> getoptional(String emp_no);
	    
}
