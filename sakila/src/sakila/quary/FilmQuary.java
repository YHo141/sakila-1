package sakila.quary;

public class FilmQuary {
	public static final String SELECT_FILM_LIST = "SELECT fl.category, f.title, l.name, f.rating, f.rental_rate FROM film_list fl inner join film f on fl.FID = f.film_id inner join language l on l.language_id = f.language_id LIMIT 0, 10";
}
