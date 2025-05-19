package com.bornfire.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.validation.constraints.NotNull;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import com.bornfire.entities.BankMaster;
import com.bornfire.entities.BankMasterRep;
import com.bornfire.entities.ClientMaster;
import com.bornfire.entities.ClientMasterRep;
import com.bornfire.entities.InvoiceMaster;
import com.bornfire.entities.InvoiceMasterRep;
import com.bornfire.entities.Inward;
import com.bornfire.entities.InwardRep;
import com.bornfire.entities.LeaveTable;
import com.bornfire.entities.LeaveTableRep;
import com.bornfire.entities.Outward;
import com.bornfire.entities.OutwardRep;
import com.bornfire.entities.PROCESS_ENTITY;
import com.bornfire.entities.PROCESS_REP;
import com.bornfire.entities.PlacementMaintenance;
import com.bornfire.entities.PlacementMaintenanceRep;
import com.bornfire.entities.PlacementMaster;
import com.bornfire.entities.PlacementMasterRep;
import com.bornfire.entities.PlacementResourceMaster;
import com.bornfire.entities.PlacementResourcesMasterRepo;
import com.bornfire.entities.ProfessionalCharge;
import com.bornfire.entities.ProfessionalChargeRep;
import com.bornfire.entities.Purchase_Order_Entity;
import com.bornfire.entities.Purchase_Order_Repo;
import com.bornfire.entities.Taxation_parameter_Rep;
import com.bornfire.entities.Taxation_parameters;
import com.bornfire.entities.TimesheetManagement;
import com.bornfire.entities.TimesheetManagementRep;
import com.bornfire.entities.WorkOrder;
import com.bornfire.entities.WorkOrderRep;
import com.bornfire.entities.perfomance_evaluation;
import com.bornfire.entities.perfomance_evaluation_REP;
import com.bornfire.entities.salary_parameter;
import com.bornfire.entities.salary_parameter_rep;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

//import antlr.collections.List;
//import com.bornfire.entities.UserProfile;

@Service
@ConfigurationProperties("output")
@Transactional
public class PlacementServices {

	// private static final Logger logger =
	// LoggerFactory.getLogger(PlacementServices.class);

	


	@Autowired
	perfomance_evaluation_REP perfomance_evaluation_rep;
	
	@Autowired
	WorkOrderRep WorkOrderRep;
	
	@Autowired
	PROCESS_REP PROCESS_REP;
	
	@Autowired
	Purchase_Order_Repo Purchase_Order_Repo;
	
	@Autowired
	InwardRep InwardRep;
	
	@Autowired
	OutwardRep OutwardRep;
	
	  @Autowired
	  private LeaveTableRep leaveTableRep;


	private static final Logger logger = LoggerFactory.getLogger(PlacementServices.class);
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	Taxation_parameter_Rep Taxation_parameter_rep;

	@Autowired
	DataSource srcdataSource;

	@Autowired
	ClientMasterRep clientMasterRep;
	
	@Autowired
	BankMasterRep bankMasterRep;
	
	@Autowired
	PlacementResourcesMasterRepo placementResourcesMasterRepo;

	@Autowired
	PlacementMasterRep placementMasterRep;
	
	@Autowired
	Environment env;

	@Autowired
	TimesheetManagementRep timesheetManagementRep;

	@Autowired
	ProfessionalChargeRep professionalChargeRep;

	@Autowired
	InvoiceMasterRep invoiceMasterRep;
	
	@Autowired
	salary_parameter_rep salary_parameter_Rep;
	
	@Autowired
	PlacementMaintenanceRep placementmaintenancerep;
	
	
	
	@NotNull
	private String exportpath;

	@Value("${default.password}")
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getExportpath() {
		return exportpath;
	}

	public void setExportpath(String exportpath) {
		this.exportpath = exportpath;
	}
	
	
	
//	===============Client Master===================

	/*
	 * public String addClientUser(ClientMaster clientMaster, String formmode) {
	 * 
	 * String msg = ""; System.out.println("jaan");
	 * 
	 * if (formmode.equals("add")) {
	 * 
	 * ClientMaster up = clientMaster;
	 * 
	 * up.setEntity_flg("Y");
	 * 
	 * clientMasterRep.save(up);
	 * 
	 * msg = "Added Successfully"; } else if (formmode.equals("edit")) {
	 * 
	 * ClientMaster up = clientMaster; up.setEntity_flg("Y");
	 * clientMasterRep.save(up); msg = "Edited Successfully"; }
	 * 
	 * return msg; }
	 * 
	 * // Get User
	 * 
	 * public ClientMaster getClientUser(String id) {
	 * 
	 * if (clientMasterRep.existsById(id)) { ClientMaster up =
	 * clientMasterRep.findById(id).get(); up.getEntity_flg(); return up; } else {
	 * return new ClientMaster(); } };
	 */

	// Get List




//	====================== Resource Master =========================

	public String addUser(PlacementResourceMaster placementResourceMaster, String formmode) {

		String msg = "";

		if (formmode.equals("add")) {

			PlacementResourceMaster up = placementResourceMaster;

			up.setEntity_flg("Y");
			placementResourcesMasterRepo.save(up);

			msg = "Added Successfully";
		} else if (formmode.equals("edit")) {

			PlacementResourceMaster up = placementResourceMaster;

			up.setEntity_flg("Y");
			placementResourcesMasterRepo.save(up);

			msg = "Edited Successfully";
		}
		return msg;
	}

//	Get user
	public PlacementResourceMaster getUser(String id) {

		if (placementResourcesMasterRepo.existsById(id)) {
			PlacementResourceMaster up = placementResourcesMasterRepo.findById(id).get();
			up.getEntity_flg();
			return up;
		} else {
			return new PlacementResourceMaster();
		}

	};

//	get List

	public List<PlacementResourceMaster> getUsersList() {

		List<PlacementResourceMaster> users = placementResourcesMasterRepo.getplacementlist();

		return users;

	}

//	============  Professional Charge  =================

	public String addUser1(ProfessionalCharge professionalCharge, String formmode) {

		String msg = "";

		if (formmode.equals("add")) {

			ProfessionalCharge up = professionalCharge;
			professionalChargeRep.save(up);
			up.setEntity_flg("Y");
			msg = "Added Successfully";

		} else if (formmode.equals("edit")) {

			ProfessionalCharge up = professionalCharge;
			up.setEntity_flg("Y");
			professionalChargeRep.save(up);
			msg = "Edited Successfully";

		}
		return msg;
	}

	// get user

	public ProfessionalCharge getUser1(BigDecimal id) {

		if (professionalChargeRep.existsById(id)) {
			ProfessionalCharge up = professionalChargeRep.findById(id).get();
			up.getEntity_flg();
			return up;
		} else {
			return new ProfessionalCharge();
		}

	};

	// userList

	public List<ProfessionalCharge> getUsersList1() {

		List<ProfessionalCharge> users = professionalChargeRep.getplacementlist1();

		return users;

	}

//	============  InvoiceMaster Charge  =================

	/*
	 * public String addInvoiceUser(InvoiceMaster invoiceMaster, String formmode) {
	 * 
	 * String msg = "";
	 * 
	 * if (formmode.equals("add")) {
	 * 
	 * InvoiceMaster up = invoiceMaster; invoiceMasterRep.save(up);
	 * up.setEntity_flg("Y"); msg = "Added Successfully";
	 * 
	 * } else if (formmode.equals("edit")) {
	 * 
	 * InvoiceMaster up = invoiceMaster; up.setEntity_flg("Y");
	 * invoiceMasterRep.save(up); msg = "Edited Successfully";
	 * 
	 * } return msg; }
	 */
	


//	get user

	/*
	 * public InvoiceMaster getInvoiceUser(String invoice_no) {
	 * 
	 * if (invoiceMasterRep.existsById(invoice_no)) { InvoiceMaster up =
	 * invoiceMasterRep.findById(invoice_no).get(); //up.getEntity_flg(); return up;
	 * } else { return new InvoiceMaster(); } };
	 */

	// get List

	/*
	 * public List<InvoiceMaster> getInvoiceList() {
	 * 
	 * List<InvoiceMaster> users = invoiceMasterRep.getplacementlist2();
	 * 
	 * return users;
	 * 
	 * }
	 */
	
	

//	===============Placement Master===================

	public String addPlacementUser(PlacementMaster placementMaster, String formmode) {

		String msg = "";

		if (formmode.equals("add")) {

			PlacementMaster up = placementMaster;

			up.setEntity_flg("Y");

			placementMasterRep.save(up);

			msg = "Added Successfully";
		} else if (formmode.equals("edit")) {

			PlacementMaster up = placementMaster;
			up.setEntity_flg("Y");
			placementMasterRep.save(up);
			msg = "Edited Successfully";
		}

		return msg;
	}

	// Get User

	public PlacementMaster getPlacementUser(String id) {

		if (placementMasterRep.existsById(id)) {
			PlacementMaster up = placementMasterRep.findById(id).get();
			up.getEntity_flg();
			return up;
		} else {
			return new PlacementMaster();
		}
	};
	
	// clientmaster
	
	/*
	 * public ClientMaster getClientlist(String id) {
	 * 
	 * if(clientMasterRep.existsById(id)) {
	 * 
	 * ClientMaster up = clientMasterRep.findById(id).get(); up.getEntity_flg();
	 * return up; } else { return new ClientMaster(); } }
	 */
	// Get List

	public List<PlacementMaster> getPlacementMasterlist(String id, String name) {

		List<PlacementMaster> users = placementMasterRep.findByParam(id, name);

		return users;

	}

//	Time sheet 

	public String addTimesheetUser(TimesheetManagement timesheetManagement, String formmode) {

		String msg = "";

		if (formmode.equals("add")) {

			TimesheetManagement up = timesheetManagement;

			up.setEntity_flg("Y");

			timesheetManagementRep.save(up);

			msg = "Added Successfully";
			
		} else if (formmode.equals("edit")) {

			TimesheetManagement up = timesheetManagement;

			up.setEntity_flg("Y");
			timesheetManagementRep.save(up);
			msg = "Edited Successfully";
		}

		return msg;
	}

	// Get User

	public TimesheetManagement getTimesheetUser(String id) {

		if (timesheetManagementRep.existsById(id)) {
			TimesheetManagement up = timesheetManagementRep.findById(id).get();
			up.getEntity_flg();
			return up;
		} else {
			return new TimesheetManagement();
		}
	};

//	Get List

	public List<TimesheetManagement> getTimesheetManagementList(String id, String name) {

		List<TimesheetManagement> users = timesheetManagementRep.findByParam(id, name);

		return users;

	}
//==================================JASPER==============
	public File getFile(String inv_no,  String filetype) throws FileNotFoundException, JRException, SQLException {

		System.out.println("0000");
		//logger.info(pdfgenerator);
		String path = env.getProperty("output.exportpath");
	//	D:\JasperDownload
		
		System.out.println(path);
		
		String fileName = "";
		String zipFileName = "";
		File outputFile;
		

		logger.info("Getting Output file : Third_PARTY");

		fileName = "INVOICE_" + inv_no;

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			//logger.info("Getting Jasper file :" + "Third_PARTY");

			if (filetype.equals("pdf")) {
				System.out.println("inner pdf");
					jasperFile = this.getClass()
							.getResourceAsStream("/static/jasper/Invoice_Master1.jrxml");
				}else {
				
					jasperFile = this.getClass()
							.getResourceAsStream("/static/jasper/Invoice_Master1.jrxml");
				
			}

			System.out.println("#####");
			JasperReport jr = JasperCompileManager.compileReport(jasperFile);
			System.out.println("@@@@@@@@");
			System.out.println(jr);
			System.out.println("@@@@@@@@");
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			//logger.info("Assigning Parameters for Jasper");
			
			
			map.put("INV_NO", inv_no);
			
			if (filetype.equals("pdf")) {
				fileName = fileName + ".pdf";
				path = path+fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JasperExportManager.exportReportToPdfFile(jp, path);
				
			}else {

				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);
		return outputFile;
	}
//============================================JASPER END ============================
	
	
//	public String exportReport(String reportFormat) throws FileNotFoundException,JRException {
//		
//		List<InvoiceMaster> invoice = (List<InvoiceMaster>) invoiceMasterRep.findAll();
//		//load a file and compile it
//		File file = org.springframework.util.ResourceUtils.getFile("classpath:Invoice_Master.jrxml");
//		JasperReport jasper = JasperCompileManager.compileReport(file.getAbsolutePath());
//		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(invoice);
//		Map<String, Object> parameters = new HashMap<>();
//		parameters.put("CreatedBy", "invoice");
//		JasperPrint jasperprint = JasperFillManager.fillReport(jasper, parameters, datasource);
//		
//		if(reportFormat.equalsIgnoreCase("pdf")) {
//			
//			JasperExportManager.exportReportToPdfFile(jasperprint, "C:\\invoice"+"invoice.pdf");
//
//		}
//		return "";
//		
//	}

	
	
	//==========================Bank Master================================
	
	@SuppressWarnings("unchecked")
	public Object getBanklist() {
		 List<BankMaster> Banklist =(List<BankMaster>)
			        sessionFactory.getCurrentSession().createQuery("from BankMaster where del_flg='N' order by BANK_SRL_NO").getResultList();

		return Banklist;
	}
	
	public String addBankuser(BankMaster bankMaster, String formmode,String user) {
        String msg = "";
        try {
        	DecimalFormat numformate = new DecimalFormat("000");
        	BigInteger SRLNUMBER = (BigInteger) sessionFactory.getCurrentSession().createNativeQuery("SELECT NEXT VALUE FOR B_SRL_NO AS SRL_NO")
					.getSingleResult();
        	BigDecimal SRLNUMBERS = new BigDecimal(SRLNUMBER);
			String srlno = numformate.format(SRLNUMBERS);
			String u="00";
			
        	BankMaster up = bankMaster;
        	up.setBank_srl_no(srlno);
        	up.setEntity_flg("N");
        	up.setDel_flg("N");
        	up.setModify_flg("N");
        	up.setEntry_user(user);
        	up.setEntry_time(new Date());
        	bankMasterRep.save(up);
             msg = "Bank Added Successfully";
        } catch (Exception e) {
            msg = "Error Occured. Please contact Administrator";
            e.printStackTrace();
        }
        return msg;
    }
	
	public String bankMasterModify(BankMaster bankMaster , String bank_srl_no , String user) {
		
		String msg="";
		try {
			
			//ClientMaster clientMasterOld = clientMasterRep.getClientlist(client_srl_no) ;
			//clientMasterOld.setClient_srl_no(clientMaster.getClient_srl_no());
			BankMaster bankold = bankMasterRep.getBanklist(bank_srl_no);
			
			bankold.setBank_srl_no(bankMaster.getBank_srl_no());
			bankold.setBank_name(bankMaster.getBank_name());
			bankold.setBank_br(bankMaster.getBank_br());
			bankold.setBank_addr(bankMaster.getBank_addr());
			bankold.setAcct_type(bankMaster.getAcct_type());
			bankold.setAcc_number(bankMaster.getAcc_number());
			bankold.setAcc_name(bankMaster.getAcc_name());
			bankold.setAcct_crncy(bankMaster.getAcct_crncy());
			bankold.setIfsc_code(bankMaster.getIfsc_code());
			bankold.setSwift_code(bankMaster.getSwift_code());
			bankold.setPayment_code(bankMaster.getPayment_code());
			bankold.setModify_user(user);
			bankold.setModify_time(new Date());
			bankold.setEntity_flg("N");
			bankold.setModify_flg("Y");
			bankMasterRep.save(bankold);
			
			msg="Bank Modified Successfully";
		} catch (Exception e) {
			msg = "Error Occured. Please contact Administrator";
			e.printStackTrace();
		}
		return msg;
		
	}
	
public String bankMasterVerify(BankMaster bankMaster, String bank_srl_no, String user) {
		
		String msg="";
		try {
			
			BankMaster bankold = bankMasterRep.getBanklist(bank_srl_no);
			
				
				bankold.setBank_srl_no(bankMaster.getBank_srl_no());
				bankold.setBank_name(bankMaster.getBank_name());
				bankold.setBank_br(bankMaster.getBank_br());
				bankold.setBank_addr(bankMaster.getBank_addr());
				bankold.setAcct_type(bankMaster.getAcct_type());
				bankold.setVerify_user(user);
				bankold.setVerify_time(new Date());
				bankold.setEntity_flg("Y");
				bankold.setModify_flg("N");
				bankMasterRep.save(bankold);
				msg="Verified succesfully";
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
public String bankMasterDelete(BankMaster bankMaster, String bank_srl_no, String user) {
	
	String msg="";
	try {
		BankMaster bankold = bankMasterRep.getBanklist(bank_srl_no);
		
		
		
			//System.out.println(clientMaster.getClient_srl_no());
			bankold.setBank_srl_no(bankMaster.getBank_srl_no());
			bankold.setDel_flg("Y");
			//clientMasterOld.setEntity_flg("N");
			bankMasterRep.save(bankold);
			msg="Deleted succesfully";
			
			
			
		
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	return msg;
}
	
	
	//==========================Client Master===========================
	@SuppressWarnings("unchecked")
	public Object getClientlist() {
		 List<ClientMaster> Clientlist =(List<ClientMaster>)
			        sessionFactory.getCurrentSession().createQuery("from ClientMaster where del_flg='N' order by CLIENT_SRL_NO").getResultList();

		return Clientlist;
	}
	
	public String addClientUser(ClientMaster clientMaster, String formmode, String user) {
        String msg = "";
        try {
        	
        	DecimalFormat numformate = new DecimalFormat("VEN000");
        	BigInteger ClientNumbe = (BigInteger) sessionFactory.getCurrentSession().createNativeQuery("SELECT NEXT VALUE FOR CLIENT_SRL_NO AS SRL_NO ")
					.getSingleResult();
        	BigDecimal ClientNumber = new BigDecimal(ClientNumbe);

			String srlno = numformate.format(ClientNumber);
			
			
        	ClientMaster up = clientMaster;
        	up.setClient_srl_no(srlno);
        	up.setDel_flg("N");
			up.setModify_flg("N");
			up.setEntity_flg("N");
			up.setEntry_user(user);
			up.setEntry_time(new Date());
        	
            clientMasterRep.save(up);
             msg = "Client Added Successfully";
        } catch (Exception e) {
            msg = "Error Occured. Please contact Administrator";
            e.printStackTrace();
        }
        return msg;
    }

	
	public String clientMasterModify(ClientMaster clientMaster, String client_srl_no, String user) {
		
		String msg="";
		try {
			ClientMaster clientMasterOld = clientMasterRep.getClientlist(client_srl_no) ; 
			
				
				clientMasterOld.setClient_srl_no(clientMaster.getClient_srl_no());
				clientMasterOld.setPhone(clientMaster.getPhone());
				clientMasterOld.setContact_person(clientMaster.getContact_person());
				clientMasterOld.setClient_type(clientMaster.getClient_type());
				clientMasterOld.setClient_name(clientMaster.getClient_name());
				clientMasterOld.setClient_addr(clientMaster.getClient_addr());
				clientMasterOld.setEmail(clientMaster.getEmail());
				clientMasterOld.setClient_location(clientMaster.getClient_location());
				clientMasterOld.setCin_no(clientMaster.getCin_no());
				clientMasterOld.setGstin(clientMaster.getGstin());
				clientMasterOld.setHsn_code(clientMaster.getHsn_code());
				clientMasterOld.setVendor_code(clientMaster.getVendor_code());
				clientMasterOld.setBill_to(clientMaster.getBill_to());
				clientMasterOld.setDeliver_to(clientMaster.getDeliver_to());
				clientMasterOld.setModify_user(user);
				clientMasterOld.setModify_time(new Date());

				clientMasterOld.setEntity_flg("N");
				clientMasterOld.setModify_flg("Y");
				
				clientMasterRep.save(clientMasterOld);
				msg="Modified succesfully";
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	public String clientMasterVerify(ClientMaster clientMaster, String client_srl_no, String user) {
		
		String msg="";
		try {
			ClientMaster clientMasterOld = clientMasterRep.getClientlist(client_srl_no) ; 
			
				
				clientMasterOld.setClient_srl_no(clientMaster.getClient_srl_no());
				clientMasterOld.setPhone(clientMaster.getPhone());
				clientMasterOld.setVerify_user(user);
				clientMasterOld.setVerify_time(new Date());
				clientMasterOld.setEntity_flg("Y");
				clientMasterOld.setModify_flg("N");
				
				clientMasterRep.save(clientMasterOld);
				msg="Verified succesfully";
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	public String clientMasterDelete(ClientMaster clientMaster, String client_srl_no) {
		
		String msg="";
		try {

			ClientMaster clientMasterOld = clientMasterRep.getClientlist(client_srl_no) ; 
			
	
				
				clientMasterOld.setDel_flg("Y");
				clientMasterOld.setEntity_flg("N");
				clientMasterRep.save(clientMasterOld);
				
				
				/*
				 * clientMasterOld.setClient_srl_no(clientMaster.getClient_srl_no());
				 * clientMasterOld.setClient_type(clientMaster.getClient_type());
				 * clientMasterOld.setClient_name(clientMaster.getClient_name());
				 * clientMasterOld.setClient_addr(clientMaster.getClient_addr());
				 * clientMasterOld.setContact_person(clientMaster.getContact_person());
				 * clientMasterOld.setPhone(clientMaster.getPhone());
				 * clientMasterOld.setEmail(clientMaster.getEmail());
				 * clientMasterOld.setClient_location(clientMaster.getClient_location());
				 * clientMasterOld.setCin_no(clientMaster.getCin_no());
				 * clientMasterOld.setGstin(clientMaster.getGstin());
				 * clientMasterOld.setHsn_code(clientMaster.getHsn_code());
				 * clientMasterOld.setVendor_code(clientMaster.getVendor_code());
				 * clientMasterOld.setBill_to(clientMaster.getBill_to());
				 * clientMasterOld.setDeliver_to(clientMaster.getDeliver_to());
				 */
				
				msg="Deleted succesfully";
				
				
				
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	
	@SuppressWarnings("unchecked")
	public Object getplacementlist2() {
		 List<InvoiceMaster> invoicelist =(List<InvoiceMaster>)
			        sessionFactory.getCurrentSession().createQuery("from InvoiceMaster").getResultList();

		return invoicelist;
	}
	
	public String addInvoiceUser(InvoiceMaster invoiceMaster, String formmode) {
        String msg = "";
        try {
        	InvoiceMaster up = invoiceMaster;
        	
        	BigInteger srno=(BigInteger) sessionFactory.getCurrentSession().createNativeQuery("SELECT NEXT VALUE FOR INVOICE_SRL_NO AS SRL_NO").getSingleResult();
        	up.setPo_id(String.valueOf(srno));
        	invoiceMasterRep.save(up);
             msg = "Invoice Added Successfully";
        } catch (Exception e) {
            msg = "Error Occured. Please contact Administrator";
            e.printStackTrace();
        }
        return msg;
    }
	
	public String invoiceMasterModify(InvoiceMaster invoiceMaster, String po_id) {
		
		String msg="gg";
		try {
			InvoiceMaster invoiceold = invoiceMasterRep.getplacementlist2(po_id);
			System.out.println(invoiceMaster.getPo_id());
			
			System.out.println(invoiceMaster.getInv_no());
			System.out.println(invoiceMaster.getInv_due_date());
			System.out.println(invoiceMaster.getStatus());
			
			
			invoiceold.setInv_no(invoiceMaster.getInv_no());
			invoiceold.setInv_due_date(invoiceMaster.getInv_due_date());
			invoiceold.setInv_date(invoiceMaster.getInv_date());
			invoiceold.setStatus(invoiceMaster.getStatus());
			invoiceold.setTotal_amt(invoiceMaster.getTotal_amt());
			invoiceold.setEmp_id(invoiceMaster.getEmp_id());
			invoiceold.setEmp_name(invoiceMaster.getEmp_name());
			invoiceold.setPm_email(invoiceMaster.getPm_email());
			invoiceold.setProj_mgr(invoiceMaster.getProj_mgr());
			invoiceold.setPo_delivery_date(invoiceMaster.getPo_delivery_date());
			invoiceold.setPo_rate_inr(invoiceMaster.getPo_rate_inr());
			invoiceold.setGrn_efforts(invoiceMaster.getGrn_efforts());
			invoiceold.setGrn_no(invoiceMaster.getGrn_no());
			invoiceold.setGrn_amt(invoiceMaster.getGrn_amt());
			invoiceold.setInv_cgst(invoiceMaster.getInv_cgst());
			invoiceold.setInv_sgst(invoiceMaster.getInv_sgst());
			invoiceold.setInv_igst(invoiceMaster.getInv_igst());
			invoiceold.setInv_tot_gst(invoiceMaster.getInv_igst());
			invoiceold.setInv_tot_amt(invoiceMaster.getInv_tot_amt());
			invoiceold.setGrn_date(invoiceMaster.getGrn_date());
			invoiceMasterRep.save(invoiceold);
			
			PlacementMaintenance placementMaintenance=placementmaintenancerep.getplacementlist2(po_id);
			placementMaintenance.setProj_mgr(invoiceMaster.getProj_mgr());
			placementMaintenance.setPm_email(invoiceMaster.getPm_email());
			placementMaintenance.setGrn_no(invoiceMaster.getGrn_no());
			placementMaintenance.setGrn_amt(invoiceMaster.getGrn_amt());
			placementMaintenance.setInv_cgst(invoiceMaster.getInv_cgst());
			placementMaintenance.setInv_sgst(invoiceMaster.getInv_sgst());
			placementMaintenance.setInv_igst(invoiceMaster.getInv_igst());
			placementMaintenance.setInv_tot_gst(invoiceMaster.getInv_igst());
			placementMaintenance.setInv_tot_amt(invoiceMaster.getInv_tot_amt());
			placementMaintenance.setGrn_efforts(invoiceMaster.getGrn_efforts());
			placementMaintenance.setPo_rate_inr(invoiceMaster.getPo_rate_inr());
			placementMaintenance.setPo_delivery_date(invoiceMaster.getPo_delivery_date());
			placementMaintenance.setEmp_id(invoiceMaster.getEmp_id());
			placementMaintenance.setInv_no(invoiceMaster.getInv_no());
			placementMaintenance.setInv_due_date(invoiceMaster.getInv_due_date());
			placementMaintenance.setInv_date(invoiceMaster.getInv_date());
			placementMaintenance.setEmp_name(invoiceMaster.getEmp_name());
			placementMaintenance.setGrn_date(invoiceMaster.getGrn_date());
			placementmaintenancerep.save(placementMaintenance);
			
			msg="Modified Successfully";
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	
	public String CreatePMAdd(PlacementMaintenance placementmaintenance, String formmode) {
		String msg = "";
		if(formmode.equals("add")) {
			
			DecimalFormat numformate = new DecimalFormat("0");
        	BigInteger PlacementSrlNo = (BigInteger) sessionFactory.getCurrentSession().createNativeQuery("SELECT NEXT VALUE FOR PM_SRL_NO AS SRL_NO")
					.getSingleResult();
        	BigDecimal srl_no = new BigDecimal(PlacementSrlNo);
			String pmsrlno = numformate.format(srl_no);
	
			PlacementMaintenance up = placementmaintenance;
			String pn=placementmaintenance.getPo_no();
			String ei=placementmaintenance.getEmp_id();
			SimpleDateFormat formatdate = new SimpleDateFormat("ddMMyyyy");
			String date2=formatdate.format(placementmaintenance.getPo_date());
			String pd=date2;
			System.out.println(pmsrlno);
			up.setSrl_no(pmsrlno);
			up.setPo_id(pn+ei+pd);
			placementmaintenancerep.save(up);
			
			msg = "PlacementMaintenance Added Successfully";
		}else if(formmode.equals("edit")) {
			
			PlacementMaintenance up1=(PlacementMaintenance) placementmaintenancerep.getPoMain(placementmaintenance.getPo_no());
			//PlacementMaintenance[] up1Array = new PlacementMaintenance[]{placementmaintenancerep.getPoMain(placementmaintenance.getPo_no())};
			System.out.println(up1);
			up1.setPo_date(placementmaintenance.getPo_date());
			up1.setExtn_flg(placementmaintenance.getExtn_flg());
			up1.setExtn_date(placementmaintenance.getExtn_date());
			up1.setProj_mgr(placementmaintenance.getProj_mgr());
			up1.setPm_email(placementmaintenance.getPm_email());
			up1.setUnit_loc(placementmaintenance.getUnit_loc());
			up1.setGstin(placementmaintenance.getGstin());
			up1.setEmp_id(placementmaintenance.getEmp_id());
			up1.setEmp_name(placementmaintenance.getEmp_name());
			up1.setVendor(placementmaintenance.getVendor());
			up1.setLocation(placementmaintenance.getLocation());
			up1.setNo_of_items(placementmaintenance.getNo_of_items());
			up1.setTotal_value(placementmaintenance.getTotal_value());
			
			
			
			placementmaintenancerep.save(up1);
			msg = "PlacementMaintenance Modified Successfully";
		}else if(formmode.equals("modifyg")) {
			
			PlacementMaintenance up5=placementmaintenancerep.getPoM((placementmaintenance.getPo_id()).substring(0,placementmaintenance.getPo_id().indexOf(",")));
			System.out.println(placementmaintenance.getPo_no());
			System.out.println(placementmaintenance.getGrn_amt());
			System.out.println(placementmaintenance.getGrn_date());
			System.out.println(placementmaintenance.getGrn_efforts());
			System.out.println(placementmaintenance.getGrn_no());
			up5.setGrn_no(placementmaintenance.getGrn_no());
			up5.setGrn_date(placementmaintenance.getGrn_date());
			up5.setGrn_amt(placementmaintenance.getGrn_amt());
			up5.setGrn_efforts(placementmaintenance.getGrn_efforts());
			
			System.out.println(placementmaintenance.getGrn_date());
			
			placementmaintenancerep.save(up5);
			
			InvoiceMaster op5 = invoiceMasterRep.getplacementlist2((placementmaintenance.getPo_id()).substring(0,placementmaintenance.getPo_id().indexOf(",")));
			op5.setGrn_no(placementmaintenance.getPo_no());
			op5.setGrn_date(placementmaintenance.getGrn_date());
			op5.setGrn_amt(placementmaintenance.getGrn_amt());
			op5.setGrn_efforts(placementmaintenance.getGrn_efforts());
			invoiceMasterRep.save(op5);
			msg = "GRN Updated Successfully";
		}
		else if(formmode.equals("modifyI")) {
			
			PlacementMaintenance upi=placementmaintenancerep.getPoM((placementmaintenance.getPo_id()).substring(0,placementmaintenance.getPo_id().indexOf(",")));
			System.out.println(placementmaintenance.getEmp_name());
			upi.setInv_no(placementmaintenance.getInv_no());
			upi.setInv_date(placementmaintenance.getInv_date());
			upi.setPo_delivery_date(placementmaintenance.getPo_delivery_date());
			upi.setPo_rate_inr(placementmaintenance.getPo_rate_inr());
			upi.setGrn_efforts(placementmaintenance.getGrn_efforts());
			upi.setGrn_amt(placementmaintenance.getGrn_amt());
			upi.setInv_igst(placementmaintenance.getInv_igst());
			upi.setInv_tot_gst(placementmaintenance.getInv_igst());
			upi.setInv_tot_amt(placementmaintenance.getInv_tot_amt());
			upi.setInv_due_date(placementmaintenance.getInv_due_date());
			
			System.out.println(placementmaintenance.getGrn_date());
			
			placementmaintenancerep.save(upi);
			
			InvoiceMaster opi = invoiceMasterRep.getplacementlist2((placementmaintenance.getPo_id()).substring(0,placementmaintenance.getPo_id().indexOf(",")));
			opi.setInv_no(placementmaintenance.getInv_no());
			opi.setInv_date(placementmaintenance.getInv_date());
			opi.setPo_delivery_date(placementmaintenance.getPo_delivery_date());
			opi.setPo_rate_inr(placementmaintenance.getPo_rate_inr());
			opi.setGrn_efforts(placementmaintenance.getGrn_efforts());
			opi.setGrn_amt(placementmaintenance.getGrn_amt());
			opi.setInv_igst(placementmaintenance.getInv_igst());
			opi.setInv_tot_gst(placementmaintenance.getInv_igst());
			opi.setInv_tot_amt(placementmaintenance.getInv_tot_amt());
			opi.setInv_due_date(placementmaintenance.getInv_due_date());
			invoiceMasterRep.save(opi);
			msg = "Invoice Updated Successfully";
		}
		
		else if(formmode.equals("modify")) {
			System.out.println((placementmaintenance.getPo_id()).substring(0,placementmaintenance.getPo_id().indexOf(",")));
			PlacementMaintenance up2=placementmaintenancerep.getPoM((placementmaintenance.getPo_id()).substring(0,placementmaintenance.getPo_id().indexOf(",")));
			System.out.println(up2);
			System.out.println(placementmaintenance.getPo_id());
			System.out.println(placementmaintenance.getPo_rate_inr());
			System.out.println(placementmaintenance.getUnit_loc());
			System.out.println(placementmaintenance.getPo_delivery_date());
			System.out.println(placementmaintenance.getPo_item_no());
			System.out.println(placementmaintenance.getPo_qty());
			up2.setPo_date(placementmaintenance.getPo_date());	
			up2.setUnit_loc(placementmaintenance.getUnit_loc());
			up2.setPo_rate_inr(placementmaintenance.getPo_rate_inr());
			up2.setPo_amt_inr(placementmaintenance.getPo_amt_inr());
			up2.setPo_delivery_date(placementmaintenance.getPo_delivery_date());
			up2.setPo_item_no(placementmaintenance.getPo_item_no());
			up2.setPo_qty(placementmaintenance.getPo_qty());
			placementmaintenancerep.save(up2);
			
			System.out.println(up2);
			InvoiceMaster op2 = invoiceMasterRep.getplacementlist2((placementmaintenance.getPo_id()).substring(0,placementmaintenance.getPo_id().indexOf(",")));
			op2.setPo_delivery_date(placementmaintenance.getPo_delivery_date());
			op2.setLocation(placementmaintenance.getUnit_loc());
			op2.setPo_rate_inr(placementmaintenance.getPo_rate_inr());
			invoiceMasterRep.save(op2);
			System.out.println(op2);
			msg = "Purchase Order Modified Successfully";
		}
		return msg;
	}
	
	
	/*
	 * public PlacementMaintenance store(MultipartFile file) throws IOException {
	 * String fileName = file.getOriginalFilename(); PlacementMaintenance
	 * placementmaintenance = new
	 * PlacementMaintenance(UUID.randomUUID().toString(),fileName,
	 * file.getContentType(), file.getBytes()); return
	 * placementmaintenancerep.save(placementmaintenance); }
	 */
	
	public PlacementMaintenance getFileById(String id)
	{
		Optional <PlacementMaintenance> fileOptional=placementmaintenancerep.findById(id);
		
		if(fileOptional.isPresent()) {
			return fileOptional.get();
		}
		return null;
	}
	
	public List<PlacementMaintenance> getFileList()
	{
		return placementmaintenancerep.findAll();
		
	}

	public String upload(PlacementMaintenance placementmaintenance, String formmode) {

		String msg = "";
		try {
			
			FileInputStream file = new FileInputStream(new File("C:\\Users\\user\\Documents\\PO No Excel.xlsx"));
			
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			PlacementMaintenance up = placementmaintenance;
			
			up.setUpload_flg("Y");
			placementmaintenancerep.save(up);
			msg = "PlacementMaintenance Upload Successfully";
		} catch (Exception e) {
			msg = "Error Occured. Please contact Administrator";
			e.printStackTrace();
		}
		
		return msg;
	}

	public void save(MultipartFile file) {
		// TODO Auto-generated method stub
		
	}

	
	public File getFile1(String filetype) throws FileNotFoundException, JRException, SQLException {

		
		
		String path = env.getProperty("output.exportpath");
		System.out.println(path);
		
		String fileName = "";
		String zipFileName = "";
		File outputFile;
		

		logger.info("Getting Output file : Third_PARTY");

		fileName = "SPF_ALL";

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			

			if (filetype.equals("pdf")) {
				System.out.println("inner pdf");
					jasperFile = this.getClass()
							.getResourceAsStream("/static/jasper/SPF2.jrxml");
				}else {
				
					jasperFile = this.getClass()
							.getResourceAsStream("/static/jasper/SPF2.jrxml");
				
			}

			
			JasperReport jr = JasperCompileManager.compileReport(jasperFile);
			
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			//logger.info("Assigning Parameters for Jasper");
			
			
			//map.put("SRNO", "AA");
			
			if (filetype.equals("pdf")) {
				fileName = fileName + ".pdf";
				path = path+fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JasperExportManager.exportReportToPdfFile(jp, path);
				
			}else {

				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);
		return outputFile;
	}
	
	
public File getFileesi(String filetype) throws FileNotFoundException, JRException, SQLException {

		
		
		String path = env.getProperty("output.exportpath");
		System.out.println(path);
		
		String fileName = "";
		String zipFileName = "";
		File outputFile;
		

		logger.info("Getting Output file : Third_PARTY");

		fileName = "ESI_ALL";

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			

			if (filetype.equals("pdf")) {
				System.out.println("inner pdf");
					jasperFile = this.getClass()
							.getResourceAsStream("/static/jasper/ESI_1.jrxml");
				}else {
				
					jasperFile = this.getClass()
							.getResourceAsStream("/static/jasper/ESI_1.jrxml");
				
			}

			
			JasperReport jr = JasperCompileManager.compileReport(jasperFile);
			
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			//logger.info("Assigning Parameters for Jasper");
			
			
			//map.put("SRNO", "AA");
			
			if (filetype.equals("pdf")) {
				fileName = fileName + ".pdf";
				path = path+fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JasperExportManager.exportReportToPdfFile(jp, path);
				
			}else {

				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);
		return outputFile;
	}
	
public File getFile23(String fileType, String mon) throws FileNotFoundException, JRException, SQLException {
    String salaryMonth = mon;
    String path = env.getProperty("output.exportpath");
    String fileName = "SPF" + mon;
    String zipFileName = fileName + ".zip";
    File outputFile;

    logger.info("Getting Output file: Month");

    try {
        File jasperFile;
        File subrep1 = null;
        //InputStream subrep1 = getClass().getResourceAsStream("/static/Ex_Emp.jrxml");

        if (fileType.equals("pdf")) {
            jasperFile = ResourceUtils.getFile("classpath:static/jasper/Origin.jrxml");
            subrep1 = ResourceUtils.getFile("classpath:static/jasper/Ex_Emp.jrxml");
        } else {
            jasperFile = ResourceUtils.getFile("classpath:static/jasper/Origin.jrxml");
            subrep1 = ResourceUtils.getFile("classpath:static/jasper/Ex_Emp.jrxml");
        }

       // JasperReport jr = JasperCompileManager.compileReport(jasperFile);
        JasperReport jr = (JasperReport) JRLoader.loadObject(jasperFile);
        HashMap<String, Object> map = new HashMap<>();
        
        map.put("salary_month", salaryMonth);
        map.put("SubRep", subrep1);
        System.out.println(map);
        if (fileType.equals("pdf")) {
            fileName = fileName + ".pdf";
            path = path + fileName;
            JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
            JasperExportManager.exportReportToPdfFile(jp, path);
        } else {
            fileName = fileName + ".xlsx";
            path += fileName;
            JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setExporterInput(new SimpleExporterInput(jp));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
            exporter.exportReport();
        }
    } catch (Exception e) {
        e.printStackTrace();
        // Handle the exception appropriately, e.g., log the error or throw a custom exception.
        // You can add specific error-handling code here.
    }

    outputFile = new File(path);
    return outputFile;
}

public File getFile2(String fileType, String mon) throws FileNotFoundException, JRException, SQLException {

    String salaryMonth = mon;
    String year = mon.substring(0, 4);
    String month = mon.substring(4, 6);

    System.out.println(year + "--------------" + month);

    String yearL;
    switch (month) {
        case "01": yearL = "Jan"; break;
        case "02": yearL = "Feb"; break;
        case "03": yearL = "Mar"; break;
        case "04": yearL = "Apr"; break;
        case "05": yearL = "May"; break;
        case "06": yearL = "Jun"; break;
        case "07": yearL = "Jul"; break;
        case "08": yearL = "Aug"; break;
        case "09": yearL = "Sep"; break;
        case "10": yearL = "Oct"; break;
        case "11": yearL = "Nov"; break;
        case "12": yearL = "Dec"; break;
        default: yearL = "";
    }

    String path = env.getProperty("output.exportpath");
    System.out.println(path);

    String fileName = "BORNFIRE-SPf-" + year + "-" + yearL;
    String zipFileName = fileName + ".zip";
    File outputFile;

    logger.info("Getting Output file : Month");

    try {
        InputStream jasperFile;

        if (fileType.equals("pdf")) {
            System.out.println("inner pdf");
            jasperFile = this.getClass().getResourceAsStream("/static/jasper/Origin.jrxml");
            fileName = fileName + ".pdf";
            path = Paths.get(path, fileName).toString();
        } else {
            jasperFile = this.getClass().getResourceAsStream("/static/jasper/Origin.jrxml");
            fileName = fileName + ".xlsx";
            path = Paths.get(path, fileName).toString();
        }

        JasperReport jr = JasperCompileManager.compileReport(jasperFile);
        HashMap<String, Object> map = new HashMap<>();
        map.put("salary_month", salaryMonth);
        System.out.println(map);

        if (fileType.equals("pdf")) {
            JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
            JasperExportManager.exportReportToPdfFile(jp, path);
        } else {
        	 SimpleXlsxReportConfiguration reportConfig = new SimpleXlsxReportConfiguration();
             reportConfig.setSheetNames(new String[]{fileName});
           
        	JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setExporterInput(new SimpleExporterInput(jp));
            exporter.setConfiguration(reportConfig);
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
            exporter.exportReport();
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    outputFile = new File(path);
    return outputFile;
}

	
 /*public File getFileESI2(String filetype,String Mon) throws FileNotFoundException, JRException, SQLException {

		
		String Salary_month=Mon;
		String path = env.getProperty("output.exportpath");
		System.out.println(path);
		
		String fileName = "";
		String zipFileName = "";
		File outputFile;
		

		logger.info("Getting Output file : Month");

		fileName = "ESI"+Mon;

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			

			if (filetype.equals("pdf")) {
				System.out.println("inner pdf");
					jasperFile = this.getClass()
							.getResourceAsStream("/static/jasper/lastesi.jrxml");
				}else {
				
					jasperFile = this.getClass()
							.getResourceAsStream("/static/jasper/lastesi.jrxml");
				
			}

			
			JasperReport jr = JasperCompileManager.compileReport(jasperFile);
			
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			//logger.info("Assigning Parameters for Jasper");
			
			
			map.put("salary_month", Salary_month);
			System.out.println(map);
			if (filetype.equals("pdf")) {
				fileName = fileName + ".pdf";
				path = path+fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JasperExportManager.exportReportToPdfFile(jp, path);
				
			}else {

				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);
		return outputFile;
	}

*/


public File getFileESI2(String filetype, String Mon) throws FileNotFoundException, JRException, SQLException,IllegalArgumentException {
		String Salary_month=Mon;
		String Year=Mon.substring(0,4);
		String Month=Mon.substring(4,6);
		System.out.println(Year+"--------------"+Month);
		String YearL=null;
		if(Month.equals("01")) {
			YearL="Jan";
		}else if(Month.equals("02")) {
			YearL="Feb";
		}else if(Month.equals("02")) {
			YearL="Feb";
		}else if(Month.equals("03")) {
			YearL="Mar";
		}else if(Month.equals("04")) {
			YearL="Apr";
		}else if(Month.equals("05")) {
			YearL="May";
		}else if(Month.equals("06")) {
			YearL="Jun";
		}else if(Month.equals("07")) {
			YearL="Jul";
		}else if(Month.equals("08")) {
			YearL="Aug";
		}else if(Month.equals("09")) {
			YearL="Sep";
		}else if(Month.equals("10")) {
			YearL="Oct";
		}else if(Month.equals("11")) {
			YearL="Nov";
		}else if(Month.equals("12")) {
			YearL="Dec";
		}
        String path = env.getProperty("output.exportpath");
        System.out.println(path);

        String fileName = "";
        File outputFile;

        logger.info("Getting Output file : Month");

        fileName = "BORNFIRE-ESIC-" + Year + "-" + YearL;

        try {
            InputStream jasperFile;

            if (filetype.equals("pdf")) {
                System.out.println("inner pdf");
                jasperFile = this.getClass().getResourceAsStream("/static/jasper/lastesi.jrxml");
            } else {
                jasperFile = this.getClass().getResourceAsStream("/static/jasper/lastesi.jrxml");
            }

            if (jasperFile == null) {
                throw new FileNotFoundException("Jasper file not found");
            }

            JasperReport jr = JasperCompileManager.compileReport(jasperFile);

            HashMap<String, Object> map = new HashMap<>();
            map.put("salary_month", Salary_month);
            System.out.println(map);

            if (filetype.equals("pdf")) {
                fileName = fileName + ".pdf";
                path = path + fileName;
                JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
                JasperExportManager.exportReportToPdfFile(jp, path);
            } else {
                fileName = fileName + ".xlsx";
                path += fileName;
                SimpleXlsxReportConfiguration reportConfig = new SimpleXlsxReportConfiguration();
                reportConfig.setSheetNames(new String[]{fileName});
                JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
                JRXlsxExporter exporter = new JRXlsxExporter();
                exporter.setExporterInput(new SimpleExporterInput(jp));
                exporter.setConfiguration(reportConfig);
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
                exporter.exportReport();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e; // Rethrow the exception for higher-level handling
        } catch (Exception e) {
            e.printStackTrace();
            throw new JRException("Error generating Jasper report", e);
        }

        outputFile = new File(path);
        return outputFile;
    
}


	

public File getSalaryFile(String filetype,String Mon) throws FileNotFoundException, JRException, SQLException {

	
	String Salary_month=Mon;
	String path = env.getProperty("output.exportpath");
	System.out.println(path);
	
	String fileName = "";
	String zipFileName = "";
	File outputFile;
	

	logger.info("Getting Output file : Month");

	fileName = "Salary_"+Mon;

	zipFileName = fileName + ".zip";

	try {
		InputStream jasperFile;

		

		if (filetype.equals("pdf")) {
			System.out.println("inner pdf");
				jasperFile = this.getClass()
						.getResourceAsStream("/static/jasper/SalaryUpload.jrxml");
			}else {
			
				jasperFile = this.getClass()
						.getResourceAsStream("/static/jasper/SalaryUpload.jrxml");
			
		}

		
		JasperReport jr = JasperCompileManager.compileReport(jasperFile);
		
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		//logger.info("Assigning Parameters for Jasper");
		
		
		map.put("salary_month", Salary_month);
		System.out.println(map);
		if (filetype.equals("pdf")) {
			fileName = fileName + ".pdf";
			path = path+fileName;
			JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
			JasperExportManager.exportReportToPdfFile(jp, path);
			
		}else {

			fileName = fileName + ".xlsx";
			path += fileName;
			JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
			JRXlsxExporter exporter = new JRXlsxExporter();
			exporter.setExporterInput(new SimpleExporterInput(jp));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
			exporter.exportReport();
		}

		
	} catch (Exception e) {
		e.printStackTrace();
	}

	outputFile = new File(path);
	return outputFile;
}


public File getGstFile(String filetype,String Month,String Year,String Raised) throws FileNotFoundException, JRException, SQLException {

	
	String month=Month;
	String year=Year;
	String raised=Raised;
	String path = env.getProperty("output.exportpath");
	System.out.println(path);
	
	String fileName = "";
	String zipFileName = "";
	File outputFile;
	

	logger.info("Getting Output file : Month");

	fileName = "GST-"+raised+"("+month+"-"+year+")";

	zipFileName = fileName + ".zip";

	try {
		
		if(raised.equals("By Us - India")) {
		InputStream jasperFile;

		

		if (filetype.equals("pdf")) {
			System.out.println("inner pdf");
				jasperFile = this.getClass()
						.getResourceAsStream("/static/jasper/ByUsIndia.jrxml");
			}else {
			
				jasperFile = this.getClass()
						.getResourceAsStream("/static/jasper/ByUsIndia.jrxml");
			
		}

		
		JasperReport jr = JasperCompileManager.compileReport(jasperFile);
		
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		//logger.info("Assigning Parameters for Jasper");
		
		
		map.put("month", month);
		map.put("year", year);
		System.out.println(map);
		if (filetype.equals("pdf")) {
			fileName = fileName + ".pdf";
			path = path+fileName;
			JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
			JasperExportManager.exportReportToPdfFile(jp, path);
			
		}else {

			fileName = fileName + ".xlsx";
			path += fileName;
			JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
			JRXlsxExporter exporter = new JRXlsxExporter();
			exporter.setExporterInput(new SimpleExporterInput(jp));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
			exporter.exportReport();
		}

		}
		
		else if(raised.equals("On Us - India")) {
			InputStream jasperFile;

			

			if (filetype.equals("pdf")) {
				System.out.println("inner pdf");
					jasperFile = this.getClass()
							.getResourceAsStream("/static/jasper/OnUsIndia.jrxml");
				}else {
				
					jasperFile = this.getClass()
							.getResourceAsStream("/static/jasper/OnUsIndia.jrxml");
				
			}

			
			JasperReport jr = JasperCompileManager.compileReport(jasperFile);
			
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			//logger.info("Assigning Parameters for Jasper");
			
			
			map.put("month", month);
			map.put("year", year);
			System.out.println(map);
			if (filetype.equals("pdf")) {
				fileName = fileName + ".pdf";
				path = path+fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JasperExportManager.exportReportToPdfFile(jp, path);
				
			}else {

				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();
			}

		}
	} catch (Exception e) {
		e.printStackTrace();
	}

	outputFile = new File(path);
	return outputFile;
}





public File getECLFile(String filename, String filetype, String Month, String Year) {
    String path = env.getProperty("output.exportpath");
    String fileName = "GST" + filetype;
    File outputFile = null;

    try {
        // Load JasperReport files
        try {
            InputStream[] jasperFiles = {
                    this.getClass().getResourceAsStream("/static/jasper/ByUsIndia.jrxml"),
                    this.getClass().getResourceAsStream("/static/jasper/overseas.jrxml"),
                    this.getClass().getResourceAsStream("/static/jasper/OnUsIndia.jrxml"),

            };

            // Compile JasperReports
            JasperReport[] jasperReports = new JasperReport[jasperFiles.length];
            for (int i = 0; i < jasperFiles.length; i++) {
                jasperReports[i] = JasperCompileManager.compileReport(jasperFiles[i]);
            }

            // System.out.println(Month + Year); // Commented out, as it seems unnecessary

            HashMap<String, Object> map = new HashMap<>();
            map.put("month", Month);
            map.put("year", Year);

            // Fill JasperPrint for each report
            JasperPrint[] jasperPrints = new JasperPrint[jasperReports.length];
            for (int i = 0; i < jasperReports.length; i++) {
                try (Connection connection = srcdataSource.getConnection()) {
                    jasperPrints[i] = JasperFillManager.fillReport(jasperReports[i], map, connection);
                }
            }

            // Combine JasperPrints
            JasperPrint combinedJasperPrint = new JasperPrint();
            for (JasperPrint jasperPrint : jasperPrints) {
                List<JRPrintPage> pages = jasperPrint.getPages();
                for (JRPrintPage page : pages) {
                    combinedJasperPrint.addPage(page);
                }
            }

            // Export to XLSX
            fileName = fileName + ".xlsx";
            path += File.separator + fileName; // Use File.separator for path construction

            SimpleXlsxReportConfiguration reportConfig = new SimpleXlsxReportConfiguration();
            reportConfig.setSheetNames(new String[]{filename});

            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setExporterInput(new SimpleExporterInput(combinedJasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
            exporter.setConfiguration(reportConfig); // Set the configuration
            exporter.exportReport();
        } catch (Exception e) {
            logger.error("Error generating ECL file", e);
        }

        // Check if the file exists before returning
        if (new File(path).exists()) {
            outputFile = new File(path);
        }
    } catch (Exception e) {
        logger.error("Error generating ECL file", e);
    }

    return outputFile;
}


public File gettdsexcel(String filename, String filetype, String moths, String years) {
    String path = this.env.getProperty("output.exportpath");
    String fileName = "TDS" + filetype;
    File outputFile = null;

    try {
       try {
          InputStream[] jasperFiles = new InputStream[]{this.getClass().getResourceAsStream("/static/jasper/tdss.jrxml")};
          JasperReport[] jasperReports = new JasperReport[jasperFiles.length];

          for(int i = 0; i < jasperFiles.length; ++i) {
             jasperReports[i] = JasperCompileManager.compileReport(jasperFiles[i]);
          }

          HashMap<String, Object> map = new HashMap();
          map.put("moths", moths);
          System.out.println(moths);
          map.put("years", years);
          System.out.println(years);
          JasperPrint[] jasperPrints = new JasperPrint[jasperReports.length];

          for(int i = 0; i < jasperReports.length; ++i) {
             Connection connection = this.srcdataSource.getConnection();

             try {
                jasperPrints[i] = JasperFillManager.fillReport(jasperReports[i], map, connection);
             } catch (Throwable var21) {
                if (connection != null) {
                   try {
                      connection.close();
                   } catch (Throwable var20) {
                      var21.addSuppressed(var20);
                   }
                }

                throw var21;
             }

             if (connection != null) {
                connection.close();
             }
          }

          JasperPrint combinedJasperPrint = new JasperPrint();
          JasperPrint[] var26 = jasperPrints;
          int var14 = jasperPrints.length;

          for(int var15 = 0; var15 < var14; ++var15) {
             JasperPrint jasperPrint = var26[var15];
             List<JRPrintPage> pages = jasperPrint.getPages();
             Iterator var18 = pages.iterator();

             while(var18.hasNext()) {
                JRPrintPage page = (JRPrintPage)var18.next();
                combinedJasperPrint.addPage(page);
             }
          }

          fileName = fileName + ".xlsx";
          path = path + File.separator + fileName;
          SimpleXlsxReportConfiguration reportConfig = new SimpleXlsxReportConfiguration();
          reportConfig.setSheetNames(new String[]{filename});
          JRXlsxExporter exporter = new JRXlsxExporter();
          exporter.setExporterInput(new SimpleExporterInput(combinedJasperPrint));
          exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
          exporter.setConfiguration(reportConfig);
          exporter.exportReport();
       } catch (Exception var22) {
          logger.error("Error generating TDS file", var22);
       }

       if ((new File(path)).exists()) {
          outputFile = new File(path);
       }
    } catch (Exception var23) {
       logger.error("Error generating TDS file", var23);
    }

    return outputFile;
 }
	




public  String addparam(salary_parameter salaryParameter,String formmode) {
	
	String msg="";
	try {
		
		if(formmode.equals("add")) {

		    salary_parameter up = salaryParameter;
		    Session session = sessionFactory.getCurrentSession();
		    
		    // Check if group_name already exists
		    String groupName = salaryParameter.getGroup_name();
		    Long count = (Long) session.createQuery("SELECT COUNT(sp) FROM salary_parameter sp WHERE sp.group_name = :groupName")
		                               .setParameter("groupName", groupName)
		                               .uniqueResult();

		    if (count > 0) {
		        msg = "Group name already exists. Please select different Group.";
		    } else {
		      

                BigInteger billNumberDecimal = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR PARA_SRL_NO AS SRL_NO")
                        .getSingleResult();
                BigDecimal srl_no = new BigDecimal(billNumberDecimal);
                
		        up.setSrl_no(srl_no);
		        up.setStatus("Pending");
		        up.setGroup_name(groupName);
		        
		        if (up.getNational().equals("yes")) {
	                up.setCountry(up.getCountry());
	                up.setNational(up.getNational());
	                up.setDes_tax_deduction(BigDecimal.valueOf(0));
	                up.setDes_fjt(BigDecimal.valueOf(0));
	                up.setCountry("");
                }if (up.getNational().equals("no") ) {
                    up.setCountry(up.getCountry());
                    up.setNational(up.getNational());
                }

		        salary_parameter_Rep.save(up);
		        msg = "Parameters added successfully.";
		    }
		}else if (formmode.equals("edit")) {
            Optional<salary_parameter> existingRecordOptional = salary_parameter_Rep.findById(salaryParameter.getSrl_no());
            
            if (existingRecordOptional.isPresent()) {
                salary_parameter existingRecord = existingRecordOptional.get();
                
                if (salaryParameter.getGroup_name() != null) {
                    existingRecord.setGroup_name(salaryParameter.getGroup_name());
                }
                if (salaryParameter.getDes_basic() != null) {
                    existingRecord.setDes_basic(salaryParameter.getDes_basic());
                }
                if (salaryParameter.getDes_hra() != null) {
                    existingRecord.setDes_hra(salaryParameter.getDes_hra());
                }
                if (salaryParameter.getDes_medical() != null) {
                    existingRecord.setDes_medical(salaryParameter.getDes_medical());
                }
                if (salaryParameter.getDes_conveyance() != null) {
                    existingRecord.setDes_conveyance(salaryParameter.getDes_conveyance());
                }
                if (salaryParameter.getDes_attire() != null) {
                    existingRecord.setDes_attire(salaryParameter.getDes_attire());
                }
                if (salaryParameter.getDes_ltc() != null) {
                    existingRecord.setDes_ltc(salaryParameter.getDes_ltc());
                }
                if (salaryParameter.getDes_employer_pf() != null) {
                    existingRecord.setDes_employer_pf(salaryParameter.getDes_employer_pf());
                }
                if (salaryParameter.getDes_special_allowance() != null) {
                    existingRecord.setDes_special_allowance(salaryParameter.getDes_special_allowance());
                }
                if (salaryParameter.getDes_professional_tax() != null) {
                    existingRecord.setDes_professional_tax(salaryParameter.getDes_professional_tax());
                }
                if (salaryParameter.getDes_tax_deduction() != null) {
                    existingRecord.setDes_tax_deduction(salaryParameter.getDes_tax_deduction());
                }
                if (salaryParameter.getCtc_cost_to_company() != null) {
                    existingRecord.setCtc_cost_to_company(salaryParameter.getCtc_cost_to_company());
                }
                if (salaryParameter.getCtc_basic_salary() != null) {
                    existingRecord.setCtc_basic_salary(salaryParameter.getCtc_basic_salary());
                }
                if (salaryParameter.getCtc_house_rent_allowance() != null) {
                    existingRecord.setCtc_house_rent_allowance(salaryParameter.getCtc_house_rent_allowance());
                }
                if (salaryParameter.getCtc_special_allowance() != null) {
                    existingRecord.setCtc_special_allowance(salaryParameter.getCtc_special_allowance());
                }
                if (salaryParameter.getCtc_medical_reimbursement() != null) {
                    existingRecord.setCtc_medical_reimbursement(salaryParameter.getCtc_medical_reimbursement());
                }
                if (salaryParameter.getCtc_conveyance_allowance() != null) {
                    existingRecord.setCtc_conveyance_allowance(salaryParameter.getCtc_conveyance_allowance());
                }
                if (salaryParameter.getCtc_lunch_allownace() != null) {
                    existingRecord.setCtc_lunch_allownace(salaryParameter.getCtc_lunch_allownace());
                }
                if (salaryParameter.getCtc_education_allowance() != null) {
                    existingRecord.setCtc_education_allowance(salaryParameter.getCtc_education_allowance());
                }
                if (salaryParameter.getCtc_business_attire_reimbursement() != null) {
                    existingRecord.setCtc_business_attire_reimbursement(salaryParameter.getCtc_business_attire_reimbursement());
                }
                if (salaryParameter.getCtc_car_maintenance_allowance() != null) {
                    existingRecord.setCtc_car_maintenance_allowance(salaryParameter.getCtc_car_maintenance_allowance());
                }
                if (salaryParameter.getCtc_leave_travel_allowance() != null) {
                    existingRecord.setCtc_leave_travel_allowance(salaryParameter.getCtc_leave_travel_allowance());
                }
                if (salaryParameter.getCtc_pf_employer_contribution() != null) {
                    existingRecord.setCtc_pf_employer_contribution(salaryParameter.getCtc_pf_employer_contribution());
                }
                if (salaryParameter.getCtc_annual_loyalty_bonus() != null) {
                    existingRecord.setCtc_annual_loyalty_bonus(salaryParameter.getCtc_annual_loyalty_bonus());
                }
                if (salaryParameter.getCtc_gross_salary() != null) {
                    existingRecord.setCtc_gross_salary(salaryParameter.getCtc_gross_salary());
                }
                if (salaryParameter.getCtc_professional_deduction() != null) {
                    existingRecord.setCtc_professional_deduction(salaryParameter.getCtc_professional_deduction());
                }
                if (salaryParameter.getCtc_standard_deduction() != null) {
                    existingRecord.setCtc_standard_deduction(salaryParameter.getCtc_standard_deduction());
                }
                if (salaryParameter.getCtc_tax_deduction() != null) {
                    existingRecord.setCtc_tax_deduction(salaryParameter.getCtc_tax_deduction());
                }
                if (salaryParameter.getCtc_net_salary() != null) {
                    existingRecord.setCtc_net_salary(salaryParameter.getCtc_net_salary());
                }
                if (salaryParameter.getMon_cost_to_company() != null) {
                    existingRecord.setMon_cost_to_company(salaryParameter.getMon_cost_to_company());
                }
                if (salaryParameter.getMon_basic_salary() != null) {
                    existingRecord.setMon_basic_salary(salaryParameter.getMon_basic_salary());
                }
                if (salaryParameter.getMon_house_rent_allowance() != null) {
                    existingRecord.setMon_house_rent_allowance(salaryParameter.getMon_house_rent_allowance());
                }
                if (salaryParameter.getMon_special_allowance() != null) {
                    existingRecord.setMon_special_allowance(salaryParameter.getMon_special_allowance());
                }
                if (salaryParameter.getMon_medical_reimbursement() != null) {
                    existingRecord.setMon_medical_reimbursement(salaryParameter.getMon_medical_reimbursement());
                }
                if (salaryParameter.getMon_conveyance_allowance() != null) {
                    existingRecord.setMon_conveyance_allowance(salaryParameter.getMon_conveyance_allowance());
                }
                if (salaryParameter.getMon_lunch_allownace() != null) {
                    existingRecord.setMon_lunch_allownace(salaryParameter.getMon_lunch_allownace());
                }
                if (salaryParameter.getMon_education_allowance() != null) {
                    existingRecord.setMon_education_allowance(salaryParameter.getMon_education_allowance());
                }
                if (salaryParameter.getMon_business_attire_reimbursement() != null) {
                    existingRecord.setMon_business_attire_reimbursement(salaryParameter.getMon_business_attire_reimbursement());
                }
                if (salaryParameter.getMon_car_maintenance_allowance() != null) {
                    existingRecord.setMon_car_maintenance_allowance(salaryParameter.getMon_car_maintenance_allowance());
                }
                if (salaryParameter.getMon_leave_travel_allowance() != null) {
                    existingRecord.setMon_leave_travel_allowance(salaryParameter.getMon_leave_travel_allowance());
                }
                if (salaryParameter.getMon_pf_employer_contribution() != null) {
                    existingRecord.setMon_pf_employer_contribution(salaryParameter.getMon_pf_employer_contribution());
                }
                if (salaryParameter.getMon_annual_loyalty_bonus() != null) {
                    existingRecord.setMon_annual_loyalty_bonus(salaryParameter.getMon_annual_loyalty_bonus());
                }
                if (salaryParameter.getMon_gross_salary() != null) {
                    existingRecord.setMon_gross_salary(salaryParameter.getMon_gross_salary());
                }
                if (salaryParameter.getMon_professional_deduction() != null) {
                    existingRecord.setMon_professional_deduction(salaryParameter.getMon_professional_deduction());
                }
                if (salaryParameter.getMon_standard_deduction() != null) {
                    existingRecord.setMon_standard_deduction(salaryParameter.getMon_standard_deduction());
                }
                if (salaryParameter.getMon_tax_deduction() != null) {
                    existingRecord.setMon_tax_deduction(salaryParameter.getMon_tax_deduction());
                }
                if (salaryParameter.getMon_net_salary() != null) {
                    existingRecord.setMon_net_salary(salaryParameter.getMon_net_salary());
                }
                if (salaryParameter.getYear_cost_to_company() != null) {
                    existingRecord.setYear_cost_to_company(salaryParameter.getYear_cost_to_company());
                }
                if (salaryParameter.getYear_basic_salary() != null) {
                    existingRecord.setYear_basic_salary(salaryParameter.getYear_basic_salary());
                }
                if (salaryParameter.getYear_house_rent_allowance() != null) {
                    existingRecord.setYear_house_rent_allowance(salaryParameter.getYear_house_rent_allowance());
                }
                if (salaryParameter.getYear_special_allowance() != null) {
                    existingRecord.setYear_special_allowance(salaryParameter.getYear_special_allowance());
                }
                if (salaryParameter.getYear_medical_reimbursement() != null) {
                    existingRecord.setYear_medical_reimbursement(salaryParameter.getYear_medical_reimbursement());
                }
                if (salaryParameter.getYear_conveyance_allowance() != null) {
                    existingRecord.setYear_conveyance_allowance(salaryParameter.getYear_conveyance_allowance());
                }
                if (salaryParameter.getYear_lunch_allownace() != null) {
                    existingRecord.setYear_lunch_allownace(salaryParameter.getYear_lunch_allownace());
                }
                if (salaryParameter.getYear_education_allowance() != null) {
                    existingRecord.setYear_education_allowance(salaryParameter.getYear_education_allowance());
                }
                if (salaryParameter.getYear_business_attire_reimbursement() != null) {
                    existingRecord.setYear_business_attire_reimbursement(salaryParameter.getYear_business_attire_reimbursement());
                }
                if (salaryParameter.getYear_car_maintenance_allowance() != null) {
                    existingRecord.setYear_car_maintenance_allowance(salaryParameter.getYear_car_maintenance_allowance());
                }
                if (salaryParameter.getYear_leave_travel_allowance() != null) {
                    existingRecord.setYear_leave_travel_allowance(salaryParameter.getYear_leave_travel_allowance());
                }
                if (salaryParameter.getYear_pf_employer_contribution() != null) {
                    existingRecord.setYear_pf_employer_contribution(salaryParameter.getYear_pf_employer_contribution());
                }
                if (salaryParameter.getYear_annual_loyalty_bonus() != null) {
                    existingRecord.setYear_annual_loyalty_bonus(salaryParameter.getYear_annual_loyalty_bonus());
                }
                if (salaryParameter.getYear_gross_salary() != null) {
                    existingRecord.setYear_gross_salary(salaryParameter.getYear_gross_salary());
                }
                if (salaryParameter.getYear_professional_deduction() != null) {
                    existingRecord.setYear_professional_deduction(salaryParameter.getYear_professional_deduction());
                }
                if (salaryParameter.getYear_standard_deduction() != null) {
                    existingRecord.setYear_standard_deduction(salaryParameter.getYear_standard_deduction());
                }
                if (salaryParameter.getYear_tax_deduction() != null) {
                    existingRecord.setYear_tax_deduction(salaryParameter.getYear_tax_deduction());
                }
                if (salaryParameter.getYear_net_salary() != null) {
                    existingRecord.setYear_net_salary(salaryParameter.getYear_net_salary());
                }
                if (salaryParameter.getThs_cost_to_company() != null) {
                    existingRecord.setThs_cost_to_company(salaryParameter.getThs_cost_to_company());
                }
                if (salaryParameter.getThs_basic_salary() != null) {
                    existingRecord.setThs_basic_salary(salaryParameter.getThs_basic_salary());
                }
                if (salaryParameter.getThs_house_rent_allowance() != null) {
                    existingRecord.setThs_house_rent_allowance(salaryParameter.getThs_house_rent_allowance());
                }
                if (salaryParameter.getThs_special_allowance() != null) {
                    existingRecord.setThs_special_allowance(salaryParameter.getThs_special_allowance());
                }
                if (salaryParameter.getThs_medical_reimbursement() != null) {
                    existingRecord.setThs_medical_reimbursement(salaryParameter.getThs_medical_reimbursement());
                }
                if (salaryParameter.getThs_conveyance_allowance() != null) {
                    existingRecord.setThs_conveyance_allowance(salaryParameter.getThs_conveyance_allowance());
                }
                if (salaryParameter.getThs_lunch_allownace() != null) {
                    existingRecord.setThs_lunch_allownace(salaryParameter.getThs_lunch_allownace());
                }
                if (salaryParameter.getThs_education_allowance() != null) {
                    existingRecord.setThs_education_allowance(salaryParameter.getThs_education_allowance());
                }
                if (salaryParameter.getThs_business_attire_reimbursement() != null) {
                    existingRecord.setThs_business_attire_reimbursement(salaryParameter.getThs_business_attire_reimbursement());
                }
                if (salaryParameter.getThs_car_maintenance_allowance() != null) {
                    existingRecord.setThs_car_maintenance_allowance(salaryParameter.getThs_car_maintenance_allowance());
                }
                if (salaryParameter.getThs_leave_travel_allowance() != null) {
                    existingRecord.setThs_leave_travel_allowance(salaryParameter.getThs_leave_travel_allowance());
                }
                if (salaryParameter.getThs_pf_employer_contribution() != null) {
                    existingRecord.setThs_pf_employer_contribution(salaryParameter.getThs_pf_employer_contribution());
                }
                if (salaryParameter.getThs_annual_loyalty_bonus() != null) {
                    existingRecord.setThs_annual_loyalty_bonus(salaryParameter.getThs_annual_loyalty_bonus());
                }
                if (salaryParameter.getThs_gross_salary() != null) {
                    existingRecord.setThs_gross_salary(salaryParameter.getThs_gross_salary());
                }
                if (salaryParameter.getThs_professional_deduction() != null) {
                    existingRecord.setThs_professional_deduction(salaryParameter.getThs_professional_deduction());
                }
                if (salaryParameter.getThs_standard_deduction() != null) {
                    existingRecord.setThs_standard_deduction(salaryParameter.getThs_standard_deduction());
                }
                if (salaryParameter.getThs_tax_deduction() != null) {
                    existingRecord.setThs_tax_deduction(salaryParameter.getThs_tax_deduction());
                }
                if (salaryParameter.getThs_net_salary() != null) {
                    existingRecord.setThs_net_salary(salaryParameter.getThs_net_salary());
                }
                if (salaryParameter.getDes_fjt() != null) {
                    existingRecord.setDes_fjt(salaryParameter.getDes_fjt());
                }
                
                
                existingRecord.setStatus("Pending");
                if (salaryParameter.getNational().equals("yes")) {
                	existingRecord.setCountry(salaryParameter.getCountry());
                	existingRecord.setNational(salaryParameter.getNational());
                	existingRecord.setDes_tax_deduction(BigDecimal.valueOf(0));
                	existingRecord.setDes_fjt(BigDecimal.valueOf(0));
                	existingRecord.setCountry("");
                }if (salaryParameter.getNational().equals("no") ) {
                	existingRecord.setCountry(salaryParameter.getCountry());
                	existingRecord.setNational(salaryParameter.getNational());
                }
                
                salary_parameter_Rep.save(existingRecord);
                msg = "Parameters updated successfully.";
            } else {
                msg = "Record not found for update.";
            }
        }else if(formmode.equals("verify")) {
        	 Optional<salary_parameter> existingRecordOptional = salary_parameter_Rep.findById(salaryParameter.getSrl_no());
        	 salary_parameter existingRecord = existingRecordOptional.get();
             existingRecord.setStatus("Verified");
             salary_parameter_Rep.save(existingRecord);
             msg = "Parameters verified successfully...";
         }else if(formmode.equals("delete")) {
        	 Optional<salary_parameter> existingRecordOptional = salary_parameter_Rep.findById(salaryParameter.getSrl_no());

             if (existingRecordOptional.isPresent()) {
                 salary_parameter existingRecord = existingRecordOptional.get();
                 salary_parameter_Rep.delete(existingRecord);
                 msg = "Parameters deleted successfully.";
             } else {
                 msg = "Record not found for deletion.";
             }
         } else {
             msg = "error occured...";
         }
        return msg;
    } catch (Exception e) {
        e.printStackTrace();
        return "Parameters update unsuccessful.";
    }
	
}





public  ResponseEntity<String> add_Tax_param(List<Taxation_parameters> taxationParametersList,String formmode) {
	
	ResponseEntity<String>  msg = null;
	try {
		if(formmode.equals("add")) {
        	Session session = sessionFactory.getCurrentSession();
        	 String groupName = taxationParametersList.get(0).getGroup_name();
 		    Long count = (Long) session.createQuery("SELECT COUNT(sp) FROM Taxation_parameters sp WHERE sp.group_name = :groupName")
 		                               .setParameter("groupName", groupName)
 		                               .uniqueResult();

 		    if (count > 0) {
 		        msg =  ResponseEntity.ok("Group name already exists. Please select different Group.");
 		    } else {
 		    	for (Taxation_parameters param : taxationParametersList) {
                    // Fetch the next sequence value
                    BigInteger billNumberDecimal = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR TAX_SRL_NO AS SRL_NO")
                            .getSingleResult();
                    BigDecimal srl_no = new BigDecimal(billNumberDecimal);
                    param.setSrl_no(srl_no);
                    param.setStatus("Pending");

                    // Nationality handling
                    if ("yes".equalsIgnoreCase(param.getNational())) {
                        param.setNational_country(""); // Clear country if national is 'yes'
                    } else if ("no".equalsIgnoreCase(param.getNational())) {
                        param.setNational_country(param.getNational_country()); // Set country if national is 'no'
                    }

                    // Handle the field 'fjd'
                    param.setFjd(param.getFjd());

                    // Save the parameter
                    Taxation_parameter_rep.save(param);
            }
            msg= ResponseEntity.ok("Parameters added successfully.");
		}
		}else if ("edit".equalsIgnoreCase(formmode)) {
            for (Taxation_parameters param : taxationParametersList) {
                if (param.getSrl_no() == null) {
                    // Add new record if srl_no is null
                    Session session = sessionFactory.getCurrentSession();
                    
                    
                    BigInteger billNumberDecimal = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR TAX_SRL_NO AS SRL_NO")
                            .getSingleResult();
                    BigDecimal srl_no = new BigDecimal(billNumberDecimal);
                    
                    param.setSrl_no(srl_no);
                    param.setStatus("Pending");
                    Taxation_parameter_rep.save(param);
                } else {
                    // Update existing record if srl_no is not null
                    Optional<Taxation_parameters> existingRecordOptional = Taxation_parameter_rep.findById(param.getSrl_no());

                    if (existingRecordOptional.isPresent()) {
                        Taxation_parameters existingRecord = existingRecordOptional.get();
                        existingRecord.setTax_code(param.getTax_code());
                        existingRecord.setMin_amount(param.getMin_amount());
                        existingRecord.setMax_amount(param.getMax_amount());
                        existingRecord.setTax_value(param.getTax_value());
                        existingRecord.setFjd(param.getFjd());
                        existingRecord.setStatus("Pending");
                        existingRecord.setNational(param.getNational());
                        
                        if (param.getNational().equals("yes")) {
                           // existingRecord.setSpecify_country(param.getSpecify_country());
                            existingRecord.setNational_country("");
                            System.out.println("insideeeee");
                        }if (param.getNational().equals("no") ) {
                            existingRecord.setNational_country(param.getNational_country());
                           // existingRecord.setSpecify_country("");
                        }
                        
                        Taxation_parameter_rep.save(existingRecord);
                    }
                }
            }
			   msg= ResponseEntity.ok("Parameters updated successfully.");
		}
		return msg;
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.ok("Parameters update unsuccessful.");
    }
	
}


public String deleteRow(BigDecimal srl_no ) {
  String msg = null;
	try {
   	 Optional<Taxation_parameters> existingRecordOptional = Taxation_parameter_rep.findById(srl_no);
		if (existingRecordOptional.isPresent()) {
			Taxation_parameters existingRecord = existingRecordOptional.get();
            Taxation_parameter_rep.delete(existingRecord);
            msg="Parameters deleted successfully.";
        } else {
        	msg="Record not found for deletion.";
        }
	return msg;

} catch (Exception e) {
    e.printStackTrace();
    return msg="Parameters deleted unsuccessful.";
}
}

public String ver_rows(String group_name, String formmode) {
    String msg = null;
    try {
        // Retrieve all records with the specified group name
		List<Taxation_parameters> records = Taxation_parameter_rep.getview(group_name);
        System.out.println("The recrd is:"+records.size());
        if (!records.isEmpty()) {
            // Update the status for each record
            for (Taxation_parameters record : records) {
                record.setStatus("Verified");
            }
            // Save all updated records
            Taxation_parameter_rep.saveAll(records);
            msg = "Parameters verified successfully...";
        } else {
            msg = "No records found for the specified group name.";
        }
    } catch (Exception e) {
        e.printStackTrace();
        msg = "Parameters verification unsuccessful.";
    }
    return msg;
}


public String del_rows(String group_name, String formmode) {
    String msg = null;
    try {
        // Retrieve all records with the specified group name
		List<Taxation_parameters> records = Taxation_parameter_rep.getview(group_name);
        System.out.println("The recrd is:"+records.size());
        if (!records.isEmpty()) {
            // Update the status for each record
            for (Taxation_parameters record : records) {
                Taxation_parameter_rep.delete(record);
            }
            msg = "Parameters deleted successfully...";
        } else {
            msg = "No records found for the specified group name.";
        }
    } catch (Exception e) {
        e.printStackTrace();
        msg = "Parameters deleted unsuccessful.";
    }
    return msg;
}


public String sendapprovlstages(List<Map<String, String>> tableData) {
    Session session = sessionFactory.getCurrentSession(); // Ensure session management is configured properly

    try {
        // Delete all existing rows before adding new approval stages
        leaveTableRep.deleteAll();

        // Loop through the table data to insert rows
        for (Map<String, String> row : tableData) {
            for (Map.Entry<String, String> entry : row.entrySet()) {
                String key = entry.getKey(); // e.g., "days-1", "days-2", ...
                String input = entry.getValue(); // Get the corresponding value
                
                // Only process keys that match "days-<index>"
                if (key.startsWith("days-") && input != null && !input.isEmpty()) {
                    // Retrieve serial number
                    BigInteger billNumberDecimal = (BigInteger) session
                            .createNativeQuery("SELECT NEXT VALUE FOR APPR_SRL_NO AS SRL_NO")
                            .getSingleResult();
                    BigDecimal srl_no = new BigDecimal(billNumberDecimal);

                    // Prepare the entity
                    LeaveTable leaveTable = new LeaveTable();
                    leaveTable.setNo_row(srl_no);
                    leaveTable.setNumber_of_days(new BigDecimal(input)); // Ensure input is a valid number
                    
                    // Save the object to the database
                    leaveTableRep.save(leaveTable);
                }
            }
        }

        return "Data processed successfully.";
    } catch (Exception e) {
        e.printStackTrace();
        return "Failed to process data: " + e.getMessage();
    }
}


public String deletestages() {
	
try {
	  leaveTableRep.deleteAll();
	  return"deleted successfully...";
} catch (Exception e) {
    e.printStackTrace();
    return "Failed to process data.";
}
}

public long getno_of_stages() {
	return leaveTableRep.count();
}
	
public List<String> getno_of_days() {
	
	return leaveTableRep.getAllNumberOfDays();
}



public String evaluation(perfomance_evaluation perfomance_evaluation,String userId,String formmode) {
	Session session = sessionFactory.getCurrentSession();
	String msg="";
	perfomance_evaluation up=perfomance_evaluation;

	 if (formmode.equals("add")) {
        

         BigInteger billNumberDecimal = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR PERFORMANCE_SRL_NO AS SRL_NO")
                 .getSingleResult();
         BigDecimal srl_no = new BigDecimal(billNumberDecimal);
         
         
         
         up.setSrl_no(String.valueOf(srl_no));
         up.setCreate_user(userId);
         up.setStatus("Pending");
         up.setReview_assigning_date(new Date());
         perfomance_evaluation_rep.save(up);
         msg="Assigned successfully...";
     }else if (formmode.equals("modify")) {

    	 Optional<perfomance_evaluation> gett = perfomance_evaluation_rep.findById(userId);

         if (gett.isPresent()) {

        	 perfomance_evaluation up1 = gett.get();
         up1.setStatus("submitted");
         up1.setGoals_and_objectives_this(perfomance_evaluation.getGoals_and_objectives_this());
         up1.setAcheive_accoumb(perfomance_evaluation.getAcheive_accoumb());
         up1.setStrength_areas(perfomance_evaluation.getStrength_areas());
         up1.setSuggets_career(perfomance_evaluation.getSuggets_career());
         up1.setNext_eva_one(perfomance_evaluation.getNext_eva_one());
         up1.setNext_eva_two(perfomance_evaluation.getNext_eva_two());
         up1.setNext_eva_three(perfomance_evaluation.getNext_eva_three());
         up1.setReview_submition_date(new Date());
         perfomance_evaluation_rep.save(up1);
         msg="Updated successfully...";
         } else {
             msg = "Performance evaluation not found";
         }
     }
	 
	
	return msg;
}

public String addinvward( Inward Inward_add) {
	Session session = sessionFactory.getCurrentSession();
	
	
	BigInteger gateno = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR Gate_no AS SRL_NO")
            .getSingleResult();
	
	Inward_add.setGateEntryNumberGen("Gate00"+gateno);
	String msg="Addsucessfully-"+"Gate00"+gateno;
	Inward_add.setDelFlag("N");
	Inward_add.setEntityFlag("Y");
	Inward_add.setVerifyFlag("N");
	Inward_add.setGate_entry_flg("Y");
	String po_id=Inward_add.getPoNumber();
	Purchase_Order_Entity getinquir=Purchase_Order_Repo.getinquiry(po_id);
	Optional<Purchase_Order_Entity> gett = Purchase_Order_Repo.findById(po_id);
	if (gett.isPresent()) {
		getinquir.setGateEntryFlg('Y');
		Purchase_Order_Repo.save(getinquir);		
	}
	InwardRep.save(Inward_add);
	
	return msg;
}


public String addoutwards( Outward Outward_add) {
	Session session = sessionFactory.getCurrentSession();
	
	
	BigInteger gateno = (BigInteger)session.createNativeQuery("SELECT NEXT VALUE FOR outward AS SRL_NO")
            .getSingleResult();
	Outward_add.setGate_out_NumberGen("Gateout"+gateno);
	String msg="Addsucessfully-"+"Gateout"+gateno;
	Outward_add.setDelFlag("N");
	Outward_add.setEntityFlag("Y");
	Outward_add.setVerifyFlag("N");
	OutwardRep.save(Outward_add);
	
	return msg;
}

public String addInspectProductionProcess( PROCESS_ENTITY PROCESS_ENTITY_add) {
	System.out.println("refid="+PROCESS_ENTITY_add.getRef_id());
	List<PROCESS_ENTITY> up =PROCESS_REP.findByID(PROCESS_ENTITY_add.getRef_id());
	for(PROCESS_ENTITY up1:up) {
		System.out.println(PROCESS_ENTITY_add.getInspectProcess());
		up1.setInspectProcess(PROCESS_ENTITY_add.getInspectProcess());
		up1.setQCVerifydate(PROCESS_ENTITY_add.getQCVerifydate());
		PROCESS_REP.save(up1);
		WorkOrder wo =WorkOrderRep.getinquirywolist(up1.getWoid());
		wo.setStatus(PROCESS_ENTITY_add.getInspectProcess());
		WorkOrderRep.save(wo);
	}
	
	String msg="Addsucessfully";
	
	return msg;
}



public String addwo(WorkOrder WorkOrder_add,String loginuserid) {
	String msg="";
	Session session = sessionFactory.getCurrentSession();

	
	try {
   
		String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		
    
        
        // Check the date field
        System.out.println("entrydate: " +formattedDate );
        System.out.println("the entry user"+loginuserid);
        
        // Generate new PO ID from sequence
        BigInteger WO_id = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR work_order AS id")
                                                .getSingleResult();
        System.out.println("Generated Id: WO000" + WO_id);
        
        WorkOrder_add.setWorkId("WO000" + WO_id);
        WorkOrder_add.setEntryUser(loginuserid);
        WorkOrder_add.setEntryTime(formattedDate);
        
        
        WorkOrder_add.setEntityFlg('Y');
        WorkOrder_add.setVerifyFlg('N');
        WorkOrder_add.setModifyFlg('N');
        WorkOrder_add.setDelFlg('N');
        WorkOrder_add.setGateEntryFlg('N');
        WorkOrder_add.setQcFlg('N');
        WorkOrder_add.setApprovedFlg('N');
        
        // Save the entity to the database
        
        WorkOrderRep.save(WorkOrder_add);
        
        // Return success message
        msg = "WO successfully added...!!";
		
	}catch(Exception e) {
		
		msg="could not add wo";
	}
       return msg;
}

public String verifyWO(WorkOrder WorkOrder,String loginuserid,String workId) {
	
	System.out.println("the inside wo service"+workId);
	
	String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	
	String msg="";
	
	try
	{
		if(workId.isEmpty()) {
			msg="Error message : 404";
			}else {
				
				Optional<WorkOrder> woentity=WorkOrderRep.findById(workId);
				
				WorkOrder entity=woentity.get();
				entity.setVerifyFlg('Y');
				entity.setVerifyUser(loginuserid);
				entity.setVerifyTime(formattedDate);
				
				WorkOrderRep.save(entity);
				
				msg="successfully verified by"+loginuserid;
		}
	}
	catch(Exception e) {
		
		msg="the id not found";
	}
	return msg;
	
}


public String modifywo(WorkOrder WorkOrder) {
	String msg="";
	
	try {
		
		String woid=WorkOrder.getWorkId();
		
		Optional<WorkOrder> woentity=WorkOrderRep.findById(woid);
		
		WorkOrder entity=woentity.get();
		
		if(woentity.isPresent()) {
			WorkOrder.setEntityFlg(entity.getEntityFlg());
			WorkOrder.setVerifyFlg(entity.getVerifyFlg());
			WorkOrder.setModifyFlg(entity.getModifyFlg());
			WorkOrder.setDelFlg(entity.getDelFlg());
			WorkOrder.setGateEntryFlg(entity.getGateEntryFlg());
			WorkOrder.setQcFlg(entity.getQcFlg());
			WorkOrder.setApprovedFlg(entity.getApprovedFlg());
			
			WorkOrder.setVerifyTime(entity.getVerifyTime());
			WorkOrder.setVerifyUser(entity.getVerifyUser());
			WorkOrder.setEntryTime(entity.getEntryTime());
			WorkOrder.setEntryUser(entity.getEntryUser());
			WorkOrderRep.save(WorkOrder);
			
			msg="Record Modified";
		
			
		}
		else {
			msg="Id not present";
		}
		
		
	}catch(Exception e){
		msg="not found";
	}
	
	return msg;
}



@PersistenceContext
private EntityManager entityManager;

public Object getColumnValue(String columnName) {
    if (entityManager == null) {
        throw new IllegalStateException("EntityManager is not initialized");
    }

    String query = "SELECT " + columnName + " FROM ORGANIZATION_DETAILS";
    javax.persistence.Query nativeQuery = entityManager.createNativeQuery(query);
    return nativeQuery.getSingleResult();
}



	
}