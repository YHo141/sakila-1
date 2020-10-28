package sakila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.quary.CustomerQuary;
import sakila.vo.Address;
import sakila.vo.Customer;
import sakila.vo.JoinToTable;
import sakila.vo.Rental;

public class CustomerDao implements ICustomerDao{
	private CustomerQuary customerQuary;
	
	@Override // 회원 목록 페이지 출력 Dao 
	public List<JoinToTable> selectCustomerList(Connection conn) throws Exception{
		List<JoinToTable> list = new ArrayList<JoinToTable>();
		
		PreparedStatement stmt = conn.prepareStatement(customerQuary.SELECT_CUSTOMER_LIST);
		System.out.println(stmt + ": CustomerDao의 stmt 확인");
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			JoinToTable join = new JoinToTable();
			join.setAddress(new Address());
			join.setCustomer(new Customer());
			join.setRental(new Rental());
			
			join.getCustomer().setLastName(rs.getString("name"));
			join.getAddress().setPhone(rs.getString("a.phone"));
			join.getCustomer().setActive(rs.getInt("c.active"));
			join.getRental().setRentalDate(rs.getString("r.return_date"));
			
			list.add(join);
		}
		stmt.close();
		
		return list;
	}
}
