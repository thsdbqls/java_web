package www;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Hello() {
        System.out.println("서블릿 생성자 실행!!!");

    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.getInputStream();   // 들어오는 데이터를 확인하는 원초적인 스트림
		//response.getOutputStream();  // 나가는 데이터를 처리하는 원초적인 스트림
		
		PrintWriter out = response.getWriter();
		out.print("Hello SERVLET!!!");  // 이것은 웹상에서 보내는 것이고  // 클라이언트에게 데이터를 전성할때 사용
		System.out.println("Hello SERVLET!!!");  // 이것은 콘솔에서 확인하는 것  // 서버에서 모니터링할 때 사용
	}

}
