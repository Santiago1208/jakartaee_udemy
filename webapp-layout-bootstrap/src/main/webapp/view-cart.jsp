<%@ page contentType="text/html; UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cart</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</head>
<body>
    <div class="container">
        <h1>Cart</h1>
        <c:choose>
            <c:when test="${empty sessionScope.cart.cartItems}">
                <div class="alert alert-warning">Sorry, the cart is empty</div>
            </c:when>
            <c:otherwise>
                <form action="${pageContext.request.contextPath}/cart/update" method="post">
                    <table class="table table-striped table-hover">
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
                            <td colspan="5" style="text-align: right">Total</td>
                            <td>${sessionScope.cart.total}</td>
                        </tr>
                    </table>
                    <p><button class="btn btn-primary" type="submit">Update</button></p>
                </form>
            </c:otherwise>
        </c:choose>
        <div class="my-2">
            <a class="btn btn-sm btn-secondary" href="${pageContext.request.contextPath}/index.html">Go Back</a>
            <a class="btn btn-sm btn-success" href="${pageContext.request.contextPath}/products">Keep Buying</a>
        </div>
    </div>
</body>
</html>
