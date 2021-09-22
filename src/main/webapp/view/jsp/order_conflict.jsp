<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %> 
<%@ page import="entities.*" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="https://fonts.googleapis.com/css2?family=Varela+Round&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="http://localhost:8080/final/view/css/order_conflict.css">
    <fmt:setLocale value="${lang}"/>
	<fmt:setBundle basename="resources" var="lang"/>
	<title><fmt:message key="inter.conflict.title" bundle="${lang}"/></title>
</head>
<body>
	<div class="folder">
        <div class="data">
            <h1 class="info"><fmt:message key="inter.conflict.text" bundle="${lang}"/></h1>
            <div class="tables">
                <div class="table">
                    <h1><fmt:message key="inter.conflict.local" bundle="${lang}"/></h1>
                    <table>
                        <tr>
                            <th><fmt:message key="inter.conflict.name" bundle="${lang}"/></th>
                            <th><fmt:message key="inter.conflict.category" bundle="${lang}"/></th>
                            <th><fmt:message key="inter.conflict.gender" bundle="${lang}"/></th>
                            <th><fmt:message key="inter.conflict.producer" bundle="${lang}"/></th>
                            <th><fmt:message key="inter.conflict.price" bundle="${lang}"/></th>
                            <th><fmt:message key="inter.conflict.photo" bundle="${lang}"/></th>
                        </tr>
                        <% 
		                	List<Product> products = (List<Product>)request.getAttribute("local_products");
		                	String html = "<tr>" +
		                            "<td>%s</td>" +
		                            "<td>%s</td>" +
		                            "<td>%s</td>" +
		                            "<td>%s</td>" +
		                            "<td>%.2f</td>" +
		                            "<td><a class=\"link\" href=\"http://localhost:8080/final/view/media/%s/%s\">%s</td>" +
		                        	"</tr>";
		                	for (Product prod : products) {
		                		out.println(String.format(html, prod.getName(), prod.getCategory(), 
		                				prod.getGender(), prod.getProducer(), prod.getPrice(), 
		                				prod.getCategory(), prod.getPhoto(), prod.getPhoto()));
		                	}
		                %>
                    </table>
                </div>
                <div class="table">
                    <h1><fmt:message key="inter.conflict.cloud" bundle="${lang}"/></h1>
                    <table>
                        <tr>
                            <th><fmt:message key="inter.conflict.name" bundle="${lang}"/></th>
                            <th><fmt:message key="inter.conflict.category" bundle="${lang}"/></th>
                            <th><fmt:message key="inter.conflict.gender" bundle="${lang}"/></th>
                            <th><fmt:message key="inter.conflict.producer" bundle="${lang}"/></th>
                            <th><fmt:message key="inter.conflict.price" bundle="${lang}"/></th>
                            <th><fmt:message key="inter.conflict.photo" bundle="${lang}"/></th>
                        </tr>
                        <% 
		                	products = (List<Product>)request.getAttribute("cloud_products");
		                	html = "<tr>" +
		                            "<td>%s</td>" +
		                            "<td>%s</td>" +
		                            "<td>%s</td>" +
		                            "<td>%s</td>" +
		                            "<td>%.2f</td>" +
		                            "<td><a class=\"link\" href=\"http://localhost:8080/final/view/media/%s/%s\">%s</td>" +
		                        	"</tr>";
		                	for (Product prod : products) {
		                		out.println(String.format(html, prod.getName(), prod.getCategory(), 
		                				prod.getGender(), prod.getProducer(), prod.getPrice(), 
		                				prod.getCategory(), prod.getPhoto(), prod.getPhoto()));
		                	}
		                %>
                    </table>
                </div>
            </div>
        </div>
        <div class="buttons">
            <form method="POST" action="http://localhost:8080/final/server/manage_carts">
                <input type="hidden" name="command" value="local">
                <input type="submit" class="button" value="<fmt:message key="inter.conflict.local.save" bundle="${lang}"/>">
            </form>
            <form method="POST" action="http://localhost:8080/final/server/manage_carts">
                <input type="hidden" name="command" value="cloud">
                <input type="submit" class="button" value="<fmt:message key="inter.conflict.cloud.save" bundle="${lang}"/>">
            </form>
            <form method="POST" action="http://localhost:8080/final/server/manage_carts">
                <input type="hidden" name="command" value="combine">
                <input type="submit" class="button" value="<fmt:message key="inter.conflict.combine.save" bundle="${lang}"/>">
            </form>
        </div>
    </div>
</body>
</html>