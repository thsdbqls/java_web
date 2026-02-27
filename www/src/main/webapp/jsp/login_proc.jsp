<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 1) 전달된 값 확인하고 변수에 저장하기
String username = request.getParameter("username");
String password = request.getParameter("password");

// 2) sql문을 처리하자.
Class.forName("org.h2.Driver");
    String url = "jdbc:h2:tcp://localhost/~/test";
    String user = "sa";
    Connection conn = DriverManager.getConnection(url, user, "");
    String sql = "select username, password from users where username = ? and password = ?";
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setString(1, username);
    ps.setString(2, password);
    ResultSet rs = ps.executeQuery();
    
    if(rs.next()){
    	request.getSession().setAttribute("username", username);
    	session.setMaxInactiveInterval(60);
    	//request.getServletContext().setAttribute(name, object);  // 이것이 application이다
    	%>
    	<script>
    	alert("로그인 성공");
    	</script>
    <%}else{%>
    	<script>
    	alert("로그인 실패");
    	</script>
   <% } %>
   <script>
   location.href = "/jsp/login.jsp"
   </script>