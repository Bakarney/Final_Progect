<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Varela+Round&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/main.css">
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/home.css">
    <fmt:setLocale value="${lang}"/>
	<fmt:setBundle basename="resources" var="lang"/>
    <title><fmt:message key="inter.home.title" bundle="${lang}"/></title>
</head>
<body>
    <jsp:include page="header.jsp"/>
	<div class="body">
        <div class="welcome">
            <h1><fmt:message key="inter.home.welcome" bundle="${lang}"/></h1>
            <a class="button" href="http://localhost:8080/final/server/catalog"><fmt:message key="inter.home.catalog.btn" bundle="${lang}"/></a>
        </div>
        <div class="two">
            <div class="t_plastic">
                <img src="http://localhost:8080/final/view/media/plastic toys/train.png">
                <div class="t_p_text">
                    <h1><fmt:message key="inter.home.catalog.plastic" bundle="${lang}"/></h1>
                    <a class="button" href="http://localhost:8080/final/server/catalog"><fmt:message key="inter.home.catalog.plastic.btn" bundle="${lang}"/></a>
                </div>
            </div>
            <div class="t_wooden">
                <div class="t_w_text">
                    <h1><fmt:message key="inter.home.catalog.wood" bundle="${lang}"/></h1>
                    <a class="button" href="http://localhost:8080/final/server/catalog"><fmt:message key="inter.home.catalog.wood.btn" bundle="${lang}"/></a>
                </div>
                <img src="http://localhost:8080/final/view/media/wooden toys/flower.png">
            </div>
        </div>
        <div class="plastic">
            <div class="p_header">
                <h1><fmt:message key="inter.home.plastic" bundle="${lang}"/></h1>
                <a href="http://localhost:8080/final/server/catalog" class="p_h_link"><fmt:message key="inter.home.plastic.all" bundle="${lang}"/><img src="http://localhost:8080/final/view/media/5f8f84f3d4d23a31c1f2fcae_arrow-right-mini-icon.svg"></a>
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
                <h1><fmt:message key="inter.home.wood" bundle="${lang}"/></h1>
                <a href="http://localhost:8080/final/server/catalog" class="p_h_link"><fmt:message key="inter.home.wood.all" bundle="${lang}"/><img src="http://localhost:8080/final/view/media/5f8f84f3d4d23a31c1f2fcae_arrow-right-mini-icon.svg"></a>
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
            <fmt:message key="inter.home.story.title" bundle="${lang}"/>
            <h1><fmt:message key="inter.home.story.slogan" bundle="${lang}"/></h1>
            <fmt:message key="inter.home.story.text" bundle="${lang}"/>
            <button onclick="myFunction();return false;"><fmt:message key="inter.home.story.btn" bundle="${lang}"/></button>
        </div>
        <div class="video" id="video"></div>
        <h1 class="text"><fmt:message key="inter.home.slogan.a" bundle="${lang}"/></h1>
        <div class="ad">
            <div class="a_info">
                <h1><fmt:message key="inter.home.slogan.b" bundle="${lang}"/></h1>
                <p><fmt:message key="inter.home.slogan.b.text" bundle="${lang}"/></p>
                <a class="button" href=""><fmt:message key="inter.home.slogan.btn" bundle="${lang}"/></a>
            </div>
            <img src="http://localhost:8080/final/view/media/5f8f8d03a222a54f11c1161e_26318.jpg">
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </div>
</body>
</html>