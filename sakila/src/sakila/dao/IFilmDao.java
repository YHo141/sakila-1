package sakila.dao;

import java.sql.Connection;
import java.util.List;

import sakila.vo.Actor;
import sakila.vo.Category;
import sakila.vo.Film;
import sakila.vo.FilmList;
import sakila.vo.JoinToTable;
import sakila.vo.Language;
import sakila.vo.Rental;

public interface IFilmDao {
	List<JoinToTable> selectFilmList(Connection conn, String searchTitle, String categoryName, int currentPage, int limitPage) throws Exception;
	int selectFilmListCount(Connection conn, String searchTitle, String cateogoryName) throws Exception;
	List<Category> selectFilmByCategory(Connection conn) throws Exception;
	JoinToTable selectFilmListOne(Connection conn, int filmId) throws Exception;
	void updateFilm(Connection conn, Film film) throws Exception;
	List<Language> selectFilmAddLanguage(Connection conn) throws Exception;
	void insertFilm(Connection conn, Film film) throws Exception;
	void insertFilmCategory(Connection conn, int filmId, int categoryId) throws Exception;
	int selectFilmCategoryByInsert(Connection conn, Film film) throws Exception;
	List<Actor> selectActorListByFilmOne(Connection conn, int filmId) throws Exception;
	void insertFilmInventory(Connection conn, int filmId, int storeId) throws Exception;
	
	
	List<JoinToTable> selectFilmPromotionList(Connection conn, int currentPage, int limitPage, String searchTitle) throws Exception;
	List<Rental> selectFilmPromotionOneReturn(Connection conn, int filmId) throws Exception;
	List<Rental> selectFilmPromotionOneAll(Connection conn, int filmId) throws Exception;
	void updateFilmPromotionByReturn(Connection conn, int inventoryId) throws Exception;
	void insertFilmPromotionByRental(Connection conn, Rental rental) throws Exception;
	
	int selectFilmPromotionCount(Connection conn, String searchTitle) throws Exception;
	
	List<JoinToTable> selectFilmPromotionNotnullList(Connection conn, int currentPage, int limitPage, String searchTitle) throws Exception;
	
	int selectFilmPromotionNotnullCount(Connection conn, String searchTitle) throws Exception;
	
	List<JoinToTable> selectFilmPromotionNullList(Connection conn, int currentPage, int limitPage, String searchTitle) throws Exception;
	
	int selectFilmPromotionNullCount(Connection conn, String searchTitle) throws Exception;
}
