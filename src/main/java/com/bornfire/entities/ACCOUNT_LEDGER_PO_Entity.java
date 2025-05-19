package com.bornfire.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT_LEDGER_PO")
public class ACCOUNT_LEDGER_PO_Entity {

    @Id
    @Column(name = "ID", length = 10) 
    private String id;  // Primary Key (Change if necessary)

    @Column(name = "PO_ID", length = 100)
    private String poId;

    @Column(name = "VENDOR_ID", length = 100)
    private String vendorId;

    @Column(name = "CAT_CODE", length = 100)
    private String catCode;

    @Column(name = "DESCRIPTION", length = 200)
    private String description;

    @Column(name = "AMOUNTPERITEM", length = 100)
    private String amountPerItem;

    @Column(name = "TOTAL_AMOUNT", precision = 18, scale = 2)
    private BigDecimal totalAmount;

    
    
    public String getHeadDescription() {
		return headDescription;
	}

	public void setHeadDescription(String headDescription) {
		this.headDescription = headDescription;
	}

	public String getMainDescription() {
		return mainDescription;
	}

	public void setMainDescription(String mainDescription) {
		this.mainDescription = mainDescription;
	}

	@Column(name = "HEAD_DESCRIPTION", length = 100)
    private String headDescription;

    @Column(name = "MAIN_DESCRIPTION", length = 100)
    private String mainDescription;
    
    public ACCOUNT_LEDGER_PO_Entity(String item_code, String item) {
		super();
		this.item_code = item_code;
		this.item = item;
	}

	public String getItem_code() {
		return item_code;
	}

	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	@Column(name = "ITEM_CODE", length = 100)
    private String item_code;
    

    @Column(name = "ITEM", length = 100)
    private String item;
    
	public ACCOUNT_LEDGER_PO_Entity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ACCOUNT_LEDGER_PO_Entity(String id, String poId, String vendorId, String catCode, String description,
			String amountPerItem, BigDecimal totalAmount,String headDescription,String mainDescription) {
		super();
		this.id = id;
		this.poId = poId;
		this.vendorId = vendorId;
		this.catCode = catCode;
		this.description = description;
		this.amountPerItem = amountPerItem;
		this.totalAmount = totalAmount;
		this.headDescription=headDescription;
		this.mainDescription=mainDescription;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPoId() {
		return poId;
	}

	public void setPoId(String poId) {
		this.poId = poId;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getCatCode() {
		return catCode;
	}

	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAmountPerItem() {
		return amountPerItem;
	}

	public void setAmountPerItem(String amountPerItem) {
		this.amountPerItem = amountPerItem;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
}
