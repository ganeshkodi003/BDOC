package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="CAL_MASTER")
public class BTMAdminCalendarMaster {
	
	@Id
	private BigDecimal srl;
	private String year;
	private String month;
	private String holiday_str;
	private String month_remarks;
	private String hol_1;
	private String hol_2;
	private String hol_3;
	private String hol_4;
	private String hol_5;
	private String entry_user;
	private String modify_user;
	private String auth_user;
	private Date entry_time;
	private Date modify_time;
	private Date auth_time;
	private String del_flag;
	private String entity_flag;
	private String modify_flag;
	private String week_holiday_str;
	private String verify_user;
	private Date verify_time;
	private Date nextduedate;
	private String other;
	private String upload;
	private String input;

	public BigDecimal getSrl() {
		return srl;
	}

	public void setSrl(BigDecimal srl) {
		this.srl = srl;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getHoliday_str() {
		return holiday_str;
	}

	public void setHoliday_str(String holiday_str) {
		this.holiday_str = holiday_str;
	}

	public String getMonth_remarks() {
		return month_remarks;
	}

	public void setMonth_remarks(String month_remarks) {
		this.month_remarks = month_remarks;
	}

	public String getHol_1() {
		return hol_1;
	}

	public void setHol_1(String hol_1) {
		this.hol_1 = hol_1;
	}

	public String getHol_2() {
		return hol_2;
	}

	public void setHol_2(String hol_2) {
		this.hol_2 = hol_2;
	}

	public String getHol_3() {
		return hol_3;
	}

	public void setHol_3(String hol_3) {
		this.hol_3 = hol_3;
	}

	public String getHol_4() {
		return hol_4;
	}

	public void setHol_4(String hol_4) {
		this.hol_4 = hol_4;
	}

	public String getHol_5() {
		return hol_5;
	}

	public void setHol_5(String hol_5) {
		this.hol_5 = hol_5;
	}

	public String getEntry_user() {
		return entry_user;
	}

	public void setEntry_user(String entry_user) {
		this.entry_user = entry_user;
	}

	public String getModify_user() {
		return modify_user;
	}

	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}

	public String getAuth_user() {
		return auth_user;
	}

	public void setAuth_user(String auth_user) {
		this.auth_user = auth_user;
	}

	public Date getEntry_time() {
		return entry_time;
	}

	public void setEntry_time(Date entry_time) {
		this.entry_time = entry_time;
	}

	public Date getModify_time() {
		return modify_time;
	}

	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}

	public Date getAuth_time() {
		return auth_time;
	}

	public void setAuth_time(Date auth_time) {
		this.auth_time = auth_time;
	}

	public String getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}

	public String getEntity_flag() {
		return entity_flag;
	}

	public void setEntity_flag(String entity_flag) {
		this.entity_flag = entity_flag;
	}

	public String getModify_flag() {
		return modify_flag;
	}

	public void setModify_flag(String modify_flag) {
		this.modify_flag = modify_flag;
	}

	public String getWeek_holiday_str() {
		return week_holiday_str;
	}

	public void setWeek_holiday_str(String week_holiday_str) {
		this.week_holiday_str = week_holiday_str;
	}

	public String getVerify_user() {
		return verify_user;
	}

	public void setVerify_user(String verify_user) {
		this.verify_user = verify_user;
	}

	public Date getVerify_time() {
		return verify_time;
	}

	public void setVerify_time(Date verify_time) {
		this.verify_time = verify_time;
	}

	public Date getNextduedate() {
		return nextduedate;
	}

	public void setNextduedate(Date nextduedate) {
		this.nextduedate = nextduedate;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getUpload() {
		return upload;
	}

	public void setUpload(String upload) {
		this.upload = upload;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public BTMAdminCalendarMaster(BigDecimal srl, String year, String month, String holiday_str, String month_remarks,
			String hol_1, String hol_2, String hol_3, String hol_4, String hol_5, String entry_user, String modify_user,
			String auth_user, Date entry_time, Date modify_time, Date auth_time, String del_flag, String entity_flag,
			String modify_flag, String week_holiday_str, String verify_user, Date verify_time, Date nextduedate,
			String other, String upload, String input) {
		super();
		this.srl = srl;
		this.year = year;
		this.month = month;
		this.holiday_str = holiday_str;
		this.month_remarks = month_remarks;
		this.hol_1 = hol_1;
		this.hol_2 = hol_2;
		this.hol_3 = hol_3;
		this.hol_4 = hol_4;
		this.hol_5 = hol_5;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.auth_user = auth_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.auth_time = auth_time;
		this.del_flag = del_flag;
		this.entity_flag = entity_flag;
		this.modify_flag = modify_flag;
		this.week_holiday_str = week_holiday_str;
		this.verify_user = verify_user;
		this.verify_time = verify_time;
		this.nextduedate = nextduedate;
		this.other = other;
		this.upload = upload;
		this.input = input;
	}

	public BTMAdminCalendarMaster() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "BTMAdminCalendarMaster [year=" + year + ", month=" + month + "]";
	}
	

}
