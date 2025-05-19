package com.bornfire.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bornfire.config.AES;
import com.bornfire.entities.BTMAdminAssociateMod;
import com.bornfire.entities.BTMAdminAssociateModRep;
import com.bornfire.entities.BTMAdminAssociateProfile;
import com.bornfire.entities.BTMAdminAssociateProfileRep;
import com.bornfire.entities.BTMAdminCalendarMaster;
import com.bornfire.entities.BTMAdminCalendarMasterRep;
import com.bornfire.entities.BTMAdminExpenseReportRep;
import com.bornfire.entities.BTMAdminHolidayMaster;
import com.bornfire.entities.BTMAdminHolidayMasterRep;
import com.bornfire.entities.BTMAdminOrganizationMaster;
import com.bornfire.entities.BTMAdminOrganizationMasterRep;
import com.bornfire.entities.BTMAdminProfileManager;
import com.bornfire.entities.BTMAdminProfileMangerRep;
import com.bornfire.entities.BTMDocumentMaster;
import com.bornfire.entities.BTMDocumentMasterRep;
import com.bornfire.entities.BTMProjectDetailsRep;
import com.bornfire.entities.BTMProjectMaster;
import com.bornfire.entities.BTMProjectMasterRep;
import com.bornfire.entities.BTMProjectTeamDetailsRep;
import com.bornfire.entities.BTMRefCodeMaster;
import com.bornfire.entities.BTMRefCodeMasterRep;
import com.bornfire.entities.BTMTravelMaster;
import com.bornfire.entities.BTMTravelMasterRep;
import com.bornfire.entities.BTMWorkAssignment;
import com.bornfire.entities.BTMWorkAssignmentRep;
import com.bornfire.entities.BhmsInvProductSaleMasterRep;
import com.bornfire.entities.BhmsInvProductSaleMasterTable;
import com.bornfire.entities.Branch_Entity;
import com.bornfire.entities.Branch_rep;
import com.bornfire.entities.CapitalTrans;
import com.bornfire.entities.CapitalTransRep;
import com.bornfire.entities.Chart_Acc_Entity;
import com.bornfire.entities.Chart_Acc_Rep;
import com.bornfire.entities.Erp_ChartOfAccounts;
import com.bornfire.entities.Erp_ChartOfAccountsRep;
import com.bornfire.entities.ExpenseMaster;
import com.bornfire.entities.FINISHED_GOODS_ENTITY;
import com.bornfire.entities.FINISHED_GOODS_Rep;
import com.bornfire.entities.HRMS_USER_PROFILE_ENTITY;
import com.bornfire.entities.LeaveMaster;
import com.bornfire.entities.LeaveMasterRep;
import com.bornfire.entities.LeaveTableRep;
import com.bornfire.entities.OnDuty;
import com.bornfire.entities.OnDutyRep;
import com.bornfire.entities.PO_invoice_entity;
import com.bornfire.entities.PO_invoice_rep;
import com.bornfire.entities.ProjectDetails;
import com.bornfire.entities.ProjectTeamDetails;
import com.bornfire.entities.ResourceMaster;
import com.bornfire.entities.ResourceMasterRepo;
import com.bornfire.entities.TSK_OrganizationMasterEntity;
import com.bornfire.entities.TSK_OrganizationMasterRep;
import com.bornfire.entities.TSK_PROCESS_ENTITY;
import com.bornfire.entities.TSK_PROCESS_REP;
import com.bornfire.entities.TSK_branchEntity;
import com.bornfire.entities.TSK_branch_Rep;
import com.bornfire.entities.Training;
import com.bornfire.entities.Training_Rep;

@Service
@ConfigurationProperties("output")
@Transactional
public class AdminOperServices {

	
	@Autowired
	FINISHED_GOODS_Rep FINISHED_GOODS_Rep;
	
	@Autowired
	CapitalTransRep CapitalTransRep;

	@Autowired
	TSK_branch_Rep TSK_branch_Reps;
	@Autowired
	TSK_OrganizationMasterRep TSK_OrganizationMasterReps;
	@Autowired
	BhmsInvProductSaleMasterRep bhmsInvProductSaleMasterrep;

	@Autowired
	PO_invoice_rep PO_invoice_reps;

	@Autowired
	private LeaveTableRep leaveTableRep;

	@Autowired
	BTMAdminAssociateProfileRep btmAdminAssociateProfileRep;

	@Autowired
	Training_Rep Training_Rep;


	@Autowired
	BTMAdminOrganizationMasterRep btmAdminOrganizationMasterRep;

	@Autowired
	BTMAdminHolidayMasterRep btmAdminHolidayMasterRep;

	@Autowired
	BTMAdminProfileMangerRep btmAdminProfileMangerRep;

	@Autowired
	BTMAdminExpenseReportRep btmAdminExpenseReportRep;

	@Autowired
	BTMProjectMasterRep btmProjectMasterRep;

	@Autowired
	BTMTravelMasterRep BTMtravelMasterRep;

	@Autowired
	BTMWorkAssignmentRep btmWorkAssignmentRep;

	@Autowired
	BTMRefCodeMasterRep btmRefCodeMasterRep;

	@Autowired
	BTMDocumentMasterRep btmDocumentMasterRep;

	@Autowired
	LeaveMasterRep leaveMasterRep;

	@Autowired
	OnDutyRep onDutyRep;

	@Autowired
	BTMProjectDetailsRep btmProjectDetailsRep;

	@Autowired
	BTMProjectTeamDetailsRep btmProjectTeamDetailsRep;

	@Autowired
	BTMAdminCalendarMasterRep btmAdminCalendarMasterRep;

	@Autowired
	BTMAdminAssociateModRep btmAdminAssociateModRep;

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	ResourceMasterRepo resourceMasterRepo;

	@Autowired
	Chart_Acc_Rep chart_Acc_Rep;

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	Erp_ChartOfAccountsRep Erp_ChartOfAccountsRep;

// ==================================== Admin Organization Master ========================================

	public String addOrganizationModyfiy(BTMAdminOrganizationMaster btmAdminOrganizationMaster, String formmode) {

		String msg = "";

		if (formmode.equals("edit")) {
			

			BTMAdminOrganizationMaster up = btmAdminOrganizationMaster;
			
			BTMAdminOrganizationMaster old = btmAdminOrganizationMasterRep.getOrganization();
			System.out.println("OrganizationMaster="+old.getMaternity_Leave());
			//BTMAdminOrganizationMaster old = btmAdminOrganizationMasterRep.getOrganizationbyid(up.getRegn_no());
			
			up.setEntity_flg("Y");

			up.setDel_flg("Y");
			up.setCash_balance(old.getCash_balance());
			up.setAccount_balance(old.getAccount_balance());
			up.setCapitalamount(old.getCash_balance().add(old.getAccount_balance()));
			up.setCurrent_cash_balance(old.getCurrent_cash_balance());
			up.setCurrent_account_balance(old.getCurrent_account_balance());

			btmAdminOrganizationMasterRep.save(up);

			/*---move to Associateprofile--*/
			try {

			long casualleave = up.getCasual_leave().longValue();
			long sickleave = up.getSick_leave().longValue();
			long maternity = up.getEarned_leave().longValue();
			long paternity = up.getSpl_leave().longValue();
			long yearly = up.getEarned_leave().longValue();
			long specialleave=up.getSpl_leave().longValue();

			// Update all rows in HRMS.RESOURCE_MASTER table
			String queryStr = "UPDATE RESOURCE_MASTER "
					+ "SET " + "CASUAL_LEAVE = :casualleave, "
					+ "SICK_LEAVE = :sickleave, " + "MATERNITY_LEAVE = :maternity, " + "PATERNITY_LEAVE = :paternity," + "SPL_LEAVE =:specialleave,"
					+ "YEARLY_LEAVE = :yearly";

			Query query = entityManager.createNativeQuery(queryStr);
			query.setParameter("casualleave", casualleave);
			query.setParameter("sickleave", sickleave);
			query.setParameter("maternity", maternity);
			query.setParameter("paternity", paternity);
			query.setParameter("yearly", yearly);
			query.setParameter("specialleave", specialleave);

			int rowsUpdated = query.executeUpdate();
			}catch (Exception e) {
		        e.printStackTrace();
			}

			msg = "Modified Successfully";

		}
		return msg;
	}

	
	
	// ==================================== TSK Organization Master ========================================

		public String TSK_Org(TSK_OrganizationMasterEntity TSK_OrganizationMasterEntity, String formmode) {

			String msg = "";
			Session session = sessionFactory.getCurrentSession();

			if (formmode.equals("add")) {
				TSK_OrganizationMasterEntity up = TSK_OrganizationMasterEntity;
				
				if (formmode.equals("add")) {
					  // Generate ID from sequence
					BigInteger bigIntId = (BigInteger) session
						    .createNativeQuery("SELECT NEXT VALUE FOR ORG_ID").getSingleResult();
						up.setId(bigIntId.toString());
						up.setDel_flg("N");
						List<TSK_OrganizationMasterEntity> old = TSK_OrganizationMasterReps.getall();

						if (old == null || old.isEmpty()) {
						    up.setOrg_id("ORG001");
						} else {
						    int newIdNumber = old.size() + 1;
						    String formattedId = String.format("ORG%03d", newIdNumber); // Pads with zeros
						    up.setOrg_id(formattedId);
						}

				/*BTMAdminOrganizationMaster old = btmAdminOrganizationMasterRep.getOrganization();
				System.out.println("OrganizationMaster="+old.getMaternity_Leave());
				
				up.setEntity_flg("Y");

				up.setDel_flg("Y");
				up.setCash_balance(old.getCash_balance());
				up.setAccount_balance(old.getAccount_balance());
				up.setCapitalamount(old.getCash_balance().add(old.getAccount_balance()));
				up.setCurrent_cash_balance(old.getCurrent_cash_balance());
				up.setCurrent_account_balance(old.getCurrent_account_balance());
*/
		            TSK_OrganizationMasterReps.save(up);

				/*---move to Associateprofile--*/
				try {

				long casualleave = up.getCasual_leave().longValue();
				long sickleave = up.getSick_leave().longValue();
				long maternity = up.getEarned_leave().longValue();
				long paternity = up.getSpl_leave().longValue();
				long yearly = up.getEarned_leave().longValue();
				long specialleave=up.getSpl_leave().longValue();

				// Update all rows in HRMS.RESOURCE_MASTER table
				String queryStr = "UPDATE RESOURCE_MASTER "
						+ "SET " + "CASUAL_LEAVE = :casualleave, "
						+ "SICK_LEAVE = :sickleave, " + "MATERNITY_LEAVE = :maternity, " + "PATERNITY_LEAVE = :paternity," + "SPL_LEAVE =:specialleave,"
						+ "YEARLY_LEAVE = :yearly";

				Query query = entityManager.createNativeQuery(queryStr);
				query.setParameter("casualleave", casualleave);
				query.setParameter("sickleave", sickleave);
				query.setParameter("maternity", maternity);
				query.setParameter("paternity", paternity);
				query.setParameter("yearly", yearly);
				query.setParameter("specialleave", specialleave);

				int rowsUpdated = query.executeUpdate();
				}catch (Exception e) {
			        e.printStackTrace();
				}

				msg = "Organization Added Successfully";

			}
			}else if (formmode.equals("edit")) {
				
				System.out.println("id is:"+TSK_OrganizationMasterEntity.getId());

				TSK_OrganizationMasterEntity up=TSK_OrganizationMasterReps.getallid(TSK_OrganizationMasterEntity.getId());
				up=TSK_OrganizationMasterEntity;
				BTMAdminOrganizationMaster old = btmAdminOrganizationMasterRep.getOrganization();
				System.out.println("OrganizationMaster="+old.getMaternity_Leave());
				//BTMAdminOrganizationMaster old = btmAdminOrganizationMasterRep.getOrganizationbyid(up.getRegn_no());
				
				up.setEntity_flg("Y");
				up.setDel_flg("N");
				up.setCash_balance(old.getCash_balance());
				up.setAccount_balance(old.getAccount_balance());
				up.setCapitalamount(old.getCash_balance().add(old.getAccount_balance()));
				up.setCurrent_cash_balance(old.getCurrent_cash_balance());
				up.setCurrent_account_balance(old.getCurrent_account_balance());

				TSK_OrganizationMasterReps.save(up);

				/*---move to Associateprofile--*/
				try {

				long casualleave = up.getCasual_leave().longValue();
				long sickleave = up.getSick_leave().longValue();
				long maternity = up.getEarned_leave().longValue();
				long paternity = up.getSpl_leave().longValue();
				long yearly = up.getEarned_leave().longValue();
				long specialleave=up.getSpl_leave().longValue();

				// Update all rows in HRMS.RESOURCE_MASTER table
				String queryStr = "UPDATE RESOURCE_MASTER "
						+ "SET " + "CASUAL_LEAVE = :casualleave, "
						+ "SICK_LEAVE = :sickleave, " + "MATERNITY_LEAVE = :maternity, " + "PATERNITY_LEAVE = :paternity," + "SPL_LEAVE =:specialleave,"
						+ "YEARLY_LEAVE = :yearly";

				Query query = entityManager.createNativeQuery(queryStr);
				query.setParameter("casualleave", casualleave);
				query.setParameter("sickleave", sickleave);
				query.setParameter("maternity", maternity);
				query.setParameter("paternity", paternity);
				query.setParameter("yearly", yearly);
				query.setParameter("specialleave", specialleave);

				int rowsUpdated = query.executeUpdate();
				}catch (Exception e) {
			        e.printStackTrace();
				}

				msg = "Modified Successfully";

			}
			return msg;
		
		}
	
	public BTMAdminOrganizationMaster getUser(String id) {

		if (btmAdminOrganizationMasterRep.existsById(id)) {
			BTMAdminOrganizationMaster up = btmAdminOrganizationMasterRep.findById(id).get();
			return up;
		} else {
			return new BTMAdminOrganizationMaster();
		}
	};
	/*
	 * public List<BTMAdminAssociateProfile> getAssociteUserslist() {
	 * 
	 * List<BTMAdminAssociateProfile> users =
	 * btmAdminAssociateProfileRep.getAssociatelist();
	 * 
	 * return users;
	 * 
	 * }
	 */

//	================================== Admin Associate master =========================================

	public String addAssociateUser(BTMAdminAssociateProfile user, String formmode, String UserId, String filename,
			String sessionUserId, String filetype, byte[] byteArray) throws IOException {

		String msg = "";

		if (formmode.equals("edit")) {
			BTMAdminAssociateProfile up1 = btmAdminAssociateProfileRep.getEmployeedetail(user.getResource_id());
			{

				user.setEntity_flg("N");
				user.setModify_flg("Y");
				user.setDel_flg("N");
				user.setEmployee_id(user.getResource_id());
				if (user.getAadharfile() == null) {
					user.setAadhar(up1.getAadharfilename());
					user.setAadharfile(up1.getAadharfile());
					;
				}
				if (user.getPanCardfilename() == null) {
					user.setPancardfile(up1.getPancardfile());
					user.setPanCardfilename(up1.getPanCardfilename());
					;
				}
				if (user.getPassportfilename() == null) {
					user.setPassportfile(up1.getPassportfile());
					user.setPassportfilename(up1.getPassportfilename());
					;
				}
				if (user.getDrivinglicensefilename() == null) {
					user.setDrivinglicense(up1.getDrivinglicense());
					user.setDrivinglicensefilename(up1.getDrivinglicensefilename());
					;
				}

				if (user.getSlcfilename() == null) {
					user.setSlc(up1.getSlc());
					user.setSlcfilename(up1.getSlcfilename());
					;
				}

				if (user.getDiplomafilename() == null) {
					user.setDiplomafile(up1.getDiplomafile());
					user.setDiplomafilename(up1.getDiplomafilename());
					;
				}

				if (user.getHscdocumentfilename() == null) {
					user.setHscdocumentfile(up1.getHscdocumentfile());
					user.setHscdocumentfilename(up1.getHscdocumentfilename());
				}
				if (user.getUgdocumenfilename() == null) {
					user.setUgdocumentfile(up1.getUgdocumentfile());
					user.setUgdocumenfilename(up1.getUgdocumenfilename());
				}
				if (user.getPgdocumentfilename() == null) {
					user.setPgdocumentfile(up1.getPgdocumentfile());
					user.setPgdocumentfilename(up1.getPgdocumentfilename());
				}

				if (user.getFiles() == null) {
					user.setFileName(up1.getFileName());
					user.setFiles(up1.getFiles());
					user.setFiletype(up1.getFiletype());
				}

				user.setModify_user(UserId);

				if (user.getLogin_high() == "") {
					user.setLogin_high(null);
				}
				if (user.getLogin_low() == "") {
					user.setLogin_low(null);
				}

				user.setCasualLeave(up1.getCasualLeave());
				user.setSickLeave(up1.getSickLeave());
				user.setMaternityLeave(up1.getMaternityLeave());
				user.setPaternityLeave(up1.getPaternityLeave());
				user.setYearlyLeave(up1.getYearlyLeave());

				user.setModify_time(new Date());
				Session session = sessionFactory.getCurrentSession();
				session.saveOrUpdate(up1);
				up1.setEntity_flg("N");
				up1.setModify_flg("Y");
				// BTMAdminAssociateMod NEW=new BTMAdminAssociateMod(up1);
				btmAdminAssociateProfileRep.save(user);
				// btmAdminAssociateModRep.save(NEW);
				msg = "Modified Successfully";
			}

		} else if (formmode.equals("verify")) {
			
			if (UserId == null || UserId.isEmpty()) {

				return "User ID is required for verification.";
			}

			Session session = sessionFactory.getCurrentSession();
			BTMAdminAssociateProfile ver = btmAdminAssociateProfileRep.getEmployeedetail(user.getResource_id());

			Optional<BTMAdminAssociateProfile> entity = btmAdminAssociateProfileRep.findById(UserId);

			BTMAdminAssociateProfile user1 = entity.get();

			System.out.println("previous user is : " + user.getResource_id());

			
			if (user.getResource_id().equals(UserId)) {
				msg = "The same user cannot verify themselves.";
			} else {
				ver.setEntity_flg("Y");
				ver.setModify_flg("N");
				ver.setDel_flg("N");
				ver.setVerify_user(UserId);
				ver.setVerify_time(new Date());
				if (ver.getLogin_high() == "") {
					ver.setLogin_high(null);
				}
				if (ver.getLogin_low() == "") {
					ver.setLogin_low(null);
				}

				btmAdminAssociateProfileRep.save(ver);
				session.saveOrUpdate(ver);
				BTMAdminAssociateMod Main = new BTMAdminAssociateMod(ver);
				Main.setEntity_flg("N");
				// Main.setModify_flg("N");
				// Main.setDel_flg("N");
				btmAdminAssociateModRep.save(Main);
				// btmAdminAssociatePrifleRep.deleteById(user.getResource_id());
				msg = "Verified Successfully";
			}
		} else if (formmode.equals("delete")) {

			BTMAdminAssociateProfile Dmain = btmAdminAssociateProfileRep.getEmployeedetail(user.getResource_id());
			Dmain.setEntity_flg("N");
			Dmain.setDel_flg("Y");
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(Dmain);
			msg = "Deleted Successfully";

		} else if (formmode.equals("cancel")) {

			BTMAdminAssociateMod ver = btmAdminAssociateModRep.getEmployeedetail(user.getResource_id());
			user.setEntity_flg("Y");
			user.setModify_flg("N");
			user.setDel_flg("N");
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(ver);
			BTMAdminAssociateProfile Main = new BTMAdminAssociateProfile(ver);
			// Main.setEntity_flg("Y");
			// Main.setModify_flg("N");
			// Main.setDel_flg("N");
			btmAdminAssociateProfileRep.save(Main);
			// btmAdminAssociateModRep.deleteById(user.getResource_id());
			msg = "Cancelled Successfully";

		} else if (formmode.equals("add")) {

			Session session = sessionFactory.getCurrentSession();

			BigInteger sr_id = (BigInteger) session
					.createNativeQuery("SELECT NEXT VALUE FOR EMP_ASSOCIATE_SEQ AS id").getSingleResult();
			
		
			
			
		

			
				/*
				 * List<BTMAdminOrganizationMaster>
				 * organization=(List<BTMAdminOrganizationMaster>) new
				 * BTMAdminOrganizationMaster();
				 */
			
			List<BTMAdminOrganizationMaster> organizationmastertable=(List<BTMAdminOrganizationMaster>) btmAdminOrganizationMasterRep.findAll();

			BTMAdminOrganizationMaster organization=organizationmastertable.get(0);

				BTMAdminAssociateProfile up = user;
				BTMAdminAssociateMod up1 = new BTMAdminAssociateMod();
				up.setResource_id(user.getResource_id());
				up.setEmployee_id(user.getResource_id());
				up.setEntity_flg("N");
				up.setModify_flg("N");
				up.setUsedFlag("N");
				up.setFileName(filename);
				// up.setFiles(byteArray);
				// up.setFiletype(filetype);
				up.setEntry_user(UserId);
				up.setEntry_time(new Date());
				up1.setResource_id(user.getResource_id());
				up.setDel_flg("N");
				up1.setEntity_flg("N");
				up1.setModify_flg("N");
				up1.setDel_flg("N");
				up1.setEntry_user(UserId);
				up1.setEntry_time(new Date());

				if (user.getLogin_high() == "" && user.getLogin_high() != "") {
					up.setLogin_high(null);
				}

				if (user.getLogin_low() == "" && user.getLogin_low() != " ") {
					up.setLogin_low(null);
				}

				String encrypted_imei = null;
				try {
					
                     if(encrypted_imei != null)	
                     {				
					encrypted_imei = AES.encrypt(user.getImei());
                     }
                     
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				up.setImei(encrypted_imei);
				up1.setImei(encrypted_imei);
				String encrypted_password = null;
				try {
					if(encrypted_password !=null) {
					encrypted_password = AES.encrypt(user.getPassword());
					}

					System.out.println("the password:" + user.getPassword());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				up.setPassword(encrypted_password);
				up1.setPassword(encrypted_password);
				/*---save organization--*/
				up.setCasualLeave(organization.getCasual_leave().longValue());
				up.setSickLeave(organization.getSick_leave().longValue());
				up.setMaternityLeave(organization.getEarned_leave().longValue());
				up.setPaternityLeave(organization.getSick_leave().longValue());
				up.setYearlyLeave(organization.getEarned_leave().longValue());
			
				
				
				btmAdminAssociateProfileRep.save(user);
				btmAdminAssociateModRep.save(up1);
				
			/*--ACCOUNT CREATION---*/
				
				BigInteger generatedId = (BigInteger) session
			            .createNativeQuery("SELECT NEXT VALUE FOR chart_of_accounts_seq AS id")
			            .getSingleResult();
			  Erp_ChartOfAccounts list=new Erp_ChartOfAccounts();
			  
			  list.setAccountName(user.getResource_name());
			  list.setAccountType("Liability");
			  list.setParentaccount("ACC00071");
			  list.setOwnership("Employee");
			  list.setOwnershipid(user.getResource_id());
			  Date curentdate= new Date();
			  list.setEntryDate(curentdate);
			  list.setAccountNumber("ACC000"+generatedId.toString());
			  list.setAccountBalance(BigDecimal.ZERO);
			  list.setTotalCreditBalance(BigDecimal.ZERO);
			  list.setTotalDebitBalance(BigDecimal.ZERO);
			  
			  Erp_ChartOfAccountsRep.save(list);
				
				msg = "Account Added Successfully";
			
		}
		return msg;
	}

	public BTMAdminAssociateProfile getAssociteUser(String resId) {

		if (btmAdminAssociateProfileRep.existsById(resId)) {
			BTMAdminAssociateProfile up = btmAdminAssociateProfileRep.findById(resId).get();
			return up;
		} else {
			return new BTMAdminAssociateProfile();
		}
	};

	public BTMAdminAssociateProfile getAssociteVerifyUser(String resId) {

		if (btmAdminAssociateProfileRep.existsById(resId)) {
			BTMAdminAssociateProfile up = btmAdminAssociateProfileRep.findById(resId).get();
			return up;
		} else {
			return new BTMAdminAssociateProfile();
		}
	}

	public BTMAdminAssociateProfile getAssociteCancelUser(String resId) {

		if (btmAdminAssociateProfileRep.existsById(resId)) {
			BTMAdminAssociateProfile up = btmAdminAssociateProfileRep.findById(resId).get();
			return up;
		} else {
			return new BTMAdminAssociateProfile();
		}
	}

	public BTMAdminAssociateProfile getAssociteListUser(String resId) {

		if (btmAdminAssociateProfileRep.existsById(resId)) {
			BTMAdminAssociateProfile up = btmAdminAssociateProfileRep.findById(resId).get();
			return up;
		} else {
			return new BTMAdminAssociateProfile();
		}
	}

// ========================================== Maintenance associate profile ==================================

	public String modifyAssociate(BTMAdminAssociateProfile btmAdminAssociateProfile, String formmode, String userid) {

		String msg = "";

		if (formmode.equals("edit")) {

			BTMAdminAssociateProfile up1 = btmAdminAssociateProfileRep.getEmployeedetail(userid);

			BTMAdminAssociateProfile up = btmAdminAssociateProfile;

			up1.setDis_start_date(up.getDis_start_date());

			up1.setDis_end_date(up.getDis_end_date());

			up1.setEntity_flg("N");

			up1.setDel_flg("Y");

			up1.setDisable_flg(up.getDisable_flg());

			btmAdminAssociateProfileRep.save(up1);

			msg = "Modified Successfully";

		}

		return msg;

	}

// =============================== Maintenance Leave Master=======================

	public String modifyLeave(LeaveMaster leaveMaster, String formmode, String userid, String Id) {

		String msg = "";

		BigDecimal n = new BigDecimal(0);
		if (formmode.equals("approve")) {

			LeaveMaster up1 = leaveMasterRep.getleaveMaster(userid);

			LeaveMaster up = leaveMaster;
			ResourceMaster remail = resourceMasterRepo.getrole(Id);

			if (up1.getAppr_email_1() != null && remail.getEmail().equals(up1.getAppr_email_1())
					|| up1.getAppr_email_2() != null && remail.getEmail().equals(up1.getAppr_email_2())
					|| up1.getAppr_email_3() != null && remail.getEmail().equals(up1.getAppr_email_3())
					|| up1.getAppr_email_4() != null && remail.getEmail().equals(up1.getAppr_email_4())
					|| up1.getAppr_email_5() != null && remail.getEmail().equals(up1.getAppr_email_5())) {

				if (up1.getAppr_email_1() != null && remail.getEmail().equals(up1.getAppr_email_1())) {
					up1.setAppr_email_status_1("Approved");
					up1.setAppr_no_days_1(n);
				}
				if (up1.getAppr_email_2() != null && remail.getEmail().equals(up1.getAppr_email_2())) {
					up1.setAppr_email_status_2("Approved");
					up1.setAppr_no_days_2(n);
				}
				if (up1.getAppr_email_3() != null && remail.getEmail().equals(up1.getAppr_email_3())) {
					up1.setAppr_email_status_3("Approved");
					up1.setAppr_no_days_3(n);
				}
				if (up1.getAppr_email_4() != null && remail.getEmail().equals(up1.getAppr_email_4())) {
					up1.setAppr_email_status_4("Approved");
					up1.setAppr_no_days_4(n);
				}
				if (up1.getAppr_email_5() != null && remail.getEmail().equals(up1.getAppr_email_5())) {
					up1.setAppr_email_status_5("Approved");
					up1.setAppr_no_days_5(n);
				}

				if (up1.getAppr_no_days_1().compareTo(BigDecimal.ZERO) == 0
						&& up1.getAppr_no_days_2().compareTo(BigDecimal.ZERO) == 0
						&& up1.getAppr_no_days_3().compareTo(BigDecimal.ZERO) == 0
						&& up1.getAppr_no_days_4().compareTo(BigDecimal.ZERO) == 0
						&& up1.getAppr_no_days_5().compareTo(BigDecimal.ZERO) == 0) {

					up1.setStatus("Approved");

					up1.setAuth_time(new Date());

					up1.setAuth_user(Id);

					up1.setEntity_flg("Y");

					leaveMasterRep.save(up1);

				}
				msg = "Approved Successfully";
			} else if (up1.getAppr_no_days_1().compareTo(BigDecimal.ZERO) == 0
					&& up1.getAppr_no_days_2().compareTo(BigDecimal.ZERO) == 0
					&& up1.getAppr_no_days_3().compareTo(BigDecimal.ZERO) == 0
					&& up1.getAppr_no_days_4().compareTo(BigDecimal.ZERO) == 0
					&& up1.getAppr_no_days_5().compareTo(BigDecimal.ZERO) == 0) {

				up1.setStatus("Approved");

				up1.setAuth_time(new Date());

				up1.setAuth_user(Id);

				up1.setEntity_flg("Y");

				leaveMasterRep.save(up1);
				msg = "Approved Successfully";
			}

			else {

				msg = "You Can't Approve";
			}
		}

		if (formmode.equals("reject")) {

			ResourceMaster remail = resourceMasterRepo.getrole(Id);
			LeaveMaster up1 = leaveMasterRep.getleaveMaster(userid);

			LeaveMaster up = leaveMaster;

			if (up1.getAppr_email_1() != null && remail.getEmail().equals(up1.getAppr_email_1())
					|| up1.getAppr_email_2() != null && remail.getEmail().equals(up1.getAppr_email_2())
					|| up1.getAppr_email_3() != null && remail.getEmail().equals(up1.getAppr_email_3())
					|| up1.getAppr_email_4() != null && remail.getEmail().equals(up1.getAppr_email_4())
					|| up1.getAppr_email_5() != null && remail.getEmail().equals(up1.getAppr_email_5())) {

				if (up1.getAppr_email_1() != null && remail.getEmail().equals(up1.getAppr_email_1())) {
					up1.setAppr_email_status_1("Rejected");
					up1.setAppr_no_days_1(n);
				}
				if (up1.getAppr_email_2() != null && remail.getEmail().equals(up1.getAppr_email_2())) {
					up1.setAppr_email_status_2("Rejected");
					up1.setAppr_no_days_2(n);
				}
				if (up1.getAppr_email_3() != null && remail.getEmail().equals(up1.getAppr_email_3())) {
					up1.setAppr_email_status_3("Rejected");
					up1.setAppr_no_days_3(n);
				}
				if (up1.getAppr_email_4() != null && remail.getEmail().equals(up1.getAppr_email_4())) {
					up1.setAppr_email_status_4("Rejected");
					up1.setAppr_no_days_4(n);
				}
				if (up1.getAppr_email_5() != null && remail.getEmail().equals(up1.getAppr_email_5())) {
					up1.setAppr_email_status_5("Rejected");
					up1.setAppr_no_days_5(n);
				}

				if (up1.getAppr_no_days_1().compareTo(BigDecimal.ZERO) == 0
						&& up1.getAppr_no_days_2().compareTo(BigDecimal.ZERO) == 0
						&& up1.getAppr_no_days_3().compareTo(BigDecimal.ZERO) == 0
						&& up1.getAppr_no_days_4().compareTo(BigDecimal.ZERO) == 0
						&& up1.getAppr_no_days_5().compareTo(BigDecimal.ZERO) == 0) {

					up1.setStatus("Rejected");
					up1.setEntity_flg("Y");

					up1.setReject_remarks("Rejected");

					up1.setDel_flg("Y");
					up1.setAuth_time(new Date());

					up1.setAuth_user(Id);

					leaveMasterRep.save(up1);

				}
				msg = "Rejected Successfully";

			} else if (up1.getAppr_no_days_1().compareTo(BigDecimal.ZERO) == 0
					&& up1.getAppr_no_days_2().compareTo(BigDecimal.ZERO) == 0
					&& up1.getAppr_no_days_3().compareTo(BigDecimal.ZERO) == 0
					&& up1.getAppr_no_days_4().compareTo(BigDecimal.ZERO) == 0
					&& up1.getAppr_no_days_5().compareTo(BigDecimal.ZERO) == 0) {

				up1.setStatus("Rejected");
				up1.setEntity_flg("Y");

				up1.setReject_remarks("Rejected");

				up1.setDel_flg("Y");
				up1.setAuth_time(new Date());

				up1.setAuth_user(Id);

				leaveMasterRep.save(up1);

				msg = "Rejected Successfully";

			} else {

				msg = "You Can't Reject";
			}

		}
		return msg;
	}
// ============================= Maintenance OD Master ===================

	public String modifyOd(OnDuty onDuty, String formmode, String userid, String Id) {

		String msg = "";

		if (formmode.equals("approve")) {

			OnDuty up1 = onDutyRep.getOdMaster(userid);

			OnDuty up = onDuty;

			up1.setStatus("Approved");

			up1.setEntity_flg("Y");

			up1.setAuth_time(new Date());

			up1.setAuth_user(Id);

			onDutyRep.save(up1);

			msg = "Approved Successfully";

		}

		if (formmode.equals("reject")) {

			OnDuty up1 = onDutyRep.getOdMaster(userid);

			OnDuty up = onDuty;

			up1.setStatus("Rejected");

			up1.setEntity_flg("Y");

			up1.setReject_remarks("Rejected");

			up1.setDel_flg("Y");

			up1.setAuth_time(new Date());

			up1.setAuth_user(Id);

			onDutyRep.save(up1);

			msg = "Rejected Successfully";

		}

		return msg;

	}

// ============================== Maintenance Expense Master =========================

	public String modifyExpense(ExpenseMaster expenses, String formmode, String resId) {

		String msg = "";

		if (formmode.equals("approve")) {

			ExpenseMaster up1 = btmAdminExpenseReportRep.getExpenseMaster(resId);
			ExpenseMaster up = expenses;
			
			 if (up.getBal_amt() != null && up.getBal_amt().compareTo(BigDecimal.ZERO) == 0) {
		            up1.setStatus("Approved");
		            up1.setDel_flg("Y");
		        } else {
		            up1.setStatus("Pending");
		            up1.setDel_flg("N");
		        }
			up1.setEntity_flg("Y");
			
			up1.setDate_of_apr(up.getDate_of_apr());
			up1.setAmt_apr(up.getAmt_apr());
			up1.setDate_of_adv(up.getDate_of_adv());
			up1.setAdv_amt(up.getAdv_amt());
			up1.setDate_of_settl(up.getDate_of_settl());
			up1.setBal_amt(up.getBal_amt());

			btmAdminExpenseReportRep.save(up1);

			msg = "Approved Successfully";

		}

		if (formmode.equals("reject")) {

			ExpenseMaster up1 = btmAdminExpenseReportRep.getExpenseMaster(resId);
			ExpenseMaster up = expenses;
			up1.setStatus("Rejected");
			up1.setEntity_flg("Y");
			up1.setDel_flg("Y");

			btmAdminExpenseReportRep.save(up1);

			msg = "Rejected Successfully";

		}

		return msg;

	}

// ===================================== Maintenance Travel Master ================================

	public String modifyTravelMaster(BTMTravelMaster travelMaster, String formmode, String resId) {

		String msg = "";

		if (formmode.equals("approve")) {

			BTMTravelMaster up1 = BTMtravelMasterRep.getTravelMaster(resId);

			BTMTravelMaster up = travelMaster;
			
			System.out.println("the balance amount" +up.getTra_claim_amt());
			
			
			
			 if (up.getTra_claim_amt() != null && up.getTra_claim_amt().compareTo(BigDecimal.ZERO) == 0) {
		            up1.setTra_status("Approved");
		            up1.setDel_flg("Y");
		        } else {
		            up1.setTra_status("Pending");
		            up1.setDel_flg("N");
		        }
			
		

			up1.setEntity_flg("Y");

		
			
			up1.setAdv_app_date(up.getAdv_app_date());
up1.setAdv_app_amt(up.getAdv_app_amt());	
up1.setAdv_dis_date(up.getAdv_dis_date());
up1.setAdv_dis_amt(up.getAdv_dis_amt());
up1.setTra_claim_date(up.getTra_claim_date());
up1.setTra_claim_amt(up.getTra_claim_amt());
			BTMtravelMasterRep.save(up1);

			msg = "Approved Successfully";

		}

		if (formmode.equals("reject")) {

			BTMTravelMaster up1 = BTMtravelMasterRep.getTravelMaster(resId);
			BTMTravelMaster up = travelMaster;
			up1.setTra_status("Rejected");
			up1.setEntity_flg("Y");
			up1.setDel_flg("Y");

			BTMtravelMasterRep.save(up1);

			msg = "Rejected Successfully";

		}

		return msg;

	}

// ========================= Admin Holiday Master ===========================

	public String addHolidayDetails(BTMAdminHolidayMaster btmAdminHolidayMaster, String formmode, BigDecimal recordNo) {

		String msg = "";

		BTMAdminHolidayMaster up1 = btmAdminHolidayMaster;

		BigDecimal test = btmAdminHolidayMasterRep.getcount(up1.getRecord_srl());

		if (formmode.equals("add")) {

			BTMAdminHolidayMaster up = btmAdminHolidayMaster;

			up.setEntity_flg("Y");

			up.setDel_flg("N");

			btmAdminHolidayMasterRep.save(up);
			
			BigDecimal nextValue = btmAdminHolidayMasterRep.getNextSequenceValue();
			

			msg = "Added Successfully";

		} else if (formmode.equals("edit")) {

			BTMAdminHolidayMaster up = btmAdminHolidayMaster;

			up.setEntity_flg("Y");

			up.setDel_flg("N");

			btmAdminHolidayMasterRep.save(up);

			msg = "Edited Successfully";
		}

		return msg;
	}

	public List<BTMAdminHolidayMaster> getHolidaylist() {

		List<BTMAdminHolidayMaster> users = btmAdminHolidayMasterRep.getAssocitelist();

		return users;

	}

	public BTMAdminHolidayMaster getHolidayDetail(BigDecimal resId) {

		if (btmAdminHolidayMasterRep.existsById(resId)) {
			BTMAdminHolidayMaster up = btmAdminHolidayMasterRep.findById(resId).get();
			return up;
		} else {
			return new BTMAdminHolidayMaster();
		}
	};

//	================================= Admin Profile Manager ===============================

	public List<BTMAdminProfileManager> getProfileManagerlist() {

		List<BTMAdminProfileManager> users = btmAdminProfileMangerRep.getProfilelist();

		return users;

	}

	public BTMAdminProfileManager getProfileManager(String id) {

		if (btmAdminProfileMangerRep.existsById(id)) {
			BTMAdminProfileManager up = btmAdminProfileMangerRep.findById(id).get();
			return up;
		} else {
			return new BTMAdminProfileManager();
		}
	};

	public String addProfileDetails(BTMAdminProfileManager btmAdminProfileManager, String formmode) {

		String msg = "";

		if (formmode.equals("edit")) {

			BTMAdminProfileManager up = btmAdminProfileManager;

			up.setEntity_flg("Y");

			up.setDel_flg("N");

			btmAdminProfileMangerRep.save(up);

			msg = "Edited Successfully";

		} else if (formmode.equals("verify")) {

			BTMAdminProfileManager up = btmAdminProfileManager;

			up.setEntity_flg("Y");

			up.setDel_flg("N");

			up.setModify_flg("Y");

			btmAdminProfileMangerRep.save(up);

			msg = "Verified Successfully";

		} else if (formmode.equals("delete")) {

			BTMAdminProfileManager up = btmAdminProfileManager;

			up.setEntity_flg("N");

			up.setDel_flg("Y");

			btmAdminProfileMangerRep.save(up);

			msg = "Deleted Successfully";
		}

		return msg;
	}

	// LIST AND DETAILS EXPENSE REPORT
	public List<ExpenseMaster> getExpenseReportlist() {

		List<ExpenseMaster> users = btmAdminExpenseReportRep.getReportList();

		return users;

	}

	public ExpenseMaster getReportManager(String id) {

		if (btmAdminExpenseReportRep.existsById(id)) {
			ExpenseMaster up = btmAdminExpenseReportRep.findById(id).get();
			return up;
		} else {
			return new ExpenseMaster();
		}
	}

//	travel Master List

	public List<BTMTravelMaster> getTravelMasterList() {

		List<BTMTravelMaster> users = BTMtravelMasterRep.getTravellist();

		return users;
	}

	// LIST PROFILE MASTER
	public List<BTMProjectMaster> getProjectMasterlist() {

		List<BTMProjectMaster> users = btmProjectMasterRep.getProjectlist();

		return users;
	}

	// Add PROJECT MASTER

	public String addProjectMaster(BTMProjectMaster btmProjectMaster, ProjectDetails projectDetails,
			ProjectTeamDetails projectTeamDetails, String formmode, String userId) {

		String msg = "";
		int count = btmProjectMasterRep.getprojectCount(btmProjectMaster.getProj_id(), btmProjectMaster.getProj_name());

		if (count != 0) {
			msg = "already Exsist";
		} else {

			if (formmode.equals("add")) {

				BTMProjectMaster up = btmProjectMaster;

				up.setEntry_time(new Date());
				up.setEntry_user(userId);
				up.setEntity_flg("Y");
				up.setDel_flg("N");
				up.setRemarks("Pending");

				btmProjectMasterRep.save(up);
				ProjectDetails up1 = projectDetails;
				up1.setProj_id(up.getProj_id());
				up1.setClient_id(up.getClient_id());
				up1.setRemarks(up.getRemarks());
				up1.setEntity_flg("Y");
				up1.setDel_flg("N");

				btmProjectDetailsRep.save(up1);
				ProjectTeamDetails up2 = projectTeamDetails;

				up2.setClient_id(up.getClient_id());
				up2.setProj_id(up.getProj_id());
				up2.setRemarks(up.getRemarks());
				up2.setEntity_flg("Y");
				up2.setDel_flg("N");

				btmProjectTeamDetailsRep.save(up2);

				msg = "Added Successfully";

			}
		}

		if (formmode.equals("edit")) {

			BTMProjectMaster up = btmProjectMaster;
			up.setRemarks(up.getRemarks());
			up.setEntity_flg("Y");
			up.setModify_flg("Y");
			up.setModify_user(userId);
			up.setModify_time(new Date());
			up.setDel_flg("N");

			btmProjectMasterRep.save(up);

			ProjectDetails up1 = projectDetails;

			up1.setProj_id(up.getProj_id());
			up1.setClient_id(up.getClient_id());
			up1.setEntity_flg("Y");
			up1.setModify_flg("Y");
			up1.setDel_flg("N");

			btmProjectDetailsRep.save(up1);

			ProjectTeamDetails up2 = projectTeamDetails;

			up2.setClient_id(up.getClient_id());
			up2.setProj_id(up.getProj_id());
			up2.setEntity_flg("Y");
			up2.setModify_flg("Y");
			up2.setDel_flg("N");

			btmProjectTeamDetailsRep.save(up2);

			msg = "Edited Successfully";

		} else if (formmode.equals("delete")) {

			BTMProjectMaster up = btmProjectMaster;
			up.setEntity_flg("N");
			up.setDel_flg("Y");

			btmProjectMasterRep.save(up);

			ProjectDetails up1 = projectDetails;
			up1.setEntity_flg("N");
			up1.setDel_flg("Y");

			btmProjectDetailsRep.save(up1);

			ProjectTeamDetails up2 = projectTeamDetails;
			up2.setEntity_flg("N");
			up2.setDel_flg("Y");

			btmProjectTeamDetailsRep.save(up2);

			msg = "Deleted Successfully";
		}

		return msg;

	}

	// training
	public String addtrainings(String formmode, String adm_id, String adm_name, String trainee_name,
			String trainee_dept, String TrainingName, String TrainingDescription, String startdate, String enddate,
			String emp_id, String emp_name, String emp_email, String emp_role, String train_flg, byte[] byteArray,
			String name, String type) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Session session = sessionFactory.getCurrentSession();
		String msg = "";
		if (formmode.equals("add")) {
			Training up = new Training();
			BigDecimal srl_no_bd = (BigDecimal) session
					.createNativeQuery("SELECT TRAINING_id.NEXTVAL AS SRL_NO FROM DUAL").getSingleResult();
			Long srl_no = srl_no_bd.longValue(); // Convert BigDecimal to Long
			up.setSrlNo(srl_no);
			up.setAdminId(adm_id);
			up.setAdminName(adm_name);
			up.setTraineeName(trainee_name);
			up.setTraineeDept(trainee_dept);
			up.setTrainName(TrainingName);
			up.setTrainDesc(TrainingDescription);
			up.setStartDate(formatter.parse(startdate));
			up.setEndDate(formatter.parse(enddate));
			up.setEmpId(emp_id);
			up.setEmpName(emp_name);
			up.setEmpEmail(emp_email);
			up.setEmpRole(emp_role);
			up.setDocument(byteArray);
			up.setFileName(name);
			up.setFileType(type);
			Training_Rep.save(up);
			msg = "ADD successfully";
		}
		return msg;

	}

//GET PROJECT MASTER
	public BTMProjectMaster getProjectManager(String resId) {

		if (btmProjectMasterRep.existsById(resId)) {
			BTMProjectMaster up = btmProjectMasterRep.findById(resId).get();
			return up;
		} else {
			return new BTMProjectMaster();
		}
	}

	public BTMTravelMaster getTravelMaster(String resId) {

		if (BTMtravelMasterRep.existsById(resId)) {
			BTMTravelMaster up = BTMtravelMasterRep.findById(resId).get();
			return up;
		} else {
			return new BTMTravelMaster();
		}
	}

	// Work Assignment
	public BTMWorkAssignment getWorkAssignmentMaster(String resId) {

		if (btmProjectMasterRep.existsById(resId)) {
			BTMWorkAssignment up = btmWorkAssignmentRep.findById(resId).get();
			return up;
		} else {
			return new BTMWorkAssignment();
		}
	}

	// =============== Admin RefCode master =========================

	public BTMRefCodeMaster getRefMaster(String id) {

		if (btmRefCodeMasterRep.existsById(id)) {
			BTMRefCodeMaster up = btmRefCodeMasterRep.findById(id).get();
			return up;
		} else {
			return new BTMRefCodeMaster();
		}
	}

	public String addRefCodeMaster(BTMRefCodeMaster btmRefCodeMaster, String formmode, String ref_id) {

		String msg = "";

		if (formmode.equals("add")) {

			BTMRefCodeMaster up = btmRefCodeMaster;

			up.setEntity_flg("Y");

			up.setDel_flg("N");

			btmRefCodeMasterRep.save(up);

			msg = "Added Successfully";

		} else if (formmode.equals("edit")) {

			BTMRefCodeMaster up = btmRefCodeMasterRep.getRefMaster(ref_id);
			up.setRef_type_desc(btmRefCodeMaster.getRef_type_desc());
			up.setRef_id_desc(btmRefCodeMaster.getRef_id_desc());
			up.setRemarks(btmRefCodeMaster.getRemarks());
			up.setModule_id(btmRefCodeMaster.getModule_id());
			up.setRef_type(btmRefCodeMaster.getRef_type());
			BTMRefCodeMaster user = btmRefCodeMasterRep.findById(up.getRef_id()).get();

			if (btmRefCodeMaster.getRef_type().equals(user.getRef_type())
					&& btmRefCodeMaster.getRef_type_desc().equals(user.getRef_type_desc())
					&& btmRefCodeMaster.getRef_id_desc().equals(user.getRef_id_desc())
					&& btmRefCodeMaster.getModule_id().equals(user.getModule_id())
					&& btmRefCodeMaster.getRemarks().equals(user.getRemarks())) {
				msg = "No Modification More";
			} else {

				btmRefCodeMasterRep.save(up);

				msg = "Edited Successfully";
			}

		} else if (formmode.equals("delete")) {

			BTMRefCodeMaster up = btmRefCodeMaster;

			up.setEntity_flg("Y");

			up.setDel_flg("Y");

			btmRefCodeMasterRep.save(up);

			msg = "Deleted Successfully";
		}

		return msg;
	}

// =========================== Admin Doc Master =======================================

	public String addDocumentUser(BTMDocumentMaster btmDocumentMaster, String formmode) {

		String msg = "";

		if (formmode.equals("add")) {

			BTMDocumentMaster up = btmDocumentMaster;

			up.setEntity_flg("Y");

			up.setDel_flg("N");

			btmDocumentMasterRep.save(up);

			msg = "Added Successfully";

		} else if (formmode.equals("edit")) {
			BTMDocumentMaster user = btmDocumentMasterRep.findById(btmDocumentMaster.getDoc_ref_no()).get();

			if (btmDocumentMaster.getDoc_id().equals(user.getDoc_id())
					&& btmDocumentMaster.getDoc_name().equals(user.getDoc_name())
					&& btmDocumentMaster.getDoc_desc().equals(user.getDoc_desc())
					&& btmDocumentMaster.getDoc_type().equals(user.getDoc_type())
					&& btmDocumentMaster.getDoc_group().equals(user.getDoc_group())
					&& btmDocumentMaster.getAccess_type().equals(user.getAccess_type())
					&& btmDocumentMaster.getAccess_group().equals(user.getAccess_group())
					&& btmDocumentMaster.getDoc_uploader().equals(user.getDoc_uploader())
					&& btmDocumentMaster.getDoc_approver().equals(user.getDoc_approver())
					&& btmDocumentMaster.getDoc_verifier().equals(user.getDoc_verifier())
					&& btmDocumentMaster.getDoc_owner().equals(user.getDoc_owner())) {
				msg = "No Modification More";
			} else {
				BTMDocumentMaster up = btmDocumentMaster;

				up.setEntity_flg("Y");

				up.setDel_flg("N");

				btmDocumentMasterRep.save(up);

				msg = "Edited Successfully";
			}

		}

		return msg;
	}

	// get calendar list

	public List<BTMAdminCalendarMaster> getCalendarlist() {

		List<BTMAdminCalendarMaster> btm_cal = new ArrayList<>();
		List<Object[]> list_obj = btmAdminCalendarMasterRep.findBysrl();

		for (Object[] obj : list_obj) {

			BTMAdminCalendarMaster cal = new BTMAdminCalendarMaster();

			cal.setYear(String.valueOf(obj[0]));
			cal.setMonth(String.valueOf(obj[1]));

			btm_cal.add(cal);

		}
		return btm_cal;
	}

	// get monthly holiday list from HolidayMaster

	public List<BTMAdminHolidayMaster> getMonthlyHolidaylist(String year, String month) throws ParseException {

		List<BTMAdminHolidayMaster> hol_des = new ArrayList<>();
		List<Object[]> list_obj = btmAdminHolidayMasterRep.getMonthlyHolidaylists(year, month);

		for (Object[] obj : list_obj) {

			BTMAdminHolidayMaster holiday = new BTMAdminHolidayMaster();

			holiday.setCal_year(String.valueOf(obj[0]));
			holiday.setCal_month(String.valueOf(obj[1]));
			holiday.setRecord_date(new SimpleDateFormat("dd-MM-yyyy").parse(String.valueOf(obj[2])));
			// holiday.setDay(String.valueOf(obj[3]));
			holiday.setHoliday_desc(String.valueOf(obj[4]));
			holiday.setHoliday_remarks(String.valueOf(obj[5]));

			hol_des.add(holiday);

		}
		return hol_des;
	}

	public List<Object[]> getDayBillingInquiries(String payDate) {

		String cn1 = "";
		if (!payDate.equals("")) {
			// FOR PAYMENT DATE
			cn1 = " PAYMENT_DATE = '" + payDate + "' ";
		}

		System.out.println(cn1);
		@SuppressWarnings("unchecked")
		List<Object[]> list = (List<Object[]>) sessionFactory.getCurrentSession().createQuery(
				" SELECT payment_date , SUM (amount) + SUM (scan_amount) + SUM(blood_test_amount) AS amount FROM BHMSPaymentTable  WHERE "
						+ cn1 + "GROUP BY  payment_date")
				.getResultList();

		// System.out.println(Arrays.toString(list));

		return list;
	}

	public List<BhmsInvProductSaleMasterTable> getSaleList(String date_of_sale) {

		List<Object[]> list = bhmsInvProductSaleMasterrep.findAllCustom(date_of_sale);

		List<BhmsInvProductSaleMasterTable> listData = new ArrayList<BhmsInvProductSaleMasterTable>();

		for (Object[] item : list) {
			BhmsInvProductSaleMasterTable itemData = new BhmsInvProductSaleMasterTable();
			itemData.setSale_id(item[0].toString());
			itemData.setCust_name(item[1].toString());
			itemData.setDate_of_sale((Date) item[2]);
			itemData.setTotal((BigDecimal) item[3]);
			listData.add(itemData);

		}
		List<BigDecimal> arr = new ArrayList<>();
		for (BhmsInvProductSaleMasterTable item : listData) {

			arr.add(item.getTotal());

		}

		BigDecimal result = arr.stream().reduce(BigDecimal.ZERO, BigDecimal::add);

		return listData;
	}

	public Chart_Acc_Entity getGeneralLedger(String acct_num) {

		/*
		 * if (generalLedgerRep.existsById(id)) { GeneralLedgerEntity up =
		 * generalLedgerRep.findById(id).get(); return up; } else { return new
		 * GeneralLedgerEntity(); }
		 */
		return chart_Acc_Rep.getaedit(acct_num);
	}

	@Autowired
	TSK_PROCESS_REP TSK_PROCESS_REPS;

	@Transactional
	public ResponseEntity<String> add_process_tsk(List<TSK_PROCESS_ENTITY> TSK_PROCESS_ENTITY, String formmode) {
		try {
			if ("add".equalsIgnoreCase(formmode)) {
				Session session = sessionFactory.getCurrentSession();

				// Fetch the next value for HRMS.TSK_ID
				BigInteger process_id = (BigInteger) session
						.createNativeQuery("SELECT NEXT VALUE FOR TSK_PROCESS_ID AS process_id").getSingleResult();

				for (TSK_PROCESS_ENTITY param : TSK_PROCESS_ENTITY) {
					// Fetch the next value for HRMS.TSK_PROCESS_ID
					BigInteger id = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR TSK_ID AS id")
							.getSingleResult();

					// Set values
					param.setId(id.toString()); // Convert BigInteger to String
					param.setProcessId("PRS00" + process_id.toString());
					param.setStatus("work in progress");
					param.setProcessStartDate(new Date());
					System.out.println("The qty is :" + param.getQuantity());
					System.out.println("The ExpensesType is :" + param.getExpensestype());

					PO_invoice_entity up = PO_invoice_reps.get_twos(param.getBatch());
					if (up != null && up.getBatch() != null && !up.getBatch().isEmpty()) { // Ensure 'up' is not null
																							// and batch is valid
						if (up.getUseingqty() != null && up.getUseingqty() > 0) { // Check for non-null and non-zero
							try {
								// Convert Integer to BigDecimal for calculation
								BigDecimal usingQty = BigDecimal.valueOf(up.getUseingqty());
								BigDecimal reqQty = param.getReqqnt(); // Assuming this is already BigDecimal
								BigDecimal newvalue = usingQty.subtract(reqQty); // Subtract using BigDecimal

								// Update 'Useingqty' as an Integer (if necessary)
								up.setUseingqty(newvalue.intValueExact()); // Use `intValueExact` for precise conversion
								PO_invoice_reps.save(up);
							} catch (ArithmeticException e) {
								System.err.println("Error during conversion: " + e.getMessage());
							}
						}
					}

				}

				// Save all entities in batch
				TSK_PROCESS_REPS.saveAll(TSK_PROCESS_ENTITY);

				return ResponseEntity.ok("Process PRS00" + process_id.toString() + " added successfully.");
			}
			return ResponseEntity.badRequest().body("Invalid form mode.");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok("Process add unsuccessful: " + e.getMessage());
		}
	}

	/*------userprofile----*/

	public String addUserhrms(HRMS_USER_PROFILE_ENTITY userform, String formmode) {
		String msg = "";

		// When adding a new user
		if (formmode.equals("add")) {
			try {
				// Set up a session
				HRMS_USER_PROFILE_ENTITY up = userform;
				Session hs = sessionFactory.getCurrentSession();
				// Encrypt the password before saving
				String encryptedPassword = AES.encrypt("bornfire123"); // Default password
				up.setPassword(encryptedPassword);

				// Set flags for the new user
				up.setDelFlg("N");
				up.setEntityFlg("Y");
				up.setLoginFlg("N");// Save or update the user profile
				hs.saveOrUpdate(up);

				/*----associateprofile--*/
				Optional<BTMAdminAssociateProfile> associate = btmAdminAssociateProfileRep.findById(up.getUserId());
				if (associate.isPresent()) {
					BTMAdminAssociateProfile savedfunction = associate.get();
					savedfunction.setUsedFlag("Y");
					btmAdminAssociateProfileRep.save(savedfunction);
				}

				msg = "User Created Successfully";
			} catch (Exception e) {
				e.printStackTrace();
				msg = "Error creating user: " + e.getMessage();
			}
		}

		/*-----modifyuser---*/

		if (formmode.equals("modify")) {
			try {
				// Set up a session
				HRMS_USER_PROFILE_ENTITY up = userform;
				Session hs = sessionFactory.getCurrentSession();

				// Encrypt the password before saving
				// String encryptedPassword = AES.encrypt("bornfire123"); // Default password

				String password = up.getPassword();

				up.setPassword(password);

				// Set flags for the new user
				up.setDelFlg("N");
				up.setEntityFlg("Y");
				up.setLoginFlg("N");

				// Save or update the user profile
				hs.saveOrUpdate(up);

				msg = "User Modified Successfully";

			} catch (Exception e) {
				e.printStackTrace();
				msg = "Error creating user: " + e.getMessage();
			}
		}

		return msg;

	}
	
	@Autowired
	Branch_rep Branch_reps;

	public String add_branch(Branch_Entity branchEntity, String formmode) {
	    String msg = "";

	    if ("add".equals(formmode)) {
	        if (Branch_reps.existsById(branchEntity.getBranchId())) {
	            msg = "Branch already exists";
	        } else {
	            Branch_reps.save(branchEntity);
	            msg = "Branch Successfully Added";
	        }
	    } else if ("edit".equals(formmode)) {
	        if (Branch_reps.existsById(branchEntity.getBranchId())) {
	            Branch_reps.save(branchEntity);
	            msg = "Branch Modified Successfully";
	        } else {
	            msg = "Branch not found for editing";
	        }
	    } else {
	        msg = "Invalid Operation";
	    }

	    return msg;
	}
	// ==================================== TSK Branch Master ========================================

	public String TSK_branch(TSK_branchEntity TSK_branchEntity, String formmode,String OrgId) {

		String msg = "";
		Session session = sessionFactory.getCurrentSession();

		if (formmode.equals("add")) {
			TSK_branchEntity up = TSK_branchEntity;
			    BigInteger bigIntId = (BigInteger) session
			    .createNativeQuery("SELECT NEXT VALUE FOR TSK_BRANCH_ID").getSingleResult();
			    up.setBranchId("BR00"+bigIntId.toString());
					up.setDelFlg("N");
					TSK_branch_Reps.save(up);


			msg = "Branch ID : "+"BR00"+bigIntId.toString()+ " Added Successfully";

		
		}else if (formmode.equals("edit")) {
		    System.out.println("id is:" + TSK_branchEntity.getBranchId());

		    TSK_branchEntity existing = TSK_branch_Reps.find_id(TSK_branchEntity.getBranchId());
		    if (existing != null) {
		        existing.setOrg_id(OrgId);
		        existing.setOrg_name(TSK_branchEntity.getOrg_name());
		        existing.setBranchName(TSK_branchEntity.getBranchName());
		        existing.setLandline1(TSK_branchEntity.getLandline1());
		        existing.setLandline2(TSK_branchEntity.getLandline2());
		        existing.setMobileNo(TSK_branchEntity.getMobileNo());
		        existing.setAlternateMobileNo(TSK_branchEntity.getAlternateMobileNo());
		        existing.setAddressLine1(TSK_branchEntity.getAddressLine1());
		        existing.setAddressLine2(TSK_branchEntity.getAddressLine2());
		        existing.setCity(TSK_branchEntity.getCity());
		        existing.setState(TSK_branchEntity.getState());
		        existing.setCountry(TSK_branchEntity.getCountry());
		        existing.setPostalCode(TSK_branchEntity.getPostalCode());
		        existing.setModifyUser(TSK_branchEntity.getModifyUser());
		        // other fields if needed

		        TSK_branch_Reps.save(existing);
		        msg = "Branch Modified Successfully";
		    } else {
		        msg = "Branch not found!";
		    }
		}

		return msg;
	
	}

	public ResponseEntity<String> add_boilprocess_tsk(List<TSK_PROCESS_ENTITY> TSK_PROCESS_ENTITY, String formmode) {
		try {
			if ("add".equalsIgnoreCase(formmode)) {
				Session session = sessionFactory.getCurrentSession();

				// Fetch the next value for HRMS.TSK_ID
				BigInteger process_id = (BigInteger) session
						.createNativeQuery("SELECT NEXT VALUE FOR TSK_PROCESS_ID AS process_id").getSingleResult();

				for (TSK_PROCESS_ENTITY param : TSK_PROCESS_ENTITY) {
					// Fetch the next value for HRMS.TSK_PROCESS_ID
					BigInteger id = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR TSK_ID AS id")
							.getSingleResult();

					// Set values
					param.setId(id.toString()); // Convert BigInteger to String
					param.setProcessId("PRS00" + process_id.toString());
					param.setStatus("work in progress");
					param.setProcessStartDate(new Date());
					System.out.println("The qty is :" + param.getQuantity());
					System.out.println("The ExpensesType is :" + param.getExpensestype());
					
					if(param.getCategory_name1()!=null) {
						if(param.getCategory_name1().equals("B01A")) {
							PO_invoice_entity up = PO_invoice_reps.get_twos(param.getBatch());
							if (up != null && up.getBatch() != null && !up.getBatch().isEmpty()) { // Ensure 'up' is not null
																									// and batch is valid
								if (up.getUseingqty() != null && up.getUseingqty() > 0) { // Check for non-null and non-zero
									try {
										// Convert Integer to BigDecimal for calculation
										BigDecimal usingQty = BigDecimal.valueOf(up.getUseingqty());
										BigDecimal reqQty = param.getReqqnt(); // Assuming this is already BigDecimal
										BigDecimal newvalue = usingQty.subtract(reqQty); // Subtract using BigDecimal

										// Update 'Useingqty' as an Integer (if necessary)
										up.setUseingqty(newvalue.intValueExact()); // Use `intValueExact` for precise conversion
										PO_invoice_reps.save(up);
									} catch (ArithmeticException e) {
										System.err.println("Error during conversion: " + e.getMessage());
									}
								}
							}
						}
						else if(param.getCategory_name1().equals("B01D")) {
							FINISHED_GOODS_ENTITY fg=FINISHED_GOODS_Rep.get_listbyFG_ID(param.getBatch());
		                    BigDecimal fgqty=fg.getUsingQuantity().subtract(param.getReqqnt());
		                    fg.setUsingQuantity(fgqty);
		                    FINISHED_GOODS_Rep.save(fg);
							
						}else if(param.getCategory_name1().equals("B01C")) {
							List<TSK_PROCESS_ENTITY> up=TSK_PROCESS_REPS.getprocessbyid(param.getBatch());
				            for (TSK_PROCESS_ENTITY up1:up) {
				           
				            		up1.setUseingquantity(up1.getReturnquantity().subtract(param.getReqqnt()));
				            		TSK_PROCESS_REPS.save(up1);
				            }
						}
						
					}

					
										

				}

				// Save all entities in batch
				TSK_PROCESS_REPS.saveAll(TSK_PROCESS_ENTITY);
				
				
					//tran
					BigInteger journalid =  (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalentrieid AS id")
			 				.getSingleResult();
	
		         if (journalid == null) {
		             throw new IllegalStateException("Failed to generate a transaction ID. Ensure the sequence exists and is configured correctly.");
		         }
		         String sid=String.valueOf(journalid);
	             int count=1;
	             Date currentdate = new Date();


				
				for(TSK_PROCESS_ENTITY param : TSK_PROCESS_ENTITY) {
					//for rawmaterial and finished goods
					if(param.getCategory_name1().equals("B01A") || param.getCategory_name1().equals("B01D")) {
						 Erp_ChartOfAccounts account =Erp_ChartOfAccountsRep.findsbyowner(param.getItemCode());
		            	 CapitalTrans trantab= new CapitalTrans();
		            	 BigInteger id = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
									.getSingleResult();
		            	 trantab.setId(id.toString());
		            	 trantab.setJournalDate(currentdate);
		            	 trantab.setJournalTranId("TRAN000"+sid);
		            	 trantab.setPartTranId(count);
		            	 trantab.setFullAccount(account.getAccountNumber()+"-"+account.getAccountName());
		            	 trantab.setAccountNumber(account.getAccountNumber());
		            	 trantab.setAccountName(account.getAccountName());
		            	 trantab.setDescription("PRS00" + process_id.toString());
		            	 trantab.setDebits(BigDecimal.ZERO);
		            	 trantab.setCredits(param.getTotalprice());
		            	 trantab.setTran_particulars("Work In Process");
		            	 count++;
		            	 CapitalTransRep.save(trantab);
		            	 //updateing amount
		            	 account.setTotalCreditBalance(account.getTotalCreditBalance().add(param.getTotalprice()));
		            	 account.setAccountBalance(account.getAccountBalance().subtract(param.getTotalprice()));
			             Erp_ChartOfAccountsRep.save(account);
			             
			             String parentaccount = account.getParentaccount();

		            	 while (parentaccount != null && !parentaccount.isEmpty()) {
		            	     Erp_ChartOfAccounts parentchartaccount1 = Erp_ChartOfAccountsRep.findsccount(parentaccount);
		            	     
		            	     if (parentchartaccount1 != null) {
		            	         
		            	    	 parentchartaccount1.setTotalCreditBalance(parentchartaccount1.getTotalCreditBalance().add(param.getTotalprice()));
		            	    	 parentchartaccount1.setAccountBalance(parentchartaccount1.getAccountBalance().subtract(param.getTotalprice()));
		            	         Erp_ChartOfAccountsRep.save(parentchartaccount1);
		            	         parentaccount = parentchartaccount1.getParentaccount();
		            	     } else {
		            	         break; 
		            	     }
		            	 }
		            	 
		            	 //for semi finished goods
					}else if(param.getCategory_name1().equals("B01C")) {
						Erp_ChartOfAccounts account =Erp_ChartOfAccountsRep.findsccount("ACC00020");
		            	 CapitalTrans trantab= new CapitalTrans();
		            	 BigInteger id = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
									.getSingleResult();
		            	 trantab.setId(id.toString());
		            	 trantab.setJournalDate(currentdate);
		            	 trantab.setJournalTranId("TRAN000"+sid);
		            	 trantab.setPartTranId(count);
		            	 trantab.setFullAccount(account.getAccountNumber()+"-"+account.getAccountName());
		            	 trantab.setAccountNumber(account.getAccountNumber());
		            	 trantab.setAccountName(account.getAccountName());
		            	 trantab.setDescription("PRS00" + process_id.toString());
		            	 trantab.setDebits(BigDecimal.ZERO);
		            	 trantab.setCredits(param.getTotalprice());
		            	 trantab.setTran_particulars("Work In Process");
		            	 count++;
		            	 CapitalTransRep.save(trantab);
		            	 //updateing amount
		            	 account.setTotalCreditBalance(account.getTotalCreditBalance().add(param.getTotalprice()));
		            	 account.setAccountBalance(account.getAccountBalance().subtract(param.getTotalprice()));
			             Erp_ChartOfAccountsRep.save(account);
			             
			             String parentaccount = account.getParentaccount();

		            	 while (parentaccount != null && !parentaccount.isEmpty()) {
		            	     Erp_ChartOfAccounts parentchartaccount1 = Erp_ChartOfAccountsRep.findsccount(parentaccount);
		            	     
		            	     if (parentchartaccount1 != null) {
		            	         
		            	    	 parentchartaccount1.setTotalCreditBalance(parentchartaccount1.getTotalCreditBalance().add(param.getTotalprice()));
		            	    	 parentchartaccount1.setAccountBalance(parentchartaccount1.getAccountBalance().subtract(param.getTotalprice()));
		            	         Erp_ChartOfAccountsRep.save(parentchartaccount1);
		            	         parentaccount = parentchartaccount1.getParentaccount();
		            	     } else {
		            	         break; 
		            	     }
					}
							         
		             
	            	

				}
				}
				
			BigInteger id = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
						.getSingleResult();
             Erp_ChartOfAccounts account =Erp_ChartOfAccountsRep.findsccount("ACC000101");
             CapitalTrans trantab= new CapitalTrans();
             
        	 trantab.setId(id.toString());
        	 trantab.setJournalDate(currentdate);
        	 
        	 trantab.setJournalTranId("TRAN000"+sid);
        	 trantab.setPartTranId(count);
        	 trantab.setFullAccount(account.getAccountNumber()+"-"+account.getAccountName());
        	 trantab.setAccountNumber(account.getAccountNumber());
        	 trantab.setAccountName(account.getAccountName());
        	 trantab.setDescription(TSK_PROCESS_ENTITY.get(0).getProcessId());
        	 trantab.setCredits(BigDecimal.ZERO);
        	 trantab.setDebits(TSK_PROCESS_ENTITY.get(0).getMaterialtotalvalue().add(TSK_PROCESS_ENTITY.get(0).getExpensesgrandtotal()));
        	 trantab.setTran_particulars("Inventories");
        	 count++;
        	 CapitalTransRep.save(trantab);
        	 account.setTotalDebitBalance(account.getTotalDebitBalance().add(TSK_PROCESS_ENTITY.get(0).getMaterialtotalvalue().add(TSK_PROCESS_ENTITY.get(0).getExpensesgrandtotal())));
        	 account.setAccountBalance(account.getAccountBalance().add(TSK_PROCESS_ENTITY.get(0).getMaterialtotalvalue().add(TSK_PROCESS_ENTITY.get(0).getExpensesgrandtotal())));
        	 Erp_ChartOfAccountsRep.save(account);
        	 String parentaccount = account.getParentaccount();

        	 while (parentaccount != null && !parentaccount.isEmpty()) {
        	     Erp_ChartOfAccounts parentchartaccount1 = Erp_ChartOfAccountsRep.findsccount(parentaccount);
        	     
        	     if (parentchartaccount1 != null) {
        	         
        	         parentchartaccount1.setTotalCreditBalance(parentchartaccount1.getTotalCreditBalance().add(TSK_PROCESS_ENTITY.get(0).getMaterialtotalvalue().add(TSK_PROCESS_ENTITY.get(0).getExpensesgrandtotal())));
        	         parentchartaccount1.setAccountBalance(parentchartaccount1.getAccountBalance().add(TSK_PROCESS_ENTITY.get(0).getMaterialtotalvalue().add(TSK_PROCESS_ENTITY.get(0).getExpensesgrandtotal())));
        	         Erp_ChartOfAccountsRep.save(parentchartaccount1);
        	         parentaccount = parentchartaccount1.getParentaccount();
        	     } else {
        	         break; 
        	     }
        	 }
				

				return ResponseEntity.ok("Process PRS00" + process_id.toString() + " added successfully.");
			}
			return ResponseEntity.badRequest().body("Invalid form mode.");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok("Process add unsuccessful: " + e.getMessage());
		}
	}

}
