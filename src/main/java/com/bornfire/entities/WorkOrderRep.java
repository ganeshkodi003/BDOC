package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface WorkOrderRep extends JpaRepository<WorkOrder, String> {
	
	@Query(value = "select * from WORK_ORDER_TABLE  ORDER BY  WORK_ID ", nativeQuery = true)
	 List<WorkOrder> getwolist ();
	
	@Query(value = "SELECT * FROM WORK_ORDER_TABLE WHERE status IS NULL OR status = 'rejected' ORDER BY WORK_ID", nativeQuery = true)
	List<WorkOrder> getwolists();
	
	@Query(value = "SELECT * FROM WORK_ORDER_TABLE WHERE item=?1 and refid=?2 and status IS NULL OR status ='completed' ORDER BY WORK_ID", nativeQuery = true)
	WorkOrder getwolistsname(String item,String refid);

	
	@Query(value = "select * from WORK_ORDER_TABLE where  WORK_ID=?1 ", nativeQuery = true)
	 WorkOrder getinquirywolist (String workId);
	
	@Query(value = "select * from WORK_ORDER_TABLE where  refid=?1 ", nativeQuery = true)
	 WorkOrder getinquirywolist11 (String refid);
	
	@Query(value = "select WORK_ID from WORK_ORDER_TABLE where status='Completed' And(Journal_flg !='Y' or Journal_flg is null)", nativeQuery = true)
	 List<String> Woname ();
	
	@Query(value = "SELECT * FROM WORK_ORDER_TABLE where WORK_ID=?1", nativeQuery = true)
	List<WorkOrder> workdetails( String item);
	
	@Query(value = "select * from WORK_ORDER_TABLE where VendorId=?1", nativeQuery = true)
	 List<WorkOrder> getWorkOrder(String vandorid);
	
	@Query(value = "SELECT * FROM WORK_ORDER_TABLE where  WORK_ID=?1 and HSN_SAC_CODE=?2 ", nativeQuery = true)
	List<WorkOrder> getamountforWO( String item,String hsnSacCode );
	
	@Query(value = "select * from WORK_ORDER_TABLE where  WORK_ID=?1 ", nativeQuery = true)
	List< WorkOrder> downloadpdfwo (String workId);
	
	

}
