package com.bornfire.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.bornfire.entities.Notify_Entity;
import com.bornfire.entities.Notify_Entity_Rep;
import com.bornfire.entities.VendorCreation;
import com.bornfire.entities.VendorCreationRep;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class Notification_services {

    private static final Logger logger = LoggerFactory.getLogger(Notification_services.class);

    @Autowired
    VendorCreationRep vendorCreationRep;

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    Notify_Entity_Rep Notify_Entity_Reps;

    public void fitsmethod(String id, String vendor, BigDecimal amount, String type) {

        List<Notify_Entity> notify = Notify_Entity_Reps.getall();
        VendorCreation vendor_list = vendorCreationRep.getvendorlistid(vendor);

        if (vendor_list == null) {
            logger.warn("Vendor not found for ID: {}", vendor);
            return;
        }

        String vendor_mobile = vendor_list.getMobileNo();

        for (Notify_Entity up : notify) {
            if (up.getProcess().equals(type)) {

                switch (up.getAlert()) {
                    case "sms":
                        sendSMS(id, amount, vendor_mobile,type);
                        break;

                    case "whatsapp":
                        sendWhatsApp(vendor_mobile, id, amount, type);
                        break;

                    case "email":
                        sendFollowUpReminders(vendor_list, id, amount, type);
                        break;

                    default:
                        logger.info("Unknown alert type: {}", up.getAlert());
                }
            }
        }
    }

    // SMS Sending
    public void sendSMS(String id, BigDecimal amount, String mobile,String type) {
        String senderId = "";
        String message = "The "+type+" has been registered. "+type+" ID: " + id + ". Amount: Rs. " + amount;
        String templateId = "";
        String clientId = "";
        String apiKey = "";

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl("https://api.smslane.com/api/v2/SendSMS")
                .queryParam("SenderId", senderId)
                .queryParam("Message", message)
                .queryParam("MobileNumbers", mobile)
                .queryParam("TemplateId", templateId)
                .queryParam("ApiKey", apiKey)
                .queryParam("ClientId", clientId);

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(builder.toUriString(), String.class);
        logger.info("SMS Response: {}", response);
    }

    // WhatsApp Sending
    public void sendWhatsApp(String vendor_mobile, String id, BigDecimal amount,String type) {
        String ACCOUNT_SID = "YOUR_TWILIO_ACCOUNT_SID";
        String AUTH_TOKEN = "YOUR_TWILIO_AUTH_TOKEN";

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        String msg = "The "+type+" has been registered. "+type+" ID: " + id + ". Amount: Rs. " + amount;

        Message message = Message.creator(
                new PhoneNumber("whatsapp:" + vendor_mobile), // To
                new PhoneNumber("whatsapp:+14155238886"),     // From (Twilio sandbox number)
                msg).create();

        logger.info("WhatsApp message SID: {}", message.getSid());
    }

    // Email Sending
    public void sendFollowUpReminders(VendorCreation vendor, String id, BigDecimal amount,String type) {
        String from = "prasanth.m@bornfire.co.in";
        String username = from;
        String password = "your_secure_password"; // 🔒 Move to secure config!
        String host = "sg2plzcpnl491716.prod.sin2.secureserver.net";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", host);
        properties.put("mail.debug", "true");

        try {
            sendEmail(vendor, id, amount, from, username, password, properties,type);
            logger.info("Email triggered to {}", vendor.getEmailid());
        } catch (Exception e) {
            logger.error("Email sending failed: {}", e.getMessage(), e);
        }
    }

    private void sendEmail(VendorCreation vendor, String id, BigDecimal amount, String from,
                           String username, String password, Properties properties,String type) {

        String to = vendor.getEmailid();
        if (to == null || to.trim().isEmpty() || !to.contains("@")) {
            logger.warn("Invalid email for vendor ID {}: '{}'", vendor.getVendorId(), to);
            return;
        }

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Payment Pending Alert");

            String msg = "The "+type+" has been registered. "+type+" ID: " + id + ". Amount: Rs. " + amount;
            message.setText("Dear " + vendor.getVendorName() + ",\n\n" + msg + "\n\nThanks and Regards,\nTSK Team");

            Transport.send(message);
            logger.info("Email successfully sent to {}", to);

        } catch (MessagingException e) {
            logger.error("Error sending email to {}: {}", to, e.getMessage(), e);
        }
    }
}
