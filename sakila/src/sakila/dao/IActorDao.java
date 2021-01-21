package sakila.dao;

import java.sql.Connection;
import java.util.List;

import sakila.vo.Actor;
import sakila.vo.Category;
import sakila.vo.JoinToTable;

public interface IActorDao {
	List<Actor> selectActorList(Connection conn, int currentPage, int limitPage, String searchTitle) throws Exception;
	int selectActorListCount(Connection conn, String searchTitle) throws Exception;
	void insertActor(Connection conn, Actor actor) throws Exception;
	List<JoinToTable> selectActorOne(Connection conn, int actorId) throws Exception;
	void updateActor(Connection conn, Actor actor) throws Exception;
	int selectStarringActorListCount(Connection conn, String searchTitle, String selectOption) throws Exception;
	List<JoinToTable> selectStarringActorList(Connection conn, String searchTitle, String selectOption, int currentPage, int limitPage) throws Exception;
	List<Category> selectCategory(Connection conn) throws Exception;
	List<JoinToTable> selectStarringActorOne(Connection conn, int filmId) throws Exception;
	List<Actor> selectStarringActorNotSelect(Connection conn) throws Exception;
	void insertActorByFilm(Connection conn, int actorId, int filmId) throws Exception;
	List<Integer> selectStarringActorByFilm(Connection conn, int filmId) throws Exception;
}
