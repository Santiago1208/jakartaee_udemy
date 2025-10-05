<jsp:include page="layout/header.jsp"/>
<h3>${requestScope.title}</h3>
<ul class="list-group">
    <li class="list-group-item active">Menu</li>
    <li class="list-group-item"><a href="${pageContext.request.contextPath}/products">View as HTML</a></li>
    <li class="list-group-item"><a href="${pageContext.request.contextPath}/login.html">Login</a></li>
    <li class="list-group-item"><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
    <li class="list-group-item"><a href="${pageContext.request.contextPath}/cart/view">View Cart</a></li>
</ul>
<jsp:include page="layout/footer.jsp"/>
