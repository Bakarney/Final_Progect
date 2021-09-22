<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
<%@ page import="entities.Product" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="https://fonts.googleapis.com/css2?family=Varela+Round&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="http://localhost:8080/final/view/css/main.css">
	<link rel="stylesheet" href="http://localhost:8080/final/view/css/menu.css">
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/admin.css">
    <fmt:setLocale value="${lang}"/>
	<fmt:setBundle basename="resources" var="lang"/>
	<title><fmt:message key="inter.cart.title" bundle="${lang}"/></title>
</head>
<body>
	<jsp:include page="header.jsp"/>
    <div class="body">
        <div class="folder">
            <table>
                <tr>
                    <th><fmt:message key="inter.cart.name" bundle="${lang}"/></th>
                    <th><fmt:message key="inter.cart.category" bundle="${lang}"/></th>
                    <th><fmt:message key="inter.cart.gender" bundle="${lang}"/></th>
                    <th><fmt:message key="inter.cart.producer" bundle="${lang}"/></th>
                    <th><fmt:message key="inter.cart.price" bundle="${lang}"/></th>
                    <th><fmt:message key="inter.cart.photo" bundle="${lang}"/></th>
                </tr>
                <c:forEach var="prod" items="${products}">
				    <tr>
				    	<td>${prod.getName()}</td>
				    	<td>${prod.getCategory()}</td>
                        <td>${prod.getGender()}</td>
                        <td>${prod.getProducer()}</td>
                        <td>${prod.getPrice()}</td>
                        <td><a class="link" href="http://localhost:8080/final/view/media/${prod.getCategory()}/${prod.getPhoto()}">${prod.getPhoto()}</a></td>
                        <td><form method="POST" action="http://localhost:8080/final/server/remove_product?id=${prod.getId()}"><input type="submit" class="link" value="<fmt:message key="inter.cart.delete" bundle="${lang}"/>"></input></form></td>
                        </tr>
				</c:forEach>
            </table>
            <form method="POST" action="http://localhost:8080/final/server/confirm_order">
            	<input type="hidden" name="order_id" value="${order.getId()}">
            	<input class="button" type="submit" value="<fmt:message key="inter.cart.buy" bundle="${lang}"/>">
            </form>
        </div>
    </div>
</body>
</html>