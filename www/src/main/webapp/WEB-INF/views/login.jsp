<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
    
    <!-- Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <style>
        body {
            background-color: #f8f9fa;
        }
        .login-card {
            width: 100%;
            max-width: 400px;
        }
    </style>
</head>
<body>
<c:if test="${empty username}">
<div class="container d-flex justify-content-center align-items-center vh-100">
    <div class="card shadow login-card">
        <div class="card-body p-4">
            
            <h3 class="text-center mb-4">로그인</h3>
            
            <form action="/login/login" method="post">
                
                <!-- Username -->
                <div class="mb-3">
                    <label for="username" class="form-label">아이디</label>
                    <input type="text" 
                           class="form-control" 
                           id="username" 
                           name="username" 
                           placeholder="아이디를 입력하세요" 
                           required>
                </div>
                
                <!-- Password -->
                <div class="mb-3">
                    <label for="password" class="form-label">비밀번호</label>
                    <input type="password" 
                           class="form-control" 
                           id="password" 
                           name="password" 
                           placeholder="비밀번호를 입력하세요" 
                           required>
                </div>
                
                <!-- Login Button -->
                <div class="d-grid mb-3">
                    <button type="submit" class="btn btn-primary">
                        로그인
                    </button>
                </div>
                
                <!-- Links -->
                <div class="text-center">
                    <a href="/login/register" class="text-decoration-none me-2">
                        회원가입
                    </a>
                    |
                    <a href="/login/find-account" class="text-decoration-none ms-2">
                        아이디/패스워드 찾기
                    </a>
                </div>
                
            </form>
            
        </div>
    </div>
</div>
</c:if>

<c:if test="${not empty username}">
[${username}]로그인 중
[<a href="/login/logout">로그아웃</a>]


<div>
<c:set var="timeout" value="${pageContext.session.maxInactiveInterval}"/>


<div>
    남은 시간: <span id="time"></span>
    <button onclick="handleExtend()">연장</button>
</div>

<script>
    var remainingTime = ${timeout};

    function updateTimer() {
    	console.log(remainingTime);
        let minutes = Math.floor(remainingTime / 60);
        let seconds = remainingTime % 60;
        document.getElementById("time").innerText =
            minutes + "분 " + seconds + "초";
        if (remainingTime <= 0) {
        	clearInterval(obj); //setInterval을 중지하는 역할
            alert("세션이 만료되었습니다.");
            location.href = "/login/login";
        }
        remainingTime--;
    }

    obj=setInterval(updateTimer, 1000);
</script>

<script>
function extendSession(callback) {
    console.log("extendSession 호출");

    fetch("/login/extendSession")
        .then(function(response) {
        	
            if (!response.ok) {
                throw new Error("서버 응답 오류");
            }
            console.log('test');
        	console.log(response);
        	remainingTime = 60;
            return response.text();
        })
        .then(function(data) {
            callback(null, data);   // 성공 시
        })
        .catch(function(error) {
            callback(error, null);  // 실패 시
        });
}
</script>

<script>
function handleExtend() {
    extendSession(function(error, data) {
    	if (data === "OK") {
            console.log("세션 연장 성공");
            remainingTime = 60; // 타이머 초기화
        }
    	
        if (error) {
            console.error("세션 연장 실패:", error);
            return;
        }

        
    });
}
</script>
</div>
</c:if>
<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>