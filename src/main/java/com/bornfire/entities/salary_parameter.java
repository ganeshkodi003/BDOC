package com.bornfire.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SALARY_PARAMETERS")
public class salary_parameter {
	    @Id 
	    private BigDecimal	srl_no;
	    private String	group_name;
	    private BigDecimal	des_basic;
	    private BigDecimal	des_hra;
	    private BigDecimal	des_medical;
	    private BigDecimal	des_conveyance;
	    private BigDecimal	des_attire;
	    private BigDecimal	des_ltc;
	    private BigDecimal	des_employer_pf;
	    private BigDecimal	des_special_allowance;
	    private BigDecimal	des_professional_tax;
	    private BigDecimal	des_tax_deduction;
	    private BigDecimal	ctc_cost_to_company;
	    private BigDecimal	ctc_basic_salary;
	    private BigDecimal	ctc_house_rent_allowance;
	    private BigDecimal	ctc_special_allowance;
	    private BigDecimal	ctc_medical_reimbursement;
	    private BigDecimal	ctc_conveyance_allowance;
	    private BigDecimal	ctc_lunch_allownace;
	    private BigDecimal	ctc_education_allowance;
	    private BigDecimal	ctc_business_attire_reimbursement;
	    private BigDecimal	ctc_car_maintenance_allowance;
	    private BigDecimal	ctc_leave_travel_allowance;
	    private BigDecimal	ctc_pf_employer_contribution;
	    private BigDecimal	ctc_annual_loyalty_bonus;
	    private BigDecimal	ctc_gross_salary;
	    private BigDecimal	ctc_professional_deduction;
	    private BigDecimal	ctc_standard_deduction;
	    private BigDecimal	ctc_tax_deduction;
	    private BigDecimal	ctc_net_salary;
	    private BigDecimal	mon_cost_to_company;
	    private BigDecimal	mon_basic_salary;
	    private BigDecimal	mon_house_rent_allowance;
	    private BigDecimal	mon_special_allowance;
	    private BigDecimal	mon_medical_reimbursement;
	    private BigDecimal	mon_conveyance_allowance;
	    private BigDecimal	mon_lunch_allownace;
	    private BigDecimal	mon_education_allowance;
	    private BigDecimal	mon_business_attire_reimbursement;
	    private BigDecimal	mon_car_maintenance_allowance;
	    private BigDecimal	mon_leave_travel_allowance;
	    private BigDecimal	mon_pf_employer_contribution;
	    private BigDecimal	mon_annual_loyalty_bonus;
	    private BigDecimal	mon_gross_salary;
	    private BigDecimal	mon_professional_deduction;
	    private BigDecimal	mon_standard_deduction;
	    private BigDecimal	mon_tax_deduction;
	    private BigDecimal	mon_net_salary;
	    private BigDecimal	year_cost_to_company;
	    private BigDecimal	year_basic_salary;
	    private BigDecimal	year_house_rent_allowance;
	    private BigDecimal	year_special_allowance;
	    private BigDecimal	year_medical_reimbursement;
	    private BigDecimal	year_conveyance_allowance;
	    private BigDecimal	year_lunch_allownace;
	    private BigDecimal	year_education_allowance;
	    private BigDecimal	year_business_attire_reimbursement;
	    private BigDecimal	year_car_maintenance_allowance;
	    private BigDecimal	year_leave_travel_allowance;
	    private BigDecimal	year_pf_employer_contribution;
	    private BigDecimal	year_annual_loyalty_bonus;
	    private BigDecimal	year_gross_salary;
	    private BigDecimal	year_professional_deduction;
	    private BigDecimal	year_standard_deduction;
	    private BigDecimal	year_tax_deduction;
	    private BigDecimal	year_net_salary;
	    private BigDecimal	ths_cost_to_company;
	    private BigDecimal	ths_basic_salary;
	    private BigDecimal	ths_house_rent_allowance;
	    private BigDecimal	ths_special_allowance;
	    private BigDecimal	ths_medical_reimbursement;
	    private BigDecimal	ths_conveyance_allowance;
	    private BigDecimal	ths_lunch_allownace;
	    private BigDecimal	ths_education_allowance;
	    private BigDecimal	ths_business_attire_reimbursement;
	    private BigDecimal	ths_car_maintenance_allowance;
	    private BigDecimal	ths_leave_travel_allowance;
	    private BigDecimal	ths_pf_employer_contribution;
	    private BigDecimal	ths_annual_loyalty_bonus;
	    private BigDecimal	ths_gross_salary;
	    private BigDecimal	ths_professional_deduction;
	    private BigDecimal	ths_standard_deduction;
	    private BigDecimal	ths_tax_deduction;
	    private BigDecimal	ths_net_salary;
	    private BigDecimal	des_fjt;
	    private String	status;

		 @Column(name = "[national]")
	    private String	national;
	    private String	country;
	    private String	spec_country;
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
	    
	    
		public salary_parameter(String branchId) {
			super();
			this.branchId = branchId;
		}

		public BigDecimal getSrl_no() {
			return srl_no;
		}
		public void setSrl_no(BigDecimal srl_no) {
			this.srl_no = srl_no;
		}
		public String getGroup_name() {
			return group_name;
		}
		public void setGroup_name(String group_name) {
			this.group_name = group_name;
		}
		public BigDecimal getDes_basic() {
			return des_basic;
		}
		public void setDes_basic(BigDecimal des_basic) {
			this.des_basic = des_basic;
		}
		public BigDecimal getDes_hra() {
			return des_hra;
		}
		public void setDes_hra(BigDecimal des_hra) {
			this.des_hra = des_hra;
		}
		public BigDecimal getDes_medical() {
			return des_medical;
		}
		public void setDes_medical(BigDecimal des_medical) {
			this.des_medical = des_medical;
		}
		public BigDecimal getDes_conveyance() {
			return des_conveyance;
		}
		public void setDes_conveyance(BigDecimal des_conveyance) {
			this.des_conveyance = des_conveyance;
		}
		public BigDecimal getDes_attire() {
			return des_attire;
		}
		public void setDes_attire(BigDecimal des_attire) {
			this.des_attire = des_attire;
		}
		public BigDecimal getDes_ltc() {
			return des_ltc;
		}
		public void setDes_ltc(BigDecimal des_ltc) {
			this.des_ltc = des_ltc;
		}
		public BigDecimal getDes_employer_pf() {
			return des_employer_pf;
		}
		public void setDes_employer_pf(BigDecimal des_employer_pf) {
			this.des_employer_pf = des_employer_pf;
		}
		public BigDecimal getDes_special_allowance() {
			return des_special_allowance;
		}
		public void setDes_special_allowance(BigDecimal des_special_allowance) {
			this.des_special_allowance = des_special_allowance;
		}
		public BigDecimal getDes_professional_tax() {
			return des_professional_tax;
		}
		public void setDes_professional_tax(BigDecimal des_professional_tax) {
			this.des_professional_tax = des_professional_tax;
		}
		public BigDecimal getDes_tax_deduction() {
			return des_tax_deduction;
		}
		public void setDes_tax_deduction(BigDecimal des_tax_deduction) {
			this.des_tax_deduction = des_tax_deduction;
		}
		public BigDecimal getCtc_cost_to_company() {
			return ctc_cost_to_company;
		}
		public void setCtc_cost_to_company(BigDecimal ctc_cost_to_company) {
			this.ctc_cost_to_company = ctc_cost_to_company;
		}
		public BigDecimal getCtc_basic_salary() {
			return ctc_basic_salary;
		}
		public void setCtc_basic_salary(BigDecimal ctc_basic_salary) {
			this.ctc_basic_salary = ctc_basic_salary;
		}
		public BigDecimal getCtc_house_rent_allowance() {
			return ctc_house_rent_allowance;
		}
		public void setCtc_house_rent_allowance(BigDecimal ctc_house_rent_allowance) {
			this.ctc_house_rent_allowance = ctc_house_rent_allowance;
		}
		public BigDecimal getCtc_special_allowance() {
			return ctc_special_allowance;
		}
		public void setCtc_special_allowance(BigDecimal ctc_special_allowance) {
			this.ctc_special_allowance = ctc_special_allowance;
		}
		public BigDecimal getCtc_medical_reimbursement() {
			return ctc_medical_reimbursement;
		}
		public void setCtc_medical_reimbursement(BigDecimal ctc_medical_reimbursement) {
			this.ctc_medical_reimbursement = ctc_medical_reimbursement;
		}
		public BigDecimal getCtc_conveyance_allowance() {
			return ctc_conveyance_allowance;
		}
		public void setCtc_conveyance_allowance(BigDecimal ctc_conveyance_allowance) {
			this.ctc_conveyance_allowance = ctc_conveyance_allowance;
		}
		public BigDecimal getCtc_lunch_allownace() {
			return ctc_lunch_allownace;
		}
		public void setCtc_lunch_allownace(BigDecimal ctc_lunch_allownace) {
			this.ctc_lunch_allownace = ctc_lunch_allownace;
		}
		public BigDecimal getCtc_education_allowance() {
			return ctc_education_allowance;
		}
		public void setCtc_education_allowance(BigDecimal ctc_education_allowance) {
			this.ctc_education_allowance = ctc_education_allowance;
		}
		public BigDecimal getCtc_business_attire_reimbursement() {
			return ctc_business_attire_reimbursement;
		}
		public void setCtc_business_attire_reimbursement(BigDecimal ctc_business_attire_reimbursement) {
			this.ctc_business_attire_reimbursement = ctc_business_attire_reimbursement;
		}
		public BigDecimal getCtc_car_maintenance_allowance() {
			return ctc_car_maintenance_allowance;
		}
		public void setCtc_car_maintenance_allowance(BigDecimal ctc_car_maintenance_allowance) {
			this.ctc_car_maintenance_allowance = ctc_car_maintenance_allowance;
		}
		public BigDecimal getCtc_leave_travel_allowance() {
			return ctc_leave_travel_allowance;
		}
		public void setCtc_leave_travel_allowance(BigDecimal ctc_leave_travel_allowance) {
			this.ctc_leave_travel_allowance = ctc_leave_travel_allowance;
		}
		public BigDecimal getCtc_pf_employer_contribution() {
			return ctc_pf_employer_contribution;
		}
		public void setCtc_pf_employer_contribution(BigDecimal ctc_pf_employer_contribution) {
			this.ctc_pf_employer_contribution = ctc_pf_employer_contribution;
		}
		public BigDecimal getCtc_annual_loyalty_bonus() {
			return ctc_annual_loyalty_bonus;
		}
		public void setCtc_annual_loyalty_bonus(BigDecimal ctc_annual_loyalty_bonus) {
			this.ctc_annual_loyalty_bonus = ctc_annual_loyalty_bonus;
		}
		public BigDecimal getCtc_gross_salary() {
			return ctc_gross_salary;
		}
		public void setCtc_gross_salary(BigDecimal ctc_gross_salary) {
			this.ctc_gross_salary = ctc_gross_salary;
		}
		public BigDecimal getCtc_professional_deduction() {
			return ctc_professional_deduction;
		}
		public void setCtc_professional_deduction(BigDecimal ctc_professional_deduction) {
			this.ctc_professional_deduction = ctc_professional_deduction;
		}
		public BigDecimal getCtc_standard_deduction() {
			return ctc_standard_deduction;
		}
		public void setCtc_standard_deduction(BigDecimal ctc_standard_deduction) {
			this.ctc_standard_deduction = ctc_standard_deduction;
		}
		public BigDecimal getCtc_tax_deduction() {
			return ctc_tax_deduction;
		}
		public void setCtc_tax_deduction(BigDecimal ctc_tax_deduction) {
			this.ctc_tax_deduction = ctc_tax_deduction;
		}
		public BigDecimal getCtc_net_salary() {
			return ctc_net_salary;
		}
		public void setCtc_net_salary(BigDecimal ctc_net_salary) {
			this.ctc_net_salary = ctc_net_salary;
		}
		public BigDecimal getMon_cost_to_company() {
			return mon_cost_to_company;
		}
		public void setMon_cost_to_company(BigDecimal mon_cost_to_company) {
			this.mon_cost_to_company = mon_cost_to_company;
		}
		public BigDecimal getMon_basic_salary() {
			return mon_basic_salary;
		}
		public void setMon_basic_salary(BigDecimal mon_basic_salary) {
			this.mon_basic_salary = mon_basic_salary;
		}
		public BigDecimal getMon_house_rent_allowance() {
			return mon_house_rent_allowance;
		}
		public void setMon_house_rent_allowance(BigDecimal mon_house_rent_allowance) {
			this.mon_house_rent_allowance = mon_house_rent_allowance;
		}
		public BigDecimal getMon_special_allowance() {
			return mon_special_allowance;
		}
		public void setMon_special_allowance(BigDecimal mon_special_allowance) {
			this.mon_special_allowance = mon_special_allowance;
		}
		public BigDecimal getMon_medical_reimbursement() {
			return mon_medical_reimbursement;
		}
		public void setMon_medical_reimbursement(BigDecimal mon_medical_reimbursement) {
			this.mon_medical_reimbursement = mon_medical_reimbursement;
		}
		public BigDecimal getMon_conveyance_allowance() {
			return mon_conveyance_allowance;
		}
		public void setMon_conveyance_allowance(BigDecimal mon_conveyance_allowance) {
			this.mon_conveyance_allowance = mon_conveyance_allowance;
		}
		public BigDecimal getMon_lunch_allownace() {
			return mon_lunch_allownace;
		}
		public void setMon_lunch_allownace(BigDecimal mon_lunch_allownace) {
			this.mon_lunch_allownace = mon_lunch_allownace;
		}
		public BigDecimal getMon_education_allowance() {
			return mon_education_allowance;
		}
		public void setMon_education_allowance(BigDecimal mon_education_allowance) {
			this.mon_education_allowance = mon_education_allowance;
		}
		public BigDecimal getMon_business_attire_reimbursement() {
			return mon_business_attire_reimbursement;
		}
		public void setMon_business_attire_reimbursement(BigDecimal mon_business_attire_reimbursement) {
			this.mon_business_attire_reimbursement = mon_business_attire_reimbursement;
		}
		public BigDecimal getMon_car_maintenance_allowance() {
			return mon_car_maintenance_allowance;
		}
		public void setMon_car_maintenance_allowance(BigDecimal mon_car_maintenance_allowance) {
			this.mon_car_maintenance_allowance = mon_car_maintenance_allowance;
		}
		public BigDecimal getMon_leave_travel_allowance() {
			return mon_leave_travel_allowance;
		}
		public void setMon_leave_travel_allowance(BigDecimal mon_leave_travel_allowance) {
			this.mon_leave_travel_allowance = mon_leave_travel_allowance;
		}
		public BigDecimal getMon_pf_employer_contribution() {
			return mon_pf_employer_contribution;
		}
		public void setMon_pf_employer_contribution(BigDecimal mon_pf_employer_contribution) {
			this.mon_pf_employer_contribution = mon_pf_employer_contribution;
		}
		public BigDecimal getMon_annual_loyalty_bonus() {
			return mon_annual_loyalty_bonus;
		}
		public void setMon_annual_loyalty_bonus(BigDecimal mon_annual_loyalty_bonus) {
			this.mon_annual_loyalty_bonus = mon_annual_loyalty_bonus;
		}
		public BigDecimal getMon_gross_salary() {
			return mon_gross_salary;
		}
		public void setMon_gross_salary(BigDecimal mon_gross_salary) {
			this.mon_gross_salary = mon_gross_salary;
		}
		public BigDecimal getMon_professional_deduction() {
			return mon_professional_deduction;
		}
		public void setMon_professional_deduction(BigDecimal mon_professional_deduction) {
			this.mon_professional_deduction = mon_professional_deduction;
		}
		public BigDecimal getMon_standard_deduction() {
			return mon_standard_deduction;
		}
		public void setMon_standard_deduction(BigDecimal mon_standard_deduction) {
			this.mon_standard_deduction = mon_standard_deduction;
		}
		public BigDecimal getMon_tax_deduction() {
			return mon_tax_deduction;
		}
		public void setMon_tax_deduction(BigDecimal mon_tax_deduction) {
			this.mon_tax_deduction = mon_tax_deduction;
		}
		public BigDecimal getMon_net_salary() {
			return mon_net_salary;
		}
		public void setMon_net_salary(BigDecimal mon_net_salary) {
			this.mon_net_salary = mon_net_salary;
		}
		public BigDecimal getYear_cost_to_company() {
			return year_cost_to_company;
		}
		public void setYear_cost_to_company(BigDecimal year_cost_to_company) {
			this.year_cost_to_company = year_cost_to_company;
		}
		public BigDecimal getYear_basic_salary() {
			return year_basic_salary;
		}
		public void setYear_basic_salary(BigDecimal year_basic_salary) {
			this.year_basic_salary = year_basic_salary;
		}
		public BigDecimal getYear_house_rent_allowance() {
			return year_house_rent_allowance;
		}
		public void setYear_house_rent_allowance(BigDecimal year_house_rent_allowance) {
			this.year_house_rent_allowance = year_house_rent_allowance;
		}
		public BigDecimal getYear_special_allowance() {
			return year_special_allowance;
		}
		public void setYear_special_allowance(BigDecimal year_special_allowance) {
			this.year_special_allowance = year_special_allowance;
		}
		public BigDecimal getYear_medical_reimbursement() {
			return year_medical_reimbursement;
		}
		public void setYear_medical_reimbursement(BigDecimal year_medical_reimbursement) {
			this.year_medical_reimbursement = year_medical_reimbursement;
		}
		public BigDecimal getYear_conveyance_allowance() {
			return year_conveyance_allowance;
		}
		public void setYear_conveyance_allowance(BigDecimal year_conveyance_allowance) {
			this.year_conveyance_allowance = year_conveyance_allowance;
		}
		public BigDecimal getYear_lunch_allownace() {
			return year_lunch_allownace;
		}
		public void setYear_lunch_allownace(BigDecimal year_lunch_allownace) {
			this.year_lunch_allownace = year_lunch_allownace;
		}
		public BigDecimal getYear_education_allowance() {
			return year_education_allowance;
		}
		public void setYear_education_allowance(BigDecimal year_education_allowance) {
			this.year_education_allowance = year_education_allowance;
		}
		public BigDecimal getYear_business_attire_reimbursement() {
			return year_business_attire_reimbursement;
		}
		public void setYear_business_attire_reimbursement(BigDecimal year_business_attire_reimbursement) {
			this.year_business_attire_reimbursement = year_business_attire_reimbursement;
		}
		public BigDecimal getYear_car_maintenance_allowance() {
			return year_car_maintenance_allowance;
		}
		public void setYear_car_maintenance_allowance(BigDecimal year_car_maintenance_allowance) {
			this.year_car_maintenance_allowance = year_car_maintenance_allowance;
		}
		public BigDecimal getYear_leave_travel_allowance() {
			return year_leave_travel_allowance;
		}
		public void setYear_leave_travel_allowance(BigDecimal year_leave_travel_allowance) {
			this.year_leave_travel_allowance = year_leave_travel_allowance;
		}
		public BigDecimal getYear_pf_employer_contribution() {
			return year_pf_employer_contribution;
		}
		public void setYear_pf_employer_contribution(BigDecimal year_pf_employer_contribution) {
			this.year_pf_employer_contribution = year_pf_employer_contribution;
		}
		public BigDecimal getYear_annual_loyalty_bonus() {
			return year_annual_loyalty_bonus;
		}
		public void setYear_annual_loyalty_bonus(BigDecimal year_annual_loyalty_bonus) {
			this.year_annual_loyalty_bonus = year_annual_loyalty_bonus;
		}
		public BigDecimal getYear_gross_salary() {
			return year_gross_salary;
		}
		public void setYear_gross_salary(BigDecimal year_gross_salary) {
			this.year_gross_salary = year_gross_salary;
		}
		public BigDecimal getYear_professional_deduction() {
			return year_professional_deduction;
		}
		public void setYear_professional_deduction(BigDecimal year_professional_deduction) {
			this.year_professional_deduction = year_professional_deduction;
		}
		public BigDecimal getYear_standard_deduction() {
			return year_standard_deduction;
		}
		public void setYear_standard_deduction(BigDecimal year_standard_deduction) {
			this.year_standard_deduction = year_standard_deduction;
		}
		public BigDecimal getYear_tax_deduction() {
			return year_tax_deduction;
		}
		public void setYear_tax_deduction(BigDecimal year_tax_deduction) {
			this.year_tax_deduction = year_tax_deduction;
		}
		public BigDecimal getYear_net_salary() {
			return year_net_salary;
		}
		public void setYear_net_salary(BigDecimal year_net_salary) {
			this.year_net_salary = year_net_salary;
		}
		public BigDecimal getThs_cost_to_company() {
			return ths_cost_to_company;
		}
		public void setThs_cost_to_company(BigDecimal ths_cost_to_company) {
			this.ths_cost_to_company = ths_cost_to_company;
		}
		public BigDecimal getThs_basic_salary() {
			return ths_basic_salary;
		}
		public void setThs_basic_salary(BigDecimal ths_basic_salary) {
			this.ths_basic_salary = ths_basic_salary;
		}
		public BigDecimal getThs_house_rent_allowance() {
			return ths_house_rent_allowance;
		}
		public void setThs_house_rent_allowance(BigDecimal ths_house_rent_allowance) {
			this.ths_house_rent_allowance = ths_house_rent_allowance;
		}
		public BigDecimal getThs_special_allowance() {
			return ths_special_allowance;
		}
		public void setThs_special_allowance(BigDecimal ths_special_allowance) {
			this.ths_special_allowance = ths_special_allowance;
		}
		public BigDecimal getThs_medical_reimbursement() {
			return ths_medical_reimbursement;
		}
		public void setThs_medical_reimbursement(BigDecimal ths_medical_reimbursement) {
			this.ths_medical_reimbursement = ths_medical_reimbursement;
		}
		public BigDecimal getThs_conveyance_allowance() {
			return ths_conveyance_allowance;
		}
		public void setThs_conveyance_allowance(BigDecimal ths_conveyance_allowance) {
			this.ths_conveyance_allowance = ths_conveyance_allowance;
		}
		public BigDecimal getThs_lunch_allownace() {
			return ths_lunch_allownace;
		}
		public void setThs_lunch_allownace(BigDecimal ths_lunch_allownace) {
			this.ths_lunch_allownace = ths_lunch_allownace;
		}
		public BigDecimal getThs_education_allowance() {
			return ths_education_allowance;
		}
		public void setThs_education_allowance(BigDecimal ths_education_allowance) {
			this.ths_education_allowance = ths_education_allowance;
		}
		public BigDecimal getThs_business_attire_reimbursement() {
			return ths_business_attire_reimbursement;
		}
		public void setThs_business_attire_reimbursement(BigDecimal ths_business_attire_reimbursement) {
			this.ths_business_attire_reimbursement = ths_business_attire_reimbursement;
		}
		public BigDecimal getThs_car_maintenance_allowance() {
			return ths_car_maintenance_allowance;
		}
		public void setThs_car_maintenance_allowance(BigDecimal ths_car_maintenance_allowance) {
			this.ths_car_maintenance_allowance = ths_car_maintenance_allowance;
		}
		public BigDecimal getThs_leave_travel_allowance() {
			return ths_leave_travel_allowance;
		}
		public void setThs_leave_travel_allowance(BigDecimal ths_leave_travel_allowance) {
			this.ths_leave_travel_allowance = ths_leave_travel_allowance;
		}
		public BigDecimal getThs_pf_employer_contribution() {
			return ths_pf_employer_contribution;
		}
		public void setThs_pf_employer_contribution(BigDecimal ths_pf_employer_contribution) {
			this.ths_pf_employer_contribution = ths_pf_employer_contribution;
		}
		public BigDecimal getThs_annual_loyalty_bonus() {
			return ths_annual_loyalty_bonus;
		}
		public void setThs_annual_loyalty_bonus(BigDecimal ths_annual_loyalty_bonus) {
			this.ths_annual_loyalty_bonus = ths_annual_loyalty_bonus;
		}
		public BigDecimal getThs_gross_salary() {
			return ths_gross_salary;
		}
		public void setThs_gross_salary(BigDecimal ths_gross_salary) {
			this.ths_gross_salary = ths_gross_salary;
		}
		public BigDecimal getThs_professional_deduction() {
			return ths_professional_deduction;
		}
		public void setThs_professional_deduction(BigDecimal ths_professional_deduction) {
			this.ths_professional_deduction = ths_professional_deduction;
		}
		public BigDecimal getThs_standard_deduction() {
			return ths_standard_deduction;
		}
		public void setThs_standard_deduction(BigDecimal ths_standard_deduction) {
			this.ths_standard_deduction = ths_standard_deduction;
		}
		public BigDecimal getThs_tax_deduction() {
			return ths_tax_deduction;
		}
		public void setThs_tax_deduction(BigDecimal ths_tax_deduction) {
			this.ths_tax_deduction = ths_tax_deduction;
		}
		public BigDecimal getThs_net_salary() {
			return ths_net_salary;
		}
		public void setThs_net_salary(BigDecimal ths_net_salary) {
			this.ths_net_salary = ths_net_salary;
		}
		public BigDecimal getDes_fjt() {
			return des_fjt;
		}
		public void setDes_fjt(BigDecimal des_fjt) {
			this.des_fjt = des_fjt;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getNational() {
			return national;
		}
		public void setNational(String national) {
			this.national = national;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String getSpec_country() {
			return spec_country;
		}
		public void setSpec_country(String spec_country) {
			this.spec_country = spec_country;
		}
		public salary_parameter(BigDecimal srl_no, String group_name, BigDecimal des_basic, BigDecimal des_hra,
				BigDecimal des_medical, BigDecimal des_conveyance, BigDecimal des_attire, BigDecimal des_ltc,
				BigDecimal des_employer_pf, BigDecimal des_special_allowance, BigDecimal des_professional_tax,
				BigDecimal des_tax_deduction, BigDecimal ctc_cost_to_company, BigDecimal ctc_basic_salary,
				BigDecimal ctc_house_rent_allowance, BigDecimal ctc_special_allowance,
				BigDecimal ctc_medical_reimbursement, BigDecimal ctc_conveyance_allowance,
				BigDecimal ctc_lunch_allownace, BigDecimal ctc_education_allowance,
				BigDecimal ctc_business_attire_reimbursement, BigDecimal ctc_car_maintenance_allowance,
				BigDecimal ctc_leave_travel_allowance, BigDecimal ctc_pf_employer_contribution,
				BigDecimal ctc_annual_loyalty_bonus, BigDecimal ctc_gross_salary, BigDecimal ctc_professional_deduction,
				BigDecimal ctc_standard_deduction, BigDecimal ctc_tax_deduction, BigDecimal ctc_net_salary,
				BigDecimal mon_cost_to_company, BigDecimal mon_basic_salary, BigDecimal mon_house_rent_allowance,
				BigDecimal mon_special_allowance, BigDecimal mon_medical_reimbursement,
				BigDecimal mon_conveyance_allowance, BigDecimal mon_lunch_allownace, BigDecimal mon_education_allowance,
				BigDecimal mon_business_attire_reimbursement, BigDecimal mon_car_maintenance_allowance,
				BigDecimal mon_leave_travel_allowance, BigDecimal mon_pf_employer_contribution,
				BigDecimal mon_annual_loyalty_bonus, BigDecimal mon_gross_salary, BigDecimal mon_professional_deduction,
				BigDecimal mon_standard_deduction, BigDecimal mon_tax_deduction, BigDecimal mon_net_salary,
				BigDecimal year_cost_to_company, BigDecimal year_basic_salary, BigDecimal year_house_rent_allowance,
				BigDecimal year_special_allowance, BigDecimal year_medical_reimbursement,
				BigDecimal year_conveyance_allowance, BigDecimal year_lunch_allownace,
				BigDecimal year_education_allowance, BigDecimal year_business_attire_reimbursement,
				BigDecimal year_car_maintenance_allowance, BigDecimal year_leave_travel_allowance,
				BigDecimal year_pf_employer_contribution, BigDecimal year_annual_loyalty_bonus,
				BigDecimal year_gross_salary, BigDecimal year_professional_deduction,
				BigDecimal year_standard_deduction, BigDecimal year_tax_deduction, BigDecimal year_net_salary,
				BigDecimal ths_cost_to_company, BigDecimal ths_basic_salary, BigDecimal ths_house_rent_allowance,
				BigDecimal ths_special_allowance, BigDecimal ths_medical_reimbursement,
				BigDecimal ths_conveyance_allowance, BigDecimal ths_lunch_allownace, BigDecimal ths_education_allowance,
				BigDecimal ths_business_attire_reimbursement, BigDecimal ths_car_maintenance_allowance,
				BigDecimal ths_leave_travel_allowance, BigDecimal ths_pf_employer_contribution,
				BigDecimal ths_annual_loyalty_bonus, BigDecimal ths_gross_salary, BigDecimal ths_professional_deduction,
				BigDecimal ths_standard_deduction, BigDecimal ths_tax_deduction, BigDecimal ths_net_salary,
				BigDecimal des_fjt, String status, String national, String country, String spec_country,String org_id) {
			super();
			this.srl_no = srl_no;
			this.group_name = group_name;
			this.des_basic = des_basic;
			this.des_hra = des_hra;
			this.des_medical = des_medical;
			this.des_conveyance = des_conveyance;
			this.des_attire = des_attire;
			this.des_ltc = des_ltc;
			this.des_employer_pf = des_employer_pf;
			this.des_special_allowance = des_special_allowance;
			this.des_professional_tax = des_professional_tax;
			this.des_tax_deduction = des_tax_deduction;
			this.ctc_cost_to_company = ctc_cost_to_company;
			this.ctc_basic_salary = ctc_basic_salary;
			this.ctc_house_rent_allowance = ctc_house_rent_allowance;
			this.ctc_special_allowance = ctc_special_allowance;
			this.ctc_medical_reimbursement = ctc_medical_reimbursement;
			this.ctc_conveyance_allowance = ctc_conveyance_allowance;
			this.ctc_lunch_allownace = ctc_lunch_allownace;
			this.ctc_education_allowance = ctc_education_allowance;
			this.ctc_business_attire_reimbursement = ctc_business_attire_reimbursement;
			this.ctc_car_maintenance_allowance = ctc_car_maintenance_allowance;
			this.ctc_leave_travel_allowance = ctc_leave_travel_allowance;
			this.ctc_pf_employer_contribution = ctc_pf_employer_contribution;
			this.ctc_annual_loyalty_bonus = ctc_annual_loyalty_bonus;
			this.ctc_gross_salary = ctc_gross_salary;
			this.ctc_professional_deduction = ctc_professional_deduction;
			this.ctc_standard_deduction = ctc_standard_deduction;
			this.ctc_tax_deduction = ctc_tax_deduction;
			this.ctc_net_salary = ctc_net_salary;
			this.mon_cost_to_company = mon_cost_to_company;
			this.mon_basic_salary = mon_basic_salary;
			this.mon_house_rent_allowance = mon_house_rent_allowance;
			this.mon_special_allowance = mon_special_allowance;
			this.mon_medical_reimbursement = mon_medical_reimbursement;
			this.mon_conveyance_allowance = mon_conveyance_allowance;
			this.mon_lunch_allownace = mon_lunch_allownace;
			this.mon_education_allowance = mon_education_allowance;
			this.mon_business_attire_reimbursement = mon_business_attire_reimbursement;
			this.mon_car_maintenance_allowance = mon_car_maintenance_allowance;
			this.mon_leave_travel_allowance = mon_leave_travel_allowance;
			this.mon_pf_employer_contribution = mon_pf_employer_contribution;
			this.mon_annual_loyalty_bonus = mon_annual_loyalty_bonus;
			this.mon_gross_salary = mon_gross_salary;
			this.mon_professional_deduction = mon_professional_deduction;
			this.mon_standard_deduction = mon_standard_deduction;
			this.mon_tax_deduction = mon_tax_deduction;
			this.mon_net_salary = mon_net_salary;
			this.year_cost_to_company = year_cost_to_company;
			this.year_basic_salary = year_basic_salary;
			this.year_house_rent_allowance = year_house_rent_allowance;
			this.year_special_allowance = year_special_allowance;
			this.year_medical_reimbursement = year_medical_reimbursement;
			this.year_conveyance_allowance = year_conveyance_allowance;
			this.year_lunch_allownace = year_lunch_allownace;
			this.year_education_allowance = year_education_allowance;
			this.year_business_attire_reimbursement = year_business_attire_reimbursement;
			this.year_car_maintenance_allowance = year_car_maintenance_allowance;
			this.year_leave_travel_allowance = year_leave_travel_allowance;
			this.year_pf_employer_contribution = year_pf_employer_contribution;
			this.year_annual_loyalty_bonus = year_annual_loyalty_bonus;
			this.year_gross_salary = year_gross_salary;
			this.year_professional_deduction = year_professional_deduction;
			this.year_standard_deduction = year_standard_deduction;
			this.year_tax_deduction = year_tax_deduction;
			this.year_net_salary = year_net_salary;
			this.ths_cost_to_company = ths_cost_to_company;
			this.ths_basic_salary = ths_basic_salary;
			this.ths_house_rent_allowance = ths_house_rent_allowance;
			this.ths_special_allowance = ths_special_allowance;
			this.ths_medical_reimbursement = ths_medical_reimbursement;
			this.ths_conveyance_allowance = ths_conveyance_allowance;
			this.ths_lunch_allownace = ths_lunch_allownace;
			this.ths_education_allowance = ths_education_allowance;
			this.ths_business_attire_reimbursement = ths_business_attire_reimbursement;
			this.ths_car_maintenance_allowance = ths_car_maintenance_allowance;
			this.ths_leave_travel_allowance = ths_leave_travel_allowance;
			this.ths_pf_employer_contribution = ths_pf_employer_contribution;
			this.ths_annual_loyalty_bonus = ths_annual_loyalty_bonus;
			this.ths_gross_salary = ths_gross_salary;
			this.ths_professional_deduction = ths_professional_deduction;
			this.ths_standard_deduction = ths_standard_deduction;
			this.ths_tax_deduction = ths_tax_deduction;
			this.ths_net_salary = ths_net_salary;
			this.des_fjt = des_fjt;
			this.status = status;
			this.national = national;
			this.country = country;
			this.spec_country = spec_country;
			this.org_id=org_id;
		}
		public salary_parameter() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
}
