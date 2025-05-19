package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface perfomance_evaluation_REP extends JpaRepository<perfomance_evaluation, String>  {

	

	@Query(value = "select * from PERFORMANCE_EVALUATION ORDER BY srl_no", nativeQuery = true)
	 List<perfomance_evaluation> getCVFList();
	 

		@Query(value = "select * from PERFORMANCE_EVALUATION where emb_id= ?1", nativeQuery = true)
		 List<perfomance_evaluation> getCVFList1(String emb_id);
	
	@Query(value = "select  * from PERFORMANCE_EVALUATION where srl_no= ?1",nativeQuery = true)
	perfomance_evaluation getCVFform(String srl_no);


}
