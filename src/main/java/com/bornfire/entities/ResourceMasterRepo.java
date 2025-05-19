package com.bornfire.entities;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface ResourceMasterRepo extends CrudRepository<ResourceMaster, String> {
	
	
	 @Query(value = "select COUNT(RESOURCE_ID) from RESOURCE_MASTER WHERE DEL_FLG = 'N'  ", nativeQuery = true) 
	 int gettotalnum();
	 
	 @Query(value = "select * from RESOURCE_MASTER WHERE DEL_FLG = 'N' AND ENTITY_FLG = 'Y' AND VIRTUAL_FLG  ='N' ORDER BY RESOURCE_ID ASC ", nativeQuery = true) 
	 List<ResourceMaster> gettotaluser();
	 
	 @Query(value = "select * from RESOURCE_MASTER WHERE DEL_FLG = 'N'  ORDER BY RESOURCE_ID ASC ", nativeQuery = true) 
	 List<ResourceMaster> gettotalusernew();

	 @Query(value="SELECT * FROM RESOURCE_MASTER WHERE employee_id=?1 ", nativeQuery = true)
		ResourceMaster getrole(String empid);
	 
	 @Query(value="select * from RESOURCE_MASTER where employee_id=?1", nativeQuery = true)
	 List<ResourceMaster> getuser(String empid);
	 
	 
	 @Query(value="select * from RESOURCE_MASTER where employee_id=?1", nativeQuery = true)
	 ResourceMaster getuserData(String empid);
	 
	 @Query(value = "SELECT resource_id, resource_name FROM resource_master WHERE del_flg != 'Y' MINUS SELECT emp_id, emp_name FROM emp_att_master WHERE cal_year = ?1 AND cal_month = ?2 AND cal_date = ?3", nativeQuery = true)
	 List<Object[]> getAbsentees(String year , String month , String day);
	 
	 
	 @Query(value = "SELECT mobile FROM resource_master WHERE del_flg != 'Y' AND resource_id NOT IN ( SELECT emp_id   FROM emp_att_master  WHERE cal_year = ?1  AND cal_month = ?2  AND cal_date = ?3)", nativeQuery = true)
	 List<String> smssenmding(String year , String month , String day);
	 
	 @Query(value = "SELECT resource_id FROM RESOURCE_MASTER order by resource_id", nativeQuery = true)
	    List<String> getalist();
	 
	 @Query(value="SELECT * FROM RESOURCE_MASTER WHERE resource_id=?1 ", nativeQuery = true)
		ResourceMaster getarole(String empid);

	 @Query(value="SELECT * FROM RESOURCE_MASTER WHERE resource_id=?1 ", nativeQuery = true)
	 ResourceMaster getname(String resource_id);

	 @Query(value="SELECT * FROM RESOURCE_MASTER ", nativeQuery = true)
		List<ResourceMaster> getmail();
	 
	 @Query(value = "SELECT * FROM RESOURCE_MASTER WHERE used_flg='N' OR used_flg IS NULL  order by resource_id", nativeQuery = true)
	    List<ResourceMaster> getalist11();
	 
	 
	 @Query(value="select * from RESOURCE_MASTER where employee_id=?1", nativeQuery = true)
	 Optional<ResourceMaster> getuser1(String empid);
	 
	 @Query(value = "SELECT * FROM RESOURCE_MASTER where resource_id=?1   order by resource_id", nativeQuery = true)
	    List<ResourceMaster> getalist11( String resource_id );
	 
	 
	 @Query(value = "SELECT rm.resource_id, rm.resource_name " +
             "FROM resource_master rm " +
             "WHERE rm.del_flg != 'Y' " +
             "AND NOT EXISTS ( " +
             "  SELECT 1 " +
             "  FROM emp_att_master eam " +
             "  WHERE eam.emp_id = rm.resource_id " +
             "  AND eam.cal_year = ?1 " +
             "  AND eam.cal_month = ?2 " +
             "  AND eam.cal_date = ?3)", 
     nativeQuery = true)
List<Object[]> forabsentees(String year , String month , String day);



@Query(value = "SELECT COUNT(rm.resource_id) " +
        "FROM resource_master rm " +
        "WHERE rm.del_flg != 'Y' " +
        "  AND NOT EXISTS ( " +
        "    SELECT 1 " +
        "    FROM emp_att_master eam " +
        "    WHERE eam.emp_id = rm.resource_id " +
        "      AND eam.cal_year = ?1 " +
        "      AND eam.cal_month = ?2 " +
        "      AND eam.cal_date = ?3" +
        ")", 
nativeQuery = true)
int getnotlogedemployees(String year, String month, String day);



@Query(value="select * from RESOURCE_MASTER where del_flg != 'Y' ", nativeQuery = true)
List<ResourceMaster> getEmployee();


	 

	 
	
}
