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
import sakila.vo.Category;
import sakila.vo.JoinToTable;

public class ActorService {
	private IActorDao iActorDao;
	
	public ActorService(IActorDao iActorDao) {
		this.iActorDao = iActorDao;
	}
	
	private DBUtil dbUtil;
	
	public void addActorByFilm(String actorId, int filmId) {
		dbUtil = new DBUtil();
		
		Connection conn = null;
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			
			String actorIdSplit[] = actorId.split(",");
			for(int i=0; i<actorIdSplit.length; i++) {
				int actorIdInt = Integer.parseInt(actorIdSplit[i]);
				iActorDao.insertActorByFilm(conn, actorIdInt, filmId);
			}
			
			conn.commit();
		} catch (Exception e) {
			System.out.println("ActorService 예외발생");
			e.printStackTrace();
			try {conn.rollback();} catch (SQLException e2) {e2.printStackTrace();}
		} finally {
			try {conn.close();} catch (SQLException e2) {e2.printStackTrace();}
		}
	}
	
	public List<Actor> getStarringActorNotSelect(int filmId){
		dbUtil = new DBUtil();
		List<Actor> list = new ArrayList<Actor>();
		
		Connection conn = null;
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			
			List<Integer> actorList = iActorDao.selectStarringActorByFilm(conn, filmId);
			
			list = iActorDao.selectStarringActorNotSelect(conn);
			for(int i=0; i<list.size(); i++) {
				for(int j=0; j<actorList.size(); j++) {					
					if(list.get(i).getActorId() == actorList.get(j)) {
						list.remove(i);
					}
				}
			}
			
			conn.commit();
		} catch (Exception e) {
			System.out.println("ActorService 예외발생");
			e.printStackTrace();
			try {conn.rollback();} catch (SQLException e2) {e2.printStackTrace();}
		} finally {
			try {conn.close();} catch (SQLException e2) {e2.printStackTrace();}
		}
		
		return list;
	}
	
	public List<JoinToTable> getStarringActorOne(int filmId){
		dbUtil = new DBUtil();
		List<JoinToTable> list = new ArrayList<JoinToTable>();
		
		Connection conn = null;
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			
			list = iActorDao.selectStarringActorOne(conn, filmId);
			
			conn.commit();
		} catch (Exception e) {
			System.out.println("ActorService 예외발생");
			e.printStackTrace();
			try {conn.rollback();} catch (SQLException e2) {e2.printStackTrace();}
		} finally {
			try {conn.close();} catch (SQLException e2) {e2.printStackTrace();}
		}
		
		return list;
	}
	
	public List<Category> getCategory(){
		dbUtil = new DBUtil();
		List<Category> categoryList = new ArrayList<Category>();
		
		Connection conn = null;
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			
			categoryList = iActorDao.selectCategory(conn);
			
			conn.commit();
		} catch (Exception e) {
			System.out.println("ActorService 예외발생");
			e.printStackTrace();
			try {conn.rollback();} catch (SQLException e2) {e2.printStackTrace();}
		} finally {
			try {conn.close();} catch (SQLException e2) {e2.printStackTrace();}
		}
		
		return categoryList;
	}
	
	public Map<String, Object> getStarringActorList(int currentPage, int limitPage, String searchTitle, String selectOption){
		Map<String, Object> map = new HashMap<String, Object>();
		dbUtil = new DBUtil();
		
		Connection conn = null;
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			
			List<JoinToTable> list = iActorDao.selectStarringActorList(conn, searchTitle, selectOption, currentPage, limitPage);
			int lastPage = iActorDao.selectStarringActorListCount(conn, searchTitle, selectOption);
			
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
