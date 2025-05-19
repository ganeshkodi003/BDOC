package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PlacementMaintenanceRep extends JpaRepository<PlacementMaintenance, String>{
	

	@Query(value = "SELECT DISTINCT * FROM BTM_PLACEMENT_MASTER", nativeQuery = true)

	List<PlacementMaintenance> getMaintenance();
	
	@Query(value = "SELECT * FROM BTM_PLACEMENT_MASTER", nativeQuery = true)

	List<PlacementMaintenance> getinvlist();
	
	@Query(value = "SELECT * FROM BTM_PLACEMENT_MASTER", nativeQuery = true)

	List<PlacementMaintenance> getpodetails();
	
	@Query(value = "SELECT * FROM BTM_PLACEMENT_MASTER where FLAG='Y'", nativeQuery = true)

	List<PlacementMaintenance> getSdetail();
	
	@Query(value = "SELECT * FROM BTM_PLACEMENT_MASTER where FLAG='N'", nativeQuery = true)

	List<PlacementMaintenance> getFdetail();

	@Query(value = "SELECT  DISTINCT * FROM BTM_PLACEMENT_MASTER WHERE PO_ID =?1", nativeQuery = true)
	List<PlacementMaintenance> getPoMaintenance(String po_id);
	
	@Query(value = "SELECT  * FROM BTM_PLACEMENT_MASTER WHERE PO_NO =?1", nativeQuery = true)
	List<PlacementMaintenance> getPoMain(String po_no);
	
	@Query(value = "SELECT  * FROM BTM_PLACEMENT_MASTER WHERE PO_ID =?1 ", nativeQuery = true)
	PlacementMaintenance getPoM(String po_id);
	
	@Query(value = "SELECT  * FROM BTM_PLACEMENT_MASTER WHERE PO_ID =?1", nativeQuery = true)
	PlacementMaintenance getPoId(String po_id);
	
	@Query(value = "SELECT  * FROM BTM_PLACEMENT_MASTER WHERE INV_NO =?1", nativeQuery = true)
	PlacementMaintenance getinvoicelist(String inv_no);

	 @Query(value = "SELECT * FROM BTM_PLACEMENT_MASTER WHERE PO_ID =?1",  nativeQuery = true)
	 PlacementMaintenance getMaintenancelist(String po_id);
	 
	 @Query(value = "SELECT * FROM BTM_PLACEMENT_MASTER where  FLAG='Y'", nativeQuery = true)
	 List<PlacementMaintenance> getReupload(String upload_date);
	 
	 @Query(value = "SELECT * FROM BTM_PLACEMENT_MASTER where  PO_NO=?1", nativeQuery = true)
	 List<PlacementMaintenance> getPolist(String po_no); 
	 
	 @Query(value = "SELECT * FROM BTM_PLACEMENT_MASTER where  INV_NO=?1", nativeQuery = true)
	PlacementMaintenance getAlist(String inv_no); 
	 
	 @Query(value = "select * from BTM_PLACEMENT_MASTER WHERE po_id =?1  ", nativeQuery = true) 
	 PlacementMaintenance getplacementlist2(String po_id);
	 
	 
		/*
		 * @Query(value =
		 * "SELECT * FROM BTM_PLACEMENT_MASTER WHERE grn_no IS NULL AND TO_CHAR(TO_DATE(po_month, 'YYYY-MON'), 'YYYY-MM') < TO_CHAR(TRUNC(SYSDATE, 'MM'), 'YYYY-MM')"
		 * , nativeQuery = true) List<PlacementMaintenance> getponulldetails();
		 */
	 
	 @Query(value = "SELECT * FROM BTM_PLACEMENT_MASTER WHERE grn_no IS NULL AND FORMAT(CAST(po_month AS DATE), 'yyyy-MM') < FORMAT(DATEADD(MONTH, DATEDIFF(MONTH, 0, GETDATE()), 0), 'yyyy-MM')", nativeQuery = true) 
	 List<PlacementMaintenance> getponulldetails();

	 
	 @Query(value = "SELECT * FROM BTM_PLACEMENT_MASTER WHERE sp IS NOT NULL AND grn_no IS NOT NULL AND sp = ?1 AND inv_due_date >= ?2 and inv_due_date <= ?3" , nativeQuery = true) 
	 List<PlacementMaintenance> getUpdate(String sp,String inv_due_Date,String inv_date);
	 
	 @Query(value = "SELECT * FROM BTM_PLACEMENT_MASTER WHERE sp IS NOT NULL AND grn_no IS NOT NULL AND sp = ?1 AND inv_due_date >= ?2 and inv_due_date <= ?3" , nativeQuery = true) 
	 List<PlacementMaintenance> getsplist(String sp,String inv_due_Date,String inv_date);
	 
	  @Query(value = "SELECT * FROM BTM_PLACEMENT_MASTER WHERE po_id IN :poIds", nativeQuery = true)
	  List<PlacementMaintenance> getPonullDetails2(@Param("poIds") List<String> poIds);
	  
	 @Query(value = "SELECT *FROM BTM_PLACEMENT_MASTER WHERE sp IS NOT NULL AND grn_no IS NOT NULL AND sp='ASOFT'" , nativeQuery = true) 
	 List<PlacementMaintenance> getUpdate1();
	 
	 @Query(value = "SELECT *FROM BTM_PLACEMENT_MASTER WHERE sp IS NOT NULL AND grn_no IS NOT NULL AND sp='STACKPOS'" , nativeQuery = true) 
	 List<PlacementMaintenance> getUpdate2();
	 
	 @Query(value = "SELECT * FROM BTM_PLACEMENT_MASTER where po_month=?1", nativeQuery = true)
		List<Object[]>  getAbsenteesFromDatabase(String po_month,String location,String po_no );
		 @Query(value = "SELECT * FROM BTM_PLACEMENT_MASTER where po_month=?1 AND  grn_no is  null", nativeQuery = true)
			List<PlacementMaintenance>  getAbsenteesFrom(String po_month);
		 @Query(value = "SELECT * from BTM_PLACEMENT_MASTER where grn_no is NOT null", nativeQuery = true)

			List<PlacementMaintenance> grnstsnotnull();
	
}


