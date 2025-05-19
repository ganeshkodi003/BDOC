package com.bornfire.entities;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "TRAINING")
public class Training {

    @Id
    @Column(name = "srl_no", nullable = false)
    private Long srlNo;

    @Column(name = "ADM_ID", length = 100)
    private String adminId;

    @Column(name = "ADM_NAME", length = 100)
    private String adminName;

    public Training() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Training(Long srlNo, String adminId, String adminName, String traineeName, String traineeDept,
			String trainName, String trainDesc, Date startDate, Date endDate, String empId, String empName,
			String empEmail, String empRole, String trainFlg, byte[] document, String fileName, String fileType) {
		super();
		this.srlNo = srlNo;
		this.adminId = adminId;
		this.adminName = adminName;
		this.traineeName = traineeName;
		this.traineeDept = traineeDept;
		this.trainName = trainName;
		this.trainDesc = trainDesc;
		this.startDate = startDate;
		this.endDate = endDate;
		this.empId = empId;
		this.empName = empName;
		this.empEmail = empEmail;
		this.empRole = empRole;
		this.trainFlg = trainFlg;
		this.document = document;
		this.fileName = fileName;
		this.fileType = fileType;
	}

	@Column(name = "TRAINEE_NAME", length = 100)
    private String traineeName;

    @Column(name = "TRAINEE_DEPT", length = 50)
    private String traineeDept;

    @Column(name = "TRAIN_NAME", length = 100)
    private String trainName;

    @Column(name = "TRAIN_DESC", length = 500)
    private String trainDesc;

    @Column(name = "STARTDATE")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date startDate;

    @Column(name = "ENDDATE")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date endDate;

    @Column(name = "EMP_ID", length = 50)
    private String empId;

    @Column(name = "EMP_NAME", length = 100)
    private String empName;

    @Column(name = "EMP_EMAIL", length = 100)
    private String empEmail;

    @Column(name = "EMP_ROLE", length = 50)
    private String empRole;

    @Column(name = "TRAIN_FLG", length = 50)
    private String trainFlg;

    @Lob
    @Column(name = "DOCUMENT")
    private byte[] document;

    @Column(name = "FILENAME", length = 100)
    private String fileName;

    @Column(name = "FILETYPE", length = 100)
    private String fileType;

    // Getters and Setters
    public Long getSrlNo() {
        return srlNo;
    }

    public void setSrlNo(Long srlNo) {
        this.srlNo = srlNo;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getTraineeName() {
        return traineeName;
    }

    public void setTraineeName(String traineeName) {
        this.traineeName = traineeName;
    }

    public String getTraineeDept() {
        return traineeDept;
    }

    public void setTraineeDept(String traineeDept) {
        this.traineeDept = traineeDept;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getTrainDesc() {
        return trainDesc;
    }

    public void setTrainDesc(String trainDesc) {
        this.trainDesc = trainDesc;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public String getEmpRole() {
        return empRole;
    }

    public void setEmpRole(String empRole) {
        this.empRole = empRole;
    }

    public String getTrainFlg() {
        return trainFlg;
    }

    public void setTrainFlg(String trainFlg) {
        this.trainFlg = trainFlg;
    }

    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
