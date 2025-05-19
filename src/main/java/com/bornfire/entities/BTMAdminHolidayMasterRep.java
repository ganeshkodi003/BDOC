package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BTMAdminHolidayMasterRep extends CrudRepository<BTMAdminHolidayMaster, BigDecimal> {

	public Optional<BTMAdminHolidayMaster> findById(BigDecimal empId);

	/*
	 * @Query(value =
	 * "SELECT * FROM HRMS.HOLIDAY_MASTER WHERE  CAL_YEAR =to_char(sysdate,'YYYY') ORDER BY RECORD_DATE ASC"
	 * , nativeQuery = true) List<BTMAdminHolidayMaster> getAssocitelist();
	 */
	
	@Query(value = "SELECT * FROM HOLIDAY_MASTER WHERE CAL_YEAR = YEAR(GETDATE()) ORDER BY RECORD_DATE ASC", nativeQuery = true)
	List<BTMAdminHolidayMaster> getAssocitelist();
	
	
	@Query(value = "SELECT * FROM HOLIDAY_MASTER WHERE BRANCH_ID=?1 AND ORG_ID=?2 AND  CAL_YEAR = YEAR(GETDATE()) ORDER BY RECORD_DATE ASC", nativeQuery = true)
	List<BTMAdminHolidayMaster> getbranch(String branch,String org_id);


	@Query(value = "select count(*) from HOLIDAY_MASTER  where record_srl =?1", nativeQuery = true)
	public BigDecimal getcount(BigDecimal recordNo);
	
	@Query(value = "select count(*) from HOLIDAY_MASTER  where cal_year =?1 and cal_month=?2", nativeQuery = true)
	int getHolidaycount(String year,String month);
	/*
	 * @Query(value =
	 * "select CAL_YEAR,CAL_MONTH,to_char(RECORD_DATE,'dd-mm-yyyy')RECORD_DATE,TO_CHAR(RECORD_DATE, 'Day') AS DAY,HOLIDAY_DESC,HOLIDAY_REMARKS from HRMS.HOLIDAY_MASTER WHERE CAL_YEAR=?1 AND CAL_MONTH=?2"
	 * , nativeQuery = true) List<Object[]> getMonthlyHolidaylists(String year,
	 * String month);
	 */
	
	@Query(value = "SELECT CAL_YEAR, CAL_MONTH, FORMAT(RECORD_DATE, 'dd-MM-yyyy') AS RECORD_DATE, FORMAT(RECORD_DATE, 'dddd') AS DAY, HOLIDAY_DESC, HOLIDAY_REMARKS " +
            "FROM HOLIDAY_MASTER " +
            "WHERE CAL_YEAR = ?1 AND CAL_MONTH = ?2", nativeQuery = true)
List<Object[]> getMonthlyHolidaylists(String year, String month);

	@Query(value = "SELECT * from HOLIDAY_MASTER WHERE CAL_YEAR=?1 ORDER BY CAL_MONTH ASC", nativeQuery = true)
	List<BTMAdminHolidayMaster> getHolidayList(String year);
	
	
	@Query(value = "SELECT CAST(current_value AS INT) FROM sys.sequences WHERE name = :sequenceName", nativeQuery = true)
	Integer getCurrentSequenceValue(@Param("sequenceName") String sequenceName);
	
	@Query(value = "SELECT NEXT VALUE FOR hrms.Holidayseq", nativeQuery = true)
	BigDecimal getNextSequenceValue();



}
