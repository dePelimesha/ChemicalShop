<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="springform" uri="http://www.springframework.org/tags/form" %>
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
<div class="container min_container text-center">
    <div class="col-md-3 text-center order-form">
        <div class="panel panel-primary text-center">
            <h2>Contact information</h2>
            <springform:form commandName="order" action="/createorder/guest" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <springform:input path="name" placeholder="Name *"/>
                <br>
                <label>&nbsp;<springform:errors path="name" cssClass="error"/></label>
                <br>
                <springform:input path="surname" placeholder="Surnaame"/>
                <br>
                <label>&nbsp;</label>
                <br>
                <springform:input path="email" placeholder="Email"/>
                <br>
                <label>&nbsp;<springform:errors path="email" cssClass="error"/></label>
                <br>
                <springform:input path="phoneNumber" placeholder="Phone *"/>
                <br>
                <label>&nbsp;<springform:errors path="phoneNumber" cssClass="error"/></label>
                <br>
                <input type="hidden" name="finalPrice" value="${price}">
                <input type="submit" value="Submit" class="btn btn-primary" style="margin-bottom: 20px">
            </springform:form>
        </div>
    </div>
    <c:if test="${user ne 'anonymousUser'}">
        <div class="col-sm-1" style="margin-top: 220px">
            <h2 class="or">OR</h2>
        </div>
        <div class="col-sm-4" style="margin-top: 190px">
            <div class="panel panel-primary text-center">
            <h2>Use your account info</h2>
                <form action="<c:url value="/createorder/loggedOrder"/>" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="submit" class="btn btn-primary" value="Use it" style="margin-bottom: 20px">
                </form>
            </div>
        </div>
    </c:if>
</div>
<footer class="container-fluid text-center">
</footer>
</body>