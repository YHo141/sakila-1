package sakila.quary;

public class RentalQuary {
	public static final String SELECT_FILM_RETURN_LIST = "SELECT r.rental_id, f.title, f.rental_duration, r.rental_date FROM rental r inner join inventory i on i.inventory_id = r.inventory_id inner join film f on f.film_id = i.film_id LIMIT ?, ?";
	
	public static final String SELECT_FILM_RETURN_LIST_COUNT = "SELECT COUNT(*) as cnt FROM rental r inner join inventory i on i.inventory_id = r.inventory_id inner join film f on f.film_id = i.film_id";
	
	public static final String SELECT_FILM_RETURN_LIST_BY_TITLE = "SELECT r.rental_id, f.title, f.rental_duration, r.rental_date FROM rental r inner join inventory i on i.inventory_id = r.inventory_id inner join film f on f.film_id = i.film_id WHERE f.title like ? LIMIT ?, ?";
	
	public static final String SELECT_FILM_RETURN_LIST_BY_TITLE_COUNT = "SELECT COUNT(*) as cnt FROM rental r inner join inventory i on i.inventory_id = r.inventory_id inner join film f on f.film_id = i.film_id WHERE f.title like ?";
}
