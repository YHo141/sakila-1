package sakila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.quary.ActorQuery;
import sakila.vo.Actor;
import sakila.vo.Category;
import sakila.vo.Film;
import sakila.vo.JoinToTable;

public class ActorDao implements IActorDao{
	private ActorQuery actorQuery;
	
	@Override
	public void insertActorByFilm(Connection conn, int actorId, int filmId) throws Exception{
		actorQuery = new ActorQuery();
		
		PreparedStatement stmt = conn.prepareStatement(actorQuery.INSERT_ACTOR_BY_FILM);
		stmt.setInt(1, actorId);
		stmt.setInt(2, filmId);
		
		stmt.executeLargeUpdate();
		
		stmt.close();
	}
	@Override
	public List<Integer> selectStarringActorByFilm(Connection conn, int filmId) throws Exception{
		actorQuery = new ActorQuery();
		List<Integer> list = new ArrayList<Integer>();
		
		PreparedStatement stmt = conn.prepareStatement(actorQuery.SELECT_STARRING_ACTOR_BY_FILM);
		stmt.setInt(1, filmId);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			int actorId = rs.getInt("actorId");
			
			list.add(actorId);
		}
		
		stmt.close();
		rs.close();
		
		return list;
	}
	
	@Override
	public List<Actor> selectStarringActorNotSelect(Connection conn) throws Exception{
		actorQuery = new ActorQuery();
		List<Actor> list = new ArrayList<Actor>();
		
		PreparedStatement stmt = conn.prepareStatement(actorQuery.SELECT_STARRING_ACTOR_NOT_SELECT);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Actor actor = new Actor();
			actor.setLastName(rs.getString("actorName"));
			actor.setActorId(rs.getInt("actorId"));
			
			list.add(actor);
		}
		
		stmt.close();
		rs.close();
		
		return list;
	}
	
	@Override
	public List<JoinToTable> selectStarringActorOne(Connection conn, int filmId) throws Exception{
		actorQuery = new ActorQuery();
		List<JoinToTable> list = new ArrayList<JoinToTable>();
		
		PreparedStatement stmt = conn.prepareStatement(actorQuery.SELECT_STARRING_ACTOR_ONE);
		stmt.setInt(1, filmId);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			JoinToTable join = new JoinToTable();
			join.setActor(new Actor());
			join.setCategory(new Category());
			join.setFilm(new Film());
			
			join.getCategory().setName(rs.getString("categoryName"));
			join.getFilm().setTitle(rs.getString("title"));
			join.getActor().setLastName(rs.getString("actorName"));
			
			list.add(join);
		}
		
		stmt.close();
		rs.close();
		
		return list;
	}
	
	@Override
	public List<Category> selectCategory(Connection conn) throws Exception{
		actorQuery = new ActorQuery();
		List<Category> list = new ArrayList<Category>();
		
		
		PreparedStatement stmt = conn.prepareStatement(actorQuery.SELECT_CATEGORY);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			 Category category = new Category();
			 category.setName(rs.getString("name"));
			 
			 list.add(category);
		}
		stmt.close();
		rs.close();
		
		return list;
	}
	
	@Override
	public int selectStarringActorListCount(Connection conn, String searchTitle, String selectOption) throws Exception{
		int lastPage = 0;
		actorQuery = new ActorQuery();
		
		PreparedStatement stmt = conn.prepareStatement(actorQuery.SELECT_STARRING_ACTOR_LIST_COUNT);
		stmt.setString(1, "%" + searchTitle + "%");
		stmt.setString(2, "%" + selectOption + "%");
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			lastPage = rs.getInt("COUNT(*)");
		}
		
		stmt.close();
		rs.close();
		
		return lastPage;
	}
	
	@Override
	public List<JoinToTable> selectStarringActorList(Connection conn, String searchTitle, String selectOption, int currentPage, int limitPage) throws Exception{
		List<JoinToTable> list = new ArrayList<JoinToTable>();
		actorQuery = new ActorQuery();
		
		PreparedStatement stmt = conn.prepareStatement(actorQuery.SELECT_STARRING_ACTOR_LIST);
		stmt.setString(1, "%" + searchTitle + "%");
		stmt.setString(2, "%" + selectOption + "%");
		stmt.setInt(3, (currentPage-1)*limitPage);
		stmt.setInt(4, limitPage);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			JoinToTable join = new JoinToTable();
			join.setFilm(new Film());
			join.setCategory(new Category());
			
			join.getFilm().setTitle(rs.getString("title"));
			join.getFilm().setFilmId(rs.getInt("filmId"));
			join.getCategory().setName(rs.getString("name"));
			
			list.add(join);
		}
		
		stmt.close();
		rs.close();
		
		return list;
	}
	
	@Override
	public void updateActor(Connection conn, Actor actor) throws Exception{
		actorQuery = new ActorQuery();
		
		PreparedStatement stmt = conn.prepareStatement(actorQuery.UPDATE_ACTOR);
		stmt.setString(1, actor.getFirstName());
		stmt.setString(2, actor.getLastName());
		stmt.setInt(3, actor.getActorId());
		
		stmt.executeLargeUpdate();
		
		stmt.close();
	}
	
	@Override
	public List<JoinToTable> selectActorOne(Connection conn, int actorId) throws Exception{
		List<JoinToTable> list = new ArrayList<JoinToTable>();
		actorQuery = new ActorQuery();
		
		PreparedStatement stmt = conn.prepareStatement(actorQuery.SELECT_ACTOR_ONE);
		stmt.setInt(1, actorId);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			JoinToTable join = new JoinToTable();
			join.setActor(new Actor());
			join.setFilm(new Film());
			
			join.getFilm().setTitle(rs.getString("title"));
			join.getActor().setActorId(rs.getInt("actorId"));
			join.getActor().setFirstName(rs.getString("firstName"));
			join.getActor().setLastName(rs.getString("lastName"));
			
			list.add(join);
		}
		
		stmt.close();
		rs.close();
		
		return list;
	}
	
	@Override
	public void insertActor(Connection conn, Actor actor) throws Exception{
		actorQuery = new ActorQuery();
		
		PreparedStatement stmt = conn.prepareStatement(actorQuery.INSERT_ACTOR);
		stmt.setString(1, actor.getFirstName());
		stmt.setString(2, actor.getLastName());
		
		stmt.executeLargeUpdate();
		
		stmt.close();
	}
	
	@Override	// 배우 목록 리스트 출력
	public List<Actor> selectActorList(Connection conn, int currentPage, int limitPage, String searchTitle) throws Exception{
		List<Actor> list = new ArrayList<Actor>();
		actorQuery = new ActorQuery();
		
		PreparedStatement stmt = conn.prepareStatement(actorQuery.SELECT_ACTOR_LIST);
		stmt.setString(1, "%" + searchTitle + "%");
		stmt.setInt(2, (currentPage-1)*limitPage);
		stmt.setInt(3, limitPage);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Actor actor = new Actor();
			actor.setActorId(rs.getInt("actor_id"));
			actor.setFirstName(rs.getString("first_name"));
			actor.setLastName(rs.getString("last_name"));
			
			list.add(actor);
		}
		
		stmt.close();
		rs.close();
		
		return list;
	}
	
	@Override
	public int selectActorListCount(Connection conn, String searchTitle) throws Exception{
		int lastPage = 0;
		actorQuery = new ActorQuery();
		
		PreparedStatement stmt = conn.prepareStatement(actorQuery.SELECT_ACTOR_LIST_COUNT);
		stmt.setString(1, "%" + searchTitle + "%");
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			lastPage = rs.getInt("COUNT(*)");
		}
		
		stmt.close();
		rs.close();
		
		return lastPage;
	}
}
