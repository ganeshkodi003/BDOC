package com.bornfire.entities;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="PURCHASE_ORDER_TABLE")

public class Purchase_Order_Entity {
	
	    @Id
	   
	    @Column(name = "PO_ID")
	    private String poId;

	    @Column(name = "VENDOR_NAME", length = 100)
	    private String vendorName;
	    
	    @Column(name = "Assettype", length = 100)
	    private String Assettype;
	    
	    @Column(name = "Assetcategory", length = 100)
	    private String Assetcategory;
	    
	    @Column(name = "Assetsubcategory", length = 100)
	    private String Assetsubcategory;
	    
	    @Column(name = "VendorId", length = 100)
	    private String VendorId;

	    @Column(name = "DATE")
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    private Date date;

	    @Column(name = "ITEM", length = 100)
	    private String item;

	    @Column(name = "QTY")
	    private Integer qty;

	    @Column(name = "HSN_SAC_CODE", length = 100)
	    private String hsnSacCode;

	    @Column(name = "RATE")
	    private Long rate;

	    @Column(name = "TAX")
	    private Integer tax;

	    @Column(name = "FREIGHT", length = 100)
	    private String freight;

	    @Column(name = "ENTITY_FLG", length = 1)
	    private Character entityFlg;

	    @Column(name = "VERIFY_FLG", length = 1)
	    private Character verifyFlg;

	    @Column(name = "MODIFY_FLG", length = 1)
	    private Character modifyFlg;

	    @Column(name = "DEL_FLG", length = 1)
	    private Character delFlg;

	    @Column(name = "GATE_ENTRY_FLG", length = 1)
	    private Character gateEntryFlg;

	    @Column(name = "QC_FLG", length = 1)
	    private Character qcFlg;

	    @Column(name = "APPROVED_FLG", length = 1)
	    private Character approvedFlg;
	    
	    private String Address;
	    private String delivery_terms;
	    private String payment_terms;
	    private String freight_terms;
	    private String verifyuser;
	    private String verifytime;
	    private String entryuser;
	    private String entrytime;
	    private String Paymenttype;
	    private String Journal_flg;
	    private String vendortype;
	   private String grn_no;
	    private String grn_flg;
	    private String rec_qty;
		public String getPoId() {
			return poId;
		}
		public void setPoId(String poId) {
			this.poId = poId;
		}
		public String getVendorName() {
			return vendorName;
		}
		public void setVendorName(String vendorName) {
			this.vendorName = vendorName;
		}
		public String getAssettype() {
			return Assettype;
		}
		public void setAssettype(String assettype) {
			Assettype = assettype;
		}
		public String getAssetcategory() {
			return Assetcategory;
		}
		public void setAssetcategory(String assetcategory) {
			Assetcategory = assetcategory;
		}
		public String getAssetsubcategory() {
			return Assetsubcategory;
		}
		public void setAssetsubcategory(String assetsubcategory) {
			Assetsubcategory = assetsubcategory;
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
		public Integer getQty() {
			return qty;
		}
		public void setQty(Integer qty) {
			this.qty = qty;
		}
		public String getHsnSacCode() {
			return hsnSacCode;
		}
		public void setHsnSacCode(String hsnSacCode) {
			this.hsnSacCode = hsnSacCode;
		}
		public Long getRate() {
			return rate;
		}
		public void setRate(Long rate) {
			this.rate = rate;
		}
		public Integer getTax() {
			return tax;
		}
		public void setTax(Integer tax) {
			this.tax = tax;
		}
		public String getFreight() {
			return freight;
		}
		public void setFreight(String freight) {
			this.freight = freight;
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
		public Character getGateEntryFlg() {
			return gateEntryFlg;
		}
		public void setGateEntryFlg(Character gateEntryFlg) {
			this.gateEntryFlg = gateEntryFlg;
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
		public String getAddress() {
			return Address;
		}
		public void setAddress(String address) {
			Address = address;
		}
		public String getDelivery_terms() {
			return delivery_terms;
		}
		public void setDelivery_terms(String delivery_terms) {
			this.delivery_terms = delivery_terms;
		}
		public String getPayment_terms() {
			return payment_terms;
		}
		public void setPayment_terms(String payment_terms) {
			this.payment_terms = payment_terms;
		}
		public String getFreight_terms() {
			return freight_terms;
		}
		public void setFreight_terms(String freight_terms) {
			this.freight_terms = freight_terms;
		}
		public String getVerifyuser() {
			return verifyuser;
		}
		public void setVerifyuser(String verifyuser) {
			this.verifyuser = verifyuser;
		}
		public String getVerifytime() {
			return verifytime;
		}
		public void setVerifytime(String verifytime) {
			this.verifytime = verifytime;
		}
		public String getEntryuser() {
			return entryuser;
		}
		public void setEntryuser(String entryuser) {
			this.entryuser = entryuser;
		}
		public String getEntrytime() {
			return entrytime;
		}
		public void setEntrytime(String entrytime) {
			this.entrytime = entrytime;
		}
		public String getPaymenttype() {
			return Paymenttype;
		}
		public void setPaymenttype(String paymenttype) {
			Paymenttype = paymenttype;
		}
		public String getJournal_flg() {
			return Journal_flg;
		}
		public void setJournal_flg(String journal_flg) {
			Journal_flg = journal_flg;
		}
		public String getVendortype() {
			return vendortype;
		}
		public void setVendortype(String vendortype) {
			this.vendortype = vendortype;
		}
		public String getGrn_no() {
			return grn_no;
		}
		public void setGrn_no(String grn_no) {
			this.grn_no = grn_no;
		}
		public String getGrn_flg() {
			return grn_flg;
		}
		public void setGrn_flg(String grn_flg) {
			this.grn_flg = grn_flg;
		}
		public String getRec_qty() {
			return rec_qty;
		}
		public void setRec_qty(String rec_qty) {
			this.rec_qty = rec_qty;
		}
		public Purchase_Order_Entity(String poId, String vendorName, String assettype, String assetcategory,
				String assetsubcategory, String vendorId, Date date, String item, Integer qty, String hsnSacCode,
				Long rate, Integer tax, String freight, Character entityFlg, Character verifyFlg, Character modifyFlg,
				Character delFlg, Character gateEntryFlg, Character qcFlg, Character approvedFlg, String address,
				String delivery_terms, String payment_terms, String freight_terms, String verifyuser, String verifytime,
				String entryuser, String entrytime, String paymenttype, String journal_flg, String vendortype,
				String grn_no, String grn_flg, String rec_qty) {
			super();
			this.poId = poId;
			this.vendorName = vendorName;
			Assettype = assettype;
			Assetcategory = assetcategory;
			Assetsubcategory = assetsubcategory;
			VendorId = vendorId;
			this.date = date;
			this.item = item;
			this.qty = qty;
			this.hsnSacCode = hsnSacCode;
			this.rate = rate;
			this.tax = tax;
			this.freight = freight;
			this.entityFlg = entityFlg;
			this.verifyFlg = verifyFlg;
			this.modifyFlg = modifyFlg;
			this.delFlg = delFlg;
			this.gateEntryFlg = gateEntryFlg;
			this.qcFlg = qcFlg;
			this.approvedFlg = approvedFlg;
			Address = address;
			this.delivery_terms = delivery_terms;
			this.payment_terms = payment_terms;
			this.freight_terms = freight_terms;
			this.verifyuser = verifyuser;
			this.verifytime = verifytime;
			this.entryuser = entryuser;
			this.entrytime = entrytime;
			Paymenttype = paymenttype;
			Journal_flg = journal_flg;
			this.vendortype = vendortype;
			this.grn_no = grn_no;
			this.grn_flg = grn_flg;
			this.rec_qty = rec_qty;
		}
		public Purchase_Order_Entity() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
}
