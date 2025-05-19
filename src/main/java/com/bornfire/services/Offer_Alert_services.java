package com.bornfire.services;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.util.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.bornfire.entities.OFFER_ALERT_ENTITY;
import com.bornfire.entities.OFFER_ALERT_REP;
import com.bornfire.entities.VendorCreation;
import com.bornfire.entities.VendorCreationRep;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class Offer_Alert_services {

    private static final Logger logger = LoggerFactory.getLogger(Offer_Alert_services.class);

    @Autowired
    VendorCreationRep vendorCreationRep;

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    OFFER_ALERT_REP OFFER_ALERT_REPs;

    public void offer_alert(byte[] file, OFFER_ALERT_ENTITY userform) {
        List<VendorCreation> vendorList = new ArrayList<>();

        if (userform.getVendor().equalsIgnoreCase("purchase_vendor")) {
            vendorList = vendorCreationRep.getpurchaseVendor();
        } else if (userform.getVendor().equalsIgnoreCase("sale_vendor")) {
            vendorList = vendorCreationRep.getsaleVendors();
        }

        for (VendorCreation vendor : vendorList) {
            switch (userform.getAlert().toLowerCase()) {
                case "sms":
                    sendSMS(vendor.getMobileNo(), vendor.getVendorName());
                    break;
                case "whatsapp":
                    sendWhatsApp(file, vendor.getMobileNo(), vendor.getVendorName());
                    break;
                case "email":
                    sendFollowUpReminders(file, vendor, userform.getEmail(), userform.getPassword());
                    break;
                default:
                    logger.info("Unknown alert type: {}", userform.getAlert());
            }
        }
    }

    // ✅ SMS: Simple text only
    public void sendSMS(String vendorMobile, String vendorName) {
    	String message = "Dear " + vendorName + ",\n\n"
    	        + "Warm greetings from TSK!\n"
    	        + "We are pleased to extend an exclusive offer to you. Kindly refer to the attached document for more details.\n\n"
    	        + "We truly value your association and look forward to serving you.\n\n"
    	        + "Best regards,\n"
    	        + "TSK Team";

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl("https://api.smslane.com/api/v2/SendSMS")
                .queryParam("SenderId", "YOUR_SENDER_ID")
                .queryParam("Message", message)
                .queryParam("MobileNumbers", vendorMobile)
                .queryParam("TemplateId", "YOUR_TEMPLATE_ID")
                .queryParam("ApiKey", "YOUR_API_KEY")
                .queryParam("ClientId", "YOUR_CLIENT_ID");

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(builder.toUriString(), String.class);
        logger.info("SMS Response: {}", response);
    }

    // ✅ WhatsApp with media
    public void sendWhatsApp(byte[] file, String vendorMobile, String vendorName) {
        String ACCOUNT_SID = "YOUR_TWILIO_ACCOUNT_SID";
        String AUTH_TOKEN = "YOUR_TWILIO_AUTH_TOKEN";
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        String fileUrl = uploadFileAndGetUrl(file, vendorName); // 👈 Helper method

        String msg = "Dear " + vendorName + ",\n\n"
                + "Warm greetings from TSK!\n"
                + "We are pleased to extend an exclusive offer to you. Kindly refer to the attached document for more details.\n\n"
                + "We truly value your association and look forward to serving you.\n\n"
                + "Best regards,\n"
                + "TSK Team";

        Message message = Message.creator(
                new PhoneNumber("whatsapp:" + vendorMobile),
                new PhoneNumber("whatsapp:+14155238886"), // Twilio Sandbox From
                msg
        ).setMediaUrl(Arrays.asList(URI.create(fileUrl))).create();


        logger.info("WhatsApp message SID: {}", message.getSid());
    }

    // ✅ Email with attachment
    public void sendFollowUpReminders(byte[] file, VendorCreation vendor, String from, String password) {
        String host = "sg2plzcpnl491716.prod.sin2.secureserver.net";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", host);
        properties.put("mail.debug", "true");

        try {
            sendEmailWithAttachment(file, vendor, from, from, password, properties);
            logger.info("Email triggered to {}", vendor.getEmailid());
        } catch (Exception e) {
            logger.error("Email sending failed: {}", e.getMessage(), e);
        }
    }

    private void sendEmailWithAttachment(byte[] file, VendorCreation vendor, String from, String username, String password, Properties properties) throws MessagingException {
        String to = vendor.getEmailid();
        if (to == null || to.trim().isEmpty() || !to.contains("@")) {
            logger.warn("Invalid email for vendor ID {}: '{}'", vendor.getVendorId(), to);
            return;
        }

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject("Offer Alert from TSK");

        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setText("Dear " + vendor.getVendorName() + ",\n\n"
                + "Warm greetings from TSK!\n"
                + "We are pleased to extend an exclusive offer to you. Kindly refer to the attached document for more details.\n\n"
                + "We truly value your association and look forward to serving you.\n\n"
                + "Best regards,\n"
                + "TSK Team");

        MimeBodyPart attachmentPart = new MimeBodyPart();
        DataSource source = new ByteArrayDataSource(file, "image/png"); // Or image/jpeg or pdf
        attachmentPart.setDataHandler(new DataHandler(source));
        attachmentPart.setFileName("OfferImage.png");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(textPart);
        multipart.addBodyPart(attachmentPart);

        message.setContent(multipart);
        Transport.send(message);
        logger.info("Email successfully sent to {}", to);
    }

    // ✅ Helper method to save image to public folder (simulate public URL)
    private String uploadFileAndGetUrl(byte[] file, String vendorName) {
        try {
            String fileName = vendorName.replaceAll("\\s+", "_") + "_offer.png";
            String filePath = "C:/uploaded_images/" + fileName; // You must ensure this folder is public (served via web)
            File outputFile = new File(filePath);

            try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                fos.write(file);
            }

            return "https://yourdomain.com/uploaded_images/" + fileName; // Replace with real URL
        } catch (Exception e) {
            logger.error("File upload failed: {}", e.getMessage());
            return "";
        }
    }
}
