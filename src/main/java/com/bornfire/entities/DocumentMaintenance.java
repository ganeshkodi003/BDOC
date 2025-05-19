package com.bornfire.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "DOCUMENT_MAINTENANCE")
public class DocumentMaintenance {
    
	@Id
	private String emp_id;
	private String emp_name;
	private String file_name;
	@Lob
	private byte[] document;
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public byte[] getDocument() {
		return document;
	}
	public void setDocument(byte[] document) {
		this.document = document;
	}
	public DocumentMaintenance(String emp_id, String emp_name, String file_name, byte[] document) {
		super();
		this.emp_id = emp_id;
		this.emp_name = emp_name;
		this.file_name = file_name;
		this.document = document;
	}
	public DocumentMaintenance() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
