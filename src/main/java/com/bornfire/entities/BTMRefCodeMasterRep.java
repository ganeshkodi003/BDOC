package com.bornfire.entities;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BTMRefCodeMasterRep extends CrudRepository<BTMRefCodeMaster,String> {
	
	public Optional<BTMRefCodeMaster> findById(String refId);

	@Query(value = "SELECT * from REF_MASTER WHERE del_flg='N' ", nativeQuery = true)
	List<BTMRefCodeMaster> getRefCodelist();
	
	@Query(value = "SELECT * from REF_MASTER WHERE ref_id=?1 ", nativeQuery = true)
	BTMRefCodeMaster getRefMaster(String ref_id);
	
	@Query(value = "SELECT * from REF_MASTER WHERE ref_type='A25' and del_flg='N' ", nativeQuery = true)
	List<BTMRefCodeMaster> getBankList();
}
