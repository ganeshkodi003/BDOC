package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
@Table(name="BHMS_PAYMENT_TABLE")
public class BHMSPaymentTable {
	private String puin;
	@Id
	private String receipt_no;
	private String payer_type;
	private BigDecimal amount;
	private String scan;
	private BigDecimal scan_amount;
	private String blood_test;
	private BigDecimal blood_test_amount;
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date payment_date;
	private String payer_name;
	private String payment_mode;
	private String payment_status;
	private String cashier_id;
	private BigDecimal total_gst;
	private BigDecimal total_amt;
	private BigDecimal sponsored_amt;
	private BigDecimal net_patient_amt;
	private BigDecimal net_total_amt;
	private BigDecimal service_tax;
	private String reference;
	private BigDecimal total_bill_amt;
	private String card_payment_ref;
	private String doctor;
	private String visit_no;
	private Date visit_date;
	private String bill_no;
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date bill_date;
	private String department;
	private String op_ref;
	private BigDecimal consession1;
	private BigDecimal consession2;
	private BigDecimal insurance;
	private BigDecimal cgst;
	private BigDecimal sgst;
	private BigDecimal igst;
	private String entry_user;
	private Date entry_time;
	private String entry_channel;
	private String modify_user;
	private Date modify_time;
	private String modify_channel;
	private String approval_user;
	private Date approval_time;
	private String approval_channel;
	private String entity_flg;
	private String modify_flg;
	private String verify_flg;
	private String del_flg;
	public String getPuin() {
		return puin;
	}
	public void setPuin(String puin) {
		this.puin = puin;
	}
	public String getReceipt_no() {
		return receipt_no;
	}
	public void setReceipt_no(String receipt_no) {
		this.receipt_no = receipt_no;
	}
	public String getPayer_type() {
		return payer_type;
	}
	public void setPayer_type(String payer_type) {
		this.payer_type = payer_type;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getScan() {
		return scan;
	}
	public void setScan(String scan) {
		this.scan = scan;
	}
	public BigDecimal getScan_amount() {
		return scan_amount;
	}
	public void setScan_amount(BigDecimal scan_amount) {
		this.scan_amount = scan_amount;
	}
	public String getBlood_test() {
		return blood_test;
	}
	public void setBlood_test(String blood_test) {
		this.blood_test = blood_test;
	}
	public BigDecimal getBlood_test_amount() {
		return blood_test_amount;
	}
	public void setBlood_test_amount(BigDecimal blood_test_amount) {
		this.blood_test_amount = blood_test_amount;
	}
	public Date getPayment_date() {
		return payment_date;
	}
	public void setPayment_date(Date payment_date) {
		this.payment_date = payment_date;
	}
	public String getPayer_name() {
		return payer_name;
	}
	public void setPayer_name(String payer_name) {
		this.payer_name = payer_name;
	}
	public String getPayment_mode() {
		return payment_mode;
	}
	public void setPayment_mode(String payment_mode) {
		this.payment_mode = payment_mode;
	}
	public String getPayment_status() {
		return payment_status;
	}
	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}
	public String getCashier_id() {
		return cashier_id;
	}
	public void setCashier_id(String cashier_id) {
		this.cashier_id = cashier_id;
	}
	public BigDecimal getTotal_gst() {
		return total_gst;
	}
	public void setTotal_gst(BigDecimal total_gst) {
		this.total_gst = total_gst;
	}
	public BigDecimal getTotal_amt() {
		return total_amt;
	}
	public void setTotal_amt(BigDecimal total_amt) {
		this.total_amt = total_amt;
	}
	public BigDecimal getSponsored_amt() {
		return sponsored_amt;
	}
	public void setSponsored_amt(BigDecimal sponsored_amt) {
		this.sponsored_amt = sponsored_amt;
	}
	public BigDecimal getNet_patient_amt() {
		return net_patient_amt;
	}
	public void setNet_patient_amt(BigDecimal net_patient_amt) {
		this.net_patient_amt = net_patient_amt;
	}
	public BigDecimal getNet_total_amt() {
		return net_total_amt;
	}
	public void setNet_total_amt(BigDecimal net_total_amt) {
		this.net_total_amt = net_total_amt;
	}
	public BigDecimal getService_tax() {
		return service_tax;
	}
	public void setService_tax(BigDecimal service_tax) {
		this.service_tax = service_tax;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public BigDecimal getTotal_bill_amt() {
		return total_bill_amt;
	}
	public void setTotal_bill_amt(BigDecimal total_bill_amt) {
		this.total_bill_amt = total_bill_amt;
	}
	public String getCard_payment_ref() {
		return card_payment_ref;
	}
	public void setCard_payment_ref(String card_payment_ref) {
		this.card_payment_ref = card_payment_ref;
	}
	public String getDoctor() {
		return doctor;
	}
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	public String getVisit_no() {
		return visit_no;
	}
	public void setVisit_no(String visit_no) {
		this.visit_no = visit_no;
	}
	public Date getVisit_date() {
		return visit_date;
	}
	public void setVisit_date(Date visit_date) {
		this.visit_date = visit_date;
	}
	public String getBill_no() {
		return bill_no;
	}
	public void setBill_no(String bill_no) {
		this.bill_no = bill_no;
	}
	public Date getBill_date() {
		return bill_date;
	}
	public void setBill_date(Date bill_date) {
		this.bill_date = bill_date;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getOp_ref() {
		return op_ref;
	}
	public void setOp_ref(String op_ref) {
		this.op_ref = op_ref;
	}
	public BigDecimal getConsession1() {
		return consession1;
	}
	public void setConsession1(BigDecimal consession1) {
		this.consession1 = consession1;
	}
	public BigDecimal getConsession2() {
		return consession2;
	}
	public void setConsession2(BigDecimal consession2) {
		this.consession2 = consession2;
	}
	public BigDecimal getInsurance() {
		return insurance;
	}
	public void setInsurance(BigDecimal insurance) {
		this.insurance = insurance;
	}
	public BigDecimal getCgst() {
		return cgst;
	}
	public void setCgst(BigDecimal cgst) {
		this.cgst = cgst;
	}
	public BigDecimal getSgst() {
		return sgst;
	}
	public void setSgst(BigDecimal sgst) {
		this.sgst = sgst;
	}
	public BigDecimal getIgst() {
		return igst;
	}
	public void setIgst(BigDecimal igst) {
		this.igst = igst;
	}
	public String getEntry_user() {
		return entry_user;
	}
	public void setEntry_user(String entry_user) {
		this.entry_user = entry_user;
	}
	public Date getEntry_time() {
		return entry_time;
	}
	public void setEntry_time(Date entry_time) {
		this.entry_time = entry_time;
	}
	public String getEntry_channel() {
		return entry_channel;
	}
	public void setEntry_channel(String entry_channel) {
		this.entry_channel = entry_channel;
	}
	public String getModify_user() {
		return modify_user;
	}
	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}
	public Date getModify_time() {
		return modify_time;
	}
	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}
	public String getModify_channel() {
		return modify_channel;
	}
	public void setModify_channel(String modify_channel) {
		this.modify_channel = modify_channel;
	}
	public String getApproval_user() {
		return approval_user;
	}
	public void setApproval_user(String approval_user) {
		this.approval_user = approval_user;
	}
	public Date getApproval_time() {
		return approval_time;
	}
	public void setApproval_time(Date approval_time) {
		this.approval_time = approval_time;
	}
	public String getApproval_channel() {
		return approval_channel;
	}
	public void setApproval_channel(String approval_channel) {
		this.approval_channel = approval_channel;
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
	public String getVerify_flg() {
		return verify_flg;
	}
	public void setVerify_flg(String verify_flg) {
		this.verify_flg = verify_flg;
	}
	public String getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}
	public BHMSPaymentTable(String puin, String receipt_no, String payer_type, BigDecimal amount, String scan,
			BigDecimal scan_amount, String blood_test, BigDecimal blood_test_amount, Date payment_date,
			String payer_name, String payment_mode, String payment_status, String cashier_id, BigDecimal total_gst,
			BigDecimal total_amt, BigDecimal sponsored_amt, BigDecimal net_patient_amt, BigDecimal net_total_amt,
			BigDecimal service_tax, String reference, BigDecimal total_bill_amt, String card_payment_ref, String doctor,
			String visit_no, Date visit_date, String bill_no, Date bill_date, String department, String op_ref,
			BigDecimal consession1, BigDecimal consession2, BigDecimal insurance, BigDecimal cgst, BigDecimal sgst,
			BigDecimal igst, String entry_user, Date entry_time, String entry_channel, String modify_user,
			Date modify_time, String modify_channel, String approval_user, Date approval_time, String approval_channel,
			String entity_flg, String modify_flg, String verify_flg, String del_flg) {
		super();
		this.puin = puin;
		this.receipt_no = receipt_no;
		this.payer_type = payer_type;
		this.amount = amount;
		this.scan = scan;
		this.scan_amount = scan_amount;
		this.blood_test = blood_test;
		this.blood_test_amount = blood_test_amount;
		this.payment_date = payment_date;
		this.payer_name = payer_name;
		this.payment_mode = payment_mode;
		this.payment_status = payment_status;
		this.cashier_id = cashier_id;
		this.total_gst = total_gst;
		this.total_amt = total_amt;
		this.sponsored_amt = sponsored_amt;
		this.net_patient_amt = net_patient_amt;
		this.net_total_amt = net_total_amt;
		this.service_tax = service_tax;
		this.reference = reference;
		this.total_bill_amt = total_bill_amt;
		this.card_payment_ref = card_payment_ref;
		this.doctor = doctor;
		this.visit_no = visit_no;
		this.visit_date = visit_date;
		this.bill_no = bill_no;
		this.bill_date = bill_date;
		this.department = department;
		this.op_ref = op_ref;
		this.consession1 = consession1;
		this.consession2 = consession2;
		this.insurance = insurance;
		this.cgst = cgst;
		this.sgst = sgst;
		this.igst = igst;
		this.entry_user = entry_user;
		this.entry_time = entry_time;
		this.entry_channel = entry_channel;
		this.modify_user = modify_user;
		this.modify_time = modify_time;
		this.modify_channel = modify_channel;
		this.approval_user = approval_user;
		this.approval_time = approval_time;
		this.approval_channel = approval_channel;
		this.entity_flg = entity_flg;
		this.modify_flg = modify_flg;
		this.verify_flg = verify_flg;
		this.del_flg = del_flg;
	}
	public BHMSPaymentTable() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}