package sakila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import sakila.quary.StaffQuary;
import sakila.vo.Staff;

public class StaffDao implements IStaffDao{
	private StaffQuary staffQuary;
	
	@Override	// 사용자 로그인 확인
	public Staff selectStaffByKey(Connection conn, Staff staff) throws Exception {
		Staff returnStaff = null;
		staffQuary = new StaffQuary();
		
		PreparedStatement stmt = conn.prepareStatement(staffQuary.SELECT_STAFF_BY_KEY);
		stmt.setInt(1, staff.getStaffId());
		stmt.setString(2, staff.getPassword());
		System.out.println(stmt + ": staffDao(selectStaffByKey) 쿼리문 확인");
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			returnStaff = new Staff();
			
			returnStaff.setStaffId(rs.getInt("staff_id"));
			returnStaff.setUserName(rs.getString("username"));
			
			System.out.println(rs.getInt("staff_id"));
			System.out.println(rs.getString("username"));
		}
		
		stmt.close();
		
		return returnStaff;
	}
}
