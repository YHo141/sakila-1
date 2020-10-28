package sakila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.quary.RentalQuary;
import sakila.vo.Film;
import sakila.vo.Rental;
import sakila.vo.RentalAndFilm;

public class RentalDao implements IRentalDao{
	private RentalQuary rentalQuary;
	
	@Override	// 영화 반납 페이지 리스트 출력
	public List<RentalAndFilm> selectFilmReturnList(Connection conn) throws Exception{
		List<RentalAndFilm> list = new ArrayList<RentalAndFilm>();
		rentalQuary = new RentalQuary();
		
		PreparedStatement stmt = conn.prepareStatement(rentalQuary.SELECT_FILM_RETURN_LIST);
		System.out.println(stmt + ": rentalDao stmt 확인");
		
		ResultSet rs = stmt.executeQuery(); 
		
		while(rs.next()) {
			RentalAndFilm raf = new RentalAndFilm();
			raf.setFilm(new Film());
			raf.setRental(new Rental());
			
			raf.getRental().setRentalId(rs.getInt("r.rental_id"));
			raf.getFilm().setTitle(rs.getString("f.title"));
			raf.getFilm().setRentalDuration(rs.getInt("f.rental_duration"));
			raf.getRental().setRentalDate(rs.getString("r.rental_date"));
			
			list.add(raf);
		}
		
		stmt.close();
		
		return list;
	}
}
