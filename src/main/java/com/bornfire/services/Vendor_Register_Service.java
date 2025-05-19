package com.bornfire.services;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bornfire.entities.Expenses_entity;
import com.bornfire.entities.Expenses_repo;
import com.bornfire.entities.Vendor_Register_Repo;
import com.bornfire.entities.Vendor_registration_entity;

@Service
public class Vendor_Register_Service {
	
	@Autowired
	Vendor_Register_Repo Vendor_Register_Repo;
	
	
	@Autowired
	Expenses_repo expenses_repo;
	@Autowired
	SessionFactory sessionFactory;
	
	public String addVendor(Vendor_registration_entity Vendor_registration_entity,String userId,String username) {
		System.out.println("the add vendor Service");
		Session session = sessionFactory.getCurrentSession();
		BigInteger VendorId = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR vendor_Id AS id").getSingleResult();
		System.out.println("the vendorid" +VendorId);
		String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String msg="";
		
		
		try {
			
			Vendor_registration_entity.setVendorDocId("VEN000"+VendorId);
			Vendor_registration_entity.setEntityFlag("Y");
			Vendor_registration_entity.setEntryTime(formattedDate);
			Vendor_registration_entity.setEntryUser(userId);
			Vendor_registration_entity.setVerifyFlag("N");
			Vendor_registration_entity.setModifyFlag("N");
			Vendor_registration_entity.setAcc_num_flg("N");
			
			Vendor_Register_Repo.save(Vendor_registration_entity);
			msg="Vendor Added"+Vendor_registration_entity.getVendorDocId();
			}catch(Exception e) {
			msg="Not Added";
		}
	return msg;
	}
	
	/*----add expenses---*/
	
	
	public String addexpenses(Expenses_entity Expenses_entity) {
		System.out.println("the add vendor Service");
		Session session = sessionFactory.getCurrentSession();
		BigInteger ExpensesId = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR Expenses AS id").getSingleResult();
		System.out.println("the vendorid" +ExpensesId);
		String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String msg="";
		
	    try {
	    	
	    	Expenses_entity.setExp_id(ExpensesId.toString());
			
		
	    	expenses_repo.save(Expenses_entity);
		
			msg="Expenses added"+Expenses_entity.getExp_id();
			}catch(Exception e) {
			msg="Not Added";
		}
	return msg;
	}
	
	
	
	public String UpdateVendor(Vendor_registration_entity Vendor_registration_entity,String userId,String username)
	{
		String msg="";
		
		try {
			
		System.out.println("the service for modify vendor");
		 String Vendorid=Vendor_registration_entity.getVendorDocId();
		 System.out.println("vendorId" +Vendorid);
		 
		 if(Vendorid == null || Vendorid.isEmpty()) {
			 msg="Intially Id is Not Present";
		 }
		 else {
			 System.out.println("enter update service");
			 Optional<Vendor_registration_entity> entity=Vendor_Register_Repo.findById(Vendorid);
			 if(entity.isPresent()) {
				 
				 Vendor_registration_entity up=entity.get();
				 Vendor_registration_entity.setEntityFlag(up.getEntityFlag());
				 Vendor_registration_entity.setEntryTime(up.getEntryTime());
				 Vendor_registration_entity.setEntryUser(up.getEntryUser());
				 Vendor_registration_entity.setVerifyFlag(up.getVerifyFlag());
				 Vendor_registration_entity.setModifyFlag(up.getModifyFlag());
				 Vendor_Register_Repo.save(Vendor_registration_entity);
				 msg="Vendor Update Successfully..!!";
				 }else {
				 msg="Id is Not Present In Database";
			 }
		 }
		 }catch(Exception e) {
			msg="Id Not Found";
		}
		return msg;
	}
}
