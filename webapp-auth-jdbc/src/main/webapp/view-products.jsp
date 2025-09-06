<%@ page contentType="text/html; UTF-8" %>
<%@ taglib prefix="c"  uri="jakarta.tags.core" %>
<jsp:include page="layout/header.jsp"/>
<h3>${requestScope.title}</h3>
<c:if test="${requestScope.username.present}">
    <div class="alert alert-info">Welcome ${requestScope.username.get()}!</div>
    <p><a class="btn btn-primary" href="${pageContext.request.contextPath}/product/add">[+] New Product</a></p>
</c:if>
<table class='table table-striped table-hover'>
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
                <td><a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/cart/add?id=${p.id}">Add to Cart</a></td>
                <td><a class="btn btn-sm btn-success" href="${pageContext.request.contextPath}/product/add?id=${p.id}">Edit Product</a></td>
                <td><a class="btn btn-sm btn-danger" href="${pageContext.request.contextPath}/product/delete?id=${p.id}"
                       onclick="return confirm('Are you sure you want to delete this record?')">Delete Product</a></td>
            </c:if>
        </tr>
    </c:forEach>
</table>
<p>${applicationScope.globalMessage}</p>
<p>${requestScope.requestMessage}</p>
<jsp:include page="layout/footer.jsp"/>