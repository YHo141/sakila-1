package sakila.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sakila.commons.DBUtil;
import sakila.dao.IStatisticsDao;
import sakila.vo.SalesByFilmCategory;

public class StatisticsService {
	private IStatisticsDao iStatisticsDao;
	
	public StatisticsService(IStatisticsDao iStatisticsDao) {
		this.iStatisticsDao = iStatisticsDao;
	}
	
	private DBUtil dbUtil;
	
	public Map<String, Object> getStoreCategorySum(int currentPage, int limitPage){
		Map<String, Object> map = new HashMap<String, Object>();
		dbUtil = new DBUtil();
		
		Connection conn = null;
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			
			List<SalesByFilmCategory> list = iStatisticsDao.selectStoreCategorySum(conn, currentPage, limitPage);
			int lastPage = iStatisticsDao.selectStoreCategorySumCount(conn);
			
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
	
	public int getStoreRentalSum(int staffId) {
		dbUtil = new DBUtil();
		Connection conn = null;
		
		int storeRentalSum = 0;
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			
			storeRentalSum = iStatisticsDao.selectStoreRentalSum(conn, staffId);
			
			conn.commit();
		} catch (Exception e) {
			System.out.println("ActorService 예외발생");
			e.printStackTrace();
			try {conn.rollback();} catch (SQLException e2) {e2.printStackTrace();}
		} finally {
			try {conn.close();} catch (SQLException e2) {e2.printStackTrace();}
		}
		
		return storeRentalSum;
	}
	
	public float getStoreSum(int staffId) {
		dbUtil = new DBUtil();
		Connection conn = null;
		
		float storeSum = 0;
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			
			String staffName = iStatisticsDao.selectStoreByStaff(conn, staffId);
			
			storeSum = iStatisticsDao.selectStoreSum(conn, staffName);
			
			conn.commit();
		} catch (Exception e) {
			System.out.println("ActorService 예외발생");
			e.printStackTrace();
			try {conn.rollback();} catch (SQLException e2) {e2.printStackTrace();}
		} finally {
			try {conn.close();} catch (SQLException e2) {e2.printStackTrace();}
		}
		
		return storeSum;
	}
}
