package com.bornfire.entities;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "GENERAL_JOURNALS")
public class General_journal_entity {

	@Id
	@Column(name = "GJ_ID")
	private Integer gjId;


    @Column(name = "PRODUCT_NAME", length = 100)
    private String productName;

    @Column(name = "CATEGORY_NAME", length = 100)
    private String categoryName;

    @Column(name = "BATCH", length = 500)
    private String batch;
    
    @Column(name = "[DESC]", length = 500)
    private String desc; // Keep the variable name as is


    @Column(name = "FROM_ACC_TYPE", length = 500)
    private String fromAccType;

    @Column(name = "TO_ACC_TYPE", length = 500)
    private String toAccType;

    @Column(name = "FROM_ACC", length = 500)
    private String fromAcc;

    @Column(name = "TO_ACC", length = 500)
    private String toAcc;

    @Column(name = "CREDIT")
    private BigDecimal credit;

    @Column(name = "DEBIT")
    private BigDecimal debit;
    
    @Column(name = "ENTRY_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date entryDate;

    @Column(name = "EXPIRY_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expiryDate;


    @Column(name = "DEL_FLG", length = 10)
    private String delFlg;

    @Column(name = "MOD_FLG", length = 10)
    private String modFlg;

    
    
    

    @Column(name = "FROMACCNO", length = 50)
    private String fromAccNo;

    @Column(name = "TOACCNO", length = 50)
    private String toAccNo;

    @Column(name = "CREATE_USER", length = 50)
    private String createUser;

    @Column(name = "VERIFY_USER", length = 50)
    private String verifyUser;

    @Column(name = "MODIFY_USER", length = 50)
    private String modifyUser;

    @Column(name = "VERIFY_FLG", length = 10)
    private String verifyFlg;

    @Column(name = "CREATE_TIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    @Column(name = "VERIFY_TIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date verifyTime;

    @Column(name = "MODIFY_TIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date modifyTime;
    
    private String order_type;
    
    
    private String Payment_By;
    @Column(name = "VendorId", length = 10)
    private String VendorId;
	public Integer getGjId() {
		return gjId;
	}
	public void setGjId(Integer gjId) {
		this.gjId = gjId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getFromAccType() {
		return fromAccType;
	}
	public void setFromAccType(String fromAccType) {
		this.fromAccType = fromAccType;
	}
	public String getToAccType() {
		return toAccType;
	}
	public void setToAccType(String toAccType) {
		this.toAccType = toAccType;
	}
	public String getFromAcc() {
		return fromAcc;
	}
	public void setFromAcc(String fromAcc) {
		this.fromAcc = fromAcc;
	}
	public String getToAcc() {
		return toAcc;
	}
	public void setToAcc(String toAcc) {
		this.toAcc = toAcc;
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
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
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
	public String getFromAccNo() {
		return fromAccNo;
	}
	public void setFromAccNo(String fromAccNo) {
		this.fromAccNo = fromAccNo;
	}
	public String getToAccNo() {
		return toAccNo;
	}
	public void setToAccNo(String toAccNo) {
		this.toAccNo = toAccNo;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getVerifyUser() {
		return verifyUser;
	}
	public void setVerifyUser(String verifyUser) {
		this.verifyUser = verifyUser;
	}
	public String getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
	public String getVerifyFlg() {
		return verifyFlg;
	}
	public void setVerifyFlg(String verifyFlg) {
		this.verifyFlg = verifyFlg;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getVerifyTime() {
		return verifyTime;
	}
	public void setVerifyTime(Date verifyTime) {
		this.verifyTime = verifyTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getOrder_type() {
		return order_type;
	}
	public void setOrder_type(String order_type) {
		this.order_type = order_type;
	}
	public String getPayment_By() {
		return Payment_By;
	}
	public void setPayment_By(String payment_By) {
		Payment_By = payment_By;
	}
	public String getVendorId() {
		return VendorId;
	}
	public void setVendorId(String vendorId) {
		VendorId = vendorId;
	}
	public General_journal_entity(Integer gjId, String productName, String categoryName, String batch, String desc,
			String fromAccType, String toAccType, String fromAcc, String toAcc, BigDecimal credit, BigDecimal debit,
			Date entryDate, Date expiryDate, String delFlg, String modFlg, String fromAccNo, String toAccNo,
			String createUser, String verifyUser, String modifyUser, String verifyFlg, Date createTime, Date verifyTime,
			Date modifyTime, String order_type, String payment_By, String vendorId) {
		super();
		this.gjId = gjId;
		this.productName = productName;
		this.categoryName = categoryName;
		this.batch = batch;
		this.desc = desc;
		this.fromAccType = fromAccType;
		this.toAccType = toAccType;
		this.fromAcc = fromAcc;
		this.toAcc = toAcc;
		this.credit = credit;
		this.debit = debit;
		this.entryDate = entryDate;
		this.expiryDate = expiryDate;
		this.delFlg = delFlg;
		this.modFlg = modFlg;
		this.fromAccNo = fromAccNo;
		this.toAccNo = toAccNo;
		this.createUser = createUser;
		this.verifyUser = verifyUser;
		this.modifyUser = modifyUser;
		this.verifyFlg = verifyFlg;
		this.createTime = createTime;
		this.verifyTime = verifyTime;
		this.modifyTime = modifyTime;
		this.order_type = order_type;
		Payment_By = payment_By;
		VendorId = vendorId;
	}
	public General_journal_entity() {
		super();
		// TODO Auto-generated constructor stub
	}

	




    
}
