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
