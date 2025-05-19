package com.bornfire.entities;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BTMTravelMasterRep extends CrudRepository<BTMTravelMaster, String> {

	public Optional<BTMTravelMaster> findById(String empId);

	@Query(value = "SELECT * FROM TRAVEL_MASTER", nativeQuery = true)
	List<BTMTravelMaster> getTravellist();
	
	@Query(value = "SELECT * FROM TRAVEL_MASTER where BRANCH_ID=?1 AND ORG_ID=?2 ", nativeQuery = true)
	List<BTMTravelMaster> getbranchtravel(String branch,String org_id );
	
	@Query(value = "SELECT * FROM TRAVEL_MASTER where Tra_status!='Completed' and Tra_status!='Rejected' and del_flg='N'", nativeQuery = true)
	List<BTMTravelMaster> getTravellist1();
	
	@Query(value = "SELECT * FROM TRAVEL_MASTER where BRANCH_ID=?1 AND ORG_ID=?2 AND  Tra_status!='Completed' and Tra_status!='Rejected' and del_flg='N'", nativeQuery = true)
	List<BTMTravelMaster> getTravellist1new(String branch,String org_id);
	
	@Query(value = "select * from TRAVEL_MASTER  where tra_ref=?1", nativeQuery = true)
	BTMTravelMaster getTravelMaster(String resId);
	
	@Query(value = "select * from TRAVEL_MASTER  where ass_id=?1", nativeQuery = true)
	List<BTMTravelMaster> getTravelListbyid(String Id);
	
}

