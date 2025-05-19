package com.bornfire.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "BHMS_INV_PRODUCT_TABLE")
public class BHMSInventoryProductStock {
	@Id
	private String ID;
	private String MFR;
	private String BATCH;
	private Date EXPIRY_DATE;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String HSN_CODE; 
	private String PRODUCT_NAME;
	private String CATEGORY_NAME;
	private String PKG;
	private Double UNITS; 
	private Double SUB_UNITS; 
	private Double NO_OF_UNITS; 
	private Double SUB_UNITS_COST; 
	private Double MRP;
	private Double RATE;
	private Double DISCOUNT_PERCENT;
	private Double DISCOUNT_AMOUNT;
	private Double GST_PERCENT;
	private Double AMOUNT;
	private String GSTIN;
	private String PURCHASED_FROM_NAME;
	private String Status;
	public byte[] getFiledata() {
		return filedata;
	}
	public void setFiledata(byte[] filedata) {
		this.filedata = filedata;
	}
	  @Lob
	private byte[] filedata;
	
	
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	private Date PURCHASE_DATE;
	@JsonFormat(pattern = "yyyy-MM-dd")
	
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getMFR() {
		return MFR;
	}
	public void setMFR(String mFR) {
		MFR = mFR;
	}
	public String getBATCH() {
		return BATCH;
	}
	public void setBATCH(String bATCH) {
		BATCH = bATCH;
	}
	public Date getEXPIRY_DATE() {
		return EXPIRY_DATE;
	}
	public void setEXPIRY_DATE(Date eXPIRY_DATE) {
		EXPIRY_DATE = eXPIRY_DATE;
	}
	public String getHSN_CODE() {
		return HSN_CODE;
	}
	public void setHSN_CODE(String hSN_CODE) {
		HSN_CODE = hSN_CODE;
	}
	public String getPRODUCT_NAME() {
		return PRODUCT_NAME;
	}
	public void setPRODUCT_NAME(String pRODUCT_NAME) {
		PRODUCT_NAME = pRODUCT_NAME;
	}
	public String getCATEGORY_NAME() {
		return CATEGORY_NAME;
	}
	public void setCATEGORY_NAME(String cATEGORY_NAME) {
		CATEGORY_NAME = cATEGORY_NAME;
	}
	public String getPKG() {
		return PKG;
	}
	public void setPKG(String pKG) {
		PKG = pKG;
	}
	public Double getUNITS() {
		return UNITS;
	}
	public void setUNITS(Double uNITS) {
		UNITS = uNITS;
	}
	public Double getSUB_UNITS() {
		return SUB_UNITS;
	}
	public void setSUB_UNITS(Double sUB_UNITS) {
		SUB_UNITS = sUB_UNITS;
	}
	public Double getNO_OF_UNITS() {
		return NO_OF_UNITS;
	}
	public void setNO_OF_UNITS(Double nO_OF_UNITS) {
		NO_OF_UNITS = nO_OF_UNITS;
	}
	public Double getSUB_UNITS_COST() {
		return SUB_UNITS_COST;
	}
	public void setSUB_UNITS_COST(Double sUB_UNITS_COST) {
		SUB_UNITS_COST = sUB_UNITS_COST;
	}
	public Double getMRP() {
		return MRP;
	}
	public void setMRP(Double mRP) {
		MRP = mRP;
	}
	public Double getRATE() {
		return RATE;
	}
	public void setRATE(Double rATE) {
		RATE = rATE;
	}
	public Double getDISCOUNT_PERCENT() {
		return DISCOUNT_PERCENT;
	}
	public void setDISCOUNT_PERCENT(Double dISCOUNT_PERCENT) {
		DISCOUNT_PERCENT = dISCOUNT_PERCENT;
	}
	public Double getDISCOUNT_AMOUNT() {
		return DISCOUNT_AMOUNT;
	}
	public void setDISCOUNT_AMOUNT(Double dISCOUNT_AMOUNT) {
		DISCOUNT_AMOUNT = dISCOUNT_AMOUNT;
	}
	public Double getGST_PERCENT() {
		return GST_PERCENT;
	}
	public void setGST_PERCENT(Double gST_PERCENT) {
		GST_PERCENT = gST_PERCENT;
	}
	public Double getAMOUNT() {
		return AMOUNT;
	}
	public void setAMOUNT(Double aMOUNT) {
		AMOUNT = aMOUNT;
	}
	public String getGSTIN() {
		return GSTIN;
	}
	public void setGSTIN(String gSTIN) {
		GSTIN = gSTIN;
	}
	public String getPURCHASED_FROM_NAME() {
		return PURCHASED_FROM_NAME;
	}
	public void setPURCHASED_FROM_NAME(String pURCHASED_FROM_NAME) {
		PURCHASED_FROM_NAME = pURCHASED_FROM_NAME;
	}
	public Date getPURCHASE_DATE() {
		return PURCHASE_DATE;
	}
	public void setPURCHASE_DATE(Date pURCHASE_DATE) {
		PURCHASE_DATE = pURCHASE_DATE;
	}
	public BHMSInventoryProductStock(String iD, String mFR, String bATCH, Date eXPIRY_DATE, String hSN_CODE,
			String pRODUCT_NAME, String cATEGORY_NAME, String pKG, Double uNITS, Double sUB_UNITS, Double nO_OF_UNITS,
			Double sUB_UNITS_COST, Double mRP, Double rATE, Double dISCOUNT_PERCENT, Double dISCOUNT_AMOUNT,
			Double gST_PERCENT, Double aMOUNT, String gSTIN, String pURCHASED_FROM_NAME, Date pURCHASE_DATE) {
		super();
		ID = iD;
		MFR = mFR;
		BATCH = bATCH;
		EXPIRY_DATE = eXPIRY_DATE;
		HSN_CODE = hSN_CODE;
		PRODUCT_NAME = pRODUCT_NAME;
		CATEGORY_NAME = cATEGORY_NAME;
		PKG = pKG;
		UNITS = uNITS;
		SUB_UNITS = sUB_UNITS;
		NO_OF_UNITS = nO_OF_UNITS;
		SUB_UNITS_COST = sUB_UNITS_COST;
		MRP = mRP;
		RATE = rATE;
		DISCOUNT_PERCENT = dISCOUNT_PERCENT;
		DISCOUNT_AMOUNT = dISCOUNT_AMOUNT;
		GST_PERCENT = gST_PERCENT;
		AMOUNT = aMOUNT;
		GSTIN = gSTIN;
		PURCHASED_FROM_NAME = pURCHASED_FROM_NAME;
		PURCHASE_DATE = pURCHASE_DATE;
	}
	public BHMSInventoryProductStock() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "BHMSInventoryProductStock [ID=" + ID + ", MFR=" + MFR + ", BATCH=" + BATCH + ", EXPIRY_DATE="
				+ EXPIRY_DATE + ", HSN_CODE=" + HSN_CODE + ", PRODUCT_NAME=" + PRODUCT_NAME + ", CATEGORY_NAME="
				+ CATEGORY_NAME + ", PKG=" + PKG + ", UNITS=" + UNITS + ", SUB_UNITS=" + SUB_UNITS + ", NO_OF_UNITS="
				+ NO_OF_UNITS + ", SUB_UNITS_COST=" + SUB_UNITS_COST + ", MRP=" + MRP + ", RATE=" + RATE
				+ ", DISCOUNT_PERCENT=" + DISCOUNT_PERCENT + ", DISCOUNT_AMOUNT=" + DISCOUNT_AMOUNT + ", GST_PERCENT="
				+ GST_PERCENT + ", AMOUNT=" + AMOUNT + ", GSTIN=" + GSTIN + ", PURCHASED_FROM_NAME="
				+ PURCHASED_FROM_NAME + ", PURCHASE_DATE=" + PURCHASE_DATE + "]";
	}
	
	
	
	
}
