package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="ERP_EXPENSES_MASTER")

public class ERP_EXPENSES_ENTITY {
	
	 @Id
	  @Column(name = "ID")
	    private Long id;
	 
	 
	    @Column(name = "EXP_ID", length = 100)
	    private String expId;

	    @Column(name = "EXP_CATEGORY", length = 100)
	    private String expCategory;

	    @Column(name = "EXP_DATE")
	    @Temporal(TemporalType.DATE)
	    private Date expDate;

	    @Column(name = "EXP_ITEM_CODE", length = 100)
	    private String expItemCode;

	    @Column(name = "EXP_NAME", length = 100)
	    private String expName;

	    @Column(name = "QTY")
	    private Long qty;

	    @Column(name = "PRICE", precision = 24, scale = 2)
	    private BigDecimal price;

	    @Column(name = "AMOUNT", precision = 24, scale = 2)
	    private BigDecimal amount;

	    @Column(name = "TOTAL_AMOUNT", precision = 24, scale = 2)
	    private BigDecimal totalAmount;

	    @Column(name = "PAYMENT_TYPE", length = 100)
	    private String paymentType;

	    @Column(name = "REFERENCE_NUMBER")
	    private Long referenceNumber;

	    @Column(name = "DEL_FLG", length = 1)
	    private String delFlg;

	    @Column(name = "MOD_FLG", length = 1)
	    private String modFlg;

	    @Column(name = "VERIFY_FLG", length = 1)
	    private String verifyFlg;

	    @Column(name = "ENTITY_FLG", length = 1)
	    private String entityFlg;
	    
	    @Column(name = "EXPENSES_type", length = 1)
	    private String expenses_type;
	    @Column(name="BRANCH_ID",length=50)
	    private String branchId;

		public String getBranchId() {
			return branchId;
		}

		public void setBranchId(String branchId) {
			this.branchId = branchId;
		}
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getExpId() {
			return expId;
		}

		public void setExpId(String expId) {
			this.expId = expId;
		}

		public String getExpCategory() {
			return expCategory;
		}

		public void setExpCategory(String expCategory) {
			this.expCategory = expCategory;
		}

		public Date getExpDate() {
			return expDate;
		}

		public void setExpDate(Date expDate) {
			this.expDate = expDate;
		}

		public String getExpItemCode() {
			return expItemCode;
		}

		public void setExpItemCode(String expItemCode) {
			this.expItemCode = expItemCode;
		}

		public String getExpName() {
			return expName;
		}

		public void setExpName(String expName) {
			this.expName = expName;
		}

		public Long getQty() {
			return qty;
		}

		public void setQty(Long qty) {
			this.qty = qty;
		}

		public BigDecimal getPrice() {
			return price;
		}

		public void setPrice(BigDecimal price) {
			this.price = price;
		}

		public BigDecimal getAmount() {
			return amount;
		}

		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}

		public BigDecimal getTotalAmount() {
			return totalAmount;
		}

		public void setTotalAmount(BigDecimal totalAmount) {
			this.totalAmount = totalAmount;
		}

		public String getPaymentType() {
			return paymentType;
		}

		public void setPaymentType(String paymentType) {
			this.paymentType = paymentType;
		}

		public Long getReferenceNumber() {
			return referenceNumber;
		}

		public void setReferenceNumber(Long referenceNumber) {
			this.referenceNumber = referenceNumber;
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

		public String getVerifyFlg() {
			return verifyFlg;
		}

		public void setVerifyFlg(String verifyFlg) {
			this.verifyFlg = verifyFlg;
		}

		public String getEntityFlg() {
			return entityFlg;
		}

		public void setEntityFlg(String entityFlg) {
			this.entityFlg = entityFlg;
		}

		public String getExpenses_type() {
			return expenses_type;
		}

		public void setExpenses_type(String expenses_type) {
			this.expenses_type = expenses_type;
		}

		public ERP_EXPENSES_ENTITY(Long id, String expId, String expCategory, Date expDate, String expItemCode,
				String expName, Long qty, BigDecimal price, BigDecimal amount, BigDecimal totalAmount,
				String paymentType, Long referenceNumber, String delFlg, String modFlg, String verifyFlg,
				String entityFlg, String expenses_type,String branchId) {
			super();
			this.id = id;
			this.expId = expId;
			this.expCategory = expCategory;
			this.expDate = expDate;
			this.expItemCode = expItemCode;
			this.expName = expName;
			this.qty = qty;
			this.price = price;
			this.amount = amount;
			this.totalAmount = totalAmount;
			this.paymentType = paymentType;
			this.referenceNumber = referenceNumber;
			this.delFlg = delFlg;
			this.modFlg = modFlg;
			this.verifyFlg = verifyFlg;
			this.entityFlg = entityFlg;
			this.expenses_type = expenses_type;
			this.branchId=branchId;
		}

		public ERP_EXPENSES_ENTITY() {
			super();
			// TODO Auto-generated constructor stub
		}

		
	    

	

}
