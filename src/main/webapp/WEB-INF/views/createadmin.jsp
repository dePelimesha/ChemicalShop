<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="springform" uri="http://www.springframework.org/tags/form" %>
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
    <div class="container text-center signup-form">
        <div class="panel panel-primary" style="width: 360px">
            <h1>Register</h1>
            <div class="container text-center" style="width: 180px">
                <springform:form commandName="adminReg" action="/createAdmin" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <springform:input path="login" placeholder="Login *"/>
                    <label>&nbsp;<springform:errors path="login" cssClass="error"/></label>
                    <springform:input type="password" path="password" placeholder="Password *"/>
                    <label>&nbsp;<springform:errors path="password" cssClass="error"/></label>
                    <input type="submit" value="Create admin" style="margin-bottom: 20px; margin-top: 20px;" class="btn btn-primary">
                    <label></label>
                </springform:form>
            </div>
        </div>
    </div>
</div>
<footer class="container-fluid text-center">
</footer>
</body>
</html>
