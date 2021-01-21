package sakila.quary;

public class ActorQuery {
	public static final String SELECT_ACTOR_LIST="SELECT actor_id ,first_name, last_name FROM actor WHERE last_name like ? LIMIT ?, ?";
	public static final String SELECT_ACTOR_LIST_COUNT="SELECT COUNT(*) FROM actor WHERE last_name like ?";
	public static final String INSERT_ACTOR="INSERT INTO actor(first_name, last_name) VALUES(?,?)";
	public static final String SELECT_ACTOR_ONE="SELECT a.actor_id actorId, a.first_name firstName, a.last_name lastName, f.title title FROM actor a LEFT JOIN film_actor fa ON a.actor_id = fa.actor_id LEFT JOIN film f ON fa.film_id = f.film_id WHERE a.actor_id = ?";
	public static final String UPDATE_ACTOR="UPDATE actor SET first_name = ?, last_name = ?, last_update = NOW() WHERE actor_id = ?";
	public static final String SELECT_STARRING_ACTOR_LIST="SELECT c.name name, f.title title, f.film_id filmId FROM film f LEFT JOIN film_category fc ON f.film_id = fc.film_id LEFT JOIN category c ON c.category_id = fc.category_id WHERE f.title LIKE ? AND c.name LIKE ? LIMIT ?, ?";
	public static final String SELECT_STARRING_ACTOR_LIST_COUNT="SELECT COUNT(*) FROM film f LEFT JOIN film_category fc ON f.film_id = fc.film_id LEFT JOIN category c ON c.category_id = fc.category_id WHERE f.title LIKE ? AND c.name LIKE ?";
	public static final String SELECT_CATEGORY="SELECT name FROM category";
	public static final String SELECT_STARRING_ACTOR_ONE="SELECT c.name categoryName, f.title title, CONCAT(a.last_name, ' ' ,a.first_name) actorName FROM film f LEFT JOIN film_category fc ON f.film_id = fc.film_id LEFT JOIN category c ON c.category_id = fc.category_id LEFT JOIN film_actor fa ON fa.film_id = f.film_id LEFT JOIN actor a ON fa.actor_id = a.actor_id WHERE f.film_id = ?";
	public static final String SELECT_STARRING_ACTOR_NOT_SELECT="SELECT a.actor_id actorId, CONCAT(a.last_name, ' ' ,a.first_name) actorName FROM film_actor fa INNER JOIN actor a ON fa.actor_id = a.actor_id GROUP BY actorName";
	public static final String SELECT_STARRING_ACTOR_BY_FILM="SELECT actor_id actorId FROM film_actor WHERE film_id = ?";
	public static final String INSERT_ACTOR_BY_FILM="INSERT INTO film_actor(actor_id, film_id) VALUES(?,?)";
}
