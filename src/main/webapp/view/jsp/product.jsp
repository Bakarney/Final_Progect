<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="entities.Product" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Varela+Round&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/main.css">
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/product.css">
    <fmt:setLocale value="${lang}"/>
	<fmt:setBundle basename="resources" var="lang"/>
    <title>${product.getName()}</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div class="body">
        <div class="folder">
            <img src="http://localhost:8080/final/view/media/${product.getCategory()}/${product.getPhoto()}">
            <table class="data">
                <tr>
                    <td><fmt:message key="inter.product.name" bundle="${lang}"/></td>
                    <td>${product.getName()}</td>
                </tr>
                <tr>
                    <td><fmt:message key="inter.product.category" bundle="${lang}"/></td>
                    <td>${product.getCategory()}</td>
                </tr>
                <tr>
                    <td><fmt:message key="inter.product.description" bundle="${lang}"/></td>
                    <td>${product.getDescription()}</td>
                </tr>
                <tr>
                    <td><fmt:message key="inter.product.gender" bundle="${lang}"/></td>
                    <td>${product.getGender()}</td>
                </tr>
                <tr>
                    <td><fmt:message key="inter.product.producer" bundle="${lang}"/></td>
                    <td>${product.getProducer()}</td>
                </tr>
                <tr>
                    <td><fmt:message key="inter.product.price" bundle="${lang}"/></td>
                    <td>${product.getPrice()}</td>
                </tr>
            </table>
            <form method="POST" action="http://localhost:8080/final/server/add_product">
            	<input type="hidden" name="product_id" value="${product.getId()}">
            	<input class="button" type="submit" value="<fmt:message key="inter.product.buy" bundle="${lang}"/>">
            </form>
        </div>
    </div>
</body>
</html>