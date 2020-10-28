package sakila.quary;

public class FilmQuary {
	public static final String SELECT_FILM_LIST = "SELECT fl.category, f.title, l.name, f.rating, f.rental_rate FROM film_list fl inner join film f on fl.FID = f.film_id inner join language l on l.language_id = f.language_id LIMIT 0, 10";
	
	public static final String SELECT_FILM_PROMOTION_LIST = "SELECT fl.category, f.title, l.name, f.rating, COUNT(*) FROM film_list fl  inner join film f on fl.FID = f.film_id inner join language l on l.language_id = f.language_id inner join inventory i on i.film_id = f.film_id inner join rental r on r.inventory_id = i.inventory_id WHERE r.return_date IS NOT NULL GROUP BY f.title LIMIT 0, 10";
}
