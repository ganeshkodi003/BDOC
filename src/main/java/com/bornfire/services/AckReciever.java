package com.bornfire.services;

import java.io.IOException;

import java.io.InputStream;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import java.util.Optional;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.BodyPart;

import javax.mail.Folder;

import javax.mail.Message;

import javax.mail.MessagingException;

import javax.mail.Multipart;

import javax.mail.NoSuchProviderException;

import javax.mail.Session;

import javax.mail.internet.MimeMultipart;

import javax.mail.search.FlagTerm;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.bornfire.entities.InvoiceMaster;

import com.bornfire.entities.InvoiceMasterRep;

import com.bornfire.entities.PlacementMaintenance;

import com.bornfire.entities.PlacementMaintenanceRep;

import com.ibm.icu.text.DecimalFormat;

import com.sun.mail.pop3.POP3Store;

import oracle.net.aso.e;

@Service

public class AckReciever {

	@Autowired

	PlacementMaintenanceRep placementMaintenanceRep;
	

	@Autowired

	InvoiceMasterRep invoiceMasterRep;

	public String receiveEmail(String pop3Host, String storeType, String user, String password, String start3,
	        PlacementMaintenance placementmaintenance) {

	    try {
	        Properties properties = new Properties();
	        properties.put("mail.pop3.host", pop3Host);
	        Session emailSession = Session.getDefaultInstance(properties);

	        final int MAX_MESSAGES = Integer.parseInt(start3);

	        POP3Store emailStore = (POP3Store) emailSession.getStore(storeType);
	        emailStore.connect(user, password);

	        Folder emailFolder = emailStore.getFolder("INBOX");
	        emailFolder.open(Folder.READ_ONLY);

	        int end = emailFolder.getMessageCount();
	        int start = Math.max(1, end -36); // Retrieve the last 20 messages
	        Message[] messages = emailFolder.getMessages(start, end);

	        String Sub[] = new String[messages.length]; // Initialize Sub[] with the correct size
	        for (int j = 0; j < messages.length; j++) {
	            Message messaged = messages[j];
	            System.out.println("---------------------------------------------------------------------------------------");
	            System.out.println("Email Number " + (j + 1));

	            Sub[j] = messaged.getSubject();
	            System.out.println("Subject: " + Sub[j]); // Use Sub[j] to print the subject

	            String ss = getTextFromMessage(messaged);

	            if (ss != null && ss.contains("GRN")) {
	                // Your code here
	            } else if (ss.contains("Acknowledgement")) {
	                String DPdate = null;
	                try {
	                    // Extract the date directly from the "Date:" field
	                    int dateIndex = ss.indexOf("Date: ");
	                    if (dateIndex != -1) {
	                        String dateLine = ss.substring(dateIndex + 6, ss.indexOf("\n", dateIndex)).trim(); // Skip "Date: "
	                        System.out.println(dateLine);

	                        String[] dateParts = dateLine.split(" ");
	                        String date = dateParts[0]; // yyyy-MM-dd
	                        String[] dateComponents = date.split("-");

	                        String dayOfMonth = dateComponents[2];
	                        String month = dateComponents[1];
	                        String year = dateComponents[0];

	                        DPdate = dayOfMonth + "-" + month + "-" + year;
	                        System.out.println(DPdate);
	                    } else {
	                        System.out.println("Date index not found.");
	                    }
	                } catch (StringIndexOutOfBoundsException e) {
	                    System.out.println("Error: " + e.getMessage());
	                }

	                System.out.println(DPdate);

	                ArrayList<String> a = new ArrayList<String>();
	                ArrayList<String> b = new ArrayList<String>();

	                String specificWord = "BORNFIRE\\d{4}[A-Z]\\d{3}";
	                String specificWord1 = "\\d{12}";

	                Pattern pattern = Pattern.compile(specificWord);
	                Matcher matcher = pattern.matcher(ss);

	                int count = 0;
	                while (matcher.find()) {
	                    String lastMatch = matcher.group(0);
	                    System.out.println("Last match: " + lastMatch);
	                    a.add(lastMatch);
	                    System.out.println("-0-0-0-0-0-0-0-0-0-0-" + matcher);
	                    count++;
	                }

	                Pattern pattern1 = Pattern.compile(specificWord1);
	                Matcher matcher1 = pattern1.matcher(ss);

	                while (matcher1.find()) {
	                    String lastMatch = matcher1.group(0);
	                    System.out.println("Last match: " + lastMatch);
	                    b.add(lastMatch);
	                    System.out.println("-0-0-0-0-0-0-0-0-0-0-" + matcher1);
	                }

	                if (a.size() == b.size()) {
	                    int size = a.size();
	                    for (int i1 = 0; i1 < size; i1++) {
	                        System.out.println("Value from list1: " + a.get(i1));
	                        System.out.println("Value from list2: " + b.get(i1));
	                        CreateAAdd(placementmaintenance, a.get(i1), b.get(i1), DPdate);
	                    }
	                } else {
	                    System.out.println("Both ArrayLists must have the same size to iterate through them in one loop.");
	                }
	                System.out.println("The word matching '" + specificWord + "' appears " + count + " times in the text.");

	                String IN = ss.substring(ss.indexOf("BORNFIRE"), ss.indexOf("Regards,"));

	                System.out.println(IN);

	                String IN1 = IN.substring(IN.indexOf("BORNFIRE"), 16);

	                System.out.println(IN1);

	                String DP = IN.substring(IN.indexOf("INR"));

	                String DP1 = DP.substring(7, 19);

	                System.out.println(DP1);
	                System.out.println("))))))))))))))))" + DP1 + "JJJJJJJJJJJJ" + DPdate);

	                System.out.println(CreateAAdd(placementmaintenance, IN1, DP1, DPdate));
	            } else {
	                System.out.println("Substring not found in the email content.");
	            }
	        }

	        // Close folder and store outside the loop
	        emailFolder.close(false);
	        emailStore.close();

	    } catch (NoSuchProviderException e) {
	        e.printStackTrace();
	    } catch (MessagingException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    String msg = null;
	    if (Integer.parseInt(start3) > 1) {
	        msg = "Mails are Received Successfully";
	    } else if (Integer.parseInt(start3) == 1) {
	        msg = "Mail Received Successfully";
	    }

	    return msg;
	}



	private String CreateAAdd(PlacementMaintenance placementmaintenance, String IN1, String DP1, String DPdate) {
	    String dpDate = null;
	   System.out.println("hhhhhhhhhhhhhhhhhhh"+IN1);
	   System.out.println("hhhhhhhhhhhhhhhhhhh"+DPdate);
	   
	    try {
	        PlacementMaintenance up = placementMaintenanceRep.getAlist(IN1);
	        if (up != null) {
	            System.out.println(placementMaintenanceRep.getAlist(IN1));
	            System.out.println(IN1 + "3333333333" + DP1 + "$$$$$$$$$$$$$" + DPdate);
	            up.setDp_date(DPdate);
	            System.out.println(DPdate + "jijiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");

	            up.setDp_no(DP1);

	            placementMaintenanceRep.save(up);

	            dpDate = up.getDp_date();
	        } else {
	            System.out.println("No PlacementMaintenance found for IN1: " + IN1);
	        }
	    } catch (Exception e) {
	        System.out.println("Error in CreateAAdd: " + e.getMessage());
	        e.printStackTrace();
	    }
	    return dpDate;
	}


	private String CreatePAdd(PlacementMaintenance placementmaintenance, String Effort, String Amount, String GRN,

			String po_id, String GRNDate, String po_id1, String EmpNo) {

		System.out.println(placementmaintenance.getPo_id());

		Optional<PlacementMaintenance> up = placementMaintenanceRep.findById(po_id);
		Optional<PlacementMaintenance> op = placementMaintenanceRep.findById(po_id1);

		if (up.isPresent()) {

			if (up.get().getLocation().equals("CHENNAI")) {

				System.out.println(Effort + GRN + Amount);

				System.out.println(up.get().getLocation());

				double i = Double.valueOf(Amount);

				DecimalFormat f = new DecimalFormat("#,##,##0.00");

				System.out.println(f.format(i));

				up.get().setGrn_efforts(GRN);

				up.get().setGrn_no(Effort);

				up.get().setGrn_amt(f.format(i));

				System.out.println("------------->>>>>>>>>>>>>" + up.get().setGrn_amt(f.format(i)));

				// String amt = Amount;

				// Double GrnAmt = Double.parseDouble(Amount);

				System.out.println(Amount);

				// System.out.println("<<<<<>>>>"+GrnAmt);

				// double Gst_val = GrnAmt*18/100;

				// System.out.println(f.format(Gst_val));

				// int grnint = Double.valueOf(GRN).intValue();

				// BigDecimal totint = new BigDecimal(GrnAmt);

				// System.out.println(totint+"<<<<<>>>>>");

				up.get().setInv_igst("0");

				up.get().setInv_tot_gst("0");

				up.get().setInv_tot_amt(String.valueOf(up.get().setGrn_amt(f.format(i))));

				up.get().setGrn_date(GRNDate);
				System.out.println((up.get().getSp()));
				// up.get().setGrn_no(GRN);
				if (up.get().getSp()==null) {				} else if ((up.get().getSp()).equals("ASOFT")) {
					String o = up.get().getSp_rate().replace(",", "");

					String p = o.substring(0, o.indexOf("."));
					// Double p1=Double.parseDouble(GRN);
					BigDecimal bigDecimalValue = new BigDecimal(GRN);
					BigDecimal op1 = bigDecimalValue.multiply(BigDecimal.valueOf(Integer.parseInt(p)));
					// BigDecimal op1=Integer.parseInt(p)*bigDecimalValue;
					System.out.println(op1);
					int decimalPlaces = 0;
					BigDecimal bd = new BigDecimal(op1.toString());
					bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);
					// int o=(Integer.parseInt(up.get().getFixed_amt()))*Integer.parseInt(GRN);
					// System.out.println(o);
					up.get().setSp_inv_amt(f.format(bd).toString());
					System.out.println("KKKKKKKKKKKKKKK" + f.format(bd).toString());
					BigDecimal percentage = BigDecimal.valueOf(18); // Percentage value
					long longValue = op1.longValue();
					BigDecimal igst = BigDecimal.valueOf(longValue)
							.multiply(percentage.divide(BigDecimal.valueOf(100)));
					// int decimalPlaces = 0;
					BigDecimal bde = new BigDecimal(igst.toString());
					bde = bde.setScale(decimalPlaces, RoundingMode.HALF_UP);
					// System.out.println(bd);
					// BigDecimal bd1= bd.add(bd);
					BigDecimal de = bde.add(bd);
					up.get().setSp_inv_cgst("0");
					up.get().setSp_inv_sgst("0");
					up.get().setSp_inv_igst(f.format(bde).toString());
					up.get().setSp_inv_tot_gst(f.format(bde).toString());
					up.get().setSp_inv_tot_amt(f.format(de).toString());
				} else if ((up.get().getSp()).equals("MISCOT")) {
					String o = up.get().getSp_rate().replace(",", "");

					String p = o.substring(0, o.indexOf("."));
					// Double p1=Double.parseDouble(GRN);
					BigDecimal bigDecimalValue = new BigDecimal(GRN);
					BigDecimal op1 = bigDecimalValue.multiply(BigDecimal.valueOf(Integer.parseInt(p)));
					// BigDecimal op1=Integer.parseInt(p)*bigDecimalValue;
					System.out.println(op1);
					int decimalPlaces = 0;
					BigDecimal bd = new BigDecimal(op1.toString());
					bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);
					// int o=(Integer.parseInt(up.get().getFixed_amt()))*Integer.parseInt(GRN);
					// System.out.println(o);
					up.get().setSp_inv_amt(f.format(bd).toString());
					System.out.println("KKKKKKKKKKKKKKK" + f.format(bd).toString());
					BigDecimal percentage = BigDecimal.valueOf(18); // Percentage value
					long longValue = op1.longValue();
					BigDecimal igst = BigDecimal.valueOf(longValue)
							.multiply(percentage.divide(BigDecimal.valueOf(100)));
					// int decimalPlaces = 0;
					BigDecimal bde = new BigDecimal(igst.toString());
					bde = bde.setScale(decimalPlaces, RoundingMode.HALF_UP);
					// System.out.println(bd);
					// BigDecimal bd1= bd.add(bd);
					BigDecimal de = bde.add(bd);
					up.get().setSp_inv_cgst("0");
					up.get().setSp_inv_sgst("0");
					up.get().setSp_inv_igst(f.format(bde).toString());
					up.get().setSp_inv_tot_gst(f.format(bde).toString());
					up.get().setSp_inv_tot_amt(f.format(de).toString());
				} else if ((up.get().getSp()).equals("STACKPOS")) {
					System.out.println(up.get().getSp_rate());
					String o = up.get().getSp_rate().replace(",", "");
					String p = o.substring(0, o.indexOf("."));
					BigDecimal bigDecimalValue = new BigDecimal(GRN);
					BigDecimal op1 = bigDecimalValue.multiply(BigDecimal.valueOf(Integer.parseInt(p)));
					// int op1=Integer.parseInt(p)*p1;
					System.out.println(op1);
					int decimalPlaces = 0;
					BigDecimal bd = new BigDecimal(op1.toString());
					bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);

					// int o=(Integer.parseInt(up.get().getFixed_amt()))*Integer.parseInt(GRN);
					// System.out.println(o);
					up.get().setSp_inv_amt(f.format(bd).toString());
					System.out.println("JJJJJJJJJJJJ" + f.format(op1).toString());
					BigDecimal percentage = BigDecimal.valueOf(9); // Percentage value
					long longValue = op1.longValue();
					BigDecimal igst = BigDecimal.valueOf(longValue)
							.multiply(percentage.divide(BigDecimal.valueOf(100)));
					// int decimalPlaces = 0;
					BigDecimal bde = new BigDecimal(igst.toString());
					bde = bde.setScale(decimalPlaces, RoundingMode.HALF_UP);
					// System.out.println(bd);
					BigDecimal bd1 = bde.add(bde);
					BigDecimal de = bd1.add(bd);
					up.get().setSp_inv_cgst(f.format(bde).toString());
					up.get().setSp_inv_sgst(f.format(bde).toString());
					up.get().setSp_inv_tot_gst(f.format(bd1).toString());
					up.get().setSp_inv_tot_amt(f.format(de).toString());
				}else if ((up.get().getSp()).equals("WHITESTONE")) {
					String o = up.get().getFixed_amt().replace(",", "");
					String p = o.substring(0, o.indexOf("."));
					BigDecimal bigDecimalValue = new BigDecimal(GRN);
					BigDecimal op1 = bigDecimalValue.multiply(BigDecimal.valueOf(Integer.parseInt(p)));
					System.out.println(op1);
					// int o=(Integer.parseInt(up.get().getFixed_amt()))*Integer.parseInt(GRN);
					// System.out.println(o);
					
					int decimalPlaces = 0;
					BigDecimal bd = new BigDecimal(op1.toString());
					bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);
					
					up.get().setSp_inv_amt(f.format(bd).toString());
					System.out.println("LLLLLLLLLLLLLLLLL" + f.format(bd).toString());
					BigDecimal percentage = BigDecimal.valueOf(9); // Percentage value
					long longValue = op1.longValue();
					BigDecimal cgst = BigDecimal.valueOf(longValue)
							.multiply(percentage.divide(BigDecimal.valueOf(100)));
					//int decimalPlaces = 0;
					BigDecimal bde = new BigDecimal(cgst.toString());
					bde = bde.setScale(decimalPlaces, RoundingMode.HALF_UP);
					System.out.println(bde);
					BigDecimal bd1 = bde.add(bde);
					BigDecimal de = bd1.add(bd);
					up.get().setSp_inv_cgst(f.format(bde).toString());
					up.get().setSp_inv_sgst(f.format(bde).toString());
					up.get().setSp_inv_tot_gst(f.format(bd1).toString());
					up.get().setSp_inv_tot_amt(f.format(de).toString());
				}


				placementMaintenanceRep.save(up.get());

			}

			else {

				System.out.println(Effort + GRN + Amount);

				System.out.println(up.get().getLocation());

				double i = Double.valueOf(Amount);

				DecimalFormat f = new DecimalFormat("#,##,##0.00");

				System.out.println(f.format(i));

				up.get().setGrn_efforts(GRN);

				up.get().setGrn_no(Effort);

				up.get().setGrn_amt(f.format(i));

				// String amt = Amount;

				Double GrnAmt = Double.parseDouble(Amount);

				System.out.println(Amount);

				System.out.println("<<<<<>>>>" + GrnAmt);

				double Gst_val = GrnAmt * 18 / 100;

				System.out.println(f.format(Gst_val));

				int grnint = Double.valueOf(GRN).intValue();

				BigDecimal totint = new BigDecimal(GrnAmt + Math.round(Gst_val));

				System.out.println(totint + "<<<<<>>>>>");

				up.get().setInv_igst(String.valueOf(f.format(Math.round(Gst_val))));

				up.get().setInv_tot_gst(String.valueOf(f.format(Math.round(Gst_val))));

				up.get().setInv_tot_amt(String.valueOf(f.format(totint)));

				up.get().setGrn_date(GRNDate);

				System.out.println(up.get().getSp() + "||||||||||||||");

				// up.get().setGrn_no(GRN);

				if (up.get().getSp()==null) {
									} else if ((up.get().getSp()).equals("ASOFT")) {
					String o = up.get().getSp_rate().replace(",", "");

					String p = o.substring(0, o.indexOf("."));
					// Double p1=Double.parseDouble(GRN);
					BigDecimal bigDecimalValue = new BigDecimal(GRN);
					BigDecimal op1 = bigDecimalValue.multiply(BigDecimal.valueOf(Integer.parseInt(p)));
					// BigDecimal op1=Integer.parseInt(p)*bigDecimalValue;
					System.out.println(op1);
					int decimalPlaces = 0;
					BigDecimal bd = new BigDecimal(op1.toString());
					bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);
					// int o=(Integer.parseInt(up.get().getFixed_amt()))*Integer.parseInt(GRN);
					// System.out.println(o);
					up.get().setSp_inv_amt(f.format(bd).toString());
					System.out.println("KKKKKKKKKKKKKKK" + f.format(bd).toString());
					BigDecimal percentage = BigDecimal.valueOf(18); // Percentage value
					long longValue = op1.longValue();
					BigDecimal igst = BigDecimal.valueOf(longValue)
							.multiply(percentage.divide(BigDecimal.valueOf(100)));
					// int decimalPlaces = 0;
					BigDecimal bde = new BigDecimal(igst.toString());
					bde = bde.setScale(decimalPlaces, RoundingMode.HALF_UP);
					// System.out.println(bd);
					// BigDecimal bd1= bd.add(bd);
					BigDecimal de = bde.add(bd);
					up.get().setSp_inv_cgst("0");
					up.get().setSp_inv_sgst("0");
					up.get().setSp_inv_igst(f.format(bde).toString());
					up.get().setSp_inv_tot_gst(f.format(bde).toString());
					up.get().setSp_inv_tot_amt(f.format(de).toString());
				} else if ((up.get().getSp()).equals("MISCOT")) {
					String o = up.get().getSp_rate().replace(",", "");

					String p = o.substring(0, o.indexOf("."));
					// Double p1=Double.parseDouble(GRN);
					BigDecimal bigDecimalValue = new BigDecimal(GRN);
					BigDecimal op1 = bigDecimalValue.multiply(BigDecimal.valueOf(Integer.parseInt(p)));
					// BigDecimal op1=Integer.parseInt(p)*bigDecimalValue;
					System.out.println(op1);
					int decimalPlaces = 0;
					BigDecimal bd = new BigDecimal(op1.toString());
					bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);
					// int o=(Integer.parseInt(up.get().getFixed_amt()))*Integer.parseInt(GRN);
					// System.out.println(o);
					up.get().setSp_inv_amt(f.format(bd).toString());
					System.out.println("KKKKKKKKKKKKKKK" + f.format(bd).toString());
					BigDecimal percentage = BigDecimal.valueOf(18); // Percentage value
					long longValue = op1.longValue();
					BigDecimal igst = BigDecimal.valueOf(longValue)
							.multiply(percentage.divide(BigDecimal.valueOf(100)));
					// int decimalPlaces = 0;
					BigDecimal bde = new BigDecimal(igst.toString());
					bde = bde.setScale(decimalPlaces, RoundingMode.HALF_UP);
					// System.out.println(bd);
					// BigDecimal bd1= bd.add(bd);
					BigDecimal de = bde.add(bd);
					up.get().setSp_inv_cgst("0");
					up.get().setSp_inv_sgst("0");
					up.get().setSp_inv_igst(f.format(bde).toString());
					up.get().setSp_inv_tot_gst(f.format(bde).toString());
					up.get().setSp_inv_tot_amt(f.format(de).toString());
				} else if ((up.get().getSp()).equals("STACKPOS")) {
					System.out.println(up.get().getSp_rate());
					String o = up.get().getSp_rate().replace(",", "");
					String p = o.substring(0, o.indexOf("."));
					BigDecimal bigDecimalValue = new BigDecimal(GRN);
					BigDecimal op1 = bigDecimalValue.multiply(BigDecimal.valueOf(Integer.parseInt(p)));
					// int op1=Integer.parseInt(p)*p1;
					System.out.println(op1);
					int decimalPlaces = 0;
					BigDecimal bd = new BigDecimal(op1.toString());
					bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);

					// int o=(Integer.parseInt(up.get().getFixed_amt()))*Integer.parseInt(GRN);
					// System.out.println(o);
					up.get().setSp_inv_amt(f.format(bd).toString());
					System.out.println("JJJJJJJJJJJJ" + f.format(op1).toString());
					BigDecimal percentage = BigDecimal.valueOf(9); // Percentage value
					long longValue = op1.longValue();
					BigDecimal igst = BigDecimal.valueOf(longValue)
							.multiply(percentage.divide(BigDecimal.valueOf(100)));
					// int decimalPlaces = 0;
					BigDecimal bde = new BigDecimal(igst.toString());
					bde = bde.setScale(decimalPlaces, RoundingMode.HALF_UP);
					// System.out.println(bd);
					BigDecimal bd1 = bde.add(bde);
					BigDecimal de = bd1.add(bd);
					up.get().setSp_inv_cgst(f.format(bde).toString());
					up.get().setSp_inv_sgst(f.format(bde).toString());
					up.get().setSp_inv_tot_gst(f.format(bd1).toString());
					up.get().setSp_inv_tot_amt(f.format(de).toString());
				}
				 else if ((up.get().getSp()).equals("WHITESTONE")) {
					 String o = up.get().getFixed_amt().replace(",", "");
						String p = o.substring(0, o.indexOf("."));
						BigDecimal bigDecimalValue = new BigDecimal(GRN);
						BigDecimal op1 = bigDecimalValue.multiply(BigDecimal.valueOf(Integer.parseInt(p)));
						System.out.println(op1);
						// int o=(Integer.parseInt(up.get().getFixed_amt()))*Integer.parseInt(GRN);
						// System.out.println(o);
						
						int decimalPlaces = 0;
						BigDecimal bd = new BigDecimal(op1.toString());
						bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);
						
						up.get().setSp_inv_amt(f.format(bd).toString());
						System.out.println("LLLLLLLLLLLLLLLLL" + f.format(bd).toString());
						BigDecimal percentage = BigDecimal.valueOf(9); // Percentage value
						long longValue = op1.longValue();
						BigDecimal cgst = BigDecimal.valueOf(longValue)
								.multiply(percentage.divide(BigDecimal.valueOf(100)));
						//int decimalPlaces = 0;
						BigDecimal bde = new BigDecimal(cgst.toString());
						bde = bde.setScale(decimalPlaces, RoundingMode.HALF_UP);
						System.out.println(bde);
						BigDecimal bd1 = bde.add(bde);
						BigDecimal de = bd1.add(bd);
						up.get().setSp_inv_cgst(f.format(bde).toString());
						up.get().setSp_inv_sgst(f.format(bde).toString());
						up.get().setSp_inv_tot_gst(f.format(bd1).toString());
						up.get().setSp_inv_tot_amt(f.format(de).toString());

				 }
				
				placementMaintenanceRep.save(up.get());

			}

		} else if (op.isPresent()) {

			if (op.get().getLocation().equals("CHENNAI")) {

				System.out.println("******************" + Effort + GRN + Amount);

				System.out.println(up.get().getLocation());

				double i = Double.valueOf(Amount);

				DecimalFormat f = new DecimalFormat("#,##,##0.00");

				System.out.println(f.format(i));

				// op.get().setPo_id(PO+EmpNo+DeliveryDate);

				op.get().setEmp_id(EmpNo);

				op.get().setGrn_efforts(GRN);

				op.get().setGrn_no(Effort);

				op.get().setGrn_amt(f.format(i));

				System.out.println("------------->>>>>>>>>>>>>" + op.get().setGrn_amt(f.format(i)));

				// String amt = Amount;

				// Double GrnAmt = Double.parseDouble(Amount);

				System.out.println(Amount);

				// System.out.println("<<<<<>>>>"+GrnAmt);

				// double Gst_val = GrnAmt*18/100;

				// System.out.println(f.format(Gst_val));

				// int grnint = Double.valueOf(GRN).intValue();

				// BigDecimal totint = new BigDecimal(GrnAmt);

				// System.out.println(totint+"<<<<<>>>>>");

				op.get().setInv_igst("0");

				op.get().setInv_tot_gst("0");

				op.get().setInv_tot_amt(String.valueOf(op.get().setGrn_amt(f.format(i))));

				op.get().setGrn_date(GRNDate);

				// up.get().setGrn_no(GRN);
				
				if (op.get().getSp()==null) {
									} else if ((op.get().getSp()).equals("ASOFT")) {
					String o = up.get().getSp_rate().replace(",", "");

					String p = o.substring(0, o.indexOf("."));
					// Double p1=Double.parseDouble(GRN);
					BigDecimal bigDecimalValue = new BigDecimal(GRN);
					BigDecimal op1 = bigDecimalValue.multiply(BigDecimal.valueOf(Integer.parseInt(p)));
					// BigDecimal op1=Integer.parseInt(p)*bigDecimalValue;
					System.out.println(op1);
					int decimalPlaces = 0;
					BigDecimal bd = new BigDecimal(op1.toString());
					bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);
					// int o=(Integer.parseInt(up.get().getFixed_amt()))*Integer.parseInt(GRN);
					// System.out.println(o);
					up.get().setSp_inv_amt(f.format(bd).toString());
					System.out.println("KKKKKKKKKKKKKKK" + f.format(bd).toString());
					BigDecimal percentage = BigDecimal.valueOf(18); // Percentage value
					long longValue = op1.longValue();
					BigDecimal igst = BigDecimal.valueOf(longValue)
							.multiply(percentage.divide(BigDecimal.valueOf(100)));
					// int decimalPlaces = 0;
					BigDecimal bde = new BigDecimal(igst.toString());
					bde = bde.setScale(decimalPlaces, RoundingMode.HALF_UP);
					// System.out.println(bd);
					// BigDecimal bd1= bd.add(bd);
					BigDecimal de = bde.add(bd);
					up.get().setSp_inv_cgst("0");
					up.get().setSp_inv_sgst("0");
					up.get().setSp_inv_igst(f.format(bde).toString());
					up.get().setSp_inv_tot_gst(f.format(bde).toString());
					up.get().setSp_inv_tot_amt(f.format(de).toString());
				}  else if ((op.get().getSp()).equals("MISCOT")) {
					String o = up.get().getSp_rate().replace(",", "");

					String p = o.substring(0, o.indexOf("."));
					// Double p1=Double.parseDouble(GRN);
					BigDecimal bigDecimalValue = new BigDecimal(GRN);
					BigDecimal op1 = bigDecimalValue.multiply(BigDecimal.valueOf(Integer.parseInt(p)));
					// BigDecimal op1=Integer.parseInt(p)*bigDecimalValue;
					System.out.println(op1);
					int decimalPlaces = 0;
					BigDecimal bd = new BigDecimal(op1.toString());
					bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);
					// int o=(Integer.parseInt(up.get().getFixed_amt()))*Integer.parseInt(GRN);
					// System.out.println(o);
					up.get().setSp_inv_amt(f.format(bd).toString());
					System.out.println("KKKKKKKKKKKKKKK" + f.format(bd).toString());
					BigDecimal percentage = BigDecimal.valueOf(18); // Percentage value
					long longValue = op1.longValue();
					BigDecimal igst = BigDecimal.valueOf(longValue)
							.multiply(percentage.divide(BigDecimal.valueOf(100)));
					// int decimalPlaces = 0;
					BigDecimal bde = new BigDecimal(igst.toString());
					bde = bde.setScale(decimalPlaces, RoundingMode.HALF_UP);
					// System.out.println(bd);
					// BigDecimal bd1= bd.add(bd);
					BigDecimal de = bde.add(bd);
					up.get().setSp_inv_cgst("0");
					up.get().setSp_inv_sgst("0");
					up.get().setSp_inv_igst(f.format(bde).toString());
					up.get().setSp_inv_tot_gst(f.format(bde).toString());
					up.get().setSp_inv_tot_amt(f.format(de).toString());
				}
									else if ((op.get().getSp()).equals("STACKPOS")) {
					System.out.println(up.get().getSp_rate());
					String o = up.get().getSp_rate().replace(",", "");
					String p = o.substring(0, o.indexOf("."));
					BigDecimal bigDecimalValue = new BigDecimal(GRN);
					BigDecimal op1 = bigDecimalValue.multiply(BigDecimal.valueOf(Integer.parseInt(p)));
					// int op1=Integer.parseInt(p)*p1;
					System.out.println(op1);
					int decimalPlaces = 0;
					BigDecimal bd = new BigDecimal(op1.toString());
					bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);

					// int o=(Integer.parseInt(up.get().getFixed_amt()))*Integer.parseInt(GRN);
					// System.out.println(o);
					up.get().setSp_inv_amt(f.format(bd).toString());
					System.out.println("JJJJJJJJJJJJ" + f.format(op1).toString());
					BigDecimal percentage = BigDecimal.valueOf(9); // Percentage value
					long longValue = op1.longValue();
					BigDecimal igst = BigDecimal.valueOf(longValue)
							.multiply(percentage.divide(BigDecimal.valueOf(100)));
					// int decimalPlaces = 0;
					BigDecimal bde = new BigDecimal(igst.toString());
					bde = bde.setScale(decimalPlaces, RoundingMode.HALF_UP);
					// System.out.println(bd);
					BigDecimal bd1 = bde.add(bde);
					BigDecimal de = bd1.add(bd);
					up.get().setSp_inv_cgst(f.format(bde).toString());
					up.get().setSp_inv_sgst(f.format(bde).toString());
					up.get().setSp_inv_tot_gst(f.format(bd1).toString());
					up.get().setSp_inv_tot_amt(f.format(de).toString());
				}else if ((op.get().getSp()).equals("WHITESTONE")) {
					 String o = up.get().getFixed_amt().replace(",", "");
						String p = o.substring(0, o.indexOf("."));
						BigDecimal bigDecimalValue = new BigDecimal(GRN);
						BigDecimal op1 = bigDecimalValue.multiply(BigDecimal.valueOf(Integer.parseInt(p)));
						System.out.println(op1);
						// int o=(Integer.parseInt(up.get().getFixed_amt()))*Integer.parseInt(GRN);
						// System.out.println(o);
						
						int decimalPlaces = 0;
						BigDecimal bd = new BigDecimal(op1.toString());
						bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);
						
						up.get().setSp_inv_amt(f.format(bd).toString());
						System.out.println("LLLLLLLLLLLLLLLLL" + f.format(bd).toString());
						BigDecimal percentage = BigDecimal.valueOf(9); // Percentage value
						long longValue = op1.longValue();
						BigDecimal cgst = BigDecimal.valueOf(longValue)
								.multiply(percentage.divide(BigDecimal.valueOf(100)));
						//int decimalPlaces = 0;
						BigDecimal bde = new BigDecimal(cgst.toString());
						bde = bde.setScale(decimalPlaces, RoundingMode.HALF_UP);
						System.out.println(bde);
						BigDecimal bd1 = bde.add(bde);
						BigDecimal de = bd1.add(bd);
						up.get().setSp_inv_cgst(f.format(bde).toString());
						up.get().setSp_inv_sgst(f.format(bde).toString());
						up.get().setSp_inv_tot_gst(f.format(bd1).toString());
						up.get().setSp_inv_tot_amt(f.format(de).toString());

				 }


				placementMaintenanceRep.save(up.get());

			}

			else {

				System.out.println("**************" + Effort + GRN + Amount);

				System.out.println(op.get().getLocation());

				double i = Double.valueOf(Amount);

				DecimalFormat f = new DecimalFormat("#,##,##0.00");

				System.out.println(f.format(i));

				op.get().setGrn_efforts(GRN);

				op.get().setGrn_no(Effort);
				op.get().setEmp_id(EmpNo);
				op.get().setGrn_amt(f.format(i));

				// String amt = Amount;

				Double GrnAmt = Double.parseDouble(Amount);

				System.out.println(Amount);

				System.out.println("<<<<<>>>>" + GrnAmt);

				double Gst_val = GrnAmt * 18 / 100;

				System.out.println(f.format(Gst_val));

				int grnint = Double.valueOf(GRN).intValue();

				BigDecimal totint = new BigDecimal(GrnAmt + Math.round(Gst_val));

				System.out.println(totint + "<<<<<>>>>>");

				op.get().setInv_igst(String.valueOf(f.format(Math.round(Gst_val))));

				op.get().setInv_tot_gst(String.valueOf(f.format(Math.round(Gst_val))));

				op.get().setInv_tot_amt(String.valueOf(f.format(totint)));

				op.get().setGrn_date(GRNDate);

				System.out.println("||||||||||||||||"+op.get().getSp());
				// up.get().setGrn_no(GRN);
				if (op.get().getSp()==null) {
									
				} else if ((op.get().getSp()).equals("ASOFT")) {
					String o = op.get().getSp_rate().replace(",", "");

					String p = o.substring(0, o.indexOf("."));
					// Double p1=Double.parseDouble(GRN);
					BigDecimal bigDecimalValue = new BigDecimal(GRN);
					BigDecimal op1 = bigDecimalValue.multiply(BigDecimal.valueOf(Integer.parseInt(p)));
					// BigDecimal op1=Integer.parseInt(p)*bigDecimalValue;
					System.out.println(op1);
					int decimalPlaces = 0;
					BigDecimal bd = new BigDecimal(op1.toString());
					bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);
					// int o=(Integer.parseInt(up.get().getFixed_amt()))*Integer.parseInt(GRN);
					// System.out.println(o);
					op.get().setSp_inv_amt(f.format(bd).toString());
					System.out.println("KKKKKKKKKKKKKKK" + f.format(bd).toString());
					BigDecimal percentage = BigDecimal.valueOf(18); // Percentage value
					long longValue = op1.longValue();
					BigDecimal igst = BigDecimal.valueOf(longValue)
							.multiply(percentage.divide(BigDecimal.valueOf(100)));
					// int decimalPlaces = 0;
					BigDecimal bde = new BigDecimal(igst.toString());
					bde = bde.setScale(decimalPlaces, RoundingMode.HALF_UP);
					// System.out.println(bd);
					// BigDecimal bd1= bd.add(bd);
					BigDecimal de = bde.add(bd);
					op.get().setSp_inv_cgst("0");
					op.get().setSp_inv_sgst("0");
					op.get().setSp_inv_igst(f.format(bde).toString());
					op.get().setSp_inv_tot_gst(f.format(bde).toString());
					op.get().setSp_inv_tot_amt(f.format(de).toString());
				}else if ((op.get().getSp()).equals("MISCOT")) {
					String o = op.get().getSp_rate().replace(",", "");

					String p = o.substring(0, o.indexOf("."));
					// Double p1=Double.parseDouble(GRN);
					BigDecimal bigDecimalValue = new BigDecimal(GRN);
					BigDecimal op1 = bigDecimalValue.multiply(BigDecimal.valueOf(Integer.parseInt(p)));
					// BigDecimal op1=Integer.parseInt(p)*bigDecimalValue;
					System.out.println(op1);
					int decimalPlaces = 0;
					BigDecimal bd = new BigDecimal(op1.toString());
					bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);
					// int o=(Integer.parseInt(up.get().getFixed_amt()))*Integer.parseInt(GRN);
					// System.out.println(o);
					op.get().setSp_inv_amt(f.format(bd).toString());
					System.out.println("KKKKKKKKKKKKKKK" + f.format(bd).toString());
					BigDecimal percentage = BigDecimal.valueOf(18); // Percentage value
					long longValue = op1.longValue();
					BigDecimal igst = BigDecimal.valueOf(longValue)
							.multiply(percentage.divide(BigDecimal.valueOf(100)));
					// int decimalPlaces = 0;
					BigDecimal bde = new BigDecimal(igst.toString());
					bde = bde.setScale(decimalPlaces, RoundingMode.HALF_UP);
					// System.out.println(bd);
					// BigDecimal bd1= bd.add(bd);
					BigDecimal de = bde.add(bd);
					op.get().setSp_inv_cgst("0");
					op.get().setSp_inv_sgst("0");
					op.get().setSp_inv_igst(f.format(bde).toString());
					op.get().setSp_inv_tot_gst(f.format(bde).toString());
					op.get().setSp_inv_tot_amt(f.format(de).toString());
				} else if ((op.get().getSp()).equals("STACKPOS")) {
					System.out.println(op.get().getSp_rate());
					String o = op.get().getSp_rate().replace(",", "");
					String p = o.substring(0, o.indexOf("."));
					BigDecimal bigDecimalValue = new BigDecimal(GRN);
					BigDecimal op1 = bigDecimalValue.multiply(BigDecimal.valueOf(Integer.parseInt(p)));
					// int op1=Integer.parseInt(p)*p1;
					System.out.println(op1);
					int decimalPlaces = 0;
					BigDecimal bd = new BigDecimal(op1.toString());
					bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);

					// int o=(Integer.parseInt(up.get().getFixed_amt()))*Integer.parseInt(GRN);
					// System.out.println(o);
					op.get().setSp_inv_amt(f.format(bd).toString());
					System.out.println("JJJJJJJJJJJJ" + f.format(op1).toString());
					BigDecimal percentage = BigDecimal.valueOf(9); // Percentage value
					long longValue = op1.longValue();
					BigDecimal igst = BigDecimal.valueOf(longValue)
							.multiply(percentage.divide(BigDecimal.valueOf(100)));
					// int decimalPlaces = 0;
					BigDecimal bde = new BigDecimal(igst.toString());
					bde = bde.setScale(decimalPlaces, RoundingMode.HALF_UP);
					// System.out.println(bd);
					BigDecimal bd1 = bde.add(bde);
					BigDecimal de = bd1.add(bd);
					op.get().setSp_inv_cgst(f.format(bde).toString());
					op.get().setSp_inv_sgst(f.format(bde).toString());
					op.get().setSp_inv_tot_gst(f.format(bd1).toString());
					op.get().setSp_inv_tot_amt(f.format(de).toString());
				}else if ((op.get().getSp()).equals("WHITESTONE")) {
					String o = op.get().getFixed_amt().replace(",", "");
					String p = o.substring(0, o.indexOf("."));
					BigDecimal bigDecimalValue = new BigDecimal(GRN);
					BigDecimal op1 = bigDecimalValue.multiply(BigDecimal.valueOf(Integer.parseInt(p)));
					System.out.println(op1);
					// int o=(Integer.parseInt(up.get().getFixed_amt()))*Integer.parseInt(GRN);
					// System.out.println(o);
					
					int decimalPlaces = 0;
					BigDecimal bd = new BigDecimal(op1.toString());
					bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);
					
					op.get().setSp_inv_amt(f.format(bd).toString());
					System.out.println("LLLLLLLLLLLLLLLLL" + f.format(bd).toString());
					BigDecimal percentage = BigDecimal.valueOf(9); // Percentage value
					long longValue = op1.longValue();
					BigDecimal cgst = BigDecimal.valueOf(longValue)
							.multiply(percentage.divide(BigDecimal.valueOf(100)));
					//int decimalPlaces = 0;
					BigDecimal bde = new BigDecimal(cgst.toString());
					bde = bde.setScale(decimalPlaces, RoundingMode.HALF_UP);
					System.out.println(bde);
					BigDecimal bd1 = bde.add(bde);
					BigDecimal de = bd1.add(bd);
					op.get().setSp_inv_cgst(f.format(bde).toString());
					op.get().setSp_inv_sgst(f.format(bde).toString());
					op.get().setSp_inv_tot_gst(f.format(bd1).toString());
					op.get().setSp_inv_tot_amt(f.format(de).toString());
				}


				placementMaintenanceRep.save(op.get());

			}

		} else {
		}

		Optional<InvoiceMaster> up1 = invoiceMasterRep.findById(po_id);
		Optional<InvoiceMaster> op1 = invoiceMasterRep.findById(po_id1);

		up1 = invoiceMasterRep.findById(po_id);

		if (up1.isPresent()) {

			if (up1.get().getLocation().equals("CHENNAI")) {

				System.out.println(Effort + GRN + Amount);

				System.out.println(up1.get().getLocation());

				double i = Double.valueOf(Amount);

				DecimalFormat f = new DecimalFormat("#,##,##0.00");

				System.out.println(f.format(i));

				up1.get().setGrn_efforts(GRN);

				up1.get().setGrn_no(Effort);

				up1.get().setGrn_amt(f.format(i));

				System.out.println("------------->>>>>>>>>>>>>" + up.get().setGrn_amt(f.format(i)));

				// String amt = Amount;

				// Double GrnAmt = Double.parseDouble(Amount);

				System.out.println(Amount);

				// System.out.println("<<<<<>>>>"+GrnAmt);

				// double Gst_val = GrnAmt*18/100;

				// System.out.println(f.format(Gst_val));

				// int grnint = Double.valueOf(GRN).intValue();

				// BigDecimal totint = new BigDecimal(GrnAmt);

				// System.out.println(totint+"<<<<<>>>>>");

				up1.get().setInv_igst("0");

				up1.get().setInv_tot_gst("0");

				up1.get().setInv_tot_amt(String.valueOf(up.get().setGrn_amt(f.format(i))));

				NumToWords w = new NumToWords();

				// String num=String.valueOf(totint);

				// int ii=Integer.parseInt(num);

				// String j=w.convert((ii));

				// System.out.println(j);

				String bob = Double.toString(Double.valueOf(Amount));

				String[] convert = bob.split("\\.");

				System.out.println("===================>>>>>>>>>>" + bob);

				int a = Integer.parseInt(convert[0]);

				System.out.println(w.convert(a));

				int b = Integer.parseInt(convert[1] + 0);

				if (b == 0) {

					System.out.println();

					System.out.println(w.convert(a)); // 13454

					// System.out.println(EnglishNumberToWords.convert(b)); // 92345

					// String Rupee=EnglishNumberToWords.convert(totint1[]);

					// System.out.println("*** " + EnglishNumberToWords.convert(totint));

					up1.get().setTotal_amt(("(Rupees " + w.convert(a) + " Only)"));

				} else {

					System.out.println(w.convert(a)); // 13454

					System.out.println(w.convert(b)); // 92345

					// String Rupee=EnglishNumberToWords.convert(totint1[]);

					// System.out.println("*** " + EnglishNumberToWords.convert(totint));

					up1.get().setTotal_amt(("(Rupees " + w.convert(a) + " and " + w.convert(b) + " Paise Only)"));

				}

				up1.get().setEnclosures("LUT (ARN: AD330323059503E)");

				up1.get().setGrn_date(GRNDate);

				// up.get().setGrn_no(GRN);

				invoiceMasterRep.save(up1.get());

			} else {

				System.out.println(Amount);

				double i = Double.valueOf(Amount);

				System.out.println(i);

				DecimalFormat f = new DecimalFormat("#,##,##0.00");

				up1.get().setGrn_amt(f.format(i));

				up1.get().setGrn_efforts(GRN);

				up1.get().setGrn_no(Effort);

				up1.get().setGrn_date(GRNDate);

				double GrnAmt = Double.valueOf(Amount);

				double Gst_val = GrnAmt * 18 / 100;

				// String S=Integer.toString(Gst_val);

				// String[] parts = S.split("\\.");

				// System.out.println(parts[0]);

				System.out.println(Math.round(Gst_val));

				int grnint = Double.valueOf(GRN).intValue();

				double totint = GrnAmt + Math.round(Gst_val);

				System.out.println(totint);

				up1.get().setInv_igst(String.valueOf(f.format(Math.round(Gst_val))));

				up1.get().setInv_tot_gst(String.valueOf(f.format(Math.round(Gst_val))));

				up1.get().setInv_tot_amt(String.valueOf(f.format(totint)));

				NumToWords w = new NumToWords();

				// String num=String.valueOf(totint);

				// int ii=Integer.parseInt(num);

				// String j=w.convert((ii));

				// System.out.println(j);

				String bob = Double.toString(totint);

				String[] convert = bob.split("\\.");

				int a = Integer.parseInt(convert[0]);

				System.out.println(w.convert(a));

				int b = Integer.parseInt(convert[1] + 0);

				if (b == 0) {

					System.out.println();

					System.out.println(w.convert(a)); // 13454

					// System.out.println(EnglishNumberToWords.convert(b)); // 92345

					// String Rupee=EnglishNumberToWords.convert(totint1[]);

					// System.out.println("*** " + EnglishNumberToWords.convert(totint));

					up1.get().setTotal_amt(("(Rupees " + w.convert(a) + " Only)"));

				} else {

					System.out.println(w.convert(a)); // 13454

					System.out.println(w.convert(b)); // 92345

					// String Rupee=EnglishNumberToWords.convert(totint1[]);

					// System.out.println("*** " + EnglishNumberToWords.convert(totint));

					up1.get().setTotal_amt(("(Rupees " + w.convert(a) + " and " + w.convert(b) + " Paise Only)"));

				}

				up1.get().setGrn_date(GRNDate);

				invoiceMasterRep.save(up1.get());

			}

		}

		else if (op1.isPresent()) {

			if (op1.get().getLocation().equals("CHENNAI")) {

				System.out.println(Effort + GRN + Amount);

				System.out.println(up1.get().getLocation());

				double i = Double.valueOf(Amount);

				DecimalFormat f = new DecimalFormat("#,##,##0.00");

				System.out.println(f.format(i));

				// op1.get().setPo_id(PO+EmpNo+DeliveryDate);

				op1.get().setEmp_id(EmpNo);

				op1.get().setGrn_efforts(GRN);

				op1.get().setGrn_no(Effort);

				op1.get().setGrn_amt(f.format(i));

				System.out.println("------------->>>>>>>>>>>>>" + op.get().setGrn_amt(f.format(i)));

				// String amt = Amount;

				// Double GrnAmt = Double.parseDouble(Amount);

				System.out.println(Amount);

				// System.out.println("<<<<<>>>>"+GrnAmt);

				// double Gst_val = GrnAmt*18/100;

				// System.out.println(f.format(Gst_val));

				// int grnint = Double.valueOf(GRN).intValue();

				// BigDecimal totint = new BigDecimal(GrnAmt);

				// System.out.println(totint+"<<<<<>>>>>");

				op1.get().setInv_igst("0");

				op1.get().setInv_tot_gst("0");

				op1.get().setInv_tot_amt(String.valueOf(op.get().setGrn_amt(f.format(i))));

				NumToWords w = new NumToWords();

				// String num=String.valueOf(totint);

				// int ii=Integer.parseInt(num);

				// String j=w.convert((ii));

				// System.out.println(j);

				String bob = Double.toString(Double.valueOf(Amount));

				String[] convert = bob.split("\\.");

				System.out.println("===================>>>>>>>>>>" + bob);

				int a = Integer.parseInt(convert[0]);

				System.out.println(w.convert(a));

				int b = Integer.parseInt(convert[1] + 0);

				if (b == 0) {

					System.out.println();

					System.out.println(w.convert(a)); // 13454

					// System.out.println(EnglishNumberToWords.convert(b)); // 92345

					// String Rupee=EnglishNumberToWords.convert(totint1[]);

					// System.out.println("*** " + EnglishNumberToWords.convert(totint));

					up1.get().setTotal_amt(("(Rupees " + w.convert(a) + " Only)"));

				} else {

					System.out.println(w.convert(a)); // 13454

					System.out.println(w.convert(b)); // 92345

					// String Rupee=EnglishNumberToWords.convert(totint1[]);

					// System.out.println("*** " + EnglishNumberToWords.convert(totint));

					up1.get().setTotal_amt(("(Rupees " + w.convert(a) + " and " + w.convert(b) + " Paise Only)"));

				}

				op1.get().setEnclosures("LUT (ARN: AD330323059503E)");

				op1.get().setGrn_date(GRNDate);

				// up.get().setGrn_no(GRN);

				invoiceMasterRep.save(op1.get());

			} else {

				System.out.println(Amount);

				double i = Double.valueOf(Amount);

				System.out.println(i);

				DecimalFormat f = new DecimalFormat("#,##,##0.00");

				op1.get().setGrn_amt(f.format(i));

				op1.get().setGrn_efforts(GRN);

				op1.get().setGrn_no(Effort);

				op1.get().setGrn_date(GRNDate);

				op1.get().setEmp_id(EmpNo);

				op1.get().setPo_id(po_id1);

				double GrnAmt = Double.valueOf(Amount);

				double Gst_val = GrnAmt * 18 / 100;

				// String S=Integer.toString(Gst_val);

				// String[] parts = S.split("\\.");

				// System.out.println(parts[0]);

				System.out.println(Math.round(Gst_val));

				int grnint = Double.valueOf(GRN).intValue();

				double totint = GrnAmt + Math.round(Gst_val);

				System.out.println(totint);

				op1.get().setInv_igst(String.valueOf(f.format(Math.round(Gst_val))));

				op1.get().setInv_tot_gst(String.valueOf(f.format(Math.round(Gst_val))));

				op1.get().setInv_tot_amt(String.valueOf(f.format(totint)));

				NumToWords w = new NumToWords();

				// String num=String.valueOf(totint);

				// int ii=Integer.parseInt(num);

				// String j=w.convert((ii));

				// System.out.println(j);

				String bob = Double.toString(totint);

				String[] convert = bob.split("\\.");

				int a = Integer.parseInt(convert[0]);

				System.out.println(w.convert(a));

				int b = Integer.parseInt(convert[1] + 0);

				if (b == 0) {

					System.out.println();

					System.out.println(w.convert(a)); // 13454

					// System.out.println(EnglishNumberToWords.convert(b)); // 92345

					// String Rupee=EnglishNumberToWords.convert(totint1[]);

					// System.out.println("*** " + EnglishNumberToWords.convert(totint));

					op1.get().setTotal_amt(("(Rupees " + w.convert(a) + " Only)"));

				} else {

					System.out.println(w.convert(a)); // 13454

					System.out.println(w.convert(b)); // 92345

					// String Rupee=EnglishNumberToWords.convert(totint1[]);

					// System.out.println("*** " + EnglishNumberToWords.convert(totint));

					op1.get().setTotal_amt(("(Rupees " + w.convert(a) + " and " + w.convert(b) + " Paise Only)"));

				}

				op1.get().setGrn_date(GRNDate);

				invoiceMasterRep.save(op1.get());

			}

		}

		// msg = "PlacementMaintenance Added Successfully";

		return null;

		// TODO Auto-generated method stub

	}

	private BigDecimal BigDecimal(int op1) {
		// TODO Auto-generated method stub
		return null;
	}

	public void main(String[] args) {

		String host = "sg2plcpnl0144.prod.sin2.secureserver.net";// change accordingly

		String mailStoreType = "pop3";

		String username = "vijay.r@bornfire.in";

		String password = "Vijay@123";// change accordingly

		// receiveEmail(host, mailStoreType, username, password);

	}

	private String getTextFromMessage(Message message) throws MessagingException, IOException {

		if (message.isMimeType("text/plain")) {

			return message.getContent().toString();

		}

		if (message.isMimeType("multipart/*")) {

			MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();

			return getTextFromMimeMultipart(mimeMultipart);

		}

		return "";

	}

	private String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws MessagingException, IOException {

		String result = "";

		for (int i = 0; i < mimeMultipart.getCount(); i++) {

			BodyPart bodyPart = mimeMultipart.getBodyPart(i);

			if (bodyPart.isMimeType("text/plain")) {

				return result + "\n" + bodyPart.getContent(); // without return, same text appears twice in my tests

			}

			result += this.parseBodyPart(bodyPart);

		}

		return result;

	}

	private String parseBodyPart(BodyPart bodyPart) throws MessagingException, IOException {

		if (bodyPart.isMimeType("text/html")) {

			return "\n" + org.jsoup.Jsoup.parse(bodyPart.getContent().toString()).text();

		}

		if (bodyPart.getContent() instanceof MimeMultipart) {

			return getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());

		}

		return "";

	}

}
