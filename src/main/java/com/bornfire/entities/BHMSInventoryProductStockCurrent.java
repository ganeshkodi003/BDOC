package com.bornfire.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "BHMS_INV_PRODUCT_STOCK_CURRENT")
public class BHMSInventoryProductStockCurrent {
	@Id
	private String ID;
	private String MFR;
	private String BATCH;
	private String PRODUCT_NAME;
	private String CATEGORY_NAME;
	private String PKG;
	private Double UNITS; 
	private Double SUB_UNITS; 
	private Double NO_OF_UNITS; 
	private Double SUB_UNITS_COST; 
	private Double MRP;
	private Date EXPIRY_DATE;
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Double GST_PERCENT;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getMFR() {
		return MFR;
	}
	@JsonProperty(value="MFR")
	public void setMFR(String mFR) {
		this.MFR = mFR;
	}
	public String getBATCH() {
		return BATCH;
	}
	@JsonProperty(value="BATCH")
	public void setBATCH(String bATCH) {
		this.BATCH = bATCH;
	}
	public String getPRODUCT_NAME() {
		return PRODUCT_NAME;
	}
	@JsonProperty(value="PRODUCT_NAME")
	public void setPRODUCT_NAME(String pRODUCT_NAME) {
		this.PRODUCT_NAME = pRODUCT_NAME;
	}
	public String getCATEGORY_NAME() {
		return CATEGORY_NAME;
	}
	@JsonProperty(value="CATEGORY_NAME")
	public void setCATEGORY_NAME(String cATEGORY_NAME) {
		this.CATEGORY_NAME = cATEGORY_NAME;
	}
	public String getPKG() {
		return PKG;
	}
	@JsonProperty(value="PKG")
	public void setPKG(String pKG) {
		this.PKG = pKG;
	}
	public Double getUNITS() {
		return UNITS;
	}
	@JsonProperty(value="UNITS")
	public void setUNITS(Double uNITS) {
		this.UNITS = uNITS;
	}
	public Double getSUB_UNITS() {
		return SUB_UNITS;
	}
	@JsonProperty(value="SUB_UNITS")
	public void setSUB_UNITS(Double sUB_UNITS) {
		this.SUB_UNITS = sUB_UNITS;
	}
	public Double getNO_OF_UNITS() {
		return NO_OF_UNITS;
	}
	@JsonProperty(value="NO_OF_UNITS")
	public void setNO_OF_UNITS(Double nO_OF_UNITS) {
		this.NO_OF_UNITS = nO_OF_UNITS;
	}
	public Double getSUB_UNITS_COST() {
		return SUB_UNITS_COST;
	}
	@JsonProperty(value="SUB_UNITS_COST")
	public void setSUB_UNITS_COST(Double sUB_UNITS_COST) {
		this.SUB_UNITS_COST = sUB_UNITS_COST;
	}
	public Double getMRP() {
		return MRP;
	}
	@JsonProperty(value="MRP")
	public void setMRP(Double mRP) {
		this.MRP = mRP;
	}
	public Date getEXPIRY_DATE() {
		return EXPIRY_DATE;
	}
	@JsonProperty(value="EXPIRY_DATE")
	public void setEXPIRY_DATE(Date eXPIRY_DATE) {
		this.EXPIRY_DATE = eXPIRY_DATE;
	}
	public Double getGST_PERCENT() {
		return GST_PERCENT;
	}
	@JsonProperty(value="GST_PERCENT")
	public void setGST_PERCENT(Double gST_PERCENT) {
		this.GST_PERCENT = gST_PERCENT;
	}
	public BHMSInventoryProductStockCurrent(String iD, String mFR, String bATCH, String pRODUCT_NAME,
			String cATEGORY_NAME, String pKG, Double uNITS, Double sUB_UNITS, Double nO_OF_UNITS, Double sUB_UNITS_COST,
			Double mRP, Date eXPIRY_DATE, Double gST_PERCENT) {
		super();
		ID = iD;
		MFR = mFR;
		BATCH = bATCH;
		PRODUCT_NAME = pRODUCT_NAME;
		CATEGORY_NAME = cATEGORY_NAME;
		PKG = pKG;
		UNITS = uNITS;
		SUB_UNITS = sUB_UNITS;
		NO_OF_UNITS = nO_OF_UNITS;
		SUB_UNITS_COST = sUB_UNITS_COST;
		MRP = mRP;
		EXPIRY_DATE = eXPIRY_DATE;
		GST_PERCENT = gST_PERCENT;
	}
	public BHMSInventoryProductStockCurrent() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
