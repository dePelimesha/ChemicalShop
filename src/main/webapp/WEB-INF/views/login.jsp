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
<div class="center-block">
    <div class="col-sm-4 signin-form">
        <div class="panel panel-primary text-center">
            <h2>Sign in</h2>
            <c:url var="loginUrl" value="/login" />
            <form action="${loginUrl}" method="post">
                <c:if test="${param.error != null}">
                    <div class="alert alert-danger">
                        <p>Wrong login or password.</p>
                    </div>
                </c:if>
                <input type="text" name="login" placeholder="Login" required>
                <br>
                <br>
                <input type="password"  name="password" placeholder="Password" required>
                <br>
                <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
                <br>
                <input type="submit" class="btn btn-primary" style="margin-bottom: 10px" value="Sign in">
                <div class="panel-footer"><a href="<c:url value="/register"/>">Register</a></div>
            </form>
        </div>
    </div>
</div>
</div>
<footer class="container-fluid text-center">
</footer>
</body>
</html>
