<%@ page contentType="text/html; UTF-8" %>
<jsp:include page="layout/header.jsp"/>
<h3>Welcome back! You are logged in already</h3>
<div>
    <a class="btn btn-primary" href="${pageContext.request.contextPath}/index.jsp">Go Back</a>
    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/logout">Logout</a>
</div>
<jsp:include page="layout/footer.jsp"/>