package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ACCOUNT_LEDGER_PO_Rep extends JpaRepository<ACCOUNT_LEDGER_PO_Entity, String> {

	@Query(value = "SELECT * FROM ACCOUNT_LEDGER_PO ORDER BY ID ASC ", nativeQuery = true)
	List<ACCOUNT_LEDGER_PO_Entity> findsall();
	
	@Query(value = "SELECT * FROM ACCOUNT_LEDGER_PO WHERE PO_ID = ?1 ORDER BY ID ASC", nativeQuery = true)
    List<ACCOUNT_LEDGER_PO_Entity> findByPoId(String poId);

	@Query(value = "SELECT * FROM ACCOUNT_LEDGER_PO  ORDER BY ID ASC", nativeQuery = true)
    List<ACCOUNT_LEDGER_PO_Entity> findByAll();
    
    @Query(value = "SELECT * FROM ACCOUNT_LEDGER_PO WHERE HEAD_DESCRIPTION='CURRENT ASSETS' ", nativeQuery = true)
    List<ACCOUNT_LEDGER_PO_Entity> getUnique();

    @Query(value = "SELECT * FROM ACCOUNT_LEDGER_PO WHERE HEAD_DESCRIPTION='NON-CURRENT-ASSETS' ", nativeQuery = true)
    List<ACCOUNT_LEDGER_PO_Entity> getUnique_non();
    
    @Query(value = "SELECT * FROM ACCOUNT_LEDGER_PO WHERE DESCRIPTION = ?1 ORDER BY ID ASC", nativeQuery = true)
    List<ACCOUNT_LEDGER_PO_Entity> findByDesc(String Desc);

	}
