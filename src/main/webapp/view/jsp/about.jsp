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
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/about.css">
    <fmt:setLocale value="${lang}"/>
	<fmt:setBundle basename="resources" var="lang"/>
    <title><fmt:message key="inter.about.title" bundle="${lang}"/></title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div class="body">
        <div class="blank"></div>
        <div class="navigation">
            <a href="http://localhost:8080/final/server/home"><fmt:message key="inter.about.home" bundle="${lang}"/></a>
            <img src="http://localhost:8080/final/view/media/5f8f84f3d4d23a31c1f2fcae_arrow-right-mini-icon.svg">
            <fmt:message key="inter.about.navigation" bundle="${lang}"/>
        </div>
        <div class="all">
            <div class="a_int">
                <p class="a_green"><fmt:message key="inter.about.slogan" bundle="${lang}"/></p>
                <h1><fmt:message key="inter.about.intro" bundle="${lang}"/></h1>
                <fmt:message key="inter.about.intro.text" bundle="${lang}"/>
                <a href=""><fmt:message key="inter.about.intro.more" bundle="${lang}"/></a>
            </div>
            <img class="back" src="http://localhost:8080/final/view/media/5f8f84f3d4d23a5f74f2fcaf_about-image.jpg">
            <h1 class="margin"><fmt:message key="inter.about.banner.a.text" bundle="${lang}"/></h1>
            <div class="banner">
                <div class="b_info">
                    <h1><fmt:message key="inter.about.banner.a.main" bundle="${lang}"/></h1>
                    <fmt:message key="inter.about.banner.a.text" bundle="${lang}"/>
                    <p></p>
                    <a class="button" href="http://localhost:8080/final/server/catalog"><fmt:message key="inter.about.banner.a.btn" bundle="${lang}"/></a>
                </div>
                <img class="move" src="http://localhost:8080/final/view/media/insta/5f8f84f3d4d23a6c9cf2fcb1_side-image-01.jpg">
            </div>
            <div class="banner">
                <img class="move" src="http://localhost:8080/final/view/media/insta/5f8f84f3d4d23a618af2fcb0_side-image-02.jpg">
                <div class="b_info">
                    <h1><fmt:message key="inter.about.banner.b.main" bundle="${lang}"/></h1>
                    <fmt:message key="inter.about.banner.b.text" bundle="${lang}"/>
                    <p></p>
                    <a class="button" href="http://localhost:8080/final/server/catalog"><fmt:message key="inter.about.banner.b.btn" bundle="${lang}"/></a>
                </div>
            </div>
            <div class="story">
                <fmt:message key="inter.about.story.title" bundle="${lang}"/>
                <h1><fmt:message key="inter.about.story.slogan" bundle="${lang}"/></h1>
                <fmt:message key="inter.about.story.text" bundle="${lang}"/>
                <button onclick="myFunction();return false;"><fmt:message key="inter.about.story.btn" bundle="${lang}"/></button>
                
            </div>
            <div id="video"></div>
        </div>
        <jsp:include page="footer.jsp"/>
    </div>
</body>
</html>