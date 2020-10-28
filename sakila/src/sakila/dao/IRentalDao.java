package sakila.dao;

import java.sql.Connection;
import java.util.List;

import sakila.vo.RentalAndFilm;

public interface IRentalDao {
	List<RentalAndFilm> selectFilmReturnList(Connection conn) throws Exception;
}
