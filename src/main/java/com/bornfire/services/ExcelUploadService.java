package com.bornfire.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.persistence.Id;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bornfire.entities.InvoiceMaster;
import com.bornfire.entities.InvoiceMasterRep;
import com.bornfire.entities.IssueTracker;
import com.bornfire.entities.IssueTrackerRep;
import com.bornfire.entities.PlacementMaintenance;
import com.bornfire.entities.PlacementMaintenanceRep;

@Service
public class ExcelUploadService {

	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	IssueTrackerRep issueTrackerRep;
	@Autowired
	PlacementMaintenanceRep placementMaintenanceRep;
	@Autowired
	InvoiceMasterRep invoiceMasterRep;
	
	@Autowired
	checkservice checkservice;
	
	
	@Autowired
	InvoicesubmitService invoicesubmitService;
	@SuppressWarnings("deprecation")
	public List<IssueTracker> UploadIssue(String screenId, MultipartFile file, String userid, IssueTracker issuetraker)
			throws SQLException, FileNotFoundException, IOException {

		String fileName = file.getOriginalFilename();

		String fileExt = "";
		String msg = "";

		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			fileExt = fileName.substring(i + 1);
		}

		List<IssueTracker> result = new ArrayList<IssueTracker>();
		if (fileExt.equals("xlsx") || fileExt.equals("xls")) {

			if (fileName.contains("IssueTracker")) {

				try {

					/*
					 * Workbook workbook =
					 * StreamingReader.builder().rowCacheSize(100).bufferSize(4096)
					 * .open(file.getInputStream());
					 */
					Workbook workbook = WorkbookFactory.create(file.getInputStream());

					List<HashMap<Integer, String>> mapList = new ArrayList<>();
					for (Sheet s : workbook) {
						for (Row r : s) {

							if (!isRowEmpty(r)) {
								if (r.getRowNum() == 0) {
									continue;
								}

								String val = null;

								HashMap<Integer, String> map = new HashMap<>();

								for (int j = 0; j < 28; j++) {

									Cell cell = r.getCell(j);
									if (cell.getCellType() == 0) {
										int val1 = (int) cell.getNumericCellValue();
										val = String.valueOf(val1);
										map.put(j, val);
									} /*
										 * else if(cell.getCellType()== 1) { Date val1 = cell.getDateCellValue();
										 * map.put(j, val); }
										 */ else {
										val = cell.getStringCellValue();
										map.put(j, val);
									}
								}

								mapList.add(map);

							}

						}

					}
					for (HashMap<Integer, String> item : mapList) {
						IssueTracker issue = new IssueTracker();

						Optional<IssueTracker> issueList = issueTrackerRep.findById(item.get(0));
						if (item.get(0) == "") {
							msg = "!--------------------------Excel Sheet Empty--------------------------------!";
							System.out.println(msg);
						} else if (issueList.isPresent()) {
							msg = "!--------------------------------Issue Serial Already Exist--------------------------------------!";
							System.out.println(msg);
						} else {
							issue.setSrl_no(item.get(0));
							issue.setCategory(item.get(1));
							issue.setGroups(item.get(2));
							issue.setProduct(item.get(3));
							issue.setModule(item.get(4));
							issue.setScreen(item.get(5));
							issue.setOperation(item.get(6));
							issue.setOper_desc(item.get(7));
							issue.setIssue_ref_no(item.get(8));
							String sDate1 = item.get(9);
							Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
							issue.setDate_of_issue(date1);
							issue.setRpt_by(item.get(10));
							issue.setApr_by(item.get(11));
							issue.setNat_of_issue(item.get(12));
							issue.setIssue_details(item.get(13));
							issue.setIssue_severity(item.get(14));
							issue.setIssue_rmks(item.get(15));
							issue.setAssign_to(item.get(16));
							String sDate2 = item.get(17);
							Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate2);

							issue.setAssign_date(date2);
							String fix = item.get(18);
							BigDecimal fix1 = new BigDecimal(fix);
							issue.setFix_period(fix1);
							String sDate3 = item.get(19);
							Date date3 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate3);
							issue.setDel_date(date3);
							issue.setFix_details(item.get(20));
							String sDate4 = item.get(21);
							Date date4 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate4);
							issue.setDate_of_fix(date4);
							issue.setTest_by(item.get(22));
							String sDate5 = item.get(23);
							Date date5 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate5);
							issue.setTest_date(date5);
							issue.setTest_results(item.get(24));
							issue.setIssue_status(item.get(25));
							String turn = item.get(26);
							BigDecimal turn1 = new BigDecimal(turn);
							issue.setTat_per(turn1);
							issue.setFinal_cls(item.get(27));
							issue.setDel_flg("N");

							result.add(issue);

							issueTrackerRep.save(issue);

							msg = "!-------------------------------Excel Data Uploaded Successfully--------------------------------------!";
							System.out.println(msg);

						}

					}

					return result;

				} catch (Exception e) {
					e.printStackTrace();
					throw new CustomException("File has not been successfully uploaded");
				}
			} else {
				throw new CustomException("Invalid File Name");
			}
		}
		return result;
	}

	public String UploadPO(String screenId, MultipartFile file, String userid,
			PlacementMaintenance placementMaintenance) throws SQLException, FileNotFoundException, IOException {

		String fileName = file.getOriginalFilename();

		String fileExt = "";
		String msg = "";

		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			fileExt = fileName.substring(i + 1);
		}

		ArrayList<PlacementMaintenance> result = new ArrayList<PlacementMaintenance>();
		if (fileExt.equals("xlsx") || fileExt.equals("xls")) {

			// if (fileName.contains("PO_Master")) {

			try {

				/*
				 * Workbook workbook =
				 * StreamingReader.builder().rowCacheSize(100).bufferSize(4096)
				 * .open(file.getInputStream());
				 */
				Workbook workbook = WorkbookFactory.create(file.getInputStream());

				List<HashMap<Integer, String>> mapList = new ArrayList<>();
				for (Sheet s : workbook) {
					for (Row r : s) {

						if (!isRowEmpty(r)) {
							if (r.getRowNum() == 0) {
								continue;
							}

							String val = null;

							HashMap<Integer, String> map = new HashMap<>();

							for (int j = 0; j < 50; j++) {

								Cell cell = r.getCell(j);
								DataFormatter formatter = new DataFormatter();
								String text = formatter.formatCellValue(cell);
								map.put(j, text);
								/*
								 * if (cell.getCellComment()==CellType.NUMERIC) { int val1 =
								 * (int)cell.getNumericCellValue(); val = String.valueOf(val1); map.put(j, val);
								 * }
								 * 
								 * else if(cell.getCellType() == CellType.STRING){ val =
								 * cell.getStringCellValue(); map.put(j, val);
								 * 
								 * }
								 */
							}
							mapList.add(map);

						}

					}

				}
				
				for (HashMap<Integer, String> item : mapList) {
					PlacementMaintenance PO = new PlacementMaintenance();
					//InvoiceMaster IM=new InvoiceMaster();
					String sDat2 = item.get(3);
					String p;
					System.out.println(sDat2);
					System.out.println(sDat2.charAt(2));
					if (sDat2.charAt(2) == '/') {
			            System.out.println(sDat2.substring(2, 3));
			            String g[] = sDat2.split("/");

			            for (int i1 = 0; i1 < g.length; i1++) {
			                System.out.println("Element at index " + i1 + ": " + g[i1]);
			            }

			            if (g[2].length() == 2) {
			                p = g[1] + "-" + g[0] + "-20" + g[2];
			                System.out.println("Formatted date:???????????? " + p);
			            } else {
			                p = g[1] + "-" + g[0] + "-" + g[2];
			                System.out.println("Formatted--------- date: " + p);
			            }
			        }
					else {
						 p=sDat2;
						 System.out.println("::::::::::"+p);
					}
					 try {
						 System.out.println("{{{{{{{{{}}}}}}}}}}}}}}}}}"+p);
					SimpleDateFormat formatdate = new SimpleDateFormat("ddMMyyyy");
					Date dat2 = new SimpleDateFormat("dd-MM-yyyy").parse(p);
					String m = formatdate.format(dat2);
					System.out.println(m);
					 } catch (ParseException e) {
					        e.printStackTrace();
					        msg="PO Date Not Valid";
					        return msg;
					    }
					 String sDate3 = item.get(21);
						if(item.get(21)!="") {
					 try {
				Date date3 = new SimpleDateFormat("dd-MM-yyyy").parse(sDate3);
				
					  } catch (ParseException e) {
					        e.printStackTrace();
					        msg="PO Delivery Date Not Valid";
					        return msg;
					    }
				}else {
				}
				/*
				 * String sDate4 = item.get(35); if(item.get(35)!="") { try { Date date4 = new
				 * SimpleDateFormat("dd-MM-yyyy").parse(sDate4); } catch (ParseException e) {
				 * e.printStackTrace(); msg="GRN Date Not Valid"; return msg; } }else {
				 * 
				 * }
				 */
					 SimpleDateFormat formatdate = new SimpleDateFormat("ddMMyyyy");
					 Date date3 = new SimpleDateFormat("dd-MM-yyyy").parse(sDate3);
						String m = formatdate.format(date3);
						
						
					Optional<PlacementMaintenance> POList = placementMaintenanceRep
							.findById(item.get(2) + item.get(14) + m);
					
					if (item.get(0) == "") {
						msg = "Excel Sheet Empty";
						System.out.println(msg);
					} else if (POList.isPresent()) {
						msg = "PO Already Exist";
						System.out.println(msg);
					} else {
						DecimalFormat numformate = new DecimalFormat("");
						BigDecimal PlacementSrlNo = (BigDecimal) sessionFactory.getCurrentSession()
								.createNativeQuery("SELECT PM_SRL_NO.NEXTVAL AS SRL_NO FROM DUAL").getSingleResult();
						String pmsrlno = numformate.format(PlacementSrlNo);
						
						PO.setSrl_no(pmsrlno);
						
						PO.setPo_id(item.get(2) + item.get(14) + m);
						
						PO.setVendor(item.get(0));
						//IM.setVendor(item.get(0));
						PO.setLocation(item.get(1));
						if(item.get(2).length()!=10){
							PO.setPo_no(item.get(2));
							PO.setFlag('N');
							PO.setMessage("Po No Must Be 10 Digit");
						}else {
						PO.setPo_no(item.get(2));
						PO.setMessage("SUCCESS");
						PO.setFlag('Y');
					}
						String sDate2 = item.get(3);
						if(sDate2!="") {
						Date date2 = new SimpleDateFormat("dd-MM-yyyy").parse(p);
						PO.setPo_date(date2);
						}else {
							
						}
						PO.setUnit_loc(item.get(11));
						PO.setProj_mgr(item.get(6));
						PO.setPm_email(item.get(7));
						PO.setGstin(item.get(12));
						PO.setNo_of_items(item.get(15));
						PO.setEmp_name(item.get(13));
						PO.setEmp_id(item.get(14));
						PO.setPo_item_no(item.get(17));
						PO.setPo_qty(item.get(18));
						
						/*
						 * int i1= Double.valueOf(item.get(19)).intValue(); System.out.println(i1);
						 * DecimalFormat f = new DecimalFormat("#,##,##0.00");
						 */
						
						
						System.out.println(">>>>>>>>>>");
						
						System.out.println("??????????????"+item.get(19));
						
						
						/*
						 * String P=item.get(19); System.out.println(new BigDecimal(P)); double i1=
						 * Double.valueOf(P); System.out.println(); DecimalFormat f = new
						 * DecimalFormat("#,##,##0.00"); System.out.println(f.format(i1));
						 */
						System.out.println(formatLakh(Double.valueOf(item.get(19))));
						//System.out.println();
						PO.setPo_rate_inr(formatLakh(Double.valueOf(item.get(19))));
						PO.setPo_amt_inr(item.get(20));
						PO.setExtn_flg(item.get(4));
						
						String eDate2 = item.get(5);
						if(eDate2!="") {
						Date edate = new SimpleDateFormat("dd-MM-yyyy").parse(eDate2);
						PO.setExtn_date(edate);
						}else {
							
						}
						
						if(sDate3!="") {
						Date date31 = new SimpleDateFormat("dd-MM-yyyy").parse(sDate3);
						PO.setPo_delivery_date(date31);
						}else {
							
						}
						System.out.println("''''''''''''''''''"+item.get(20));
						System.out.println();
						
						// PO.setSp(item.get(22));
						 //System.out.println(formatLakh(Double.valueOf(item.get(23))));
						 
						// PO.setSp_rate(formatLakh(Double.valueOf(item.get(23))));
						// System.out.println(formatLakh(Double.valueOf(item.get(24))));
						// System.out.println(item.get(22));
						// System.out.println(item.get(23));
						 if(item.get(22)==null ||  (item.get(22)=="")) {
							
						} else {
							 PO.setSp(item.get(22)); 
							 System.out.println("---------------"+item.get(22));
						}
						 
						 
						 if(item.get(23)==null ||  item.get(23)=="" ) {
							
							 } else {
								 System.out.println("))))))))))"+formatLakh(Double.valueOf(item.get(23))));
								 PO.setSp_rate(formatLakh(Double.valueOf(item.get(23))));
							 }
						 PO.setRate_mode(item.get(24)); 
						 
						 System.out.println(item.get(25)); 
						 if (item.get(25) == null || item.get(25)=="") {
						 
						 }else { 
							 System.out.println("(((((((((("+formatLakh(Double.valueOf(item.get(25))));
							 PO.setFixed_amt(formatLakh(Double.valueOf(item.get(25))));
							 }
						 if(item.get(26)==null || item.get(26)=="") { 
							
						 }else {
							 PO.setPercent(item.get(26));
							 }
						
						PO.setInv_tot_amt(item.get(31));
						PO.setPo_month(item.get(33));
						PO.setBill_total_amt(item.get(16));
						PO.setCin(item.get(34));
						PO.setPan(item.get(35));
						PO.setVendor1(item.get(37));
						PO.setGstin_1(item.get(36));
						PO.setHsn(item.get(38));
						PO.setBank_name(item.get(39));
						PO.setAcct_type(item.get(40));
						PO.setAcct_no(item.get(41));
						PO.setAcct_name(item.get(42));
						PO.setIfsc_code(item.get(43));
						PO.setSwift_code(item.get(44));
						//IM.setInv_no("4356362377");
						result.add(PO);
						Calendar cal = Calendar.getInstance();
						SimpleDateFormat login = new SimpleDateFormat("dd-MMM-yy");
						String date = login.format(cal.getTime());
                        PO.setUpload_date(date);
                        
						placementMaintenanceRep.save(PO);
						//invoiceMasterRep.save(IM);
						 
						//checkservice.extsubmit(PO);
							
						
						
						
						
					//	System.out.println(datainvo.getPo_no());
						

                       
                        
						//invoiceMasterRep.save(datainvo);

						msg = "Excel Data Uploaded Successfully";

		
					}
					
				}
				
				
				String msg2 = invoicesubmitService.checksub(mapList);
				System.out.println(msg2);
				

				return msg;

			} catch (Exception e) {
				e.printStackTrace();
				msg = "File has not been successfully uploaded";
			}

			/*
			 * } else { msg="Invalid File Name"; }
			 */
		}
		return msg;

	}
	
	private static String formatLakh(double d) {
	    String s = String.format(Locale.UK, "%1.2f", Math.abs(d));
	    s = s.replaceAll("(.+)(...\\...)", "$1,$2");
	    while (s.matches("\\d{3,},.+")) {
	        s = s.replaceAll("(\\d+)(\\d{2},.+)", "$1,$2");
	    }
	    return d < 0 ? ("-" + s) : s;
	}
	
	private static boolean isRowEmpty(Row row) {
		boolean isEmpty = true;
		DataFormatter dataFormatter = new DataFormatter();

		if (row != null) {
			for (Cell cell : row) {
				if (dataFormatter.formatCellValue(cell).trim().length() > 0) {
					isEmpty = false;
					break;
				}
			}
		}
		return isEmpty;
	}
}