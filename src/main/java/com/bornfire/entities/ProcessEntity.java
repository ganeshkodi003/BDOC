package com.bornfire.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PROCESS_TABLE") 
public class ProcessEntity {

	@Id
    @Column(name = "PROCESS_ID")
    private String processId;

    @Column(name = "PROCESS_MENU")
    private String processMenu;

    @Column(name = "PROCESS_DESCRIPTION")
    private String processDescription;

    @Column(name = "REMARKS")
    private String remarks;

    @Column(name = "DEL_FLG")
    private String delFlg;

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getProcessMenu() {
		return processMenu;
	}

	public void setProcessMenu(String processMenu) {
		this.processMenu = processMenu;
	}

	public String getProcessDescription() {
		return processDescription;
	}

	public void setProcessDescription(String processDescription) {
		this.processDescription = processDescription;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
	}

	public ProcessEntity(String processId, String processMenu, String processDescription, String remarks,
			String delFlg) {
		super();
		this.processId = processId;
		this.processMenu = processMenu;
		this.processDescription = processDescription;
		this.remarks = remarks;
		this.delFlg = delFlg;
	}

}
