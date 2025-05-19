package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorCreationRep  extends JpaRepository<VendorCreation,String> {

	@Query(value = "SELECT * FROM VendorView ORDER BY vendor_number, vendor_id", nativeQuery = true)
    List<VendorCreation> getvendorlist();
	@Query(value = "SELECT * FROM VendorCreation WHERE del_flg = 'N' ORDER BY CAST(SUBSTRING(vendor_id, PATINDEX('%[0-9]%', vendor_id ), LEN(vendor_id)) AS BIGINT)DESC, vendor_id DESC ;" ,nativeQuery = true)
    List<VendorCreation> getvendorlist1();


	
	
	@Query(value = "SELECT * FROM VendorCreation WHERE del_flg = 'N' AND vendor_type='PurchaseVendor' ORDER BY CAST(SUBSTRING(vendor_id, PATINDEX('%[0-9]%', vendor_id ), LEN(vendor_id)) AS BIGINT)DESC, vendor_id DESC ; ", nativeQuery = true)
    List<VendorCreation> getpurchaseVendor();

	@Query(value = "SELECT * FROM VendorCreation WHERE BRANCH_ID=?1 AND del_flg = 'N' AND vendor_type='PurchaseVendor' ORDER BY CAST(SUBSTRING(vendor_id, PATINDEX('%[0-9]%', vendor_id ), LEN(vendor_id)) AS BIGINT)DESC, vendor_id DESC ; ", nativeQuery = true)
    List<VendorCreation> getpurchaseVendorone(String branchId);
	
	@Query(value = "SELECT * FROM VendorCreation WHERE del_flg = 'N' AND vendor_type='salevendor' ORDER BY CAST(SUBSTRING(vendor_id, PATINDEX('%[0-9]%', vendor_id ), LEN(vendor_id)) AS BIGINT)DESC, vendor_id DESC ;", nativeQuery = true)
    List<VendorCreation> getsaleVendor();

	@Query(value = "SELECT * FROM VendorCreation WHERE BRANCH_ID=?1 AND del_flg = 'N' AND vendor_type='salevendor' ORDER BY CAST(SUBSTRING(vendor_id, PATINDEX('%[0-9]%', vendor_id ), LEN(vendor_id)) AS BIGINT)DESC, vendor_id DESC ;", nativeQuery = true)
    List<VendorCreation> getsaleVendorone(String branchId);
	
	
	
	@Query(value = "SELECT * FROM VendorCreation where vendor_id=?1", nativeQuery = true)
    VendorCreation getvendorlistid(String vendor_id);
	
	@Query(value = "SELECT * FROM VendorCreation where vendor_type='SaleVendor' ", nativeQuery = true)
    List<VendorCreation> getsalesvendorlist();
	
	boolean existsByVendorTypeAndMobileNoAndVendorNameAndSalecategory(String vendorType, String mobileNo, String vendorName, String salecategory);

	boolean existsByVendorTypeAndMobileNoAndVendorNameAndVendorIdNot(
		    String vendorType, String mobileNo, String vendorName, String vendorId
		);
	
	
	@Query(value="SELECT * FROM VendorCreation where vendor_id=?1" , nativeQuery = true)
	VendorCreation getvendornameonlyfordownload(String vendorid);
	
	
	@Query(value = "SELECT * FROM VendorCreation where del_flg ='N' AND vendor_type='SaleVendor' ", nativeQuery = true)
    List<VendorCreation> getsaleVendors();
	

	@Query(value = "SELECT * FROM VendorCreation where BRANCH_ID = ?1 AND  org_id=?2 AND  del_flg = 'N' ORDER BY CAST(SUBSTRING(vendor_id, PATINDEX('%[0-9]%', vendor_id ), LEN(vendor_id)) AS BIGINT)DESC, vendor_id DESC ;" ,nativeQuery = true)
    List<VendorCreation> getvendorlistbyBranch(String BRANCH_ID,String org_id);

}
