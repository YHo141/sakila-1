package sakila.dao;

import java.sql.Connection;
import java.util.List;

import sakila.vo.JoinToTable;

public interface IFilmDao {
	List<JoinToTable> selectFilmList(Connection conn) throws Exception;
	
	List<JoinToTable> selectFilmPromotionList(Connection conn) throws Exception;
}
