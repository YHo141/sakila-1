package sakila.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import sakila.commons.DBUtil;
import sakila.dao.IRentalDao;
import sakila.vo.JoinToTable;

public class RentalService {
	private IRentalDao iRentalDao;
	
	public RentalService(IRentalDao iRentalDao){
		this.iRentalDao = iRentalDao;
	}
	
	private DBUtil dbUtil;
	
	public List<JoinToTable> getFilmRentalList() {
		List<JoinToTable> list = new ArrayList<JoinToTable>();
		dbUtil = new DBUtil();
		
		Connection conn = null;
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			
			list = iRentalDao.selectFilmReturnList(conn);
			System.out.println(list + "RentalService의 list 확인");
			
			conn.commit();
		} catch (Exception e) {
			System.out.println("RentalService 예외발생");
			e.printStackTrace();
			try {conn.rollback();} catch (Exception e2) {e2.printStackTrace();}
		} finally {
			try {conn.close();} catch (Exception e2) {e2.printStackTrace();}
		}
		
		return list;
	}
}
