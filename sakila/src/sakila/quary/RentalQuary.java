package sakila.quary;

public class RentalQuary {
	public static final String SELECT_FILM_RETURN_LIST = "SELECT r.rental_id, f.title, f.rental_duration, DATE_ADD(r.rental_date, INTERVAL f.rental_duration DAY) return_date, r.return_date FROM rental r inner join inventory i on i.inventory_id = r.inventory_id inner join film f on f.film_id = i.film_id ORDER BY DATE_ADD(r.rental_date, INTERVAL f.rental_duration DAY) DESC LIMIT ?, ?";
	
	public static final String SELECT_FILM_RETURN_LIST_COUNT = "SELECT COUNT(*) as cnt FROM rental r inner join inventory i on i.inventory_id = r.inventory_id inner join film f on f.film_id = i.film_id";
	
	public static final String SELECT_FILM_RETURN_LIST_BY_TITLE = "SELECT r.rental_id, f.title, f.rental_duration, DATE_ADD(r.rental_date, INTERVAL f.rental_duration DAY) return_date, r.return_date FROM rental r inner join inventory i on i.inventory_id = r.inventory_id inner join film f on f.film_id = i.film_id WHERE f.title like ? ORDER BY DATE_ADD(r.rental_date, INTERVAL f.rental_duration DAY) DESC LIMIT ?, ?";
	
	public static final String SELECT_FILM_RETURN_LIST_BY_TITLE_COUNT = "SELECT COUNT(*) as cnt FROM rental r inner join inventory i on i.inventory_id = r.inventory_id inner join film f on f.film_id = i.film_id WHERE f.title like ?";
	
	public static final String UPDATE_RENTAL_RETURN_DATE = "UPDATE rental SET return_date = NOW() WHERE rental_id = ?";
}
