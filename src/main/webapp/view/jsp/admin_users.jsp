<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
<%@ page import="entities.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="https://fonts.googleapis.com/css2?family=Varela+Round&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="http://localhost:8080/final/view/css/main.css">
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/admin.css">
    <fmt:setLocale value="${lang}"/>
	<fmt:setBundle basename="resources" var="lang"/>
	<title><fmt:message key="inter.admin_users.title" bundle="${lang}"/></title>
</head>
<body>
	<jsp:include page="admin_header.jsp"/>
    <div class="body">
        <div class="folder">
            <table>
                <tr>
                    <th><fmt:message key="inter.admin_users.id" bundle="${lang}"/></th>
                    <th><fmt:message key="inter.admin_users.name" bundle="${lang}"/></th>
                    <th><fmt:message key="inter.admin_users.email" bundle="${lang}"/></th>
                    <th><fmt:message key="inter.admin_users.phone" bundle="${lang}"/></th>
                    <th><fmt:message key="inter.admin_users.address" bundle="${lang}"/></th>
                    <th><fmt:message key="inter.admin_users.card" bundle="${lang}"/></th>
                    <th><fmt:message key="inter.admin_users.admin" bundle="${lang}"/></th>
                    <th><fmt:message key="inter.admin_users.active" bundle="${lang}"/> </th>
                </tr>
                <c:forEach var="user" items="${users}">
	            	<tr>
		                <td>${user.getId()}</td>
		                <td>${user.getName()}</td>
		                <td>${user.getEmail()}</td>
		                <td>${user.getPhone()}</td>
		                <td>${user.getAddress()}</td>
		                <td>${user.getCard()}</td>
		                <td>${user.isAdmin()}</td>
		                <td>${user.isActive()}</td>
		                <td><form action="http://localhost:8080/final/server/block_user?id=${user.getId()}" method="POST"><input type="submit" class="link" value="<fmt:message key="inter.admin_users.block.btn" bundle="${lang}"/>"></input></form></td>
		                <td><form action="http://localhost:8080/final/server/admin_user?id=${user.getId()}" method="POST"><input type="submit" class="link" value="<fmt:message key="inter.admin_users.admin.btn" bundle="${lang}"/>"></input></form></td>
		                <td><form action="http://localhost:8080/final/server/delete_user?id=${user.getId()}" method="POST"><input type="submit" class="link" value="<fmt:message key="inter.admin_users.delete.btn" bundle="${lang}"/>"></input></form></td>
	            	</tr>
                </c:forEach>
            </table>
        </div>
    </div>
</body>
</html>