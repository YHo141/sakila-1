package sakila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.quary.StatisticsQuery;
import sakila.vo.SalesByFilmCategory;

public class StatisticsDao implements IStatisticsDao{
	private StatisticsQuery statisticsQuery;
	
	@Override
	public int selectStoreCategorySumCount(Connection conn) throws Exception{
		int lastPage = 0;
		statisticsQuery = new StatisticsQuery();
		
		PreparedStatement stmt = conn.prepareStatement(statisticsQuery.SELECT_STORE_CATEGORY_SUM_COUNT);
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			lastPage = rs.getInt("COUNT(*)");
		}
		
		stmt.close();
		rs.close();
		
		return lastPage;
	}
	
	@Override
	public List<SalesByFilmCategory> selectStoreCategorySum(Connection conn, int currentPage, int limitPage) throws Exception{
		List<SalesByFilmCategory> list = new ArrayList<SalesByFilmCategory>();
		statisticsQuery = new StatisticsQuery();
		
		PreparedStatement stmt = conn.prepareStatement(statisticsQuery.SELECT_STORE_CATEGORY_SUM);
		stmt.setInt(1, (currentPage-1)*limitPage);
		stmt.setInt(2, limitPage);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			SalesByFilmCategory sfc = new SalesByFilmCategory();
			
			sfc.setCategory(rs.getString("category"));
			sfc.setTotalSales(rs.getFloat("totalSales"));
			
			list.add(sfc);
		}
		
		stmt.close();
		rs.close();
		
		return list;
 	}
	
	@Override
	public int selectStoreRentalSum(Connection conn, int staffId) throws Exception{
		statisticsQuery = new StatisticsQuery();
		int storeRentalSum = 0;
		
		PreparedStatement stmt = conn.prepareStatement(statisticsQuery.SELECT_STORE_RENTAL_SUM);
		stmt.setInt(1, staffId);
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			storeRentalSum = rs.getInt("COUNT(*)");
		}
		
		stmt.close();
		rs.close();
		
		return storeRentalSum;
	}
	
	@Override
	public String selectStoreByStaff(Connection conn, int staffId) throws Exception{
		statisticsQuery = new StatisticsQuery();
		
		String staffName = "";
		
		PreparedStatement stmt = conn.prepareStatement(statisticsQuery.SELECT_STORE_BY_STAFF);
		stmt.setInt(1, staffId);
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			staffName = rs.getString("staffName");
		}
		
		stmt.close();
		rs.close();
		
		return staffName;
	}
	
	@Override
	public float selectStoreSum(Connection conn, String staffName) throws Exception{
		statisticsQuery = new StatisticsQuery();
		float storeSum = 0;
		
		PreparedStatement stmt = conn.prepareStatement(statisticsQuery.SELECT_STORE_SUM);
		stmt.setString(1, staffName);
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			storeSum = rs.getFloat("total_sales");
		}
		
		stmt.close();
		rs.close();
		
		return storeSum;
	}
	
}
