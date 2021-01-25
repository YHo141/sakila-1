package sakila.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import sakila.commons.DBUtil;
import sakila.dao.IStaffDao;
import sakila.vo.Address;
import sakila.vo.JoinToTable;
import sakila.vo.Staff;

public class StaffService {
	private IStaffDao iStaffDao;
	
	public StaffService(IStaffDao iStaffDao) {
		this.iStaffDao = iStaffDao;
	}
	
	private DBUtil dbUtil;
	
	public void modifyStaff(String username, String email, String phone, int staffId) {
		Connection conn = null;
		dbUtil = new DBUtil();
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			
			JoinToTable join = new JoinToTable();
			join.setAddress(new Address());
			join.setStaff(new Staff());
			
			join.getAddress().setPhone(phone);
			join.getStaff().setStaffId(staffId);
			join.getStaff().setEmail(email);
			join.getStaff().setUserName(username);
			
			iStaffDao.updateStaff(conn, join);
			
			conn.commit();
			
		} catch (Exception e) {
			System.out.println("StaffService ���ܹ߻�");
			e.printStackTrace();
			try {conn.rollback();} catch (Exception e1) {e1.printStackTrace();}
		} finally {
			try {conn.close();} catch (Exception e1) {e1.printStackTrace();}
		}
	}
	
	// ���̵�� �����̸��� ������
	public Staff getStaffIdAndName(int staffId, String password) {
		Staff staff = null;
		
		Connection conn = null;
		dbUtil = new DBUtil();
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			//System.out.println(conn + ": StaffService(getStaffIdAndName) connȮ��");
			
			Staff setStaff = new Staff();
			setStaff.setStaffId(staffId);
			setStaff.setPassword(password);
			
			staff = iStaffDao.selectStaffByKey(conn, setStaff);
			
			//System.out.println(staff.getStaffId() + "staffId ����");
			//System.out.println(staff.getUserName() + "username ����");
			
			conn.commit();
			
		} catch (Exception e) {
			System.out.println("StaffService ���ܹ߻�");
			e.printStackTrace();
			try {conn.rollback();} catch (Exception e1) {e1.printStackTrace();}
		} finally {
			try {conn.close();} catch (Exception e1) {e1.printStackTrace();}
		}
		
		
		return staff;
	}
	
	public List<JoinToTable> getStaff(int staffId){
		List<JoinToTable> list = new ArrayList<JoinToTable>();
		
		Connection conn = null;
		dbUtil = new DBUtil();	
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			//System.out.println(conn + ": StaffService(getStaff) connȮ��");
			
			
			list = iStaffDao.selectStaff(conn, staffId);
			//System.out.println(conn + ": StaffService(getStaff) listȮ��");
			
			conn.commit();
		} catch (Exception e) {
			System.out.println("StaffService ���ܹ߻�");
			e.printStackTrace();
			try {conn.rollback();} catch (Exception e1) {e1.printStackTrace();}
		} finally {
			try {conn.close();} catch (Exception e1) {e1.printStackTrace();}
		}
		
		return list;
	}
}





























