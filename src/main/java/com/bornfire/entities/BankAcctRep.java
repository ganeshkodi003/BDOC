package com.bornfire.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAcctRep extends JpaRepository<BankAcctEntity , String>{

	
}
