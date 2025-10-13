<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html; UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="layout/header.jsp"/>
<h3>${requestScope.title}</h3>
<form action="${pageContext.request.contextPath}/product/add" method="post">
    <input type="hidden" name="id" value="${product.id}">
    <div class="row mb-2">
        <label class="col-form-label col-sm-2" for="name">Name:</label>
        <div class="col-sm-4">
            <input class="form-control" type="text" name="name" id="name" value="${product.name}">
        </div>
        <c:if test="${errors != null && !empty errors.name}">
            <div style="color: red;">
                ${errors.name}
            </div>
        </c:if>
    </div>
    <div class="row mb-2">
        <label class="col-form-label col-sm-2" for="price">Price:</label>
        <div class="col-sm-4">
            <input class="form-control" type="number" name="price" id="price" value="${product.price > 0 ? product.price : ""}">
        </div>
        <c:if test="${errors != null && !empty errors.price}">
            <div style="color: red;">
                    ${errors.price}
            </div>
        </c:if>
    </div>
    <div class="row mb-2">
        <label class="col-form-label col-sm-2" for="sku">SKU:</label>
        <div class="col-sm-4">
            <input class="form-control" type="text" name="sku" id="sku" value="${product.sku}">
        </div>
        <c:if test="${errors != null && !empty errors.sku}">
            <div style="color: red;">
                    ${errors.sku}
            </div>
        </c:if>
    </div>
    <div class="row mb-2">
        <label class="col-form-label col-sm-2" for="createdAt">Created At:</label>
        <div class="col-sm-4">
            <input class="form-control" type="date" name="createdAt" id="createdAt" value="${product.createdAt != null ? product.createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : ""}">
        </div>
        <c:if test="${errors != null && !empty errors.createdAt}">
            <div style="color: red;">
                    ${errors.createdAt}
            </div>
        </c:if>
    </div>
    <div class="row mb-2">
        <label class="col-form-label col-sm-2" for="category">Category:</label>
        <div class="col-sm-4">
            <select class="form-select" name="category" id="category">
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
    <div class="row mb-2">
        <div>
            <input class="btn btn-primary" type="submit" value="Save">
        </div>
    </div>
</form>
<jsp:include page="layout/footer.jsp"/>