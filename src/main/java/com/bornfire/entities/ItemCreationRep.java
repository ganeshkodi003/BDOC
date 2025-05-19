package com.bornfire.entities;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCreationRep extends JpaRepository<ItemCreation, String> {

	@Query(value = "SELECT CAST(current_value AS BIGINT) FROM sys.sequences WHERE name ='ITEMCODE'", nativeQuery = true)
	BigInteger getItemCode();

	@Query(value = "SELECT * from item_creation where del_flg='N' and category='B01A' ", nativeQuery = true)
	List<ItemCreation> getitemrawmaterial();
	
	@Query(value = "SELECT * from item_creation where del_flg='N' and category='B01A' or category='B01D'", nativeQuery = true)
	List<ItemCreation> getitemrawmaterialandfg();

	@Query(value = "SELECT * from item_creation where del_flg='N' and category='B01E' ", nativeQuery = true)
	List<ItemCreation> getitemstoresandspares();

	@Query(value = "SELECT * from item_creation where del_flg='N' and category='B01B' ", nativeQuery = true)
	List<ItemCreation> getitemPackingMaterial();

	@Query(value = "SELECT * from item_creation where del_flg='N' and category='B01D' ", nativeQuery = true)
	List<ItemCreation> getitemFinishedgoods();

	@Query(value = "SELECT * from item_creation where del_flg='N' and category='B01C' ", nativeQuery = true)
	List<ItemCreation> getitemSemiFinishedgoods();

	@Query(value = "SELECT * FROM item_creation WHERE del_flg = 'N' ORDER BY CAST(SUBSTRING(item_code, PATINDEX('%[0-9]%', item_code ), LEN(item_code)) AS BIGINT)DESC, item_code DESC", nativeQuery = true)
	List<ItemCreation> getitemlist();
	
	@Query(value = "SELECT * from item_creation where del_flg='N' and category!='B01D' ORDER BY item_code desc ", nativeQuery = true)
	List<ItemCreation> getitemforpurchaselist();
	
	@Query(value = "SELECT * from item_creation where del_flg='N' And category='B01D' ORDER BY item_code desc ", nativeQuery = true)
	List<ItemCreation> getitemlistbyfg();

	@Query(value = "SELECT * FROM item_creation WHERE item_code = ?1", nativeQuery = true)
	List<Object[]> getSingleRow(String code);
	
	@Query(value = "SELECT * FROM item_creation WHERE unit = ?1 And category='B01D'", nativeQuery = true)
	List<ItemCreation> getSinglebyunit(String unit);

	@Query(value = "SELECT * from item_creation where item_code=?1 ", nativeQuery = true)
	ItemCreation getitemlistbyid(String item_code);
	
	@Query(value = "SELECT category from item_creation where item_code=?1 ", nativeQuery = true)
	String getitemlistbyidcat(String item_code);
	
	@Query(value = "SELECT * FROM item_creation WHERE item_code = ?1", nativeQuery = true)
	List<ItemCreation> getdetailsitem(String itemCode);

}
