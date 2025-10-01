<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>글 작성</title>
</head>
<body>
<h2>글 작성</h2>
<form action="/boards/create" method="post">
    <label>제목:</label>
    <input type="text" name="title" required><br>
    <label>작성자:</label>
    <input type="text" name="writer" required><br>
    <label>내용:</label><br>
    <textarea name="content" rows="5" cols="30" required></textarea><br>
    <button type="submit">작성</button>
</form>
<a href="/boards">목록으로</a>
</body>
</html>
