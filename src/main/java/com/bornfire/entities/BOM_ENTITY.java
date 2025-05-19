package com.bornfire.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "BOM")
public class BOM_ENTITY {
	
	 @Id
	 private Long id;
	
    @Column(name = "BOM_ID", length = 100)
    private String bomId;

    @Column(name = "Item_code", length = 100)
    private String itemCode;

    @Column(name = "Item_name", length = 100)
    private String itemName;

    @Column(name = "Hsn_code", length = 100)
    private String hsnCode;

    @Column(name = "Category_name", length = 100)
    private String categoryName;

    @Column(name = "Base_unit", length = 100)
    private String baseUnit;

    @Column(name = "Qty", precision = 18, scale = 4)
    private BigDecimal qty;

    @Column(name = "Expenses_type", length = 100)
    private String expensesType;

    @Column(name = "Expenses_cost", precision = 18, scale = 4)
    private BigDecimal expensesCost;

    @Column(name = "Mfg_productname", length = 100)
    private String mfgProductName;

    @Column(name = "entity_flg", length = 1)
    private String entityFlag;

    @Column(name = "del_flg", length = 1)
    private String deleteFlag;

    @Column(name = "verify_flg", length = 1)
    private String verifyFlag;

    @Column(name = "create_date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate createDate;
    
    @Column(name = "price", precision = 18, scale = 2)
    private BigDecimal price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBomId() {
		return bomId;
	}

	public void setBomId(String bomId) {
		this.bomId = bomId;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getBaseUnit() {
		return baseUnit;
	}

	public void setBaseUnit(String baseUnit) {
		this.baseUnit = baseUnit;
	}

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public String getExpensesType() {
		return expensesType;
	}

	public void setExpensesType(String expensesType) {
		this.expensesType = expensesType;
	}

	public BigDecimal getExpensesCost() {
		return expensesCost;
	}

	public void setExpensesCost(BigDecimal expensesCost) {
		this.expensesCost = expensesCost;
	}

	public String getMfgProductName() {
		return mfgProductName;
	}

	public void setMfgProductName(String mfgProductName) {
		this.mfgProductName = mfgProductName;
	}

	public String getEntityFlag() {
		return entityFlag;
	}

	public void setEntityFlag(String entityFlag) {
		this.entityFlag = entityFlag;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getVerifyFlag() {
		return verifyFlag;
	}

	public void setVerifyFlag(String verifyFlag) {
		this.verifyFlag = verifyFlag;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BOM_ENTITY(Long id, String bomId, String itemCode, String itemName, String hsnCode, String categoryName,
			String baseUnit, BigDecimal qty, String expensesType, BigDecimal expensesCost, String mfgProductName,
			String entityFlag, String deleteFlag, String verifyFlag, LocalDate createDate, BigDecimal price) {
		super();
		this.id = id;
		this.bomId = bomId;
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.hsnCode = hsnCode;
		this.categoryName = categoryName;
		this.baseUnit = baseUnit;
		this.qty = qty;
		this.expensesType = expensesType;
		this.expensesCost = expensesCost;
		this.mfgProductName = mfgProductName;
		this.entityFlag = entityFlag;
		this.deleteFlag = deleteFlag;
		this.verifyFlag = verifyFlag;
		this.createDate = createDate;
		this.price = price;
	}

	public BOM_ENTITY() {
		super();
		// TODO Auto-generated constructor stub
	}

	
    
    

}
