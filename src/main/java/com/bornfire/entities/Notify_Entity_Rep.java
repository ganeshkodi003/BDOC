package com.bornfire.entities;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface Notify_Entity_Rep extends JpaRepository<Notify_Entity, String> {

	@Query(value = "select * from NOTIFICATION_MASTER", nativeQuery = true)
	 Optional<Notify_Entity> getbycatid();
	 
	 @Query(value = "select * from NOTIFICATION_MASTER where DEL_FLG='N' ORDER BY ID", nativeQuery = true)
	 List<Notify_Entity> getall();
	 @Query(value = "select * from NOTIFICATION_MASTER where ID =?1", nativeQuery = true)
	 Notify_Entity getbyid(String id);
	
		@Query(value = "select * from NOTIFICATION_MASTER where ID =?1", nativeQuery = true)
		 Notify_Entity getalls(String id);
		 

		@Query(value = "select * from NOTIFICATION_MASTER where BRANCH_ID =?1 AND DEL_FLG='N'", nativeQuery = true)
		List<Notify_Entity> getuserid(String BRANCH_ID);
}
