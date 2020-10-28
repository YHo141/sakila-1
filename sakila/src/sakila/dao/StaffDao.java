package sakila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.quary.StaffQuary;
import sakila.vo.Address;
import sakila.vo.City;
import sakila.vo.Country;
import sakila.vo.JoinToTable;
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
	public List<JoinToTable> selectStaff(Connection conn, int staffId) throws Exception {
		List<JoinToTable> list = new ArrayList<JoinToTable>();
		staffQuary = new StaffQuary();
		
		PreparedStatement stmt = conn.prepareStatement(staffQuary.SELECT_STAFF);
		stmt.setInt(1, staffId);
		System.out.println(stmt + ": staffDao(selectStaffy) 쿼리문 확인");
		
		ResultSet rs = stmt.executeQuery();
		System.out.println(rs + ": staffDao(selectStaffy) rs 확인");
		
		while(rs.next()) {
			JoinToTable join = new JoinToTable();
			join.setCity(new City());
			join.setCountry(new Country());
			join.setStaff(new Staff());
			join.setAddress(new Address());
			
			join.getStaff().setUserName(rs.getString("s.username"));
			join.getStaff().setLastName(rs.getString("name"));
			join.getAddress().setPhone(rs.getString("a.phone"));
			join.getCountry().setCountry(rs.getString("address"));
			join.getStaff().setEmail(rs.getString("s.email"));
			
			
			list.add(join);
		}
		
		stmt.close();
		
		return list;
	}
}












