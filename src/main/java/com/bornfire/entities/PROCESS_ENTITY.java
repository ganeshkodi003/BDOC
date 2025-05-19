package com.bornfire.entities;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "BHMS_PROCESS_TABLE")
public class PROCESS_ENTITY {

    @Id
    private String id;

	private String pname;

    private String batch;

   
	private String gstin;

    private String expdate;

    private String mrp;

    private String unitinkg;
    private String catname;
    private String pkgs;
    private String status;
    private String make_product_name;
  
	private String ref_id;

    private String kilograms;
    private String qty;
    private String InspectProcess;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date QCVerifydate;
    private String woid;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getGstin() {
		return gstin;
	}
	public void setGstin(String gstin) {
		this.gstin = gstin;
	}
	public String getExpdate() {
		return expdate;
	}
	public void setExpdate(String expdate) {
		this.expdate = expdate;
	}
	public String getMrp() {
		return mrp;
	}
	public void setMrp(String mrp) {
		this.mrp = mrp;
	}
	public String getUnitinkg() {
		return unitinkg;
	}
	public void setUnitinkg(String unitinkg) {
		this.unitinkg = unitinkg;
	}
	public String getCatname() {
		return catname;
	}
	public void setCatname(String catname) {
		this.catname = catname;
	}
	public String getPkgs() {
		return pkgs;
	}
	public void setPkgs(String pkgs) {
		this.pkgs = pkgs;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMake_product_name() {
		return make_product_name;
	}
	public void setMake_product_name(String make_product_name) {
		this.make_product_name = make_product_name;
	}
	public String getRef_id() {
		return ref_id;
	}
	public void setRef_id(String ref_id) {
		this.ref_id = ref_id;
	}
	public String getKilograms() {
		return kilograms;
	}
	public void setKilograms(String kilograms) {
		this.kilograms = kilograms;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getInspectProcess() {
		return InspectProcess;
	}
	public void setInspectProcess(String inspectProcess) {
		InspectProcess = inspectProcess;
	}
	public Date getQCVerifydate() {
		return QCVerifydate;
	}
	public void setQCVerifydate(Date qCVerifydate) {
		QCVerifydate = qCVerifydate;
	}
	public String getWoid() {
		return woid;
	}
	public void setWoid(String woid) {
		this.woid = woid;
	}
	public PROCESS_ENTITY(String id, String pname, String batch, String gstin, String expdate, String mrp,
			String unitinkg, String catname, String pkgs, String status, String make_product_name, String ref_id,
			String kilograms, String qty, String inspectProcess, Date qCVerifydate, String woid) {
		super();
		this.id = id;
		this.pname = pname;
		this.batch = batch;
		this.gstin = gstin;
		this.expdate = expdate;
		this.mrp = mrp;
		this.unitinkg = unitinkg;
		this.catname = catname;
		this.pkgs = pkgs;
		this.status = status;
		this.make_product_name = make_product_name;
		this.ref_id = ref_id;
		this.kilograms = kilograms;
		this.qty = qty;
		InspectProcess = inspectProcess;
		QCVerifydate = qCVerifydate;
		this.woid = woid;
	}
	public PROCESS_ENTITY() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
