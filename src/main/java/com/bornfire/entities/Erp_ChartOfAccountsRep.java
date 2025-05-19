package com.bornfire.entities;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Erp_ChartOfAccountsRep extends JpaRepository<Erp_ChartOfAccounts, String> {

	@Query(value = "SELECT * FROM erp_chartOfAccounts ", nativeQuery = true)
	List<Erp_ChartOfAccounts> findsall();
	
	@Query(value = "SELECT CAST(count(*) AS BIGINT)  from erp_chartOfAccounts where parentaccount=?1", nativeQuery = true)
	BigInteger parentcount(String parentaccount);
	
	@Query(value = "SELECT FORMAT(CAST(COUNT(*) AS INT) + 1, '00') FROM erp_chartOfAccounts WHERE parentaccount=?1", nativeQuery = true)
	String parentcountnumber(String parentaccount);

	
	
	@Query(value = "SELECT *FROM erp_chartOfAccounts A WHERE NOT EXISTS ( SELECT 1 FROM erp_chartOfAccounts B WHERE B.parentaccount = A.account_number)", nativeQuery = true)
	List<Erp_ChartOfAccounts> findschild();
	
	@Query(value = "select * from erp_chartOfAccounts where account_name=?1 ", nativeQuery = true)
	Erp_ChartOfAccounts findsbyaccountname(String account_name);
	
	@Query(value = "select * from erp_chartOfAccounts where ownership=?1 ", nativeQuery = true)
	List<Erp_ChartOfAccounts> findsbyaccountgroup(String ownership);
	
	@Query(value = "SELECT * FROM erp_chartOfAccounts where ownershipid =?1 ", nativeQuery = true)
	Erp_ChartOfAccounts findsbyowner(String ownershipid);
	
	@Query(value = "SELECT * FROM erp_chartOfAccounts where parentaccount is null or parentaccount='' ", nativeQuery = true)
	List<Erp_ChartOfAccounts> findsallparent();
	
	@Query(value = "SELECT * FROM erp_chartOfAccounts where  parentaccount=?1" , nativeQuery = true)
	List<Erp_ChartOfAccounts> findschildaccount(String parentaccount );
	
	@Query(value = "SELECT * FROM erp_chartOfAccounts where  account_number=?1" , nativeQuery = true)
	Erp_ChartOfAccounts findsccount(String account_number );
	
	@Query(value = "SELECT CAST(current_value AS BIGINT) FROM sys.sequences WHERE name ='chart_of_accounts_seq'", nativeQuery = true)
	BigInteger getaccountnumber();
}
