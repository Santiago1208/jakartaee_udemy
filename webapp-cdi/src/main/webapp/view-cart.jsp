<%@ page contentType="text/html; UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="layout/header.jsp"/>
<h3>${requestScope.title}</h3>
<c:choose>
    <%--@elvariable id="cart" type="org.srestrepo.apiservlet.webapp.cdi.models.Cart"--%>
    <c:when test="${empty cart.cartItems}">
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
                <c:forEach items="${cart.cartItems}" var="item">
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
                    <td>${cart.total}</td>
                </tr>
            </table>
            <p><button class="btn btn-primary" type="submit">Update</button></p>
        </form>
    </c:otherwise>
</c:choose>
<div class="my-2">
    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/index.jsp">Go Back</a>
    <a class="btn btn-success" href="${pageContext.request.contextPath}/products">Keep Buying</a>
</div>
<jsp:include page="layout/footer.jsp"/>
