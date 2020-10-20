package sakila.commons;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	public Connection getConnection() throws Exception{		
		String driver = "jdbc:mariadb://localhost:3306/sakila";
		String dbId = "root";
		String dbPw = "java1004";
		Connection connection = DriverManager.getConnection(driver, dbId, dbPw);
		
		return connection;
	}
}
