<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Varela+Round&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/main.css">
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/delivery.css">
    <fmt:setLocale value="${lang}"/>
	<fmt:setBundle basename="resources" var="lang"/>
    <title><fmt:message key="inter.delivery.title" bundle="${lang}"/></title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div class="body">
        <div class="blank"></div>
        <div class="navigation">
            <a href="http://localhost:8080/final/server/home"><fmt:message key="inter.delivery.nav.home" bundle="${lang}"/></a>
            <img src="http://localhost:8080/final/view/media/5f8f84f3d4d23a31c1f2fcae_arrow-right-mini-icon.svg">
            <fmt:message key="inter.delivery.nav.delivery" bundle="${lang}"/>
        </div>
        <div class="all">
            <div class="a_info">
                <h1><fmt:message key="inter.delivery.info" bundle="${lang}"/></h1>
                <fmt:message key="inter.delivery.info.text" bundle="${lang}"/>
                <h1><fmt:message key="inter.delivery.info.a" bundle="${lang}"/></h1>
                <fmt:message key="inter.delivery.info.a.text" bundle="${lang}"/>
                <h1><fmt:message key="inter.delivery.info.b" bundle="${lang}"/></h1>
                <fmt:message key="inter.delivery.info.b.text" bundle="${lang}"/>
                <h1><fmt:message key="inter.delivery.info.other" bundle="${lang}"/></h1>
                <ul>
                    <li><fmt:message key="inter.delivery.info.other.a" bundle="${lang}"/></li>
                    <li><fmt:message key="inter.delivery.info.other.b" bundle="${lang}"/></li>
                    <li><fmt:message key="inter.delivery.info.other.c" bundle="${lang}"/></li>
                    <li><fmt:message key="inter.delivery.info.other.d" bundle="${lang}"/></li>
                </ul>
            </div>
            <div class="a_link">
                <h2><fmt:message key="inter.delivery.question" bundle="${lang}"/></h2>
                <a class="button" href="http://localhost:8080/final/server/contacts"><fmt:message key="inter.delivery.contact" bundle="${lang}"/></a>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
    </div>
</body>
</html>