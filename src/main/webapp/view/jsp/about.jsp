<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Varela+Round&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/main.css">
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/about.css">
    <script src="../main.js"></script>
    <title>About</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div class="body">
        <div class="blank"></div>
        <div class="navigation">
            <a href="http://localhost:8080/final/server/home">Home</a>
            <img src="http://localhost:8080/final/view/media/5f8f84f3d4d23a31c1f2fcae_arrow-right-mini-icon.svg">
            About
        </div>
        <div class="all">
            <div class="a_int">
                <p class="a_green">All You Need is Fun!</p>
                <h1>Introducing ConstStore</h1>
                Our store has a rich history of 10 years. During this time, we have made tens of thousands of children happy with hundreds of thousands of toys.
                <a href="">More About Us</a>
            </div>
            <img class="back" src="http://localhost:8080/final/view/media/5f8f84f3d4d23a5f74f2fcaf_about-image.jpg">
            <h1 class="margin">It will be one of the greatest activity for your child</h1>
            <div class="banner">
                <div class="b_info">
                    <h1>Beautifully Designed</h1>
                    Our best designers work day and night to make our toys as pleasant and fun as possible for your child. Each of them is a work of children's art.
                    <p></p>
                    <a class="button" href="http://localhost:8080/final/server/catalog">Get It Now!</a>
                </div>
                <img class="move" src="http://localhost:8080/final/view/media/insta/5f8f84f3d4d23a6c9cf2fcb1_side-image-01.jpg">
            </div>
            <div class="banner">
                <img class="move" src="http://localhost:8080/final/view/media/insta/5f8f84f3d4d23a618af2fcb0_side-image-02.jpg">
                <div class="b_info">
                    <h1>Beautifully Designed</h1>
                    Our best designers work day and night to make our toys as pleasant and fun as possible for your child. Each of them is a work of children's art.
                    <p></p>
                    <a class="button" href="http://localhost:8080/final/server/catalog">Get It Now!</a>
                </div>
            </div>
            <div class="story">
                About The Shop
                <h1>Watch Our Story</h1>
                Is's great story about our age, heroism and total dedication. You will not regret the time spent.
                <button onclick="myFunction();return false;">Click me </button>
                
            </div>
            <div id="video"></div>
        </div>
        <jsp:include page="footer.jsp"/>
    </div>
</body>
</html>