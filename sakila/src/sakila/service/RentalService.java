package sakila.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sakila.commons.DBUtil;
import sakila.dao.IRentalDao;
import sakila.vo.JoinToTable;

public class RentalService {
	private IRentalDao iRentalDao;
	
	public RentalService(IRentalDao iRentalDao){
		this.iRentalDao = iRentalDao;
	}
	
	private DBUtil dbUtil;
	
	public Map<String, Object> getFilmRentalList(int currentPage, int limitPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		dbUtil = new DBUtil();
		
		Connection conn = null;
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			
			List<JoinToTable> list = iRentalDao.selectFilmReturnList(conn, currentPage, limitPage);
			int lastPage = iRentalDao.selectFilmReturnListCount(conn);
			if(lastPage % limitPage == 0) {
				lastPage = lastPage / limitPage - 1;
			} else {
				lastPage = lastPage / limitPage;
			}
			
			System.out.println(list + "RentalService�� list Ȯ��");
			System.out.println(lastPage + "RentalService�� lastPage Ȯ��");
			
			map.put("list", list);
			map.put("lastPage", lastPage);
			
			conn.commit();
		} catch (Exception e) {
			System.out.println("RentalService ���ܹ߻�");
			e.printStackTrace();
			try {conn.rollback();} catch (Exception e2) {e2.printStackTrace();}
		} finally {
			try {conn.close();} catch (Exception e2) {e2.printStackTrace();}
		}
		
		return map;
	}
}
