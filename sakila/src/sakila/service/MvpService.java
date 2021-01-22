package sakila.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sakila.commons.DBUtil;
import sakila.dao.IMvpDao;
import sakila.vo.JoinToTable;

public class MvpService {
	private IMvpDao iMvpDao;
	
	public MvpService(IMvpDao iMvpDao) {
		this.iMvpDao = iMvpDao;
	}
	
	private DBUtil dbUtil;
	
	public Map<String, Object> getPaymentByMvp(int currentPage, int limitPage) {
		dbUtil = new DBUtil();
		Map<String, Object> map = new HashMap<String, Object>();
		
		Connection conn = null;
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			
			List<JoinToTable> list = iMvpDao.selectPaymentByMvp(conn, currentPage, limitPage);
			int lastPage = iMvpDao.selectPaymentByMvpCount(conn);
			
			if(lastPage % limitPage == 0) {
				lastPage = lastPage / limitPage;
			} else {
				lastPage = lastPage / limitPage + 1;
			}
			
			map.put("list", list);
			map.put("lastPage", lastPage);
			
			conn.commit();
		} catch (Exception e) {
			System.out.println("ActorService 예외발생");
			e.printStackTrace();
			try {conn.rollback();} catch (SQLException e2) {e2.printStackTrace();}
		} finally {
			try {conn.close();} catch (SQLException e2) {e2.printStackTrace();}
		}
		
		return map;
	}
}
