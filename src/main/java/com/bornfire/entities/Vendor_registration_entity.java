package com.bornfire.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import javax.persistence.Column;

@Entity
@Table (name="VENDOR_REGISTRATION_TABLE")
public class Vendor_registration_entity {
	    @Id
	    @Column(name = "vendor_doc_id", length = 100)
	    private String vendorDocId;

	    @Column(name = "vendor_doc_date")
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    private Date vendorDocDate;

	    @Column(name = "salutation", length = 10)
	    private String salutation;

	    @Column(name = "vendor_id", length = 100)
	    private String vendorId;

	    @Column(name = "vendor_name", length = 100)
	    private String vendorName;

	    @Column(name = "vendor_type", length = 20)
	    private String vendorType;

	    @Column(name = "account_name", length = 200)
	    private String accountName;

	    @Column(name = "group_name", length = 50)
	    private String groupName;

	    @Column(name = "gst_regi_type", length = 100)
	    private String gstRegiType;

	    @Column(name = "gst_party_type", length = 100)
	    private String gstPartyType;

	    @Column(name = "vendor_grade", length = 10)
	    private String vendorGrade;

	    @Column(name = "vendor_status", length = 50)
	    private String vendorStatus;

	    @Column(name = "mobile_no")
	    private Long mobileNo;

	    @Column(name = "email", length = 100)
	    private String email;

	    @Column(name = "phone_no")
	    private Long phoneNo;

	    @Column(name = "fax_no", length = 100)
	    private String faxNo;

	    @Column(name = "joining_date")
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    private Date joiningDate;

	    @Column(name = "evaluation", length = 100)
	    private String evaluation;

	    @Column(name = "trail_month", length = 100)
	    private String trailMonth;

	    @Column(name = "related_price", length = 100)
	    private String relatedPrice;

	    @Column(name = "deductee_type", length = 100)
	    private String deducteeType;

	    @Column(name = "evaluation_date")
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    private Date evaluationDate;

	    @Column(name = "nature_business", length = 100)
	    private String natureBusiness;

	    @Column(name = "status_other", length = 100)
	    private String statusOther;

	    @Column(name = "active", length = 100)
	    private String active;

	    @Column(name = "TDS_applicabla", length = 50)
	    private String tdsApplicable;

	    @Column(name = "typeofdeductee", length = 50)
	    private String typeOfDeductee;

	    @Column(name = "address_detail1", length = 100)
	    private String addressDetail1;

	    @Column(name = "address_detail2", length = 100)
	    private String addressDetail2;

	    @Column(name = "address_detail3", length = 100)
	    private String addressDetail3;

	    @Column(name = "city", length = 100)
	    private String city;

	    @Column(name = "state_city", length = 100)
	    private String stateCity;

	    @Column(name = "country", length = 100)
	    private String country;

	    @Column(name = "state_code", length = 100)
	    private String stateCode;

	    @Column(name = "pin_code", length = 50)
	    private String pinCode;

	    @Column(name = "continent", length = 100)
	    private String continent;

	    @Column(name = "pan_no", length = 100)
	    private String panNo;

	    @Column(name = "pan_name", length = 100)
	    private String panName;

	    @Column(name = "GST_3Digit", length = 100)
	    private String gst3Digit;

	    @Column(name = "GST_IN_NO", length = 100)
	    private String gstInNo;

	    @Column(name = "intruduced_by", length = 100)
	    private String introducedBy;

	    @Column(name = "range_other", length = 100)
	    private String rangeOther;

	    @Column(name = "business_geography", length = 100)
	    private String businessGeography;

	    @Column(name = "division", length = 100)
	    private String division;

	    @Column(name = "commissionerate", length = 100)
	    private String commissionerate;

	    @Column(name = "service_tax_no", length = 100)
	    private String serviceTaxNo;

	    @Column(name = "region", length = 100)
	    private String region;

	    @Column(name = "area", length = 100)
	    private String area;

	    @Column(name = "bank_acc_no", length = 100)
	    private String bankAccNo;

	    @Column(name = "bank_name", length = 100)
	    private String bankName;

	    @Column(name = "branch_name", length = 100)
	    private String branchName;

	    @Column(name = "branch_code", length = 100)
	    private String branchCode;

	    @Column(name = "bank_acc_name", length = 100)
	    private String bankAccName;

	    @Column(name = "IFSC_code", length = 100)
	    private String ifscCode;

	    @Column(name = "entity_flg", length = 50)
	    private String entityFlag;

	    @Column(name = "modify_flg", length = 50)
	    private String modifyFlag;

	    @Column(name = "verify_flg", length = 50)
	    private String verifyFlag;

	    @Column(name = "entry_time", length = 100)
	    private String entryTime;

	    @Column(name = "entry_user", length = 100)
	    private String entryUser;

	    @Column(name = "verify_time", length = 100)
	    private String verifyTime;

	    @Column(name = "verify_user", length = 100)
	    private String verifyUser;
	    
	    
	    @Column(name = "Assettype", length = 100)
	    private String Assettype;
	    
	    @Column(name = "Assetcategory", length = 100)
	    private String Assetcategory;
	    
	    @Column(name = "Assetsubcategory", length = 100)
	    private String Assetsubcategory;
	    
	    
	    
	    private String acc_num_flg;



		public String getVendorDocId() {
			return vendorDocId;
		}



		public void setVendorDocId(String vendorDocId) {
			this.vendorDocId = vendorDocId;
		}



		public Date getVendorDocDate() {
			return vendorDocDate;
		}



		public void setVendorDocDate(Date vendorDocDate) {
			this.vendorDocDate = vendorDocDate;
		}



		public String getSalutation() {
			return salutation;
		}



		public void setSalutation(String salutation) {
			this.salutation = salutation;
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



		public String getVendorType() {
			return vendorType;
		}



		public void setVendorType(String vendorType) {
			this.vendorType = vendorType;
		}



		public String getAccountName() {
			return accountName;
		}



		public void setAccountName(String accountName) {
			this.accountName = accountName;
		}



		public String getGroupName() {
			return groupName;
		}



		public void setGroupName(String groupName) {
			this.groupName = groupName;
		}



		public String getGstRegiType() {
			return gstRegiType;
		}



		public void setGstRegiType(String gstRegiType) {
			this.gstRegiType = gstRegiType;
		}



		public String getGstPartyType() {
			return gstPartyType;
		}



		public void setGstPartyType(String gstPartyType) {
			this.gstPartyType = gstPartyType;
		}



		public String getVendorGrade() {
			return vendorGrade;
		}



		public void setVendorGrade(String vendorGrade) {
			this.vendorGrade = vendorGrade;
		}



		public String getVendorStatus() {
			return vendorStatus;
		}



		public void setVendorStatus(String vendorStatus) {
			this.vendorStatus = vendorStatus;
		}



		public Long getMobileNo() {
			return mobileNo;
		}



		public void setMobileNo(Long mobileNo) {
			this.mobileNo = mobileNo;
		}



		public String getEmail() {
			return email;
		}



		public void setEmail(String email) {
			this.email = email;
		}



		public Long getPhoneNo() {
			return phoneNo;
		}



		public void setPhoneNo(Long phoneNo) {
			this.phoneNo = phoneNo;
		}



		public String getFaxNo() {
			return faxNo;
		}



		public void setFaxNo(String faxNo) {
			this.faxNo = faxNo;
		}



		public Date getJoiningDate() {
			return joiningDate;
		}



		public void setJoiningDate(Date joiningDate) {
			this.joiningDate = joiningDate;
		}



		public String getEvaluation() {
			return evaluation;
		}



		public void setEvaluation(String evaluation) {
			this.evaluation = evaluation;
		}



		public String getTrailMonth() {
			return trailMonth;
		}



		public void setTrailMonth(String trailMonth) {
			this.trailMonth = trailMonth;
		}



		public String getRelatedPrice() {
			return relatedPrice;
		}



		public void setRelatedPrice(String relatedPrice) {
			this.relatedPrice = relatedPrice;
		}



		public String getDeducteeType() {
			return deducteeType;
		}



		public void setDeducteeType(String deducteeType) {
			this.deducteeType = deducteeType;
		}



		public Date getEvaluationDate() {
			return evaluationDate;
		}



		public void setEvaluationDate(Date evaluationDate) {
			this.evaluationDate = evaluationDate;
		}



		public String getNatureBusiness() {
			return natureBusiness;
		}



		public void setNatureBusiness(String natureBusiness) {
			this.natureBusiness = natureBusiness;
		}



		public String getStatusOther() {
			return statusOther;
		}



		public void setStatusOther(String statusOther) {
			this.statusOther = statusOther;
		}



		public String getActive() {
			return active;
		}



		public void setActive(String active) {
			this.active = active;
		}



		public String getTdsApplicable() {
			return tdsApplicable;
		}



		public void setTdsApplicable(String tdsApplicable) {
			this.tdsApplicable = tdsApplicable;
		}



		public String getTypeOfDeductee() {
			return typeOfDeductee;
		}



		public void setTypeOfDeductee(String typeOfDeductee) {
			this.typeOfDeductee = typeOfDeductee;
		}



		public String getAddressDetail1() {
			return addressDetail1;
		}



		public void setAddressDetail1(String addressDetail1) {
			this.addressDetail1 = addressDetail1;
		}



		public String getAddressDetail2() {
			return addressDetail2;
		}



		public void setAddressDetail2(String addressDetail2) {
			this.addressDetail2 = addressDetail2;
		}



		public String getAddressDetail3() {
			return addressDetail3;
		}



		public void setAddressDetail3(String addressDetail3) {
			this.addressDetail3 = addressDetail3;
		}



		public String getCity() {
			return city;
		}



		public void setCity(String city) {
			this.city = city;
		}



		public String getStateCity() {
			return stateCity;
		}



		public void setStateCity(String stateCity) {
			this.stateCity = stateCity;
		}



		public String getCountry() {
			return country;
		}



		public void setCountry(String country) {
			this.country = country;
		}



		public String getStateCode() {
			return stateCode;
		}



		public void setStateCode(String stateCode) {
			this.stateCode = stateCode;
		}



		public String getPinCode() {
			return pinCode;
		}



		public void setPinCode(String pinCode) {
			this.pinCode = pinCode;
		}



		public String getContinent() {
			return continent;
		}



		public void setContinent(String continent) {
			this.continent = continent;
		}



		public String getPanNo() {
			return panNo;
		}



		public void setPanNo(String panNo) {
			this.panNo = panNo;
		}



		public String getPanName() {
			return panName;
		}



		public void setPanName(String panName) {
			this.panName = panName;
		}



		public String getGst3Digit() {
			return gst3Digit;
		}



		public void setGst3Digit(String gst3Digit) {
			this.gst3Digit = gst3Digit;
		}



		public String getGstInNo() {
			return gstInNo;
		}



		public void setGstInNo(String gstInNo) {
			this.gstInNo = gstInNo;
		}



		public String getIntroducedBy() {
			return introducedBy;
		}



		public void setIntroducedBy(String introducedBy) {
			this.introducedBy = introducedBy;
		}



		public String getRangeOther() {
			return rangeOther;
		}



		public void setRangeOther(String rangeOther) {
			this.rangeOther = rangeOther;
		}



		public String getBusinessGeography() {
			return businessGeography;
		}



		public void setBusinessGeography(String businessGeography) {
			this.businessGeography = businessGeography;
		}



		public String getDivision() {
			return division;
		}



		public void setDivision(String division) {
			this.division = division;
		}



		public String getCommissionerate() {
			return commissionerate;
		}



		public void setCommissionerate(String commissionerate) {
			this.commissionerate = commissionerate;
		}



		public String getServiceTaxNo() {
			return serviceTaxNo;
		}



		public void setServiceTaxNo(String serviceTaxNo) {
			this.serviceTaxNo = serviceTaxNo;
		}



		public String getRegion() {
			return region;
		}



		public void setRegion(String region) {
			this.region = region;
		}



		public String getArea() {
			return area;
		}



		public void setArea(String area) {
			this.area = area;
		}



		public String getBankAccNo() {
			return bankAccNo;
		}



		public void setBankAccNo(String bankAccNo) {
			this.bankAccNo = bankAccNo;
		}



		public String getBankName() {
			return bankName;
		}



		public void setBankName(String bankName) {
			this.bankName = bankName;
		}



		public String getBranchName() {
			return branchName;
		}



		public void setBranchName(String branchName) {
			this.branchName = branchName;
		}



		public String getBranchCode() {
			return branchCode;
		}



		public void setBranchCode(String branchCode) {
			this.branchCode = branchCode;
		}



		public String getBankAccName() {
			return bankAccName;
		}



		public void setBankAccName(String bankAccName) {
			this.bankAccName = bankAccName;
		}



		public String getIfscCode() {
			return ifscCode;
		}



		public void setIfscCode(String ifscCode) {
			this.ifscCode = ifscCode;
		}



		public String getEntityFlag() {
			return entityFlag;
		}



		public void setEntityFlag(String entityFlag) {
			this.entityFlag = entityFlag;
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



		public String getAcc_num_flg() {
			return acc_num_flg;
		}



		public void setAcc_num_flg(String acc_num_flg) {
			this.acc_num_flg = acc_num_flg;
		}



		public Vendor_registration_entity(String vendorDocId, Date vendorDocDate, String salutation, String vendorId,
				String vendorName, String vendorType, String accountName, String groupName, String gstRegiType,
				String gstPartyType, String vendorGrade, String vendorStatus, Long mobileNo, String email, Long phoneNo,
				String faxNo, Date joiningDate, String evaluation, String trailMonth, String relatedPrice,
				String deducteeType, Date evaluationDate, String natureBusiness, String statusOther, String active,
				String tdsApplicable, String typeOfDeductee, String addressDetail1, String addressDetail2,
				String addressDetail3, String city, String stateCity, String country, String stateCode, String pinCode,
				String continent, String panNo, String panName, String gst3Digit, String gstInNo, String introducedBy,
				String rangeOther, String businessGeography, String division, String commissionerate,
				String serviceTaxNo, String region, String area, String bankAccNo, String bankName, String branchName,
				String branchCode, String bankAccName, String ifscCode, String entityFlag, String modifyFlag,
				String verifyFlag, String entryTime, String entryUser, String verifyTime, String verifyUser,
				String assettype, String assetcategory, String assetsubcategory, String acc_num_flg) {
			super();
			this.vendorDocId = vendorDocId;
			this.vendorDocDate = vendorDocDate;
			this.salutation = salutation;
			this.vendorId = vendorId;
			this.vendorName = vendorName;
			this.vendorType = vendorType;
			this.accountName = accountName;
			this.groupName = groupName;
			this.gstRegiType = gstRegiType;
			this.gstPartyType = gstPartyType;
			this.vendorGrade = vendorGrade;
			this.vendorStatus = vendorStatus;
			this.mobileNo = mobileNo;
			this.email = email;
			this.phoneNo = phoneNo;
			this.faxNo = faxNo;
			this.joiningDate = joiningDate;
			this.evaluation = evaluation;
			this.trailMonth = trailMonth;
			this.relatedPrice = relatedPrice;
			this.deducteeType = deducteeType;
			this.evaluationDate = evaluationDate;
			this.natureBusiness = natureBusiness;
			this.statusOther = statusOther;
			this.active = active;
			this.tdsApplicable = tdsApplicable;
			this.typeOfDeductee = typeOfDeductee;
			this.addressDetail1 = addressDetail1;
			this.addressDetail2 = addressDetail2;
			this.addressDetail3 = addressDetail3;
			this.city = city;
			this.stateCity = stateCity;
			this.country = country;
			this.stateCode = stateCode;
			this.pinCode = pinCode;
			this.continent = continent;
			this.panNo = panNo;
			this.panName = panName;
			this.gst3Digit = gst3Digit;
			this.gstInNo = gstInNo;
			this.introducedBy = introducedBy;
			this.rangeOther = rangeOther;
			this.businessGeography = businessGeography;
			this.division = division;
			this.commissionerate = commissionerate;
			this.serviceTaxNo = serviceTaxNo;
			this.region = region;
			this.area = area;
			this.bankAccNo = bankAccNo;
			this.bankName = bankName;
			this.branchName = branchName;
			this.branchCode = branchCode;
			this.bankAccName = bankAccName;
			this.ifscCode = ifscCode;
			this.entityFlag = entityFlag;
			this.modifyFlag = modifyFlag;
			this.verifyFlag = verifyFlag;
			this.entryTime = entryTime;
			this.entryUser = entryUser;
			this.verifyTime = verifyTime;
			this.verifyUser = verifyUser;
			Assettype = assettype;
			Assetcategory = assetcategory;
			Assetsubcategory = assetsubcategory;
			this.acc_num_flg = acc_num_flg;
		}



		public Vendor_registration_entity() {
			super();
			// TODO Auto-generated constructor stub
		}		  
}
