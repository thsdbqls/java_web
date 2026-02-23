<%@page import="phonebookv2.PhonebookVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%PhonebookVO pb=(PhonebookVO)request.getAttribute("pb"); %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>상세보기 페이지</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f9;
        }

        .container {
            width: 80%;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            color: #333;
        }

        .detail {
            display: flex;
            flex-direction: column;
            gap: 15px;
            margin-top: 20px;
        }

        .detail div {
            display: flex;
            justify-content: space-between;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        .detail div span {
            font-weight: bold;
        }

        .note {
            font-style: italic;
            color: #666;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        .back-button {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007BFF;
            color: #fff;
            text-align: center;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 20px;
        }

        .back-button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

    <div class="container">
        <h1>상세보기</h1>
        <div class="detail">
        	<div>
        		<!-- 만약에 사진이 있으면 사진을 나타내고 없으면 기본 이미지 적용 -->
        		<%if(pb.getPic()!=null){ %>
                <ing src="/fileupload/<%=pb.getPic()%>" alt="image" width="100px">
                <%=pb.getPic()%>
                <%} else{ %>
                <ing src="/fileupload/default.png" alt="image" width="100px">
                <%}%>
            </div>
            <div>
                <span>아이디:</span>
                <span><%=pb.getId() %></span>
            </div>
            <div>
                <span>이름:</span>
                <span><%=pb.getName() %></span>
            </div>
            <div>
                <span>전화번호:</span>
                <span><%=pb.getHp()%></span>
            </div>
            <div>
                <span>이메일:</span>
                <span><%=pb.getEmail() %></span>
            </div>
            <div>
                <span>메모:</span>
                <span class="note"><%=pb.getMemo() %></span>
            </div>
        </div>
        <a href="/v2/views" class="back-button">뒤로가기</a>
        <a href="/v2/updateform?id=<%=pb.getId()%>" class="back-button">수정</a>
        <a href="javascript:deleteconfirm('<%=pb.getId()%>')" class="back-button">삭제</a>
    </div>
    <script>
    function deleteconfirm(id){
    	//confirm("정말 삭제하시겠습니까?");
    	console.log(id);
    	let result = confirm("정말 삭제하시겠습니까?");
    	//console.log(result);
    	console.log(`/v2/delete?id=\${id}`);
    	if(result){
    		alert('해당 전화번호부를 삭제했습니다');
    		location.href=`/v2/delete?id=\${id}`
    	}
    }
    </script>

</body>
</html>