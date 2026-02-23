package www;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener {
	
	// 객체가 new할 때 호출
	public MyListener() {
		System.out.println("리스너 생성");
	}
	
	// 객체가 생성된 후 바로 호출
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("서버 생성");
	}
	
	// 객체가 종료할 때 호출
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("서버 종료");
	}
}
