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
</head>
<body>
<h3>User Form Example</h3>

<%if (errors != null && !errors.isEmpty()) {%>
<ul>
  <%for (String error : errors.values()) { %>
  <li><%=error%></li>
  <%}%>
</ul>
<%}%>
<form action="/webapp-form/register" method="post">
  <div>
    <label for="username">Username:</label>
    <div><input type="text" name="username" id="username" autofocus></div>
    <%if(errors != null && errors.containsKey("username")) {%>
    <small style="color: red"><%=errors.get("username")%></small>
    <%}%>
  </div>
  <div>
    <label for="password">Password:</label>
    <div><input type="password" name="password" id="password"></div>
    <%if(errors != null && errors.containsKey("password")) {%>
    <small style="color: red"><%=errors.get("password")%></small>
    <%}%>
  </div>
  <div>
    <label for="email">E-Mail:</label>
    <div><input type="email" name="email" id="email"></div>
    <%if(errors != null && errors.containsKey("email")) {%>
    <small style="color: red"><%=errors.get("email")%></small>
    <%}%>
  </div>
  <div>
    <label for="country">Country:</label>
    <div>
      <select name="country" id="country">
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
    <%if(errors != null && errors.containsKey("country")) {%>
    <small style="color: red"><%=errors.get("country")%></small>
    <%}%>
  </div>
  <div>
    <label for="programming">Programming Languages:</label>
    <div>
      <select name="programming" id="programming" multiple>
        <option value="java" selected>Java SE</option>
        <option value="jakartaee" selected>Jakarta EE 9</option>
        <option value="spring">Spring Boot</option>
        <option value="js">JavaScript</option>
        <option value="angular">Angular</option>
        <option value="react" selected>React</option>
      </select>
    </div>
    <%if(errors != null && errors.containsKey("country")) {%>
    <small style="color: red"><%=errors.get("country")%></small>
    <%}%>
  </div>
  <div>
    <label>Roles:</label>
    <div>
      <input type="checkbox" name="roles" id="role-admin" value="ROLE_ADMIN">
      <label for="role-admin">Administrator</label>
    </div>
    <div>
      <input type="checkbox" name="roles" id="role-user" value="ROLE_USER" checked>
      <label for="role-user">User</label>
    </div>
    <div>
      <input type="checkbox" name="roles" id="role-moderator" value="ROLE_MODERATOR">
      <label for="role-moderator">Moderator</label>
    </div>
    <%if(errors != null && errors.containsKey("roles")) {%>
    <small style="color: red"><%=errors.get("roles")%></small>
    <%}%>
  </div>
  <div>
    <label>Idioms:</label>
    <div>
      <input type="radio" name="idiom" id="spanish" value="es">
      <label for="spanish">Spanish</label>
    </div>
    <div>
      <input type="radio" name="idiom" id="english" value="en">
      <label for="english">English</label>
    </div>
    <div>
      <input type="radio" name="idiom" id="french" value="fr">
      <label for="french">French</label>
    </div>
    <%if(errors != null && errors.containsKey("idiom")) {%>
    <small style="color: red"><%=errors.get("idiom")%></small>
    <%}%>
  </div>
  <div>
    <label for="enable">Enable:</label>
    <div>
      <input type="checkbox" name="enable" id="enable" checked>
    </div>
  </div>
  <div>
    <div>
      <input type="submit" value="Save">
    </div>
  </div>
  <input type="hidden" name="secret" value="12345">
</form>
</body>
</html>