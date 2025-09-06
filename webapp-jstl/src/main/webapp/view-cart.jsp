<%@ page contentType="text/html; UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cart</title>
</head>
<body>
    <h1>Cart</h1>
    <c:choose>
        <c:when test="${empty sessionScope.cart.cartItems}">
            <p>Sorry, the cart is empty</p>
        </c:when>
        <c:otherwise>
            <form action="${pageContext.request.contextPath}/cart/update" method="post">
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Amount</th>
                        <th>Total</th>
                        <th>Delete</th>
                    </tr>
                    <c:forEach items="${sessionScope.cart.cartItems}" var="item">
                    <tr>
                        <td>${item.product.id}</td>
                        <td>${item.product.name}</td>
                        <td>${item.product.price}</td>
                        <td><input type="text" name="amount" value="${item.amount}" /></td>
                        <td>${item.lineTotal}</td>
                        <td><input type="checkbox" name="delete" value="${item.product.id}" /></td>
                    </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="4" style="text-align: right">Total</td>
                        <td>${sessionScope.cart.total}</td>
                    </tr>
                </table>
                <p><button type="submit">Update</button></p>
            </form>
        </c:otherwise>
    </c:choose>
    <p><a href="${pageContext.request.contextPath}/products">Keep Buying</a></p>
    <p><a href="${pageContext.request.contextPath}/index.html">Go Back</a></p>
</body>
</html>
