package sakila.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DBListener implements ServletContextListener {
    public DBListener() {}
    public void contextDestroyed(ServletContextEvent sce)  { }

    public void contextInitialized(ServletContextEvent sce)  { 
        System.out.println("DBListener.contextInitialized() ����");
        try {
        	Class.forName("");
        	System.out.println("mariadb ����̺�ε� ����");
		} catch (Exception e) {
			System.out.println("Class.forName() ���� ���� !");
			e.printStackTrace();
		}
    }
	
}
