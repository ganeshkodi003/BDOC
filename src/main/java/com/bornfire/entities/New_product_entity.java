package com.bornfire.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;

@Entity
@Table(name = "NEW_PRODUCTS")
public class New_product_entity {

    @Id
    @Column(name = "NP_ID", length = 100)
    private String npId;

    @Column(name = "PNAME", length = 100)
    private String productName;

    @Column(name = "BATCH", length = 100)
    private String batch;

    @Column(name = "GSTIN", length = 100)
    private String gstin;

    @Column(name = "EXPDATE", length = 100)
    private String expiryDate;

    @Column(name = "MRP", length = 100)
    private String mrp;

    @Column(name = "UNITINKG", length = 100)
    private String unitInKg;

    @Column(name = "CATNAME", length = 100)
    private String categoryName;

    @Column(name = "KGS", length = 100)
    private String kgs;

    @Column(name = "STATUS", length = 100)
    private String status;

    @Column(name = "MAKE_PRODUCT_NAME", length = 100)
    private String makeProductName;
    
    @Column(name = "expensesType", length = 100)
    private String expensesType;
    
    @Column(name = "expensesamount", length = 100)
    private String expensesamount;

	public String getNpId() {
		return npId;
	}

	public void setNpId(String npId) {
		this.npId = npId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getMrp() {
		return mrp;
	}

	public void setMrp(String mrp) {
		this.mrp = mrp;
	}

	public String getUnitInKg() {
		return unitInKg;
	}

	public void setUnitInKg(String unitInKg) {
		this.unitInKg = unitInKg;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getKgs() {
		return kgs;
	}

	public void setKgs(String kgs) {
		this.kgs = kgs;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMakeProductName() {
		return makeProductName;
	}

	public void setMakeProductName(String makeProductName) {
		this.makeProductName = makeProductName;
	}

	public String getExpensesType() {
		return expensesType;
	}

	public void setExpensesType(String expensesType) {
		this.expensesType = expensesType;
	}

	public String getExpensesamount() {
		return expensesamount;
	}

	public void setExpensesamount(String expensesamount) {
		this.expensesamount = expensesamount;
	}

	public New_product_entity(String npId, String productName, String batch, String gstin, String expiryDate,
			String mrp, String unitInKg, String categoryName, String kgs, String status, String makeProductName,
			String expensesType, String expensesamount) {
		super();
		this.npId = npId;
		this.productName = productName;
		this.batch = batch;
		this.gstin = gstin;
		this.expiryDate = expiryDate;
		this.mrp = mrp;
		this.unitInKg = unitInKg;
		this.categoryName = categoryName;
		this.kgs = kgs;
		this.status = status;
		this.makeProductName = makeProductName;
		this.expensesType = expensesType;
		this.expensesamount = expensesamount;
	}

	public New_product_entity() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    

	
}
