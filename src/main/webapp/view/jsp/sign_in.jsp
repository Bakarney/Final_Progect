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
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/sign_in.css">
    <script src="main.js"></script>
    <title>Document</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div class="body">
        <form class="sign_in_form" method="POST" action="http://localhost:8080/final/server/sign_in">
            <p>Sign in</p>
            <%
            	String error = request.getParameter("error");
            	if (error != null)
            		out.println(error);
            %>
            <input name="email" class="input" type="email" placeholder="Email">
            <input name="password" class="input" type="password" placeholder="Password">
            <div class="buttons">
                <a class="button a_button" href="http://localhost:8080/final/server/sign_up">Sign up</a>
                <input class="button" type="submit" value="Sign in">
            </div>
        </form>
    </div>
</body>
</html>