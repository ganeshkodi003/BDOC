package com.bornfire.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Code_Creation")
public class CodeCreation {

    @Id
    private String id;
    @Column(name = "Head_code")
    private String headCode;
    @Column(name = "Head_description")
    private String headDescription;
    @Column(name = "category_code")
    private String categoryCode;
    @Column(name = "category_description")
    private String categoryDescription;
    @Column(name = "sub_category_code")
    private String subCategoryCode;
    @Column(name = "sub_category_description")
    private String subCategoryDescription;
    @Column(name = "asset_code")
    private String assetCode;
    
    @Column(name = "accountnumber")
    private String accountnumber;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHeadCode() {
		return headCode;
	}

	public void setHeadCode(String headCode) {
		this.headCode = headCode;
	}

	public String getHeadDescription() {
		return headDescription;
	}

	public void setHeadDescription(String headDescription) {
		this.headDescription = headDescription;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public String getSubCategoryCode() {
		return subCategoryCode;
	}

	public void setSubCategoryCode(String subCategoryCode) {
		this.subCategoryCode = subCategoryCode;
	}

	public String getSubCategoryDescription() {
		return subCategoryDescription;
	}

	public void setSubCategoryDescription(String subCategoryDescription) {
		this.subCategoryDescription = subCategoryDescription;
	}

	public String getAssetCode() {
		return assetCode;
	}

	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}

	public String getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}

	public CodeCreation(String id, String headCode, String headDescription, String categoryCode,
			String categoryDescription, String subCategoryCode, String subCategoryDescription, String assetCode,
			String accountnumber) {
		super();
		this.id = id;
		this.headCode = headCode;
		this.headDescription = headDescription;
		this.categoryCode = categoryCode;
		this.categoryDescription = categoryDescription;
		this.subCategoryCode = subCategoryCode;
		this.subCategoryDescription = subCategoryDescription;
		this.assetCode = assetCode;
		this.accountnumber = accountnumber;
	}

	public CodeCreation() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
	
    
}

