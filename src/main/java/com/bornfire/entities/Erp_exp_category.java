package com.bornfire.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ERP_EXPEN_CATEGORY")
public class Erp_exp_category {
	
	  @Id
	    @Column(name = "CAT_ID", nullable = false, length = 100)
	    private String catId;

	    @Column(name = "CAT_NAME", length = 100)
	    private String catName;

	    @Column(name = "DEL_FLG", length = 1)
	    private String delFlg;
	    
	    @Column(name = "EXP_TYPE", length = 100)
	    private String exptype;

		public String getCatId() {
			return catId;
		}

		public void setCatId(String catId) {
			this.catId = catId;
		}

		public String getCatName() {
			return catName;
		}

		public void setCatName(String catName) {
			this.catName = catName;
		}

		public String getDelFlg() {
			return delFlg;
		}

		public void setDelFlg(String delFlg) {
			this.delFlg = delFlg;
		}

		public String getExptype() {
			return exptype;
		}

		public void setExptype(String exptype) {
			this.exptype = exptype;
		}

		public Erp_exp_category(String catId, String catName, String delFlg, String exptype) {
			super();
			this.catId = catId;
			this.catName = catName;
			this.delFlg = delFlg;
			this.exptype = exptype;
		}

		public Erp_exp_category() {
			super();
			// TODO Auto-generated constructor stub
		}

		
	

}
