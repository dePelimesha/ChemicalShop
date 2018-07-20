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
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/resources/css/ownstyles.css"/>" rel="stylesheet" type="text/css">
    <script>
        <%@include file="/resources/js/jquery-3.3.1.min.js"%>
        <%@include file="/resources/js/bootstrap.min.js" %>
    </script>
    <style>
        <%@include file="/resources/css/productpagestyle.css" %>
        <%@include file="/resources/css/cart-table-style.css" %>
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/views/navbar.jsp"/>
<div class="container">
    <c:if test="${productInCart != 0}">
        <div class="table-responsive cart_info">
            <table class="table table-condensed">
                <thead>
                <tr class="cart_menu">
                    <td class="image">Product</td>
                    <td class="price">Cost</td>
                    <td class="quantity">Count</td>
                    <td class="total">Total</td>
                    <td class="delete"></td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${orderPositions}" var="position">
                    <tr>
                        <td class="cart_product"><a href=""><p>${position.product.productName}</p></a></td>
                        <td class="cart_price"><p>${position.product.price} BYN.</p></td>
                        <td class="cart_quantity">
                        <div class="cart_quantity_button">
                            <c:if test="${position.product.inStock <= position.productCount}">
                                <a onclick='alert("Out of stock");return false;' class="cart_quantity_down" href="<c:url value="/cart/increment/${position.product.productId}"/>"> + </a>
                            </c:if>
                            <c:if test="${position.product.inStock > position.productCount}">
                                <a class="cart_quantity_down" href="<c:url value="/cart/increment/${position.product.productId}"/>"> + </a>
                            </c:if>
                            <input readonly class="cart_quantity_input" type="text" name="quantity" value="${position.productCount}" size="1">
                            <c:if test="${position.productCount <= 1}">
                                <a onclick='return false;' class="cart_quantity_down" href="<c:url value="/cart/decrement/${position.product.productId}"/>"> - </a>
                            </c:if>
                            <c:if test="${position.productCount > 1}">
                                <a class="cart_quantity_down" href="<c:url value="/cart/decrement/${position.product.productId}"/>"> - </a>
                            </c:if>
                        </div>
                        </td>
                        <td class="cart_total"><p class="cart_total_price">${position.productCount * position.product.price} BYN.</p></td>
                        <td><a class="btn btn-default" href="<c:url value="/cart/delete/${position.product.productId}"/> ">Drop from cart</a></td>
                    </tr>
                </c:forEach>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td class="right">Order total: ${price} BYN.</td>
                    <td></td>
                </tr>
                <td><a class="btn btn-default check_out" href="<c:url value="/createorder"/> ">Checkout</a></td>
                </tbody>
            </table>
        </div>
    </c:if>
    <c:if test="${productInCart == 0}">
        <div class="text-center empty_result">
            <h1>Oops, cart is empty</h1>
        </div>
    </c:if>
</div>
<footer class="container-fluid text-center">
</footer>
</body>
</html>
