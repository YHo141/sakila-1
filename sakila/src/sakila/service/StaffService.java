package sakila.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import sakila.commons.DBUtil;
import sakila.dao.IStaffDao;
import sakila.dao.StaffDao;
import sakila.vo.Staff;

public class StaffService {
	private IStaffDao iStaffDao;
	
	public StaffService(IStaffDao iStaffDao) {
		this.iStaffDao = iStaffDao;
	}
	
	private DBUtil dbUtil;
	
	// ���̵�� �����̸��� ������
	public Staff getStaffIdAndName(int staffId, String password) {
		Staff staff = null;
		
		Connection conn = null;
		
		dbUtil = new DBUtil();
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			System.out.println(conn + ": StaffService(getStaffIdAndName) connȮ��");
			
			Staff setStaff = new Staff();
			setStaff.setStaffId(staffId);
			setStaff.setPassword(password);
			
			staff = iStaffDao.selectStaffByKey(conn, setStaff);
			
			System.out.println(staff.getStaffId() + "staffId ����");
			System.out.println(staff.getUserName() + "username ����");
			
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
}
