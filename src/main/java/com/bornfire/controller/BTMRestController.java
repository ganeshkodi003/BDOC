package com.bornfire.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.bornfire.config.SequenceGenerator;
import com.bornfire.entities.AssociateResponseModel;
import com.bornfire.entities.AttendanceID;
import com.bornfire.entities.AttendanceRegister;
import com.bornfire.entities.AttendanceRegisterGet;
import com.bornfire.entities.AttendanceRegisterGetRep;
import com.bornfire.entities.AttendanceRegisterRep;
import com.bornfire.entities.AttendenaceresponseModel;
import com.bornfire.entities.BGLSBusinessTable_Entity;
import com.bornfire.entities.BGLSBusinessTable_Rep;
import com.bornfire.entities.BTMAdminAssociateProfile;
import com.bornfire.entities.BTMAdminAssociateProfileRep;
import com.bornfire.entities.BTMAdminHolidayMasterRep;
import com.bornfire.entities.BTMEmpTimeSheet;
import com.bornfire.entities.BTMEmpTimeSheetRep;
import com.bornfire.entities.BTMProjectMaster;
import com.bornfire.entities.BTMProjectMasterRep;
import com.bornfire.entities.Baj_Sal_Work_Entity;
import com.bornfire.entities.CandEvalFormEntity;
import com.bornfire.entities.Chart_Acc_Entity;
import com.bornfire.entities.Chart_Acc_Rep;
import com.bornfire.entities.HRMS_USER_PROFILE_ENTITY;
import com.bornfire.entities.HRMS_USER_PROFILE_REPOSITORY;
import com.bornfire.entities.IssueTracker;
import com.bornfire.entities.Lease_Loan_Master_Entity;
import com.bornfire.entities.Lease_Loan_Master_Repo;
import com.bornfire.entities.PlacementMaintenance;
import com.bornfire.entities.PlacementMaintenanceRep;
import com.bornfire.entities.Reference_Code_Entity;
import com.bornfire.entities.Reference_code_Rep;
import com.bornfire.entities.ResourceMaster;
import com.bornfire.entities.ResourceMasterRepo;
import com.bornfire.entities.Salary_Pay_Entity;
import com.bornfire.entities.TRAN_MAIN_TRM_WRK_ENTITY;
import com.bornfire.entities.TRAN_MAIN_TRM_WRK_REP;
 
import com.bornfire.entities.paystructureentity;
import com.bornfire.entities.paystructurerep;
import com.bornfire.services.AckReciever;
import com.bornfire.services.BHMS_Services;
import com.bornfire.services.CustomException;
import com.bornfire.services.MainApplication;
import com.bornfire.services.OnDutyServices;
import com.bornfire.services.RecievingMail;
import com.bornfire.services.RecievingMail2;
import com.bornfire.services.Spsendingmail;
import com.bornfire.services.Timesheetmaster;
import com.bornfire.services.sendingmail;
import com.ibm.icu.text.SimpleDateFormat;

@RestController
@Transactional
//@RequestMapping(value = "BTM")
public class BTMRestController {
	@Autowired
	OnDutyServices onDutyServices;
	

	@Autowired 
	HRMS_USER_PROFILE_REPOSITORY hrms_userprofile_repo;

	@Autowired
	Reference_code_Rep reference_code_Rep;
	
	
	@Autowired
	Chart_Acc_Rep chart_Acc_Rep;
	
	@Autowired
	ResourceMasterRepo ResourceMasterRepo;
	
	@Autowired
	SequenceGenerator sequence;
	
	@Autowired
	BGLSBusinessTable_Rep bGLSBusinessTable_Rep;

	@Autowired
	Timesheetmaster timesheetmaster;

	@Autowired
	BHMS_Services BHMS_services;
	
	@Autowired
	RecievingMail recievingMail;
	
	@Autowired
	sendingmail Sendingmail;
	
	@Autowired
	TRAN_MAIN_TRM_WRK_REP tRAN_MAIN_TRM_WRK_REP;

	
	@Autowired
	Spsendingmail spsendingmail;

	@Autowired
	RecievingMail2 recievingMail2;

	@Autowired
	BTMAdminAssociateProfileRep btmAdminAssociateProfileRep;

	@Autowired
	PlacementMaintenanceRep placementMaintenanceRep;

	@Autowired
	BTMProjectMasterRep btmProjectMasterRep;

	@Autowired
	AttendanceRegisterRep attendanceRegisterRep;

	@Autowired
	AttendanceRegisterGetRep attendanceRegisterGetRep;
	
	@Autowired
	Lease_Loan_Master_Repo lease_Loan_Master_Repo;

	@Autowired
	ResourceMasterRepo resourceMasterRepo;

	@Autowired
	AttendenaceresponseModel attendenaceresponseModel;

	@Autowired
	BTMEmpTimeSheetRep BTMEmpTimeSheetRep;

	@Autowired
	BTMAdminHolidayMasterRep btmAdminHolidayMasterRep;
	
	@Autowired 
	AckReciever ackReciever;

	@Autowired
	MainApplication mainApplication;
	
	@RequestMapping(value = "trigger", method = { RequestMethod.GET ,RequestMethod.POST })
	public String trigger(@RequestParam("start") String start,
			@ModelAttribute PlacementMaintenance placementmaintenance, Model md) throws SQLException, ParseException {
		System.out.println("COMING");
		String user="vijay.r@bornfire.in";
		String password="Vijay@123";
		   String storeType="pop3";
		   String pop3Host="sg2plzcpnl491716.prod.sin2.secureserver.net";
		   System.out.println(start);
		   return ackReciever.receiveEmail(pop3Host, storeType, user, password,start,placementmaintenance);
		// return "Success";
	}
	
	@RequestMapping(value = "getTypeDescription", method = RequestMethod.GET)
	@ResponseBody
	public String getTypeDescription(Model md, HttpServletRequest rq, @RequestParam(required = false) String refType,
			@ModelAttribute Reference_Code_Entity reference_Code_Entity) {

		List<Object[]> typeDescList = reference_code_Rep.getTypeDesc(refType);
		System.out.println("The typelist :"+typeDescList);

		String typeDesc = "";
		if (typeDescList != null && !typeDescList.isEmpty()) {
			Object[] firstRow = typeDescList.get(0);
			if (firstRow.length >= 2) {
				// Assuming the fields you need are at index 0 and 1
				typeDesc = firstRow[0] + " - " + firstRow[1];
			}
		}

		md.addAttribute("typeDesc", typeDesc);
		return typeDesc;

	}
	
	
	@RequestMapping(value = "t", method = { RequestMethod.GET ,RequestMethod.POST })
	public String t(@ModelAttribute PlacementMaintenance placementmaintenance, Model md) throws SQLException, ParseException {
		
		   return mainApplication.nApplication();
		// return "Success";
	}
	
	@RequestMapping(value = "RecievingMail", method = { RequestMethod.GET })
	public String getmail(@RequestParam("user") String user, @RequestParam("password") String password,
			@ModelAttribute PlacementMaintenance placementmaintenance, @RequestParam("pop3Host") String pop3Host,
			@RequestParam("storeType") String storeType, Model md) throws SQLException, ParseException {
		return recievingMail.receiveEmail(pop3Host, storeType, user, password, placementmaintenance);
		// return "Success";
	}
	@RequestMapping(value = "sendingmail", method = { RequestMethod.GET, RequestMethod.POST })
	public String sendmails(@RequestParam(required = false) String from_id,
			@RequestParam(required = false) String from_id_password,
			@RequestParam(required = false) List<String> checkedValues, Model md) throws SQLException, ParseException {

		System.out.println("Hi " + from_id + from_id_password);

		if (checkedValues != null && !checkedValues.isEmpty()) {
			for (String value : checkedValues) {
				System.out.println("Checked Value: " + value);
			}
		} else {
			System.out.println("No checked values received.");
		}

		String to = "vijay.r@bornfire.in";
		String from = from_id;
		String username = from_id; // change accordingly
		String password = from_id_password; // change accordingly
		String host = "sg2plzcpnl491716.prod.sin2.secureserver.net";

		return Sendingmail.sendmail(from, username, password, to, host, checkedValues);
	}
	
	@RequestMapping(value = "Spsendingmail", method = { RequestMethod.GET,RequestMethod.POST })
	public String Spsendmails(@RequestParam(required = false) String sp,@RequestParam(required = false) String inv_due_date,@RequestParam(required = false) String inv_date, Model md)throws SQLException, ParseException {
		System.out.println("Hi");
		System.out.println(sp+inv_due_date+inv_date);
		String to = "vijay.r@bornfire.in";
		String from = "grn@bornfire.in";
		   String username = "grn@bornfire.in";//change accordingly
		   String password = "Sound@Amman3";//change accordingly
		   String host = "sg2plzcpnl491716.prod.sin2.secureserver.net";
		   //String Sp="WHITESTONE";
		return spsendingmail.sendmail(from,username,password,to,host,sp,inv_due_date,inv_date);
		// return "Success";
	}

	@RequestMapping(value = "RecievingMail2", method = { RequestMethod.GET })
	public String getmail2(@RequestParam("user") String user, @RequestParam("password") String password,
			@ModelAttribute PlacementMaintenance placementmaintenance, @RequestParam("pop3Host") String pop3Host,
			@RequestParam("storeType") String storeType, Model md) throws SQLException, ParseException {
		return recievingMail2.receiveEmail2(pop3Host, storeType, user, password, placementmaintenance);
		// return "Success";
	}

	@RequestMapping(value = "timedetails", method = { RequestMethod.GET, RequestMethod.POST })
	public ArrayList<String> gettimedetails(@RequestParam("resid") String resid, @RequestParam("month") String month,
			@RequestParam("year") BigDecimal year, Model md) throws SQLException {
		return timesheetmaster.selectdetails(resid, month, year);
		// return "Success";
	}

	@RequestMapping(value = "getresourcedata", method = { RequestMethod.GET, RequestMethod.POST })
	public BTMAdminAssociateProfile getresourcedata(@RequestParam("resId") String resId, Model md) {
		BTMAdminAssociateProfile arl = btmAdminAssociateProfileRep.getEmployeedetailList(resId);

		return arl;
		// return "Success";
	}

	@RequestMapping(value = "getMonthYear", method = { RequestMethod.GET, RequestMethod.POST })
	public BTMEmpTimeSheet getMonthYear(@RequestParam(required = false) String resId, Model md,
			HttpServletRequest req) {
		String userId = (String) req.getSession().getAttribute("USERID");
		BTMEmpTimeSheet mnthyr = onDutyServices.getTimeSheetselect(userId);

		return mnthyr;
	}

	@RequestMapping(value = "getDailyActivity", method = { RequestMethod.GET, RequestMethod.POST })
	public ArrayList<String> getDailyActivity(@RequestParam("day") String day, @RequestParam("month") String month,
			@RequestParam("year") BigDecimal year, Model md) {
		ArrayList<String> mnthyr = timesheetmaster.DailyActivitydetails(day, month, year);

		return mnthyr;
	}

	@RequestMapping(value = "getprojectdata", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public BTMProjectMaster getprojectdata(@RequestParam("projId") String projId, Model md) {
		BTMProjectMaster arl = btmProjectMasterRep.getProjectMasterList(projId);

		return arl;
		// return "Success";
	}

	@PostMapping(value = "/attendancemonthlydatallklkl")
	@ResponseBody
	public ArrayList<AttendenaceresponseModel> attmonthly(@RequestParam(required = false) String year,
			@RequestParam(required = false) String Month, @RequestParam(required = false) String month1) {

		//List<ResourceMaster> resource = resourceMasterRepo.gettotaluser();

		List<HRMS_USER_PROFILE_ENTITY> resource  = hrms_userprofile_repo.getAlllist();


		ArrayList<AttendenaceresponseModel> ccc = new ArrayList<>();
		Map<String, String> profilePhotosMap =getProfilePhotos();
		
		
		
		//System.out.println("the photo from associate" +profilePhotosMap);
		
		for (HRMS_USER_PROFILE_ENTITY userid : resource) {
			
			//System.out.println("THE DON VINOTH"+userid.getUserId());
			String days_present = attendanceRegisterRep.getPresentcnt(userid.getUserId(), year, Month);
			int holiday = btmAdminHolidayMasterRep.getHolidaycount(year, month1);
			AttendenaceresponseModel att = new AttendenaceresponseModel();
			
			
			
		
			
			//Map<String, String> profilePhotosMap =get_imageformonthly(userid.getUserId());
			
			
			/*
			 * String photos=profilePhotosMap.get(userid.getUserId()); if (photos != null) {
			 * att.setFile(photos); } else {
			 * System.out.println("No photo found for User ID: " +userid.getUserId()); }
			 */
			
			
			/*
			 * for(Map.Entry<String, String> entry :profilePhotosMap.entrySet()) { String id
			 * = entry.getKey(); System.out.println("the cecking" +id);
			 * 
			 * System.out.println("the user ID from UserId" +userid.getUserId());
			 * 
			 * if (userid.getUserId().equals(id)) {
			 * 
			 * System.out.println("the inside checking");
			 * 
			 * if (entry.getValue() != null) {
			 * 
			 * att.setFile(entry.getValue());
			 * 
			 * } else { System.out.println("entry.getValue() is null"); } } else {
			 * System.out.println("The id is null or does not match"); } }
			 */
			
			
			
			  for(Map.Entry<String, String> entry :profilePhotosMap.entrySet()) { 
				  String id = entry.getKey(); 
				  
				  System.out.println("the cecking" +id);
			  
			  System.out.println("the user ID from UserId" +userid.getUserId());
				if (id != null && userid != null && userid.getUserId() != null && id.equals(userid.getUserId())) {
				    att.setFile(entry.getValue());
				} else {
				    if (id == null) {
				        System.out.println("id is null");
				    }
				    if (userid == null) {
				        System.out.println("userid is null");
				    } else if (userid.getUserId() == null) {
				        System.out.println("att.getId() is null");
				    } else {
				        System.out.println("att.getId().getEmp_id() is null or does not match");
				    }
				}
			  }
			
			  
			  
			 
			
			att.setEmp_id(userid.getUserId());
			att.setEmp_name(userid.getUserNames());
			att.setPresent_count(days_present);
			att.setHolidays(holiday);
			ccc.add(att);
		}

		return ccc;

	}
	
	/*---updateAPIformonthlydata--*/
	
	
	
	@PostMapping(value = "/attendancemonthlydata")
	@ResponseBody
	public List<AttendenaceresponseModel> getMonthlyAttendanceData(
	        @RequestParam(required = false) String year,
	        @RequestParam(required = false) String month,
	        @RequestParam(required = false) String month1,
	        @RequestParam(required = false) String branchid) {
		
	System.out.println("the inside controller for month");
		
		List<HRMS_USER_PROFILE_ENTITY> users;
		
		if(branchid == null || branchid.isEmpty()) {
			System.out.println("THE MONTH IS NULL");
			 users = hrms_userprofile_repo.getAlllist();
		}
		else
		{
			System.out.println("THE MONTH IS NOT NULL BRANCH");
			  users = hrms_userprofile_repo.getAlllistbranch(branchid);
			
		}

	  
	    int holidayCount = btmAdminHolidayMasterRep.getHolidaycount(year, month1);

	    List<AttendenaceresponseModel> responseList = new ArrayList<>();

	    for (HRMS_USER_PROFILE_ENTITY user : users) {
	      
	        String daysPresent = attendanceRegisterRep.getPresentcnt(user.getUserId(), year, month);

	        AttendenaceresponseModel attendanceResponse = new AttendenaceresponseModel();
	        attendanceResponse.setEmp_id(user.getUserId());
	        attendanceResponse.setEmp_name(user.getUserNames());
	        attendanceResponse.setPresent_count(daysPresent);
	        attendanceResponse.setHolidays(holidayCount);

	        List<BTMAdminAssociateProfile> profiles = btmAdminAssociateProfileRep.getAssociatelistforphotos(user.getUserId());
	        if (!profiles.isEmpty() && profiles.get(0).getFiles() != null) {
	            String base64Image = Base64.getEncoder().encodeToString(profiles.get(0).getFiles());
	            attendanceResponse.setFile(base64Image);
	        } else {
	            attendanceResponse.setFile(user.getUserNames());  // Or set a default image string
	        }

	        responseList.add(attendanceResponse);
	    }

	    return responseList;
	}

	
	

	@PostMapping(value = "/attendancemonthlydatainquiries")
	@ResponseBody
	public ArrayList<AttendenaceresponseModel> attmonthlyinquiries(@RequestParam(required = false) String year,
			@RequestParam(required = false) String Month, @RequestParam(required = false) String month1,
			HttpServletRequest req) {

		String userId = (String) req.getSession().getAttribute("USERID");

		ArrayList<AttendenaceresponseModel> ccc = new ArrayList<>();

		AttendanceRegister attlist = onDutyServices.getMonthlyInquiries(userId, year, Month);
		String days_present = attendanceRegisterRep.getPresentcnt(userId, year, Month);

		int holiday = btmAdminHolidayMasterRep.getHolidaycount(year, month1);
		AttendenaceresponseModel att = new AttendenaceresponseModel();
		att.setEmp_id(attlist.getId().getEmp_id());
		att.setEmp_name(attlist.getEmp_name());
		att.setPresent_count(days_present);
		att.setHolidays(holiday);
		ccc.add(att);

		return ccc;

	}

	@PostMapping(value = "/associatedetails")
	@ResponseBody
	public ArrayList<AssociateResponseModel> associatedetails(@RequestParam(required = false) String userid,
			@RequestParam(required = false) String year, @RequestParam(required = false) String Month) {
		String userr = userid;
		int mont = 07;

		List<AttendanceRegisterGet> attlist = attendanceRegisterGetRep.getAssociatedata(userr, year, Month);
		ArrayList<AssociateResponseModel> lastlist = new ArrayList<>();
		for (AttendanceRegisterGet att : attlist) {
			AssociateResponseModel finallist = new AssociateResponseModel();

			finallist.setEmp_id(att.getId().getEmp_id());
			finallist.setEmp_name(att.getEmp_name());
			finallist.setAtt_date(att.getId().getLogin_time());

			finallist.setAtt_day(att.getCal_day());
			finallist.setLogin_time1(att.getLogin_time1());
			finallist.setLogout_time1(att.getLogout_time1());

			// finallist.setAtt_day(att.getCal_day());
			finallist.setRemarks(att.getEmp_remarks());
			lastlist.add(finallist);
		}
		return lastlist;

	}

	@GetMapping("/profile-photos")
	    public Map<String, String> getProfilePhotos() {
		
        Map<String, String> imagesMap = new HashMap<>();

        List<BTMAdminAssociateProfile> profiles = btmAdminAssociateProfileRep.getAssociatelist(); // Fetch profiles with IDs and photo blobs
        String base64Image=null;
        for (BTMAdminAssociateProfile profile : profiles) {
        	
            byte[] photoBytes = profile.getFiles();// Get the image bytes
            System.out.println(profile.getFileName());
            System.out.println(profile.getResource_id());
            
            if(photoBytes!=null) {
            	base64Image = Base64.getEncoder().encodeToString(photoBytes);// Convert to Base64
            	System.out.println("the testing for photos" +profile.getEmployee_id());
            	
            }
            imagesMap.put(profile.getEmployee_id(), base64Image); // Store in map with ID as key and Base64 image as value
        }

        return imagesMap; // Return the map to the client
    }
	
	
	
	@GetMapping("/get_image")
	public Map<String, String> get_image(@RequestParam String id) {
	    System.out.println("Fetching image for ID: " + id);
	    Map<String, String> imagesMap = new HashMap<>();
	    System.out.println("hiiiiiiiiiiiiii");
	    // Fetch the profile details for the given employee ID
	    BTMAdminAssociateProfile profile = btmAdminAssociateProfileRep.getEmployeedetail(id);
	    
	    String base64Image = null;
	    if (profile != null) {
	        byte[] photoBytes = profile.getFiles(); // Get the image bytes from the database
	        if (photoBytes != null) {
	            base64Image = Base64.getEncoder().encodeToString(photoBytes); // Convert image to Base64
	            imagesMap.put(profile.getEmployee_id(), base64Image); // Store Base64 image with employee_id as key
	        }
	    }

	    // Return the map of images (with employee_id as key)
	    return imagesMap;
	}

	
	
	/*-----formonthly-----*/

	public Map<String, String> get_imageformonthly( String id) {
	    System.out.println("Fetching image for ID: " + id);
	    Map<String, String> imagesMap = new HashMap<>();
	    System.out.println("hiiiiiiiiiiiiii");
	    // Fetch the profile details for the given employee ID
	    BTMAdminAssociateProfile profile = btmAdminAssociateProfileRep.getEmployeedetail(id);
	    
	    String base64Image = null;
	    if (profile != null) {
	        byte[] photoBytes = profile.getFiles(); // Get the image bytes from the database
	        if (photoBytes != null) {
	            base64Image = Base64.getEncoder().encodeToString(photoBytes); // Convert image to Base64
	            imagesMap.put(profile.getEmployee_id(), base64Image); // Store Base64 image with employee_id as key
	        }
	    }

	    // Return the map of images (with employee_id as key)
	    return imagesMap;
	}
	
	@PostMapping(value = "/gettodalcounts")
	@ResponseBody
	public ArrayList<Integer> gettodalcounts(@RequestParam(required = false) Date dtt,
			@RequestParam(required = false) String year, @RequestParam(required = false) String month,
			@RequestParam(required = false) String day) {
		Date dat1 = null;
		SimpleDateFormat formatdate = new SimpleDateFormat("dd/MM/yyyy");
		String str = formatdate.format(dtt);
		try {
			dat1 = formatdate.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int totalemployees = resourceMasterRepo.gettotalnum();
		int present = attendanceRegisterRep.getALpresent(year, month, day);
		int onduty = attendanceRegisterRep.getALLOnDuty(year, month, day);
		int absent = attendanceRegisterRep.getALLAbsent(year, month, day);
		int absentees=resourceMasterRepo.getnotlogedemployees(year, month, day);

		ArrayList<Integer> response = new ArrayList<Integer>();
		response.add(totalemployees);
		response.add(onduty);
		response.add(present);
		response.add(absent);
		response.add(absentees);

		return response;

	}

	/*
	 * @PostMapping(value = "/gettodalcount")
	 * 
	 * @ResponseBody public List<String> gettodalcount(@RequestParam(required=false)
	 * Date dtt,Model md,@RequestParam(required=false) String
	 * year,@RequestParam(required=false) String month,@RequestParam(required=false)
	 * String day) { System.out.println("TEST"); String oo="sdsssss";
	 * System.out.println("first line"); List<String>
	 * Absentees=attendanceRegisterRep.getAbsentees(year, month, day); List<String>
	 * A5=new ArrayList<String>();
	 * 
	 * 
	 * //List<AttendanceRegister> attrReg = new ArrayList<>();
	 * //md.addAttribute("alist", attendanceRegisterRep.getAbsentees(year, month,
	 * day)); A5.add(Absentees.get(0)); A5.add(Absentees.get(1));
	 * System.out.println(A5);
	 * System.out.println("the value isssssssssssssss"+Absentees.get(0));
	 * System.out.println(A5.get(0)); return A5; }
	 */


	@RequestMapping(value = "gettotalcount", method = RequestMethod.GET)
	public List<ResourceMaster> gettodalcount(@RequestParam(required = false) Date dtt, Model md,
			@RequestParam(required = false) String year, @RequestParam(required = false) String month,
			@RequestParam(required = false) String day) {
		System.out.println("TEST");
		String oo = "sdsssss";
		System.out.println("first line");
		//List<ResourceMaster> Absentees = resourceMasterRepo.getAbsentees(year, month, day);
		List<Object[]> Absentees = resourceMasterRepo.forabsentees(year, month, day);
		// List<String> A5=new ArrayList<String>();
		List<ResourceMaster> custMaster_List = new ArrayList<>();
		for (Object[] obj : Absentees) {
			ResourceMaster info = new ResourceMaster();
			info.setResource_id(String.valueOf(obj[0]));
			info.setResource_name(String.valueOf(obj[1]));

			custMaster_List.add(info);
		}

		
		System.out.println("KJJJJJJJJJJJJ"+custMaster_List.get(0).toString());
		/*
		 * List<ResourceMaster> list1 = new ArrayList<ResourceMaster>();
		 * System.out.println(">>>>>>>>>>>>"); for(ResourceMaster AR : Absentees) {
		 * 
		 * list1.get(0).setResource_id(AR.getResource_id());
		 * 
		 * list1.setResource_name(AR.getResource_name());
		 * list1.add(AttendanceRegister_E);
		 * 
		 * //list1.get(0).getResource_id();
		 * System.out.println(list1.get(0).getResource_name()); }
		 */
		// System.out.println(list1);
		// List<AttendanceRegister> attrReg = new ArrayList<>();
		// md.addAttribute("alist", attendanceRegisterRep.getAbsentees(year, month,
		// day));
		/*
		 * A5.add(Absentees.get(0)); A5.add(Absentees.get(1)); System.out.println(A5);
		 * System.out.println("the value isssssssssssssss"+Absentees.get(0));
		 * System.out.println(A5.get(0));
		 */
		return custMaster_List;
	}

	// plese restart the

	@PostMapping(value = "/attendancetoday")
	@ResponseBody
	public ArrayList<AssociateResponseModel> attendancetoday(@RequestParam(required = false) String year,
			@RequestParam(required = false) String month, @RequestParam(required = false) String day,
			 @RequestParam(required = false) String branchId) {

		System.out.println("the inside controller");
		
		List<AttendanceRegisterGet> attlist;
		
		if(branchId == null || branchId.isEmpty()) {
			System.out.println("check 1 branch is null");
			attlist = attendanceRegisterGetRep.getAttAllnormal(year, month, day);
		}
		else
		{
			System.out.println("check 1 branch is notnul");
		attlist = attendanceRegisterGetRep.getAttAll(year, month, day,branchId);
		}
		

		
		System.out.println("the lenhjg"+attlist.size());

		Map<String, String> profilePhotosMap =getProfilePhotos();
		ArrayList<AssociateResponseModel> lastlist = new ArrayList<>();
		for (AttendanceRegisterGet att : attlist) {
			AssociateResponseModel finallist = new AssociateResponseModel();
			
			for(Map.Entry<String, String> entry  :profilePhotosMap.entrySet()) {
				String id = entry.getKey();
				System.out.println("te id" +id);
				System.out.println("te emp" +att.getId().getEmp_id());
				if (id != null && att != null && att.getId() != null && id.equals(att.getId().getEmp_id())) {
				    finallist.setFile(entry.getValue());
				} else {
				    if (id == null) {
				        System.out.println("id is null");
				    }
				    if (att == null) {
				        System.out.println("att is null");
				    } else if (att.getId() == null) {
				        System.out.println("att.getId() is null");
				    } else {
				        System.out.println("att.getId().getEmp_id() is null or does not match");
				    }
				}

				
			}
			
			System.out.println("the user id" +att.getId().getEmp_id());
			
			
		
			
			finallist.setEmp_id(att.getId().getEmp_id());
			finallist.setEmp_name(att.getEmp_name());

			finallist.setLogin_time1(att.getLogin_time1());
			finallist.setLogout_time1(att.getLogout_time1());
			// finallist.setAtt_day(att.getCal_day());
			finallist.setLogout(att.getDevice());
			// finallist.setLogin(att.getLogout_time1());
			finallist.setStatus("Present");
			finallist.setRemarks(att.getEmp_remarks());
		
			// finallist.setLogout(att.getLast_update_time());
			lastlist.add(finallist);
		}

		return lastlist;

	}

	@PostMapping(value = "/attendancetodayinquiries")
	@ResponseBody
	public ArrayList<AssociateResponseModel> attendancetodayInquiries(@RequestParam(required = false) String year,
			@RequestParam(required = false) String month, @RequestParam(required = false) String day

			, HttpServletRequest req) {

		String userId = (String) req.getSession().getAttribute("USERID");

		List<AttendanceRegisterGet> attlist = attendanceRegisterGetRep.getAttAllInquires(userId, year, month, day);

		ArrayList<AssociateResponseModel> lastlist = new ArrayList<>();
		for (AttendanceRegisterGet att : attlist) {
			AssociateResponseModel finallist = new AssociateResponseModel();

			finallist.setEmp_id(att.getId().getEmp_id());
			finallist.setEmp_name(att.getEmp_name());

			finallist.setLogin_time1(att.getLogin_time1());
			finallist.setLogout_time1(att.getLogout_time1());
			// finallist.setAtt_day(att.getCal_day());

			// finallist.setLogin(att.getLogout_time1());
			finallist.setStatus("Present");
			finallist.setRemarks(att.getEmp_remarks());
			// finallist.setLogout(att.getLast_update_time());
			lastlist.add(finallist);
		}

		return lastlist;

	}

	@PostMapping(value = "fileUploadIssue")
	@ResponseBody
	public List<IssueTracker> uploadFileIssue(@RequestParam("file") MultipartFile file, String screenId,
			@ModelAttribute IssueTracker issue, Model md, HttpServletRequest rq)
			throws FileNotFoundException, SQLException, IOException {

		System.out.println("fileSize" + file.getSize());

		if (file.getSize() < 500000) {
			String userid = (String) rq.getSession().getAttribute("USERID");
			List<IssueTracker> msg = onDutyServices.UploadIssue(userid, file, userid, issue);
			return msg;
		} else {
			throw new CustomException("File has not been successfully uploaded. Requires less than 128 KB size.");
		}

	}

	@PostMapping(value = "fileUploadPOMaster")
	@ResponseBody
	public String uploadFilePO(@RequestParam("file") MultipartFile file, String screenId,
			@ModelAttribute PlacementMaintenance issue, Model md, HttpServletRequest rq)
			throws FileNotFoundException, SQLException, IOException {

		System.out.println("fileSize" + file.getSize());

		if (file.getSize() < 500000) {
			String userid = (String) rq.getSession().getAttribute("USERID");
			String msg = onDutyServices.UploadPO(userid, file, userid, issue);
			return msg;
		} else {
			throw new CustomException("File has not been successfully uploaded. Requires less than 128 KB size.");
		}

	}

	@RequestMapping(value = "reUpload", method = RequestMethod.GET)
	public ResponseEntity<Resource> generateExcelReport(@RequestParam("upload_date") String upload_date)
			throws IOException, ParseException {
		List<PlacementMaintenance> Po = placementMaintenanceRep.getReupload(upload_date);
		System.out.println(upload_date);
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet();

		int rowCount = 0;
		Row row = sheet.createRow(rowCount++);

		Font font = wb.createFont();
		font.setBold(true);

		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setBorderTop(BorderStyle.THICK);
		cellStyle.setBorderBottom(BorderStyle.THICK);
		cellStyle.setBorderLeft(BorderStyle.THICK);
		cellStyle.setBorderRight(BorderStyle.THICK);
		cellStyle.setFont(font);

		Cell cell = row.createCell(0);
		cell.setCellValue("VENDOR");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(1);
		cell.setCellValue("LOCATION");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(2);
		cell.setCellValue("PO_NO");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(3);
		cell.setCellValue("PO_DATE");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(4);
		cell.setCellValue("EXTN_FLG");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(5);
		cell.setCellValue("EXTN_DATE");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(6);
		cell.setCellValue("PROJ_MGR");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(7);
		cell.setCellValue("PM_EMAIL");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(8);
		cell.setCellValue("SP");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(9);
		cell.setCellValue("RESUBMIT FLG");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(10);
		cell.setCellValue("SP_RATE");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(11);
		cell.setCellValue("UNIT_LOC");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(12);
		cell.setCellValue("GSTIN");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(13);
		cell.setCellValue("EMP_NAME");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(14);
		cell.setCellValue("EMP_ID");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(15);
		cell.setCellValue("NO_OF_ITEMS");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(16);
		cell.setCellValue("TOTAL_VALUE");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(17);
		cell.setCellValue("PO_ITEM_NO");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(18);
		cell.setCellValue("PO_QTY");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(19);
		cell.setCellValue("PO_RATE_INR");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(20);
		cell.setCellValue("PO_AMT_INR");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(21);
		cell.setCellValue("PO_DELIVERY_DATE");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(22);
		cell.setCellValue("NEW_INV_NO");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(23);
		cell.setCellValue("RESUBMIT_DATE");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(24);
		cell.setCellValue("SP_RATE");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(25);
		cell.setCellValue("SP_INV_NO");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(26);
		cell.setCellValue("SP_INV_DATE");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(27);
		cell.setCellValue("SP_INV_AMT");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(28);
		cell.setCellValue("BILL_REMARKS");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(29);
		cell.setCellValue("PO_STATUS");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(30);
		cell.setCellValue("ENTITY_FLG");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(31);
		cell.setCellValue("DEL_FLG");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(32);
		cell.setCellValue("MODIFY_FLG");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(33);
		cell.setCellValue("PO_MONTH");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(34);
		cell.setCellValue("SP_INV_AMT");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(35);
		cell.setCellValue("GRN_NO");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(36);
		cell.setCellValue("GRN_DATE");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(37);
		cell.setCellValue("GRN_EFFORTS");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(38);
		cell.setCellValue("GRN_AMT");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(39);
		cell.setCellValue("CHECK_FLG");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(40);
		cell.setCellValue("INV_NO");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(41);
		cell.setCellValue("INV_DATE");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(42);
		cell.setCellValue("INV_DUE_DATE");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(43);
		cell.setCellValue("INV_AMT");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(44);
		cell.setCellValue("INV_SGST");
		cell.setCellStyle(cellStyle);

		cellStyle = wb.createCellStyle();
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);

		for (PlacementMaintenance PO : Po) {
			row = sheet.createRow(rowCount++);

			int columnCount = 0;

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getVendor());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getLocation());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getPo_no());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getPo_date().toString());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getExtn_flg());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getExpiary_date());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getProj_mgr());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getPm_email());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getSp());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getRe_sumbit_flg());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getSp_rate());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getUnit_loc());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getGstin());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getEmp_name());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getEmp_id());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getNo_of_items());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getTotal_value());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getPo_item_no());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getPo_qty());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getPo_rate_inr());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getPo_amt_inr());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getPo_delivery_date().toString());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getNew_inv_no());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getResubmit_date());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getSp_rate());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getSp_inv_no());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getSp_inv_date());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getSp_inv_amt());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getCancel_remarks());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getStatus());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getEntity_flg());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getDel_flg());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getModify_flg());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getPo_month());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getGrn_no().toString());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getGrn_date());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getGrn_efforts());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getGrn_amt());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getCancel_flg());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getInv_no());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getInv_date());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getInv_due_date());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(PO.getInv_amt());
			cell.setCellStyle(cellStyle);

		}

		ByteArrayOutputStream os = new ByteArrayOutputStream();

		wb.write(os);
		wb.close();

		ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(
				MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=SuccessReport.xlsx");

		ResponseEntity<Resource> response = new ResponseEntity<Resource>(new InputStreamResource(is), headers,
				HttpStatus.OK);

		return response;
	}

	
	@RequestMapping(value = "/po_filter")
	@ResponseBody
	public ArrayList<PlacementMaintenance> po_filter(@RequestParam(required = false) String po_month,HttpServletRequest req) {

		

		List<PlacementMaintenance> place =  placementMaintenanceRep.getAbsenteesFrom(po_month);

		ArrayList<PlacementMaintenance> PO_Status = new ArrayList<>();
		for (PlacementMaintenance att : place) {
			PlacementMaintenance finallist = new PlacementMaintenance();

			finallist.setPo_no(att.getPo_no());
			String d[]=(att.getPo_date().toString()).split(" ");
			finallist.setPo_item_no(d[0]);
			finallist.setEmp_name(att.getEmp_name());
			finallist.setPo_rate_inr(att.getPo_rate_inr());
			finallist.setEmp_id(att.getEmp_id());
			String e[]=(att.getPo_delivery_date().toString()).split(" ");
			finallist.setInv_igst(e[0]);
			finallist.setPo_month(att.getPo_month());
			finallist.setProj_mgr(att.getProj_mgr());
			finallist.setPm_email(att.getPm_email());
			finallist.setLocation(att.getLocation());
			
	
			
			// finallist.setLogout(att.getLast_update_time());
			PO_Status.add(finallist);
		}
		System.out.println(PO_Status.toString());
		System.out.println(">>>>>>>>>"+PO_Status);
	 
			/* return PO_Status ; */

	
	    // Convert the list to JSON using Jackson
		return PO_Status;
	}
	
	@Autowired	
	com.bornfire.entities.CandEvalFormRep CandEvalFormRep;
	@RequestMapping(value = "showDetail", method = RequestMethod.GET)
	public CandEvalFormEntity showDetail(@RequestParam(required = false) String po_month,
	                                           @RequestParam(required = false) String a,
	                                           HttpServletRequest req) {
	    System.out.println("-----------------------------------" + a);
	    if (a != null && !a.isEmpty()) {
	        return CandEvalFormRep.gethashmap(a);
	    } else {
	        // Handle case where 'a' is null or empty
	        // You may return a default response or throw an exception
	        return null; // or throw new IllegalArgumentException("Parameter 'a' is required");
	    }
	}
	
	@Autowired	
	com.bornfire.entities.ResourceMasterRepo resourceMasterRepo1;
	@RequestMapping(value = "showDetailforss", method = RequestMethod.GET)
	public ResourceMaster showDetailforss(@RequestParam(required = false) String po_month,
	                                           @RequestParam(required = false) String a,
	                                           HttpServletRequest req) {
	    System.out.println("-----------------------------------" + a);
	    if (a != null && !a.isEmpty()) {
	        return resourceMasterRepo1.getarole(a);
	    } else {
	        // Handle case where 'a' is null or empty
	        // You may return a default response or throw an exception
	        return null; // or throw new IllegalArgumentException("Parameter 'a' is required");
	    }
	}
	
	@Autowired	
	com.bornfire.entities.Salary_Pay_Rep salary_Pay_Rep;
	/*@RequestMapping(value = "showDetailforss", method = RequestMethod.GET)
	public Salary_Pay_Entity showDetailforss(@RequestParam(required = false) String po_month,
	                                           @RequestParam(required = false) String a,
	                                           HttpServletRequest req) {
	    System.out.println("-----------------------------------" + a);
	    if (a != null && !a.isEmpty()) {
	        return salary_Pay_Rep.getaedit(a);
	    } else {
	        // Handle case where 'a' is null or empty
	        // You may return a default response or throw an exception
	        return null; // or throw new IllegalArgumentException("Parameter 'a' is required");
	    }
	}*/
	

	@RequestMapping(value = "showRevisionDetail", method = RequestMethod.GET)
	public Salary_Pay_Entity showRevisionDetail(@RequestParam(required = false) String po_month,
	                                           @RequestParam(required = false) String ctc,
	                                           HttpServletRequest req) {
	    System.out.println("-----------------------------------" + ctc);
	    if (ctc != null && !ctc.isEmpty()) {
	        return salary_Pay_Rep.getaedit(ctc);
	    } else {
	        // Handle case where 'a' is null or empty
	        // You may return a default response or throw an exception
	        return null; // or throw new IllegalArgumentException("Parameter 'a' is required");
	    }
	}
	
	@Autowired
	com.bornfire.entities.Baj_Work_Repo Baj_Work_Repo;
	
	@RequestMapping(value = "tdstab1edits", method = RequestMethod.POST)
	@ResponseBody
	public String tdstab1edit(@ModelAttribute Baj_Sal_Work_Entity Baj_Sal_Work_Entity, String tran_id,@RequestParam(required = false) String uniqueid) throws ParseException {
		
System.out.println("hihihi");
		Baj_Sal_Work_Entity up = Baj_Work_Repo.getlisttab1(uniqueid);
		System.out.println("hi this is btm");
		Baj_Work_Repo.save(up);
		//SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);

		/*try {
			
			up.setEmp_name(tdsentity.getEmp_name());
			up.setPan_no(tdsentity.getPan_no());
			up.setDate_of_pay(tdsentity.getDate_of_pay());
			up.setNet_salary(tdsentity.getNet_salary());
			up.setRate_of_tds(tdsentity.getTds_tran_ref());
			up.setBank_bsr_code(tdsentity.getBank_bsr_code());
			up.setTds(tdsentity.getTds());
			up.setBank_chalan_no(tdsentity.getBank_chalan_no());
			up.setBank_name(tdsentity.getBank_name());
			up.setChalan_amt(tdsentity.getChalan_amt());
			up.setTds_remit_date(tdsentity.getTds_remit_date());
			up.setTds_tran_ref(tdsentity.getTds_tran_ref());
			
			//up.setUniqueid(Gstoverseas.getUniqueid());
			
			
		    
			tdsRepos.save(up);
			System.out.println("hi this is gst from tds"+tdsentity.getEmp_name());
			System.out.println("hi this is btm"+tdsentity.getDate_of_pay());
			
			// Save the 'up' object with the updated entry_time

		} catch (Exception e) {
		    e.printStackTrace(); // Handle potential errors here, such as ParseException
		}*/
	

		return "edited Successfully tdstable1";

	}
	
	@Autowired
	paystructurerep payStructureRepository; // Assuming the repository name is PayStructureRepository

	@GetMapping("/getMonthList")
	@ResponseBody
	public List<paystructureentity> getMonthList(@RequestParam(required = false) String month) {
	    System.out.println("Fetching data for month: " + month);
	    String demo = "DEMO"; // Not used, can be removed
	    YearMonth currentYearMonth = YearMonth.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM"); // Corrected pattern for month
	    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy");
	    String formattedMonth = currentYearMonth.format(formatter);
	    String formattedYear = currentYearMonth.format(formatter1);
	    System.out.println("Current Month and Year: " + formattedYear + formattedMonth);

	    List<paystructureentity> monthList = null; // Declare outside the if-else blocks

	    if (month .equals(null)) {
	        monthList = payStructureRepository.getpayssdemo(formattedYear, formattedMonth);
	        System.out.println("uuuuuuuuuuuuuuuuuuuuuu" + monthList);
	    } else {
	        monthList = payStructureRepository.getpayss(month);
	        System.out.println("Fetched data: " + monthList);
	    }

	    return monthList; // Return the list outside the if-else blocks
	}
	@PostMapping("/fileimport")
	 public String uploadFile(@RequestParam("file") MultipartFile file) {
	    String message = "";
	 try {
	    if (BHMS_services.hasExcelFormat(file)) {
	    	BHMS_services.save(file);
	        message = "Uploaded the file successfully!"; 
	     
	    }else {
	    message = "Please upload an excel file!";
	  }
	     } catch (Exception e) {
 	  System.out.println(e);
     message = "Could not upload the file: " + file.getOriginalFilename() + "!";
   }
	    return message;
	}
	
	@GetMapping("transactionaccountdetails")
	public Chart_Acc_Entity transactionaccountdetails(@RequestParam(required = false) String acct_num) {
		Chart_Acc_Entity accountvalue = chart_Acc_Rep.getlistpopupvalues(acct_num);
		System.out.println("THE ACCOUNT NUMBER IS" + acct_num);
		return accountvalue;
	}
	
	@GetMapping("validateAccountStatus")
	public String validateAccountStatus(Model md, HttpServletRequest rq, @RequestParam(required = false) String tran_id,
			HttpServletRequest request) {

		System.out.println("Tran id : " + tran_id);
		String msg = "";

		BigDecimal cr_amt = BigDecimal.ZERO;
		BigDecimal dr_amt = BigDecimal.ZERO;

		/* list of tranId values */
		List<TRAN_MAIN_TRM_WRK_ENTITY> values = tRAN_MAIN_TRM_WRK_REP.findByjournalvalues(tran_id);

		for (TRAN_MAIN_TRM_WRK_ENTITY entity : values) {
			if (entity.getPart_tran_type().equals("Credit")) {
				cr_amt = cr_amt.add(entity.getTran_amt());
			} else {
				dr_amt = dr_amt.add(entity.getTran_amt());
			}
		}

		if (cr_amt.compareTo(dr_amt) == 0) {
			msg = "Validation successful: Credit and Debit amounts are equal.";
		} else {
			msg = "Validation failed: Credit and Debit amounts are not equal.";
		}

		return msg;
	}

	
	@Transactional
	@GetMapping("postedTrmRecords")
	public String postedTrmRecords(Model md, HttpServletRequest rq, @RequestParam(required = false) String acct_num,
			@RequestParam(required = false) String tran_id, @RequestParam(required = false) String part_tran_id,
			HttpServletRequest request) {

		System.out.println("Tran id : " + tran_id);

		/* list of tranId values */
		List<TRAN_MAIN_TRM_WRK_ENTITY> values = tRAN_MAIN_TRM_WRK_REP.findByjournalvalues(tran_id);

		List<String> partTranTypes = new ArrayList<>();
		List<BigDecimal> partTranid = new ArrayList<>();

		for (TRAN_MAIN_TRM_WRK_ENTITY entity : values) {
			partTranTypes.add(entity.getPart_tran_type());
			partTranid.add(entity.getPart_tran_id());

		}

		if (partTranid.size() > 1) {

			boolean hasDebit = partTranTypes.contains("Debit");
			boolean hasCredit = partTranTypes.contains("Credit");

			if (hasDebit && hasCredit) {

				String user = (String) rq.getSession().getAttribute("USERID");
				Date con_date = (Date) rq.getSession().getAttribute("TRANDATE");

				List<TRAN_MAIN_TRM_WRK_ENTITY> savedatas = new ArrayList<>();

				for (TRAN_MAIN_TRM_WRK_ENTITY entity : values) {
					entity.setPost_user(user);
					entity.setPost_time(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
					entity.setDel_flg("N");
					entity.setTran_status("POSTED");
					savedatas.add(entity);

					if (entity.getFlow_code().equalsIgnoreCase("DISBT")) {
						String accountNumber = entity.getAcct_num();
						String acountName = lease_Loan_Master_Repo.accountName(accountNumber);
						if (Objects.nonNull(acountName) || acountName != null) {
							Lease_Loan_Master_Entity leaseRecord = lease_Loan_Master_Repo.findByref_no(accountNumber);
							leaseRecord.setDisbursement(entity.getTran_amt());
							lease_Loan_Master_Repo.save(leaseRecord);
						} else {
							System.out.println("Not a loan account");
						}

					} else {
						System.out.println("Not a disbursement");
					}
				}

				String auditID = sequence.generateRequestUUId();
				String user1 = (String) request.getSession().getAttribute("USERID");
				String username = (String) request.getSession().getAttribute("USERNAME");

				BGLSBusinessTable_Entity audit = new BGLSBusinessTable_Entity();
				audit.setAudit_date(new Date());
				audit.setEntry_time(new Date());
				audit.setEntry_user(user1);
				audit.setFunc_code("VERIFIED");
				audit.setAudit_table("BGLSBUSINESSTABLE");
				audit.setAudit_screen("VERIFIED");
				audit.setEvent_id(user1);
				audit.setEvent_name(username);
				audit.setModi_details("Verified Successfully");

				
				//UserProfile values1 = userProfileRep.getRole(user1);
				//audit.setAuth_user(values1.getAuth_user());
				//audit.setAuth_time(values1.getAuth_time());
				ResourceMaster values1 = ResourceMasterRepo.getrole(user1);
				audit.setAuth_user(values1.getAccess_id());
				audit.setAuth_time(new Date());
				audit.setAudit_ref_no(auditID);

				for (TRAN_MAIN_TRM_WRK_ENTITY entity : values) {

					if (entity.getPart_tran_type().equals("Credit")) {

						System.out.println("Credit Part");

						/* Account details get by account number in COA table */

						Chart_Acc_Entity originalAccount = chart_Acc_Rep.getaedit(entity.getAcct_num());

						BigDecimal balance = originalAccount.getAcct_bal();
						BigDecimal creditBalance = Objects.nonNull(originalAccount.getCr_amt())
								? originalAccount.getCr_amt()
								: BigDecimal.ZERO;
						BigDecimal debitBalance = Objects.nonNull(originalAccount.getDr_amt())
								? originalAccount.getDr_amt()
								: BigDecimal.ZERO;

						BigDecimal creditVal = tRAN_MAIN_TRM_WRK_REP.getcredit(entity.getTran_id(),
								entity.getPart_tran_id());

						System.out.println("Account Balance : " + balance);
						System.out.println("Cr_Amt : " + creditBalance);
						System.out.println("Dr_amt : " + debitBalance);
						System.out.println("Credited Amount : " + creditVal);

						// Add credit amount to the account balance
						BigDecimal newBalance = balance.add(creditVal);

						// Add credited amount to the cr_amt
						creditBalance = creditBalance.add(creditVal);
						debitBalance.add(BigDecimal.ZERO);

						System.out.println("Updated Account Balance : " + newBalance);
						System.out.println("Updated Cr_Amt  : " + creditBalance);
						System.out.println("Updated Dr_Amt : " + debitBalance);

						// Update the account with the new balances
						originalAccount.setAcct_bal(newBalance);
						originalAccount.setCr_amt(creditBalance);
						originalAccount.setDr_amt(debitBalance);
						originalAccount.setModify_time(con_date);
						originalAccount.setModify_user(user);

						chart_Acc_Rep.save(originalAccount);

					} else {

						System.out.println("Debit Part");

						/* Account details get by account number in COA table */

						Chart_Acc_Entity originalAccount = chart_Acc_Rep.getaedit(entity.getAcct_num());

						BigDecimal balance = originalAccount.getAcct_bal();
						BigDecimal creditBalance = Objects.nonNull(originalAccount.getCr_amt())
								? originalAccount.getCr_amt()
								: BigDecimal.ZERO;
						BigDecimal debitBalance = Objects.nonNull(originalAccount.getDr_amt())
								? originalAccount.getDr_amt()
								: BigDecimal.ZERO;

						BigDecimal debitVal = tRAN_MAIN_TRM_WRK_REP.getdebit(entity.getTran_id(),
								entity.getPart_tran_id());
						System.out.println("Account Balance : " + balance);
						System.out.println("Dr_Amt : " + debitBalance);
						System.out.println("Cr_Amt : " + creditBalance);
						System.out.println("Debited Amount : " + debitVal);

						// Subtract debited amount from the account balance
						BigDecimal newBalance = balance.subtract(debitVal);

						// add debited amount to dr_amt
						debitBalance = debitBalance.add(debitVal);
						creditBalance.add(BigDecimal.ZERO);

						System.out.println("Updated Account Balance: " + newBalance);
						System.out.println("Updated Dr_Amt : " + debitBalance);
						System.out.println("Updated Cr_Amt : " + creditBalance);

						// Update the account with the new balances
						originalAccount.setAcct_bal(newBalance);
						originalAccount.setDr_amt(debitBalance);
						originalAccount.setCr_amt(creditBalance);
						originalAccount.setModify_time(con_date);
						originalAccount.setModify_user(user);

						chart_Acc_Rep.save(originalAccount);
					}
				}

				bGLSBusinessTable_Rep.save(audit);
				tRAN_MAIN_TRM_WRK_REP.saveAll(savedatas);
			} else {
				return "Error: Please select at least one credit and one debit transaction.";
			}
		}

		return "Posted Successfully";
	}

	
	
}
