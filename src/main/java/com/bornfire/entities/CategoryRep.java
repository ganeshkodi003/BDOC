package com.bornfire.entities;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRep extends JpaRepository<Category, String>  {
	@Query(value = "SELECT CAST(current_value AS BIGINT) FROM sys.sequences WHERE name ='CategoryNo'", nativeQuery = true)
	BigInteger getCategoryNo();
	
	@Query(value = "SELECT CAST(current_value AS BIGINT) FROM sys.sequences WHERE name ='Head_code'", nativeQuery = true)
	BigInteger headcodeNo();
	
	@Query(value = "SELECT CAST(current_value AS BIGINT) FROM sys.sequences WHERE name ='CATEGORYCODESEQ'", nativeQuery = true)
	BigInteger CATEGORYCODESEQNo();
	
	@Query(value = "select * from Category where del_flg='N' order by assetcode ", nativeQuery = true)
	List<Category> getCategorylist();
	
	@Query(value = "select * from Category where assetcode=?1  ", nativeQuery = true)
	Category getCategorybycode(String assetcode);
	
	
	
	
	
	

}
