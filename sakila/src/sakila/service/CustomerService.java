package sakila.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sakila.commons.DBUtil;
import sakila.dao.ICustomerDao;
import sakila.vo.Address;
import sakila.vo.Customer;
import sakila.vo.JoinToTable;
import sakila.vo.Rental;

public class CustomerService {
	private ICustomerDao iCustomerDao;
	
	public CustomerService(ICustomerDao iCustomerDao) {
		this.iCustomerDao = iCustomerDao;
	}
	
	private DBUtil dbUtil;
	
	public void modifyCustomer(String phone, String email, int customerId) {
		dbUtil = new DBUtil();
		
		Connection conn = null;
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			
			JoinToTable join = new JoinToTable();
			join.setAddress(new Address());
			join.setCustomer(new Customer());
			join.getAddress().setPhone(phone);
			join.getCustomer().setEmail(email);
			
			join.getCustomer().setCustomerId(customerId);
			
			iCustomerDao.updateCustomer(conn, join);
			
			conn.commit();
		} catch (Exception e) {
			System.out.println("CustomerSerive 예외 발생");
			e.printStackTrace();
			try {conn.rollback();} catch (Exception e2) {e2.printStackTrace();}
		} finally {
			try {conn.close();} catch (Exception e2) {e2.printStackTrace();}
		}
	}
	
	public List<JoinToTable> getCustomerOneByFilm(int customerId){
		List<JoinToTable> list = new ArrayList<JoinToTable>();
		dbUtil = new DBUtil();
		
		Connection conn = null;
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			
			list = iCustomerDao.selectCustomerByFilmReturn(conn, customerId);
			
			conn.commit();
		} catch (Exception e) {
			System.out.println("CustomerSerive 예외 발생");
			e.printStackTrace();
			try {conn.rollback();} catch (Exception e2) {e2.printStackTrace();}
		} finally {
			try {conn.close();} catch (Exception e2) {e2.printStackTrace();}
		}
		
		return list;
	}
	
	public JoinToTable getCustomerOne(int customerId){
		JoinToTable join = new JoinToTable();
		dbUtil = new DBUtil();
		
		Connection conn = null;
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			
			join = iCustomerDao.selectCustomerOne(conn, customerId);
			
			conn.commit();
		} catch (Exception e) {
			System.out.println("CustomerSerive 예외 발생");
			e.printStackTrace();
			try {conn.rollback();} catch (Exception e2) {e2.printStackTrace();}
		} finally {
			try {conn.close();} catch (Exception e2) {e2.printStackTrace();}
		}
		
		return join;
	}
	
	public Map<String, Object> getCustomerList(String searchName, int currentPage, int limitPage){
		Map<String, Object> map = new HashMap<String, Object>();
		dbUtil = new DBUtil();
		
		Connection conn = null;
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			
			List<JoinToTable> list = iCustomerDao.selectCustomerList(conn, searchName, currentPage, limitPage);
			int lastPage = iCustomerDao.selectCustomerListCount(conn, searchName);
			
			JoinToTable join = new JoinToTable();
			join.setRental(new Rental());
			
			for(int i=0; i<list.size(); i++) {
				int customerId = list.get(i).getCustomer().getCustomerId();
				
				List<String> returnDateList = iCustomerDao.selectCustomerListByReturnDate(conn, customerId);
				for(int j=0; j<returnDateList.size(); j++) {
					if(returnDateList.get(j) == null) {
						list.get(i).getRental().setRentalDate("대여중");
					}
				}
			}
			
			if(lastPage % limitPage == 0) {
				lastPage = lastPage / limitPage;
			} else {
				lastPage = lastPage / limitPage + 1; 
			}
			
			map.put("list", list);
			map.put("lastPage", lastPage);
			
			conn.commit();
		} catch (Exception e) {
			System.out.println("CustomerSerive 예외 발생");
			e.printStackTrace();
			try {conn.rollback();} catch (Exception e2) {e2.printStackTrace();}
		} finally {
			try {conn.close();} catch (Exception e2) {e2.printStackTrace();}
		}
		
		return map;
	}
}
