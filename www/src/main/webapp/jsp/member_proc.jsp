<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
    // 한글 깨짐 처리
    request.setCharacterEncoding("UTF-8");
    // 전달되는 값을 확인하고 수신하는 것이 우선이다!!
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String hp = request.getParameter("hp");
    String email = request.getParameter("email");
    String nickname = request.getParameter("nickname");
    
    // 오류가 발생할 경우 전달값을 System.out.println으로 확인한다
    
    Class.forName("org.h2.Driver");
    String url = "jdbc:h2:tcp://localhost/~/test";
    String user = "sa";
    Connection conn = DriverManager.getConnection(url, user, "");
    String sql = "INSERT INTO users (username, password, email, hp, nickname) VALUES (?, ?, ?, ?, ?)";
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setString(1, username);
    ps.setString(2, password);
    ps.setString(3, hp);
    ps.setString(4, email);
    ps.setString(5, nickname);

    int result = ps.executeUpdate();
    
    if(result>0){
     %>
     <script>
     alert("회원가입이 되셨습니다")
     location.href = "/jsp/login.jsp";
     </script>
     <%} else{ %>
     <<script>
alert("회원가입에 실패했습니다");
history.back();     
</script>
<% }%>
