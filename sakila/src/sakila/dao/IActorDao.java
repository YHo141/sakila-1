package sakila.dao;

import java.sql.Connection;
import java.util.List;

import sakila.vo.Actor;
import sakila.vo.JoinToTable;

public interface IActorDao {
	List<Actor> selectActorList(Connection conn, int currentPage, int limitPage, String searchTitle) throws Exception;
	int selectActorListCount(Connection conn, String searchTitle) throws Exception;
	void insertActor(Connection conn, Actor actor) throws Exception;
	List<JoinToTable> selectActorOne(Connection conn, int actorId) throws Exception;
	void updateActor(Connection conn, Actor actor) throws Exception;
}
