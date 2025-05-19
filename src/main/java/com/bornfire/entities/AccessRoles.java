package com.bornfire.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="BTM_ACS_RLS")
public class AccessRoles {
	

	@Id
	private String	role_id;
	private String	role_desc;
	private String	permissions;
	private String	work_class;
	private String	domain_id;
	private String	admin;
	private String	entity_flg;
	private String	auth_flg;
	private String	modify_flg;
	private String	del_flg;
	private String	menulist;
	private String	entry_user;
	private String	modify_user;
	private String	auth_user;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	entry_time;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	
	private Date	modify_time;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	auth_time;
	private String audit_operations;
	private String ips_parameter;
	private String ips_operations;
	private String monitoring;
	private String myt_registration;
	private String wallet_master;
	private String consent_registration;
	private String merchant_registration;
	private String new_role_flg;
	private String remarks;

	private String master;
	private String orders;
	private String production_cycle;
	private String inventory;
	private String hrms;
	private String reports;
	private String transaction_master;


	
	
	public AccessRoles(String role_id, String role_desc, String permissions, String work_class, String domain_id,
			String admin, String entity_flg, String auth_flg, String modify_flg, String del_flg, String menulist,
			String entry_user, String modify_user, String auth_user, Date entry_time, Date modify_time, Date auth_time,
			String audit_operations, String ips_parameter, String ips_operations, String monitoring,
			String myt_registration, String wallet_master, String consent_registration, String merchant_registration,
			String new_role_flg, String remarks, String master, String orders, String production_cycle,
			String inventory, String hrms, String reports, String transaction_master) {
		super();
		this.role_id = role_id;
		this.role_desc = role_desc;
		this.permissions = permissions;
		this.work_class = work_class;
		this.domain_id = domain_id;
		this.admin = admin;
		this.entity_flg = entity_flg;
		this.auth_flg = auth_flg;
		this.modify_flg = modify_flg;
		this.del_flg = del_flg;
		this.menulist = menulist;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.auth_user = auth_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.auth_time = auth_time;
		this.audit_operations = audit_operations;
		this.ips_parameter = ips_parameter;
		this.ips_operations = ips_operations;
		this.monitoring = monitoring;
		this.myt_registration = myt_registration;
		this.wallet_master = wallet_master;
		this.consent_registration = consent_registration;
		this.merchant_registration = merchant_registration;
		this.new_role_flg = new_role_flg;
		this.remarks = remarks;
		this.master = master;
		this.orders = orders;
		this.production_cycle = production_cycle;
		this.inventory = inventory;
		this.hrms = hrms;
		this.reports = reports;
		this.transaction_master = transaction_master;
	}




	public String getRole_id() {
		return role_id;
	}




	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}




	public String getRole_desc() {
		return role_desc;
	}




	public void setRole_desc(String role_desc) {
		this.role_desc = role_desc;
	}




	public String getPermissions() {
		return permissions;
	}




	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}




	public String getWork_class() {
		return work_class;
	}




	public void setWork_class(String work_class) {
		this.work_class = work_class;
	}




	public String getDomain_id() {
		return domain_id;
	}




	public void setDomain_id(String domain_id) {
		this.domain_id = domain_id;
	}




	public String getAdmin() {
		return admin;
	}




	public void setAdmin(String admin) {
		this.admin = admin;
	}




	public String getEntity_flg() {
		return entity_flg;
	}




	public void setEntity_flg(String entity_flg) {
		this.entity_flg = entity_flg;
	}




	public String getAuth_flg() {
		return auth_flg;
	}




	public void setAuth_flg(String auth_flg) {
		this.auth_flg = auth_flg;
	}




	public String getModify_flg() {
		return modify_flg;
	}




	public void setModify_flg(String modify_flg) {
		this.modify_flg = modify_flg;
	}




	public String getDel_flg() {
		return del_flg;
	}




	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}




	public String getMenulist() {
		return menulist;
	}




	public void setMenulist(String menulist) {
		this.menulist = menulist;
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




	public String getAudit_operations() {
		return audit_operations;
	}




	public void setAudit_operations(String audit_operations) {
		this.audit_operations = audit_operations;
	}




	public String getIps_parameter() {
		return ips_parameter;
	}




	public void setIps_parameter(String ips_parameter) {
		this.ips_parameter = ips_parameter;
	}




	public String getIps_operations() {
		return ips_operations;
	}




	public void setIps_operations(String ips_operations) {
		this.ips_operations = ips_operations;
	}




	public String getMonitoring() {
		return monitoring;
	}




	public void setMonitoring(String monitoring) {
		this.monitoring = monitoring;
	}




	public String getMyt_registration() {
		return myt_registration;
	}




	public void setMyt_registration(String myt_registration) {
		this.myt_registration = myt_registration;
	}




	public String getWallet_master() {
		return wallet_master;
	}




	public void setWallet_master(String wallet_master) {
		this.wallet_master = wallet_master;
	}




	public String getConsent_registration() {
		return consent_registration;
	}




	public void setConsent_registration(String consent_registration) {
		this.consent_registration = consent_registration;
	}




	public String getMerchant_registration() {
		return merchant_registration;
	}




	public void setMerchant_registration(String merchant_registration) {
		this.merchant_registration = merchant_registration;
	}




	public String getNew_role_flg() {
		return new_role_flg;
	}




	public void setNew_role_flg(String new_role_flg) {
		this.new_role_flg = new_role_flg;
	}




	public String getRemarks() {
		return remarks;
	}




	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}




	public String getMaster() {
		return master;
	}




	public void setMaster(String master) {
		this.master = master;
	}




	public String getOrders() {
		return orders;
	}




	public void setOrders(String orders) {
		this.orders = orders;
	}




	public String getProduction_cycle() {
		return production_cycle;
	}




	public void setProduction_cycle(String production_cycle) {
		this.production_cycle = production_cycle;
	}




	public String getInventory() {
		return inventory;
	}




	public void setInventory(String inventory) {
		this.inventory = inventory;
	}




	public String getHrms() {
		return hrms;
	}




	public void setHrms(String hrms) {
		this.hrms = hrms;
	}




	public String getReports() {
		return reports;
	}




	public void setReports(String reports) {
		this.reports = reports;
	}




	public String getTransaction_master() {
		return transaction_master;
	}




	public void setTransaction_master(String transaction_master) {
		this.transaction_master = transaction_master;
	}




	public AccessRoles() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}