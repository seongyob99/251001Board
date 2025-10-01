<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시판 리스트</title>
</head>
<body>
<h2>게시판</h2>
<a href="/boards/create">글 작성</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>제목</th>
        <th>작성자</th>
        <th>조회</th>
        <th>상세</th>
    </tr>
    <c:forEach var="b" items="${boards}">
        <tr>
            <td>${b.id}</td>
            <td>${b.title}</td>
            <td>${b.writer}</td>
            <td>${b.view}</td>
            <td><a href="/boards/${b.id}">보기</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
