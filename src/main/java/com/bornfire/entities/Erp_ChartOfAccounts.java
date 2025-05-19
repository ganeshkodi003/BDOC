package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "erp_chartOfAccounts")
public class Erp_ChartOfAccounts {
	 @Id
	    
	    @Column(name = "account_number", length = 100, nullable = false, unique = true)
	    private String accountNumber;
	    
	    @Column(name = "account_type", length = 100, nullable = false)
	    private String accountType;
	    
	    @Column(name = "account_name", length = 100, nullable = false)
	    private String accountName;
	    
	    @Column(name = "entry_date")
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    private Date entryDate;
	    
	    @Column(name = "remarks", length = 100)
	    private String remarks;
	    
	    @Column(name = "total_credit_balance")
	    private BigDecimal totalCreditBalance;
	    
	    @Column(name = "total_debit_balance")
	    private BigDecimal totalDebitBalance;
	    
	    @Column(name = "account_balance")
	    private BigDecimal accountBalance;
	    
	    @Column(name = "parentaccount")
	    private String parentaccount;
	    
	    @Column(name = "childaccountcode")
	    private String childaccountcode;
	    
	    @Column(name = "ownership")
	    private String ownership;
	    
	    @Column(name = "ownershipid")
	    private String ownershipid;
	    
	    @Column(name = "OpeningBalance")
	    private BigDecimal openingbalance;

		public String getAccountNumber() {
			return accountNumber;
		}

		public void setAccountNumber(String accountNumber) {
			this.accountNumber = accountNumber;
		}

		public String getAccountType() {
			return accountType;
		}

		public void setAccountType(String accountType) {
			this.accountType = accountType;
		}

		public String getAccountName() {
			return accountName;
		}

		public void setAccountName(String accountName) {
			this.accountName = accountName;
		}

		public Date getEntryDate() {
			return entryDate;
		}

		public void setEntryDate(Date entryDate) {
			this.entryDate = entryDate;
		}

		public String getRemarks() {
			return remarks;
		}

		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}

		public BigDecimal getTotalCreditBalance() {
			return totalCreditBalance;
		}

		public void setTotalCreditBalance(BigDecimal totalCreditBalance) {
			this.totalCreditBalance = totalCreditBalance;
		}

		public BigDecimal getTotalDebitBalance() {
			return totalDebitBalance;
		}

		public void setTotalDebitBalance(BigDecimal totalDebitBalance) {
			this.totalDebitBalance = totalDebitBalance;
		}

		public BigDecimal getAccountBalance() {
			return accountBalance;
		}

		public void setAccountBalance(BigDecimal accountBalance) {
			this.accountBalance = accountBalance;
		}

		public String getParentaccount() {
			return parentaccount;
		}

		public void setParentaccount(String parentaccount) {
			this.parentaccount = parentaccount;
		}

		public String getChildaccountcode() {
			return childaccountcode;
		}

		public void setChildaccountcode(String childaccountcode) {
			this.childaccountcode = childaccountcode;
		}

		public String getOwnership() {
			return ownership;
		}

		public void setOwnership(String ownership) {
			this.ownership = ownership;
		}

		public String getOwnershipid() {
			return ownershipid;
		}

		public void setOwnershipid(String ownershipid) {
			this.ownershipid = ownershipid;
		}

		public BigDecimal getOpeningbalance() {
			return openingbalance;
		}

		public void setOpeningbalance(BigDecimal openingbalance) {
			this.openingbalance = openingbalance;
		}

		public Erp_ChartOfAccounts(String accountNumber, String accountType, String accountName, Date entryDate,
				String remarks, BigDecimal totalCreditBalance, BigDecimal totalDebitBalance, BigDecimal accountBalance,
				String parentaccount, String childaccountcode, String ownership, String ownershipid,
				BigDecimal openingbalance) {
			super();
			this.accountNumber = accountNumber;
			this.accountType = accountType;
			this.accountName = accountName;
			this.entryDate = entryDate;
			this.remarks = remarks;
			this.totalCreditBalance = totalCreditBalance;
			this.totalDebitBalance = totalDebitBalance;
			this.accountBalance = accountBalance;
			this.parentaccount = parentaccount;
			this.childaccountcode = childaccountcode;
			this.ownership = ownership;
			this.ownershipid = ownershipid;
			this.openingbalance = openingbalance;
		}

		public Erp_ChartOfAccounts() {
			super();
			// TODO Auto-generated constructor stub
		}

		
		
}
