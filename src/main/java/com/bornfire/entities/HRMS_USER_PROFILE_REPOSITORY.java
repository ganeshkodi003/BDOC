package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HRMS_USER_PROFILE_REPOSITORY extends JpaRepository<HRMS_USER_PROFILE_ENTITY,String> {
	
	@Query(value = "SELECT * FROM HRMS_USER_PROFILE_TABLE_NEW  where DEL_FLG='N' ", nativeQuery = true)

	List<HRMS_USER_PROFILE_ENTITY> getAlllist();
	
	@Query(value = "SELECT * FROM HRMS_USER_PROFILE_TABLE_NEW where BRANCH_ID =?1 AND  DEL_FLG='N' ", nativeQuery = true)
      List<HRMS_USER_PROFILE_ENTITY> getAlllistbranch(String branchid);
	
	@Query(value = "SELECT * FROM HRMS_USER_PROFILE_TABLE_NEW where USER_ID=?1  ", nativeQuery = true)

	HRMS_USER_PROFILE_ENTITY getlistuserid(String USER_ID);
	
	 @Query(value="SELECT * FROM HRMS_USER_PROFILE_TABLE_NEW WHERE USER_ID=?1 ", nativeQuery = true)
	 HRMS_USER_PROFILE_ENTITY getrole(String userid);
	 
		@Query(value = "SELECT * FROM HRMS_USER_PROFILE_TABLE_NEW WHERE BRANCH_ID =?1 AND ORG_ID=?2 AND  del_flg = 'N' ORDER BY CAST(SUBSTRING(USER_ID, PATINDEX('%[0-9]%', USER_ID ), LEN(USER_ID)) AS BIGINT)DESC, USER_ID DESC ;" ,nativeQuery = true)
	    List<HRMS_USER_PROFILE_ENTITY> getuserid(String BRANCH_ID,String ORG_ID );

}
