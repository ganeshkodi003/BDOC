package com.bornfire.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TAXATION_PARAMETERS")
public class Taxation_parameters {

	@Id
	 private BigDecimal srl_no;
	 private String group_name;
	 
	 @Column(name = "[national]")
	 private String national;
	 private String national_country;
	 private String specify_country;
	 private String tax_code;
	 private String min_amount;
	 private String max_amount;
	 private String tax_value;
	private String status;
	private String fjd;
	public BigDecimal getSrl_no() {
		return srl_no;
	}
	public void setSrl_no(BigDecimal srl_no) {
		this.srl_no = srl_no;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public String getNational() {
		return national;
	}
	public void setNational(String national) {
		this.national = national;
	}
	public String getNational_country() {
		return national_country;
	}
	public void setNational_country(String national_country) {
		this.national_country = national_country;
	}
	public String getSpecify_country() {
		return specify_country;
	}
	public void setSpecify_country(String specify_country) {
		this.specify_country = specify_country;
	}
	public String getTax_code() {
		return tax_code;
	}
	public void setTax_code(String tax_code) {
		this.tax_code = tax_code;
	}
	public String getMin_amount() {
		return min_amount;
	}
	public void setMin_amount(String min_amount) {
		this.min_amount = min_amount;
	}
	public String getMax_amount() {
		return max_amount;
	}
	public void setMax_amount(String max_amount) {
		this.max_amount = max_amount;
	}
	public String getTax_value() {
		return tax_value;
	}
	public void setTax_value(String tax_value) {
		this.tax_value = tax_value;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFjd() {
		return fjd;
	}
	public void setFjd(String fjd) {
		this.fjd = fjd;
	}
	public Taxation_parameters(BigDecimal srl_no, String group_name, String national, String national_country,
			String specify_country, String tax_code, String min_amount, String max_amount, String tax_value,
			String status, String fjd) {
		super();
		this.srl_no = srl_no;
		this.group_name = group_name;
		this.national = national;
		this.national_country = national_country;
		this.specify_country = specify_country;
		this.tax_code = tax_code;
		this.min_amount = min_amount;
		this.max_amount = max_amount;
		this.tax_value = tax_value;
		this.status = status;
		this.fjd = fjd;
	}
	public Taxation_parameters() {
		super();
		// TODO Auto-generated constructor stub
	}
}
