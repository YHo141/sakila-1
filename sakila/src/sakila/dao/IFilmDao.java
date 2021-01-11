package sakila.dao;

import java.sql.Connection;
import java.util.List;

import sakila.vo.JoinToTable;

public interface IFilmDao {
	List<JoinToTable> selectFilmList(Connection conn) throws Exception;
	
	List<JoinToTable> selectFilmPromotionList(Connection conn, int currentPage, int limitPage, String searchTitle) throws Exception;
	
	int selectFilmPromotionCount(Connection conn, String searchTitle) throws Exception;
	
	List<JoinToTable> selectFilmPromotionNotnullList(Connection conn, int currentPage, int limitPage, String searchTitle) throws Exception;
	
	int selectFilmPromotionNotnullCount(Connection conn, String searchTitle) throws Exception;
	
	List<JoinToTable> selectFilmPromotionNullList(Connection conn, int currentPage, int limitPage, String searchTitle) throws Exception;
	
	int selectFilmPromotionNullCount(Connection conn, String searchTitle) throws Exception;
}
