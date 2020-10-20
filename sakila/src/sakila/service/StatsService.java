package sakila.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import sakila.commons.DBUtil;
import sakila.commons.DayUtil;
import sakila.dao.StatsDao;
import sakila.vo.Stats;

public class StatsService {
	private StatsDao statsDao;
	private DBUtil dbUtil;
	private DayUtil dayUtil;
	// 
	public void countStats() {
		Connection conn = null;
		statsDao = new StatsDao();
		dbUtil = new DBUtil();
		dayUtil = new DayUtil();
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			System.out.println(conn + "<-- service(countStats) conn");
			
			String day = dayUtil.getDay();
			System.out.println(day + ": service(countStats) day값");
			
			Stats stats = new Stats();
			stats.setDay(day);
			
			if(statsDao.selectDay(conn, stats)) {
				statsDao.updateStats(conn, stats);
			}
			else {
				statsDao.insertState(conn, stats);
			}
			
			conn.commit();
		} catch (Exception e) {
			System.out.println("service 예외 발생");
			try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();}
		} finally {
			try {conn.close();} catch (SQLException e) {e.printStackTrace();}
		}
	}
	
	// 두개의 리턴 값을 가지고 서비스 메소드 ,, 방문자수 확인을 위한 메소드(오늘, 전체)
	public Map<String, Object> getStats() {
		Map<String, Object> map = new HashMap<String, Object>();;
		
		int sumCnt = 1;
		Stats stats = null;
		Connection conn = null;
		
		statsDao = new StatsDao();
		dbUtil = new DBUtil();
		dayUtil = new DayUtil();
		
		try {	
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			System.out.println(conn + "<-- service(getStats) conn");
			
			String day = dayUtil.getDay();
			System.out.println(day + ": service(getStats) day값");
			
			stats = new Stats();
			stats.setDay(day);
			stats.setCount(statsDao.selectCnt(conn, stats));
			sumCnt = statsDao.selectSumCnt(conn);
			
			// map에 넣어줌
			map.put("stats", stats);
			map.put("sumCnt", sumCnt);
			
			conn.commit();
		} catch (Exception e) {
			System.out.println("service 예외 발생");
			try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();}
		} finally {
			try {conn.close();} catch (SQLException e) {e.printStackTrace();}
		}
		
		return map;
	}
}












