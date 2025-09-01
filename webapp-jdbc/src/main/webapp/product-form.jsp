<%@ page import="java.util.List" %>
<%@ page import="org.srestrepo.apiservlet.webapp.jdbc.models.Category" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.srestrepo.apiservlet.webapp.jdbc.models.Product" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html; UTF-8" %>

<%
    List<Category> categories = (List<Category>) request.getAttribute("categories");
    Map<String, String> errors = (Map<String, String>) request.getAttribute("errors");
    Product beingEdited = (Product) request.getAttribute("product");
    String createdAt = beingEdited.getCreatedAt() != null ? beingEdited.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><%=beingEdited.getId() != null && beingEdited.getId() > 0L ? "Edit" : "Add"%> Product</title>
</head>
<body>
    <h1><%=beingEdited.getId() != null && beingEdited.getId() > 0L ? "Edit" : "Add"%> Product</h1>
    <form action="<%=request.getContextPath()%>/product/add" method="post">
        <input type="hidden" name="id" value="<%= beingEdited.getId() %>">
        <div>
            <label for="name">Name:</label>
            <div>
                <input type="text" name="name" id="name" value="<%=beingEdited.getName() != null ? beingEdited.getName() : ""%>">
            </div>
            <%if (errors != null && errors.containsKey("name")) {%>
                <div style="color: red;">
                    <%=errors.get("name")%>
                </div>
            <%}%>
        </div>
        <div>
            <label for="price">Price:</label>
            <div>
                <input type="number" name="price" id="price" value="<%=beingEdited.getPrice()%>">
            </div>
            <%if (errors != null && errors.containsKey("price")) {%>
                <div style="color: red;">
                    <%=errors.get("price")%>
                </div>
            <%}%>
        </div>
        <div>
            <label for="sku">SKU:</label>
            <div>
                <input type="text" name="sku" id="sku" value="<%=beingEdited.getSku() != null ? beingEdited.getSku() : ""%>">
            </div>
            <%if (errors != null && errors.containsKey("sku")) {%>
                <div style="color: red;">
                    <%=errors.get("sku")%>
                </div>
            <%}%>
        </div>
        <div>
            <label for="createdAt">Created At:</label>
            <div>
                <input type="date" name="createdAt" id="createdAt" value="<%=createdAt%>">
            </div>
            <%if (errors != null && errors.containsKey("createdAt")) {%>
                <div style="color: red;">
                    <%=errors.get("createdAt")%>
                </div>
            <%}%>
        </div>
        <div>
            <label for="category">Category:</label>
            <div>
                <select name="category" id="category">
                    <option value="">------- Select -------</option>
                    <%for (Category c : categories) {%>
                        <option value="<%= c.getId() %>" <%=c.getId().equals(beingEdited.getCategory().getId()) ? "selected" : ""%>><%= c.getName() %></option>
                    <%}%>
                </select>
            </div>
            <%if (errors != null && errors.containsKey("category")) {%>
                <div style="color: red;">
                    <%=errors.get("category")%>
                </div>
            <%}%>
        </div>
        <div>
            <input type="submit" value="Save">
        </div>
    </form>
</body>
</html>