package com.bornfire.entities;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BDOCUserProfileRep extends CrudRepository<BDOCUserProfile, String> {

	public Optional<BDOCUserProfile> findByusername(String userName);

	@Query(value = "select * from BIPS_USER_PROFILE where USER_ID= ?1", nativeQuery = true)
	BDOCUserProfile findByIdCustom(String Id);

	@Query(value = "select * from BIPS_USER_PROFILE where NVL(DEL_FLG,1) <> 'Y'", nativeQuery = true)
	List<BDOCUserProfile> findByAll(String Id);

	@Query(value = "select count(*) from BIPS_USER_PROFILE where del_flg='N'  and user_id=?1 ", nativeQuery = true)
	String getusercount(String custId);

	@Query(value = "select * from BIPS_USER_PROFILE where USER_ID =?1 and del_flg='N' ", nativeQuery = true)
	BDOCUserProfile getEmployeedetails(String userid);

	@Query(value = "SELECT * FROM BIPS_USER_PROFILE WHERE USER_ID=?1 ", nativeQuery = true)
	BDOCUserProfile getroles(String userid);

	@Query(value = "SELECT * FROM BIPS_USER_PROFILE WHERE USER_ID=?1 AND del_flg='N' UNION ALL SELECT * FROM BIPS_USER_PROFILE_MOD_TABLE WHERE USER_ID=?1 AND del_flg='N'", nativeQuery = true)
	List<BDOCUserProfile> getBlobImg(String userid);

}
