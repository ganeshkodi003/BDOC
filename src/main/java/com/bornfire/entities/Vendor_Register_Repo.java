package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Vendor_Register_Repo extends JpaRepository<Vendor_registration_entity,String>
{
	
	@Query(value = "SELECT * FROM VENDOR_REGISTRATION_TABLE", nativeQuery = true)
      List<Vendor_registration_entity> getvendorlist();
      
      @Query(value = "SELECT * FROM VENDOR_REGISTRATION_TABLE where vendor_doc_id=?1 ", nativeQuery = true)
      Vendor_registration_entity getvendorparticular(String vendorDocId);
      
      @Query(value = "SELECT vendor_doc_id FROM VENDOR_REGISTRATION_TABLE", nativeQuery = true)
      List<String> getvendordetails();
      
      @Query(value = "SELECT * FROM VENDOR_REGISTRATION_TABLE where vendor_doc_id=?1 ", nativeQuery = true)
      List<Vendor_registration_entity> getvdetails(String vendor_id);
      
      
      @Query(value = "SELECT vendor_doc_id FROM VENDOR_REGISTRATION_TABLE where acc_num_flg='N' ", nativeQuery = true)
      List<String> getvendoridforCOA();

}
