package sakila.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import sakila.commons.DBUtil;
import sakila.dao.ICustomerDao;
import sakila.vo.JoinToTable;

public class CustomerService {
	private ICustomerDao iCustomerDao;
	
	public CustomerService(ICustomerDao iCustomerDao) {
		this.iCustomerDao = iCustomerDao;
	}
	
	private DBUtil dbUtil;
	
	public List<JoinToTable> getCustomerList(){
		List<JoinToTable> list = new ArrayList<JoinToTable>();
		dbUtil = new DBUtil();
		
		Connection conn = null;
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			
			list = iCustomerDao.selectCustomerList(conn);
			
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
}
