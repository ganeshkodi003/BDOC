package com.bornfire.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Entity
@Table(name = "WORK_ORDER_TABLE")
public class WorkOrder {


	@Id
    @Column(name = "WORK_ID", length = 100)
    private String workId;

    @Column(name = "VENDOR_NAME", length = 100)
    private String vendorName;
    
    @Column(name = "VendorId", length = 100)
    private String VendorId;
     
    @Column(name = "DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Column(name = "ITEM", length = 100)
    private String item;

    @Column(name = "QTY")
    private int qty;

    @Column(name = "HSN_SAC_CODE", length = 100)
    private String hsnSacCode;

    @Column(name = "RATE")
    private long rate;

    @Column(name = "TAX")
    private int tax;

    @Column(name = "Paymenttype", length = 100)
    private String  Paymenttype;
    
    @Column(name = "FREIGHT", length = 100)
    private String freight;

    @Column(name = "ENTITY_FLG", length = 1)
    private char entityFlg;

    @Column(name = "VERIFY_FLG", length = 1)
    private char verifyFlg;

    @Column(name = "MODIFY_FLG", length = 1)
    private char modifyFlg;

    @Column(name = "DEL_FLG", length = 1)
    private char delFlg;

    @Column(name = "GATE_ENTRY_FLG", length = 1)
    private char gateEntryFlg;

    @Column(name = "QC_FLG", length = 1)
    private char qcFlg;

    @Column(name = "APPROVED_FLG", length = 1)
    private char approvedFlg;

    @Column(name = "Address", length = 500)
    private String address;

    @Column(name = "payment_terms", length = 200)
    private String paymentTerms;

    @Column(name = "freight_terms", length = 200)
    private String freightTerms;

    @Column(name = "delivery_terms", length = 200)
    private String deliveryTerms;

    @Column(name = "verifytime", length = 100)
    private String verifyTime;

    @Column(name = "verifyuser", length = 100)
    private String verifyUser;

    @Column(name = "entryuser", length = 100)
    private String entryUser;

    @Column(name = "entrytime", length = 100)
    private String entryTime;
    
    @Column(name = "status", length = 100)
    private String  status;
    
    @Column(name = "refid", length = 100)
    private String  refid;
    
    @Column(name = "Journal_flg", length = 100)
    private String  Journal_flg;

	public String getWorkId() {
		return workId;
	}

	public void setWorkId(String workId) {
		this.workId = workId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getVendorId() {
		return VendorId;
	}

	public void setVendorId(String vendorId) {
		VendorId = vendorId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getHsnSacCode() {
		return hsnSacCode;
	}

	public void setHsnSacCode(String hsnSacCode) {
		this.hsnSacCode = hsnSacCode;
	}

	public long getRate() {
		return rate;
	}

	public void setRate(long rate) {
		this.rate = rate;
	}

	public int getTax() {
		return tax;
	}

	public void setTax(int tax) {
		this.tax = tax;
	}

	public String getPaymenttype() {
		return Paymenttype;
	}

	public void setPaymenttype(String paymenttype) {
		Paymenttype = paymenttype;
	}

	public String getFreight() {
		return freight;
	}

	public void setFreight(String freight) {
		this.freight = freight;
	}

	public char getEntityFlg() {
		return entityFlg;
	}

	public void setEntityFlg(char entityFlg) {
		this.entityFlg = entityFlg;
	}

	public char getVerifyFlg() {
		return verifyFlg;
	}

	public void setVerifyFlg(char verifyFlg) {
		this.verifyFlg = verifyFlg;
	}

	public char getModifyFlg() {
		return modifyFlg;
	}

	public void setModifyFlg(char modifyFlg) {
		this.modifyFlg = modifyFlg;
	}

	public char getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(char delFlg) {
		this.delFlg = delFlg;
	}

	public char getGateEntryFlg() {
		return gateEntryFlg;
	}

	public void setGateEntryFlg(char gateEntryFlg) {
		this.gateEntryFlg = gateEntryFlg;
	}

	public char getQcFlg() {
		return qcFlg;
	}

	public void setQcFlg(char qcFlg) {
		this.qcFlg = qcFlg;
	}

	public char getApprovedFlg() {
		return approvedFlg;
	}

	public void setApprovedFlg(char approvedFlg) {
		this.approvedFlg = approvedFlg;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPaymentTerms() {
		return paymentTerms;
	}

	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}

	public String getFreightTerms() {
		return freightTerms;
	}

	public void setFreightTerms(String freightTerms) {
		this.freightTerms = freightTerms;
	}

	public String getDeliveryTerms() {
		return deliveryTerms;
	}

	public void setDeliveryTerms(String deliveryTerms) {
		this.deliveryTerms = deliveryTerms;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRefid() {
		return refid;
	}

	public void setRefid(String refid) {
		this.refid = refid;
	}

	public String getJournal_flg() {
		return Journal_flg;
	}

	public void setJournal_flg(String journal_flg) {
		Journal_flg = journal_flg;
	}

	public WorkOrder(String workId, String vendorName, String vendorId, Date date, String item, int qty,
			String hsnSacCode, long rate, int tax, String paymenttype, String freight, char entityFlg, char verifyFlg,
			char modifyFlg, char delFlg, char gateEntryFlg, char qcFlg, char approvedFlg, String address,
			String paymentTerms, String freightTerms, String deliveryTerms, String verifyTime, String verifyUser,
			String entryUser, String entryTime, String status, String refid, String journal_flg) {
		super();
		this.workId = workId;
		this.vendorName = vendorName;
		VendorId = vendorId;
		this.date = date;
		this.item = item;
		this.qty = qty;
		this.hsnSacCode = hsnSacCode;
		this.rate = rate;
		this.tax = tax;
		Paymenttype = paymenttype;
		this.freight = freight;
		this.entityFlg = entityFlg;
		this.verifyFlg = verifyFlg;
		this.modifyFlg = modifyFlg;
		this.delFlg = delFlg;
		this.gateEntryFlg = gateEntryFlg;
		this.qcFlg = qcFlg;
		this.approvedFlg = approvedFlg;
		this.address = address;
		this.paymentTerms = paymentTerms;
		this.freightTerms = freightTerms;
		this.deliveryTerms = deliveryTerms;
		this.verifyTime = verifyTime;
		this.verifyUser = verifyUser;
		this.entryUser = entryUser;
		this.entryTime = entryTime;
		this.status = status;
		this.refid = refid;
		Journal_flg = journal_flg;
	}

	public WorkOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
    
  
	
	
}
