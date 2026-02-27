<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>로그인</title>

    <!-- Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<%if(request.getSession().getAttribute("username")==null){ %>
<div class="container d-flex justify-content-center align-items-center vh-100">
    <div class="card shadow p-4" style="width: 400px;">
        <h3 class="text-center mb-4">로그인</h3>

        <form action="/jsp/login_proc.jsp" method="post">
            
            <!-- 아이디 -->
            <div class="mb-3">
                <label for="username" class="form-label">아이디</label>
                <input type="text" 
                       class="form-control" 
                       id="username" 
                       name="username" 
                       placeholder="아이디를 입력하세요" 
                       required>
            </div>

            <!-- 비밀번호 -->
            <div class="mb-3">
                <label for="password" class="form-label">비밀번호</label>
                <input type="password" 
                       class="form-control" 
                       id="password" 
                       name="password" 
                       placeholder="비밀번호를 입력하세요" 
                       required>
            </div>

            <!-- 로그인 버튼 -->
            <div class="d-grid mb-3">
                <button type="submit" class="btn btn-primary">로그인</button>
            </div>

            <!-- 링크 영역 -->
            <div class="text-center">
                <a href="/jsp/member.jsp" class="text-decoration-none">회원가입</a>
                <span class="mx-2">|</span>
                <a href="/find-username" class="text-decoration-none">아이디 찾기</a>
                <span class="mx-2">|</span>
                <a href="/find-password" class="text-decoration-none">비밀번호 찾기</a>
            </div>

        </form>
    </div>
</div>
<%}else{ %> 
[<%=request.getSession().getAttribute("username") %>]로그인 중
[<a href="/jsp/logout.jsp">로그아웃</a>]
<div>
<%
    session.setMaxInactiveInterval(60); // 1분
    int timeout = session.getMaxInactiveInterval();
%>
<div class="text-end me-3">
    남은 시간: <span id="timer"></span>
    <!-- <button class="btn btn-sm btn-warning ms-2" onclick="extendSession()">연장</button> -->
    <button class="btn btn-sm btn-warning ms-2" onclick="handleExtend()">연장</button>
</div>

<script>
    let timeLeft = <%=timeout%>; // 서버에서 받은 세션 시간

    const timerElement = document.getElementById("timer");

    function updateTimer() {
    	console.log(timeLeft);
        let minutes = Math.floor(timeLeft / 60);
        let seconds = timeLeft % 60;

        timerElement.innerText = minutes + "분 " + seconds + "초";

        if (timeLeft <= 0) {
            alert("세션이 만료되었습니다. 다시 로그인해주세요.");
            location.href = "/jsp/login.jsp";
        }

        timeLeft--;
    }

    setInterval(updateTimer, 1000);
    updateTimer();
</script>

<script>/*
function extendSession() {
	// fetch then은 순차처리(동기화 상태이다)
    fetch("/jsp/extend.jsp")
    .then(response => {
        //alert("세션이 1분 연장되었습니다.");
        timeLeft = 60; // 화면 타이머도 리셋
        console.log(response);
    });
}*/
</script>


<script>

function extendSession(callback) {

    fetch("/jsp/extend.jsp", {
        method: "GET",
        cache: "no-cache"
    })
    .then(function(response) {

        if (!response.ok) {
            throw new Error("서버 응답 오류");
        }

        console.log('test');
        console.log(response);
        timeLeft = 60;
        return response.text();

    })
    .then(function(data) {

        // 성공 시 콜백 실행
        if (callback) {
            callback(null, data);
        }

    })
    .catch(function(error) {

        // 실패 시 콜백 실행
        if (callback) {
            callback(error, null);
        }

    });
}

</script>
<script>
function handleExtend() {

    extendSession(function(error, data) {

        if (error) {
            console.error("세션 연장 실패:", error);
            alert("세션 연장 실패");
            return;
        }

        // 성공 처리
        timeLeft = 60;
        console.log("세션 연장 성공");

    });

}
</script>
</div>

<%}%>
<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>