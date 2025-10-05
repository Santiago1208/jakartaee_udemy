<%@page contentType="text/html; UTF-8" %>
<jsp:include page="layout/header.jsp"/>
<h3>${requestScope.title}</h3>
<form action="${pageContext.request.contextPath}/login" method="post">
  <div class="row my-2">
    <label for="username" class="form-label">Username:</label>
    <div>
      <input class="form-control" type="text" name="username" id="username" autofocus>
    </div>
  </div>
  <div class="row my-2">
    <label for="password" class="form-label">Password:</label>
    <div>
      <input class="form-control" type="password" name="password" id="password">
    </div>
  </div>
  <div class="row my-2">
      <div>
        <input type="submit" class="btn btn-primary" value="Login">
      </div>
  </div>
</form>
<jsp:include page="layout/footer.jsp"/>
