package sakila.dao;

import java.sql.Connection;
import java.util.List;

import sakila.vo.Actor;

public interface IActorDao {
	List<Actor> selectActorList(Connection conn) throws Exception;
}
