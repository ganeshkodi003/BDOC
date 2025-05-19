package com.bornfire.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HRMS_TRAINING")
public class Hrms_Training {
	@Id
	private String Training_name;
	private String Training_desc;
	public String getTraining_name() {
		return Training_name;
	}
	public Hrms_Training() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Hrms_Training(String training_name, String training_desc, String verify_flg) {
		super();
		Training_name = training_name;
		Training_desc = training_desc;
		Verify_flg = verify_flg;
	}
	public void setTraining_name(String training_name) {
		Training_name = training_name;
	}
	public String getTraining_desc() {
		return Training_desc;
	}
	public void setTraining_desc(String training_desc) {
		Training_desc = training_desc;
	}
	public String getVerify_flg() {
		return Verify_flg;
	}
	public void setVerify_flg(String verify_flg) {
		Verify_flg = verify_flg;
	}
	private String Verify_flg;
	

}
