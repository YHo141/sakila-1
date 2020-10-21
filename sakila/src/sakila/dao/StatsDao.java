package sakila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import sakila.quary.StatsQuary;
import sakila.vo.Stats;

public class StatsDao implements IStatsDao{
	private StatsQuary statsQuary;
	
	@Override	// ���� ��¥�� �ִ��� ������ Ȯ��
	public boolean selectDay(Connection conn, Stats stats) throws Exception{
		boolean result = false; 
		
		statsQuary = new StatsQuary();		
		PreparedStatement stmt = conn.prepareStatement(statsQuary.SELECT_DATE);
		
		stmt.setString(1, stats.getDay());
		ResultSet rs = stmt.executeQuery();
		System.out.println(rs + ": select_date ����");
		
		if(rs.next()) {
			result = true;
		}
		
		System.out.println(result + " : ���� Ȯ�� ��");
		
		stmt.close();
		
		return result;
	}
	
	@Override	// ���� ��¥�� ������ �߰�
	public void insertState(Connection conn, Stats stats) throws Exception{
		statsQuary = new StatsQuary();	
		
		PreparedStatement stmt = conn.prepareStatement(statsQuary.INSERT_STATS);
		stmt.setString(1, stats.getDay());
		
		stmt.executeLargeUpdate();
		
		stmt.close();
	}
	
	@Override	// ���� ��¥�� ������ ������Ʈ
	public void updateStats(Connection conn, Stats stats) throws Exception{
		statsQuary = new StatsQuary();	
		
		PreparedStatement stmt = conn.prepareStatement(statsQuary.UPDATE_STATS);
		stmt.setString(1, stats.getDay());
		
		stmt.executeLargeUpdate();
		
		stmt.close();
	}
	
	@Override	// ���� �ִ� ��¥ �������� �湮�ڰ� ������ �޼ҵ�
	public int selectCnt(Connection conn, Stats stats) throws Exception{
		int getCnt = 1;
		
		statsQuary = new StatsQuary();	
		
		PreparedStatement stmt = conn.prepareStatement(statsQuary.SELECT_CNT);
		stmt.setString(1, stats.getDay());
		
		ResultSet rs = stmt.executeQuery();
		System.out.println(rs + ": select_cnt ����");
		
		if(rs.next()) {
			getCnt = rs.getInt("cnt");
		}
		
		return getCnt;
	}
	
	@Override	// ��ü �湮�� �������� ���� �޼ҵ�
	public int selectSumCnt(Connection conn) throws Exception{
		int getCount = 1;
		
		statsQuary = new StatsQuary();	
		
		PreparedStatement stmt = conn.prepareStatement(statsQuary.SELECT_SUM_CNT);
		
		ResultSet rs = stmt.executeQuery();
		System.out.println(rs + ": select_sum_cnt ����");
		
		if(rs.next()) {
			getCount = rs.getInt("SUM(cnt)");
		}
		
		return getCount;
	}
}