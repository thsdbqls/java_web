package Book;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/book")
public class MainController extends HttpServlet {

	BookAdminService service;
	
	public MainController() {
		service=new BookAdminService();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=0;

		System.out.println(req.getRequestURI());
		String command=req.getRequestURI().substring(1);
		switch(command) {
		case "insertform": resp.sendRedirect("insertform.html"); break;
		case "insert":   /// 입력
			
			break;
		case "views": 
			
			break;
		case "view" :
			
			break;
		case "updateform": 
			System.out.println("hihi");
			//id=Integer.parseInt(req.getParameter("isbn"));
			//BookVO upb=service.getBook(id);
			//req.setAttribute("b",upb);
			req.getRequestDispatcher("/WEB-INF/views/updateform.jsp").forward(req, resp);
			break;
		case "update": 			// 수정
			int isbn=Integer.parseInt(req.getParameter("isbn"));
			String bookname=req.getParameter("bookname");
			String author=req.getParameter("author");
			String publisher=req.getParameter("publisher");
			String image=req.getParameter("image");
			
			System.out.println("들어온 값 = ");
			System.out.println(isbn);
			System.out.println(bookname);
			System.out.println(author);
			System.out.println(publisher);
			System.out.println(image);
			
			service.update(new BookVO(isbn,bookname,author,publisher,image));
			boolean _result=service.delete(id);
			if(_result) req.setAttribute("result","update success");
			else req.setAttribute("result","update fail");
			req.setAttribute("list",service.getBooks());
			req.getRequestDispatcher("/WEB-INF/views/views.jsp").forward(req, resp);
			break;
		
		default: break;
		}
		
		
	}

}
