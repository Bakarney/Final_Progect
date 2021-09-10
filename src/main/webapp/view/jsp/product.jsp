<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="entities.Product" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Varela+Round&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/main.css">
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/product.css">
    <script src="main.js"></script>
    <title>${product.getName()}</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div class="body">
        <div class="folder">
            <img src="http://localhost:8080/final/view/media/${product.getCategory()}/${product.getPhoto()}">
            <div class="data">
                <div class="fields">
                    <p>Name:</p>
                    <p>Category:</p>
                    <p>Description:</p>
                    <p>Gender:</p>
                    <p>Producer:</p>
                    <p>Price:</p>
                </div>
                <div class="info">
                    <p>${product.getName()}</p>
                    <p>${product.getCategory()}</p>
                    <p>${product.getDescription()}</p>
                    <p>${product.getGender()}</p>
                    <p>${product.getProducer()}</p>
                    <p>${product.getPrice()}</p>
                </div>
            </div>
            <form method="POST" action="http://localhost:8080/final/view/media/add_product">
            	<input type="hidden" name="product_id" value="${product.getId()}">
            	<input class="button" type="submit" value="Buy">
            </form>
        </div>
    </div>
</body>
</html>