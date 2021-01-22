package sakila.quary;

public class MvpQuery {
	public static final String SELECT_PATMENT_BY_MVP = "SELECT CONCAT(c.last_name, ' ' ,c.first_name) customerName, SUM(p.amount) amount FROM payment p INNER JOIN customer c ON p.customer_id = c.customer_id GROUP BY p.customer_id HAVING SUM(p.amount) > 150 ORDER BY SUM(p.amount) DESC LIMIT ?, ?";
	public static final String SELECT_PAYMENT_BY_MVP_COUNT = "SELECT COUNT(*) FROM payment p INNER JOIN customer c ON p.customer_id = c.customer_id GROUP BY p.customer_id HAVING SUM(p.amount) > 150";
}
