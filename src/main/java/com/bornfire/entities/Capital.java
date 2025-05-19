package com.bornfire.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
@Table(name = "Capital")
@Entity
public class Capital {
	@Id
	private Long capid;
    private Long capitalAmount;
	public Long getCapid() {
		return capid;
	}
	public void setCapid(Long capid) {
		this.capid = capid;
	}
	public Long getCapitalAmount() {
		return capitalAmount;
	}
	public void setCapitalAmount(Long capitalAmount) {
		this.capitalAmount = capitalAmount;
	}
	public Capital(Long capid, Long capitalAmount) {
		super();
		this.capid = capid;
		this.capitalAmount = capitalAmount;
	}
	public Capital() {
		super();
		// TODO Auto-generated constructor stub
	}

	   
    
}