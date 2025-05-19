package com.bornfire.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class BhmsInvProductSaleMasterTableID implements Serializable {

	private String sale_id;
	
	private String sub_sale_id;

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



		
	
}