package wabc;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GuGuDan extends HttpServlet{
@Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// 구구단
	// 웹에서 갑을 받아서
	String _data = req.getParameter("dan");
	int dan = Integer.parseInt(_dan);
	
	resp.setContentType("text/html; charset=utf-8");
	resp.setCharacterEncoding("utf-8");
	
	PrintWriter out = resp.getWriter();
	out.print("<html><head></head><body>");
	for(int i=1; i<10; i++) {
		System.out.println(dan+" x "+ i + "=" + (dan*i));
		
		// 웹에서 표시하게 하는 것은 스트림
		
		out.print("2 x "+ i + "=" + (2*i)+"<br>");
	}
	out.print("</body></html>");
}
}
