package sakila.quary;

public class FilmQuary {
	public static final String SELECT_FILM_LIST = "SELECT f.film_id filmId, c.name categoryName, f.title title, l.name languageName, f.rating rating, f.rental_rate rentalRate FROM film f LEFT JOIN language l ON f.language_id = l.language_id LEFT JOIN film_category fc ON fc.film_id = f.film_id LEFT JOIN category c ON c.category_id = fc.category_id WHERE f.title LIKE ? AND c.name LIKE ? LIMIT ?, ?";
	public static final String SELECT_FILM_LIST_COUNT = "SELECT COUNT(*) FROM film_list fl inner join film f on fl.FID = f.film_id inner join language l on l.language_id = f.language_id WHERE f.title LIKE ? AND fl.category LIKE ?";
	public static final String SELECT_FILM_BY_CATEGORY = "SELECT name, category_id categoryId FROM category";
	public static final String SELECT_FILM_LIST_ONE = "SELECT f.film_id filmId, f.title title, f.description description, c.name categoryName, f.rental_rate rentalRate, f.length filmLength, f.rating rating FROM film f LEFT JOIN film_category fc ON f.film_id = fc.film_id LEFT JOIN category c ON c.category_id = fc.category_id WHERE f.film_id = ?";
	public static final String SELECT_ACTOR_LIST_BY_FILM_ONE = "SELECT CONCAT(a.last_name, ' ', a.first_name) name FROM film f LEFT JOIN film_actor fa ON f.film_id = fa.film_id LEFT JOIN actor a ON a.actor_id = fa.actor_id WHERE f.film_id = ?";
	public static final String UPDATE_FILM = "UPDATE film SET description = ?, rental_rate = ? WHERE film_id = ?";
	public static final String SELECT_FILM_ADD_LANGUAGE = "SELECT name,language_id languageId FROM language";
	public static final String INSERT_FILM = "INSERT INTO film(title, description, language_id, rental_rate, length, rating) VALUES(?,?,?,?,?,?)";
	public static final String INSERT_FILM_CATEGORY = "INSERT INTO film_category(film_id, category_id) VALUES(?,?)";
	public static final String SELECT_FILM_CATEGORY_BY_INSERT = "SELECT film_id filmId FROM film WHERE title=? AND description=?";
	
	public static final String SELECT_FILM_PROMOTION_LIST = "SELECT fl.category, f.title, l.name, f.rating, COUNT(*) FROM film_list fl  inner join film f on fl.FID = f.film_id inner join language l on l.language_id = f.language_id inner join inventory i on i.film_id = f.film_id inner join rental r on r.inventory_id = i.inventory_id WHERE f.title LIKE ? GROUP BY f.title LIMIT ?, ?";
	
	public static final String SELECT_FILM_PROMOTION_COUNT = "SELECT COUNT(*) FROM film_list fl  inner join film f on fl.FID = f.film_id inner join language l on l.language_id = f.language_id inner join inventory i on i.film_id = f.film_id inner join rental r on r.inventory_id = i.inventory_id WHERE f.title LIKE ? GROUP BY f.title";
	
	public static final String SELECT_FILM_PROMOTION_LIST_NOTNULL = "SELECT fl.category, f.title, l.name, f.rating, COUNT(*) FROM film_list fl  inner join film f on fl.FID = f.film_id inner join language l on l.language_id = f.language_id inner join inventory i on i.film_id = f.film_id inner join rental r on r.inventory_id = i.inventory_id WHERE r.return_date IS NOT NULL AND f.title LIKE ? GROUP BY f.title LIMIT ?, ?";
	
	public static final String SELECT_FILM_PROMOTION_COUNT_NOTNULL = "SELECT COUNT(*) FROM film_list fl  inner join film f on fl.FID = f.film_id inner join language l on l.language_id = f.language_id inner join inventory i on i.film_id = f.film_id inner join rental r on r.inventory_id = i.inventory_id WHERE r.return_date IS NOT NULL AND f.title LIKE ? GROUP BY f.title";
	
	public static final String SELECT_FILM_PROMOTION_LIST_NULL = "SELECT fl.category, f.title, l.name, f.rating, COUNT(*) FROM film_list fl  inner join film f on fl.FID = f.film_id inner join language l on l.language_id = f.language_id inner join inventory i on i.film_id = f.film_id inner join rental r on r.inventory_id = i.inventory_id WHERE r.return_date IS NULL AND f.title LIKE ? GROUP BY f.title LIMIT ?, ?";
	
	public static final String SELECT_FILM_PROMOTION_COUNT_NULL = "SELECT COUNT(*) FROM film_list fl  inner join film f on fl.FID = f.film_id inner join language l on l.language_id = f.language_id inner join inventory i on i.film_id = f.film_id inner join rental r on r.inventory_id = i.inventory_id WHERE r.return_date IS NULL AND f.title LIKE ? GROUP BY f.title";
}
