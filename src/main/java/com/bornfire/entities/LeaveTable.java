package com.bornfire.entities;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LEAVE_TABLE")
public class LeaveTable {
	
	@Id
	private BigDecimal	no_row;
	private BigDecimal	number_of_days;
	public BigDecimal getNo_row() {
		return no_row;
	}
	public void setNo_row(BigDecimal no_row) {
		this.no_row = no_row;
	}
	public BigDecimal getNumber_of_days() {
		return number_of_days;
	}
	public void setNumber_of_days(BigDecimal number_of_days) {
		this.number_of_days = number_of_days;
	}
	public LeaveTable() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LeaveTable(BigDecimal no_row, BigDecimal number_of_days) {
		super();
		this.no_row = no_row;
		this.number_of_days = number_of_days;
	}
	
	
	

}
