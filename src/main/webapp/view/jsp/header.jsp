<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="entities.*" %>
<%@ page import="java.util.List" %>
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
    <title><fmt:message key="inter.header.title" bundle="${lang}"/></title>
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
            <div class="panel">
            	<c:if test="${user == null}">
	            	<a href="http://localhost:8080/final/server/sign_in" class="profile_link">
	                	<p><fmt:message key="inter.header.sign_in" bundle="${lang}"/></p>
	                </a>
	            </c:if>
	            <c:if test="${user != null}">
	            	<a href="http://localhost:8080/final/server/profile" class="profile_link">
	                	<p>${user.getName()}</p>
	                </a>
                    <form class="form" method="POST" action="http://localhost:8080/final/server/sign_out">
                    	<input type="submit" class="profile" value="<fmt:message key="inter.header.sign_out" bundle="${lang}"/>"></input>
                   	</form>
	            </c:if>
            </div>
        </div>
        <div class="white_head">
            <h1><fmt:message key="inter.header.shop" bundle="${lang}"/></h1>
            <div class="w_h_links">
                <a href="http://localhost:8080/final/server/catalog"><fmt:message key="inter.header.link.a" bundle="${lang}"/></a>
                <a href="http://localhost:8080/final/server/delivery"><fmt:message key="inter.header.link.b" bundle="${lang}"/></a>
                <a href="http://localhost:8080/final/server/about"><fmt:message key="inter.header.link.c" bundle="${lang}"/></a>
                <a href="http://localhost:8080/final/server/contacts"><fmt:message key="inter.header.link.d" bundle="${lang}"/></a>
            </div>
            <div class="w_h_blank"></div>
            <a class="w_h_cart" href="http://localhost:8080/final/server/cart">
            	<div class="w_h_c_text">
            		<fmt:message key="inter.header.cart" bundle="${lang}"/>
            	</div>
            	<img src="http://localhost:8080/final/view/media/cart.svg">
            	<div class="w_h_c_num">
            		<%
            			Order order = (Order)request.getSession().getAttribute("order");
            			if (order != null)
            				out.println(order.getCart().size());
            			else
            				out.println(0);
            		%>
            	</div>
            </a>
        </div>
    </div>
</body>
</html>