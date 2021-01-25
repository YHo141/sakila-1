package sakila.quary;

public class CustomerQuary {
	public static final String SELECT_CUSTOMER_LIST = "SELECT c.customer_id customerId, CONCAT(c.first_name, ' ', c.last_name) as name, a.phone phone, c.active active FROM rental r inner join customer c on c.customer_id = r.customer_id inner join address a on a.address_id = c.address_id WHERE CONCAT(c.first_name, ' ', c.last_name) LIKE ? GROUP BY CONCAT(c.first_name, ' ', c.last_name) LIMIT ?, ?";
	public static final String SELECT_CUSTOMER_LIST_BY_RETURN_DATE = "SELECT return_date returnDate FROM rental WHERE customer_id = ?";
	public static final String SELECT_CUSTOMER_LIST_COUNT = "SELECT COUNT(*) FROM rental r inner join customer c on c.customer_id = r.customer_id inner join address a on a.address_id = c.address_id WHERE CONCAT(c.first_name, ' ', c.last_name) LIKE ? GROUP BY CONCAT(c.first_name, ' ', c.last_name)";
	public static final String SELECT_CUSTOMER_ONE = "SELECT c.customer_id customerId, c.store_id storeId, CONCAT(c.last_name, ' ', c.first_name) customerName, CONCAT(cy.country, ' ', ct.city, ' ', a.address) address ,a.phone phone, c.email email FROM customer c INNER JOIN address a ON c.address_id = a.address_id INNER JOIN city ct ON ct.city_id = a.city_id INNER JOIN country cy ON cy.country_id = ct.country_id WHERE c.customer_id = ?";
	public static final String SELECT_CUSTOMER_BY_FILM_RETURN = "SELECT f.title title, r.rental_date rentalDate, DATE_ADD(r.rental_date, INTERVAL f.rental_duration DAY) scheduledReturnDate,  FLOOR(DATEDIFF(NOW(), DATE_ADD(r.rental_date, INTERVAL f.rental_duration DAY)) / f.rental_duration) * f.rental_rate arrears FROM rental r INNER JOIN inventory i ON r.inventory_id = i.inventory_id INNER JOIN film f ON f.film_id = i.film_id WHERE r.customer_id = ? and r.return_date IS NULL";
	public static final String UPDATE_CUSTOMER = "UPDATE customer c INNER JOIN address a ON c.address_id = a.address_id  SET a.phone = ?, c.email = ? WHERE customer_id = ?";
}
