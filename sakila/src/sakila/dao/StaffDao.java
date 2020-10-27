package sakila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.quary.StaffQuary;
import sakila.vo.Address;
import sakila.vo.City;
import sakila.vo.CoAndCiAndAAndS;
import sakila.vo.Country;
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
	
	@Override
	public List<CoAndCiAndAAndS> selectStaff(Connection conn, int staffId) throws Exception {
		List<CoAndCiAndAAndS> list = new ArrayList<CoAndCiAndAAndS>();
		staffQuary = new StaffQuary();
		
		PreparedStatement stmt = conn.prepareStatement(staffQuary.SELECT_STAFF);
		stmt.setInt(1, staffId);
		System.out.println(stmt + ": staffDao(selectStaffy) 쿼리문 확인");
		
		ResultSet rs = stmt.executeQuery();
		System.out.println(rs + ": staffDao(selectStaffy) rs 확인");
		
		while(rs.next()) {
			CoAndCiAndAAndS returnStaff = new CoAndCiAndAAndS();
			returnStaff.setCity(new City());
			returnStaff.setCountry(new Country());
			returnStaff.setStaff(new Staff());
			returnStaff.setAddress(new Address());
			
			returnStaff.getStaff().setUserName(rs.getString("s.username"));
			returnStaff.getStaff().setLastName(rs.getString("name"));
			returnStaff.getAddress().setPhone(rs.getString("a.phone"));
			returnStaff.getCountry().setCountry(rs.getString("address"));
			returnStaff.getStaff().setEmail(rs.getString("s.email"));
			
			
			list.add(returnStaff);
		}
		
		stmt.close();
		
		return list;
	}
}












