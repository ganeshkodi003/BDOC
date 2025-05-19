package com.bornfire.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table (name="Expenses_Table" , schema="dbo")
public class Expenses_entity {
	@Id
	private String Exp_id;
	private String Exp_type;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date Exp_date;
	private String Notes;
	public String getExp_id() {
		return Exp_id;
	}
	public void setExp_id(String exp_id) {
		Exp_id = exp_id;
	}
	public String getExp_type() {
		return Exp_type;
	}
	public void setExp_type(String exp_type) {
		Exp_type = exp_type;
	}
	public Date getExp_date() {
		return Exp_date;
	}
	public void setExp_date(Date exp_date) {
		Exp_date = exp_date;
	}
	public String getNotes() {
		return Notes;
	}
	public void setNotes(String notes) {
		Notes = notes;
	}
	public Expenses_entity(String exp_id, String exp_type, Date exp_date, String notes) {
		super();
		Exp_id = exp_id;
		Exp_type = exp_type;
		Exp_date = exp_date;
		Notes = notes;
	}
	public Expenses_entity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
