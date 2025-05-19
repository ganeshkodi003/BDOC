package com.bornfire.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FG_Group {
	 @Id
	private String FG_itemcode;
	private String name;
	private BigDecimal Total;
	public String getFG_itemcode() {
		return FG_itemcode;
	}
	public void setFG_itemcode(String fG_itemcode) {
		FG_itemcode = fG_itemcode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getTotal() {
		return Total;
	}
	public void setTotal(BigDecimal total) {
		Total = total;
	}
	public FG_Group(String fG_itemcode, String name, BigDecimal total) {
		super();
		FG_itemcode = fG_itemcode;
		this.name = name;
		Total = total;
	}
	public FG_Group() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
