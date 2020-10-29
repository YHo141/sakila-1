package sakila.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sakila.commons.DBUtil;
import sakila.dao.IActorDao;
import sakila.vo.Actor;

public class ActorService {
	private IActorDao iActorDao;
	
	public ActorService(IActorDao iActorDao) {
		this.iActorDao = iActorDao;
	}
	
	private DBUtil dbUtil;
	
	public List<Actor> getActorList(){
		List<Actor> list = new ArrayList<Actor>();
		dbUtil = new DBUtil();
		
		Connection conn = null;
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			
			list = iActorDao.selectActorList(conn);
			System.out.println(list + ": getActorList에서 list 확인");
			
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
}
