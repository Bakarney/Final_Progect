<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="entities.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Varela+Round&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/main.css">
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/profile.css">
    <script src="main.js"></script>
    <title>Document</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <%
    	User user = (User)session.getAttribute("user");
    %>
    <div class="body">
        <div class="folder">
            <div class="data">
                <div class="fields">
                    <p>Name:</p>
                    <p>Email:</p>
                    <p>Phone:</p>
                    <p>Address:</p>
                    <p>Card:</p>
                </div>
                <div class="info">
                    <p><%= user.getName() %></p>
                    <p><%= user.getEmail() %></p>
                    <p><%= user.getPhone() %></p>
                    <p><%= user.getAddress() %></p>
                    <p><%= user.getCard() %></p>
                </div>
            </div>
            <form method="POST" action="http://localhost:8080/final/server/sign_out">
            	<input class="button" type="submit" value="Sign Out">
            </form>
        </div>
    </div>
</body>
</html>