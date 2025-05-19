package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface PROCESS_REP extends JpaRepository<PROCESS_ENTITY, String>{
	@Query(value = 
		    "SELECT * FROM ( " +
		    "SELECT *, ROW_NUMBER() OVER (PARTITION BY ref_id ORDER BY ref_id) AS rn " +
		    "FROM BHMS_PROCESS_TABLE " +
		    "WHERE status = 'work in progress' " +
		    ") AS subquery " +
		    "WHERE rn = 1 " +
		    "ORDER BY ref_id", nativeQuery = true)
		List<Object[]> findUnderProcess();

		@Query(value = 
			    "SELECT * FROM ( " +
			    "SELECT *, ROW_NUMBER() OVER (PARTITION BY ref_id ORDER BY ref_id) AS rn " +
			    "FROM BHMS_PROCESS_TABLE " +
			    "WHERE status = 'Completed' And InspectProcess is null " +
			    ") AS subquery " +
			    "WHERE rn = 1 " +
			    "ORDER BY ref_id", nativeQuery = true)
			List<Object[]> completedProcesss();

			@Query(value = 
				    "SELECT * FROM ( " +
				    "SELECT *, ROW_NUMBER() OVER (PARTITION BY ref_id ORDER BY ref_id) AS rn " +
				    "FROM BHMS_PROCESS_TABLE " +
				    "WHERE status = 'Completed' And InspectProcess is not null " +
				    ") AS subquery " +
				    "WHERE rn = 1 " +
				    "ORDER BY ref_id", nativeQuery = true)
				List<Object[]> qacompletedProcess();
		

	@Query(value = "SELECT * FROM BHMS_PROCESS_TABLE WHERE status = 'Completed'", nativeQuery = true)
	List<Object[]> completedProcess();
	

	@Query(value = "SELECT * FROM BHMS_PROCESS_TABLE WHERE status = 'Rejected'", nativeQuery = true)
	List<Object[]> Rejectprocess();

	
	@Query(value = "select * from BHMS_PROCESS_TABLE", nativeQuery = true)
	List<Object[]>  findAllCustom();
	
	@Query(value = "select * from BHMS_PROCESS_TABLE", nativeQuery = true)
	List<PROCESS_ENTITY>  findAlldatass();
	
	@Query(value = "select * from BHMS_PROCESS_TABLE where ref_id=?1", nativeQuery = true)
	List<PROCESS_ENTITY> findByID(String ref_id);
	
	@Query(value = "select TOP 1 * from BHMS_PROCESS_TABLE where ref_id=?1", nativeQuery = true)
	PROCESS_ENTITY findByIDS(String ref_id);
	
	@Query(value = "SELECT DISTINCT pname FROM BHMS_PROCESS_TABLE", nativeQuery = true)
	List<String> findAllDistinctProductNames();
	
	@Query(value = "SELECT DISTINCT make_product_name FROM BHMS_PROCESS_TABLE where status='Completed' and InspectProcess ='Completed' ", nativeQuery = true)
	List<String> findAllmakeProductNames();

	@Query(value = "select ref_id,catname from BHMS_PROCESS_TABLE  where pname=?1", nativeQuery = true)
	List<Object[]> findAllCustomss(String pname);

	@Query(value = "select ref_id,catname from BHMS_PROCESS_TABLE  where make_product_name=?1", nativeQuery = true)
	List<Object[]> findffff(String make_product_name);

	
	@Query(value = "select expdate from BHMS_PROCESS_TABLE  where PRODUCT_NAME=?1 and ref_id=?2", nativeQuery = true)
	List<Object[]> findallstocktype(String product_name,String ref_id);
	

	@Query(value = "select expdate from BHMS_PROCESS_TABLE  where make_product_name=?1 and ref_id=?2", nativeQuery = true)
	List<Object[]> findallmakes(String make_product_name,String ref_id);
	
	@Query(value = "select PKGS,UNITINKG, MRP,GSTIN from BHMS_PROCESS_TABLE where PRODUCT_NAME=?1 and ref_id=?2", nativeQuery = true)
	List<Object[]> productStockCurrent(String product_name,String ref_id);

	@Query(value = "select PKGS,UNITINKG, MRP,GSTIN from BHMS_PROCESS_TABLE where make_product_name=?1 and ref_id=?2", nativeQuery = true)
	List<Object[]> productStockCurrentll(String make_product_name,String ref_id);
}


