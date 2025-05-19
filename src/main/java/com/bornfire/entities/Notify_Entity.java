package com.bornfire.entities;

import javax.persistence.*;

@Entity
@Table(name = "NOTIFICATION_MASTER")
public class Notify_Entity {

	  @Id
	    @Column(name = "ID")
	    private String id;

    @Column(name = "PROCESS")
    private String process;
  
    @Column(name = "BRANCH_ID")
    private String branchId;
    
    @Column(name = "ORG_ID", length = 100)
    private String org_id;
    
    public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

    
    
    
    public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
	}

	@Column(name = "ALERT")
    private String alert;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "DEL_FLG")
    private String delFlg;

 // ✅ No-arg constructor for Hibernate
    public Notify_Entity() {
    }
	public Notify_Entity(String process, String id, String branchId, String alert, String email, String password,
			String delFlg,String org_id) {
		super();
		this.process = process;
		this.id = id;
		this.branchId = branchId;
		this.alert = alert;
		this.email = email;
		this.password = password;
		this.delFlg = delFlg;
		this.org_id=org_id;
	}

}
