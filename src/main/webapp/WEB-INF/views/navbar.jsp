<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="jumbotron" style="height: 220px; margin-top: 0px;">
    <div class="container">
        <h1><img src="/resources/image/lab.png" style="width: 120px; height: 120px"/>ChemicalShop</h1>

    </div>
</div>
<nav class="navbar navbar-inverse" style="margin-bottom: 0px">
    <div class="container">
        <ul class="nav navbar-nav">
            <li><a href="<c:url value="/home"/>">Home</a></li>
            <li class="dropdown">
                <a data-toggle="dropdown" class="dropdown-toggle">Products
                    <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="<c:url value="/products"/>">All products</a></li>
                    <li class="divider"></li>
                    <c:forEach items="${category}" var="category">
                        <li>
                            <a href="<c:url value="/products/category/${category.categoryId}"/>">${category.categoryName}</a>
                        </li>
                    </c:forEach>
                </ul>
            </li>
            <li><a href="<c:url value="/contact"/>">Contact us</a></li>
        </ul>
        <form class="navbar-form navbar-left" action="<c:url value="/search"/>" method="post">
            <div class="input-group">
                <input type="text" class="form-control" placeholder="Search" name="searchValue">
                <span class="input-group-btn">
                    <button class="btn btn-default" type="submit" style="height: 34px; margin-left: -1px">
                        <i class="glyphicon glyphicon-search"></i>
                    </button>
                </span>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </div>
        </form>
        <ul class="nav navbar-nav navbar-right">
            <c:if test="${user eq 'anonymousUser'}">
                <li><a href="<c:url value="/login"/>"><span class="glyphicon"></span>Login</a></li>
            </c:if>
            <c:if test="${user ne 'anonymousUser'}">
                <li class="dropdown">
                    <a data-toggle="dropdown" class="dropdown-toggle">${user}
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <li><a href="<c:url value="/orders"/>"><span class="glyphicon"></span>Orders</a></li>
                            <li><a href="<c:url value="/createProduct"/>"><span class="glyphicon"></span>Admin page</a></li>
                            <li><a href="<c:url value="/createAdmin"/>"><span class="glyphicon"></span>Create admin</a></li>
                            <li class="divider"></li>
                        </sec:authorize>
                        <li><a href="<c:url value="/logout"/>"><span class="glyphicon"></span>Logout</a></li>
                    </ul>
                </li>
            </c:if>
            <li><a href="<c:url value="/cart/view"/>"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span> Cart(${productInCart})</a></li>
        </ul>
    </div>
</nav>