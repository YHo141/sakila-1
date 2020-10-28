package sakila.dao;

import java.sql.Connection;
import java.util.List;

import sakila.vo.JoinToTable;

public interface IRentalDao {
	List<JoinToTable> selectFilmReturnList(Connection conn) throws Exception;
}
