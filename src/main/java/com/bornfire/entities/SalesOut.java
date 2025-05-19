package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "SALES_OUT")
public class SalesOut {
	 @Id
	    @Column(name = "ID", length = 100, nullable = false)
	    private String id;

	 
	    @Column(name = "WO_ID", length = 100, nullable = false)
	    private String woId;

	    @Column(name = "VENDOR_ID", length = 100)
	    private String vendorId;

	    @Column(name = "ITEM_CODE", length = 100)
	    private String itemcode;

	    @Column(name = "ORDER_DATE")
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
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
	    
	    @Column(name = "hsncode", length = 100)
	    private String hsncode;
	    
	    @Column(name = "orderno", length = 100)
	    private String orderNo;
	   
	   @Column(name = "SHIPPING_ADDRESS", length = 100) 
	   private String shippingaddress;
	   
	   @Column(name = "PHONE_NO", length = 100)
	   private String phoneno;

		@Column(name = "LAST_EXTRA_AMOUNT")
	    private BigDecimal last_extra_amount;

	    @Column(name = "EXTRA_AMOUNT")
	    private BigDecimal extra_amount;
	    
	   private String bill_companyname;
	   private String ship_companyname;
	   private String bill_address;
	   private String ship_address;
	   private String bill_street;
	   private String ship_street;
	   private String bill_dis;
	   private String ship_dis;
	   private String bill_pincode;
	   private String ship_pincode;
	   private String bill_phno;
	   private String  ship_phno;
	   private String bill_email;
	   private String ship_email;
	   private String bill_gstno;
	   private String ship_gstno;
	   private String batch;
	   private String stock;
	   private String saletype;
	   private String vendor_name;
	   @Column(name = "usingqty")
	   private BigDecimal usingqty;
	   
	   @Column(name = "returning_flg")
	   private String returning_flg;
	   
	   @Column(name = "invoice_conform_date")
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    private Date invoice_conform_date;
	   
	   private String deliverytype;
	   private BigDecimal deliverycharge;
	   private 	String deliverystatus;
	   @DateTimeFormat(pattern = "yyyy-MM-dd")
	    private Date deliverydate;
	   private String deliverypaymenttype;
	   

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
	public String getHsncode() {
		return hsncode;
	}
	public void setHsncode(String hsncode) {
		this.hsncode = hsncode;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getShippingaddress() {
		return shippingaddress;
	}
	public void setShippingaddress(String shippingaddress) {
		this.shippingaddress = shippingaddress;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public BigDecimal getLast_extra_amount() {
		return last_extra_amount;
	}
	public void setLast_extra_amount(BigDecimal last_extra_amount) {
		this.last_extra_amount = last_extra_amount;
	}
	public BigDecimal getExtra_amount() {
		return extra_amount;
	}
	public void setExtra_amount(BigDecimal extra_amount) {
		this.extra_amount = extra_amount;
	}
	public String getBill_companyname() {
		return bill_companyname;
	}
	public void setBill_companyname(String bill_companyname) {
		this.bill_companyname = bill_companyname;
	}
	public String getShip_companyname() {
		return ship_companyname;
	}
	public void setShip_companyname(String ship_companyname) {
		this.ship_companyname = ship_companyname;
	}
	public String getBill_address() {
		return bill_address;
	}
	public void setBill_address(String bill_address) {
		this.bill_address = bill_address;
	}
	public String getShip_address() {
		return ship_address;
	}
	public void setShip_address(String ship_address) {
		this.ship_address = ship_address;
	}
	public String getBill_street() {
		return bill_street;
	}
	public void setBill_street(String bill_street) {
		this.bill_street = bill_street;
	}
	public String getShip_street() {
		return ship_street;
	}
	public void setShip_street(String ship_street) {
		this.ship_street = ship_street;
	}
	public String getBill_dis() {
		return bill_dis;
	}
	public void setBill_dis(String bill_dis) {
		this.bill_dis = bill_dis;
	}
	public String getShip_dis() {
		return ship_dis;
	}
	public void setShip_dis(String ship_dis) {
		this.ship_dis = ship_dis;
	}
	public String getBill_pincode() {
		return bill_pincode;
	}
	public void setBill_pincode(String bill_pincode) {
		this.bill_pincode = bill_pincode;
	}
	public String getShip_pincode() {
		return ship_pincode;
	}
	public void setShip_pincode(String ship_pincode) {
		this.ship_pincode = ship_pincode;
	}
	public String getBill_phno() {
		return bill_phno;
	}
	public void setBill_phno(String bill_phno) {
		this.bill_phno = bill_phno;
	}
	public String getShip_phno() {
		return ship_phno;
	}
	public void setShip_phno(String ship_phno) {
		this.ship_phno = ship_phno;
	}
	public String getBill_email() {
		return bill_email;
	}
	public void setBill_email(String bill_email) {
		this.bill_email = bill_email;
	}
	public String getShip_email() {
		return ship_email;
	}
	public void setShip_email(String ship_email) {
		this.ship_email = ship_email;
	}
	public String getBill_gstno() {
		return bill_gstno;
	}
	public void setBill_gstno(String bill_gstno) {
		this.bill_gstno = bill_gstno;
	}
	public String getShip_gstno() {
		return ship_gstno;
	}
	public void setShip_gstno(String ship_gstno) {
		this.ship_gstno = ship_gstno;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public String getSaletype() {
		return saletype;
	}
	public void setSaletype(String saletype) {
		this.saletype = saletype;
	}
	public String getVendor_name() {
		return vendor_name;
	}
	public void setVendor_name(String vendor_name) {
		this.vendor_name = vendor_name;
	}
	public BigDecimal getUsingqty() {
		return usingqty;
	}
	public void setUsingqty(BigDecimal usingqty) {
		this.usingqty = usingqty;
	}
	public Date getInvoice_conform_date() {
		return invoice_conform_date;
	}
	public void setInvoice_conform_date(Date invoice_conform_date) {
		this.invoice_conform_date = invoice_conform_date;
	}
	public String getDeliverytype() {
		return deliverytype;
	}
	public void setDeliverytype(String deliverytype) {
		this.deliverytype = deliverytype;
	}
	public BigDecimal getDeliverycharge() {
		return deliverycharge;
	}
	public void setDeliverycharge(BigDecimal deliverycharge) {
		this.deliverycharge = deliverycharge;
	}
	public String getDeliverystatus() {
		return deliverystatus;
	}
	public void setDeliverystatus(String deliverystatus) {
		this.deliverystatus = deliverystatus;
	}
	public Date getDeliverydate() {
		return deliverydate;
	}
	public void setDeliverydate(Date deliverydate) {
		this.deliverydate = deliverydate;
	}
	public String getDeliverypaymenttype() {
		return deliverypaymenttype;
	}
	public void setDeliverypaymenttype(String deliverypaymenttype) {
		this.deliverypaymenttype = deliverypaymenttype;
	}
	
	public String getReturning_flg() {
		return returning_flg;
	}

	public void setReturning_flg(String returning_flg) {
		this.returning_flg = returning_flg;
	}

	
	public SalesOut(String id, String woId, String vendorId, String itemcode, Date orderDate, Date dueDate, String item,
			Integer qty, BigDecimal totalQty, String unit, String priceHeader, BigDecimal price, String taxPercentage,
			BigDecimal taxAmount, BigDecimal amountPerItem, BigDecimal totalAmount, BigDecimal advanceAmount,
			BigDecimal balanceAmount, String paymentType, String referenceNumber, BigDecimal paidAmount,
			Character entityFlg, Character verifyFlg, Character modifyFlg, Character delFlg, Character inventoryFlg,
			Character qcFlg, Character approvedFlg, String verifyTime, String verifyUser, String entryUser,
			String entryTime, String assetType, String assetCategory, String assetSubcategory, String hsncode,
			String orderNo, String shippingaddress, String phoneno, BigDecimal last_extra_amount,
			BigDecimal extra_amount, String bill_companyname, String ship_companyname, String bill_address,
			String ship_address, String bill_street, String ship_street, String bill_dis, String ship_dis,
			String bill_pincode, String ship_pincode, String bill_phno, String ship_phno, String bill_email,
			String ship_email, String bill_gstno, String ship_gstno, String batch, String stock, String saletype,
			String vendor_name, BigDecimal usingqty, String returning_flg, Date invoice_conform_date,
			String deliverytype, BigDecimal deliverycharge, String deliverystatus, Date deliverydate,
			String deliverypaymenttype, String branchId) {
		super();
		this.id = id;
		this.woId = woId;
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
		this.hsncode = hsncode;
		this.orderNo = orderNo;
		this.shippingaddress = shippingaddress;
		this.phoneno = phoneno;
		this.last_extra_amount = last_extra_amount;
		this.extra_amount = extra_amount;
		this.bill_companyname = bill_companyname;
		this.ship_companyname = ship_companyname;
		this.bill_address = bill_address;
		this.ship_address = ship_address;
		this.bill_street = bill_street;
		this.ship_street = ship_street;
		this.bill_dis = bill_dis;
		this.ship_dis = ship_dis;
		this.bill_pincode = bill_pincode;
		this.ship_pincode = ship_pincode;
		this.bill_phno = bill_phno;
		this.ship_phno = ship_phno;
		this.bill_email = bill_email;
		this.ship_email = ship_email;
		this.bill_gstno = bill_gstno;
		this.ship_gstno = ship_gstno;
		this.batch = batch;
		this.stock = stock;
		this.saletype = saletype;
		this.vendor_name = vendor_name;
		this.usingqty = usingqty;
		this.returning_flg = returning_flg;
		this.invoice_conform_date = invoice_conform_date;
		this.deliverytype = deliverytype;
		this.deliverycharge = deliverycharge;
		this.deliverystatus = deliverystatus;
		this.deliverydate = deliverydate;
		this.deliverypaymenttype = deliverypaymenttype;
		this.branchId = branchId;
	}

	public SalesOut() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
