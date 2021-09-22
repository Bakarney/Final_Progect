<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="entities.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Varela+Round&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/main.css">
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/header.css">
    <fmt:setLocale value="${lang}"/>
	<fmt:setBundle basename="resources" var="lang"/>
    <title><fmt:message key="inter.admin_header.title" bundle="${lang}"/></title>
</head>
<body>
    <div class="header">
        <div class="green_head">
        	<div class="lang">
            	<form>
            		<input type="hidden" name="lang" value="ua">
            		<input class="lang_link" type="submit" value="UA">
            	</form>
            	|
            	<form>
            		<input type="hidden" name="lang" value="en">
            		<input class="lang_link" type="submit" value="EN">
            	</form>
            </div>
            <form method="POST" action="http://localhost:8080/final/server/sign_out" class="profile">
            	<input type="submit" class="simple_btn" value="<fmt:message key="inter.admin_header.out" bundle="${lang}"/>">
            </form>
        </div>
        <div class="white_head">
            <h1><fmt:message key="inter.admin_header.shop" bundle="${lang}"/></h1>
            <div class="w_h_links">
                <a href="http://localhost:8080/final/server/admin_catalog"><fmt:message key="inter.admin_header.link.a" bundle="${lang}"/></a>
                <a href="http://localhost:8080/final/server/admin_users"><fmt:message key="inter.admin_header.link.b" bundle="${lang}"/></a>
                <a href="http://localhost:8080/final/server/admin_categories"><fmt:message key="inter.admin_header.link.c" bundle="${lang}"/></a>
                <a href="http://localhost:8080/final/server/admin_producers"><fmt:message key="inter.admin_header.link.d" bundle="${lang}"/></a>
                <a href="http://localhost:8080/final/server/admin_orders"><fmt:message key="inter.admin_header.link.e" bundle="${lang}"/></a>
            </div>
            <div class="w_h_blank"></div>
        </div>
    </div>
</body>
</html>