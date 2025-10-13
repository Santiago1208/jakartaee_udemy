<%@ page contentType="text/html; UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="layout/header.jsp"/>
<h3>${requestScope.title}</h3>
<c:if test="${requestScope.username.present && requestScope.username.get().equals('admin')}">
    <p><a class="btn btn-primary" href="${pageContext.request.contextPath}/users/add">[+] New</a></p>
</c:if>
<table class="table table-striped table-hover">
    <tr>
        <th>ID</th>
        <th>Username</th>
        <th>E-mail</th>
        <c:if test="${requestScope.username.present && requestScope.username.get().equals('admin')}">
            <th>Edit</th>
            <th>Delete</th>
        </c:if>
    </tr>
    <c:forEach items="${requestScope.users}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.email}</td>
            <c:if test="${requestScope.username.present && requestScope.username.get().equals('admin')
                            && !user.username.equals('admin')}">
                <td><a class="btn btn-sm btn-success" href="${pageContext.request.contextPath}/users/add?id=${user.id}">Edit User</a></td>
                <td><a class="btn btn-sm btn-danger" href="${pageContext.request.contextPath}/users/delete?id=${user.id}"
                    onclick="return confirm('Are you sure you want to delete this user?')">Delete User</a></td>
            </c:if>
        </tr>
    </c:forEach>
</table>
<jsp:include page="layout/footer.jsp"/>
