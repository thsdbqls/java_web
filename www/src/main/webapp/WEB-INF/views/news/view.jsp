<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
<style>
img{
	border-radius: 10px;
	vertical-align: top;
}
.title{

}
.pressname{
}
.gisa{
display:flex;
border:1px solid gray;
margin:10px;
padding : 10px;
border-radius:10px;
}
.innergisa{
display:flex;
flex-direction: column;
padding-left : 10px;
}
</style>
</head>
<body>
<c:if test="${not empty news}">

<c:url var="viewurl" value="/news/view">
    <c:param name="id" value="${news.id}" />
</c:url>

<div class="gisa" onclick="location.href='${viewurl}'">

<c:url var="imgurl" value="${news.mainImage}" />
<img src="${imgurl}" width="180px" height="200px" style="object-fit:cover">

<div class="innergisa">
<span class="title">${news.title}</span>

<div>
<i class="fa-solid fa-circle-user"></i>
<span class="pressname">${news.reporterName}</span>
</div>

<div>
<i class="fa-solid fa-circle-user"></i>
<span class="pressname">${news.pressName}</span>
</div>

<div>
<span class="pressname">${news.content}</span>
</div>
<div>
<span class="pressname">${news.createdAt}</span>
</div>
<div>
<span class="pressname">${news.updatedAt}</span>
</div>

</div>
</div>
</c:if>
</body>
</html>