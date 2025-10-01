<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
</head>
<body>
<h2>회원가입</h2>
<form action="/members/signup" method="post">
    <label>아이디:</label>
    <input type="text" name="username" required><br>
    <label>비밀번호:</label>
    <input type="password" name="password" required><br>
    <button type="submit">회원가입</button>
</form>
<a href="/members/login">로그인</a>
</body>
</html>
