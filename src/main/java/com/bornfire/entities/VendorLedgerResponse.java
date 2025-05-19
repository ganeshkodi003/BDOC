package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.List;

public class VendorLedgerResponse {
    private List<Transaction_table_demo> transactions;
    private BigDecimal openingbalance;
    private String type;
	public List<Transaction_table_demo> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction_table_demo> transactions) {
		this.transactions = transactions;
	}
	public BigDecimal getOpeningbalance() {
		return openingbalance;
	}
	public void setOpeningbalance(BigDecimal openingbalance) {
		this.openingbalance = openingbalance;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public VendorLedgerResponse(List<Transaction_table_demo> transactions, BigDecimal openingbalance, String type) {
		super();
		this.transactions = transactions;
		this.openingbalance = openingbalance;
		this.type = type;
	}
	public VendorLedgerResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
