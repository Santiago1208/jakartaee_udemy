<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
    <h2>Users</h2>
    <ul>
    <c:forEach items="${requestScope.users}" var="user">
        <li>${user.username}</li>
    </c:forEach>
    </ul>
</body>
</html>
