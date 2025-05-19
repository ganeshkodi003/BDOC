package com.bornfire.services;

import com.bornfire.entities.CandEvalFormEntity;
import com.bornfire.entities.CandEvalFormRep;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class sendigmail_offerletter {

    @Autowired
    CandEvalFormRep CandEvalFormRep;
    @Autowired
	Environment env;

    public String sendingmailones(String from, String host, String to,  String cc, String username, String password, String ref_no) {
        // Get the session object
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587"); // Port for TLS/STARTTLS
        properties.put("mail.smtp.auth", "true"); // Enable authentication
        properties.put("mail.smtp.starttls.enable", "true"); // Enable STARTTLS
       // System.out.println(y);

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
        	
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("OFFER LETTER");
            
            if (cc != null && !cc.isEmpty()) {
                String[] ccAddresses = cc.split(",");
                for (String ccAddress : ccAddresses) {
                    message.addRecipient(Message.RecipientType.CC, new InternetAddress(ccAddress));
                    message.setSubject("OFFER LETTER");
                }
            }

            MimeMultipart multipart = new MimeMultipart();

            // Add text part
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("BORNFIRE INNOVATIONS");
            multipart.addBodyPart(textPart);

            // Get candidate evaluation form data
            List<CandEvalFormEntity> place = CandEvalFormRep.getCvfmail(ref_no);
            for (CandEvalFormEntity att : place) {
                MimeMultipart multipart2 = new MimeMultipart();
                
                MimeBodyPart textPart2 = new MimeBodyPart();
                
                textPart.setText("Following our recent discussions, we are delighted to offer you the position of " + att.getPosition_title() +
                        " with Our Organization. Bornfire creates and redefines solutions for BFSI Sector. If you join Bornfire, you will " +
                        " become part of a fast-paced and dedicated team that works together to provide our clients with the highest possible level of service and advice.\n\n" +
                        "As a member of Our Bornfire team, we would ask for your commitment to delivering outstanding quality and results that exceed client expectations. In addition, we expect your personal accountability in all the products, actions, advice, and results that you provide as a representative of Our Organization. In return, we are committed to providing you with every opportunity to learn, grow and stretch to the highest level of your ability and potential.\n\n" +
                        "We are confident you will find this new opportunity both challenging and rewarding. The following points outline the terms and conditions we are proposing.\n\n" +
                        "Your starting salary (cost to the company) will be Rs." + att.getCtc() +
                        " annually as agreed upon by us. Our organization believes that, while maintaining the cost of the employee to the company at agreed levels, the structure of payment has been tailored to suit the specific needs of the employee within the norms set out in the Organization policy.\n\n" +
                        "With Regards,\n\n" +
                        "KALIDASS K\n" +
                        "HR Executive\n\n" +
                        "Bornfire Innovations Private Limited\n" +
                        "Viji Nivas, Second Floor,\n" +
                        "10, Soundaraiyar Street,\n" +
                        "Ammapet,\n" +
                        "Salem - 636003 Tamilnadu, India\n\n" +
                        "Land Line: +91 44 24650400\n" +
                        "Mobile: +91 95668 74563\n\n" +
                        "http://bornfire.in\n\n" +
                        "Disclaimer: The information in this mail is confidential and is intended solely for addressee. Access to this mail by anyone else is unauthorized. Copying or further distribution beyond the original recipient may be unlawful. We are not responsible for any damage caused by a virus or alteration of the e-mail by a third party or otherwise. The contents of this message may not necessarily represent the views or policies of Bornfire Innovations.\n");
               
                // Add text content to the multipart
                multipart2.addBodyPart(textPart2);
            }


            List<CandEvalFormEntity> places = CandEvalFormRep.getCvfmail(ref_no);

            for (CandEvalFormEntity atts : places) {
                try {
                    byte[] imageData = atts.getOffer(); // Retrieve image data from the database
                    if (imageData != null) {
                        // Assuming attachmentName variable holds the name of the attachment
                    	   String attachmentName = "Bornfire-OfferLetter-" + ref_no + ".pdf";
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

            // This will contain the first part of the file name before the dot

            

            // Add attachments
          /*  for (String fileName : y) {
                try {
                   // String filePath = "C:\\newbhrps\\bhrps\\BHRPS_NEW\\downloadreports\\"; //C:\\newbhrps\\bhrps\\BHRPS_NEW\\downloadreports
                   // String filePath = "C:\\";  
                //	String filePath = "C:\\BHRPS_NEW\\downloadreports\\";
            	  //  String filePath = env.getProperty("output.exportpath");
                //	String filePath ="D:\\";
                	String filePath=env.getProperty("output.exportpath");


                    String entireFilePath = filePath + fileName;
                    MimeBodyPart attachmentPart = new MimeBodyPart();
                    attachmentPart.attachFile(new File(entireFilePath));
                    multipart.addBodyPart(attachmentPart);
                    
                   
                } catch (IOException e) {
                    e.printStackTrace();
                    // Handle exception if file attachment fails
                    return "Attachment failed: " + e.getMessage(); // Return error message
                }
                System.out.println(fileName);
            }*/
            message.setContent(multipart);
        

            // Set the multipart as the content of the message
          

            // Send the message
            Transport.send(message);
            System.out.println("Message sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
            return "send failed";
        }
        return "send successfully";
    }
}
