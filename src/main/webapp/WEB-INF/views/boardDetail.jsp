<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시글 상세</title>
</head>
<body>
<h2>${board.title}</h2>
<p>작성자: ${board.writer}</p>
<p>내용: ${board.content}</p>
<p>조회수: ${board.view}</p>

<a href="/boards/${board.id}/edit">수정</a>
<form action="/boards/${board.id}/delete" method="post" style="display:inline;">
    <button type="submit">삭제</button>
</form>
<a href="/boards">목록으로</a>
</body>
</html>
