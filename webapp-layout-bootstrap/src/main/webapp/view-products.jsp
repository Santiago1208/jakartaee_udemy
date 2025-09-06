<%@ page contentType="text/html; UTF-8" %>
<%@ taglib prefix="c"  uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View Products</title>
</head>
<body>
    <h1>View Products</h1>
    <c:if test="${requestScope.username.present}">
        <div>Welcome ${requestScope.username.get()}!</div>
        <p><a href="${pageContext.request.contextPath}/product/add">[+] New Product</a></p>
    </c:if>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Type</th>
            <c:if test="${requestScope.username.present}">
                <th>Price</th>
                <th>Add</th>
                <th>Edit</th>
                <th>Delete</th>
            </c:if>
        </tr>
        <c:forEach items="${requestScope.products}" var="p">
            <tr>
                <td>${p.id}</td>
                <td>${p.name}</td>
                <td>${p.category.name}</td>
                <c:if test="${requestScope.username.present}">
                    <td>${p.price}</td>
                    <td><a href="${pageContext.request.contextPath}/cart/add?id=${p.id}">Add to Cart</a></td>
                    <td><a href="${pageContext.request.contextPath}/product/add?id=${p.id}">Edit Product</a></td>
                    <td><a href="${pageContext.request.contextPath}/product/delete?id=${p.id}"
                           onclick="return confirm('Are you sure you want to delete this record?')">Delete Product</a></td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <p>${applicationScope.globalMessage}</p>
    <p>${requestScope.requestMessage}</p>
</body>
</html>
