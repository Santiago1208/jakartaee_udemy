<%@ page contentType="text/html; UTF-8"%>
<%@ page import="org.srestrepo.apiservlet.webapp.jdbc.models.CartItem" %>
<%@ page import="org.srestrepo.apiservlet.webapp.jdbc.models.Cart" %>
<%
    Cart cart = (Cart) session.getAttribute("cart");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cart</title>
</head>
<body>
    <h1>Cart</h1>
    <%if (cart.getCartItems().isEmpty()) {%>
        <p>Sorry, the cart is empty</p>
    <%} else {%>
        <form action="<%=request.getContextPath()%>/cart/update" method="post">
            <table>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Amount</th>
                    <th>Total</th>
                    <th>Delete</th>
                </tr>
                <%for (CartItem item : cart.getCartItems()) {%>
                <tr>
                    <td><%=item.getProduct().getId()%></td>
                    <td><%=item.getProduct().getName()%></td>
                    <td><%=item.getProduct().getPrice()%></td>
                    <td><input type="text" name="amount" value="<%=item.getAmount()%>" /></td>
                    <td><%=item.getLineTotal()%></td>
                    <td><input type="checkbox" name="delete" value="<%=item.getProduct().getId()%>" /></td>
                </tr>
                <%}%>
                <tr>
                    <td colspan="4" style="text-align: right">Total</td>
                    <td><%=cart.getTotal()%></td>
                </tr>
            </table>
            <p><button type="submit">Update</button></p>
        </form>
    <%}%>
    <p><a href="<%=request.getContextPath()%>/products">Keep Buying</a></p>
    <p><a href="<%=request.getContextPath()%>/index.html">Go Back</a></p>
</body>
</html>
