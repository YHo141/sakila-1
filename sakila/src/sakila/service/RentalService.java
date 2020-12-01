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
	
	public Map<String, Object> getFilmRentalList(int currentPage, int limitPage, String searchTitle) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<JoinToTable> list = new ArrayList<JoinToTable>();
		int lastPage = 0;
		
		dbUtil = new DBUtil();
		
		Connection conn = null;
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			
			if(searchTitle.equals("")) {
				list = iRentalDao.selectFilmReturnList(conn, currentPage, limitPage);
				lastPage = iRentalDao.selectFilmReturnListCount(conn);
				System.out.println(list + "RentalService�� list Ȯ��");
				System.out.println(lastPage + "RentalService�� lastPage Ȯ��");
			} else {
				list = iRentalDao.selectFilmReturnListByTitle(conn, currentPage, limitPage, searchTitle);
				lastPage = iRentalDao.selectFilmReturnListByTitleCount(conn, searchTitle);
				System.out.println(list + "RentalService�� list Ȯ��");
				System.out.println(lastPage + "RentalService�� lastPage Ȯ��");
			}
			
			if(lastPage % limitPage == 0) {
				lastPage = lastPage / limitPage - 1;
			} else {
				lastPage = lastPage / limitPage;
			}
			
			map.put("list", list);
			map.put("lastPage", lastPage + 1);
			
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
	
	public void getUpdateRentalReturnDate(int rentalId) {
		dbUtil = new DBUtil();
		Connection conn = null;
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			
			iRentalDao.updateRentalReturnDate(conn, rentalId);
			
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {conn.rollback();} catch (Exception e2) {e2.printStackTrace();}
		} finally {
			try {conn.close();} catch (Exception e2) {e2.printStackTrace();}
		}
	}
}
























