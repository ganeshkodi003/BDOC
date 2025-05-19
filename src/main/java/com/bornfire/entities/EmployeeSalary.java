package com.bornfire.entities;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "EmployeeSalary")
public class EmployeeSalary {

    @Id
    @Column(name = "SAL_ID")
    private Integer salId;

    @Column(name = "EMP_ID")
    private String empId;

    @Column(name = "EMP_NAME")
    private String empName;

    @Column(name = "GROSSSALARY")
    private BigDecimal grossSalary;

    @Column(name = "NETSALARY")
    private BigDecimal netSalary;

    @Column(name = "Deductions")
    private BigDecimal deductions;
    
    @Column(name = "Payment_By")
    private String Payment_By;

    @Column(name = "MONTH")
    private Integer month;

    @Column(name = "YEAR")
    private Integer year;

    @Column(name = "Attendancecount")
    private Integer attendanceCount;

    @Column(name = "odcount")
    private Integer odCount;

    @Column(name = "leavecount")
    private Integer leaveCount;

    @Column(name = "holidaycount")
    private Integer holidayCount;

    @Column(name = "modifyflg")
    private String modifyFlg;
    
    @Column(name= "Working_days")
    private String workingdays;
    
    @Column(name="Designation")
    private String Designation;
    
    
    
    
    

	public String getDesignation() {
		return Designation;
	}

	public void setDesignation(String designation) {
		Designation = designation;
	}



	

	public String getWorkingdays() {
		return workingdays;
	}

	public void setWorkingdays(String workingdays) {
		this.workingdays = workingdays;
	}

	public Integer getSalId() {
		return salId;
	}

	public void setSalId(Integer salId) {
		this.salId = salId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public BigDecimal getGrossSalary() {
		return grossSalary;
	}

	public void setGrossSalary(BigDecimal grossSalary) {
		this.grossSalary = grossSalary;
	}

	public BigDecimal getNetSalary() {
		return netSalary;
	}

	public void setNetSalary(BigDecimal netSalary) {
		this.netSalary = netSalary;
	}

	public BigDecimal getDeductions() {
		return deductions;
	}

	public void setDeductions(BigDecimal deductions) {
		this.deductions = deductions;
	}

	public String getPayment_By() {
		return Payment_By;
	}

	public void setPayment_By(String payment_By) {
		Payment_By = payment_By;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getAttendanceCount() {
		return attendanceCount;
	}

	public void setAttendanceCount(Integer attendanceCount) {
		this.attendanceCount = attendanceCount;
	}

	public Integer getOdCount() {
		return odCount;
	}

	public void setOdCount(Integer odCount) {
		this.odCount = odCount;
	}

	public Integer getLeaveCount() {
		return leaveCount;
	}

	public void setLeaveCount(Integer leaveCount) {
		this.leaveCount = leaveCount;
	}

	public Integer getHolidayCount() {
		return holidayCount;
	}

	public void setHolidayCount(Integer holidayCount) {
		this.holidayCount = holidayCount;
	}

	public String getModifyFlg() {
		return modifyFlg;
	}

	public void setModifyFlg(String modifyFlg) {
		this.modifyFlg = modifyFlg;
	}

	public EmployeeSalary(Integer salId, String empId, String empName, BigDecimal grossSalary, BigDecimal netSalary,
			BigDecimal deductions, String payment_By, Integer month, Integer year, Integer attendanceCount,
			Integer odCount, Integer leaveCount, Integer holidayCount, String modifyFlg, String workingdays,
			String designation) {
		super();
		this.salId = salId;
		this.empId = empId;
		this.empName = empName;
		this.grossSalary = grossSalary;
		this.netSalary = netSalary;
		this.deductions = deductions;
		Payment_By = payment_By;
		this.month = month;
		this.year = year;
		this.attendanceCount = attendanceCount;
		this.odCount = odCount;
		this.leaveCount = leaveCount;
		this.holidayCount = holidayCount;
		this.modifyFlg = modifyFlg;
		this.workingdays = workingdays;
		Designation = designation;
	}

	public EmployeeSalary() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
    
}
