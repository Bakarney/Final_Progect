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
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/footer.css">
    <fmt:setLocale value="${lang}"/>
	<fmt:setBundle basename="resources" var="lang"/>
    <title><fmt:message key="inter.footer.title" bundle="${lang}"/></title>
</head>
<body>
	<div class="foot">
            <div class="sub">
                <div class="s_info">
                    <img src="http://localhost:8080/final/view/media/social/telegram.svg">
                    <h1><fmt:message key="inter.footer.sub" bundle="${lang}"/><span><fmt:message key="inter.footer.sub.discount" bundle="${lang}"/></span></h1>
                </div>
                <form>
                    <input class="s_f_email" type="email" placeholder="<fmt:message key="inter.footer.sub.email" bundle="${lang}"/>">
                    <button type="submit"><fmt:message key="inter.footer.sub.btn" bundle="${lang}"/></button>
                </form>
            </div>
            <div class="insta">
                <div class="i_header">
                    <h1><fmt:message key="inter.footer.insta.title" bundle="${lang}"/></h1>
                    <a href=""><fmt:message key="inter.footer.insta.link" bundle="${lang}"/></a>
                </div>
                <div class="i_photos">
                    <div class="i_p_left">
                        <img src="http://localhost:8080/final/view/media/insta/5f8f84f3d4d23a4687f2fc98_instagram-02.jpg">
                        <img src="http://localhost:8080/final/view/media/insta/5f8f84f3d4d23a5bfdf2fc9b_instagram-03.jpg">
                        <img src="http://localhost:8080/final/view/media/insta/5f8f84f3d4d23a618af2fcb0_side-image-02.jpg">
                    </div>
                    <div class="i_p_left">
                        <img src="http://localhost:8080/final/view/media/insta/5f8f9748b2ae71b90dee5df4_1488193340773.jpg">
                        <img src="http://localhost:8080/final/view/media/insta/5f8f9789aeb3fbf6c6eafeed.jpg">
                        <img src="http://localhost:8080/final/view/media/insta/5f8f9806dd6620f0d2a4f2fc_30907.970.jpg">
                    </div>
                </div>
                <a class="button" href=""><fmt:message key="inter.footer.insta.btn" bundle="${lang}"/></a>
            </div>
            <div class="footer">
                <h1><fmt:message key="inter.footer.shop" bundle="${lang}"/></h1>
                <div class="f_links">
                    <a href="http://localhost:8080/final/server/catalog"><fmt:message key="inter.footer.link.a" bundle="${lang}"/></a>
                    <a href="http://localhost:8080/final/server/delivery"><fmt:message key="inter.footer.link.b" bundle="${lang}"/></a>
                    <a href="http://localhost:8080/final/server/about"><fmt:message key="inter.footer.link.c" bundle="${lang}"/></a>
                    <a href="http://localhost:8080/final/server/contacts"><fmt:message key="inter.footer.link.d" bundle="${lang}"/></a>
                </div>
                <div class="social">
                    <a href=""><img src="http://localhost:8080/final/view/media/social/twitter.svg"></a>
                    <a href=""><img src="http://localhost:8080/final/view/media/social/facebook.svg"></a>
                    <a href=""><img src="http://localhost:8080/final/view/media/social/instagram.svg"></a>
                    <a href=""><img src="http://localhost:8080/final/view/media/social/pinterest.svg"></a>
                    <a href=""><img src="http://localhost:8080/final/view/media/social/youtube.svg"></a>
                </div>
            </div>
        </div>
</body>
</html>