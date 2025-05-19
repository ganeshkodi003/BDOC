package com.bornfire.entities;


import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;




@Repository
public interface New_product_Rep extends CrudRepository<New_product_entity, String> {
	
	  @Query(value = "SELECT * FROM NEW_PRODUCTS " , nativeQuery = true)
	  List<New_product_entity> getall();
	 
		@Query(value = 
			    "SELECT * FROM ( " +
			    "SELECT *, ROW_NUMBER() OVER (PARTITION BY NP_ID ORDER BY NP_ID) AS rn " +
			    "FROM NEW_PRODUCTS " +
			    ") AS subquery " +
			    "ORDER BY MAKE_PRODUCT_NAME", nativeQuery = true)
			List<Object[]> findUnderProcess();
			
		    @Modifying
		    @Transactional // Ensures the delete operation is wrapped in a transaction
		    @Query("DELETE FROM New_product_entity e WHERE e.makeProductName = :makeProductName")
		    void deleteByMakeProductName(@Param("makeProductName") String makeProductName);
		   
		    @Query(value = "SELECT DISTINCT MAKE_PRODUCT_NAME FROM NEW_PRODUCTS", nativeQuery = true)
		    List<String> getMakeProductNames();


			@Query(value = "select PNAME,BATCH,UNITINKG,CATNAME,KGS,MAKE_PRODUCT_NAME,expensesType,expensesamount from NEW_PRODUCTS  where MAKE_PRODUCT_NAME=?1", nativeQuery = true)
			List<Object[]> findAllCustom(String MAKE_PRODUCT_NAME);
}