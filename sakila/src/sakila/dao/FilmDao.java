package sakila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.quary.FilmQuary;
import sakila.vo.Film;
import sakila.vo.FilmList;
import sakila.vo.JoinToTable;
import sakila.vo.Language;

public class FilmDao implements IFilmDao{
	private FilmQuary filmQuary;
	
	@Override	// 영화 제고 목록 페이징을 위한  리스트 출력
	public int selectFilmPromotionNullCount(Connection conn, String searchTitle) throws Exception{
		int lastPage = 0;
		filmQuary = new FilmQuary();
		
		PreparedStatement stmt = conn.prepareStatement(filmQuary.SELECT_FILM_PROMOTION_COUNT_NULL);
		stmt.setString(1, "%" + searchTitle + "%");
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			lastPage++;
		}
		
		return lastPage;
	}
	
	@Override	// 영화 제고 관리 리스트 출력 Dao
	public List<JoinToTable> selectFilmPromotionNullList(Connection conn, int currentPage, int limitPage, String searchTitle) throws Exception{
		List<JoinToTable> list = new ArrayList<JoinToTable>();
		filmQuary = new FilmQuary();
		
		PreparedStatement stmt = conn.prepareStatement(filmQuary.SELECT_FILM_PROMOTION_LIST_NULL);
		stmt.setString(1, "%" + searchTitle + "%");
		stmt.setInt(2, (currentPage-1)*limitPage);
		stmt.setInt(3, limitPage);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			JoinToTable join = new JoinToTable();
			join.setFilmList(new FilmList());
			join.setFilm(new Film());
			join.setLanguage(new Language());
			
			join.getFilmList().setCategory(rs.getString("fl.category"));
			join.getFilm().setTitle(rs.getString("f.title"));
			join.getLanguage().setName(rs.getString("l.name"));
			join.getFilm().setRating(rs.getString("f.rating"));
			join.getFilm().setFilmId(rs.getInt("COUNT(*)"));	// 재고수 
			
			list.add(join);
		}
		
		rs.close();
		stmt.close();
		
		return list;
	}
	
	@Override	// 영화 제고 목록 페이징을 위한  리스트 출력
	public int selectFilmPromotionNotnullCount(Connection conn, String searchTitle) throws Exception{
		int lastPage = 0;
		filmQuary = new FilmQuary();
		
		PreparedStatement stmt = conn.prepareStatement(filmQuary.SELECT_FILM_PROMOTION_COUNT_NOTNULL);
		stmt.setString(1, "%" + searchTitle + "%");
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			lastPage++;
		}
		
		return lastPage;
	}
	
	@Override	// 영화 제고 관리 리스트 출력 Dao
	public List<JoinToTable> selectFilmPromotionNotnullList(Connection conn, int currentPage, int limitPage, String searchTitle) throws Exception{
		List<JoinToTable> list = new ArrayList<JoinToTable>();
		filmQuary = new FilmQuary();
		
		PreparedStatement stmt = conn.prepareStatement(filmQuary.SELECT_FILM_PROMOTION_LIST_NOTNULL);
		stmt.setString(1, "%" + searchTitle + "%");
		stmt.setInt(2, (currentPage-1)*limitPage);
		stmt.setInt(3, limitPage);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			JoinToTable join = new JoinToTable();
			join.setFilmList(new FilmList());
			join.setFilm(new Film());
			join.setLanguage(new Language());
			
			join.getFilmList().setCategory(rs.getString("fl.category"));
			join.getFilm().setTitle(rs.getString("f.title"));
			join.getLanguage().setName(rs.getString("l.name"));
			join.getFilm().setRating(rs.getString("f.rating"));
			join.getFilm().setFilmId(rs.getInt("COUNT(*)") - 1);	// 재고수 
			
			list.add(join);
		}
		
		rs.close();
		stmt.close();
		
		return list;
	}
	
	@Override	// 영화 제고 목록 페이징을 위한  리스트 출력
	public int selectFilmPromotionCount(Connection conn, String searchTitle) throws Exception{
		int lastPage = 0;
		filmQuary = new FilmQuary();
		
		PreparedStatement stmt = conn.prepareStatement(filmQuary.SELECT_FILM_PROMOTION_COUNT);
		stmt.setString(1, "%" + searchTitle + "%");
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			lastPage++;
		}
		
		return lastPage;
	}
	
	@Override	// 영화 제고 관리 리스트 출력 Dao
	public List<JoinToTable> selectFilmPromotionList(Connection conn, int currentPage, int limitPage, String searchTitle) throws Exception{
		List<JoinToTable> list = new ArrayList<JoinToTable>();
		filmQuary = new FilmQuary();
		
		PreparedStatement stmt = conn.prepareStatement(filmQuary.SELECT_FILM_PROMOTION_LIST);
		stmt.setString(1, "%" + searchTitle + "%");
		stmt.setInt(2, (currentPage-1)*limitPage);
		stmt.setInt(3, limitPage);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			JoinToTable join = new JoinToTable();
			join.setFilmList(new FilmList());
			join.setFilm(new Film());
			join.setLanguage(new Language());
			
			join.getFilmList().setCategory(rs.getString("fl.category"));
			join.getFilm().setTitle(rs.getString("f.title"));
			join.getLanguage().setName(rs.getString("l.name"));
			join.getFilm().setRating(rs.getString("f.rating"));
			join.getFilm().setFilmId(rs.getInt("COUNT(*)") - 1);	// 재고수 
			
			list.add(join);
		}
		
		rs.close();
		stmt.close();
		
		return list;
	}
	
	@Override	// 영화 목록 관리 리스트 출력 Dao
	public List<JoinToTable> selectFilmList(Connection conn) throws Exception{
		List<JoinToTable> list = new ArrayList<JoinToTable>();
		filmQuary = new FilmQuary();
		
		PreparedStatement stmt = conn.prepareStatement(filmQuary.SELECT_FILM_LIST);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			JoinToTable join = new JoinToTable();
			join.setFilmList(new FilmList());
			join.setFilm(new Film());
			join.setLanguage(new Language());
			
			join.getFilmList().setCategory(rs.getString("fl.category"));
			join.getFilm().setTitle(rs.getString("f.title"));
			join.getLanguage().setName(rs.getString("l.name"));
			join.getFilm().setRating(rs.getString("f.rating"));
			join.getFilm().setRentalRate(rs.getFloat("f.rental_rate"));
			
			list.add(join);
		}
		
		stmt.close();
		
		return list;
	}
}
