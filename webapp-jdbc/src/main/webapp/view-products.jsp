<%@ page contentType="text/html; UTF-8" %>
<%@ page import="org.srestrepo.apiservlet.webapp.jdbc.models.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Optional" %>
<%
    List<Product> products = (List<Product>) request.getAttribute("products");
    Optional<String> username = (Optional<String>) request.getAttribute("username");
    String requestMessage = (String) request.getAttribute("requestMessage");
    String appMessage = (String) getServletContext().getAttribute("globalMessage");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View Products</title>
</head>
<body>
    <h1>View Products</h1>
    <%if (username.isPresent()) {%>
        <div>Welcome <%=username.get()%>!</div>
        <p><a href="<%=request.getContextPath()%>/product/add">[+] New Product</a></p>
    <%}%>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Type</th>
            <%if (username.isPresent()) {%>
                <th>Price</th>
                <th>Add</th>
                <th>Edit</th>
            <%}%>
        </tr>
        <%for (Product p : products) {%>
            <tr>
                <td><%=p.getId()%></td>
                <td><%=p.getName()%></td>
                <td><%=p.getCategory().getName()%></td>
                <%if (username.isPresent()) {%>
                    <td><%=p.getPrice()%></td>
                    <td><a href="<%=request.getContextPath()%>/cart/add?id=<%=p.getId()%>">Add to Cart</a></td>
                <td><a href="<%=request.getContextPath()%>/product/add?id=<%=p.getId()%>">Edit Product</a></td>
                <%}%>
            </tr>
        <%}%>
    </table>
    <p><%=appMessage%></p>
    <p><%=requestMessage%></p>
</body>
</html>