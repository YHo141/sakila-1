package sakila.dao;

import java.sql.Connection;
import java.util.List;

import sakila.vo.JoinToTable;

public interface IRentalDao {
	List<JoinToTable> selectFilmReturnList(Connection conn, int currentPage, int limitPage) throws Exception;
	
	int selectFilmReturnListCount(Connection conn) throws Exception;
}
