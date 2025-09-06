<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html; UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${product.id != null && product.id > 0 ? "Edit" : "Add"} Product</title>
</head>
<body>
    <h1>${product.id != null && product.id > 0 ? "Edit" : "Add"} Product</h1>
    <form action="${pageContext.request.contextPath}/product/add" method="post">
        <input type="hidden" name="id" value="${product.id}">
        <div>
            <label for="name">Name:</label>
            <div>
                <input type="text" name="name" id="name" value="${product.name}">
            </div>
            <c:if test="${errors != null && !empty errors.name}">
                <div style="color: red;">
                    ${errors.name}
                </div>
            </c:if>
        </div>
        <div>
            <label for="price">Price:</label>
            <div>
                <input type="number" name="price" id="price" value="${product.price > 0 ? product.price : ""}">
            </div>
            <c:if test="${errors != null && !empty errors.price}">
                <div style="color: red;">
                        ${errors.price}
                </div>
            </c:if>
        </div>
        <div>
            <label for="sku">SKU:</label>
            <div>
                <input type="text" name="sku" id="sku" value="${product.sku}">
            </div>
            <c:if test="${errors != null && !empty errors.sku}">
                <div style="color: red;">
                        ${errors.sku}
                </div>
            </c:if>
        </div>
        <div>
            <label for="createdAt">Created At:</label>
            <div>
                <input type="date" name="createdAt" id="createdAt" value="${product.createdAt != null ? product.createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : ""}">
            </div>
            <c:if test="${errors != null && !empty errors.createdAt}">
                <div style="color: red;">
                        ${errors.createdAt}
                </div>
            </c:if>
        </div>
        <div>
            <label for="category">Category:</label>
            <div>
                <select name="category" id="category">
                    <option value="">------- Select -------</option>
                    <c:forEach items="${categories}" var="c">
                        <option value="${c.id}" ${c.id.equals(product.category.id) ? "selected" : ""}>${c.name}</option>
                    </c:forEach>
                </select>
            </div>
            <c:if test="${errors != null && !empty errors.category}">
                <div style="color: red;">
                        ${errors.category}
                </div>
            </c:if>
        </div>
        <div>
            <input type="submit" value="Save">
        </div>
    </form>
</body>
</html>