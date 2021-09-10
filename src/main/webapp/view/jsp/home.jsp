<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Varela+Round&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/main.css">
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/home.css">
    <script src="main.js"></script>
    <title>Home</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
<div class="body">
        <div class="welcome">
            <h1>Welcome on territory of fun</h1>
            <a class="button" href="http://localhost:8080/final/server/catalog">Open Catalog</a>
        </div>
        <div class="two">
            <div class="t_plastic">
                <img src="http://localhost:8080/final/view/media/plastic toys/train.png">
                <div class="t_p_text">
                    <h1>Plastic Constructor</h1>
                    <a class="button" href="http://localhost:8080/final/server/catalog">Shop Now</a>
                </div>
            </div>
            <div class="t_wooden">
                <div class="t_w_text">
                    <h1>Wooden Constructor</h1>
                    <a class="button" href="http://localhost:8080/final/server/catalog">Shop Now</a>
                </div>
                <img src="http://localhost:8080/final/view/media/wooden toys/flower.png">
            </div>
        </div>
        <div class="plastic">
            <div class="p_header">
                <h1>Plastic constructor</h1>
                <a href="http://localhost:8080/final/server/catalog" class="p_h_link">See All Toys<img src="http://localhost:8080/final/view/media/5f8f84f3d4d23a31c1f2fcae_arrow-right-mini-icon.svg"></a>
            </div>
            <div class="showcase">
                <div class="s_row">
                    <a href="" class="product">
                        <img src="http://localhost:8080/final/view/media/plastic toys/railway.png">
                        <div class="p_info">
                            <p class="p_i_text">LegoCity Railway</p>
                            <p class="p_i_price">$ 30.00 USD</p>
                        </div>
                    </a>
                    <a href="" class="product">
                        <img src="http://localhost:8080/final/view/media/plastic toys/snow.png">
                        <div class="p_info">
                            <p class="p_i_text">LegoCity Snowplow</p>
                            <p class="p_i_price">$ 38.00 USD</p>
                        </div>
                    </a>
                    <a href="" class="product">
                        <img src="http://localhost:8080/final/view/media/plastic toys/mine.png">
                        <div class="p_info">
                            <p class="p_i_text">LegoMinecraft The Vilage</p>
                            <p class="p_i_price">$ 24.00 USD</p>
                        </div>
                    </a>
                    <a href="" class="product">
                        <img src="http://localhost:8080/final/view/media/plastic toys/police.png">
                        <div class="p_info">
                            <p class="p_i_text">LegoCity Police</p>
                            <p class="p_i_price">$ 27.00 USD</p>
                        </div>
                    </a>
                </div>
            </div>
        </div>
        <div class="plastic">
            <div class="p_header">
                <h1>Wooden Toys</h1>
                <a href="http://localhost:8080/final/server/catalog" class="p_h_link">See All Toys<img src="http://localhost:8080/final/view/media/5f8f84f3d4d23a31c1f2fcae_arrow-right-mini-icon.svg"></a>
            </div>
            <div class="showcase">
                <div class="s_row">
                    <a href="" class="product">
                        <img src="http://localhost:8080/final/view/media/wooden toys/flower.png">
                        <div class="p_info">
                            <p class="p_i_text">Happy Flower</p>
                            <p class="p_i_price">$ 38.00 USD</p>
                        </div>
                    </a>
                    <a href="" class="product">
                        <img src="http://localhost:8080/final/view/media/wooden toys/machine.png">
                        <div class="p_info">
                            <p class="p_i_text">Lift Machine</p>
                            <p class="p_i_price">$ 24.00 USD</p>
                        </div>
                    </a>
                    <a href="" class="product">
                        <img src="http://localhost:8080/final/view/media/wooden toys/photo.png">
                        <div class="p_info">
                            <p class="p_i_text">Wooden Camera</p>
                            <p class="p_i_price">$ 32.00 USD</p>
                        </div>
                    </a>
                    <a href="" class="product">
                        <img src="http://localhost:8080/final/view/media/wooden toys/rabbit.png">
                        <div class="p_info">
                            <p class="p_i_text">Little Rabit</p>
                            <p class="p_i_price">$ 16.00 USD</p>
                        </div>
                    </a>
                </div>
            </div>
        </div>
        <div class="story">
            About The Shop
            <h1>Watch Our Story</h1>
            Is's great story about courage, heroism and total dedication. You will not regret the time spent.
            <button onclick="myFunction();return false;">Click me </button>
        </div>
        <div class="video" id="video"></div>
        <h1 class="text">Simple & Colorful Playtime for All Family</h1>
        <div class="ad">
            <div class="a_info">
                <h1>Don't waste you time!</h1>
                <p>We have special offers for fun activities for the whole family. The Game Set features several games suitable for everyone, young and old.It includes board games such as Jenga and Wolves and Sheep, as well as two wooden construction sets with fully functioning mechanisms.</p>
                <a class="button" href="">GET IT NOW!</a>
            </div>
            <img src="http://localhost:8080/final/view/media/5f8f8d03a222a54f11c1161e_26318.jpg">
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </div>
</body>
</html>