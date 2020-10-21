package sakila.dao;

import java.sql.Connection;

import sakila.vo.Stats;

public interface IStatsDao {
	public boolean selectDay(Connection conn, Stats stats) throws Exception;
	void insertState(Connection conn, Stats stats) throws Exception;
	void updateStats(Connection conn, Stats stats) throws Exception;
	int selectCnt(Connection conn, Stats stats) throws Exception;
	int selectSumCnt(Connection conn) throws Exception;
}
