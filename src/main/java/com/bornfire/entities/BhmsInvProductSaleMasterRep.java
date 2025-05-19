package com.bornfire.entities;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional

public interface BhmsInvProductSaleMasterRep extends JpaRepository<BhmsInvProductSaleMasterTable, String> {

	@Query(value = "select sale_id,cust_name,date_of_sale,total from bhms_inv_product_sale_master where date_of_sale=?1 group by sale_id,cust_name,date_of_sale,total", nativeQuery = true)
	List<Object[]> findAllCustom(String date_of_sale);
	
	@Query(value = "select sm.sale_id,sm.cust_name,sm.date_of_sale,pt.amount,pt.BLOOD_TEST_AMOUNT,pt.SCAN_AMOUNT,pt.TOTAL_BILL_AMT,(sm.TOTAL + pt.NET_TOTAL_AMT) as price from BHMS_INV_PRODUCT_SALE_MASTER sm INNER JOIN BHMS_PAYMENT_TABLE pt ON sm.SALE_ID=pt.VISIT_NO where sm.DATE_OF_SALE=?1", nativeQuery = true)
	List<Object[]> findAllCustomforMrgedBillPrint(String date_of_sale);

	@Query(value = "select sale_id,cust_name,date_of_sale,total from bhms_inv_product_sale_master where date_of_sale >=?1 And date_of_sale <=?2 group by sale_id,cust_name,date_of_sale,total", nativeQuery = true)
	List<Object[]> findAllCustomBetweenTwoDates(String Start, String End);

	@Query(value = "select * from bhms_inv_product_sale_master where sale_id=?1", nativeQuery = true)
	List<BhmsInvProductSaleMasterTable> findAllCustomId(String sid);

	@Query(value = "  SELECT MAX( CAST( SALE_ID AS int) ) FROM bhms_inv_product_sale_master", nativeQuery = true)
	int getUniqueSalesId();
	
	@Query(value = "select * from bhms_inv_product_sale_master", nativeQuery = true)
	List<BhmsInvProductSaleMasterTable> findAll();
	
    @Query(value = "SELECT * FROM ( " +
                   "SELECT *, ROW_NUMBER() OVER (PARTITION BY sale_id ORDER BY sale_id) AS row_num " +
                   "FROM bhms_inv_product_sale_master) AS subquery " +
                   "WHERE row_num = 1", nativeQuery = true)
    List<BhmsInvProductSaleMasterTable> findAllUniqueSaleIds();




}
