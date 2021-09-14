<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %> 
<%@ page import="entities.*" %> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="https://fonts.googleapis.com/css2?family=Varela+Round&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="http://localhost:8080/final/view/css/order_conflict.css">
	<title>Order conflict</title>
</head>
<body>
	<div class="folder">
        <div class="data">
            <h1 class="info">Conflict between local and cloud carts was found!</h1>
            <div class="tables">
                <div class="table">
                    <h1>Local cart</h1>
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
                    <h1>Cloud cart</h1>
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
                <input type="submit" class="button" value="Save local">
            </form>
            <form method="POST" action="http://localhost:8080/final/server/manage_carts">
                <input type="hidden" name="command" value="cloud">
                <input type="submit" class="button" value="Save cloud">
            </form>
            <form method="POST" action="http://localhost:8080/final/server/manage_carts">
                <input type="hidden" name="command" value="combine">
                <input type="submit" class="button" value="Combine local and cloud">
            </form>
        </div>
    </div>
</body>
</html>