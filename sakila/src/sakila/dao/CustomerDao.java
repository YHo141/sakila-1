package sakila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.quary.CustomerQuary;
import sakila.vo.Address;
import sakila.vo.City;
import sakila.vo.Country;
import sakila.vo.Customer;
import sakila.vo.Film;
import sakila.vo.JoinToTable;
import sakila.vo.Rental;

public class CustomerDao implements ICustomerDao{
	private CustomerQuary customerQuary;
	
	@Override
	public void updateCustomer(Connection conn, JoinToTable join) throws Exception{
		customerQuary = new CustomerQuary();
		
		PreparedStatement stmt = conn.prepareStatement(customerQuary.UPDATE_CUSTOMER);
		stmt.setString(1, join.getAddress().getPhone());
		stmt.setString(2, join.getCustomer().getEmail());
		stmt.setInt(3, join.getCustomer().getCustomerId());
		
		stmt.executeLargeUpdate();
		
		stmt.close();
	}
	
	@Override
	public List<JoinToTable> selectCustomerByFilmReturn(Connection conn, int customerId) throws Exception{
		List<JoinToTable> list = new ArrayList<JoinToTable>();
		customerQuary = new CustomerQuary();
		
		PreparedStatement stmt = conn.prepareStatement(customerQuary.SELECT_CUSTOMER_BY_FILM_RETURN);
		stmt.setInt(1, customerId);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			JoinToTable join = new JoinToTable();
			join.setFilm(new Film());
			join.setRental(new Rental());
			
			join.getFilm().setTitle(rs.getString("title"));
			join.getRental().setRentalDate(rs.getString("rentalDate"));
			join.getRental().setScheduledReturnDate(rs.getString("scheduledReturnDate"));
			join.getRental().setArrears(rs.getString("arrears"));
			
			list.add(join);
		}
		
		stmt.close();
		rs.close();
		
		return list;
	}
	
	@Override
	public JoinToTable selectCustomerOne(Connection conn, int customerId) throws Exception{
		JoinToTable join = new JoinToTable();
		customerQuary = new CustomerQuary();
		
		PreparedStatement stmt = conn.prepareStatement(customerQuary.SELECT_CUSTOMER_ONE);
		stmt.setInt(1, customerId);
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			join.setCustomer(new Customer());
			join.setAddress(new Address());
			join.setCity(new City());
			join.setCountry(new Country());
			
			join.getCustomer().setCustomerId(rs.getInt("customerId"));
			join.getCustomer().setStoreId(rs.getInt("storeId"));
			join.getCustomer().setLastName(rs.getString("customerName"));
			join.getAddress().setPhone(rs.getString("phone"));
			join.getAddress().setAddress(rs.getString("address"));
			join.getCustomer().setEmail(rs.getString("email"));
			
		}
		
		stmt.close();
		rs.close();
		
		return join;
	}
	
	@Override
	public int selectCustomerListCount(Connection conn, String searchName) throws Exception{
		int lastPage = 0;
		customerQuary = new CustomerQuary();
		
		PreparedStatement stmt = conn.prepareStatement(customerQuary.SELECT_CUSTOMER_LIST_COUNT);
		stmt.setString(1, "%" + searchName + "%");
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			lastPage++;
		}
		
		stmt.close();
		rs.close();
		
		return lastPage;
	}
	
	@Override
	public List<String> selectCustomerListByReturnDate(Connection conn, int customerId) throws Exception {
		List<String> list = new ArrayList<String>();
		customerQuary = new CustomerQuary();
		
		PreparedStatement stmt = conn.prepareStatement(customerQuary.SELECT_CUSTOMER_LIST_BY_RETURN_DATE);
		stmt.setInt(1, customerId);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			String returnDate = rs.getString("returnDate");
			
			list.add(returnDate);
		}
		
		stmt.close();
		rs.close();
				
		return list;
	}
	
	@Override // 회원 목록 페이지 출력 Dao 
	public List<JoinToTable> selectCustomerList(Connection conn, String searchName, int currentPage, int limitPage) throws Exception{
		List<JoinToTable> list = new ArrayList<JoinToTable>();
		customerQuary = new CustomerQuary();
		
		PreparedStatement stmt = conn.prepareStatement(customerQuary.SELECT_CUSTOMER_LIST);
		stmt.setString(1, "%" + searchName + "%");
		stmt.setInt(2, (currentPage - 1)*limitPage);
		stmt.setInt(3, limitPage);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			JoinToTable join = new JoinToTable();
			join.setAddress(new Address());
			join.setCustomer(new Customer());
			join.setRental(new Rental());
			
			join.getCustomer().setCustomerId(rs.getInt("customerId"));
			join.getCustomer().setLastName(rs.getString("name"));
			join.getAddress().setPhone(rs.getString("phone"));
			join.getCustomer().setActive(rs.getInt("active"));
			
			list.add(join);
		}
		
		stmt.close();
		rs.close();
		
		return list;
	}
}
