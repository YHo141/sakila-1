package sakila.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sakila.commons.DBUtil;
import sakila.dao.IStaffDao;
import sakila.dao.StaffDao;
import sakila.vo.CoAndCiAndAAndS;
import sakila.vo.Staff;

public class StaffService {
	private IStaffDao iStaffDao;
	
	public StaffService(IStaffDao iStaffDao) {
		this.iStaffDao = iStaffDao;
	}
	
	private DBUtil dbUtil;
	
	// 아이디와 유저이름을 가져옴
	public Staff getStaffIdAndName(int staffId, String password) {
		Staff staff = null;
		
		Connection conn = null;
		dbUtil = new DBUtil();
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			System.out.println(conn + ": StaffService(getStaffIdAndName) conn확인");
			
			Staff setStaff = new Staff();
			setStaff.setStaffId(staffId);
			setStaff.setPassword(password);
			
			staff = iStaffDao.selectStaffByKey(conn, setStaff);
			
			System.out.println(staff.getStaffId() + "staffId 최종");
			System.out.println(staff.getUserName() + "username 최종");
			
			conn.commit();
			
		} catch (Exception e) {
			System.out.println("StaffService 예외발생");
			e.printStackTrace();
			try {conn.rollback();} catch (Exception e1) {e1.printStackTrace();}
		} finally {
			try {conn.close();} catch (Exception e1) {e1.printStackTrace();}
		}
		
		
		return staff;
	}
	
	public List<CoAndCiAndAAndS> getStaff(int staffId){
		List<CoAndCiAndAAndS> list = new ArrayList<CoAndCiAndAAndS>();
		
		Connection conn = null;
		dbUtil = new DBUtil();	
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			System.out.println(conn + ": StaffService(getStaff) conn확인");
			
			list = iStaffDao.selectStaff(conn, staffId);
			System.out.println(conn + ": StaffService(getStaff) list확인");
			
			conn.commit();
		} catch (Exception e) {
			System.out.println("StaffService 예외발생");
			e.printStackTrace();
			try {conn.rollback();} catch (Exception e1) {e1.printStackTrace();}
		} finally {
			try {conn.close();} catch (Exception e1) {e1.printStackTrace();}
		}
		
		
		return list;
	}
}





























