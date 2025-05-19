package com.bornfire.entities;


import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface BHMSInventoryProductStockRepo extends JpaRepository<BHMSInventoryProductStock, String> {

	@Query(value = "select BATCH,CATEGORY_NAME from BHMS_INV_PRODUCT_TABLE  where PRODUCT_NAME=?1", nativeQuery = true)

	List<Object[]> findAllCustom(String product_id);
	
	
	@Query(value = "select * from BHMS_INV_PRODUCT_TABLE", nativeQuery = true)
	List<Object[]> productStockList();

	@Query(value = "select * from BHMS_INV_PRODUCT_TABLE  where ID=?1 ", nativeQuery = true)
	BHMSInventoryProductStock findAllCustomUnit(String product_id);
	
	
	@Query(value = "SELECT NEXT VALUE FOR ID", nativeQuery = true)

	int srlnum();
	

	@Query(value = "select AMOUNT,PURCHASE_DATE from BHMS_INV_PRODUCT_TABLE  where PRODUCT_NAME=?1 and BATCH=?2", nativeQuery = true)
	List<Object[]> amountget(String product_name,String batch);

}

