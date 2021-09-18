<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="entities.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Varela+Round&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/main.css">
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/header.css">
    <script src="main.js"></script>
    <title>Header</title>
</head>
<body>
    <div class="header">
        <div class="green_head">
            <div class="social">
                <a href=""><img src="http://localhost:8080/final/view/media/social/twitter.svg"></a>
                <a href=""><img src="http://localhost:8080/final/view/media/social/facebook.svg"></a>
                <a href=""><img src="http://localhost:8080/final/view/media/social/instagram.svg"></a>
                <a href=""><img src="http://localhost:8080/final/view/media/social/pinterest.svg"></a>
                <a href=""><img src="http://localhost:8080/final/view/media/social/youtube.svg"></a>
            </div>
            <form method="POST" action="http://localhost:8080/final/server/sign_out" class="profile">
            	<input type="submit" class="simple_btn" value="Sign out">
            </form>
        </div>
        <div class="white_head">
            <h1>ConstStore</h1>
            <div class="w_h_links">
                <a href="http://localhost:8080/final/server/admin_catalog">Catalog</a>
                <a href="http://localhost:8080/final/server/admin_users">Users</a>
                <a href="http://localhost:8080/final/server/admin_categories">Categories</a>
                <a href="http://localhost:8080/final/server/admin_producers">Producers</a>
                <a href="http://localhost:8080/final/server/admin_orders">Orders</a>
            </div>
            <div class="w_h_blank"></div>
        </div>
    </div>
</body>
</html>