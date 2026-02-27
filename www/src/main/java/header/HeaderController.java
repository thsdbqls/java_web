package header;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.json.JSONStringer;


@WebServlet("/api/*")
public class HeaderController extends HttpServlet {
	
	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	resp.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8889");
	resp.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
	resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
	resp.setHeader("Access-Control-Allow-Credentials", "true");
	resp.setStatus(HttpServletResponse.SC_OK);
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 resp.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8889");
	     resp.setHeader("Access-Control-Allow-Credentials", "true");
	     resp.setContentType("application/json;charset=UTF-8");
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		String uri=req.getRequestURI();
		String[] command=uri.split("/");
		switch (command[2]) {
		//http://localhost:8888/api/insert
		case "insert" :
			//form형태로 데이터를 받을 경우 처리됨:
			//content-type이 application/x-www-form-urlencoded
			//json형태로 데이터를 받을 경우 null처리가 됨
			//content-type이 application/json
			System.out.println(req.getParameter("id"));
			System.out.println(req.getParameter("name"));
			System.out.println(req.getParameter("age"));
			//application/json일 경우 아래의 코드로 문자열을 읽어야함			
			BufferedReader reader
			=new BufferedReader(req.getReader());
			StringBuilder json=new StringBuilder();
			String line;
			while((line=reader.readLine()) !=null) {
				json.append(line);
			}
			System.out.println("line:"+json.toString()); 
			
			//문자열을 json객체로 처리하는 함수
			/*
			JSONObject jobj=new JSONObject(line);
			System.out.println(jobj.toString());
			*/
			
			//문자열을 json객체로 처리하는 함수
			JSONStringer jsonstr=new JSONStringer();
			
			
			break;
		//http://localhost:8888/api/findall
		case "findall" :
			//별도의 입력값이 필요없음
			resp.getWriter().print(
					"[{\"name\":\"홍길동1\",\"age\":\"23\"}"
					+ ",{\"name\":\"홍길동2\",\"age\":\"24\"}]"
			);
			resp.setStatus(HttpServletResponse.SC_OK);
			break;
		//http://localhost:8888/api/findbyid?id=1
		case "findbyid" :
			System.out.println(req.getParameter("id"));
			resp.getWriter().print("{\"name\":\"홍길동\",\"age\":\"22\"}");
			resp.setStatus(HttpServletResponse.SC_OK);
			break;
			
		default:
			
			break;
		}
		super.service(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		System.out.println(req.getRequestURI());
		//resp는 응답에 대한 header와 body를 제공하는 것임
		//header
		resp.setCharacterEncoding("UTF-8");
		//resp.setContentType("text/html;chatset=utf-8");
		resp.setContentType("application/json");
		//body(return값)
		resp.getWriter().print("{\"name\":\"홍길동\",\"age\":\"22\"}");
		
		//단순한 문자열이므로 필요없는 데이터
		//resp.getWriter().print("HTTP Status 200 - OK");
		
		resp.setStatus(HttpServletResponse.SC_OK);
		//super.doGet(req, resp);
		/// 
		*/
	}
}

