package sakila.quary;

public class ActorQuery {
	public static final String SELECT_ACTOR_LIST="SELECT actor_id ,first_name, last_name FROM actor WHERE last_name like ? LIMIT ?, ?";
	public static final String SELECT_ACTOR_LIST_COUNT="SELECT COUNT(*) FROM actor WHERE last_name like ?";
	public static final String INSERT_ACTOR="INSERT INTO actor(first_name, last_name) VALUES(?,?)";
	public static final String SELECT_ACTOR_ONE="SELECT a.actor_id actorId, a.first_name firstName, a.last_name lastName, f.title title FROM actor a LEFT JOIN film_actor fa ON a.actor_id = fa.actor_id LEFT JOIN film f ON fa.film_id = f.film_id WHERE a.actor_id = ?";
	public static final String UPDATE_ACTOR="UPDATE actor SET first_name = ?, last_name = ?, last_update = NOW() WHERE actor_id = ?";
}
