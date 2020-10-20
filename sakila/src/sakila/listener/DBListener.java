package sakila.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DBListener implements ServletContextListener {
    public DBListener() {}
    public void contextDestroyed(ServletContextEvent sce)  { }

    public void contextInitialized(ServletContextEvent sce)  { 
        System.out.println("DBListener.contextInitialized() 실행");
        try {
        	Class.forName("");
        	System.out.println("mariadb 드라이브로딩 성공");
		} catch (Exception e) {
			System.out.println("Class.forName() 실행 실패 !");
			e.printStackTrace();
		}
    }
	
}
