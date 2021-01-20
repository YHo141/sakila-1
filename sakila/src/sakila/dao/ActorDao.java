package sakila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.quary.ActorQuery;
import sakila.vo.Actor;
import sakila.vo.Film;
import sakila.vo.JoinToTable;

public class ActorDao implements IActorDao{
	private ActorQuery actorQuery;
	
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
