package com.bornfire.entities;


import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "BTM_TRAINING")
public class BtmTraining {

    @Id
    @Column(name = "SRL_NO")
    private int srlNo;

    @Column(name = "EMPLOYEE_NAME")
    private String employeeName;

    @Column(name = "EMPLOYEE_ID")
    private String employeeId;

    @Column(name = "REPORTING_MANAGER")
    private String reportingManager;

    @Column(name = "ASSIGN_DATE")
    @Temporal(TemporalType.DATE)
    private Date assignDate;

    @Column(name = "SUBMISSION_DATE")
    @Temporal(TemporalType.DATE)
    private Date submissionDate;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "ADMIN_ID")
    private String adminId;

    @Column(name = "ADMIN_NAME")
    private String adminName;

    @Column(name = "TRAINEE_NAME")
    private String traineeName;

    @Column(name = "TRAINEE_DEPT")
    private String traineeDept;

    @Column(name = "TRAINING_NAME")
    private String trainingName;

    @Column(name = "TRAINING_DESCRIPTION")
    private String trainingDescription;

    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "END_DATE")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(name = "ASSIGN_EMPLOYEE_ID")
    private String assignEmployeeId;

    @Column(name = "ASSIGN_EMPLOYEE_NAME")
    private String assignEmployeeName;

    @Column(name = "ASSIGN_EMPLOYEE_EMAIL")
    private String assignEmployeeEmail;

    @Column(name = "ASSIGN_EMPLOYEE_ROLE")
    private String assignEmployeeRole;

    @Column(name = "FLAG_UPLOAD_FLAG")
    private char flagUploadFlag;

    @Column(name = "FILE_UPLOAD")
    private Blob fileUpload;

    @Column(name = "MODIFY_FLAG")
    private char modifyFlag;

    @Column(name = "VERIFY_FLAG")
    private char verifyFlag;

    @Column(name = "DELETE_FLAG")
    private char deleteFlag;

    // Default Constructor
    public BtmTraining() {
    }

    // Parameterized Constructor
    public BtmTraining(int srlNo, String employeeName, String employeeId, String reportingManager,
                       Date assignDate, Date submissionDate, String status, String adminId,
                       String adminName, String traineeName, String traineeDept, String trainingName,
                       String trainingDescription, Date startDate, Date endDate, String assignEmployeeId,
                       String assignEmployeeName, String assignEmployeeEmail, String assignEmployeeRole,
                       char flagUploadFlag, Blob fileUpload, char modifyFlag, char verifyFlag, char deleteFlag) {
        this.srlNo = srlNo;
        this.employeeName = employeeName;
        this.employeeId = employeeId;
        this.reportingManager = reportingManager;
        this.assignDate = assignDate;
        this.submissionDate = submissionDate;
        this.status = status;
        this.adminId = adminId;
        this.adminName = adminName;
        this.traineeName = traineeName;
        this.traineeDept = traineeDept;
        this.trainingName = trainingName;
        this.trainingDescription = trainingDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.assignEmployeeId = assignEmployeeId;
        this.assignEmployeeName = assignEmployeeName;
        this.assignEmployeeEmail = assignEmployeeEmail;
        this.assignEmployeeRole = assignEmployeeRole;
        this.flagUploadFlag = flagUploadFlag;
        this.fileUpload = fileUpload;
        this.modifyFlag = modifyFlag;
        this.verifyFlag = verifyFlag;
        this.deleteFlag = deleteFlag;
    }
    public int getSrlNo() {
        return srlNo;
    }

    public void setSrlNo(int srlNo) {
        this.srlNo = srlNo;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getReportingManager() {
        return reportingManager;
    }

    public void setReportingManager(String reportingManager) {
        this.reportingManager = reportingManager;
    }

    public Date getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public String getTrainingDescription() {
        return trainingDescription;
    }

    public void setTrainingDescription(String trainingDescription) {
        this.trainingDescription = trainingDescription;
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

    public String getAssignEmployeeId() {
        return assignEmployeeId;
    }

    public void setAssignEmployeeId(String assignEmployeeId) {
        this.assignEmployeeId = assignEmployeeId;
    }

    public String getAssignEmployeeName() {
        return assignEmployeeName;
    }

    public void setAssignEmployeeName(String assignEmployeeName) {
        this.assignEmployeeName = assignEmployeeName;
    }

    public String getAssignEmployeeEmail() {
        return assignEmployeeEmail;
    }

    public void setAssignEmployeeEmail(String assignEmployeeEmail) {
        this.assignEmployeeEmail = assignEmployeeEmail;
    }

    public String getAssignEmployeeRole() {
        return assignEmployeeRole;
    }

    public void setAssignEmployeeRole(String assignEmployeeRole) {
        this.assignEmployeeRole = assignEmployeeRole;
    }

    public char getFlagUploadFlag() {
        return flagUploadFlag;
    }

    public void setFlagUploadFlag(char flagUploadFlag) {
        this.flagUploadFlag = flagUploadFlag;
    }

    public Blob getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(Blob fileUpload) {
        this.fileUpload = fileUpload;
    }

    public char getModifyFlag() {
        return modifyFlag;
    }

    public void setModifyFlag(char modifyFlag) {
        this.modifyFlag = modifyFlag;
    }

    public char getVerifyFlag() {
        return verifyFlag;
    }

    public void setVerifyFlag(char verifyFlag) {
        this.verifyFlag = verifyFlag;
    }

    public char getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(char deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}

