
package com.bornfire.entities;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface OFFER_ALERT_REP extends JpaRepository<OFFER_ALERT_ENTITY, String> {

	@Query(value = "select * from OFFER_ALERT", nativeQuery = true)
	 Optional<OFFER_ALERT_ENTITY> getbycatid();
	 
	 @Query(value = "select * from OFFER_ALERT where DEL_FLG='N' ORDER BY ID", nativeQuery = true)
	 List<OFFER_ALERT_ENTITY> getall();
	 @Query(value = "select * from OFFER_ALERT where ID =?1", nativeQuery = true)
	 OFFER_ALERT_ENTITY getbyid(String id);
	 


		@Query(value = "select * from OFFER_ALERT where ID =?1", nativeQuery = true)
		OFFER_ALERT_ENTITY getalls(String id);
		

		@Query(value = "select * from OFFER_ALERT where BRANCH_ID =?1 AND ORG_ID=?2  AND DEL_FLG='N'", nativeQuery = true)
		List<OFFER_ALERT_ENTITY> getuserid(String BRANCH_ID,String ORG_ID);
		 
	
}
