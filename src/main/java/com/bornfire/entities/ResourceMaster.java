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

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "RESOURCE_MASTER")
public class ResourceMaster implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String	organisation;
	private String	category;
	@Id
	private String	resource_id;
	private String	resource_name;
	private String	employee_ref;
	private String	employee_id;
	private Date	doj;
	private Date	dob;
	private String	groups;
	private String	team;
	private String	report_mgr_id;
	private String	report_mgr_name;
	private String	design;
	private String	role;
	private String	qual;
	private String	addl_qual;
	private String	skill_set;
	private String	expertise;
	private String	pancard;
	private String	aadhar;
	private String	passport;
	private String	driving_license;
	private String	gender;
	private String	blood_group;
	private String	marital_status;
	private BigDecimal	dependants;
	private String	mobile;
	private String	alt_mobile;
	private String	access_id;
	private String	email;
	private String	addr1;
	private String	addr2;
	private String	city;
	private String	state;
	private String	country;
	private String	postal_code;
	private String	loc_addr1;
	private String	loc_addr2;
	private String	loc_city;
	private String	loc_state;
	private String	loc_country;
	private String	loc_postal_code;
	private String	user_cont_person;
	private BigDecimal	cont_person_no;
	private String	password;
	private BigDecimal	life_of_pw;
	private Date	acct_expy_date;
	private Date	pw_expy_date;
	private String	user_remarks;
	private String	disable_flg;
	private Date	dis_start_date;
	private Date	dis_end_date;
	private String	login_low;
	private String	login_high;
	private String	entry_user;
	private String	modify_user;
	private String	verify_user;
	private Date	entry_time;
	private Date	modify_time;
	private Date	verify_time;
	private String	del_flg;
	private String	entity_flg;
	private String	modify_flg;
	private String	first_name;
	private String	middle_name;
	private String	last_name;
	private String	short_name;
	private String	virtual_flg;
	private String	login_status;
	private String	channel_id;
	private String	locked_flg;
	private String	session_id;
	private String	imei;
	private Date	dor;
	private String	ifsc;
	private String	bank;
	private String	bank_act_no;
	public String getOrganisation() {
		return organisation;
	}
	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getResource_id() {
		return resource_id;
	}
	public void setResource_id(String resource_id) {
		this.resource_id = resource_id;
	}
	public String getResource_name() {
		return resource_name;
	}
	public void setResource_name(String resource_name) {
		this.resource_name = resource_name;
	}
	public String getEmployee_ref() {
		return employee_ref;
	}
	public void setEmployee_ref(String employee_ref) {
		this.employee_ref = employee_ref;
	}
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getGroups() {
		return groups;
	}
	public void setGroups(String groups) {
		this.groups = groups;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getReport_mgr_id() {
		return report_mgr_id;
	}
	public void setReport_mgr_id(String report_mgr_id) {
		this.report_mgr_id = report_mgr_id;
	}
	public String getReport_mgr_name() {
		return report_mgr_name;
	}
	public void setReport_mgr_name(String report_mgr_name) {
		this.report_mgr_name = report_mgr_name;
	}
	public String getDesign() {
		return design;
	}
	public void setDesign(String design) {
		this.design = design;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getQual() {
		return qual;
	}
	public void setQual(String qual) {
		this.qual = qual;
	}
	public String getAddl_qual() {
		return addl_qual;
	}
	public void setAddl_qual(String addl_qual) {
		this.addl_qual = addl_qual;
	}
	public String getSkill_set() {
		return skill_set;
	}
	public void setSkill_set(String skill_set) {
		this.skill_set = skill_set;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public String getPancard() {
		return pancard;
	}
	public void setPancard(String pancard) {
		this.pancard = pancard;
	}
	public String getAadhar() {
		return aadhar;
	}
	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}
	public String getPassport() {
		return passport;
	}
	public void setPassport(String passport) {
		this.passport = passport;
	}
	public String getDriving_license() {
		return driving_license;
	}
	public void setDriving_license(String driving_license) {
		this.driving_license = driving_license;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBlood_group() {
		return blood_group;
	}
	public void setBlood_group(String blood_group) {
		this.blood_group = blood_group;
	}
	public String getMarital_status() {
		return marital_status;
	}
	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}
	public BigDecimal getDependants() {
		return dependants;
	}
	public void setDependants(BigDecimal dependants) {
		this.dependants = dependants;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAlt_mobile() {
		return alt_mobile;
	}
	public void setAlt_mobile(String alt_mobile) {
		this.alt_mobile = alt_mobile;
	}
	public String getAccess_id() {
		return access_id;
	}
	public void setAccess_id(String access_id) {
		this.access_id = access_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPostal_code() {
		return postal_code;
	}
	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}
	public String getLoc_addr1() {
		return loc_addr1;
	}
	public void setLoc_addr1(String loc_addr1) {
		this.loc_addr1 = loc_addr1;
	}
	public String getLoc_addr2() {
		return loc_addr2;
	}
	public void setLoc_addr2(String loc_addr2) {
		this.loc_addr2 = loc_addr2;
	}
	public String getLoc_city() {
		return loc_city;
	}
	public void setLoc_city(String loc_city) {
		this.loc_city = loc_city;
	}
	public String getLoc_state() {
		return loc_state;
	}
	public void setLoc_state(String loc_state) {
		this.loc_state = loc_state;
	}
	public String getLoc_country() {
		return loc_country;
	}
	public void setLoc_country(String loc_country) {
		this.loc_country = loc_country;
	}
	public String getLoc_postal_code() {
		return loc_postal_code;
	}
	public void setLoc_postal_code(String loc_postal_code) {
		this.loc_postal_code = loc_postal_code;
	}
	public String getUser_cont_person() {
		return user_cont_person;
	}
	public void setUser_cont_person(String user_cont_person) {
		this.user_cont_person = user_cont_person;
	}
	public BigDecimal getCont_person_no() {
		return cont_person_no;
	}
	public void setCont_person_no(BigDecimal cont_person_no) {
		this.cont_person_no = cont_person_no;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public BigDecimal getLife_of_pw() {
		return life_of_pw;
	}
	public void setLife_of_pw(BigDecimal life_of_pw) {
		this.life_of_pw = life_of_pw;
	}
	public Date getAcct_expy_date() {
		return acct_expy_date;
	}
	public void setAcct_expy_date(Date acct_expy_date) {
		this.acct_expy_date = acct_expy_date;
	}
	public Date getPw_expy_date() {
		return pw_expy_date;
	}
	public void setPw_expy_date(Date pw_expy_date) {
		this.pw_expy_date = pw_expy_date;
	}
	public String getUser_remarks() {
		return user_remarks;
	}
	public void setUser_remarks(String user_remarks) {
		this.user_remarks = user_remarks;
	}
	public String getDisable_flg() {
		return disable_flg;
	}
	public void setDisable_flg(String disable_flg) {
		this.disable_flg = disable_flg;
	}
	public Date getDis_start_date() {
		return dis_start_date;
	}
	public void setDis_start_date(Date dis_start_date) {
		this.dis_start_date = dis_start_date;
	}
	public Date getDis_end_date() {
		return dis_end_date;
	}
	public void setDis_end_date(Date dis_end_date) {
		this.dis_end_date = dis_end_date;
	}
	public String getLogin_low() {
		return login_low;
	}
	public void setLogin_low(String login_low) {
		this.login_low = login_low;
	}
	public String getLogin_high() {
		return login_high;
	}
	public void setLogin_high(String login_high) {
		this.login_high = login_high;
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
	public Date getVerify_time() {
		return verify_time;
	}
	public void setVerify_time(Date verify_time) {
		this.verify_time = verify_time;
	}
	public String getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}
	public String getEntity_flg() {
		return entity_flg;
	}
	public void setEntity_flg(String entity_flg) {
		this.entity_flg = entity_flg;
	}
	public String getModify_flg() {
		return modify_flg;
	}
	public void setModify_flg(String modify_flg) {
		this.modify_flg = modify_flg;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getMiddle_name() {
		return middle_name;
	}
	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getShort_name() {
		return short_name;
	}
	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}
	public String getVirtual_flg() {
		return virtual_flg;
	}
	public void setVirtual_flg(String virtual_flg) {
		this.virtual_flg = virtual_flg;
	}
	public String getLogin_status() {
		return login_status;
	}
	public void setLogin_status(String login_status) {
		this.login_status = login_status;
	}
	public String getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}
	public String getLocked_flg() {
		return locked_flg;
	}
	public void setLocked_flg(String locked_flg) {
		this.locked_flg = locked_flg;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public Date getDor() {
		return dor;
	}
	public void setDor(Date dor) {
		this.dor = dor;
	}
	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBank_act_no() {
		return bank_act_no;
	}
	public void setBank_act_no(String bank_act_no) {
		this.bank_act_no = bank_act_no;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		if (this.getAcct_expy_date().after(new Date())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		if (this.getLocked_flg().equals("Y")) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		if (this.getPw_expy_date().after(new Date())) {
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
		    Date dateAfter = this.getPw_expy_date();
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
	@Override
	@JsonIgnore
	public boolean isEnabled() {

		Date currDate = new Date();

		try {
			if ((currDate.after(this.getDis_start_date()) && currDate.before(this.getDis_end_date()))
					|| this.entity_flg.equals("N")){
				return false;
			}
				else {
			
				return true;
			}
		} catch (NullPointerException e) {
			
			return true;
		}
		
	}

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
        if (this.login_high == null || this.login_low == null || this.login_high.isEmpty() || this.login_low.isEmpty()) {
            return true; // Allow login in case of null or empty values
        }

        // Parse the high and low login times
        Date loginHigh = dateFormat.parse(this.login_high);
        Date loginLow = dateFormat.parse(this.login_low);

        // Convert them to LocalTime
        LocalTime high = LocalDateTime.ofInstant(loginHigh.toInstant(), ZoneId.systemDefault()).toLocalTime();
        LocalTime low = LocalDateTime.ofInstant(loginLow.toInstant(), ZoneId.systemDefault()).toLocalTime();
        LocalTime currTime = java.time.LocalTime.now(); // Get current time

        // Check if current time is between login_low and login_high, and entity_flg is "Y"
        if (currTime.isAfter(low) && currTime.isBefore(high) && "Y".equals(entity_flg)) {
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
	public String toString() {
		return "ResourceMaster [organisation=" + organisation + ", category=" + category + ", resource_id="
				+ resource_id + ", resource_name=" + resource_name + ", employee_ref=" + employee_ref + ", employee_id="
				+ employee_id + ", doj=" + doj + ", dob=" + dob + ", groups=" + groups + ", team=" + team
				+ ", report_mgr_id=" + report_mgr_id + ", report_mgr_name=" + report_mgr_name + ", design=" + design
				+ ", role=" + role + ", qual=" + qual + ", addl_qual=" + addl_qual + ", skill_set=" + skill_set
				+ ", expertise=" + expertise + ", pancard=" + pancard + ", aadhar=" + aadhar + ", passport=" + passport
				+ ", driving_license=" + driving_license + ", gender=" + gender + ", blood_group=" + blood_group
				+ ", marital_status=" + marital_status + ", dependants=" + dependants + ", mobile=" + mobile
				+ ", alt_mobile=" + alt_mobile + ", access_id=" + access_id + ", email=" + email + ", addr1=" + addr1
				+ ", addr2=" + addr2 + ", city=" + city + ", state=" + state + ", country=" + country + ", postal_code="
				+ postal_code + ", loc_addr1=" + loc_addr1 + ", loc_addr2=" + loc_addr2 + ", loc_city=" + loc_city
				+ ", loc_state=" + loc_state + ", loc_country=" + loc_country + ", loc_postal_code=" + loc_postal_code
				+ ", user_cont_person=" + user_cont_person + ", cont_person_no=" + cont_person_no + ", password="
				+ password + ", life_of_pw=" + life_of_pw + ", acct_expy_date=" + acct_expy_date + ", pw_expy_date="
				+ pw_expy_date + ", user_remarks=" + user_remarks + ", disable_flg=" + disable_flg + ", dis_start_date="
				+ dis_start_date + ", dis_end_date=" + dis_end_date + ", login_low=" + login_low + ", login_high="
				+ login_high + ", entry_user=" + entry_user + ", modify_user=" + modify_user + ", verify_user="
				+ verify_user + ", entry_time=" + entry_time + ", modify_time=" + modify_time + ", verify_time="
				+ verify_time + ", del_flg=" + del_flg + ", entity_flg=" + entity_flg + ", modify_flg=" + modify_flg
				+ ", first_name=" + first_name + ", middle_name=" + middle_name + ", last_name=" + last_name
				+ ", short_name=" + short_name + ", virtual_flg=" + virtual_flg + ", login_status=" + login_status
				+ ", channel_id=" + channel_id + ", locked_flg=" + locked_flg + ", session_id=" + session_id + ", imei="
				+ imei + ", dor=" + dor + ", ifsc=" + ifsc + ", bank=" + bank + ", bank_act_no=" + bank_act_no + "]";
	}

	

}
