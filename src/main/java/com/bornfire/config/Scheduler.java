package com.bornfire.config;

import java.sql.SQLException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.bornfire.entities.PlacementMaintenance;
import com.bornfire.services.AckReciever;
import com.bornfire.services.RecievingMail;
import com.bornfire.services.RecievingMail2;
import com.bornfire.services.Spsendingmail;
import com.bornfire.services.sendingmail;
@Component
public class Scheduler {
	@Autowired
	RecievingMail recievingMail;
	
	@Autowired
	AckReciever ackReciever;
	
	@Autowired
	RecievingMail2 recievingMail2;
	
	@Autowired
	sendingmail sendingmails;

	@Autowired
	Spsendingmail spsendingmail;
	
	
//@Scheduled(fixedRate = 5000)
	public String getmail234() throws SQLException, ParseException {
	    PlacementMaintenance placementmaintenance = new PlacementMaintenance();
	    String user = "vijay.r@bornfire.in";
	    String password = "Vijay@123";
	    String storeType = "pop3";
	    String pop3Host = "sg2plzcpnl491716.prod.sin2.secureserver.net";
	    String start3 = "5";
	    try {
	        return ackReciever.receiveEmail(pop3Host, storeType, user, password, start3, placementmaintenance);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "Error receiving emails: " + e.getMessage();
	    }
	}

	
//@Scheduled(fixedRate = 5000)
	public String getmail2344() throws SQLException, ParseException {
	    PlacementMaintenance placementmaintenance = new PlacementMaintenance();
	    String user = "grn@bornfire.in";
	    String password = "Sound@Amman3";
	    String storeType = "pop3";
	    String pop3Host = "sg2plzcpnl491716.prod.sin2.secureserver.net";

	    try {
	        // You need to implement the receiveEmail method in the recievingMail class
	        String result = recievingMail.receiveEmail(pop3Host, storeType, user, password, placementmaintenance);
	        return result;
	    } catch (Exception e) {
	        // Handle exceptions appropriately
	        e.printStackTrace();
	        return "Error: " + e.getMessage();
	    }
	}
	
	//@Scheduled(fixedRate = 5000)
	public String getmail2( ) throws SQLException, ParseException {
		
		// return "Success";
		PlacementMaintenance placementmaintenance =new PlacementMaintenance();
		String user="grn@bornfire.in";
		String password="Sound@Amman3";
		String storeType="pop3";
		String pop3Host="sg2plzcpnl491716.prod.sin2.secureserver.net";
		return recievingMail2.receiveEmail2(pop3Host, storeType, user, password,placementmaintenance);
	}
	
	//@Scheduled(fixedRate = 5000)
	/*
	 * public String sendmails( ) throws SQLException, ParseException {
	 * System.out.println("Hi"); String to = "ragul.r@bornfire.in"; String from
	 * ="accts@bornfire.in"; String username = "accts@bornfire.in";//change
	 * accordingly String password = "VNivas@636003";//change accordingly String
	 * host = "sg2plzcpnl491716.prod.sin2.secureserver.net"; return
	 * sendingmails.sendmail( from, username, password, to,host ) ;
	 * 
	 * }
	 */
		//@Scheduled(fixedRate = 10000)
				public String spsendmails( ) throws SQLException, ParseException {
					System.out.println("Hi");
					String to = "siddhaiyan@bornfire.in";
					   String from = "accts@bornfire.in";
					   String username = "accts@bornfire.in";//change accordingly
					   String password = "VNivas@636003";//change accordingly
					   String host = "sg2plzcpnl491716.prod.sin2.secureserver.net";
					   String Sp="STACKPOS";
					   String inv_due_date="01-JUL-2023";
					   String inv_date="31-JUL-2023";
					return spsendingmail.sendmail(  from,  username,  password, to,host ,Sp, inv_due_date, inv_date ) ;
					
					
				}
}
