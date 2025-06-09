package com.bornfire.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bornfire.config.SequenceGenerator;
import com.bornfire.entities.BDOCAccessRole;
import com.bornfire.entities.BDOCAccessRoleModTable;
import com.bornfire.entities.BDOCAccessRoleModTableRep;
import com.bornfire.entities.BDOCAccessRoleRep;
 

@Service
@ConfigurationProperties("output")
@Transactional
public class BDOCAccessRoleService {

	@Autowired
	BDOCAccessRoleRep accessandrolesrepository;

	@Autowired
	BDOCAccessRoleModTableRep BDOCAccessRoleMosRep;

	@Autowired
	SequenceGenerator sequence;
 

	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<Object[]> getdetail(String fromDate, String toDate) {

		List<Object[]> list = (List<Object[]>) sessionFactory.getCurrentSession()
				.createNativeQuery("select a.x_request_id,a.RECEIVERPARTICIPANT_BIC,(select bank_name "
						+ "from BIPS_OTHER_BANK_AGENT_TABLE where bank_agent=a.receiverparticipant_bic)BANK_NAME,a.SCHM_NAME,"
						+ "a.IDENTIFICATION,a.PSU_ID_TYPE,a.PSU_ID,a.PHONE_NUMBER,a.READ_BALANCE,"
						+ "a.READ_DEBIT_ACCT,a.READ_TRAN_DETAILS,a.READ_ACCT_DETAILS,a.CREATE_DATE,a.del_flg,a.del_time from BIPS_CONSENT_OUTWARD_ACCESS_TABLE a "
						+ "where trunc(a.create_date) between '" + fromDate + "' and '" + toDate
						+ "' order by a.create_date asc")
				.getResultList();

		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getdetailCoI(String tranDate) {

		List<Object[]> list = (List<Object[]>) sessionFactory.getCurrentSession().createNativeQuery(
				"select a.x_request_id,a.RECEIVERPARTICIPANT_BIC,(select bank_name from BIPS_OTHER_BANK_AGENT_TABLE where bank_agent=a.receiverparticipant_bic)BANK_NAME,a.INQUIRY_TYPE,a.ACCOUNT_ID,a.PSU_ID_TYPE,a.PSU_ID,a.STATUS,a.STATUS_ERROR"
						+ " from BIPS_CONSENT_OUTWARD_INQUIRY_TABLE a where (a.Inquiry_type='ReadBalances' OR Inquiry_type='ReadBalances' OR a.Inquiry_type='ReadAccountsDetails' OR a.Inquiry_type='ReadTransactionsDetails') and trunc(entry_time)='"
						+ tranDate + "' order by a.entry_time asc")
				.getResultList();

		return list;

	}

	public String addPARAMETER(BDOCAccessRole accessRole, String USERID, String formmode, String adminValue,
			String userprofilevalue, String accessValue, String organizationmastvalue, String finalString) {

		String msg = "";

		if (formmode.equals("add")) {
			BDOCAccessRoleModTable up = new BDOCAccessRoleModTable(accessRole);
			String count = accessandrolesrepository.getusercount(up.getRole_id());
			if (count.equals("0")) {
			 
				up.setDel_flg("N");
				up.setModify_flg("N");
				up.setEntity_flg("N");
				up.setNew_role_flg("Y");
				up.setEntry_user(USERID);
				up.setEntry_time(new Date());
				up.setModify_user(USERID);
				up.setModify_time(new Date());
				up.setAdmin(adminValue);
				up.setUser_profile_maintenance(userprofilevalue);
				up.setAccess_role(accessValue);
				up.setMenulist(finalString);
				up.setOrganization_master(organizationmastvalue);
				BDOCAccessRoleMosRep.save(up);
				msg = "Role Created Successfully";
 

			} else {
				msg = "Role Already Exist";
			}
		} else if (formmode.equals("edit")) {
			BDOCAccessRole up = accessRole;
			BDOCAccessRoleModTable up1 = new BDOCAccessRoleModTable(up);
			Optional<BDOCAccessRole> user = accessandrolesrepository.findById(accessRole.getRole_id());
			BDOCAccessRole user1 = user.get();
			if (isNullCheck(up.getRole_desc()).equals(isNullCheck(user1.getRole_desc()))
					&& isNullCheck(up.getPermissions()).equals(isNullCheck(user1.getPermissions()))
					&& isNullCheck(up.getRemarks()).equals(isNullCheck(user1.getRemarks()))
					&& isNullCheck(up.getWork_class()).equals(isNullCheck(user1.getWork_class()))
					&& finalString.equals(isNullCheck(user1.getMenulist()))) {
				msg = "No any modification done";
			} else {
				up1.setAdmin(adminValue);
				if (!finalString.equals("")) {
					up1.setMenulist(finalString);
				} else {
					up1.setMenulist(user.get().getMenulist());
				}
				up1.setDel_flg("N");
				up1.setModify_flg("Y");
				up1.setEntity_flg("N");
				up1.setAdmin(adminValue);
				up1.setNew_role_flg("N");
				up1.setEntry_user(user1.getEntry_user());
				up1.setEntry_time(user1.getEntry_time());
				up1.setAuth_user(user1.getAuth_user());
				up1.setAuth_time(user1.getAuth_time());
				up1.setModify_user(USERID);
				up1.setModify_time(new Date());
				Session session = sessionFactory.getCurrentSession();
				session.saveOrUpdate(up1);
				user1.setEntity_flg("N");
				accessandrolesrepository.save(user1);
				msg = "Role Edited Successfully";
				StringBuilder stringBuilder = new StringBuilder();
				if (isNullCheck(up.getRole_desc()).equals(isNullCheck(user1.getRole_desc()))
						|| isNullCheck(up.getPermissions()).equals(isNullCheck(user1.getPermissions()))
						|| isNullCheck(up.getRemarks()).equals(isNullCheck(user1.getRemarks()))
						|| isNullCheck(up.getWork_class()).equals(isNullCheck(user1.getWork_class()))
						|| finalString.equals(isNullCheck(user1.getMenulist()))) {

					if (!isNullCheck(up.getRole_desc()).equals(isNullCheck(user1.getRole_desc()))) {
						stringBuilder = stringBuilder
								.append("Role Desc+" + user1.getRole_desc() + "+" + up.getRole_desc() + "||");
					}

					if (!isNullCheck(up.getPermissions()).equals(isNullCheck(user1.getPermissions()))) {
						stringBuilder = stringBuilder
								.append("Permission+" + user1.getPermissions() + "+" + up.getPermissions() + "||");
					}

					if (!isNullCheck(up.getRemarks()).equals(isNullCheck(user1.getRemarks()))) {
						stringBuilder = stringBuilder
								.append("Remarks+" + user1.getRemarks() + "+" + up.getRemarks() + "||");
					}
					if (!isNullCheck(up.getWork_class()).equals(isNullCheck(user1.getWork_class()))) {
						stringBuilder = stringBuilder
								.append("Work Class+" + user1.getWork_class() + "+" + up.getWork_class() + "||");
					}

					if (!finalString.equals(isNullCheck(user1.getMenulist()))) {
						if (!finalString.equals(user1.getMenulist()))
							stringBuilder = stringBuilder
									.append("Menu List+" + user1.getMenulist() + "+" + finalString + "||");
					}
 

				}
			}
		} else if (formmode.equals("delete")) {

			Optional<BDOCAccessRole> user = accessandrolesrepository.findById(accessRole.getRole_id());
			BigDecimal bg = accessandrolesrepository.userrolecount(accessRole.getRole_id());
			BigDecimal bs = new BigDecimal(0);
			if (bg.equals(bs)) {
				BDOCAccessRole accessRoleTable = user.get();
				accessRoleTable.setDel_flg("Y");
				accessRoleTable.setEntity_flg("N");
				accessandrolesrepository.save(accessRoleTable);
				msg = "Role Deleted Successfully";

				 

			} else {
				msg = "This Role already assigned to Users,Can't delete";
			}

		} else if (formmode.equals("verify")) {
			String verifyAccessRole = verifyAccessRole(accessRole.getRole_id(), USERID);
			Optional<BDOCAccessRoleModTable> dat = BDOCAccessRoleMosRep.findById(accessRole.getRole_id());
			if (verifyAccessRole.equals("0")) {
				msg = "Role Verified Successfully";
				BDOCAccessRoleMosRep.deleteById(accessRole.getRole_id());

				return msg;
			}
		} else if (formmode.equals("cancel")) {
			BDOCAccessRoleModTable up2 = BDOCAccessRoleMosRep.findByIDcustom(accessRole.getRole_id());

			//System.out.println("CancelAcc" + up2.getNew_role_flg());
			if (up2.getNew_role_flg().equals("Y")) {
				msg = "Role Cancelled Successfully";
				BDOCAccessRoleMosRep.deleteById(accessRole.getRole_id());
				return msg;
			} else {
				String status = cancelRoleModification(accessRole.getRole_id(), USERID);
				Optional<BDOCAccessRoleModTable> dat = BDOCAccessRoleMosRep.findById(accessRole.getRole_id());

		 
			}

		}
		return msg;
	}

	private String cancelRoleModification(String role_id, String USERID) {
		Optional<BDOCAccessRole> user = accessandrolesrepository.findById(role_id);
		BDOCAccessRole user1 = user.get();
		user1.setDel_flg("N");
		user1.setModify_flg("N");
		user1.setEntity_flg("Y");
		user1.setNew_role_flg("N");
		user1.setAuth_user(USERID);
		user1.setAuth_time(new Date());

		accessandrolesrepository.save(user1);
		return "0";
	}

	private String verifyAccessRole(String role_id, String USERID) {
		Optional<BDOCAccessRoleModTable> user = BDOCAccessRoleMosRep.findById(role_id);
		BDOCAccessRoleModTable user1 = user.get();
		user1.setDel_flg("N");
		user1.setModify_flg("N");
		user1.setEntity_flg("Y");
		user1.setNew_role_flg("N");
		user1.setAuth_user(USERID);
		user1.setAuth_time(new Date());
		BDOCAccessRole verifiedData = new BDOCAccessRole(user1);
		accessandrolesrepository.save(verifiedData);
		return "0";
	}

	public BDOCAccessRole getRoleId(String id) {
		Session session = sessionFactory.getCurrentSession();
		Query<BDOCAccessRole> query = session
				.createQuery(" from BDOCAccessRole where role_id=?1 and NVL(DEL_FLG,1) <> 'Y'", BDOCAccessRole.class);
		query.setParameter(1, id);
		List<BDOCAccessRole> result = query.getResultList();
		if (!result.isEmpty()) {
			return result.get(0);
		} else {
			return new BDOCAccessRole();
		}

	}

	public BDOCAccessRole getRoleMenu(String id) {
		Session session = sessionFactory.getCurrentSession();
		Query<BDOCAccessRole> query = session.createQuery("from BDOCAccessRole where role_id=?1", BDOCAccessRole.class);
		query.setParameter(1, id);
		List<BDOCAccessRole> result = query.getResultList();
		if (!result.isEmpty()) {
			return result.get(0);
		} else {
			return new BDOCAccessRole();
		}
	}

 

	public String deleteRole(String userid) {
		Session hs = sessionFactory.getCurrentSession();
		@SuppressWarnings("rawtypes")
		Query qr;
		qr = hs.createQuery("select count(*) from UserProfile where role_id= ?1");
		qr.setParameter(1, userid);
		long count = (long) qr.getSingleResult();
		String msg = "";
		if (count == 0) {
			Optional<BDOCAccessRole> user = accessandrolesrepository.findById(userid);
			BDOCAccessRole reg = user.get();
			reg.setDel_flg("Y");
			accessandrolesrepository.save(reg);
			msg = "Role Deleted Successfully";
		} else {
			msg = "This role has been assigned to an User.Cannot Delete ";
		}
		return msg;
	}

	public List<BDOCAccessRole> rulelist() {
		List<BDOCAccessRole> list = accessandrolesrepository.finsAllData();
		/*
		 * for (BDOCAccessRole a : list) { String menu= a.getMenulist(); String caps=
		 * menu.replaceAll("([a-z])([A-Z])", "$1 $2"); a.setMenulist(caps);
		 * 
		 * }
		 */
		
		return list;
	}

	public BDOCAccessRoleModTable getModifyData(String roleID) {
		Optional<BDOCAccessRoleModTable> list = BDOCAccessRoleMosRep.findById(roleID);
		if (list.isPresent()) {
			
			return list.get();
		}
		return null;
	}

	public BDOCAccessRoleModTable getViewNewData(String roleID) {
		Optional<BDOCAccessRoleModTable> list = BDOCAccessRoleMosRep.findById(roleID);
		if (list.isPresent()) {
			return list.get();
		} else {
			return new BDOCAccessRoleModTable();
		}
	}

	private String isNullCheck(String inpData) {
		String outData = "";
		if (inpData != null) {
			if (!String.valueOf(inpData).equals("null")) {
				if (!String.valueOf(inpData).equals("")) {
					outData = String.valueOf(inpData);
					return outData;
				}
			}
		}
		return outData;
	}
}