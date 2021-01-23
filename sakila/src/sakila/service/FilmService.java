package sakila.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import sakila.commons.DBUtil;
import sakila.dao.IFilmDao;
import sakila.vo.Actor;
import sakila.vo.Category;
import sakila.vo.Film;
import sakila.vo.FilmList;
import sakila.vo.JoinToTable;
import sakila.vo.Language;

public class FilmService {
	private IFilmDao iFilmDao;
	
	public FilmService(IFilmDao iFilmDao) {
		this.iFilmDao = iFilmDao;
	}
	
	private DBUtil dbUtil;
	
	public void addFilmAction(String title, String description, int languageId, int categoryId, float rentalRate, int length, String rating) {
		Film film = new Film();
		dbUtil = new DBUtil();
		
		Connection conn = null;
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			
			film.setTitle(title);
			film.setDescription(description);
			film.setLanguageId(languageId);
			film.setRentalRate(rentalRate);
			film.setLength(length);
			film.setRating(rating);
			
			iFilmDao.insertFilm(conn, film);
			int filmId = iFilmDao.selectFilmCategoryByInsert(conn, film);
			iFilmDao.insertFilmCategory(conn, filmId, categoryId);
			
			conn.commit();
		} catch (Exception e) {
			System.out.println("FilmService 예외 발생");
			e.printStackTrace();
			try {conn.rollback();} catch (Exception e2) {e2.printStackTrace();}
		} finally {
			try {conn.close();} catch (Exception e2) {e2.printStackTrace();}
		}
	}
	
	public Map<String, Object> getFilmAdd(){
		Map<String, Object> map = new HashMap<String, Object>();
		dbUtil = new DBUtil();
		
		Connection conn = null;
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			
			List<Language> languageList = iFilmDao.selectFilmAddLanguage(conn);
			List<Category> categoryList = iFilmDao.selectFilmByCategory(conn);
			
			map.put("languageList", languageList);
			map.put("categoryList", categoryList);
			
			conn.commit();
		} catch (Exception e) {
			System.out.println("FilmService 예외 발생");
			e.printStackTrace();
			try {conn.rollback();} catch (Exception e2) {e2.printStackTrace();}
		} finally {
			try {conn.close();} catch (Exception e2) {e2.printStackTrace();}
		}
		
		return map;
	}
	
	public void modifyFilm(String description, float rentalRate, int filmId) {
		Film film = new Film();
		dbUtil = new DBUtil();
		
		Connection conn = null;
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			
			film.setDescription(description);
			film.setRentalRate(rentalRate);
			film.setFilmId(filmId);
			
			iFilmDao.updateFilm(conn, film);
			
			conn.commit();
		} catch (Exception e) {
			System.out.println("FilmService 예외 발생");
			e.printStackTrace();
			try {conn.rollback();} catch (Exception e2) {e2.printStackTrace();}
		} finally {
			try {conn.close();} catch (Exception e2) {e2.printStackTrace();}
		}
	}
	
	public Map<String, Object> getFilmListOne(int filmId) {
		Map<String, Object> map = new HashMap<String, Object>();
		dbUtil = new DBUtil();
		
		Connection conn = null;
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			
			JoinToTable filmList = iFilmDao.selectFilmListOne(conn, filmId);
			List<Actor> actorList = iFilmDao.selectActorListByFilmOne(conn, filmId);
			
			map.put("filmList", filmList);
			map.put("actorList", actorList);
			
			conn.commit();
		} catch (Exception e) {
			System.out.println("FilmService 예외 발생");
			e.printStackTrace();
			try {conn.rollback();} catch (Exception e2) {e2.printStackTrace();}
		} finally {
			try {conn.close();} catch (Exception e2) {e2.printStackTrace();}
		}
		
		return map;
	}
	
	// 영화 목록을 가져옴
	public Map<String, Object> getFilmList(String searchTitle, String categoryName, int currentPage, int limitPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		dbUtil = new DBUtil();
		
		Connection conn = null;
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			
			List<JoinToTable> list = iFilmDao.selectFilmList(conn, searchTitle, categoryName, currentPage, limitPage);
			List<Category> categoryList = iFilmDao.selectFilmByCategory(conn);
			int lastPage = iFilmDao.selectFilmListCount(conn, searchTitle, categoryName);
			
			if(lastPage % limitPage == 0) {
				lastPage = lastPage / limitPage;
			} else {
				lastPage = lastPage / limitPage + 1;
			}
			
			map.put("list", list);
			map.put("categoryList", categoryList);
			map.put("lastPage", lastPage);
			
			conn.commit();
		} catch (Exception e) {
			System.out.println("FilmService 예외 발생");
			e.printStackTrace();
			try {conn.rollback();} catch (Exception e2) {e2.printStackTrace();}
		} finally {
			try {conn.close();} catch (Exception e2) {e2.printStackTrace();}
		}
		
		
		return map;
	}
	
	// 영화 재고 목록을 가져옴
	public Map<String, Object> getFilmPromotionNullList(int currentPage, int limitPage, String searchTitle) {
		Map<String, Object> map = new HashMap<String, Object>();
		dbUtil = new DBUtil();
		
		Connection conn = null;
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			
			List<JoinToTable> list = iFilmDao.selectFilmPromotionNullList(conn, currentPage, limitPage, searchTitle);
			int lastPage = iFilmDao.selectFilmPromotionNullCount(conn, searchTitle);
			
			if(lastPage % limitPage == 0) {
				lastPage = lastPage / limitPage;
			} else {
				lastPage = lastPage / limitPage + 1;
			}
			
			map.put("list", list);
			map.put("lastPage", lastPage);
			
			conn.commit();
		} catch (Exception e) {
			System.out.println("FilmService 예외 발생");
			e.printStackTrace();
			try {conn.rollback();} catch (Exception e2) {e2.printStackTrace();}
		} finally {
			try {conn.close();} catch (Exception e2) {e2.printStackTrace();}
		}
		
		
		return map;
	}
	
	// 영화 재고 목록을 가져옴
	public Map<String, Object> getFilmPromotionNotnullList(int currentPage, int limitPage, String searchTitle) {
		Map<String, Object> map = new HashMap<String, Object>();
		dbUtil = new DBUtil();
		
		Connection conn = null;
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
			
			List<JoinToTable> list = iFilmDao.selectFilmPromotionNotnullList(conn, currentPage, limitPage, searchTitle);
			int lastPage = iFilmDao.selectFilmPromotionNotnullCount(conn, searchTitle);
			
			if(lastPage % limitPage == 0) {
				lastPage = lastPage / limitPage;
			} else {
				lastPage = lastPage / limitPage + 1;
			}
			
			map.put("list", list);
			map.put("lastPage", lastPage);
			
			conn.commit();
		} catch (Exception e) {
			System.out.println("FilmService 예외 발생");
			e.printStackTrace();
			try {conn.rollback();} catch (Exception e2) {e2.printStackTrace();}
		} finally {
			try {conn.close();} catch (Exception e2) {e2.printStackTrace();}
		}
		
		
		return map;
	}
	
	// 영화 재고 목록을 가져옴
	public Map<String, Object> getFilmPromotionList(int currentPage, int limitPage, String searchTitle) {
		Map<String, Object> map = new HashMap<String, Object>();
		dbUtil = new DBUtil();
			
		Connection conn = null;
			
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false);
				
			List<JoinToTable> list = iFilmDao.selectFilmPromotionList(conn, currentPage, limitPage, searchTitle);
			int lastPage = iFilmDao.selectFilmPromotionCount(conn, searchTitle);
			
			if(lastPage % limitPage == 0) {
				lastPage = lastPage / limitPage;
			} else {
				lastPage = lastPage / limitPage + 1;
			}
			
			map.put("list", list);
			map.put("lastPage", lastPage);
				
			conn.commit();
		} catch (Exception e) {
			System.out.println("FilmService 예외 발생");
			e.printStackTrace();
			try {conn.rollback();} catch (Exception e2) {e2.printStackTrace();}
		} finally {
			try {conn.close();} catch (Exception e2) {e2.printStackTrace();}
		}
			
		
		return map;
	}
}
