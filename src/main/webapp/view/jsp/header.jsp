<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="entities.User" %>
<!DOCTYPE html>
<html lang="en">
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
            <div class="panel">
	            <%
	            	String html;
	            	User user = (User)session.getAttribute("user");
	            	if (user == null) {
	            		html = "<a href=\"http://localhost:8080/final/server/sign_in\" class=\"profile\">" +
	                    		"<p>Sign in</p>" +
	                        	"</a>";
	            	} else {
	            		html = "<a href=\"http://localhost:8080/final/server/profile\" class=\"profile\">" +
	                    		"<p>" + user.getName() + "</p>" +
	                        	"</a>" +
	                        	"<a href=\"http://localhost:8080/final/server/sign_out\" class=\"profile\">" +
	                    		"<p>Sign out</p>" +
	                        	"</a>";
	            	}
	            	out.println(html);
	            %>
            </div>
        </div>
        <div class="white_head">
            <h1>ConstStore</h1>
            <div class="w_h_links">
                <a href="http://localhost:8080/final/server/catalog">Catalog</a>
                <a href="http://localhost:8080/final/server/delivery">Delivery</a>
                <a href="http://localhost:8080/final/server/about">About</a>
                <a href="http://localhost:8080/final/server/contacts">Contacts</a>
            </div>
            <div class="w_h_blank"></div>
            <a class="w_h_cart" href="http://localhost:8080/final/server/cart">
            	<div class="w_h_c_text">
            		Cart
            	</div>
            	<img src="http://localhost:8080/final/view/media/cart.svg">
            	<div class="w_h_c_num">
            		3
            	</div>
            </a>
        </div>
    </div>
</body>
</html>