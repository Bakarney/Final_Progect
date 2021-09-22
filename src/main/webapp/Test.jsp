<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Test page</title>
</head>
<body>
	Hello client
	<fmt:setLocale value="ru"/>
	<fmt:setBundle basename="resources" var="lang"/>
	
	<fmt:message key="inter.about.home" bundle="${lang}"/>
</body>
</html>