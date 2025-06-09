package com.bornfire.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BDOC_ACCESS_ROLE_MOD_TABLE")
public class BDOCAccessRoleModTable {

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
	private Date	entry_time;
	private Date	modify_time;
	private Date	auth_time;
	private String	new_role_flg;
	private String	remarks;
	private String	access_role;
	private String	user_profile_maintenance;
	private String	organization_master;

	
	
	
	
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





	public String getAccess_role() {
		return access_role;
	}





	public void setAccess_role(String access_role) {
		this.access_role = access_role;
	}





	public String getUser_profile_maintenance() {
		return user_profile_maintenance;
	}





	public void setUser_profile_maintenance(String user_profile_maintenance) {
		this.user_profile_maintenance = user_profile_maintenance;
	}





	public String getOrganization_master() {
		return organization_master;
	}





	public void setOrganization_master(String organization_master) {
		this.organization_master = organization_master;
	}





	public BDOCAccessRoleModTable(String role_id, String role_desc, String permissions, String work_class,
			String domain_id, String admin, String entity_flg, String auth_flg, String modify_flg, String del_flg,
			String menulist, String entry_user, String modify_user, String auth_user, Date entry_time, Date modify_time,
			Date auth_time, String new_role_flg, String remarks, String access_role, String user_profile_maintenance,
			String organization_master) {
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
		this.new_role_flg = new_role_flg;
		this.remarks = remarks;
		this.access_role = access_role;
		this.user_profile_maintenance = user_profile_maintenance;
		this.organization_master = organization_master;
	}





	public BDOCAccessRoleModTable(BDOCAccessRole ipsAccessRole) {
		this.role_id = ipsAccessRole.getRole_id();
		this.role_desc = ipsAccessRole.getRole_desc();
		this.permissions = ipsAccessRole.getPermissions();
		this.work_class = ipsAccessRole.getWork_class();
		this.domain_id = ipsAccessRole.getDomain_id();
		this.admin = ipsAccessRole.getAdmin();
		this.entity_flg = ipsAccessRole.getEntity_flg();
		this.auth_flg = ipsAccessRole.getAuth_flg();
		this.modify_flg = ipsAccessRole.getModify_flg();
		this.del_flg = ipsAccessRole.getDel_flg();
		this.menulist = ipsAccessRole.getMenulist();
		this.entry_user = ipsAccessRole.getEntry_user();
		this.modify_user = ipsAccessRole.getModify_user();
		this.auth_user = ipsAccessRole.getAuth_user();
		this.entry_time = ipsAccessRole.getEntry_time();
		this.modify_time = ipsAccessRole.getModify_time();
		this.auth_time = ipsAccessRole.getAuth_time();
		this.new_role_flg = ipsAccessRole.getNew_role_flg();
		this.remarks = ipsAccessRole.getRemarks();
		this.user_profile_maintenance = ipsAccessRole.getUser_profile_maintenance();
		this.access_role = ipsAccessRole.getAccess_role();
		this.organization_master = ipsAccessRole.getOrganization_master();
	}





	public BDOCAccessRoleModTable() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}