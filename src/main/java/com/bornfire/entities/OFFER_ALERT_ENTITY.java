
package com.bornfire.entities;

import javax.persistence.*;

@Entity
@Table(name = "OFFER_ALERT")
public class OFFER_ALERT_ENTITY {

	  @Id
	    @Column(name = "ID")
	    private String id;

    @Column(name = "VENDOR")
    private String vendor;
  
    @Column(name = "BRANCH_ID")
    private String branchId;


	@Column(name = "ALERT")
    private String alert;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "DEL_FLG")
    private String delFlg;
    
    @Lob
    @Column(name = "PICTURE")
    private byte[] file;
    
    
	@Column(name = "ORG_ID", length = 100)
    private String org_id;
    
    public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}


    
    public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
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


 // ✅ No-arg constructor for Hibernate
    public OFFER_ALERT_ENTITY() {
    }
	public OFFER_ALERT_ENTITY(String vendor, String id, String branchId, String alert, String email, String password,
			String delFlg,byte[] file,String org_id) {
		super();
		this.vendor = vendor;
		this.id = id;
		this.branchId = branchId;
		this.alert = alert;
		this.email = email;
		this.password = password;
		this.delFlg = delFlg;
		this.file=file;
		this.org_id=org_id;
	}

}
