package com.bornfire.entities;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;

@Entity
@Table(name = "Inward_table")
public class Inward {

    @Id
    @Column(name = "GateEntryNumber_gen", length = 100)
    private String gateEntryNumberGen;

    @Column(name = "In_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inDate;

    @Column(name = "GateEntryNumber", length = 100)
    private String gateEntryNumber;

    @Column(name = "BillNumber", length = 100)
    private String billNumber;

    @Column(name = "Invoicenumber", length = 100)
    private String invoiceNumber;

    @Column(name = "Invoicedate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date invoiceDate;

    @Column(name = "customername", length = 200)
    private String customerName;

    @Column(name = "DriverName", length = 200)
    private String driverName;

    @Column(name = "vehicleNumber", length = 200)
    private String vehicleNumber;

    @Column(name = "Entity_flg", length = 100)
    private String entityFlag;

    @Column(name = "Verify_flg", length = 100)
    private String verifyFlag;

    @Column(name = "Modify_flg", length = 100)
    private String modifyFlag;

    @Column(name = "DEL_flg", length = 100)
    private String delFlag;

    @Column(name = "Po_number", length = 100)
    private String poNumber;
    
    private String QC_FLG;
    
    private String gate_entry_flg;

	public String getGateEntryNumberGen() {
		return gateEntryNumberGen;
	}

	public void setGateEntryNumberGen(String gateEntryNumberGen) {
		this.gateEntryNumberGen = gateEntryNumberGen;
	}

	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public String getGateEntryNumber() {
		return gateEntryNumber;
	}

	public void setGateEntryNumber(String gateEntryNumber) {
		this.gateEntryNumber = gateEntryNumber;
	}

	public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getEntityFlag() {
		return entityFlag;
	}

	public void setEntityFlag(String entityFlag) {
		this.entityFlag = entityFlag;
	}

	public String getVerifyFlag() {
		return verifyFlag;
	}

	public void setVerifyFlag(String verifyFlag) {
		this.verifyFlag = verifyFlag;
	}

	public String getModifyFlag() {
		return modifyFlag;
	}

	public void setModifyFlag(String modifyFlag) {
		this.modifyFlag = modifyFlag;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public String getQC_FLG() {
		return QC_FLG;
	}

	public void setQC_FLG(String qC_FLG) {
		QC_FLG = qC_FLG;
	}

	public String getGate_entry_flg() {
		return gate_entry_flg;
	}

	public void setGate_entry_flg(String gate_entry_flg) {
		this.gate_entry_flg = gate_entry_flg;
	}

	public Inward(String gateEntryNumberGen, Date inDate, String gateEntryNumber, String billNumber,
			String invoiceNumber, Date invoiceDate, String customerName, String driverName, String vehicleNumber,
			String entityFlag, String verifyFlag, String modifyFlag, String delFlag, String poNumber, String qC_FLG,
			String gate_entry_flg) {
		super();
		this.gateEntryNumberGen = gateEntryNumberGen;
		this.inDate = inDate;
		this.gateEntryNumber = gateEntryNumber;
		this.billNumber = billNumber;
		this.invoiceNumber = invoiceNumber;
		this.invoiceDate = invoiceDate;
		this.customerName = customerName;
		this.driverName = driverName;
		this.vehicleNumber = vehicleNumber;
		this.entityFlag = entityFlag;
		this.verifyFlag = verifyFlag;
		this.modifyFlag = modifyFlag;
		this.delFlag = delFlag;
		this.poNumber = poNumber;
		QC_FLG = qC_FLG;
		this.gate_entry_flg = gate_entry_flg;
	}

	public Inward() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
    
}