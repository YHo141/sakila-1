package sakila.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import sakila.dao.StatsDao;
import sakila.service.StatsService;

@WebListener
public class StatsListener implements HttpSessionListener {
    public StatsListener() {}
    public void sessionCreated(HttpSessionEvent se)  { 
    	System.out.println("세션 발생");
    	if(se.getSession().isNew()) {    		
    		StatsService statsService = new StatsService(new StatsDao());
    		statsService.countStats();
    	}
    }

    public void sessionDestroyed(HttpSessionEvent arg0)  {}	
}
