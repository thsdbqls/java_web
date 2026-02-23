package phonebook;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class MainController extends HttpServlet {
	
	PhonebookService service;
	
	public MainController() {
		service = new PhonebookService();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=0;
		
		
		// 한글이 깨지는 이유는 전송되는 객체인 req의 encoding설정이 되어 있지 않아서 임
		//req.setCharacterEncoding("utf-8");
		// 위의 엔코딩을 사전에 처리하는 방법은 filter를 사용하여 처리할 수 있다
		
		System.out.println(req.getRequestURI());
		String command = req.getRequestURI().substring(1);  // substring을 1번째 부터 잘라내라
		switch(command){
			case "insertform" : 
				resp.sendRedirect("insertform.html");
				break;
			case "insert" : 
				// 값 전달 받기 -> 서비스로 처리하고 결과 받기 -> 결과를 저장하고 jsp페이지에 넘기기
				id = Integer.parseInt(req.getParameter("id"));
				String name = req.getParameter("name");
				String hp =	req.getParameter("hp");	
				String email =req.getParameter("email");
				String memo =req.getParameter("memo");
				System.out.println(name);
				//boolean result = service.insert(id, name, hp, email, memo);
				
				//req.setAttribute("result", result);  // 이것은 데이터를 저장하는 역활(이것이 모델이다) / 이것을 JSP로 보내겠다는 것이다
				//req.getRequestDispatcher("/WEB-INF/views/result.jsp").forward(req, resp);
				
				break;
			case "views" : 
				List<PhonebookVO> list = service.getPhonebooks();  // service는 데이터를 전달하는 역활 / 
				req.setAttribute("list", list);  // 이것은 데이터를 저장하는 역활(이것이 모델이다) / 이것을 JSP로 보내겠다는 것이다
				req.getRequestDispatcher("/WEB-INF/views/views.jsp").forward(req, resp);
				break;
			case "view" :
				id=Integer.parseInt(req.getParameter("id"));
				PhonebookVO pb=service.getPhonebook(id);
				req.setAttribute("pb",pb);
				req.getRequestDispatcher("/WEB-INF/views/view.jsp").forward(req, resp);
				break;
			case "updateform" : 
				id=Integer.parseInt(req.getParameter("id"));
				PhonebookVO upb=service.getPhonebook(id);
				req.setAttribute("pb",upb);
				req.getRequestDispatcher("/WEB-INF/views/updateform.jsp").forward(req, resp);
				
				break;
			case "update" : 
				id = Integer.parseInt(req.getParameter("id"));
				String _name = req.getParameter("name");
				String _hp =	req.getParameter("hp");	
				String _email =req.getParameter("email");
				String _memo =req.getParameter("memo");
				service.update(new PhonebookVO(id, _name, _hp, _email, _memo));
				boolean _result = service.delete(id);
				if(_result) req.setAttribute("result", "update success");
				else req.setAttribute("result", "update fail");
				req.setAttribute("list", service.getPhonebooks());
				req.getRequestDispatcher("/WEB-INF/views/views.jsp").forward(req, resp);
				break;
			case "delete" : 
				id=id = Integer.parseInt(req.getParameter("id"));
				boolean result = service.delete(id);
				if(result) req.setAttribute("result", "delete success");
				else req.setAttribute("result", "delete fail");
				req.setAttribute("list", service.getPhonebooks());
				req.getRequestDispatcher("/WEB-INF/views/views.jsp").forward(req, resp);
				break;
			default : break;
			
		}
	}
}
