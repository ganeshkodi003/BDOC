package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Hrms_TrainingRep extends CrudRepository<Hrms_Training,String> {
	
	@Query(value = "SELECT * FROM HRMS_TRAINING ", nativeQuery = true)

	List<Hrms_Training> gelist();

}
