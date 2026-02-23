<%@page import="www2.Phonebook"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
//String name=(String)request.getAttribute("name");
Phonebook pb = (Phonebook)request.getAttribute("pb");
%>
이름:<%=pb.getName()%>
전화번호:<%=pb.getHp()%>
이메일:<%=pb.getEmail()%>
</body>
</html>