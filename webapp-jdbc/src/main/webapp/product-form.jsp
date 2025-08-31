<%@ page import="java.util.List" %>
<%@ page import="org.srestrepo.apiservlet.webapp.jdbc.models.Category" %>
<%@ page contentType="text/html; UTF-8" %>

<%
    List<Category> categories = (List<Category>) request.getAttribute("categories");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Product</title>
</head>
<body>
    <h1>Add Product</h1>
    <form action="<%=request.getContextPath()%>/product/add" method="post">
        <div>
            <label for="name">Name:</label>
            <div>
                <input type="text" name="name" id="name">
            </div>
        </div>
        <div>
            <label for="price">Price:</label>
            <div>
                <input type="number" name="price" id="price">
            </div>
        </div>
        <div>
            <label for="sku">SKU:</label>
            <div>
                <input type="text" name="sku" id="sku">
            </div>
        </div>
        <div>
            <label for="createdAt">Created At:</label>
            <div>
                <input type="date" name="createdAt" id="createdAt">
            </div>
        </div>
        <div>
            <label for="category">Category:</label>
            <div>
                <select name="category" id="category">
                    <option value="">------- Select -------</option>
                    <%for (Category c : categories) {%>
                        <option value="<%= c.getId() %>"><%= c.getName() %></option>
                    <%}%>
                </select>
            </div>
        </div>
        <div>
            <input type="submit" value="Save">
        </div>
    </form>
</body>
</html>