package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Taxation_parameter_Rep extends CrudRepository<Taxation_parameters, BigDecimal>  {

		
		 @Query(value = "SELECT tp.* FROM TAXATION_PARAMETERS tp " +"WHERE tp.srl_no IN (SELECT MIN(tp2.srl_no) " +
          "FROM TAXATION_PARAMETERS tp2 " + "GROUP BY tp2.group_name)", nativeQuery = true)
		 List<Taxation_parameters> getdata();
		
		@Query(value = "SELECT * from TAXATION_PARAMETERS where group_name = ?1 ", nativeQuery = true)
		List<Taxation_parameters> getview(String group_name);
		
		 @Query(value = "SELECT DISTINCT national_country FROM TAXATION_PARAMETERS", nativeQuery = true)
		    List<String> getDistinctCountries();
		
		 
		@Query(value = "SELECT * from TAXATION_PARAMETERS where national_country = ?1  AND status = 'Verified' ", nativeQuery = true)
		List<Taxation_parameters> getcountryrows(String national_country);

}
