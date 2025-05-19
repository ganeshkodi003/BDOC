
package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "PO_RETURN_TABLE")
public class PO_Return_Entity {

    @Id
    @Column(name = "ID", length = 100, nullable = false)
    private String id;
    
	@Column(name = "PO_ID", length = 100, nullable = false)
    private String poId;

    @Column(name = "VENDOR_ID", length = 100)
    private String vendorId;

    @Column(name = "ITEM_CODE", length = 100)
    private String itemcode;

    @Column(name = "ORDER_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;

    @Column(name = "DUE_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dueDate;

    @Column(name = "ITEM", length = 100)
    private String item;

    @Column(name = "QTY")
    private Integer qty;

    @Column(name = "TOTAL_QTY")
    private BigDecimal totalQty;

    @Column(name = "UNIT", length = 100)
    private String unit;

    @Column(name = "PRICE_HEADER", length = 100)
    private String priceHeader;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "TAX_PERCENTAGE", length = 100)
    private String taxPercentage;

    @Column(name = "TAX_AMOUNT")
    private BigDecimal  taxAmount;

    @Column(name = "AMOUNTPERITEM")
    private BigDecimal amountPerItem;

    @Column(name = "TOTAL_AMOUNT")
    private BigDecimal totalAmount;
    
    @Column(name = "TOTAL_AMOUNTS")
    private BigDecimal totalAmounts;

	@Column(name = "ADVANCE_AMOUNT")
    private BigDecimal advanceAmount;
    
    @Column(name = "vendorbalance")
    private BigDecimal vendorbalance;
    
    @Column(name = "credit")
    private BigDecimal credit;
    
    @Column(name = "debit")
    private BigDecimal debit;

    @Column(name = "BALANCE_AMOUNT")
    private BigDecimal balanceAmount;

    @Column(name = "PAYMENT_TYPE", length = 100)
    private String paymentType;

    @Column(name = "REFERENCE_NUMBER", length = 100)
    private String referenceNumber;

    @Column(name = "PAID_AMOUNT")
    private Integer paidAmount;

    @Column(name = "ENTITY_FLG", length = 1)
    private Character entityFlg;

    @Column(name = "VERIFY_FLG", length = 1)
    private Character verifyFlg;

    @Column(name = "MODIFY_FLG", length = 1)
    private Character modifyFlg;

    @Column(name = "DEL_FLG", length = 1)
    private Character delFlg;

    @Column(name = "INVENTORY_FLG", length = 1)
    private Character inventoryFlg;

    @Column(name = "QC_FLG", length = 1)
    private Character qcFlg;

    @Column(name = "APPROVED_FLG", length = 1)
    private Character approvedFlg;

    @Column(name = "verifytime", length = 100)
    private String verifyTime;

    @Column(name = "verifyuser", length = 100)
    private String verifyUser;

    @Column(name = "entryuser", length = 100)
    private String entryUser;

    @Column(name = "entrytime", length = 100)
    private String entryTime;

    @Column(name = "Assettype", length = 100)
    private String assetType;

    @Column(name = "Assetcategory", length = 100)
    private String assetCategory;

    @Column(name = "Assetsubcategory", length = 100)
    private String assetSubcategory;
    
    @Column(name = "orderNo", length = 100)
    private String orderNo;
    
    @Column(name = "HSN_code", length = 100)
    private String hsncode;
    
    @Column(name = "Po_Return_id", length = 100)
    private String po_return_id;

    @Column(name = "return_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date return_date;

    @Column(name="BRANCH_ID",length=50)
     private String branchId;

 	public String getBranchId() {
 		return branchId;
 	}

 	public void setBranchId(String branchId) {
 		this.branchId = branchId;
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

	public String getItemcode() {
		return itemcode;
	}

	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public BigDecimal getTotalQty() {
		return totalQty;
	}

	public void setTotalQty(BigDecimal totalQty) {
		this.totalQty = totalQty;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPriceHeader() {
		return priceHeader;
	}

	public void setPriceHeader(String priceHeader) {
		this.priceHeader = priceHeader;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getTaxPercentage() {
		return taxPercentage;
	}

	public void setTaxPercentage(String taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public BigDecimal getAmountPerItem() {
		return amountPerItem;
	}

	public void setAmountPerItem(BigDecimal amountPerItem) {
		this.amountPerItem = amountPerItem;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getTotalAmounts() {
		return totalAmounts;
	}

	public void setTotalAmounts(BigDecimal totalAmounts) {
		this.totalAmounts = totalAmounts;
	}

	public BigDecimal getAdvanceAmount() {
		return advanceAmount;
	}

	public void setAdvanceAmount(BigDecimal advanceAmount) {
		this.advanceAmount = advanceAmount;
	}

	public BigDecimal getVendorbalance() {
		return vendorbalance;
	}

	public void setVendorbalance(BigDecimal vendorbalance) {
		this.vendorbalance = vendorbalance;
	}

	public BigDecimal getCredit() {
		return credit;
	}

	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	public BigDecimal getDebit() {
		return debit;
	}

	public void setDebit(BigDecimal debit) {
		this.debit = debit;
	}

	public BigDecimal getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(BigDecimal balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public Integer getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Integer paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Character getEntityFlg() {
		return entityFlg;
	}

	public void setEntityFlg(Character entityFlg) {
		this.entityFlg = entityFlg;
	}

	public Character getVerifyFlg() {
		return verifyFlg;
	}

	public void setVerifyFlg(Character verifyFlg) {
		this.verifyFlg = verifyFlg;
	}

	public Character getModifyFlg() {
		return modifyFlg;
	}

	public void setModifyFlg(Character modifyFlg) {
		this.modifyFlg = modifyFlg;
	}

	public Character getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(Character delFlg) {
		this.delFlg = delFlg;
	}

	public Character getInventoryFlg() {
		return inventoryFlg;
	}

	public void setInventoryFlg(Character inventoryFlg) {
		this.inventoryFlg = inventoryFlg;
	}

	public Character getQcFlg() {
		return qcFlg;
	}

	public void setQcFlg(Character qcFlg) {
		this.qcFlg = qcFlg;
	}

	public Character getApprovedFlg() {
		return approvedFlg;
	}

	public void setApprovedFlg(Character approvedFlg) {
		this.approvedFlg = approvedFlg;
	}

	public String getVerifyTime() {
		return verifyTime;
	}

	public void setVerifyTime(String verifyTime) {
		this.verifyTime = verifyTime;
	}

	public String getVerifyUser() {
		return verifyUser;
	}

	public void setVerifyUser(String verifyUser) {
		this.verifyUser = verifyUser;
	}

	public String getEntryUser() {
		return entryUser;
	}

	public void setEntryUser(String entryUser) {
		this.entryUser = entryUser;
	}

	public String getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public String getAssetCategory() {
		return assetCategory;
	}

	public void setAssetCategory(String assetCategory) {
		this.assetCategory = assetCategory;
	}

	public String getAssetSubcategory() {
		return assetSubcategory;
	}

	public void setAssetSubcategory(String assetSubcategory) {
		this.assetSubcategory = assetSubcategory;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getHsncode() {
		return hsncode;
	}

	public void setHsncode(String hsncode) {
		this.hsncode = hsncode;
	}

	public String getPo_return_id() {
		return po_return_id;
	}

	public void setPo_return_id(String po_return_id) {
		this.po_return_id = po_return_id;
	}

	public Date getReturn_date() {
		return return_date;
	}

	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}

	public PO_Return_Entity(String id, String poId, String vendorId, String itemcode, Date orderDate, Date dueDate,
			String item, Integer qty, BigDecimal totalQty, String unit, String priceHeader, BigDecimal price,
			String taxPercentage, BigDecimal taxAmount, BigDecimal amountPerItem, BigDecimal totalAmount,
			BigDecimal totalAmounts, BigDecimal advanceAmount, BigDecimal vendorbalance, BigDecimal credit,
			BigDecimal debit, BigDecimal balanceAmount, String paymentType, String referenceNumber, Integer paidAmount,
			Character entityFlg, Character verifyFlg, Character modifyFlg, Character delFlg, Character inventoryFlg,
			Character qcFlg, Character approvedFlg, String verifyTime, String verifyUser, String entryUser,
			String entryTime, String assetType, String assetCategory, String assetSubcategory, String orderNo,
			String hsncode, String po_return_id, Date return_date, String branchId) {
		super();
		this.id = id;
		this.poId = poId;
		this.vendorId = vendorId;
		this.itemcode = itemcode;
		this.orderDate = orderDate;
		this.dueDate = dueDate;
		this.item = item;
		this.qty = qty;
		this.totalQty = totalQty;
		this.unit = unit;
		this.priceHeader = priceHeader;
		this.price = price;
		this.taxPercentage = taxPercentage;
		this.taxAmount = taxAmount;
		this.amountPerItem = amountPerItem;
		this.totalAmount = totalAmount;
		this.totalAmounts = totalAmounts;
		this.advanceAmount = advanceAmount;
		this.vendorbalance = vendorbalance;
		this.credit = credit;
		this.debit = debit;
		this.balanceAmount = balanceAmount;
		this.paymentType = paymentType;
		this.referenceNumber = referenceNumber;
		this.paidAmount = paidAmount;
		this.entityFlg = entityFlg;
		this.verifyFlg = verifyFlg;
		this.modifyFlg = modifyFlg;
		this.delFlg = delFlg;
		this.inventoryFlg = inventoryFlg;
		this.qcFlg = qcFlg;
		this.approvedFlg = approvedFlg;
		this.verifyTime = verifyTime;
		this.verifyUser = verifyUser;
		this.entryUser = entryUser;
		this.entryTime = entryTime;
		this.assetType = assetType;
		this.assetCategory = assetCategory;
		this.assetSubcategory = assetSubcategory;
		this.orderNo = orderNo;
		this.hsncode = hsncode;
		this.po_return_id = po_return_id;
		this.return_date = return_date;
		this.branchId=branchId;
	}

	public PO_Return_Entity() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}

