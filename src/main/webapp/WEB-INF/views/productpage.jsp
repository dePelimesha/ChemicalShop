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
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/views/navbar.jsp"/>
<div class="container min_container">
    <h2></h2>
    <div class="row">
        <div class="col-sm-9 text-center" style="margin-top: 10px">
            <div class="col-sm-5">
                <img src="<c:url value="/image/${product.productId}"/>" alt="" style="width: 250px; height: 250px"/>
            </div>
            <c:if test="${product.inStock != 0}">
            <div class="col-sm-7 text-left">
                <h2>${product.productName}</h2>
                <h4>Price: ${product.price} BYN<span class="label" style="background-color: #4cae4c">In stock</span></h4>
                <h4>Category: ${product.category.categoryName}</h4>
                <h4>Physical properties: ${product.physicalProperties}</h4>
                <h4>Molar mass: ${product.molarMass} g/mol</h4>
                <h4>Chemical formula: ${product.chemicalFormula}</h4>
                <c:if test="${not cart.contains(product.productId)}">
                    <form action="<c:url value="${pageContext.request.contextPath}/cart/add"/>" method="post">
                        <input type="hidden" name="id" value="${product.productId}">
                        <input type="hidden" name="url" value="${requestScope['javax.servlet.forward.request_uri']}">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <button class="btn btn-default add-to-cart" type="submit">Add to cart</button>
                    </form>
                </c:if>
                <c:if test="${cart.contains(product.productId)}">
                    <form action="<c:url value="${pageContext.request.contextPath}/cart/delete"/>" method="post">
                        <input type="hidden" name="id" value="${product.productId}">
                        <input type="hidden" name="url" value="${requestScope['javax.servlet.forward.request_uri']}">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <button class="btn btn-default add-to-cart" type="submit">Drop from cart</button>
                    </form>
                </c:if>
            </div>
            </c:if>
            <c:if test="${product.inStock == 0}">
                <div class="col-sm-7 text-left">
                    <h2>${product.productName}</h2>
                    <h4>Price: ${product.price} BYN<span class="label" style="background-color: #c7254e">Out of stock</span></h4>
                    <h4>Category: ${product.category.categoryName}</h4>
                    <h4>Physical properties: ${product.physicalProperties}</h4>
                    <h4>Molar mass: ${product.molarMass} g/mol</h4>
                    <h4>Chemical formula: ${product.chemicalFormula}</h4>
                    <button class="btn btn-default" type="submit" style="background-color: gray()" disabled>Add to cart</button>
                </div>
            </c:if>
        </div>
    </div>
    <h2></h2>
    <div class="row">
        <div class=" container recommended_items"><!--recommended_items-->
            <h2 class="title text-center">Recommended for You</h2>
            <c:forEach items="${recommendedProducts}" var="product">
                <a href="<c:url value="/productDetails/${product.productId}"/>">
                    <div class="col-sm-4">
                        <div class="product-image-wrapper">
                            <div class="single-products">
                                <div class="productinfo text-center">
                                    <a href="<c:url value="/productDetails/${product.productId}"/> ">
                                        <img src="<c:url value="/image/${product.productId}"/>" alt="" style="width: 100px; height: 100px"/>
                                        <h3>${product.productName}</h3>
                                    </a>
                                </div>
                                <div>
                                    <c:if test="${product.inStock == 0}">
                                        <div class="label text-left">
                                            <span class="label" style="background-color: #c7254e">Out of stock</span>
                                        </div>
                                        <div class="text-left">
                                            <button class="btn btn-default add-btn" type="submit" style="background-color: gray()" disabled>Add to cart</button>
                                        </div>
                                    </c:if>
                                    <c:if test="${product.inStock != 0}">
                                        <div class="label text-left">
                                            <span class="label" style="background-color: #4cae4c">In stock</span>
                                        </div>
                                        <div class="text-left">
                                            <c:if test="${not cart.contains(product.productId)}">
                                                <form action="<c:url value="${pageContext.request.contextPath}/cart/add"/>" method="post">
                                                    <input type="hidden" name="id" value="${product.productId}">
                                                    <input type="hidden" name="url" value="${requestScope['javax.servlet.forward.request_uri']}">
                                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                                    <button class="btn btn-default add-btn" type="submit">Add to cart</button>
                                                </form>
                                            </c:if>
                                            <c:if test="${cart.contains(product.productId)}">
                                                <form action="<c:url value="${pageContext.request.contextPath}/cart/delete"/>" method="post">
                                                    <input type="hidden" name="id" value="${product.productId}">
                                                    <input type="hidden" name="url" value="${requestScope['javax.servlet.forward.request_uri']}">
                                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                                    <button class="btn btn-default add-btn" type="submit">Drop from cart</button>
                                                </form>
                                            </c:if>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </a>
            </c:forEach>
        </div>
    </div>
</div>
<footer class="container-fluid text-center">
</footer>
</body>
</html>