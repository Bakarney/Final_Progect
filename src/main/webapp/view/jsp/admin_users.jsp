<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
<%@ page import="entities.User" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link href="https://fonts.googleapis.com/css2?family=Varela+Round&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="http://localhost:8080/final/view/css/main.css">
	<link rel="stylesheet" href="http://localhost:8080/final/view/css/header.css">
	<link rel="stylesheet" href="http://localhost:8080/final/view/css/menu.css">
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/admin.css">
	<title>Admin users</title>
</head>
<body>
	<jsp:include page="admin_header.jsp"/>
    <div class="body">
        <div class="folder">
            <table>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Address</th>
                    <th>Card</th>
                    <th>Admin</th>
                    <th>Active</th>
                </tr>
                <% 
                	List<User> users = (List<User>)request.getAttribute("users");
                	String html = "<tr>" +
                            "<td>%d</td>" +
                            "<td>%s</td>" +
                            "<td>%s</td>" +
                            "<td>%s</td>" +
                            "<td>%s</td>" +
                            "<td>%s</td>" +
                            "<td>%s</td>" +
                            "<td>%s</td>" +
                            "<td><a class=\"link\" href=\"http://localhost:8080/final/server/block_user?id=%d\">%s</a></td>" +
                            "<td><a class=\"link\" href=\"http://localhost:8080/final/server/admin_user?id=%d\">Admin</a></td>" +
                            "<td><a class=\"link\" href=\"http://localhost:8080/final/server/delete_user?id=%d\">Delete</a></td>" +
                        	"</tr>";
                	for (User user : users) {
                		out.println(String.format(html, user.getId(), user.getName(), user.getEmail(), 
                				user.getPhone(), user.getAddress(), user.getCard(), (user.isAdmin()) ? "Yes" : "No", (user.isActive()) ? "Yes" : "No",
                				user.getId(), (user.isActive()) ? "Block" : "Unblock", user.getId(), user.getId()));
                	}
                %>
            </table>
        </div>
    </div>
</body>
</html>