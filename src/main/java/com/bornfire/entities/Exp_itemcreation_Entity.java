package com.bornfire.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EXP_ITEM_CREATION")
public class Exp_itemcreation_Entity {
	
	
	   @Id
	    @Column(name = "EXP_ITEM_ID", length = 100)
	    private String expItemId;

	    @Column(name = "EXP_ITEM_NAME", length = 100)
	    private String expItemName;

	    @Column(name = "EXP_ITEM_PRICE", precision = 24, scale = 2)
	    private Double expItemPrice;
	    
	    @Column(name = "ITEM_AMT", precision = 24, scale = 2)
	    private Double item_amt;

	    @Column(name = "DEL_FLG", length = 1)
	    private String delFlg;

		public String getExpItemId() {
			return expItemId;
		}

		public void setExpItemId(String expItemId) {
			this.expItemId = expItemId;
		}

		public String getExpItemName() {
			return expItemName;
		}

		public void setExpItemName(String expItemName) {
			this.expItemName = expItemName;
		}

		public Double getExpItemPrice() {
			return expItemPrice;
		}

		public void setExpItemPrice(Double expItemPrice) {
			this.expItemPrice = expItemPrice;
		}

		public Double getItem_amt() {
			return item_amt;
		}

		public void setItem_amt(Double item_amt) {
			this.item_amt = item_amt;
		}

		public String getDelFlg() {
			return delFlg;
		}

		public void setDelFlg(String delFlg) {
			this.delFlg = delFlg;
		}

		public Exp_itemcreation_Entity(String expItemId, String expItemName, Double expItemPrice, Double item_amt,
				String delFlg) {
			super();
			this.expItemId = expItemId;
			this.expItemName = expItemName;
			this.expItemPrice = expItemPrice;
			this.item_amt = item_amt;
			this.delFlg = delFlg;
		}

		public Exp_itemcreation_Entity() {
			super();
			// TODO Auto-generated constructor stub
		}

		
	    

}
