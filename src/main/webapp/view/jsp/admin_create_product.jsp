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
	<title>${product.getName()}</title>
</head>
<body>
	<jsp:include page="admin_header.jsp"/>
    <div class="body">
        <form class="folder" method="POST" action="http://localhost:8080/final/server/create_product" enctype="multipart/form-data">
            <table>
                <tr>
                    <th>Name</th>
                    <th><input name="name" type="text"></th>
                </tr>
                <tr>
                    <th>Category</th>
                    <th><input name="category" type="text"></th>
                </tr>
                <tr>
                    <th>Gender</th>
                    <th><input name="gender" type="text"></th>
                </tr>
                <tr>
                    <th>Producer</th>
                    <th><input name="producer" type="text"></th>
                </tr>
                <tr>
                    <th>Number</th>
                    <th><input  name="number" type="number"></th>
                </tr>
                <tr>
                    <th>Price</th>
                    <th><input name="price" type="number"></th>
                </tr>
                <tr>
                    <th>Photo</th>
                    <th><input name="photo" type="file"></th>
                </tr>
            </table>
            <input class="button" type="submit" value="Create">
        </form>
    </div>
</body>
</html>