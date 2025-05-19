
package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ACCOUNT_LEDGER_SALE_Rep extends JpaRepository<ACCOUNT_LEDGER_SALE_Entity, String> {

	@Query(value = "SELECT * FROM ACCOUNT_LEDGER_SALE ORDER BY ID ASC ", nativeQuery = true)
	List<ACCOUNT_LEDGER_SALE_Entity> findsall();
	
	@Query(value = "SELECT * FROM ACCOUNT_LEDGER_SALE WHERE WO_ID = ?1 ORDER BY ID ASC", nativeQuery = true)
    List<ACCOUNT_LEDGER_SALE_Entity> findByPoId(String poId);

	@Query(value = "SELECT * FROM ACCOUNT_LEDGER_SALE  ORDER BY ID ASC", nativeQuery = true)
    List<ACCOUNT_LEDGER_SALE_Entity> findByAll();
    
    @Query(value = "SELECT * FROM ACCOUNT_LEDGER_SALE WHERE HEAD_DESCRIPTION='CURRENT ASSETS' ", nativeQuery = true)
    List<ACCOUNT_LEDGER_SALE_Entity> getUnique();

    @Query(value = "SELECT * FROM ACCOUNT_LEDGER_SALE WHERE HEAD_DESCRIPTION='NON-CURRENT-ASSETS' ", nativeQuery = true)
    List<ACCOUNT_LEDGER_SALE_Entity> getUnique_non();
    
    
	}
