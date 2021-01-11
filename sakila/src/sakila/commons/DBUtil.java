package sakila.commons;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	public Connection getConnection() throws Exception{		
		String driver = "jdbc:mariadb://legend-by.kro.kr:3306/sakila";
		String dbId = "bur5698";
		String dbPw = "letsrember12";
		Connection connection = DriverManager.getConnection(driver, dbId, dbPw);
		
		return connection;
	}
}
