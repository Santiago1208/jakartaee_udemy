<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Products</title>
</head>
<body>
    <h2>Products</h2>
    <ul>
    <c:forEach items="${requestScope.products}" var="p">
        <li>${p.name}</li>
    </c:forEach>
    </ul>
</body>
</html>
