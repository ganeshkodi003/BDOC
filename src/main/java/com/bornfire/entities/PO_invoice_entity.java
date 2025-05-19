
package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "PO_INVOICE_TABLE")
public class PO_invoice_entity {

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

    @Column(name = "ADVANCE_AMOUNT")
    private BigDecimal advanceAmount;

    @Column(name = "BALANCE_AMOUNT")
    private BigDecimal balanceAmount;

    @Column(name = "PAYMENT_TYPE", length = 100)
    private String paymentType;

    @Column(name = "REFERENCE_NUMBER", length = 100)
    private String referenceNumber;

    @Column(name = "PAID_AMOUNT")
    private BigDecimal paidAmount;

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
    
    @Column(name = "batch", length = 100)
    private String batch;
    
    @Column(name = "useingqty", length = 100)
    private Integer useingqty;
    
    @Column(name = "assetcode", length = 100)
    private String assetcode;
    
    @Column(name = "vendor_name", length = 100)
    private String vendor_name;
    
    @Column(name = "invoice_conform_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date invoice_conform_date;
    
    @Column(name = "EXTRA_AMOUNT", length = 100)
    private BigDecimal extra_amount;
    
    @Column(name = "LAST_EXTRA_AMOUNT", length = 100)
    private BigDecimal last_extra_amount;
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

	public BigDecimal getAdvanceAmount() {
		return advanceAmount;
	}

	public void setAdvanceAmount(BigDecimal advanceAmount) {
		this.advanceAmount = advanceAmount;
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

	public BigDecimal getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
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

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public Integer getUseingqty() {
		return useingqty;
	}

	public void setUseingqty(Integer useingqty) {
		this.useingqty = useingqty;
	}

	public String getAssetcode() {
		return assetcode;
	}

	public void setAssetcode(String assetcode) {
		this.assetcode = assetcode;
	}

	public String getVendor_name() {
		return vendor_name;
	}

	public void setVendor_name(String vendor_name) {
		this.vendor_name = vendor_name;
	}

	public Date getInvoice_conform_date() {
		return invoice_conform_date;
	}

	public void setInvoice_conform_date(Date invoice_conform_date) {
		this.invoice_conform_date = invoice_conform_date;
	}

	public BigDecimal getExtra_amount() {
		return extra_amount;
	}

	public void setExtra_amount(BigDecimal extra_amount) {
		this.extra_amount = extra_amount;
	}

	public BigDecimal getLast_extra_amount() {
		return last_extra_amount;
	}

	public void setLast_extra_amount(BigDecimal last_extra_amount) {
		this.last_extra_amount = last_extra_amount;
	}

	public PO_invoice_entity(String id, String poId, String vendorId, String itemcode, Date orderDate, Date dueDate,
			String item, Integer qty, BigDecimal totalQty, String unit, String priceHeader, BigDecimal price,
			String taxPercentage, BigDecimal taxAmount, BigDecimal amountPerItem, BigDecimal totalAmount,
			BigDecimal advanceAmount, BigDecimal balanceAmount, String paymentType, String referenceNumber,
			BigDecimal paidAmount, Character entityFlg, Character verifyFlg, Character modifyFlg, Character delFlg,
			Character inventoryFlg, Character qcFlg, Character approvedFlg, String verifyTime, String verifyUser,
			String entryUser, String entryTime, String assetType, String assetCategory, String assetSubcategory,
			String orderNo, String hsncode, String batch, Integer useingqty, String assetcode, String vendor_name,
			Date invoice_conform_date, BigDecimal extra_amount, BigDecimal last_extra_amount,String branchId) {
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
		this.advanceAmount = advanceAmount;
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
		this.batch = batch;
		this.useingqty = useingqty;
		this.assetcode = assetcode;
		this.vendor_name = vendor_name;
		this.invoice_conform_date = invoice_conform_date;
		this.extra_amount = extra_amount;
		this.last_extra_amount = last_extra_amount;
		this.branchId=branchId;
	}

	public PO_invoice_entity() {
		super();
		// TODO Auto-generated constructor stub
	}

	
    
    

}

