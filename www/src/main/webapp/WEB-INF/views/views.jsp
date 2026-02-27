<%@page import="phonebookv2.PhonebookVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<%
if(request.getAttribute("result")==null){}
else if(request.getAttribute("result").equals("delete success")){
//삭제 성공시 script를 이용하여 알람표시
%>
<script>alert("전화번호부 삭제 성공!!");</script>
<%
}else if(request.getAttribute("result").equals("delete fail")){
%>
<script>alert("전화번호부 삭제 실패!!");</script>
<%	
}else if(request.getAttribute("result").equals("insert success")){
%>
<script>alert("전화번호부 입력 성공!!");</script>
<%	
}else if(request.getAttribute("result").equals("insert fail")){
%>
<script>alert("전화번호부 입력 실패!!");</script>
<%	
}else if(request.getAttribute("result").equals("update success")){
%>
<script>alert("전화번호부 수정 성공!!");</script>
<%
}else if(request.getAttribute("result").equals("update fail")){
%>
<script>alert("전화번호부 수정 실패!!");</script>
<%
}
%>
<%
List<PhonebookVO> list=(List<PhonebookVO>)request.getAttribute("list");
%>
<div class="container mt-3">
<button onclick="location.href='/v2/insertform'">전화번호부 입력</button>
<table class="table table-hover">
<thead>
<tr><th>아이디</th><th>이름</th><th>전화번호</th><th>이메일 주소</th></tr>
</thead>
<tbody>


<%for(PhonebookVO pb:list){ %>
<tr onclick="location.href='/v2/view?id=<%=pb.getId()%>'">
<td><%=pb.getId()%></td>
<td><%=pb.getName()%></td>
<td><%=pb.getHp()%></td>
<td><%=pb.getEmail()%></td>
</tr>
<%}%>


</tbody>
</table>
</div>
</body>
</html>