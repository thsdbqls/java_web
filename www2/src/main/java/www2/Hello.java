package www2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class Hello extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().print("WEB Hello Server");
		
		// request객체에 어떠한 데이터를 저장하는 용도의 함수
		//req.setAttribute("name", "hongkildong");
		req.setAttribute("pb", new Phonebook("hongkildong","010-1111-1111", "hong@gmail.com"));
		// 저장된 데이터를 화면에 표시하기 위해 이동하는 페이지 명령어
		req.getRequestDispatcher("/WEB-INF/views/myname.jsp").forward(req, resp);
	}
}
