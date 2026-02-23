package wabc;

import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener {
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("new 가 실행된면 실행");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("리스너 시작");
	}
	

}
 