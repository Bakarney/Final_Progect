<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="entities.User" %>
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
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/profile.css">
    <fmt:setLocale value="${lang}"/>
	<fmt:setBundle basename="resources" var="lang"/>
    <title><fmt:message key="inter.profile.title" bundle="${lang}"/></title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <%
    	User user = (User)session.getAttribute("user");
    %>
    <div class="body">
        <div class="folder">
            <div class="data">
                <div class="fields">
                    <p><fmt:message key="inter.profile.name" bundle="${lang}"/></p>
                    <p><fmt:message key="inter.profile.email" bundle="${lang}"/></p>
                    <p><fmt:message key="inter.profile.phone" bundle="${lang}"/></p>
                    <p><fmt:message key="inter.profile.address" bundle="${lang}"/></p>
                    <p><fmt:message key="inter.profile.card" bundle="${lang}"/></p>
                </div>
                <div class="info">
                    <p><%= user.getName() %></p>
                    <p><%= user.getEmail() %></p>
                    <p><%= user.getPhone() %></p>
                    <p><%= user.getAddress() %></p>
                    <p><%= user.getCard() %></p>
                </div>
            </div>
            <form method="POST" action="http://localhost:8080/final/server/sign_out">
            	<input class="button" type="submit" value="<fmt:message key="inter.profile.out" bundle="${lang}"/>">
            </form>
        </div>
    </div>
</body>
</html>