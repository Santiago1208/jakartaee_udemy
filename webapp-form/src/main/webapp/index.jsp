<%@page contentType="text/html" pageEncoding="utf-8" %>
<%@page import="java.util.Map" %>

<%
    Map<String, String> errors = (Map<String, String>) request.getAttribute("errors");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Form</title>
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<h3>User Form Example</h3>

<%if (errors != null && !errors.isEmpty()) {%>
<ul class="alert alert-danger mx-5">
    <%for (String error : errors.values()) { %>
    <li><%=error%></li>
    <%}%>
</ul>
<%}%>
<div class="px-5">
    <form action="/webapp-form/register" method="post">
        <div class="row mb-3">
            <label for="username" class="col-form-label col-sm-2">Username:</label>
            <div class="col-sm-4"><input type="text" name="username" id="username" autofocus class="form-control"></div>
        </div>
        <%if (errors != null && errors.containsKey("username")) {%>
        <div class="row mb-3 alert alert-danger col-sm-4"><%=errors.get("username")%></div>
        <%}%>
        <div class="row mb-3">
            <label for="password" class="col-form-label col-sm-2">Password:</label>
            <div class="col-sm-4"><input type="password" name="password" id="password" class="form-control"></div>
        </div>
        <%if (errors != null && errors.containsKey("password")) {%>
        <div class="row mb-3 alert alert-danger col-sm-4"><%=errors.get("password")%>
        </div>
        <%}%>
        <div class="row mb-3">
            <label for="email" class="col-form-label col-sm-2">E-Mail:</label>
            <div class="col-sm-4"><input type="email" name="email" id="email" class="form-control"></div>
        </div>
        <%if (errors != null && errors.containsKey("email")) {%>
        <div class="row mb-3 alert alert-danger col-sm-4"><%=errors.get("email")%>
        </div>
        <%}%>
        <div class="row mb-3">
            <label for="country" class="col-form-label col-sm-2">Country:</label>
            <div class="col-sm-4">
                <select name="country" id="country" class="form-select">
                    <option value="">-- Select one --</option>
                    <option value="ES">Spain</option>
                    <option value="MX">Mexico</option>
                    <option value="CL">Chile</option>
                    <option value="AR">Argentina</option>
                    <option value="PE">Peru</option>
                    <option value="CO" selected>Colombia</option>
                    <option value="VE">Venezuela</option>
                </select>
            </div>
        </div>
        <%if (errors != null && errors.containsKey("country")) {%>
        <div class="row mb-3 alert alert-danger col-sm-4"><%=errors.get("country")%>
        </div>
        <%}%>
        <div class="row mb-3">
            <label for="programming" class="col-form-label col-sm-2">Programming Languages:</label>
            <div class="col-sm-4">
                <select name="programming" id="programming" multiple class="form-select">
                    <option value="java" selected>Java SE</option>
                    <option value="jakartaee" selected>Jakarta EE 9</option>
                    <option value="spring">Spring Boot</option>
                    <option value="js">JavaScript</option>
                    <option value="angular">Angular</option>
                    <option value="react" selected>React</option>
                </select>
            </div>
        </div>
        <%if (errors != null && errors.containsKey("country")) {%>
        <div class="row mb-3 alert alert-danger col-sm-4"><%=errors.get("country")%>
        </div>
        <%}%>
        <div class="row mb-3">
            <label class="col-form-label col-sm-2">Roles:</label>
            <div class="form-check">
                <input type="checkbox" name="roles" id="role-admin" value="ROLE_ADMIN" class="form-check-input">
                <label for="role-admin" class="form-check-label">Administrator</label>
            </div>
            <div class="form-check">
                <input type="checkbox" name="roles" id="role-user" value="ROLE_USER" checked class="form-check-input">
                <label for="role-user" class="form-check-label">User</label>
            </div>
            <div class="form-check">
                <input type="checkbox" name="roles" id="role-moderator" value="ROLE_MODERATOR" class="form-check-input">
                <label for="role-moderator" class="form-check-label">Moderator</label>
            </div>
        </div>
        <%if (errors != null && errors.containsKey("roles")) {%>
        <div class="row mb-3 alert alert-danger col-sm-4"><%=errors.get("roles")%>
        </div>
        <%}%>
        <div class="row mb-3">
            <label class="col-form-label col-sm-2">Idioms:</label>
            <div class="form-check">
                <input type="radio" name="idiom" id="spanish" value="es" class="form-check-input">
                <label for="spanish" class="form-check-label">Spanish</label>
            </div>
            <div class="form-check">
                <input type="radio" name="idiom" id="english" value="en" class="form-check-input">
                <label for="english" class="form-check-label">English</label>
            </div>
            <div class="form-check">
                <input type="radio" name="idiom" id="french" value="fr" class="form-check-input">
                <label for="french" class="form-check-label">French</label>
            </div>
        </div>
        <%if (errors != null && errors.containsKey("idiom")) {%>
        <div class="row mb-3 alert alert-danger col-sm-4"><%=errors.get("idiom")%>
        </div>
        <%}%>
        <div class="row mb-3">
            <label for="enable" class="col-form-label col-sm-2">Enable:</label>
            <div class="form-check">
                <input type="checkbox" name="enable" id="enable" checked class="form-check-input">
            </div>
        </div>
        <div class="row mb-3">
            <div>
                <input type="submit" value="Save" class="btn btn-primary">
            </div>
        </div>
        <input type="hidden" name="secret" value="12345">
    </form>
</div>
</body>
</html>