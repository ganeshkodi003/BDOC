package com.bornfire.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categorycreation")
public class category_creation {
	@Id
	private String category_id;
	private String category_name;
	private String del_flg;
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}
	public category_creation(String category_id, String category_name, String del_flg) {
		super();
		this.category_id = category_id;
		this.category_name = category_name;
		this.del_flg = del_flg;
	}
	public category_creation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
