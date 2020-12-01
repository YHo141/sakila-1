package sakila.dao;

import java.sql.Connection;
import java.util.List;

import sakila.vo.JoinToTable;

public interface IRentalDao {
	List<JoinToTable> selectFilmReturnList(Connection conn, int currentPage, int limitPage) throws Exception;
	
	int selectFilmReturnListCount(Connection conn) throws Exception;
	
	List<JoinToTable> selectFilmReturnListByTitle(Connection conn, int currentPage, int limitPage, String searchTitle) throws Exception;
	
	int selectFilmReturnListByTitleCount(Connection conn, String searchTitle) throws Exception;
	
	void updateRentalReturnDate(Connection conn, int rentalId) throws Exception;
}
