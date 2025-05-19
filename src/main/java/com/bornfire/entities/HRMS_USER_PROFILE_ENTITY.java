package com.bornfire.entities;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "HRMS_USER_PROFILE_TABLE_NEW")
public class HRMS_USER_PROFILE_ENTITY  implements UserDetails {

    @Id
    @Column(name = "USER_ID", length = 20)
    private String userId;

    @Column(name = "USER_NAME", length = 50)
    private String userNames;

    @Column(name = "EMPLOYEE_ID", length = 20)
    private String employeeId;

    @Column(name = "EMPLOYEE_NAME", length = 50)
    private String employeeName;

    @Column(name = "DESIGNATION", length = 20)
    private String designation;

    @Column(name = "NATURE_OF_EMPLOYMENT", length = 20)
    private String natureOfEmployment;

    @Column(name = "MAX_INACTIVE_TIME", length = 20)
    private String maxInactiveTime;

    @Column(name = "ACC_EXP_DATE")
    private LocalDateTime accExpDate;

    @Column(name = "LOGIN_LOW", length = 20)
    private String loginLow;

    @Column(name = "LOGIN_HIGH", length = 20)
    private String loginHigh;

    @Column(name = "USER_DISABLE_DATE")
    private LocalDateTime userDisableDate;

    @Column(name = "USER_DISABLE_TILL")
    private LocalDateTime userDisableTill;

    @Column(name = "PASSWORD", length = 100)
    private String password;

    @Column(name = "PASS_EXP_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date passExpDate;

    @Column(name = "USER_STATUS", length = 20)
    private String userStatus;

    @Column(name = "LOGIN_STATUS", length = 20)
    private String loginStatus;

    @Column(name = "VIRTUAL_USER_FLG", length = 20)
    private String virtualUserFlg;

    @Column(name = "WORK_CLASS", length = 20)
    private String workClass;

    @Column(name = "MOBILE_NO")
    private BigDecimal mobileNo;

    @Column(name = "EMAIL_ID", length = 50)
    private String emailId;

    @Column(name = "CATEGORY", length = 50)
    private String category;

    @Column(name = "ROLE_ID", length = 20)
    private String role;

    @Column(name = "ROLE_DESCRIPTION", length = 20)
    private String roleDescription;

    @Column(name = "PERMISSION", length = 20)
    private String permission;

    @Column(name = "EFFECTIVE_TILL")
    private LocalDateTime effectiveTill;

    @Column(name = "ADMIN", length = 20)
    private String admin;

    @Column(name = "ACCOUNTS", length = 20)
    private String accounts;

    @Column(name = "GENERAL", length = 20)
    private String general;

    @Column(name = "AUDIT_INQ", length = 20)
    private String auditInq;

    @Column(name = "DOCTOR", length = 50)
    private String doctor;

    @Column(name = "LABORATORY", length = 20)
    private String laboratory;

    @Column(name = "PHARMACEUTICALS", length = 20)
    private String pharmaceuticals;

    @Column(name = "NURSES", length = 20)
    private String nurses;

    @Column(name = "SCANNING", length = 20)
    private String scanning;

    @Column(name = "XRAY", length = 20)
    private String xray;

    @Column(name = "CHANNELS", length = 20)
    private String channels;

    @Column(name = "ENTITY_FLG", length = 1)
    private String entityFlg;

    @Column(name = "DEL_FLG", length = 1)
    private String delFlg;

    @Column(name = "ENTRY_USER", length = 20)
    private String entryUser;

    @Column(name = "ENTRY_TIME", length = 40)
    private String entryTime;

    @Column(name = "MODIFY_USER", length = 20)
    private String modifyUser;

    @Column(name = "MODIFY_TIME", length = 40)
    private String modifyTime;

    @Column(name = "AUTH_USER", length = 20)
    private String authUser;

    @Column(name = "AUTH_TIME", length = 40)
    private String authTime;

    @Column(name = "LOGIN_FLG", length = 50)
    private String loginFlg;

    @Column(name = "USER_LOCKED_FLG", length = 50)
    private String userLockedFlg;

    @Column(name = "NO_OF_ATTMP")
    private BigDecimal noOfAttmp;

    @Column(name = "DISABLE_FLG", length = 50)
    private String disableFlg;

    @Column(name = "SPECIALIST", length = 100)
    private String specialist;
    
    
    @Column(name="BRANCH_ID",length=50)
    private String branchId;
    
    
    @Column(name = "ORG_ID", length = 100)
    private String org_id;
    
    public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
    
    

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserNames() {
		return userNames;
	}

	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getNatureOfEmployment() {
		return natureOfEmployment;
	}

	public void setNatureOfEmployment(String natureOfEmployment) {
		this.natureOfEmployment = natureOfEmployment;
	}

	public String getMaxInactiveTime() {
		return maxInactiveTime;
	}

	public void setMaxInactiveTime(String maxInactiveTime) {
		this.maxInactiveTime = maxInactiveTime;
	}

	public LocalDateTime getAccExpDate() {
		return accExpDate;
	}

	public void setAccExpDate(LocalDateTime accExpDate) {
		this.accExpDate = accExpDate;
	}

	public String getLoginLow() {
		return loginLow;
	}

	public void setLoginLow(String loginLow) {
		this.loginLow = loginLow;
	}

	public String getLoginHigh() {
		return loginHigh;
	}

	public void setLoginHigh(String loginHigh) {
		this.loginHigh = loginHigh;
	}

	public LocalDateTime getUserDisableDate() {
		return userDisableDate;
	}

	public void setUserDisableDate(LocalDateTime userDisableDate) {
		this.userDisableDate = userDisableDate;
	}

	public LocalDateTime getUserDisableTill() {
		return userDisableTill;
	}

	public void setUserDisableTill(LocalDateTime userDisableTill) {
		this.userDisableTill = userDisableTill;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getPassExpDate() {
		return passExpDate;
	}

	public void setPassExpDate(Date passExpDate) {
		this.passExpDate = passExpDate;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public String getVirtualUserFlg() {
		return virtualUserFlg;
	}

	public void setVirtualUserFlg(String virtualUserFlg) {
		this.virtualUserFlg = virtualUserFlg;
	}

	public String getWorkClass() {
		return workClass;
	}

	public void setWorkClass(String workClass) {
		this.workClass = workClass;
	}

	public BigDecimal getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(BigDecimal mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public LocalDateTime getEffectiveTill() {
		return effectiveTill;
	}

	public void setEffectiveTill(LocalDateTime effectiveTill) {
		this.effectiveTill = effectiveTill;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getAccounts() {
		return accounts;
	}

	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}

	public String getGeneral() {
		return general;
	}

	public void setGeneral(String general) {
		this.general = general;
	}

	public String getAuditInq() {
		return auditInq;
	}

	public void setAuditInq(String auditInq) {
		this.auditInq = auditInq;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public String getLaboratory() {
		return laboratory;
	}

	public void setLaboratory(String laboratory) {
		this.laboratory = laboratory;
	}

	public String getPharmaceuticals() {
		return pharmaceuticals;
	}

	public void setPharmaceuticals(String pharmaceuticals) {
		this.pharmaceuticals = pharmaceuticals;
	}

	public String getNurses() {
		return nurses;
	}

	public void setNurses(String nurses) {
		this.nurses = nurses;
	}

	public String getScanning() {
		return scanning;
	}

	public void setScanning(String scanning) {
		this.scanning = scanning;
	}

	public String getXray() {
		return xray;
	}

	public void setXray(String xray) {
		this.xray = xray;
	}

	public String getChannels() {
		return channels;
	}

	public void setChannels(String channels) {
		this.channels = channels;
	}

	public String getEntityFlg() {
		return entityFlg;
	}

	public void setEntityFlg(String entityFlg) {
		this.entityFlg = entityFlg;
	}

	public String getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
	}

	public String getEntryUser() {
		return entryUser;
	}

	public void setEntryUser(String entryUser) {
		this.entryUser = entryUser;
	}

	public String getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getAuthUser() {
		return authUser;
	}

	public void setAuthUser(String authUser) {
		this.authUser = authUser;
	}

	public String getAuthTime() {
		return authTime;
	}

	public void setAuthTime(String authTime) {
		this.authTime = authTime;
	}

	public String getLoginFlg() {
		return loginFlg;
	}

	public void setLoginFlg(String loginFlg) {
		this.loginFlg = loginFlg;
	}

	public String getUserLockedFlg() {
		return userLockedFlg;
	}

	public void setUserLockedFlg(String userLockedFlg) {
		this.userLockedFlg = userLockedFlg;
	}

	public BigDecimal getNoOfAttmp() {
		return noOfAttmp;
	}

	public void setNoOfAttmp(BigDecimal noOfAttmp) {
		this.noOfAttmp = noOfAttmp;
	}

	public String getDisableFlg() {
		return disableFlg;
	}

	public void setDisableFlg(String disableFlg) {
		this.disableFlg = disableFlg;
	}

	public String getSpecialist() {
		return specialist;
	}

	public void setSpecialist(String specialist) {
		this.specialist = specialist;
	}

	public HRMS_USER_PROFILE_ENTITY(String userId, String userName, String employeeId, String employeeName,
			String designation, String natureOfEmployment, String maxInactiveTime, LocalDateTime accExpDate,
			String loginLow, String loginHigh, LocalDateTime userDisableDate, LocalDateTime userDisableTill,
			String password, Date passExpDate, String userStatus, String loginStatus, String virtualUserFlg,
			String workClass, BigDecimal mobileNo, String emailId, String category, String roleId,
			String roleDescription, String permission, LocalDateTime effectiveTill, String admin, String accounts,
			String general, String auditInq, String doctor, String laboratory, String pharmaceuticals, String nurses,
			String scanning, String xray, String channels, String entityFlg, String delFlg, String entryUser,
			String entryTime, String modifyUser, String modifyTime, String authUser, String authTime, String loginFlg,
			String userLockedFlg, BigDecimal noOfAttmp, String disableFlg, String specialist,String org_id) {
		super();
		this.userId = userId;
		this.userNames = userName;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.designation = designation;
		this.natureOfEmployment = natureOfEmployment;
		this.maxInactiveTime = maxInactiveTime;
		this.accExpDate = accExpDate;
		this.loginLow = loginLow;
		this.loginHigh = loginHigh;
		this.userDisableDate = userDisableDate;
		this.userDisableTill = userDisableTill;
		this.password = password;
		this.passExpDate = passExpDate;
		this.userStatus = userStatus;
		this.loginStatus = loginStatus;
		this.virtualUserFlg = virtualUserFlg;
		this.workClass = workClass;
		this.mobileNo = mobileNo;
		this.emailId = emailId;
		this.category = category;
		this.role = roleId;
		this.roleDescription = roleDescription;
		this.permission = permission;
		this.effectiveTill = effectiveTill;
		this.admin = admin;
		this.accounts = accounts;
		this.general = general;
		this.auditInq = auditInq;
		this.doctor = doctor;
		this.laboratory = laboratory;
		this.pharmaceuticals = pharmaceuticals;
		this.nurses = nurses;
		this.scanning = scanning;
		this.xray = xray;
		this.channels = channels;
		this.entityFlg = entityFlg;
		this.delFlg = delFlg;
		this.entryUser = entryUser;
		this.entryTime = entryTime;
		this.modifyUser = modifyUser;
		this.modifyTime = modifyTime;
		this.authUser = authUser;
		this.authTime = authTime;
		this.loginFlg = loginFlg;
		this.userLockedFlg = userLockedFlg;
		this.noOfAttmp = noOfAttmp;
		this.disableFlg = disableFlg;
		this.specialist = specialist;
		this.org_id=org_id;
	}
	
	
	public HRMS_USER_PROFILE_ENTITY(String branchId) {
		super();
		this.branchId = branchId;
	}


	public HRMS_USER_PROFILE_ENTITY() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		if (this.getPassExpDate().after(new Date())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		if (this.getLoginFlg().equals("Y")) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		if (this.getPassExpDate().after(new Date())) {
			return true;
		} else {
			return false;
		}
	}
	@JsonIgnore
	public boolean isPasswordExpired() throws ParseException {
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat formatdate = new SimpleDateFormat("MM/dd/yyyy");
	String date2=formatdate.format(cal.getTime());
	
		// Convert `String` to `Date`
		    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
		    Date dateBefore = sdf.parse(date2);
		    Date dateAfter = this.getPassExpDate();
		// Calculate the number of days between dates
		    long timeDiff = Math.abs(dateAfter.getTime() - dateBefore.getTime());
		    long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
		    System.out.println("The number of days between dates: " + daysDiff);
		    if (daysDiff==0) {
		    	return true;
		    }else{
				return false;
			}
		    	
	}
	/*
	 * @Override
	 * 
	 * @JsonIgnore public boolean isEnabled() {
	 * 
	 * Date currDate = new Date();
	 * 
	 * try { if ((currDate.after(this.getDis_start_date()) &&
	 * currDate.before(this.getDis_end_date())) || this.entity_flg.equals("N")){
	 * return false; } else {
	 * 
	 * return true; } } catch (NullPointerException e) {
	 * 
	 * return true; }
	 * 
	 * }
	 */

	/*
	 * public boolean isLoginAllowed() {
	 * 
	 * DateFormat dateFormat = new SimpleDateFormat("hh:mm");
	 * 
	 * try { Date loginHigh = dateFormat.parse(this.login_high); Date loginLow =
	 * dateFormat.parse(this.login_low);
	 * 
	 * LocalTime high = LocalDateTime.ofInstant(loginHigh.toInstant(),
	 * ZoneId.systemDefault()).toLocalTime(); LocalTime low =
	 * LocalDateTime.ofInstant(loginLow.toInstant(),
	 * ZoneId.systemDefault()).toLocalTime(); LocalTime currTime =
	 * java.time.LocalTime.now();
	 * 
	 * if (currTime.isAfter(low) && currTime.isBefore(high) &&
	 * entity_flg.equals("Y")) { return true; } else { return true; }
	 * 
	 * } catch (ParseException e) {
	 * 
	 * e.printStackTrace(); }catch(NullPointerException e) {
	 * 
	 * return true; }
	 * 
	 * return false; }
	 */
	
	
public boolean isLoginAllowed() {
    // Use the appropriate time format (24-hour format is recommended)
    DateFormat dateFormat = new SimpleDateFormat("HH:mm");

    try {
        // Handle cases where login_high or login_low might be empty or null
        if (this.loginHigh == null || this.loginLow == null || this.loginHigh.isEmpty() || this.loginLow.isEmpty()) {
            return true; // Allow login in case of null or empty values
        }

        // Parse the high and low login times
        Date loginHigh = dateFormat.parse(this.loginHigh);
        Date loginLow = dateFormat.parse(this.loginLow);

        // Convert them to LocalTime
        LocalTime high = LocalDateTime.ofInstant(loginHigh.toInstant(), ZoneId.systemDefault()).toLocalTime();
        LocalTime low = LocalDateTime.ofInstant(loginLow.toInstant(), ZoneId.systemDefault()).toLocalTime();
        LocalTime currTime = java.time.LocalTime.now(); // Get current time

        // Check if current time is between login_low and login_high, and entity_flg is "Y"
        if (currTime.isAfter(low) && currTime.isBefore(high) && "Y".equals(entityFlg)) {
            return true; // Login allowed
        } else {
            return false; // Login not allowed
        }

    } catch (ParseException e) {
        e.printStackTrace();
        return false; // Parsing error: treat as not allowed
    } catch (NullPointerException e) {
        return true; // Allow login if null values are encountered
    }
}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	

 
}
