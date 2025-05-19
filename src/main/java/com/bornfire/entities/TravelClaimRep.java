package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TravelClaimRep extends JpaRepository<TravelClaim, String> {
	
	@Query(value="select * from TravelClaim where del_flg='N'", nativeQuery = true)
	List<TravelClaim> climelist();
	
	@Query(value="select * from TravelClaim where del_flg='N' and id=?1", nativeQuery = true)
	TravelClaim climelistbyid(String id);

}
