<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Varela+Round&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/main.css">
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/sign_up.css">
    <script src="main.js"></script>
    <title>Document</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div class="body">
        <form class="folder" method="POST" action="http://localhost:8080/final/server/sign_up">
            <div class="main">
                <div class="data">
                    <div class="fields">
                        <p>Name:</p>
                        <p>Email:</p>
                        <p>Password:</p>
                        <p>Phone:</p>
                        <p>Address:</p>
                        <p>Card:</p>
                    </div>
                    <div class="info">
                        <input name="name" class="input" type="text">
                        <input name="email" class="input" type="email">
                        <input name="password" class="input" type="password">
                        <input name="phone" class="input" type="number">
                        <input name="address" class="input" type="text">
                        <input name="card" class="input" type="number">
                    </div>
                </div>
            </div>
            <input class="button" type="submit" value="Sign up">
        </form>
    </div>
</body>
</html>