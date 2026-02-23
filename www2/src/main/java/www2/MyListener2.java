package www2;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyListener2 implements ServletRequestListener{

	@Override
	public
	void requestInitialized(ServletRequestEvent sre) {
		System.out.println("request 객체의 setAttribute함수 실행될 때 실행됨!!");
	}
	
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		System.out.println("request 객체가 종료될 때 실행됨!!!");
	}
}
