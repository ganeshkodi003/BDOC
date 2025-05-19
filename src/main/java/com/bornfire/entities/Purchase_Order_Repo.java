package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Purchase_Order_Repo extends JpaRepository<Purchase_Order_Entity,String>{
	
	@Query(value = "select * from PURCHASE_ORDER_TABLE ORDER BY  PO_ID ", nativeQuery = true)
	 List<Purchase_Order_Entity> getPolist ();
	 
	 @Query(value = "select * from PURCHASE_ORDER_TABLE where GATE_ENTRY_FLG ='N'  ORDER BY  PO_ID ", nativeQuery = true)
	 List<Purchase_Order_Entity> getPolists ();
	 
	 @Query(value = "select * from PURCHASE_ORDER_TABLE where PO_ID=?1 ", nativeQuery = true)
	 Purchase_Order_Entity getinquiry( String poId);
	 
	 @Query(value = "select * from PURCHASE_ORDER_TABLE where date=?1 ", nativeQuery = true)
	 List<Purchase_Order_Entity> getdatewise (String date);
	 
	 @Query(value = "select * from PURCHASE_ORDER_TABLE where GATE_ENTRY_FLG ='Y'  ", nativeQuery = true)
	 List<Purchase_Order_Entity> getPolistforquality();
	 
	 @Query(value = "select * from PURCHASE_ORDER_TABLE where VendorId=?1 AND GATE_ENTRY_FLG ='Y' AND QC_FLG='Y'", nativeQuery = true)
	 List<Purchase_Order_Entity> getPolistforqualityS(String vandorid);
	
	 @Query(value = "select PO_ID from PURCHASE_ORDER_TABLE where QC_FLG='Y' and (Journal_flg !='Y' or Journal_flg is null)  ", nativeQuery = true)
	 List<String> getPoname();
	 
	 @Query(value = "select * from PURCHASE_ORDER_TABLE where PO_ID=?1  ", nativeQuery = true)
	 List<Purchase_Order_Entity> getpodetails(String item);
	 
	 @Query(value = "select * from PURCHASE_ORDER_TABLE where PO_ID=?1 and HSN_SAC_CODE=?2  ", nativeQuery = true)
	 List<Purchase_Order_Entity> getamountforPO(String PO_ID,String hsnSacCode);
	 
	 @Query(value = "select * from PURCHASE_ORDER_TABLE where VendorId=?1 ", nativeQuery = true)
	 List<Purchase_Order_Entity> getpodeails(String vandorid);
	
	 @Query(value = "select * from PURCHASE_ORDER_TABLE where PO_ID=?1 ", nativeQuery = true)
	 List<Purchase_Order_Entity> getdownloadpdfpo( String poId);
	 
	 @Query(value = "select * from PURCHASE_ORDER_TABLE where QC_FLG='Y'  ", nativeQuery = true)
	 List<Purchase_Order_Entity> getqcforgrn();
	 
}

