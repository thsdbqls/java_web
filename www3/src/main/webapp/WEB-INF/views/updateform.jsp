<%@page import="phonebook.PhonebookVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
PhonebookVO pb = (PhonebookVO)request.getAttribute("pb");    
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>전화번호부 입력 폼</h3>
<form action="/update" method="post">
<label>아이디:</label>
<input type="text" name="id" id="id" value=<%=pb.getId() %> readonly="readonly"><br>
<input type="hidden" name="id" id="id" value=<%=pb.getId() %>><br>
<label>이름:</label>
<input type="text" name="name" id="name" value=<%=pb.getName() %>><br>
<label>전화번호:</label>
<input type="text" name="hp" id="hp" value=<%=pb.getHp() %>><br>
<label>이메일:</label>
<input type="text" name="email" id="email" value=<%=pb.getEmail() %>><br>
<label>메모:</label>
<input type="text" name="memo" id="memo" value=<%=pb.getMemo() %>><br>
<input type="submit" value="전화번호부 수정"><br>
</form>
</body>
</html>