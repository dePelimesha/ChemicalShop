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
    <link href="<c:url value="resources/css/ownstyles.css"/>" rel="stylesheet" type="text/css">
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
    <section class="section text-center">
    <ul class="list-icon-items clearfix no-margins">
        <li class="grid-3 secondary alpha">
            <i class="circ large tertiary ss-icon ss-standard">
                <img src="resources/image/email.png" alt="" width="40" height="40" style="margin-top: 30px">
                <i class="circ pulse"></i>
            </i>
            <h4 class="secondary">Email</h4>
            <p>main@chemshop.com</p>
        </li>
        <li class="grid-3 secondary">
            <i class="circ large tertiary ss-icon ss-standard">
                <img src="resources/image/phone.png" alt="" width="40" height="40" style="margin-top: 30px">
                <i class="circ pulse"></i>
            </i>
            <h4 class="secondary">Phone</h4>
            <p class="secondary">+375-17-222-88-77</p>
        </li>
        <li class="grid-3 secondary">
                <i class="circ large tertiary ss-icon ss-social">
                    <img src="resources/image/twitter.png" alt="" width="40" height="40" style="margin-top: 30px">
                    <i class="circ pulse"></i>
                </i>
                <h4 class="secondary">Twitter</h4>
                <p>
                    <span>@ChemShop</span>
                </p>
            </a>
        </li>
        <li class="grid-3 secondary omega">
            <i class="circ large tertiary ss-icon ss-social">
                <img src="resources/image/skype.png" alt="" width="40" height="40" style="margin-top: 30px">
                <i class="circ pulse"></i>
            </i>
            <h4 class="secondary">Skype</h4>
            <p>
                <span class="pseudo-link">ChemicalShop</span>
            </p>
        </li>
    </ul>
    </section>
</div>
<footer class="container-fluid text-center">
</footer>
</body>
</html>