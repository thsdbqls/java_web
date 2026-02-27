package phonebookv2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

// 해당 컨트롤러는 http://localhost:8888/v2/ 모든 주소를 해당 서블릿이 처리한다.
@WebServlet("/v2/*")  // v2에 관한것은 모두 받아들이 겠다
public class MainController extends HttpServlet {
	
	PhonebookService service;
	boolean result;
	
	public MainController() {
		service = new PhonebookService();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getRequestURI());
		// 입력(입력 폼) : http://localhost:8888/v2/insertform
		// 입력(입력 처리) :http://localhost:8888/v2/insert
		// 전체 출력 : http://localhost:8888/v2/views
		// 선택 출력 : http://localhost:8888/v2/view?id=1
		// 수정 (폼) : http://localhost:8888/v2/updateform?id=1
		// 수정 (처리) : http://localhost:8888/v2/update
		// 삭제 : http://localhost:8888/v2/delete?id=1
		
		String path = req.getRequestURI();
		String[] paths = req.getRequestURI().split("/");
		for(String p:paths) {
			System.out.println(p);
		}
		
		// address: /v2/views
		// paths의 0번째 해당하는 부분 : 빈 란
		// paths의 1번째 해당하는 부분 : v2
		// paths의 2번째 해당하는 부분 : views
		switch(paths[2]) {
		case "insertform": 
			resp.sendRedirect("/insertform.html"); // 이것은 기본적으로 v2가 장착되게 된다. /가 있으면 상대 경로가 되며 /가 없으면 절대 경로가 된다
			break;  
		case "insert" : 
			// 스트림을 이용하여 multipart/form-data를 수신하는 원초적 데이터
			// 1) 첫번째 방법
			/*
			byte[] b = new byte[4*1024*1024];
			InputStream in = req.getInputStream();
			int len = in.read(b);
			String data = new String(b, 0, len);
			System.out.println(data);
			*/
			
			// 2) 두번째 방법(안정적인 방식이다)
			/*
			req.setCharacterEncoding("UTF-8");
			byte[] b = new byte[1*1024*1024];
			InputStream in = req.getInputStream();
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			int len;
			while((len = in.read(b)) != -1) {
				bos.write(b, 0, len);
			}
			byte[] result = bos.toByteArray();
			if (result.length > 0) {
				//String data = new String(result, "UTF-8")  // 문자열 형식
				String data = new String(result, "ISO-8859-1");  // multipart 형식이다
				System.out.println(data);
			} else {
				System.out.println("데이터 없음");
			}
			*/
			//String pic = req.getParameter("pic");
			//System.out.println("pic:"+pic);
			//String[] datas=data.split("------WebKitFormBoundary");
			//System.out.println("####1:"+datas[0]);
			//System.out.println("####2:"+datas[1]);
			//System.out.println("####3:"+datas[2]);
			//System.out.println(datas[1].indexOf("name="));
			//int pos=datas[1].indexOf("name=");
			//System.out.println("###str###:"+datas[1].substring(pos));
			//System.out.println("###str###:"+datas[1].substring(pos+5));
			//System.out.println("###str###:"+datas[1].substring(pos+9).trim());
			
			// 3) 세번째 방법 : 라이브러리를 이용하여 쉽게 폼데이터 내용 확인 및 파일 다운로드 하기
			// www.servlets.com/cos/
			MultipartRequest multi = new MultipartRequest(req,
					"D:\\25work\\workspace\\www (3)\\src\\main\\webapp\\fileupload",
					5*1024*1024,
					"UTF-8",
					new DefaultFileRenamePolicy());  
			// request, 저장할 위치, 크기, 정책(같은 파일이 들어오면 이름을 자동으로 +1 시킨다)
			
			// 파라메다를 가지고 오는 방법
			
			int id=Integer.parseInt(multi.getParameter("id"));
			String name=multi.getParameter("name");
			String hp=multi.getParameter("hp");
			String email=multi.getParameter("email");
			String memo=multi.getParameter("memo");
			System.out.println(multi.getParameter("id"));
			System.out.println(multi.getParameter("name"));
			System.out.println(multi.getParameter("hp"));
			System.out.println(multi.getParameter("email"));
			System.out.println(multi.getParameter("memo"));
			// 파일에 대한 정보(파일 명) - 파일 다운로드는 이미 실행되었고 파일의 정보를 획득하는 작업이다.
			//System.out.println(multi.getParameter("pic")); // file에서는 사용이 불가하다
			Enumeration<String> files = multi.getFileNames();
			String filename = null;
			while(files.hasMoreElements()) {  // 혹시 다음에 파일이 있어요? 라는 것이다.
				//System.out.println(files.nextElement());  // tag의 name
				// 이터네이터는 next를 호출하면 버퍼에서 제거되므로 위의 코드를 주석처리해야 처음 호출하는 것과 같다.
				String tagname = files.nextElement();
				//System.out.println(multi.getOriginalFileName(tagname));
				String _filename = multi.getOriginalFileName(tagname);
				filename = _filename;
			}
			// 입력 후 결과를 req에 저장한 후 views.jsp를 실행하는 코드
			result=service.insert(id, name, hp, email,memo,filename);
			if(result) req.setAttribute("result","insert success");
			else req.setAttribute("result","update fail");
			req.setAttribute("list",service.getPhonebooks());
			req.getRequestDispatcher("/WEB-INF/views/views.jsp").forward(req, resp);
			break;
		case "views" : 
			System.out.println("views명령 실행");
			List<PhonebookVO> list=service.getPhonebooks();  // 데이터를 가죠오는 작업
			req.setAttribute("list",list); // 데이터를 저장하는 작업
			req.getRequestDispatcher("/WEB-INF/views/views.jsp").forward(req, resp);  // 저장된 데이터를 표시하는 페이지로 이동하는 
			break;
		case "view" : 
			System.out.println("view명령 실행");
			id=Integer.parseInt(req.getParameter("id"));
			PhonebookVO pb=service.getPhonebook(id);
			req.setAttribute("pb",pb);
			req.getRequestDispatcher("/WEB-INF/views/view.jsp").forward(req, resp);
			break;
			
		case "updateform": 
			id=Integer.parseInt(req.getParameter("id"));
			PhonebookVO upb=service.getPhonebook(id);
			req.setAttribute("pb",upb);
			req.getRequestDispatcher("/WEB-INF/views/updateform.jsp").forward(req, resp);
			break;	
			
		case "update" : 
			System.out.println("update명령 실행");
			id=Integer.parseInt(req.getParameter("id"));
			String _name=req.getParameter("name");
			String _hp=req.getParameter("hp");
			String _email=req.getParameter("email");
			String _memo=req.getParameter("memo");
			//service.update(new PhonebookVO(id,_name,_hp,_email,_memo));
			boolean _result=service.delete(id);
			if(_result) req.setAttribute("result","update success");
			else req.setAttribute("result","update fail");
			service.update(new PhonebookVO(id,_name,_hp,_email,_memo));
			req.setAttribute("list",service.getPhonebooks());
			req.getRequestDispatcher("/WEB-INF/views/views.jsp").forward(req, resp);
			break;
			
		case "delete" : 
			System.out.println("delete명령 실행");
			id = Integer.parseInt(req.getParameter("id"));
			System.out.println(id);
			result=service.delete(id);
			if(result) req.setAttribute("result","delete success");
			else req.setAttribute("result","delete fail");
			req.setAttribute("list",service.getPhonebooks());
			req.getRequestDispatcher("/WEB-INF/views/views.jsp").forward(req, resp);
			break;

		}
	}
}
