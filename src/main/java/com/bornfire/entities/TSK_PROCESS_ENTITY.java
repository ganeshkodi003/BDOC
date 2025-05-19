package com.bornfire.entities;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "TSK_PROCESS_TABLE")
public class TSK_PROCESS_ENTITY {

    @Id
    @Column(name = "ID")
    private String id;
    
	@Column(name = "PROCESS_ID")
    private String processId;

	@Column(name = "STATUS")
    private String status;

	@Column(name = "bomid")
    private String bomid;

    @Column(name = "kilograms")
    private String kilograms;

    @Column(name = "units")
    private String units;

    @Column(name = "itemCode")
    private String itemCode;

    @Column(name = "itemname")
    private String itemname;

    @Column(name = "category_name1")
    private String category_name1;

    @Column(name = "Quantity")
    private BigDecimal quantity;

    @Column(name = "stock_qty")
    private BigDecimal stock_qty;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "reqqnt")
    private BigDecimal reqqnt;

    @Column(name = "totalprice")
    private BigDecimal totalprice;

    @Column(name = "batch")
    private String batch;

    @Column(name = "ExpensesType")
    private String expensestype;

    @Column(name = "per_Amount")
    private BigDecimal per_Amount;

    @Column(name = "totalexpcost")
    private BigDecimal totalexpcost;

    @Column(name = "makingproductName")
    private String makingproductName;

    @Column(name = "grantTotal")
    private String grantTotal;

    @Column(name = "entity_flg")
    private String entityFlg;

    @Column(name = "del_flg")
    private String delFlg;

    @Column(name = "verify_flg")
    private String verifyFlg;

    @Column(name = "modify_flg")
    private String modifyFlg;

    @Column(name = "process_start_date")  
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date processStartDate;

    @Column(name = "process_end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date processEndDate;
    
    @Column(name = "baseunit")
    private String baseunit;
    
    @Column(name = "returnquantity")
    private BigDecimal returnquantity;

    @Column(name = "useingquantity")
    private BigDecimal useingquantity;
    
    @Column(name = "returnquantityunit")
    private String returnquantityunit;

    @Column(name="BRANCH_ID",length=50)
     private String branchId;
    
    @Column(name="outtern",length=50)
    private BigDecimal outtern;
    
    @Column(name="Kasadu",length=50)
    private BigDecimal kasadu;
    
    @Column(name="Ghee",length=50)
    private BigDecimal ghee;
    
    @Column(name="totalKasadu",length=50)
    private BigDecimal totalkasadu;
    
    @Column(name="totalGhee",length=50)
    private BigDecimal totalghee;
    
    @Column(name="Materialtotalvalue",length=50)
    private BigDecimal materialtotalvalue;
    
    @Column(name="Expensesgrandtotal",length=50)
    private BigDecimal expensesgrandtotal;
    
    @Column(name="Actualkasadu",length=50)
    private BigDecimal actualkasadu ;
    
    @Column(name="differghee",length=50)
    private BigDecimal differghee;
    
    @Column(name="differkasadu",length=50)
    private BigDecimal differkasadu;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBomid() {
		return bomid;
	}

	public void setBomid(String bomid) {
		this.bomid = bomid;
	}

	public String getKilograms() {
		return kilograms;
	}

	public void setKilograms(String kilograms) {
		this.kilograms = kilograms;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public String getCategory_name1() {
		return category_name1;
	}

	public void setCategory_name1(String category_name1) {
		this.category_name1 = category_name1;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getStock_qty() {
		return stock_qty;
	}

	public void setStock_qty(BigDecimal stock_qty) {
		this.stock_qty = stock_qty;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getReqqnt() {
		return reqqnt;
	}

	public void setReqqnt(BigDecimal reqqnt) {
		this.reqqnt = reqqnt;
	}

	public BigDecimal getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(BigDecimal totalprice) {
		this.totalprice = totalprice;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getExpensestype() {
		return expensestype;
	}

	public void setExpensestype(String expensestype) {
		this.expensestype = expensestype;
	}

	public BigDecimal getPer_Amount() {
		return per_Amount;
	}

	public void setPer_Amount(BigDecimal per_Amount) {
		this.per_Amount = per_Amount;
	}

	public BigDecimal getTotalexpcost() {
		return totalexpcost;
	}

	public void setTotalexpcost(BigDecimal totalexpcost) {
		this.totalexpcost = totalexpcost;
	}

	public String getMakingproductName() {
		return makingproductName;
	}

	public void setMakingproductName(String makingproductName) {
		this.makingproductName = makingproductName;
	}

	public String getGrantTotal() {
		return grantTotal;
	}

	public void setGrantTotal(String grantTotal) {
		this.grantTotal = grantTotal;
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

	public String getVerifyFlg() {
		return verifyFlg;
	}

	public void setVerifyFlg(String verifyFlg) {
		this.verifyFlg = verifyFlg;
	}

	public String getModifyFlg() {
		return modifyFlg;
	}

	public void setModifyFlg(String modifyFlg) {
		this.modifyFlg = modifyFlg;
	}

	public Date getProcessStartDate() {
		return processStartDate;
	}

	public void setProcessStartDate(Date processStartDate) {
		this.processStartDate = processStartDate;
	}

	public Date getProcessEndDate() {
		return processEndDate;
	}

	public void setProcessEndDate(Date processEndDate) {
		this.processEndDate = processEndDate;
	}

	public String getBaseunit() {
		return baseunit;
	}

	public void setBaseunit(String baseunit) {
		this.baseunit = baseunit;
	}

	public BigDecimal getReturnquantity() {
		return returnquantity;
	}

	public void setReturnquantity(BigDecimal returnquantity) {
		this.returnquantity = returnquantity;
	}

	public BigDecimal getUseingquantity() {
		return useingquantity;
	}

	public void setUseingquantity(BigDecimal useingquantity) {
		this.useingquantity = useingquantity;
	}

	public String getReturnquantityunit() {
		return returnquantityunit;
	}

	public void setReturnquantityunit(String returnquantityunit) {
		this.returnquantityunit = returnquantityunit;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public BigDecimal getOuttern() {
		return outtern;
	}

	public void setOuttern(BigDecimal outtern) {
		this.outtern = outtern;
	}

	public BigDecimal getKasadu() {
		return kasadu;
	}

	public void setKasadu(BigDecimal kasadu) {
		this.kasadu = kasadu;
	}

	public BigDecimal getGhee() {
		return ghee;
	}

	public void setGhee(BigDecimal ghee) {
		this.ghee = ghee;
	}

	public BigDecimal getTotalkasadu() {
		return totalkasadu;
	}

	public void setTotalkasadu(BigDecimal totalkasadu) {
		this.totalkasadu = totalkasadu;
	}

	public BigDecimal getTotalghee() {
		return totalghee;
	}

	public void setTotalghee(BigDecimal totalghee) {
		this.totalghee = totalghee;
	}

	public BigDecimal getMaterialtotalvalue() {
		return materialtotalvalue;
	}

	public void setMaterialtotalvalue(BigDecimal materialtotalvalue) {
		this.materialtotalvalue = materialtotalvalue;
	}

	public BigDecimal getExpensesgrandtotal() {
		return expensesgrandtotal;
	}

	public void setExpensesgrandtotal(BigDecimal expensesgrandtotal) {
		this.expensesgrandtotal = expensesgrandtotal;
	}

	public BigDecimal getActualkasadu() {
		return actualkasadu;
	}

	public void setActualkasadu(BigDecimal actualkasadu) {
		this.actualkasadu = actualkasadu;
	}

	public BigDecimal getDifferghee() {
		return differghee;
	}

	public void setDifferghee(BigDecimal differghee) {
		this.differghee = differghee;
	}

	public BigDecimal getDifferkasadu() {
		return differkasadu;
	}

	public void setDifferkasadu(BigDecimal differkasadu) {
		this.differkasadu = differkasadu;
	}

	public TSK_PROCESS_ENTITY(String id, String processId, String status, String bomid, String kilograms, String units,
			String itemCode, String itemname, String category_name1, BigDecimal quantity, BigDecimal stock_qty,
			BigDecimal price, BigDecimal reqqnt, BigDecimal totalprice, String batch, String expensestype,
			BigDecimal per_Amount, BigDecimal totalexpcost, String makingproductName, String grantTotal,
			String entityFlg, String delFlg, String verifyFlg, String modifyFlg, Date processStartDate,
			Date processEndDate, String baseunit, BigDecimal returnquantity, BigDecimal useingquantity,
			String returnquantityunit, String branchId, BigDecimal outtern, BigDecimal kasadu, BigDecimal ghee,
			BigDecimal totalkasadu, BigDecimal totalghee, BigDecimal materialtotalvalue, BigDecimal expensesgrandtotal,
			BigDecimal actualkasadu, BigDecimal differghee, BigDecimal differkasadu) {
		super();
		this.id = id;
		this.processId = processId;
		this.status = status;
		this.bomid = bomid;
		this.kilograms = kilograms;
		this.units = units;
		this.itemCode = itemCode;
		this.itemname = itemname;
		this.category_name1 = category_name1;
		this.quantity = quantity;
		this.stock_qty = stock_qty;
		this.price = price;
		this.reqqnt = reqqnt;
		this.totalprice = totalprice;
		this.batch = batch;
		this.expensestype = expensestype;
		this.per_Amount = per_Amount;
		this.totalexpcost = totalexpcost;
		this.makingproductName = makingproductName;
		this.grantTotal = grantTotal;
		this.entityFlg = entityFlg;
		this.delFlg = delFlg;
		this.verifyFlg = verifyFlg;
		this.modifyFlg = modifyFlg;
		this.processStartDate = processStartDate;
		this.processEndDate = processEndDate;
		this.baseunit = baseunit;
		this.returnquantity = returnquantity;
		this.useingquantity = useingquantity;
		this.returnquantityunit = returnquantityunit;
		this.branchId = branchId;
		this.outtern = outtern;
		this.kasadu = kasadu;
		this.ghee = ghee;
		this.totalkasadu = totalkasadu;
		this.totalghee = totalghee;
		this.materialtotalvalue = materialtotalvalue;
		this.expensesgrandtotal = expensesgrandtotal;
		this.actualkasadu = actualkasadu;
		this.differghee = differghee;
		this.differkasadu = differkasadu;
	}

	public TSK_PROCESS_ENTITY() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
   

	
}
