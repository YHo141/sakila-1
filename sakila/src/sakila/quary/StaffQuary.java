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

}
