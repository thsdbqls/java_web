<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    session.setMaxInactiveInterval(60); // 다시 1분으로 설정
    out.print("OK");
%>