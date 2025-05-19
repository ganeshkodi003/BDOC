package com.bornfire.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "BHMS_INV_PRODUCT_SALE_MASTER")
@IdClass(BhmsInvProductSaleMasterTableID.class)

public class BhmsInvProductSaleMasterTable implements Serializable{
	
	@Id
	private String sale_id;
	
	@Id
	private String sub_sale_id;
	
	private String batch;
	private String expiry_date;
	private String cust_name;
	private String year;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonFormat(pattern="dd-MM-yyyy")

	private Date date_of_sale;
	private String product_name;
	private String category_name;
	private String pkg;
	private String current_stock;
	private BigDecimal mrp_rate;
	private BigDecimal sale_rate;
	private String quantity;
	private BigDecimal price;
	private BigDecimal total;
	private String payment_by;
	private String remarks;
	private BigDecimal given_amount;
	private BigDecimal remaining_amount;
	private BigDecimal balance_amount;
	
	private String entry_user;
	private String entry_time;
	private String entity_flg;
	public String getSale_id() {
		return sale_id;
	}
	public void setSale_id(String sale_id) {
		this.sale_id = sale_id;
	}
	public String getSub_sale_id() {
		return sub_sale_id;
	}
	public void setSub_sale_id(String sub_sale_id) {
		this.sub_sale_id = sub_sale_id;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getExpiry_date() {
		return expiry_date;
	}
	public void setExpiry_date(String expiry_date) {
		this.expiry_date = expiry_date;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Date getDate_of_sale() {
		return date_of_sale;
	}
	public void setDate_of_sale(Date date_of_sale) {
		this.date_of_sale = date_of_sale;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getPkg() {
		return pkg;
	}
	public void setPkg(String pkg) {
		this.pkg = pkg;
	}
	public String getCurrent_stock() {
		return current_stock;
	}
	public void setCurrent_stock(String current_stock) {
		this.current_stock = current_stock;
	}
	public BigDecimal getMrp_rate() {
		return mrp_rate;
	}
	public void setMrp_rate(BigDecimal mrp_rate) {
		this.mrp_rate = mrp_rate;
	}
	public BigDecimal getSale_rate() {
		return sale_rate;
	}
	public void setSale_rate(BigDecimal sale_rate) {
		this.sale_rate = sale_rate;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public String getPayment_by() {
		return payment_by;
	}
	public void setPayment_by(String payment_by) {
		this.payment_by = payment_by;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public BigDecimal getGiven_amount() {
		return given_amount;
	}
	public void setGiven_amount(BigDecimal given_amount) {
		this.given_amount = given_amount;
	}
	public BigDecimal getRemaining_amount() {
		return remaining_amount;
	}
	public void setRemaining_amount(BigDecimal remaining_amount) {
		this.remaining_amount = remaining_amount;
	}
	public BigDecimal getBalance_amount() {
		return balance_amount;
	}
	public void setBalance_amount(BigDecimal balance_amount) {
		this.balance_amount = balance_amount;
	}
	public String getEntry_user() {
		return entry_user;
	}
	public void setEntry_user(String entry_user) {
		this.entry_user = entry_user;
	}
	public String getEntry_time() {
		return entry_time;
	}
	public void setEntry_time(String entry_time) {
		this.entry_time = entry_time;
	}
	public String getEntity_flg() {
		return entity_flg;
	}
	public void setEntity_flg(String entity_flg) {
		this.entity_flg = entity_flg;
	}
	@Override
	public String toString() {
		return "BhmsInvProductSaleMasterTable [sale_id=" + sale_id + ", sub_sale_id=" + sub_sale_id + ", batch=" + batch
				+ ", expiry_date=" + expiry_date + ", cust_name=" + cust_name + ", year=" + year + ", date_of_sale="
				+ date_of_sale + ", product_name=" + product_name + ", category_name=" + category_name + ", pkg=" + pkg
				+ ", current_stock=" + current_stock + ", mrp_rate=" + mrp_rate + ", sale_rate=" + sale_rate
				+ ", quantity=" + quantity + ", price=" + price + ", total=" + total + ", payment_by=" + payment_by
				+ ", remarks=" + remarks + ", given_amount=" + given_amount + ", remaining_amount=" + remaining_amount
				+ ", balance_amount=" + balance_amount + ", entry_user=" + entry_user + ", entry_time=" + entry_time
				+ ", entity_flg=" + entity_flg + "]";
	}
	public BhmsInvProductSaleMasterTable(String sale_id, String sub_sale_id, String batch, String expiry_date,
			String cust_name, String year, Date date_of_sale, String product_name, String category_name, String pkg,
			String current_stock, BigDecimal mrp_rate, BigDecimal sale_rate, String quantity, BigDecimal price,
			BigDecimal total, String payment_by, String remarks, BigDecimal given_amount, BigDecimal remaining_amount,
			BigDecimal balance_amount, String entry_user, String entry_time, String entity_flg) {
		super();
		this.sale_id = sale_id;
		this.sub_sale_id = sub_sale_id;
		this.batch = batch;
		this.expiry_date = expiry_date;
		this.cust_name = cust_name;
		this.year = year;
		this.date_of_sale = date_of_sale;
		this.product_name = product_name;
		this.category_name = category_name;
		this.pkg = pkg;
		this.current_stock = current_stock;
		this.mrp_rate = mrp_rate;
		this.sale_rate = sale_rate;
		this.quantity = quantity;
		this.price = price;
		this.total = total;
		this.payment_by = payment_by;
		this.remarks = remarks;
		this.given_amount = given_amount;
		this.remaining_amount = remaining_amount;
		this.balance_amount = balance_amount;
		this.entry_user = entry_user;
		this.entry_time = entry_time;
		this.entity_flg = entity_flg;
	}
	public BhmsInvProductSaleMasterTable() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
