<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="springform" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <div class="col-md-3 text-center order-form">
        <div class="panel panel-primary text-center">
            <h3>Add new product</h3>
            <springform:form commandName="newProduct" action="/createProduct" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <springform:input path="productName" placeholder="Product"/>
                <br>
                <label>&nbsp;<springform:errors path="productName" cssClass="error"/></label>
                <br>
                <springform:input path="price" placeholder="Price"/>
                <br>
                <label>&nbsp;<springform:errors path="price" cssClass="error"/></label>
                <br>
                <springform:input path="inStock" placeholder="Count"/>
                <br>
                <label>&nbsp;<springform:errors path="inStock" cssClass="error"/></label>
                <br>
                <springform:input path="physicalProperties" placeholder="Phisical property"/>
                <br>
                <label>&nbsp;</label>
                <br>
                <springform:input path="molarMass" placeholder="Molar mass"/>
                <br>
                <label>&nbsp;<springform:errors path="molarMass" cssClass="error"/></label>
                <br>
                <springform:input path="chemicalFormula" placeholder="Chemical formula"/>
                <br>
                <label>&nbsp;</label>
                <br>
                <select name="chooseCategory">
                    <c:forEach items="${category}" var="category">
                        <option value="${category.categoryName}">${category.categoryName}</option>
                    </c:forEach>
                </select>
                <br>
                <label>&nbsp;</label>
                <br>
                <input type="submit" value="Add product" class="btn btn-primary" style="margin-bottom: 20px"/>
            </springform:form>
        </div>
    </div>
    <div class="col-md-3" style="margin-top: 110px">
        <div class="panel panel-primary text-center">
            <h3>Change image</h3>
            <form action="/changeImage" method="post" enctype="multipart/form-data">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="text" name="productsName" placeholder="Product"/>
                <input type="file" name="image" style="margin-left: 15px"/>
                <c:if test="${noUserErr ne null}">
                    <p class="alert alert-danger">No such product</p>
                </c:if>
                <c:if test="${imageErr ne null}">
                    <p class="alert alert-danger">Wrong file</p>
                </c:if>
                <input type="submit" value="Change Image" class="btn btn-primary" style="margin-bottom: 20px"/>
            </form>
        </div>

        <div class="panel panel-primary text-center">
            <h3>Add from JSON</h3>
            <form action="/addFromJSON" method="post" enctype="multipart/form-data">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="file" name="image" style="margin-left: 15px"/>
                <c:if test="${err ne null}">
                    <p class="alert alert-danger">Wrong file</p>
                </c:if>
                <input type="submit" value="Add product" class="btn btn-primary" style="margin-bottom: 20px"/>
            </form>
        </div>

        <div class="panel panel-primary text-center">
            <h3>Get JSON file</h3>
            <form action="" method="get" enctype="multipart/form-data">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <a href="<c:url value='/downloadJSON' />" class="btn btn-success custom-width" style="margin-bottom: 20px">Download</a></td>
            </form>
        </div>
    </div>
</div>
<footer class="container-fluid text-center">
</footer>
</body>
</html>