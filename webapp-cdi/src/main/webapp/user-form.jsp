<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="layout/header.jsp"/>
<h3>${requestScope.title}</h3>
<form action="${pageContext.request.contextPath}/users/add" method="post">
    <input type="hidden" name="id" value="${requestScope.user.id}">
    <div class="row mb-2">
        <label class="col-form-label col-sm-2" for="username">Username:</label>
        <div class="col-sm-4">
            <input class="form-control" type="text" name="username" id="username" value="${requestScope.user.username}">
        </div>
        <c:if test="${requestScope.errors != null && !empty requestScope.errors.username}">
            <div style="color: red;">
                    ${requestScope.errors.username}
            </div>
        </c:if>
    </div>
    <div class="row mb-2">
        <label class="col-form-label col-sm-2" for="password">Password:</label>
        <div class="col-sm-4">
            <input class="form-control" type="password" name="password" id="password">
        </div>
        <c:if test="${requestScope.errors != null && !empty requestScope.errors.password}">
            <div style="color: red;">
                    ${requestScope.errors.password}
            </div>
        </c:if>
    </div>
    <div class="row mb-2">
        <label class="col-form-label col-sm-2" for="email">E-mail:</label>
        <div class="col-sm-4">
            <input class="form-control" type="email" name="email" id="email" value="${requestScope.user.email}">
        </div>
        <c:if test="${requestScope.errors != null && !empty requestScope.errors.email}">
            <div style="color: red;">
                    ${requestScope.errors.email}
            </div>
        </c:if>
    </div>
    <div class="row mb-2">
        <div>
            <input class="btn btn-primary" type="submit" value="Save">
            <a class="btn btn-danger" href="${pageContext.request.contextPath}/users"
                onclick="return confirm('Are you sure you want to leave?')">Cancel</a>
        </div>
    </div>
</form>
<jsp:include page="layout/footer.jsp"/>
