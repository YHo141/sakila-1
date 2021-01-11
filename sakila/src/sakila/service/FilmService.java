package sakila.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import sakila.commons.DBUtil;
import sakila.dao.IFilmDao;
import sakila.vo.JoinToTable;

public class FilmService {
	private IFilmDao iFilmDao;
	
	public FilmService(IFilmDao iFilmDao) {
		this.iFilmDao = iFilmDao;
	}
	
	private DBUtil dbUtil;
	
	// 영화 목록을 가져옴
	public List<JoinToTable> getFilmList() {
		List<JoinToTable> list = new ArrayList<JoinToTable>();
		dbUtil = new DBUtil();
		
		Connection conn = null;
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			
			list = iFilmDao.selectFilmList(conn);
			
			conn.commit();
		} catch (Exception e) {
			System.out.println("FilmService 예외 발생");
			e.printStackTrace();
			try {conn.rollback();} catch (Exception e2) {e2.printStackTrace();}
		} finally {
			try {conn.close();} catch (Exception e2) {e2.printStackTrace();}
		}
		
		
		return list;
	}
	
	// 영화 재고 목록을 가져옴
	public Map<String, Object> getFilmPromotionNullList(int currentPage, int limitPage, String searchTitle) {
		Map<String, Object> map = new HashMap<String, Object>();
		dbUtil = new DBUtil();
		
		Connection conn = null;
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			
			List<JoinToTable> list = iFilmDao.selectFilmPromotionNullList(conn, currentPage, limitPage, searchTitle);
			int lastPage = iFilmDao.selectFilmPromotionNullCount(conn, searchTitle);
			
			if(lastPage % limitPage == 0) {
				lastPage = lastPage / limitPage;
			} else {
				lastPage = lastPage / limitPage + 1;
			}
			
			map.put("list", list);
			map.put("lastPage", lastPage);
			
			conn.commit();
		} catch (Exception e) {
			System.out.println("FilmService 예외 발생");
			e.printStackTrace();
			try {conn.rollback();} catch (Exception e2) {e2.printStackTrace();}
		} finally {
			try {conn.close();} catch (Exception e2) {e2.printStackTrace();}
		}
		
		
		return map;
	}
	
	// 영화 재고 목록을 가져옴
	public Map<String, Object> getFilmPromotionNotnullList(int currentPage, int limitPage, String searchTitle) {
		Map<String, Object> map = new HashMap<String, Object>();
		dbUtil = new DBUtil();
		
		Connection conn = null;
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			
			List<JoinToTable> list = iFilmDao.selectFilmPromotionNotnullList(conn, currentPage, limitPage, searchTitle);
			int lastPage = iFilmDao.selectFilmPromotionNotnullCount(conn, searchTitle);
			
			if(lastPage % limitPage == 0) {
				lastPage = lastPage / limitPage;
			} else {
				lastPage = lastPage / limitPage + 1;
			}
			
			map.put("list", list);
			map.put("lastPage", lastPage);
			
			conn.commit();
		} catch (Exception e) {
			System.out.println("FilmService 예외 발생");
			e.printStackTrace();
			try {conn.rollback();} catch (Exception e2) {e2.printStackTrace();}
		} finally {
			try {conn.close();} catch (Exception e2) {e2.printStackTrace();}
		}
		
		
		return map;
	}
	
	// 영화 재고 목록을 가져옴
	public Map<String, Object> getFilmPromotionList(int currentPage, int limitPage, String searchTitle) {
		Map<String, Object> map = new HashMap<String, Object>();
		dbUtil = new DBUtil();
			
		Connection conn = null;
			
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
				
			List<JoinToTable> list = iFilmDao.selectFilmPromotionList(conn, currentPage, limitPage, searchTitle);
			int lastPage = iFilmDao.selectFilmPromotionCount(conn, searchTitle);
			
			if(lastPage % limitPage == 0) {
				lastPage = lastPage / limitPage;
			} else {
				lastPage = lastPage / limitPage + 1;
			}
			
			map.put("list", list);
			map.put("lastPage", lastPage);
				
			conn.commit();
		} catch (Exception e) {
			System.out.println("FilmService 예외 발생");
			e.printStackTrace();
			try {conn.rollback();} catch (Exception e2) {e2.printStackTrace();}
		} finally {
			try {conn.close();} catch (Exception e2) {e2.printStackTrace();}
		}
			
		
		return map;
	}
}
