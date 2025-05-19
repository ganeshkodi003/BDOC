package com.bornfire.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "branch_table")
public class Branch_Entity {
	@Id
    @Column(name = "Branch_Id", length = 100, nullable = false)
    private String branchId;

    @Column(name = "Branch_Name", length = 100)
    private String branchName;

    @Column(name = "Landline1", length = 100)
    private String landline1;

    @Column(name = "Landline2", length = 100)
    private String landline2;

    @Column(name = "Mobile_No", length = 100)
    private String mobileNo;

    @Column(name = "Alternate_Mobile_No", length = 100)
    private String alternateMobileNo;

    @Column(name = "Address_Line_1", length = 100)
    private String addressLine1;

    @Column(name = "Address_Line_2", length = 100)
    private String addressLine2;

    @Column(name = "City", length = 100)
    private String city;

    @Column(name = "State", length = 100)
    private String state;

    @Column(name = "Country", length = 100)
    private String country;

    @Column(name = "Postal_Code", length = 100)
    private String postalCode;

    @Column(name = "entry_user", length = 100)
    private String entryUser;

    @Column(name = "modify_user", length = 100)
    private String modifyUser;

    @Column(name = "del_flg", length = 100)
    private String delFlg;

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getLandline1() {
		return landline1;
	}

	public void setLandline1(String landline1) {
		this.landline1 = landline1;
	}

	public String getLandline2() {
		return landline2;
	}

	public void setLandline2(String landline2) {
		this.landline2 = landline2;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getAlternateMobileNo() {
		return alternateMobileNo;
	}

	public void setAlternateMobileNo(String alternateMobileNo) {
		this.alternateMobileNo = alternateMobileNo;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getEntryUser() {
		return entryUser;
	}

	public void setEntryUser(String entryUser) {
		this.entryUser = entryUser;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public String getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
	}

	public Branch_Entity(String branchId, String branchName, String landline1, String landline2, String mobileNo,
			String alternateMobileNo, String addressLine1, String addressLine2, String city, String state,
			String country, String postalCode, String entryUser, String modifyUser, String delFlg) {
		super();
		this.branchId = branchId;
		this.branchName = branchName;
		this.landline1 = landline1;
		this.landline2 = landline2;
		this.mobileNo = mobileNo;
		this.alternateMobileNo = alternateMobileNo;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.state = state;
		this.country = country;
		this.postalCode = postalCode;
		this.entryUser = entryUser;
		this.modifyUser = modifyUser;
		this.delFlg = delFlg;
	}

	public Branch_Entity() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
