package news;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/news/*")
public class NewsController extends HttpServlet {
	
	NewsService service;
	public NewsController() {
		service = new NewsService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getRequestURI());
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		
		String[] command = req.getRequestURI().split("/");
		switch (command[2]) {
		case "views":   
			// 파라메다존재 여부 확인
			// 서비스에서 가져올 데이터
			List<MainNews> list = service.getMainNews();
			// 가져온 데이터를 저장
			req.setAttribute("list", list);
			// 페이지 이동(위에서 저장한 데이터를 views.jsp에서 화면 표시)
			req.getRequestDispatcher("/WEB-INF/views/news/views.jsp").forward(req, resp);
			System.out.println("views call");
			break;
			
		case "view":
			System.out.println("view call");
			// (1)파라메다 존재 여부 확인
			Long id =  Long.parseLong(req.getParameter("id"));
			System.out.println(id);
			
			// (3)서비스에서 가져올 데이터
			News news = service.getSubNews(id);
			
			// (4)가져온 데이터를 저장
			if(news != null) {
			    req.setAttribute("news", news);
			}
						
			// (2)페이지 이동
			req.getRequestDispatcher("/WEB-INF/views/news/view.jsp").forward(req, resp);
			break;
			
		default:
			break;
		}
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
