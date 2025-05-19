package com.bornfire.entities;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ERP_EXPENSES_REPO extends JpaRepository<ERP_EXPENSES_ENTITY, Long>
{
	
	@Query(value = "select cast(count(DISTINCT EXP_ID) AS int)  from  ERP_EXPENSES_MASTER  ", nativeQuery = true)
	int getexpid();
	
	@Query(value = "SELECT * FROM ERP_EXPENSES_MASTER", nativeQuery = true)
	List<ERP_EXPENSES_ENTITY> getExplists();
	
	@Query(value = "SELECT * FROM (SELECT *, ROW_NUMBER() OVER (PARTITION BY EXP_ID ORDER BY EXP_ID ) AS row_num FROM ERP_EXPENSES_MASTER ) \r\n" + 
			"\r\n" + 
			"AS subquery WHERE row_num = 1 ;", nativeQuery = true)
	List<ERP_EXPENSES_ENTITY> getExplistsSS();
	
	@Query(value = "SELECT * FROM ERP_EXPENSES_MASTER WHERE EXP_ID=?1 ", nativeQuery = true)
	List<ERP_EXPENSES_ENTITY> getExplistsparticulr(String expId);

	@Query(value = "SELECT * FROM ERP_EXPENSES_MASTER", nativeQuery = true)
	List<ERP_EXPENSES_ENTITY> getAmtDate();
	
	@Query(value = "SELECT * FROM ERP_EXPENSES_MASTER WHERE BRANCH_ID = :branchId", nativeQuery = true)
	List<ERP_EXPENSES_ENTITY> getAmtDateBranch(@Param("branchId") String branchId);

	
	@Query(value = "SELECT * FROM ERP_EXPENSES_MASTER " +
	        "WHERE (:branchId IS NULL OR branch_id = :branchId) " +
	        "  AND (:fromDate IS NULL OR EXP_DATE >= CAST(:fromDate AS DATE)) " +
	        "  AND (:toDate IS NULL OR EXP_DATE <= CAST(:toDate AS DATE))",
	        nativeQuery = true)
	List<ERP_EXPENSES_ENTITY> getAmtDate(
	        @Param("branchId") String branchId,
	        @Param("fromDate") Date  fromDate,
	        @Param("toDate") Date  toDate
	);

	@Query(value = "SELECT * FROM ERP_EXPENSES_MASTER " +
            "WHERE (:branchId IS NULL OR branch_id = :branchId) " +
            "ORDER BY exp_date", 
    nativeQuery = true)
List<ERP_EXPENSES_ENTITY> getAmtDatess(@Param("branchId") String branchId);

}