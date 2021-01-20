package sakila.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sakila.commons.DBUtil;
import sakila.dao.IActorDao;
import sakila.vo.Actor;
import sakila.vo.JoinToTable;

public class ActorService {
	private IActorDao iActorDao;
	
	public ActorService(IActorDao iActorDao) {
		this.iActorDao = iActorDao;
	}
	
	private DBUtil dbUtil;
	
	public void modifyActor(String firstName, String lastName, int actorId) {
		dbUtil = new DBUtil();
		
		Connection conn = null;
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			
			Actor actor = new Actor();
			actor.setActorId(actorId);
			actor.setFirstName(firstName);
			actor.setLastName(lastName);
			
			iActorDao.updateActor(conn, actor);
			
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {conn.rollback();} catch (Exception e2) {e2.printStackTrace();}
		} finally {
			try {conn.close();} catch (Exception e2) {e2.printStackTrace();}
		}
	}
	
	public List<JoinToTable> getActorOne(int actorId){
		List<JoinToTable> list = new ArrayList<JoinToTable>();
		dbUtil = new DBUtil();
		
		Connection conn = null;
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			
			list = iActorDao.selectActorOne(conn, actorId);
			
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {conn.rollback();} catch (Exception e2) {e2.printStackTrace();}
		} finally {
			try {conn.close();} catch (Exception e2) {e2.printStackTrace();}
		}
		
		return list;
	}
	
	public void addActor(String firstName, String lastName) {
		dbUtil = new DBUtil();
		
		Connection conn = null;
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			
			Actor actor = new Actor();
			actor.setFirstName(firstName);
			actor.setLastName(lastName);
			
			iActorDao.insertActor(conn, actor);
			
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {conn.rollback();} catch (Exception e2) {e2.printStackTrace();}
		} finally {
			try {conn.close();} catch (Exception e2) {e2.printStackTrace();}
		}
	}
	
	public Map<String, Object> getActorList(int currentPage, int limitPage, String searchTitle){
		Map<String, Object> map = new HashMap<String, Object>();
		dbUtil = new DBUtil();
		
		Connection conn = null;
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			
			List<Actor> list = iActorDao.selectActorList(conn, currentPage, limitPage, searchTitle);
			int lastPage = iActorDao.selectActorListCount(conn, searchTitle);
			
			if(lastPage % limitPage == 0) {
				lastPage = lastPage / limitPage;
			} else {
				lastPage = lastPage / limitPage +1;
			}
			
			map.put("list", list);
			map.put("lastPage", lastPage);
			
			conn.commit();
		} catch (Exception e) {
			System.out.println("ActorService 예외발생");
			e.printStackTrace();
			try {conn.rollback();} catch (SQLException e2) {e2.printStackTrace();}
		} finally {
			try {conn.close();} catch (SQLException e2) {e2.printStackTrace();}
		}
		
		
		return map;
	}
}
