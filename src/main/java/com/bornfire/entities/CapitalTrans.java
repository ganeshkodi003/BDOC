package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "capital_trans")
public class CapitalTrans {
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "Journal_Date")
    private Date journalDate;
	
	 @Id
	@Column(name = "id")
    private String id ;

   
    @Column(name = "Journal_Tran_Id")
    private String journalTranId;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "Part_Tran_Id")
    private int partTranId;

    @Column(name = "fullaccount")
    private String fullAccount;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "account_balance")
    private BigDecimal accountBalance;

    @Column(name = "description")
    private String description;

    @Column(name = "Debits")
    private BigDecimal debits;

    @Column(name = "Credits")
    private BigDecimal credits;

    @Column(name = "total_Debits")
    private BigDecimal totalDebits;

    @Column(name = "total_Credits")
    private BigDecimal totalCredits;
    
    @Column(name = "tran_particulars")
    private String tran_particulars;
    
    @Column(name = "vendor_id")
    private String vendor_id;
    
    @Column(name = "vendor_name")
    private String vendor_name;
    
    @Column(name = "type")
    private String type;

	public Date getJournalDate() {
		return journalDate;
	}

	public void setJournalDate(Date journalDate) {
		this.journalDate = journalDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJournalTranId() {
		return journalTranId;
	}

	public void setJournalTranId(String journalTranId) {
		this.journalTranId = journalTranId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getPartTranId() {
		return partTranId;
	}

	public void setPartTranId(int partTranId) {
		this.partTranId = partTranId;
	}

	public String getFullAccount() {
		return fullAccount;
	}

	public void setFullAccount(String fullAccount) {
		this.fullAccount = fullAccount;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getDebits() {
		return debits;
	}

	public void setDebits(BigDecimal debits) {
		this.debits = debits;
	}

	public BigDecimal getCredits() {
		return credits;
	}

	public void setCredits(BigDecimal credits) {
		this.credits = credits;
	}

	public BigDecimal getTotalDebits() {
		return totalDebits;
	}

	public void setTotalDebits(BigDecimal totalDebits) {
		this.totalDebits = totalDebits;
	}

	public BigDecimal getTotalCredits() {
		return totalCredits;
	}

	public void setTotalCredits(BigDecimal totalCredits) {
		this.totalCredits = totalCredits;
	}

	public String getTran_particulars() {
		return tran_particulars;
	}

	public void setTran_particulars(String tran_particulars) {
		this.tran_particulars = tran_particulars;
	}

	public String getVendor_id() {
		return vendor_id;
	}

	public void setVendor_id(String vendor_id) {
		this.vendor_id = vendor_id;
	}

	public String getVendor_name() {
		return vendor_name;
	}

	public void setVendor_name(String vendor_name) {
		this.vendor_name = vendor_name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public CapitalTrans(Date journalDate, String id, String journalTranId, String remarks, int partTranId,
			String fullAccount, String accountNumber, String accountName, BigDecimal accountBalance, String description,
			BigDecimal debits, BigDecimal credits, BigDecimal totalDebits, BigDecimal totalCredits,
			String tran_particulars, String vendor_id, String vendor_name, String type) {
		super();
		this.journalDate = journalDate;
		this.id = id;
		this.journalTranId = journalTranId;
		this.remarks = remarks;
		this.partTranId = partTranId;
		this.fullAccount = fullAccount;
		this.accountNumber = accountNumber;
		this.accountName = accountName;
		this.accountBalance = accountBalance;
		this.description = description;
		this.debits = debits;
		this.credits = credits;
		this.totalDebits = totalDebits;
		this.totalCredits = totalCredits;
		this.tran_particulars = tran_particulars;
		this.vendor_id = vendor_id;
		this.vendor_name = vendor_name;
		this.type = type;
	}

	public CapitalTrans() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    

    
    

}
