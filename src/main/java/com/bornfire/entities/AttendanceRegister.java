package com.bornfire.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "EMP_ATT_MASTER",schema = "dbo")
 
public class AttendanceRegister  {

	 @EmbeddedId
 private AttendanceID id;
	
	private String emp_name;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date logout_time;
	
	
	private String leave_flg;
	private String leave_category;
	private String leave_remarks;
	

	private String first_entry_time;
	private String last_update_time;
	private String del_flg;
	private String emp_remarks;
	private String ip_address;
	private String device;
	private String login_status;
	public AttendanceID getId() {
		return id;
	}
	public void setId(AttendanceID id) {
		this.id = id;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public Date getLogout_time() {
		return logout_time;
	}
	public void setLogout_time(Date logout_time) {
		this.logout_time = logout_time;
	}
	public String getLeave_flg() {
		return leave_flg;
	}
	public void setLeave_flg(String leave_flg) {
		this.leave_flg = leave_flg;
	}
	public String getLeave_category() {
		return leave_category;
	}
	public void setLeave_category(String leave_category) {
		this.leave_category = leave_category;
	}
	public String getLeave_remarks() {
		return leave_remarks;
	}
	public void setLeave_remarks(String leave_remarks) {
		this.leave_remarks = leave_remarks;
	}
	public String getFirst_entry_time() {
		return first_entry_time;
	}
	public void setFirst_entry_time(String first_entry_time) {
		this.first_entry_time = first_entry_time;
	}
	public String getLast_update_time() {
		return last_update_time;
	}
	public void setLast_update_time(String last_update_time) {
		this.last_update_time = last_update_time;
	}
	public String getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}
	public String getEmp_remarks() {
		return emp_remarks;
	}
	public void setEmp_remarks(String emp_remarks) {
		this.emp_remarks = emp_remarks;
	}
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getLogin_status() {
		return login_status;
	}
	public void setLogin_status(String login_status) {
		this.login_status = login_status;
	}
	public AttendanceRegister(AttendanceID id, String emp_name, Date logout_time, String leave_flg,
			String leave_category, String leave_remarks, String first_entry_time, String last_update_time,
			String del_flg, String emp_remarks, String ip_address, String device, String login_status) {
		super();
		this.id = id;
		this.emp_name = emp_name;
		this.logout_time = logout_time;
		this.leave_flg = leave_flg;
		this.leave_category = leave_category;
		this.leave_remarks = leave_remarks;
		this.first_entry_time = first_entry_time;
		this.last_update_time = last_update_time;
		this.del_flg = del_flg;
		this.emp_remarks = emp_remarks;
		this.ip_address = ip_address;
		this.device = device;
		this.login_status = login_status;
	}
	public AttendanceRegister() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}

