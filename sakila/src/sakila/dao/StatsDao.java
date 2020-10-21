package sakila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import sakila.quary.StatsQuary;
import sakila.vo.Stats;

public class StatsDao implements IStatsDao{
	private StatsQuary statsQuary;
	
	@Override	// 오늘 날짜가 있는지 없는지 확인
	public boolean selectDay(Connection conn, Stats stats) throws Exception{
		boolean result = false; 
		
		statsQuary = new StatsQuary();		
		PreparedStatement stmt = conn.prepareStatement(statsQuary.SELECT_DATE);
		
		stmt.setString(1, stats.getDay());
		ResultSet rs = stmt.executeQuery();
		System.out.println(rs + ": select_date 정상");
		
		if(rs.next()) {
			result = true;
		}
		
		System.out.println(result + " : 세션 확인 값");
		
		stmt.close();
		
		return result;
	}
	
	@Override	// 오늘 날짜가 없으면 추가
	public void insertState(Connection conn, Stats stats) throws Exception{
		statsQuary = new StatsQuary();	
		
		PreparedStatement stmt = conn.prepareStatement(statsQuary.INSERT_STATS);
		stmt.setString(1, stats.getDay());
		
		stmt.executeLargeUpdate();
		
		stmt.close();
	}
	
	@Override	// 오늘 날짜가 있으면 업데이트
	public void updateStats(Connection conn, Stats stats) throws Exception{
		statsQuary = new StatsQuary();	
		
		PreparedStatement stmt = conn.prepareStatement(statsQuary.UPDATE_STATS);
		stmt.setString(1, stats.getDay());
		
		stmt.executeLargeUpdate();
		
		stmt.close();
	}
	
	@Override	// 내가 있는 날짜 기준으로 방문자가 나오는 메소드
	public int selectCnt(Connection conn, Stats stats) throws Exception{
		int getCnt = 1;
		
		statsQuary = new StatsQuary();	
		
		PreparedStatement stmt = conn.prepareStatement(statsQuary.SELECT_CNT);
		stmt.setString(1, stats.getDay());
		
		ResultSet rs = stmt.executeQuery();
		System.out.println(rs + ": select_cnt 정상");
		
		if(rs.next()) {
			getCnt = rs.getInt("cnt");
		}
		
		return getCnt;
	}
	
	@Override	// 전체 방문자 페이지를 고르는 메소드
	public int selectSumCnt(Connection conn) throws Exception{
		int getCount = 1;
		
		statsQuary = new StatsQuary();	
		
		PreparedStatement stmt = conn.prepareStatement(statsQuary.SELECT_SUM_CNT);
		
		ResultSet rs = stmt.executeQuery();
		System.out.println(rs + ": select_sum_cnt 정상");
		
		if(rs.next()) {
			getCount = rs.getInt("SUM(cnt)");
		}
		
		return getCount;
	}
}