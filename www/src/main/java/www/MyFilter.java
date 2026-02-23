package www;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyFilter implements Filter {

	public MyFilter() {
		System.out.println("필터 생성자 생성");
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 필터 등록
		System.out.println("필터 초기화!!");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("필터 실행");
		
		// chain의 역활은 필터 다음 서블릿으로 넘어가는 단계이다
		chain.doFilter(request, response);
	}
 

}
