package com.bornfire.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Outward_table")
public class Outward {
	@Id
    @Column(name = "Gate_out_Number_gen", length = 100)
    private String gate_out_NumberGen;

    @Column(name = "out_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date outDate;
    
    @Column(name = "Gate_out_Number", length = 100)
    private String gate_out_Number;

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

    @Column(name = "sale_id", length = 100)
    private String sale_id;

	public String getGate_out_NumberGen() {
		return gate_out_NumberGen;
	}

	public void setGate_out_NumberGen(String gate_out_NumberGen) {
		this.gate_out_NumberGen = gate_out_NumberGen;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public String getGate_out_Number() {
		return gate_out_Number;
	}

	public void setGate_out_Number(String gate_out_Number) {
		this.gate_out_Number = gate_out_Number;
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

	public String getSale_id() {
		return sale_id;
	}

	public void setSale_id(String sale_id) {
		this.sale_id = sale_id;
	}

	public Outward(String gate_out_NumberGen, Date outDate, String gate_out_Number, String billNumber,
			String invoiceNumber, Date invoiceDate, String customerName, String driverName, String vehicleNumber,
			String entityFlag, String verifyFlag, String modifyFlag, String delFlag, String sale_id) {
		super();
		this.gate_out_NumberGen = gate_out_NumberGen;
		this.outDate = outDate;
		this.gate_out_Number = gate_out_Number;
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
		this.sale_id = sale_id;
	}

	public Outward() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}
