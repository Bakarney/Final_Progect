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
	<title>${product.getName()}</title>
</head>
<body>
	<jsp:include page="admin_header.jsp"/>
    <div class="body">
        <form class="folder" method="POST" action="http://localhost:8080/final/server/update_product" enctype="multipart/form-data">
            <table>
                <tr>
                    <th><fmt:message key="inter.admin_product.id" bundle="${lang}"/></th>
                    <th>${product.getId()}</th>
                    <th><input name="id" type="number" value="${product.getId()}" readonly></th>
                </tr>
                <tr>
                    <th><fmt:message key="inter.admin_product.name" bundle="${lang}"/></th>
                    <th>${product.getName()}</th>
                    <th><input name="name" type="text" value="${product.getName()}"></th>
                </tr>
                <tr>
                    <th><fmt:message key="inter.admin_product.category" bundle="${lang}"/></th>
                    <th>${product.getCategory()}</th>
                    <th><input name="category" type="text" value="${product.getCategory()}"></th>
                </tr>
                <tr>
                    <th><fmt:message key="inter.admin_product.gender" bundle="${lang}"/></th>
                    <th>${product.getGender()}</th>
                    <th><input name="gender" type="text" value="${product.getGender()}"></th>
                </tr>
                <tr>
                    <th><fmt:message key="inter.admin_product.producer" bundle="${lang}"/></th>
                    <th>${product.getProducer()}</th>
                    <th><input name="producer" type="text" value="${product.getProducer()}"></th>
                </tr>
                <tr>
                    <th><fmt:message key="inter.admin_product.number" bundle="${lang}"/></th>
                    <th>${product.getNumber()}</th>
                    <th><input  name="number" type="number" value="${product.getNumber()}"></th>
                </tr>
                <tr>
                    <th><fmt:message key="inter.admin_product.price" bundle="${lang}"/></th>
                    <th>${product.getPrice()}</th>
                    <th><input name="price" type="number" value="${product.getPrice()}"></th>
                </tr>
                <tr>
                    <th><fmt:message key="inter.admin_product.photo" bundle="${lang}"/></th>
                    <th><a class="link" href="http://localhost:8080/final/view/media/${product.getCategory()}/${product.getPhoto()}">${product.getPhoto()}</th>
                    <th><input name="photo" type="file"></th>
                </tr>
            </table>
            <input class="button" type="submit" value="<fmt:message key="inter.admin_product.btn" bundle="${lang}"/>">
        </form>
    </div>
</body>
</html>