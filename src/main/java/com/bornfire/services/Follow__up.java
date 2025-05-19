package com.bornfire.services;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bornfire.config.AES;
import com.bornfire.entities.BTMAdminAssociateProfile;
import com.bornfire.entities.Follow_Up_Entity;
import com.bornfire.entities.Follow_Up_Rep;
import com.bornfire.entities.HRMS_USER_PROFILE_ENTITY;
import com.bornfire.entities.Notify_Entity;
import com.bornfire.entities.Notify_Entity_Rep;
import com.bornfire.entities.OFFER_ALERT_ENTITY;
import com.bornfire.entities.OFFER_ALERT_REP;
import com.bornfire.entities.SALES_invoice_TABLERep;
import com.bornfire.entities.VendorCreation;
import com.bornfire.entities.VendorCreationRep;

@Service
public class Follow__up {

    private static final Logger logger = LoggerFactory.getLogger(Follow__up.class);
    @Autowired
    OFFER_ALERT_REP OFFER_ALERT_REPs;
    @Autowired
    private Follow_Up_Rep followUpReps;
    @Autowired
    private VendorCreationRep vendorCreationRep;

	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	Notify_Entity_Rep Notify_Entity_Reps;
	@Autowired
	Offer_Alert_services Offer_Alert_servicess;
    
    //For Sale Payment [Reminder]
    
    
     //@Scheduled(cron = "5 * * * * ?") // Run every five second
    //@Scheduled(cron = "0 0 9 * * ?") // Run daily at 9 AM
    public void sendFollowUpReminders() {
        try {
            String from = "";
            String username ="prasanth.m@bornfire.co.in";//need TSK username
            String password = "";//need TSK password
            String host = "sg2plzcpnl491716.prod.sin2.secureserver.net";

            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.ssl.trust", host); // Trust the server certificate
            properties.put("mail.debug", "true");  // Enable debugging

            LocalDate today = LocalDate.now();
            List<Follow_Up_Entity> invoices = followUpReps.getByAll();

            for (Follow_Up_Entity invoice : invoices) {
            	Date invoiceDate = invoice.getDueDate();
            	if (invoiceDate == null) {
            	    logger.warn("Invoice date is null for invoice: {}", invoice);
            	    return;
            	}

            	// Convert Date to LocalDate using Calendar
            	Calendar calendar = Calendar.getInstance();
            	calendar.setTime(invoiceDate);

            	LocalDate invoiceLocalDate = LocalDate.of(
            	        calendar.get(Calendar.YEAR),
            	        calendar.get(Calendar.MONTH) + 1, // Month is 0-based, so add 1
            	        calendar.get(Calendar.DAY_OF_MONTH)
            	);

            	if (today.equals(invoiceLocalDate)) {
            	    VendorCreation vendor = vendorCreationRep.getvendorlistid(invoice.getVendorId());
            	    if (vendor == null) {
            	        logger.warn("Vendor not found for ID: {}", invoice.getVendorId());
            	        return;
            	    }

            	    try {
                        sendEmail(vendor, invoice, from, username, password, properties);

                        // Introduce a 1-minute delay after sending each email
                        Thread.sleep(60 * 1000); // 60 seconds = 1 minute

                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // Reset interrupt flag
                        logger.error("Thread interrupted during delay: {}", e.getMessage(), e);
                    } catch (Exception e) {
                        logger.error("Error sending email for vendor ID {}: {}", vendor.getVendorId(), e.getMessage(), e);
                    }
            	    System.out.println("Inside 1");
            	} else {
            	    logger.debug("Invoice not due for follow-up: {}", invoice);
            	}

            }
        } catch (Exception e) {
            logger.error("Error sending follow-up reminders: {}", e.getMessage(), e);
        }
    }

    private void sendEmail(VendorCreation vendor, Follow_Up_Entity invoice, String from, 
            String username, String password, Properties properties) {
try {
    System.out.println("Inside 2");

String email = "prasanth.m@bornfire.co.in";//vendor.getEmailid();
if (email == null || email.trim().isEmpty() || !email.contains("@")) {
 logger.warn("Invalid email address for vendor ID: {}", vendor.getVendorId());
 return;
}

Session session = Session.getInstance(properties, new Authenticator() {
 @Override
 protected PasswordAuthentication getPasswordAuthentication() {
     return new PasswordAuthentication(username, password);
 }
});
System.out.println("Inside 3"+from);

MimeMessage message = new MimeMessage(session);
try {
    message.setFrom(new InternetAddress(from));
} catch (NullPointerException e) {
    logger.error("Failed to set 'from' address: {}", from, e);
    throw e; // Re-throw for further handling
}message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
message.setSubject("Payment Pending Alert");
System.out.println("Inside 4");
Date invoiceDate = new Date(); // Example date
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
String formattedDate = sdf.format(invoiceDate);
System.out.println(formattedDate);
String body = String.format(
	    "Dear %s,\n\nWarm Greetings!\n\nThis is a gentle reminder for the outstanding payment " +
	    "in the amount of Rs. %s, for Invoice ID [%s] dated [%s].\n\nThanks and Regards,\n%s\n" +
	    "HR Department\nT.SenthilKumar & Company,\n284 B KRISHANA MARKET,\nKS BUILDING, ODDANCHATRAM,\n" +
	    "DINDIGUL-624619,\nPh: 9443743002.",
	    vendor.getVendorName(),
	    invoice.getBalanceAmount(),
	    invoice.getWoId(),
	    formattedDate,
	    from
	);

message.setText(body);

Transport.send(message);
logger.info("Email sent successfully to {}", email);

} catch (MessagingException e) {
logger.error("Failed to send email to {}: {}", vendor.getEmailid(), e.getMessage());
}
}

    

    public String saveFollowUpDetails(Follow_Up_Entity followUpEntity, String formMode) {
        String response;
        
        try {
            // Only process if formMode is "add"
            if ("add".equalsIgnoreCase(formMode)) {
                // Check if the Follow-Up entry with the given woId exists
                Follow_Up_Entity existingFollowUp = followUpReps.getbycatid(followUpEntity.getWoId());

                // If it exists, return an error message
                if (existingFollowUp != null) {
                    return "The WO Id already exists!";
                } else {
                    // Set necessary fields
                    followUpEntity.setId(followUpEntity.getWoId());  // Set ID to Work Order ID
                    followUpEntity.setDelFlg("N");  // Set the flag to "Not Deleted"

                    // Save the follow-up entity to the database
                    followUpReps.save(followUpEntity);

                    response = "Follow-Up added successfully!";
                }
            } else {
                response = "Invalid form mode specified!";
            }
        } catch (Exception e) {
            // Log the exception for debugging (consider using a logger for production code)
            response = "An error occurred while saving Follow-Up details: " + e.getMessage();
        }

        return response;
    }

    
    
    public String addnotify(Notify_Entity userform, String formmode) {
        String msg = "";
        org.hibernate.Session session = sessionFactory.getCurrentSession();


        if ("add".equals(formmode)) {
            try {
                
                	 BigInteger Id = (BigInteger) session
         		            .createNativeQuery("SELECT NEXT VALUE FOR NOIFY_ID AS id")
         		            .getSingleResult();
                	 userform.setId(Id.toString());
                    String encryptedPassword = AES.encrypt(userform.getPassword());
                    userform.setPassword(encryptedPassword);
                    userform.setDelFlg("N");
                    Notify_Entity_Reps.save(userform);
                    msg = "Notification Created Successfully";
                
            } catch (Exception e) {
                e.printStackTrace();
                msg = "Error creating user: " + e.getMessage();
            }
        }
        if ("modify".equals(formmode)) {
            try {
                Notify_Entity old = Notify_Entity_Reps.getbyid(userform.getId());
                System.out.println("=== UserForm Data ===");
                System.out.println("ID: " + userform.getId());
                System.out.println("Email: " + userform.getEmail());
                System.out.println("Password: " + userform.getPassword());
                System.out.println("Alert: " + userform.getAlert());
                System.out.println("Process: " + userform.getProcess());
                System.out.println("Branch ID: " + userform.getBranchId());
                System.out.println("=====================");
                // Update only necessary fields
                old.setEmail(userform.getEmail());
                old.setProcess(userform.getProcess());
                old.setAlert(userform.getAlert());
                old.setBranchId(userform.getBranchId());
                old.setOrg_id(userform.getOrg_id());
                // Add other fields as needed

                String password = userform.getPassword();
                if (password != null && !password.trim().isEmpty()) {
                    String encryptedPassword = AES.encrypt(password);
                    old.setPassword(encryptedPassword);
                } else {
                    System.out.println("No new password provided, keeping existing one.");
                }

                old.setDelFlg("N");

                Notify_Entity_Reps.save(old);

                msg = "Notification Modified Successfully";
            } catch (Exception e) {
                e.printStackTrace();
                msg = "Error modifying user: " + e.getMessage();
            }
        }



        return msg;
    }
    public String offer_add(OFFER_ALERT_ENTITY userform, String formmode, byte[] file, String id) {
        String msg = "";
        org.hibernate.Session session = sessionFactory.getCurrentSession();

        try {
            if ("add".equals(formmode)) {
                // Add logic as before
                BigInteger Id = (BigInteger) session
                    .createNativeQuery("SELECT NEXT VALUE FOR OFFER_ID AS id")
                    .getSingleResult();
                userform.setId(Id.toString());

                String encryptedPassword = AES.encrypt(userform.getPassword());
                userform.setPassword(encryptedPassword);
                userform.setFile(file);
                userform.setDelFlg("N");
                OFFER_ALERT_REPs.save(userform);
                
                
                //Function for send Offer alert(sms,whatsup,email)
                try {
                Offer_Alert_servicess.offer_alert(file,userform);
                }catch( Exception e){
                	System.out.println("Error occured in message sent..."+e);
                }
                
                msg = "Notification Created Successfully";
            } else if ("modify".equals(formmode)) {
            	System.out.println("The id is "+id);

                // Find the existing entity by ID
                OFFER_ALERT_ENTITY old = OFFER_ALERT_REPs.getbyid(id);
                if (old != null) {
                    old.setEmail(userform.getEmail());
                    old.setVendor(userform.getVendor());
                    old.setAlert(userform.getAlert());
                    old.setBranchId(userform.getBranchId());
                    old.setFile(file);

                    if (userform.getPassword() != null && !userform.getPassword().trim().isEmpty()) {
                        String encryptedPassword = AES.encrypt(userform.getPassword());
                        old.setPassword(encryptedPassword);
                    }

                    old.setDelFlg("N");
                    OFFER_ALERT_REPs.save(old);
                    msg = "Notification Modified Successfully";
                } else {
                    msg = "Notification not found with ID: " + id;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Error: " + e.getMessage();
        }

        return msg;
    }
}
