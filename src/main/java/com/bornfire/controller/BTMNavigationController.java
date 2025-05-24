package com.bornfire.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import com.bornfire.config.AES;
import com.bornfire.entities.*;
import com.bornfire.services.AdminOperServices;
import com.bornfire.services.AttendanceRegisterService;
import com.bornfire.services.BHMS_Services;
import com.bornfire.services.BankDetailService;
import com.bornfire.services.ExcelUploadService;
import com.bornfire.services.FileUploadServices;
import com.bornfire.services.Follow__up;
import com.bornfire.services.Graphical_design_services;
import com.bornfire.services.InquiriesServices;
import com.bornfire.services.LoginServices;
import com.bornfire.services.Mail;
import com.bornfire.services.MaintenanceOperServices;
import com.bornfire.services.Notification_services;
import com.bornfire.services.OnDutyServices;
import com.bornfire.services.PlacementServices;
import com.bornfire.services.ProjectMasterServices;
import com.bornfire.services.ReportServices;
import com.bornfire.services.TimeSheetPdf;
import com.bornfire.services.UserProfileService;
import com.bornfire.services.Vendor_Register_Service;
import com.bornfire.services.WorkAssignmentReportExcel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.icu.text.SimpleDateFormat;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import net.sf.jasperreports.engine.JRException;

@Controller
@ConfigurationProperties("default")
public class BTMNavigationController {

	private static final Logger logger = LoggerFactory.getLogger(BTMNavigationController.class);
	
	
	@Autowired
	UserProfileService userProfileService ;
	
	@Autowired
	Notification_services notify;
	@Autowired 
	TSK_branch_Rep TSK_branch_Reps;
	@Autowired
	Graphical_design_services Graphical_design_servicess;

	@Autowired
	private Follow_Up_Rep followUpRep;

	@Autowired
	Graphical_design_services Gr_servic;

	@Autowired
	Erp_ChartOfAccountsRep Erp_ChartOfAccountsRep;

	@Autowired
	Erp_ChartOfAccountsRep erp_chartOfAccountsrep;
	@Autowired
	CapitalTransRep capitaltransrep;
	@Autowired
	Transaction_table_demo_rep Transaction_table_demo_rep;

	@Autowired
	CodeCreationRep codecreationrep;
	@Autowired
	TravelClaimRep travelClaimrep;

	@Autowired
	FINISHED_GOODS_Rep FINISHED_GOODS_Rep;

	@Autowired
	AllowancemakerRep Allowancemakerrep;

	@Autowired
	ProcessRep ProcessReps;
	
	@Autowired
	TSK_PROCESS_REP TSK_PROCESS_REP;
	@Autowired
	Transaction_table_rep Transaction_table_Rep;
	@Autowired
	category_creationRep category_creationRep;
	@Autowired
	PO_Return_Rep PO_Return_Reps;

	@Autowired
	BOM_REPO pOM_REPO;

	@Autowired
	Sales_ReturnRep Sales_ReturnRep;

	@Autowired
	Payment_Out_Rep Payment_Out_Reps;

	@Autowired
	PO_invoice_rep PO_invoice_reps;

	@Autowired
	PURCHASE_ORDER_ENTITY_NEW_REP PURCHASE_ORDER_ENTITY_NEW_rep;
	@Autowired
	SALES_invoice_TABLERep SALES_invoice_TABLERep;

	@Autowired
	General_journal_Rep General_journal_rep;
	@Autowired
	VendorCreationRep VendorCreationRep;
	@Autowired
	ItemCreationRep ItemCreationRep;

	@Autowired
	DAB_Repo dAB_Repo;

	@Autowired
	PROCESS_REP PROCESS_rep;

	@Autowired
	Branch_rep Branch_reps;
	@Autowired
	ACCOUNT_LEDGER_PO_Rep ACCOUNT_LEDGER_PO_rep;
	@Autowired
	ACCOUNT_LEDGER_SALE_Rep ACCOUNT_LEDGER_SALE_rep;
	@Autowired
	Capitalrep Capitalrep;

	@Autowired
	TRAN_MAIN_TRM_WRK_REP tRAN_MAIN_TRM_WRK_REP;

	@Autowired
	OutwardRep OutwardRep;

	@Autowired
	SALES_ORDER_ENTITY_NEW_REP SALES_ORDER_ENTITY_NEW_rep;

	@Autowired
	New_product_Rep New_product_rep;

	@Autowired
	InwardRep InwardRep;

	@Autowired
	EmployeeSalaryRep EmployeeSalaryRep;

	@Autowired
	BHMSInventoryProductStockRepo BHMSInventoryProductStockrepo;

	@Autowired
	BhmsInvProductSaleMasterRep bhmsInvProductSaleMasterrep;

	@Autowired
	BHMSInventoryProductStockCurrentRepo BHMSInventoryProductStockCurrentrepo;

	@Autowired
	Hrms_TrainingRep Hrms_TrainingRep;

	@Autowired
	perfomance_evaluation_REP perfomance_evaluation_rep;

	@Autowired
	LeaveTableRep LeaveTablerep;

	@Autowired
	Mail maile;

	@Autowired
	Taxation_parameter_Rep Taxation_parameter_rep;

	@Autowired
	GstoverseasRepo gstoverseasRepo;
	@Autowired
	PlacementMaintenanceRep placementMaintenanceRep;

	@Autowired
	BsalRep bsalRep;

	@Autowired
	GstBtmRep gstBtmRep;

	@Autowired
	GstRep gstRep;

	@Autowired
	spfRepo SpfRepo;

	@Autowired
	spf_repo Spf_repo;

	@Autowired
	LoginServices loginServices;

	@Autowired
	Notify_Entity_Rep Notify_Entity_Reps;
	@Autowired
	IssueTrackerRep issueTrackerRep;

	@Autowired
	ReportServices reportServices;

	@Autowired
	ClientMasterRep clientMasterRep;

	@Autowired
	DocumentMainRep documentMainRep;

	@Autowired
	BankMasterRep bankMasterRep;

	@Autowired
	BTMAdminProfileMangerRep btmAdminProfileMangerRep;

	@Autowired
	BankDetailService bankDetailService;

	@Autowired
	PlacementServices placementServices;

	@Autowired
	OnDutyServices onDutyServices;

	@Autowired
	DataSource srcdataSource;

	@Autowired
	InquiriesServices inquiriesServices;

	@Autowired
	BHMS_Services BHMS_services;

	@Autowired
	Follow__up Follow__ups;

	@Autowired
	AdminOperServices adminOperServices;

	@Autowired
	ExcelUploadService excelUploadService;

	@Autowired
	BLRSBatchJobAlertRep blrsBatchJobAlertRep;

	@Autowired
	PlacementMasterRep placementMasterRep;

	@Autowired
	TimesheetManagementRep timesheetManagementRep;

	@Autowired
	InvoiceMasterRep invoiceMasterRep;

	@Autowired
	ProfileManagerRep profileManagerRep;

	@Autowired
	BTMProjectMasterRep btmProjectMasterRep;

	@Autowired
	BTMProjectTeamDetailsRep btmProjectTeamDetailsRep;

	@Autowired
	BTMProjectDetailsRep btmProjectDetailsRep;

	@Autowired
	AttendanceRegisterRep attendanceRegisterRep;

    @Autowired
    OFFER_ALERT_REP OFFER_ALERT_REPs;
	@Autowired
	PaymentInRep PaymentInRep;

	@Autowired
	TSK_OrganizationMasterRep TSK_OrganizationMasterReps;
	@Autowired
	OnDutyRep onDutyRep;

	@Autowired
	BTMAdminOndutyCountRep bTMAdminOndutyCountRep;

	@Autowired
	LeaveMasterRep leaveMasterRep;

	@Autowired
	BTMAdminAssociateProfileRep btmAdminAssociateProfileRep;

	@Autowired
	LeaveMasterCounterRep leaveMasterCounterRep;

	@Autowired
	ExtenseMasterRep extenseMasterRep;

	@Autowired
	BTMWorkAssignmentRep btmWorkAssignmentRep;

	@Autowired
	BTMEmpTimeSheetRep bTMEmpTimeSheetRep;

	@Autowired
	BTMTravelMasterRep btmTravelMasterRep;

	@Autowired
	BTMRefCodeMasterRep btmRefCodeMasterRep;

	@Autowired
	BTMAdminOrganizationMasterRep btmAdminOrganizationMasterRep;

	@Autowired
	BTMEmpTimeSheetRep btmEmpTimeSheetRep;

	@Autowired
	BTMMTimeSheetRep btmmTimeSheetRep;

	@Autowired
	TimeSheetBeanRep timeSheetBeanRep;

	@Autowired
	BTMAdminExpenseReportRep btmAdminExpenseReportRep;

	@Autowired
	BTMDocumentMasterRep btmDocumentMasterRep;

	@Autowired
	BTMEventMasterRep btmEventMasterRep;

	@Autowired
	MaintenanceOperServices maintenanceOperServices;

	@Autowired
	AttendanceRegisterService attendanceRegisterService;

	@Autowired
	PlacementResourcesMasterRepo placementResourcesMasterRepo;

	@Autowired
	ResourceMasterRepo resourceMasterRepo;

	@Autowired
	TimeSheetPdf timeSheetPdf;

	@Autowired
	WorkAssignmentReportExcel workAssignmentReportExcel;

	@Autowired
	BTMAdminHolidayMasterRep btmAdminHolidayMasterRep;

	@Autowired
	PlacementMaintenanceRep placementmaintenancerep;

	@Autowired
	BTMAdminAssociateModRep btmAdminAssociateModRep;

	@Autowired
	AccessRolesRep accessRolesRep;
	@Autowired
	bexpiRepo bexpiRepoa;

	@Autowired
	tdsRepo tdsRepos;
	@Autowired
	btdsviewRepo btdsviewRepos;
	@Autowired
	com.bornfire.entities.AttendanceRegisterGetRep AttendanceRegisterGetRep;

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	salary_parameter_rep salary_parameter_Rep;

	@Autowired
	ProjectMasterServices projectMasterServices;

	@Autowired
	Salary_Pay_Rep salary_Pay_Rep;

	@Autowired
	CandEvalFormRep candEvalFormRep;

	@Autowired
	com.bornfire.services.sendingmail_appointment sendingmail_appointment;
	@Autowired
	com.bornfire.services.sendigmail_offerletter sendigmail_offerletter;

	@Autowired
	com.bornfire.services.Sendingmail_coveringletter sendingmail_coveringletter;

	@Autowired
	com.bornfire.services.sendingmail_batchjob sendingmail_batchjob;

	@Autowired
	paystructurerep Paystructurerep;

	@Autowired
	ProfileManagerRep1 profileManagerRep1;

	@Autowired
	PerdiemMasterRep perdiemMasterRep;

	@Autowired
	Assosiate_Profile_Repo assosiate_Profile_Repo;
	@Autowired
	com.bornfire.entities.Baj_Work_Repo Baj_Work_Repo;

	@Autowired
	FileUploadServices fileUploadServices;

	@Autowired
	com.bornfire.entities.Document_Master_Repo Document_Master_Repo;

	@Autowired
	TrainingRep trainingRep;

	@Autowired
	Vendor_Register_Repo Vendor_Register_Repo;

	@Autowired
	Reference_code_Rep reference_code_Rep;

	@Autowired
	Chart_Acc_Rep chart_Acc_Rep;

	@Autowired
	BGLSBusinessTable_Rep bglsBusinessTable_Rep;

	@Autowired
	GeneralLedgerRep generalLedgerRep;

	@Autowired
	GeneralLedgerWork_Rep generalLedgerWork_Rep;

	@Autowired
	ParameterRep parameterrep;

	@Autowired
	HRMS_USER_PROFILE_REPOSITORY hrmsrepoo;

	@Autowired
	SalesOutRep salesOutRep;
	
	
	@Autowired
	private salary_parameter_rep salary_parameter_rep1111;

	String pagesize;

	public String getPagesize() {
		return pagesize;
	}

	public void setPagesize(String pagesize) {
		this.pagesize = pagesize;
	}

	@RequestMapping(value = "Dashboard", method = { RequestMethod.GET, RequestMethod.POST })
	public String getdashboard(Model md, HttpServletRequest req, HttpServletResponse response,
			 @RequestParam(value = "activeMenu", required = false) String activeMenu) {

	    String userid = (String) req.getSession().getAttribute("USERID");
	    HRMS_USER_PROFILE_ENTITY aa = hrmsrepoo.getrole(userid);
	    md.addAttribute("activeMenu", activeMenu);
	    System.out.println("the role: " + aa.getRole());

	    md.addAttribute("RoleMenu", aa);
	    md.addAttribute("menu", "Dashboard");
	    md.addAttribute("resetMenu", true);

	    int completed = 0;
	    int uncompleted = 0;

	    md.addAttribute("reportList", "");
	    md.addAttribute("completed", completed);
	    md.addAttribute("uncompleted", uncompleted);
	    md.addAttribute("formmode", "list");

	    // Check if it's an AJAX request
	    
	        return "BTMDashboard"; // Full page
	    
	}

 
	@RequestMapping(value = "login?logout", method = RequestMethod.POST)
	@ResponseBody
	public String logoutUpdate(HttpServletRequest req) {

		String msg;

		String userid = (String) req.getSession().getAttribute("USERID");

		try {
			logger.info("Updating Logout");
			loginServices.SessionLogging("LOGOUT", "M0", req.getSession().getId(), userid, req.getRemoteAddr(),
					"IN-ACTIVE");
			msg = "success";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "failed";
		}
		return msg;
	}

//	======================================  Admin Module ====================================================
	
	
	@RequestMapping(value = "OrganizationMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String TSK_organizationMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String id, Model md, HttpServletRequest req,
			@RequestParam(value = "activeMenu", required = false) String activeMenu) throws ParseException {

		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		md.addAttribute("activeMenu", activeMenu);
		if(formmode == null) {
			md.addAttribute("formmode", "list");
			List<TSK_OrganizationMasterEntity> UserProfilelist=TSK_OrganizationMasterReps.getall();
			System.out.println("the list is:"+UserProfilelist.size());
			md.addAttribute("UserProfilelist", UserProfilelist);
		}else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");
			System.out.println("enter add....");
			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);
		}else if (formmode.equals("view")) {
			List<LeaveTable> leaves = LeaveTablerep.getAll(); // Fetch your data
			md.addAttribute("leaves", leaves);
			md.addAttribute("leave_count", leaves.size());
			long count = LeaveTablerep.count();
			md.addAttribute("hasRecords", count > 0);
			md.addAttribute("formmode", "view");
			TSK_OrganizationMasterEntity listall=TSK_OrganizationMasterReps.getallid(id);
			md.addAttribute("adminOrganization", listall);
			int empcount = btmAdminAssociateProfileRep.getAssociateProfilecount();
			md.addAttribute("empcount", empcount);
			Long maxBranchId = Branch_reps.findMaxBranchId();
			System.out.println("findMaxBranchId: " + maxBranchId);

			if (maxBranchId == null || maxBranchId == 0) {
				md.addAttribute("Id", "00" + 1);
			} else {
				md.addAttribute("Id", String.format("%03d", maxBranchId + 1));
			}
			List<Branch_Entity> list = Branch_reps.findAll();
			md.addAttribute("list", list);

		} else if (formmode.equals("delete")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("delete", "delete");
			TSK_OrganizationMasterEntity listall=TSK_OrganizationMasterReps.getallid(id);
			md.addAttribute("adminOrganization", listall);

		}else if (formmode.equals("edit")) { 

			md.addAttribute("formmode", "edit");
			TSK_OrganizationMasterEntity list=TSK_OrganizationMasterReps.getallid(id);
			md.addAttribute("adminOrganization", list);
			int empcount = btmAdminAssociateProfileRep.getAssociateProfilecount();
			md.addAttribute("empcount", empcount);

		}else if (formmode.equals("branchList")) {
			md.addAttribute("formmode", "branchList");
			List<TSK_branchEntity> list=TSK_branch_Reps.findAll();
			System.out.println("the list is:"+list.size());
			md.addAttribute("list", list);
		}else if (formmode.equals("addbranch")) {
			md.addAttribute("formmode", "addbranch");
			System.out.println("enter add....");
			md.addAttribute("formmode", "addbranch");
			List<Object[]> result = TSK_OrganizationMasterReps.getnames();
			List<String> orgs = new ArrayList<>();
			for (Object[] row : result) {
			    String orgId = String.valueOf(row[0]);
			    String orgName = String.valueOf(row[1]);
			    orgs.add(orgId + " - " + orgName);
			}
			md.addAttribute("orgs", orgs);

		}  else if (formmode.equals("branchEdit")) {

			md.addAttribute("formmode", "branchEdit");
			md.addAttribute("editer", "editer");
			TSK_branchEntity list=TSK_branch_Reps.find_id(id);
			md.addAttribute("branch", list);
			int empcount = btmAdminAssociateProfileRep.getAssociateProfilecount();
			md.addAttribute("empcount", empcount);
			List<Object[]> result = TSK_OrganizationMasterReps.getnames();
			List<String> orgs = new ArrayList<>();
			for (Object[] row : result) {
			    String orgId = String.valueOf(row[0]);
			    String orgName = String.valueOf(row[1]);
			    orgs.add(orgId + " - " + orgName);
			}
			md.addAttribute("orgs", orgs);


		}else if (formmode.equals("branchDelete")) {

			md.addAttribute("formmode", "branchEdit");
			md.addAttribute("deleter", "deleter");
			TSK_branchEntity list=TSK_branch_Reps.find_id(id);
			md.addAttribute("branch", list);
			int empcount = btmAdminAssociateProfileRep.getAssociateProfilecount();
			md.addAttribute("empcount", empcount);
			List<Object[]> result = TSK_OrganizationMasterReps.getnames();
			List<String> orgs = new ArrayList<>();
			for (Object[] row : result) {
			    String orgId = String.valueOf(row[0]);
			    String orgName = String.valueOf(row[1]);
			    orgs.add(orgId + " - " + orgName);
			}
			md.addAttribute("orgs", orgs);


		}else if (formmode.equals("branchView")) {

			md.addAttribute("formmode", "branchEdit");
			TSK_branchEntity list=TSK_branch_Reps.find_id(id);
			md.addAttribute("branch", list);
			List<Object[]> result = TSK_OrganizationMasterReps.getnames();
			List<String> orgs = new ArrayList<>();
			for (Object[] row : result) {
			    String orgId = String.valueOf(row[0]);
			    String orgName = String.valueOf(row[1]);
			    orgs.add(orgId + " - " + orgName);
			}
			md.addAttribute("orgs", orgs);
		}else {

			md.addAttribute("formmode", formmode);
		}

		return "BDOCOrganizationMaster";
	}
	
	@RequestMapping(value = "TSK_organizationMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String TSK_organizationMasterAdd(@RequestParam("formmode") String formmode,
			@ModelAttribute TSK_OrganizationMasterEntity TSK_OrganizationMasterEntity, Model md, HttpServletRequest rq) {

		String msg = adminOperServices.TSK_Org(TSK_OrganizationMasterEntity, formmode);
		return msg;
	}
	
	@RequestMapping(value = "TSK_branchAdd", method = RequestMethod.POST)
	@ResponseBody
	public String TSK_branchAdd(@RequestParam("formmode") String formmode,
			@ModelAttribute TSK_branchEntity TSK_branchEntity, Model md, HttpServletRequest rq,
			@RequestParam(required = false) String OrgId) {
	    System.out.println("org id is : " + OrgId); // Log org_id value

		String msg = adminOperServices.TSK_branch(TSK_branchEntity, formmode,OrgId);
		return msg;
	}

 
 
	@RequestMapping(value = "BDOCUserProfile", method = { RequestMethod.GET, RequestMethod.POST })
	public String userprofile(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid,
			@RequestParam(value = "page", required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req,
			@RequestParam(value = "activeMenu", required = false) String activeMenu)
			throws SQLException {

		String userID = (String) req.getSession().getAttribute("USERID");
	 
		
		md.addAttribute("activeMenu", activeMenu);
		/*
		 * md.addAttribute("RuleIDType", accessandrolesrepository.roleidtype());
		 * md.addAttribute("IPSRoleMenu", AccessRoleService.getRoleMenu(roleId));
		 */
		md.addAttribute("PdfViewer", "UserProfile");
		md.addAttribute("loginuser", userID);
		
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("formmode", "list");
			md.addAttribute("userProfiles", userProfileService.getUsersList());
		} else if (formmode.equals("add")) {
			md.addAttribute("user_id", userID);
			md.addAttribute("formmode", formmode);
		 
		} else if (formmode.equals("edit")) {
			md.addAttribute("user_id", userID);
			md.addAttribute("formmode", formmode);
			md.addAttribute("userProfile", userProfileService.getUser(userid));
			 
		} else if (formmode.equals("verify")) {
			md.addAttribute("user_id", userID);
			md.addAttribute("formmode", formmode);
			md.addAttribute("userProfile", userProfileService.getUser2(userid));
		} else if (formmode.equals("view")) {
			md.addAttribute("user_id", userID);
			md.addAttribute("formmode", "view");
			md.addAttribute("userProfile", userProfileService.getUser(userid));
		} else if (formmode.equals("cancel")) {
			md.addAttribute("user_id", userID);
			md.addAttribute("formmode", "cancel");
			md.addAttribute("userProfile", userProfileService.getUser2(userid));
		} else if (formmode.equals("viewnew")) {
			md.addAttribute("user_id", userID);
			md.addAttribute("formmode", "viewnew");
			md.addAttribute("userProfile", userProfileService.getUser2(userid));
		} else if (formmode.equals("delete")) {
			md.addAttribute("user_id", userID);
			md.addAttribute("formmode", formmode);
			md.addAttribute("userProfile", userProfileService.getUser(userid));
		}
		return "BDOCUserProfile";
	}

	
	@RequestMapping(value = "deleteorg", method = RequestMethod.POST)
	@ResponseBody
	public String deleteorg(@RequestParam(required = false) String id, HttpServletRequest rq) {

		String msg = "";
		System.out.println("deleting Organization..."+id);
		// Validate userId parameter
		if (id == null || id.isEmpty()) {
			return "Organization ID is required for Deletion.";
		}

		// Fetch user entity from repository
		TSK_OrganizationMasterEntity entity = TSK_OrganizationMasterReps.getallid(id);

		entity.setDel_flg("Y");
		TSK_OrganizationMasterReps.save(entity);
		msg = "Organization Deleted successfully.";

		return msg;
	}
	
	@RequestMapping(value = "deleteBranch", method = RequestMethod.POST)
	@ResponseBody
	public String deleteBranch(@RequestParam(required = false) String branchId, HttpServletRequest rq) {

		String msg = "";
		System.out.println("deleting Branch..."+branchId);
		// Validate userId parameter
		if (branchId == null || branchId.isEmpty()) {
			return "Branch ID is required for Deletion.";
		}
		// Fetch user entity from repository
		TSK_branchEntity entity=TSK_branch_Reps.find_id(branchId);
		entity.setDelFlg("Y");
		TSK_branch_Reps.save(entity);
		msg = "Branch Deleted successfully.";

		return msg;
	}
	@RequestMapping(value = "organizationMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String organizationMasterAdd(@RequestParam("formmode") String formmode,
			@ModelAttribute BTMAdminOrganizationMaster btmAdminOrganizationMaster, Model md, HttpServletRequest rq) {

		String msg = adminOperServices.addOrganizationModyfiy(btmAdminOrganizationMaster, formmode);
		return msg;
	}

	@RequestMapping(value = "adminAssociateProfile", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminAssociateProfile(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("adminAssociateProfileList", btmAdminAssociateProfileRep.getAssociatelist());
			/*
			 * List<String> list = Branch_reps.find_branch_id();
			 * md.addAttribute("branchIds", list);
			 */
		      List<String> OrgIds = TSK_OrganizationMasterReps.get_org();
					md.addAttribute("OrgIds", OrgIds);


		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("refernce_code", btmRefCodeMasterRep.getBankList());
			md.addAttribute("seqcurrentvalue", "TSK00" + btmAdminAssociateProfileRep.getseq().add(BigInteger.ONE));
			/*
			 * List<String> list = Branch_reps.find_branch_id();
			 * md.addAttribute("branchIds", list);
			 */
			List<String> OrgIds = TSK_OrganizationMasterReps.get_org();
					md.addAttribute("OrgIds", OrgIds);


		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", "edit");
			md.addAttribute("adminAssociateProfile", adminOperServices.getAssociteUser(resId));
			BTMAdminAssociateProfile up = adminOperServices.getAssociteListUser(resId);
			if (up.getAadharfile() != null) {
				String Aadharfile = Base64.getEncoder().encodeToString(up.getAadharfile());
				md.addAttribute("Aadharfile", Aadharfile);
			}
			if (up.getPancardfile() != null) {
				String Pancardfile = Base64.getEncoder().encodeToString(up.getPancardfile());
				md.addAttribute("Pancardfile", Pancardfile);
			}
			if (up.getPassportfile() != null) {
				String Passportfile = Base64.getEncoder().encodeToString(up.getPassportfile());
				md.addAttribute("Passportfile", Passportfile);
			}

			if (up.getDrivinglicense() != null) {
				String Drivinglicensefile = Base64.getEncoder().encodeToString(up.getDrivinglicense());
				md.addAttribute("Drivinglicensefile", Drivinglicensefile);

			}
			if (up.getSlc() != null) {
				String slcfile = Base64.getEncoder().encodeToString(up.getSlc());
				md.addAttribute("slcfile", slcfile);
			}

			if (up.getHscdocumentfile() != null) {
				String Hscdocumentfile = Base64.getEncoder().encodeToString(up.getHscdocumentfile());
				md.addAttribute("Hscdocumentfile", Hscdocumentfile);
				md.addAttribute("hg", "HSC");
			}
			if (up.getDiplomafile() != null) {
				String Diplomadocumentfile = Base64.getEncoder().encodeToString(up.getDiplomafile());
				md.addAttribute("Diplomadocumentfile", Diplomadocumentfile);
				md.addAttribute("hg", "Diploma");
			}

			if (up.getUgdocumentfile() != null) {
				String Ugdocumentfile = Base64.getEncoder().encodeToString(up.getUgdocumentfile());
				md.addAttribute("Ugdocumentfile", Ugdocumentfile);
			}

			if (up.getPgdocumentfile() != null) {
				String Pgdocumentfile = Base64.getEncoder().encodeToString(up.getPgdocumentfile());
				md.addAttribute("Pgdocumentfile", Pgdocumentfile);
				md.addAttribute("Pg", "Yes");
			}

			if (up.getPgdocumentfile() == null) {
				md.addAttribute("Pg", "No");
			}

			if (up.getFiles() != null) {
				String Profileimg = Base64.getEncoder().encodeToString(up.getFiles());
				md.addAttribute("Profileimg", Profileimg);
			}

			/*
			 * List<String> list = Branch_reps.find_branch_id();
			 * md.addAttribute("branchIds", list);
			 */
			List<String> OrgIds = TSK_OrganizationMasterReps.get_org();
			md.addAttribute("OrgIds", OrgIds);

		} else if (formmode.equals("verify")) {

			md.addAttribute("formmode", "verify");
			md.addAttribute("adminAssociateProfile", adminOperServices.getAssociteVerifyUser(resId));

			BTMAdminAssociateProfile up = adminOperServices.getAssociteListUser(resId);
			if (up.getAadharfile() != null) {
				String Aadharfile = Base64.getEncoder().encodeToString(up.getAadharfile());
				md.addAttribute("Aadharfile", Aadharfile);
			}
			if (up.getPancardfile() != null) {
				String Pancardfile = Base64.getEncoder().encodeToString(up.getPancardfile());
				md.addAttribute("Pancardfile", Pancardfile);
			}
			if (up.getPassportfile() != null) {
				String Passportfile = Base64.getEncoder().encodeToString(up.getPassportfile());
				md.addAttribute("Passportfile", Passportfile);
			}

			if (up.getDrivinglicense() != null) {
				String Drivinglicensefile = Base64.getEncoder().encodeToString(up.getDrivinglicense());
				md.addAttribute("Drivinglicensefile", Drivinglicensefile);

			}
			if (up.getSlc() != null) {
				String slcfile = Base64.getEncoder().encodeToString(up.getSlc());
				md.addAttribute("slcfile", slcfile);
			}

			if (up.getHscdocumentfile() != null) {
				String Hscdocumentfile = Base64.getEncoder().encodeToString(up.getHscdocumentfile());
				md.addAttribute("Hscdocumentfile", Hscdocumentfile);
				md.addAttribute("hg", "HSC");
			}
			if (up.getDiplomafile() != null) {
				String Diplomadocumentfile = Base64.getEncoder().encodeToString(up.getDiplomafile());
				md.addAttribute("Diplomadocumentfile", Diplomadocumentfile);
				md.addAttribute("hg", "Diploma");
			}

			if (up.getUgdocumentfile() != null) {
				String Ugdocumentfile = Base64.getEncoder().encodeToString(up.getUgdocumentfile());
				md.addAttribute("Ugdocumentfile", Ugdocumentfile);
			}

			if (up.getPgdocumentfile() != null) {
				String Pgdocumentfile = Base64.getEncoder().encodeToString(up.getPgdocumentfile());
				md.addAttribute("Pgdocumentfile", Pgdocumentfile);
				md.addAttribute("Pg", "Yes");
			}

			if (up.getPgdocumentfile() == null) {
				md.addAttribute("Pg", "No");
			}

			if (up.getFiles() != null) {
				String Profileimg = Base64.getEncoder().encodeToString(up.getFiles());
				md.addAttribute("Profileimg", Profileimg);
			}

			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);

		} else if (formmode.equals("delete")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("adminAssociateProfile", adminOperServices.getAssociteUser(resId));
			BTMAdminAssociateProfile up = adminOperServices.getAssociteListUser(resId);
			if (up.getAadharfile() != null) {
				String Aadharfile = Base64.getEncoder().encodeToString(up.getAadharfile());
				md.addAttribute("Aadharfile", Aadharfile);
			}
			if (up.getPancardfile() != null) {
				String Pancardfile = Base64.getEncoder().encodeToString(up.getPancardfile());
				md.addAttribute("Pancardfile", Pancardfile);
			}
			if (up.getPassportfile() != null) {
				String Passportfile = Base64.getEncoder().encodeToString(up.getPassportfile());
				md.addAttribute("Passportfile", Passportfile);
			}

			if (up.getDrivinglicense() != null) {
				String Drivinglicensefile = Base64.getEncoder().encodeToString(up.getDrivinglicense());
				md.addAttribute("Drivinglicensefile", Drivinglicensefile);

			}
			if (up.getSlc() != null) {
				String slcfile = Base64.getEncoder().encodeToString(up.getSlc());
				md.addAttribute("slcfile", slcfile);
			}

			if (up.getHscdocumentfile() != null) {
				String Hscdocumentfile = Base64.getEncoder().encodeToString(up.getHscdocumentfile());
				md.addAttribute("Hscdocumentfile", Hscdocumentfile);
				md.addAttribute("hg", "HSC");
			}
			if (up.getDiplomafile() != null) {
				String Diplomadocumentfile = Base64.getEncoder().encodeToString(up.getDiplomafile());
				md.addAttribute("Diplomadocumentfile", Diplomadocumentfile);
				md.addAttribute("hg", "Diploma");
			}

			if (up.getUgdocumentfile() != null) {
				String Ugdocumentfile = Base64.getEncoder().encodeToString(up.getUgdocumentfile());
				md.addAttribute("Ugdocumentfile", Ugdocumentfile);
			}

			if (up.getPgdocumentfile() != null) {
				String Pgdocumentfile = Base64.getEncoder().encodeToString(up.getPgdocumentfile());
				md.addAttribute("Pgdocumentfile", Pgdocumentfile);
				md.addAttribute("Pg", "Yes");
			}

			if (up.getPgdocumentfile() == null) {
				md.addAttribute("Pg", "No");
			}

			if (up.getFiles() != null) {
				String Profileimg = Base64.getEncoder().encodeToString(up.getFiles());
				md.addAttribute("Profileimg", Profileimg);
			}
			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);

		} else if (formmode.equals("cancel")) {

			md.addAttribute("formmode", "cancel");
			md.addAttribute("adminAssociateProfile", adminOperServices.getAssociteCancelUser(resId));
			BTMAdminAssociateProfile up = adminOperServices.getAssociteListUser(resId);
			if (up.getAadharfile() != null) {
				String Aadharfile = Base64.getEncoder().encodeToString(up.getAadharfile());
				md.addAttribute("Aadharfile", Aadharfile);
			}
			if (up.getPancardfile() != null) {
				String Pancardfile = Base64.getEncoder().encodeToString(up.getPancardfile());
				md.addAttribute("Pancardfile", Pancardfile);
			}
			if (up.getPassportfile() != null) {
				String Passportfile = Base64.getEncoder().encodeToString(up.getPassportfile());
				md.addAttribute("Passportfile", Passportfile);
			}

			if (up.getDrivinglicense() != null) {
				String Drivinglicensefile = Base64.getEncoder().encodeToString(up.getDrivinglicense());
				md.addAttribute("Drivinglicensefile", Drivinglicensefile);

			}
			if (up.getSlc() != null) {
				String slcfile = Base64.getEncoder().encodeToString(up.getSlc());
				md.addAttribute("slcfile", slcfile);
			}

			if (up.getHscdocumentfile() != null) {
				String Hscdocumentfile = Base64.getEncoder().encodeToString(up.getHscdocumentfile());
				md.addAttribute("Hscdocumentfile", Hscdocumentfile);
				md.addAttribute("hg", "HSC");
			}
			if (up.getDiplomafile() != null) {
				String Diplomadocumentfile = Base64.getEncoder().encodeToString(up.getDiplomafile());
				md.addAttribute("Diplomadocumentfile", Diplomadocumentfile);
				md.addAttribute("hg", "Diploma");
			}

			if (up.getUgdocumentfile() != null) {
				String Ugdocumentfile = Base64.getEncoder().encodeToString(up.getUgdocumentfile());
				md.addAttribute("Ugdocumentfile", Ugdocumentfile);
			}

			if (up.getPgdocumentfile() != null) {
				String Pgdocumentfile = Base64.getEncoder().encodeToString(up.getPgdocumentfile());
				md.addAttribute("Pgdocumentfile", Pgdocumentfile);
				md.addAttribute("Pg", "Yes");
			}

			if (up.getPgdocumentfile() == null) {
				md.addAttribute("Pg", "No");
			}

			if (up.getFiles() != null) {
				String Profileimg = Base64.getEncoder().encodeToString(up.getFiles());
				md.addAttribute("Profileimg", Profileimg);
			}

			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);

		} else if (formmode.equals("listview")) {

			md.addAttribute("formmode", "listview");
			md.addAttribute("adminAssociateProfile", adminOperServices.getAssociteListUser(resId));
			BTMAdminAssociateProfile up = adminOperServices.getAssociteListUser(resId);
			if (up.getAadharfile() != null) {
				String Aadharfile = Base64.getEncoder().encodeToString(up.getAadharfile());
				md.addAttribute("Aadharfile", Aadharfile);
			}
			if (up.getPancardfile() != null) {
				String Pancardfile = Base64.getEncoder().encodeToString(up.getPancardfile());
				md.addAttribute("Pancardfile", Pancardfile);
			}
			if (up.getPassportfile() != null) {
				String Passportfile = Base64.getEncoder().encodeToString(up.getPassportfile());
				md.addAttribute("Passportfile", Passportfile);
			}

			if (up.getDrivinglicense() != null) {
				String Drivinglicensefile = Base64.getEncoder().encodeToString(up.getDrivinglicense());
				md.addAttribute("Drivinglicensefile", Drivinglicensefile);

			}
			if (up.getSlc() != null) {
				String slcfile = Base64.getEncoder().encodeToString(up.getSlc());
				md.addAttribute("slcfile", slcfile);
			}

			if (up.getHscdocumentfile() != null) {
				String Hscdocumentfile = Base64.getEncoder().encodeToString(up.getHscdocumentfile());
				md.addAttribute("Hscdocumentfile", Hscdocumentfile);
				md.addAttribute("hg", "HSC");
			}
			if (up.getDiplomafile() != null) {
				String Diplomadocumentfile = Base64.getEncoder().encodeToString(up.getDiplomafile());
				md.addAttribute("Diplomadocumentfile", Diplomadocumentfile);
				md.addAttribute("hg", "Diploma");
			}

			if (up.getUgdocumentfile() != null) {
				String Ugdocumentfile = Base64.getEncoder().encodeToString(up.getUgdocumentfile());
				md.addAttribute("Ugdocumentfile", Ugdocumentfile);
			}

			if (up.getPgdocumentfile() != null) {
				String Pgdocumentfile = Base64.getEncoder().encodeToString(up.getPgdocumentfile());
				md.addAttribute("Pgdocumentfile", Pgdocumentfile);
				md.addAttribute("Pg", "Yes");
			}

			if (up.getPgdocumentfile() == null) {
				md.addAttribute("Pg", "No");
			}

			if (up.getFiles() != null) {
				String Profileimg = Base64.getEncoder().encodeToString(up.getFiles());
				md.addAttribute("Profileimg", Profileimg);
			}

			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);

		}

		return "BTMAdminAssociateProfile";
	}

	/*-----HRMSuserprofilenew-----*/
	@RequestMapping(value = "HRMSUserProfile", method = { RequestMethod.GET, RequestMethod.POST })
	public String HRMSUserProfile(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, @RequestParam(required = false) String userId, Model md,
			HttpServletRequest req) throws ParseException {
		String userId1 = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId1));
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(userId1));

		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");

			md.addAttribute("UserProfilelist", hrmsrepoo.getAlllist());
			System.out.println("THE SIZE" + hrmsrepoo.getAlllist().size());
			/*
			 * List<String> list = Branch_reps.find_branch_id();
			 * md.addAttribute("branchIds", list);
			 */
			/*--organization--*/
			List<String> OrgIds = TSK_OrganizationMasterReps.get_org();
			md.addAttribute("OrgIds", OrgIds);
		}

		else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("resourceids", resourceMasterRepo.getalist11());
			/*
			 * List<String> list = Branch_reps.find_branch_id();
			 * md.addAttribute("branchIds", list);
			 */
			
			/*--organization--*/
			List<String> OrgIds = TSK_OrganizationMasterReps.get_org();
			md.addAttribute("OrgIds", OrgIds);

		}

		else if (formmode.equals("view")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("forms", "view");

			md.addAttribute("UserProfilelist", hrmsrepoo.getlistuserid(userId));

		} else if (formmode.equals("verify")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("UserProfilelist", hrmsrepoo.getlistuserid(userId));

		} else if (formmode.equals("modify")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("UserProfilelist", hrmsrepoo.getlistuserid(userId));
			/*
			 * List<String> list = Branch_reps.find_branch_id();
			 * md.addAttribute("branchIds", list);
			 */
			
			/*--organization--*/
			List<String> OrgIds = TSK_OrganizationMasterReps.get_org();
			md.addAttribute("OrgIds", OrgIds);

		}

		else if (formmode.equals("delete")) {
			md.addAttribute("formmode", "view");
			md.addAttribute("forms", "delete");

			md.addAttribute("UserProfilelist", hrmsrepoo.getlistuserid(userId));

		}

		return "HRMSuserprofile";
	}

	/*----create user---*/

	@RequestMapping(value = "hrmscreateUser", method = RequestMethod.POST)
	@ResponseBody
	public String hrmscreateUser(@ModelAttribute HRMS_USER_PROFILE_ENTITY userform, Model md, String formmode,
			HttpServletRequest rq) {

		System.out.println("createUser");
		String user_id = (String) rq.getSession().getAttribute("USERID");
		String user_name = (String) rq.getSession().getAttribute("USERNAME");

	System.out.println("the BRANCHID" +userform.getBranchId());

		String msg = adminOperServices.addUserhrms(userform, formmode);
		md.addAttribute("menu", "BTMHeaderMenu"); // To highlight the menu

		return msg;

	}

	@RequestMapping(value = "getuserdetailsresource", method = { RequestMethod.GET })
	@ResponseBody
	public List<ResourceMaster> getuserdetailsresource(String resource_id, HttpServletRequest req) {

		List<ResourceMaster> up = resourceMasterRepo.getalist11(resource_id);
		System.out.println(up);

		return up;
	}

	/*---verifyuserprofile--*/

	@RequestMapping(value = "verifyuser", method = RequestMethod.POST)
	@ResponseBody
	public String verifyUser(@RequestParam(required = false) String userId, HttpServletRequest rq) {

		String msg = "";
		System.out.println("Verifying user...");

		// Fetch session attributes
		String sessionUserId = (String) rq.getSession().getAttribute("USERID");
		String sessionUserName = (String) rq.getSession().getAttribute("USERNAME");

		// Validate userId parameter
		if (userId == null || userId.isEmpty()) {
			return "User ID is required for verification.";
		}

		// Fetch user entity from repository
		Optional<HRMS_USER_PROFILE_ENTITY> entity = hrmsrepoo.findById(userId);

		if (entity.isPresent()) {
			HRMS_USER_PROFILE_ENTITY user = entity.get();

			System.out.println("the Entity" + user.getUserId());
			System.out.println("the session id" + sessionUserId);

			// Prevent self-verification
			if (user.getUserId().equals(sessionUserId)) {
				msg = "The same user cannot verify themselves.";
			} else {
				// Update user status to "verified"
				user.setUserStatus("Y");
				hrmsrepoo.save(user);
				msg = "User verified successfully.";
			}
		} else {
			msg = "User not found.";
		}

		return msg;
	}

	/*---deleteuser---*/

	@RequestMapping(value = "deleteuser", method = RequestMethod.POST)
	@ResponseBody
	public String deleteuser(@RequestParam(required = false) String userId, HttpServletRequest rq) {

		String msg = "";
		System.out.println("deleting user...");

		// Fetch session attributes
		String sessionUserId = (String) rq.getSession().getAttribute("USERID");
		String sessionUserName = (String) rq.getSession().getAttribute("USERNAME");

		// Validate userId parameter
		if (userId == null || userId.isEmpty()) {
			return "User ID is required for Deletion.";
		}

		// Fetch user entity from repository
		Optional<HRMS_USER_PROFILE_ENTITY> entity = hrmsrepoo.findById(userId);
		if (entity.isPresent()) {
			HRMS_USER_PROFILE_ENTITY user = entity.get();

			user.setDelFlg("Y");
			hrmsrepoo.save(user);
			msg = "User Deleted successfully.";
		} else {
			msg = "User not found.";
		}

		return msg;
	}

	@RequestMapping(value = "adminAssociateProfileAdd", method = RequestMethod.POST)
	@ResponseBody
	public String adminAssociateAdd(@RequestParam("formmode") String formmode,
			@RequestParam(required = false) MultipartFile Profileimage, MultipartFile Aadhardocument,
			MultipartFile PanCarddocument, MultipartFile Passportdocument, MultipartFile DrivingLicensedocument,
			MultipartFile SLCdocument, MultipartFile HSCdocument, MultipartFile UGdocument,
			MultipartFile Diplomadocument, MultipartFile pgdocument,
			@ModelAttribute BTMAdminAssociateProfile bTMAdminAssociateProfile, Model md, HttpServletRequest rq)
			throws IOException {
		String userId = (String) rq.getSession().getAttribute("USERID");
		String filename = null;
		String filetype = null;
		byte[] profilebyteArray = null;
		byte[] AadhardocumentbyteArray = null;
		byte[] PanCarddocumentbyteArray = null;
		byte[] PassportdocumentbyteArray = null;
		byte[] DrivingLicensedocumentbyteArray = null;
		byte[] SLCdocumentbyteArray = null;
		byte[] HSCdocumentbyteArray = null;
		byte[] UGdocumentbyteArray = null;
		byte[] DiplomadocumentbyteArray = null;
		byte[] pgdocumentbyteArray = null;

		if (Profileimage != null && !Profileimage.isEmpty()) {
			profilebyteArray = Profileimage.getBytes();
			// filename = Profileimage.getOriginalFilename();
			filetype = Profileimage.getContentType();
			bTMAdminAssociateProfile.setFiles(profilebyteArray);
			bTMAdminAssociateProfile.setFileName(Profileimage.getOriginalFilename());
			// filename=Aadhardocument.getOriginalFilename();
			System.out.println("filename==" + filename);

		}
		if (Aadhardocument != null && !Aadhardocument.isEmpty()) {
			AadhardocumentbyteArray = Aadhardocument.getBytes();
			bTMAdminAssociateProfile.setAadharfile(AadhardocumentbyteArray);
			bTMAdminAssociateProfile.setAadharfilename(Aadhardocument.getOriginalFilename());
		}
		if (PanCarddocument != null && !PanCarddocument.isEmpty()) {
			PanCarddocumentbyteArray = PanCarddocument.getBytes();
			bTMAdminAssociateProfile.setPancardfile(PanCarddocumentbyteArray);
			bTMAdminAssociateProfile.setPanCardfilename(PanCarddocument.getOriginalFilename());
		}
		if (Passportdocument != null && !Passportdocument.isEmpty()) {
			PassportdocumentbyteArray = Passportdocument.getBytes();
			bTMAdminAssociateProfile.setPassportfile(PassportdocumentbyteArray);
			bTMAdminAssociateProfile.setPassportfilename(Passportdocument.getOriginalFilename());
		}
		if (DrivingLicensedocument != null && !DrivingLicensedocument.isEmpty()) {
			DrivingLicensedocumentbyteArray = DrivingLicensedocument.getBytes();
			bTMAdminAssociateProfile.setDrivinglicense(DrivingLicensedocumentbyteArray);
			bTMAdminAssociateProfile.setDrivinglicensefilename(DrivingLicensedocument.getOriginalFilename());
		}
		if (SLCdocument != null && !SLCdocument.isEmpty()) {
			SLCdocumentbyteArray = SLCdocument.getBytes();
			bTMAdminAssociateProfile.setSlc(SLCdocumentbyteArray);
			bTMAdminAssociateProfile.setSlcfilename(SLCdocument.getOriginalFilename());
		}
		if (HSCdocument != null && !HSCdocument.isEmpty()) {
			HSCdocumentbyteArray = HSCdocument.getBytes();
			bTMAdminAssociateProfile.setHscdocumentfile(HSCdocumentbyteArray);
			bTMAdminAssociateProfile.setHscdocumentfilename(HSCdocument.getOriginalFilename());
		}

		if (UGdocument != null && !UGdocument.isEmpty()) {
			UGdocumentbyteArray = UGdocument.getBytes();
			bTMAdminAssociateProfile.setUgdocumentfile(UGdocumentbyteArray);
			bTMAdminAssociateProfile.setUgdocumenfilename(UGdocument.getOriginalFilename());
		}
		if (Diplomadocument != null && !Diplomadocument.isEmpty()) {
			DiplomadocumentbyteArray = Diplomadocument.getBytes();
			bTMAdminAssociateProfile.setDiplomafile(DiplomadocumentbyteArray);
			bTMAdminAssociateProfile.setDiplomafilename(Diplomadocument.getOriginalFilename());
		}
		if (pgdocument != null && !pgdocument.isEmpty()) {
			pgdocumentbyteArray = pgdocument.getBytes();
			bTMAdminAssociateProfile.setPgdocumentfile(pgdocumentbyteArray);
			bTMAdminAssociateProfile.setPgdocumentfilename(pgdocument.getOriginalFilename());
		}
		String sessionUserId = (String) rq.getSession().getAttribute("USERID");
		String sessionUserName = (String) rq.getSession().getAttribute("USERNAME");

		String msg = adminOperServices.addAssociateUser(bTMAdminAssociateProfile, formmode, userId, filename, filetype,
				sessionUserId, profilebyteArray);
		return msg;
	}

 

 
	@RequestMapping(value = "adminProfileMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminProfileMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {

		String userId = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("adminProfileManagerList", adminOperServices.getProfileManagerlist());

		} else if (formmode.equals("list1")) {

			md.addAttribute("formmode", "list1");
			md.addAttribute("adminProfileManagerList", adminOperServices.getProfileManagerlist());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("adminProfileManager", adminOperServices.getProfileManager(resId));

		} else if (formmode.equals("view1")) {

			md.addAttribute("formmode", "view1");
			md.addAttribute("adminProfileManager", adminOperServices.getProfileManager(resId));

		} else if (formmode.equals("verifyList")) {

			md.addAttribute("formmode", "verifyList");
			md.addAttribute("adminProfileManagerList", adminOperServices.getProfileManagerlist());

		} else if (formmode.equals("verify")) {

			md.addAttribute("formmode", "verify");
			md.addAttribute("adminProfileManager", adminOperServices.getProfileManager(resId));

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", "edit");
			md.addAttribute("adminProfileManager", adminOperServices.getProfileManager(resId));

		} else if (formmode.equals("deleteList")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("adminProfileManagerList", adminOperServices.getProfileManagerlist());

		} else if (formmode.equals("delete")) {

			md.addAttribute("formmode", "delete");
			md.addAttribute("adminProfileManager", adminOperServices.getProfileManager(resId));

		}

		return "BTMAdminProfileMaster";
	}

	@RequestMapping(value = "adminProfileMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String adminProfileMasterAdd(@RequestParam("formmode") String formmode, Model md,
			@ModelAttribute BTMAdminProfileManager btmAdminProfileManager, HttpServletRequest rq) {

		String msg = adminOperServices.addProfileDetails(btmAdminProfileManager, formmode);
		return msg;
	}

	/*
	 * @RequestMapping(value = "adminLeaveMaster", method = { RequestMethod.GET,
	 * RequestMethod.POST }) public String adminLeaveMaster(@RequestParam(required =
	 * false) String formmode,@RequestParam(required = false) BigDecimal year,
	 * 
	 * @RequestParam(required = false) BigDecimal resId, @RequestParam(required =
	 * false) String RefId, Model md, HttpServletRequest req) throws ParseException
	 * { String userId = (String) req.getSession().getAttribute("USERID");
	 * md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
	 * md.addAttribute("menu", "BTMHeaderMenu"); BigDecimal year1 = new
	 * BigDecimal(Calendar.getInstance().get(Calendar.YEAR)); if (formmode == null
	 * || formmode.equals("list")) {
	 * 
	 * md.addAttribute("formmode", "list"); md.addAttribute("AdminLeaveList",
	 * leaveMasterRep.getAdminLeaveList1(year1));
	 * 
	 * }else if (formmode.equals("list1")) {
	 * 
	 * md.addAttribute("formmode", "list1"); md.addAttribute("AdminLeaveList",
	 * leaveMasterRep.getAdminLeaveList1(year));
	 * 
	 * }else if (formmode.equals("view")) {
	 * 
	 * md.addAttribute("formmode", "view"); md.addAttribute("leaveMaster",
	 * onDutyServices.getLeaveDetail(resId)); md.addAttribute("AdminLeaveList",
	 * leaveMasterCounterRep.getLeaveCounterlist(RefId));
	 * 
	 * }
	 * 
	 * return "BTMAdminLeaveMaster"; }
	 */

	@RequestMapping(value = "adminLeaveMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminLeaveMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) BigDecimal year, @RequestParam(required = false) BigDecimal resId,
			@RequestParam(required = false) String RefId, @RequestParam(required = false) String datelist,
			@RequestParam(required = false) String datelist1, @RequestParam(required = false) String datelist2,
			@RequestParam(required = false) String datelist3, @RequestParam(required = false) String datelist4,
			@RequestParam(required = false) String datelist5, @RequestParam(required = false) String datelist2025,
			Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		BigDecimal year1 = new BigDecimal(Calendar.getInstance().get(Calendar.YEAR));
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			System.out.println("================" + datelist);
			System.out.println("================2222222222222" + datelist1);

			md.addAttribute("AdminLeaveList", leaveMasterRep.getAdminLeaveList11(year1));

			/*
			 * List<String> list = Branch_reps.find_branch_id();
			 * md.addAttribute("branchIds", list);
			 */
			
			List<String> OrgIds = TSK_OrganizationMasterReps.get_org();
			md.addAttribute("OrgIds", OrgIds);

			/*---Update 2025---*/
			if (datelist2025 != null) {
				System.out.println("================+++++++++++++" + datelist2025);

				List<LeaveMaster> get2025 = leaveMasterRep.getAdminLeaveList2025(datelist2025);
				md.addAttribute("AdminLeaveList", get2025);

			}

			if (datelist != null) { // Optional: Print the value to the console if
				System.out.println("================+++++++++++++" + datelist);

				List<LeaveMaster> adminvalues = leaveMasterRep.getAdminLeaveList111(datelist);
				/* List<LeaveMaster> adminvalues = leaveMasterRep.gettestig(datelist); */
				md.addAttribute("AdminLeaveList", adminvalues);
			}
			if (datelist1 != null) {
				// Optional: Print the value to the console if needed
				System.out.println("================+++++++++++++" + datelist1);

				List<LeaveMaster> adminvalues1 = leaveMasterRep.getAdmindetailsit1(datelist1);
				// List<LeaveMaster> adminvalues = leaveMasterRep.gettestig(datelist);
				md.addAttribute("AdminLeaveList", adminvalues1);
				;

				// Continue with the logic using adminvalues as needed
			}

			// Continue with the logic using adminvalues as needed }

			if (datelist2 != null) {
				// Optional: Print the value to the console if needed
				System.out.println("================+++++++++++++" + datelist1);

				List<LeaveMaster> adminvalues1 = leaveMasterRep.getAdmindetailsit2(datelist2);
				// List<LeaveMaster> adminvalues = leaveMasterRep.gettestig(datelist);
				md.addAttribute("AdminLeaveList", adminvalues1);
				;

				// Continue with the logic using adminvalues as needed
			}
			if (datelist3 != null) {
				// Optional: Print the value to the console if needed
				System.out.println("================+++++++++++++" + datelist1);

				List<LeaveMaster> adminvalues1 = leaveMasterRep.getAdmindetailsit3(datelist3);
				// List<LeaveMaster> adminvalues = leaveMasterRep.gettestig(datelist);
				md.addAttribute("AdminLeaveList", adminvalues1);
				;

				// Continue with the logic using adminvalues as needed
			}

			if (datelist4 != null) {
				// Optional: Print the value to the console if needed
				System.out.println("================+++++++++++++" + datelist4);

				List<LeaveMaster> adminvalues1 = leaveMasterRep.getAdmindetailsit4(datelist4);
				// List<LeaveMaster> adminvalues = leaveMasterRep.gettestig(datelist);
				md.addAttribute("AdminLeaveList", adminvalues1);
				;

				// Continue with the logic using adminvalues as needed
			}

			if (datelist5 != null) {
				// Optional: Print the value to the console if needed
				System.out.println("================+++++++++++++" + datelist5);

				List<LeaveMaster> adminvalues1 = leaveMasterRep.getAdmindetailsit5(datelist5);
				// List<LeaveMaster> adminvalues = leaveMasterRep.gettestig(datelist);
				md.addAttribute("AdminLeaveList", adminvalues1);
				;

				// Continue with the logic using adminvalues as needed
			}

			// md.addAttribute("AdminLeaveList", leaveMasterRep.getAdminLeaveList11(year1));

		} else if (formmode.equals("list1")) {

			// md.addAttribute("formmode", "list1");
			// md.addAttribute("AdminLeaveList", leaveMasterRep.getAdminLeaveList1(year));
			// md.addAttribute("AdminLeaveList", leaveMasterRep.getAdminLeaveList2(year1));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("leaveMaster", onDutyServices.getLeaveDetail(resId));
			md.addAttribute("AdminLeaveList", leaveMasterCounterRep.getLeaveCounterlist(RefId));

		} else if (formmode.equals("view1")) {

			md.addAttribute("formmode", "view1");
			// md.addAttribute("leaveMaster", onDutyServices.getLeaveDetail(resId));
			// md.addAttribute("AdminLeaveList",
			// leaveMasterCounterRep.getLeaveCounterlist(RefId));

		}

		return "BTMAdminLeaveMaster";
	}

	@RequestMapping(value = "adminODMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminODMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) BigDecimal year, @RequestParam(required = false) String resId,
			@RequestParam(required = false) String RefId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		BigDecimal year2 = new BigDecimal(Calendar.getInstance().get(Calendar.YEAR));
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("AdminODList", onDutyRep.getOdMasterList1(year2));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("leaveMaster", onDutyServices.getODDetail(resId));
			md.addAttribute("AdminODList", bTMAdminOndutyCountRep.getOndutyCounterlist(RefId));

		}

		return "BTMAdminODMaster";
	}

	@RequestMapping(value = "adminHolidayMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminHolidayMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) BigDecimal resId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("view")) {

			md.addAttribute("formmode", "view");

			md.addAttribute("formmode", "list");
			md.addAttribute("adminHolidayList", adminOperServices.getHolidaylist());
			/*
			 * List<String> list = Branch_reps.find_branch_id();
			 * md.addAttribute("branchIds", list);
			 */
			List<String> OrgIds = TSK_OrganizationMasterReps.get_org();
			md.addAttribute("OrgIds", OrgIds);

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", "add");
			md.addAttribute("adminHolidayProfile", new BTMAdminHolidayMaster());
			Integer currentSeqValue = btmAdminHolidayMasterRep.getCurrentSequenceValue("Holidayseq"); // Method to fetch
																										// the sequence
																										// value
			md.addAttribute("number", currentSeqValue);
			/*
			 * List<String> list = Branch_reps.find_branch_id();
			 * md.addAttribute("branchIds", list);
			 */
			List<String> OrgIds = TSK_OrganizationMasterReps.get_org();
			md.addAttribute("OrgIds", OrgIds);
			

		} else if (formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("adminHolidayList", adminOperServices.getHolidaylist());
			/*
			 * List<String> list = Branch_reps.find_branch_id();
			 * md.addAttribute("branchIds", list);
			 */
			
			List<String> OrgIds = TSK_OrganizationMasterReps.get_org();
			md.addAttribute("OrgIds", OrgIds);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", "edit");
			md.addAttribute("adminHolidayProfile", adminOperServices.getHolidayDetail(resId));
			/*
			 * List<String> list = Branch_reps.find_branch_id();
			 * md.addAttribute("branchIds", list);
			 */
			List<String> OrgIds = TSK_OrganizationMasterReps.get_org();
			md.addAttribute("OrgIds", OrgIds);

		} else if (formmode.equals("list1")) {

			md.addAttribute("formmode", "list1");
			md.addAttribute("adminHolidayList", adminOperServices.getHolidaylist());
			/*
			 * List<String> list = Branch_reps.find_branch_id();
			 * md.addAttribute("branchIds", list);
			 */
			List<String> OrgIds = TSK_OrganizationMasterReps.get_org();
			md.addAttribute("OrgIds", OrgIds);

		} else if (formmode.equals("view1")) {

			md.addAttribute("formmode", "view1");
			md.addAttribute("adminHolidayProfile", adminOperServices.getHolidayDetail(resId));
			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);

		}
		return "BTMAdminHolidayMaster";
	}

	@RequestMapping(value = "adminHolidayMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String adminHolidayMasterAdd(@RequestParam("formmode") String formmode,
			@RequestParam(required = false) BigDecimal recordNo,
			@ModelAttribute BTMAdminHolidayMaster btmAdminHolidayMaster, Model md, HttpServletRequest rq) {

		String msg = adminOperServices.addHolidayDetails(btmAdminHolidayMaster, formmode, recordNo);
		return msg;
	}

	@RequestMapping(value = "adminDocumentMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminDocumentMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("view")) {

			md.addAttribute("formmode", "view");

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", "add");
			md.addAttribute("profileManagers", btmAdminAssociateProfileRep.getEmployeedetail2());
		} else if (formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("adminDocumentList", btmDocumentMasterRep.getDocumentlist());

		} else if (formmode.equals("view1")) {

			md.addAttribute("formmode", "view1");
			md.addAttribute("adminDocMaster", btmDocumentMasterRep.getDocument(resId));

		} else if (formmode.equals("list1")) {

			md.addAttribute("formmode", "list1");
			md.addAttribute("adminDocumentList", btmDocumentMasterRep.getDocumentlist());

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", "edit");
			md.addAttribute("adminDocMaster", btmDocumentMasterRep.getDocument(resId));

		}

		return "BTMAdminDocMaster";
	}

	@RequestMapping(value = "adminDocumentMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String adminDocumentMasterAdd(@RequestParam("formmode") String formmode,
			@RequestParam(required = false) BigDecimal recordNo, @ModelAttribute BTMDocumentMaster btmDocumentMaster,
			Model md, HttpServletRequest rq) {

		String msg = adminOperServices.addDocumentUser(btmDocumentMaster, formmode);
		return msg;
	}

	@RequestMapping(value = "adminReferenceMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminReferenceMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(userId));

		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("view")) {

			md.addAttribute("formmode", "view");

		} else if (formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("adminRefCodeMaster", btmRefCodeMasterRep.getRefCodelist());

		} else if (formmode.equals("view1")) {

			md.addAttribute("formmode", "view1");
			md.addAttribute("RefCodeMaster", adminOperServices.getRefMaster(resId));

		} else if (formmode.equals("list1")) {

			md.addAttribute("formmode", "list1");
			md.addAttribute("adminRefCodeMaster", btmRefCodeMasterRep.getRefCodelist());

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", "edit");
			md.addAttribute("RefCodeMaster", adminOperServices.getRefMaster(resId));

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("deleteList")) {

			md.addAttribute("formmode", "deleteList");
			md.addAttribute("adminRefCodeMaster", btmRefCodeMasterRep.getRefCodelist());

		} else if (formmode.equals("delete")) {

			md.addAttribute("formmode", "delete");
			md.addAttribute("RefCodeMaster", adminOperServices.getRefMaster(resId));
		}

		return "BTMAdminRefCodeMaster";
	}

	@RequestMapping(value = "adminRefCodeMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String adminRefCodeAdd(@RequestParam("formmode") String formmode, @RequestParam("ref_id") String ref_id,
			@ModelAttribute BTMRefCodeMaster btmRefCodeMaster, Model md, HttpServletRequest rq) {
		String msg = adminOperServices.addRefCodeMaster(btmRefCodeMaster, formmode, ref_id);
		return msg;
	}

	@RequestMapping(value = "adminProjectMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminProjectMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, @RequestParam(required = false) String resName,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("view")) {

			md.addAttribute("formmode", "view");

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", "add");
			md.addAttribute("adminprojectMaster", new BTMProjectMaster());

		} else if (formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("adminProjectMaster", btmProjectMasterRep.getProjectlist());

		} else if (formmode.equals("list1")) {

			md.addAttribute("formmode", "list1");
			md.addAttribute("adminProjectMaster", btmProjectMasterRep.getProjectlist());

		} else if (formmode.equals("deleteList")) {

			md.addAttribute("formmode", "deleteList");
			md.addAttribute("adminProjectMaster", btmProjectMasterRep.getProjectlist());

		} else if (formmode.equals("view1")) {

			md.addAttribute("formmode", "view1");
			md.addAttribute("adminProjectMaster", btmProjectMasterRep.getProjectlist());
			md.addAttribute("adminprojectMaster", btmProjectMasterRep.getProjectShow(resId, resName));
			md.addAttribute("projectDetails", btmProjectDetailsRep.getProjectDetails(resId));
			md.addAttribute("teamDetails", btmProjectTeamDetailsRep.getProjectTeamDetails(resId));

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", "edit");
			md.addAttribute("adminProjectMaster", btmProjectMasterRep.getProjectlist());
			md.addAttribute("adminprojectMaster", btmProjectMasterRep.getProjectShow(resId, resName));
			md.addAttribute("projectDetails", btmProjectDetailsRep.getProjectDetails(resId));
			md.addAttribute("teamDetails", btmProjectTeamDetailsRep.getProjectTeamDetails(resId));

		} else if (formmode.equals("delete")) {

			md.addAttribute("formmode", "delete");
			md.addAttribute("adminprojectMaster", btmProjectMasterRep.getProjectShow(resId, resName));
			md.addAttribute("projectDetails", btmProjectDetailsRep.getProjectDetails(resId));
			md.addAttribute("teamDetails", btmProjectTeamDetailsRep.getProjectTeamDetails(resId));

		}

		return "BTMAdminProjectMaster";
	}

	@RequestMapping(value = "adminProjectMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String adminProjectMasterAdd(@RequestParam("formmode") String formmode,
			@RequestParam(required = false) String userid, @ModelAttribute BTMProjectMaster btmProjectMaster,
			@ModelAttribute ProjectDetails projectDetails, @ModelAttribute ProjectTeamDetails projectTeamDetails,
			Model md, HttpServletRequest rq) {

		// String userid2 = (String) rq.getSession().getAttribute("USERID");
		String msg = adminOperServices.addProjectMaster(btmProjectMaster, projectDetails, projectTeamDetails, formmode,
				userid);
		return msg;
	}

	@RequestMapping(value = "adminTravelMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminTravelMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("TravelList", adminOperServices.getTravelMasterList());
			/*
			 * List<String> list = Branch_reps.find_branch_id();
			 * md.addAttribute("branchIds", list);
			 */
			List<String> OrgIds = TSK_OrganizationMasterReps.get_org();
			md.addAttribute("OrgIds", OrgIds);


		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("adminTravelMaster", adminOperServices.getTravelMaster(resId));
			/*
			 * List<String> list = Branch_reps.find_branch_id();
			 * md.addAttribute("branchIds", list);
			 */
			List<String> OrgIds = TSK_OrganizationMasterReps.get_org();
			md.addAttribute("OrgIds", OrgIds);

		}

		return "BTMAdminTravelMaster";
	}

	@RequestMapping(value = "adminExpenseReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminExpenseReport(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, @RequestParam(required = false) String userid, Model md,
			HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("expenseReport", adminOperServices.getExpenseReportlist());
			/*
			 * List<String> list = Branch_reps.find_branch_id();
			 * md.addAttribute("branchIds", list);
			 */
			List<String> OrgIds = TSK_OrganizationMasterReps.get_org();
			md.addAttribute("OrgIds", OrgIds);

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("adminExpenseReport", adminOperServices.getReportManager(resId));
			/*
			 * List<String> list = Branch_reps.find_branch_id();
			 * md.addAttribute("branchIds", list);
			 */
			List<String> OrgIds = TSK_OrganizationMasterReps.get_org();
			md.addAttribute("OrgIds", OrgIds);

		}
		return "BTMAdminExpenseReport";
	}

	@RequestMapping(value = "adminWorkAssignment", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminWorkAssignment(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("WorkAssignmentList", btmWorkAssignmentRep.getWorkAssignlist());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("WorkAssignment", btmWorkAssignmentRep.getWorkAssign(resId));

		}

		return "BTMAdminWorkAssignment";
	}

	@RequestMapping(value = "adminTimesheetMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminTimesheetMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		String empid = (String) req.getSession().getAttribute("USERID");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");

			md.addAttribute("Profile", btmAdminAssociateProfileRep.getAssociatelist());

		} else if (formmode.equals("list1")) {

			md.addAttribute("formmode", "list1");
			md.addAttribute("TimesheetList", btmEmpTimeSheetRep.getTimeSheetdata(resId));

		}

		return "BTMAdminTimesheetMaster";
	}

	@RequestMapping(value = "adminCalendarMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminCalendarMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String year, @RequestParam(required = false) String month,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("adminCalendarMaster", adminOperServices.getCalendarlist());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("adminHolidayDetails", adminOperServices.getMonthlyHolidaylist(year, month));

		}

		return "BTMAdminCalendarMaster";
	}

	@RequestMapping(value = "adminDailyActivity", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminDailyActivity(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			// md.addAttribute("adminCalendarMaster", adminOperServices.getCalendarlist());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			// md.addAttribute("adminHolidayDetails",
			// adminOperServices.getMonthlyHolidaylist(year, month));

		}
		return "BTMAdminDailyActivity";
	}

	@RequestMapping(value = "adminDocumentMaintenance", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminDocumentMaintenance(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String emp_id, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {

		md.addAttribute("allusers", resourceMasterRepo.gettotaluser());
		md.addAttribute("Document", documentMainRep.Documents());
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		return "BTMAdminDocMaintenance";
	}

	@RequestMapping(value = "uploadDocss", method = RequestMethod.POST)
	@ResponseBody
	public String uploadDoc(@RequestParam("document") MultipartFile file, Model md, HttpServletRequest rq,
			@RequestParam(required = false) String doc_id, @RequestParam(required = false) String doc_name,
			@RequestParam(required = false) String doc_desc, @RequestParam(required = false) String doc_type,
			@RequestParam(required = false) String doc_group, @RequestParam(required = false) String file_name)
			throws ParseException, IOException {
		String userId = (String) rq.getSession().getAttribute("USERID");
		String username = (String) rq.getSession().getAttribute("USERNAME");
		try {
			logger.info("Received file: {}", file.getOriginalFilename());

			byte[] bytes = file.getBytes();

			String msg = onDutyServices.uploadDocss(bytes, doc_id, doc_name, doc_desc, doc_type, doc_group, file_name,
					userId, username, file.getOriginalFilename());

			logger.info("File uploaded successfully: {}", file.getOriginalFilename());
			return msg;
		} catch (IOException e) {
			logger.error("File upload failed: {}", e.getMessage());
			return "File upload failed: " + e.getMessage();
		}
	}

	@GetMapping("/{docRefNo}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String docRefNo) {
		BTMDocumentMaster document = btmDocumentMasterRep.getDocument(docRefNo);
		if (document != null) {
			byte[] documentContent = document.getDocument();
			String fileName = document.getDoc_location();
			String nameoffile = document.getFile_name();
			String fileType = document.getFile_type();

			if (fileName.endsWith(".png")) {
				fileType = "image/png";
			} else if (fileName.endsWith(".pdf")) {
				fileType = "application/pdf";
			} else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
				fileType = "image/jpeg";
			} else if (fileName.endsWith(".mp4")) {
				fileType = "video/mp4";
			} else if (fileType == null || fileType.isEmpty()) {
				// Set a default file type if file_type is null or unrecognized
				fileType = "application/octet-stream";
			}
			ByteArrayResource resource = new ByteArrayResource(documentContent);
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName=" + fileName)
					.contentType(MediaType.parseMediaType(fileType)).contentLength(documentContent.length)
					.body(resource);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(value = "dateandDay", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<LeaveResponseModal> dateandDay(@RequestParam(required = false) String firstval,
			@RequestParam(required = false) String lastval, @ModelAttribute DocumentMaintenance DocMain, Model md,
			HttpServletRequest rq, RedirectAttributes ra) throws ParseException, IOException {

		ArrayList<LeaveResponseModal> arl = onDutyServices.dateSelector(firstval, lastval);

		return arl;
	}

// ============================================  Admin Module End =====================================

//=============================================  Operation Module =======================================

	@RequestMapping(value = "changePassword", method = { RequestMethod.GET, RequestMethod.POST })
	public String changePassword(@RequestParam(required = false) String formmode, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		String userid = (String) req.getSession().getAttribute("USERID");

		md.addAttribute("profileManager", btmAdminAssociateProfileRep.getEmployeedetail(userid));
		return "BTMChangePassword";
	}

	@RequestMapping(value = "changePasswordLogin", method = { RequestMethod.GET, RequestMethod.POST })
	public String changePasswordLogin(@RequestParam(required = false) String formmode, Model md, HttpServletRequest req)
			throws ParseException {
		return "BTMChangePasswordLogin";
	}

	@RequestMapping(value = "changePasswordReq", method = RequestMethod.POST)
	@ResponseBody
	public String changePasswordReq(@RequestParam("oldpass") String oldpass, @RequestParam("newpass") String newpass,
			@RequestParam("userid") String userid, Model md, HttpServletRequest rq) {
		String msg = loginServices.changePasswordfromUP(oldpass, newpass, userid);
		md.addAttribute("message", "succes");
		md.addAttribute("profileManager", btmAdminAssociateProfileRep.getEmployeedetail(userid));
		return msg;
	}

	@RequestMapping(value = "changePasswordRequest", method = RequestMethod.POST)
	@ResponseBody
	public String changePasswordReq(@RequestParam("oldpass") String oldpass, @RequestParam("newpass") String newpass,
			Model md, HttpServletRequest rq) {
		String userid = (String) rq.getSession().getAttribute("USERID");
		System.out.println("OLD PASSWORD" + oldpass);
		System.out.println("NEW PASSWORD" + newpass);
		System.out.println("THE USER ID" + userid);

		String msg = loginServices.changePassword(oldpass, newpass, userid);

		// Invalidate (clear) the session
		rq.getSession().invalidate();

		return msg;
	}

	@RequestMapping(value = "applyLeave", method = { RequestMethod.GET, RequestMethod.POST })
	public String applyLeave(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) BigDecimal resId, @RequestParam(required = false) BigDecimal casualleave,
			String empid, Model md, HttpServletRequest req) throws ParseException {

		String userId = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		md.addAttribute("profileManager", btmAdminAssociateProfileRep.getEmployeedetail(userId));
		// md.addAttribute("leavecount",
		// btmAdminOrganizationMasterRep.getleavecountforcasual(casualleave));

		md.addAttribute("Approvalmail", placementServices.getno_of_stages());
		List<String> days = placementServices.getno_of_days();

		List<ResourceMaster> emails = resourceMasterRepo.getmail();
		md.addAttribute("emails", emails);

		md.addAttribute("ColumnValue", 0);

		// md.addAttribute("getno_of_days", days);
		// md.addAttribute("LeaveList", leaveMasterRep.getLeaveListbyRecord(resId));
		// md.addAttribute("srl_no", onDutyServices.getSrlNoValue());

		return "BTMLeaveMaster";
	}

	@GetMapping("/getleavefrom")
	@ResponseBody
	public long getleavefromassociate(@RequestParam(required = false) String RESOURCE_ID, String CATEGORY) {
		String msg = "fetched";
		System.out.println("THE USER ID" + RESOURCE_ID);
		System.out.println("the category" + CATEGORY);

		BTMAdminAssociateProfile entity = btmAdminAssociateProfileRep.getEmployeedetail(RESOURCE_ID);
		System.out.println("the count" + entity.getResource_name());
		Integer countertable = leaveMasterCounterRep.getcounter(CATEGORY, RESOURCE_ID);
		if (countertable == null) {
			countertable = 0;
		}
		long finalCL = 0;

		if (CATEGORY.equals("Casual Leave")) {
			System.out.println("the enter casual");
			Long casualleave = entity.getCasualLeave();
			finalCL = casualleave - countertable;
			System.out.println("the cl LEAVE " + finalCL);
		} else if (CATEGORY.equals("Maternity Leave")) {
			Long casualleave = entity.getMaternityLeave();
			finalCL = casualleave - countertable;
			System.out.println("the ml LEAVE " + finalCL);

		} else if (CATEGORY.equals("Sick Leave")) {
			Long casualleave = entity.getSickLeave();
			finalCL = casualleave - countertable;
			System.out.println("the sl LEAVE " + finalCL);

		} else if (CATEGORY.equals("Paternity Leave")) {
			Long casualleave = entity.getPaternityLeave();
			finalCL = casualleave - countertable;
			System.out.println("the pl LEAVE " + finalCL);

		} else if (CATEGORY.equals("Yearly Leave")) {
			Long casualleave = entity.getYearlyLeave();
			finalCL = casualleave - countertable;
			System.out.println("the yl LEAVE " + finalCL);

		}

		return finalCL;
	}

	@RequestMapping(value = "mailsubmit", method = RequestMethod.POST)
	@ResponseBody
	public String mailsubmit(@RequestParam(required = false) String email1,
			@RequestParam(required = false) String email2, @RequestParam(required = false) String email3,
			@RequestParam(required = false) String email4, @RequestParam(required = false) String email5,
			HttpServletRequest req) {

		String userId = (String) req.getSession().getAttribute("USERID");
		String username = (String) req.getSession().getAttribute("USERNAME");
		// String b = a;
		// String to = b;
		String from = "valarmathi.s@bornfire.in";
		String usernamelogin = "valarmathi.s@bornfire.in"; // change accordingly
		String password = "MiddleEast#123"; // change accordingly
		String host = "sg2plzcpnl491716.prod.sin2.secureserver.net";
		// String ref_no = d;
		String mail = maile.sendmail(userId, username, email1, email2, email3, email4, email5, usernamelogin, password,
				from, host);

		return mail;
	}

	@RequestMapping(value = "leaveMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String leaveMasterAdd(@ModelAttribute LeaveMaster leaveMaster,
			@ModelAttribute SampleLeaveMaster sampleLeaveMaster, @RequestParam(required = false) String formmode,
			@RequestParam(required = false) String employee_id, @RequestParam(required = false) String year,
			@RequestParam(required = false) String from_date, Model md, HttpServletRequest rq)
			throws ParseException, SQLException {
		String msg = onDutyServices.addLeave(leaveMaster, sampleLeaveMaster, formmode);
		return msg;
	}

	@RequestMapping(value = "markOnDuty", method = { RequestMethod.GET, RequestMethod.POST })
	public String markOnDuty(@RequestParam(required = false) String formmode, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		String userid1 = (String) req.getSession().getAttribute("USERID");

		md.addAttribute("profileManager", btmAdminAssociateProfileRep.getEmployeedetail(userid1));
		md.addAttribute("projectManager", btmProjectMasterRep.getProjectlist());
		BTMAdminAssociateProfile test = btmAdminAssociateProfileRep.getEmployeedetail(userid1);
		// md.addAttribute("srl_no", onDutyServices.getSrlNo());

		return "BTMMarkOnDuty";
	}

	@RequestMapping(value = "onDutyAdd", method = RequestMethod.POST)
	@ResponseBody
	public String onDutyAdd(@ModelAttribute OnDuty onDuty, @ModelAttribute BTMAdminSampleOD btmAdminOndutyCount,
			@RequestParam(required = false) String formmode, Model md, HttpServletRequest rq)
			throws ParseException, SQLException {

		String msg = onDutyServices.addOnDuty(onDuty, btmAdminOndutyCount, formmode);
		return msg;
	}

	@RequestMapping(value = "travelMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String travelMaster(@RequestParam(required = false) String formmode, Model md, HttpServletRequest req)
			throws ParseException {

		String userid1 = (String) req.getSession().getAttribute("USERID");
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		String Travel = "TRA";
		md.addAttribute("travel_Ref", Travel.concat(onDutyServices.getTravelRef()));

		if (formmode == null || formmode.equals("add")) {

			md.addAttribute("formmode", "add");
			md.addAttribute("travelMaster", new BTMTravelMaster());
			md.addAttribute("profileManager", btmAdminAssociateProfileRep.getEmployeedetail(userid1));
			/*
			 * List<String> list = Branch_reps.find_branch_id();
			 * md.addAttribute("branchIds", list);
			 */
			
			List<String> OrgIds = TSK_OrganizationMasterReps.get_org();
			md.addAttribute("OrgIds", OrgIds);

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("profileManager", btmAdminAssociateProfileRep.getEmployeedetail(userid1));

		}

		return "BTMTravelMaster";
	}

	@RequestMapping(value = "travelMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String travelMasterAdd(@ModelAttribute BTMTravelMaster BTMtravelMaster, Model md, HttpServletRequest rq) {

		String msg = onDutyServices.addTravelList(BTMtravelMaster);
		return msg;
	}

	@RequestMapping(value = "submittravel", method = RequestMethod.POST)
	@ResponseBody
	public String submittravel(@RequestParam(required = false) String tra_ref,
			@ModelAttribute BTMTravelMaster btmTravelMaster, Model md, HttpServletRequest rq) {

		String msg = onDutyServices.addTravelList(btmTravelMaster);

		System.out.println(btmTravelMaster.getAss_id());
		System.out.println(btmTravelMaster.getClient_id());
		System.out.println(btmTravelMaster.getAss_name());
		System.out.println(btmTravelMaster.getPrj_id());
		return msg;
	}

	@RequestMapping(value = "claimExpenses", method = { RequestMethod.GET, RequestMethod.POST })
	public String claimExpenses(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, Model md, HttpServletRequest req) throws ParseException {

		String userid2 = (String) req.getSession().getAttribute("USERID");
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		String Expense = "EXP";
		md.addAttribute("expense_Ref", Expense.concat(onDutyServices.getExpRef()));

		if (formmode == null || formmode.equals("add")) {

			md.addAttribute("formmode", "add");
			md.addAttribute("adminExpenseReport", btmAdminAssociateProfileRep.getEmployeedetail(userid2));
			/*
			 * List<String> list = Branch_reps.find_branch_id();
			 * md.addAttribute("branchIds", list);
			 */
			
			List<String> OrgIds = TSK_OrganizationMasterReps.get_org();
			md.addAttribute("OrgIds", OrgIds);
			
		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("adminExpenseReport", btmAdminAssociateProfileRep.getEmployeedetail(userid2));
			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);

		}
		return "BTMClaimExpenses";
	}

	@RequestMapping(value = "claimExpensesAdd", method = RequestMethod.POST)
	@ResponseBody
	public String claimExpensesAdd(@RequestParam("formmode") String formmode,
			@ModelAttribute ExpenseMaster expenseMaster, Model md, HttpServletRequest rq) {

		String msg = onDutyServices.addExpenseReport(expenseMaster, formmode);
		return msg;
	}

	@RequestMapping(value = "workAssignment", method = { RequestMethod.GET, RequestMethod.POST })
	public String workAssignment(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, Model md, HttpServletRequest req) throws ParseException {

		String userid1 = (String) req.getSession().getAttribute("USERID");
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("WorkAssignmentList", btmWorkAssignmentRep.getWorkAssignListById(userid1));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("WorkAssignment", btmWorkAssignmentRep.getWorkAssign(resId));

		}

		return "BTMWorkAssign";
	}

	@RequestMapping(value = "timeSheet", method = { RequestMethod.GET, RequestMethod.POST })
	public String timeSheet(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String emp_id, String month, BigDecimal year, Model md,
			HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		String userid2 = (String) req.getSession().getAttribute("USERID");
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("formmode", "list");

			md.addAttribute("TimesheetList", btmEmpTimeSheetRep.getTimeSheetLast(userid2));

		} else if (formmode.equals("modify")) {

			md.addAttribute("formmode", "modify");
			md.addAttribute("TimesheetList", btmEmpTimeSheetRep.getTimeSheetModify(emp_id, year, month));

		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");
			md.addAttribute("TimesheetList", onDutyServices.getTimeSheetselect(userid2));
		} else if (formmode.equals("Yes")) {
			md.addAttribute("formmode", "Yes");
			md.addAttribute("TimesheetList", onDutyServices.getTimeSheetselect(userid2));
		} else if (formmode.equals("addnew")) {
			md.addAttribute("formmode", "addnew");
		}

		return "BTMTimesheetOperation";
	}

	@RequestMapping(value = "timeSheetedit", method = { RequestMethod.GET, RequestMethod.POST })
	public String timeSheetedit(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String month, @RequestParam(required = false) String year,
			@RequestParam(required = false) String empid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {

		String userid2 = (String) req.getSession().getAttribute("USERID");
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("formmode", "list");
			md.addAttribute("TimesheetList", timeSheetBeanRep.getTimesheetList(userid2));
			// md.addAttribute("WorkList", btmWorkAssignmentRep.getWorkMaster(id2));
			// md.addAttribute("TimesheetList",
			// timesheetManagementRep.getTimesheetList(id2));
			// md.addAttribute("WorkAssignment", btmWorkAssignmentRep.getWorkAssign(resId));
		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("TimesheetList", timeSheetBeanRep.getTimesheetList(userid2));
			// md.addAttribute("WorkList", btmWorkAssignmentRep.getWorkMaster(id2));

			md.addAttribute("timeSheetList", timeSheetBeanRep.getTimeSheetdata(empid, year, month));
			// md.addAttribute("TimesheetList",
			// timesheetManagementRep.getTimesheetList(id2));
			// md.addAttribute("WorkAssignment", btmWorkAssignmentRep.getWorkAssign(id2));

		}

		return "BTMTimeSheet";
	}

	@RequestMapping(value = "timeSheetAdd", method = RequestMethod.POST)
	@ResponseBody
	public String timeSheetAdd(@ModelAttribute BTMEmpTimeSheet btmEmpTimeSheet, Model md, HttpServletRequest rq) {
		String msg = onDutyServices.addTimeSheet(btmEmpTimeSheet);
		return msg;
	}

	@RequestMapping(value = "timeSheetEdit", method = RequestMethod.POST)
	@ResponseBody
	public String timeSheetEdit(@RequestParam(required = false) String empid,
			@RequestParam(required = false) BigDecimal year, @RequestParam(required = false) String month,
			@ModelAttribute BTMEmpTimeSheet btmEmpTimeSheet, Model md, HttpServletRequest rq) {

		String msg = onDutyServices.EditTimeSheet(btmEmpTimeSheet, empid, year, month);
		return msg;
	}

	@RequestMapping(value = "issueTracker", method = { RequestMethod.GET, RequestMethod.POST })
	public String issueTracker(@RequestParam(required = false) String formmode, String srl_no,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("view")) {
			md.addAttribute("formmode", "view");
		} else if (formmode.equals("list")) {
			md.addAttribute("formmode", "list");

			md.addAttribute("IssueTracker", issueTrackerRep.findAll());

		} else if (formmode.equals("elist")) {

			md.addAttribute("formmode", "elist");
			md.addAttribute("issueTracker", issueTrackerRep.getIssueList());

		} else if (formmode.equals("view1")) {
			md.addAttribute("formmode", "view1");
			md.addAttribute("allusers", resourceMasterRepo.gettotaluser());
			md.addAttribute("issueview", issueTrackerRep.getIssue(srl_no));

		}

		else if (formmode.equals("modify")) {

			md.addAttribute("formmode", "modify");
			md.addAttribute("issueview", issueTrackerRep.getIssue(srl_no));
			md.addAttribute("allusers", resourceMasterRepo.gettotaluser());
			md.addAttribute("issuemodify", issueTrackerRep.getIssue(srl_no));

		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");
			md.addAttribute("allusers", resourceMasterRepo.gettotaluser());
		} else if (formmode.equals("upload")) {
			md.addAttribute("formmode", "upload");
		}
		return "BTMIssueTracker";
	}

	@RequestMapping(value = "issueAdd", method = RequestMethod.POST)
	@ResponseBody
	public String issueAdd(@RequestParam(required = false) String formmode, @ModelAttribute IssueTracker issuetracker,
			Model md, HttpServletRequest rq) {
		String msg = onDutyServices.addissue(issuetracker, formmode);
		return msg;
	}

	@RequestMapping(value = "issueEdit", method = RequestMethod.POST)
	@ResponseBody
	public String issueEdit(@RequestParam(required = false) String formmode, @ModelAttribute IssueTracker issuetracker,
			Model md, HttpServletRequest rq) {
		String msg = onDutyServices.editissue(issuetracker, formmode);
		return msg;
	}

	@RequestMapping(value = "downFormat", method = RequestMethod.GET)
	public ResponseEntity<Resource> generateExcelReport() throws IOException {
		List<IssueTracker> Issues = issueTrackerRep.getIssueFormat();

		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet();

		int rowCount = 0;
		Row row = sheet.createRow(rowCount++);

		Font font = wb.createFont();
		font.setBold(true);

		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setBorderTop(BorderStyle.THICK);
		cellStyle.setBorderBottom(BorderStyle.THICK);
		cellStyle.setBorderLeft(BorderStyle.THICK);
		cellStyle.setBorderRight(BorderStyle.THICK);
		cellStyle.setFont(font);

		Cell cell = row.createCell(0);
		cell.setCellValue("srl_no");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(1);
		cell.setCellValue("Category");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(2);
		cell.setCellValue("Groups");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(3);
		cell.setCellValue("Product");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(4);
		cell.setCellValue("Module");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(5);
		cell.setCellValue("Screen");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(6);
		cell.setCellValue("Operation");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(7);
		cell.setCellValue("Description");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(8);
		cell.setCellValue("Issue Ref No");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(9);
		cell.setCellValue("Date of Issue");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(10);
		cell.setCellValue("Reported By");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(11);
		cell.setCellValue("Approved By");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(12);
		cell.setCellValue("Nature of Issue");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(13);
		cell.setCellValue("Issue Details");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(14);
		cell.setCellValue("Severity");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(15);
		cell.setCellValue("Remarks");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(16);
		cell.setCellValue("Assigned To");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(17);
		cell.setCellValue("Date of Assigned");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(18);
		cell.setCellValue("Fix Period");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(19);
		cell.setCellValue("Delivery Date");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(20);
		cell.setCellValue("Fix Details");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(21);
		cell.setCellValue("Date of Fix");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(22);
		cell.setCellValue("Tested By");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(23);
		cell.setCellValue("Tested On");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(24);
		cell.setCellValue("Test Result");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(25);
		cell.setCellValue("Issue Status");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(26);
		cell.setCellValue("Turn Around Time");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(27);
		cell.setCellValue("Final Closure");
		cell.setCellStyle(cellStyle);

		cellStyle = wb.createCellStyle();
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);

		for (IssueTracker issue : Issues) {
			row = sheet.createRow(rowCount++);

			int columnCount = 0;

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getSrl_no());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getCategory());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getGroups());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getProduct());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getModule());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getScreen());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getOperation());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getOper_desc());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getIssue_ref_no());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getDate_of_issue());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getRpt_by());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getApr_by());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getNat_of_issue());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getIssue_details());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getIssue_severity());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getIssue_rmks());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getAssign_to());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getAssign_date());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getFix_period().toString());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getDel_date());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getFix_details());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getDate_of_fix());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getTest_by());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getTest_date());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getTest_results());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getIssue_status());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getTat_per().doubleValue());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getFinal_cls());
			cell.setCellStyle(cellStyle);

		}

		ByteArrayOutputStream os = new ByteArrayOutputStream();

		wb.write(os);
		wb.close();

		ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(
				MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=IssueTracker.xlsx");

		ResponseEntity<Resource> response = new ResponseEntity<Resource>(new InputStreamResource(is), headers,
				HttpStatus.OK);

		return response;
	}
//================================== Opertion Module End ======================================================

//==================================== Inquiries Module =========================================================

	@RequestMapping(value = "organizationPolicy", method = { RequestMethod.GET, RequestMethod.POST })
	public String organizationPolicy(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		return "BTMOrganizationPolicy";
	}

	@RequestMapping(value = "holidayMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String holidayMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) BigDecimal resId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("holidayList", adminOperServices.getHolidaylist());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("BTMholidayList", adminOperServices.getHolidayDetail(resId));

		}

		return "BTMHolidayMaster";
	}

	@RequestMapping(value = "associateMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String associateMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		String userid1 = (String) req.getSession().getAttribute("USERID");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("associateMaster", inquiriesServices.getAssociateData(userid1));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("associateMaster", inquiriesServices.getAssociateData(userid1));

		}

		return "BTMAssociateProfile";
	}

	@RequestMapping(value = "profileMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String profileMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		String id2 = (String) req.getSession().getAttribute("USERID");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("profileMasterList", adminOperServices.getProfileManager(id2));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("profileMasterList", adminOperServices.getProfileManager(id2));

		}

		return "BTMProfileMaster";
	}

	@RequestMapping(value = "projectMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String projectMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resName, @RequestParam(required = false) String resId, Model md,
			HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("projectMasterList", adminOperServices.getProjectMasterlist());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("projectList", btmProjectMasterRep.getProjectShow(resId, resName));
			md.addAttribute("projectDetails", btmProjectDetailsRep.getProjectDetails(resId));
			md.addAttribute("projectTeamDetails", btmProjectTeamDetailsRep.getProjectTeamDetails(resId));

		}

		return "BTMInqueriesProjectMaster";
	}

	@RequestMapping(value = "leaveMaster", method = RequestMethod.GET)
	public String leaveMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) BigDecimal resId, @RequestParam(required = false) String RefId, Model md,
			HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		String userid = (String) req.getSession().getAttribute("USERID");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("LeaveList", leaveMasterRep.getLeaveListbyid(userid));
		}

		else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("LeaveList", leaveMasterRep.getLeaveListbyRecord(resId));
			md.addAttribute("LeaveListref", leaveMasterCounterRep.getleavelistbyrec(RefId));

		}

		return "BTMILeaveMaster";
	}

	@RequestMapping(value = "onDuty", method = { RequestMethod.GET, RequestMethod.POST })
	public String onDuty(@RequestParam(required = false) String formmode, @RequestParam(required = false) String resId,
			@RequestParam(required = false) String RefId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		String userid = (String) req.getSession().getAttribute("USERID");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("onDutyList", onDutyRep.getOdListbyid(userid));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("onDutyList", onDutyRep.getOdListbyrecord(resId));
			md.addAttribute("onDutyListCount", bTMAdminOndutyCountRep.getondutybyref(RefId));

		}

		return "BTMOdApply";
	}

	@RequestMapping(value = "listOfTravel", method = { RequestMethod.GET, RequestMethod.POST })
	public String listOfTravel(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		String userid = (String) req.getSession().getAttribute("USERID");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("TravelList", btmTravelMasterRep.getTravelListbyid(userid));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("adminTravelMaster", adminOperServices.getTravelMaster(resId));
		}

		return "BTMTravelList";
	}

	@RequestMapping(value = "expensesReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String expensesReport(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		String userid2 = (String) req.getSession().getAttribute("USERID");

		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("formmode", "list");
			md.addAttribute("ExpenseList", extenseMasterRep.getListByassId(userid2));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("expenseReport", adminOperServices.getReportManager(resId));

		}
		return "BTMExpensesReport";

	}

	@RequestMapping(value = "workAssignList", method = { RequestMethod.GET, RequestMethod.POST })
	public String workAssignList(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		String userid1 = (String) req.getSession().getAttribute("USERID");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("WorkAssignmentList", btmWorkAssignmentRep.getWorkAssignlist());
		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("WorkAssignment", btmWorkAssignmentRep.getWorkAssign(resId));

		}

		return "BTMInquiriesWorkAssignment";
	}

	@RequestMapping(value = "solutionDocument", method = { RequestMethod.GET, RequestMethod.POST })
	public String solutionDocument(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) String doc_id,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("solutionDocList", btmDocumentMasterRep.getDocumentlist1());
		} else if (formmode.equals("view")) {
			md.addAttribute("formmode", "view");
			md.addAttribute("solutionDocList", btmDocumentMasterRep.getDocument(doc_id));
		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");
			BigDecimal refno = btmDocumentMasterRep.findMaxDocRefNo();
			int refNoInt = refno.intValueExact();
			md.addAttribute("refno", refNoInt + 1);
		}
		return "BTMSolutionDocument";
	}

	@RequestMapping(value = "attendanceRegister", method = { RequestMethod.GET, RequestMethod.POST })
	public String attendanceRegister(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) BigDecimal serialNo, @RequestParam(required = false) String yearone,
			@RequestParam(required = false) String monthone, @RequestParam(required = false) String dayone, Model md,
			HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatdate = new SimpleDateFormat("dd/MM/yyyy");
		String str = formatdate.format(cal.getTime());
		Date dat1 = null;
		try {
			dat1 = formatdate.parse(str);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		// resourceMasterRepo.gettotaluser();
		int totalemployees = resourceMasterRepo.gettotalnum();
		int present = attendanceRegisterRep.getALLpresent(dat1);
		int absent = totalemployees - present;
		int onduty = 0;
		md.addAttribute("attendanceList", attendanceRegisterRep.getALL(dat1));
		md.addAttribute("numofEmployees", totalemployees);
		md.addAttribute("numofPresent", present);
		md.addAttribute("numofabsent", absent);
		md.addAttribute("numofonduty", onduty);
		md.addAttribute("allusers", resourceMasterRepo.gettotalusernew());
		SimpleDateFormat formatMonth = new SimpleDateFormat("MM");
		String month = formatMonth.format(cal.getTime());

		md.addAttribute("CurrentMonth", month);
		SimpleDateFormat formatyear = new SimpleDateFormat("yyyy");
		String year = formatyear.format(cal.getTime());
		LocalDate currentDate = LocalDate.now();

		int currentYear = currentDate.getYear();
		String currentMonth = String.format("%02d", currentDate.getMonthValue());
		String currentDay = String.format("%02d", currentDate.getDayOfMonth());
		md.addAttribute("Currentyear1", currentYear);
		md.addAttribute("Currentmonth1", currentMonth);
		md.addAttribute("Currentyday1", currentDay);
		System.out.println(currentYear + "" + currentMonth + "" + currentDay + "sms redefining ");
		md.addAttribute("Currentyear", year);
		md.addAttribute("sms", AttendanceRegisterGetRep.getsms(yearone, monthone, dayone));
		// List<AttendanceRegisterGet>
		// smsd=AttendanceRegisterGetRep.getsms(yearone,monthone,dayone);
		// System.out.println("hhhhhhhhhhhhhhhhhhhhhhh"+smsd.get(0));

		System.out.println("sms seendingoooooooooooooooooooooooooooooo"
				+ AttendanceRegisterGetRep.getsms(yearone, monthone, dayone));

		List<String> list = Branch_reps.find_branch_id();
		md.addAttribute("branchIds", list);

		return "BTMAttendanceRegister";
	}

	@RequestMapping(value = "attendanceAbsentRegister", method = { RequestMethod.GET, RequestMethod.POST })
	public String attendanceAbsentRegister(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) BigDecimal serialNo, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		return "BTMAttendanceAbsentList";
	}

	@RequestMapping(value = "timesheetMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String timesheetMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(required = false) String month, @RequestParam(required = false) BigDecimal year,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {

		String empId = (String) req.getSession().getAttribute("USERID");
		// AttendanceID
		// userid3 = (AttendanceID) req.getSession().getAttribute("USERID");
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");

			md.addAttribute("TimesheetList", btmEmpTimeSheetRep.getTimeSheetLast(empId));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("TimesheetList", btmEmpTimeSheetRep.getTimeSheetLast(resId));
		} else if (formmode.equals("view1")) {

			md.addAttribute("formmode", "view1");
			md.addAttribute("TimesheetList", btmEmpTimeSheetRep.getTimeSheetdataView(resId, month, year));
		}

		return "BTMTimesheetMaster";
	}

	@RequestMapping(value = "timesheetMain", method = { RequestMethod.GET, RequestMethod.POST })
	public String timesheetMain(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String emp_id, @RequestParam(required = false) BigDecimal year,
			@RequestParam(required = false) String month, Model md, HttpServletRequest req) throws ParseException {

		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");

			md.addAttribute("TimesheetList", btmEmpTimeSheetRep.getTimeSheetlist());

		} else if (formmode.equals("approve")) {

			md.addAttribute("formmode", "approve");
			md.addAttribute("timeSheetVerify", btmEmpTimeSheetRep.getTimeSheetModify(emp_id, year, month));
		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("TimesheetView", btmmTimeSheetRep.getTimeSheetList(emp_id));
		} else if (formmode.equals("view1")) {

			md.addAttribute("formmode", "view1");
			md.addAttribute("timeSheetVerify", btmmTimeSheetRep.getTimeSheetVerify(emp_id, year, month));
		}

		return "BTMTimeSheetMaintenance";
	}

	@RequestMapping(value = "listOfIssue", method = { RequestMethod.GET, RequestMethod.POST })
	public String listOfIssue(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("formmode", "list");

			md.addAttribute("IssueTracker", issueTrackerRep.findAll());

		} else if (formmode.equals("view1")) {
			md.addAttribute("formmode", "view1");
			md.addAttribute("allusers", resourceMasterRepo.gettotaluser());
			md.addAttribute("issueview", issueTrackerRep.getIssue(userid));

		}

		return "BTMIssueTracker";

	}

//   =================================== Reports Module =============================================

	@RequestMapping(value = "attendanceReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String attendanceReport(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		md.addAttribute("allusers", resourceMasterRepo.gettotaluser());
		md.addAttribute("role", resourceMasterRepo.getrole(userId));
		return "BTMAttendanceReport";
	}

	@RequestMapping(value = "employeeRegister", method = { RequestMethod.GET, RequestMethod.POST })
	public String employeeRegister(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		md.addAttribute("allusers", resourceMasterRepo.gettotaluser());
		return "BTMEmployeeRegister";
	}

	@RequestMapping(value = "projectMasterReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String projectMasterReport(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		md.addAttribute("projectMasterList", adminOperServices.getProjectMasterlist());
		md.addAttribute("allusers", resourceMasterRepo.gettotaluser());
		return "BTMProjectMasterReports";
	}

	@RequestMapping(value = "holidayList", method = { RequestMethod.GET, RequestMethod.POST })
	public String holidayList(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("allusers", resourceMasterRepo.gettotaluser());
		md.addAttribute("menu", "BTMHeaderMenu");
		return "BTMHolidayList";
	}

	@RequestMapping(value = "profileMasterReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String profileMasterReport(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		md.addAttribute("allusers", resourceMasterRepo.gettotaluser());

		return "BTMProfileMasterReport";
	}

	@RequestMapping(value = "leaveRegister", method = { RequestMethod.GET, RequestMethod.POST })
	public String leaveRegister(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		md.addAttribute("allusers", resourceMasterRepo.gettotaluser());
		return "BTMLeaveRegister";
	}

	@RequestMapping(value = "timesheetReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String timsheetReport(@RequestParam(required = false) String EmpId,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		md.addAttribute("profileManagers", btmAdminAssociateProfileRep.getEmployeedetail2());
		md.addAttribute("allusers", resourceMasterRepo.gettotaluser());

		return "BTMTimeSheetJasperReport";
	}

	@RequestMapping(value = "workAssignReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String workAssignReport(@RequestParam(required = false) String empId,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("profileManagers", btmWorkAssignmentRep.getWorkAssigndetail());
		return "BTMWorkAssignJasperReport";
	}

	// ============================================ Placement Menu
	// =====================================

	// ==================================BankMaster=========================================

	@RequestMapping(value = "bankMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String bankMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String bank_srl_no, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("banklist", placementServices.getBanklist());

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);

			md.addAttribute("banklist", bankMasterRep.getBanklist(bank_srl_no));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("banklist", bankMasterRep.getBanklist(bank_srl_no));

		} else if (formmode.equals("verify")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("banklist", bankMasterRep.getBanklist(bank_srl_no));

		} else if (formmode.equals("delete")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("banklist", bankMasterRep.getBanklist(bank_srl_no));

		} else {

			md.addAttribute("formmode", formmode);
			md.addAttribute("banklist", bankMasterRep.getBanklist(""));
		}
		return "BTMBankMaster";
	}

	@RequestMapping(value = "bankMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String bankMasterAdd(@RequestParam(required = false) String formmode, @ModelAttribute BankMaster bankMaster,
			Model md, HttpServletRequest rq) {
		String userid1 = (String) rq.getSession().getAttribute("USERID");
		String msg = placementServices.addBankuser(bankMaster, formmode, userid1);
		return msg;
	}

	@RequestMapping(value = "bankMasterModify", method = RequestMethod.POST)
	@ResponseBody
	public String bankMasterModify(@RequestParam(required = false) String bank_srl_no,
			@ModelAttribute BankMaster bankMaster, Model md, HttpServletRequest rq)
			throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException, IOException {
		String userid1 = (String) rq.getSession().getAttribute("USERID");

		String msg = placementServices.bankMasterModify(bankMaster, bank_srl_no, userid1);
		return msg;
	}

	@RequestMapping(value = "bankMasterVerify", method = RequestMethod.POST)
	@ResponseBody
	public String bankMasterVerify(@RequestParam(required = false) String bank_srl_no,
			@ModelAttribute BankMaster bankMaster, Model md, HttpServletRequest rq)
			throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException, IOException {

		String userid1 = (String) rq.getSession().getAttribute("USERID");
		String msg = placementServices.bankMasterVerify(bankMaster, bank_srl_no, userid1);
		return msg;
	}

	@RequestMapping(value = "bankMasterDelete", method = RequestMethod.POST)
	@ResponseBody
	public String bankMasterDelete(@RequestParam(required = false) String bank_srl_no,
			@ModelAttribute BankMaster bankMaster, Model md, HttpServletRequest rq)
			throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException, IOException {
		String userid1 = (String) rq.getSession().getAttribute("USERID");
		String msg = placementServices.bankMasterDelete(bankMaster, bank_srl_no, userid1);
		return msg;
	}

	// =================================clientMaster======================================

	@RequestMapping(value = "clientMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String clientMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String client_srl_no, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("clientlist", placementServices.getClientlist());

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("clientlist", clientMasterRep.getClientlist(client_srl_no));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("clientlist", clientMasterRep.getClientlist(client_srl_no));

		} else if (formmode.equals("verify")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("clientlist", clientMasterRep.getClientlist(client_srl_no));

		} else if (formmode.equals("delete")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("clientlist", clientMasterRep.getClientlist(client_srl_no));

		} else {

			md.addAttribute("formmode", formmode);
			md.addAttribute("clientlist", clientMasterRep.getClientlist(" "));
		}
		return "BTMClientMaster";
	}

	@RequestMapping(value = "clientMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String clientMasterAdd(@RequestParam(required = false) String formmode,
			@ModelAttribute ClientMaster clientMaster, Model md, HttpServletRequest rq) {
		String userid1 = (String) rq.getSession().getAttribute("USERID");
		String msg = placementServices.addClientUser(clientMaster, formmode, userid1);
		return msg;
	}

	@RequestMapping(value = "clientMasterModify", method = RequestMethod.POST)
	@ResponseBody
	public String clientMasterModify(@RequestParam(required = false) String client_srl_no,
			@ModelAttribute ClientMaster clientMaster, Model md, HttpServletRequest rq)
			throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException, IOException {

		String userid1 = (String) rq.getSession().getAttribute("USERID");

		String msg = placementServices.clientMasterModify(clientMaster, client_srl_no, userid1);
		return msg;
	}

	@RequestMapping(value = "clientMasterVerify", method = RequestMethod.POST)
	@ResponseBody
	public String clientMasterVerify(@RequestParam(required = false) String client_srl_no,
			@ModelAttribute ClientMaster clientMaster, Model md, HttpServletRequest rq)
			throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException, IOException {
		String userid1 = (String) rq.getSession().getAttribute("USERID");
		String msg = placementServices.clientMasterVerify(clientMaster, client_srl_no, userid1);
		return msg;
	}

	@RequestMapping(value = "clientMasterDelete", method = RequestMethod.POST)
	@ResponseBody
	public String clientMasterDelete(@RequestParam(required = false) String client_srl_no,
			@ModelAttribute ClientMaster clientMaster, Model md, HttpServletRequest rq)
			throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException, IOException {

		String msg = placementServices.clientMasterDelete(clientMaster, client_srl_no);
		return msg;
	}

	// invoice

	@RequestMapping(value = "invoiceMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String invoiceMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String invoice_no, String inv_no, String po_id,
			@RequestParam(required = false) String EmpId, @RequestParam(required = false) String bank_name, Model md,
			HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("formmode", "list");

			Date currentDate = new Date();

			// Create a date format
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

			// Format the current date
			String formattedDate = dateFormat.format(currentDate);

			// Print the formatted date
			System.out.println("Current Date: " + formattedDate);
			md.addAttribute("formattedDate", formattedDate);
			// md.addAttribute("invoiceMasterList", placementServices.getplacementlist2());
			md.addAttribute("invoiceMasterList", invoiceMasterRep.getplacementlist());
			// md.addAttribute("invoicefromplacement",placementMaintenanceRep.getinvlist());
		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("banklist", bankMasterRep.getBanklist2());
			md.addAttribute("clients", clientMasterRep.getClientlist2());

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("invoiceMasterList", invoiceMasterRep.getplacementlist2(po_id));
			// md.addAttribute("invoiceMasterList",
			// placementMaintenanceRep.getinvoicelist(inv_no));
			// md.addAttribute("clients", clientMasterRep.getClientlist2());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("invoiceMasterList", invoiceMasterRep.getplacementlist2(po_id));

		} else {
			md.addAttribute("formmode", formmode);
		}

		return "BTMInvoiceMaster";
	}

	@RequestMapping(value = "invoiceMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String invoiceMasterAdd(@RequestParam(required = false) String formmode,
			@ModelAttribute InvoiceMaster invoiceMaster, Model md, HttpServletRequest rq) {
		String msg = placementServices.addInvoiceUser(invoiceMaster, formmode);
		return msg;
	}

	@RequestMapping(value = "invoiceMasterModify", method = RequestMethod.POST)
	@ResponseBody
	public String invoiceMasterModify(@RequestParam(required = false) String po_id,
			@ModelAttribute InvoiceMaster invoiceMaster, Model md, HttpServletRequest rq)
			throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException, IOException {
		System.out.println(po_id);
		String msg = placementServices.invoiceMasterModify(invoiceMaster, po_id);
		return msg;
	}

	@RequestMapping(value = "/invoicedelete", method = RequestMethod.POST)

	@ResponseBody
	public String invoicedelete(@RequestParam(required = false) String po_id, PlacementMaintenance placementMaintenance,
			InvoiceMaster invoiceMaster, Model md, HttpServletRequest rq) {

		PlacementMaintenance up = placementMaintenanceRep.getPoM(po_id);
		if (up == null) {
			return "Error: PlacementMaintenance not found for po_id " + po_id;
		}
		placementMaintenanceRep.delete(up);

		InvoiceMaster up1 = invoiceMasterRep.getplacementlist2(po_id);
		if (up1 == null) {
			return "Error: InvoiceMaster not found for po_id " + po_id;
		}
		invoiceMasterRep.delete(up1);

		return "Deleted successfully";

	}

	// ==================================================================================================

	@RequestMapping(value = "resourceMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String resourceMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String EmpId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("placementProfileList", placementServices.getUsersList());

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("placementProfile", placementServices.getUser(EmpId));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("placementProfile", placementServices.getUser(EmpId));

		} else {

			md.addAttribute("formmode", formmode);
			md.addAttribute("placementProfile", placementServices.getUser(""));
		}
		return "BTMResourceMaster";
	}

	@RequestMapping(value = "resourceMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String resourceMasterAdd(@RequestParam("formmode") String formmode,
			@ModelAttribute PlacementResourceMaster placementResourceMaster, Model md, HttpServletRequest rq) {
		String msg = placementServices.addUser(placementResourceMaster, formmode);
		return msg;
	}

	@RequestMapping(value = "placementMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String placementMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String EmpId, @RequestParam(required = false) String Placement_id,
			@RequestParam(required = false) String EmpName, @RequestParam(required = false) String mobile_no,
			@RequestParam(required = false) String email, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");

		} else if (formmode.equals("list1")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("Placement_id", Placement_id);
			md.addAttribute("EmpName", EmpName);
			md.addAttribute("mobile_no", mobile_no);
			md.addAttribute("email", email);
			md.addAttribute("placementList", placementServices.getPlacementMasterlist(Placement_id, EmpName));

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("placementProfile", placementServices.getPlacementUser(EmpId));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("placementProfile", placementServices.getPlacementUser(EmpId));
		} else {
			md.addAttribute("formmode", formmode);
			md.addAttribute("placementProfile", placementServices.getPlacementUser(""));
		}

		return "BTMPlacementMaster";
	}

	@RequestMapping(value = "placementMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String placementMasterAdd(@RequestParam("formmode") String formmode,
			@ModelAttribute PlacementMaster placementMaster, Model md, HttpServletRequest rq) {

		String msg = placementServices.addPlacementUser(placementMaster, formmode);
		return msg;
	}

	@RequestMapping(value = "timesheetManagement", method = { RequestMethod.GET, RequestMethod.POST })
	public String timesheetManagement(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String EmpId, @RequestParam(required = false) String Placement_id,
			@RequestParam(required = false) String EmpName, @RequestParam(required = false) String mobile_no,
			@RequestParam(required = false) String email, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");

		} else if (formmode.equals("list1")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("Placement_id", Placement_id);
			md.addAttribute("EmpName", EmpName);
			md.addAttribute("mobile_no", mobile_no);
			md.addAttribute("email", email);
			md.addAttribute("timeSheetList", placementServices.getTimesheetManagementList(Placement_id, EmpName));

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("placementProfile", placementServices.getTimesheetUser(EmpId));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("placementProfile", placementServices.getTimesheetUser(EmpId));

		} else {

			md.addAttribute("formmode", formmode);
			md.addAttribute("placementProfile", placementServices.getTimesheetUser(""));
		}

		return "BTMTimesheetManagement";
	}

	@RequestMapping(value = "timesheetManagementAdd", method = RequestMethod.POST)
	@ResponseBody
	public String timesheetManagementAdd(@RequestParam("formmode") String formmode,
			@ModelAttribute TimesheetManagement timesheetManagement, Model md, HttpServletRequest rq) {

		String msg = placementServices.addTimesheetUser(timesheetManagement, formmode);
		return msg;
	}

	@RequestMapping(value = "professionalCharge", method = { RequestMethod.GET, RequestMethod.POST })
	public String professionalCharge(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) BigDecimal SerialNo, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("professionalChargeList", placementServices.getUsersList1());

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("professionalCharge", placementServices.getUser1(SerialNo));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("professionalCharge", placementServices.getUser1(SerialNo));

		} else {

			md.addAttribute("formmode", formmode);
		}

		return "BTMProfessionalCharge";
	}

	@RequestMapping(value = "professionalChargeAdd", method = RequestMethod.POST)

	@ResponseBody
	public String professionalChargeAdd(@RequestParam("formmode") String formmode,
			@ModelAttribute ProfessionalCharge professionalCharge, Model md, HttpServletRequest rq) {

		String msg = placementServices.addUser1(professionalCharge, formmode);
		return msg;
	}

	// ========================== Jasper download ========================/

	@RequestMapping(value = "invoiceReportDownload", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource invoiceReportDownload(HttpServletResponse response,

			@RequestParam(value = "inv_no", required = false) String inv_no,

			@RequestParam(value = "filetype", required = false) String filetype) throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {

			logger.info("Getting download File :" + inv_no + ", FileType :" + filetype + "");

			File repfile = placementServices.getFile(inv_no, filetype);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}

	@RequestMapping(value = "AttendanceReportDownload", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource AttendanceReportDownload(HttpServletResponse response,

			@RequestParam(value = "emp_id", required = false) String emp_id,
			@RequestParam(value = "cal_month", required = false) String cal_month,
			@RequestParam(value = "cal_year", required = false) String cal_year,

			@RequestParam(value = "report_type", required = false) String report_type)
			throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {

			logger.info("Getting download File :" + emp_id + ", FileType :" + report_type + "");

			File repfile = reportServices.getFileAttendance(emp_id, cal_month, cal_year, report_type);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}

	@RequestMapping(value = "AttendanceRegisterDailyReport", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource AttendanceRegisterDailyReport(HttpServletResponse response,

			@RequestParam(value = "login_time", required = false) Date login_time,
			@RequestParam(value = "report_type", required = false) String report_type)
			throws IOException, SQLException {

		response.setContentType("application/octet-stream");
		SimpleDateFormat formatday = new SimpleDateFormat("dd");
		SimpleDateFormat formatMonth = new SimpleDateFormat("MM");
		SimpleDateFormat formatyear = new SimpleDateFormat("yyyy");
		String cal_month = formatMonth.format(login_time);
		String cal_year = formatyear.format(login_time);
		String cal_date = formatday.format(login_time);
		InputStreamResource resource = null;
		try {

			logger.info("Getting download File :" + cal_month + "_" + cal_year + "_" + cal_date + ", FileType :"
					+ report_type + "");

			File repfile = reportServices.getFileDailyAttendance(cal_year, cal_month, cal_date, report_type);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());

			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}

	@RequestMapping(value = "AttendanceRegisterMonthReport", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource AttendanceRegisterMonthReport(HttpServletResponse response,
			@RequestParam(value = "cal_month", required = false) String cal_month,
			@RequestParam(value = "cal_year", required = false) String cal_year,

			@RequestParam(value = "report_type", required = false) String report_type)
			throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {

			logger.info("Getting download File :" + cal_month + ", FileType :" + report_type + "");

			File repfile = reportServices.getFileMonthyAttendance(cal_month, cal_year, report_type);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}

	@RequestMapping(value = "leaveRegisterReportDownload", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource leaveRegisterReportDownload(HttpServletResponse response,
			@RequestParam(value = "employee_id", required = false) String employee_id,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "leave_category", required = false) String leave_category,

			@RequestParam(value = "report_type", required = false) String report_type)
			throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {

			logger.info("Getting download File :" + employee_id + ", FileType :" + report_type + "");

			File repfile = reportServices.getFileLeaveRegister(employee_id, year, leave_category, report_type);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}

	@RequestMapping(value = "projectMasterReportDownload", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource projectMasterReportDownload(HttpServletResponse response,
			@RequestParam(value = "proj_id", required = false) String proj_id,

			@RequestParam(value = "report_type", required = false) String report_type)
			throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {

			logger.info("Getting download File :" + proj_id + ", FileType :" + report_type + "");

			File repfile = reportServices.getFileProject(proj_id, report_type);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}

	@RequestMapping(value = "holidayListReportDownload", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource holidayListReportDownload(HttpServletResponse response,
			@RequestParam(value = "cal_year", required = true) String cal_year,
			@RequestParam(value = "detailsRequired", required = false) String detailsRequired,

			@RequestParam(value = "report_type", required = false) String report_type)
			throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {

			logger.info("Getting download File :" + cal_year + ", FileType :" + report_type + "");
			if (detailsRequired.equals("No")) {
				File repfile = reportServices.getFileHolidayList(cal_year, report_type);

				response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
				resource = new InputStreamResource(new FileInputStream(repfile));
			} else if (detailsRequired.equals("Yes")) {
				File repfile = reportServices.getFileHolidayDetailsList(cal_year, report_type);

				response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
				resource = new InputStreamResource(new FileInputStream(repfile));
			}

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}

	@RequestMapping(value = "timesheetReportDownload", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource timesheetReportDownload(HttpServletResponse response,
			@RequestParam(value = "emp_id", required = true) String emp_id,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "month", required = false) String month,

			@RequestParam(value = "report_type", required = false) String report_type)
			throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {

			logger.info("Getting download File :" + emp_id + ", FileType :" + report_type + "");

			File repfile = reportServices.getFileTimeSheet(emp_id, year, month, report_type);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}

	@RequestMapping(value = "employeeRegisterReportDownload", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource employeeRegisterReportDownload(HttpServletResponse response,
			@RequestParam(value = "monthend", required = false) String monthend,
			@RequestParam(value = "detials", required = false) String detials,
			@RequestParam(value = "reportto", required = true) String reportto,

			@RequestParam(value = "report_type", required = false) String report_type)
			throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {

			logger.info("Getting download File :" + monthend + ", FileType :" + report_type + ",ReporttO :" + reportto);

			File repfile = reportServices.getemployeeRegister(monthend, detials, reportto, report_type);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}

	@RequestMapping(value = "workAssignReportDownload", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource workAssignReportDownload(HttpServletResponse response,
			@RequestParam(value = "emp_id", required = true) String emp_id,
			@RequestParam(value = "report_type", required = false) String report_type)
			throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {

			logger.info("Getting download File :" + emp_id + ", FileType :" + report_type + "");

			File repfile = reportServices.getFileWorkAssign(emp_id, report_type);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}

	@RequestMapping(value = "profileMasterReportDownload", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource profileMasterReportDownload(HttpServletResponse response,
			@RequestParam(value = "emp_id", required = true) String emp_id,
			@RequestParam(value = "profileType", required = false) String profileType,
			@RequestParam(value = "reportType", required = false) String report_type) throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {

			logger.info("Getting download File :" + emp_id + ", FileType :" + report_type + "");

			File repfile = reportServices.getFileProfileMaster(emp_id, profileType, report_type);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}

	// ========================================== Maintenance Module
	// ===========================================

	@RequestMapping(value = "MtAssociateProfile", method = { RequestMethod.GET, RequestMethod.POST })

	public String MtAssociateProfile(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("adminAssociateProfileList", btmAdminAssociateProfileRep.getAssociatelist());
			/*
			 * List<String> list = Branch_reps.find_branch_id();
			 * md.addAttribute("branchIds", list);
			 */
			List<String> OrgIds = TSK_OrganizationMasterReps.get_org();
			md.addAttribute("OrgIds", OrgIds);


		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("adminAssociateProfile", adminOperServices.getAssociteUser(resId));

		}
		return "BTMMAssociateProfile";
	}

	@RequestMapping(value = "MtAssociateProfileAdd", method = RequestMethod.POST)
	@ResponseBody
	public String MtAssociateProfileAdd(@RequestParam("formmode") String formmode,
			@RequestParam("userid") String userid, @ModelAttribute BTMAdminAssociateProfile btmAdminAssociateProfile,
			Model md, HttpServletRequest rq) {

		String msg = adminOperServices.modifyAssociate(btmAdminAssociateProfile, formmode, userid);
		return msg;
	}

	@RequestMapping(value = "MtProjectMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String MtProjectMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, @RequestParam(required = false) String resName, Model md,
			HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("projectMasterList", adminOperServices.getProjectMasterlist());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("MtprojectList", btmProjectMasterRep.getProjectShow(resId, resName));
			md.addAttribute("projectDetails", btmProjectDetailsRep.getProjectDetails(resId));
			md.addAttribute("projectTeamDetails", btmProjectTeamDetailsRep.getProjectTeamDetails(resId));

		}

		return "BTMMtProjectMaster";
	}

	@RequestMapping(value = "MtLeaveMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String MtLeaveMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) BigDecimal resId, @RequestParam(required = false) String RefId, Model md,
			HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("AdminLeaveList", leaveMasterRep.getLeavelist1());
			/*
			 * List<String> list = Branch_reps.find_branch_id();
			 * md.addAttribute("branchIds", list);
			 */
			
			List<String> OrgIds = TSK_OrganizationMasterReps.get_org();
			md.addAttribute("OrgIds", OrgIds);

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("MtLeaveMaster", onDutyServices.getLeaveDetail(resId));
			md.addAttribute("LeaveListCounter", leaveMasterCounterRep.getLeaveCounterlist(RefId));
			md.addAttribute("approvalstatus", onDutyServices.getstatus(RefId, userId));
			md.addAttribute("pendingstatus", onDutyServices.getstatuspending(RefId, userId));

		}

		return "BTMMLeaveMaster";
	}

	@RequestMapping(value = "MtleaveMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String MtleaveMasterAdd(@RequestParam("formmode") String formmode, @RequestParam("userid") String userid,
			@ModelAttribute LeaveMaster leaveMaster, Model md, HttpServletRequest rq) {
		String Id = (String) rq.getSession().getAttribute("USERID");
		String msg = adminOperServices.modifyLeave(leaveMaster, formmode, userid, Id);

		return msg;
	}

	@RequestMapping(value = "MtOnDuty", method = { RequestMethod.GET, RequestMethod.POST })
	public String MtOnDuty(@RequestParam(required = false) String formmode,

			@RequestParam(required = false) String resId, @RequestParam(required = false) String RefId, Model md,
			HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");

			md.addAttribute("AdminODList", onDutyRep.getODlist1());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");

			md.addAttribute("MtOnDuty", onDutyServices.getODDetail(resId));

			md.addAttribute("onDutyListCount", bTMAdminOndutyCountRep.getOndutyCounterlist(RefId));

		}

		return "BTMMtOnDuty";
	}

	@RequestMapping(value = "MtOdMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String MtOdMasterAdd(@RequestParam("formmode") String formmode, @RequestParam("userid") String userid,
			@ModelAttribute OnDuty onDuty, Model md, HttpServletRequest rq) throws ParseException {
		String Id = (String) rq.getSession().getAttribute("USERID");
		String msg = adminOperServices.modifyOd(onDuty, formmode, userid, Id);

		return msg;
	}

	@RequestMapping(value = "MtTimesheetMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String MtTimesheetMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		String empid = (String) req.getSession().getAttribute("USERID");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");

			md.addAttribute("WorkAssignmentList", btmWorkAssignmentRep.getWorkAssignlist());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("WorkAssignment", btmWorkAssignmentRep.getWorkAssign(resId));

		}

		return "BTMAdminTimesheetMaster";
	}

	@RequestMapping(value = "MtTravelMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String MtTravelMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("AdminTravelList", btmTravelMasterRep.getTravellist1());
			/*
			 * List<String> list = Branch_reps.find_branch_id();
			 * md.addAttribute("branchIds", list);
			 */
			
			List<String> OrgIds = TSK_OrganizationMasterReps.get_org();
			md.addAttribute("OrgIds", OrgIds);

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("MtTravelMaster", adminOperServices.getTravelMaster(resId));

		}

		return "BTMMtTravelMaster";
	}

	@RequestMapping(value = "MtTravelMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String MtTravelMasterAdd(@RequestParam("formmode") String formmode, @RequestParam("userid") String userid,
			@ModelAttribute BTMTravelMaster travelMaster, Model md, HttpServletRequest rq) throws ParseException {

		String msg = adminOperServices.modifyTravelMaster(travelMaster, formmode, userid);
		return msg;
	}

	@RequestMapping(value = "MtExpenseReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String MtExpenseReport(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, @RequestParam(required = false) String userid,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("report", btmAdminExpenseReportRep.getReportList1());
			/*
			 * List<String> list = Branch_reps.find_branch_id();
			 * md.addAttribute("branchIds", list);
			 */
			
			List<String> OrgIds = TSK_OrganizationMasterReps.get_org();
			md.addAttribute("OrgIds", OrgIds);

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("MtExpenseReport", adminOperServices.getReportManager(resId));

		}
		return "BTMMtExpenseReport";

	}

	@RequestMapping(value = "MtExpenseReportAdd", method = RequestMethod.POST)
	@ResponseBody
	public String MtExpenseReportAdd(@RequestParam("formmode") String formmode, @RequestParam("userid") String userid,
			@ModelAttribute ExpenseMaster expenses, Model md, HttpServletRequest rq) throws ParseException {

		String msg = adminOperServices.modifyExpense(expenses, formmode, userid);
		return msg;
	}

	@RequestMapping(value = "MtEventMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String MtEventMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, Model md, HttpServletRequest req) throws ParseException {

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("eventMasterList", btmEventMasterRep.getScreenlist());

		}

		return "BTMMtEventMaster";
	}

	@RequestMapping(value = "MtWorkAssignment", method = { RequestMethod.GET, RequestMethod.POST })
	public String MtWorkAssignment(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("view")) {

			md.addAttribute("formmode", "view");

		} else if (formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("WorkAssignmentList", btmWorkAssignmentRep.getWorkAssignlist());

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", "add");
			md.addAttribute("profileManagers", btmAdminAssociateProfileRep.getEmployeedetail2());

		} else if (formmode.equals("list1")) {

			md.addAttribute("formmode", "list1");
			md.addAttribute("WorkAssignmentList", btmWorkAssignmentRep.getWorkAssignlist());

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", "edit");
			md.addAttribute("WorkAssignmentList", btmWorkAssignmentRep.getWorkAssignlist());
			md.addAttribute("WorkAssignment", btmWorkAssignmentRep.getWorkAssign(resId));

		} else if (formmode.equals("view1")) {

			md.addAttribute("formmode", "view1");
			md.addAttribute("WorkAssignment", btmWorkAssignmentRep.getWorkAssign(resId));

		} else if (formmode.equals("approveList")) {

			md.addAttribute("formmode", "approveList");
			md.addAttribute("WorkAssignmentList", btmWorkAssignmentRep.getWorkAssignlist());
			md.addAttribute("WorkAssignment", btmWorkAssignmentRep.getWorkAssign(resId));

		} else if (formmode.equals("view2")) {

			md.addAttribute("formmode", "view2");
			md.addAttribute("WorkAssignment", btmWorkAssignmentRep.getWorkAssign(resId));

		}

		return "BTMMtWorkAssignment";
	}

	@RequestMapping(value = "MtWorkAssignmentAdd", method = RequestMethod.POST)
	@ResponseBody
	public String MtWorkAssignmentAdd(@RequestParam("formmode") String formmode, @RequestParam("userid") String userid,
			@RequestParam("emp_name") String emp_name, @ModelAttribute BTMWorkAssignment btmWorkAssignment, Model md,
			HttpServletRequest rq) throws ParseException {

		String msg = maintenanceOperServices.addWorkAssign(btmWorkAssignment, formmode, userid, emp_name);
		return msg;
	}

	@RequestMapping(value = "Pomaintenance", method = { RequestMethod.GET, RequestMethod.POST })
	public String Pomaintenance(@RequestParam(required = false) String formmode, String po_delivery_date,
			String po_month, @RequestParam(required = false) String po_no, @RequestParam(required = false) String po_id,
			Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");

		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		System.out.println(formmode);
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("polist", placementmaintenancerep.getMaintenance());

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("sview")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("fview")) {
			md.addAttribute("poidlist", placementmaintenancerep.getPoId(po_id));
			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("success")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("slist", placementmaintenancerep.getSdetail());

		} else if (formmode.equals("failure")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("flist", placementmaintenancerep.getFdetail());
		}

		else if (formmode.equals("upload")) {

			md.addAttribute("formmode", formmode);

		}

		else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
			System.out.println(po_id);
			PlacementMaintenance oo = placementmaintenancerep.getMaintenancelist(po_id);
			System.out.println(":::::::::" + oo.getPo_no());

			md.addAttribute("polist", placementmaintenancerep.getPoMaintenance(po_id));
			md.addAttribute("pmlist", oo);
			md.addAttribute("ilist", placementmaintenancerep.getPolist(oo.getPo_no()));

		}

		else if (formmode.equals("view")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("polist", placementmaintenancerep.getPoMaintenance(po_no));
		} else if (formmode.equals("Modifyg")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("po", placementmaintenancerep.getPoM(po_id));
			// md.addAttribute("polist", placementmaintenancerep.getPoMaintenance(po_id));
			// md.addAttribute("po",placementmaintenancerep.getPoM(po_no));
		} else if (formmode.equals("ModifyI")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("po", placementmaintenancerep.getPoM(po_id));
			// md.addAttribute("polist", placementmaintenancerep.getPoMaintenance(po_id));
			// md.addAttribute("po",placementmaintenancerep.getPoM(po_no));
		} else if (formmode.equals("modify")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("po", placementmaintenancerep.getPoM(po_id));
			md.addAttribute("polist", placementmaintenancerep.getPoMaintenance(po_no));
		} else if (formmode.equals("ModifyR")) {
			System.out.println(formmode);
			md.addAttribute("formmode", formmode);
			md.addAttribute("po", placementmaintenancerep.getPoM(po_id));
			// md.addAttribute("polist", placementmaintenancerep.getPoMaintenance(po_no));
		}

		return "Pomaintenance";
	}

	@RequestMapping(value = "PurchaseOrders", method = { RequestMethod.GET, RequestMethod.POST })
	public String PurchaseOrders(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String po_no, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("polist", placementmaintenancerep.getpodetails());

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);

		} else {
			md.addAttribute("formmode", formmode);
		}

		return "PO";
	}

	@RequestMapping(value = "Remittances", method = { RequestMethod.GET, RequestMethod.POST })
	public String Remittence(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String po_no, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("polist", placementmaintenancerep.getpodetails());

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);

		} else {
			md.addAttribute("formmode", formmode);
		}

		return "Remittance";
	}

	@RequestMapping(value = "SPRates", method = { RequestMethod.GET, RequestMethod.POST })
	public String SPRates(@RequestParam(required = false) String formmode, @RequestParam(required = false) String po_no,
			Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("polist", placementmaintenancerep.getpodetails());

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);

		} else {
			md.addAttribute("formmode", formmode);
		}

		return "SPRates";
	}

	@RequestMapping(value = "GRNDetails", method = { RequestMethod.GET, RequestMethod.POST })
	public String GRNDetails(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String po_no, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("polist", placementmaintenancerep.getpodetails());

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);

		} else {
			md.addAttribute("formmode", formmode);
		}

		return "Grn";
	}

	@RequestMapping(value = "InvoiceDetails", method = { RequestMethod.GET, RequestMethod.POST })
	public String InvoiceDetails(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String po_no, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("polist", placementmaintenancerep.getpodetails());
		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);

		} else {
			md.addAttribute("formmode", formmode);
		}

		return "InvDetails";
	}

	@RequestMapping(value = "Acknowledgement", method = { RequestMethod.GET, RequestMethod.POST })
	public String Acknowledgement(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String po_no, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("polist", placementmaintenancerep.getpodetails());
		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);

		} else {
			md.addAttribute("formmode", formmode);
		}

		return "InvStatus";
	}

	@RequestMapping(value = "SPInvoices", method = { RequestMethod.GET, RequestMethod.POST })
	public String SPInvoices(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String po_no, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("polist", placementmaintenancerep.getpodetails());
		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);

		} else {
			md.addAttribute("formmode", formmode);
		}

		return "Ivc";
	}

	@RequestMapping(value = "SPPayments", method = { RequestMethod.GET, RequestMethod.POST })
	public String SPPayments(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String po_no, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("polist", placementmaintenancerep.getpodetails());
		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);

		} else {
			md.addAttribute("formmode", formmode);
		}

		return "SPpay";
	}

	@RequestMapping(value = "PurchaseOrderDetails", method = { RequestMethod.GET, RequestMethod.POST })
	public String PurchaseOrderDetails(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String po_no, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("polist", placementmaintenancerep.getpodetails());
		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);

		} else {
			md.addAttribute("formmode", formmode);
		}

		return "PurchaseOrderDetails";
	}

	@RequestMapping(value = "CreatePMAdd", method = RequestMethod.POST)
	@ResponseBody
	public String CreatePMAdd(@RequestParam(required = false) String formmode,
			@ModelAttribute PlacementMaintenance placementmaintenance, Model md, HttpServletRequest rq) {

		System.out.println(formmode + placementmaintenance.getPo_no() + placementmaintenance.getPo_month()
				+ placementmaintenance.getPo_id());

		String msg = placementServices.CreatePMAdd(placementmaintenance, formmode);
		System.out.println(msg);
		return msg;
	}

	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@ResponseBody
	public String upload(@RequestParam(required = false) String formmode,
			@ModelAttribute PlacementMaintenance placementmaintenance, Model md, HttpServletRequest rq) {
		String msg = placementServices.upload(placementmaintenance, formmode);
		return msg;
	}

	@RequestMapping(value = "logoutsub", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String logoutsub(Model md, HttpServletRequest rq) throws ParseException {

		String userid1 = (String) rq.getSession().getAttribute("USERID");
		String msg = attendanceRegisterService.logoutsubmit(userid1);
		return "BTMStart.html";
	}

	@RequestMapping(value = "TimesheetVerify", method = RequestMethod.POST)
	@ResponseBody
	public String TimesheetVerify(@RequestParam(required = false) String emp_id,
			@RequestParam(required = false) BigDecimal year, @RequestParam(required = false) String month,
			@ModelAttribute BTMEmpTimeSheet time, Model md, HttpServletRequest rq)
			throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException, IOException {
		String userid = (String) rq.getSession().getAttribute("USERID");

		String msg = timeSheetPdf.timeSheetVerify(time, emp_id, year, month, userid);
		return msg;
	}

	// INQURIES ATTENDANCE REGISTER

	@RequestMapping(value = "attendanceRegisterInquries", method = { RequestMethod.GET, RequestMethod.POST })
	public String attendanceRegisterInquries(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) BigDecimal serialNo, Model md, HttpServletRequest req)
			throws ParseException {

		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatdate = new SimpleDateFormat("dd/MM/yyyy");
		String str = formatdate.format(cal.getTime());
		Date dat1 = null;
		try {
			dat1 = formatdate.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// resourceMasterRepo.gettotaluser();
		// int totalemployees = resourceMasterRepo.gettotalnum();
		// int present = attendanceRegisterRep.getALLpresent(dat1);
		// int absent = totalemployees-present;
		// int onduty = 0;
		md.addAttribute("attendanceList", attendanceRegisterRep.getALL(dat1));
		// md.addAttribute("numofEmployees",totalemployees);
		// md.addAttribute("numofPresent",present);
		// md.addAttribute("numofAbsent",absent);
		// md.addAttribute("numofOnduty",onduty);
		md.addAttribute("allusers", resourceMasterRepo.gettotaluser());
		SimpleDateFormat formatMonth = new SimpleDateFormat("MM");
		String month = formatMonth.format(cal.getTime());

		md.addAttribute("CurrentMonth", month);
		SimpleDateFormat formatyear = new SimpleDateFormat("yyyy");
		String year = formatyear.format(cal.getTime());
		md.addAttribute("Currentyear", year);

		return "BTMAttendanceRegisterInquires";
	}

	@RequestMapping(value = "BTMAdminAccessandRole", method = { RequestMethod.GET, RequestMethod.POST })

	public String BTMAdminAccessandRole(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String role_id, Model md, HttpServletRequest req,
			@RequestParam(value = "activeMenu", required = false) String activeMenu)
			throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException {

		String userId = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		md.addAttribute("activeMenu", activeMenu);

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("accessRolesList", accessRolesRep.getRolelist());

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("BTMAccessRole", new AccessRoles());

		} else if (formmode.equals("view")) {
			System.out.println("role id is " + role_id);
			md.addAttribute("formmode", formmode);

			AccessRoles accessRolesview = accessRolesRep.getUser(role_id);

			md.addAttribute("accessRolesview", accessRolesview);

			md.addAttribute("formmode", "view");

		} else if (formmode.equals("delete")) {

			md.addAttribute("formmode", formmode);

			AccessRoles accessRolesview = accessRolesRep.getUser(role_id);

			md.addAttribute("accessRolesview", accessRolesview);

			md.addAttribute("formmode", "delete");

		} else if (formmode.equals("modify")) {

			md.addAttribute("formmode", formmode);

			AccessRoles accessRolesview = accessRolesRep.getUser(role_id);

			md.addAttribute("accessRolesview", accessRolesview);

			md.addAttribute("BTMAccessRole", new AccessRoles());
			md.addAttribute("formmode", "modify");

		} else {

			md.addAttribute("formmode", formmode);

		}

		return "AccessRole";
	}

	@RequestMapping(value = "addAccessRole", method = RequestMethod.POST)
	@ResponseBody

	public String addAccessRole(@RequestParam(required = false) String formmode, Model md, HttpServletRequest rq,
			@RequestParam(value = "adminValue", required = false) String adminValue,
			@RequestParam(value = "masterValue", required = false) String masterValue,
			@RequestParam(value = "ordersValue", required = false) String ordersValue,
			@RequestParam(value = "productioncycleValue", required = false) String productioncycleValue,
			@RequestParam(value = "inventoryValue", required = false) String inventoryValue,
			@RequestParam(value = "hrmsValue", required = false) String hrmsValue,
			@RequestParam(value = "reportsValue", required = false) String reportsValue,
			@RequestParam(value = "tranValue", required = false) String tranValue,
			@RequestParam(value = "finalString", required = false) String finalString, @ModelAttribute AccessRoles ar) {
		System.out.println("came to nav controller access and role add");
		AccessRoles up = new AccessRoles();
		up.setRole_id(ar.getRole_id());
		up.setRole_desc(ar.getRole_desc());
		up.setPermissions(ar.getPermissions());
		up.setRemarks(ar.getRemarks());
		up.setWork_class(ar.getWork_class());
		up.setMenulist(finalString);
		up.setAdmin(adminValue);
		up.setMaster(masterValue);
		up.setOrders(ordersValue);
		up.setProduction_cycle(productioncycleValue);
		up.setInventory(inventoryValue);
		up.setHrms(hrmsValue);
		up.setReports(reportsValue);
		up.setTransaction_master(tranValue);
		;
		up.setDel_flg("N");
		accessRolesRep.save(up);
		String msg = "Role Added Successfully";
		return msg;

	}

	/*---deleteuser---*/

	@RequestMapping(value = "deleteAccessRole", method = RequestMethod.POST)
	@ResponseBody
	public String deleteAccessRole(@RequestParam(required = false) String role_id, HttpServletRequest rq) {

		String msg = "";
		System.out.println("deleting Role and Access...");

		// Fetch session attributes
		String sessionUserId = (String) rq.getSession().getAttribute("USERID");
		String sessionUserName = (String) rq.getSession().getAttribute("USERNAME");

		// Validate userId parameter

		// Fetch user entity from repository
		AccessRoles entity = accessRolesRep.getUser(role_id);
		entity.setDel_flg("Y");
		accessRolesRep.save(entity);
		msg = "Role Deleted successfully.";

		return msg;
	}

	@RequestMapping(value = "fsubmit", method = RequestMethod.POST)
	@ResponseBody
	public String fsubmit(@RequestParam(required = false) String po_id, Model md, HttpServletRequest rq,
			@ModelAttribute PlacementMaintenance placementMaintenance) {
		System.out.println("Hi" + placementMaintenance.getPo_id());
		System.out.println(placementMaintenance.getPo_no());

		PlacementMaintenance up = placementMaintenanceRep.getPoId(po_id);
		up.setPo_no(placementMaintenance.getPo_no());
		up.setPo_delivery_date(placementMaintenance.getPo_delivery_date());
		up.setEmp_id(placementMaintenance.getEmp_id());

		up.setMessage("SUCCESS");
		up.setFlag('Y');
		// System.out.println(up.getPo_no());
		placementMaintenanceRep.save(up);
		return "success";

	}

	@RequestMapping(value = "PO_Status", method = { RequestMethod.GET, RequestMethod.POST })
	public String PO_Status(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String salary_code, Model md, HttpServletRequest req)
			throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		/* md.addAttribute("menu", "paystructure"); */
		System.out.println("hi");
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("formmode", "list");
			md.addAttribute("postatus_notnull", placementMaintenanceRep.getponulldetails());
		}

		return "PO_Status";

	}

	@RequestMapping(value = "GRNStatus", method = { RequestMethod.GET, RequestMethod.POST })
	public String GRNStatus(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String name, Model md, HttpServletRequest req,
			@ModelAttribute PlacementMaintenance placementMaintenance)
			throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		/* md.addAttribute("menu", "paystructure"); */

		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("formmode", "list");
			md.addAttribute("grnstsnotnull", placementMaintenanceRep.grnstsnotnull());

		}

		return "GRNStatus";
	}

	@RequestMapping(value = "remainder", method = { RequestMethod.GET, RequestMethod.POST })
	public String remainder(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, Model md, HttpServletRequest req) throws ParseException {

		String userid1 = (String) req.getSession().getAttribute("USERID");
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("postatus_notnull", placementMaintenanceRep.getponulldetails());
		}

		return "Remainder";
	}

	@RequestMapping(value = "spinvoices", method = { RequestMethod.GET, RequestMethod.POST })
	public String spinvoices(@RequestParam(required = false) String formmode, @RequestParam(required = false) String sp,
			@RequestParam(required = false) String inv_due_date, @RequestParam(required = false) String inv_date,
			Model md, HttpServletRequest req) throws ParseException {
		String userid1 = (String) req.getSession().getAttribute("USERID");
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("postatus_notnull", placementMaintenanceRep.getponulldetails());
		} else if (formmode.equals("table")) {
			System.out.println(inv_due_date);
			System.out.println(inv_date);

			List<String> myList = new ArrayList<>();

			// Add elements to the list

			md.addAttribute("formmode", "table");
			md.addAttribute("fuc", sp);
			md.addAttribute("fuc1", inv_due_date);
			md.addAttribute("fuc2", inv_date);
			md.addAttribute("splist", placementMaintenanceRep.getsplist(sp, inv_due_date, inv_date));
		}

		return "SPINVOICES";
	}

	@RequestMapping(value = "spf", method = { RequestMethod.GET, RequestMethod.POST })
	public String spf(@RequestParam(required = false) String formmode, @RequestParam(required = false) String resName,
			@RequestParam(required = false) String a, @RequestParam(required = false) String Month, Model md,
			HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode == "table") {
			md.addAttribute("formmode", "table");
		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", "edit");
			md.addAttribute("editing", Spf_repo.findit(a));
		} else if (formmode.equals("list")) {
			if (Month != null || Month == "") {
				List<spf_entity> spfValues = Spf_repo.getall(Month);

				md.addAttribute("ghj", spfValues);
				md.addAttribute("formmode", "list");
				md.addAttribute("month", Month);
			} else {
				YearMonth currentYearMonth = YearMonth.now();

				// Format the current month and year as a string
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
				String formattedMonthYear = currentYearMonth.format(formatter);
				// String[] parts = formattedMonthYear.split(" ");
				// Print the current month and year
				System.out.println("Current Month and Year: " + formattedMonthYear);

				List<spf_entity> spfValues = Spf_repo.getall(formattedMonthYear);

				md.addAttribute("ghj", spfValues);
				md.addAttribute("formmode", "list");

			}

		}

		return "spf";
	}

	@RequestMapping(value = "SPFDownload", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource SPFDownload(HttpServletResponse response

	) throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {

			String filetype = "Excel";
			// logger.info("Getting download File :" + + ", FileType :Excel" + + "");

			File repfile = placementServices.getFile1(filetype);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}

	@RequestMapping(value = "ESIDownload", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource ESIDownload(HttpServletResponse response

	) throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {

			String filetype = "Excel";
			// logger.info("Getting download File :" + + ", FileType :Excel" + + "");

			File repfile = placementServices.getFileesi(filetype);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}

	@RequestMapping(value = "SPFDownload1", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource SPFDownload1(HttpServletResponse response, @RequestParam(required = false) String MONTH

	) throws IOException, SQLException {

		response.setContentType("application/octet-stream");
		System.out.println("===============" + MONTH);
		InputStreamResource resource = null;
		try {

			String filetype = "Excel";
			String Mon = MONTH;
			// logger.info("Getting download File :" + + ", FileType :Excel" + + "");

			File repfile = placementServices.getFile2(filetype, Mon);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}

	@RequestMapping(value = "SalaryDownload1", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource SalaryDownload1(HttpServletResponse response,
			@RequestParam(required = false) String MONTH

	) throws IOException, SQLException {

		response.setContentType("application/octet-stream");
		System.out.println("===============" + MONTH);
		InputStreamResource resource = null;
		try {

			String filetype = "Excel";
			String Mon = MONTH;
			// logger.info("Getting download File :" + + ", FileType :Excel" + + "");

			File repfile = placementServices.getSalaryFile(filetype, Mon);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}

	@RequestMapping(value = "ESIDownload1", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource ESIDownload1(HttpServletResponse response, @RequestParam(required = false) String MONTH

	) throws IOException, SQLException {

		response.setContentType("application/octet-stream");
		System.out.println("===============" + MONTH);
		InputStreamResource resource = null;
		try {

			String filetype = "Excel";
			String Mon = MONTH;
			// logger.info("Getting download File :" + + ", FileType :Excel" + + "");

			File repfile = placementServices.getFileESI2(filetype, Mon);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}

	@RequestMapping(value = "viewtospf", method = RequestMethod.POST)
	@ResponseBody
	public String viewtospf(@RequestParam(required = false) String formmode, Model md, HttpServletRequest rq,
			@RequestParam(required = false) String b) {
		String userId = (String) rq.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		System.out.println(b);
		int uniqueIdCounter = Integer.parseInt(b);
		try {
			// AccessRoles up = new AccessRoles();
			// List<BSPF_ENTITY> up1 = SpfRepo.getData(b);
			List<BSPF_ENTITY> up2 = SpfRepo.getData(b);
			List<spf_entity> up3 = new ArrayList<>();

			for (BSPF_ENTITY bspfEntity : up2) {

				if (bspfEntity.getSpf() == null || bspfEntity.getSpf().compareTo(BigDecimal.ZERO) == 0) {

					spf_entity spfEntity = new spf_entity();
					spfEntity.setEmp_no(bspfEntity.getEmp_no());
					spfEntity.setEmp_name(bspfEntity.getEmp_name());
					spfEntity.setEmp_desig(bspfEntity.getEmp_desig());
					spfEntity.setDate_of_birth(bspfEntity.getDate_of_birth());
					spfEntity.setDate_of_join(bspfEntity.getDate_of_join());
					spfEntity.setSpf_acct_no(bspfEntity.getSpf_acct_no());
					spfEntity.setUrn_no(bspfEntity.getUrn_no());
					spfEntity.setNo_of_days(bspfEntity.getNo_of_days());
					spfEntity.setDays_paid(bspfEntity.getDays_paid());
					spfEntity.setBank_name(bspfEntity.getBank_name());
					spfEntity.setBank_acct_no(bspfEntity.getBank_acct_no());
					spfEntity.setSalary_month(bspfEntity.getSalary_month());
					spfEntity.setEmp_cont_12("0.00");
					spfEntity.setEmp_cont_367("0.00");
					spfEntity.setEmp_cont_833("0.00");
					spfEntity.setTot_emp_cont("0.00");

					spfEntity.setHra(bspfEntity.getHra());
					spfEntity.setSpl_allow(bspfEntity.getSpl_allow());
					spfEntity.setMedi_reimb(bspfEntity.getMedi_reimb());
					spfEntity.setConv_allow(bspfEntity.getConv_allow());
					spfEntity.setLunch_allow(bspfEntity.getLunch_allow());
					spfEntity.setEdu_allow(bspfEntity.getEdu_allow());
					spfEntity.setBuss_attire(bspfEntity.getBuss_attire());
					spfEntity.setCar_maint(bspfEntity.getCar_maint());
					spfEntity.setLeave_travel_allow(bspfEntity.getLeave_travel_allow());
					spfEntity.setOutstn_allow(bspfEntity.getOutstn_allow());
					spfEntity.setAnnual_loyal_bonus(bspfEntity.getAnnual_loyal_bonus());
					spfEntity.setOtr_allow(bspfEntity.getOtr_allow());

					String grossSalary = bspfEntity.getGross_salary().toString();
					String formattedValue = formatLakh(Double.valueOf(grossSalary));
					spfEntity.setGross_salary(formattedValue);

					String BasicPay = bspfEntity.getBasic_pay().toString();
					String formattedValue1 = formatLakh(Double.valueOf(BasicPay));
					spfEntity.setBasic_pay(formattedValue1);
					// BigDecimal bigDecimalValue = new BigDecimal(a);
					// spfEntity.setGross_salary(bigDecimalValue);
					spfEntity.setSpf(bspfEntity.getSpf());
					spfEntity.setTds(bspfEntity.getTds());
					spfEntity.setProf_tax(bspfEntity.getProf_tax());
					spfEntity.setEsi(bspfEntity.getEsi());
					spfEntity.setRecovery(bspfEntity.getRecovery());
					spfEntity.setOtr_ded(bspfEntity.getOtr_ded());
					spfEntity.setTotal_deductions(bspfEntity.getTotal_deductions());
					spfEntity.setNet_salary(bspfEntity.getNet_salary());
					spfEntity.setDate_of_pay(bspfEntity.getDate_of_pay());
					spfEntity.setCum_tds_fy(bspfEntity.getCum_tds_fy());
					spfEntity.setCtc_amt(bspfEntity.getCtc_amt());
					spfEntity.setMobile_no(bspfEntity.getMobile_no());
					spfEntity.setEmail_id(bspfEntity.getEmail_id());
					spfEntity.setIfsc_code(bspfEntity.getIfsc_code());
					spfEntity.setRemarks(bspfEntity.getRemarks());
					spfEntity.setAdhar_no(bspfEntity.getAdhar_no());

					spfEntity.setUnique_id(bspfEntity.getSalary_month() + bspfEntity.getEmp_no());
					up3.add(spfEntity);
				}

				else {

					spf_entity spfEntity = new spf_entity();
					spfEntity.setEmp_no(bspfEntity.getEmp_no());
					spfEntity.setEmp_name(bspfEntity.getEmp_name());
					spfEntity.setEmp_desig(bspfEntity.getEmp_desig());
					spfEntity.setDate_of_birth(bspfEntity.getDate_of_birth());
					spfEntity.setDate_of_join(bspfEntity.getDate_of_join());
					spfEntity.setSpf_acct_no(bspfEntity.getSpf_acct_no());
					spfEntity.setUrn_no(bspfEntity.getUrn_no());
					spfEntity.setNo_of_days(bspfEntity.getNo_of_days());
					spfEntity.setDays_paid(bspfEntity.getDays_paid());
					spfEntity.setBank_name(bspfEntity.getBank_name());
					spfEntity.setBank_acct_no(bspfEntity.getBank_acct_no());
					spfEntity.setSalary_month(bspfEntity.getSalary_month());

					BigDecimal expectedValue = new BigDecimal("300000");
					if (bspfEntity.getCtc_amt().compareTo(expectedValue) >= 0) {
						BigDecimal basicPay = new BigDecimal("15000");
						spfEntity.setBasic_pay("15,000.00");

						// BigDecimal basicPay = bspfEntity.getBasic_pay();

						int intValue = basicPay.intValue();
						int emailId = Math.round(15000 * 8.33f / 100);
						String stringValue = Integer.toString(emailId);
						spfEntity.setEmp_cont_833(formatLakh(Double.valueOf(stringValue)));

						int ifsc = Math.round(15000 * 3.67f / 100);
						String stringValue1 = Integer.toString(ifsc);
						spfEntity.setEmp_cont_367(formatLakh(Double.valueOf(stringValue1)));

						int remarks = Math.round(15000 * 12.00f / 100);
						String stringValue2 = Integer.toString(remarks);
						spfEntity.setTot_emp_cont(formatLakh(Double.valueOf(stringValue2)));

					} else if (bspfEntity.getCtc_amt().compareTo(expectedValue) < 0) {
						BigDecimal basicPay = new BigDecimal("13985");
						spfEntity.setBasic_pay("13,985.00");

						int intValue = basicPay.intValue();

						int remarks = 1500;
						String stringValue2 = Integer.toString(remarks);
						spfEntity.setTot_emp_cont(formatLakh(Double.valueOf(stringValue2)));

						int emailId = Math.round(remarks * 8.33f / 12);
						String stringValue = Integer.toString(emailId);
						spfEntity.setEmp_cont_833(formatLakh(Double.valueOf(stringValue)));

						int ifsc = Math.round(remarks * 3.67f / 12);
						String stringValue1 = Integer.toString(ifsc);
						spfEntity.setEmp_cont_367(formatLakh(Double.valueOf(stringValue1)));

					} else {
						// Handle the case where bspfEntity.getCtc_amt() is equal to expectedValue
						// You can add code here if needed.
					}

					String grossSalary = bspfEntity.getGross_salary().toString();
					String formattedValue = formatLakh(Double.valueOf(grossSalary));
					spfEntity.setGross_salary(formattedValue);

					spfEntity.setHra(bspfEntity.getHra());
					spfEntity.setSpl_allow(bspfEntity.getSpl_allow());
					spfEntity.setMedi_reimb(bspfEntity.getMedi_reimb());
					spfEntity.setConv_allow(bspfEntity.getConv_allow());
					spfEntity.setLunch_allow(bspfEntity.getLunch_allow());
					spfEntity.setEdu_allow(bspfEntity.getEdu_allow());
					spfEntity.setBuss_attire(bspfEntity.getBuss_attire());
					spfEntity.setCar_maint(bspfEntity.getCar_maint());
					spfEntity.setLeave_travel_allow(bspfEntity.getLeave_travel_allow());
					spfEntity.setOutstn_allow(bspfEntity.getOutstn_allow());
					spfEntity.setAnnual_loyal_bonus(bspfEntity.getAnnual_loyal_bonus());
					spfEntity.setOtr_allow(bspfEntity.getOtr_allow());
					// spfEntity.setGross_salary(bspfEntity.getGross_salary());
					spfEntity.setSpf(bspfEntity.getSpf());
					spfEntity.setTds(bspfEntity.getTds());
					spfEntity.setProf_tax(bspfEntity.getProf_tax());
					spfEntity.setEsi(bspfEntity.getEsi());
					spfEntity.setRecovery(bspfEntity.getRecovery());
					spfEntity.setOtr_ded(bspfEntity.getOtr_ded());
					spfEntity.setTotal_deductions(bspfEntity.getTotal_deductions());
					spfEntity.setNet_salary(bspfEntity.getNet_salary());
					spfEntity.setDate_of_pay(bspfEntity.getDate_of_pay());
					spfEntity.setCum_tds_fy(bspfEntity.getCum_tds_fy());
					spfEntity.setCtc_amt(bspfEntity.getCtc_amt());
					spfEntity.setMobile_no(bspfEntity.getMobile_no());
					spfEntity.setEmail_id(bspfEntity.getEmail_id());
					spfEntity.setIfsc_code(bspfEntity.getIfsc_code());
					spfEntity.setRemarks(bspfEntity.getRemarks());
					spfEntity.setAdhar_no(bspfEntity.getAdhar_no());

					spfEntity.setUnique_id(bspfEntity.getSalary_month() + bspfEntity.getEmp_no());
					up3.add(spfEntity);
				}

			}
			System.out.println(up3);
			Spf_repo.saveAll(up3);
			String msg = "Data Saved Successfully"; // Changed the message
			return msg;
		} catch (Exception e) {
			e.printStackTrace();
			return "Error: " + e.getMessage();
		}
	}

	/*---SPF---*/

	@RequestMapping(value = "viewtospf1", method = RequestMethod.POST)
	@ResponseBody
	public String viewtospf1(@RequestParam(required = false) String formmode, Model md, HttpServletRequest rq,
			@RequestParam(required = false) String b) {
		String userId = (String) rq.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		System.out.println(b);
		int uniqueIdCounter = Integer.parseInt(b);
		try {
			// AccessRoles up = new AccessRoles();
			// List<BSPF_ENTITY> up1 = SpfRepo.getData(b);
			List<BSPF_ENTITY> up2 = SpfRepo.getData(b);
			List<spf_entity> up3 = new ArrayList<>();

			for (BSPF_ENTITY bspfEntity : up2) {

				if (bspfEntity.getSpf() == null || bspfEntity.getSpf().compareTo(BigDecimal.ZERO) == 0) {

					spf_entity spfEntity = new spf_entity();
					spfEntity.setEmp_no(bspfEntity.getEmp_no());
					spfEntity.setEmp_name(bspfEntity.getEmp_name());
					spfEntity.setEmp_desig(bspfEntity.getEmp_desig());
					spfEntity.setDate_of_birth(bspfEntity.getDate_of_birth());
					spfEntity.setDate_of_join(bspfEntity.getDate_of_join());
					spfEntity.setSpf_acct_no(bspfEntity.getSpf_acct_no());
					spfEntity.setUrn_no(bspfEntity.getUrn_no());
					spfEntity.setNo_of_days(bspfEntity.getNo_of_days());
					spfEntity.setDays_paid(bspfEntity.getDays_paid());
					spfEntity.setBank_name(bspfEntity.getBank_name());
					spfEntity.setBank_acct_no(bspfEntity.getBank_acct_no());
					spfEntity.setSalary_month(bspfEntity.getSalary_month());
					spfEntity.setEmp_cont_12("0");
					spfEntity.setEmp_cont_367("0");
					spfEntity.setEmp_cont_833("0");
					spfEntity.setTot_emp_cont("0");

					spfEntity.setHra(bspfEntity.getHra());
					spfEntity.setSpl_allow(bspfEntity.getSpl_allow());
					spfEntity.setMedi_reimb(bspfEntity.getMedi_reimb());
					spfEntity.setConv_allow(bspfEntity.getConv_allow());
					spfEntity.setLunch_allow(bspfEntity.getLunch_allow());
					spfEntity.setEdu_allow(bspfEntity.getEdu_allow());
					spfEntity.setBuss_attire(bspfEntity.getBuss_attire());
					spfEntity.setCar_maint(bspfEntity.getCar_maint());
					spfEntity.setLeave_travel_allow(bspfEntity.getLeave_travel_allow());
					spfEntity.setOutstn_allow(bspfEntity.getOutstn_allow());
					spfEntity.setAnnual_loyal_bonus(bspfEntity.getAnnual_loyal_bonus());
					spfEntity.setOtr_allow(bspfEntity.getOtr_allow());

					String grossSalary = bspfEntity.getGross_salary().toString();
					// String formattedValue = formatLakh(Double.valueOf(grossSalary));
					spfEntity.setGross_salary(grossSalary);

					String BasicPay = bspfEntity.getBasic_pay().toString();
					// String formattedValue1 = formatLakh(Double.valueOf(BasicPay));
					spfEntity.setBasic_pay(BasicPay);
					// BigDecimal bigDecimalValue = new BigDecimal(a);
					// spfEntity.setGross_salary(bigDecimalValue);
					spfEntity.setSpf(bspfEntity.getSpf());
					spfEntity.setTds(bspfEntity.getTds());
					spfEntity.setProf_tax(bspfEntity.getProf_tax());
					spfEntity.setEsi(bspfEntity.getEsi());
					spfEntity.setRecovery(bspfEntity.getRecovery());
					spfEntity.setOtr_ded(bspfEntity.getOtr_ded());
					spfEntity.setTotal_deductions(bspfEntity.getTotal_deductions());
					spfEntity.setNet_salary(bspfEntity.getNet_salary());
					spfEntity.setDate_of_pay(bspfEntity.getDate_of_pay());
					spfEntity.setCum_tds_fy(bspfEntity.getCum_tds_fy());
					spfEntity.setCtc_amt(bspfEntity.getCtc_amt());
					spfEntity.setMobile_no(bspfEntity.getMobile_no());
					spfEntity.setEmail_id(bspfEntity.getEmail_id());
					spfEntity.setIfsc_code(bspfEntity.getIfsc_code());
					spfEntity.setRemarks(bspfEntity.getRemarks());
					spfEntity.setAdhar_no(bspfEntity.getAdhar_no());
					if (bspfEntity.getBank_name().equals("ICICI")) {
						spfEntity.setTran_type("I");
					} else {
						spfEntity.setTran_type("N");
					}

					spfEntity.setUnique_id(bspfEntity.getSalary_month() + bspfEntity.getEmp_no());
					up3.add(spfEntity);
				} else if (bspfEntity.getSpf().equals(new BigDecimal("2400"))) {

					spf_entity spfEntity = new spf_entity();
					spfEntity.setEmp_no(bspfEntity.getEmp_no());
					spfEntity.setEmp_name(bspfEntity.getEmp_name());
					spfEntity.setEmp_desig(bspfEntity.getEmp_desig());
					spfEntity.setDate_of_birth(bspfEntity.getDate_of_birth());
					spfEntity.setDate_of_join(bspfEntity.getDate_of_join());
					spfEntity.setSpf_acct_no(bspfEntity.getSpf_acct_no());
					spfEntity.setUrn_no(bspfEntity.getUrn_no());
					spfEntity.setNo_of_days(bspfEntity.getNo_of_days());
					spfEntity.setDays_paid(bspfEntity.getDays_paid());
					spfEntity.setBank_name(bspfEntity.getBank_name());
					spfEntity.setBank_acct_no(bspfEntity.getBank_acct_no());
					spfEntity.setSalary_month(bspfEntity.getSalary_month());
					spfEntity.setEmp_cont_12("2400");
					spfEntity.setEmp_cont_367("734");
					spfEntity.setEmp_cont_833("1666");
					spfEntity.setTot_emp_cont("2400");

					spfEntity.setHra(bspfEntity.getHra());
					spfEntity.setSpl_allow(bspfEntity.getSpl_allow());
					spfEntity.setMedi_reimb(bspfEntity.getMedi_reimb());
					spfEntity.setConv_allow(bspfEntity.getConv_allow());
					spfEntity.setLunch_allow(bspfEntity.getLunch_allow());
					spfEntity.setEdu_allow(bspfEntity.getEdu_allow());
					spfEntity.setBuss_attire(bspfEntity.getBuss_attire());
					spfEntity.setCar_maint(bspfEntity.getCar_maint());
					spfEntity.setLeave_travel_allow(bspfEntity.getLeave_travel_allow());
					spfEntity.setOutstn_allow(bspfEntity.getOutstn_allow());
					spfEntity.setAnnual_loyal_bonus(bspfEntity.getAnnual_loyal_bonus());
					spfEntity.setOtr_allow(bspfEntity.getOtr_allow());

					String grossSalary = bspfEntity.getGross_salary().toString();
					// String formattedValue = formatLakh(Double.valueOf(grossSalary));
					spfEntity.setGross_salary(grossSalary);
					BigDecimal expectedValue = new BigDecimal("300000");
					if (bspfEntity.getCtc_amt().compareTo(expectedValue) >= 0) {
						BigDecimal basicPay = new BigDecimal("15000");
						spfEntity.setBasic_pay("15000");
					}
					// String BasicPay=bspfEntity.getBasic_pay().toString();
					// String formattedValue1 = formatLakh(Double.valueOf(BasicPay));
					// spfEntity.setBasic_pay(BasicPay);
					// BigDecimal bigDecimalValue = new BigDecimal(a);
					// spfEntity.setGross_salary(bigDecimalValue);
					spfEntity.setSpf(bspfEntity.getSpf());
					spfEntity.setTds(bspfEntity.getTds());
					spfEntity.setProf_tax(bspfEntity.getProf_tax());
					spfEntity.setEsi(bspfEntity.getEsi());
					spfEntity.setRecovery(bspfEntity.getRecovery());
					spfEntity.setOtr_ded(bspfEntity.getOtr_ded());
					spfEntity.setTotal_deductions(bspfEntity.getTotal_deductions());
					spfEntity.setNet_salary(bspfEntity.getNet_salary());
					spfEntity.setDate_of_pay(bspfEntity.getDate_of_pay());
					spfEntity.setCum_tds_fy(bspfEntity.getCum_tds_fy());
					spfEntity.setCtc_amt(bspfEntity.getCtc_amt());
					spfEntity.setMobile_no(bspfEntity.getMobile_no());
					spfEntity.setEmail_id(bspfEntity.getEmail_id());
					spfEntity.setIfsc_code(bspfEntity.getIfsc_code());
					spfEntity.setRemarks(bspfEntity.getRemarks());
					spfEntity.setAdhar_no(bspfEntity.getAdhar_no());
					if (bspfEntity.getBank_name().equals("ICICI")) {
						spfEntity.setTran_type("I");
					} else {
						spfEntity.setTran_type("N");
					}

					spfEntity.setUnique_id(bspfEntity.getSalary_month() + bspfEntity.getEmp_no());
					up3.add(spfEntity);
				} else {

					spf_entity spfEntity = new spf_entity();
					spfEntity.setEmp_no(bspfEntity.getEmp_no());
					spfEntity.setEmp_name(bspfEntity.getEmp_name());
					spfEntity.setEmp_desig(bspfEntity.getEmp_desig());
					spfEntity.setDate_of_birth(bspfEntity.getDate_of_birth());
					spfEntity.setDate_of_join(bspfEntity.getDate_of_join());
					spfEntity.setSpf_acct_no(bspfEntity.getSpf_acct_no());
					spfEntity.setUrn_no(bspfEntity.getUrn_no());
					spfEntity.setNo_of_days(bspfEntity.getNo_of_days());
					spfEntity.setDays_paid(bspfEntity.getDays_paid());
					spfEntity.setBank_name(bspfEntity.getBank_name());
					spfEntity.setBank_acct_no(bspfEntity.getBank_acct_no());
					spfEntity.setSalary_month(bspfEntity.getSalary_month());
					/*
					 * if(bspfEntity.getBank_name().equals("ICICI")) { spfEntity.setTran_type("I");
					 * }else { spfEntity.setTran_type("N"); }
					 */

					if (bspfEntity != null && bspfEntity.getBank_name() != null
							&& bspfEntity.getBank_name().equals("ICICI")) {
						spfEntity.setTran_type("I");
					} else {
						spfEntity.setTran_type("N");
					}

					BigDecimal expectedValue = new BigDecimal("300000");
					if (bspfEntity.getCtc_amt().compareTo(expectedValue) >= 0) {
						BigDecimal basicPay = new BigDecimal("15000");
						spfEntity.setBasic_pay("15000");

						// BigDecimal basicPay = bspfEntity.getBasic_pay();

						int intValue = basicPay.intValue();
						int emailId = Math.round(15000 * 8.33f / 100);
						String stringValue = Integer.toString(emailId);
						spfEntity.setEmp_cont_833(stringValue);
						// spfEntity.setEmp_cont_833(formatLakh(Double.valueOf(stringValue)));

						int ifsc = Math.round(15000 * 3.67f / 100);
						String stringValue1 = Integer.toString(ifsc);
						spfEntity.setEmp_cont_367(stringValue1);
						// spfEntity.setEmp_cont_367(formatLakh(Double.valueOf(stringValue1)));

						int remarks = Math.round(15000 * 12.00f / 100);
						String stringValue2 = Integer.toString(remarks);
						spfEntity.setTot_emp_cont(stringValue2);
						// spfEntity.setTot_emp_cont(formatLakh(Double.valueOf(stringValue2)));

					} else if (bspfEntity.getCtc_amt().compareTo(expectedValue) < 0) {
						BigDecimal basicPay = new BigDecimal("13985");
						spfEntity.setBasic_pay("13985");

						int intValue = basicPay.intValue();

						int remarks = 1500;
						String stringValue2 = Integer.toString(remarks);
						spfEntity.setTot_emp_cont(stringValue2);
						// spfEntity.setTot_emp_cont(formatLakh(Double.valueOf(stringValue2)));

						int emailId = Math.round(remarks * 8.33f / 12);
						String stringValue = Integer.toString(emailId);
						spfEntity.setEmp_cont_833(stringValue);
						// spfEntity.setEmp_cont_833(formatLakh(Double.valueOf(stringValue)));

						int ifsc = Math.round(remarks * 3.67f / 12);
						String stringValue1 = Integer.toString(ifsc);
						spfEntity.setEmp_cont_367(stringValue1);
						// spfEntity.setEmp_cont_367(formatLakh(Double.valueOf(stringValue1)));

					} else {
						// Handle the case where bspfEntity.getCtc_amt() is equal to expectedValue
						// You can add code here if needed.
					}

					String grossSalary = bspfEntity.getGross_salary().toString();
					// String formattedValue = formatLakh(Double.valueOf(grossSalary));
					spfEntity.setGross_salary(grossSalary);

					spfEntity.setHra(bspfEntity.getHra());
					spfEntity.setSpl_allow(bspfEntity.getSpl_allow());
					spfEntity.setMedi_reimb(bspfEntity.getMedi_reimb());
					spfEntity.setConv_allow(bspfEntity.getConv_allow());
					spfEntity.setLunch_allow(bspfEntity.getLunch_allow());
					spfEntity.setEdu_allow(bspfEntity.getEdu_allow());
					spfEntity.setBuss_attire(bspfEntity.getBuss_attire());
					spfEntity.setCar_maint(bspfEntity.getCar_maint());
					spfEntity.setLeave_travel_allow(bspfEntity.getLeave_travel_allow());
					spfEntity.setOutstn_allow(bspfEntity.getOutstn_allow());
					spfEntity.setAnnual_loyal_bonus(bspfEntity.getAnnual_loyal_bonus());
					spfEntity.setOtr_allow(bspfEntity.getOtr_allow());
					// spfEntity.setGross_salary(bspfEntity.getGross_salary());
					spfEntity.setSpf(bspfEntity.getSpf());
					spfEntity.setTds(bspfEntity.getTds());
					spfEntity.setProf_tax(bspfEntity.getProf_tax());
					spfEntity.setEsi(bspfEntity.getEsi());
					spfEntity.setRecovery(bspfEntity.getRecovery());
					spfEntity.setOtr_ded(bspfEntity.getOtr_ded());
					spfEntity.setTotal_deductions(bspfEntity.getTotal_deductions());
					spfEntity.setNet_salary(bspfEntity.getNet_salary());
					spfEntity.setDate_of_pay(bspfEntity.getDate_of_pay());
					spfEntity.setCum_tds_fy(bspfEntity.getCum_tds_fy());
					spfEntity.setCtc_amt(bspfEntity.getCtc_amt());
					spfEntity.setMobile_no(bspfEntity.getMobile_no());
					spfEntity.setEmail_id(bspfEntity.getEmail_id());
					spfEntity.setIfsc_code(bspfEntity.getIfsc_code());
					spfEntity.setRemarks(bspfEntity.getRemarks());
					spfEntity.setAdhar_no(bspfEntity.getAdhar_no());

					spfEntity.setUnique_id(bspfEntity.getSalary_month() + bspfEntity.getEmp_no());
					up3.add(spfEntity);
				}

			}
			System.out.println(up3);
			Spf_repo.saveAll(up3);
			String msg = "Data Saved Successfully"; // Changed the message
			return msg;
		} catch (Exception e) {
			e.printStackTrace();
			return "Error: " + e.getMessage();
		}
	}

	private static String formatLakh(double d) {
		String s = String.format(Locale.UK, "%1.2f", Math.abs(d));
		s = s.replaceAll("(.+)(...\\...)", "$1,$2");
		while (s.matches("\\d{3,},.+")) {
			s = s.replaceAll("(\\d+)(\\d{2},.+)", "$1,$2");
		}
		return d < 0 ? ("-" + s) : s;
	}

	@RequestMapping(value = "viewtogst1", method = RequestMethod.POST)
	@ResponseBody
	public String viewtogst1(@RequestParam(required = false) String formmode, Model md, HttpServletRequest rq,
			@RequestParam(required = false) String b, @RequestParam(required = false) String a) {

		String userId = (String) rq.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		System.out.println("}}}}}}}}}}}}}}}}}}}}}}}}" + a + b);

		try {
			List<BEXPI_entity> up2 = bexpiRepoa.getDataOverseas(b, a);
			List<Gstoverseas> up3 = new ArrayList<>();

			for (BEXPI_entity bEXPI_entity : up2) {
				Gstoverseas gstoverseas = new Gstoverseas();
				gstoverseas.setBank_account(bEXPI_entity.getBank_account());
				gstoverseas.setBank_charges(bEXPI_entity.getBank_charges());
				gstoverseas.setClient(bEXPI_entity.getClient());
				gstoverseas.setDel_flg(bEXPI_entity.getDel_flg());
				gstoverseas.setDescription(bEXPI_entity.getDescription());
				gstoverseas.setEmployee(bEXPI_entity.getEmployee());
				gstoverseas.setEnd_date(bEXPI_entity.getEnd_date());
				gstoverseas.setEntity_flg(bEXPI_entity.getEntity_flg());
				gstoverseas.setEntry_time(bEXPI_entity.getEntry_time());
				gstoverseas.setEntry_user(bEXPI_entity.getEntry_user());
				gstoverseas.setEx_fluc(bEXPI_entity.getEx_fluc());
				gstoverseas.setFbank_chg_fcy(bEXPI_entity.getFbank_chg_fcy());
				gstoverseas.setInv_amt_fcy(bEXPI_entity.getInv_amt_fcy());
				gstoverseas.setInv_amt_inr(bEXPI_entity.getInv_amt_inr());
				gstoverseas.setInv_date(bEXPI_entity.getInv_date());
				gstoverseas.setInv_no(bEXPI_entity.getInv_no());
				gstoverseas.setModify_flg(bEXPI_entity.getModify_flg());
				gstoverseas.setModify_time(bEXPI_entity.getModify_time());
				gstoverseas.setModify_user(bEXPI_entity.getModify_user());
				gstoverseas.setPart_tran_id(bEXPI_entity.getPart_tran_id());
				gstoverseas.setPart_tran_type(bEXPI_entity.getPart_tran_type());
				gstoverseas.setPayment_date(bEXPI_entity.getPayment_date());
				gstoverseas.setRate(bEXPI_entity.getRate());
				gstoverseas.setRemit_amt_fcy(bEXPI_entity.getRemit_amt_fcy());
				gstoverseas.setRemit_amt_inr(bEXPI_entity.getRemit_amt_inr());
				gstoverseas.setRemit_rate(bEXPI_entity.getRemit_rate());
				gstoverseas.setStart_date(bEXPI_entity.getStart_date());
				gstoverseas.setTran_crncy(bEXPI_entity.getTran_crncy());
				gstoverseas.setTran_date(bEXPI_entity.getTran_date());
				gstoverseas.setTran_id(bEXPI_entity.getTran_id());
				gstoverseas.setVerify_time(bEXPI_entity.getVerify_time());
				gstoverseas.setVerify_user(bEXPI_entity.getVerify_user());
				gstoverseas.setUniqueid(bEXPI_entity.getTran_id() + bEXPI_entity.getPart_tran_id());

				up3.add(gstoverseas);
			}

			System.out.println(up3);
			//
			gstoverseasRepo.saveAll(up3);

			// gstBtmRep.getInsert(b,a);

			System.out.println("}}}}}}}}}}}}}}}}}}}}}}}}" + a + b);
			// int uniqueIdCounter = Integer.parseInt(b);
			// int uniqueIdCounter1 = Integer.parseInt(a);

			try {
				List<gst> up4 = gstRep.getDatagst(b, a);
				List<GstBtmEntity> up5 = new ArrayList<>();

				for (gst gsts : up4) {
					GstBtmEntity gstBtmEntity = new GstBtmEntity();
					gstBtmEntity.setClient(gsts.getClient());
					gstBtmEntity.setClient_remark(gsts.getClient_remark());
					gstBtmEntity.setClient_type(gsts.getClient_type());
					gstBtmEntity.setEligible_amt(gsts.getEligible_amt());
					gstBtmEntity.setFin_year(gsts.getFin_year());
					gstBtmEntity.setGst_type(gsts.getGst_type());
					gstBtmEntity.setGstin(gsts.getGstin());
					gstBtmEntity.setInv_amt(gsts.getInv_amt());
					gstBtmEntity.setInv_cgst(gsts.getInv_cgst());
					gstBtmEntity.setInv_desc(gsts.getInv_desc());
					gstBtmEntity.setInv_igst(gsts.getInv_igst());
					gstBtmEntity.setInv_sgst(gsts.getInv_sgst());
					gstBtmEntity.setInv_tot_amt(gsts.getInv_tot_amt());
					gstBtmEntity.setInvoice_date(gsts.getInvoice_date());
					gstBtmEntity.setInvoice_no(gsts.getInvoice_no());
					gstBtmEntity.setPart_tran_id(gsts.getPart_tran_id());
					gstBtmEntity.setPart_tran_type(gsts.getPart_tran_type());
					gstBtmEntity.setPay_part_tran_id(gsts.getPay_part_tran_id());
					gstBtmEntity.setPay_part_tran_type(gsts.getPay_part_tran_type());
					gstBtmEntity.setPay_tran_date(gsts.getPay_tran_date());
					gstBtmEntity.setPayment_date(gsts.getPayment_date());
					gstBtmEntity.setRpay_tran_id(gsts.getRpay_tran_id());
					gstBtmEntity.setTotal_gst_amt(gsts.getTotal_gst_amt());
					gstBtmEntity.setTran_date(gsts.getTran_date());
					gstBtmEntity.setTran_id(gsts.getTran_id());
					gstBtmEntity.setUniqueid(gsts.getTran_id() + gsts.getPart_tran_id());

					up5.add(gstBtmEntity);
				}

				System.out.println(up5);
				gstBtmRep.saveAll(up5);

				// System.out.println(gstRep.getInsert(a,b));
				String msg = "Data Saved Successfully";
				return msg;

			} catch (Exception e) {
				e.printStackTrace();
				return "Error: " + e.getMessage();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "Error: " + e.getMessage();
		}
	}

	@RequestMapping(value = "esi", method = { RequestMethod.GET, RequestMethod.POST })
	public String esi(@RequestParam(required = false) String formmode, @RequestParam(required = false) String resName,
			@RequestParam(required = false) String a, @RequestParam(required = false) String Month, Model md,
			HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("list1")) {
			if (Month != null || Month == "") {
				List<spf_entity> spfValues = Spf_repo.getESI(Month);

				for (int i = 0; i < spfValues.size(); i++) {
					spf_entity entity = spfValues.get(i);
					// Do something with the entity, e.g., print its properties
					System.out.println("Bank Acct No: " + entity.getBank_acct_no());
					System.out.println("Bank Name: " + entity.getBank_name());
					System.out.println("Salary Month: " + entity.getSalary_month());

					String stringValue = entity.getGross_salary(); // Replace this with your desired string
					BigDecimal grosspay = new BigDecimal(stringValue);

					int intValue = grosspay.intValue();
					int emailId = Math.round(intValue * 3.25f / 100);
					String stringValue1 = Integer.toString(emailId);
					entity.setEmail_id(stringValue1);

					int ifsc = Math.round(intValue * 0.75f / 100);
					String stringValue2 = Integer.toString(ifsc);
					entity.setIfsc_code(stringValue2);

					int remarks = Math.round(intValue * 4.00f / 100);
					String stringValue3 = Integer.toString(remarks);
					entity.setRemarks(stringValue3);
					// spfValues.set(0, entity);
				}

				md.addAttribute("ghj", spfValues);
				md.addAttribute("formmode", "list1");
				md.addAttribute("month", Month);
			} else {
				YearMonth currentYearMonth = YearMonth.now();

				// Format the current month and year as a string
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
				String formattedMonthYear = currentYearMonth.format(formatter);
				// String[] parts = formattedMonthYear.split(" ");
				// Print the current month and year
				System.out.println("Current Month and Year: " + formattedMonthYear);

				List<spf_entity> spfValues = Spf_repo.getall(formattedMonthYear);

				for (int i = 0; i < spfValues.size(); i++) {
					spf_entity entity = spfValues.get(i);
					// Do something with the entity, e.g., print its properties
					System.out.println("Bank Acct No: " + entity.getBank_acct_no());
					System.out.println("Bank Name: " + entity.getBank_name());
					System.out.println("Salary Month: " + entity.getSalary_month());

					String stringValue = entity.getBasic_pay(); // Replace this with your desired string
					BigDecimal basicPay = new BigDecimal(stringValue);

					int intValue = basicPay.intValue();
					int emailId = Math.round(intValue * 8.33f / 100);
					String stringValue1 = Integer.toString(emailId);
					entity.setEmail_id(stringValue1);

					int ifsc = Math.round(intValue * 3.67f / 100);
					String stringValue11 = Integer.toString(ifsc);
					entity.setIfsc_code(stringValue11);

					int remarks = Math.round(intValue * 12.00f / 100);
					String stringValue2 = Integer.toString(remarks);
					entity.setRemarks(stringValue2);
					spfValues.set(0, entity);

				}

				md.addAttribute("ghj", spfValues);
				md.addAttribute("formmode", "list1");

			}

		} else if (formmode == null || formmode.equals("list")) {
			if (Month != null || Month == "") {
				List<spf_entity> spfValues = Spf_repo.getESI(Month);

				for (int i = 0; i < spfValues.size(); i++) {
					spf_entity entity = spfValues.get(i);
					// Do something with the entity, e.g., print its properties
					System.out.println("Bank Acct No: " + entity.getBank_acct_no());
					System.out.println("Bank Name: " + entity.getBank_name());
					System.out.println("Salary Month: " + entity.getSalary_month());

					String stringValue = entity.getGross_salary(); // Replace this with your desired string
					BigDecimal grosspay = new BigDecimal(stringValue);

					int intValue = grosspay.intValue();
					int emailId = Math.round(intValue * 3.25f / 100);
					String stringValue1 = Integer.toString(emailId);
					entity.setEmail_id(stringValue1);

					int ifsc = Math.round(intValue * 0.75f / 100);
					String stringValue2 = Integer.toString(ifsc);
					entity.setIfsc_code(stringValue2);

					int remarks = Math.round(intValue * 4.00f / 100);
					String stringValue3 = Integer.toString(remarks);
					entity.setRemarks(stringValue3);
					// spfValues.set(0, entity);
				}

				md.addAttribute("ghj", spfValues);
				md.addAttribute("formmode", "list");
				md.addAttribute("month", Month);
			} else {
				YearMonth currentYearMonth = YearMonth.now();

				// Format the current month and year as a string
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
				String formattedMonthYear = currentYearMonth.format(formatter);
				// String[] parts = formattedMonthYear.split(" ");
				// Print the current month and year
				System.out.println("Current Month and Year: " + formattedMonthYear);

				List<spf_entity> spfValues = Spf_repo.getall(formattedMonthYear);

				for (int i = 0; i < spfValues.size(); i++) {
					spf_entity entity = spfValues.get(i);
					// Do something with the entity, e.g., print its properties
					System.out.println("Bank Acct No: " + entity.getBank_acct_no());
					System.out.println("Bank Name: " + entity.getBank_name());
					System.out.println("Salary Month: " + entity.getSalary_month());

					String stringValue = entity.getBasic_pay(); // Replace this with your desired string
					BigDecimal basicPay = new BigDecimal(stringValue);

					int intValue = basicPay.intValue();
					int emailId = Math.round(intValue * 8.33f / 100);
					String stringValue1 = Integer.toString(emailId);
					entity.setEmail_id(stringValue1);

					int ifsc = Math.round(intValue * 3.67f / 100);
					String stringValue11 = Integer.toString(ifsc);
					entity.setIfsc_code(stringValue11);

					int remarks = Math.round(intValue * 12.00f / 100);
					String stringValue2 = Integer.toString(remarks);
					entity.setRemarks(stringValue2);
					spfValues.set(0, entity);

				}

				md.addAttribute("ghj", spfValues);
				md.addAttribute("formmode", "list");

			}

		}

		return "ESI";
	}

	@RequestMapping(value = "ackt", method = { RequestMethod.GET, RequestMethod.POST })
	public String ackt(@RequestParam(required = false) String formmode, @RequestParam(required = false) String resName,
			@RequestParam(required = false) String a, @RequestParam(required = false) String Month, Model md,
			HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode == "table") {
			md.addAttribute("formmode", "table");
		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", "edit");
			md.addAttribute("editing", Spf_repo.findit(a));
		} else if (formmode.equals("list")) {
			if (Month != null || Month == "") {
				List<spf_entity> spfValues = Spf_repo.getall(Month);

				md.addAttribute("ghj", spfValues);
				md.addAttribute("formmode", "list");
				md.addAttribute("month", Month);
			} else {
				YearMonth currentYearMonth = YearMonth.now();

				// Format the current month and year as a string
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
				String formattedMonthYear = currentYearMonth.format(formatter);
				// String[] parts = formattedMonthYear.split(" ");
				// Print the current month and year
				System.out.println("Current Month and Year: " + formattedMonthYear);

				List<spf_entity> spfValues = Spf_repo.getall(formattedMonthYear);

				md.addAttribute("ghj", spfValues);
				md.addAttribute("formmode", "list");

			}

		}

		return "AckTrigger";
	}

	@RequestMapping(value = "gst", method = { RequestMethod.GET, RequestMethod.POST })
	public String gst(@RequestParam(required = false) String formmode, @RequestParam(required = false) String raised,
			@RequestParam(required = false) String resName, @RequestParam(required = false) String tran_id,
			@RequestParam(required = false) String Month, @RequestParam(required = false) String Year, Model md,
			@RequestParam(required = false) String uniqueid, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		// System.out.println(raised);

		if (formmode == null || formmode == "table") {
			md.addAttribute("formmode", "table");

			// System.out.println("it is add part train id + train id for gst
			// india"+uniqueid);
		} else if (formmode.equals("table2")) {
			md.addAttribute("formmode", "table2");
		} else if (formmode.equals("vtb")) {
			md.addAttribute("formmode", "vtb");
		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "added");
		}

		else if (formmode.equals("add1")) {
			md.addAttribute("formmode", "add1");
		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", "edit");
			md.addAttribute("editing", gstBtmRep.findByTran(uniqueid));
		} else if (formmode.equals("edit1")) {

			md.addAttribute("formmode", "edit1");
			md.addAttribute("editingoverseas", gstoverseasRepo.findByTranoverseas(uniqueid));
		} else if (formmode.equals("list")) {
			YearMonth currentYearMonth = YearMonth.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
			DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy");
			String formattedMonth = currentYearMonth.format(formatter);
			String formattedYear = currentYearMonth.format(formatter1);
			System.out.println("Current Month and Year: " + formattedMonth + formattedYear);
			System.out.println(Month + Year);
			if (raised.equals("By Us - India")) {
				if (Month == null || Year == null) {
					List<GstBtmEntity> gstvalues = gstBtmRep.getByIndia(formattedMonth, formattedYear);
					md.addAttribute("ghj", gstvalues);

					md.addAttribute("raised", raised);
					md.addAttribute("monthy", formattedMonth);
					md.addAttribute("year", formattedYear);
					md.addAttribute("formmode", "list");

				} else if (Month != null || Year != null) {
					List<GstBtmEntity> gstvalues = gstBtmRep.getByIndia(Month, Year);
					md.addAttribute("ghj", gstvalues);
					md.addAttribute("raised", raised);
					md.addAttribute("monthy", Month);
					md.addAttribute("year", Year);
					md.addAttribute("formmode", "list");
				}
			} else if (raised.equals("On Us - India")) {
				if (Month == null || Year == null) {
					List<GstBtmEntity> gstvalues = gstBtmRep.getOnIndia(formattedMonth, formattedYear);
					md.addAttribute("ghj", gstvalues);
					md.addAttribute("raised", raised);
					md.addAttribute("monthy", formattedMonth);
					md.addAttribute("year", formattedYear);
					md.addAttribute("formmode", "list");
				} else {
					List<GstBtmEntity> gstvalues = gstBtmRep.getOnIndia(Month, Year);
					md.addAttribute("ghj", gstvalues);
					md.addAttribute("raised", raised);
					md.addAttribute("monthy", Month);
					md.addAttribute("year", Year);
					md.addAttribute("formmode", "list");
				}

			}
		} else if (formmode.equals("list1")) {
			YearMonth currentYearMonth1 = YearMonth.now();
			DateTimeFormatter formatter11 = DateTimeFormatter.ofPattern("MM");
			DateTimeFormatter formatter111 = DateTimeFormatter.ofPattern("yyyy");
			String formattedMonth1 = currentYearMonth1.format(formatter111);
			String formattedYear1 = currentYearMonth1.format(formatter111);
			System.out.println("Current Month and Year: " + formattedMonth1 + formattedYear1);
			System.out.println(Month + Year);
			if (raised.equals("By Us - Overseas")) {
				if (Month == null || Year == null) {
					// <GstBtmEntity> gstvalues =
					// gstBtmRep.getOnIndia(formattedMonth1,formattedYear1);

					List<Gstoverseas> gstvaluess = gstoverseasRepo.getBygstoversea(formattedMonth1, formattedYear1);
					md.addAttribute("ghj", gstvaluess);
					System.out.println("+++++" + gstvaluess);
					md.addAttribute("raised", raised);
					md.addAttribute("monthy", formattedMonth1);
					md.addAttribute("year", formattedYear1);
					md.addAttribute("formmode", "list1");
				} else {
					List<Gstoverseas> gstvaluess = gstoverseasRepo.getBygstoversea(Month, Year);
					System.out.println(gstvaluess);
					md.addAttribute("ghj", gstvaluess);
					md.addAttribute("raised", raised);
					md.addAttribute("monthy", Month);
					md.addAttribute("year", Year);
					md.addAttribute("formmode", "list1");
				}

			}
		}

		return "GST";
	}

	@RequestMapping(value = "bank_acct", method = { RequestMethod.GET, RequestMethod.POST })
	public String bank_acct(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String raised, @RequestParam(required = false) String resName,
			@RequestParam(required = false) String a, @RequestParam(required = false) String b,
			@RequestParam(required = false) String Month, @RequestParam(required = false) String Year, Model md,
			HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {
			if (Month == null || Month == "") {
				YearMonth currentYearMonth = YearMonth.now();

				// Format the current month and year as a string
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
				String formattedMonthYear = currentYearMonth.format(formatter);
				// String[] parts = formattedMonthYear.split(" ");
				// Print the current month and year
				System.out.println("Current Month and Year: " + formattedMonthYear);

				List<spf_entity> spfValues = Spf_repo.getall(formattedMonthYear);

				md.addAttribute("ghj", spfValues);
				md.addAttribute("formmode", "list");

			} else {
				List<spf_entity> spfValues = Spf_repo.getall(Month);
				md.addAttribute("ghj", spfValues);
				md.addAttribute("formmode", "list");
				md.addAttribute("month", Month);

			}
			// md.addAttribute("ghj", Spf_repo.getData(b));
			// md.addAttribute("formmode", "list");
		}

		else if (formmode.equals("table")) {
			md.addAttribute("formmode", "table");
		} else if (formmode.equals("edit")) {
			md.addAttribute("formmode", "edit");
			md.addAttribute("editing", Spf_repo.findit(a));
		} else {

		}
		return "BankAcct";
	}

	@RequestMapping(value = "viewtobtm", method = RequestMethod.POST)
	@ResponseBody
	public String viewtobtm(@RequestParam(required = false) String b, Model md, HttpServletRequest rq) {
		// System.out.println(b);
		bankDetailService.getviewtobtm(b);
		return "success";

	}

	@RequestMapping(value = "GstDownload1", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource GstDownload1(HttpServletResponse response, @RequestParam(required = false) String raised,
			@RequestParam(required = false) String month, @RequestParam(required = false) String year

	) throws IOException, SQLException {

		response.setContentType("application/octet-stream");
		System.out.println("===============" + month + year);
		InputStreamResource resource = null;
		try {

			String filetype = "Excel";
			String Month = month;
			String Year = year;
			String Raised = raised;
			// logger.info("Getting download File :" + + ", FileType :Excel" + + "");

			File repfile = placementServices.getGstFile(filetype, Month, Year, Raised);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}

	@RequestMapping(value = "GSTPROFILE", method = RequestMethod.POST)
	@ResponseBody
	public String barathvarson(Model md, HttpServletRequest rq, @ModelAttribute GstBtmEntity gstBtmEntity,
			String tran_id, String Gst_type) {

		System.out.println("The solid Id >>>>>>>>>>>>>>>>>>>>>>>>> " + gstBtmEntity.getTran_id());
		GstBtmEntity up = gstBtmRep.findByTran(gstBtmEntity.getTran_id());
		up.setInv_amt(gstBtmEntity.getInv_amt());
		up.setClient(gstBtmEntity.getClient());
		up.setGstin(gstBtmEntity.getGstin());
		up.setInvoice_no(gstBtmEntity.getInvoice_no());
		up.setInvoice_date(gstBtmEntity.getInvoice_date());
		up.setInv_desc(gstBtmEntity.getInv_desc());
		up.setInv_sgst(gstBtmEntity.getInv_sgst());
		up.setInv_cgst(gstBtmEntity.getInv_cgst());
		up.setInv_igst(gstBtmEntity.getInv_igst());
		up.setTotal_gst_amt(gstBtmEntity.getTotal_gst_amt());
		up.setInv_tot_amt(gstBtmEntity.getInv_tot_amt());
		up.setClient_remark(gstBtmEntity.getClient_remark());
		up.setGst_type(gstBtmEntity.getGst_type());

		System.out.println(gstBtmEntity.getGst_type());
		System.out.println(tran_id);
		gstBtmRep.save(up);
		// System.out.println("SalaryParameter");

		return "success";

	}

	@PostMapping("viewtogst")
	@ResponseBody
	public String viewtogst(@RequestParam(required = false) String formmode, Model md, HttpServletRequest rq,
			@RequestParam(required = false) String b) {
		String userId = (String) rq.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		System.out.println(b);
		System.out.println(b.substring(0, 4));
		System.out.println(b.substring(4, 6));
		int uniqueIdCounter = Integer.parseInt(b);
		try {
			// AccessRoles up = new AccessRoles();
			// List<BSPF_ENTITY> up1 = SpfRepo.getData(b);
			List<gst> up2 = gstRep.getData(b.substring(4, 6), b.substring(0, 4));
			List<spf_entity> up3 = new ArrayList<>();

			System.out.println(up2);
			Spf_repo.saveAll(up3);
			String msg = "Data Saved Successfully"; // Changed the message
			return msg;
		} catch (Exception e) {
			e.printStackTrace();
			return "Error: " + e.getMessage();
		}
	}

	@RequestMapping(value = "TDS", method = { RequestMethod.GET, RequestMethod.POST })
	public String TDS(@RequestParam(required = false) String formmode, @RequestParam(required = false) String moths,
			@RequestParam(required = false) String d, @RequestParam(required = false) String twoDigitYear,
			@RequestParam(required = false) String years, @RequestParam(required = false) String date_of_pay,
			@RequestParam(required = false) String uniqueids, @RequestParam(required = false) String Month,
			@RequestParam(required = false) String Year, Model md, tdsentity tdsentity, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		System.out.println(moths);
		System.out.println(d);
		System.out.println(years);
		if (formmode == null || formmode == "table") {
			md.addAttribute("formmode", "table");
		} else if (formmode.equals("view")) {
			md.addAttribute("formmode", "view");
		}

		else if (formmode.equals("list1")) {

			/*
			 * if(moths == null || years == null) { List<tdsentity> tdsvalues =
			 * tdsRepos.gettdswithdec(moths,years); md.addAttribute("tds",tdsvalues);
			 * 
			 * }else if(moths != null || years!= null){
			 */

			YearMonth currentYearMonth = YearMonth.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
			DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy");
			String formattedMonth = currentYearMonth.format(formatter);
			String formattedYear = currentYearMonth.format(formatter1);
			System.out.println("Current Month and Year: " + formattedMonth + formattedYear);
			System.out.println(moths + Year);
			if (moths == null || years == null) {
				List<tdsentity> tdsvalues = tdsRepos.gettdswithfirst(formattedMonth, formattedYear);
				md.addAttribute("tds", tdsvalues);
				md.addAttribute("formmode", "list1");
			} else if (moths != null || years != null) {
				System.out.println("==========================" + moths + "----------------" + years);
				List<tdsentity> tdsvalues = tdsRepos.gettdswithdecs(moths, years);
				md.addAttribute("tds", tdsvalues);
				System.out.println(tdsvalues);
				md.addAttribute("formmode", "list1");// hhhhhhhhhhhhhhhhhh

			}

		}

		else if (formmode.equals("table1")) {
			md.addAttribute("formmode", "table1");
		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");
		} else if (formmode.equals("add1")) {
			md.addAttribute("formmode", "add1");
		} else if (formmode.equals("edit")) {

			md.addAttribute("edittab1", tdsRepos.getlisttab1(uniqueids));
			md.addAttribute("formmode", "edit");
		} else if (formmode.equals("edit1")) {

			md.addAttribute("edittab2", tdsRepos.getlisttab2(uniqueids));
			md.addAttribute("formmode", "edit1");
		} else if (formmode.equals("delete")) {
			md.addAttribute("formmode", "delete");
		}

		return "TDS";
	}

	@RequestMapping(value = "editgstonus", method = RequestMethod.POST)
	@ResponseBody
	public String editgstonus(@ModelAttribute GstBtmEntity GstBtmEntity, String tran_id, String Gst_type,
			@RequestParam(required = false) String uniqueid, @RequestParam(required = false) String Ddt,
			@RequestParam(required = false) String dsr, @RequestParam(required = false) String rds,
			@RequestParam(required = false) String temp4, @RequestParam(required = false) String f)
			throws ParseException {
		String u = uniqueid;

		System.out.println(u);

		GstBtmEntity up = gstBtmRep.findByTran(u);
		System.out.println("hi this is uniqueid for editonusindia" + gstBtmRep.findByTran(u));
		System.out.println("hi this is btm");

		// SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss",
		// Locale.ENGLISH);

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
			up.setTran_id(GstBtmEntity.getTran_id());
			up.setPart_tran_type(GstBtmEntity.getPart_tran_type());
			up.setClient(GstBtmEntity.getClient());
			up.setGstin(GstBtmEntity.getGstin());
			up.setGst_type(GstBtmEntity.getGst_type());
			up.setInvoice_no(GstBtmEntity.getInvoice_no());
			up.setInv_desc(GstBtmEntity.getInv_desc());
			up.setInvoice_date(GstBtmEntity.getInvoice_date());
			up.setInv_amt(GstBtmEntity.getInv_amt());
			up.setInv_sgst(GstBtmEntity.getInv_sgst());
			up.setInv_cgst(GstBtmEntity.getInv_cgst());
			up.setInv_igst(GstBtmEntity.getInv_igst());
			up.setTotal_gst_amt(GstBtmEntity.getTotal_gst_amt());
			up.setInv_tot_amt(GstBtmEntity.getInv_tot_amt());
			up.setClient_remark(GstBtmEntity.getClient_remark());
			// up.setTran_date(dateFormat.parse(f));
			// up.setInvoice_date(dateFormat.parse(rds));
			up.setTran_date(GstBtmEntity.getTran_date());

			up.setUniqueid(GstBtmEntity.getUniqueid());

			gstBtmRep.save(up);
			System.out.println("hi this is gst edit for india" + GstBtmEntity.getGst_type());
			System.out.println("hi this is btm" + GstBtmEntity.getGstin());
			System.out.println("hi this is btm" + GstBtmEntity.getTran_date());

			// Save the 'up' object with the updated entry_time

		} catch (Exception e) {
			e.printStackTrace(); // Handle potential errors here, such as ParseException
		}

		return "edited Successfully";

	}

	@RequestMapping(value = "editoverseas", method = RequestMethod.POST)
	@ResponseBody
	public String editoverseas(@ModelAttribute Gstoverseas Gstoverseas, String tran_id,
			@RequestParam(required = false) String uniqueid) throws ParseException {
		String u1 = uniqueid;

		Gstoverseas up = gstoverseasRepo.findByTranoverseas(u1);
		System.out.println("hi this is btm");

		// SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss",
		// Locale.ENGLISH);

		try {
			up.setTran_id(Gstoverseas.getTran_id());
			up.setPart_tran_type(Gstoverseas.getPart_tran_type());
			up.setClient(Gstoverseas.getClient());

			up.setInvoice_no(Gstoverseas.getInvoice_no());
			up.setInv_no(Gstoverseas.getInv_no());
			up.setInv_date(Gstoverseas.getInv_date());
			up.setInv_amt_fcy(Gstoverseas.getInv_amt_fcy());
			up.setInv_amt_inr(Gstoverseas.getInv_amt_inr());
			up.setBank_account(Gstoverseas.getBank_account());
			up.setBank_account(Gstoverseas.getBank_account());
			up.setTran_date(Gstoverseas.getTran_date());
			// up.setUniqueid(Gstoverseas.getUniqueid());

			gstoverseasRepo.save(up);
			System.out.println("hi this is gst from overseas" + Gstoverseas.getTran_id());
			System.out.println("hi this is btm" + Gstoverseas.getInv_no());

			// Save the 'up' object with the updated entry_time

		} catch (Exception e) {
			e.printStackTrace(); // Handle potential errors here, such as ParseException
		}

		return "edited Successfully OVERSEAS";

	}

	/*
	 * @RequestMapping(value = "downloadpage", method = RequestMethod.GET)
	 * 
	 * @ResponseBody public InputStreamResource INRReportDownload
	 * (HttpServletResponse response,
	 * 
	 * @RequestParam(value = "filetype", required = false) String filetype) throws
	 * IOException, SQLException, JRException {
	 * 
	 * response.setContentType("application/octet-stream");
	 * 
	 * InputStreamResource resource = null; try { File repfile =
	 * placementServices.getECLFile(filetype);
	 * 
	 * response.setHeader("Content-Disposition", "attachment; filename=" +
	 * repfile.getName()); response.setContentType(
	 * "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); // Set
	 * the content type to Excel
	 * 
	 * try (InputStream inputStream = new FileInputStream(repfile); OutputStream
	 * outputStream = response.getOutputStream()) {
	 * 
	 * byte[] buffer = new byte[1024]; int bytesRead;
	 * 
	 * while ((bytesRead = inputStream.read(buffer)) != -1) {
	 * outputStream.write(buffer, 0, bytesRead); }
	 * 
	 * outputStream.flush(); } } catch (FileNotFoundException e) { // Handle file
	 * not found exception e.printStackTrace(); // Consider logging or handling the
	 * exception appropriately } catch (IOException e) { // Handle IO exception
	 * e.printStackTrace(); // Consider logging or handling the exception
	 * appropriately } catch (Exception e) { // Handle other exceptions
	 * e.printStackTrace(); // Consider logging or handling the exception
	 * appropriately }
	 * 
	 * return resource; }
	 */

	/*
	 * @RequestMapping(value = "INRReportDownload", method = RequestMethod.GET)
	 * 
	 * @ResponseBody public InputStreamResource INRReportDownload
	 * (HttpServletResponse response,
	 * 
	 * @RequestParam(value = "filetype", required = false) String
	 * filetype,@RequestParam(required = false) String month,@RequestParam(required
	 * = false) String year) throws IOException, SQLException, JRException {
	 * 
	 * response.setContentType("application/octet-stream");
	 * 
	 * InputStreamResource resource = null; try { File repfile =
	 * placementServices.getECLFile(filetype,month,year);
	 * 
	 * response.setHeader("Content-Disposition", "attachment; filename=" +
	 * repfile.getName()); response.setContentType(
	 * "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); // Set
	 * the content type to Excel
	 * 
	 * try (InputStream inputStream = new FileInputStream(repfile); OutputStream
	 * outputStream = response.getOutputStream()) {
	 * 
	 * byte[] buffer = new byte[1024]; int bytesRead;
	 * 
	 * while ((bytesRead = inputStream.read(buffer)) != -1) {
	 * outputStream.write(buffer, 0, bytesRead); }
	 * 
	 * outputStream.flush(); } } catch (FileNotFoundException e) { // Handle file
	 * not found exception e.printStackTrace(); // Consider logging or handling the
	 * exception appropriately } catch (IOException e) { // Handle IO exception
	 * e.printStackTrace(); // Consider logging or handling the exception
	 * appropriately } catch (Exception e) { // Handle other exceptions
	 * e.printStackTrace(); // Consider logging or handling the exception
	 * appropriately }
	 * 
	 * return resource; }
	 */

	@RequestMapping(value = "/INRReportDownload", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<InputStreamResource> INRReportDownload(HttpServletResponse response,
			@RequestParam(required = false) String filetype, @RequestParam(required = false) String month,
			@RequestParam(required = false) String year) throws IOException, SQLException, JRException {

		// Set the response content type
		response.setContentType("application/octet-stream");

		String YearL = null;
		if (month.equals("01")) {
			YearL = "Jan";
		} else if (month.equals("02")) {
			YearL = "Feb";
		} else if (month.equals("02")) {
			YearL = "Feb";
		} else if (month.equals("03")) {
			YearL = "Mar";
		} else if (month.equals("04")) {
			YearL = "Apr";
		} else if (month.equals("05")) {
			YearL = "May";
		} else if (month.equals("06")) {
			YearL = "Jun";
		} else if (month.equals("07")) {
			YearL = "Jul";
		} else if (month.equals("08")) {
			YearL = "Aug";
		} else if (month.equals("09")) {
			YearL = "Sep";
		} else if (month.equals("10")) {
			YearL = "Oct";
		} else if (month.equals("11")) {
			YearL = "Nov";
		} else if (month.equals("12")) {
			YearL = "Dec";
		}
		// Construct the file name
		String fileName = "GST_" + YearL + "-" + year + ".xlsx";

		// Get the file from the service
		File eclFile = placementServices.getECLFile(fileName, filetype, month, year);

		// Prepare the InputStreamResource
		InputStreamResource resource = new InputStreamResource(new FileInputStream(eclFile));

		// Prepare the response headers
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

		// Return the ResponseEntity with InputStreamResource and headers
		return ResponseEntity.ok().headers(headers).contentLength(eclFile.length())
				.contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
	}

	@RequestMapping(value = "adddatasindia", method = RequestMethod.POST)
	@ResponseBody
	public String adddatasindia(Model md, HttpServletRequest rq, @ModelAttribute GstBtmEntity GstBtmEntity,
			String tran_id, @RequestParam(required = false) String tran_date,
			@RequestParam(required = false) String invoice_date) {

		System.out.println("the rating AGENCY>>>> ");
		GstBtmEntity up = GstBtmEntity;
		up.setTran_date(GstBtmEntity.getTran_date());
		up.setInvoice_date(GstBtmEntity.getInvoice_date());

		up.setUniqueid(GstBtmEntity.getTran_id() + GstBtmEntity.getPart_tran_id());

		System.out.println("hi it is gst here your adding the record for india");
		System.out.println("hi it is gst here your adding the record " + GstBtmEntity.getTran_date());

		gstBtmRep.save(up);

		return "add successfullu";

	}

	@RequestMapping(value = "addoverseas", method = RequestMethod.POST)
	@ResponseBody
	public String addoverseas(Model md, HttpServletRequest rq, @ModelAttribute Gstoverseas Gstoverseas,
			String tran_id) {

		System.out.println("the rating AGENCY>>>> ");
		Gstoverseas up = Gstoverseas;
		up.setUniqueid(Gstoverseas.getTran_id() + Gstoverseas.getPart_tran_id());

		System.out.println("hi it is gst here your adding the record for overseas");

		gstoverseasRepo.save(up);

		return "";

	}

	@RequestMapping(value = "deleteoverseas", method = RequestMethod.POST)
	@ResponseBody
	public String deleteoverseas(Model md, HttpServletRequest rq, @ModelAttribute Gstoverseas Gstoverseas,
			String tran_id) {

		System.out.println("the rating AGENCY>>>> ");
		Gstoverseas up = Gstoverseas;
		System.out.println("hi it is gst here your adding the record " + Gstoverseas.getTran_date());

		System.out.println("hi it is gst here your adding the record for overseas");

		gstoverseasRepo.save(up);

		return "deleted successfully";

	}

	@RequestMapping(value = "deleteindia", method = RequestMethod.POST)
	@ResponseBody
	public String deleteindia(Model md, HttpServletRequest rq, @ModelAttribute GstBtmEntity GstBtmEntity,
			String tran_id, @RequestParam(required = false) String tran_date,
			@RequestParam(required = false) String invoice_date) {

		System.out.println("the rating AGENCY>>>> ");
		GstBtmEntity up = GstBtmEntity;

		System.out.println("hi it is gst here your adding the record for india");
		System.out.println("hi it is gst here your adding the record " + GstBtmEntity.getTran_date());

		gstBtmRep.save(up);

		return "deleted successfully";

	}

	@RequestMapping(value = "viewtotds", method = RequestMethod.POST)
	@ResponseBody
	public String viewtotds(@RequestParam(required = false) String formmode, Model md, HttpServletRequest rq,
			@RequestParam(required = false) String b, @RequestParam(required = false) String a) {

		String userId = (String) rq.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		System.out.println("}}}}}}}}}}}}}}}}}}}}}}}}" + a + b);

		try {
			List<btdsview> up2 = btdsviewRepos.getdatetdslist(b, a);
			List<tdsentity> up3 = new ArrayList<>();

			for (btdsview btdsviews : up2) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				tdsentity tdsentitys = new tdsentity();
				tdsentitys.setEmp_no(btdsviews.getEmp_no());
				tdsentitys.setEmp_name(btdsviews.getEmp_name());
				tdsentitys.setEmp_desig(btdsviews.getEmp_desig());
				tdsentitys.setEmp_group(btdsviews.getEmp_group());
				tdsentitys.setPan_no(btdsviews.getPan_no());
				tdsentitys.setDate_of_birth(btdsviews.getDate_of_birth());
				tdsentitys.setDate_of_joining(btdsviews.getDate_of_joining());
				tdsentitys.setRecord_date(btdsviews.getRecord_date());
				tdsentitys.setSalary_month(btdsviews.getSalary_month());
				tdsentitys.setBasic_pay(btdsviews.getBasic_pay());
				tdsentitys.setHra(btdsviews.getHra());
				tdsentitys.setSpl_allow(btdsviews.getSpl_allow());
				tdsentitys.setMedi_reimb(btdsviews.getMedi_reimb());
				tdsentitys.setConv_allow(btdsviews.getConv_allow());
				tdsentitys.setLunch_allow(btdsviews.getLunch_allow());
				tdsentitys.setEdu_allow(btdsviews.getEdu_allow());
				tdsentitys.setBuss_attire(btdsviews.getBuss_attire());
				tdsentitys.setCar_maint(btdsviews.getCar_maint());
				tdsentitys.setLeave_travel_allow(btdsviews.getLeave_travel_allow());
				tdsentitys.setOutstn_allow(btdsviews.getOutstn_allow());
				tdsentitys.setAnnual_loyal_bonus(btdsviews.getAnnual_loyal_bonus());
				tdsentitys.setOtr_allow(btdsviews.getOtr_allow());
				tdsentitys.setGross_salary(btdsviews.getGross_salary());
				tdsentitys.setSpf(btdsviews.getSpf());
				tdsentitys.setTds(btdsviews.getTds());
				tdsentitys.setProf_tax(btdsviews.getProf_tax());
				tdsentitys.setEsi(btdsviews.getEsi());
				tdsentitys.setRecovery(btdsviews.getRecovery());
				tdsentitys.setOtr_ded(btdsviews.getOtr_ded());
				tdsentitys.setTotal_deductions(btdsviews.getTotal_deductions());
				tdsentitys.setNet_salary(btdsviews.getNet_salary());
				tdsentitys.setDate_of_pay(btdsviews.getDate_of_pay());
				tdsentitys.setCum_tds_fy(btdsviews.getCum_tds_fy());
				tdsentitys.setProv_it(btdsviews.getProv_it());
				tdsentitys.setTax_due(btdsviews.getTax_due());
				tdsentitys.setTax_per_month(btdsviews.getTax_per_month());
				tdsentitys.setBank_name(btdsviews.getBank_name());
				tdsentitys.setBank_bsr_code(btdsviews.getBank_bsr_code());
				tdsentitys.setBank_chalan_no(btdsviews.getBank_chalan_no());
				tdsentitys.setChalan_amt(btdsviews.getChalan_amt());
				tdsentitys.setTds_remit_date(btdsviews.getTds_remit_date());
				tdsentitys.setTds_tran_ref(btdsviews.getTds_tran_ref());
				tdsentitys.setMobile_no(btdsviews.getMobile_no());
				tdsentitys.setEmail_id(btdsviews.getEmail_id());
				tdsentitys.setEntity_flg(btdsviews.getEntity_flg());
				tdsentitys.setDel_flg(btdsviews.getDel_flg());
				tdsentitys.setEntry_time(btdsviews.getEntry_time());
				tdsentitys.setEntry_user(btdsviews.getEntry_user());
				tdsentitys.setModify_flg(btdsviews.getModify_flg());
				tdsentitys.setModify_time(btdsviews.getModify_time());
				tdsentitys.setModify_user(btdsviews.getModify_user());
				tdsentitys.setVerify_time(btdsviews.getVerify_time());
				tdsentitys.setVerify_user(btdsviews.getVerify_user());
				tdsentitys.setRemarks(btdsviews.getRemarks());
				tdsentitys.setAadhar_no(btdsviews.getAadhar_no());
				tdsentitys.setRate_of_tds(btdsviews.getRate_of_tds());
				tdsentitys.setUniqueids(tdsentitys.getEmp_no() + dateFormat.format(tdsentitys.getDate_of_pay()));

				up3.add(tdsentitys);

				// up3.add(gstoverseas);
			}

			System.out.println(up3);
			//
			tdsRepos.saveAll(up3);

			// gstBtmRep.getInsert(b,a);

			System.out.println("}}}}}}}}}}}}}}}}}}}}}}}}" + a + b);
			// int uniqueIdCounter = Integer.parseInt(b);
			// int uniqueIdCounter1 = Integer.parseInt(a);

			// System.out.println(gstRep.getInsert(a,b));
			String msg = "Data Saved Successfully";
			return msg;

		} catch (Exception e) {
			e.printStackTrace();
			return "Error: " + e.getMessage();
		}
	}

	@RequestMapping(value = "submitaddtds", method = RequestMethod.POST)
	@ResponseBody
	public String submitaddtds(Model md, HttpServletRequest rq, @ModelAttribute tdsentity tdsentity, String emp_name) {

		System.out.println("the rating AGENCY>>>> ");
		tdsentity up = tdsentity;
		up.setUniqueids(tdsentity.getEmp_no() + tdsentity.getDate_of_pay());
		System.out.println(tdsentity.getEmp_no() + tdsentity.getDate_of_pay());

		System.out.println("hi it is gst here your adding the record for TDS");

		tdsRepos.save(up);

		return "added successfully Tds";

	}

	@RequestMapping(value = "submitaddtds2", method = RequestMethod.POST)
	@ResponseBody
	public String submitaddtds2(Model md, HttpServletRequest rq, @ModelAttribute tdsentity tdsentity, String tran_id) {

		System.out.println("the rating AGENCY>>>> ");
		tdsentity up = tdsentity;
		up.setUniqueids(tdsentity.getEmp_no() + tdsentity.getDate_of_pay());
		System.out.println(tdsentity.getEmp_no() + tdsentity.getDate_of_pay());

		System.out.println("hi it is gst here your adding the record for TDS");

		tdsRepos.save(up);

		return "";

	}

	@RequestMapping(value = "tdstab1edit", method = RequestMethod.POST)
	@ResponseBody
	public String tdstab1edit(@ModelAttribute tdsentity tdsentity, String tran_id,
			@RequestParam(required = false) String uniqueids) throws ParseException {
		String u1 = uniqueids;

		tdsentity up = tdsRepos.getlisttab1(u1);
		System.out.println("hi this is btm");

		// SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss",
		// Locale.ENGLISH);

		try {

			up.setEmp_name(tdsentity.getEmp_name());
			up.setPan_no(tdsentity.getPan_no());
			up.setDate_of_pay(tdsentity.getDate_of_pay());
			up.setNet_salary(tdsentity.getNet_salary());
			up.setRate_of_tds(tdsentity.getTds_tran_ref());
			up.setBank_bsr_code(tdsentity.getBank_bsr_code());
			up.setTds(tdsentity.getTds());
			up.setBank_chalan_no(tdsentity.getBank_chalan_no());
			up.setBank_name(tdsentity.getBank_name());
			up.setChalan_amt(tdsentity.getChalan_amt());
			up.setTds_remit_date(tdsentity.getTds_remit_date());
			up.setTds_tran_ref(tdsentity.getTds_tran_ref());

			// up.setUniqueid(Gstoverseas.getUniqueid());

			tdsRepos.save(up);
			System.out.println("hi this is gst from tds" + tdsentity.getEmp_name());
			System.out.println("hi this is btm" + tdsentity.getDate_of_pay());

			// Save the 'up' object with the updated entry_time

		} catch (Exception e) {
			e.printStackTrace(); // Handle potential errors here, such as ParseException
		}

		return "edited Successfully tdstable1";

	}

	@RequestMapping(value = "tdstab2edit", method = RequestMethod.POST)
	@ResponseBody
	public String tdstab2edit(@ModelAttribute tdsentity tdsentity, String tran_id,
			@RequestParam(required = false) String uniqueids) throws ParseException {
		String u1 = uniqueids;

		tdsentity up = tdsRepos.getlisttab1(u1);
		System.out.println("hi this is btm");

		// SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss",
		// Locale.ENGLISH);

		try {

			up.setEmp_name(tdsentity.getEmp_name());
			up.setPan_no(tdsentity.getPan_no());
			up.setDate_of_pay(tdsentity.getDate_of_pay());
			up.setNet_salary(tdsentity.getNet_salary());
			up.setRate_of_tds(tdsentity.getTds_tran_ref());
			up.setBank_bsr_code(tdsentity.getBank_bsr_code());
			up.setTds(tdsentity.getTds());
			up.setBank_chalan_no(tdsentity.getBank_chalan_no());
			up.setBank_name(tdsentity.getBank_name());
			up.setChalan_amt(tdsentity.getChalan_amt());
			up.setTds_remit_date(tdsentity.getTds_remit_date());
			up.setTds_tran_ref(tdsentity.getTds_tran_ref());

			// up.setUniqueid(Gstoverseas.getUniqueid());

			tdsRepos.save(up);
			System.out.println("hi this is gst from tds" + tdsentity.getEmp_name());
			System.out.println("hi this is btm" + tdsentity.getDate_of_pay());

			// Save the 'up' object with the updated entry_time

		} catch (Exception e) {
			e.printStackTrace(); // Handle potential errors here, such as ParseException
		}

		return "edited Successfully tdstable2";

	}

	@RequestMapping(value = "deletetds", method = RequestMethod.POST)
	@ResponseBody
	public String deletetds(Model md, HttpServletRequest rq, @ModelAttribute tdsentity tdsentity, String tran_id,
			@RequestParam(required = false) String uniqueid) {

		System.out.println(uniqueid);
		tdsentity up = tdsRepos.delete1(uniqueid);

		System.out.println("hi it is gst here your adding the record for india");
		System.out.println("hi it is gst here your adding the record " + tdsentity.getUniqueids());

		tdsRepos.delete(up);

		return "deleted successfully";

	}

	/*
	 * @RequestMapping(value = "/tdsexceldownload", method = RequestMethod.GET,
	 * produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	 * 
	 * @ResponseBody public ResponseEntity<InputStreamResource> tdsexceldownload(
	 * HttpServletResponse response,
	 * 
	 * @RequestParam(required = false) String filetype,
	 * 
	 * @RequestParam(required = false) String moths,
	 * 
	 * @RequestParam(required = false) String years) throws IOException,
	 * SQLException, JRException {
	 * 
	 * // Set the response content type
	 * response.setContentType("application/octet-stream");
	 * 
	 * String YearL=null; if(moths.equals("01")) { YearL="Jan"; }else
	 * if(moths.equals("02")) { YearL="Feb"; }else if(moths.equals("02")) {
	 * YearL="Feb"; }else if(moths.equals("03")) { YearL="Mar"; }else
	 * if(moths.equals("04")) { YearL="Apr"; }else if(moths.equals("05")) {
	 * YearL="May"; }else if(moths.equals("06")) { YearL="Jun"; }else
	 * if(moths.equals("07")) { YearL="Jul"; }else if(moths.equals("08")) {
	 * YearL="Aug"; }else if(moths.equals("09")) { YearL="Sep"; }else
	 * if(moths.equals("10")) { YearL="Oct"; }else if(moths.equals("11")) {
	 * YearL="Nov"; }else if(moths.equals("12")) { YearL="Dec"; } // Construct the
	 * file name String fileName = "GST_" + YearL + "-" + years + ".xlsx";
	 * 
	 * // Get the file from the service File eclFile =
	 * placementServices.gettdsexcel(fileName,filetype, moths, years);
	 * 
	 * // Prepare the InputStreamResource InputStreamResource resource = new
	 * InputStreamResource(new FileInputStream(eclFile));
	 * 
	 * // Prepare the response headers HttpHeaders headers = new HttpHeaders();
	 * headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" +
	 * fileName);
	 * 
	 * // Return the ResponseEntity with InputStreamResource and headers return
	 * ResponseEntity.ok() .headers(headers) .contentLength(eclFile.length())
	 * .contentType(MediaType.APPLICATION_OCTET_STREAM) .body(resource); }
	 */
	@RequestMapping(value = { "/tdsexceldownload" }, method = { RequestMethod.GET }, produces = {
			"application/octet-stream" })
	@ResponseBody
	public ResponseEntity<InputStreamResource> tdsexceldownload(HttpServletResponse response,
			@RequestParam(required = false) String filetype, @RequestParam(required = false) String moths,
			@RequestParam(required = false) String years) throws IOException, SQLException, JRException {
		response.setContentType("application/octet-stream");
		String YearL = null;
		if (moths.equals("01")) {
			YearL = "Jan";
		} else if (moths.equals("02")) {
			YearL = "Feb";
		} else if (moths.equals("02")) {
			YearL = "Feb";
		} else if (moths.equals("03")) {
			YearL = "Mar";
		} else if (moths.equals("04")) {
			YearL = "Apr";
		} else if (moths.equals("05")) {
			YearL = "May";
		} else if (moths.equals("06")) {
			YearL = "Jun";
		} else if (moths.equals("07")) {
			YearL = "Jul";
		} else if (moths.equals("08")) {
			YearL = "Aug";
		} else if (moths.equals("09")) {
			YearL = "Sep";
		} else if (moths.equals("10")) {
			YearL = "Oct";
		} else if (moths.equals("11")) {
			YearL = "Nov";
		} else if (moths.equals("12")) {
			YearL = "Dec";
		}

		String fileName = "TDS" + YearL + "-" + years + ".xlsx";
		File eclFile = this.placementServices.gettdsexcel(fileName, filetype, moths, years);
		InputStreamResource resource = new InputStreamResource(new FileInputStream(eclFile));
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=" + fileName);
		return ((BodyBuilder) ResponseEntity.ok().headers(headers)).contentLength(eclFile.length())
				.contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
	}

	@RequestMapping(value = "submitdelete", method = RequestMethod.POST)
	@ResponseBody
	public String submitaddtds(Model md, HttpServletRequest rq,
			@ModelAttribute BTMAdminAssociateProfile BTMAdminAssociateProfile,
			@RequestParam(required = false) String resId) {

		System.out.println("fghjkl" + BTMAdminAssociateProfile.getDel_flg());
		BTMAdminAssociateProfile up = btmAdminAssociateProfileRep.delete2(resId);
		System.out.println(resId);
		System.out.println(up);
		up.setDel_flg("Y");

		btmAdminAssociateProfileRep.save(up);

		return "deleted successfully";

	}

	/*
	 * @RequestMapping(value = "sendSmsss", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public String sendSms(Model md, HttpServletRequest rq) {
	 * System.out.println("hihihihihihihihihihihkihihhhhhhhhhhhhhhhhhhhhh");
	 * 
	 * String url = "https://api.smslane.com/api/v2/SendSMS"; String senderId =
	 * "BOFIRE"; // Verify sender ID with the service provider String message =
	 * "Hi Employee, Please Mark Attendance through BTM Application for the day. It is MANDATORY. \n Thanks and Regards, Bornfire Innovation Private Limited."
	 * ; String mobileNumber = "9384374949"; String templateId =
	 * "1707170806443753132"; // Verify template ID with the service provider String
	 * apiKey = "Bornfire2017"; // Verify API key with the service provider String
	 * clientId = "siddhaiyan@bornfire.in";
	 * 
	 * HttpHeaders headers = new HttpHeaders();
	 * headers.setContentType(MediaType.APPLICATION_JSON);
	 * 
	 * // Construct the request body String requestBody = String.format(
	 * "{\"SenderId\":\"%s\",\"Message\":\"%s\",\"MobileNumbers\":\"%s\",\"TemplateId\":\"%s\",\"ApiKey\":\"%s\",\"ClientId\":\"%s\"}",
	 * senderId, message, mobileNumber, templateId, apiKey, clientId);
	 * 
	 * HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
	 * 
	 * RestTemplate restTemplate = new RestTemplate(); String response =
	 * restTemplate.postForObject(url,"nnjnnmn"+request, String.class);
	 * 
	 * return response; }
	 */

	@RequestMapping(value = "/sendsms", method = RequestMethod.POST)
	@ResponseBody
	public String sendSms(@RequestParam(required = false) String SenderId,
			@RequestParam(required = false) String Message, @RequestParam(required = false) String MobileNumbers,
			@RequestParam(required = false) String TemplateId, @RequestParam(required = false) String ApiKey,
			@RequestParam(required = false) String ClientId, @RequestParam(required = false) String oneto) {
		// Build the URL with proper encoding
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.smslane.com/api/v2/SendSMS")
				.queryParam("SenderId", SenderId).queryParam("Message", Message)
				.queryParam("MobileNumbers", MobileNumbers).queryParam("TemplateId", TemplateId)
				.queryParam("ApiKey", ApiKey).queryParam("ClientId", ClientId);

		// Create a RestTemplate instance to make HTTP requests
		RestTemplate restTemplate = new RestTemplate();

		// Send the request and get the response
		String response = restTemplate.getForObject(builder.toUriString() + "hhhhhhhhhh", String.class);
		System.out.println(response);
		return response;
	}

	@RequestMapping(value = "valuesending", method = RequestMethod.POST)
	@ResponseBody
	public String valuesending(Model md, HttpServletRequest rq,
			@ModelAttribute BTMAdminAssociateProfile BTMAdminAssociateProfile,

			@RequestParam(required = false) List<String> encodedNumbers,
			@RequestParam(required = false) List<String> demonumber, @RequestParam(required = false) String l,
			@RequestParam(required = false) String v, @RequestParam(required = false) String s,
			@RequestParam(required = false) String k, @RequestParam(required = false) String i,
			@RequestParam(required = false) String t) {

		try {

			List<String> b = encodedNumbers;

			encodedNumbers.add("8610708934");
			encodedNumbers.add("9486540575");

			String encodedSender = URLEncoder.encode(l, "UTF-8");
			String encodedMessage = v;
			String encodedMobileNumbers = URLEncoder.encode(s, "UTF-8");
			String encodedTemplateId = URLEncoder.encode(k, "UTF-8");
			String encodedApiKey = URLEncoder.encode(i, "UTF-8");
			String encodedClientId = URLEncoder.encode(t, "UTF-8");

			for (String m : b) {
				try {
					String apiUrl = "https://api.smslane.com/api/v2/SendSMS";
					String constructedUrl = apiUrl + "?SenderId=" + encodedSender + "&Message=" + v + "&MobileNumbers="
							+ "91" + m + "&TemplateId=" + encodedTemplateId + "&ApiKey=" + encodedApiKey + "&ClientId="
							+ encodedClientId;

					URL url = new URL(constructedUrl);
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");

					// Reading the response
					BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					String inputLine;
					StringBuilder response = new StringBuilder();

					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					in.close();
					System.out.println(response.toString());
					connection.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return "Sent Successfully";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "Error constructing URL";
		}
	}

	@RequestMapping(value = "send", method = RequestMethod.POST)
	@ResponseBody
	public String sendSMS(@RequestParam String url) {
		System.out.println("hhhhvvvvvvvvvvvvvvvv" + url);

		// Assuming you're passing the constructed URL containing SMS details as a query
		// parameter 'url'
		// Here you can perform additional validation on the request or handle the SMS
		// sending logic

		// For simplicity, I'll just make a GET request to the provided URL
		String dates = url;
		// RestTemplate restTemplate = new RestTemplate();
		// String response = restTemplate.getForObject(apiUrl, String.class);

		// You can return the response received from the SMS sending service or any
		// other relevant message
		return dates;

	}

	@RequestMapping(value = "onetwo", method = RequestMethod.POST)
	@ResponseBody
	public List<String> onetwo(Model md, HttpServletRequest rq,
			@ModelAttribute BTMAdminAssociateProfile BTMAdminAssociateProfile, @RequestParam(required = false) String p,
			@RequestParam(required = false) String q, @RequestParam(required = false) String r,
			@RequestParam(required = false) String url) {
		System.out.println("KKKKK" + p);
		// md.addAttribute("sms", AttendanceRegisterGetRep.getsms(p, q, r));
		// List<String> smsList = AttendanceRegisterGetRep.getsms(p, q, r); // Assuming
		// getsms() returns a
		System.out.println(resourceMasterRepo.smssenmding(p, q, r));
		List<String> smsList = resourceMasterRepo.smssenmding(p, q, r);
		// List<String>
		md.addAttribute("smss", smsList);
		// System.out.println(AttendanceRegisterGetRep.getsms(p, q, r));

		System.out.println(resourceMasterRepo.smssenmding(p, q, r));
		System.out.println(smsList);

		return resourceMasterRepo.smssenmding(p, q, r);

	}

	@RequestMapping(value = "salarystructures", method = { RequestMethod.GET, RequestMethod.POST })
	public String salarystructures(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String emp_no,
			@RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date ctc_eff_date,
			@RequestParam(required = false) String emp_no1, String keyword, Model md, HttpServletRequest req)

	{
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("formmode", "list");
			md.addAttribute("salarypay", salary_Pay_Rep.getList());
		} else if (formmode.equals("add")) {
			System.out.println(emp_no1);
			md.addAttribute("salarypay", salary_Pay_Rep.getsalfromcvf(emp_no));

			md.addAttribute("formmode", "add");

			md.addAttribute("empty", "");
			md.addAttribute("resmasterlist", resourceMasterRepo.getalist());

			// md.addAttribute("empty", "");
			// md.addAttribute("resmasterlist",salary_Pay_Rep.getalist());

		} else if (formmode.equals("edit")) {
			md.addAttribute("formmode", "edit");
			md.addAttribute("salarypay", salary_Pay_Rep.getaedit1(emp_no, ctc_eff_date));
		} else if (formmode.equals("view")) {
			md.addAttribute("formmode", "view");
			md.addAttribute("salarypay", salary_Pay_Rep.getaedit1(emp_no, ctc_eff_date));
		} else if (formmode.equals("enquiry")) {
			md.addAttribute("formmode", "enquiry");
			md.addAttribute("salarypay", salary_Pay_Rep.getaedit1(emp_no, ctc_eff_date));
		} else if (formmode.equals("revision")) {
			md.addAttribute("formmode", "revision");
			md.addAttribute("salarypay", salary_Pay_Rep.getaedit1(emp_no, ctc_eff_date));
		}
		return "salarystructures";
	}

	@RequestMapping(value = "AddScreen", method = RequestMethod.POST)

	@ResponseBody
	public String AddScreen(Model md, HttpServletRequest rq,

			@ModelAttribute Salary_Pay_Entity salary_Pay_Entity) {
		System.out.println(salary_Pay_Entity + "gggggggggggggggggggggggggggg");
		System.out.println(salary_Pay_Entity.getCtc_amt() + "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");

		Salary_Pay_Entity up = salary_Pay_Entity;
		up.setDel_flg("N");
		up.setEntity_flg("Y");
		up.setModify_flg("N");
		salary_Pay_Rep.save(up);
		System.out.println(up + "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
		return "Saved Successfully";
	}

	@RequestMapping(value = "modifyscreen", method = RequestMethod.POST)

	@ResponseBody
	public String modifyscreens(@RequestParam(required = false) String emp_no,
			@RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date ctc_eff_date, Model md,
			HttpServletRequest rq, @ModelAttribute Salary_Pay_Entity salary_Pay_Entity) {
		String msg = "";
		Salary_Pay_Entity up = salary_Pay_Entity;
		if (Objects.nonNull(up)) {
			up = salary_Pay_Entity;
			up.setDel_flg("N");
			up.setEntity_flg("Y");
			up.setModify_flg("N");
			salary_Pay_Rep.save(up);
			msg = "Modify Successfully";
		} else {
			msg = "Data Not Found";
		}
		return msg;
	}

	@RequestMapping(value = "revisionscreen", method = RequestMethod.POST)

	@ResponseBody
	public String revisionscreen(@RequestParam(required = false) String emp_no, Model md, HttpServletRequest rq,
			@ModelAttribute Salary_Pay_Entity salary_Pay_Entity) {
		String msg = "";
		Salary_Pay_Entity up = salary_Pay_Entity;
		System.out.println("load...");
		if (Objects.nonNull(up)) {
			up.setDel_flg("N");
			up.setEntity_flg("Y");
			up.setModify_flg("N");
			salary_Pay_Rep.save(up);
			msg = "Revised Successfully";
		} else {
			msg = "Data Not Found";
		}
		return msg;
	}

	@RequestMapping(value = "deletescreen", method = RequestMethod.POST)
	@ResponseBody
	public String deletescreen(@RequestParam(required = false) String emp_no,
			@RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date ctc_eff_date) {
		String msg = null;
		try {
			Salary_Pay_Entity vv = salary_Pay_Rep.getaedit1(emp_no, ctc_eff_date);
			vv.setDel_flg("Y");
			salary_Pay_Rep.delete(vv);
			msg = "Deleted Successfully";
		} catch (Exception e) {
			msg = "Delete Unsuccessfull";
		}
		return msg;
	}

	@GetMapping("paystructures")
	public String paystructures(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String emp_no, @RequestParam(required = false) String salaryMonth,
			@RequestParam(required = false) String empname, String keyword, Model model, HttpServletRequest req) throws JsonProcessingException

	{
		String userId = (String) req.getSession().getAttribute("USERID");
		model.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		model.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("list")) {
			model.addAttribute("formmode", "list");
			model.addAttribute("salarypay", Paystructurerep.getpay());
		} else if (formmode.equals("add")) {
			model.addAttribute("formmode", "add");
			List<BTMAdminAssociateProfile> list = btmAdminAssociateProfileRep.getAssociateDeletelist();
			System.out.println("resource length" + list.size());
			model.addAttribute("resourcename", list);
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(list); // list = resourcename
			model.addAttribute("resourcelistjson", json);
			/*
			 * List<String> list1 = Branch_reps.find_branch_id();
			 * model.addAttribute("branchIds", list1);
			 */
			List<String> OrgIds = TSK_OrganizationMasterReps.get_org();
			model.addAttribute("OrgIds", OrgIds);
		} else if (formmode.equals("edit")) {
			model.addAttribute("formmode", "edit");
			// md.addAttribute("salarypay", Paystructurerep.getaedit(emp_no));
			model.addAttribute("salarypay", Paystructurerep.getpaystructureedit(emp_no, salaryMonth, empname));
			/*
			 * List<String> list1 = Branch_reps.find_branch_id();
			 * model.addAttribute("branchIds", list1);
			 */
			List<String> OrgIds = TSK_OrganizationMasterReps.get_org();
			model.addAttribute("OrgIds", OrgIds);
		} else if (formmode.equals("view")) {
			model.addAttribute("formmode", "view");
			model.addAttribute("salarypay", Paystructurerep.getaedit(emp_no));
		} else if (formmode.equals("verify")) {
			model.addAttribute("formmode", "verify");
			model.addAttribute("salarypay", Paystructurerep.getpaystructureedit(emp_no, salaryMonth, empname));
		} else if (formmode.equals("enquiry")) {
			model.addAttribute("formmode", "enquiry");
			model.addAttribute("salarypay", Paystructurerep.getpaystructureedit(emp_no, salaryMonth, empname));
		} else if (formmode.equals("delete")) {
			model.addAttribute("formmode", "enquiry");
			model.addAttribute("delete_but", "delete_but");
			model.addAttribute("salarypay", Paystructurerep.getpaystructureedit(emp_no, salaryMonth, empname));
		}
		return "paystructure";
	}

	/*--EMPLOYEE SALARY SLIP--*/

	@RequestMapping(value = "employeesalaryslip", method = { RequestMethod.GET, RequestMethod.POST })
	public String employeesalaryslip(@RequestParam(required = false) String formmode, Model md,
			@RequestParam(required = false) String empId, @RequestParam(required = false) String month,
			@RequestParam(required = false) String empName,
			@RequestParam(required = false) String year) {
		if (formmode == null || formmode.equals("employeesalarylist")) {
			md.addAttribute("formmode", "employeesalarylist");
			List<EmployeeSalary> lists = EmployeeSalaryRep.getlist();
			System.out.println("the employee count" + lists.size());
			md.addAttribute("Employeelist", lists);
		}
		else if(formmode.equals("Employeemonthlysalaryslip")) {
			md.addAttribute("formmode", "Employeemonthlysalaryslip");
			paystructureentity paystructure=Paystructurerep.getid(empId);
			md.addAttribute("EmployeeSlip",paystructure);
			EmployeeSalary employeesalary = EmployeeSalaryRep.getindividualdata(empId,month,year);
			md.addAttribute("employeesalarydetails",employeesalary);
			
		}
		return "EMPLOYEESALARYSLIP";
	}

	@RequestMapping(value = "getdetailsformonthbased", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<EmployeeSalary> getdetails(@RequestParam(required = false) String month,
			@RequestParam(required = false) String year) {
		System.out.println("the month" + month);
		System.out.println("the year" + year);
		List<EmployeeSalary> entity = EmployeeSalaryRep.getlists(month, year);
		System.out.println("the length" + entity.size());

		return entity;
	}

	/* ---PAYSLIP-- */
	@RequestMapping(value = "Employeepayslip", method = { RequestMethod.GET, RequestMethod.POST })
	public String Employeepayslip(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date ctc_eff_date,
			@RequestParam(required = false) String emp_no1, @RequestParam(required = false) String emp_no,
			@RequestParam(required = false) String salaryMonth, @RequestParam(required = false) String empname,
			String keyword, Model md, HttpServletRequest req)

	{
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		System.out.println("THE EMPLOYEE NAME " + empname);
		System.out.println("THE EMPLOYEE NUMBER " + emp_no);
		System.out.println("THE SALARY MONTH " + salaryMonth);

		if (formmode == null || formmode.equals("add")) {
			md.addAttribute("formmode", "add");
			// md.addAttribute("salarypay", salary_Pay_Rep.getList());
			paystructureentity getemployeesdetails = Paystructurerep.getpaystructureedit(emp_no, salaryMonth, empname);
			md.addAttribute("EmployeeSlip", getemployeesdetails);
		}
		return "EmployeePaySlip";
	}

	/*
	 * ---EMPLOYEE SALARY SLIP---
	 * 
	 * @RequestMapping(value="Employeemonthlysalaryslip", method= {
	 * RequestMethod.GET,RequestMethod.POST }) public String
	 * emploeesalaryslip(@RequestParam(required=false) String emp_no,
	 * 
	 * @RequestParam(required=false) String salaryMonth,
	 * 
	 * @RequestParam(required=false) String empname,
	 * 
	 * @RequestParam(required=false) String formmode) {
	 * 
	 * 
	 * return null;
	 * 
	 * }
	 */

	@RequestMapping(value = "nationalvalues", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<String> nationalvalues(@RequestParam(required = false) String nationalvalue) {

		List<String> group = salary_parameter_Rep.getDistinctCountries(nationalvalue);
		System.out.println("size :" + group.size());

		return group;
	}

	@RequestMapping(value = "nationalvaluesone", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public int nationalvaluesone(@RequestParam(required = false) String nationalvalue, Model md) {
		System.out.println("value :" + nationalvalue);
		List<String> group = salary_parameter_Rep.getDistinctCountries(nationalvalue);
		System.out.println("size :" + group.size());
		md.addAttribute("groups", group);

		return group.size();
	}

	@RequestMapping(value = "getctcdatas", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public salary_parameter getctcdatas(@RequestParam(required = false) String selectedgroup) {

		salary_parameter group = salary_parameter_Rep.getgroup(selectedgroup);
		System.out.println("size :" + group.getCtc_cost_to_company());

		return group;
	}

	@RequestMapping(value = "AddScreen1", method = RequestMethod.POST)
	@ResponseBody
	public String AddScreen1(Model md, HttpServletRequest rq, @ModelAttribute paystructureentity Paystructureentity) {
		String userId = (String) rq.getSession().getAttribute("USERID");
		Date date = new Date();
		paystructureentity up = Paystructureentity;
		up.setEntry_user(userId);
		up.setEntry_time(date);
		up.setDel_flg("N");
		up.setEntity_flg("N");
		up.setModify_flg("N");
		up.setVerify_flg("N");
		Paystructurerep.save(up);
		return "Saved Successfully";
	}

	@RequestMapping(value = "modifyscreen1", method = RequestMethod.POST)
	@ResponseBody
	public String modifyscreens1(@RequestParam(required = false) String formMode,
			@RequestParam(required = false) String sal, @RequestParam(required = false) String name, Model md,
			HttpServletRequest rq, @ModelAttribute paystructureentity paystructureentity) {
		String msg = "";
		String userId = (String) rq.getSession().getAttribute("USERID");

		try {
			Optional<paystructureentity> up1 = Paystructurerep.getoptional(formMode);
			if (up1.isPresent()) {
				paystructureentity up = up1.get();
				up = paystructureentity;
				up.setDel_flg("N");
				up.setEntry_user(userId);
				up.setEntity_flg("N");
				up.setModify_flg("N");
				up.setVerify_flg("N");
				Paystructurerep.save(up);
				msg = "Modify Successfully";
			} else {
				msg = "Data Not Found";
			}
		} catch (Exception e) {
			msg = "Error occurred while verifing the data";
		}
		return msg;
	}

	@RequestMapping(value = "deletescreen1", method = RequestMethod.GET)
	@ResponseBody
	public String deletescreen1(@RequestParam(required = false) String emp_no) {
		String msg = "";
		try {
			paystructureentity vv = Paystructurerep.getid(emp_no);
			vv.setDel_flg("Y");
			Paystructurerep.save(vv);
			System.out.println("enter 1 ");
			msg = "Deleted Successfully";
		} catch (Exception e) {
			msg = "Delete Unsuccessfull" + e;
		}
		return msg;
	}

	@RequestMapping(value = "verifyscreen1", method = RequestMethod.POST)
	@ResponseBody
	public String verifyscreens1(@RequestParam(required = false) String formMode,
			@RequestParam(required = false) String sal, @RequestParam(required = false) String name, Model md,
			HttpServletRequest rq, @ModelAttribute paystructureentity paystructureentity) {
		String msg = "";

		System.out.println("Verifying user...");

		// Fetch session attributes
		String sessionUserId = (String) rq.getSession().getAttribute("USERID");
		String sessionUserName = (String) rq.getSession().getAttribute("USERNAME");

		System.out.println(sessionUserId);
		System.out.println(sessionUserName);

		try {
			Optional<paystructureentity> up1 = Paystructurerep.getoptional(formMode);

			if (sessionUserId.equals(up1.get().getEntry_user())) {
				msg = "Same User Can't Verify!!!";
			} else {
				if (up1.isPresent()) {
					paystructureentity up = up1.get();
					up = paystructureentity;
					up.setDel_flg("N");
					up.setEntity_flg("N");
					up.setModify_flg("N");
					up.setVerify_flg("Y");
					Paystructurerep.save(up);
					msg = "Verified Successfully";
				} else {
					msg = "Data Not Found";
				}
			}
		} catch (Exception e) {
			msg = "Error occurred while verifying the data" + e;
		}
		return msg;
	}

	@RequestMapping(value = "/cvfsubmit", method = RequestMethod.POST)
	@ResponseBody
	public String cvfsubmit(@RequestParam(required = false) String ref_no, Model md, HttpServletRequest rq,
			@ModelAttribute CandEvalFormEntity candEvalFormEntity) {

		// System.out.println("hi" + ref_no);
		// System.out.println("hi" + candEvalFormEntity.getCandi_name());

		CandEvalFormEntity up = candEvalFormEntity;

		up.setVerify_flg("N");

		candEvalFormRep.save(up);

		return "success";

	}

	@RequestMapping(value = "/cvfdelete", method = RequestMethod.POST)
	@ResponseBody
	public String cvfdelete(@RequestParam(required = false) String ref_no, Model md, HttpServletRequest rq) {

		CandEvalFormEntity up = candEvalFormRep.getCVFform(ref_no);
		candEvalFormRep.delete(up);

		return "Deleted successfully";

	}

	byte[] setvalue1;
	byte[] setImgValue1;

	@RequestMapping(value = "cvffileupload", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String cvffileupload(@RequestParam("file") MultipartFile file, CandEvalFormEntity candEvalFormEntity)
			throws IOException {
		// Call service layer to handle file uploads and form data
		// Print the value of 'fileData' to the console

		// System.out.println("Emp Id: " + document_Master_Entity.getEmp_id());

		byte[] byteArray = file.getBytes();

		this.setvalue1 = byteArray;

		return "success"; // Redirect to upload page after upload
	}

	@RequestMapping(value = "cvffileupload1", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String cvffileupload1(CandEvalFormEntity candEvalFormEntity) {
		// Call service layer to handle file uploads and form data
		// Print the value of 'fileData' to the console

		// System.out.println("Emp Id: " + document_Master_Entity.getEmp_id());
		candEvalFormEntity.setAnnexure_resume(setvalue1);
		// candEvalFormEntity.setDoc_image(setImgValue);
		candEvalFormEntity.setVerify_flg("N");
		candEvalFormRep.save(candEvalFormEntity);
		return "success"; // Redirect to upload page after upload
	}

	@RequestMapping(value = "/cvfverifysubmit/{b}", method = RequestMethod.POST)
	@ResponseBody
	public String cvfverifysubmit(@RequestParam(required = false) String ref_no, Model md, HttpServletRequest rq,
			@PathVariable String b, @ModelAttribute CandEvalFormEntity candEvalFormEntity) {

		CandEvalFormEntity up = candEvalFormRep.getCVFform(b);

		System.out.println("verify" + up.getRef_no());
		System.out.println("verify" + up.getAnnexure_resume());

		up.setVerify_flg("Y");

		candEvalFormRep.save(up);

		return "success";

	}

	@RequestMapping(value = "/cvfmodifysubmit1/{b}", method = RequestMethod.POST)
	@ResponseBody
	public String cvfmodifysubmit1(@RequestParam(required = false) String ref_no, Model md, HttpServletRequest rq,
			@PathVariable String b, @ModelAttribute CandEvalFormEntity candEvalFormEntity) {

		candEvalFormEntity.setRef_no(b);

		CandEvalFormEntity up1 = candEvalFormRep.getCVFform(b);
		candEvalFormEntity.setAnnexure_resume(up1.getAnnexure_resume());

		CandEvalFormEntity up = candEvalFormEntity;
		up.getCandi_name();
		System.out.println(up.getCandi_name());
		System.out.println(up.getAnnexure_resume());

		up.setVerify_flg("N");
		up.setAnnexure_resume(setvalue1);
		candEvalFormRep.save(up);

		return "success";

	}

	@RequestMapping(value = "/cvfmodifysubmit2/{ref_no}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ByteArrayResource> cvfmodifysubmit2(@PathVariable String ref_no) {
		CandEvalFormEntity entity = candEvalFormRep.getCVFform(ref_no);

		if (entity != null && entity.getAnnexure_resume() != null) {
			byte[] blobData = entity.getAnnexure_resume();
			System.out.println(blobData);

			// Set response headers
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_PDF);
			headers.setContentDispositionFormData("attachment", "annexure_resume.pdf");

			// Create a ByteArrayResource from the blob data
			ByteArrayResource resource = new ByteArrayResource(blobData);

			// Return ResponseEntity with the PDF blob data
			return ResponseEntity.ok().headers(headers).contentLength(blobData.length).body(resource);
		} else {
			// Return appropriate response if the entity or blob data is not found
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(value = "cvf", method = { RequestMethod.GET, RequestMethod.POST })
	public String cvf(@RequestParam(required = false) String formmode, @RequestParam(required = false) String ref_no,
			Model md, HttpServletRequest rq) {

		String userId = (String) rq.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("del", candEvalFormRep.getCVFList());
			md.addAttribute("formmode", "list");

		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");

		} else if (formmode.equals("verify")) {

			md.addAttribute("cvfview", candEvalFormRep.getCVFform(ref_no));
			md.addAttribute("formmode", "verify");

		} else if (formmode.equals("view")) {

			md.addAttribute("cvfview", candEvalFormRep.getCVFform(ref_no));
			md.addAttribute("formmode", "view");

		} else if (formmode.equals("modify")) {

			md.addAttribute("cvfview", candEvalFormRep.getCVFform(ref_no));
			md.addAttribute("formmode", "modify");
		}
		return "CandidateEvaluationForm";
	}

	@RequestMapping(value = "offerLetter", method = { RequestMethod.GET, RequestMethod.POST })
	public String offerLetter(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@ModelAttribute CandEvalFormEntity candEvalFormEntity, @RequestParam(required = false) String a,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {
		// md.addAttribute("IssueMaster", issueMasterRep.findAllCustom());

		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		md.addAttribute("empty", "");
		md.addAttribute("kkkk", a);
		md.addAttribute("cvfverify", candEvalFormRep.getCVFListoffer());

		System.out.println(a);
		md.addAttribute("cvfverifys", candEvalFormRep.getCVFforms(a));

		return "OfferLetter";
	}

	@RequestMapping(value = "SalaryRevision", method = { RequestMethod.GET, RequestMethod.POST })
	public String SalaryRevision(@RequestParam(required = false) String userid,
			@RequestParam(required = false) String formmode, Model md, HttpServletRequest req,
			@RequestParam(required = false) String emp_no,
			@RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date ctc_eff_date) {
		// md.addAttribute("IssueMaster", issueMasterRep.findAllCustom());

		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("formmode", "list");
			md.addAttribute("getct", salary_Pay_Rep.getList());

		} else if (formmode.equals("ctc")) {
			md.addAttribute("formmode", "ctc");
			md.addAttribute("getctc1", salary_Pay_Rep.getaedit1(emp_no, ctc_eff_date));

		}

		return "SalaryRevision";
	}

	@RequestMapping(value = "SalaryRevisiondownload", method = RequestMethod.GET)
	@ResponseBody
	public InputStreamResource SalaryRevisiondownload(HttpServletResponse response,
			@RequestParam(value = "emp_no", required = false) String emp_no,
			@RequestParam(value = "ctc_eff_date", required = false) String ctc_eff_date,
			@RequestParam(value = "filetype", required = false) String filetype) throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		System.out.println(emp_no);
		System.out.println(ctc_eff_date);
		try {
			logger.info("Getting download File :" + emp_no + ", FileType :" + filetype + "");

			File repfile = projectMasterServices.getctcpdf(emp_no, ctc_eff_date, filetype);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));
		} catch (JRException e) {
			// Log the error using the logger
			logger.error("Error generating TDS file", e);
			// Optionally, rethrow the exception or handle it as needed
			// throw new YourCustomException("Error generating TDS file", e);
		}

		return resource;
	}

	byte[] setctc;
	byte[] setctc1;

	@RequestMapping(value = "fileuploadrevision", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String fileuploadrevision(@RequestParam("file") MultipartFile file,
			@RequestParam("file1") MultipartFile file1, @RequestParam(required = false) String emp,
			Salary_Pay_Entity salary_Pay_Entity) throws IOException {

		byte[] byteArray = file.getBytes();
		this.setctc = byteArray;
		byte[] byteArrays = file1.getBytes();
		this.setctc1 = byteArrays;

		return "success";
	}

	@RequestMapping(value = "fileuploadrevisionsave", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String fileuploadrevisionsave(@RequestParam(required = false) String emp_no,
			@RequestParam(required = false) String ctc,

			Salary_Pay_Entity salary_Pay_Entity) throws ParseException {

		Salary_Pay_Entity up = salary_Pay_Rep.savectc(emp_no, ctc);

		up.setStr(setctc);
		up.setRevision(setctc1);

		salary_Pay_Rep.save(up);
		return "success";
	}

	@RequestMapping(value = "sendingmail_coveringletter", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<String> sendingmail_coveringletter(@RequestParam(required = false) String a,
			@RequestParam(required = false) String cc, @RequestParam(required = false) String d,
			@RequestParam(required = false) String ctc, Model md) throws SQLException, ParseException, IOException {
		System.out.println("Hi");
		String b = a;
		String to = b;

		String from = "HR@bornfire.in";
		String username = "HR@bornfire.in"; // change accordingly
		String password = "VNivas@636003"; // change accordingly
		String host = "sg2plzcpnl491716.prod.sin2.secureserver.net";
		String emp_no = d;

		System.out.println("sdfghjkl;");

		// Call sendMail method with correct parameters
		sendingmail_coveringletter.sendingctcmail(from, host, to, cc, username, password, emp_no, ctc); // pass from,
																										// host,
		// password, and to

		// Return success response
		return ResponseEntity.status(HttpStatus.OK).body("Email sent successfully");
	}

	@RequestMapping(value = "Appointment_Letter", method = { RequestMethod.GET, RequestMethod.POST })
	public String Appointment_Letter(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) String c,
			@RequestParam(required = false) String a, @RequestParam(required = false) String ref_no,
			CandEvalFormEntity candEvalFormEntity,
			@RequestParam(value = "page", required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		md.addAttribute("menu", "projectmaster"); // To highlight the menu

		if (formmode == null || formmode.equals("list")) {

			System.out.println(a);

			md.addAttribute("cvfverifys", candEvalFormRep.getCVFforms(a));
			System.out.println(candEvalFormRep.getCVFforms(a));
			md.addAttribute("kkkk", a);
			md.addAttribute("cvfverify", candEvalFormRep.getCVFListapp());
			md.addAttribute("empty", "");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", "edit");
			// md.addAttribute("formmode", "add"); // to set which form - valid values are
			// "edit" , "add" & "list"

			// md.addAttribute("domains", userProfileDao.getDomainList());
			// md.addAttribute("projectmaster", userProfileDao.getUser(userid));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", "add");

			// md.addAttribute("domains", userProfileDao.getDomainList());
			// md.addAttribute("projectmaster", userProfileDao.getUser(userid));

		} else {

			md.addAttribute("formmode", formmode);
			// md.addAttribute("domains", reportServices.getDomainList());
			// md.addAttribute("FinUserProfiles", userProfileDao.getFinUsersList());
			// md.addAttribute("projectmaster", userProfileDao.getUser(""));

		}

		return "Appointment_Letter";
	}

	@RequestMapping(value = "sendingmail_appointment", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<String> sendMails(@RequestParam(required = false) String a,
			@RequestParam(required = false) String cc, @RequestParam(required = false) String d,
			@RequestParam(required = false) List<String> t, Model md) throws SQLException, ParseException {
		System.out.println("Hi");
		String b = a;
		String to = b;
		String from = "valarmathi.s@bornfire.in";
		String username = "valarmathi.s@bornfire.in"; // change accordingly
		String password = "Bornfire@123"; // change accordingly
		String host = "sg2plzcpnl491716.prod.sin2.secureserver.net";
		String ref_no = d;
		/*
		 * List<String> y = t; for (String bb : y) { System.out.println(bb);
		 * 
		 * }
		 */

		System.out.println("sdfghjkl;");

		// Call sendMail method with correct parameters
		sendingmail_appointment.sendingmail(from, host, to, cc, username, password, ref_no); // pass from, host,
																								// password, and to

		// Return success response
		return ResponseEntity.status(HttpStatus.OK).body("Email sent successfully");
	}

	@RequestMapping(value = "Appointmentdownload", method = RequestMethod.GET)
	@ResponseBody
	public InputStreamResource Appointmentdownload(HttpServletResponse response,
			@RequestParam(value = "a", required = false) String a,
			@RequestParam(value = "filetype", required = false) String filetype) throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		System.out.println(a);
		try {
			logger.info("Getting download File :" + a + ", FileType :" + filetype + "");

			File repfile = projectMasterServices.getTdsExcel(a, filetype);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));
		} catch (JRException e) {
			// Log the error using the logger
			logger.error("Error generating TDS file", e);
			// Optionally, rethrow the exception or handle it as needed
			// throw new YourCustomException("Error generating TDS file", e);
		}

		return resource;
	}

	// private static final Logger logger =
	// LoggerFactory.getLogger(NavigationController.class);

	/*
	 * @RequestMapping(value = "/AppointmentdownloadSSSSS", method =
	 * RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	 * 
	 * @ResponseBody public InputStreamResource
	 * invoiceReportDownloadsww(HttpServletResponse response,
	 * 
	 * @RequestParam(value = "a", required = false) String a,
	 * 
	 * @RequestParam(value = "filetype", required = false) String filetype) throws
	 * IOException, SQLException {
	 * 
	 * response.setContentType("application/octet-stream");
	 * 
	 * InputStreamResource resource = null; try {
	 * logger.info("Getting download File :" + a + ", FileType :" + filetype + "");
	 * 
	 * // File repfile = projectMasterServices.getappointment(a, filetype);
	 * 
	 * response.setHeader("Content-Disposition", "attachment; filename=" +
	 * repfile.getName()); resource = new InputStreamResource(new
	 * FileInputStream(repfile)); } catch (FileNotFoundException e) {
	 * logger.error("File not found", e); // Handle the exception appropriately,
	 * such as returning an error response
	 * response.setStatus(HttpServletResponse.SC_NOT_FOUND); // Set HTTP 404 Not
	 * Found status } catch (JRException e) {
	 * logger.error("Error generating appointment file", e); // Handle the exception
	 * appropriately, such as returning an error response
	 * response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Set HTTP
	 * 500 Internal Server Error status }
	 * 
	 * return resource; }
	 */

	@RequestMapping(value = "offerLetterss", method = RequestMethod.GET)
	@ResponseBody
	public InputStreamResource invoiceReportDownloads(HttpServletResponse response,
			@RequestParam(value = "a", required = false) String a,
			@RequestParam(value = "filetype", required = false) String filetype) throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		System.out.println("GGGGGGG" + a);
		try {
			logger.info("Getting download File :" + a + ", FileType :" + filetype + "");

			File repfile = projectMasterServices.getofferExcel(a, filetype);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));
		} catch (JRException e) {
			// Log the error using the logger
			logger.error("Error generating TDS file", e);
			// Optionally, rethrow the exception or handle it as needed
			// throw new YourCustomException("Error generating TDS file", e);
		}

		return resource;
	}

	@RequestMapping(value = "salarystructuredownload", method = RequestMethod.GET)
	@ResponseBody
	public InputStreamResource invoiceReportDownload1(HttpServletResponse response,
			@RequestParam(value = "a", required = false) String a,
			@RequestParam(value = "ctc_eff_date", required = false) String ctc_eff_date,
			@RequestParam(value = "filetype", required = false) String filetype) throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		System.out.println(a);
		System.out.println(ctc_eff_date);
		try {
			logger.info("Getting download File :" + a + ", FileType :" + filetype + "");

			File repfile = projectMasterServices.getsalExcel(a, ctc_eff_date, filetype);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));
		} catch (JRException e) {
			// Log the error using the logger
			logger.error("Error generating TDS file", e);
			// Optionally, rethrow the exception or handle it as needed
			// throw new YourCustomException("Error generating TDS file", e);
		}

		return resource;
	}

	@RequestMapping(value = "sendingmail_offerletter", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<String> sendMailss(@RequestParam(required = false) String a,
			@RequestParam(required = false) String cc, @RequestParam(required = false) String d,
			@RequestParam(required = false) List<String> t, Model md) throws SQLException, ParseException, IOException {
		System.out.println("Hi");
		String b = a;
		String to = b;

		String from = "valarmathi.s@bornfire.in";
		String username = "valarmathi.s@bornfire.in"; // change accordingly
		String password = "Bornfire@123"; // change accordingly
		String host = "sg2plzcpnl491716.prod.sin2.secureserver.net";
		String ref_no = d;
		/*
		 * List<String> y = t; for (String bb : y) { System.out.println(bb);
		 * 
		 * }
		 */

		System.out.println("sdfghjkl;");

		// Call sendMail method with correct parameters
		sendigmail_offerletter.sendingmailones(from, host, to, cc, username, password, ref_no); // pass from, host,
																								// password, and to

		// Return success response
		return ResponseEntity.status(HttpStatus.OK).body("Email sent successfully");
	}

	@RequestMapping(value = "/pmaddsubmit", method = { RequestMethod.GET, RequestMethod.POST })

	@ResponseBody
	public String pmaddsubmit(Model md, HttpServletRequest rq, @RequestParam(required = false) String emp_id,
			@RequestParam(required = false) String emp_name, @RequestParam(required = false) String prev_orgn_1,
			@ModelAttribute ProfileManagerEntity1 profileManagerEntity) {

		System.out.println(emp_id);
		System.out.println(emp_name);
		System.out.println(prev_orgn_1);
		System.out.println("before" + profileManagerEntity.getPrev_orgn_1());

		ProfileManagerEntity1 up = profileManagerEntity;

		up.setVerify_flg("N");

		System.out.println("after" + up.getPrev_orgn_1());

		for (int i = 1; i <= 10; i++) {
			String fieldName = "prev_orgn_" + i;
			String fieldValue = null;
			switch (i) {
			case 1:
				fieldValue = profileManagerEntity.getPrev_orgn_1();
				if (fieldValue != null) {
					profileManagerEntity.setPrev_orgn_1(String.join("||", fieldValue.split(",")));
				}
				break;
			case 2:
				fieldValue = profileManagerEntity.getPrev_orgn_2();
				if (fieldValue != null) {
					profileManagerEntity.setPrev_orgn_2(String.join("||", fieldValue.split(",")));
				}
				break;
			case 3:
				fieldValue = profileManagerEntity.getPrev_orgn_3();
				if (fieldValue != null) {
					profileManagerEntity.setPrev_orgn_3(String.join("||", fieldValue.split(",")));
				}
				break;
			case 4:
				fieldValue = profileManagerEntity.getPrev_orgn_4();
				if (fieldValue != null) {
					profileManagerEntity.setPrev_orgn_4(String.join("||", fieldValue.split(",")));
				}
				break;
			case 5:
				fieldValue = profileManagerEntity.getPrev_orgn_5();
				if (fieldValue != null) {
					profileManagerEntity.setPrev_orgn_5(String.join("||", fieldValue.split(",")));
				}
				break;
			case 6:
				fieldValue = profileManagerEntity.getPrev_orgn_6();
				if (fieldValue != null) {
					profileManagerEntity.setPrev_orgn_6(String.join("||", fieldValue.split(",")));
				}
				break;
			case 7:
				fieldValue = profileManagerEntity.getPrev_orgn_7();
				if (fieldValue != null) {
					profileManagerEntity.setPrev_orgn_7(String.join("||", fieldValue.split(",")));
				}
				break;
			case 8:
				fieldValue = profileManagerEntity.getPrev_orgn_8();
				if (fieldValue != null) {
					profileManagerEntity.setPrev_orgn_8(String.join("||", fieldValue.split(",")));
				}
				break;
			case 9:
				fieldValue = profileManagerEntity.getPrev_orgn_9();
				if (fieldValue != null) {
					profileManagerEntity.setPrev_orgn_9(String.join("||", fieldValue.split(",")));
				}
				break;
			case 10:
				fieldValue = profileManagerEntity.getPrev_orgn_2();
				if (fieldValue != null) {
					profileManagerEntity.setPrev_orgn_2(String.join("||", fieldValue.split(",")));
				}
				break;
			}
		}

		for (int i = 1; i <= 20; i++) {
			String fieldName = "Proj_det_" + i;
			String fieldValue = null;
			switch (i) {
			case 1:
				fieldValue = profileManagerEntity.getProj_det_1();
				if (fieldValue != null) {
					profileManagerEntity.setProj_det_1(String.join("||", fieldValue.split(",")));
				}
				break;
			case 2:
				fieldValue = profileManagerEntity.getProj_det_2();
				if (fieldValue != null) {
					profileManagerEntity.setProj_det_2(String.join("||", fieldValue.split(",")));
				}
				break;
			case 3:
				fieldValue = profileManagerEntity.getProj_det_3();
				if (fieldValue != null) {
					profileManagerEntity.setProj_det_3(String.join("||", fieldValue.split(",")));
				}
				break;
			case 4:
				fieldValue = profileManagerEntity.getProj_det_4();
				if (fieldValue != null) {
					profileManagerEntity.setProj_det_4(String.join("||", fieldValue.split(",")));
				}
				break;
			case 5:
				fieldValue = profileManagerEntity.getProj_det_5();
				if (fieldValue != null) {
					profileManagerEntity.setProj_det_5(String.join("||", fieldValue.split(",")));
				}
				break;
			case 6:
				fieldValue = profileManagerEntity.getProj_det_6();
				if (fieldValue != null) {
					profileManagerEntity.setProj_det_6(String.join("||", fieldValue.split(",")));
				}
				break;
			case 7:
				fieldValue = profileManagerEntity.getProj_det_7();
				if (fieldValue != null) {
					profileManagerEntity.setProj_det_7(String.join("||", fieldValue.split(",")));
				}
				break;
			case 8:
				fieldValue = profileManagerEntity.getProj_det_8();
				if (fieldValue != null) {
					profileManagerEntity.setProj_det_8(String.join("||", fieldValue.split(",")));
				}
				break;
			case 9:
				fieldValue = profileManagerEntity.getProj_det_9();
				if (fieldValue != null) {
					profileManagerEntity.setProj_det_9(String.join("||", fieldValue.split(",")));
				}
				break;
			case 10:
				fieldValue = profileManagerEntity.getProj_det_2();

				if (fieldValue != null) {
					profileManagerEntity.setProj_det_2(String.join("||", fieldValue.split(",")));
				}
				break;
			case 11:
				fieldValue = profileManagerEntity.getProj_det_11();
				if (fieldValue != null) {
					profileManagerEntity.setProj_det_11(String.join("||", fieldValue.split(",")));
				}
				break;
			case 12:
				fieldValue = profileManagerEntity.getProj_det_12();
				if (fieldValue != null) {
					profileManagerEntity.setProj_det_12(String.join("||", fieldValue.split(",")));
				}
				break;
			case 13:
				fieldValue = profileManagerEntity.getProj_det_13();
				if (fieldValue != null) {
					profileManagerEntity.setProj_det_13(String.join("||", fieldValue.split(",")));
				}
				break;
			case 14:
				fieldValue = profileManagerEntity.getProj_det_14();
				if (fieldValue != null) {
					profileManagerEntity.setProj_det_14(String.join("||", fieldValue.split(",")));
				}
				break;
			case 15:
				fieldValue = profileManagerEntity.getProj_det_15();
				if (fieldValue != null) {
					profileManagerEntity.setProj_det_15(String.join("||", fieldValue.split(",")));
				}
				break;
			case 16:
				fieldValue = profileManagerEntity.getProj_det_16();
				if (fieldValue != null) {
					profileManagerEntity.setProj_det_16(String.join("||", fieldValue.split(",")));
				}
				break;
			case 17:
				fieldValue = profileManagerEntity.getProj_det_17();
				if (fieldValue != null) {
					profileManagerEntity.setProj_det_17(String.join("||", fieldValue.split(",")));
				}
				break;
			case 18:
				fieldValue = profileManagerEntity.getProj_det_18();
				if (fieldValue != null) {
					profileManagerEntity.setProj_det_18(String.join("||", fieldValue.split(",")));
				}
				break;
			case 19:
				fieldValue = profileManagerEntity.getProj_det_19();
				if (fieldValue != null) {
					profileManagerEntity.setProj_det_19(String.join("||", fieldValue.split(",")));
				}
				break;
			case 20:
				fieldValue = profileManagerEntity.getProj_det_20();
				if (fieldValue != null) {
					profileManagerEntity.setProj_det_20(String.join("||", fieldValue.split(",")));
				}
				break;
			}
		}

		profileManagerRep1.save(up);

		return "success";
	}

	@RequestMapping(value = "/pmversubmit/{emp}", method = { RequestMethod.POST })
	@ResponseBody
	public String pmversubmit(Model md, HttpServletRequest rq, @PathVariable String emp) {

		System.out.println("verify" + emp);
		ProfileManagerEntity1 up = profileManagerRep1.getPMform(emp);

		if (Objects.nonNull(up)) {
			up.setVerify_flg("Y");
			profileManagerRep1.save(up);
			return "success"; // Update successful
		} else {
			return "failure"; // Update failed
		}
	}

	@RequestMapping(value = "/pmdelete", method = RequestMethod.POST)
	@ResponseBody
	public String pmdelete(@RequestParam String emp_id, Model md, HttpServletRequest rq) {

		ProfileManagerEntity1 up = profileManagerRep1.getPMform(emp_id);

		profileManagerRep1.delete(up);

		return "Deleted successfully";

	}

	@RequestMapping(value = "/promanager", method = { RequestMethod.GET, RequestMethod.POST })
	public String promanager(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String emp_id, Model model, HttpServletRequest req) {

		String userId = (String) req.getSession().getAttribute("USERID");
		model.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		model.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("list")) {
			model.addAttribute("list", profileManagerRep1.getPMList());
			model.addAttribute("formmode", "list");
		} else if (formmode.equals("add")) {
			model.addAttribute("formmode", "add");
		} else if (formmode.equals("view")) {
			model.addAttribute("pmview", profileManagerRep1.getPMform(emp_id));

			// Process data for the first table with 6 columns
			List<String> pmForms = profileManagerRep1.getPMforms(emp_id);
			ArrayList<String[]> parsedForms = new ArrayList<>();
			for (String a : pmForms) {
				String[] values = a.split("\\|\\|");
				// Iterate over values and split into rows if more than 6 values are found
				for (int i = 0; i < values.length; i += 6) {
					String[] newRow = new String[6]; // Create a new row with 6 columns

					for (int j = 0; j < 6; j++) {
						// Check if index is within bounds
						if (i + j < values.length) {
							newRow[j] = values[i + j] != null ? values[i + j] : ""; // Replace null with empty string

						} else {
							newRow[j] = ""; // Fill with empty string if fewer than 6 values remain
						}
					}
					// Add the new row to parsedForms only if it has at least one non-zero value

					parsedForms.add(newRow);

				}
			}

			// Print parsed data for the first table
			for (String[] row : parsedForms) {
				System.out.println(Arrays.toString(row));
			}

			// Add parsedForms to the model for the first table
			model.addAttribute("pmlist", parsedForms);

			// Process data for the second table with 7 columns
			List<String> pmForms2 = profileManagerRep1.getPMforms2(emp_id);
			ArrayList<String[]> parsedForms2 = new ArrayList<>();
			for (String a : pmForms2) {
				String[] values = a.split("\\|\\|");
				// Iterate over values and split into rows if more than 7 values are found
				for (int i = 0; i < values.length; i += 7) {
					String[] newRow2 = new String[7]; // Create a new row with 7 columns
					for (int j = 0; j < 7; j++) {
						// Check if index is within bounds
						if (i + j < values.length) {
							newRow2[j] = values[i + j]; // Replace null with empty string
						} else {
							newRow2[j] = ""; // Fill with empty string if fewer than 7 values remain
						}
						// Count empty string values as well
						if (newRow2[j].isEmpty()) {
							// You can increment a counter here if you want to count empty values
						}
					}
					// Add the new row to parsedForms2
					parsedForms2.add(newRow2);
				}
			}

			// Print parsed data for the second table
			for (String[] row : parsedForms2) {
				System.out.println(Arrays.toString(row));
			}

			// Add parsedForms2 to the model for the second table
			model.addAttribute("pmlist2", parsedForms2);

			model.addAttribute("formmode", "view");

		} else if (formmode.equals("verify")) {
			model.addAttribute("pmview", profileManagerRep1.getPMform(emp_id));

			// Process data for the first table with 6 columns
			List<String> pmForms = profileManagerRep1.getPMforms(emp_id);
			ArrayList<String[]> parsedForms = new ArrayList<>();
			for (String a : pmForms) {
				String[] values = a.split("\\|\\|");
				// Iterate over values and split into rows if more than 6 values are found
				for (int i = 0; i < values.length; i += 6) {
					String[] newRow = new String[6]; // Create a new row with 6 columns

					for (int j = 0; j < 6; j++) {
						// Check if index is within bounds
						if (i + j < values.length) {
							newRow[j] = values[i + j] != null ? values[i + j] : ""; // Replace null with empty string

						} else {
							newRow[j] = ""; // Fill with empty string if fewer than 6 values remain
						}
					}
					// Add the new row to parsedForms only if it has at least one non-zero value

					parsedForms.add(newRow);

				}
			}

			// Print parsed data for the first table
			for (String[] row : parsedForms) {
				System.out.println(Arrays.toString(row));
			}

			// Add parsedForms to the model for the first table
			model.addAttribute("pmlist", parsedForms);

			// Process data for the second table with 7 columns
			List<String> pmForms2 = profileManagerRep1.getPMforms2(emp_id);
			ArrayList<String[]> parsedForms2 = new ArrayList<>();
			for (String a : pmForms2) {
				String[] values = a.split("\\|\\|");
				// Iterate over values and split into rows if more than 7 values are found
				for (int i = 0; i < values.length; i += 7) {
					String[] newRow2 = new String[7]; // Create a new row with 7 columns
					for (int j = 0; j < 7; j++) {
						// Check if index is within bounds
						if (i + j < values.length) {
							newRow2[j] = values[i + j]; // Replace null with empty string
						} else {
							newRow2[j] = ""; // Fill with empty string if fewer than 7 values remain
						}
						// Count empty string values as well
						if (newRow2[j].isEmpty()) {
							// You can increment a counter here if you want to count empty values
						}
					}
					// Add the new row to parsedForms2
					parsedForms2.add(newRow2);
				}
			}

			// Print parsed data for the second table
			for (String[] row : parsedForms2) {
				System.out.println(Arrays.toString(row));
			}

			// Add parsedForms2 to the model for the second table
			model.addAttribute("pmlist2", parsedForms2);

			model.addAttribute("formmode", "verify");
		}

		else if (formmode.equals("modify")) {
			model.addAttribute("pmview", profileManagerRep1.getPMform(emp_id));

			// Process data for the first table with 6 columns
			List<String> pmForms = profileManagerRep1.getPMforms(emp_id);
			ArrayList<String[]> parsedForms = new ArrayList<>();
			for (String a : pmForms) {
				String[] values = a.split("\\|\\|");
				// Iterate over values and split into rows if more than 6 values are found
				for (int i = 0; i < values.length; i += 6) {
					String[] newRow = new String[6]; // Create a new row with 6 columns

					for (int j = 0; j < 6; j++) {
						// Check if index is within bounds
						if (i + j < values.length) {
							newRow[j] = values[i + j] != null ? values[i + j] : ""; // Replace null with empty string

						} else {
							newRow[j] = ""; // Fill with empty string if fewer than 6 values remain
						}
					}
					// Add the new row to parsedForms only if it has at least one non-zero value

					parsedForms.add(newRow);

				}
			}

			// Print parsed data for the first table
			for (String[] row : parsedForms) {
				System.out.println(Arrays.toString(row));
			}

			// Add parsedForms to the model for the first table
			model.addAttribute("pmlist", parsedForms);

			// Process data for the second table with 7 columns
			List<String> pmForms2 = profileManagerRep1.getPMforms2(emp_id);
			ArrayList<String[]> parsedForms2 = new ArrayList<>();
			for (String a : pmForms2) {
				String[] values = a.split("\\|\\|");
				// Iterate over values and split into rows if more than 7 values are found
				for (int i = 0; i < values.length; i += 7) {
					String[] newRow2 = new String[7]; // Create a new row with 7 columns
					for (int j = 0; j < 7; j++) {
						// Check if index is within bounds
						if (i + j < values.length) {
							newRow2[j] = values[i + j]; // Replace null with empty string
						} else {
							newRow2[j] = ""; // Fill with empty string if fewer than 7 values remain
						}
						// Count empty string values as well
						if (newRow2[j].isEmpty()) {
							// You can increment a counter here if you want to count empty values
						}
					}
					// Add the new row to parsedForms2
					parsedForms2.add(newRow2);
				}
			}

			// Print parsed data for the second table
			for (String[] row : parsedForms2) {
				System.out.println(Arrays.toString(row));
			}

			// Add parsedForms2 to the model for the second table
			model.addAttribute("pmlist2", parsedForms2);

			model.addAttribute("formmode", "modify");
		}

		return "ProfileManager";
	}

	@RequestMapping(value = "assosiateProfile", method = { RequestMethod.GET, RequestMethod.POST })
	public String assosiateProfile(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resource_id, Model md, HttpServletRequest rq,
			@ModelAttribute Assosiate_Profile_Entity assosiate_Profile_Entity) {

		String userId = (String) rq.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("AssosiateList", assosiate_Profile_Repo.getAssosiateList());

		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");
		} else if (formmode.equals("verify")) {

			md.addAttribute("formmode", "verify");
			md.addAttribute("AssosiateVerify", assosiate_Profile_Repo.getSingleIdData(resource_id));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("AssosiateVerify", assosiate_Profile_Repo.getSingleIdData(resource_id));

		} else if (formmode.equals("modify")) {
			md.addAttribute("formmode", "modify");
			md.addAttribute("AssosiateVerify", assosiate_Profile_Repo.getSingleIdData(resource_id));
		}

		return "AssosiateProfile";
	}

	@RequestMapping(value = "assosiateAdd", method = RequestMethod.POST)
	@ResponseBody
	public String assosiateAdd(String ref_no, Model md, HttpServletRequest rq,
			@ModelAttribute Assosiate_Profile_Entity assosiate_Profile_Entity) {
		assosiate_Profile_Entity.setEntity_flg("N");
		assosiate_Profile_Repo.save(assosiate_Profile_Entity);
		return "success";

	}

	@RequestMapping(value = "assosiateDelete", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String assosiateDelete(@RequestParam(required = false) String resource_id,
			@RequestParam(required = false) String formmode, Model md, HttpServletRequest rq,
			@ModelAttribute Assosiate_Profile_Entity assosiate_Profile_Entity) {

		assosiate_Profile_Repo.deleteById(resource_id);

		return "deleted";

	}

	@RequestMapping(value = "assosiateVerify", method = RequestMethod.POST)
	@ResponseBody
	public String assosiateVerify(Model md, HttpServletRequest rq,
			@ModelAttribute Assosiate_Profile_Entity assosiate_Profile_Entity) {

		assosiate_Profile_Entity.setEntity_flg("Y");
		assosiate_Profile_Repo.save(assosiate_Profile_Entity);
		return "success";

	}

	@RequestMapping(value = "assosiateModify", method = RequestMethod.POST)
	@ResponseBody
	public String assosiateModify(String ref_no, Model md, HttpServletRequest rq,
			@ModelAttribute Assosiate_Profile_Entity assosiate_Profile_Entity) {
		assosiate_Profile_Entity.setEntity_flg("N");
		assosiate_Profile_Repo.save(assosiate_Profile_Entity);
		return "success";

	}

	@RequestMapping(value = "pay_master", method = { RequestMethod.GET, RequestMethod.POST })
	public String pay_master(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) String record,
			@RequestParam(required = false) String salaryMonth, @RequestParam(required = false) String empname,
			@RequestParam(required = false) String a, @RequestParam(required = false) String salary_month,
			@RequestParam(required = false) String empno, paystructureentity Paystructureentity,

			@RequestParam(value = "page", required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		// String loginuserid = (String) req.getSession().getAttribute("USERID");
		// Logging Navigation
		// loginServices.SessionLogging("USERPROFILE", "M2", req.getSession().getId(),
		// loginuserid, req.getRemoteAddr(),
		// "ACTIVE");

		md.addAttribute("menu", "projectmaster"); // To highlight the menu
		System.out.println("modiy pay master" + empno + salaryMonth);

		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		// System.out.println(salary_month);

		if (formmode == null || formmode.equals("list")) {
			System.out.println(Paystructurerep.getpays(salary_month));
			// md.addAttribute("salarypay", Paystructurerep.getpays(record));
			YearMonth currentYearMonth = YearMonth.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM"); // Corrected pattern for month
			DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy");
			String formattedMonth = currentYearMonth.format(formatter);
			String formattedYear = currentYearMonth.format(formatter1);
			System.out.println("Current Month and Year: " + formattedYear + formattedMonth);

			// md.addAttribute("salarypay", Paystructurerep.getpay());
			md.addAttribute("salarypay", Paystructurerep.getpayssdemo(formattedYear, formattedMonth));

			md.addAttribute("formmode", "list");

		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");
		} else if (formmode.equals("edit")) {
			md.addAttribute("salarypay", Paystructurerep.getaedits(empno, salaryMonth, empname));
			md.addAttribute("formmode", "edit");

		}

		return "pay_master";
	}

	@RequestMapping(value = "permas", method = { RequestMethod.GET, RequestMethod.POST })
	public String permas(@RequestParam(required = false) String formmode, @RequestParam(required = false) String emp_no,
			Model md, HttpServletRequest rq) {

		String userId = (String) rq.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("list1", perdiemMasterRep.getPerMasList());
			md.addAttribute("formmode", "list");

		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");

		} else if (formmode.equals("view")) {

			System.out.println(emp_no);
			md.addAttribute("permasview", perdiemMasterRep.getPerMasform(emp_no));

			md.addAttribute("list5", perdiemMasterRep.getPerMasList3(emp_no));

			md.addAttribute("formmode", "view");

		}

		return "PerdiemMaster";
	}

	@RequestMapping(value = "/permassubmit", method = RequestMethod.POST)
	@ResponseBody
	public String permassubmit(@RequestParam(required = false) String emp_no, Model md, HttpServletRequest rq,
			@ModelAttribute PerdiemMasterEntity perdiemMasterEntity) {

		System.out.println("hi" + emp_no);

		PerdiemMasterEntity up = perdiemMasterEntity;

		up.setEntity_flg("N");

		perdiemMasterRep.save(up);

		return "success";

	}

	@RequestMapping(value = "/permasdelete", method = RequestMethod.POST)
	@ResponseBody
	public String permasdelete(@RequestParam(required = false) String emp_no, Model md, HttpServletRequest rq) {

		PerdiemMasterEntity up = perdiemMasterRep.getPerMasform(emp_no);

		perdiemMasterRep.delete(up);

		return "Deleted successfully";

	}

	@RequestMapping(value = "batchJob", method = { RequestMethod.GET, RequestMethod.POST })
	public String batchJob(@RequestParam(required = false) String formmode, Model md, HttpServletRequest rq) {

		String userId = (String) rq.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		return "BatchJob";
	}

	@RequestMapping(value = "monthlySalaryWork", method = { RequestMethod.GET, RequestMethod.POST })
	public String monthlySalaryWork(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) String record,

			@RequestParam(required = false) String a, @RequestParam(required = false) String uniqueid,
			@RequestParam(required = false) String ref_no, @RequestParam(required = false) String emp_no,

			CandEvalFormEntity candEvalFormEntity,
			@RequestParam(value = "page", required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		md.addAttribute("menu", "projectmaster"); // To highlight the menu

		System.out.println(record);
		if (formmode == null || formmode.equals("add")) {
			// System.out.println(Paystructurerep.getpays(record));
			// md.addAttribute("salarypay",
			// Paystructurerep.getpays(record));assosiate_Profile_Repo
			// md.addAttribute("salarypay", Baj_Work_Repo.getpays(record));
			// md.addAttribute("salarypay", Paystructurerep.getpay());

			md.addAttribute("formmode", "add");
			;
		} else if (formmode.equals("list1")) {
			// System.out.println(Paystructurerep.getpays(record));
			md.addAttribute("salarypay", Baj_Work_Repo.getpays(record));
			System.out.println(Baj_Work_Repo.getpays(record));
			// md.addAttribute("salarypay", Paystructurerep.getpay());

			md.addAttribute("formmode", "list1");
		} else if (formmode.equals("edit")) {
			md.addAttribute("salarypay", Baj_Work_Repo.getlisttab1(uniqueid));
			md.addAttribute("formmode", "edit");

		} else if (formmode.equals("add1")) {
			md.addAttribute("formmode", "add1");

		} else if (formmode.equals("add2")) {
			md.addAttribute("formmode", "add2");

		} else if (formmode.equals("view")) {
			md.addAttribute("salarypay", Baj_Work_Repo.getlisttab1(uniqueid));
			md.addAttribute("formmode", "view");

		} else if (formmode.equals("verify")) {
			md.addAttribute("salarypay", Baj_Work_Repo.getlisttab1(uniqueid));
			md.addAttribute("formmode", "verify");

		}

		return "monthlySalaryWork";
	}

	@RequestMapping(value = "/monthlyversubmit/{uni}", method = { RequestMethod.POST })
	@ResponseBody
	public String monthlyversubmit(Model md, HttpServletRequest rq, @PathVariable String uni) {

		System.out.println("verify" + uni);
		Baj_Sal_Work_Entity up = Baj_Work_Repo.getlisttab1(uni);

		if (Objects.nonNull(up)) {
			up.setEntity_flg("Y");
			Baj_Work_Repo.save(up);
			return "success"; // Update successful
		} else {
			return "failure"; // Update failed
		}
	}

	@RequestMapping(value = "monthlysalary", method = { RequestMethod.GET, RequestMethod.POST })
	public String monthlysalary(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) String record,
			@RequestParam(required = false) String salaryMonth, @RequestParam(required = false) String empname,
			@RequestParam(required = false) String a, @RequestParam(required = false) String salary_month,
			@RequestParam(required = false) String empno, paystructureentity Paystructureentity,

			@RequestParam(value = "page", required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		// String loginuserid = (String) req.getSession().getAttribute("USERID");
		// Logging Navigation
		// loginServices.SessionLogging("USERPROFILE", "M2", req.getSession().getId(),
		// loginuserid, req.getRemoteAddr(),
		// "ACTIVE");

		md.addAttribute("menu", "projectmaster"); // To highlight the menu
		System.out.println("modiy pay master" + empno + salaryMonth);

		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		int year = 2024;
		int month = 10;

		YearMonth yearMonth = YearMonth.of(year, month);
		int daysInMonth = yearMonth.lengthOfMonth();
		int working_day = 0;
		int present_day = 0;

		List<ResourceMaster> resource = resourceMasterRepo.gettotaluser();
		for (ResourceMaster user : resource) {
			String days_present = attendanceRegisterRep.getPresentcnt(user.getResource_id(), "2024", "10");
			int holiday = btmAdminHolidayMasterRep.getHolidaycount("2024", "10");
			System.out.println("user_id=" + user.getResource_id());
			System.out.println("days_present=" + days_present);
			System.out.println("holiday=" + holiday);
			System.out.println("daysInMonth=" + daysInMonth);
			System.out.println("working_day=" + (daysInMonth - holiday));
			working_day = daysInMonth - holiday;
			present_day = working_day - Integer.valueOf(days_present);
			System.out.println("present_day=" + (working_day - Integer.valueOf(days_present)));
			paystructureentity sal = Paystructurerep.getsingle(user.getResource_id());
			// System.out.println("Net_sal="+sal.getNet_salary());
			if (sal != null) {
				BigDecimal netsalary = sal.getNet_salary();
				// BigDecimal perday=netsalary/working_day;
				// BigDecimal result = perday.multiply(BigDecimal.valueOf(present_day));
				// System.out.println("salary="+ result);
			}
		}

		// System.out.println(salary_month);

		if (formmode == null || formmode.equals("list")) {
			System.out.println(Paystructurerep.getpays(salary_month));
			// md.addAttribute("salarypay", Paystructurerep.getpays(record));
			YearMonth currentYearMonth = YearMonth.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM"); // Corrected pattern for month
			DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy");
			String formattedMonth = currentYearMonth.format(formatter);
			String formattedYear = currentYearMonth.format(formatter1);
			System.out.println("Current Month and Year: " + formattedYear + formattedMonth);

			// md.addAttribute("salarypay", Paystructurerep.getpay());
			md.addAttribute("salarypay", Paystructurerep.getpayssdemo(formattedYear, formattedMonth));

			md.addAttribute("formmode", "list");

		}
		return "monthlysalary";
	}

	/*
	 * @RequestMapping(value = "monthlySalaryWork", method = { RequestMethod.GET,
	 * RequestMethod.POST }) public String monthlySalaryWork(@RequestParam(required
	 * = false) String formmode, Model md, HttpServletRequest rq) {
	 * 
	 * return "MonthlySalaryWork"; }
	 */

	@RequestMapping(value = "monthlySalaryGenerator", method = { RequestMethod.GET, RequestMethod.POST })
	public String monthlySalaryGenerator(@RequestParam(required = false) String formmode, Model md,
			HttpServletRequest rq) {

		String userId = (String) rq.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		return "MonthlySalaryGenerator";
	}

	@SuppressWarnings("null")
	@RequestMapping(value = "salaryTransactionCreation", method = { RequestMethod.GET, RequestMethod.POST })
	public String salaryTransactionCreation(@RequestParam(required = false) String formmode, Model md,
			HttpServletRequest rq, paystructureentity Paystructureentity, @RequestParam(required = false) String sal) {

		String userId = (String) rq.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("add")) {
			// Fetch data from the database
			List<paystructureentity> basicPayEntities = Paystructurerep.getstc(sal);

			BigDecimal Basic = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getBasic_pay();
				if (basicPay != null) {
					Basic = Basic.add(basicPay);

				} else {

					Basic = Basic.add(BigDecimal.ZERO);

				}
			}

			BigDecimal hra = BigDecimal.ZERO;

			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getHra();
				if (basicPay != null) {
					hra = hra.add(basicPay);
				} else {

					hra = hra.add(BigDecimal.ZERO);
				}
			}

			BigDecimal spl = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {

				BigDecimal basicPay = entity.getSpl_allow();
				if (basicPay != null) {
					spl = spl.add(basicPay);
				} else {

					spl = spl.add(BigDecimal.ZERO);

				}
			}

			BigDecimal med = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getMedi_reimb();
				if (basicPay != null) {
					med = med.add(basicPay);
				} else {

					med = med.add(BigDecimal.ZERO);

				}
			}

			BigDecimal convey = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getConv_allow();
				if (basicPay != null) {
					convey = convey.add(basicPay);

				} else {
					convey = convey.add(BigDecimal.ZERO);
				}
			}

			BigDecimal lunch = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getLunch_allow();
				if (basicPay != null) {

					lunch = lunch.add(basicPay);

				} else {

					lunch = lunch.add(BigDecimal.ZERO);

				}
			}

			BigDecimal educ = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getEdu_allow();
				if (basicPay != null) {

					educ = educ.add(basicPay);

				} else {

					educ = educ.add(BigDecimal.ZERO);

				}
			}

			BigDecimal attire = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getBuss_attire();
				if (basicPay != null) {

					attire = attire.add(basicPay);

				} else {

					attire = attire.add(BigDecimal.ZERO);

				}
			}

			BigDecimal car = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getCar_maint();
				if (basicPay != null) {

					car = car.add(basicPay);

				} else {

					car = car.add(BigDecimal.ZERO);

				}
			}

			BigDecimal leave = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getLeave_travel_allow();
				if (basicPay != null) {

					leave = leave.add(basicPay);

				} else {

					leave = leave.add(BigDecimal.ZERO);

				}
			}

			BigDecimal outsta = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getOutstn_allow();
				if (basicPay != null) {

					outsta = outsta.add(basicPay);

				} else {

					outsta = outsta.add(BigDecimal.ZERO);

				}
			}

			BigDecimal annual = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getAnnual_loyal_bonus();
				if (basicPay != null) {

					annual = annual.add(basicPay);

				} else {

					annual = annual.add(BigDecimal.ZERO);

				}
			}

			BigDecimal other = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getOtr_allow();
				if (basicPay != null) {

					other = other.add(basicPay);

				} else {

					other = other.add(BigDecimal.ZERO);

				}
			}

			BigDecimal gross = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getGross_salary();
				if (basicPay != null) {

					gross = gross.add(basicPay);

				} else {

					gross = gross.add(BigDecimal.ZERO);

				}
			}

			BigDecimal staff = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getSpf();
				if (basicPay != null) {

					staff = staff.add(basicPay);

				} else {

					staff = staff.add(BigDecimal.ZERO);

				}
			}

			BigDecimal inc = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getTds();
				if (basicPay != null) {

					inc = inc.add(basicPay);

				} else {

					inc = inc.add(BigDecimal.ZERO);

				}
			}

			BigDecimal proff = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getProf_tax();
				if (basicPay != null) {

					proff = proff.add(basicPay);

				} else {

					proff = proff.add(BigDecimal.ZERO);

				}
			}

			BigDecimal emplo = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getEsi();
				if (basicPay != null) {

					emplo = emplo.add(basicPay);

				} else {

					emplo = emplo.add(BigDecimal.ZERO);

				}
			}

			BigDecimal recov = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getRecovery();
				if (basicPay != null) {

					recov = recov.add(basicPay);

				} else {

					recov = recov.add(BigDecimal.ZERO);

				}
			}

			BigDecimal otrded = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getOtr_ded();
				if (basicPay != null) {

					otrded = otrded.add(basicPay);

				} else {

					otrded = otrded.add(BigDecimal.ZERO);

				}
			}

			BigDecimal totded = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getTotal_deductions();
				if (basicPay != null) {

					totded = totded.add(basicPay);

				} else {

					totded = totded.add(BigDecimal.ZERO);

				}
			}

			BigDecimal nets = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getNet_salary();
				if (basicPay != null) {

					nets = nets.add(basicPay);

				} else {

					nets = nets.add(BigDecimal.ZERO);

				}
			}

			// System.out.println(Basic);
			md.addAttribute("stc", Basic);
			md.addAttribute("hr", hra);
			md.addAttribute("sp", spl);
			md.addAttribute("me", med);
			md.addAttribute("con", convey);
			md.addAttribute("lun", lunch);
			md.addAttribute("edu", educ);
			md.addAttribute("att", attire);
			md.addAttribute("ca", car);
			md.addAttribute("lea", leave);
			md.addAttribute("out", outsta);
			md.addAttribute("ann", annual);
			md.addAttribute("otr", other);
			md.addAttribute("gro", gross);
			md.addAttribute("sta", staff);
			md.addAttribute("in", inc);
			md.addAttribute("pro", proff);
			md.addAttribute("emp", emplo);
			md.addAttribute("rec", recov);
			md.addAttribute("otrd", otrded);
			md.addAttribute("totd", totded);
			md.addAttribute("net", nets);

			md.addAttribute("formmode", "add");
		} else if (formmode.equals("journal")) {
			md.addAttribute("formmode", "journal");

		} else if (formmode == null || formmode.equals("add1")) {
			// Fetch data from the database
			List<paystructureentity> basicPayEntities = Paystructurerep.getstc(sal);

			BigDecimal Basic = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getBasic_pay();
				if (basicPay != null) {
					Basic = Basic.add(basicPay);

				} else {

					Basic = Basic.add(BigDecimal.ZERO);

				}
			}

			BigDecimal hra = BigDecimal.ZERO;

			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getHra();
				if (basicPay != null) {
					hra = hra.add(basicPay);
				} else {

					hra = hra.add(BigDecimal.ZERO);
				}
			}

			BigDecimal spl = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {

				BigDecimal basicPay = entity.getSpl_allow();
				if (basicPay != null) {
					spl = spl.add(basicPay);
				} else {

					spl = spl.add(BigDecimal.ZERO);

				}
			}

			BigDecimal med = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getMedi_reimb();
				if (basicPay != null) {
					med = med.add(basicPay);
				} else {

					med = med.add(BigDecimal.ZERO);

				}
			}

			BigDecimal convey = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getConv_allow();
				if (basicPay != null) {
					convey = convey.add(basicPay);

				} else {
					convey = convey.add(BigDecimal.ZERO);
				}
			}

			BigDecimal lunch = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getLunch_allow();
				if (basicPay != null) {

					lunch = lunch.add(basicPay);

				} else {

					lunch = lunch.add(BigDecimal.ZERO);

				}
			}

			BigDecimal educ = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getEdu_allow();
				if (basicPay != null) {

					educ = educ.add(basicPay);

				} else {

					educ = educ.add(BigDecimal.ZERO);

				}
			}

			BigDecimal attire = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getBuss_attire();
				if (basicPay != null) {

					attire = attire.add(basicPay);

				} else {

					attire = attire.add(BigDecimal.ZERO);

				}
			}

			BigDecimal car = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getCar_maint();
				if (basicPay != null) {

					car = car.add(basicPay);

				} else {

					car = car.add(BigDecimal.ZERO);

				}
			}

			BigDecimal leave = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getLeave_travel_allow();
				if (basicPay != null) {

					leave = leave.add(basicPay);

				} else {

					leave = leave.add(BigDecimal.ZERO);

				}
			}

			BigDecimal outsta = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getOutstn_allow();
				if (basicPay != null) {

					outsta = outsta.add(basicPay);

				} else {

					outsta = outsta.add(BigDecimal.ZERO);

				}
			}

			BigDecimal annual = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getAnnual_loyal_bonus();
				if (basicPay != null) {

					annual = annual.add(basicPay);

				} else {

					annual = annual.add(BigDecimal.ZERO);

				}
			}

			BigDecimal other = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getOtr_allow();
				if (basicPay != null) {

					other = other.add(basicPay);

				} else {

					other = other.add(BigDecimal.ZERO);

				}
			}

			BigDecimal gross = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getGross_salary();
				if (basicPay != null) {

					gross = gross.add(basicPay);

				} else {

					gross = gross.add(BigDecimal.ZERO);

				}
			}

			BigDecimal staff = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getSpf();
				if (basicPay != null) {

					staff = staff.add(basicPay);

				} else {

					staff = staff.add(BigDecimal.ZERO);

				}
			}

			BigDecimal inc = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getTds();
				if (basicPay != null) {

					inc = inc.add(basicPay);

				} else {

					inc = inc.add(BigDecimal.ZERO);

				}
			}

			BigDecimal proff = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getProf_tax();
				if (basicPay != null) {

					proff = proff.add(basicPay);

				} else {

					proff = proff.add(BigDecimal.ZERO);

				}
			}

			BigDecimal emplo = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getEsi();
				if (basicPay != null) {

					emplo = emplo.add(basicPay);

				} else {

					emplo = emplo.add(BigDecimal.ZERO);

				}
			}

			BigDecimal recov = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getRecovery();
				if (basicPay != null) {

					recov = recov.add(basicPay);

				} else {

					recov = recov.add(BigDecimal.ZERO);

				}
			}

			BigDecimal otrded = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getOtr_ded();
				if (basicPay != null) {

					otrded = otrded.add(basicPay);

				} else {

					otrded = otrded.add(BigDecimal.ZERO);

				}
			}

			BigDecimal totded = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getTotal_deductions();
				if (basicPay != null) {

					totded = totded.add(basicPay);

				} else {

					totded = totded.add(BigDecimal.ZERO);

				}
			}

			BigDecimal nets = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getNet_salary();
				if (basicPay != null) {

					nets = nets.add(basicPay);

				} else {

					nets = nets.add(BigDecimal.ZERO);

				}
			}

			// System.out.println(Basic);
			md.addAttribute("stc", Basic);
			md.addAttribute("hr", hra);
			md.addAttribute("sp", spl);
			md.addAttribute("me", med);
			md.addAttribute("con", convey);
			md.addAttribute("lun", lunch);
			md.addAttribute("edu", educ);
			md.addAttribute("att", attire);
			md.addAttribute("ca", car);
			md.addAttribute("lea", leave);
			md.addAttribute("out", outsta);
			md.addAttribute("ann", annual);
			md.addAttribute("otr", other);
			md.addAttribute("gro", gross);
			md.addAttribute("sta", staff);
			md.addAttribute("in", inc);
			md.addAttribute("pro", proff);
			md.addAttribute("emp", emplo);
			md.addAttribute("rec", recov);
			md.addAttribute("otrd", otrded);
			md.addAttribute("totd", totded);
			md.addAttribute("net", nets);

			md.addAttribute("formmode", "add1");
		}

		return "SalaryTransactionCreation";
	}

	@RequestMapping(value = "salaryPaymentTransaction", method = { RequestMethod.GET, RequestMethod.POST })
	public String salaryPaymentTransaction(@RequestParam(required = false) String formmode, Model md,
			HttpServletRequest rq) {

		String userId = (String) rq.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		return "SalaryPaymentTransaction";
	}

	@RequestMapping(value = "bankFileDownload", method = { RequestMethod.GET, RequestMethod.POST })
	public String bankFileDownload(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String a, Model md, HttpServletRequest rq) {
		String userId = (String) rq.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		System.out.println("bankFileDownload" + a);
		if (formmode == null || formmode.equals("list")) {
			// System.out.println(Paystructurerep.getpays(record));
			// md.addAttribute("salarypay",
			// Paystructurerep.getpays(record));assosiate_Profile_Repo
			// md.addAttribute("salarypay", Baj_Work_Repo.getpays(record));
			// md.addAttribute("salarypay", Paystructurerep.getpay());

			md.addAttribute("formmode", "list");
			;
		} else if (formmode.equals("add")) {

			Date currentDate = new Date();

			// Create a date format
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

			// Format the current date
			String formattedDate = dateFormat.format(currentDate);

			// Print the formatted date
			System.out.println("Current Date: " + formattedDate);
			md.addAttribute("formattedDate", formattedDate);
			md.addAttribute("salarypay", Paystructurerep.bankjobicici(a));
			// md.addAttribute("ifsccode", Paystructurerep.bjicicinotpresent(a));

			System.out.println(Paystructurerep.bankjobicici(a));
			// System.out.println(Baj_Work_Repo.getpays(record));
			// md.addAttribute("salarypay", Paystructurerep.getpay());

			md.addAttribute("formmode", "add");
		}
		return "BankFileDownload";
	}

	@RequestMapping(value = "paySlipGeneration", method = { RequestMethod.GET, RequestMethod.POST })
	public String paySlipGeneration(@RequestParam(required = false) String formmode, Model md, HttpServletRequest rq) {

		String userId = (String) rq.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		md.addAttribute("cvfverify", Paystructurerep.getpaystruce());

		return "PaySlipGeneration";
	}

	@RequestMapping(value = "perdiemGeneration", method = { RequestMethod.GET, RequestMethod.POST })
	public String perdiemGeneration(@RequestParam(required = false) String formmode, Model md, HttpServletRequest rq) {

		String userId = (String) rq.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		md.addAttribute("cvfverify", perdiemMasterRep.getperdium());
		return "PerdiemGeneration";
	}

	@RequestMapping(value = "viewtotds1", method = RequestMethod.POST)
	@ResponseBody
	public String viewtotds1(@RequestParam(required = false) String formmode,

			Model md, HttpServletRequest rq, @RequestParam(required = false) String b,
			@RequestParam(required = false) String a, @RequestParam(required = false) String t) throws ParseException {

		String userId = (String) rq.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", Baj_Work_Repo.getpays(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = dateFormat.parse(t);
		System.out.println("}}}}}}}}}}}}}}}}}}}}}}}}" + a);
		System.out.println("]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]" + t);

		// String h = a.substring(0, a.length() - 1);
		// System.out.println(h); // Output: 202

		int lastDigit = Character.getNumericValue(a.charAt(a.length() - 1));
		System.out.println(lastDigit);// Get the last digit as an integer
		lastDigit--; // Increment the last digit
		String h = a.substring(0, a.length() - 1) + lastDigit;

		// Remove the last character and append the incremented digit
		System.out.println("sdfghjkl" + h); // Output: 2024

		String inputYearString = a.substring(0, 4);
		String inputMonthString = a.substring(4);
		System.out.println("sdfghjhgfdsdfghj8888888888" + inputMonthString);
		String YearL = null;

		YearMonth currentYearMonth = YearMonth.now();
		// LocalDate endOfMonth = currentYearMonth.atEndOfMonth();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy");
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd");

		/*
		 * int n = Integer.parseInt(a); System.out.println("dfdfdfdfdf"+n);
		 * 
		 * 
		 * int month = n % 100;
		 * 
		 * 
		 * YearMonth yearMonth = YearMonth.of(n / 100, month); int lastDayOfMonth =
		 * yearMonth.lengthOfMonth();
		 * 
		 * 
		 * int lastDay = lastDayOfMonth;
		 * 
		 * 
		 * System.out.println("Last day of the month: " + lastDay);
		 */

		int n = Integer.parseInt(a);
		System.out.println("dfdfdfdfdf" + n);

		int month = n % 100;

		// Determine the last day of the month
		YearMonth yearMonth = YearMonth.of(n / 100, month);
		int lastDayOfMonth = yearMonth.lengthOfMonth();

		// Convert lastDayOfMonth to BigDecimal
		BigDecimal lastDay = BigDecimal.valueOf(lastDayOfMonth);
		System.out.println("Last day of the month: " + lastDay);

		// System.out.println(formatter2);
		String formattedMonth = currentYearMonth.format(formatter);
		String formattedYear = currentYearMonth.format(formatter1);
		// String formattedDate = endOfMonth.format(dateFormatter);
		System.out.println(formattedMonth);
		System.out.println(formattedYear);
		String p = formattedYear + formattedMonth;
		// System.out.println("End of the Month Date: " + formattedDate);

		if (a.equals(p)) {

			try {
				List<paystructureentity> up2 = Paystructurerep.getpays(h);
				// List<paystructureentity> salary=Paystructurerep.getpaysnewss(h);
				List<Baj_Sal_Work_Entity> up3 = new ArrayList<>();

				for (paystructureentity salpay : up2) {
					// SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Baj_Sal_Work_Entity salwork = new Baj_Sal_Work_Entity();
					salwork.setEmp_no(salpay.getEmp_no());
					salwork.setEmp_name(salpay.getEmp_name());
					salwork.setEmp_desig(salpay.getEmp_desig());
					salwork.setEmp_group(salpay.getEmp_group());
					salwork.setEmp_division(salpay.getEmp_division());

					salwork.setDate_of_birth(salpay.getDate_of_birth());
					salwork.setDate_of_joining(salpay.getDate_of_joining());

					salwork.setSpf_acct_no(salpay.getSpf_acct_no());
					salwork.setUrn_no(salpay.getUrn_no());
					salwork.setRecord_date(salpay.getRecord_date());
					salwork.setSalary_month(a); // First setting
					// salwork.setSalary_month(salpay.getSalary_month()); // Second setting

					// salwork.setNo_of_days(salpay.getNo_of_days());
					salwork.setNo_of_days(lastDay);
					// System.out.println("dfghjklpppppppppppppppppp"+lastDay);
					// salwork.setDays_paid(salpay.getDays_paid());
					salwork.setDays_paid(lastDay);
					// System.out.println("dfghjklpppppppppppppppppp"+lastDay);
					salwork.setLoss_of_pay(salpay.getLoss_of_pay());
					salwork.setPayment_mode(salpay.getPayment_mode());
					salwork.setBank_name(salpay.getBank_name());
					salwork.setBank_acct_no(salpay.getBank_acct_no());
					salwork.setBasic_pay(salpay.getBasic_pay());
					salwork.setHra(salpay.getHra());
					salwork.setSpl_allow(salpay.getSpl_allow());
					salwork.setMedi_reimb(salpay.getMedi_reimb());

					salwork.setConv_allow(salpay.getConv_allow());
					salwork.setLunch_allow(salpay.getLunch_allow());
					salwork.setEdu_allow(salpay.getEdu_allow());
					salwork.setBuss_attire(salpay.getBuss_attire());
					salwork.setCar_maint(salpay.getCar_maint());
					salwork.setLeave_travel_allow(salpay.getLeave_travel_allow());
					salwork.setOutstn_allow(salpay.getOutstn_allow());
					salwork.setAnnual_loyal_bonus(salpay.getAnnual_loyal_bonus());

					salwork.setOtr_allow(salpay.getOtr_allow());
					salwork.setGross_salary(salpay.getGross_salary());
					salwork.setSpf(salpay.getSpf());
					salwork.setTds(salpay.getTds());
					salwork.setProf_tax(salpay.getProf_tax());
					salwork.setEsi(salpay.getEsi());
					salwork.setRecovery(salpay.getRecovery());
					salwork.setOtr_ded(salpay.getOtr_ded());
					salwork.setTotal_deductions(salpay.getTotal_deductions());
					salwork.setNet_salary(salpay.getNet_salary());
					salwork.setDate_of_pay(salpay.getDate_of_pay());
					salwork.setCum_tds_fy(salpay.getCum_tds_fy());
					salwork.setProv_it(salpay.getProv_it());
					salwork.setTax_due(salpay.getTax_due());
					salwork.setTax_per_month(salpay.getTax_per_month());
					salwork.setCtc_amt(salpay.getCtc_amt());
					salwork.setDecl_status(salpay.getDecl_status());
					salwork.setCtc_eff_date(salpay.getCtc_eff_date());
					salwork.setCtc_end_date(salpay.getCtc_end_date());
					salwork.setMobile_no(salpay.getMobile_no());
					salwork.setEmail_id(salpay.getEmail_id());
					salwork.setRecord_type(salpay.getRecord_type());
					salwork.setRecord_srl_no(salpay.getRecord_srl_no());
					salwork.setRecord_date(salpay.getRecord_date());
					salwork.setRecord_date(date);
					// String d[]=(salpay.getRecord_date().toString()).split(" ");
					// salwork.setRecord_date(d[0]);
					salwork.setPay_status(salpay.getPay_status());
					salwork.setEntity_flg(salpay.getEntity_flg());
					salwork.setDel_flg(salpay.getDel_flg());
					salwork.setEntry_time(salpay.getEntry_time());
					salwork.setEntry_user(salpay.getEntry_user());
					salwork.setModify_flg(salpay.getModify_flg());
					salwork.setModify_time(salpay.getModify_time());
					salwork.setModify_user(salpay.getModify_user());
					salwork.setVerify_time(salpay.getVerify_time());
					salwork.setVerify_user(salpay.getVerify_user());
					salwork.setRemarks(salpay.getRemarks());
					salwork.setAadhar_no(salpay.getAadhar_no());
					salwork.setUniqueid(salwork.getEmp_no() + (salwork.getSalary_month()));
					// System.out.println(salwork.getEmp_no() + (salwork.getSalary_month()));

					up3.add(salwork);

					// up3.add(gstoverseas);
				}

				System.out.println(up3);
				//
				Baj_Work_Repo.saveAll(up3);
				// System.out.println("sdfghjkl"+salwork.getUniqueid());

				// gstBtmRep.getInsert(b,a);

				// System.out.println("}}}}}}}}}}}}}}}}}}}}}}}}" + a + b);
				// int uniqueIdCounter = Integer.parseInt(b);
				// int uniqueIdCounter1 = Integer.parseInt(a)salpay

				// System.out.println(gstRep.getInsert(a,b));
				String msg = "Data Saved Successfully";
				return msg;

			} catch (Exception e) {
				e.printStackTrace();
				return "Error: " + e.getMessage();
			}

		}

		else {

			try {
				List<paystructureentity> up2 = Paystructurerep.getpays(a);
				// List<paystructureentity> salary=Paystructurerep.getpaysnewss(h);
				List<Baj_Sal_Work_Entity> up3 = new ArrayList<>();

				for (paystructureentity salpay : up2) {
					// SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Baj_Sal_Work_Entity salwork = new Baj_Sal_Work_Entity();
					salwork.setEmp_no(salpay.getEmp_no());
					salwork.setEmp_name(salpay.getEmp_name());
					salwork.setEmp_desig(salpay.getEmp_desig());
					salwork.setEmp_group(salpay.getEmp_group());
					salwork.setEmp_division(salpay.getEmp_division());

					salwork.setDate_of_birth(salpay.getDate_of_birth());
					salwork.setDate_of_joining(salpay.getDate_of_joining());

					salwork.setSpf_acct_no(salpay.getSpf_acct_no());
					salwork.setUrn_no(salpay.getUrn_no());
					salwork.setRecord_date(salpay.getRecord_date());
					salwork.setSalary_month(a); // First setting
					// salwork.setSalary_month(salpay.getSalary_month()); // Second setting

					// salwork.setNo_of_days(salpay.getNo_of_days());
					salwork.setNo_of_days(lastDay);
					// System.out.println("dfghjklpppppppppppppppppp"+lastDay);
					// salwork.setDays_paid(salpay.getDays_paid());
					salwork.setDays_paid(lastDay);
					// System.out.println("dfghjklpppppppppppppppppp"+lastDay);
					salwork.setLoss_of_pay(salpay.getLoss_of_pay());
					salwork.setPayment_mode(salpay.getPayment_mode());
					salwork.setBank_name(salpay.getBank_name());
					salwork.setBank_acct_no(salpay.getBank_acct_no());
					salwork.setBasic_pay(salpay.getBasic_pay());
					salwork.setHra(salpay.getHra());
					salwork.setSpl_allow(salpay.getSpl_allow());
					salwork.setMedi_reimb(salpay.getMedi_reimb());

					salwork.setConv_allow(salpay.getConv_allow());
					salwork.setLunch_allow(salpay.getLunch_allow());
					salwork.setEdu_allow(salpay.getEdu_allow());
					salwork.setBuss_attire(salpay.getBuss_attire());
					salwork.setCar_maint(salpay.getCar_maint());
					salwork.setLeave_travel_allow(salpay.getLeave_travel_allow());
					salwork.setOutstn_allow(salpay.getOutstn_allow());
					salwork.setAnnual_loyal_bonus(salpay.getAnnual_loyal_bonus());

					salwork.setOtr_allow(salpay.getOtr_allow());
					salwork.setGross_salary(salpay.getGross_salary());
					salwork.setSpf(salpay.getSpf());
					salwork.setTds(salpay.getTds());
					salwork.setProf_tax(salpay.getProf_tax());
					salwork.setEsi(salpay.getEsi());
					salwork.setRecovery(salpay.getRecovery());
					salwork.setOtr_ded(salpay.getOtr_ded());
					salwork.setTotal_deductions(salpay.getTotal_deductions());
					salwork.setNet_salary(salpay.getNet_salary());
					salwork.setDate_of_pay(salpay.getDate_of_pay());
					salwork.setCum_tds_fy(salpay.getCum_tds_fy());
					salwork.setProv_it(salpay.getProv_it());
					salwork.setTax_due(salpay.getTax_due());
					salwork.setTax_per_month(salpay.getTax_per_month());
					salwork.setCtc_amt(salpay.getCtc_amt());
					salwork.setDecl_status(salpay.getDecl_status());
					salwork.setCtc_eff_date(salpay.getCtc_eff_date());
					salwork.setCtc_end_date(salpay.getCtc_end_date());
					salwork.setMobile_no(salpay.getMobile_no());
					salwork.setEmail_id(salpay.getEmail_id());
					salwork.setRecord_type(salpay.getRecord_type());
					salwork.setRecord_srl_no(salpay.getRecord_srl_no());
					salwork.setRecord_date(date);
					salwork.setPay_status(salpay.getPay_status());
					salwork.setEntity_flg(salpay.getEntity_flg());
					salwork.setDel_flg(salpay.getDel_flg());
					salwork.setEntry_time(salpay.getEntry_time());
					salwork.setEntry_user(salpay.getEntry_user());
					salwork.setModify_flg(salpay.getModify_flg());
					salwork.setModify_time(salpay.getModify_time());
					salwork.setModify_user(salpay.getModify_user());
					salwork.setVerify_time(salpay.getVerify_time());
					salwork.setVerify_user(salpay.getVerify_user());
					salwork.setRemarks(salpay.getRemarks());
					salwork.setAadhar_no(salpay.getAadhar_no());
					salwork.setUniqueid(salwork.getEmp_no() + (salwork.getSalary_month()));
					// System.out.println(salwork.getEmp_no() + (salwork.getSalary_month()));

					up3.add(salwork);

					// up3.add(gstoverseas);
				}

				System.out.println(up3);
				//
				Baj_Work_Repo.saveAll(up3);
				// System.out.println("sdfghjkl"+salwork.getUniqueid());

				// gstBtmRep.getInsert(b,a);

				// System.out.println("}}}}}}}}}}}}}}}}}}}}}}}}" + a + b);
				// int uniqueIdCounter = Integer.parseInt(b);
				// int uniqueIdCounter1 = Integer.parseInt(a)salpay

				// System.out.println(gstRep.getInsert(a,b));
				String msg = "Data Saved Successfully";
				return msg;

			} catch (Exception e) {
				e.printStackTrace();
				return "Error: " + e.getMessage();
			}

		}
	}

	/*
	 * private BigDecimal parseDate(String t) { // TODO Auto-generated method stub
	 * return null; }
	 */

	@RequestMapping(value = "submitaddbaj", method = RequestMethod.POST)
	@ResponseBody
	public String submitaddbaj(Model md, HttpServletRequest rq, @ModelAttribute Baj_Sal_Work_Entity Baj_Sal_Work_Entity,
			String emp_name) {

		System.out.println("the rating AGENCY>>>> ");
		Baj_Sal_Work_Entity up = Baj_Sal_Work_Entity;
		up.setUniqueid(Baj_Sal_Work_Entity.getEmp_no() + Baj_Sal_Work_Entity.getSalary_month());
		System.out.println(Baj_Sal_Work_Entity.getEmp_no() + Baj_Sal_Work_Entity.getSalary_month());

		System.out.println("hi it is gst here your adding the record for TDS");

		Baj_Work_Repo.save(up);

		return "added successfully BAJ SALARY";

	}

	@RequestMapping(value = "tdstab1edit1", method = RequestMethod.POST)
	@ResponseBody
	public String tdstab1edit1(@ModelAttribute Baj_Sal_Work_Entity Baj_Sal_Work_Entity, String tran_id,
			@RequestParam(required = false) String uniqueid) throws ParseException {

		System.out.println("hihihi");
		System.out.println(uniqueid);
		// Baj_Sal_Work_Entity up = Baj_Work_Repo.getlisttab1(uniqueid);
		Baj_Sal_Work_Entity up = Baj_Sal_Work_Entity;
		System.out.println("hi this is btm");
		Baj_Work_Repo.save(up);
		// SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss",
		// Locale.ENGLISH);

		/*
		 * try {
		 * 
		 * up.setEmp_name(tdsentity.getEmp_name()); up.setPan_no(tdsentity.getPan_no());
		 * up.setDate_of_pay(tdsentity.getDate_of_pay());
		 * up.setNet_salary(tdsentity.getNet_salary());
		 * up.setRate_of_tds(tdsentity.getTds_tran_ref());
		 * up.setBank_bsr_code(tdsentity.getBank_bsr_code());
		 * up.setTds(tdsentity.getTds());
		 * up.setBank_chalan_no(tdsentity.getBank_chalan_no());
		 * up.setBank_name(tdsentity.getBank_name());
		 * up.setChalan_amt(tdsentity.getChalan_amt());
		 * up.setTds_remit_date(tdsentity.getTds_remit_date());
		 * up.setTds_tran_ref(tdsentity.getTds_tran_ref());
		 * 
		 * //up.setUniqueid(Gstoverseas.getUniqueid());
		 * 
		 * 
		 * 
		 * tdsRepos.save(up);
		 * System.out.println("hi this is gst from tds"+tdsentity.getEmp_name());
		 * System.out.println("hi this is btm"+tdsentity.getDate_of_pay());
		 * 
		 * // Save the 'up' object with the updated entry_time
		 * 
		 * } catch (Exception e) { e.printStackTrace(); // Handle potential errors here,
		 * such as ParseException }
		 */

		return "edited Successfully tdstable1";

	}

	@RequestMapping(value = "deletetds1", method = RequestMethod.POST)
	@ResponseBody
	public String deletetds1(Model md, HttpServletRequest rq, @ModelAttribute Baj_Sal_Work_Entity Baj_Sal_Work_Entity,
			String tran_id, @RequestParam(required = false) String uniqueid) {

		System.out.println(uniqueid);
		Baj_Sal_Work_Entity up = Baj_Work_Repo.getlisttab1(uniqueid);

		System.out.println("hi it is gst here your adding the record for india");
		System.out.println("hi it is gst here your adding the record " + Baj_Sal_Work_Entity.getUniqueid());

		Baj_Work_Repo.delete(up);

		return "deleted successfully";

	}

	@RequestMapping(value = "swappingtosalery", method = RequestMethod.POST)
	@ResponseBody
	public String swappingtosalery(@RequestParam(required = false) String formmode, Model md, HttpServletRequest rq,
			@RequestParam(required = false) String b, @RequestParam(required = false) String a) {

		String userId = (String) rq.getSession().getAttribute("USERID");

		System.out.println("}}}}}}}}}}}}}}}}}}}}}}}}" + a);

		try {
			List<Baj_Sal_Work_Entity> up2 = Baj_Work_Repo.getswap(a);
			// System.out.println(up2);
			List<paystructureentity> up3 = new ArrayList<>();

			for (Baj_Sal_Work_Entity salsecond : up2) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				paystructureentity salarymain = new paystructureentity();
				salarymain.setEmp_no(salsecond.getEmp_no());
				salarymain.setEmp_name(salsecond.getEmp_name());
				salarymain.setEmp_desig(salsecond.getEmp_desig());
				salarymain.setEmp_group(salsecond.getEmp_group());
				salarymain.setEmp_division(salsecond.getEmp_division());

				salarymain.setDate_of_birth(salsecond.getDate_of_birth());
				salarymain.setDate_of_joining(salsecond.getDate_of_joining());

				salarymain.setSpf_acct_no(salsecond.getSpf_acct_no());
				salarymain.setUrn_no(salsecond.getUrn_no());
				salarymain.setRecord_date(salsecond.getRecord_date());
				salarymain.setSalary_month(salsecond.getSalary_month()); // First setting
				// salarymain.setSalary_month(salsecond.getSalary_month()); // Second setting

				// salarymain.setNo_of_days(salsecond.getNo_of_days());
				salarymain.setNo_of_days(salsecond.getNo_of_days());
				// System.out.println("dfghjklpppppppppppppppppp"+lastDay);
				// salarymain.setDays_paid(salsecond.getDays_paid());
				salarymain.setDays_paid(salsecond.getDays_paid());
				// System.out.println("dfghjklpppppppppppppppppp"+lastDay);
				salarymain.setLoss_of_pay(salsecond.getLoss_of_pay());
				salarymain.setPayment_mode(salsecond.getPayment_mode());
				salarymain.setBank_name(salsecond.getBank_name());
				salarymain.setBank_acct_no(salsecond.getBank_acct_no());
				salarymain.setBasic_pay(salsecond.getBasic_pay());
				salarymain.setHra(salsecond.getHra());
				salarymain.setSpl_allow(salsecond.getSpl_allow());
				salarymain.setMedi_reimb(salsecond.getMedi_reimb());

				salarymain.setConv_allow(salsecond.getConv_allow());
				salarymain.setLunch_allow(salsecond.getLunch_allow());
				salarymain.setEdu_allow(salsecond.getEdu_allow());
				salarymain.setBuss_attire(salsecond.getBuss_attire());
				salarymain.setCar_maint(salsecond.getCar_maint());
				salarymain.setLeave_travel_allow(salsecond.getLeave_travel_allow());
				salarymain.setOutstn_allow(salsecond.getOutstn_allow());
				salarymain.setAnnual_loyal_bonus(salsecond.getAnnual_loyal_bonus());

				salarymain.setOtr_allow(salsecond.getOtr_allow());
				salarymain.setGross_salary(salsecond.getGross_salary());
				salarymain.setSpf(salsecond.getSpf());
				salarymain.setTds(salsecond.getTds());
				salarymain.setProf_tax(salsecond.getProf_tax());
				salarymain.setEsi(salsecond.getEsi());
				salarymain.setRecovery(salsecond.getRecovery());
				salarymain.setOtr_ded(salsecond.getOtr_ded());
				salarymain.setTotal_deductions(salsecond.getTotal_deductions());
				salarymain.setNet_salary(salsecond.getNet_salary());
				salarymain.setDate_of_pay(salsecond.getDate_of_pay());
				salarymain.setCum_tds_fy(salsecond.getCum_tds_fy());
				salarymain.setProv_it(salsecond.getProv_it());
				salarymain.setTax_due(salsecond.getTax_due());
				salarymain.setTax_per_month(salsecond.getTax_per_month());
				salarymain.setCtc_amt(salsecond.getCtc_amt());
				salarymain.setDecl_status(salsecond.getDecl_status());
				salarymain.setCtc_eff_date(salsecond.getCtc_eff_date());
				salarymain.setCtc_end_date(salsecond.getCtc_end_date());
				salarymain.setMobile_no(salsecond.getMobile_no());
				salarymain.setEmail_id(salsecond.getEmail_id());
				salarymain.setRecord_type(salsecond.getRecord_type());
				salarymain.setRecord_srl_no(salsecond.getRecord_srl_no());
				salarymain.setRecord_date(salsecond.getRecord_date());
				// salarymain.setRecord_date(salsecond.getRecord_date());
				// String d[]=(salsecond.getRecord_date().toString()).split(" ");
				// salarymain.setRecord_date(d[0]);
				salarymain.setPay_status(salsecond.getPay_status());
				salarymain.setEntity_flg(salsecond.getEntity_flg());
				salarymain.setDel_flg(salsecond.getDel_flg());
				salarymain.setEntry_time(salsecond.getEntry_time());
				salarymain.setEntry_user(salsecond.getEntry_user());
				salarymain.setModify_flg(salsecond.getModify_flg());
				salarymain.setModify_time(salsecond.getModify_time());
				salarymain.setModify_user(salsecond.getModify_user());
				salarymain.setVerify_time(salsecond.getVerify_time());
				salarymain.setVerify_user(salsecond.getVerify_user());
				salarymain.setRemarks(salsecond.getRemarks());
				salarymain.setAadhar_no(salsecond.getAadhar_no());

				up3.add(salarymain);

				// up3.add(gstoverseas);
			}

			// System.out.println("IIIIIIIIIIIIIIIIIIIIIIIIIIIIII"+up3);
			//
			Paystructurerep.saveAll(up3);

			// gstBtmRep.getInsert(b,a);

			System.out.println("}}}}}}}}}}}}}}}}}}}}}}}}" + up3);
			// int uniqueIdCounter = Integer.parseInt(b);
			// int uniqueIdCounter1 = Integer.parseInt(a);

			// System.out.println(gstRep.getInsert(a,b));
			String msg = "Data swapped Successfully";
			return msg;

		} catch (Exception e) {
			e.printStackTrace();
			return "Error: " + e.getMessage();
		}
	}

	@RequestMapping(value = "account_LedgerPost", method = { RequestMethod.GET, RequestMethod.POST })
	public String accountLedgerPost(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resource_id, Model md, HttpServletRequest rq) {

		String userId = (String) rq.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");

		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");
		} else if (formmode.equals("verify")) {

			md.addAttribute("formmode", "verify");

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");

		} else if (formmode.equals("modify")) {
			md.addAttribute("formmode", "modify");

		}

		return "AccountLedgerPost";
	}

	byte[] setvalue;
	byte[] setImgValue;

	@RequestMapping(value = "fileupload", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String handleFileUpload(@RequestParam("file") MultipartFile file,
			Document_Master_Entity document_Master_Entity) throws IOException {
		// Call service layer to handle file uploads and form data
		// Print the value of 'fileData' to the console

		// System.out.println("Emp Id: " + document_Master_Entity.getEmp_id());

		byte[] byteArray = file.getBytes();
		this.setvalue = byteArray;

		return "success"; // Redirect to upload page after upload
	}

	@RequestMapping(value = "fileupload2", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String handleFileUpload2(@RequestParam("file") MultipartFile file,
			Document_Master_Entity document_Master_Entity) throws IOException {
		// Call service layer to handle file uploads and form data
		// Print the value of 'fileData' to the console

		// System.out.println("Emp Id: " + document_Master_Entity.getEmp_id());

		byte[] byteArray = file.getBytes();
		this.setImgValue = byteArray;

		return "success"; // Redirect to upload page after upload
	}

	@RequestMapping(value = "fileupload1", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String handleFileUpload1(Document_Master_Entity document_Master_Entity) {
		// Call service layer to handle file uploads and form data
		// Print the value of 'fileData' to the console

		System.out.println("Emp Id: " + document_Master_Entity.getEmp_id());
		document_Master_Entity.setDocument(setvalue);
		document_Master_Entity.setDoc_image(setImgValue);

		Document_Master_Repo.save(document_Master_Entity);
		return "success"; // Redirect to upload page after upload
	}

	@RequestMapping(value = "fileuploadoffervalue", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String fileuploadoffervalue(@RequestParam(required = false) String ref,
			CandEvalFormEntity candEvalFormEntity) {
		// Call service layer to handle file uploads and form data
		// Print the value of 'fileData' to the console
		CandEvalFormEntity up = candEvalFormRep.getoffer(ref);
		System.out.println("getRef_no: " + up.getRef_no());
		up.setOffer(setImgoffer);
		// document_Master_Entity.setDoc_image(setImgValue);

		candEvalFormRep.save(up);
		return "success"; // Redirect to upload page after upload
	}

	byte[] setImgoffer;

	@RequestMapping(value = "fileuploadoffer", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String fileuploadoffer(@RequestParam("file") MultipartFile file, @RequestParam(required = false) String ref,
			CandEvalFormEntity candEvalFormEntity) throws IOException {
		// Call service layer to handle file uploads and form data
		// Print the value of 'fileData' to the console
		System.out.println("jjjjjjjjjjjjkkkkkkkkkkkkkkkkkkkkkkk" + ref);
		// System.out.println("Emp Id: " + document_Master_Entity.getEmp_id());
		System.out.println("ghghghggggggggggggggggggg" + file);

		byte[] byteArray = file.getBytes();
		this.setImgoffer = byteArray;

		return "success"; // Redirect to upload page after upload
	}

	@RequestMapping(value = "fileuploadappointmentvalue", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String fileuploadappointmentvalue(@RequestParam(required = false) String ref,
			@RequestParam(required = false) String date, @RequestParam(required = false) String name,
			@RequestParam(required = false) String position,

			CandEvalFormEntity candEvalFormEntity) throws ParseException {
		// Call service layer to handle file uploads and form data
		// Print the value of 'fileData' to the console
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date dates = dateFormat.parse(date);
		System.out.println(date);

		CandEvalFormEntity up = candEvalFormRep.getappointment(ref);
		System.out.println("getRef_no: " + up.getRef_no());
		up.setRef_no(ref);
		up.setCandi_name(name);
		up.setDate_of_appointment_letter(dates);
		System.out.println(date);
		up.setPosition(position);
		System.out.println("getRef_no" + up.getEmail_id());
		up.setAddress(up.getAddress());
		up.setCtc(up.getCtc());
		up.setEmail_id(up.getEmail_id());
		up.setAppointment(setImgappoint1);
		up.setSalarystru(setImgappoint2);
		// document_Master_Entity.setDoc_image(setImgValue);

		candEvalFormRep.save(up);
		return "success"; // Redirect to upload page after upload
	}

	byte[] setImgappoint1;
	byte[] setImgappoint2;

	@RequestMapping(value = "fileuploadappointment", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String fileuploadappointment(@RequestParam("file") MultipartFile file,
			@RequestParam("file1") MultipartFile file1, @RequestParam(required = false) String ref,
			CandEvalFormEntity candEvalFormEntity) throws IOException {
		// Call service layer to handle file uploads and form data
		// Print the value of 'fileData' to the console
		System.out.println("appoint" + ref);
		// System.out.println("Emp Id: " + document_Master_Entity.getEmp_id());
		System.out.println("ment" + file);
		System.out.println("ment" + file1);

		byte[] byteArray = file.getBytes();
		this.setImgappoint1 = byteArray;
		byte[] byteArrays = file1.getBytes();
		this.setImgappoint2 = byteArrays;

		return "success"; // Redirect to upload page after upload
	}

	@PostMapping(value = "fileUploadPOMaster1")
	@ResponseBody
	public String uploadFilePO(@RequestParam("file") MultipartFile file, String screenId,
			@ModelAttribute Assosiate_Profile_Entity Assosiate_Profile_Entity, Model md, HttpServletRequest rq)
			throws FileNotFoundException, SQLException, IOException, NullPointerException {

		System.out.println("the testing   rest controller");

		System.out.println("fileSize" + file.getSize());

		if (file.getSize() < 50000000) {
			String userid = (String) rq.getSession().getAttribute("USERID");
			String msg = projectMasterServices.UploadPO(userid, file, userid, Assosiate_Profile_Entity);
			return msg;
		} else {
			return "File has not been successfully uploaded. Requires less than 128 KB size.";
		}

	}

	@RequestMapping(value = { "/bankexceldownload" }, method = { RequestMethod.GET }, produces = {
			"application/octet-stream" })
	@ResponseBody
	public ResponseEntity<InputStreamResource> bankexceldownload(HttpServletResponse response,
			@RequestParam(required = false) String filetype, @RequestParam(required = false) String a,
			@RequestParam(required = false) String years) throws IOException, SQLException, JRException {
		response.setContentType("application/octet-stream");

		String fileName = "bankFileDownload" + a + ".xlsx";
		File eclFile = this.projectMasterServices.gettdsexcelbatchjob(fileName, filetype, a);
		InputStreamResource resource = new InputStreamResource(new FileInputStream(eclFile));
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=" + fileName);
		return ((BodyBuilder) ResponseEntity.ok().headers(headers)).contentLength(eclFile.length())
				.contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
	}

	@RequestMapping(value = "batchjobemail", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<String> batchjobemail(@RequestParam(required = false) List<String> t, Model md)
			throws SQLException, ParseException, IOException {
		System.out.println("Hi");

		String to = "barath.p@bornfire.in";
		String from = "barath.p@bornfire.in";
		String username = "barath.p@bornfire.in"; // change accordingly
		String password = "Bornfire@123"; // change accordingly
		String host = "sg2plzcpnl491716.prod.sin2.secureserver.net";

		List<String> y = t;
		for (String bb : y) {
			System.out.println(bb);

		}

		System.out.println("sdfghjkl;");

		// Call sendMail method with correct parameters
		sendingmail_batchjob.batchjobsendingmail(from, host, to, username, password, y); // pass from, host,
																							// password, and to

		// Return success response
		return ResponseEntity.status(HttpStatus.OK).body("Email sent successfully");
	}

	@RequestMapping(value = "payslipgeneration", method = RequestMethod.GET)
	@ResponseBody
	public InputStreamResource payslipgeneration(HttpServletResponse response,
			@RequestParam(value = "a", required = false) String a,
			@RequestParam(value = "t", required = false) String t,
			@RequestParam(value = "filetype", required = false) String filetype) throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		System.out.println(a);
		try {
			logger.info("Getting download File :" + a + ", FileType :" + filetype + "");

			File repfile = projectMasterServices.payslip(a, t, filetype);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));
		} catch (JRException e) {
			// Log the error using the logger
			logger.error("Error generating TDS file", e);
			// Optionally, rethrow the exception or handle it as needed
			// throw new YourCustomException("Error generating TDS file", e);
		}

		return resource;
	}

	@Autowired
	com.bornfire.services.Payslipgenerationemail Payslipgenerationemail;

	@RequestMapping(value = "payslipgenerationemail", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<String> payslipgenerationemails(@RequestParam(required = false) String a,
			@RequestParam(required = false) String d, @RequestParam(required = false) List<String> t, Model md)
			throws SQLException, ParseException, IOException {
		System.out.println("Hi");

		String to = null;
		String from = "barath.p@bornfire.in";
		String username = "barath.p@bornfire.in"; // change accordingly
		String password = "Bornfire@123"; // change accordingly
		String host = "sg2plzcpnl491716.prod.sin2.secureserver.net";
		String ref_no = d;
		System.out.println("refno" + ref_no);
		List<String> y = t;
		for (String bb : y) {
			System.out.println(bb);

		}

		System.out.println("sdfghjkl;");

		// Call sendMail method with correct parameters
		Payslipgenerationemail.Payslipgenerationemails(from, host, to, username, password, ref_no, y); // pass from,
																										// host,
		// password, and to

		// Return success response
		return ResponseEntity.status(HttpStatus.OK).body("Email sent successfully");
	}

	@RequestMapping(value = "paypredium", method = RequestMethod.GET)
	@ResponseBody
	public InputStreamResource paypredium(HttpServletResponse response,
			@RequestParam(value = "a", required = false) String a,
			@RequestParam(value = "filetype", required = false) String filetype) throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		System.out.println(a);
		try {
			logger.info("Getting download File :" + a + ", FileType :" + filetype + "");

			File repfile = projectMasterServices.payslippredim(a, filetype);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));
		} catch (JRException e) {
			// Log the error using the logger
			logger.error("Error generating TDS file", e);
			// Optionally, rethrow the exception or handle it as needed
			// throw new YourCustomException("Error generating TDS file", e);
		}

		return resource;
	}

	@PostMapping("/sendapprovalstages")
	@ResponseBody
	public String receiveData(@RequestBody List<Map<String, String>> tableData) {

		System.out.println("entered......");
		String msg = placementServices.sendapprovlstages(tableData);
		return msg;
	}

	@RequestMapping(value = "deletestages", method = { RequestMethod.GET, RequestMethod.POST })
	public String deletestages(@RequestParam(required = false) String formmode) {
		String msg = placementServices.deletestages();
		return msg;

	}

	@RequestMapping(value = "salary_parameters", method = { RequestMethod.GET, RequestMethod.POST })
	public String salary_parameters(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) BigDecimal srl_no, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");

		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			md.addAttribute("formmode", "list");
			List<salary_parameter> parameters = salary_parameter_Rep.getdata();
			md.addAttribute("getdatas", parameters);

		} else if (formmode.equals("add")) {
			md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			md.addAttribute("formmode", formmode);
			List<String> group = Taxation_parameter_rep.getDistinctCountries();
			md.addAttribute("countryList", group);

		} else if (formmode.equals("edit")) {
			md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			salary_parameter paramview = salary_parameter_Rep.getview(srl_no);
			md.addAttribute("paramview", paramview);
			md.addAttribute("formmode", "edit");
			List<String> group = Taxation_parameter_rep.getDistinctCountries();
			md.addAttribute("countryList", group);

		} else if (formmode.equals("view")) {
			md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			salary_parameter paramview = salary_parameter_Rep.getview(srl_no);
			md.addAttribute("paramview", paramview);
			md.addAttribute("formmode", "view");
		} else if (formmode.equals("verify")) {
			md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			salary_parameter paramview = salary_parameter_Rep.getview(srl_no);
			md.addAttribute("paramview", paramview);
			md.addAttribute("formmode", "verify");
		} else if (formmode.equals("delete")) {
			md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			salary_parameter paramview = salary_parameter_Rep.getview(srl_no);
			md.addAttribute("paramview", paramview);
			md.addAttribute("formmode", "delete");
		}

		return "SalaryParameters";
	}

	/*-----ERP_SALARY  PARAMETER---*/
	@RequestMapping(value = "salary_parameters_ERP", method = { RequestMethod.GET, RequestMethod.POST })
	public String salary_parameters_ERP(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) BigDecimal srl_no, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");

		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			md.addAttribute("formmode", "list");
			List<salary_parameter> parameters = salary_parameter_Rep.getdata();
			md.addAttribute("getdatas", parameters);
			List<String> OrgIds = TSK_OrganizationMasterReps.get_org();
			md.addAttribute("OrgIds", OrgIds);

		} else if (formmode.equals("add")) {
			md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			md.addAttribute("formmode", formmode);
			List<String> group = Taxation_parameter_rep.getDistinctCountries();
			md.addAttribute("countryList", group);
			/*
			 * List<String> list = Branch_reps.find_branch_id();
			 * md.addAttribute("branchIds", list);
			 */
			List<String> OrgIds = TSK_OrganizationMasterReps.get_org();
			md.addAttribute("OrgIds", OrgIds);

		} else if (formmode.equals("edit")) {
			md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			salary_parameter paramview = salary_parameter_Rep.getview(srl_no);
			md.addAttribute("paramview", paramview);
			md.addAttribute("formmode", "edit");
			List<String> group = Taxation_parameter_rep.getDistinctCountries();
			md.addAttribute("countryList", group);
			/*
			 * List<String> list = Branch_reps.find_branch_id();
			 * md.addAttribute("branchIds", list);
			 */
			
			List<String> OrgIds = TSK_OrganizationMasterReps.get_org();
			md.addAttribute("OrgIds", OrgIds);

		} else if (formmode.equals("view")) {
			md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			salary_parameter paramview = salary_parameter_Rep.getview(srl_no);
			md.addAttribute("paramview", paramview);
			md.addAttribute("formmode", "view");
		} else if (formmode.equals("verify")) {
			md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			salary_parameter paramview = salary_parameter_Rep.getview(srl_no);
			md.addAttribute("paramview", paramview);
			md.addAttribute("formmode", "verify");
		} else if (formmode.equals("delete")) {
			md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			salary_parameter paramview = salary_parameter_Rep.getview(srl_no);
			md.addAttribute("paramview", paramview);
			md.addAttribute("formmode", "delete");
		}

		return "ERPSaleryparameter";
	}

	@RequestMapping(value = "add_salary_parameters", method = RequestMethod.POST)
	@ResponseBody
	public String add_salary_parameters(@RequestParam(required = false) String formmode,
			@ModelAttribute salary_parameter salary_parameter)
			throws NoSuchAlgorithmException, InvalidKeySpecException {

		String msg = "";
		if (formmode.equals("add")) {
			System.out.println("enter the add");
			msg = placementServices.addparam(salary_parameter, formmode);
		} else if (formmode.equals("edit")) {
			System.out.println("enter the edit");
			msg = placementServices.addparam(salary_parameter, formmode);
		} else if (formmode.equals("verify")) {
			System.out.println("enter the verify");
			msg = placementServices.addparam(salary_parameter, formmode);
		} else if (formmode.equals("delete")) {
			System.out.println("enter the delete");
			msg = placementServices.addparam(salary_parameter, formmode);
		}
		return msg;
	}

	@RequestMapping(value = "getcoutriesdata", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Taxation_parameters> getcoutriesdata(@RequestParam(required = false) String selectedgroup, Model md) {

		System.out.println("enter the controll" + selectedgroup);
		List<Taxation_parameters> countryList = Taxation_parameter_rep.getcountryrows(selectedgroup);

		return countryList;
	}

	@RequestMapping(value = "Taxation_parameters", method = { RequestMethod.GET, RequestMethod.POST })
	public String Taxation_parameters(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String emp_no, Model md, HttpServletRequest rq,
			@RequestParam(required = false) String group_name) {
		String userId = (String) rq.getSession().getAttribute("USERID");
		if (formmode == null || formmode.equals("add")) {
			md.addAttribute("formmode", "add");
			List<Taxation_parameters> parameters = Taxation_parameter_rep.getdata();
			md.addAttribute("getdatas", parameters);
			System.out.println("enter the add");
			md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		} else if (formmode.equals("edit")) {
			md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			List<Taxation_parameters> paramview = Taxation_parameter_rep.getview(group_name);
			md.addAttribute("paramview", paramview);
			md.addAttribute("formmode", "edit");
		} else if (formmode.equals("verify")) {
			md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			List<Taxation_parameters> paramview = Taxation_parameter_rep.getview(group_name);
			md.addAttribute("paramview", paramview);
			md.addAttribute("formmode", "verify");
		} else if (formmode.equals("delete")) {
			md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			List<Taxation_parameters> paramview = Taxation_parameter_rep.getview(group_name);
			md.addAttribute("paramview", paramview);
			md.addAttribute("formmode", "delete");
		} else if (formmode.equals("view")) {
			md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			List<Taxation_parameters> paramview = Taxation_parameter_rep.getview(group_name);
			md.addAttribute("paramview", paramview);
			md.addAttribute("formmode", "view");
		}
		return "TaxationParameters";
	}

	@PostMapping("/add_Tax_param")
	@Transactional
	public ResponseEntity<String> addTaxParam(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) BigDecimal srl_no,
			@RequestBody List<Taxation_parameters> taxationParametersList) {
		ResponseEntity<String> msg = null;
		if ("add".equals(formmode)) {
			msg = placementServices.add_Tax_param(taxationParametersList, formmode);

		} else if ("edit".equals(formmode)) {
			msg = placementServices.add_Tax_param(taxationParametersList, formmode);
			System.out.println("the msg :" + msg);
		}
		return msg;
	}

	@RequestMapping(value = "del_row_param", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String del_row_param(@RequestParam(required = false) BigDecimal srl_no,
			@RequestParam(required = false) String formmode, @RequestParam(required = false) String group_name) {

		String msg = "";
		if (formmode == null) {
			msg = placementServices.deleteRow(srl_no);
		} else if (formmode.equals("verify")) {
			String h = group_name.substring(1);
			System.out.println("enter the verify:" + h);
			msg = placementServices.ver_rows(h, formmode);
		} else if (formmode.equals("delete")) {
			String h = group_name.substring(1);
			System.out.println("enter the verify:" + group_name);
			msg = placementServices.del_rows(h, formmode);
		}
		return msg;
	}

	@RequestMapping(value = "Performance_Evaluation", method = { RequestMethod.GET, RequestMethod.POST })
	public String Performance_Evaluation(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String ref_no, Model md, HttpServletRequest rq) {

		String userId = (String) rq.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			md.addAttribute("del", perfomance_evaluation_rep.getCVFList());
			md.addAttribute("formmode", "list");

			md.addAttribute("dell", perfomance_evaluation_rep.getCVFList1(userId));
		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");
			List<String> list = resourceMasterRepo.getalist();
			md.addAttribute("userlist", list);
		} else if (formmode.equals("view")) {
			md.addAttribute("cvfview", perfomance_evaluation_rep.getCVFform(ref_no));
			md.addAttribute("formmode", "view");

			LocalDate currentDate = LocalDate.now();
			System.out.println("The size:" + currentDate);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String formattedDate = currentDate.format(formatter);
			md.addAttribute("datee", formattedDate);

			md.addAttribute("submit", userId);

		}
		return "Performance_Evaluation";
	}

	@RequestMapping(value = "Per_Eva_get_Name", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResourceMaster Per_Eva_get_Name(@RequestParam(required = false) String empId, Model md) {

		System.out.println("enter the controll" + empId);
		ResourceMaster empname = resourceMasterRepo.getname(empId);
		return empname;
	}

	@RequestMapping(value = "Per_Eva_submit", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String Per_Eva_submit(@ModelAttribute perfomance_evaluation perfomance_evaluation,
			@RequestParam(required = false) String formmode, @RequestParam(required = false) String ref_no, Model md,
			HttpServletRequest rq) {
		String msg = "";
		String userId = (String) rq.getSession().getAttribute("USERID");

		if (formmode.equals("add")) {
			msg = placementServices.evaluation(perfomance_evaluation, userId, "add");

		} else if (formmode.equals("modify")) {
			System.out.println("enter the controll");
			msg = placementServices.evaluation(perfomance_evaluation, ref_no, "modify");

		}
		return msg;
	}

	@RequestMapping(value = "orientationtraining", method = { RequestMethod.GET, RequestMethod.POST })
	public String orientationtraining(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String ref_no, @RequestParam(required = false) String userid,
			@RequestParam(required = false) String doc_id, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest rq)
			throws ParseException {

		String userId = (String) rq.getSession().getAttribute("USERID");
		String userNAME = (String) rq.getSession().getAttribute("USERNAME");
		System.out.println("name :" + userNAME);
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
			// md.addAttribute("del", perfomance_evaluation_rep.getCVFList());
			// md.addAttribute("del",trainingRep.gettraininglist());
			md.addAttribute("formmode", "list");
			md.addAttribute("traininglist", Hrms_TrainingRep.gelist());

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", "add");

		} else if (formmode.equals("views")) {
			md.addAttribute("formmode", "views");
			md.addAttribute("userid", userId);
			md.addAttribute("username", userNAME);
			List<String> list = resourceMasterRepo.getalist();
			md.addAttribute("userlist", list);
			md.addAttribute("TNAME", ref_no);

		} else if (formmode.equals("schedule")) {
			md.addAttribute("formmode", "schedule");
			md.addAttribute("userid", userId);
			md.addAttribute("username", userNAME);
			List<String> list = resourceMasterRepo.getalist();
			md.addAttribute("userlist", list);
			md.addAttribute("TNAME", ref_no);
		}

		return "OrientationTraining";
	}

	@RequestMapping(value = "Trainingadd", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String Training(@RequestParam(required = false) MultipartFile file,
			@RequestParam(required = false) String formmode, String adm_id, String adm_name, String trainee_name,
			String trainee_dept, String train_name, String train_desc, String startdate, String enddate, String emp_id,
			String emp_name, String emp_email, String emp_role, String train_flg) throws IOException {

		System.out.println("AM" + adm_id);
		byte[] byteArray = file.getBytes();
		String name = file.getOriginalFilename();
		String type = file.getContentType();
		String msg = "";
		try {
			msg = adminOperServices.addtrainings(formmode, adm_id, adm_name, trainee_name, trainee_dept, train_name,
					train_desc, startdate, enddate, emp_id, emp_name, emp_email, emp_role, train_flg, byteArray, name,
					type);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}

	@RequestMapping(value = "training_mail", method = RequestMethod.POST)
	@ResponseBody
	public String training_mail(@RequestParam(required = false) String email, String adm_name, String adm_id,
			String trainee_name, String emb_name, HttpServletRequest req) {
		System.out.println("email :" + email);
		System.out.println("adm_name :" + adm_name);
		System.out.println("adm_id :" + adm_id);
		System.out.println("trainee_name :" + trainee_name);
		String from = "prasanth.m@bornfire.in";
		String usernamelogin = "prasanth.m@bornfire.in"; // change accordingly
		String password = "MiddleEast#123"; // change accordingly
		String hostt = "sg2plzcpnl491716.prod.sin2.secureserver.net";
		// String ref_no = d;
		String mail = Mail.trainingmail(adm_id, adm_name, email, usernamelogin, password, from, hostt, trainee_name,
				emb_name);

		return mail;
	}

	@RequestMapping(value = "sale", method = { RequestMethod.GET, RequestMethod.POST })
	public String sale(Model md, HttpServletRequest req) {
		String loginuserid = (String) req.getSession().getAttribute("USERID");

		String userId = (String) req.getSession().getAttribute("USERID");
		String userNAME = (String) req.getSession().getAttribute("USERNAME");
		System.out.println("name :" + userNAME);
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		List<String> list = PROCESS_rep.findAllmakeProductNames();
		md.addAttribute("productList", list);
		int saleid = bhmsInvProductSaleMasterrep.getUniqueSalesId();
		md.addAttribute("sale_id", saleid + 1);

		return "BHMSInvProductSale";
	}

	@RequestMapping(value = "addMedicineSaleReq", method = RequestMethod.POST)
	@ResponseBody
	public String addMedicineSaleReq(@RequestBody List<BhmsInvProductSaleMasterTable> saleMasterDate, Model md,
			HttpServletRequest rq) {

		String response = BHMS_services.createSaleMedicine(saleMasterDate);

		return response;

	}

	@RequestMapping(value = "saleReportDet", method = { RequestMethod.GET, RequestMethod.POST })
	public String saleReportDet(@RequestParam(value = "sid", required = false) String sid, Model md,
			HttpServletRequest req) {
		String loginuserid = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));
		md.addAttribute("menu", "BTMHeaderMenu");

		List<BhmsInvProductSaleMasterTable> list = BHMS_services.getSaleReport(sid);

		if (list.size() > 0) {
			md.addAttribute("invoice_no", list.get(0).getSale_id());
			md.addAttribute("cust_name", list.get(0).getCust_name());
			md.addAttribute("date_of_sale", list.get(0).getDate_of_sale());
			md.addAttribute("total", list.get(0).getTotal());
			md.addAttribute("payment_by", list.get(0).getPayment_by());
			md.addAttribute("remaining_amount", list.get(0).getRemaining_amount());
			md.addAttribute("balance_amount", list.get(0).getBalance_amount());
			md.addAttribute("given_amount", list.get(0).getGiven_amount());

		}

		md.addAttribute("saleList", BHMS_services.getSaleReport(sid));

		return "BHMSInvSaleDetReport";
	}

	@RequestMapping(value = "deletestockLock", method = RequestMethod.POST)
	@ResponseBody
	public String deletestockLock(@RequestParam(value = "product_name", required = true) String product_name,
			@RequestParam(value = "category_name", required = true) String category_name,

			@RequestParam(value = "qty", required = true) String qty,
			@RequestParam(value = "batch", required = true) String batch, HttpServletRequest req) {

		String user_id = (String) req.getSession().getAttribute("USERID");

		String response = BHMS_services.deletestockLock(user_id, product_name, category_name, qty, batch);

		return response;

	}

	@RequestMapping(value = "newstockLock", method = RequestMethod.GET)
	@ResponseBody
	public String newstockLock(@RequestParam(value = "product_name", required = true) String product_name,
			@RequestParam(value = "category_name", required = true) String category_name,

			@RequestParam(value = "qty", required = true) String qty,
			@RequestParam(value = "batch", required = true) String batch, HttpServletRequest req) {

		String user_id = (String) req.getSession().getAttribute("USERID");

		String response = BHMS_services.newstockLock(user_id, product_name, category_name, qty, batch);

		return response;

	}

	@RequestMapping(value = "getStocksList", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Object[]> getStocksList(@RequestParam(value = "product_id", required = true) String product_id) {
		List<Object[]> response = BHMS_services.getStocksListss(product_id);
		System.out.println(response);
		return response;

	}

	@RequestMapping(value = "getStocksalls", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Object[]> getStocksalls(@RequestParam(value = "product_id", required = true) String product_id) {
		List<Object[]> response = PROCESS_rep.findffff(product_id);
		System.out.println(response);
		return response;

	}

	@RequestMapping(value = "getStocksss", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Object[]> getStocksss(@RequestParam(value = "product_id", required = true) String product_id) {
		List<Object[]> response = BHMS_services.getStocksList(product_id);
		System.out.println("the response" + response);
		return response;

	}

	@RequestMapping(value = "getStockpo", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Purchase_Order_Entity> getStockpo(@RequestParam(value = "item", required = true) String item) {
		List<Purchase_Order_Entity> response = po_repo.getpodetails(item);
		System.out.println("the response" + response);
		return response;

	}

	@RequestMapping(value = "getpkgtypeList", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Object[]> getpkgtypeList(@RequestParam(value = "product_id", required = true) String product_name,
			String batch) {
		List<Object[]> response = BHMS_services.getStockTypeList(product_name, batch);
		// List<Object[]> response = PROCESS_rep.findallstocktype(product_name, batch);
		System.out.println(response);
		return response;

	}

	@RequestMapping(value = "getpkgtypeListss", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Object[]> getpkgtypeListss(@RequestParam(value = "product_id", required = true) String product_name,
			String batch) {
		// List<Object[]> response = BHMS_services.getStockTypeList(product_name,
		// batch);
		List<Object[]> response = PROCESS_rep.findallstocktype(product_name, batch);
		System.out.println(response);
		return response;

	}

	@RequestMapping(value = "makesListss", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Object[]> makesListss(@RequestParam(value = "product_id", required = true) String product_name,
			String batch) {
		List<Object[]> response = PROCESS_rep.findallmakes(product_name, batch);
		System.out.println(response);
		return response;

	}

	@RequestMapping(value = "getCurrentStockfn", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Object[]> getCurrentStock(@RequestParam(value = "product_id", required = true) String product_name,
			String batch) {
		List<Object[]> response = BHMS_services.getProductStockCurrentfn(product_name, batch);
		// List<Object[]> response = PROCESS_rep.productStockCurrent(product_name,
		// batch);

		System.out.println(response);

		List<BHMSInventoryProductStockCurrent> list = new ArrayList<BHMSInventoryProductStockCurrent>();

		return response;
	}

	@RequestMapping(value = "getCurrentStockfnss", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Object[]> getCurrentStockfnss(@RequestParam(value = "product_id", required = true) String product_name,
			String batch) {
		// List<Object[]> response =
		// BHMS_services.getProductStockCurrentfn(product_name, batch);
		List<Object[]> response = PROCESS_rep.productStockCurrent(product_name, batch);

		System.out.println(response);

		List<BHMSInventoryProductStockCurrent> list = new ArrayList<BHMSInventoryProductStockCurrent>();

		return response;
	}

	@RequestMapping(value = "getcert", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Object[]> getcert(@RequestParam(value = "product_id", required = true) String product_name,
			@RequestParam(value = "batch", required = true) String batch) {
		// List<Object[]> response =
		// BHMS_services.getProductStockCurrentfn(product_name, batch);
		List<Object[]> response = PROCESS_rep.productStockCurrentll(product_name, batch);

		System.out.println(response);

		return response;
	}

	@RequestMapping(value = "purchaseing", method = { RequestMethod.GET, RequestMethod.POST })
	public String purchaseing(Model md, HttpServletRequest req) {
		String loginuserid = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));
		List<Object[]> stockList = BHMSInventoryProductStockrepo.productStockList();
		md.addAttribute("stockList", stockList);
		int a = stockList.size() + 1;
		String id = "ID" + a;
		md.addAttribute("ce_id", id);

		return "purchase.html";
	}

	@RequestMapping(value = "fileuploadInventory", method = RequestMethod.POST)
	@ResponseBody
	public String uploadDoc(@RequestParam("filedatas") MultipartFile file,
			@ModelAttribute BHMSInventoryProductStock stockhistorymaster,
			@RequestParam(value = "expdate", required = false) String expdate,
			@RequestParam(value = "purdate", required = false) String purdate, HttpServletRequest rq) {

		System.out.println("expdate is: " + expdate);
		System.out.println("purdate is: " + purdate);
		byte[] bytes = null;
		String response;
		try {
			if (file != null && !file.isEmpty()) {

				System.out.println("File name: " + file.getOriginalFilename());
				System.out.println("File size: " + file.getSize());
				bytes = file.getBytes();
			}

			// Parse dates
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			Date exppdate = dateformat.parse(expdate);
			Date purrdate = dateformat.parse(purdate);

			stockhistorymaster.setEXPIRY_DATE(exppdate);
			stockhistorymaster.setPURCHASE_DATE(purrdate);

			// Save stock history
			response = BHMS_services.stockHistory(stockhistorymaster, exppdate, purrdate, bytes);
		} catch (Exception e) {
			response = "Error: " + e.getMessage();
		}
		return response;
	}

	@RequestMapping(value = "process", method = { RequestMethod.GET, RequestMethod.POST })
	public String process(Model md, HttpServletRequest req, @RequestParam(required = false) String formmode) {
		String loginuserid = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));

		if (formmode == null) {
			md.addAttribute("formmode", null);
			List<Object[]> stockList = PROCESS_rep.findUnderProcess();
			md.addAttribute("stockLists", stockList);
			List<BHMSInventoryProductStockCurrent> productList = BHMSInventoryProductStockCurrentrepo
					.productStockList();
			md.addAttribute("productList", productList);
			md.addAttribute("woList", WorkOrderRep.getwolists());

			List<Object[]> List = BHMSInventoryProductStockrepo.productStockList();
			int a = List.size() + 1;
			String id = "ID" + a;
			md.addAttribute("ce_id", id);

			List<String> makelist = New_product_rep.getMakeProductNames();
			md.addAttribute("makelist", makelist);
		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");
			List<Object[]> stockList = New_product_rep.findUnderProcess();
			md.addAttribute("stockLists", stockList);
			List<BHMSInventoryProductStockCurrent> productList = BHMSInventoryProductStockCurrentrepo
					.productStockList();
			md.addAttribute("productList", productList);
			List<Object[]> List = BHMSInventoryProductStockrepo.productStockList();
			int a = List.size() + 1;
			String id = "ID" + a;
			md.addAttribute("ce_id", id);

		}
		return "processing.html";
	}

	@RequestMapping(value = "getmakeproduct", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Object[]> getmakeproduct(@RequestParam(value = "makesname", required = true) String makesname) {
		List<Object[]> response = New_product_rep.findAllCustom(makesname);
		System.out.println(response);
		return response;

	}

	@RequestMapping(value = "getWO", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public WorkOrder getWO(@RequestParam(value = "woid", required = true) String woid) {
		WorkOrder up = WorkOrderRep.getinquirywolist(woid);
		return up;

	}

	@RequestMapping(value = "getWOname", method = RequestMethod.GET)
	@ResponseBody
	public WorkOrder getWOname(@RequestParam(required = false) String refid) {
		System.out.println("the controller for pp" + refid);
		WorkOrder up = WorkOrderRep.getinquirywolist11(refid);
		System.out.println("the size" + up.getWorkId());

		return up;

	}

	@RequestMapping(value = "delete_product", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String delete_product(@RequestParam(value = "makeproductname", required = true) String makeproductname) {
		String msg;
		try {
			New_product_rep.deleteByMakeProductName(makeproductname); // This now runs inside a transaction
			msg = "Deleted successfully!";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Error deleting products with makeproductname: " + makeproductname;
		}
		return msg;
	}

	@PostMapping("/add_processes")
	@Transactional
	public ResponseEntity<String> addTaxParam(@RequestParam(required = false) String formmode, String woid,
			@RequestBody List<PROCESS_ENTITY> processEntityList) {
		ResponseEntity<String> msg = null;
		try {
			if ("add".equals(formmode) || "edit".equals(formmode)) {
				System.out.println("Form mode: " + formmode);
				System.out.println("Received processEntityList: " + processEntityList);
				msg = BHMS_services.add_Tax_param(processEntityList, formmode, woid);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
		return msg;
	}

	@PostMapping("/add_new_product")
	@Transactional
	public ResponseEntity<String> add_new_product(@RequestParam(required = false) String formmode,
			@RequestBody List<New_product_entity> New_product_entity) {
		ResponseEntity<String> msg = null;
		try {
			if ("add".equals(formmode) || "edit".equals(formmode)) {
				System.out.println("Form mode: " + formmode);
				System.out.println("Received processEntityList: " + New_product_entity);
				msg = BHMS_services.addNewProduct(New_product_entity, formmode);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
		return msg;
	}

	@RequestMapping(value = "finalprocess", method = { RequestMethod.GET, RequestMethod.POST })
	public String finalprocess(Model md, HttpServletRequest req) {
		String loginuserid = (String) req.getSession().getAttribute("USERID");

		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));
		// md.addAttribute("role_menu", userProfileDao.getUser(loginuserid));

		List<Object[]> stockList = PROCESS_rep.findUnderProcess();
		md.addAttribute("stockLists", stockList);
		List<Object[]> comlist = PROCESS_rep.completedProcess();
		md.addAttribute("comlist", comlist);

		List<Object[]> rejlist = PROCESS_rep.Rejectprocess();
		md.addAttribute("rejlist", rejlist);

		List<Object[]> list = BHMSInventoryProductStockrepo.productStockList();
		int a = list.size() + 1;
		String id = "ID" + a;
		md.addAttribute("ce_id", id);

		return "final_process.html";
	}

	@RequestMapping(value = "completion", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String completion(@RequestParam(value = "id", required = false) String ref_id,
			@RequestParam(value = "qty", required = false) String qty) {
		String msg = null;
		try {
			System.out.println("The id is : " + ref_id + "quantity is: " + qty);
			msg = BHMS_services.completion(ref_id, qty);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	@RequestMapping(value = "Rejection", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String Rejection(@RequestParam(value = "id", required = false) String ref_id) {
		String msg = null;
		try {
			System.out.println("The id is : " + ref_id);
			msg = BHMS_services.rejection(ref_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	@GetMapping("/download/{ID}")
	public ResponseEntity<ByteArrayResource> downloadFile1(@PathVariable("ID") String ID) {
		BHMSInventoryProductStock document = BHMSInventoryProductStockrepo.findAllCustomUnit(ID);
		if (document != null) {
			byte[] documentContent = document.getFiledata();
			ByteArrayResource resource = new ByteArrayResource(documentContent);
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"download.png\"")
					.contentType(MediaType.parseMediaType("image/png")).contentLength(documentContent.length)
					.body(resource);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(value = "stockHistory", method = { RequestMethod.GET, RequestMethod.POST })
	public String stockHistory(Model md, HttpServletRequest req) {
		String loginuserid = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));
		// md.addAttribute("role_menu", userProfileDao.getUser(loginuserid));

		List<Object[]> stockList = BHMSInventoryProductStockrepo.productStockList();
		md.addAttribute("stockList", stockList);
		int a = stockList.size() + 1;
		String id = "ID" + a;
		md.addAttribute("ce_id", id);

		return "BHMSInvStockHistory";
	}

	@RequestMapping(value = "currentStock", method = { RequestMethod.GET, RequestMethod.POST })
	public String currentStock(@RequestParam(value = "mode", required = false) String mode, Model md,
			HttpServletRequest req) {
		String loginuserid = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));
		// md.addAttribute("role_menu", userProfileDao.getUser(loginuserid));
		List<Object[]> stockList = BHMSInventoryProductStockCurrentrepo.productStockCurrentList();
		md.addAttribute("stockList", stockList);
		List<BHMSInventoryProductStockCurrent> list = BHMSInventoryProductStockCurrentrepo.productStockList();
		md.addAttribute("productList", list);

		if (mode == null) {
			md.addAttribute("mode", "check");
		}
		return "BHMSInvStockMaster";
	}

	@RequestMapping(value = "/getCurrentStockUsingProductName", method = { RequestMethod.GET })
	public List<BHMSInventoryProductStockCurrent> getCurrentStockUsingProductName(
			@RequestParam(value = "product_name", required = true) String product_name) {
		List<BHMSInventoryProductStockCurrent> response = BHMS_services.getStockUnitsLists(product_name);
		System.out.println(response);
		return response;

	}

	@RequestMapping(value = "BHMSInvStockMasterEdit", method = { RequestMethod.GET, RequestMethod.POST })
	public String BHMSInvStockMasterEdit(@RequestParam(value = "id", required = false) String id, Model md,
			HttpServletRequest req) {
		String loginuserid = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("role_menu", userProfileDao.getUser(loginuserid));
		md.addAttribute("ceData", BHMS_services.getstockeditt(id));
		// List<BHMSCommonExpensesEntity> list =
		// inventoryMgmtService.getCommonExpensesList();
		// System.out.println(inventoryMgmtService.getstockeditt(id));
		// System.out.println(inventoryMgmtService.getMedicalExpense(billid));
		return "BHMSInvStockMasterEdit.html";
	}

	@RequestMapping(value = "BHMSInvStockMasterEditReq", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String BHMSInvStockMasterEditReq(@RequestParam(value = "ID", required = false) String id,
			@ModelAttribute BHMSInventoryProductStockCurrent meMaster, Model md, HttpServletRequest req) {
		String loginuserid = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("role_menu", userProfileDao.getUser(loginuserid));
		// String user_id = (String) req.getSession().getAttribute("USERID");
		String response = BHMS_services.updateStockCurrent(meMaster, id);

		System.out.println("The response is " + response);
		return response;
	}

	@RequestMapping(value = "BHMSDaytoDayBilling", method = RequestMethod.GET)
	public String BHMSDaytoDayBilling(@RequestParam(value = "mode", required = false) String mode,
			@RequestParam(value = "prn", required = false) String prn, HttpServletRequest hs, Model model) {

		String loginuserid = (String) hs.getSession().getAttribute("USERID");
		model.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));
		// model.addAttribute("role_menu", userProfileDao.getUser(loginuserid));
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String datep = dateFormat.format(currentDate);
		System.out.println(currentDate);
		model.addAttribute("mode", "check");

		model.addAttribute("dailyRegAmount", adminOperServices.getDayBillingInquiries(datep));
		if (mode == null) {
			model.addAttribute("mode", "check");

		}

		return "BHMSDaytoDayBilling.html";
	}

	@RequestMapping(value = "saleReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String saleReport(@RequestParam(value = "mode", required = false) String mode,
			@RequestParam(value = "date_of_sale", required = false) String date_of_sale, Model md,
			HttpServletRequest req, Model model) {
		String loginuserid = (String) req.getSession().getAttribute("USERID");
		model.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));
		// md.addAttribute("role_menu", userProfileDao.getUser(loginuserid));

		md.addAttribute("menu", "Sale Report");

		if (date_of_sale == null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			date_of_sale = sdf.format(new Date());

			md.addAttribute("saleList", adminOperServices.getSaleList(date_of_sale));
		} else {
			md.addAttribute("saleList", adminOperServices.getSaleList(date_of_sale));
		}

		model.addAttribute("mode", "check");

		if (mode == null) {
			model.addAttribute("mode", "check");
		}

		return "BHMSInvSaleReport";
	}

	@RequestMapping(value = "singleStockHistory", method = RequestMethod.POST)
	@ResponseBody
	public String singleStockHistory(@ModelAttribute BHMSInventoryProductStock stockhistorymaster,
			@RequestParam(value = "expdate", required = false) String expdate,
			@RequestParam(value = "purdate", required = false) String purdate, Model md, HttpServletRequest req) {
		String loginuserid = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("role_menu", userProfileDao.getUser(loginuserid));
		// String user_id = (String) req.getSession().getAttribute("USERID");
		/*
		 * int num =bhmsInvCgyMasterRep.srlnum(); System.out.println(num+"srl123");
		 */

		String response = BHMS_services.stockHistory(stockhistorymaster, expdate, purdate);
		return response;

	}

	@RequestMapping(value = "deepstorecurrent", method = RequestMethod.POST)
	@ResponseBody
	public String deepstorecurrent(@ModelAttribute BHMSInventoryProductStockCurrent stockcurrentmaster, Model md,
			@RequestParam(value = "expdate", required = false) String expdate, HttpServletRequest req) {
		String loginuserid = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("role_menu", userProfileDao.getUser(loginuserid));
		// String user_id = (String) req.getSession().getAttribute("USERID");

		String response = BHMS_services.currentStock(stockcurrentmaster, expdate);
		return response;

	}

	@RequestMapping("favicon.ico")
	@ResponseBody
	void favicon() {
		// No-op handler to ignore favicon.ico requests
	}

	@RequestMapping(value = "history_billing", method = { RequestMethod.GET, RequestMethod.POST })
	public String history_billing(HttpServletRequest req, Model model,
			@RequestParam(value = "mode", required = false) String mode) {

		String loginuserid = (String) req.getSession().getAttribute("USERID");
		model.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));

		model.addAttribute("mode", "check");
		List<BhmsInvProductSaleMasterTable> list = bhmsInvProductSaleMasterrep.findAllUniqueSaleIds();
		model.addAttribute("stockList", list);

		return "Medical_history.html";

	}

	@RequestMapping(value = "gj", method = { RequestMethod.GET, RequestMethod.POST })
	public String GL(HttpServletRequest req, @RequestParam(required = false) String formmode, Model model, String type,
			@RequestParam(required = false) String id, @RequestParam(value = "mode", required = false) String mode,
			@RequestParam(required = false) String order_type) {

		String loginuserid = (String) req.getSession().getAttribute("USERID");

		String userId = (String) req.getSession().getAttribute("USERID");
		String userNAME = (String) req.getSession().getAttribute("USERNAME");
		System.out.println("name :" + userNAME);
		model.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		model.addAttribute("formmode", "add");

		if (formmode == null || formmode.equals("add")) {

			model.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));
			model.addAttribute("currentDate", LocalDate.now());
			List<Erp_ChartOfAccounts> allaccount = Erp_ChartOfAccountsRep.findschild();
			model.addAttribute("liststrn", allaccount);
			BigInteger tranid = capitaltransrep.gettrnnumber();
			if (tranid.compareTo(BigInteger.ONE) > 0) {
				tranid = tranid.add(BigInteger.ONE);
			}

			model.addAttribute("TranID", "TRAN000" + tranid);

		}

		else if (formmode.equals("list")) {

			model.addAttribute("formmode", "list");
			List<CapitalTrans> list = capitaltransrep.getCategorylist();
			System.out.println("length=" + list.size());
			model.addAttribute("liststrn", list);
		}

		else if (formmode.equals("view")) {
			model.addAttribute("formmode", "view");
			General_journal_entity list = General_journal_rep.findbyid(Integer.valueOf(id));
			model.addAttribute("list", list);
		} else if (formmode.equals("verify")) {
			model.addAttribute("formmode", "verify");
			General_journal_entity list = General_journal_rep.findbyid(Integer.valueOf(id));
			model.addAttribute("list", list);
		} else if (formmode.equals("delete")) {
			model.addAttribute("formmode", "delete");
			General_journal_entity list = General_journal_rep.findbyid(Integer.valueOf(id));
			model.addAttribute("list", list);
		} else if (formmode.equals("edit")) {
			model.addAttribute("formmode", "edit");
			General_journal_entity list = General_journal_rep.findbyid(Integer.valueOf(id));
			model.addAttribute("list", list);
			List<BHMSInventoryProductStockCurrent> productList = BHMSInventoryProductStockCurrentrepo
					.productStockList();
			model.addAttribute("productList", productList);
			model.addAttribute("currentDate", LocalDate.now());
		} else if (formmode.equals("workorder")) {
			model.addAttribute("formmode", "workorder");
			model.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));
			/*
			 * List<BHMSInventoryProductStockCurrent> productList =
			 * BHMSInventoryProductStockCurrentrepo .productStockList();
			 */
			// model.addAttribute("productList", productList);
			model.addAttribute("workitem", WorkOrderRep.Woname());

			model.addAttribute("currentDate", LocalDate.now());
		}

		return "General_Journal.html";

	}

	@RequestMapping(value = "getamount", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Object[]> getamount(@RequestParam(value = "product_id", required = true) String product_name,
			String batch) {
		List<Object[]> response = BHMSInventoryProductStockrepo.amountget(product_name, batch);
		System.out.println(response);
		return response;
	}

	@RequestMapping(value = "gjAdd", method = { RequestMethod.POST })
	@ResponseBody
	public String gjAdd(@ModelAttribute General_journal_entity generalJournalEntity, HttpServletRequest req) {
		System.out.println("Received Product Name: " + generalJournalEntity.getProductName());

		String loginuserid = (String) req.getSession().getAttribute("USERID");

		return BHMS_services.addgj(generalJournalEntity, loginuserid);
	}

	/*-----workordertojournal*/
	@RequestMapping(value = "Addworkoredrtojurnal", method = { RequestMethod.POST })
	@ResponseBody
	public String Addworkoredrtojurnal(@ModelAttribute General_journal_entity generalJournalEntity,
			HttpServletRequest req) {
		System.out.println("Received Product Name: " + generalJournalEntity.getProductName());

		String loginuserid = (String) req.getSession().getAttribute("USERID");

		return BHMS_services.Addworkoredrtojurnal(generalJournalEntity, loginuserid);
	}

	@RequestMapping(value = "gjedit", method = { RequestMethod.POST })
	@ResponseBody
	public String gjedit(@ModelAttribute General_journal_entity generalJournalEntity, HttpServletRequest req) {
		System.out.println("Received Product Name: " + generalJournalEntity.getProductName());

		String loginuserid = (String) req.getSession().getAttribute("USERID");

		return BHMS_services.editgj(generalJournalEntity, loginuserid);
	}

	@RequestMapping(value = "gjfns", method = { RequestMethod.POST })
	@ResponseBody
	public String gjfns(@RequestParam(required = false) String formmode, HttpServletRequest req,
			@RequestParam(required = false) String id) {

		String loginuserid = (String) req.getSession().getAttribute("USERID");
		return BHMS_services.updatefunctions(formmode, id, loginuserid);

	}

	@RequestMapping(value = "ledger", method = { RequestMethod.GET, RequestMethod.POST })
	public String ledger(HttpServletRequest req, Model model, String formmode, String month, String year,
			String vandorid, @RequestParam(value = "mode", required = false) String mode) {

		String loginuserid = (String) req.getSession().getAttribute("USERID");
		model.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));

		if (formmode == null) {
			List<ACCOUNT_LEDGER_PO_Entity> lists = ACCOUNT_LEDGER_PO_rep.findByAll();
			model.addAttribute("list", lists);
			model.addAttribute("formmode", "list");
		} else if (formmode.equals("salaries")) {
			List<EmployeeSalary> lis = EmployeeSalaryRep.getlist();
			model.addAttribute("lists", lis);
			model.addAttribute("formmode", "salaries");
		} /*
			 * else if (formmode.equals("vendor")) { model.addAttribute("getvendor",
			 * Vendor_Register_Repo.getvendorlist()); model.addAttribute("formmode",
			 * "vendor"); }
			 */
		else if (formmode.equals("wo")) {
			model.addAttribute("formmode", "wos");
			List<ACCOUNT_LEDGER_SALE_Entity> lists = ACCOUNT_LEDGER_SALE_rep.findByAll();
			model.addAttribute("list", lists);
		}

		else if (formmode.equals("cash")) {
			List<ACCOUNT_LEDGER_PO_Entity> lists = ACCOUNT_LEDGER_PO_rep.findByAll();
			List<Transaction_table> tran = Transaction_table_Rep.getcashtran();
			BTMAdminOrganizationMaster up = btmAdminOrganizationMasterRep.getOrganization();
			model.addAttribute("tran", tran);
			model.addAttribute("opbalance", up);
			model.addAttribute("list", lists);
			model.addAttribute("formmode", "cash");
		}

		else if (formmode.equals("Account")) {
			List<Transaction_table> tran = Transaction_table_Rep.getbanktran();
			BTMAdminOrganizationMaster up = btmAdminOrganizationMasterRep.getOrganization();
			model.addAttribute("tran", tran);
			model.addAttribute("opbalance", up);
			model.addAttribute("formmode", "bank");
		}

		else if (formmode.equals("vendor")) {
			List<Transaction_table> tran = Transaction_table_Rep.getbanktran();

			model.addAttribute("getvendor", VendorCreationRep.getvendorlist1());

			model.addAttribute("tran", tran);
			model.addAttribute("formmode", "vendor");
		}

		else if (formmode.equals("ven")) {
			List<Transaction_table> tran = Transaction_table_Rep.getbanktran();

			model.addAttribute("getvendor", VendorCreationRep.getvendorlist1());

			model.addAttribute("tran", tran);
			model.addAttribute("formmode", "ven");
		}

		return "General_Ledger.html";
	}

	@RequestMapping(value = "get_salary", method = { RequestMethod.GET })
	@ResponseBody
	public List<EmployeeSalary> getSalary(@RequestParam(required = false) String month,
			@RequestParam(required = false) String year) {

		return EmployeeSalaryRep.getlists(month, year);
	}

	@Autowired
	WorkOrderRep WorkOrderRep;

	@RequestMapping(value = "work_order", method = { RequestMethod.GET, RequestMethod.POST })
	public String work_order(HttpServletRequest req, Model model, @RequestParam(required = false) String formmode,
			@RequestParam(value = "workId", required = false) String workId) {

		String loginuserid = (String) req.getSession().getAttribute("USERID");
		model.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));

		if (formmode == null || formmode.equals("list")) {

			model.addAttribute("formmode", "list");
			model.addAttribute("poList", WorkOrderRep.getwolist());

		}

		else if (formmode.equals("add")) {
			model.addAttribute("formmode", "add");
			model.addAttribute("vendordetails", Vendor_Register_Repo.getvendordetails());
		}

		else if (formmode.equals("inquiry")) {
			model.addAttribute("formmode", "inquiry");
			model.addAttribute("inquiry", WorkOrderRep.getinquirywolist(workId));
		} else if (formmode.equals("verify")) {
			model.addAttribute("formmode", "verify");
			model.addAttribute("inquiry", WorkOrderRep.getinquirywolist(workId));
		}

		else if (formmode.equals("modify")) {
			System.out.println("THE MODIFY WO");
			model.addAttribute("formmode", "modify");
			model.addAttribute("inquiry", WorkOrderRep.getinquirywolist(workId));
		}

		return "work_order.html";

	}

	/*----deletewo---*/
	@RequestMapping(value = "deletewo", method = { RequestMethod.DELETE })
	@ResponseBody
	public String deletewo(@RequestParam(value = "workId", required = false) String workId) {
		String msg = "";
		if (workId == null || workId.isEmpty()) {
			msg = "Id is not found";
		} else {
			Optional<WorkOrder> entity = WorkOrderRep.findById(workId);
			if (entity.isPresent()) {
				WorkOrderRep.delete(entity.get());
				msg = "Record deleted successfully";
			} else {
				msg = "Record not found";
			}
		}
		return msg;
	}

	/*---modifywo--*/
	@RequestMapping(value = "modifywo", method = { RequestMethod.POST })
	@ResponseBody
	public String modifywo(@ModelAttribute WorkOrder WorkOrder, HttpServletRequest req) {
		System.out.println("the enter the controllerwo");
		System.out.println("the po id for controller" + WorkOrder.getWorkId());
		return placementServices.modifywo(WorkOrder);
	}

	/*------verifyWorkOrder------*/

	@RequestMapping(value = "verifyWO", method = { RequestMethod.POST })
	@ResponseBody
	public String verifyWO(@ModelAttribute WorkOrder WorkOrder, HttpServletRequest req,
			@RequestParam(value = "workId", required = true) String workId) {
		System.out.println("the woid" + workId);

		String loginuserid = (String) req.getSession().getAttribute("USERID");

		return placementServices.verifyWO(WorkOrder, loginuserid, workId);
	}

	/*-----TSK Purchase Order---*/
	@Autowired
	Purchase_Order_Repo po_repo;
	@Autowired
	ItemCreationRep ItemCreationrep;

	@RequestMapping(value = "purchaseOrder", method = { RequestMethod.GET, RequestMethod.POST })
	public String purchaseOrder(HttpServletRequest req, Model md, String formmode,
			@RequestParam(required = false) String Opid) {
		System.out.println("purchase order screen");
		String loginuserid = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null) {
			md.addAttribute("formmode", "add");
			md.addAttribute("getvendor", VendorCreationRep.getpurchaseVendor());
			md.addAttribute("ItemList", ItemCreationrep.getitemforpurchaselist());
			md.addAttribute("getpocount", PURCHASE_ORDER_ENTITY_NEW_rep.getPOcount() + 1);
			BTMAdminOrganizationMaster up = btmAdminOrganizationMasterRep.getOrganization();
			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);

			md.addAttribute("cashaccountbal", up);
		} else if (formmode.equals("list")) {
			md.addAttribute("formmode", "list");
			md.addAttribute("getitemlist", PURCHASE_ORDER_ENTITY_NEW_rep.getPOlistnew());
			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);
		} else if (formmode.equals("view")) {
			md.addAttribute("formmode", "view");
			md.addAttribute("getvendor", VendorCreationRep.getvendorlist());
			md.addAttribute("ItemList", ItemCreationrep.getitemlist());
			md.addAttribute("getpocount", PURCHASE_ORDER_ENTITY_NEW_rep.getPOcount() + 1);
			md.addAttribute("getpo", PURCHASE_ORDER_ENTITY_NEW_rep.getbyid(Opid));
		} else if (formmode.equals("modify")) {
			md.addAttribute("formmode", "modify");
			md.addAttribute("getvendor", VendorCreationRep.getvendorlist());
			md.addAttribute("ItemList", ItemCreationrep.getitemlist());
			md.addAttribute("getpocount", PURCHASE_ORDER_ENTITY_NEW_rep.getPOcount() + 1);
			md.addAttribute("getpo", PURCHASE_ORDER_ENTITY_NEW_rep.getbyid(Opid));
			BTMAdminOrganizationMaster up = btmAdminOrganizationMasterRep.getOrganization();
			md.addAttribute("cashaccountbal", up);
		} else if (formmode.equals("confirmPO")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("getitemlist", PURCHASE_ORDER_ENTITY_NEW_rep.getPOlist());
		}

		return "PurchaseOrder";
	}

	@RequestMapping(value = "SalesOrder", method = { RequestMethod.GET, RequestMethod.POST })
	public String SalesOrder(@RequestParam(value = "id", required = false) String id, HttpServletRequest req, Model md,
			String formmode) {
		System.out.println("Sales order screen");
		String loginuserid = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(loginuserid));

		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null) {
			md.addAttribute("formmode", "add");
			md.addAttribute("getvendor", VendorCreationRep.getsaleVendor());
			md.addAttribute("wocount", SALES_ORDER_ENTITY_NEW_rep.getwOcount() + 1);
			md.addAttribute("ItemList", ItemCreationrep.getitemlistbyfg());

			LocalDate currentDate = LocalDate.now();

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String formattedDate = currentDate.format(formatter);

			System.out.println("dateee" + formattedDate);
			md.addAttribute("currentdatee", formattedDate);

			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);
		}

		else if (formmode.equals("flashsale")) {

			md.addAttribute("formmode", "flashsale");
			md.addAttribute("getvendor", VendorCreationRep.getsaleVendor());
			md.addAttribute("wocount", SALES_ORDER_ENTITY_NEW_rep.getwOcount() + 1);
			md.addAttribute("ItemList", ItemCreationrep.getitemlist());

			LocalDate currentDate = LocalDate.now();

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String formattedDate = currentDate.format(formatter);

			System.out.println("dateee" + formattedDate);
			md.addAttribute("currentdatee", formattedDate);

		}

		else if (formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("getitemlist", SALES_ORDER_ENTITY_NEW_rep.getsalesdetails());

			System.out.println("the list");
			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);
		}

		else if (formmode.equals("view")) {
			md.addAttribute("getvendor", VendorCreationRep.getsaleVendor());
			md.addAttribute("ItemList", ItemCreationrep.getitemlist());
			System.out.println("the id" + id);
			md.addAttribute("formmode", "view");

			md.addAttribute("getlist", SALES_ORDER_ENTITY_NEW_rep.getparticular(id));

			System.out.println("the testing" + SALES_ORDER_ENTITY_NEW_rep.getparticular(id));

		} else if (formmode.equals("modify")) {

			md.addAttribute("formmode", "modify");
			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);
			md.addAttribute("getvendor", VendorCreationRep.getsaleVendor());
			md.addAttribute("ItemList", ItemCreationrep.getitemlistbyfg());
			System.out.println("the id" + id);

			md.addAttribute("getlist", SALES_ORDER_ENTITY_NEW_rep.getparticular(id));

			System.out.println("the testing" + SALES_ORDER_ENTITY_NEW_rep.getparticular(id));

		}

		else if (formmode.equals("confirmWO")) {
			BHMS_services.updateWO(id);
			md.addAttribute("formmode", "list");
			md.addAttribute("getitemlist", SALES_ORDER_ENTITY_NEW_rep.getsalesdetails());
		}

		return "Saleordercreation";

	}

	@RequestMapping(value = "new_po_list", method = { RequestMethod.GET, RequestMethod.POST })
	public String new_po_list(@RequestParam(required = false) String Opid, HttpServletRequest req, Model md,
			String formmode) {
		System.out.println("purchase order screen");
		String loginuserid = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(loginuserid));

		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null) {
			md.addAttribute("formmode", "list");
			md.addAttribute("getitemlist", PO_invoice_reps.getPOlistbyapprovalnew());
			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);

		} else if (formmode.equals("view")) {
			md.addAttribute("formmode", "view");
			md.addAttribute("getvendor", VendorCreationRep.getvendorlist());
			md.addAttribute("ItemList", ItemCreationrep.getitemlist());
			md.addAttribute("getpocount", PO_invoice_reps.getPOcount());
			md.addAttribute("getpo", PO_invoice_reps.getbyid(Opid));
		}

		return "New_PO_list.html";

	}

	@RequestMapping(value = "new_wo_list", method = { RequestMethod.GET, RequestMethod.POST })
	public String new_wo_list(@RequestParam(required = false) String id, HttpServletRequest req, Model md,
			String formmode) {
		System.out.println("purchase order screen");
		String loginuserid = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(loginuserid));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null) {

			md.addAttribute("formmode", "history");
			md.addAttribute("getitemlist", SALES_invoice_TABLERep.getsalesinvoicedetailsnew());

			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);
		} else if (formmode.equals("view")) {
			// md.addAttribute("getvendor", VendorCreationRep.getvendorlist());
			md.addAttribute("ItemList", ItemCreationrep.getitemlistbyfg());
			System.out.println("the id" + id);
			md.addAttribute("formmode", "view");

			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);
			md.addAttribute("getlist", SALES_ORDER_ENTITY_NEW_rep.getparticular(id));

			System.out.println("the testing" + SALES_ORDER_ENTITY_NEW_rep.getparticular(id));

		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");
			md.addAttribute("getvendor", VendorCreationRep.getsaleVendor());
			md.addAttribute("wocount", SALES_ORDER_ENTITY_NEW_rep.getwOcount() + 1);
			md.addAttribute("ItemList", ItemCreationrep.getitemlistbyfg());

			LocalDate currentDate = LocalDate.now();

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String formattedDate = currentDate.format(formatter);

			System.out.println("dateee" + formattedDate);
			md.addAttribute("currentdatee", formattedDate);

			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);

		}

		else if (formmode.equals("historyview")) {
			// md.addAttribute("getvendor", VendorCreationRep.getvendorlist());
			md.addAttribute("ItemList", ItemCreationrep.getitemlistbyfg());
			System.out.println("the id" + id);
			md.addAttribute("formmode", "historyview");
			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);

			md.addAttribute("getlist", SALES_invoice_TABLERep.getparticular(id));

			System.out.println("the testing" + SALES_ORDER_ENTITY_NEW_rep.getparticular(id));

		}

		return "New_wO_list.html";

	}

	@RequestMapping(value = "new_wo_list1", method = { RequestMethod.GET, RequestMethod.POST })
	public String new_wo_list1(@RequestParam(required = false) String id, HttpServletRequest req, Model md,
			String formmode) {
		System.out.println("purchase order screen");
		String loginuserid = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(loginuserid));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null) {

			md.addAttribute("formmode", "history");
			md.addAttribute("getitemlist", salesOutRep.getsalesinvoicedetailsnew());

			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);
		} else if (formmode.equals("view")) {
			// md.addAttribute("getvendor", VendorCreationRep.getvendorlist());
			md.addAttribute("ItemList", ItemCreationrep.getitemlistbyfg());
			System.out.println("the id" + id);
			md.addAttribute("formmode", "view");

			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);
			md.addAttribute("getlist", salesOutRep.getparticular(id));

			System.out.println("the testing" + SALES_ORDER_ENTITY_NEW_rep.getparticular(id));

		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");
			md.addAttribute("getvendor", resourceMasterRepo.getEmployee());
			md.addAttribute("wocount", SALES_ORDER_ENTITY_NEW_rep.getwOcount() + 1);
			md.addAttribute("ItemList", ItemCreationrep.getitemlistbyfg());

			LocalDate currentDate = LocalDate.now();

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String formattedDate = currentDate.format(formatter);

			System.out.println("dateee" + formattedDate);
			md.addAttribute("currentdatee", formattedDate);

			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);

		}

		else if (formmode.equals("historyview")) {
			// md.addAttribute("getvendor", VendorCreationRep.getvendorlist());
			md.addAttribute("ItemList", ItemCreationrep.getitemlistbyfg());
			System.out.println("the id" + id);
			md.addAttribute("formmode", "historyview");
			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);

			md.addAttribute("getlist", salesOutRep.getparticular(id));

			System.out.println("the testing" + salesOutRep.getparticular(id));

		}

		return "New_wO_list1.html";

	}

	@RequestMapping(value = "SaleIn", method = { RequestMethod.GET, RequestMethod.POST })
	public String SaleIn(@RequestParam(required = false) String id, HttpServletRequest req, Model md, String formmode) {
		System.out.println("purchase order screen");
		String loginuserid = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(loginuserid));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null) {

			md.addAttribute("formmode", "history");
			md.addAttribute("getitemlist", salesOutRep.getsalesindetailsnew());

			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);
		} else if (formmode.equals("view")) {
			// md.addAttribute("getvendor", VendorCreationRep.getvendorlist());
			md.addAttribute("ItemList", ItemCreationrep.getitemlistbyfg());
			System.out.println("the id" + id);
			md.addAttribute("formmode", "view");

			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);
			md.addAttribute("getlist", salesOutRep.getparticular(id));

			System.out.println("the testing" + SALES_ORDER_ENTITY_NEW_rep.getparticular(id));

		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");
			md.addAttribute("getvendor", resourceMasterRepo.getEmployee());
			md.addAttribute("wocount", SALES_ORDER_ENTITY_NEW_rep.getwOcount() + 1);
			md.addAttribute("ItemList", ItemCreationrep.getitemlistbyfg());

			LocalDate currentDate = LocalDate.now();

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String formattedDate = currentDate.format(formatter);

			System.out.println("dateee" + formattedDate);
			md.addAttribute("currentdatee", formattedDate);

			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);

		}

		else if (formmode.equals("historyview")) {
			// md.addAttribute("getvendor", VendorCreationRep.getvendorlist());
			md.addAttribute("ItemList", ItemCreationrep.getitemlistbyfg());
			System.out.println("the id" + id);
			md.addAttribute("formmode", "historyview");
			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);

			md.addAttribute("getlist", salesOutRep.getparticular(id));

			System.out.println("the testing" + salesOutRep.getparticular(id));

		}

		return "SaleIn.html";

	}

	@RequestMapping(value = "getitemdetails", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Object[]> getitemdetails(@RequestParam(value = "code", required = true) String code) {
		List<Object[]> response = ItemCreationrep.getSingleRow(code);
		System.out.println("the response" + response);
		return response;

	}

	/*---purchase add---*/
	@RequestMapping(value = "AddPO", method = { RequestMethod.POST })
	@ResponseBody
	public String AddPO(@ModelAttribute Purchase_Order_Entity po_entity, HttpServletRequest req) {

		String loginuserid = (String) req.getSession().getAttribute("USERID");

		return BHMS_services.addpo(po_entity, loginuserid);
	}

	/*---modify--*/
	@RequestMapping(value = "modifypo", method = { RequestMethod.POST })
	@ResponseBody
	public String modifypo(@ModelAttribute Purchase_Order_Entity Purchase_Order_Entity, HttpServletRequest req) {
		System.out.println("the enter the controller");
		System.out.println("the po id for controller" + Purchase_Order_Entity.getPoId());
		return BHMS_services.updatepo(Purchase_Order_Entity);
	}

	/*---verifypo--*/
	@RequestMapping(value = "verifypo", method = { RequestMethod.POST })
	@ResponseBody
	public String verifypo(@ModelAttribute Purchase_Order_Entity Purchase_Order_Entity, HttpServletRequest req) {
		System.out.println("the enter the controller for verify");
		System.out.println("the po id for controller" + Purchase_Order_Entity.getPoId());

		String loginuserid = (String) req.getSession().getAttribute("USERID");
		System.out.println("user id" + loginuserid);

		return BHMS_services.verifypo(Purchase_Order_Entity, loginuserid);
	}

	/*---deletepo--*/
	@RequestMapping(value = "DeletePOrecord", method = { RequestMethod.POST })
	@ResponseBody
	public String DeletePOrecord(@ModelAttribute Purchase_Order_Entity Purchase_Order_Entity, HttpServletRequest req,
			@RequestParam(value = "poId", required = false) String poId) {

		String loginuserid = (String) req.getSession().getAttribute("USERID");
		System.out.println("user id" + loginuserid);

		return BHMS_services.DeletePOrecord(Purchase_Order_Entity, poId);
	}

	@RequestMapping(value = "Inward", method = { RequestMethod.GET, RequestMethod.POST })
	public String Inward(HttpServletRequest req, @RequestParam(required = false) String formmode, String poid,
			Model model, @RequestParam(required = false) String id,
			@RequestParam(value = "mode", required = false) String mode) {

		String loginuserid = (String) req.getSession().getAttribute("USERID");

		String userId = (String) req.getSession().getAttribute("USERID");
		String userNAME = (String) req.getSession().getAttribute("USERNAME");
		System.out.println("name :" + userNAME);
		model.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));

		if (formmode == null) {
			model.addAttribute("formmode", "list");
			List<Purchase_Order_Entity> lists = po_repo.getPolists();
			model.addAttribute("poList", lists);

		}

		else if (formmode.equals("history")) {
			model.addAttribute("formmode", "history");
			List<Inward> gateList = InwardRep.getlist();
			model.addAttribute("gateList", gateList);

		} else if (formmode.equals("add")) {
			model.addAttribute("formmode", "add");
			model.addAttribute("inquiry", po_repo.getinquiry(poid));
			model.addAttribute("poid", poid);
			model.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));
		}

		return "Inward.html";

	}

	@RequestMapping(value = "Outward", method = { RequestMethod.GET, RequestMethod.POST })
	public String Outward(HttpServletRequest req, @RequestParam(required = false) String formmode, String poid,
			String sid, Model model, @RequestParam(required = false) String id,
			@RequestParam(value = "mode", required = false) String mode) {

		String loginuserid = (String) req.getSession().getAttribute("USERID");
		String userId = (String) req.getSession().getAttribute("USERID");
		String userNAME = (String) req.getSession().getAttribute("USERNAME");
		System.out.println("name :" + userNAME);
		model.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		if (formmode == null) {
			List<BhmsInvProductSaleMasterTable> list = bhmsInvProductSaleMasterrep.findAllUniqueSaleIds();
			model.addAttribute("stockList", list);
			model.addAttribute("formmode", "bill");
			model.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));
		}

		else if (formmode.equals("add")) {
			model.addAttribute("saleList", BHMS_services.getSaleReport(sid));
			model.addAttribute("formmode", "add");
			model.addAttribute("poid", poid);
			model.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));
		}

		else if (formmode.equals("list")) {
			List<Outward> list = OutwardRep.getlist();
			model.addAttribute("formmode", "list");
			model.addAttribute("List", list);
		}

		return "outward.html";

	}

	@RequestMapping(value = "Addinward", method = { RequestMethod.POST })
	@ResponseBody
	public String Addinward(@ModelAttribute Inward Inward_add, HttpServletRequest req) {
		String msg = placementServices.addinvward(Inward_add);
		return msg;
	}

	/* ------QualityCheck */

	@RequestMapping(value = "QualityCheck", method = { RequestMethod.GET, RequestMethod.POST })
	public String QualityCheck(HttpServletRequest req, @RequestParam(required = false) String formmode, String poid,
			Model model, @RequestParam(required = false) String id,
			@RequestParam(value = "mode", required = false) String mode) {
		String userId = (String) req.getSession().getAttribute("USERID");
		String userNAME = (String) req.getSession().getAttribute("USERNAME");
		System.out.println("name :" + userNAME);
		model.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));

		if (formmode == null) {
			model.addAttribute("formmode", "list");
			List<Purchase_Order_Entity> lists = po_repo.getPolistforquality();
			model.addAttribute("poList", lists);
			System.out.println("the list size" + lists.size());

		} else if (formmode.equals("QualityCheck")) {
			model.addAttribute("formmode", "QualityCheck");
			model.addAttribute("inquiry", po_repo.getinquiry(poid));
			model.addAttribute("vehicaldetails", InwardRep.getvehicallist(poid));
		}

		return "QualityCheck.html";

	}

	@RequestMapping(value = "Addoutward", method = { RequestMethod.POST })
	@ResponseBody
	public String Addoutward(@ModelAttribute Outward outward_add, HttpServletRequest req) {
		String msg = placementServices.addoutwards(outward_add);
		return msg;
	}

	/*----QualityCheck--*/

	@RequestMapping(value = "CheckQuality", method = { RequestMethod.POST })
	@ResponseBody
	public String CheckQuality(@ModelAttribute Purchase_Order_Entity Purchase_Order_Entity,
			@ModelAttribute Inward Inward, HttpServletRequest req) {
		System.out.println("the enter the controller for quality");
		System.out.println("the po id for controller" + Purchase_Order_Entity.getPoId());

		String loginuserid = (String) req.getSession().getAttribute("USERID");
		System.out.println("user id" + loginuserid);

		return BHMS_services.CheckQuality(Purchase_Order_Entity, Inward, loginuserid);
	}

	@RequestMapping(value = "InspectProductionProcess", method = { RequestMethod.GET, RequestMethod.POST })
	public String InspectProductionProcess(HttpServletRequest req, @RequestParam(required = false) String formmode,
			String refid, Model model, @RequestParam(required = false) String id,
			@RequestParam(value = "mode", required = false) String mode) {

		String loginuserid = (String) req.getSession().getAttribute("USERID");

		String userId = (String) req.getSession().getAttribute("USERID");
		String userNAME = (String) req.getSession().getAttribute("USERNAME");
		System.out.println("name :" + userNAME);
		model.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));

		if (formmode == null) {
			model.addAttribute("formmode", "list");
			List<Object[]> comlist = PROCESS_rep.completedProcesss();

			model.addAttribute("comlist", comlist);
			// List<Purchase_Order_Entity> lists =po_repo.getPolists();
			// model.addAttribute("poList", lists);

		}

		else if (formmode.equals("history")) {
			model.addAttribute("formmode", "history");
			List<Object[]> comlist = PROCESS_rep.qacompletedProcess();
			model.addAttribute("comlist", comlist);

		} else if (formmode.equals("add")) {
			model.addAttribute("formmode", "add");
			PROCESS_ENTITY process = PROCESS_rep.findByIDS(refid);
			model.addAttribute("process", process);
			model.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));
		}

		return "InspectProductionProcess.html";

	}

	@RequestMapping(value = "AddInspectProductionProcess", method = { RequestMethod.POST })
	@ResponseBody
	public String AddInspectProductionProcess(@ModelAttribute PROCESS_ENTITY PROCESS_ENTITY_add,
			HttpServletRequest req) {
		String msg = placementServices.addInspectProductionProcess(PROCESS_ENTITY_add);
		return msg;
	}

	@RequestMapping(value = "AddCapital", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String AddCapital(HttpServletRequest req, @RequestParam(required = false) long capitalnum, long previous) {
		Capitalrep.getCapitalamount();

		Capital up = Capitalrep.getCapitalamountbyid(previous);
		up.setCapitalAmount(capitalnum);
		Capitalrep.save(up);
		return "completed";
	}

	/*------vendor creation---*/

	@RequestMapping(value = "vendorRegistration", method = { RequestMethod.GET, RequestMethod.POST })
	public String vendorRegistration(HttpServletRequest req, @RequestParam(required = false) String formmode,
			String refid, Model model, @RequestParam(value = "mode", required = false) String mode,
			@RequestParam(value = "vendorDocId", required = false) String vendorDocId) {

		String loginuserid = (String) req.getSession().getAttribute("USERID");
		String userId = (String) req.getSession().getAttribute("USERID");
		String userNAME = (String) req.getSession().getAttribute("USERNAME");
		System.out.println("name :" + userNAME);
		model.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));

		if (formmode == null || formmode.equals("list")) {
			// Session session = sessionFactory.getCurrentSession();
			// BigInteger id = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR
			// HRMS.vendor_Id AS id");

			model.addAttribute("formmode", "list");
			model.addAttribute("getvendor", Vendor_Register_Repo.getvendorlist());
		} else if (formmode.equals("add")) {
			model.addAttribute("formmode", "add");

		} else if (formmode.equals("Modify")) {
			model.addAttribute("formmode", "Modify");
			model.addAttribute("getvendormodify", Vendor_Register_Repo.getvendorparticular(vendorDocId));

		}
		return "VendorRegistration";

	}

	@Autowired
	Vendor_Register_Service Vendor_Service;

	@Autowired
	Expenses_repo expenses_repo;

	@RequestMapping(value = "addexpenses11", method = { RequestMethod.POST })
	@ResponseBody
	public String addexpenses11(@ModelAttribute Expenses_entity Expenses_entity, HttpServletRequest req) {
		System.out.println("the add vendor controller");
		String userId = (String) req.getSession().getAttribute("USERID");
		String username = (String) req.getSession().getAttribute("USERNAME");

		String msg = Vendor_Service.addexpenses(Expenses_entity);
		return msg;
	}

	/*----update vendor---*/

	@RequestMapping(value = "UpdateVendor", method = { RequestMethod.POST })
	@ResponseBody
	public String UpdateVendor(@ModelAttribute Vendor_registration_entity Vendor_registration_entity,
			HttpServletRequest req) {
		System.out.println("the add vendor controller for update");
		String userId = (String) req.getSession().getAttribute("USERID");
		String username = (String) req.getSession().getAttribute("USERNAME");
		String msg = Vendor_Service.UpdateVendor(Vendor_registration_entity, userId, username);
		return msg;
	}

	@RequestMapping(value = "getvendordetails", method = RequestMethod.GET)
	@ResponseBody
	public List<Vendor_registration_entity> getvendordetails(@RequestParam String vendor_id) {
		System.out.println("Fetching details for vendor: " + vendor_id);

		// Query to fetch a single vendor by vendor_name
		List<Vendor_registration_entity> vendor = Vendor_Register_Repo.getvdetails(vendor_id);

		return vendor;

	}

	@RequestMapping(value = "workdetails", method = RequestMethod.GET)
	@ResponseBody
	public List<WorkOrder> workdetails(@RequestParam String item) {
		System.out.println("Fetching details for item: " + item);

		// Query to fetch a single vendor by vendor_name
		List<WorkOrder> vendor = WorkOrderRep.workdetails(item);

		return vendor;
	}

	@RequestMapping(value = "getwodetails", method = RequestMethod.GET)
	@ResponseBody
	public List<WorkOrder> getwodetails(@RequestParam String VendorId) {
		System.out.println("Fetching details for item: " + VendorId);

		// Query to fetch a single vendor by vendor_name
		List<WorkOrder> vendor = WorkOrderRep.getWorkOrder(VendorId);

		return vendor;
	}

	@RequestMapping(value = "getpodetails", method = RequestMethod.GET)
	@ResponseBody
	public List<Purchase_Order_Entity> getpodetails(@RequestParam String VendorId) {
		System.out.println("Fetching details for item: " + VendorId);

		// Query to fetch a single vendor by vendor_name
		List<Purchase_Order_Entity> vendor = po_repo.getpodeails(VendorId);

		return vendor;
	}

	@RequestMapping(value = "getamountforWO", method = RequestMethod.GET)
	@ResponseBody
	public List<WorkOrder> getamountforWO(@RequestParam String item, String hsnSacCode) {
		System.out.println("Fetching details for item: " + item);
		System.out.println("Fetching details for code: " + hsnSacCode);

		// Query to fetch a single vendor by vendor_name
		List<WorkOrder> vendor = WorkOrderRep.getamountforWO(item, hsnSacCode);

		return vendor;
	}

	/* ---po for journal */
	@RequestMapping(value = "getamountforPO", method = RequestMethod.GET)
	@ResponseBody
	public List<Purchase_Order_Entity> getamountforPO(@RequestParam String PO_ID, String hsnSacCode) {
		System.out.println("Fetching details for item: " + PO_ID);
		System.out.println("Fetching details for code: " + hsnSacCode);

		// Query to fetch a single vendor by vendor_name
		List<Purchase_Order_Entity> vendor = po_repo.getamountforPO(PO_ID, hsnSacCode);
		for (Purchase_Order_Entity up : vendor) {

			System.out.println("the paymentby" + up.getPaymenttype());
		}

		return vendor;
	}

	@RequestMapping(value = "trail_balance", method = { RequestMethod.GET, RequestMethod.POST })
	public String trail_balance(HttpServletRequest req, Model model) {

		String userId = (String) req.getSession().getAttribute("USERID");
		model.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));

		// For purchase
		List<ACCOUNT_LEDGER_PO_Entity> po_lists = ACCOUNT_LEDGER_PO_rep.findAll();
		model.addAttribute("Purchase", po_lists);

		BigDecimal total = capitaltransrep.getTotalPurchase();
		// BigDecimal totalAmount= BHMS_services.get_po_total();
		model.addAttribute("total", total); // Pass totalAmount to view

		// For sale
		List<ACCOUNT_LEDGER_SALE_Entity> lists_sale = ACCOUNT_LEDGER_SALE_rep.findByAll();
		model.addAttribute("wo", lists_sale);
		BigDecimal sale_amt = capitaltransrep.getTotalSale();
		// BigDecimal sale_amt= BHMS_services.sale_total();
		model.addAttribute("sale_amt", sale_amt); // Pass totalAmount to view

		// current asset
		Erp_ChartOfAccounts currentamount = Erp_ChartOfAccountsRep.findsccount("ACC00016");
		model.addAttribute("currentasset", currentamount.getAccountBalance());

		// Non-Current Assets
		Erp_ChartOfAccounts noncurrentamount = Erp_ChartOfAccountsRep.findsccount("ACC00015");
		model.addAttribute("Non_Current_Assets", noncurrentamount.getAccountBalance());

		// Current Liabilities
		Erp_ChartOfAccounts CurrentLiabilities = Erp_ChartOfAccountsRep.findsccount("ACC00065");
		model.addAttribute("CurrentLiabilities", CurrentLiabilities.getAccountBalance());

		// Expense
		Erp_ChartOfAccounts Expense_Account = Erp_ChartOfAccountsRep.findsccount("ACC00085");
		model.addAttribute("Expense_Account", Expense_Account.getAccountBalance());

		// For Wages
		List<EmployeeSalary> list_sal = EmployeeSalaryRep.getlist();
		BigDecimal wages_total = BHMS_services.wages_total();
		model.addAttribute("wages_amt", wages_total); // Pass totalAmount to view
		model.addAttribute("salaries", list_sal);

		// For Capital Amount (cash+Account)
		// BTMAdminOrganizationMaster
		// cap=btmAdminOrganizationMasterRep.getOrganization();
		// model.addAttribute("caps", cap.getCapitalamount());

		Erp_ChartOfAccounts capsamount = Erp_ChartOfAccountsRep.findsccount("ACC00011");
		model.addAttribute("caps", capsamount.getAccountBalance());

		////////////////////////////////////////////////////////////
		// Getting current Asset values for Purchase
		List<ACCOUNT_LEDGER_PO_Entity> getUnique = ACCOUNT_LEDGER_PO_rep.getUnique();
		Set<String> processedPoIds = new HashSet<>(); // Track unique PO IDs

		Map<String, BigDecimal> descriptionTotalMap = new HashMap<>();

		// Aggregate total amount for each unique description
		for (ACCOUNT_LEDGER_PO_Entity entry : getUnique) {
			String description = entry.getDescription();
			BigDecimal amount = entry.getTotalAmount();
			String poId = entry.getPoId(); // Assuming poId is the unique identifier

			// Process only if amount is not null and PO ID is not already processed
			if (amount != null && !processedPoIds.contains(poId)) {
				descriptionTotalMap.put(description,
						descriptionTotalMap.getOrDefault(description, BigDecimal.ZERO).add(amount));
				processedPoIds.add(poId); // Mark PO ID as processed
			}
		}

		// Convert Map to List of Entities
		List<ACCOUNT_LEDGER_PO_Entity> list = new ArrayList<>();
		for (Map.Entry<String, BigDecimal> entry : descriptionTotalMap.entrySet()) {
			ACCOUNT_LEDGER_PO_Entity entity = new ACCOUNT_LEDGER_PO_Entity();
			entity.setDescription(entry.getKey());
			entity.setTotalAmount(entry.getValue());
			list.add(entity);
		}

		model.addAttribute("Getunique", list);

		// Getting Non-Current Asset values for Purchase
		List<ACCOUNT_LEDGER_PO_Entity> getUnique_non = ACCOUNT_LEDGER_PO_rep.getUnique_non();
		Set<String> processedPoIds_non = new HashSet<>(); // Track unique PO IDs

		Map<String, BigDecimal> descriptionTotalMap1 = new HashMap<>();

		// Aggregate total amount for each unique description
		for (ACCOUNT_LEDGER_PO_Entity entry : getUnique_non) {
			String description = entry.getDescription();
			BigDecimal amount = entry.getTotalAmount();
			String poId = entry.getPoId(); // Assuming poId is the unique identifier

			// Process only if amount is not null and PO ID is not already processed
			if (amount != null && !processedPoIds_non.contains(poId)) {
				descriptionTotalMap1.put(description,
						descriptionTotalMap1.getOrDefault(description, BigDecimal.ZERO).add(amount));
				processedPoIds_non.add(poId); // Mark PO ID as processed
			}
		}

		// Convert Map to List of Entities
		List<ACCOUNT_LEDGER_PO_Entity> getUnique_non1 = new ArrayList<>();
		for (Map.Entry<String, BigDecimal> entry : descriptionTotalMap1.entrySet()) {
			ACCOUNT_LEDGER_PO_Entity entity = new ACCOUNT_LEDGER_PO_Entity();
			entity.setDescription(entry.getKey());
			entity.setTotalAmount(entry.getValue());
			getUnique_non1.add(entity);
		}

		// Send the aggregated data to the model
		model.addAttribute("getUnique_non", getUnique_non1);

		//////////////////////////////////////////////////////////////
		// Getting Current Asset values for Sale
		List<ACCOUNT_LEDGER_SALE_Entity> getUnique1 = ACCOUNT_LEDGER_SALE_rep.getUnique();
		Set<String> processedSaleIds = new HashSet<>(); // Track unique Sale IDs

		Map<String, BigDecimal> descriptionTotalMap11 = new HashMap<>();

		// Aggregate total amount for each unique description
		for (ACCOUNT_LEDGER_SALE_Entity entry : getUnique1) {
			String description = entry.getDescription();
			BigDecimal amount = entry.getTotalAmount();
			String saleId = entry.getWoId(); // ✅ Ensure this method exists

			// Process only if amount is not null and Sale ID is not already processed
			if (amount != null && saleId != null && !processedSaleIds.contains(saleId)) {
				descriptionTotalMap11.put(description,
						descriptionTotalMap11.getOrDefault(description, BigDecimal.ZERO).add(amount));
				processedSaleIds.add(saleId); // Mark Sale ID as processed
			}
		}

		// Convert Map to List of Entities
		List<ACCOUNT_LEDGER_SALE_Entity> list1 = new ArrayList<>();
		for (Map.Entry<String, BigDecimal> entry : descriptionTotalMap11.entrySet()) {
			ACCOUNT_LEDGER_SALE_Entity entity = new ACCOUNT_LEDGER_SALE_Entity();
			entity.setDescription(entry.getKey());
			entity.setTotalAmount(entry.getValue());
			list1.add(entity);
		}

		// Send aggregated data to the model
		model.addAttribute("Getunique_sale", list1);

		// Getting Non-Current Asset values for Sale
		List<ACCOUNT_LEDGER_SALE_Entity> getUnique_non11 = ACCOUNT_LEDGER_SALE_rep.getUnique_non();
		Set<String> processedSaleIds_non = new HashSet<>(); // Track unique Sale IDs for Non-Current Assets

		Map<String, BigDecimal> descriptionTotalMap111 = new HashMap<>();

		// Aggregate total amount for each unique description
		for (ACCOUNT_LEDGER_SALE_Entity entry : getUnique_non11) {
			String description = entry.getDescription();
			BigDecimal amount = entry.getTotalAmount();
			String saleId = entry.getWoId(); // ✅ Ensure this method exists

			// Process only if amount is not null and Sale ID is not already processed
			if (amount != null && saleId != null && !processedSaleIds_non.contains(saleId)) {
				descriptionTotalMap111.put(description,
						descriptionTotalMap111.getOrDefault(description, BigDecimal.ZERO).add(amount));
				processedSaleIds_non.add(saleId); // Mark Sale ID as processed
			}
		}

		// Convert Map to List of Entities
		List<ACCOUNT_LEDGER_SALE_Entity> getUnique_non111 = new ArrayList<>();
		for (Map.Entry<String, BigDecimal> entry : descriptionTotalMap111.entrySet()) {
			ACCOUNT_LEDGER_SALE_Entity entity = new ACCOUNT_LEDGER_SALE_Entity();
			entity.setDescription(entry.getKey());
			entity.setTotalAmount(entry.getValue());
			getUnique_non111.add(entity);
		}

		// Send the aggregated data to the model
		model.addAttribute("getUnique_non_sale", getUnique_non111);

		return "trail_balance.html";

	}

	/* ----chart of account--- */

	@RequestMapping(value = "chartOfAccounts", method = RequestMethod.GET)
	public String chartOfAccounts(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String acct_num, String keyword, Model md, HttpServletRequest req) {
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("formmode", "list");
			md.addAttribute("chartaccount", chart_Acc_Rep.getListoffice());
		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");
			md.addAttribute("Chart1", reference_code_Rep.getReferenceCode("COA_01"));
			md.addAttribute("Chart2", reference_code_Rep.getReferenceCode("COA_02"));
			md.addAttribute("Chart3", reference_code_Rep.getReferenceCode("COA_03"));
			md.addAttribute("Chart4", reference_code_Rep.getReferenceCode("COA_04"));
			md.addAttribute("Chart5", reference_code_Rep.getReferenceCode("COA_05"));
			md.addAttribute("Chart6", reference_code_Rep.getReferenceCode("COA_06"));
			md.addAttribute("Chart7", reference_code_Rep.getReferenceCode("COA_07"));
			md.addAttribute("Chart8", reference_code_Rep.getReferenceCode("COA_08"));

			/*---for vendor--*/

			List<String> vendorId = Vendor_Register_Repo.getvendoridforCOA();
			System.out.println("the vendor id" + vendorId);

			md.addAttribute("vendor", vendorId);

		} else if (formmode.equals("modify")) {
			md.addAttribute("formmode", "modify");
			md.addAttribute("chartaccount", chart_Acc_Rep.getaedit(acct_num));
		} else if (formmode.equals("verify")) {
			md.addAttribute("chartaccount", chart_Acc_Rep.getaedit(acct_num));
			md.addAttribute("formmode", "verify");
		} else if (formmode.equals("view")) {
			md.addAttribute("formmode", "view");
			md.addAttribute("chartaccount", chart_Acc_Rep.getaedit(acct_num));
			md.addAttribute("Chart1", reference_code_Rep.getReferenceCode("COA_01"));
			md.addAttribute("Chart2", reference_code_Rep.getReferenceCode("COA_02"));
			md.addAttribute("Chart3", reference_code_Rep.getReferenceCode("COA_03"));
			md.addAttribute("Chart4", reference_code_Rep.getReferenceCode("COA_04"));
			md.addAttribute("Chart5", reference_code_Rep.getReferenceCode("COA_05"));
			md.addAttribute("Chart6", reference_code_Rep.getReferenceCode("COA_06"));
			md.addAttribute("Chart7", reference_code_Rep.getReferenceCode("COA_07"));
			md.addAttribute("Chart8", reference_code_Rep.getReferenceCode("COA_08"));

		}
		return "ChartOfAccounts";
	}

	@RequestMapping(value = "Modify_Screens", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> modifyScreens(HttpServletRequest rq, @ModelAttribute Chart_Acc_Entity ua,
			@RequestParam(required = false) String acct) {
		String userId = (String) rq.getSession().getAttribute("USERID");
		ResponseEntity<String> msg = null;
		return msg = BHMS_services.ledgedit(ua, acct, userId);

	}

	/*---ADD FUNCTION FOR NEW--*/

	@RequestMapping(value = "AddScreens", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> AddScreens(Model md, HttpServletRequest rq, @ModelAttribute Chart_Acc_Entity ua,
			@RequestParam(required = false) String vendor_id) {
		String userid = (String) rq.getSession().getAttribute("USERID");
		List<String> existingData = chart_Acc_Rep.getexistingData();

		System.out.println("Vendor ID: " + vendor_id);

		if (ua.getAcct_num() == null || ua.getAcct_num().isEmpty()) {
			return ResponseEntity.badRequest().body("Account number is required and cannot be empty");
		}

		if (existingData.contains(ua.getAcct_num())) {
			return ResponseEntity.badRequest().body("Account number already exists");
		}

		try {
			// Save chart of accounts
			Chart_Acc_Entity up = ua;
			up.setAcct_num(ua.getAcct_num());
			up.setAcct_name(vendor_id);
			up.setEntity_flg("N");
			up.setDel_flg("N");
			chart_Acc_Rep.save(up);

			/*
			 * // Create audit log Long auditID = bglsBusinessTable_Rep.getAuditRefUUID();
			 * Optional<ResourceMaster> optionalUser = resourceMasterRepo.getuser1(userid);
			 * 
			 * ResourceMaster user = optionalUser.get();
			 * 
			 * BGLSBusinessTable_Entity audit = new BGLSBusinessTable_Entity();
			 * LocalDateTime currentDateTime = LocalDateTime.now(); Date dateValue =
			 * Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
			 * 
			 * audit.setAudit_date(new Date()); audit.setEntry_time(dateValue);
			 * audit.setEntry_user(userid); audit.setRemarks("Saved Successfully");
			 * audit.setAudit_table("ERP_CHART_OF_ACCOUNTS");
			 * audit.setAudit_screen("CHART OF ACCOUNTS - ADD"); audit.setEvent_id(userid);
			 * audit.setEvent_name(user.getUsername());
			 * audit.setModi_details("Login Successfully"); audit.setAuth_user(userid);
			 * audit.setAuth_time(dateValue); audit.setAudit_ref_no(auditID.toString());
			 * audit.setField_name("-"); bglsBusinessTable_Rep.save(audit);
			 */

			return ResponseEntity.ok("Saved Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while saving");
		}
	}

	@RequestMapping(value = "refCodeMain", method = { RequestMethod.GET, RequestMethod.POST })
	public String refCodeMain(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String emp_id, Model model, HttpServletRequest request) {

		if (formmode == null || formmode.equals("list")) {

			model.addAttribute("formmode", "list");
			model.addAttribute("refList", reference_code_Rep.getRefList());
		} else if (formmode.equals("nav")) {

			model.addAttribute("formmode", "nav");
			model.addAttribute("refType", reference_code_Rep.getReferenceType());
		}
		return "ReferenceCodeMaintenance";
	}

	@RequestMapping(value = "trialBalanceReports2", method = { RequestMethod.GET, RequestMethod.POST })
	public String trialBalanceReports2(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String tran, Model md, HttpServletRequest rq,
			@RequestParam(required = false) String glshCode, HttpServletRequest request) {

		Date TRANDATE = (Date) request.getSession().getAttribute("TRANDATE");
		System.out.println(TRANDATE);
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("formmode", "list");

			System.out.println("balance");
			md.addAttribute("trialbal", chart_Acc_Rep.getglcode());
			md.addAttribute("trialbalance", chart_Acc_Rep.getList());
			// Date TRANDATE = new Date(); // Replace with your actual date
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date d = dateFormat.parse("2024-05-15");
				md.addAttribute("TRANDATE", d); // Add the date to the model
			} catch (ParseException e) {
				e.printStackTrace(); // Handle the exception if the date format is incorrect
			}

		}

		return "TrailBalanceReports2.html";
	}

	@RequestMapping(value = "BGLS/ghlslistdata", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Chart_Acc_Entity> ghlslistdata(@RequestParam(required = false) String glshCode) {
		System.out.println("gld");
		System.out.println(glshCode);
		List<Chart_Acc_Entity> GLSHValue = chart_Acc_Rep.getglsh(glshCode);
		System.out.println("THE GLSH RECORD IS" + glshCode);
		return GLSHValue;
	}

	@RequestMapping(value = "refAdd", method = RequestMethod.POST)
	@ResponseBody
	public String refAdd(HttpServletRequest rq, @ModelAttribute Reference_Code_Entity reference_Code_Entity) {
		String userid = (String) rq.getSession().getAttribute("USERID");
		reference_Code_Entity.setEntity_flg("N");
		reference_Code_Entity.setModify_flg("N");
		reference_Code_Entity.setDel_flg("N");
		reference_Code_Entity.setEntry_user(userid);
		reference_Code_Entity.setEntry_time(new Date());
		reference_code_Rep.save(reference_Code_Entity);

		BGLSBusinessTable_Entity audit = new BGLSBusinessTable_Entity();

		Long auditID = bglsBusinessTable_Rep.getAuditRefUUID();
		Optional<ResourceMaster> up1 = resourceMasterRepo.getuser1(userid);
		ResourceMaster user = up1.get();

		LocalDateTime currentDateTime = LocalDateTime.now();
		Date dateValue = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
		audit.setAudit_date(new Date());
		audit.setEntry_time(dateValue);
		audit.setEntry_user(user.getEmployee_id());
		audit.setFunc_code("REFERENCE CODE");
		audit.setRemarks("Sucessfully Saved");
		audit.setAudit_table("BGLS_REF_MASTER");
		audit.setAudit_screen("REFERENCE CODE MAINTENANCE - ADD");
		audit.setEvent_id(userid);
		audit.setEvent_name(user.getUsername());
		// audit.setModi_details("Login Successfully");
		// ResourceMaster auth_user = resourceMasterRepo.getRole(user.getUserid());
		// String auth_user_val = auth_user.getAuth_user();
		// Date auth_user_date = auth_user.getAuth_time();

		audit.setAuth_user(userid);
		audit.setAuth_time(dateValue);
		audit.setAudit_ref_no(auditID.toString());
		audit.setField_name("-");

		bglsBusinessTable_Rep.save(audit);
		return "Sucessfully Saved";
	}

	@RequestMapping(value = "glcode", method = { RequestMethod.GET, RequestMethod.POST })
	public String glcode(@RequestParam(required = false) String formmode, @RequestParam(required = false) String glcode,
			Model md, HttpServletRequest request, @RequestParam(required = false) String glsh_Code) {

		if (formmode == null || formmode.equals("view")) {

			md.addAttribute("formmode", "view");

		} else if (formmode.equals("list")) {

			md.addAttribute("formmode", "list");

			md.addAttribute("getvaluelist", generalLedgerRep.getlistvalue());
		} else if (formmode.equals("view1")) {

			md.addAttribute("formmode", "view1");
			// md.addAttribute("GeneralLedger", adminOperServices.getGeneralLedger(glcode));

			md.addAttribute("GeneralLedger", chart_Acc_Rep.getaedit(glcode));
			System.out.println(chart_Acc_Rep.getaedit(glcode) + "GLCODE" + glcode);
		} else if (formmode.equals("list1")) {

			md.addAttribute("formmode", "list1");
			md.addAttribute("BamGeneralLedger", generalLedgerRep.getRefCodelist());

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", "edit");
			md.addAttribute("GeneralLedger", adminOperServices.getGeneralLedger(glcode));

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("deleteList")) {

			md.addAttribute("formmode", "deleteList");
			md.addAttribute("BamGeneralLedger", generalLedgerRep.getRefCodelist());

		} else if (formmode.equals("delete")) {

			md.addAttribute("formmode", "delete");
			md.addAttribute("GeneralLedger", adminOperServices.getGeneralLedger(glcode));

		} else if (formmode.equals("upload")) {

			md.addAttribute("formmode", "upload");

		} else if (formmode.equals("uploadlist")) {

			md.addAttribute("formmode", "uploadlist");
			md.addAttribute("Listofvalues", generalLedgerWork_Rep.getlistvalue());
		} else if (formmode.equals("viewusinglsh")) {

			md.addAttribute("formmode", "viewusinglsh");
			md.addAttribute("singlerecord", generalLedgerRep.getsinglevalue(glsh_Code));
		}

		return "GeneralLedger.html";
	}

	@RequestMapping(value = "Parameter", method = { RequestMethod.GET, RequestMethod.POST })
	public String Parameters(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String refnumber,
			@RequestParam(value = "page", required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req,
			String Sort, String acct_open_form, String acc_temp, String cus_temp) {

		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("Role", resourceMasterRepo.getuser1(userId));
		Date TRANDATE = (Date) req.getSession().getAttribute("TRANDATE");

// String loginuserid = (String) req.getSession().getAttribute("USERID");
// Logging Navigation
// loginServices.SessionLogging("USERPROFILE", "M2", req.getSession().getId(),
// loginuserid, req.getRemoteAddr(),
// "ACTIVE");

		if (formmode == null || formmode.equals("list")) {
			System.out.println("Value=======================>>> " + refnumber);
			md.addAttribute("OtherServices", parameterrep.listofvalue());
			md.addAttribute("menu", "AMLCustomerKYC");
			md.addAttribute("menuname", "CustomerKYC");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"
			// md.addAttribute("CustomerKYC",CMGrepository.findAll(PageRequest.of(currentPage,
			// pageSize)));

		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");
			md.addAttribute("branch_id", reference_code_Rep.getBranch_Id());
			md.addAttribute("schme_type", reference_code_Rep.getSchme_Type());
			md.addAttribute("schme_code", reference_code_Rep.getSchme_Code());
			md.addAttribute("gl_code", reference_code_Rep.getGL_CODE());
			md.addAttribute("glsh", reference_code_Rep.getGLSH());
			md.addAttribute("curr", reference_code_Rep.getCurr());
			md.addAttribute("multi_curr", reference_code_Rep.getMulti_Curr());
			md.addAttribute("userId", resourceMasterRepo.getuser1(userId));

			String paramRef = parameterrep.getParamRef();
			String ParamReference;
			if (paramRef != null) {
				ParamReference = "REF" + (Integer.valueOf(paramRef) + 1);
			} else {
				ParamReference = "REF1";
			}
			md.addAttribute("ParamRef", ParamReference);
			System.out.println("PARAM " + ParamReference);

		} else if (formmode.equals("workflow")) {
			md.addAttribute("formmode", "workflow");
			md.addAttribute("acc_temp", acc_temp);
			md.addAttribute("userId", userId);
		} else if (formmode.equals("view")) {
			md.addAttribute("formmode", formmode);

			md.addAttribute("formmode", "view");
			System.out.println("Value========>>>" + refnumber);
			md.addAttribute("OtherServicess", parameterrep.findbyId(refnumber));
			md.addAttribute("parameterslist", parameterrep.findAll());
			ParametersDetails check = parameterrep.findbyId(refnumber);

			String checklist = check.getCheck_list();
			List<String> valuesList = checklist != null ? Arrays.asList(checklist.split(",")) : new ArrayList<>();
			md.addAttribute("valuesList", valuesList);

			// Check and split the approval values
			String appr1 = check.getApproval1();
			List<String> valappr1 = appr1 != null ? Arrays.asList(appr1.split(",")) : new ArrayList<>();
			md.addAttribute("appr1", valappr1);

			String appr2 = check.getApproval2();
			List<String> valappr2 = appr2 != null ? Arrays.asList(appr2.split(",")) : new ArrayList<>();
			md.addAttribute("appr2", valappr2);

			String appr3 = check.getApproval3();
			List<String> valappr3 = appr3 != null ? Arrays.asList(appr3.split(",")) : new ArrayList<>();
			md.addAttribute("appr3", valappr3);

			// Check and split the alert values
			String alr1 = check.getAlert1();
			List<String> valalr1 = alr1 != null ? Arrays.asList(alr1.split(",")) : new ArrayList<>();
			md.addAttribute("alr1", valalr1);

			String alr2 = check.getAlert2();
			List<String> valalr2 = alr2 != null ? Arrays.asList(alr2.split(",")) : new ArrayList<>();
			md.addAttribute("alr2", valalr2);

			String alr3 = check.getAlert3();
			List<String> valalr3 = alr3 != null ? Arrays.asList(alr3.split(",")) : new ArrayList<>();
			md.addAttribute("alr3", valalr3);

		} else if (formmode.equals("verify")) {
			md.addAttribute("formmode", formmode);

			md.addAttribute("formmode", "verify");
			System.out.println("Value========>>>" + refnumber);
			md.addAttribute("OtherServicess", parameterrep.findbyId(refnumber));
			md.addAttribute("parameterslist", parameterrep.findAll());
			// for check list
			ParametersDetails check = parameterrep.findbyId(refnumber);

			String checklist = check.getCheck_list();
			List<String> valuesList = checklist != null ? Arrays.asList(checklist.split(",")) : new ArrayList<>();
			md.addAttribute("valuesList", valuesList);

			// Check and split the approval values
			String appr1 = check.getApproval1();
			List<String> valappr1 = appr1 != null ? Arrays.asList(appr1.split(",")) : new ArrayList<>();
			md.addAttribute("appr1", valappr1);

			String appr2 = check.getApproval2();
			List<String> valappr2 = appr2 != null ? Arrays.asList(appr2.split(",")) : new ArrayList<>();
			md.addAttribute("appr2", valappr2);

			String appr3 = check.getApproval3();
			List<String> valappr3 = appr3 != null ? Arrays.asList(appr3.split(",")) : new ArrayList<>();
			md.addAttribute("appr3", valappr3);

			// Check and split the alert values
			String alr1 = check.getAlert1();
			List<String> valalr1 = alr1 != null ? Arrays.asList(alr1.split(",")) : new ArrayList<>();
			md.addAttribute("alr1", valalr1);

			String alr2 = check.getAlert2();
			List<String> valalr2 = alr2 != null ? Arrays.asList(alr2.split(",")) : new ArrayList<>();
			md.addAttribute("alr2", valalr2);

			String alr3 = check.getAlert3();
			List<String> valalr3 = alr3 != null ? Arrays.asList(alr3.split(",")) : new ArrayList<>();
			md.addAttribute("alr3", valalr3);

			md.addAttribute("userId", userId);

		} else if (formmode.equals("new")) {
			md.addAttribute("formmode", "new");
			md.addAttribute("acc_temp", acc_temp);
			md.addAttribute("tran_date", TRANDATE);
			md.addAttribute("userId", userId);
		} else if (formmode.equals("move")) {
			md.addAttribute("formmode", "move");
			md.addAttribute("cus_temp", cus_temp);
			md.addAttribute("tran_date", TRANDATE);
			md.addAttribute("userId", userId);
		} else if (formmode.equals("with")) {
			md.addAttribute("formmode", "with");
			md.addAttribute("acct_open_form", acct_open_form);
			md.addAttribute("userId", userId);
		} else if (formmode.equals("modify")) {
			md.addAttribute("formmode", "modify");
			md.addAttribute("OtherServicess", parameterrep.findbyId(refnumber));
			md.addAttribute("parameterslist", parameterrep.findAll());
			md.addAttribute("branch_id", reference_code_Rep.getBranch_Id());
			md.addAttribute("schme_type", reference_code_Rep.getSchme_Type());
			md.addAttribute("schme_code", reference_code_Rep.getSchme_Code());
			md.addAttribute("gl_code", reference_code_Rep.getGL_CODE());
			md.addAttribute("glsh", reference_code_Rep.getGLSH());
			md.addAttribute("curr", reference_code_Rep.getCurr());
			md.addAttribute("multi_curr", reference_code_Rep.getMulti_Curr());
			// for check list
			// for check list
			ParametersDetails check = parameterrep.findbyId(refnumber);
			String checklist = check.getCheck_list();
			List<String> valuesList = checklist != null ? Arrays.asList(checklist.split(",")) : new ArrayList<>();
			md.addAttribute("valuesList", valuesList);

			// Check and split the approval values
			String appr1 = check.getApproval1();
			List<String> valappr1 = appr1 != null ? Arrays.asList(appr1.split(",")) : new ArrayList<>();
			md.addAttribute("appr1", valappr1);

			String appr2 = check.getApproval2();
			List<String> valappr2 = appr2 != null ? Arrays.asList(appr2.split(",")) : new ArrayList<>();
			md.addAttribute("appr2", valappr2);

			String appr3 = check.getApproval3();
			List<String> valappr3 = appr3 != null ? Arrays.asList(appr3.split(",")) : new ArrayList<>();
			md.addAttribute("appr3", valappr3);

			// Check and split the alert values
			String alr1 = check.getAlert1();
			List<String> valalr1 = alr1 != null ? Arrays.asList(alr1.split(",")) : new ArrayList<>();
			md.addAttribute("alr1", valalr1);

			String alr2 = check.getAlert2();
			List<String> valalr2 = alr2 != null ? Arrays.asList(alr2.split(",")) : new ArrayList<>();
			md.addAttribute("alr2", valalr2);

			String alr3 = check.getAlert3();
			List<String> valalr3 = alr3 != null ? Arrays.asList(alr3.split(",")) : new ArrayList<>();
			md.addAttribute("alr3", valalr3);
		}

// to set which form - valid values are "edit" , "add" & "list"
// md.addAttribute("CustomerKYC",
// CMGrepository.findAll(PageRequest.of(currentPage, pageSize)));

		md.addAttribute("riskmgntflag", "riskmgntflag");
		md.addAttribute("riskcatflag", "riskcatflag");

		return "PARAMETER";
	}

	@RequestMapping(value = "accountLedger", method = { RequestMethod.GET, RequestMethod.POST })
	public String accountLedger(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String acct_num, String keyword, Model md, HttpServletRequest req) {
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("formmode", "list");
			md.addAttribute("chartaccount", chart_Acc_Rep.getListoffice());
		} else if (formmode.equals("view")) {
			md.addAttribute("formmode", "view");
			md.addAttribute("dataList", tRAN_MAIN_TRM_WRK_REP.getList(acct_num));/* Journal Entries */
			md.addAttribute("chartaccount", chart_Acc_Rep.getaedit(acct_num));/* COA */

		}
		return "AccountLedger";
	}

	@RequestMapping(value = "VerifyScreens", method = RequestMethod.POST)

	@ResponseBody
	public String VerifyScreens(Model md, HttpServletRequest rq, @ModelAttribute Chart_Acc_Entity chart_Acc_Entity) {
		Chart_Acc_Entity up = chart_Acc_Entity;
		up.setEntity_flg("Y");
		up.setDel_flg("N");
		chart_Acc_Rep.save(up);
		String userid = (String) rq.getSession().getAttribute("USERID");
		// FOR AUIDT
		Long auditID = bglsBusinessTable_Rep.getAuditRefUUID();
		Optional<ResourceMaster> up1 = resourceMasterRepo.getuser1(userid);
		;
		ResourceMaster user = up1.get();

		BGLSBusinessTable_Entity audit = new BGLSBusinessTable_Entity();
		LocalDateTime currentDateTime = LocalDateTime.now();
		Date dateValue = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
		audit.setAudit_date(new Date());
		audit.setEntry_time(dateValue);
		audit.setEntry_user(userid);

		audit.setRemarks("Verified Successfully");
		audit.setAudit_table("BGLS_CHART_OF_ACCOUNTS");
		audit.setAudit_screen("CHART OF ACCOUNTS - VERIFY");
		audit.setEvent_id(userid);
		audit.setEvent_name(user.getUsername());
		// audit.setModi_details("Login Successfully");
		Optional<ResourceMaster> auth_user = resourceMasterRepo.getuser1(userid);
		// String auth_user_val = auth_user.getAuth_user();
		// Date auth_user_date = auth_user.getAuth_time();
		audit.setAuth_user(userid);
		audit.setAuth_time(dateValue);
		audit.setAudit_ref_no(auditID.toString());
		audit.setField_name("-");

		bglsBusinessTable_Rep.save(audit);
		return "Verified Successfully";

	}

	@Autowired
	Account_Ledger_Rep account_Ledger_Rep;

	@RequestMapping(value = "accountLedgerPost", method = { RequestMethod.GET, RequestMethod.POST })
	public String accountLedger(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String acct_num, @RequestParam(required = false) String part_tran,
			@RequestParam(required = false) String tran_idss, @RequestParam(required = false) String part_transs,
			@RequestParam(required = false) String tran_id, @RequestParam(required = false) String part_tran_id,
			Model md, HttpServletRequest rq) {

		String user = (String) rq.getSession().getAttribute("USERID");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("jour", tRAN_MAIN_TRM_WRK_REP.findByjournal());
			md.addAttribute("user_id", user);
			md.addAttribute("formmode", "list");

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", "add");
		} else if (formmode.equals("add1")) {

			md.addAttribute("formmode", "add1");

		} else if (formmode.equals("massentires")) {
			md.addAttribute("formmode", "massentires");
		} else if (formmode.equals("verify")) {

			md.addAttribute("formmode", "verify");
			md.addAttribute("jour", tRAN_MAIN_TRM_WRK_REP.findByjournalvalues(tran_id));
			md.addAttribute("Acctnum", acct_num);
			md.addAttribute("ledgervalues", tRAN_MAIN_TRM_WRK_REP.getValuepopvalues(tran_id, acct_num, part_tran_id));

			md.addAttribute("currentPartTran", part_tran_id);
			md.addAttribute("maxPartTran", tRAN_MAIN_TRM_WRK_REP.maxPartranID(tran_id));
			md.addAttribute("gldetails", chart_Acc_Rep.getlistpopupvalues(acct_num));

		} else if (formmode.equals("view")) {
			md.addAttribute("formmode", "view");
			md.addAttribute("ledgervalues", account_Ledger_Rep.getValuepop(tran_id, acct_num, part_tran_id));
		} else if (formmode.equals("modify")) {
			// md.addAttribute("jour", account_Ledger_Rep.getjourform(acct_num));
			md.addAttribute("formmode", "modify");

		}

		return "AccountLedgerPost";
	}

	@RequestMapping(value = "journalEntries", method = { RequestMethod.GET, RequestMethod.POST })
	public String journalEntries(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String acct_num, @RequestParam(required = false) String part_tran,
			@RequestParam(required = false) String tran_idss, @RequestParam(required = false) String part_transs,
			@RequestParam(required = false) String tran_id, @RequestParam(required = false) String part_tran_id,
			@RequestParam(required = false) String account_number,
			@RequestParam(required = false) String loan_sanctioned, @RequestParam(required = false) String account_name,
			@RequestParam(required = false) String schm_type, @RequestParam(required = false) String flow_code,
			@RequestParam(required = false) String flow_date, @RequestParam(required = false) String flow_amount,
			@RequestParam(required = false) String account_no, @RequestParam(required = false) String currency,
			@RequestParam(required = false) String accountName, Model md, HttpServletRequest rq) {

		String user = (String) rq.getSession().getAttribute("USERID");
		String user1 = (String) rq.getSession().getAttribute("BRANCH_ID");

		String userId = (String) rq.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));

		String fullTranID1 = "TR" + tRAN_MAIN_TRM_WRK_REP.gettrmRefUUID1();
		md.addAttribute("plusonetran2", fullTranID1);

		if (formmode == null || formmode.equals("add")) {

			md.addAttribute("part_tran", part_tran);

			String fullTranID = "TR" + tRAN_MAIN_TRM_WRK_REP.gettrmRefUUID1();

			/* NUmber part increment 1 */
			String tranIdNUm = fullTranID.substring(3);
			int newTranNUm = Integer.parseInt(tranIdNUm) + 1;

			/* Letter part get */
			String tranLetterPart = fullTranID.substring(0, 3);

			if (account_number != null) {
				md.addAttribute("accountnumbervalue", account_number);
			} else {

			}

			if (loan_sanctioned != null) {
				md.addAttribute("totalamountvalue", loan_sanctioned);
			} else {

				md.addAttribute("totalamountvalue", 0); // Set a default value
			}

			if (account_name != null) {
				md.addAttribute("accountnamevalue", account_name);
			} else {

			}

			/* from flow time */

			if (account_no != null) {
				md.addAttribute("accountnumber", account_no);
				md.addAttribute("currencyvalue", "SCR");

			} else {

			}

			if (flow_amount != null) {
				md.addAttribute("flowamount", flow_amount);
			} else {

			}

			if (flow_code != null) {
				md.addAttribute("flowcodes", flow_code);
			} else {

			}

			if (flow_date != null) {

				String[] flowTime = flow_date.split("-");
				String FlowDateSend = flowTime[0] + "/" + flowTime[1] + "/" + flowTime[2];
				md.addAttribute("flowdate", FlowDateSend);
			} else {

			}

			if (accountName != null) {
				md.addAttribute("accountnamevalue", accountName);
			} else {

			}

			if (currency != null) {
				md.addAttribute("currencyvalue", "SCR");
			} else {

			}

			if (schm_type != null) {
				if (schm_type.equals("LA")) {
					md.addAttribute("accounttypevalue", "LeasyLoan");
				} else if (schm_type.equals("TD")) {
					md.addAttribute("accounttypevalue", "TermLoan");
				} else {
					md.addAttribute("accounttypevalue", ""); // Default case
				}
			} else {

			}

			// This will be reflected in your frontend

			md.addAttribute("plusonetran", tranLetterPart + newTranNUm);
			md.addAttribute("plusonetran1", fullTranID);
			md.addAttribute("popup", chart_Acc_Rep.getlistpopup());
			// md.addAttribute("popupvalues", depositRep.getdatavalues());
			// md.addAttribute("accountvalues", lease_Loan_Master_Repo.getCSlist());
			md.addAttribute("currentDate", new Date());
			md.addAttribute("partTranId", "1");
			md.addAttribute("user", user);
			md.addAttribute("tranStatus", "ENTERED");
			// md.addAttribute("popup", account_Ledger_Rep.popup());
			// System.out.println("123456789"+account_Ledger_Rep.popup());

			md.addAttribute("formmode", "add");
		} else if (formmode.equals("list1")) {

			md.addAttribute("jour", tRAN_MAIN_TRM_WRK_REP.findByjournal());
			md.addAttribute("formmode", "list1");

		} else if (formmode.equals("add1")) {

			/*
			 * md.addAttribute("part_transs", part_transs); md.addAttribute("tran_idss",
			 * tran_idss); md.addAttribute("part_tran", part_tran); String a =
			 * account_Ledger_Rep.getlast(); String b = a.substring(3); int c =
			 * Integer.parseInt(b) + 1; String d = a.substring(0, 3);
			 * md.addAttribute("plusonetran", d + c);
			 */
			md.addAttribute("formmode", "add1");
			// md.addAttribute("popup", account_Ledger_Rep.popup());
			md.addAttribute("popup", chart_Acc_Rep.getlistpopup());

		} else if (formmode.equals("massentires")) {
			md.addAttribute("formmode", "massentires");
		} else if (formmode.equals("verify")) {

			md.addAttribute("formmode", "verify");
			// md.addAttribute("ledgervalues", account_Ledger_Rep.getValuepop(tran_id,
			// acct_num, part_tran_id));

		} else if (formmode.equals("view")) {
			md.addAttribute("formmode", "view");
			md.addAttribute("jour", tRAN_MAIN_TRM_WRK_REP.findByjournalvalues(tran_id));
			md.addAttribute("Acctnum", acct_num);
			md.addAttribute("ledgervalues", tRAN_MAIN_TRM_WRK_REP.getValuepopvalues(tran_id, acct_num, part_tran_id));
			md.addAttribute("currentPartTran", part_tran_id);
			System.out.println("part_tran_id" + part_tran_id);
			md.addAttribute("maxPartTran", tRAN_MAIN_TRM_WRK_REP.maxPartranID(tran_id));
			// md.addAttribute("gldetails", chart_Acc_Rep.getlistpopupvalues(acct_num));
		} else if (formmode.equals("modify")) {
			// md.addAttribute("jour", account_Ledger_Rep.getjourform(acct_num));
			md.addAttribute("formmode", "modify");
			md.addAttribute("jour", tRAN_MAIN_TRM_WRK_REP.findByjournal());
			md.addAttribute("ledgervalues", tRAN_MAIN_TRM_WRK_REP.getValuepopvalues(tran_id, acct_num, part_tran_id));
			md.addAttribute("currentPartTran", part_tran_id);
			System.out.println("part_tran_id" + part_tran_id);
			md.addAttribute("maxPartTran", tRAN_MAIN_TRM_WRK_REP.maxPartranID(tran_id));
			// md.addAttribute("gldetails", chart_Acc_Rep.getlistpopupvalues(acct_num));
		} else if (formmode.equals("modify1")) {
			// md.addAttribute("jour", account_Ledger_Rep.getjourform(acct_num));
			md.addAttribute("formmode", "modify1");
			md.addAttribute("ledgervalues", tRAN_MAIN_TRM_WRK_REP.getValuepopvalues(tran_id, acct_num, part_tran_id));
			md.addAttribute("jour", tRAN_MAIN_TRM_WRK_REP.findByjournalvalues(tran_id));
			md.addAttribute("currentPartTran", part_tran_id);
			System.out.println("part_tran_id" + part_tran_id);
			md.addAttribute("maxPartTran", tRAN_MAIN_TRM_WRK_REP.maxPartranID(tran_id));
			System.out.println("maxPartTran" + tRAN_MAIN_TRM_WRK_REP.countPartTranIDs(tran_id));
			md.addAttribute("tableparttran", tRAN_MAIN_TRM_WRK_REP.currentTableRecords(tran_id));
			System.out.println(tRAN_MAIN_TRM_WRK_REP.currentTableRecords(tran_id) + "tableparttran");
			// md.addAttribute("gldetails", chart_Acc_Rep.getlistpopupvalues(acct_num));
		} else if (formmode.equals("view2")) {
			// md.addAttribute("jour", account_Ledger_Rep.getjourform(acct_num));
			md.addAttribute("formmode", "view2");
			md.addAttribute("part_tran", part_tran);

			// String fullTranID = "TR" + tRAN_MAIN_TRM_WRK_REP.gettrmRefUUID1();

			/* NUmber part increment 1 */
			// String tranIdNUm = fullTranID.substring(3);
			// int newTranNUm = Integer.parseInt(tranIdNUm) + 1;

			/* Letter part get */
			// String tranLetterPart = fullTranID.substring(0, 3);

			// md.addAttribute("plusonetran", tranLetterPart + newTranNUm);
			// md.addAttribute("popup", chart_Acc_Rep.getlistpopup());
			md.addAttribute("currentDate", new Date());
			md.addAttribute("partTranId", "1");
			md.addAttribute("user", user);
			md.addAttribute("tranStatus", "ENTERED");
			// md.addAttribute("popup", account_Ledger_Rep.popup());
			// System.out.println("123456789"+account_Ledger_Rep.popup());
		}

		return "JournalEntries";
	}

	@RequestMapping(value = "addtransactiondata", method = RequestMethod.POST)
	@ResponseBody
	public String addTransactionDatas(Model md, HttpServletRequest rq,
			@RequestBody List<TRAN_MAIN_TRM_WRK_ENTITY> transactionDetails) {
		List<TRAN_MAIN_TRM_WRK_ENTITY> transactionsToSave = new ArrayList<>();
		String tranid = "";
		for (TRAN_MAIN_TRM_WRK_ENTITY transaction : transactionDetails) {
			tranid = transaction.getTran_id();
			String nextSerialNumber = tRAN_MAIN_TRM_WRK_REP.gettrmRefUUID(); // Fetch the next SRLNO
			transaction.setSrl_no(nextSerialNumber); // Set the serial number manually
			transactionsToSave.add(transaction);

		}

		tRAN_MAIN_TRM_WRK_REP.saveAll(transactionsToSave);
		return "TRAN ID " + tranid + " Saved Successfully";
	}

	@RequestMapping(value = "trialBalanceReports1", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Object[]> trialBalanceReports1(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date balancedate,
			@RequestParam(required = false) String tran, Model md, HttpServletRequest rq) {

		List<Object[]> balances = dAB_Repo.getbalance(balancedate);

		// Convert List<Object[]> to List<Map<String, Object>>
		List<Map<String, Object>> result = new ArrayList<>();
		for (Object[] row : balances) {
			Map<String, Object> rowMap = new HashMap<>();
			rowMap.put("primary_gl_desc", row[0]); // gl_desc AS primary_gl_desc
			rowMap.put("gl_code", row[1]); // gl_code
			rowMap.put("glsh_code", row[2]); // glsh_code
			rowMap.put("total_credit", row[3]); // total_credit
			rowMap.put("total_debit", row[4]); // total_debit
			rowMap.put("acct_crncy", row[5]); // acct_crncy
			result.add(rowMap);
		}
		return balances;
	}

	@RequestMapping(value = "TB", method = { RequestMethod.GET, RequestMethod.POST })
	public String TB(HttpServletRequest req, @RequestParam(required = false) String formmode, String poid, Model model,
			@RequestParam(required = false) String id, @RequestParam(value = "mode", required = false) String mode) {
		String userId = (String) req.getSession().getAttribute("USERID");
		model.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));

		List<General_journal_entity> lists = General_journal_rep.findsall();
		model.addAttribute("list", lists);

		List<EmployeeSalary> lis = EmployeeSalaryRep.getlist();
		model.addAttribute("salaries", lis);
		List<BhmsInvProductSaleMasterTable> list = bhmsInvProductSaleMasterrep.findAllUniqueSaleIds();
		model.addAttribute("sales", list);

		// Purchase
		List<General_journal_entity> Purchase = General_journal_rep.findsallp();
		model.addAttribute("Purchase", Purchase);

		// work order
		List<General_journal_entity> wo = General_journal_rep.findsallWORKORDER();
		model.addAttribute("wo", wo);
		// sales
		List<BhmsInvProductSaleMasterTable> sales = bhmsInvProductSaleMasterrep.findAllUniqueSaleIds();
		model.addAttribute("sales", sales);
		// salaries
		List<EmployeeSalary> salaries = EmployeeSalaryRep.getlist();
		model.addAttribute("salaries", salaries);
		model.addAttribute("cap", btmAdminOrganizationMasterRep.getOrganization());

		return "TB.html";

	}

	/* ----Expenses--- */

	@RequestMapping(value = "expenses", method = { RequestMethod.GET, RequestMethod.POST })
	public String expenses(@RequestParam(required = false) String formmode, Model md, HttpServletRequest rq) {
		String user = (String) rq.getSession().getAttribute("USERID");

		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("formmode", "list");

			md.addAttribute("getexpenses", expenses_repo.getEXPLIST());

		} else if (formmode.equals("addexpenses")) {
			md.addAttribute("formmode", "addexpenses");

		}

		return "Expenses";
	}
	/*----GRN*/

	@RequestMapping(value = "GRN", method = { RequestMethod.GET, RequestMethod.POST })
	public String GRN(@RequestParam(required = false) String formmode, @RequestParam(required = false) String poId,
			Model md, HttpServletRequest rq) {
		String user = (String) rq.getSession().getAttribute("USERID");

		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("formmode", "list");
			md.addAttribute("poList", po_repo.getqcforgrn());
		} else if (formmode.equals("viewpo")) {
			md.addAttribute("formmode", "viewpo");
			md.addAttribute("inquiry", po_repo.getinquiry(poId));
		}

		return "GRN_TSK";
	}

	/*----pdf downloadpo*/

	@GetMapping("/downloadPO")
	public ResponseEntity<InputStreamResource> downloadPdf(@RequestParam("poId") String poId)
			throws DocumentException, IOException {

		System.out.println("the inside the download");
		List<Purchase_Order_Entity> poList = po_repo.getdownloadpdfpo(poId);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Document document = new Document();
		PdfWriter.getInstance(document, out);
		document.open();

		// Font boldFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

		Paragraph header = new Paragraph("TSK ENTERPRISES");
		header.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(header);
		document.add(new Paragraph(" "));

		Paragraph title = new Paragraph("Purchase Order List");
		title.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(title);

		document.add(new Paragraph(" "));

		for (Purchase_Order_Entity po : poList) {

			document.add(new Paragraph("PO ID : " + po.getPoId()));
			document.add(new Paragraph("Vendor Name : " + po.getVendorName()));
			document.add(new Paragraph("Vendor Id : " + po.getVendorId()));
			document.add(new Paragraph("Address : " + po.getAddress()));
			document.add(new Paragraph("Item : " + po.getItem()));
			document.add(new Paragraph("Date : " + po.getDate()));
			document.add(new Paragraph("HSN: " + po.getHsnSacCode()));
			document.add(new Paragraph("Rate : " + po.getRate()));
			document.add(new Paragraph("Qty : " + po.getQty()));
			document.add(new Paragraph("Freight : " + po.getFreight()));
			document.add(new Paragraph("Payment Type : " + po.getPaymenttype()));
			document.add(new Paragraph("Payment Term: " + po.getPayment_terms()));
			document.add(new Paragraph("Freight Term: " + po.getFreight_terms()));
			document.add(new Paragraph("Delivery Term: " + po.getDelivery_terms()));
		}
		document.close();
		ByteArrayInputStream pdfStream = new ByteArrayInputStream(out.toByteArray());
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=PurchaseOrder_" + poId + ".pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(pdfStream));
	}

	/*-----workoredr-----*/
	@GetMapping("/downloadWorkOrder")
	public ResponseEntity<InputStreamResource> downloadWorkOrder(@RequestParam("workId") String workId)
			throws DocumentException, IOException {

		System.out.println("the inside the download");
		List<WorkOrder> woList = WorkOrderRep.downloadpdfwo(workId);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Document document = new Document();
		PdfWriter.getInstance(document, out);
		document.open();

		Paragraph header = new Paragraph("TSK ENTERPRISES");
		header.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(header);
		document.add(new Paragraph(" "));
		document.add(new Paragraph("WorkOrder List"));
		document.add(new Paragraph(" "));

		for (WorkOrder po : woList) {

			document.add(new Paragraph("WO ID : " + po.getWorkId()));
			document.add(new Paragraph("Vendor Name : " + po.getVendorName()));
			document.add(new Paragraph("Vendor Id : " + po.getVendorId()));
			document.add(new Paragraph("Address : " + po.getAddress()));
			document.add(new Paragraph("Item : " + po.getItem()));
			document.add(new Paragraph("Date : " + po.getDate()));
			document.add(new Paragraph("HSN: " + po.getHsnSacCode()));
			document.add(new Paragraph("Rate : " + po.getRate()));
			document.add(new Paragraph("Qty : " + po.getQty()));
			document.add(new Paragraph("Freight : " + po.getFreight()));
			document.add(new Paragraph("Payment Type : " + po.getPaymenttype()));
			document.add(new Paragraph("Payment Term: " + po.getPaymentTerms()));
			document.add(new Paragraph("Freight Term: " + po.getFreightTerms()));
			document.add(new Paragraph("Delivery Term: " + po.getDeliveryTerms()));
		}
		document.close();
		ByteArrayInputStream pdfStream = new ByteArrayInputStream(out.toByteArray());
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=WorkOrder_" + workId + ".pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(pdfStream));
	}

	@RequestMapping(value = "VendorCreation", method = { RequestMethod.GET, RequestMethod.POST })
	public String VendorCreation(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String vandor_id, Model md, HttpServletRequest req) {
		String loginuserid = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));

		md.addAttribute("RoleMenu", hrmsrepoo.getrole(loginuserid));
		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null) {
			md.addAttribute("formmode", "list");
			md.addAttribute("getvendor", VendorCreationRep.getvendorlist1());

			List<String> OrgIds = TSK_OrganizationMasterReps.get_org();
			md.addAttribute("OrgIds", OrgIds);
			
		} else if ("add".equals(formmode)) {
			md.addAttribute("formmode", "add");
			for (int i = 1; i <= 8; i++) {
				md.addAttribute("Chart" + i, reference_code_Rep.getReferenceCode("COA_0" + i));
			}
			BigInteger accno = erp_chartOfAccountsrep.getaccountnumber();
			accno = accno.add(BigInteger.ONE);

			// md.addAttribute("getAccNo", "000011" + BHMS_services.getAccNo());
			md.addAttribute("getAccNo", "ACC000" + accno);


			List<String> OrgIds = TSK_OrganizationMasterReps.get_org();
			md.addAttribute("OrgIds", OrgIds);

		} else if ("modify".equals(formmode)) {
			if (vandor_id == null) {
				md.addAttribute("error", "Missing vendor ID or account number");
				return "ErrorPage";
			}
			md.addAttribute("formmode", "modify");
			md.addAttribute("getvendor", VendorCreationRep.getvendorlistid(vandor_id));
			for (int i = 1; i <= 8; i++) {
				md.addAttribute("Chart" + i, reference_code_Rep.getReferenceCode("COA_0" + i));
			}
			md.addAttribute("vandor_id", vandor_id);

			List<String> OrgIds = TSK_OrganizationMasterReps.get_org();
			md.addAttribute("OrgIds", OrgIds);
		} else if ("view".equals(formmode)) {
			if (vandor_id == null) {
				md.addAttribute("error", "Missing vendor ID or account number");
				return "ErrorPage";
			}
			md.addAttribute("formmode", "view");
			md.addAttribute("forms", "view");

			md.addAttribute("getvendor", VendorCreationRep.getvendorlistid(vandor_id));
			for (int i = 1; i <= 8; i++) {
				md.addAttribute("Chart" + i, reference_code_Rep.getReferenceCode("COA_0" + i));
			}
			md.addAttribute("vandor_id", vandor_id);
		} else if ("delete".equals(formmode)) {
			if (vandor_id == null) {
				md.addAttribute("error", "Missing vendor ID or account number");
				return "ErrorPage";
			}
			md.addAttribute("formmode", "view");
			md.addAttribute("forms", "delete");
			md.addAttribute("getvendor", VendorCreationRep.getvendorlistid(vandor_id));

			for (int i = 1; i <= 8; i++) {
				md.addAttribute("Chart" + i, reference_code_Rep.getReferenceCode("COA_0" + i));
			}
			md.addAttribute("vandor_id", vandor_id);
		}
		return "VendorCreation";
	}

	/*----Item creation---*/

	@RequestMapping(value = "ItemCreation", method = { RequestMethod.GET, RequestMethod.POST })
	public String ItemCreation(@RequestParam(required = false) String formmode, String itemcode,
			@RequestParam(required = false) String poId, Model md, HttpServletRequest rq) {
		String loginuserid = (String) rq.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(loginuserid));

		md.addAttribute("menu", "BTMHeaderMenu");
		Session session = sessionFactory.getCurrentSession();
		if (formmode == null) {
			md.addAttribute("formmode", "list");
			md.addAttribute("getitemlist", ItemCreationRep.getitemlist());

		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");
			BigInteger id = ItemCreationRep.getItemCode();
			id = id.add(BigInteger.ONE);
			md.addAttribute("itemcode", "ITEM00" + id);
			System.out.println("itemcode" + id);
			md.addAttribute("getCategory", CategoryRep.getCategorylist());
			// md.addAttribute("getCategory",
			// category_creationRep.getcategory_creationlist());

			md.addAttribute("CategoryNo", "C00" + CategoryRep.getCategoryNo().add(BigInteger.ONE));
		} else if (formmode.equals("Modify")) {
			md.addAttribute("formmode", "Modify");

			ItemCreation list = ItemCreationRep.getitemlistbyid(itemcode);
			byte[] imageBytes = list.getUploadeImage();
			if (imageBytes != null) {
				String base64Image = Base64.getEncoder().encodeToString(imageBytes);
				md.addAttribute("image", "data:image/jpeg;base64," + base64Image);
			} else {
				md.addAttribute("image", null);
			}
			// md.addAttribute("getCategory",CategoryRep.getCategorylist() );
			md.addAttribute("getCategory", category_creationRep.getcategory_creationlist());
			md.addAttribute("CategoryNo", "Category00" + CategoryRep.getCategoryNo().add(BigInteger.ONE));
			md.addAttribute("list", list);
			md.addAttribute("getCategory", CategoryRep.getCategorylist());
		} else if (formmode.equals("view")) {
			md.addAttribute("formmode", "view");
			md.addAttribute("priceToggle", true);
			ItemCreation list = ItemCreationRep.getitemlistbyid(itemcode);

			byte[] imageBytes = list.getUploadeImage();
			if (imageBytes != null) {
				String base64Image = Base64.getEncoder().encodeToString(imageBytes);
				md.addAttribute("image", "data:image/jpeg;base64," + base64Image);
			} else {
				md.addAttribute("image", null);
			}

			md.addAttribute("list", list);
		}

		else if (formmode.equals("deleteview")) {
			md.addAttribute("formmode", "deleteview");
			md.addAttribute("priceToggle", true);
			ItemCreation list = ItemCreationRep.getitemlistbyid(itemcode);

			byte[] imageBytes = list.getUploadeImage();
			if (imageBytes != null) {
				String base64Image = Base64.getEncoder().encodeToString(imageBytes);
				md.addAttribute("image", "data:image/jpeg;base64," + base64Image);
			} else {
				md.addAttribute("image", null);
			}

			md.addAttribute("list", list);
		}

		return "ItemCr";
	}

	@RequestMapping(value = "Purchase_Invoice", method = { RequestMethod.GET, RequestMethod.POST })
	public String Purchase_Invoice(HttpServletRequest req, @RequestParam(required = false) String formmode, Model md,
			@RequestParam(value = "id", required = false) String id) {
		String userId = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(userId));

		if (formmode.equals("purchaseorder")) {
			md.addAttribute("formmode", "view");
			md.addAttribute("getitemlist", PURCHASE_ORDER_ENTITY_NEW_rep.getPOlist());
			List<PURCHASE_ORDER_ENTITY_NEW> POlist = PURCHASE_ORDER_ENTITY_NEW_rep.getbyid(id);
			System.out.println("The size is:" + POlist.size());
			// list of purchase
			md.addAttribute("PO_list", POlist);

			VendorCreation vendorlist = VendorCreationRep.getvendorlistid(POlist.get(0).getVendorId());
			md.addAttribute("vendorlist", vendorlist);
			md.addAttribute("poid", id);

			md.addAttribute("po_date", POlist.get(0).getOrderDate());
			md.addAttribute("po_referencenumber", POlist.get(0).getReferenceNumber());
			md.addAttribute("po_reference_date", POlist.get(0).getOrderDate());
			md.addAttribute("ordernumber", POlist.get(0).getOrderNo());
			md.addAttribute("po_condition", "");

		}

		else if (formmode.equals("purchaseInvoice")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("getitemlist", PO_invoice_reps.getbyid(id));
			// md.addAttribute("getitemlist",PURCHASE_ORDER_ENTITY_NEW_rep.getPOlist());
			List<PO_invoice_entity> POlist = PO_invoice_reps.getbyid(id);
			System.out.println("The size is:" + POlist.size());
			// list of purchase
			md.addAttribute("PO_list", POlist);

			VendorCreation vendorlist = VendorCreationRep.getvendorlistid(POlist.get(0).getVendorId());
			md.addAttribute("vendorlist", vendorlist);
			md.addAttribute("poid", id);

			md.addAttribute("po_date", POlist.get(0).getOrderDate());
			md.addAttribute("po_referencenumber", POlist.get(0).getReferenceNumber());
			md.addAttribute("po_reference_date", POlist.get(0).getOrderDate());
			md.addAttribute("ordernumber", POlist.get(0).getOrderNo());
			md.addAttribute("po_condition", "");

		}
		return "Purchase_Invoice.html";

	}

	@RequestMapping(value = "JURNALVOUCHER", method = { RequestMethod.GET, RequestMethod.POST })
	public String JURNALVOUCHER(HttpServletRequest req, @RequestParam(required = false) String formmode, Model md,
			@RequestParam(value = "id", required = false) String id) {
		String userId = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(userId));

		if (formmode.equals("purchaseorder")) {
			md.addAttribute("formmode", "view");
			md.addAttribute("getitemlist", PURCHASE_ORDER_ENTITY_NEW_rep.getPOlist());
			List<PURCHASE_ORDER_ENTITY_NEW> POlist = PURCHASE_ORDER_ENTITY_NEW_rep.getbyid(id);
			System.out.println("The size is:" + POlist.size());
			// list of purchase
			md.addAttribute("PO_list", POlist);
			if (POlist.get(0).getPaymentType().equals("cash")) {

				md.addAttribute("paymenttype", "Cash");
			} else {
				md.addAttribute("paymenttype", "Bank");
			}

			VendorCreation vendorlist = VendorCreationRep.getvendorlistid(POlist.get(0).getVendorId());
			md.addAttribute("vendorlist", vendorlist);
			md.addAttribute("poid", id);

			md.addAttribute("currenttype", "purchaseorder");

			md.addAttribute("po_date", POlist.get(0).getOrderDate());
			md.addAttribute("po_referencenumber", POlist.get(0).getReferenceNumber());
			md.addAttribute("po_reference_date", POlist.get(0).getOrderDate());
			md.addAttribute("ordernumber", POlist.get(0).getOrderNo());
			md.addAttribute("po_condition", "");

		}

		else if (formmode.equals("purchaseInvoice")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("getitemlist", PO_invoice_reps.getbyid(id));
			// md.addAttribute("getitemlist",PURCHASE_ORDER_ENTITY_NEW_rep.getPOlist());
			List<PO_invoice_entity> POlist = PO_invoice_reps.getbyid(id);
			if (POlist.get(0).getPaymentType().equals("cash")) {

				md.addAttribute("paymenttype", "Cash");
			} else {
				md.addAttribute("paymenttype", "Bank");
			}

			List<PURCHASE_ORDER_ENTITY_NEW> Poorder = PURCHASE_ORDER_ENTITY_NEW_rep.getbyid(id);
			if (Poorder.get(0).getPaidAmount() > 0) {
				md.addAttribute("advance", "yes");
				md.addAttribute("advanceamount", Poorder.get(0).getPaidAmount());
			}

			md.addAttribute("currenttype", "purchaseInvoice");

			System.out.println("The size is:" + POlist.size());
			// list of purchase
			md.addAttribute("PO_list", POlist);

			VendorCreation vendorlist = VendorCreationRep.getvendorlistid(POlist.get(0).getVendorId());
			md.addAttribute("vendorlist", vendorlist);
			md.addAttribute("poid", id);

			md.addAttribute("po_date", POlist.get(0).getOrderDate());
			md.addAttribute("po_referencenumber", POlist.get(0).getReferenceNumber());
			md.addAttribute("po_reference_date", POlist.get(0).getOrderDate());
			md.addAttribute("ordernumber", POlist.get(0).getOrderNo());
			md.addAttribute("po_condition", "");

		}
		return "JURNALVOUCHER.html";

	}

	@RequestMapping(value = "Sales_Invoice", method = { RequestMethod.GET, RequestMethod.POST })
	public String Sales_Invoice(HttpServletRequest req, @RequestParam(required = false) String formmode, String poid,
			Model model, @RequestParam(required = false) String id,
			@RequestParam(value = "mode", required = false) String mode) {

		System.out.println("the id" + id);
		String userId = (String) req.getSession().getAttribute("USERID");
		model.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		if (formmode.equals("saleorder")) {

			model.addAttribute("getdetails", SALES_ORDER_ENTITY_NEW_rep.getparticular(id));

			List<SALES_ORDER_ENTITY_NEW> sales = SALES_ORDER_ENTITY_NEW_rep.getparticular(id);

			System.out.println("the size" + sales.size());

			model.addAttribute("salelist", sales);

			model.addAttribute("wo_id", id);
			model.addAttribute("orderdate", sales.get(0).getOrderDate());
			model.addAttribute("salerefnumber", sales.get(0).getReferenceNumber());

			/*---address*/
			model.addAttribute("billcompany", sales.get(0).getBill_companyname());
			model.addAttribute("shipcompany", sales.get(0).getShip_companyname());
			model.addAttribute("billaddress", sales.get(0).getBill_address());
			model.addAttribute("shipaddress", sales.get(0).getShip_address());
			model.addAttribute("billphone", sales.get(0).getBill_phno());
			model.addAttribute("billdis", sales.get(0).getBill_dis());
			model.addAttribute("billgst", sales.get(0).getBill_gstno());

			model.addAttribute("shipstreet", sales.get(0).getShip_street());
			model.addAttribute("shipphone", sales.get(0).getShip_phno());
			model.addAttribute("shipemail", sales.get(0).getShip_email());
			model.addAttribute("shipgst", sales.get(0).getShip_gstno());

			model.addAttribute("ordernumber", sales.get(0).getOrderNo());
		}

		else if (formmode.equals("saleinvoice")) {
			model.addAttribute("getdetails", SALES_invoice_TABLERep.getparticular(id));

			List<SALES_invoice_TABLE> sales = SALES_invoice_TABLERep.getparticular(id);

			System.out.println("the size" + sales.size());

			model.addAttribute("salelist", sales);

			model.addAttribute("wo_id", id);
			model.addAttribute("orderdate", sales.get(0).getOrderDate());
			model.addAttribute("salerefnumber", sales.get(0).getReferenceNumber());

			/*---address*/
			model.addAttribute("billcompany", sales.get(0).getBill_companyname());
			model.addAttribute("shipcompany", sales.get(0).getShip_companyname());
			model.addAttribute("billaddress", sales.get(0).getBill_address());
			model.addAttribute("shipaddress", sales.get(0).getShip_address());
			model.addAttribute("billphone", sales.get(0).getBill_phno());
			model.addAttribute("billdis", sales.get(0).getBill_dis());
			model.addAttribute("billgst", sales.get(0).getBill_gstno());

			model.addAttribute("shipstreet", sales.get(0).getShip_street());
			model.addAttribute("shipphone", sales.get(0).getShip_phno());
			model.addAttribute("shipemail", sales.get(0).getShip_email());
			model.addAttribute("shipgst", sales.get(0).getShip_gstno());
			model.addAttribute("ordernumber", sales.get(0).getOrderNo());

		}

		return "Sales_Invoice.html";

	}

	@RequestMapping(value = "Addvendor", method = { RequestMethod.POST })
	@ResponseBody
	public String Addvendor(@ModelAttribute VendorCreation VendorRegistrationadd, HttpServletRequest req) {

		String loginuserid = (String) req.getSession().getAttribute("USERID");
		System.out.println("classify is :" + VendorRegistrationadd.getClassify());
		return BHMS_services.addvandor(VendorRegistrationadd, loginuserid);
	}

	@RequestMapping(value = "AddCategoryCreation", method = { RequestMethod.POST })
	@ResponseBody
	public String CategoryCreationadd(@ModelAttribute Category Categoryadd, HttpServletRequest req) {

		String loginuserid = (String) req.getSession().getAttribute("USERID");

		return BHMS_services.addCategory(Categoryadd);
	}

	@RequestMapping(value = "ErpAddCategoryCreation", method = { RequestMethod.POST })
	@ResponseBody
	public String ErpAddCategoryCreation(@ModelAttribute category_creation erpCategoryadd, HttpServletRequest req) {

		String loginuserid = (String) req.getSession().getAttribute("USERID");

		return BHMS_services.adderpCategory(erpCategoryadd);
	}

	@PostMapping("/AddPo_submit")
	@Transactional
	public ResponseEntity<String> AddPo(@RequestParam(required = false) String formmode,
			@RequestBody List<PURCHASE_ORDER_ENTITY_NEW> list) {
		ResponseEntity<String> msg = null;
		try {
			if ("add".equals(formmode) || "edit".equals(formmode)) {
				System.out.println("Form mode: " + formmode);
				System.out.println("Received processEntityList: " + list);
				msg = BHMS_services.add_po(list, formmode);
				
				
	// This function call for notifications
	//notify.fitsmethod(list.get(0).getPoId(),list.get(0).getVendorId(),list.get(0).getTotalAmount(),"PURCHASE_ORDER");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
		return msg;
	}

	/*-----addwonew*/

	@PostMapping("/AddWo")
	@Transactional
	public ResponseEntity<String> AddWo(@RequestParam(required = false) String formmode,
			@RequestBody List<SALES_ORDER_ENTITY_NEW> list) {

		System.out.println("enter the controller for workorder");
		ResponseEntity<String> msg = null;
		try {
			if ("add".equals(formmode) || "edit".equals(formmode)) {
				System.out.println("Form mode: " + formmode);
				System.out.println("Received processEntityList: " + list);
				msg = BHMS_services.add_wonew(list, formmode);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
		return msg;
	}

	@PostMapping("/Addsaleinvoice")
	@Transactional
	public ResponseEntity<String> Addsaleivoice(@RequestParam(required = false) String formmode,
			@RequestBody List<SALES_invoice_TABLE> list) {

		System.out.println("enter the controller for workorder");
		ResponseEntity<String> msg = null;
		try {
			if ("add".equals(formmode) || "edit".equals(formmode)) {
				System.out.println("Form mode: " + formmode);
				System.out.println("Received processEntityList: " + list);
				msg = BHMS_services.saleinvoice_edit(list, formmode);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
		return msg;
	}

	@PostMapping("/directsaleinvoice")
	@Transactional
	public ResponseEntity<String> directsaleivoice(@RequestParam(required = false) String formmode,
			@RequestBody List<SALES_invoice_TABLE> list) {

		System.out.println("enter the controller for workorder");
		ResponseEntity<String> msg = null;
		try {
			if ("add".equals(formmode) || "edit".equals(formmode)) {
				System.out.println("Form mode: " + formmode);
				System.out.println("Received processEntityList: " + list);
				msg = BHMS_services.directsaleinvoice_edit(list, formmode);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
		return msg;
	}

	@PostMapping("/SaleOut")
	@Transactional
	public ResponseEntity<String> SaleOut(@RequestParam(required = false) String formmode,
			@RequestBody List<SalesOut> list) {

		System.out.println("enter the controller for workorder");
		ResponseEntity<String> msg = null;
		try {
			if ("add".equals(formmode) || "edit".equals(formmode)) {
				System.out.println("Form mode: " + formmode);
				System.out.println("Received processEntityList: " + list);
				msg = BHMS_services.saleOut(list, formmode);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
		return msg;
	}

	@RequestMapping(value = "modifyvendor", method = { RequestMethod.POST })
	@ResponseBody
	public String modifyvendor(@RequestParam(required = false) String vandor_id,
			@ModelAttribute VendorCreation VendorRegistrationadd, HttpServletRequest req) {
		VendorCreation list = VendorCreationRep.getvendorlistid(vandor_id);
		System.out.println("vendor type is: " + list.getVendorType());

		VendorRegistrationadd.setVendorId(vandor_id);
		VendorRegistrationadd.setVendorType(list.getVendorType());
		VendorRegistrationadd.setDelflg(list.getDelflg());
		VendorCreationRep.save(VendorRegistrationadd);
		return vandor_id + " Modified Successfully";
	}

	@RequestMapping(value = "modifyitem", method = { RequestMethod.POST })
	@ResponseBody
	public String modifyitem(@RequestParam(required = false) MultipartFile file,
			@ModelAttribute ItemCreation ItemCreationadd, HttpServletRequest req) {
		String loginuserid = (String) req.getSession().getAttribute("USERID");
		String filename = null;
		String filetype = null;
		byte[] byteArray = null;

		String itemcode1 = ItemCreationadd.getItemCode();

		if (file != null && !file.isEmpty()) {
			try {
				byteArray = file.getBytes();
				filename = file.getOriginalFilename();
				filetype = file.getContentType();
				System.out.println("Uploaded File: " + filename);
				ItemCreationadd.setUploadeImage(byteArray); // Set uploaded image
			} catch (IOException e) {
				e.printStackTrace();
				return "File upload failed due to an error.";
			}
		} else {
			// Fetch existing image if no file uploaded
			String itemcode = ItemCreationadd.getItemCode();
			ItemCreation list = ItemCreationRep.getitemlistbyid(itemcode);

			if (list != null) {
				ItemCreationadd.setUploadeImage(list.getUploadeImage()); // Set existing image
			} else {
				return "Item not found with the provided item code.";
			}
		}

		// Set the flags for modification and deletion
		ItemCreationadd.setModifyFlag("Y");
		ItemCreationadd.setDeleteFlag("N");

		// Save the updated item
		try {
			ItemCreationRep.save(ItemCreationadd);
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed to update the item. Please try again.";
		}

		return itemcode1 + " Modified Successfully";
	}

	@RequestMapping(value = "Additem", method = { RequestMethod.POST })
	@ResponseBody
	public String Additem(@RequestParam(required = false) MultipartFile file,
			@ModelAttribute ItemCreation ItemCreationadd, HttpServletRequest req) {

		String loginuserid = (String) req.getSession().getAttribute("USERID");
		String filename = null;
		String filetype = null;
		byte[] byteArray = null;

		if (file != null && !file.isEmpty()) {
			try {
				byteArray = file.getBytes();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			filename = file.getOriginalFilename();
			filetype = file.getContentType();
			System.out.println(filename);
		}
		ItemCreationadd.setUploadeImage(byteArray);

		return BHMS_services.additem(ItemCreationadd, loginuserid);
	}

	@RequestMapping(value = "deleteitemcode", method = { RequestMethod.POST })
	@ResponseBody
	public String deleteitemcode(@RequestParam(required = false) String itemcode, HttpServletRequest req) {

		String loginuserid = (String) req.getSession().getAttribute("USERID");
		ItemCreation list = ItemCreationRep.getitemlistbyid(itemcode);
		list.setDeleteFlag("Y");
		ItemCreationRep.save(list);
		return itemcode + "-Delete successfully";
	}

	@RequestMapping(value = "deletevendor", method = { RequestMethod.POST })
	@ResponseBody
	public String deletevendor(@RequestParam(required = false) String vandorid, HttpServletRequest req) {

		String loginuserid = (String) req.getSession().getAttribute("USERID");
		VendorCreation up = VendorCreationRep.getvendorlistid(vandorid);
		up.setDelflg("Y");
		VendorCreationRep.save(up);
		return vandorid + " Deleted successfully";
	}

	@RequestMapping(value = "Invoice_Modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String Invoice_Modify(HttpServletRequest req, Model md, String formmode,
			@RequestParam(required = false) String Opid) {
		System.out.println("purchase order screen");
		String loginuserid = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode.equals("list")) {
			md.addAttribute("formmode", "list");
			md.addAttribute("getitemlist", PURCHASE_ORDER_ENTITY_NEW_rep.getPOlist());
		} else if (formmode.equals("modify")) {
			md.addAttribute("formmode", "modify");

			// md.addAttribute("getvendor", VendorCreationRep.getvendorlist());
			md.addAttribute("ItemList", ItemCreationrep.getitemlist());
			md.addAttribute("getpocount", PURCHASE_ORDER_ENTITY_NEW_rep.getPOcount() + 1);
			md.addAttribute("getpo", PURCHASE_ORDER_ENTITY_NEW_rep.getbyid(Opid));
			md.addAttribute("POID", Opid);
			BTMAdminOrganizationMaster up = btmAdminOrganizationMasterRep.getOrganization();
			md.addAttribute("cashaccountbal", up);
		}

		return "Invoice_Modify.html";
	}

	/*---Modify invoice PO---*/
	@PostMapping("/invoice_edit")
	@Transactional
	public ResponseEntity<String> invoice_edit(@RequestParam(required = false) String formmode,
			@RequestBody List<PO_invoice_entity> list, @RequestParam(required = false) String Opid) {
		ResponseEntity<String> msg = null;
		try {
			if ("add".equals(formmode) || "edit".equals(formmode)) {
				BHMS_services.updatePO(Opid);
				System.out.println("Form mode: " + formmode);
				System.out.println("Opid: " + Opid);
				msg = BHMS_services.invoice_edit(list, formmode, Opid);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
		return msg;
	}

	/*---direct invoice PO---*/
	@PostMapping("/directinvoice_edit")
	public ResponseEntity<String> directinvoice_edit(@RequestParam(required = false) String formmode,
			@RequestBody List<PO_invoice_entity> list) {
		ResponseEntity<String> msg = null;
		try {
			if ("add".equals(formmode) || "edit".equals(formmode)) {
				System.out.println("Form mode: " + formmode);
				msg = BHMS_services.directinvoice_edit(list, formmode);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
		return msg;
	}

	@PostMapping("/invoice_edit_Payment_Out")
	@Transactional
	public ResponseEntity<String> invoice_edit_Payment_Out(@RequestParam(required = false) String formmode,
			@RequestBody List<PO_invoice_Pay_Out_entity> list, @RequestParam(required = false) String Opid) {
		ResponseEntity<String> msg = null;
		try {
			if ("add".equals(formmode) || "edit".equals(formmode)) {
				BHMS_services.updatePO(Opid);
				System.out.println("Total_vendor_balance " + list.get(0).getTotal_vendor_balance());
				System.out.println("Opid: " + Opid);
				msg = BHMS_services.invoice_edit_Pay_Out(list, formmode, Opid);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
		return msg;
	}

	@PostMapping("/invoice_edit_Payment_In")
	@Transactional
	public ResponseEntity<String> invoice_edit_Payment_In(@RequestParam(required = false) String formmode,
			@RequestBody List<SALES_invoice_TABLE_PAY_IN> list, @RequestParam(required = false) String Opid) {
		ResponseEntity<String> msg = null;
		try {
			if ("add".equals(formmode) || "edit".equals(formmode)) {
				BHMS_services.updatePO(Opid);
				System.out.println("Total_vendor_balance " + list.get(0).getTotal_vendor_balance());
				System.out.println("Opid: " + Opid);
				msg = BHMS_services.invoice_edit_Pay_in(list, formmode, Opid);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
		return msg;
	}

	@RequestMapping(value = "Inventory_po", method = { RequestMethod.GET, RequestMethod.POST })
	public String Inventory_po(HttpServletRequest req, Model md, String formmode,
			@RequestParam(required = false) String item_code) {
		System.out.println("purchase order screen");
		String loginuserid = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(loginuserid));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null) {
			md.addAttribute("formmode", "list");
			md.addAttribute("title", "currentlist");
			md.addAttribute("rawcurrent", PO_invoice_reps.BATCHWITHSTOCK());
			md.addAttribute("rawhistory", PO_invoice_reps.BATCHWITHOUTSTOCK());
			md.addAttribute("storesandspares", PO_invoice_reps.BATCHWITHSTOCKStoresandspares());
			md.addAttribute("fglist", FINISHED_GOODS_Rep.get_listgroup());
			System.out.println("the fglength" + FINISHED_GOODS_Rep.get_listgroup().size());
			md.addAttribute("processlist", TSK_PROCESS_REP.getprocessCompletedwithstock());
			md.addAttribute("getPackingMaterials", PO_invoice_reps.BATCHWITHSTOCKPackingMaterials());
			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);

		} else if (formmode.equals("withoutstock")) {
			md.addAttribute("formmode", "list");
			md.addAttribute("title", "historylist");
			md.addAttribute("rawcurrent", PO_invoice_reps.BATCHWITHOUTSTOCK());
			md.addAttribute("storesandspares", PO_invoice_reps.BATCHWITHOUTSTOCKStoresandspares());
			md.addAttribute("fglist", FINISHED_GOODS_Rep.get_listwithoutstock());
			md.addAttribute("processlist", TSK_PROCESS_REP.getprocessCompletedwithoutstock());
			md.addAttribute("itemSemiFinishedgoods", ItemCreationrep.getitemSemiFinishedgoods());
			md.addAttribute("getPackingMaterials", PO_invoice_reps.BATCHWITHOUTSTOCKPackingMaterials());
		} else if (formmode.equals("fglistcode")) {
			md.addAttribute("formmode", "fglist");
			md.addAttribute("fglist", FINISHED_GOODS_Rep.get_listbycode(item_code));

		} else if (formmode.equals("rawlistcode")) {
			md.addAttribute("formmode", "rawlistcode");
			md.addAttribute("rawcurrent", PO_invoice_reps.BATCHWITHSTOCKcode(item_code));

		}
		

		else if (formmode.equals("pmcode")) {
			md.addAttribute("formmode", "pmlistcode");
			md.addAttribute("rawcurrent", PO_invoice_reps.pmWITHSTOCKcode(item_code));

		}

		return "Inventory_po.html";
	}

	@Autowired
	SALES_invoice_TABLE_PAY_IN_REP SALES_invoice_TABLE_PAY_IN_rep;

	@RequestMapping(value = "paymentin", method = { RequestMethod.GET, RequestMethod.POST })
	public String Payment_in(HttpServletRequest req, Model md, String formmode,
			@RequestParam(required = false) String Opid) {
		System.out.println("sale order screen");
		String loginuserid = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(loginuserid));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null) {

			md.addAttribute("formmode", "history");
			md.addAttribute("getitemlist", SALES_invoice_TABLERep.getunique());
			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);

		} else if (formmode.equals("historyview")) {
			md.addAttribute("getvendor", VendorCreationRep.getvendorlist());
			md.addAttribute("ItemList", ItemCreationrep.getitemlistbyfg());
			System.out.println("the id" + Opid);
			md.addAttribute("formmode", "historyview");
			md.addAttribute("POID", Opid);

			md.addAttribute("getlist", SALES_invoice_TABLERep.getparticular(Opid));

			System.out.println("the testing" + SALES_ORDER_ENTITY_NEW_rep.getparticular(Opid));

		} else if (formmode.equals("list")) {

			md.addAttribute("formmode", "so_list");
			List<SALES_invoice_TABLE_PAY_IN> result = SALES_invoice_TABLE_PAY_IN_rep.getbyid();
			if (!result.isEmpty()) {
				md.addAttribute("getitemlist", result);
			} else {
				md.addAttribute("getitemlist", null);
				System.out.println("No matching row found for the query.");
			}

			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);

		} else if (formmode.equals("payinview")) {

			md.addAttribute("formmode", "payinview");
			Optional<PaymentIn> paymentDetails = PaymentInRep.findById(Opid);
			md.addAttribute("list", paymentDetails.get());
		}

		return "paymentin.html";

	}

	@RequestMapping(value = "balancevandor", method = { RequestMethod.POST })
	@ResponseBody
	public VendorCreation balancevandor(@RequestParam(required = false) String vandorid, HttpServletRequest req) {

		System.out.println(vandorid);
		VendorCreation up = VendorCreationRep.getvendorlistid(vandorid);

		return up;
	}

	@RequestMapping(value = "Purchase_Return", method = { RequestMethod.GET, RequestMethod.POST })
	public String Purchase_Return(HttpServletRequest req, Model md, String formmode,
			@RequestParam(required = false) String Opid) {
		System.out.println("purchase order screen");
		String loginuserid = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(loginuserid));

		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null) {
			md.addAttribute("getitemlist", PO_invoice_reps.getPOlistbywithqnt());
			md.addAttribute("formmode", "list");
			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);

		} else if (formmode.equals("modify")) {
			List<PO_invoice_entity> purchase = PO_invoice_reps.getbyid(Opid);
			String vendorid = "";
			for (PO_invoice_entity up1 : purchase) {
				vendorid = up1.getVendorId();
			}
			VendorCreation up = VendorCreationRep.getvendorlistid(vendorid);
			md.addAttribute("balance", up);
			md.addAttribute("formmode", "modify");
			md.addAttribute("getvendor", VendorCreationRep.getvendorlist());
			md.addAttribute("ItemList", ItemCreationrep.getitemlist());
			md.addAttribute("getpocount", PURCHASE_ORDER_ENTITY_NEW_rep.getPOcount() + 1);
			md.addAttribute("getpo", PO_invoice_reps.getbyid(Opid));
			md.addAttribute("POID", Opid);
		} else if (formmode.equals("return")) {
			md.addAttribute("formmode", "return");
			md.addAttribute("getitemlist", PO_Return_Reps.getPOlistbyapprovalnew1());
			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);

		}

		else if (formmode.equals("paidlist")) {
			md.addAttribute("formmode", "paidlist");
			md.addAttribute("getitemlist", PO_Return_Reps.getPOPaidlist());
			md.addAttribute("formmode", "paidlist");
		}

		else if (formmode.equals("return_view")) {
			md.addAttribute("formmode", "return_view");
			md.addAttribute("getvendor", VendorCreationRep.getvendorlist());
			md.addAttribute("ItemList", ItemCreationrep.getitemlist());
			md.addAttribute("getpocount", PO_Return_Reps.getPOcount() + 1);
			md.addAttribute("getpo", PO_Return_Reps.getbyid(Opid));
			md.addAttribute("POID", Opid);
		}

		return "Purchase_Return.html";

	}

	/*--- PO Return ---*/
	@PostMapping("/po_return_submit")
	@Transactional
	public ResponseEntity<String> po_return_submit(@RequestParam(required = false) String formmode,
			@RequestBody List<PO_Return_Entity> list, @RequestParam(required = false) String Opid) {
		ResponseEntity<String> msg = null;
		try {
			if ("add".equals(formmode) || "edit".equals(formmode)) {
				System.out.println("Form mode: " + formmode);
				System.out.println("Opid: " + Opid);
				msg = BHMS_services.po_return_submit(list, formmode, Opid);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
		return msg;
	}

	@RequestMapping(value = "addpaymentin", method = { RequestMethod.POST })
	@ResponseBody
	public String addpaymentin(@RequestParam(required = false) String vandor_id, String vendorname,
			@ModelAttribute PaymentIn Paymentinaddd, HttpServletRequest req) {

		String msg = BHMS_services.paymentin(Paymentinaddd, vandor_id, vendorname);

		return msg;
	}

	@Autowired
	PO_invoice_Pay_Out_Rep PO_invoice_Pay_Out_rep;

	@RequestMapping(value = "Payment_Out", method = { RequestMethod.GET, RequestMethod.POST })
	public String Payment_Out(HttpServletRequest req, Model md, String formmode,
			@RequestParam(required = false) String Opid) {
		System.out.println("purchase order screen");
		String loginuserid = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(loginuserid));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null) {
			md.addAttribute("formmode", "invoice_list");
			md.addAttribute("getitemlist", PO_invoice_reps.getPOlistbyapprovalnewPPAYOuT());
			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);
		} else if (formmode.equals("list")) {
			md.addAttribute("formmode", "list");
			List<PO_invoice_Pay_Out_entity> result = PO_invoice_Pay_Out_rep.getbyid();
			if (!result.isEmpty()) {
				md.addAttribute("getitemlist", result);
			} else {
				md.addAttribute("getitemlist", null);
				System.out.println("No matching row found for the query.");
			}

			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);

		} else if (formmode.equals("view")) {
			/*
			 * md.addAttribute("formmode", "view"); Optional<Pament_out> paymentDetails =
			 * Payment_Out_Reps.findById(Opid); md.addAttribute("list",
			 * paymentDetails.get());
			 */
			md.addAttribute("formmode", "invoice_view");
			md.addAttribute("POID", Opid);
			md.addAttribute("getvendor", VendorCreationRep.getvendorlist());
			md.addAttribute("ItemList", ItemCreationrep.getitemlist());
			md.addAttribute("getpocount", PO_invoice_reps.getPOcount());
			md.addAttribute("getpo", PO_invoice_Pay_Out_rep.getbyido(Opid));

		} else if (formmode.equals("invoice_view")) {
			md.addAttribute("formmode", "invoice_view");
			md.addAttribute("POID", Opid);
			md.addAttribute("getvendor", VendorCreationRep.getvendorlist());
			md.addAttribute("ItemList", ItemCreationrep.getitemlist());
			md.addAttribute("getpocount", PO_invoice_reps.getPOcount());
			md.addAttribute("getpo", PO_invoice_reps.getbyid(Opid));
			BTMAdminOrganizationMaster up = btmAdminOrganizationMasterRep.getOrganization();
			md.addAttribute("cashaccountbal", up);
		} else if (formmode.equals("add")) {
			md.addAttribute("getvendor", VendorCreationRep.getvendorlist());
			Integer id = Payment_Out_Reps.getlist().size() + 1;
			md.addAttribute("getreceiptno", id);
			md.addAttribute("poid", PO_invoice_reps.getPOlistbyapproval());
			md.addAttribute("formmode", "add");

		}

		return "Payment_Out.html";

	}

	@RequestMapping(value = "addpaymentout", method = { RequestMethod.POST })
	@ResponseBody
	public String addpaymentout(@RequestParam(required = false) String vandor_id, String vendorname,
			@ModelAttribute Pament_out Pament_out, HttpServletRequest req) {

		String msg = BHMS_services.Add_payment_out(vandor_id, vendorname, Pament_out);
		return msg;
	}

	@RequestMapping(value = "sale_Return", method = { RequestMethod.GET, RequestMethod.POST })
	public String sale_Return(HttpServletRequest req, Model md, String formmode,
			@RequestParam(required = false) String id) {
		System.out.println("purchase order screen");
		String loginuserid = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(loginuserid));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null) {
			md.addAttribute("formmode", "list");
			md.addAttribute("getitemlist", SALES_invoice_TABLERep.getsalesinvoicedetailsnew());

			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);

		} else if (formmode.equals("view")) {
			md.addAttribute("getvendor", VendorCreationRep.getvendorlist());
			md.addAttribute("ItemList", ItemCreationrep.getitemlist());

			System.out.println("the id" + id);
			md.addAttribute("formmode", "view");
			List<SALES_invoice_TABLE> sale = SALES_invoice_TABLERep.getparticular(id);
			String vendorid = "";
			for (SALES_invoice_TABLE up1 : sale) {
				vendorid = up1.getVendorId();
			}
			md.addAttribute("getlist", SALES_invoice_TABLERep.getparticular(id));
			VendorCreation up = VendorCreationRep.getvendorlistid(vendorid);
			md.addAttribute("balance", up);
			System.out.println("the testing" + SALES_ORDER_ENTITY_NEW_rep.getparticular(id));

		} else if (formmode.equals("return")) {
			md.addAttribute("formmode", "return");
			md.addAttribute("getitemlist", Sales_ReturnRep.getWOlistbyapprovalnew1());

			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);

		} else if (formmode.equals("return_view")) {
			md.addAttribute("formmode", "return_view");
			md.addAttribute("getvendor", VendorCreationRep.getvendorlist());
			md.addAttribute("ItemList", ItemCreationrep.getitemlist());
			md.addAttribute("getpocount", PO_Return_Reps.getPOcount() + 1);
			md.addAttribute("getlist", Sales_ReturnRep.getbyid1(id));
			md.addAttribute("getpo", Sales_ReturnRep.getbyid1(id));
			md.addAttribute("woID", id);
		}

		return "sale_Return.html";

	}

	@Autowired
	CategoryRep CategoryRep;

	@RequestMapping(value = "CategoryCreation", method = { RequestMethod.GET, RequestMethod.POST })
	public String CategoryCreation(@RequestParam(required = false) String formmode, String vandor_id, Model md,
			HttpServletRequest req) {
		String loginuserid = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null) {
			md.addAttribute("formmode", "list");
			md.addAttribute("getCategory", CategoryRep.getCategorylist());
		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");
			md.addAttribute("CategoryNo", CategoryRep.getCategoryNo().add(BigInteger.ONE));
			BigInteger Head_codeseq = CategoryRep.headcodeNo();
			String Head_code = BHMS_services.bigIntegerToAlphabet(Head_codeseq);
			System.out.println("Head_code" + Head_codeseq);
			md.addAttribute("Head_code", Head_code);
			md.addAttribute("Head_codedse", codecreationrep.getheadcode());
			md.addAttribute("CATEGORYCODE", CategoryRep.CATEGORYCODESEQNo());

		}

		else if (formmode.equals("modify")) {
			md.addAttribute("formmode", "modify");
			md.addAttribute("vandor_id", vandor_id);
			md.addAttribute("getvendor", VendorCreationRep.getvendorlistid(vandor_id));
		}
		return "CategoryCreation";
	}

	@PostMapping("/sale_returnadd")
	@Transactional
	public ResponseEntity<String> sale_return_submit(@RequestParam(required = false) String formmode,
			@RequestBody List<Sales_Return> list) {
		ResponseEntity<String> msg = null;
		try {
			if ("add".equals(formmode) || "edit".equals(formmode)) {
				System.out.println("Form mode: " + formmode);
				msg = BHMS_services.sale_return_submit(list, formmode);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
		return msg;
	}

	@RequestMapping(value = "GL_NEW", method = { RequestMethod.GET, RequestMethod.POST })
	public String GL_NEW(HttpServletRequest req, Model md, String formmode,
			@RequestParam(required = false) String Opid) {
		System.out.println("purchase order screen");
		String loginuserid = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null) {
			md.addAttribute("formmode", "list");
			md.addAttribute("vendorlist", VendorCreationRep.getvendorlist());
		} else if (formmode.equals("POlist")) {
			md.addAttribute("formmode", "Alllist");
			List<PO_invoice_entity> polist = PO_invoice_reps.vendorid(Opid);// PO List
			md.addAttribute("getpolist", polist);
			List<SALES_invoice_TABLE> salelist = SALES_invoice_TABLERep.getbyVENDOR(Opid);// Sale List
			md.addAttribute("getsalelist", salelist);
			List<PaymentIn> paymentList = PaymentInRep.getlistbyid(Opid);// Pay In List
			md.addAttribute("getitemlist", paymentList);

			List<Pament_out> payOut = Payment_Out_Reps.getlistbyid(Opid); // Pay Out List
			md.addAttribute("getPayout", payOut);

		
		} else if (formmode.equals("POview")) {
			md.addAttribute("formmode", "POview");
			md.addAttribute("getvendor", VendorCreationRep.getvendorlist());
			md.addAttribute("ItemList", ItemCreationrep.getitemlist());
			md.addAttribute("getpocount", PO_invoice_reps.getPOcount());
			md.addAttribute("getpo", PO_invoice_reps.getbyid(Opid));

		} else if (formmode.equals("Saleview")) {
			md.addAttribute("formmode", "Saleview");
			md.addAttribute("getvendor", VendorCreationRep.getvendorlist());
			md.addAttribute("ItemList", ItemCreationrep.getitemlist());
			md.addAttribute("getlist", SALES_invoice_TABLERep.getparticular(Opid));
		} else if (formmode.equals("payoutview")) {
			md.addAttribute("formmode", "payoutview");
			Optional<Pament_out> paymentDetails = Payment_Out_Reps.findById(Opid);
			md.addAttribute("list", paymentDetails.get());
		} else if (formmode.equals("payinview")) {
			md.addAttribute("formmode", "payinview");
			Optional<PaymentIn> paymentDetails = PaymentInRep.findById(Opid);
			md.addAttribute("list", paymentDetails.get());
		}

		return "GL_NEW.html";

	}

	@RequestMapping(value = "erpglcodes", method = { RequestMethod.GET, RequestMethod.POST })
	public String gl(@RequestParam(required = false) String formmode, @RequestParam(required = false) String glcode,
			Model md, HttpServletRequest request, @RequestParam(required = false) String glsh_Code) {

		if (formmode == null || formmode.equals("view")) {

			md.addAttribute("formmode", "list");

			md.addAttribute("getvaluelist", generalLedgerRep.getRefCodelist());

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", "add");

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", "edit");
			md.addAttribute("singlerecord", generalLedgerRep.getsinglevalue(glsh_Code));

		} else if (formmode.equals("delete")) {

			md.addAttribute("formmode", "delete");
			md.addAttribute("singlerecord", generalLedgerRep.getsinglevalue(glsh_Code));

		} else if (formmode.equals("viewusinglsh")) {

			md.addAttribute("formmode", "viewusinglsh");
			md.addAttribute("singlerecord", generalLedgerRep.getsinglevalue(glsh_Code));
		}

		return "gl.html";
	}

	@RequestMapping(value = "GeneralLedgerAdd", method = RequestMethod.POST)
	@ResponseBody
	public String GeneralLedgerAdd(@RequestParam("formmode") String formmode,
			@RequestParam(required = false) String GLSH_CODE, @ModelAttribute GeneralLedgerEntity generalLedgerEntity,
			Model md, HttpServletRequest rq) {
		System.out.println(GLSH_CODE);
		String userid = (String) rq.getSession().getAttribute("USERID");
		String msg = BHMS_services.addGeneralLedger(generalLedgerEntity, formmode, GLSH_CODE, userid);
		return msg;
	}

	@RequestMapping(value = "accountGL", method = { RequestMethod.GET, RequestMethod.POST })
	public String accountGL(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String acct_num, String keyword, Model md, String fromdate, String todate,
			HttpServletRequest req) {
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");

			System.out.println("The from date" + fromdate);
			System.out.println("the to date" + todate);

			if (fromdate == null && todate == null) {

				System.out.println(" normal list");
				List<Transaction_table> list = Transaction_table_Rep.getListoffice();
				md.addAttribute("chartaccount", list);
				BigDecimal totalCredits = list.stream()
						.map(chartacc -> chartacc.getCr_amt() != null ? chartacc.getCr_amt() : BigDecimal.ZERO)
						.reduce(BigDecimal.ZERO, BigDecimal::add);

				BigDecimal totalDebits = list.stream()
						.map(chartacc -> chartacc.getDr_amt() != null ? chartacc.getDr_amt() : BigDecimal.ZERO)
						.reduce(BigDecimal.ZERO, BigDecimal::add);

				md.addAttribute("totalCredits", totalCredits);
				md.addAttribute("totalDebits", "-" + totalDebits);
			} else {
				System.out.println("inside condition");
				// Fetch transactions within the given date range
				List<Transaction_table> transactionList = Transaction_table_Rep.getTransactionsByDate(fromdate, todate);
				md.addAttribute("chartaccount", transactionList);
				BigDecimal totalCredits = transactionList.stream()
						.map(chartacc -> chartacc.getCr_amt() != null ? chartacc.getCr_amt() : BigDecimal.ZERO)
						.reduce(BigDecimal.ZERO, BigDecimal::add);

				BigDecimal totalDebits = transactionList.stream()
						.map(chartacc -> chartacc.getDr_amt() != null ? chartacc.getDr_amt() : BigDecimal.ZERO)
						.reduce(BigDecimal.ZERO, BigDecimal::add);

				md.addAttribute("totalCredits", totalCredits);
				md.addAttribute("totalDebits", "-" + totalDebits);
			}

		}

		else if (formmode.equals("view")) {
			md.addAttribute("formmode", "view");
			List<Transaction_table> list = Transaction_table_Rep.getlistbyACC_no(acct_num);
			md.addAttribute("chartaccount", list);
			BigDecimal totalCredits = list.stream()
					.map(chartacc -> chartacc.getCr_amt() != null ? chartacc.getCr_amt() : BigDecimal.ZERO)
					.reduce(BigDecimal.ZERO, BigDecimal::add);

			BigDecimal totalDebits = list.stream()
					.map(chartacc -> chartacc.getDr_amt() != null ? chartacc.getDr_amt() : BigDecimal.ZERO)
					.reduce(BigDecimal.ZERO, BigDecimal::add);

			md.addAttribute("totalCredits", totalCredits);
			md.addAttribute("totalDebits", "-" + totalDebits);

		}
		return "accountGL.html";
	}

	/*----BillOfMaterial--*/
	@RequestMapping(value = "BillOfMaterial", method = { RequestMethod.GET, RequestMethod.POST })
	public String BillOfMaterial(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String acct_num, String bomId, String keyword, Model md,
			HttpServletRequest req) {

		String loginuserid = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(loginuserid));

		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("add")) {

			md.addAttribute("formmode", "add");
			md.addAttribute("rawmaterial", ItemCreationrep.getitemrawmaterial());
		} else if (formmode.equals("history")) {

			md.addAttribute("formmode", "history");
			md.addAttribute("bomdetails", pOM_REPO.getbomdetails());
			System.out.println("size" + pOM_REPO.getbomdetails());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("deleteoption", "view");
			// md.addAttribute("bomdetails", pOM_REPO.getbomdetails());
			// System.out.println("size" +pOM_REPO.getbomdetails());
			System.out.println("bomid" + bomId);

			md.addAttribute("bomdetails", pOM_REPO.getbomforview(bomId));
			md.addAttribute("getnullvalue", pOM_REPO.getbomforviewnull(bomId));

		}

		else if (formmode.equals("modify")) {

			md.addAttribute("formmode", "modify");
			// md.addAttribute("bomdetails", pOM_REPO.getbomdetails());
			// System.out.println("size" +pOM_REPO.getbomdetails());
			System.out.println("bomid" + bomId);

			md.addAttribute("bomdetails", pOM_REPO.getbomforview(bomId));
			md.addAttribute("getnullvalue", pOM_REPO.getbomforviewnull(bomId));
			md.addAttribute("rawmaterial", ItemCreationrep.getitemrawmaterial());

		} else if (formmode.equals("delete")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("deleteoption", "delete");
			md.addAttribute("bomdetails", pOM_REPO.getbomforview(bomId));
			md.addAttribute("getnullvalue", pOM_REPO.getbomforviewnull(bomId));
			System.out.println("bomid" + bomId);
		}

		return "BOM";
	}

	@RequestMapping(value = "getitemdetails1", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<ItemCreation> getitemdetails1(@RequestParam(value = "itemCode", required = false) String itemCode) {

		System.out.println("the enter");
		List<ItemCreation> response = ItemCreationRep.getdetailsitem(itemCode);
		System.out.println("the response" + response);
		return response;

	}

	/*-----forbatch/price*/

	@RequestMapping(value = "getbatch_price", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<PO_invoice_entity> getbatch_price(@RequestParam(value = "itemCode", required = false) String itemCode) {
		
		System.out.println("the enter2");
		List<PO_invoice_entity> response = PO_invoice_reps.getdetailbatch(itemCode);
		System.out.println("the responselllll" + response);
		return response;

	}
	
	
	@RequestMapping(value = "getbatch_priceforfg", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<FINISHED_GOODS_ENTITY> getbatch_pricefg(@RequestParam(value = "itemCode", required = false) String itemCode) {
		
		List<FINISHED_GOODS_ENTITY> up= FINISHED_GOODS_Rep.get_listbycode(itemCode);
		System.out.println("the enter3");
		return up;

	}
	
	

	@RequestMapping(value = "getbatchonly", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<PO_invoice_entity> getbatchonly(@RequestParam(value = "item", required = false) String item) {

		System.out.println("the item is :" + item);
		List<PO_invoice_entity> response = PO_invoice_reps.getbatchonly(item);
		
		System.out.println("the responselllll" + response);
		return response;

	}
	
	

	@RequestMapping(value = "get_two", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public PO_invoice_entity get_two(@RequestParam(value = "batch", required = false) String batch) {

		System.out.println("the enter");
		PO_invoice_entity response = PO_invoice_reps.get_twos(batch);
		System.out.println("the responselllll" + response);
		return response;

	}
	/*---bom add*/

	@PostMapping("/add_new_bom")
	@Transactional
	public ResponseEntity<String> add_new_bom(@RequestParam(required = false) String formmode,
			@RequestBody List<BOM_ENTITY> BOM_ENTITY) {
		ResponseEntity<String> msg = null;
		try {
			if ("add".equals(formmode) || "edit".equals(formmode)) {
				System.out.println("Form mode: " + formmode);
				System.out.println("Received processEntityList: " + BOM_ENTITY);
				msg = BHMS_services.addNewbom(BOM_ENTITY, formmode);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
		return msg;
	}

	@RequestMapping(value = "erpprocess", method = { RequestMethod.GET, RequestMethod.POST })
	public String erpprocess(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String acct_num, String keyword, Model md, HttpServletRequest req) {
		String loginuserid = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(loginuserid));
		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("add")) {

			md.addAttribute("formmode", "add");
			md.addAttribute("rawmaterial", PO_invoice_reps.getItems());
			md.addAttribute("bomdetails", pOM_REPO.getbomdetails());
			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);

		} else if (formmode.equals("history")) {

			md.addAttribute("formmode", "history");
			md.addAttribute("bomdetails", pOM_REPO.getbomdetails());
			System.out.println("size" + pOM_REPO.getbomdetails());

		}

		return "erpprocess";
	}

	@RequestMapping(value = "getbomproduct", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Object[]> getbomproduct(@RequestParam(value = "BOMID", required = true) String BOMID, Model md) {
		List<Object[]> response = pOM_REPO.getbom(BOMID);
		System.out.println("response-----" + response);
		md.addAttribute("rawmaterial", PO_invoice_reps.getItems());
		return response;

	}

	@RequestMapping(value = "Catcodemaintain", method = { RequestMethod.GET, RequestMethod.POST })
	public String Catcodemaintain(@RequestParam(required = false) String formmode, String headcode, Model md,
			HttpServletRequest req)

			throws ParseException {
//		String EmpId = "U72900TN2017PTC115892";
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("view")) {
			md.addAttribute("formmode", "add");

		}

		return "BAMCatCodeMain";
	}

	@RequestMapping(value = "deleteCategory", method = { RequestMethod.POST })
	@ResponseBody
	public String deleteCategory(@RequestParam(required = false) String Categoryid, HttpServletRequest req) {
		String msg = "";
		category_creation list = category_creationRep.getbycatid(Categoryid);
		if (list.getCategory_name().equals("Raw Materials") || list.getCategory_name().equals("Packing Materials")
				|| list.getCategory_name().equals("Storesandspares")) {
			list.setDel_flg("N");
			category_creationRep.save(list);
			msg = list.getCategory_id() + "-Unable To Delete ";
		} else {
			list.setDel_flg("Y");
			category_creationRep.save(list);
			msg = list.getCategory_id() + "-Delete successfully";
		}
		return msg;
	}

	@PostMapping("/add_process_tsk")
	@Transactional
	public ResponseEntity<String> add_process_tsk(@RequestParam(required = false) String formmode,
			@RequestBody List<TSK_PROCESS_ENTITY> TSK_PROCESS_ENTITY) {
		ResponseEntity<String> msg = null;
		try {
			if ("add".equals(formmode) || "edit".equals(formmode)) {
				System.out.println("Form mode: " + formmode);
				System.out.println("Received processEntityList: " + TSK_PROCESS_ENTITY);
				msg = adminOperServices.add_process_tsk(TSK_PROCESS_ENTITY, formmode);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
		return msg;
	}

	/*-----forDashboard---*/

	@PostMapping("/AddConvertToFG")
	public ResponseEntity<String> AddConvertToFG(@RequestParam(required = false) String formmode, String process_id,
			@RequestBody List<FINISHED_GOODS_ENTITY> list) {
		ResponseEntity<String> msg = null;
		try {
			if ("add".equals(formmode) || "edit".equals(formmode)) {
				System.out.println("Form mode: " + formmode);
				System.out.println("Received processEntityList: " + list);
				System.out.println("processid: " + process_id);
				msg = BHMS_services.add_ConvertToFG(list, formmode, process_id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
		return msg;
	}

	@RequestMapping(value = "ConvertToFG", method = { RequestMethod.GET, RequestMethod.POST })
	public String ConvertToFG(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String process_id, Model md, HttpServletRequest req) {
		String loginuserid = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(loginuserid));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null) {

			md.addAttribute("formmode", "sfglist");
			md.addAttribute("bomdetails", pOM_REPO.getbomdetails());
			md.addAttribute("processlist", TSK_PROCESS_REP.getprocessCompleted());
			System.out.println("size" + pOM_REPO.getbomdetails());
			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);

		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");
			md.addAttribute("process_id", process_id);

			md.addAttribute("processwithid", TSK_PROCESS_REP.getprocessbyiditem(process_id));
			md.addAttribute("rawmaterial", ItemCreationrep.getitemPackingMaterial());

		}

		return "Convertfg";
	}

	@RequestMapping(value = "FinishedGoods", method = { RequestMethod.GET, RequestMethod.POST })
	public String FinishedGoods(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String acct_num, String keyword, Model md, HttpServletRequest req) {

		String loginuserid = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(loginuserid));
		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null) {

			md.addAttribute("fglist", FINISHED_GOODS_Rep.get_listnew());
			md.addAttribute("formmode", "sfglist");
			md.addAttribute("bomdetails", pOM_REPO.getbomdetails());
			System.out.println("size" + pOM_REPO.getbomdetails());
			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);
		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");
			md.addAttribute("rawmaterial", ItemCreationrep.getitemPackingMaterial());
		}

		return "FinishedGoods";
	}

	@RequestMapping(value = "Validation", method = { RequestMethod.GET, RequestMethod.POST })
	public String Validation(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String process_id, Model md, HttpServletRequest req) {

		String loginuserid = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(loginuserid));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null) {

			md.addAttribute("processlist", TSK_PROCESS_REP.getprocessnew());
			md.addAttribute("formmode", "list");
			md.addAttribute("bomdetails", pOM_REPO.getbomdetails());
			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);

		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");
			md.addAttribute("processwithid", TSK_PROCESS_REP.getprocessbyiditem(process_id));
			md.addAttribute("processwithoutid", TSK_PROCESS_REP.getprocessbyidexp(process_id));

			md.addAttribute("rawmaterial", PO_invoice_reps.getItems());
			md.addAttribute("bomdetails", pOM_REPO.getbomdetails());

		}

		return "ErpValidation";
	}

	@RequestMapping(value = "getdetailsforgraph", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Integer> getdetailsforgraph(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date balancedate,
			@RequestParam(required = false) String tran, Model md, HttpServletRequest rq) {

		Integer balances = PO_invoice_reps.getdetails();
		Integer purchaseorder = PURCHASE_ORDER_ENTITY_NEW_rep.getPOcount();
		Integer saleinvoice = SALES_invoice_TABLERep.getcountfordashboard();
		Integer saleorder = SALES_ORDER_ENTITY_NEW_rep.getwOcount();
		Integer purchasereturn = PO_Return_Reps.getPOcount();
		Integer salereturn = Sales_ReturnRep.getcountfordashboard();
		Integer rawmaterials = PO_invoice_reps.getdetailsRAWMATERIALS();
		Integer packing = PO_invoice_reps.getdetailsPACKING();
		Integer fg = PO_invoice_reps.getdetailsfinished();
		Integer SS = PO_invoice_reps.getdetailsSS();

		Map<String, Integer> data = new HashMap<>();
		data.put("Purchaseorder", purchaseorder);
		data.put("purchaseinvoice", balances);
		data.put("saleinvoice", saleinvoice);
		data.put("saleorder", saleorder);
		data.put("purchasereturn", purchasereturn);
		data.put("salereturn", salereturn);
		data.put("RAWMATERIALS", rawmaterials);
		data.put("PACKINGMATERIALS", packing);
		data.put("FINISHEDGOODS", fg);
		data.put("STOREANDSPARE", SS);

		return data;
	}

	@RequestMapping(value = "deletebom", method = { RequestMethod.POST })
	@ResponseBody
	public String deletebom(@RequestParam(required = false) String bomId, HttpServletRequest req) {

		List<BOM_ENTITY> list = pOM_REPO.getbomfordelete(bomId);

		for (BOM_ENTITY changeflg : list) {

			changeflg.setDeleteFlag("Y");

			pOM_REPO.save(changeflg);
		}

		return list.get(0).getBomId() + "-Delete successfully";
	}

	@RequestMapping(value = "Validationadd", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String Validationadd(@RequestParam(required = false) String processid, BigDecimal returnquantity,
			String units, HttpServletRequest rq) {
		String userid = (String) rq.getSession().getAttribute("USERID");
		String msg = BHMS_services.validation(processid, units, returnquantity);
		;

		return msg;
	}

	@RequestMapping(value = "getitemdetailsbyunit", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<ItemCreation> getitemdetailsbyunit(@RequestParam(value = "unit", required = true) String unit) {
		List<ItemCreation> response = ItemCreationrep.getSinglebyunit(unit);
		System.out.println("the response" + response);
		return response;

	}

	@RequestMapping(value = "getvendor", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public VendorCreation getvendor(@RequestParam(value = "vendorid", required = true) String vendorid) {
		VendorCreation response = VendorCreationRep.getvendorlistid(vendorid);
		System.out.println("the response" + response);
		return response;

	}

	@RequestMapping(value = "getEmployee", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResourceMaster getEmployee(@RequestParam(value = "empid", required = true) String empid) {
		ResourceMaster response = resourceMasterRepo.getuserData(empid);
		System.out.println("the resource name is : " + response.getResource_name());
		System.out.println("the response" + response);
		if (response == null) {
			System.out.println("No data found for empid: " + empid);
		}

		return response;

	}

	@RequestMapping(value = "getfgdata", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<FINISHED_GOODS_ENTITY> getfgdata(@RequestParam(value = "code", required = true) String code) {
		List<FINISHED_GOODS_ENTITY> up = FINISHED_GOODS_Rep.get_listbycode(code);
		return up;

	}

	/*---Expenses---*/

	@Autowired
	Erp_exp_category_rep erp_exp_category_rep;
	@Autowired
	Exp_item_rep exp_item_rep;
	@Autowired
	ERP_EXPENSES_REPO eRP_EXPENSES_REPO;

	@RequestMapping(value = "ERPExpenses", method = { RequestMethod.GET, RequestMethod.POST })
	public String ERPExpenses(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String expId, Model md, HttpServletRequest request) {

		String loginuserid = (String) request.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(loginuserid));
		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("add")) {

			md.addAttribute("formmode", "add");
			md.addAttribute("expensesid", "EXP00" + erp_exp_category_rep.getCateCode().add(BigInteger.ONE));

			System.out.println("the cat id" + erp_exp_category_rep.getCateCode());
			md.addAttribute("expenseslist", erp_exp_category_rep.getcategory_creationlist());
			long row = erp_exp_category_rep.count();
			System.out.println("the expenses count" + row);
			md.addAttribute("expensesId", "EXPE00" + (row + 1));
			System.out.println("EXPENSES ID" + "EXPE00" + (row + 1));
			long itemrow = exp_item_rep.count();
			md.addAttribute("ExpitemId", "EXITEM00" + (itemrow + 1));
			md.addAttribute("ExpItemlist", exp_item_rep.getexpitem());
			md.addAttribute("EXPENSESS", "EXP00" + (eRP_EXPENSES_REPO.getexpid() + 1));

			BTMAdminOrganizationMaster list = btmAdminOrganizationMasterRep.getOrganization();
			md.addAttribute("cap_amt", list.getCapitalamount());
		}

		else if (formmode.equals("list")) {
			md.addAttribute("formmode", "list");
			md.addAttribute("explist", eRP_EXPENSES_REPO.getExplistsSS());
		}

		else if (formmode.equals("view")) {
			md.addAttribute("formmode", "view");
			List<ERP_EXPENSES_ENTITY> list = eRP_EXPENSES_REPO.getExplistsparticulr(expId);
			md.addAttribute("alllist", list);

			md.addAttribute("categoryname", list.get(0).getExpCategory());
			md.addAttribute("expid", list.get(0).getExpId());
			md.addAttribute("date", list.get(0).getExpDate());
			md.addAttribute("paymenttype", list.get(0).getPaymentType());
			md.addAttribute("total", list.get(0).getTotalAmount());
		} else if (formmode.equals("modify")) {
			md.addAttribute("formmode", "modify");
			// md.addAttribute("explist",eRP_EXPENSES_REPO.getExplistsparticulr(expId) );

			List<ERP_EXPENSES_ENTITY> list = eRP_EXPENSES_REPO.getExplistsparticulr(expId);
			md.addAttribute("alllist", list);

			md.addAttribute("categoryname", list.get(0).getExpCategory());
			md.addAttribute("expid", list.get(0).getExpId());
			md.addAttribute("date", list.get(0).getExpDate());
			md.addAttribute("paymenttype", list.get(0).getPaymentType());
			md.addAttribute("total", list.get(0).getTotalAmount());

			/* -----addfunction */
			md.addAttribute("expensesid", "EXP00" + erp_exp_category_rep.getCateCode().add(BigInteger.ONE));
			System.out.println("the cat id" + erp_exp_category_rep.getCateCode());
			md.addAttribute("expenseslist", erp_exp_category_rep.getcategory_creationlist());
			long row = erp_exp_category_rep.count();
			System.out.println("the expenses count" + row);
			md.addAttribute("expensesId", "EXPE00" + (row + 1));
			System.out.println("EXPENSES ID" + "EXPE00" + (row + 1));
			long itemrow = exp_item_rep.count();
			md.addAttribute("ExpitemId", "EXITEM00" + (itemrow + 1));
			md.addAttribute("ExpItemlist", exp_item_rep.getexpitem());
			md.addAttribute("EXPENSESS", "EXP00" + (eRP_EXPENSES_REPO.getexpid() + 1));
		}

		return "ERPExpenses";
	}

	/*----addexpcat--*/
	@RequestMapping(value = "ErpexpCategoryCreation", method = { RequestMethod.POST })
	@ResponseBody
	public String ErpexpCategoryCreation(@ModelAttribute Erp_exp_category erp_exp_category, HttpServletRequest req) {

		String loginuserid = (String) req.getSession().getAttribute("USERID");

		return BHMS_services.adderpexpCategory(erp_exp_category);
	}

	@RequestMapping(value = "Erpexpitem", method = { RequestMethod.POST })
	@ResponseBody
	public String Erpexpitem(@ModelAttribute Exp_itemcreation_Entity exp_itemcreation_Entity, HttpServletRequest req) {

		String loginuserid = (String) req.getSession().getAttribute("USERID");

		return BHMS_services.addexpitem(exp_itemcreation_Entity);
	}

	@RequestMapping(value = "getitemdetailsforexpenses", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Object[]> getitemdetailsforexpenses(@RequestParam(value = "code", required = true) String code) {
		List<Object[]> response = exp_item_rep.getSingleRowforexp(code);
		System.out.println("the response" + response);
		return response;

	}

	/*---expenses---*/

	@RequestMapping(value = "addexpenses", method = { RequestMethod.POST })
	@ResponseBody
	public String addexpenses(@RequestBody List<ERP_EXPENSES_ENTITY> eRP_EXPENSES_ENTITY, HttpServletRequest req) {

		String loginuserid = (String) req.getSession().getAttribute("USERID");

		return BHMS_services.addexpenses(eRP_EXPENSES_ENTITY);
	}

	@RequestMapping(value = "editsubmitexpenses", method = { RequestMethod.POST })
	@ResponseBody
	public String editsubmitexpenses(@RequestBody List<ERP_EXPENSES_ENTITY> eRP_EXPENSES_ENTITY,
			HttpServletRequest req) {

		String loginuserid = (String) req.getSession().getAttribute("USERID");

		return BHMS_services.editexpenses(eRP_EXPENSES_ENTITY);
	}

	@RequestMapping(value = "deletecat", method = { RequestMethod.POST })
	@ResponseBody
	public ResponseEntity<String> deletecat(@RequestParam(required = false) String catId) {
		Optional<Erp_exp_category> list = erp_exp_category_rep.findById(catId);
		if (list.isPresent()) {
			erp_exp_category_rep.delete(list.get());
			return ResponseEntity.ok("Category with ID " + catId + " deleted successfully.");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category with ID " + catId + " not found.");
		}
	}

	@RequestMapping(value = "TravelClaim", method = { RequestMethod.GET, RequestMethod.POST })
	public String TravelClaim(@RequestParam(required = false) String formmode, String itemcode, String travid,
			@RequestParam(required = false) String poId, Model md, HttpServletRequest rq) {
		String loginuserid = (String) rq.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));
		md.addAttribute("menu", "BTMHeaderMenu");
		Session session = sessionFactory.getCurrentSession();
		if (formmode == null) {
			md.addAttribute("formmode", "list");
			md.addAttribute("getitemlist", travelClaimrep.climelist());

		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");
			md.addAttribute("adminAssociateProfileList", btmAdminAssociateProfileRep.getEmployeedetail2());
			BigInteger id = ItemCreationRep.getItemCode();
			id = id.add(BigInteger.ONE);
			md.addAttribute("itemcode", "ITEM00" + id);
			System.out.println("itemcode" + id);
			// md.addAttribute("getCategory",CategoryRep.getCategorylist() );
			md.addAttribute("getallowance", Allowancemakerrep.getbyall());

			md.addAttribute("CategoryNo", "C00" + CategoryRep.getCategoryNo().add(BigInteger.ONE));
		} else if (formmode.equals("Modify")) {
			md.addAttribute("formmode", "Modify");
			md.addAttribute("adminAssociateProfileList", btmAdminAssociateProfileRep.getEmployeedetail2());
			md.addAttribute("TravelClaim", travelClaimrep.climelistbyid(travid));
		} else if (formmode.equals("view")) {
			md.addAttribute("formmode", "view");
			md.addAttribute("adminAssociateProfileList", btmAdminAssociateProfileRep.getEmployeedetail2());
			md.addAttribute("TravelClaim", travelClaimrep.climelistbyid(travid));

		}

		else if (formmode.equals("verify")) {
			md.addAttribute("formmode", "verify");
			// md.addAttribute("formmode","verifyopreation");
			md.addAttribute("adminAssociateProfileList", btmAdminAssociateProfileRep.getEmployeedetail2());
			md.addAttribute("TravelClaim", travelClaimrep.climelistbyid(travid));

		}

		else if (formmode.equals("deleteview")) {
			md.addAttribute("formmode", "deleteview");
			md.addAttribute("adminAssociateProfileList", btmAdminAssociateProfileRep.getEmployeedetail2());
			md.addAttribute("TravelClaim", travelClaimrep.climelistbyid(travid));
		}

		return "TravelClaim";
	}

	@RequestMapping(value = "verifytraval", method = { RequestMethod.POST })
	@ResponseBody
	public String verifytraval(@RequestParam(required = false) String id, HttpServletRequest req) {
		String msg = "";

		System.out.println("the traval id" + id);

		String loginuserid = (String) req.getSession().getAttribute("USERID");

		System.out.println("the session id" + loginuserid);

		Optional<TravelClaim> up = travelClaimrep.findById(id);

		TravelClaim getdata = up.get();

		if (getdata.getEmployeeId().equals(loginuserid)) {

			msg = "Same User cannot verify";
		} else {
			getdata.setVerify_flg("Y");
			travelClaimrep.save(getdata);

			msg = getdata.getId() + "verified";
		}

		return msg;
	}

	@RequestMapping(value = "ErpAddallowancemaker", method = { RequestMethod.POST })
	@ResponseBody
	public String ErpAddallowancemaker(@ModelAttribute AllowanceMaker AllowanceMakeradd, HttpServletRequest req) {

		String loginuserid = (String) req.getSession().getAttribute("USERID");
		AllowanceMakeradd.setEntryUser(loginuserid);
		return BHMS_services.addallowancemaker(AllowanceMakeradd);
	}

	@RequestMapping(value = "deleteallowancemaker", method = { RequestMethod.POST })
	@ResponseBody
	public String deleteallowancemaker(@RequestParam(required = false) String allowancemakerid,
			HttpServletRequest req) {
		String msg = "";
		AllowanceMaker up = Allowancemakerrep.getbyallowancemakerid(allowancemakerid);
		up.setDelFlg("Y");
		Allowancemakerrep.save(up);
		msg = up.getDesignation() + "-Delete successfully";
		return msg;
	}

	@PostMapping("/add_capital_tran")
	@Transactional
	public ResponseEntity<String> add_capital_tran(@RequestBody Transaction_table Transaction_table) {
		ResponseEntity<String> msg = null;
		try {
			System.out.println("Received Transaction: " + Transaction_table);
			msg = BHMS_services.add_Tran(Transaction_table);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
		return msg;
	}

	@RequestMapping(value = "getrole", method = { RequestMethod.POST })
	@ResponseBody
	public BTMAdminAssociateProfile getrole(@RequestParam(required = false) String empid, HttpServletRequest req) {

		String loginuserid = (String) req.getSession().getAttribute("USERID");

		BTMAdminAssociateProfile up = btmAdminAssociateProfileRep.getEmployeedetail1(empid);

		return up;
	}

	@RequestMapping(value = "getallowance", method = { RequestMethod.POST })
	@ResponseBody
	public AllowanceMaker getallowance(@RequestParam(required = false) String role, HttpServletRequest req) {

		AllowanceMaker al = Allowancemakerrep.getbyallid(role);
		String loginuserid = (String) req.getSession().getAttribute("USERID");

		return al;
	}

	@RequestMapping(value = "Addtraveltclaim", method = { RequestMethod.POST })
	@ResponseBody
	public String Addtraveltclaim(@ModelAttribute TravelClaim TravelClaimadd, String formmode, HttpServletRequest req) {
		System.out.println("value" + TravelClaimadd.getMarketType());
		String loginuserid = (String) req.getSession().getAttribute("USERID");
		return BHMS_services.Addtraveltclaims(TravelClaimadd, loginuserid, formmode);

	}

	@RequestMapping(value = "Download_Attendance", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> Download_Attendance(HttpServletResponse response,
			@RequestParam(value = "emp_id", required = false) String emp_id,
			@RequestParam(value = "cal_month", required = false) String cal_month,
			@RequestParam(value = "cal_year", required = false) String cal_year,
			@RequestParam(value = "attendanceType", required = false) String attendanceType,
			@RequestParam(value = "report_type", required = false) String report_type)
			throws IOException, SQLException, JRException, com.itextpdf.text.DocumentException {

		System.out.println("the enter the controller--1");
		if (report_type.equals("Excel")) {
			return reportServices.getFileAttendanceone(emp_id, cal_month, cal_year, report_type, attendanceType);
		} else {
			return reportServices.getFileAttendancetwo(emp_id, cal_month, cal_year, report_type, attendanceType);
		}
	}

	@RequestMapping(value = "deletetraveltclaim", method = { RequestMethod.POST })
	@ResponseBody
	public String deletetraveltclaim(@RequestParam(required = false) String travid, HttpServletRequest req) {

		String loginuserid = (String) req.getSession().getAttribute("USERID");
		TravelClaim up = travelClaimrep.climelistbyid(travid);
		up.setDel_flg("Y");
		travelClaimrep.save(up);
		return up.getId() + "-Delete successfully";
	}

	@RequestMapping(value = "addHeadcodecreation", method = { RequestMethod.POST })
	@ResponseBody
	public String addHeadcodecreation(@ModelAttribute CodeCreation CodeCreationadd, String addtype,
			HttpServletRequest req) {

		String loginuserid = (String) req.getSession().getAttribute("USERID");

		return BHMS_services.addcodecreation(CodeCreationadd, addtype);

	}

	@RequestMapping(value = "getcategory_code", method = { RequestMethod.GET })
	@ResponseBody
	public int getcategory_code(String Head_code, HttpServletRequest req) {

		int category_code = codecreationrep.getcategory_code(Head_code);

		return category_code + 1;

	}

	@RequestMapping(value = "getcategoryval", method = { RequestMethod.GET })
	@ResponseBody
	public List<Object> getcategoryval(String Head_code, HttpServletRequest req) {

		List<Object> up = codecreationrep.getcategory(Head_code);

		return up;

	}

	@RequestMapping(value = "getsubcategory_code", method = { RequestMethod.GET })
	@ResponseBody
	public String getsubcategory_code(String CategoryCodes, String Headcode, HttpServletRequest req) {

		BigInteger subcategory_code = codecreationrep.getsubcategory_code(CategoryCodes, Headcode);
		String subcategorycode = BHMS_services.bigIntegerToAlphabet(subcategory_code.add(BigInteger.ONE));

		return subcategorycode;

	}

	@RequestMapping(value = "getsubcategoryval", method = { RequestMethod.GET })
	@ResponseBody
	public List<Object> getsubcategoryval(String Headcode, String CategoryCodes, HttpServletRequest req) {

		List<Object> up = codecreationrep.getsubcategory(Headcode, CategoryCodes);
		System.out.println(up);

		return up;
	}

	@RequestMapping(value = "PurchaseReports", method = { RequestMethod.GET, RequestMethod.POST })
	public String PurchaseReports(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		md.addAttribute("allusers", resourceMasterRepo.gettotaluser());
		md.addAttribute("role", resourceMasterRepo.getrole(userId));
		// md.addAttribute("getpurcasevendorss", VendorCreationRep.getpurchaseVendor());

		// md.addAttribute("getsalevendorss", VendorCreationRep.getsaleVendor());

		List<VendorCreation> purchaseVendors = VendorCreationRep.getpurchaseVendor();
		System.out.println("the po length" + VendorCreationRep.getpurchaseVendor().size());
		List<VendorCreation> saleVendors = VendorCreationRep.getsaleVendor();
		System.out.println("the sale order" + VendorCreationRep.getsaleVendor().size());

		// Convert to JSON strings
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String purchaseVendorsJson = objectMapper.writeValueAsString(purchaseVendors);
			String saleVendorsJson = objectMapper.writeValueAsString(saleVendors);

			md.addAttribute("getpurcasevendorss", purchaseVendorsJson);
			md.addAttribute("getsalevendorss", saleVendorsJson);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "Purchase_Report.html";
	}

	/*--salereport---*/
	@RequestMapping(value = "SaleReports", method = { RequestMethod.GET, RequestMethod.POST })
	public String SaleReports(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		md.addAttribute("allusers", resourceMasterRepo.gettotaluser());
		md.addAttribute("role", resourceMasterRepo.getrole(userId));
		md.addAttribute("getvendor", VendorCreationRep.getsaleVendors());

		return "salesOrder_report.html";
	}

	/*--purchase retuenreport--*/

	@RequestMapping(value = "purchasereturnreport", method = { RequestMethod.GET, RequestMethod.POST })
	public String purchasereturnreport(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		md.addAttribute("allusers", resourceMasterRepo.gettotaluser());
		md.addAttribute("role", resourceMasterRepo.getrole(userId));
		md.addAttribute("getvendor", VendorCreationRep.getsaleVendors());

		return "purchasereturn.html";
	}

	@RequestMapping(value = "get_poid", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<String> get_poid(@RequestParam(value = "f", required = true) String type) {
		System.out.println("Received PO category: " + type);

		List<String> rs = new ArrayList<>();
		if ("po".equalsIgnoreCase(type)) {
			rs = PURCHASE_ORDER_ENTITY_NEW_rep.getall();
		} else if ("invoice".equalsIgnoreCase(type)) {
			rs = PO_invoice_reps.getall();
		} else if ("wo".equalsIgnoreCase(type)) {
			rs = SALES_ORDER_ENTITY_NEW_rep.getall();
		} else if ("invoicewo".equalsIgnoreCase(type)) {
			rs = SALES_invoice_TABLERep.getall();
		} else if ("purchasereturn".equalsIgnoreCase(type)) {
			rs = PO_Return_Reps.getreturnall();
		}

		else if ("salereturn".equalsIgnoreCase(type)) {
			rs = Sales_ReturnRep.getsalereturnall();

		}

		else {
			System.out.println("Invalid PO category: " + type);
		}

		return rs;
	}

	@RequestMapping(value = "get_woid", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<String> get_woid(@RequestParam(value = "f", required = true) String type) {
		System.out.println("Received PO category: " + type);

		List<String> rs = new ArrayList<>();
		if ("wo".equalsIgnoreCase(type)) {
			rs = SALES_ORDER_ENTITY_NEW_rep.getall();
		} else if ("invoice".equalsIgnoreCase(type)) {
			rs = SALES_invoice_TABLERep.getall();
		} else {
			System.out.println("Invalid PO category: " + type);
		}

		return rs;
	}

	@RequestMapping(value = "getOrganizationMaster", method = { RequestMethod.GET })
	@ResponseBody
	public BTMAdminOrganizationMaster getOrganizationMaster(HttpServletRequest req) {
		BTMAdminOrganizationMaster up = btmAdminOrganizationMasterRep.getOrganization();
		return up;
	}

	@RequestMapping(value = "/follow_up", method = RequestMethod.POST)
	public ResponseEntity<String> followUp(@RequestBody Follow_Up_Entity followUpEntity,
			@RequestParam(required = false) String formmode) {
		System.out.println("Inside followUp, formmode: " + formmode);

		try {
			return ResponseEntity.ok(Follow__ups.saveFollowUpDetails(followUpEntity, formmode));
		} catch (Exception e) {
			System.err.println("Error processing Follow-Up details: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error occurred while processing Follow-Up details.");
		}
	}

	@RequestMapping(value = "Download_po_Report", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> Download_po_Report(HttpServletResponse response,
			@RequestParam(value = "POType", required = false) String POType,
			@RequestParam(value = "vendorCode", required = false) String vendorId,
			@RequestParam(value = "po_cat", required = false) String po_cat,
			@RequestParam(value = "po_id", required = false) String po_id,
			@RequestParam(value = "wo_id", required = false) String woId,

			@RequestParam(value = "dailyDate", required = false) String dailyDate,
			@RequestParam(value = "from_date", required = false) String from_date,
			@RequestParam(value = "to_date", required = false) String to_date,
			@RequestParam(value = "reportType", required = false) String reportType)
			throws IOException, SQLException, JRException, com.itextpdf.text.DocumentException {

		System.out.println("Entering Download_Po_Report controller");
		// Print all parameters
		System.out.println("POType: " + POType);
		System.out.println("Vendor ID: " + vendorId);
		System.out.println("PO Category: " + po_cat);
		System.out.println("PO ID: " + po_id);
		System.out.println("Daily Date: " + dailyDate);
		System.out.println("From Date: " + from_date);
		System.out.println("To Date: " + to_date);
		System.out.println("Report Type: " + reportType);
		try {
			// Handle "Purchase Order" category
			if (po_cat.equals("po")) {

				if (reportType.equals("Excel")) {
					return reportServices.getPoReportExcel(POType, vendorId, po_cat, po_id, dailyDate, from_date,
							to_date);
				} else {
					return reportServices.getPoReportPdf(POType, vendorId, po_cat, po_id, dailyDate, from_date,
							to_date);
				}

			} else if (po_cat.equals("invoice")) {

				return reportServices.getPoReportExcelforinvoive(POType, vendorId, po_cat, po_id, dailyDate, from_date,
						to_date);

			} else if (po_cat.equals("purchasereturn")) {

				System.out.println("the step 1");

				return reportServices.getPoReportExcelforpurchasereurn(POType, vendorId, po_cat, po_id, dailyDate,
						from_date, to_date);

			}

			// Fallback for unsupported categories
			return ResponseEntity.badRequest()
					.body("Error: Unsupported category. Please select 'po' or 'invoice'.".getBytes());

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(("Error: Unable to process the request. " + e.getMessage()).getBytes());
		}
	}

	@RequestMapping(value = "Download_WO_Report", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> Download_WO_Report(HttpServletResponse response,
			@RequestParam(value = "POType", required = false) String POType,
			@RequestParam(value = "vendorCode", required = false) String vendorId,
			@RequestParam(value = "po_cat", required = false) String po_cat,
			@RequestParam(value = "wo_id", required = false) String woId,
			@RequestParam(value = "dailyDate", required = false) String dailyDate,
			@RequestParam(value = "from_date", required = false) String from_date,
			@RequestParam(value = "to_date", required = false) String to_date,
			@RequestParam(value = "reportType", required = false) String reportType)
			throws IOException, SQLException, JRException, com.itextpdf.text.DocumentException {

		System.out.println("Entering Download_WORK_Report controller");
		// Print all parameters
		System.out.println("POType: " + POType);
		System.out.println("Vendor ID: " + vendorId);
		System.out.println("PO Category: " + po_cat);
		System.out.println("woId: " + woId);
		System.out.println("Daily Date: " + dailyDate);
		System.out.println("From Date: " + from_date);
		System.out.println("To Date: " + to_date);
		System.out.println("Report Type: " + reportType);
		try {
			// Handle "Purchase Order" category
			if (po_cat.equals("wo")) {

				if (reportType.equals("Excel")) {
					return reportServices.getWORKORDERReportExcel(POType, vendorId, po_cat, woId, dailyDate, from_date,
							to_date);
				} else {
					return reportServices.getPoReportPdf(POType, vendorId, po_cat, woId, dailyDate, from_date, to_date);
				}

			} else if (po_cat.equals("invoicewo")) {
				return reportServices.getWORKORDERReportExcelforinvoice(POType, vendorId, po_cat, woId, dailyDate,
						from_date, to_date);

			}

			else if (po_cat.equals("salereturn")) {
				return reportServices.getWORKORDERReportExcelforsalereturn(POType, vendorId, po_cat, woId, dailyDate,
						from_date, to_date);

			}

			// Fallback for unsupported categories
			return ResponseEntity.badRequest()
					.body("Error: Unsupported category. Please select 'po' or 'invoice'.".getBytes());

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(("Error: Unable to process the request. " + e.getMessage()).getBytes());
		}
	}

	@RequestMapping(value = "DeliveryCharge", method = { RequestMethod.GET, RequestMethod.POST })
	public String DeliveryCharge(@RequestParam(required = false) String id, HttpServletRequest req, Model md,
			String formmode) {
		System.out.println("purchase order screen");
		String loginuserid = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(loginuserid));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null) {

			md.addAttribute("formmode", "history");
			md.addAttribute("getitemlist", SALES_invoice_TABLERep.getsaledlvlist());

			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);
		} else if (formmode.equals("deliverylist")) {
			md.addAttribute("formmode", "deliverylist");
			md.addAttribute("getitemlist", SALES_invoice_TABLERep.getdeliverycompletedlist());
			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);
		}

		else if (formmode.equals("view")) {
			md.addAttribute("getvendor", VendorCreationRep.getvendorlist());
			md.addAttribute("ItemList", ItemCreationrep.getitemlistbyfg());
			System.out.println("the id" + id);
			md.addAttribute("formmode", "view");

			md.addAttribute("getlist", SALES_ORDER_ENTITY_NEW_rep.getparticular(id));

			System.out.println("the testing" + SALES_ORDER_ENTITY_NEW_rep.getparticular(id));

		} else if (formmode.equals("historyview")) {

			// md.addAttribute("getvendor", VendorCreationRep.getvendorlist());
			md.addAttribute("ItemList", ItemCreationrep.getitemlistbyfg());
			System.out.println("the id" + id);
			md.addAttribute("formmode", "historyview");

			md.addAttribute("getlist", SALES_invoice_TABLERep.getparticular(id));

			System.out.println("the testing" + SALES_ORDER_ENTITY_NEW_rep.getparticular(id));

		}

		return "DeliveryCharge.html";

	}

	@RequestMapping(value = "DeliveryChargepaid", method = { RequestMethod.POST })
	@ResponseBody
	public String DeliveryChargepaid(@RequestParam(required = false) String paymentType, String woid,
			BigDecimal paidAmounts, HttpServletRequest req) {
		String msg = "";

		System.out.println("woid=" + woid);
		System.out.println("paidAmounts=" + paidAmounts);
		System.out.println("paymentType=" + paymentType);

		List<SALES_invoice_TABLE> up = SALES_invoice_TABLERep.getparticular(woid);

		for (SALES_invoice_TABLE wolist : up) {
			wolist.setDeliverystatus("C");
			wolist.setDeliverycharge(paidAmounts);
			wolist.setDeliverypaymenttype(paymentType);
			Date curdate = new Date();
			wolist.setDeliverydate(curdate);
			SALES_invoice_TABLERep.save(wolist);
			msg = "Delivery Charge Paid Sucessfully";

		}
		return msg;
	}

	@RequestMapping(value = "get_vendorledger", method = { RequestMethod.GET })
	@ResponseBody
	public VendorLedgerResponse get_vendorledger(@RequestParam(required = false) String Vendorid, String Yeardata,
			String Monthlydata) {

		// List<Transaction_table_demo>
		// up=Transaction_table_demo_rep.getbyvendor(Vendorid);
		// System.out.println("Yeardata="+Yeardata);
		// System.out.println("Monthlydata="+Monthlydata);

		VendorCreation vandor = VendorCreationRep.getvendorlistid(Vendorid);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(vandor.getAsOfDate());
		VendorLedgerResponse VendorLedgerResponse = null;
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		int month = calendar.get(Calendar.MONTH) + 1;
		String formattedMonth = String.format("%02d", month);
		System.out.println("month" + formattedMonth);

		if (Yeardata != null && Monthlydata != null) {
			if (year.equals(Yeardata) && formattedMonth.equals(Monthlydata)) {
				List<Transaction_table_demo> monthly = Transaction_table_demo_rep.getbyvendoryear(Yeardata, Monthlydata,
						Vendorid);
				VendorLedgerResponse = new VendorLedgerResponse(monthly, vandor.getOpeningBalance(),
						vandor.getOpening_Balance_Type());
			}

			else {
				BigDecimal totalcredit = BigDecimal.ZERO;
				BigDecimal totaldebit = BigDecimal.ZERO;
				int value = Integer.parseInt(Monthlydata);
				value -= 1;
				String previousMonthlydata = String.format("%02d", value);
				System.out.println("previousMonthlydata=" + previousMonthlydata);
				String type = vandor.getOpening_Balance_Type();
				if (type != null && type.equals("Debit") && vandor.getOpeningBalance() != null) {
					totalcredit = vandor.getOpeningBalance();
				} else if (type != null && type.equals("Credit") && vandor.getOpeningBalance() != null) {
					totaldebit = vandor.getOpeningBalance();
				}

				/*
				 * if(year.equals(Yeardata)&& formattedMonth.equals(previousMonthlydata)) {
				 * if(vandor.getOpening_Balance_Type().equals("Debit")) {
				 * totalcredit=vandor.getOpeningBalance(); } else {
				 * totaldebit=vandor.getOpeningBalance(); } }
				 */
				// List<Transaction_table_demo>
				// monthly=Transaction_table_demo_rep.getbyvendorpreviousmonth(Yeardata,previousMonthlydata,Vendorid);

				totalcredit = totalcredit
						.add(Transaction_table_demo_rep.totalCR_AMTprevious(Yeardata, previousMonthlydata, Vendorid));
				totaldebit = totaldebit
						.add(Transaction_table_demo_rep.totalDR_AMTprevious(Yeardata, previousMonthlydata, Vendorid));

				BigDecimal difference = totalcredit.subtract(totaldebit).abs();
				System.out.println("totalcredit=" + totalcredit);
				System.out.println("totaldebit=" + totaldebit);
				System.out.println("difference=" + difference);
				if (totalcredit.compareTo(totaldebit) > 0) {
					List<Transaction_table_demo> currentmonthly = Transaction_table_demo_rep.getbyvendoryear(Yeardata,
							Monthlydata, Vendorid);
					VendorLedgerResponse = new VendorLedgerResponse(currentmonthly, difference, "Debit");
				} else if (totaldebit.compareTo(totaldebit) > 0) {
					List<Transaction_table_demo> currentmonthly = Transaction_table_demo_rep.getbyvendoryear(Yeardata,
							Monthlydata, Vendorid);
					VendorLedgerResponse = new VendorLedgerResponse(currentmonthly, difference, "Credit");
				} else if (totaldebit.compareTo(totaldebit) == 0) {
					List<Transaction_table_demo> currentmonthly = Transaction_table_demo_rep.getbyvendoryear(Yeardata,
							Monthlydata, Vendorid);
					VendorLedgerResponse = new VendorLedgerResponse(currentmonthly, difference, null);
				}

			}

		}

		return VendorLedgerResponse;
	}

	@RequestMapping(value = "erpchartOfAccounts", method = { RequestMethod.GET, RequestMethod.POST })
	public String erpchartOfAccounts(HttpServletRequest req, @RequestParam(required = false) String formmode,
			Model model, String accountnum, @RequestParam(required = false) String id,
			@RequestParam(value = "mode", required = false) String mode,
			@RequestParam(required = false) String order_type) {

		String loginuserid = (String) req.getSession().getAttribute("USERID");

		String userId = (String) req.getSession().getAttribute("USERID");
		String userNAME = (String) req.getSession().getAttribute("USERNAME");
		System.out.println("name :" + userNAME);
		model.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));

		if (formmode == null || formmode.equals("add")) {
			model.addAttribute("formmode", "add");
			model.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));
			List<Erp_ChartOfAccounts> allacount = Erp_ChartOfAccountsRep.findsall();
			model.addAttribute("liststrn", allacount);
			BigInteger accno = erp_chartOfAccountsrep.getaccountnumber();
			accno = accno.add(BigInteger.ONE);
			model.addAttribute("Accno", "ACC000" + accno);
			/*
			 * List<String> productList = po_repo.getPoname();
			 * model.addAttribute("productList", productList);
			 */
			model.addAttribute("currentDate", LocalDate.now());
			model.addAttribute("getvendor", VendorCreationRep.getvendorlist1());
			model.addAttribute("resourceids", resourceMasterRepo.getalist11());
		}

		else if (formmode.equals("list")) {

			model.addAttribute("formmode", "list");
			List<Erp_ChartOfAccounts> allacount = Erp_ChartOfAccountsRep.findsallparent();
			model.addAttribute("liststrn", allacount);
		}

		else if (formmode.equals("account")) {
			List<Erp_ChartOfAccounts> allacount = Erp_ChartOfAccountsRep.findschildaccount(accountnum);
			if (allacount.isEmpty()) {
				List<CapitalTrans> tranlist = capitaltransrep.getbyaccount_number(accountnum);

				model.addAttribute("formmode", "tranlist");
				model.addAttribute("trandata", tranlist);

			} else {
				model.addAttribute("formmode", "childlist");
				model.addAttribute("liststrn", allacount);
			}
		}

		else if (formmode.equals("view")) {
			model.addAttribute("formmode", "view");
			General_journal_entity list = General_journal_rep.findbyid(Integer.valueOf(id));
			model.addAttribute("list", list);
		} else if (formmode.equals("verify")) {
			model.addAttribute("formmode", "verify");
			General_journal_entity list = General_journal_rep.findbyid(Integer.valueOf(id));
			model.addAttribute("list", list);
		} else if (formmode.equals("delete")) {
			model.addAttribute("formmode", "delete");
			General_journal_entity list = General_journal_rep.findbyid(Integer.valueOf(id));
			model.addAttribute("list", list);
		} else if (formmode.equals("edit")) {
			model.addAttribute("formmode", "edit");
			General_journal_entity list = General_journal_rep.findbyid(Integer.valueOf(id));
			model.addAttribute("list", list);
			List<BHMSInventoryProductStockCurrent> productList = BHMSInventoryProductStockCurrentrepo
					.productStockList();
			model.addAttribute("productList", productList);
			model.addAttribute("currentDate", LocalDate.now());
		}

		return "erpchartOfAccounts.html";

	}

	@RequestMapping(value = "myaccountledger", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<CapitalTrans> myaccountledger(@RequestParam(required = false) String accountnum,
			@RequestParam(required = false) String fdate, @RequestParam(required = false) String tdate)
			throws ParseException {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Match DB format
		java.sql.Date fromDate = null;
		java.sql.Date toDate = null;

		try {
			if (fdate != null && !fdate.isEmpty()) {
				fromDate = new java.sql.Date(dateFormat.parse(fdate).getTime()); // Convert to java.sql.Date
			}
			if (tdate != null && !tdate.isEmpty()) {
				toDate = new java.sql.Date(dateFormat.parse(tdate).getTime()); // Convert to java.sql.Date
			}
		} catch (ParseException e) {
			System.out.println("Invalid date format: " + e.getMessage());
			throw e;
		}

		if (fromDate == null && toDate == null) {
			System.out.println("the ano" + accountnum);
			return capitaltransrep.getbyaccount_number(accountnum);
		}

		// If only fromDate is provided, retrieve data from that date onward
		if (fromDate != null && toDate == null) {
			System.out.println("Fetching transactions from: " + fromDate);
			return capitaltransrep.getByAccountNumberFromDate(accountnum, fromDate);
		}

		System.out.println("account num " + accountnum);
		System.out.println("Filtered From Date: " + fromDate);
		System.out.println("Filtered To Date: " + toDate);

		return capitaltransrep.getByAccountNumberAndDateRange(accountnum, fromDate, toDate);
	}

	/*----accountledger---*/

	@RequestMapping(value = "accountledger", method = { RequestMethod.GET, RequestMethod.POST })
	public String accountledger(HttpServletRequest req, @RequestParam(required = false) String formmode, Model model,
			String accountnum, @RequestParam(required = false) String id,
			@RequestParam(value = "mode", required = false) String mode,
			@RequestParam(required = false) String order_type) {

		String loginuserid = (String) req.getSession().getAttribute("USERID");

		String userId = (String) req.getSession().getAttribute("USERID");
		String userNAME = (String) req.getSession().getAttribute("USERNAME");
		System.out.println("name :" + userNAME);
		model.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));

		if (formmode == null || formmode.equals("list")) {

			model.addAttribute("formmode", "list");
			List<Erp_ChartOfAccounts> allaccount = Erp_ChartOfAccountsRep.findschild();
			model.addAttribute("liststrn", allaccount);
		}

		return "accountledgernew.html";

	}

	@RequestMapping(value = "erpAddchartofaccounts", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String erpAddchartofaccounts(@ModelAttribute Erp_ChartOfAccounts Erp_ChartOfAccounts,
			HttpServletRequest req) {

		String loginuserid = (String) req.getSession().getAttribute("USERID");

		return BHMS_services.addchartofaccount(Erp_ChartOfAccounts, loginuserid);
	}

	/*-----depreciation For Assets*/

	@RequestMapping(value = "Depreciation", method = { RequestMethod.GET, RequestMethod.POST })
	public String Depreciation(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String Id, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");

		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		System.out.println("Asset is :" + formmode);
		String demo = "TESTING";

		// BAM_AssetFlows_Entity newInventory = new BAM_AssetFlows_Entity();
		// newInventory.setEntry_user(userId); // Set ENTRY_USER as the logged-in user
		// newInventory.setModify_user(userId); // Set MODIFY_USER as the logged-in user
		// newInventory.setVerify_user(userId); // Set VERIFY_USER as the logged-in user
		// (optional for add)

		if (formmode == null) {
			md.addAttribute("formmode", "list"); // List

			md.addAttribute("assets", PO_invoice_reps.getimmovableasssets());

			// md.addAttribute("assets", BAM_AssetFlows_rep.getdata());
		} else if ("view".equals(formmode)) {

			// System.out.println("Asset is :" + Id);
			md.addAttribute("formmode", "view");

			// System.out.println("Asset is :" + BAM_AssetFlows_rep.getview(Id).size());
			// md.addAttribute("paramview", BAM_AssetFlows_rep.getview(Id));
			md.addAttribute("paramview", demo);
			// md.addAttribute("paramview1", newInventory);

			md.addAttribute("paramview1", demo);
		}
		return "Depreciation";
	}

	@RequestMapping(value = "Addcapamount", method = { RequestMethod.POST })

	public ResponseEntity<String> Addcapamount(@RequestParam(required = false) String formmode, HttpServletRequest req,
			@RequestBody List<CapitalTrans> CapitalTrans) {

		String loginuserid = (String) req.getSession().getAttribute("USERID");

		return BHMS_services.addcaps(CapitalTrans, loginuserid);
	}

	@RequestMapping(value = "getaccountdescription", method = { RequestMethod.GET })
	@ResponseBody
	public List<Map<String, String>> getaccountdescription(@RequestParam(required = false) String accountnumber,
			String accountname, HttpServletRequest req) {
		Erp_ChartOfAccounts childaccount = Erp_ChartOfAccountsRep.findsccount(accountnumber);
		// finding parent account
		Erp_ChartOfAccounts parentaccount = Erp_ChartOfAccountsRep.findsccount(childaccount.getParentaccount());

		List<Map<String, String>> data = new ArrayList<>();
		Map<String, String> map = new HashMap<>();
		if (parentaccount.getAccountName().equals("purchase vendor")) {
			List<PO_invoice_entity> popending = PO_invoice_reps
					.getPOlistbyvendorandpayment(childaccount.getOwnershipid());
			map.put("id", popending.get(0).getPoId());
			map.put("pendingamount", popending.get(0).getExtra_amount().toString());
			data.add(map);

		} else if (parentaccount.getAccountName().equals("Sale Account")) {

		} else if (accountname.equals("Vendor Advance Account")) {
			List<VendorCreation> vendorlist = VendorCreationRep.getvendorlist();
			for (VendorCreation Vendor : vendorlist) {
				map.put("id", Vendor.getVendorId());
				data.add(map);
			}
		}

		return data;
	}

	@RequestMapping(value = "getaccountdata", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Erp_ChartOfAccounts> getaccountdata(
			@RequestParam(value = "accountname", required = true) String accountname) {

		Erp_ChartOfAccounts accountnames = Erp_ChartOfAccountsRep.findsbyaccountname(accountname);
		List<Erp_ChartOfAccounts> response = Erp_ChartOfAccountsRep.findschildaccount(accountnames.getAccountNumber());

		return response;

	}

	@RequestMapping(value = "addGroupCreation", method = { RequestMethod.POST })
	@ResponseBody
	public String addGroupCreation(String groupname, String vendor_type, HttpServletRequest req) {

		String loginuserid = (String) req.getSession().getAttribute("USERID");

		return BHMS_services.addgrouping(groupname, vendor_type);

	}

	@RequestMapping(value = "getGroupaccount", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Erp_ChartOfAccounts> getGroupaccount(String vendor_type, HttpServletRequest req) {

		List<Erp_ChartOfAccounts> accountnames = null;
		if (vendor_type.equals("SaleVendor")) {

			accountnames = Erp_ChartOfAccountsRep.findsbyaccountgroup("SaleGroup");
		} else {
			accountnames = Erp_ChartOfAccountsRep.findsbyaccountgroup("PurchaseGroup");
		}

		return accountnames;

	}

	/*----ESI----*/
	@RequestMapping(value = "esIERP", method = { RequestMethod.GET, RequestMethod.POST })
	public String esiFORERP(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resName, @RequestParam(required = false) String a,
			@RequestParam(required = false) String Month, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("list1")) {
			if (Month != null || Month == "") {
				List<spf_entity> spfValues = Spf_repo.getESI(Month);

				for (int i = 0; i < spfValues.size(); i++) {
					spf_entity entity = spfValues.get(i);
					// Do something with the entity, e.g., print its properties
					System.out.println("Bank Acct No: " + entity.getBank_acct_no());
					System.out.println("Bank Name: " + entity.getBank_name());
					System.out.println("Salary Month: " + entity.getSalary_month());

					String stringValue = entity.getGross_salary(); // Replace this with your desired string
					BigDecimal grosspay = new BigDecimal(stringValue);

					int intValue = grosspay.intValue();
					int emailId = Math.round(intValue * 3.25f / 100);
					String stringValue1 = Integer.toString(emailId);
					entity.setEmail_id(stringValue1);

					int ifsc = Math.round(intValue * 0.75f / 100);
					String stringValue2 = Integer.toString(ifsc);
					entity.setIfsc_code(stringValue2);

					int remarks = Math.round(intValue * 4.00f / 100);
					String stringValue3 = Integer.toString(remarks);
					entity.setRemarks(stringValue3);
					// spfValues.set(0, entity);
				}

				md.addAttribute("ghj", spfValues);
				md.addAttribute("formmode", "list1");
				md.addAttribute("month", Month);
			} else {
				YearMonth currentYearMonth = YearMonth.now();

				// Format the current month and year as a string
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
				String formattedMonthYear = currentYearMonth.format(formatter);
				// String[] parts = formattedMonthYear.split(" ");
				// Print the current month and year
				System.out.println("Current Month and Year: " + formattedMonthYear);

				List<spf_entity> spfValues = Spf_repo.getall(formattedMonthYear);

				for (int i = 0; i < spfValues.size(); i++) {
					spf_entity entity = spfValues.get(i);
					// Do something with the entity, e.g., print its properties
					System.out.println("Bank Acct No: " + entity.getBank_acct_no());
					System.out.println("Bank Name: " + entity.getBank_name());
					System.out.println("Salary Month: " + entity.getSalary_month());

					String stringValue = entity.getBasic_pay(); // Replace this with your desired string
					BigDecimal basicPay = new BigDecimal(stringValue);

					int intValue = basicPay.intValue();
					int emailId = Math.round(intValue * 8.33f / 100);
					String stringValue1 = Integer.toString(emailId);
					entity.setEmail_id(stringValue1);

					int ifsc = Math.round(intValue * 3.67f / 100);
					String stringValue11 = Integer.toString(ifsc);
					entity.setIfsc_code(stringValue11);

					int remarks = Math.round(intValue * 12.00f / 100);
					String stringValue2 = Integer.toString(remarks);
					entity.setRemarks(stringValue2);
					spfValues.set(0, entity);

				}

				md.addAttribute("ghj", spfValues);
				md.addAttribute("formmode", "list1");

			}

		} else if (formmode == null || formmode.equals("list")) {
			if (Month != null || Month == "") {
				List<spf_entity> spfValues = Spf_repo.getESI(Month);

				for (int i = 0; i < spfValues.size(); i++) {
					spf_entity entity = spfValues.get(i);
					// Do something with the entity, e.g., print its properties
					System.out.println("Bank Acct No: " + entity.getBank_acct_no());
					System.out.println("Bank Name: " + entity.getBank_name());
					System.out.println("Salary Month: " + entity.getSalary_month());
					System.out.println("salary " + entity.getGross_salary());
					String stringValue = entity.getGross_salary(); // Replace this with your desired string

					BigDecimal grosspay = new BigDecimal(stringValue);

					int intValue = grosspay.intValue();
					int emailId = Math.round(intValue * 3.25f / 100);
					String stringValue1 = Integer.toString(emailId);
					entity.setEmail_id(stringValue1);

					int ifsc = Math.round(intValue * 0.75f / 100);
					String stringValue2 = Integer.toString(ifsc);
					entity.setIfsc_code(stringValue2);

					int remarks = Math.round(intValue * 4.00f / 100);
					String stringValue3 = Integer.toString(remarks);
					entity.setRemarks(stringValue3);
					// spfValues.set(0, entity);
				}

				md.addAttribute("ghj", spfValues);
				md.addAttribute("formmode", "list");
				md.addAttribute("month", Month);
			} else {
				YearMonth currentYearMonth = YearMonth.now();

				// Format the current month and year as a string
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
				String formattedMonthYear = currentYearMonth.format(formatter);
				// String[] parts = formattedMonthYear.split(" ");
				// Print the current month and year
				System.out.println("Current Month and Year: " + formattedMonthYear);

				List<spf_entity> spfValues = Spf_repo.getall(formattedMonthYear);

				for (int i = 0; i < spfValues.size(); i++) {
					spf_entity entity = spfValues.get(i);
					// Do something with the entity, e.g., print its properties
					System.out.println("Bank Acct No: " + entity.getBank_acct_no());
					System.out.println("Bank Name: " + entity.getBank_name());
					System.out.println("Salary Month: " + entity.getSalary_month());

					String stringValue = entity.getBasic_pay(); // Replace this with your desired string
					BigDecimal basicPay = new BigDecimal(stringValue);

					int intValue = basicPay.intValue();
					int emailId = Math.round(intValue * 8.33f / 100);
					String stringValue1 = Integer.toString(emailId);
					entity.setEmail_id(stringValue1);

					int ifsc = Math.round(intValue * 3.67f / 100);
					String stringValue11 = Integer.toString(ifsc);
					entity.setIfsc_code(stringValue11);

					int remarks = Math.round(intValue * 12.00f / 100);
					String stringValue2 = Integer.toString(remarks);
					entity.setRemarks(stringValue2);
					spfValues.set(0, entity);

				}

				md.addAttribute("ghj", spfValues);
				md.addAttribute("formmode", "list");

			}

		}

		return "ESI";
	}

	/*
	 * try { String msg = adminOperServices.add_branch(branchEntity, formmode);
	 * return msg; } catch (Exception e) { return "Error: " + e.getMessage(); } }
	 */

	@RequestMapping(value = "branch_add", method = RequestMethod.POST)
	@ResponseBody
	public String branch_add(@RequestParam("formmode") String formmode, @RequestBody Branch_Entity branchEntity) {

		try {
			String msg = adminOperServices.add_branch(branchEntity, formmode);
			return msg;
		} catch (Exception e) {
			return "Error: " + e.getMessage();
		}
	}

	@RequestMapping(value = "getbranchlist", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getbranchlist(@RequestParam("branchId") String branchId,@RequestParam("org_id") String org_id) {
		try {System.out.println("ids: "+branchId+org_id);
			List<VendorCreation> list = VendorCreationRep.getvendorlistbyBranch(branchId,org_id);
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}
	@RequestMapping(value = "getorglist", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getorglist(@RequestParam("org_id") String org_id) {
		try {
			List<String> list = TSK_branch_Reps.find_branch_ids(org_id);
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}
	
	@RequestMapping(value = "getorglistnew", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getorglistnew(@RequestParam("org_id") String orgId) {
	    try {
	        List<Object[]> results = TSK_branch_Reps.findBranchInfoRaw(orgId);
	        List<List<String>> dtoList = new ArrayList<>();

	        for (Object[] row : results) {
	            List<String> rowList = new ArrayList<>();
	            rowList.add((String) row[0]); // Branch_Id
	            rowList.add((String) row[1]); // Branch_Name
	            rowList.add((String) row[2]); // ORG_NAME
	            dtoList.add(rowList);
	        }

	        return ResponseEntity.ok(dtoList);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error: " + e.getMessage());
	    }
	}

	
	

	@RequestMapping(value = "getbranchlist_sale", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getbranchlist_sale(@RequestParam("branchId") String branchId) {
		try {
			List<SALES_ORDER_ENTITY_NEW> list = SALES_ORDER_ENTITY_NEW_rep.getlistbyBranch(branchId);
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	@RequestMapping(value = "getbranchlist_saleInv", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getbranchlist_saleInv(@RequestParam("branchId") String branchId) {
		try {
			List<SALES_invoice_TABLE> list = SALES_invoice_TABLERep.getlistbyBranch(branchId);

			list.forEach(invoice -> {
				if (invoice.getOrderDate() != null) {
					invoice.setOrderDate(invoice.getOrderDate()); // Convert to dd-MM-yyyy
					System.out.println("Order date is :" + invoice.getOrderDate());
				}
			});

			return ResponseEntity.ok(list);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	@RequestMapping(value = "getbranchlist_Delvry", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getbranchlist_Delvry(@RequestParam("branchId") String branchId) {
		try {
			List<SALES_invoice_TABLE> list = SALES_invoice_TABLERep.getbranch_devry(branchId);

			list.forEach(invoice -> {
				if (invoice.getOrderDate() != null) {
					invoice.setOrderDate(invoice.getOrderDate()); // Convert to dd-MM-yyyy
					System.out.println("Order date is :" + invoice.getOrderDate());
				}
			});

			return ResponseEntity.ok(list);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	@RequestMapping(value = "getbranchcompletedlist", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getbranchcompletedlist(@RequestParam("branchId") String branchId) {
		try {
			List<SALES_invoice_TABLE> list = SALES_invoice_TABLERep.getbranchcompletedlist(branchId);

			list.forEach(invoice -> {
				if (invoice.getDeliverydate() != null) {
					invoice.setDeliverydate(invoice.getDeliverydate()); // Convert to dd-MM-yyyy
					System.out.println("Order date is :" + invoice.getDeliverydate());
				}
			});

			return ResponseEntity.ok(list);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	@RequestMapping(value = "getbranch_return", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getbranch_return(@RequestParam("branchId") String branchId) {
		try {
			List<SALES_invoice_TABLE> list = SALES_invoice_TABLERep.getbranch_return(branchId);

			list.forEach(invoice -> {
				if (invoice.getOrderDate() != null) {
					invoice.setOrderDate(invoice.getOrderDate()); // Convert to dd-MM-yyyy
					System.out.println("Order date is :" + invoice.getOrderDate());
				}
			});

			return ResponseEntity.ok(list);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	@RequestMapping(value = "getbranch_returnlist", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getbranch_returnlist(@RequestParam("branchId") String branchId) {
		try {
			List<Sales_Return> list = Sales_ReturnRep.getbranch_returnlist(branchId);

			list.forEach(invoice -> {
				if (invoice.getOrderDate() != null) {
					invoice.setOrderDate(invoice.getOrderDate()); // Convert to dd-MM-yyyy
					System.out.println("Order date is :" + invoice.getOrderDate());
				}
			});

			return ResponseEntity.ok(list);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	@RequestMapping(value = "getbranch_payIn", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getbranch_payIn(@RequestParam("branchId") String branchId) {
		try {
			List<SALES_invoice_TABLE> list = SALES_invoice_TABLERep.getbranch_payIn(branchId);

			list.forEach(invoice -> {
				if (invoice.getOrderDate() != null) {
					invoice.setOrderDate(invoice.getOrderDate()); // Convert to dd-MM-yyyy
					System.out.println("Order date is :" + invoice.getOrderDate());
				}
			});

			return ResponseEntity.ok(list);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	@RequestMapping(value = "getbranch_payInHistory", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getbranch_payInHistory(@RequestParam("branchId") String branchId) {
		try {
			List<SALES_invoice_TABLE_PAY_IN> list = SALES_invoice_TABLE_PAY_IN_rep.getbranch_payInHistory(branchId);

			return ResponseEntity.ok(list);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	@RequestMapping(value = "getbranch_PO", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getbranch_PO(@RequestParam("branchId") String branchId) {
		try {
			List<PURCHASE_ORDER_ENTITY_NEW> list = PURCHASE_ORDER_ENTITY_NEW_rep.getbranch_PO(branchId);

			list.forEach(invoice -> {
				if (invoice.getOrderDate() != null) {
					invoice.setOrderDate(invoice.getOrderDate()); // Convert to dd-MM-yyyy
					System.out.println("Order date is :" + invoice.getOrderDate());
					System.out.println("list size is :" + list.size());
				}
			});

			return ResponseEntity.ok(list);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	@RequestMapping(value = "getbranch_POInv", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getbranch_POInv(@RequestParam("branchId") String branchId) {
		try {
			List<PO_invoice_entity> list = PO_invoice_reps.getbranch_POInv(branchId);

			list.forEach(invoice -> {
				if (invoice.getOrderDate() != null) {
					invoice.setOrderDate(invoice.getOrderDate()); // Convert to dd-MM-yyyy
					System.out.println("Order date is :" + invoice.getOrderDate());
				}
			});

			return ResponseEntity.ok(list);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	@RequestMapping(value = "getbranch_POReturn", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getbranch_POReturn(@RequestParam("branchId") String branchId) {
		try {
			List<PO_invoice_entity> list = PO_invoice_reps.getbranch_POReturn(branchId);

			list.forEach(invoice -> {
				if (invoice.getOrderDate() != null) {
					invoice.setOrderDate(invoice.getOrderDate()); // Convert to dd-MM-yyyy
					System.out.println("Order date is :" + invoice.getOrderDate());
				}
			});

			return ResponseEntity.ok(list);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	@RequestMapping(value = "getbranch_PORetPid", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getbranch_PORetPid(@RequestParam("branchId") String branchId) {
		try {
			List<PO_Return_Entity> list = PO_Return_Reps.getbranch_PORetPid(branchId);

			list.forEach(invoice -> {
				if (invoice.getOrderDate() != null) {
					invoice.setOrderDate(invoice.getOrderDate()); // Convert to dd-MM-yyyy
					System.out.println("Order date is :" + invoice.getOrderDate());
				}
			});

			return ResponseEntity.ok(list);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	@RequestMapping(value = "getbranch_POHis", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getbranch_POHis(@RequestParam("branchId") String branchId) {
		try {
			List<PO_invoice_Pay_Out_entity> list = PO_invoice_Pay_Out_rep.getbranch_POHis(branchId);

			list.forEach(invoice -> {
				if (invoice.getOrderDate() != null) {
					invoice.setOrderDate(invoice.getOrderDate()); // Convert to dd-MM-yyyy
					System.out.println("Order date is :" + invoice.getOrderDate());
				}
			});

			return ResponseEntity.ok(list);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	@RequestMapping(value = "getbranch_val", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getbranch_val(@RequestParam("branchId") String branchId) {
		try {
			List<TSK_PROCESS_ENTITY> list = TSK_PROCESS_REP.getbranch_val(branchId);

			list.forEach(invoice -> {
				if (invoice.getProcessStartDate() != null) {
					invoice.setProcessStartDate(invoice.getProcessStartDate()); // Convert to dd-MM-yyyy
					System.out.println("Order date is :" + invoice.getProcessStartDate());
				}
			});

			return ResponseEntity.ok(list);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	@RequestMapping(value = "getbranch_comp", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getbranch_comp(@RequestParam("branchId") String branchId) {
		try {
			List<TSK_PROCESS_ENTITY> list = TSK_PROCESS_REP.getbranch_comp(branchId);
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	@RequestMapping(value = "getbranch_fin", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getbranch_fin(@RequestParam("branchId") String branchId) {
		try {
			List<FINISHED_GOODS_ENTITY> list = FINISHED_GOODS_Rep.getbranch_fin(branchId);
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	@RequestMapping(value = "getexpensesdata", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Object[]> getexpensesdata(@RequestParam(value = "code", required = true) String code) {
		List<Object[]> response = erp_exp_category_rep.getcategory_creationid(code);
		System.out.println("the response" + response);
		return response;

	}

	@RequestMapping(value = "getbranch_one", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getbranch_one(@RequestParam("branchId") String branchId) {
		try {
			List<Object[]> rawcurrent = PO_invoice_reps.BATCHWITHSTOCK_one(branchId);
			List<PO_invoice_entity> rawhistory = PO_invoice_reps.BATCHWITHOUTSTOCK_one(branchId);
			List<PO_invoice_entity> storesandspares = PO_invoice_reps.BATCHWITHSTOCKStoresandspares_one(branchId);
			List<Object[]> fglist = FINISHED_GOODS_Rep.get_listgroup_one(branchId);
			List<TSK_PROCESS_ENTITY> processlist = TSK_PROCESS_REP.getprocessCompletedwithstock_one(branchId);
			List<Object[]> getPackingMaterials = PO_invoice_reps.BATCHWITHSTOCKPackingMaterials_one(branchId);

			Map<String, Object> response = new HashMap<>();
			response.put("rawcurrent", rawcurrent);
			response.put("rawhistory", rawhistory);
			response.put("storesandspares", storesandspares);
			response.put("fglist", fglist);
			response.put("processlist", processlist);
			response.put("getPackingMaterials", getPackingMaterials);

			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	@RequestMapping(value = "getuserbranchlist", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getuserbranchlist(@RequestParam("branchId") String branchId,
			@RequestParam(required=false) String org_id) {
		try {
			System.out.println("b"+branchId);
			System.out.println("o"+org_id);
			
			List<HRMS_USER_PROFILE_ENTITY> list = hrmsrepoo.getuserid(branchId,org_id);
			System.out.println("the length" +list.size());
			
			return ResponseEntity.ok(list);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	@RequestMapping(value = "getuserbranchlistassociate", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getuserbranchlistassociate(@RequestParam("branchId") String branchId) {
		try {
			List<BTMAdminAssociateProfile> list = btmAdminAssociateProfileRep.getAssociatelistbesedbranch(branchId);
			return ResponseEntity.ok(list);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	@RequestMapping(value = "getuserbranchLEAVERMASTER", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getuserbranchLEAVERMASTER(@RequestParam("branchId") String branchId,
			@RequestParam(required=false) String org_id) {
		try {

			System.out.println("THE LEAVE MASTER");
			List<LeaveMaster> list = leaveMasterRep.getleavebranch11(branchId,org_id);
			System.out.println("the btesting" +list.size());

			return ResponseEntity.ok(list);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	@RequestMapping(value = "getuserbranchlistFORHOLI", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getuserbranchlistFORHOLI(@RequestParam("branchId") String branchId,
			@RequestParam(required=false) String org_id) {
		try {

			System.out.println("THE holiday MASTER");
			System.out.println("the organizaion id" +org_id);
			System.out.println("the branch id " +branchId);
			List<BTMAdminHolidayMaster> list = btmAdminHolidayMasterRep.getbranch(branchId,org_id);

			return ResponseEntity.ok(list);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	@RequestMapping(value = "getuserbranchlisttravelmaster", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getuserbranchlisttravelmaster(@RequestParam("branchId") String branchId,
			@RequestParam(required=false) String org_id) {
		try {

			List<BTMTravelMaster> list = btmTravelMasterRep.getbranchtravel(branchId,org_id);

			return ResponseEntity.ok(list);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	@RequestMapping(value = "getuserbranchlistforclaim", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getuserbranchlistforclaim(@RequestParam("branchId") String branchId,
			@RequestParam(required=false) String org_id) {
		try {

			List<ExpenseMaster> list = btmAdminExpenseReportRep.getexpensesclaim(branchId,org_id);

			return ResponseEntity.ok(list);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	@RequestMapping(value = "getuserbranchlisttravelmastermain", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getuserbranchlisttravelmastermain(@RequestParam("branchId") String branchId,
			@RequestParam(required=false) String org_id) {
		try {

			List<BTMTravelMaster> list = btmTravelMasterRep.getTravellist1new(branchId,org_id);

			return ResponseEntity.ok(list);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	/*-----Graphical_Design-----*/
	@RequestMapping(value = "Graphical_Design", method = { RequestMethod.GET, RequestMethod.POST })
	public String Graphical_Design(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, @RequestParam(required = false) String userId, Model md,
			HttpServletRequest req) throws ParseException {

		String userId1 = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(userId1));

		// Branch Id list
		List<String> list = Branch_reps.find_branch_id();
		md.addAttribute("branchIds", list);

		// Purchase Orders Data (orderDate, totalAmount)
		List<PO_invoice_entity> po_inv = PO_invoice_reps.getPOInvTotalAmount();
		md.addAttribute("po_inv", po_inv);

		// Sales Orders Data (saleDate, totalAmount)
		List<SALES_invoice_TABLE> sale_inv = SALES_invoice_TABLERep.getsaleInvTotalAmount();
		md.addAttribute("sale_inv", sale_inv);

		// Inventory Data (orderDate, stock)
		List<Object[]> results = PO_invoice_reps.getStockAndDate();
		List<PO_invoice_entity> stock = new ArrayList<>();
		for (Object[] row : results) {
			PO_invoice_entity poInvoice = new PO_invoice_entity();
			poInvoice.setUseingqty((Integer) row[3]); // Stock quantity
			poInvoice.setOrderDate((Date) row[4]); // Order date
			stock.add(poInvoice);
		}
		md.addAttribute("stock", stock);

		// Expenses Data (expDate, totalAmount)
		List<ERP_EXPENSES_ENTITY> Exp = eRP_EXPENSES_REPO.getAmtDate();
		md.addAttribute("Exp", Exp);

		return "Graphical_Design.html";
	}

	@RequestMapping(value = "getuserbranchlistforclaimMAIN", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getuserbranchlistforclaimMAIN(@RequestParam("branchId") String branchId,
			@RequestParam(required=false) String org_id) {
		try {

			List<ExpenseMaster> list = btmAdminExpenseReportRep.getexpensesclaim(branchId,org_id);

			return ResponseEntity.ok(list);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	@RequestMapping(value = "getuserbranchlistforclaimMAINsalary", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getuserbranchlistforclaimMAINsalary(@RequestParam("branchId") String branchId,
			@RequestParam(required=false) String org_id) {
		try {

			List<salary_parameter> list = salary_parameter_rep1111.getdataforsalary(branchId,org_id);

			return ResponseEntity.ok(list);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}
	
	

	@RequestMapping(value = "getdetailsbasedgroup", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getdetailsbasedgroup(@RequestParam("group_name") String group_name) {
		try {

			salary_parameter list = salary_parameter_Rep.getgroup(group_name);

			return ResponseEntity.ok(list);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	@RequestMapping(value = "salein", method = { RequestMethod.POST })
	@ResponseBody
	public String salein(@RequestParam(required = false) String woid, HttpServletRequest req) {
		String msg = "";
		msg = BHMS_services.salein(woid);

		return msg;
	}

	@RequestMapping(value = "getvenodrList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getvenodrList(@RequestParam("vendor_type") String vendor_type) {
		try {
			List<VendorCreation> list;

			if ("purchase_vendor".equalsIgnoreCase(vendor_type)) {
				list = VendorCreationRep.getpurchaseVendor();
				System.out.println("The PO sie iss:" + list.size());
			} else if ("sale_vendor".equalsIgnoreCase(vendor_type)) {
				list = VendorCreationRep.getsaleVendor();
				System.out.println("The Sale sie iss:" + list.size());
			} else {
				// Return empty list or bad request if vendor_type is invalid
				return ResponseEntity.badRequest().body("Invalid vendor_type");
			}

			return ResponseEntity.ok(list);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	@RequestMapping(value = "getvenodrListBR", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getvenodrListBR(@RequestParam("vendor_type") String vendor_type,
			@RequestParam("branchId") String branchId) {
		try {
			List<VendorCreation> list;

			if ("purchase_vendor".equalsIgnoreCase(vendor_type)) {
				list = VendorCreationRep.getpurchaseVendorone(branchId);
				System.out.println("The PO sie iss:" + list.size());
			} else if ("sale_vendor".equalsIgnoreCase(vendor_type)) {
				list = VendorCreationRep.getsaleVendorone(branchId);
				System.out.println("The Sale sie iss:" + list.size());
			} else {
				// Return empty list or bad request if vendor_type is invalid
				return ResponseEntity.badRequest().body("Invalid vendor_type");
			}

			return ResponseEntity.ok(list);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	@RequestMapping(value = "getalldesign", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getalldesign(@RequestParam("branchId") String branchId,
			@RequestParam("vendor_type") String vendor_type, @RequestParam("vendor") String vendor,
			@RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
			@RequestParam("toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate) {
		System.out.println("Enterr controller");

		// Create a DTO object
		Graphical_design_services dto = new Graphical_design_services();

		// If only branchId is provided (others are null)
		if (branchId != null && (vendor_type == null || vendor_type.isEmpty()) && (vendor == null || vendor.isEmpty())
				&& fromDate == null && toDate == null) {
			System.out.println("Enterr if block");
			// Purchase Orders Data
			dto.po_inv = PO_invoice_reps.getPOInvTotalAmountee(branchId);

			// Sales Orders Data
			dto.sale_inv = SALES_invoice_TABLERep.getsaleInvTotalAmountee(branchId);

			// Inventory Data
			List<Object[]> results = PO_invoice_reps.getStockAndDateff(branchId);
			dto.stock = new ArrayList<>();
			for (Object[] row : results) {
				PO_invoice_entity poInvoice = new PO_invoice_entity();
				poInvoice.setUseingqty((Integer) row[3]); // Stock quantity
				poInvoice.setOrderDate((Date) row[4]); // Order date
				dto.stock.add(poInvoice);
			}

			// Expenses Data
			dto.Exp = eRP_EXPENSES_REPO.getAmtDateBranch(branchId);

			// ✅ RETURN THE DTO OBJECT
			return ResponseEntity.ok(dto);

		} else {
			System.out.println("Enterr else block");

			// Delegate to service layer for filtered data
			return Graphical_design_servicess.getalldesign(branchId, vendor_type, vendor, fromDate, toDate);
		}
	}

	@RequestMapping(value = "balancesheet", method = { RequestMethod.GET, RequestMethod.POST })
	public String balancesheet(HttpServletRequest req, Model model) {

		String userId = (String) req.getSession().getAttribute("USERID");
		model.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));

		model.addAttribute("capitalaccount", "850");

		// current asset
		Erp_ChartOfAccounts currentamount = Erp_ChartOfAccountsRep.findsccount("ACC00016");
		model.addAttribute("currentasset", currentamount.getAccountBalance());

		// Non-Current Assets
		Erp_ChartOfAccounts noncurrentamount = Erp_ChartOfAccountsRep.findsccount("ACC00015");
		model.addAttribute("Non_Current_Assets", noncurrentamount.getAccountBalance());

		// Current Liabilities
		Erp_ChartOfAccounts CurrentLiabilities = Erp_ChartOfAccountsRep.findsccount("ACC00065");
		model.addAttribute("CurrentLiabilities", CurrentLiabilities.getAccountBalance());

		Erp_ChartOfAccounts capsamount = Erp_ChartOfAccountsRep.findsccount("ACC00011");
		model.addAttribute("caps", capsamount.getAccountBalance());

		return "balancesheet.html";

	}

	@RequestMapping(value = "Boil", method = { RequestMethod.GET, RequestMethod.POST })
	public String Boil(@RequestParam(required = false) String formmode, @RequestParam(required = false) String acct_num,
			String bomId, String keyword, Model md, HttpServletRequest req) {

		String loginuserid = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(loginuserid));

		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("add")) {

			md.addAttribute("formmode", "add");
			md.addAttribute("rawmaterial", ItemCreationrep.getitemrawmaterialandfg());
			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);
		} else if (formmode.equals("history")) {

			md.addAttribute("formmode", "history");
			md.addAttribute("bomdetails", pOM_REPO.getbomdetails());
			System.out.println("size" + pOM_REPO.getbomdetails());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("deleteoption", "view");
			// md.addAttribute("bomdetails", pOM_REPO.getbomdetails());
			// System.out.println("size" +pOM_REPO.getbomdetails());
			System.out.println("bomid" + bomId);

			md.addAttribute("bomdetails", pOM_REPO.getbomforview(bomId));
			md.addAttribute("getnullvalue", pOM_REPO.getbomforviewnull(bomId));

		}

		else if (formmode.equals("modify")) {

			md.addAttribute("formmode", "modify");
			// md.addAttribute("bomdetails", pOM_REPO.getbomdetails());
			// System.out.println("size" +pOM_REPO.getbomdetails());
			System.out.println("bomid" + bomId);

			md.addAttribute("bomdetails", pOM_REPO.getbomforview(bomId));
			md.addAttribute("getnullvalue", pOM_REPO.getbomforviewnull(bomId));
			md.addAttribute("rawmaterial", ItemCreationrep.getitemrawmaterial());

		} else if (formmode.equals("delete")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("deleteoption", "delete");
			md.addAttribute("bomdetails", pOM_REPO.getbomforview(bomId));
			md.addAttribute("getnullvalue", pOM_REPO.getbomforviewnull(bomId));
			System.out.println("bomid" + bomId);
		}

		return "boil";
	}

	// getbatch
	@RequestMapping(value = "getbatch_pricesfg", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<TSK_PROCESS_ENTITY> getbatchbysfg(@RequestParam(value = "itemCode", required = false) String itemCode) {

		System.out.println("the enter");
		// List<PO_invoice_entity> response = PO_invoice_reps.getdetailbatch(itemCode);
		List<TSK_PROCESS_ENTITY> response = TSK_PROCESS_REP.getprocessCompletedwithstock();
		System.out.println("the responselllll" + response);
		return response;

	}
	

	@RequestMapping(value = "notify", method = RequestMethod.POST)
	@ResponseBody
	public String notify(@ModelAttribute Notify_Entity userform, Model md, String formmode,
			HttpServletRequest rq) {

		System.out.println("Enter...");


		String msg = Follow__ups.addnotify(userform, formmode);
		md.addAttribute("menu", "BTMHeaderMenu"); // To highlight the menu

		return msg;

	}

	@RequestMapping(value = "deletenotify", method = RequestMethod.POST)
	@ResponseBody
	public String deletenotify(@RequestParam(required = false) String id, HttpServletRequest rq) {

		String msg = "";
		System.out.println("deleting notify...");
		// Validate userId parameter
		if (id == null || id.isEmpty()) {
			return "User ID is required for Deletion.";
		}

		// Fetch user entity from repository
		Notify_Entity entity = Notify_Entity_Reps.getalls(id);

		entity.setDelFlg("Y");
		Notify_Entity_Reps.save(entity);
		msg = "Notification Deleted successfully.";

		return msg;
	}
	@RequestMapping(value = "notificationMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String notificationMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, @RequestParam(required = false) String id, Model md,
			HttpServletRequest req) throws ParseException {
		String userId1 = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(userId1));
		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("formmode", "list");
			md.addAttribute("UserProfilelist", Notify_Entity_Reps.getall());
			System.out.println("Notification List Size: " + Notify_Entity_Reps.getall().size());
			/*
			 * List<String> list = Branch_reps.find_branch_id();
			 * md.addAttribute("branchIds", list);
			 */
			
			List<String> OrgIds = TSK_OrganizationMasterReps.get_org();
			md.addAttribute("OrgIds", OrgIds);
		}

		else if (formmode.equals("add")) {
			md.addAttribute("formmode", formmode);
			/*
			 * List<String> list = Branch_reps.find_branch_id();
			 * md.addAttribute("branchIds", list);
			 */
			
			List<String> OrgIds = TSK_OrganizationMasterReps.get_org();
			md.addAttribute("OrgIds", OrgIds);
			List<String> process_list=ProcessReps.getall();
			md.addAttribute("process_list", process_list);

		}

		else if (formmode.equals("view")) {
			System.out.println("The id is :"+id);
			md.addAttribute("formmode", formmode);
			md.addAttribute("forms", "view");
			Notify_Entity user = Notify_Entity_Reps.getbyid(id);
			if (user != null) {
			    String decryptedPassword;
				try {
					decryptedPassword = AES.decrypt(user.getPassword());
				    user.setPassword(decryptedPassword);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			md.addAttribute("UserProfilelist", user);

			List<String> process_list=ProcessReps.getall();
			md.addAttribute("process_list", process_list);
			

		} else if (formmode.equals("modify")) {
			md.addAttribute("formmode", formmode);
			/*
			 * List<String> list = Branch_reps.find_branch_id();
			 * md.addAttribute("branchIds", list);
			 */
			
			List<String> OrgIds = TSK_OrganizationMasterReps.get_org();
			md.addAttribute("OrgIds", OrgIds);
			
			Notify_Entity user = Notify_Entity_Reps.getbyid(id);
			if (user != null) {
			    String decryptedPassword;
				try {
					decryptedPassword = AES.decrypt(user.getPassword());
				    user.setPassword(decryptedPassword);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			md.addAttribute("UserProfilelist", user);

			List<String> process_list=ProcessReps.getall();
			md.addAttribute("process_list", process_list);

		}

		else if (formmode.equals("delete")) {
			md.addAttribute("formmode", "view");
			md.addAttribute("forms", "delete");
			Notify_Entity user = Notify_Entity_Reps.getbyid(id);
			if (user != null) {
			    String decryptedPassword;
				try {
					decryptedPassword = AES.decrypt(user.getPassword());
				    user.setPassword(decryptedPassword);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			md.addAttribute("UserProfilelist", user);

			List<String> process_list=ProcessReps.getall();
			md.addAttribute("process_list", process_list);
			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);
		}

		return "notificationMaster";
	}
	
	@RequestMapping(value = "offer_alert", method = { RequestMethod.GET, RequestMethod.POST })
	public String offer_alert(@RequestParam(required = false) String formmode, @RequestParam(required = false) String id, Model md,
			HttpServletRequest req) throws ParseException {
		String userId1 = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(userId1));
		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("formmode", "list");
			md.addAttribute("UserProfilelist", OFFER_ALERT_REPs.getall());
			System.out.println("Notification List Size: " + Notify_Entity_Reps.getall().size());
			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);
		}

		else if (formmode.equals("add")) {
			md.addAttribute("formmode", formmode);
			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);

		}

		else if (formmode.equals("view")) {
			System.out.println("The id is :"+id);
			md.addAttribute("formmode", formmode);
			md.addAttribute("forms", "view");
			OFFER_ALERT_ENTITY user = OFFER_ALERT_REPs.getbyid(id);
			if (user != null) {
			    String decryptedPassword;
				try {
					decryptedPassword = AES.decrypt(user.getPassword());
				    user.setPassword(decryptedPassword);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			md.addAttribute("UserProfilelist", user);
			

		} else if (formmode.equals("modify")) {
			md.addAttribute("formmode", formmode);
			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);
			OFFER_ALERT_ENTITY user = OFFER_ALERT_REPs.getbyid(id);
			if (user != null) {
			    String decryptedPassword;
				try {
					decryptedPassword = AES.decrypt(user.getPassword());
				    user.setPassword(decryptedPassword);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			md.addAttribute("UserProfilelist", user);

		}

		else if (formmode.equals("delete")) {
			md.addAttribute("formmode", "view");
			md.addAttribute("forms", "delete");
			OFFER_ALERT_ENTITY user = OFFER_ALERT_REPs.getbyid(id);
			if (user != null) {
			    String decryptedPassword;
				try {
					decryptedPassword = AES.decrypt(user.getPassword());
				    user.setPassword(decryptedPassword);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			md.addAttribute("UserProfilelist", user);
			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);
		}

		return "offer_alert";
	}

	@PostMapping("/offer_add")
	@ResponseBody
	public String offer_add(@RequestParam(required = false) String id,
	                        @RequestParam("branchId") String branchId,
	                        @RequestParam("alert") String alert,
	                        @RequestParam("email") String email,
	                        @RequestParam("password") String password,
	                        @RequestParam("vendor") String vendor,
	                        @RequestParam("file") MultipartFile file,
	                        @RequestParam String formmode) {

	    OFFER_ALERT_ENTITY userform = new OFFER_ALERT_ENTITY();
	    userform.setBranchId(branchId);
	    userform.setAlert(alert);
	    userform.setEmail(email);
	    userform.setPassword(password);
	    userform.setVendor(vendor);

	    byte[] fileBytes = null;

	    try {
	        if (!file.isEmpty()) {
	            fileBytes = file.getBytes();
	            System.out.println("file bytes: " + fileBytes);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        return "Error processing file: " + e.getMessage();
	    }
System.out.println("The id is: "+id);
	    // Call the service layer with the id
	    return Follow__ups.offer_add(userform, formmode, fileBytes, id);
	}

	@GetMapping("/notification/file/{id}")
	public ResponseEntity<byte[]> getFile(@PathVariable("id") String id) {
	    OFFER_ALERT_ENTITY user = OFFER_ALERT_REPs.getbyid(id);
	    byte[] fileContent = user.getFile();

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.IMAGE_JPEG); // or IMAGE_PNG
	    headers.setContentLength(fileContent.length);
	    return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
	}


	@RequestMapping(value = "deleteOffer", method = RequestMethod.POST)
	@ResponseBody
	public String deleteOffer(@RequestParam(required = false) String id, HttpServletRequest rq) {

		String msg = "";
		System.out.println("deleting notify...");
		// Validate userId parameter
		if (id == null || id.isEmpty()) {
			return "User ID is required for Deletion.";
		}

		// Fetch user entity from repository
		OFFER_ALERT_ENTITY entity = OFFER_ALERT_REPs.getalls(id);

		entity.setDelFlg("Y");
		OFFER_ALERT_REPs.save(entity);
		msg = "Notification Deleted successfully.";

		return msg;
	}

	@RequestMapping(value = "getOfferbranchlist", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getOfferbranchlist(@RequestParam("branchId") String branchId,
			@RequestParam(required=false) String org_id) {
		try {
			System.out.println("the branch" + org_id);
			List<OFFER_ALERT_ENTITY> list = OFFER_ALERT_REPs.getuserid(branchId,org_id);
			return ResponseEntity.ok(list);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}
	
	@RequestMapping(value = "getnotifybranchlist", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getnotifybranchlist(@RequestParam("branchId") String branchId) {
		try {
			List<Notify_Entity> list = Notify_Entity_Reps.getuserid(branchId);
			return ResponseEntity.ok(list);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	//boiltoprocess
	@PostMapping("/add_boil_process_tsk")
	public ResponseEntity<String> add_boil_process_tsk(@RequestParam(required = false) String formmode,
			@RequestBody List<TSK_PROCESS_ENTITY> TSK_PROCESS_ENTITY) {
		ResponseEntity<String> msg = null;
		try {
			if ("add".equals(formmode) || "edit".equals(formmode)) {
				System.out.println("Form mode: " + formmode);
				System.out.println("Received processEntityList: " + TSK_PROCESS_ENTITY);
				msg = adminOperServices.add_boilprocess_tsk(TSK_PROCESS_ENTITY, formmode);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
		return msg;
	}
	
	@RequestMapping(value = "tskValidation", method = { RequestMethod.GET, RequestMethod.POST })
	public String tskValidation(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String process_id, Model md, HttpServletRequest req) {

		String loginuserid = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(loginuserid));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null) {

			md.addAttribute("processlist", TSK_PROCESS_REP.getprocessnew());
			md.addAttribute("formmode", "list");
			md.addAttribute("bomdetails", pOM_REPO.getbomdetails());
			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);

		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");
			md.addAttribute("processwithid", TSK_PROCESS_REP.getprocessbyiditem(process_id));
			md.addAttribute("processwithoutid", TSK_PROCESS_REP.getprocessbyidexp(process_id));
			md.addAttribute("packmaterial", ItemCreationrep.getitemPackingMaterial());
			md.addAttribute("rawmaterial", PO_invoice_reps.getItems());
			md.addAttribute("bomdetails", pOM_REPO.getbomdetails());

		}

		return "TskValidation";
	}
	
	@RequestMapping(value = "TskConvertToFG", method = { RequestMethod.GET, RequestMethod.POST })
	public String tskConvertToFG(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String process_id, Model md, HttpServletRequest req) {
		String loginuserid = (String) req.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", resourceMasterRepo.getrole(loginuserid));
		md.addAttribute("RoleMenu", hrmsrepoo.getrole(loginuserid));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null) {

			md.addAttribute("formmode", "sfglist");
			md.addAttribute("bomdetails", pOM_REPO.getbomdetails());
			md.addAttribute("processlist", TSK_PROCESS_REP.getprocessCompleted());
			System.out.println("size" + pOM_REPO.getbomdetails());
			List<String> list = Branch_reps.find_branch_id();
			md.addAttribute("branchIds", list);

		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");
			md.addAttribute("process_id", process_id);

			md.addAttribute("processwithid", TSK_PROCESS_REP.getprocessbyiditem(process_id));
			md.addAttribute("rawmaterial", ItemCreationrep.getitemPackingMaterial());

		}

		return "tskConvertfg";
	}
	
	
	@RequestMapping(value = "TskValidationadd", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String TskValidationadd(
	        @RequestParam(required = false) String processid,
	        @RequestParam(required = false) BigDecimal returnquantity,
	        @RequestParam(required = false) BigDecimal actualkasadu,
	        @RequestParam(required = false) BigDecimal differghee,
	        @RequestParam(required = false) BigDecimal differkasadu,
	        @RequestParam(required = false) String units,
	        HttpServletRequest rq) {

	    String userid = (String) rq.getSession().getAttribute("USERID");

	    String msg = BHMS_services.tskvalidation(processid, units, returnquantity, actualkasadu, differghee, differkasadu);
	    return msg;
	}

	
	


}