package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "item_creation")
public class ItemCreation {

    @Id
    @Column(name = "item_code", nullable = false, unique = true, length = 100)
    private String itemCode;

    @Column(name = "item_name", length = 100)
    private String itemName;

    @Column(name = "hsn_code", length = 100)
    private String hsnCode;

    @Column(name = "category", length = 100)
    private String category;

    @Column(name = "base_unit", length = 100)
    private String baseUnit;

    @Lob
    @Column(name = "uploadeimage")
    private byte[] uploadeImage;

    @Column(name = "saleprice")
    private Long salePrice;

    @Column(name = "saletaxtype", length = 100)
    private String saleTaxType;

    @Column(name = "discounttype", length = 100)
    private String discountType;

    @Column(name = "discountprice")
    private Long discountPrice;

    @Column(name = "wholesaleprice")
    private BigDecimal wholesalePrice;

    @Column(name = "wholesaletax", length = 100)
    private String wholesaleTax;

    @Column(name = "minimumwholesaleqty")
    private Long minimumWholesaleQty;

    @Column(name = "purchaseprice")
    private Long purchasePrice;

    @Column(name = "purchasetaxtype", length = 100)
    private String purchaseTaxType;

    @Column(name = "openingquantity")
    private Long openingQuantity;

    @Column(name = "atprice")
    private Long atPrice;

    @Column(name = "asofdate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date asOfDate;

    @Column(name = "minstocktomaintain")
    private Long minStockToMaintain;
    
    
    @Column(name = "location", length = 100)
    private String location;

    @Column(name = "entity_flg", length = 100)
    private String entityFlag;

    @Column(name = "modify_flg", length = 100)
    private String modifyFlag;

    @Column(name = "verify_flg", length = 50)
    private String verifyFlag;

    @Column(name = "entry_user", length = 50)
    private String entryUser;

    @Column(name = "del_flg", length = 50)
    private String deleteFlag;
    
    @Column(name = "quantity", length = 50)
    private BigDecimal quantity;
    
    @Column(name = "gstpercentagesale", length = 50)
    private String gstpercentagesale;
    
    @Column(name = "gstpercentageprice", length = 50)
    private String gstpercentageprice;
    
    @Column(name = "Unit", length = 50)
    private String Unit;
    
    @Column(name = "secondaryunit", length = 50)
    private String secondaryunit;
    
    @Column(name = "Conversion", length = 50)
    private String Conversion;
    
    @Column(name = "salepricetype", length = 50)
    private String salepricetype;
    
    @Column(name = "DistributorPrice", length = 50)
    private BigDecimal DistributorPrice;
    
    @Column(name = "RetailerPrice", length = 50)
    private BigDecimal RetailerPrice;
    
    @Column(name = "CustomerPrice", length = 50)
    private BigDecimal CustomerPrice;
    
    @Column(name = "SSPrice", length = 50)
    private BigDecimal SSPrice;

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBaseUnit() {
		return baseUnit;
	}

	public void setBaseUnit(String baseUnit) {
		this.baseUnit = baseUnit;
	}

	public byte[] getUploadeImage() {
		return uploadeImage;
	}

	public void setUploadeImage(byte[] uploadeImage) {
		this.uploadeImage = uploadeImage;
	}

	public Long getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Long salePrice) {
		this.salePrice = salePrice;
	}

	public String getSaleTaxType() {
		return saleTaxType;
	}

	public void setSaleTaxType(String saleTaxType) {
		this.saleTaxType = saleTaxType;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public Long getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(Long discountPrice) {
		this.discountPrice = discountPrice;
	}

	public BigDecimal getWholesalePrice() {
		return wholesalePrice;
	}

	public void setWholesalePrice(BigDecimal wholesalePrice) {
		this.wholesalePrice = wholesalePrice;
	}

	public String getWholesaleTax() {
		return wholesaleTax;
	}

	public void setWholesaleTax(String wholesaleTax) {
		this.wholesaleTax = wholesaleTax;
	}

	public Long getMinimumWholesaleQty() {
		return minimumWholesaleQty;
	}

	public void setMinimumWholesaleQty(Long minimumWholesaleQty) {
		this.minimumWholesaleQty = minimumWholesaleQty;
	}

	public Long getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Long purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public String getPurchaseTaxType() {
		return purchaseTaxType;
	}

	public void setPurchaseTaxType(String purchaseTaxType) {
		this.purchaseTaxType = purchaseTaxType;
	}

	public Long getOpeningQuantity() {
		return openingQuantity;
	}

	public void setOpeningQuantity(Long openingQuantity) {
		this.openingQuantity = openingQuantity;
	}

	public Long getAtPrice() {
		return atPrice;
	}

	public void setAtPrice(Long atPrice) {
		this.atPrice = atPrice;
	}

	public Date getAsOfDate() {
		return asOfDate;
	}

	public void setAsOfDate(Date asOfDate) {
		this.asOfDate = asOfDate;
	}

	public Long getMinStockToMaintain() {
		return minStockToMaintain;
	}

	public void setMinStockToMaintain(Long minStockToMaintain) {
		this.minStockToMaintain = minStockToMaintain;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEntityFlag() {
		return entityFlag;
	}

	public void setEntityFlag(String entityFlag) {
		this.entityFlag = entityFlag;
	}

	public String getModifyFlag() {
		return modifyFlag;
	}

	public void setModifyFlag(String modifyFlag) {
		this.modifyFlag = modifyFlag;
	}

	public String getVerifyFlag() {
		return verifyFlag;
	}

	public void setVerifyFlag(String verifyFlag) {
		this.verifyFlag = verifyFlag;
	}

	public String getEntryUser() {
		return entryUser;
	}

	public void setEntryUser(String entryUser) {
		this.entryUser = entryUser;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public String getGstpercentagesale() {
		return gstpercentagesale;
	}

	public void setGstpercentagesale(String gstpercentagesale) {
		this.gstpercentagesale = gstpercentagesale;
	}

	public String getGstpercentageprice() {
		return gstpercentageprice;
	}

	public void setGstpercentageprice(String gstpercentageprice) {
		this.gstpercentageprice = gstpercentageprice;
	}

	public String getUnit() {
		return Unit;
	}

	public void setUnit(String unit) {
		Unit = unit;
	}

	public String getSecondaryunit() {
		return secondaryunit;
	}

	public void setSecondaryunit(String secondaryunit) {
		this.secondaryunit = secondaryunit;
	}

	public String getConversion() {
		return Conversion;
	}

	public void setConversion(String conversion) {
		Conversion = conversion;
	}

	public String getSalepricetype() {
		return salepricetype;
	}

	public void setSalepricetype(String salepricetype) {
		this.salepricetype = salepricetype;
	}

	public BigDecimal getDistributorPrice() {
		return DistributorPrice;
	}

	public void setDistributorPrice(BigDecimal distributorPrice) {
		DistributorPrice = distributorPrice;
	}

	public BigDecimal getRetailerPrice() {
		return RetailerPrice;
	}

	public void setRetailerPrice(BigDecimal retailerPrice) {
		RetailerPrice = retailerPrice;
	}

	public BigDecimal getCustomerPrice() {
		return CustomerPrice;
	}

	public void setCustomerPrice(BigDecimal customerPrice) {
		CustomerPrice = customerPrice;
	}

	public BigDecimal getSSPrice() {
		return SSPrice;
	}

	public void setSSPrice(BigDecimal sSPrice) {
		SSPrice = sSPrice;
	}

	public ItemCreation(String itemCode, String itemName, String hsnCode, String category, String baseUnit,
			byte[] uploadeImage, Long salePrice, String saleTaxType, String discountType, Long discountPrice,
			BigDecimal wholesalePrice, String wholesaleTax, Long minimumWholesaleQty, Long purchasePrice,
			String purchaseTaxType, Long openingQuantity, Long atPrice, Date asOfDate, Long minStockToMaintain,
			String location, String entityFlag, String modifyFlag, String verifyFlag, String entryUser,
			String deleteFlag, BigDecimal quantity, String gstpercentagesale, String gstpercentageprice, String unit,
			String secondaryunit, String conversion, String salepricetype, BigDecimal distributorPrice,
			BigDecimal retailerPrice, BigDecimal customerPrice, BigDecimal sSPrice) {
		super();
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.hsnCode = hsnCode;
		this.category = category;
		this.baseUnit = baseUnit;
		this.uploadeImage = uploadeImage;
		this.salePrice = salePrice;
		this.saleTaxType = saleTaxType;
		this.discountType = discountType;
		this.discountPrice = discountPrice;
		this.wholesalePrice = wholesalePrice;
		this.wholesaleTax = wholesaleTax;
		this.minimumWholesaleQty = minimumWholesaleQty;
		this.purchasePrice = purchasePrice;
		this.purchaseTaxType = purchaseTaxType;
		this.openingQuantity = openingQuantity;
		this.atPrice = atPrice;
		this.asOfDate = asOfDate;
		this.minStockToMaintain = minStockToMaintain;
		this.location = location;
		this.entityFlag = entityFlag;
		this.modifyFlag = modifyFlag;
		this.verifyFlag = verifyFlag;
		this.entryUser = entryUser;
		this.deleteFlag = deleteFlag;
		this.quantity = quantity;
		this.gstpercentagesale = gstpercentagesale;
		this.gstpercentageprice = gstpercentageprice;
		Unit = unit;
		this.secondaryunit = secondaryunit;
		Conversion = conversion;
		this.salepricetype = salepricetype;
		DistributorPrice = distributorPrice;
		RetailerPrice = retailerPrice;
		CustomerPrice = customerPrice;
		SSPrice = sSPrice;
	}

	public ItemCreation() {
		super();
		// TODO Auto-generated constructor stub
	}

	
    

}
