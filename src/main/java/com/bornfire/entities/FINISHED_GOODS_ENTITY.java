package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table; 
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "FINISHED_GOODS_TABLE")
public class FINISHED_GOODS_ENTITY {

    @Id
    @Column(name = "FG_ID")
    private String fgId;

    @Column(name = "RETURN_QUANTITY")
    private BigDecimal returnQuantity;

    @Column(name = "RETURN_UNIT")
    private String returnUnit;

    @Column(name = "REMAINING_QUANTITY")
    private BigDecimal remainingQuantity;

    @Column(name = "ITEM_CODE")
    private String itemCode;

    @Column(name = "ITEM_NAME")
    private String itemName;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "BASE_UNIT")
    private String baseUnit;

    @Column(name = "BATCH")
    private String batch;

    @Column(name = "QTY")
    private BigDecimal qty;

    @Column(name = "USING_QUANTITY")
    private BigDecimal usingQuantity;

    @Column(name = "REQ_QTY")
    private BigDecimal reqQty;

    @Column(name = "MFG_PRODUCT_NAME")
    private String mfgProductName;

    @Column(name = "DEL_FLG")
    private String delFlg;

    @Column(name = "MOD_FLG")
    private String modFlg;

    @Column(name = "create_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    @Column(name = "exp_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expDate;
    
    
    @Column(name = "packingmaterialprice")
    private  BigDecimal packingmaterialprice;
    
    @Column(name = "totalprice")
    private  BigDecimal totalprice;
    
    @Column(name = "FG_itemcode")
    private String fg_itemcode;
    
    @Column(name = "FG_name")
    private String fg_name;

    @Column(name="BRANCH_ID",length=50)
     private String branchId;

 	public String getBranchId() {
 		return branchId;
 	}

 	public void setBranchId(String branchId) {
 		this.branchId = branchId;
 	}
	public String getFgId() {
		return fgId;
	}

	public void setFgId(String fgId) {
		this.fgId = fgId;
	}

	public BigDecimal getReturnQuantity() {
		return returnQuantity;
	}

	public void setReturnQuantity(BigDecimal returnQuantity) {
		this.returnQuantity = returnQuantity;
	}

	public String getReturnUnit() {
		return returnUnit;
	}

	public void setReturnUnit(String returnUnit) {
		this.returnUnit = returnUnit;
	}

	public BigDecimal getRemainingQuantity() {
		return remainingQuantity;
	}

	public void setRemainingQuantity(BigDecimal remainingQuantity) {
		this.remainingQuantity = remainingQuantity;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBaseUnit() {
		return baseUnit;
	}

	public void setBaseUnit(String baseUnit) {
		this.baseUnit = baseUnit;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getUsingQuantity() {
		return usingQuantity;
	}

	public void setUsingQuantity(BigDecimal usingQuantity) {
		this.usingQuantity = usingQuantity;
	}

	public BigDecimal getReqQty() {
		return reqQty;
	}

	public void setReqQty(BigDecimal reqQty) {
		this.reqQty = reqQty;
	}

	public String getMfgProductName() {
		return mfgProductName;
	}

	public void setMfgProductName(String mfgProductName) {
		this.mfgProductName = mfgProductName;
	}

	public String getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
	}

	public String getModFlg() {
		return modFlg;
	}

	public void setModFlg(String modFlg) {
		this.modFlg = modFlg;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public BigDecimal getPackingmaterialprice() {
		return packingmaterialprice;
	}

	public void setPackingmaterialprice(BigDecimal packingmaterialprice) {
		this.packingmaterialprice = packingmaterialprice;
	}

	public BigDecimal getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(BigDecimal totalprice) {
		this.totalprice = totalprice;
	}

	public String getFg_itemcode() {
		return fg_itemcode;
	}

	public void setFg_itemcode(String fg_itemcode) {
		this.fg_itemcode = fg_itemcode;
	}

	public String getFg_name() {
		return fg_name;
	}

	public void setFg_name(String fg_name) {
		this.fg_name = fg_name;
	}

	public FINISHED_GOODS_ENTITY(String fgId, BigDecimal returnQuantity, String returnUnit,
			BigDecimal remainingQuantity, String itemCode, String itemName, String category, String baseUnit,
			String batch, BigDecimal qty, BigDecimal usingQuantity, BigDecimal reqQty, String mfgProductName,
			String delFlg, String modFlg, Date createDate, Date expDate, BigDecimal packingmaterialprice,
			BigDecimal totalprice, String fg_itemcode, String fg_name,String branchId) {
		super();
		this.fgId = fgId;
		this.returnQuantity = returnQuantity;
		this.returnUnit = returnUnit;
		this.remainingQuantity = remainingQuantity;
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.category = category;
		this.baseUnit = baseUnit;
		this.batch = batch;
		this.qty = qty;
		this.usingQuantity = usingQuantity;
		this.reqQty = reqQty;
		this.mfgProductName = mfgProductName;
		this.delFlg = delFlg;
		this.modFlg = modFlg;
		this.createDate = createDate;
		this.expDate = expDate;
		this.packingmaterialprice = packingmaterialprice;
		this.totalprice = totalprice;
		this.fg_itemcode = fg_itemcode;
		this.fg_name = fg_name;
		this.branchId=branchId;
	}

	public FINISHED_GOODS_ENTITY() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    
    
    
}
