package sakila.dao;

import java.sql.Connection;
import java.util.List;

import sakila.vo.SalesByFilmCategory;

public interface IStatisticsDao {
	String selectStoreByStaff(Connection conn, int staffId) throws Exception;
	float selectStoreSum(Connection conn, String staffName) throws Exception;
	int selectStoreRentalSum(Connection conn, int staffId) throws Exception;
	List<SalesByFilmCategory> selectStoreCategorySum(Connection conn, int currentPage, int limitPage) throws Exception;
	int selectStoreCategorySumCount(Connection conn) throws Exception;
}
