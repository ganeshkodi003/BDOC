package com.bornfire.entities;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CodeCreationRep extends JpaRepository<CodeCreation, String> {
	
	@Query(value = "SELECT DISTINCT Head_code,Head_description FROM Code_Creation WHERE category_code IS NULL AND category_description IS NULL AND sub_category_code IS NULL AND sub_category_description IS NULL AND asset_code IS NULL", nativeQuery = true)
	List<Object> getheadcode();
	
	@Query(value = "select * from Code_Creation where Head_code=?1 and Head_description is not null", nativeQuery = true)
	CodeCreation getheadcodebyaccount(String Head_code );
	
	@Query(value = "select * from Code_Creation where Head_code=?1 AND category_code=?2 and sub_category_code= ?3", nativeQuery = true)
	CodeCreation getheadcodebyhcs(String Head_code, String category_code , String sub_category_code );
	
	
	
	@Query(value = "select * from Code_Creation where Head_code=?1 and category_code=?2 and category_description is not null", nativeQuery = true)
	CodeCreation getheadcodebysubacccount(String Head_code, String category_code);
	
	@Query(value = "SELECT COUNT(DISTINCT category_code) FROM Code_Creation where Head_code=?1", nativeQuery = true)
	int getcategory_code(String Head_code);
	
	@Query(value = "SELECT COUNT(DISTINCT sub_category_code) FROM Code_Creation where category_code=?1 And Head_code=?2", nativeQuery = true)
	BigInteger getsubcategory_code(String category_code ,String Head_code);
	
	
	@Query(value = "SELECT DISTINCT category_code,category_description FROM Code_Creation where Head_code=?1 And category_code IS NOT NULL And category_description IS NOT NULL" , nativeQuery = true)
	List<Object> getcategory(String Head_code);
	
	@Query(value = "SELECT DISTINCT sub_category_code,sub_category_description FROM Code_Creation where Head_code=?1 And category_code=?2 and sub_category_code is not null and sub_category_description is not null" , nativeQuery = true)
	List<Object> getsubcategory(String Head_code,String category_code);
	
	
	

}
