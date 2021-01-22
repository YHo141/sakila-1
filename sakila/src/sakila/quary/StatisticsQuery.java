package sakila.quary;

public class StatisticsQuery {
	public static final String SELECT_STORE_BY_STAFF = "SELECT CONCAT(first_name, ' ', last_name) staffName FROM staff WHERE staff_id = ?";
	public static final String SELECT_STORE_SUM="SELECT total_sales FROM sales_by_store WHERE manager = ?";
	public static final String SELECT_STORE_RENTAL_SUM="SELECT COUNT(*) FROM rental WHERE staff_id = ? AND return_date IS NULL";
	public static final String SELECT_STORE_CATEGORY_SUM="SELECT category, total_sales totalSales FROM sales_by_film_category LIMIT ?, ?";
	public static final String SELECT_STORE_CATEGORY_SUM_COUNT="SELECT COUNT(*) FROM sales_by_film_category";
}
