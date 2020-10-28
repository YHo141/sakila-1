package sakila.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import sakila.commons.DBUtil;
import sakila.dao.IFilmDao;
import sakila.vo.JoinToTable;

public class FilmService {
	private IFilmDao iFilmDao;
	
	public FilmService(IFilmDao iFilmDao) {
		this.iFilmDao = iFilmDao;
	}
	
	private DBUtil dbUtil;
	
	public List<JoinToTable> getFilmList() {
		List<JoinToTable> list = new ArrayList<JoinToTable>();
		dbUtil = new DBUtil();
		
		Connection conn = null;
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			
			list = iFilmDao.selectFilmList(conn);
			System.out.println(list + ": FilmService stmt 출력");
			
			conn.commit();
		} catch (Exception e) {
			System.out.println("FilmService 예외 발생");
			e.printStackTrace();
			try {conn.rollback();} catch (Exception e2) {e2.printStackTrace();}
		} finally {
			try {conn.close();} catch (Exception e2) {e2.printStackTrace();}
		}
		
		
		return list;
	}
}
