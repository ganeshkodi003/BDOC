package com.bornfire.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Category")
public class Category {
	@Id
	private String assetcode;
	private String headcode;
	private String headdescription;
	private String categorycode;
	private String categorydescription;
	private String subcategorycode;
	private String subcategorydescription;
	private String del_flg;
	public String getAssetcode() {
		return assetcode;
	}
	public void setAssetcode(String assetcode) {
		this.assetcode = assetcode;
	}
	public String getHeadcode() {
		return headcode;
	}
	public void setHeadcode(String headcode) {
		this.headcode = headcode;
	}
	public String getHeaddescription() {
		return headdescription;
	}
	public void setHeaddescription(String headdescription) {
		this.headdescription = headdescription;
	}
	public String getCategorycode() {
		return categorycode;
	}
	public void setCategorycode(String categorycode) {
		this.categorycode = categorycode;
	}
	public String getCategorydescription() {
		return categorydescription;
	}
	public void setCategorydescription(String categorydescription) {
		this.categorydescription = categorydescription;
	}
	public String getSubcategorycode() {
		return subcategorycode;
	}
	public void setSubcategorycode(String subcategorycode) {
		this.subcategorycode = subcategorycode;
	}
	public String getSubcategorydescription() {
		return subcategorydescription;
	}
	public void setSubcategorydescription(String subcategorydescription) {
		this.subcategorydescription = subcategorydescription;
	}
	public String getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}
	public Category(String assetcode, String headcode, String headdescription, String categorycode,
			String categorydescription, String subcategorycode, String subcategorydescription, String del_flg) {
		super();
		this.assetcode = assetcode;
		this.headcode = headcode;
		this.headdescription = headdescription;
		this.categorycode = categorycode;
		this.categorydescription = categorydescription;
		this.subcategorycode = subcategorycode;
		this.subcategorydescription = subcategorydescription;
		this.del_flg = del_flg;
	}
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
