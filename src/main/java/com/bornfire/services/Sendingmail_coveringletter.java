package com.bornfire.services;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bornfire.entities.CandEvalFormEntity;
import com.bornfire.entities.Salary_Pay_Entity;
import com.bornfire.entities.Salary_Pay_Rep;

@Service
public class Sendingmail_coveringletter {
	@Autowired
	Salary_Pay_Rep salary_Pay_Rep;
	
    public String sendingctcmail(String from, String host, String to,  String cc, String username, String password, String emp_no, String ctc) {
        // Get the session object
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587"); // Port for TLS/STARTTLS
        properties.put("mail.smtp.auth", "true"); // Enable authentication
        properties.put("mail.smtp.starttls.enable", "true"); // Enable STARTTLS
        System.out.println(ctc);

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
        	
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("SALARY REVISION COVERING LETTER");
            
            if (cc != null && !cc.isEmpty()) {
                String[] ccAddresses = cc.split(",");
                for (String ccAddress : ccAddresses) {
                    message.addRecipient(Message.RecipientType.CC, new InternetAddress(ccAddress));
                    message.setSubject("SALARY REVISION COVERING LETTER");
                }
            }

            MimeMultipart multipart = new MimeMultipart();

            // Add text part
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("BORNFIRE INNOVATION");
            multipart.addBodyPart(textPart);

            // Get candidate evaluation form data
            List<Salary_Pay_Entity> place = salary_Pay_Rep.getactc(emp_no,ctc);
            for (Salary_Pay_Entity att : place) {
            	// Set plain text content
            	MimeMultipart multipart2 = new MimeMultipart();
            	
            	MimeBodyPart textPart2 = new MimeBodyPart();
            	textPart.setText("Dear " + att.getEmp_name() + "\n\n" +
            	    "We are pleased to revise your Annual Gross Compensation to Rs." + new java.text.DecimalFormat("#,##0.00").format(att.getCtc_amt()) +
            	    " with effect from " + new SimpleDateFormat("dd-MM-yyyy").format(att.getCtc_eff_date()) + ". Your revised CTC is subject to the standard deductions PF Employer, Employee Contribution, ESI Contribution, " +
            	    "Property Tax, and applicable TDS. Please refer to the Salary Structure provided along with this letter for break up details.\n\n" +
            	    "We expect you to continue your good work with a sterling performance " +
            	    "in line with our continued leadership @ Bornfire.\n\n" +
            	    "All the best\n\n" +
            	    "Warm Regards\n\n" +
            	    "KALIDASS K\nHR Executive.\n");
            	multipart2.addBodyPart(textPart2);


            }


            List<Salary_Pay_Entity> places = salary_Pay_Rep.getactc(emp_no,ctc);

            for (Salary_Pay_Entity atts : places) {
                try {
                    byte[] imageData = atts.getStr(); // Retrieve image data from the database
                    if (imageData != null) {
                       
                    	   String attachmentName = "Bornfire-SalaryRevision-Covering-Letter- " +emp_no + " .pdf";
                        MimeBodyPart attachmentPart = new MimeBodyPart();
                        attachmentPart.setDataHandler(new DataHandler(new ByteArrayDataSource(imageData, "image/pdf"))); // Assuming JPEG format
                        attachmentPart.setFileName(attachmentName); // Set the attachment file name
                        multipart.addBodyPart(attachmentPart);
                    } else {
                        System.err.println("No image data found for attachment: " );
                    }
                } catch (MessagingException e) {
                    e.printStackTrace();
                    // Handle exception if attachment fails
                    return "Attachment failed: " + e.getMessage(); // Return error message
                }
            }
            
            List<Salary_Pay_Entity> placess = salary_Pay_Rep.getactc(emp_no,ctc);

            for (Salary_Pay_Entity atts : placess) {
                try {
                    byte[] imageData = atts.getRevision(); // Retrieve image data from the database
                    if (imageData != null) {
                        // Assuming attachmentName variable holds the name of the attachment
                    	   String attachmentName = "Bornfire-SalaryStructure- " +emp_no + " .pdf";
                        MimeBodyPart attachmentPart = new MimeBodyPart();
                        attachmentPart.setDataHandler(new DataHandler(new ByteArrayDataSource(imageData, "image/pdf"))); // Assuming JPEG format
                        attachmentPart.setFileName(attachmentName); // Set the attachment file name
                        multipart.addBodyPart(attachmentPart);
                    } else {
                        System.err.println("No image data found for attachment: " );
                    }
                } catch (MessagingException e) {
                    e.printStackTrace();
                    // Handle exception if attachment fails
                    return "Attachment failed: " + e.getMessage(); // Return error message
                }
            }

        
            message.setContent(multipart);
        
            Transport.send(message);
            System.out.println("Message sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
            return "send failed";
        }
        return "send successfully";
    }

}
