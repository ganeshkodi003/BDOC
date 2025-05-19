package com.bornfire.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Table(name = "FOLLOW_UP")
public class Follow_Up_Entity {
    @Id
    private String id; // Corresponds to the [ID] column

    @Column(name = "WO_ID")
    private String woId;

    @Column(name = "VENDOR_ID")
    private String vendorId; // Corresponds to the [VENDOR_ID] column
    @Column(name = "INOICE_DATE")
    private Date invoiceDate; // Corresponds to the [INOICE_DATE] column
    @Column(name = "BALANCE_AMOUNT")
    private BigDecimal balanceAmount; // Corresponds to the [BALANCE_AMOUNT] column
    @Column(name = "DUE_DATE")
    private Date dueDate; // Corresponds to the [DUE_DATE] column
    @Column(name = "REMARKS")
    private String remarks; // Corresponds to the [REMARKS] column
    @Column(name = "DEL_FLG")
    private String delFlg; // Corresponds to the [DEL_FLG] column

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWoId() {
        return woId;
    }

    public void setWoId(String woId) {
        this.woId = woId;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg;
    }

	public Follow_Up_Entity(String id, String woId, String vendorId, Date invoiceDate, BigDecimal balanceAmount,
			Date dueDate, String remarks, String delFlg) {
		super();
		this.id = id;
		this.woId = woId;
		this.vendorId = vendorId;
		this.invoiceDate = invoiceDate;
		this.balanceAmount = balanceAmount;
		this.dueDate = dueDate;
		this.remarks = remarks;
		this.delFlg = delFlg;
	}

	public Follow_Up_Entity() {
		super();
		// TODO Auto-generated constructor stub
	}
}

