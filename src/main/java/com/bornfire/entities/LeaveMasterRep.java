package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;




public interface LeaveMasterRep extends CrudRepository<LeaveMaster, BigDecimal> {

	public Optional<LeaveMaster> findById(BigDecimal empId);
	
	@Query(value = "SELECT * FROM LEAVE_MASTER WHERE record_no= ?1 " , nativeQuery = true)
	LeaveMaster findByRecId(BigDecimal id1);

	@Query(value = "SELECT * FROM LEAVE_MASTER where employee_id = ?1 ", nativeQuery = true)
	LeaveMaster findByEmpId(String id1);

	@Query(value = "SELECT * FROM LEAVE_MASTER", nativeQuery = true)
	List<LeaveMaster> getLeavelist();
	
	@Query(value = "SELECT * FROM LEAVE_MASTER WHERE EMPLOYEE_ID=?1 AND LEAVE_CATEGORY=?2 AND YEAR=?3", nativeQuery = true)
	List<LeaveMaster> getLeaveReport(String Employeeid, String Leave,String Year);
	
	@Query(value = "SELECT * FROM LEAVE_MASTER where year='2021'", nativeQuery = true)
	List<LeaveMaster> getAdminLeaveList(BigDecimal year);
	
	@Query(value = "SELECT * FROM LEAVE_MASTER WHERE year IN ('2023', '2024')", nativeQuery = true)
	List<LeaveMaster> getAdminLeaveList1(BigDecimal year);
	@Query(value = "SELECT * FROM LEAVE_MASTER WHERE year IN ('2018', '2019', '2020', '2021', '2022', '2023', '2024')", nativeQuery = true)
	List<LeaveMaster> getAdminLeaveList2(BigDecimal year);

	@Query(value = "SELECT * FROM LEAVE_MASTER WHERE year = '2025'", nativeQuery = true)
	List<LeaveMaster> getAdminLeaveList2025(String year);
	
	@Query(value = "SELECT * FROM LEAVE_MASTER WHERE BRANCH_ID=?1 AND ORG_ID=?2 AND  year = '2025'", nativeQuery = true)
	List<LeaveMaster> getleavebranch11(String branch,String org_id);
	
	@Query(value = "SELECT * FROM LEAVE_MASTER WHERE year = '2024'", nativeQuery = true)
	List<LeaveMaster> getAdminLeaveList11(BigDecimal year);
	@Query(value = "SELECT * FROM LEAVE_MASTER WHERE year = '2023'", nativeQuery = true)
	List<LeaveMaster> getAdminLeaveList111(String year);
	
	@Query(value = "SELECT * FROM LEAVE_MASTER WHERE year = '2022'", nativeQuery = true)
	List<LeaveMaster> getAdmindetailsit1(String year);
	@Query(value = "SELECT * FROM LEAVE_MASTER WHERE year = '2021'", nativeQuery = true)
	List<LeaveMaster> getAdmindetailsit2(String year);
	@Query(value = "SELECT * FROM LEAVE_MASTER WHERE year = '2020'", nativeQuery = true)
	List<LeaveMaster> getAdmindetailsit3(String year);
	@Query(value = "SELECT * FROM LEAVE_MASTER WHERE year = '2019'", nativeQuery = true)
	List<LeaveMaster> getAdmindetailsit4(String year);
	@Query(value = "SELECT * FROM LEAVE_MASTER WHERE year = '2018'", nativeQuery = true)
	List<LeaveMaster> getAdmindetailsit5(String year);
	@Query(value = "SELECT * FROM LEAVE_MASTER WHERE employee_id = 'BFI0148'", nativeQuery = true)
	List<LeaveMaster> gettestig(String year);
	
	
	@Query(value = "SELECT * FROM LEAVE_MASTER where status != 'Approved'", nativeQuery = true)
	List<LeaveMaster> getLeavelist1();
	
	@Query(value = "SELECT * FROM LEAVE_MASTER where employee_id = ?1 ", nativeQuery = true)
	List<LeaveMaster> getLeaveListbyid(String userid);
	
	@Query(value = "SELECT * FROM LEAVE_MASTER where record_no = ?1 ", nativeQuery = true)
	LeaveMaster getLeaveListbyRecord(BigDecimal resId);
	
	@Query(value = "SELECT * FROM LEAVE_MASTER where from_date = ?1 AND EMPLOYEE_ID=?2", nativeQuery = true)
	LeaveMaster getLeavebyFromDate(String date,String empid);
	
	@Query(value = "SELECT * FROM LEAVE_MASTER where leave_reference = ?1 ", nativeQuery = true)
	LeaveMaster getleaveMaster(String userid);
	
	
	@Query(value = "SELECT * FROM LEAVE_MASTER where EMPLOYEE_ID=?1 AND from_date=?2 AND YEAR=?3  ", nativeQuery = true)
	LeaveMaster getleaveMastersheet(String employee_id,BigDecimal year, Date from_date,Date to_date );
	
	@Query(value = "SELECT * FROM LEAVE_MASTER WHERE year = '2024'", nativeQuery = true)
	List<LeaveMaster> finduser1list();
	@Query(value = "SELECT * FROM LEAVE_MASTER WHERE year = '2023'", nativeQuery = true)
	List<LeaveMaster> finduser1listthree();
	@Query(value = "SELECT * FROM LEAVE_MASTER WHERE year = '2022'", nativeQuery = true)
	List<LeaveMaster> finduser1listtwo();
	@Query(value = "SELECT * FROM LEAVE_MASTER WHERE year = '2021'", nativeQuery = true)
	List<LeaveMaster> finduser1listone();
	@Query(value = "SELECT * FROM LEAVE_MASTER WHERE year = '2020'", nativeQuery = true)
	List<LeaveMaster> finduser1listzero();
	@Query(value = "SELECT * FROM LEAVE_MASTER WHERE year = '2019'", nativeQuery = true)
	List<LeaveMaster> finduser1listnine();
	@Query(value = "SELECT * FROM LEAVE_MASTER WHERE year = '2018'", nativeQuery = true)
	List<LeaveMaster> finduser1listeight();

	

}