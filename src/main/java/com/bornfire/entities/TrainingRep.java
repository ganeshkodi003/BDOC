package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TrainingRep extends CrudRepository<BtmTraining, String> {
	@Query(value = "select * from BTM_TRAINING  where SRL_NO =?1", nativeQuery = true) 
	 BtmTraining getBanklist(String SRL_NO);
	 
	 @Query(value="select * from BTM_TRAINING ", nativeQuery = true)
	 List<BtmTraining> gettraininglist();

}
