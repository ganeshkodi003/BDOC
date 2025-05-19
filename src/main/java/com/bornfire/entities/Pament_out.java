
package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "paymentOut")
public class Pament_out {

    @Id
    @Column(name = "receiptno", length = 100)
    private String receiptNo;

    @Column(name = "vendorid", length = 100)
    private String vendorId;

    @Column(name = "vendorname", length = 100)
    private String vendorName;

    @Column(name = "paymenttype", length = 100)
    private String paymentType;

    @Column(name = "reference", length = 100)
    private String reference;

    @Column(name = "advance", length = 100)
    private String advance;
    
    @Column(name = "receivedamount", length = 100)
    private BigDecimal receivedamount;
    
    @Column(name = "balance", length = 100)
    private BigDecimal balance;
    
    @Column(name = "description", length = 100)
    private String  description;
        
    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
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

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getAdvance() {
		return advance;
	}

	public void setAdvance(String advance) {
		this.advance = advance;
	}

	public BigDecimal getReceivedamount() {
		return receivedamount;
	}

	public void setReceivedamount(BigDecimal receivedamount) {
		this.receivedamount = receivedamount;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Pament_out(String receiptNo, String vendorId, String vendorName, String paymentType, String reference,
			String advance, BigDecimal receivedamount, BigDecimal balance, String description, Date date) {
		super();
		this.receiptNo = receiptNo;
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.paymentType = paymentType;
		this.reference = reference;
		this.advance = advance;
		this.receivedamount = receivedamount;
		this.balance = balance;
		this.description = description;
		this.date = date;
	}

	public Pament_out() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	
	
}