package com.bornfire.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;
import javax.validation.constraints.NotNull;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import com.bornfire.config.AES;
 
import com.bornfire.config.PasswordEncryption;
import com.bornfire.entities.BTMSession;
import com.bornfire.entities.FinUserProfile;
import com.bornfire.entities.FinUserProfileRep;
import com.bornfire.entities.HRMS_USER_PROFILE_ENTITY;
import com.bornfire.entities.HRMS_USER_PROFILE_REPOSITORY;
import com.bornfire.entities.ResourceMaster;
import com.bornfire.entities.ResourceMasterRepo;
 

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Service
@ConfigurationProperties("output")
@Transactional
public class LoginServices {

	private static final Logger logger = LoggerFactory.getLogger(LoginServices.class);
 
	@Autowired
	FinUserProfileRep finUserProfileRep;

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DataSource srcdataSource;
	
	@Autowired
	ResourceMasterRepo resourceMasterRepo;
	
	
	@Autowired
	HRMS_USER_PROFILE_REPOSITORY hrmsrepoo;

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

	/*
	 * Getting 3 inputs -
	 * 
	 * UserProfile Object, Formmode - Valid values : add, edit, inputuser - user who
	 * edited the data
	 * 
	 * if formmode is add - Get password from application.properties create the user
	 * 
	 * if formmode is edit - Get password from database for that user and use other
	 * fields came from front end.
	 * 
	 * 
	 */


	public int checkAcctexpirty(String userid) {

		Optional<ResourceMaster> up = resourceMasterRepo.findById(userid);
		//Date expDate = up.get().getAcc_exp_date();
		Date expDate = up.get().getAcct_expy_date();
		Date currDate = new Date();

		DateTime dt1 = new DateTime(currDate);
		DateTime dt2 = new DateTime(expDate);

		int remaindays = Days.daysBetween(dt1, dt2).getDays();

		logger.info("Account Expired in:" + remaindays);
		return remaindays;
	}

	public int checkpassexpirty(String userid) {

		Optional<ResourceMaster> up = resourceMasterRepo.findById(userid);
		Date expDate = up.get().getPw_expy_date();
		Date currDate = new Date();

		DateTime dt1 = new DateTime(currDate);
		DateTime dt2 = new DateTime(expDate);

		int remaindays = Days.daysBetween(dt1, dt2).getDays();

		logger.info("Password Expired in:" + remaindays);
		return remaindays;
	}

//Password Change Functionality from associate profile 

	public String changePassword(String oldpass, String newpass, String userid) {
	    System.out.println("SERVICE");
	    System.out.println("OLD PASSWORD: " + oldpass);
	    System.out.println("NEW PASSWORD: " + newpass);
	    System.out.println("THE USER ID: " + userid);

	    String msg = "";
	   // Optional<ResourceMaster> up = resourceMasterRepo.findById(userid);
	    Optional<HRMS_USER_PROFILE_ENTITY> up = hrmsrepoo.findById(userid);

	    if (up.isPresent()) {
	        String decriptpass = up.get().getPassword();
	        
	        if (decriptpass == null || decriptpass.isEmpty()) {
	            // If the password from the database is null or empty
	            msg = "Error: Password not found for the user.";
	            return msg;
	        }

	        String finaldecript = null; // Declare finaldecript here to be used later

	        try {
	            finaldecript = AES.decrypt(decriptpass);  // Decrypt the password
	            System.out.println("THE PASSWORD DECRYPTED FROM DATABASE: " + finaldecript);
	        } catch (Exception e) {
	            e.printStackTrace();
	            msg = "Error decrypting the password: " + e.getMessage();
	            return msg; // Return early if there is an issue with decryption
	        }

	        String encrypted_password = null;
	        try {
	            encrypted_password = AES.encrypt(oldpass);  // Encrypt old password to compare
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        HRMS_USER_PROFILE_ENTITY user = up.get();

	        if (encrypted_password.equals(user.getPassword())) {
	            if (!oldpass.equals(newpass)) {  // Ensure new password is not same as old one
	                String encryptedPassword = null;
	                try {
	                    encryptedPassword = AES.encrypt(newpass);  // Encrypt new password
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }

	                System.out.println("New encrypted password: " + encryptedPassword);

	                user.setPassword(encryptedPassword);

	                // Set expiration date for the password
	                final Calendar cal = Calendar.getInstance();
	                cal.add(Calendar.MONTH, 1);
	                System.out.println("Password expiration date: " + cal.getTime());
	                user.setPassExpDate(cal.getTime());

	                hrmsrepoo.save(user);  // Save the user with new password

	                msg = "Password Changed Successfully";
	            } else {
	                msg = "New password cannot be the same as the Old password";
	            }
	        } else {
	            msg = "Incorrect Old Password!";
	        }
	    } else {
	        msg = "User not found!";
	    }

	    return msg;
	}

	
	//Password Change Functionality from userprofile

		public String changePasswordfromUP(String oldpass, String newpass, String userid) {

			String msg = "";
			Optional<HRMS_USER_PROFILE_ENTITY> up = hrmsrepoo.findById(userid);
			String encrypted_password =null;
			try {
				 encrypted_password =	AES.encrypt(oldpass);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			HRMS_USER_PROFILE_ENTITY user = up.get();
			
	if(encrypted_password.equals(user.getPassword())) {
		if (up.isPresent()) {

			
				//	if (PasswordEncryption.validatePassword(encrypted_password, user.getPassword())) {

					//	if (!PasswordEncryption.validatePassword(newpass, user.getPassword())) {

							String encryptedPassword = null;
							try {
								encryptedPassword = AES.encrypt(newpass);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.println(encryptedPassword+"new one");

							user.setPassword(encryptedPassword);
						//	user.setDel_flg("Y");

							final Calendar cal = Calendar.getInstance();
							cal.add(Calendar.MONTH,1);
							System.out.println(cal.getTime());
							user.setPassExpDate(cal.getTime());
									
							hrmsrepoo.save(user);

							msg = "Password Changed Successfully";

						} else {

							msg = "New password cannot be Same as Old password";
						}

					} else {
						msg = "Incorrect Old Password!";
					}
				
			
			return msg;
		};
	
	
	
	
	
	
	

	public void SessionLogging(String menuname, String menuid, String sessionid, String userid, String ip,
			String status) {
		Session hs = sessionFactory.getCurrentSession();

		try {

			if (menuname.equals("LOGOUT")) {

				hs.createQuery("update XBRLSession set status='IN-ACTIVE' where session_id = ?1")
						.setParameter(1, sessionid).executeUpdate();

			} else {

				hs.save(new BTMSession(menuname, menuid, sessionid, userid, ip, new Date(), status));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public File getUserLogFile(Date fromdate, Date todate) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

		String path = exportpath;
		String fileName = "USER_LOGS_" + dateFormat.format(new Date()) + ".xlsx";
		File outputFile;

		File jasperFile;

		File folders = new File(path);
		if (!folders.exists()) {
			folders.mkdirs();
		}

		try {
			jasperFile = ResourceUtils.getFile("classpath:static/jasper/USER_LOGS/UserLogs.jasper");
			JasperReport jr = (JasperReport) JRLoader.loadObject(jasperFile);
			HashMap<String, Object> map = new HashMap<String, Object>();

			logger.info("Assigning Parameters for Jasper");
			map.put("FromDate", dateFormat.format(fromdate));
			map.put("ToDate", dateFormat.format(todate));

			path = path + "/" + fileName;
			JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
			JRXlsxExporter exporter = new JRXlsxExporter();
			exporter.setExporterInput(new SimpleExporterInput(jp));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
			exporter.exportReport();
			logger.info("Excel File exported");

		} catch (FileNotFoundException | JRException | SQLException e) {

			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;
	}

	public List<BTMSession> getUserLog(Date fromdate, Date todate) {

		Session hs = sessionFactory.getCurrentSession();

		List<BTMSession> ls = hs.createQuery(
				"from XBRLSession where trunc(entry_time,'DD') between ?1 and ?2 and menu in ('LOGIN','LOGOUT') order by entry_time desc ",
				BTMSession.class).setParameter(1, fromdate).setParameter(2, todate).getResultList();

		return ls;
	}

}
