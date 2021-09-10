<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
</head>
<body>
	<form class="folder" method="POST" action="http://localhost:8080/final/test" enctype="multipart/form-data">
        <table>
            <tr>
                <th>ID</th>
                <th>${product.getId()}</th>
                <th><input name="id" type="number" value="${product.getId()}" readonly></th>
            </tr>
            <tr>
                <th>Name</th>
                <th>${product.getName()}</th>
                <th><input name="name" type="text" value="${product.getName()}"></th>
            </tr>
            <tr>
                <th>Category</th>
                <th>${product.getCategory()}</th>
                <th><input name="category" type="text" value="${product.getCategory()}"></th>
            </tr>
            <tr>
                <th>Gender</th>
                <th>${product.getGender()}</th>
                <th><input name="gender" type="text" value="${product.getGender()}"></th>
            </tr>
            <tr>
                <th>Producer</th>
                <th>${product.getProducer()}</th>
                <th><input name="producer" type="text" value="${product.getProducer()}"></th>
            </tr>
            <tr>
                <th>Number</th>
                <th>${product.getNumber()}</th>
                <th><input  name="number" type="number" value="${product.getNumber()}"></th>
            </tr>
            <tr>
                <th>Price</th>
                <th>${product.getPrice()}</th>
                <th><input name="price" type="number" value="${product.getPrice()}"></th>
            </tr>
            <tr>
                <th>Photo</th>
                <th><a class="link" href="http://localhost:8080/final/view/media/${product.getCategory()}/${product.getPhoto()}">${product.getPhoto()}</th>
                <th><input name="photo" type="file"></th>
            </tr>
        </table>
        <input class="button" type="submit" value="Update">
    </form>
    <form method="post" action="http://localhost:8080/final/test" enctype="multipart/form-data">
        <input type="text" name="text" value="text">
        <input type="file" name="file"/>
        <input type="submit" value="Upload"/>
    </form>
</body>
</html>