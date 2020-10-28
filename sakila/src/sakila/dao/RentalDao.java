package sakila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.quary.RentalQuary;
import sakila.vo.Film;
import sakila.vo.JoinToTable;
import sakila.vo.Rental;

public class RentalDao implements IRentalDao{
	private RentalQuary rentalQuary;
	
	@Override	// 영화 반납 페이지 리스트 출력
	public List<JoinToTable> selectFilmReturnList(Connection conn) throws Exception{
		List<JoinToTable> list = new ArrayList<JoinToTable>();
		rentalQuary = new RentalQuary();
		
		PreparedStatement stmt = conn.prepareStatement(rentalQuary.SELECT_FILM_RETURN_LIST);
		System.out.println(stmt + ": rentalDao stmt 확인");
		
		ResultSet rs = stmt.executeQuery(); 
		
		while(rs.next()) {
			JoinToTable join = new JoinToTable();
			join.setFilm(new Film());
			join.setRental(new Rental());
			
			join.getRental().setRentalId(rs.getInt("r.rental_id"));
			join.getFilm().setTitle(rs.getString("f.title"));
			join.getFilm().setRentalDuration(rs.getInt("f.rental_duration"));
			join.getRental().setRentalDate(rs.getString("r.rental_date"));
			
			list.add(join);
		}
		
		stmt.close();
		
		return list;
	}
}
