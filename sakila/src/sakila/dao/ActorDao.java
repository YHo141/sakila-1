package sakila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.quary.ActorQuery;
import sakila.vo.Actor;

public class ActorDao implements IActorDao{
	private ActorQuery actorQuery; 
	
	@Override	// ��� ��� ����Ʈ ���
	public List<Actor> selectActorList(Connection conn) throws Exception{
		List<Actor> list = new ArrayList<Actor>();
		actorQuery = new ActorQuery();
		
		PreparedStatement stmt = conn.prepareStatement(actorQuery.SELECT_ACTOR_LIST);
		System.out.println(stmt + ": selectActorList���� stmt Ȯ��");
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Actor actor = new Actor();
			
			actor.setFirstName(rs.getString("first_name"));
			actor.setLastName(rs.getString("last_name"));
			
			list.add(actor);
		}
		
		stmt.close();
		rs.close();
		
		return list;
	}
}
