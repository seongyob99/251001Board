<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인</title>
</head>
<body>
<h2>로그인</h2>
<form action="/members/login" method="post">
    <label>아이디:</label>
    <input type="text" name="username" required><br>
    <label>비밀번호:</label>
    <input type="password" name="password" required><br>
    <button type="submit">로그인</button>
</form>
<a href="/members/signup">회원가입</a>
</body>
</html>
