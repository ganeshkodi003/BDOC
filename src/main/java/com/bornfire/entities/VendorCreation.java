package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "VendorCreation")
public class VendorCreation {

    @Id
    @Column(name = "vendor_id", length = 100, nullable = false)
    private String vendorId;

    @Column(name = "vendor_name", length = 100)
    private String vendorName;

    @Column(name = "acc_no", length = 100)
    private String acc_no;
  
	@Column(name = "GL_CODE",length = 100)
    private String glCode;

    @Column(name = "GL_DESC", length = 100)
    private String glDesc;

    
	@Column(name = "CLASSIFICATION",length = 100)
    private String classify;

	@Column(name = "CURRENCY",length = 100)
    private String crncy;
    
    
	@Column(name = "GLSH_CODE",length = 100)
    private String glshCode;

    @Column(name = "GLSH_DESC", length = 100)
    private String glshDesc;

    @Column(name = "ACCT_NUM", length = 100)
    private String acctNum;

    @Column(name = "ACCT_NAME", length = 100)
    private String acctName;

    @Column(name = "CR_AMT", precision = 25, scale = 4)
    private BigDecimal crAmt;

    @Column(name = "DR_AMT", precision = 25, scale = 4)
    private BigDecimal drAmt;
    
	@Column(name = "mobile_no", length = 100)
    private String mobileNo;

    @Column(name = "vendor_type", length = 100)
    private String vendorType;

    @Column(name = "gst_type", length = 100)
    private String gstType;
    
    @Column(name = "gst_No", length = 100)
    private String gstNo;
    
    @Column(name = "msme_type", length = 100)
    private String msmeType;

    @Column(name = "msme_number", length = 100)
    private String msmeNumber;

    @Column(name = "buyer_address", length = 100)
    private String buyerAddress;

    @Column(name = "buyer_state", length = 100)
    private String buyerState;

    @Column(name = "buyer_district", length = 100)
    private String buyerDistrict;

    @Column(name = "buyer_pincode", length = 100)
    private String buyerPincode;

    @Column(name = "shipping_address", length = 100)
    private String shippingAddress;

    @Column(name = "shipping_state", length = 100)
    private String shippingState;

    @Column(name = "shipping_district", length = 100)
    private String shippingDistrict;

    @Column(name = "shipping_pincode", length = 100)
    private String shippingPincode;

    @Column(name = "bank_acno", length = 100)
    private String bankAcNo;

    @Column(name = "bank_name", length = 100)
    private String bankName;

    @Column(name = "bank_branch_name", length = 100)
    private String bankBranchName;

    @Column(name = "branch_code", length = 100)
    private String branchCode;

    @Column(name = "bank_accountholdername", length = 100)
    private String bankAccountHolderName;

    @Column(name = "ifsc_code", length = 100)
    private String ifscCode;

    @Column(name = "opening_balance", length = 100)
    private BigDecimal openingBalance;
    
    @Column(name = "emailid", length = 100)
    private String emailid;

    @Column(name = "asofdate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date asOfDate;

    @Column(name = "credit_limit", length = 100)
    private String creditLimit;

    @Column(name = "credit_limitamount")
    private Long creditLimitAmount;

    @Column(name = "entry_flg", length = 50)
    private String entryFlag;

    @Column(name = "modify_flg", length = 50)
    private String modifyFlag;

    @Column(name = "verify_flg", length = 50)
    private String verifyFlag;

    @Column(name = "entry_time", length = 100)
    private String entryTime;

    @Column(name = "entry_user", length = 100)
    private String entryUser;
    
    @Column(name="del_flg",length=100)
    private String delflg;
    
    
    @Column(name="advanceamount",length=100)
    private BigDecimal advanceamount;
    
    @Column(name="balanceamount",length=100)
    private BigDecimal balanceamount;
    
    @Column(name="salecategory",length=100)
    private String salecategory;
    
    @Column(name="Opening_Balance_Type",length=100)
    private String Opening_Balance_Type;
    

    @Column(name="BRANCH_ID",length=50)
    private String branchId;

	@Column(name = "ORG_ID", length = 100)
    private String org_id;
    
    public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

	@Column(name="group_account",length=100)
    private String group_account;

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getAcc_no() {
		return acc_no;
	}

	public void setAcc_no(String acc_no) {
		this.acc_no = acc_no;
	}

	public String getGlCode() {
		return glCode;
	}

	public void setGlCode(String glCode) {
		this.glCode = glCode;
	}

	public String getGlDesc() {
		return glDesc;
	}

	public void setGlDesc(String glDesc) {
		this.glDesc = glDesc;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public String getCrncy() {
		return crncy;
	}

	public void setCrncy(String crncy) {
		this.crncy = crncy;
	}

	public String getGlshCode() {
		return glshCode;
	}

	public void setGlshCode(String glshCode) {
		this.glshCode = glshCode;
	}

	public String getGlshDesc() {
		return glshDesc;
	}

	public void setGlshDesc(String glshDesc) {
		this.glshDesc = glshDesc;
	}

	public String getAcctNum() {
		return acctNum;
	}

	public void setAcctNum(String acctNum) {
		this.acctNum = acctNum;
	}

	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	public BigDecimal getCrAmt() {
		return crAmt;
	}

	public void setCrAmt(BigDecimal crAmt) {
		this.crAmt = crAmt;
	}

	public BigDecimal getDrAmt() {
		return drAmt;
	}

	public void setDrAmt(BigDecimal drAmt) {
		this.drAmt = drAmt;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getVendorType() {
		return vendorType;
	}

	public void setVendorType(String vendorType) {
		this.vendorType = vendorType;
	}

	public String getGstType() {
		return gstType;
	}

	public void setGstType(String gstType) {
		this.gstType = gstType;
	}

	public String getGstNo() {
		return gstNo;
	}

	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}

	public String getMsmeType() {
		return msmeType;
	}

	public void setMsmeType(String msmeType) {
		this.msmeType = msmeType;
	}

	public String getMsmeNumber() {
		return msmeNumber;
	}

	public void setMsmeNumber(String msmeNumber) {
		this.msmeNumber = msmeNumber;
	}

	public String getBuyerAddress() {
		return buyerAddress;
	}

	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
	}

	public String getBuyerState() {
		return buyerState;
	}

	public void setBuyerState(String buyerState) {
		this.buyerState = buyerState;
	}

	public String getBuyerDistrict() {
		return buyerDistrict;
	}

	public void setBuyerDistrict(String buyerDistrict) {
		this.buyerDistrict = buyerDistrict;
	}

	public String getBuyerPincode() {
		return buyerPincode;
	}

	public void setBuyerPincode(String buyerPincode) {
		this.buyerPincode = buyerPincode;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getShippingState() {
		return shippingState;
	}

	public void setShippingState(String shippingState) {
		this.shippingState = shippingState;
	}

	public String getShippingDistrict() {
		return shippingDistrict;
	}

	public void setShippingDistrict(String shippingDistrict) {
		this.shippingDistrict = shippingDistrict;
	}

	public String getShippingPincode() {
		return shippingPincode;
	}

	public void setShippingPincode(String shippingPincode) {
		this.shippingPincode = shippingPincode;
	}

	public String getBankAcNo() {
		return bankAcNo;
	}

	public void setBankAcNo(String bankAcNo) {
		this.bankAcNo = bankAcNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankBranchName() {
		return bankBranchName;
	}

	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBankAccountHolderName() {
		return bankAccountHolderName;
	}

	public void setBankAccountHolderName(String bankAccountHolderName) {
		this.bankAccountHolderName = bankAccountHolderName;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public BigDecimal getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(BigDecimal openingBalance) {
		this.openingBalance = openingBalance;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public Date getAsOfDate() {
		return asOfDate;
	}

	public void setAsOfDate(Date asOfDate) {
		this.asOfDate = asOfDate;
	}

	public String getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}

	public Long getCreditLimitAmount() {
		return creditLimitAmount;
	}

	public void setCreditLimitAmount(Long creditLimitAmount) {
		this.creditLimitAmount = creditLimitAmount;
	}

	public String getEntryFlag() {
		return entryFlag;
	}

	public void setEntryFlag(String entryFlag) {
		this.entryFlag = entryFlag;
	}

	public String getModifyFlag() {
		return modifyFlag;
	}

	public void setModifyFlag(String modifyFlag) {
		this.modifyFlag = modifyFlag;
	}

	public String getVerifyFlag() {
		return verifyFlag;
	}

	public void setVerifyFlag(String verifyFlag) {
		this.verifyFlag = verifyFlag;
	}

	public String getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}

	public String getEntryUser() {
		return entryUser;
	}

	public void setEntryUser(String entryUser) {
		this.entryUser = entryUser;
	}

	public String getDelflg() {
		return delflg;
	}

	public void setDelflg(String delflg) {
		this.delflg = delflg;
	}

	public BigDecimal getAdvanceamount() {
		return advanceamount;
	}

	public void setAdvanceamount(BigDecimal advanceamount) {
		this.advanceamount = advanceamount;
	}

	public BigDecimal getBalanceamount() {
		return balanceamount;
	}

	public void setBalanceamount(BigDecimal balanceamount) {
		this.balanceamount = balanceamount;
	}

	public String getSalecategory() {
		return salecategory;
	}

	public void setSalecategory(String salecategory) {
		this.salecategory = salecategory;
	}

	public String getOpening_Balance_Type() {
		return Opening_Balance_Type;
	}

	public void setOpening_Balance_Type(String opening_Balance_Type) {
		Opening_Balance_Type = opening_Balance_Type;
	}

	
	public String getGroup_account() {
		return group_account;
	}

	public void setGroup_account(String group_account) {
		this.group_account = group_account;
	}

	public VendorCreation(String vendorId, String vendorName, String acc_no, String glCode, String glDesc,
			String classify, String crncy, String glshCode, String glshDesc, String acctNum, String acctName,
			BigDecimal crAmt, BigDecimal drAmt, String mobileNo, String vendorType, String gstType, String gstNo,
			String msmeType, String msmeNumber, String buyerAddress, String buyerState, String buyerDistrict,
			String buyerPincode, String shippingAddress, String shippingState, String shippingDistrict,
			String shippingPincode, String bankAcNo, String bankName, String bankBranchName, String branchCode,
			String bankAccountHolderName, String ifscCode, BigDecimal openingBalance, String emailid, Date asOfDate,
			String creditLimit, Long creditLimitAmount, String entryFlag, String modifyFlag, String verifyFlag,
			String entryTime, String entryUser, String delflg, BigDecimal advanceamount, BigDecimal balanceamount,


			String salecategory, String opening_Balance_Type, String branchId, String group_account,String org_id) {

		super();
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.acc_no = acc_no;
		this.glCode = glCode;
		this.glDesc = glDesc;
		this.classify = classify;
		this.crncy = crncy;
		this.glshCode = glshCode;
		this.glshDesc = glshDesc;
		this.acctNum = acctNum;
		this.acctName = acctName;
		this.crAmt = crAmt;
		this.drAmt = drAmt;
		this.mobileNo = mobileNo;
		this.vendorType = vendorType;
		this.gstType = gstType;
		this.gstNo = gstNo;
		this.msmeType = msmeType;
		this.msmeNumber = msmeNumber;
		this.buyerAddress = buyerAddress;
		this.buyerState = buyerState;
		this.buyerDistrict = buyerDistrict;
		this.buyerPincode = buyerPincode;
		this.shippingAddress = shippingAddress;
		this.shippingState = shippingState;
		this.shippingDistrict = shippingDistrict;
		this.shippingPincode = shippingPincode;
		this.bankAcNo = bankAcNo;
		this.bankName = bankName;
		this.bankBranchName = bankBranchName;
		this.branchCode = branchCode;
		this.bankAccountHolderName = bankAccountHolderName;
		this.ifscCode = ifscCode;
		this.openingBalance = openingBalance;
		this.emailid = emailid;
		this.asOfDate = asOfDate;
		this.creditLimit = creditLimit;
		this.creditLimitAmount = creditLimitAmount;
		this.entryFlag = entryFlag;
		this.modifyFlag = modifyFlag;
		this.verifyFlag = verifyFlag;
		this.entryTime = entryTime;
		this.entryUser = entryUser;
		this.delflg = delflg;
		this.advanceamount = advanceamount;
		this.balanceamount = balanceamount;
		this.salecategory = salecategory;
		this.branchId=branchId;
		Opening_Balance_Type = opening_Balance_Type;

		this.branchId = branchId;
		this.group_account = group_account;
		this.org_id=org_id;}

	public VendorCreation() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
    
 

}