<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>

    <!-- Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container d-flex justify-content-center align-items-center vh-100">
    <div class="card shadow p-4" style="width: 500px;">
        <h3 class="text-center mb-4">회원가입</h3>

        <form action="/jsp/member_proc.jsp" method="post">

            <!-- 아이디 -->
            <div class="mb-3">
                <label class="form-label">아이디</label>
                <input type="text" class="form-control"
                       name="username"
                       placeholder="아이디 입력"
                       required>
            </div>

            <!-- 비밀번호 -->
            <div class="mb-3">
                <label class="form-label">비밀번호</label>
                <input type="password" class="form-control"
                       name="password"
                       placeholder="비밀번호 입력"
                       required>
            </div>

            <!-- 이메일 -->
            <div class="mb-3">
                <label class="form-label">이메일</label>
                <input type="email" class="form-control"
                       name="email"
                       placeholder="이메일 입력"
                       required>
            </div>

            <!-- 휴대폰 번호 -->
            <div class="mb-3">
                <label class="form-label">휴대폰 번호</label>
                <input type="text" class="form-control"
                       name="hp"
                       placeholder="010-0000-0000">
            </div>

            <!-- 닉네임 -->
            <div class="mb-3">
                <label class="form-label">닉네임</label>
                <input type="text" class="form-control"
                       name="nickname"
                       placeholder="닉네임 입력">
            </div>

            <!-- 버튼 -->
            <div class="d-grid mb-3">
                <button type="submit" class="btn btn-primary">회원가입</button>
            </div>

            <div class="text-center">
                <a href="login.jsp" class="text-decoration-none">이미 계정이 있으신가요? 로그인</a>
            </div>

        </form>
    </div>
</div>

</body>
</html>