package com.bornfire.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "allowancemaker" )
public class AllowanceMaker {

    @Id
    @Column(name = "allowancemakerid")
    private String allowancemakerId;
    
   
    @Column(name = "designation", length = 100)
    private String designation;

    @Column(name = "localmarketallowance")
    private BigDecimal localMarketAllowance;

    @Column(name = "exmarketallowance")
    private BigDecimal exMarketAllowance;

    @Column(name = "upcountryallowance")
    private BigDecimal upcountryAllowance;

    @Column(name = "kmconstrain")
    private BigDecimal kmConstrain;

    @Column(name = "petrolallowance")
    private BigDecimal petrolAllowance;

    @Column(name = "entryuser")
    private String entryUser;

    @Column(name = "modifyuser")
    private String modifyUser;

    @Column(name = "del_flg")
    private String delFlg;

    // Getters and Setters

    public String getAllowancemakerId() {
        return allowancemakerId;
    }

    public void setAllowancemakerId(String allowancemakerId) {
        this.allowancemakerId = allowancemakerId;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public BigDecimal getLocalMarketAllowance() {
        return localMarketAllowance;
    }

    public void setLocalMarketAllowance(BigDecimal localMarketAllowance) {
        this.localMarketAllowance = localMarketAllowance;
    }

    public BigDecimal getExMarketAllowance() {
        return exMarketAllowance;
    }

    public void setExMarketAllowance(BigDecimal exMarketAllowance) {
        this.exMarketAllowance = exMarketAllowance;
    }

    public BigDecimal getUpcountryAllowance() {
        return upcountryAllowance;
    }

    public void setUpcountryAllowance(BigDecimal upcountryAllowance) {
        this.upcountryAllowance = upcountryAllowance;
    }

    public BigDecimal getKmConstrain() {
        return kmConstrain;
    }

    public void setKmConstrain(BigDecimal kmConstrain) {
        this.kmConstrain = kmConstrain;
    }

    public BigDecimal getPetrolAllowance() {
        return petrolAllowance;
    }

    public void setPetrolAllowance(BigDecimal petrolAllowance) {
        this.petrolAllowance = petrolAllowance;
    }

    public String getEntryUser() {
        return entryUser;
    }

    public void setEntryUser(String entryUser) {
        this.entryUser = entryUser;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public String getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg;
    }

	public AllowanceMaker(String allowancemakerId, String designation, BigDecimal localMarketAllowance,
			BigDecimal exMarketAllowance, BigDecimal upcountryAllowance, BigDecimal kmConstrain,
			BigDecimal petrolAllowance, String entryUser, String modifyUser, String delFlg) {
		super();
		this.allowancemakerId = allowancemakerId;
		this.designation = designation;
		this.localMarketAllowance = localMarketAllowance;
		this.exMarketAllowance = exMarketAllowance;
		this.upcountryAllowance = upcountryAllowance;
		this.kmConstrain = kmConstrain;
		this.petrolAllowance = petrolAllowance;
		this.entryUser = entryUser;
		this.modifyUser = modifyUser;
		this.delFlg = delFlg;
	}

	public AllowanceMaker() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}