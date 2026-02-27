<%@page import="phonebookv2.PhonebookVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
PhonebookVO pb=(PhonebookVO)request.getAttribute("pb");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
<h3>전화번호부 수정 폼</h3>
<form action="/v2/update" method="post">
<lable>아이디:</lable><input type="text" name="id" id="id" value=<%=pb.getId()%> readonly="readonly"><br>
<input type="hidden" name="id" id="id" value=<%=pb.getId()%> >
<lable>이름:</lable><input type="text" name="name" id="name" value=<%=pb.getName()%>><br>
<lable>전화번호:</lable><input type="text" name="hp" id="hp" value=<%=pb.getHp() %>><br>
<lable>이메일:</lable><input type="text" name="email" id="email" value=<%=pb.getEmail()%>><br>
<lable>메모:</lable><input type="text" name="memo" id="memo" value=<%=pb.getMemo()%>><br>
<input type="submit" value="전화번호부 수정">
</form>
</div>
</body>
</html>