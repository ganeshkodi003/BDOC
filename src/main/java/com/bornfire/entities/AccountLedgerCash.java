package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "ACCOUNT_LEDGER_CASH")
public class AccountLedgerCash {
	@Id
    @Column(name = "tran_ID", length = 100, nullable = false)
    private String tranId;
    
    @Column(name = "tran_type", length = 100)
    private String tranType;
    
    @Column(name = "tran_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tranDate;
    
    @Column(name = "vendor_id", length = 100)
    private String vendorId;
    
    @Column(name = "vendor_name", length = 100)
    private String vendorName;
    
    @Column(name = "account_type", length = 100)
    private String accountType;
    
    @Column(name = "po_id", length = 100)
    private String poId;
    
    @Column(name = "wo_id", length = 100)
    private String woId;
    
    @Column(name = "account_number", length = 100)
    private String accountNumber;
    
    @Column(name = "debit_amount", length = 100)
    private BigDecimal debitAmount;
    
    @Column(name = "credit_amount", precision = 18, scale = 2)
    private BigDecimal creditAmount;
    
    @Column(name = "current_balance", precision = 18, scale = 2)
    private BigDecimal currentBalance;

	public String getTranId() {
		return tranId;
	}

	public void setTranId(String tranId) {
		this.tranId = tranId;
	}

	public String getTranType() {
		return tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	public Date getTranDate() {
		return tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getPoId() {
		return poId;
	}

	public void setPoId(String poId) {
		this.poId = poId;
	}

	public String getWoId() {
		return woId;
	}

	public void setWoId(String woId) {
		this.woId = woId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getDebitAmount() {
		return debitAmount;
	}

	public void setDebitAmount(BigDecimal debitAmount) {
		this.debitAmount = debitAmount;
	}

	public BigDecimal getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(BigDecimal creditAmount) {
		this.creditAmount = creditAmount;
	}

	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}

	public AccountLedgerCash(String tranId, String tranType, Date tranDate, String vendorId, String vendorName,
			String accountType, String poId, String woId, String accountNumber, BigDecimal debitAmount,
			BigDecimal creditAmount, BigDecimal currentBalance) {
		super();
		this.tranId = tranId;
		this.tranType = tranType;
		this.tranDate = tranDate;
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.accountType = accountType;
		this.poId = poId;
		this.woId = woId;
		this.accountNumber = accountNumber;
		this.debitAmount = debitAmount;
		this.creditAmount = creditAmount;
		this.currentBalance = currentBalance;
	}

	public AccountLedgerCash() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
