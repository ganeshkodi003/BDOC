package com.bornfire.entities;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface BHMSInventoryProductStockCurrentRepo extends JpaRepository<BHMSInventoryProductStockCurrent, String> {

	@Query(value = "select * from BHMS_INV_PRODUCT_STOCK_CURRENT", nativeQuery = true)
	List<Object[]> productStockCurrentList();
	
	@Query(value = "select * from BHMS_INV_PRODUCT_STOCK_CURRENT", nativeQuery = true)
	List<BHMSInventoryProductStockCurrent> productStockCurrentList1();
	
	@Query(value = "select * from BHMS_INV_PRODUCT_STOCK_CURRENT where UNITS > 0", nativeQuery = true)
	List<BHMSInventoryProductStockCurrent> productStockList();
	
	@Query(value = "select BATCH,CATEGORY_NAME from BHMS_INV_PRODUCT_STOCK_CURRENT  where PRODUCT_NAME=?1", nativeQuery = true)
	List<Object[]> findAllCustom(String product_id);
	
	@Query(value = "select PKG,EXPIRY_DATE from BHMS_INV_PRODUCT_STOCK_CURRENT  where PRODUCT_NAME=?1 and BATCH=?2", nativeQuery = true)
	List<Object[]> findallstocktype(String product_name,String batch);
	
	@Query(value = "select PKG,EXPIRY_DATE from BHMS_INV_PRODUCT_TABLE  where PRODUCT_NAME=?1 and BATCH=?2", nativeQuery = true)
	List<Object[]> findallexpirydate(String product_name,String batch);
	
	
	
	@Query(value = "select PKG,UNITS, MRP,SUB_UNITS_COST,GST_PERCENT from BHMS_INV_PRODUCT_STOCK_CURRENT where PRODUCT_NAME=?1 and BATCH=?2", nativeQuery = true)
	List<Object[]> productStockCurrent(String product_name,String batch);

	@Query(value = "select *  from BHMS_INV_PRODUCT_STOCK_CURRENT where PRODUCT_NAME=?1 and BATCH=?2 ", nativeQuery = true)
	BHMSInventoryProductStockCurrent findPackdata(String product_id, String batch);
	
	@Query(value = "select *  from BHMS_INV_PRODUCT_STOCK_CURRENT where BATCH=?1 ", nativeQuery = true)
	BHMSInventoryProductStockCurrent findcurrentstockdata(String batch);
	
	@Query(value = "select distinct[PRODUCT_NAME] from BHMS_INV_PRODUCT_STOCK_CURRENT", nativeQuery = true)
	List<Object[]> getProductStocksnames();
	
	
	@Query(value = "select * from BHMS_INV_PRODUCT_STOCK_CURRENT  where PRODUCT_NAME=?1 ", nativeQuery = true)
	List<BHMSInventoryProductStockCurrent> findAllUnit(String product_id);

	@Query(value = "select *  from BHMS_INV_PRODUCT_STOCK_CURRENT where id=?1 ", nativeQuery = true)
	BHMSInventoryProductStockCurrent findstockedit(String id);
}




