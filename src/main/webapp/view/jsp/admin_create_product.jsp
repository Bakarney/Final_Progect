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
	<link rel="stylesheet" href="http://localhost:8080/final/view/css/header.css">
	<link rel="stylesheet" href="http://localhost:8080/final/view/css/menu.css">
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/admin.css">
    <fmt:setLocale value="${lang}"/>
	<fmt:setBundle basename="resources" var="lang"/>
	<title><fmt:message key="inter.admin_create_product.title" bundle="${lang}"/></title>
</head>
<body>
	<jsp:include page="admin_header.jsp"/>
    <div class="body">
        <form class="folder" method="POST" action="http://localhost:8080/final/server/create_product" enctype="multipart/form-data">
            <table>
                <tr>
                    <th><fmt:message key="inter.admin_create_product.name" bundle="${lang}"/></th>
                    <th><input name="name" type="text"></th>
                </tr>
                <tr>
                    <th><fmt:message key="inter.admin_create_product.category" bundle="${lang}"/></th>
                    <th><input name="category" type="text"></th>
                </tr>
                <tr>
                    <th><fmt:message key="inter.admin_create_product.gender" bundle="${lang}"/></th>
                    <th><input name="gender" type="text"></th>
                </tr>
                <tr>
                    <th><fmt:message key="inter.admin_create_product.producer" bundle="${lang}"/></th>
                    <th><input name="producer" type="text"></th>
                </tr>
                <tr>
                    <th><fmt:message key="inter.admin_create_product.number" bundle="${lang}"/></th>
                    <th><input  name="number" type="number"></th>
                </tr>
                <tr>
                    <th><fmt:message key="inter.admin_create_product.price" bundle="${lang}"/></th>
                    <th><input name="price" type="number"></th>
                </tr>
                <tr>
                    <th><fmt:message key="inter.admin_create_product.photo" bundle="${lang}"/></th>
                    <th><input name="photo" type="file"></th>
                </tr>
            </table>
            <input class="button" type="submit" value="<fmt:message key="inter.admin_create_product.create" bundle="${lang}"/>">
        </form>
    </div>
</body>
</html>