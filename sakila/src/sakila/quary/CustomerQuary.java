package sakila.quary;

public class CustomerQuary {
	public static final String SELECT_CUSTOMER_LIST = "SELECT CONCAT(c.first_name, ' ', c.last_name) as name, a.phone, c.active, r.return_date FROM rental r  inner join customer c on c.customer_id = r.customer_id inner join address a on a.address_id = c.address_id GROUP BY CONCAT(c.first_name, ' ', c.last_name),  r.return_date IS NULL LIMIT 0, 10";
}
