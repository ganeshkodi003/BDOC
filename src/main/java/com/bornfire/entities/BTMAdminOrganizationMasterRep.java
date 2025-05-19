package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BTMAdminOrganizationMasterRep extends CrudRepository<BTMAdminOrganizationMaster, String> {
	  public static final EntityManager entityManager = null;

	public Optional<BTMAdminOrganizationMaster> findById(String empId);
	
	@Query(value = "select * from ORGANIZATION_DETAILS where del_flg='N' ", nativeQuery = true)
	BTMAdminOrganizationMaster getOrganizationdetail(String userid);
	
	@Query(value = "select * from ORGANIZATION_DETAILS ", nativeQuery = true)
	BTMAdminOrganizationMaster getOrganization();
	
	@Query(value = "select * from ORGANIZATION_DETAILS where regn_no='U72900TN2017PTC115892' ", nativeQuery = true)
	BTMAdminOrganizationMaster getOrganizationbyid(String regn_no);
	
	@Query(value = "select * from ORGANIZATION_DETAILS where casual_leave=?1 ", nativeQuery = true)
	BTMAdminOrganizationMaster getCL(BigDecimal casual_leave);
	
  


	
	


}
