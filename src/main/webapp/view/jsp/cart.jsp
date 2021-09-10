<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
<%@ page import="entities.Product" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
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
                <% 
                	List<Product> products = (List<Product>)request.getAttribute("products");
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
            <form method="POST" action="http://localhost:8080/final/server/confirm">
            	<input class="hidden" value="${order.getId()}">
            	<input class="button" type="submit" value="Buy">
            </form>
        </div>
    </div>
</body>
</html>