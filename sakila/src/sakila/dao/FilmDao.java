package sakila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.quary.FilmQuary;
import sakila.vo.Actor;
import sakila.vo.Category;
import sakila.vo.Film;
import sakila.vo.FilmList;
import sakila.vo.JoinToTable;
import sakila.vo.Language;

public class FilmDao implements IFilmDao{
	private FilmQuary filmQuary;
	
	@Override
	public int selectFilmCategoryByInsert(Connection conn, Film film) throws Exception{
		int filmId = 0;
		filmQuary = new FilmQuary();
		
		PreparedStatement stmt = conn.prepareStatement(filmQuary.SELECT_FILM_CATEGORY_BY_INSERT);
		stmt.setString(1, film.getTitle());
		stmt.setString(2, film.getDescription());
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			filmId = rs.getInt("filmId");
		}
		
		stmt.close();
		rs.close();
		
		return filmId;
	}
	@Override
	public void insertFilmCategory(Connection conn, int filmId, int categoryId) throws Exception{
		filmQuary = new FilmQuary();
		
		PreparedStatement stmt = conn.prepareStatement(filmQuary.INSERT_FILM_CATEGORY);
		stmt.setInt(1, filmId);
		stmt.setInt(2, categoryId);
		
		stmt.executeLargeUpdate();
		
		stmt.close();
		
	}
	@Override
	public void insertFilm(Connection conn, Film film) throws Exception{
		filmQuary = new FilmQuary();
		
		PreparedStatement stmt = conn.prepareStatement(filmQuary.INSERT_FILM);
		stmt.setString(1, film.getTitle());
		stmt.setString(2, film.getDescription());
		stmt.setInt(3, film.getLanguageId());
		stmt.setFloat(4, film.getRentalRate());
		stmt.setInt(5, film.getLength());
		stmt.setString(6, film.getRating());
		
		stmt.executeLargeUpdate();
		
		stmt.close();
	}
	
	@Override
	public List<Language> selectFilmAddLanguage(Connection conn) throws Exception{
		List<Language> list = new ArrayList<Language>();
		filmQuary = new FilmQuary();
		
		PreparedStatement stmt = conn.prepareStatement(filmQuary.SELECT_FILM_ADD_LANGUAGE);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Language language = new Language();
			language.setName(rs.getString("name"));
			language.setLanguageId(rs.getInt("languageId"));
			
			list.add(language);
		}
		
		stmt.close();
		rs.close();
		
		return list;
	}
	
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
	
	@Override
	public void updateFilm(Connection conn, Film film) throws Exception{
		filmQuary = new FilmQuary();
		
		PreparedStatement stmt = conn.prepareStatement(filmQuary.UPDATE_FILM);
		stmt.setString(1, film.getDescription());
		stmt.setFloat(2, film.getRentalRate());
		stmt.setInt(3, film.getFilmId());
		
		stmt.executeLargeUpdate();
		
		stmt.close();
	}
	
	@Override
	public List<Actor> selectActorListByFilmOne(Connection conn, int filmId) throws Exception{
		List<Actor> actorList = new ArrayList<Actor>();
		filmQuary = new FilmQuary();
		
		PreparedStatement stmt = conn.prepareStatement(filmQuary.SELECT_ACTOR_LIST_BY_FILM_ONE);
		stmt.setInt(1, filmId);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Actor actor = new Actor();
			actor.setLastName(rs.getString("name"));
			
			actorList.add(actor);
		}
		
		stmt.close();
		rs.close();
		
		return actorList;
	}
	
	@Override
	public JoinToTable selectFilmListOne(Connection conn, int filmId) throws Exception{
		JoinToTable join = new JoinToTable();
		filmQuary = new FilmQuary();
		
		PreparedStatement stmt = conn.prepareStatement(filmQuary.SELECT_FILM_LIST_ONE);
		stmt.setInt(1, filmId);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			join.setFilm(new Film());
			join.setCategory(new Category());
			
			
			join.getFilm().setFilmId(rs.getInt("filmId"));
			join.getFilm().setTitle(rs.getString("title"));
			join.getCategory().setName(rs.getString("categoryName"));
			join.getFilm().setDescription(rs.getString("description"));
			join.getFilm().setRentalRate(rs.getFloat("rentalRate"));
			join.getFilm().setLength(rs.getInt("filmLength"));
			join.getFilm().setRating(rs.getString("rating"));
		}
		
		stmt.close();
		rs.close();
		
		return join;
	}
	
	@Override
	public List<Category> selectFilmByCategory(Connection conn) throws Exception{
		List<Category> categoryList = new ArrayList<Category>();
		filmQuary = new FilmQuary();
		
		PreparedStatement stmt = conn.prepareStatement(filmQuary.SELECT_FILM_BY_CATEGORY);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Category category = new Category();
			category.setName(rs.getString("name"));
			category.setCategoryId(rs.getInt("categoryId"));
			
			categoryList.add(category);
		}
		
		stmt.close();
		rs.close();
		
		return categoryList;
	}
	
	@Override
	public int selectFilmListCount(Connection conn, String searchTitle, String categoryName) throws Exception{
		int lastPage = 0;
		filmQuary = new FilmQuary();
		
		PreparedStatement stmt = conn.prepareStatement(filmQuary.SELECT_FILM_LIST_COUNT);
		stmt.setNString(1, "%" + searchTitle + "%");
		stmt.setString(2, "%" + categoryName + "%");
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			lastPage = rs.getInt("COUNT(*)");
		}
		
		//System.out.println(lastPage + "<----- lastPage");
		
		stmt.close();
		rs.close();
		
		return lastPage;
	}
	
	@Override	// 영화 목록 관리 리스트 출력 Dao
	public List<JoinToTable> selectFilmList(Connection conn, String searchTitle, String categoryName, int currentPage, int limitPage) throws Exception{
		List<JoinToTable> list = new ArrayList<JoinToTable>();
		filmQuary = new FilmQuary();
		
		//System.out.println(searchTitle+","+categoryName+","+currentPage+","+limitPage);
		
		PreparedStatement stmt = conn.prepareStatement(filmQuary.SELECT_FILM_LIST);
		stmt.setString(1, "%" + searchTitle + "%");
		stmt.setString(2, "%" + categoryName + "%");
		stmt.setInt(3, (currentPage - 1)*limitPage);
		stmt.setInt(4, limitPage);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			JoinToTable join = new JoinToTable();
			join.setFilmList(new FilmList());
			join.setFilm(new Film());
			join.setLanguage(new Language());
			
			join.getFilm().setFilmId(rs.getInt("filmId"));
			join.getFilmList().setCategory(rs.getString("categoryName"));
			join.getFilm().setTitle(rs.getString("title"));
			join.getLanguage().setName(rs.getString("languageName"));
			join.getFilm().setRating(rs.getString("rating"));
			join.getFilm().setRentalRate(rs.getFloat("rentalRate"));
			
			list.add(join);
		}
		
		//ystem.out.println(list + "<----list");
		
		stmt.close();
		rs.close();
		
		return list;
	}
}
