<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
<%@ page import="entities.Product" %>
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
	<jsp:include page="admin_header.jsp"/>
    <div class="body">
        <div class="folder">
            <table>
                <tr>
                    <th>
                    	<div class="hamburger-menu">
			                <input id="menu__toggle" type="checkbox" />
			                <label class="menu__btn" for="menu__toggle">
			                    <span></span>
			                </label>
			                <form class="menu__box">
			                	<% String sort = request.getParameter("sort"); %>
			                	<label class="filter_label">Sorted by:</label>
			                	<div>
		                            <input type="radio" id="huey" name="sort" value="a-z" <% if (sort == null || sort.equals("a-z")) out.print("checked"); %>>
		                        	<label for="huey">Name (A-Z)</label>
		                        </div>
		                        <div>
		                            <input type="radio" id="huey" name="sort" value="z-a" <% if (sort != null && sort.equals("z-a")) out.print("checked"); %>>
		                            <label for="huey">Name (Z-A)</label>
		                        </div>
		                        <div>
		                            <input type="radio" id="huey" name="sort" value="l-h" <% if (sort != null && sort.equals("l-h")) out.print("checked"); %>>
		                            <label for="huey">Price (Low-High)</label>
		                        </div>
		                        <div>
		                            <input type="radio" id="huey" name="sort" value="h-l" <% if (sort != null && sort.equals("h-l")) out.print("checked"); %>>
		                            <label for="huey">Price (High-Low)</label>
		                        </div>
		                        <div>
		                            <input type="radio" id="huey" name="sort" value="new" <% if (sort != null && sort.equals("new")) out.print("checked"); %>>
		                            <label for="huey">Novelty (New-Old)</label>
		                        </div>
		                        <div>
		                            <input type="radio" id="huey" name="sort" value="old" <% if (sort != null && sort.equals("old")) out.print("checked"); %>>
		                            <label for="huey">Novelty (Old-New)</label>
		                        </div>
		                        
		                        <% String gender = request.getParameter("gender"); %>
		                        <label class="filter_label">Gender:</label>
		                        <div>
		                            <input type="radio" id="huey" name="gender" value="male" <% if (gender != null && gender.equals("male")) out.print("checked"); %>>
		                            <label for="huey">Male</label>
		                        </div>
		                        <div>
		                            <input type="radio" id="huey" name="gender" value="female" <% if (gender != null && gender.equals("female")) out.print("checked"); %>>
		                            <label for="huey">Female</label>
		                        </div>
		                        <div>
		                            <input type="radio" id="huey" name="gender" value="unisex" <% if (gender == null || gender.equals("unisex")) out.print("checked"); %>>
		                            <label for="huey">Unisex</label>
		                        </div>
		                        
		                        <label class="filter_label">Producer:</label>
		                        <%
		                        	String html = "<div>" +
		                                    "<input type=\"checkbox\" id=\"huey\" name=\"producer\" value=\"%s\" %s>" + 
		                                    "<label for=\"huey\">%s</label>" + 
		                                	"</div>";
		                            String[] producer = request.getParameterValues("producer");
		                            List<String> producers = (List<String>)request.getAttribute("producers");
		                            for (String i : producers) {
		                            	out.println(String.format(html, i, (producer == null || Arrays.stream(producer).anyMatch(i::equals)) ? "checked" : "", i));
		                            }
		                        %>
		                        
		                 		<label class="filter_label">Category:</label>
		                 		<%
		                        	html = "<div>" +
		                                    "<input type=\"checkbox\" id=\"huey\" name=\"category\" value=\"%s\" %s>" + 
		                                    "<label for=\"huey\">%s</label>" + 
		                                	"</div>";
		                            String[] category = request.getParameterValues("category");
		                            List<String> categories = (List<String>)request.getAttribute("categories");
		                            for (String i : categories) {
		                            	out.println(String.format(html, i, (category == null || Arrays.stream(category).anyMatch(i::equals)) ? "checked" : "", i));
		                            }
		                        %>
		                        
		                        <label class="filter_label">Price:</label>
		                        <div>
		                        	From:
		                        	<input class="price" type="number" name="bot" value="<%= (request.getParameter("bot") == null) ? "0" : request.getParameter("bot") %>">
		                        	To:
		                        	<input class="price" type="number" name="top" value="<%= (request.getParameter("top") == null) ? "100" : request.getParameter("top") %>">
		                        </div>
		                        
		                        <input class="button menu_btn" type="submit" value="Filter">
			                </form>
		        		</div>
                    </th>
                    <th>Name</th>
                    <th>Category</th>
                    <th>Gender</th>
                    <th>Producer</th>
                    <th>Number</th>
                    <th>Price</th>
                    <th>Photo</th>
                    <th></th>
                    <th></th>
                </tr>
                <% 
                	List<Product> products = (List<Product>)request.getAttribute("products");
                	html = "<tr>" +
                            "<td>%s</td>" +
                            "<td>%s</td>" +
                            "<td>%s</td>" +
                            "<td>%s</td>" +
                            "<td>%s</td>" +
                            "<td>%d</td>" +
                            "<td>%.2f</td>" +
                            "<td><a class=\"link\" href=\"http://localhost:8080/final/view/media/%s/%s\">%s</td>" +
                            "<td><a class=\"link\" href=\"http://localhost:8080/final/server/admin_product?name=%s\">Edit</a></td>" +
                            "<td><form action=\"http://localhost:8080/final/server/delete_product?id=%d\" method=\"POST\"><input type=\"submit\" class=\"link\" value=\"Delete\"></input></form></td>" +
                        	"</tr>";
                	for (Product prod : products) {
                		out.println(String.format(html, prod.getId(), prod.getName(), prod.getCategory(), 
                				prod.getGender(), prod.getProducer(), prod.getNumber(), prod.getPrice(), 
                				prod.getCategory(), prod.getPhoto(), prod.getPhoto(), prod.getName(), prod.getId()));
                	}
                %>
                <tr>
                	<th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th><a class="link" href="http://localhost:8080/final/server/create_product">Create</a></th>
                </tr>
            </table>
        </div>
    </div>
</body>
</html>