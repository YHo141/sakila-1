package sakila.quary;

public class StaffQuary {
	/*
	 * SELECT
	 * staff_id, username
	 * FROM
	 * staff
	 * WHERE
	 * staff_id = ? AND password = ?
	 */
	public static final String SELECT_STAFF_BY_KEY = "SELECT staff_id, username FROM staff WHERE staff_id = ? AND password = PASSWORD(?)";

	
	public static final String SELECT_STAFF = "SELECT s.username, CONCAT(s.first_name, ' ', s.last_name) as name, a.phone, GROUP_CONCAT(co.country, ' ' ,ci.city) as address, s.email FROM staff s inner join address a on s.address_id = a.address_id inner join city ci on ci.city_id = a.city_id inner join country co on co.country_id = ci.country_id WHERE staff_id = ?";
	public static final String UPDATE_STAFF = "UPDATE staff s INNER JOIN address a ON s.address_id = a.address_id SET s.username = ?, s.email = ?, a.phone = ? WHERE staff_id = ? ";
}