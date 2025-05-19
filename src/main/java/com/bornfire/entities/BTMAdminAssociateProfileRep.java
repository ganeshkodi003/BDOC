package com.bornfire.entities;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BTMAdminAssociateProfileRep extends CrudRepository<BTMAdminAssociateProfile, String> {

	public Optional<BTMAdminAssociateProfile> findById(String resId);


	@Query(value = "select * from RESOURCE_MASTER where del_flg='N' order by RESOURCE_ID ", nativeQuery = true)
	List<BTMAdminAssociateProfile> getAssociateDeletelist();
	
	@Query(value = "select count(*) from RESOURCE_MASTER where del_flg='N'", nativeQuery = true)
	int getAssociateProfilecount();
	
	@Query(value = "select * from RESOURCE_MASTER where del_flg='N' order by RESOURCE_ID ", nativeQuery = true)
	List<BTMAdminAssociateProfile> getAssociatelist();

	@Query(value = "select * from RESOURCE_MASTER where resource_id =?1 and del_flg='N' ", nativeQuery = true)
	BTMAdminAssociateProfile getEmployeedetail(String userid);
	
	@Query(value = "select * from RESOURCE_MASTER where resource_id =?1 and del_flg='N' ", nativeQuery = true)
	BTMAdminAssociateProfile getEmployeedetail1(String resId);
	
	@Query(value = "select * from RESOURCE_MASTER where del_flg='N' ORDER BY resource_id ASC ", nativeQuery = true)
	List<BTMAdminAssociateProfile> getEmployeedetail2();

	@Query(value = "select * from RESOURCE_MASTER where resource_id =?1", nativeQuery = true)
	BTMAdminAssociateProfile getEmployeedetailList(String resId);
	
	
	@Query(value = "SELECT * FROM RESOURCE_MASTER where resource_id=?1 ", nativeQuery = true)

	BTMAdminAssociateProfile delete2(String resId);
	
	
	@Query(value = "select CAST(CURRENT_VALUE AS BIGINT) from sys.sequences where name='EMP_ASSOCIATE_SEQ'  ", nativeQuery = true)
	BigInteger getseq();
	
	@Query(value = "select * from RESOURCE_MASTER ", nativeQuery = true)
	List<BTMAdminAssociateProfile> upadteleave();
	
	
	@Query(value = "SELECT * FROM RESOURCE_MASTER WHERE RESOURCE_ID = :resourceid AND del_flg = 'N' ORDER BY RESOURCE_ID", nativeQuery = true)
	List<BTMAdminAssociateProfile> getAssociatelistforphotos(@Param("resourceid") String resourceid);
	
    @Query(value = "SELECT * FROM RESOURCE_MASTER WHERE BRANCH_ID =?1 AND  del_flg = 'N' ORDER BY CAST(SUBSTRING(RESOURCE_ID, PATINDEX('%[0-9]%', RESOURCE_ID ), LEN(RESOURCE_ID)) AS BIGINT)DESC,  RESOURCE_ID ;" ,nativeQuery = true)
    List<BTMAdminAssociateProfile> getAssociatelistbesedbranch(String BRANCH_ID);





}
