package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "TravelClaim")
public class TravelClaim {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "employeeid")
    private String employeeId;

    @Column(name = "role")
    private String role;

    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Column(name = "town_city")
    private String townCity;

    @Column(name = "fromloc")
    private String fromLoc;

    @Column(name = "toloc")
    private String toLoc;

    @Column(name = "market_type")
    private String marketType;

    @Column(name = "allowance")
    private BigDecimal allowance;

    @Column(name = "travelmode")
    private String travelMode;

    @Column(name = "busfare")
    private BigDecimal busFare;

    @Column(name = "total_km")
    private BigDecimal totalKm;

    @Column(name = "petrolefare")
    private BigDecimal petrolFare;

    @Column(name = "stayallowance")
    private BigDecimal stayAllowance;

    @Column(name = "stationery_xerox")
    private BigDecimal stationeryXerox;

    @Column(name = "other_expense")
    private BigDecimal otherExpense;

    @Column(name = "grand_total")
    private BigDecimal grandTotal;
    
    @Column(name = "modify_flg")
    private String modify_flg;
    
    @Column(name = "del_flg")
    private String del_flg;
    
    @Column(name = "entry_user")
    private String entry_user;
    
    @Column(name = "modify_user")
    private String modify_user;
    
    @Column(name = "verify_user")
    private String verify_user;
    
    @Column(name = "verify_flg")
    private String verify_flg;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTownCity() {
		return townCity;
	}

	public void setTownCity(String townCity) {
		this.townCity = townCity;
	}

	public String getFromLoc() {
		return fromLoc;
	}

	public void setFromLoc(String fromLoc) {
		this.fromLoc = fromLoc;
	}

	public String getToLoc() {
		return toLoc;
	}

	public void setToLoc(String toLoc) {
		this.toLoc = toLoc;
	}

	public String getMarketType() {
		return marketType;
	}

	public void setMarketType(String marketType) {
		this.marketType = marketType;
	}

	public BigDecimal getAllowance() {
		return allowance;
	}

	public void setAllowance(BigDecimal allowance) {
		this.allowance = allowance;
	}

	public String getTravelMode() {
		return travelMode;
	}

	public void setTravelMode(String travelMode) {
		this.travelMode = travelMode;
	}

	public BigDecimal getBusFare() {
		return busFare;
	}

	public void setBusFare(BigDecimal busFare) {
		this.busFare = busFare;
	}

	public BigDecimal getTotalKm() {
		return totalKm;
	}

	public void setTotalKm(BigDecimal totalKm) {
		this.totalKm = totalKm;
	}

	public BigDecimal getPetrolFare() {
		return petrolFare;
	}

	public void setPetrolFare(BigDecimal petrolFare) {
		this.petrolFare = petrolFare;
	}

	public BigDecimal getStayAllowance() {
		return stayAllowance;
	}

	public void setStayAllowance(BigDecimal stayAllowance) {
		this.stayAllowance = stayAllowance;
	}

	public BigDecimal getStationeryXerox() {
		return stationeryXerox;
	}

	public void setStationeryXerox(BigDecimal stationeryXerox) {
		this.stationeryXerox = stationeryXerox;
	}

	public BigDecimal getOtherExpense() {
		return otherExpense;
	}

	public void setOtherExpense(BigDecimal otherExpense) {
		this.otherExpense = otherExpense;
	}

	public BigDecimal getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
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

	public String getVerify_user() {
		return verify_user;
	}

	public void setVerify_user(String verify_user) {
		this.verify_user = verify_user;
	}

	public String getVerify_flg() {
		return verify_flg;
	}

	public void setVerify_flg(String verify_flg) {
		this.verify_flg = verify_flg;
	}

	public TravelClaim(String id, String employeeId, String role, Date date, String townCity, String fromLoc,
			String toLoc, String marketType, BigDecimal allowance, String travelMode, BigDecimal busFare,
			BigDecimal totalKm, BigDecimal petrolFare, BigDecimal stayAllowance, BigDecimal stationeryXerox,
			BigDecimal otherExpense, BigDecimal grandTotal, String modify_flg, String del_flg, String entry_user,
			String modify_user, String verify_user, String verify_flg) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.role = role;
		this.date = date;
		this.townCity = townCity;
		this.fromLoc = fromLoc;
		this.toLoc = toLoc;
		this.marketType = marketType;
		this.allowance = allowance;
		this.travelMode = travelMode;
		this.busFare = busFare;
		this.totalKm = totalKm;
		this.petrolFare = petrolFare;
		this.stayAllowance = stayAllowance;
		this.stationeryXerox = stationeryXerox;
		this.otherExpense = otherExpense;
		this.grandTotal = grandTotal;
		this.modify_flg = modify_flg;
		this.del_flg = del_flg;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.verify_user = verify_user;
		this.verify_flg = verify_flg;
	}

	public TravelClaim() {
		super();
		// TODO Auto-generated constructor stub
	}

	
    
   
    
    
}
