<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
<%@ page import="entities.Product" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="https://fonts.googleapis.com/css2?family=Varela+Round&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="http://localhost:8080/final/view/css/main.css">
	<link rel="stylesheet" href="http://localhost:8080/final/view/css/header.css">
	<link rel="stylesheet" href="http://localhost:8080/final/view/css/menu.css">
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/admin.css">
	<title>Admin catalog</title>
</head>
<body>
	<jsp:include page="header.jsp"/>
    <div class="body">
        <div class="folder">
            <table>
                <tr>
                    <th>Name</th>
                    <th>Category</th>
                    <th>Gender</th>
                    <th>Producer</th>
                    <th>Price</th>
                    <th>Photo</th>
                </tr>
                <c:forEach var="prod" items="${products}">
				    <tr>
				    	<td>${prod.getName()}</td>
				    	<td>${prod.getCategory()}</td>
                        <td>${prod.getGender()}</td>
                        <td>${prod.getProducer()}</td>
                        <td>${prod.getPrice()}</td>
                        <td><a class="link" href="http://localhost:8080/final/view/media/${prod.getCategory()}/${prod.getPhoto()}">${prod.getPhoto()}</a></td>
                        <td><form method="POST" action="http://localhost:8080/final/server/remove_product?id=${prod.getId()}"><input type="submit" class="link" value="Delete"></input></form></td>
                        </tr>
				</c:forEach>
                <!-- % 
                	List<Product> products = (List<Product>)request.getAttribute("products");
                	String html = "<tr>" +
                            "<td>%s</td>" +
                            "<td>%s</td>" +
                            "<td>%s</td>" +
                            "<td>%s</td>" +
                            "<td>%.2f</td>" +
                            "<td><a class=\"link\" href=\"http://localhost:8080/final/view/media/%s/%s\">%s</a></td>" +
                            "<td><form method=\"POST\" action=\"http://localhost:8080/final/server/remove_product?id=%d\"><input type=\"submit\" class=\"link\" value=\"Delete\"></input></form></td>" +
                        	"</tr>";
                	for (Product prod : products) {
                		out.println(String.format(html, prod.getName(), prod.getCategory(), 
                				prod.getGender(), prod.getProducer(), prod.getPrice(), 
                				prod.getCategory(), prod.getPhoto(), prod.getPhoto(), prod.getId()));
                	}
                %-->
            </table>
            <form method="POST" action="http://localhost:8080/final/server/confirm_order">
            	<input type="hidden" name="order_id" value="${order.getId()}">
            	<input class="button" type="submit" value="Buy">
            </form>
        </div>
    </div>
</body>
</html>