<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Chemical shop</title>
    <meta charset="utf-8">
    <meta name="viewport" content="min-width=device-width, initial-scale=1">
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
    <div class="row">
        <div class="features_items">
            <c:forEach items="${products}" var="product">
                <div class="col-sm-4">
                    <div class="product-image-wrapper">
                        <div class="single-product text-center">
                            <a href="<c:url value="/productDetails/${product.productId}"/> ">
                                <img src="<c:url value="/image/${product.productId}"/>" alt="" style="width: 180px; height: 180px"/>
                                <h3>${product.productName}</h3>
                            </a>
                        </div>
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
            </c:forEach>
        </div>
    </div>
    <div class="text-center">
        <ul class="pagination">
            <c:forEach begin="1" end="${amountOfPages}" var="i">
                <c:if test="${searchValue eq null}">
                    <c:if test="${i ne openedPage}">
                        <li><a href="<c:url value="${url}${i}"/>">${i}</a></li>
                    </c:if>
                    <c:if test="${i eq openedPage}">
                        <li onclick='return false;' class="active"><a href="<c:url value="${url}${i}"/>">${i}</a></li>
                    </c:if>
                </c:if>
                <c:if test="${searchValue ne null}">
                    <c:if test="${i eq openedPage}">
                        <li onclick='return false;' class="active"><a href="<c:url value="/${url}${searchValue}/${i}"/>">${i}</a></li>
                    </c:if>
                    <c:if test="${i ne openedPage}">
                        <li><a href="<c:url value="${url}${searchValue}/${i}"/>">${i}</a></li>
                    </c:if>
                </c:if>
            </c:forEach>
        </ul>
    </div>
    <c:if test="${products.size() == 0}">
        <div class="text-center empty_result">
            <h1>No such products</h1>
        </div>
    </c:if>
</div>
<footer class="container-fluid text-center">
</footer>
</body>
</html>
