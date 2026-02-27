package login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//@WebServlet("/login") //http://localhost:8888/login url만 처리
//@WebServlet("/login/")//http://localhost:8888/login/ url만 처리
@WebServlet("/login/*") //모든 /login은 처리 가능
public class LoginController extends HttpServlet{
	LoginService service;
	public LoginController() {
		service=new LoginService();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println(req.getRequestURI());
		//test
		//http://localhost:8888/login
		//http://localhost:8888/login/login(폼로더) -> loginproc(폼처리)
		//http://localhost:8888/login/register(폼로더) -> registerproc(폼처리)
		
		// 폼은 login -> 처리는 loginproc라고 할경우 url의 갯수가 많아짐
		// method가 get방식이면 폼로더, post방식 처리는 의미한다고 할경우
		//하나의 url로 2개를 처리할 수 있음	
		System.out.println(req.getMethod());
		//curl -X POST http://localhost:8888/login
		//curl -X GET http://localhost:8888/login
		
		req.setCharacterEncoding("UTF-8");
		String uri=req.getRequestURI();
		//http://localhost:8888/login/register 가정
		String[] command=uri.split("/"); 
		//command[0]="" command[1]="login" command[2]="register"
		
		//String page="login.jsp";
		String page="login";
		switch (command[2]) {
		case "login" : // /login or /login/login 
			if(req.getMethod().equals("GET")) {
				//로그인 폼로더
				//page="login.jsp";
				page="login";
			//req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
		    } 
			else if(req.getMethod().equals("POST")){ 
				//로그인처리(post)
				String username=req.getParameter("username");
				String password=req.getParameter("password");
				System.out.println("cont:"+username);
				System.out.println("cont:"+password);
				
				boolean result=service.isLogin(username,password);
				
				if(result) {
				req.getSession().setAttribute("username",username);	
				req.getSession().setMaxInactiveInterval(60);
				}
				
				page="login";
			}
			break; 
		case "register": 
			if(req.getMethod().equals("GET")) {
				//회원가입 폼로더
				//page="register.jsp";
				page="register";
			//req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
		    } 
			else if(req.getMethod().equals("POST")){ 
				//회원가입처리(post)
				String username=req.getParameter("username");
				String password=req.getParameter("password");
				String hp=req.getParameter("hp");
				String email=req.getParameter("email");
				String nickname=req.getParameter("nickname");
				boolean result=service.register
				(new RegisterVO(username, password, hp, email, nickname));
				if(result) {
					req.setAttribute("result","register ok");
				}else {
					req.setAttribute("result","register fail");
				}
				page="login";
			}
			break;
			
		case "logout":
			req.getSession().invalidate();
			page="login";
			break;
			
		case "extendSession":
			req.getSession().setMaxInactiveInterval(60); // 다시 1분으로 연장
		    //resp.getOutputStream().print("OK");
			resp.getWriter().print("OK");
			page="";
			break;
		default: // /login
			//page="login.jsp";
			page="login";
			//req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
			
			break;
		}
		
		if(!page.equals("")) {
		//req.getRequestDispatcher("/WEB-INF/views/"+page).forward(req, resp);
		req.getRequestDispatcher("/WEB-INF/views/"+page+".jsp").forward(req, resp);
		}
		
		System.out.println("service함수 호출!!");
		//super.service(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("dopost함수 호출!!");
		//super.doPost(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doget함수 호출!!");
		//super.doGet(req, resp);
	}
}