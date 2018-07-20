<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Chemical shop</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="resources/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/resources/css/ownstyles.css"/>" rel="stylesheet" type="text/css">
    <script>
        <%@include file="/resources/js/jquery-3.3.1.min.js"%>
        <%@include file="/resources/js/bootstrap.min.js" %>
    </script>
    <style>
        <%@include file="/resources/css/productpagestyle.css" %>
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/views/navbar.jsp"/>
<div class="container min_container">
<div class="table-responsive cart_info">
    <table class="table table-condensed">
        <thead>
            <tr class="cart_menu">
                <th>Order ID</th>
                <th>Order date</th>
                <th>Cost</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Phone</th>
                <th>Email</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${orders}" var="order">
                <tr>
                    <td>${order.orderId}</td>
                    <td>${order.orderDate}</td>
                    <td>${order.orderPrice}</td>
                    <td>${order.name}</td>
                    <td>${order.surname}</td>
                    <td>${order.phoneNumber}</td>
                    <td>${order.email}</td>
                </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</div>
</body>
</html>

