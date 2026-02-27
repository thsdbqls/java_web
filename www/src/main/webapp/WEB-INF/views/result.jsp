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
System.out.println(request.getAttribute("result"));
%>
<%= request.getAttribute("result") %>
<script>

if(<%=request.getAttribute("result")%> == "register ok"){
	alert("전화번호부 입력 성공!!");
}else if(<%=request.getAttribute("result")%> == "register fail"){
	alert("전화번호부 입력 실패!!");
}
location.href="/views"
</script>
</body>
</html>