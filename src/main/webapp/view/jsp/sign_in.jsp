<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/sign_in.css">
    <fmt:setLocale value="${lang}"/>
	<fmt:setBundle basename="resources" var="lang"/>
    <title><fmt:message key="inter.sign_in.title" bundle="${lang}"/></title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div class="body">
        <form class="sign_in_form" method="POST" action="http://localhost:8080/final/server/sign_in">
            <p><fmt:message key="inter.sign_in.form" bundle="${lang}"/></p>
            <%
            	String error = request.getParameter("error");
            	if (error != null)
            		out.println(error);
            %>
            <input name="email" class="input" type="email" placeholder="<fmt:message key="inter.sign_in.input.email" bundle="${lang}"/>">
            <input name="password" class="input" type="password" placeholder="<fmt:message key="inter.sign_in.input.password" bundle="${lang}"/>">
            <div class="buttons">
                <a class="button a_button" href="http://localhost:8080/final/server/sign_up"><fmt:message key="inter.sign_in.up" bundle="${lang}"/></a>
                <input class="button" type="submit" value="<fmt:message key="inter.sign_in.in" bundle="${lang}"/>">
            </div>
        </form>
    </div>
</body>
</html>