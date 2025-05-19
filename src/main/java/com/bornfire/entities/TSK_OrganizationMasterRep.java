
package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TSK_OrganizationMasterRep extends CrudRepository<TSK_OrganizationMasterEntity, String> {
	  public static final EntityManager entityManager = null;

	public Optional<TSK_OrganizationMasterEntity> findById(String empId);

	@Query(value = "select * from TSK_ORGANIZATION_DETAILS where del_flg='N' ", nativeQuery = true)
	List<TSK_OrganizationMasterEntity> getall();
	@Query(value = "select ORG_ID, ORG_NAME from TSK_ORGANIZATION_DETAILS where del_flg='N'", nativeQuery = true)
	List<Object[]> getnames();

	@Query(value = "select ORG_ID from TSK_ORGANIZATION_DETAILS where del_flg='N' ", nativeQuery = true)
	List<String> get_org();

	@Query(value = "select * from TSK_ORGANIZATION_DETAILS where ID=?1 AND del_flg='N' ", nativeQuery = true)
	TSK_OrganizationMasterEntity getallid(String id);
	
	@Query(value = "select * from TSK_ORGANIZATION_DETAILS where del_flg='N' ", nativeQuery = true)
	TSK_OrganizationMasterEntity getOrganizationdetail(String userid);
	
	@Query(value = "select * from TSK_ORGANIZATION_DETAILS ", nativeQuery = true)
	TSK_OrganizationMasterEntity getOrganization();
	
	@Query(value = "select * from TSK_ORGANIZATION_DETAILS where regn_no='U72900TN2017PTC115892' ", nativeQuery = true)
	TSK_OrganizationMasterEntity getOrganizationbyid(String regn_no);
	
	@Query(value = "select * from TSK_ORGANIZATION_DETAILS where casual_leave=?1 ", nativeQuery = true)
	TSK_OrganizationMasterEntity getCL(BigDecimal casual_leave);
	
  


	
	


}
