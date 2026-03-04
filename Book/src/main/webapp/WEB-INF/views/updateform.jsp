<%@page import="Book.BookVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
BookVO pb=(BookVO)request.getAttribute("pb");
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
<form action="/book/update" method="post">
<lable>책 아이디:</lable><input type="text" name="isbn" id="isbn" readonly="readonly"><br>
<input type="hidden" name="id" id="id"  >
<lable>책 이름:</lable><input type="text" name="bookname" id="bookname"><br>
<lable>저자:</lable><input type="text" name="author" id="author"><br>
<lable>출판사:</lable><input type="text" name="publisher" id="publisher"><br>
<lable>이미지 주소:</lable><input type="text" name="image" id="image"><br>
<input type="submit" value="전화번호부 수정">
</form>
</div>
</body>
</html>