package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Training_Rep extends CrudRepository<Training,String> {

	@Query(value = "SELECT * FROM TRAINING ", nativeQuery = true)

	List<Training> gelist();
}
