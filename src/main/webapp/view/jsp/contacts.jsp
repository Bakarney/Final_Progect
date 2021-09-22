<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Varela+Round&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/main.css">
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/contacts.css">
    <fmt:setLocale value="${lang}"/>
	<fmt:setBundle basename="resources" var="lang"/>
    <title><fmt:message key="inter.contacts.title" bundle="${lang}"/></title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div class="body">
        <div class="blank"></div>
        <div class="navigation">
            <a href="http://localhost:8080/final/server/home"><fmt:message key="inter.contacts.nav.home" bundle="${lang}"/></a>
            <img src="http://localhost:8080/final/view/media/5f8f84f3d4d23a31c1f2fcae_arrow-right-mini-icon.svg">
            <fmt:message key="inter.contacts.nav.contacts" bundle="${lang}"/>
        </div>
        <div class="all">
            <iframe class="a_maps" src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2540.98861737099!2d30.502796915731007!3d50.441312679474144!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40d4cef08e0df497%3A0xc6f4c41b38d10a5c!2z0LLRg9C70LjRhtGPINCb0YzQstCwINCi0L7Qu9GB0YLQvtCz0L4sINCa0LjRl9CyLCAwMjAwMA!5e0!3m2!1suk!2sua!4v1606785124053!5m2!1suk!2sua" ></iframe>
            <div class="a_info">
                <form class="a_i_form">
                    <h1><fmt:message key="inter.contacts.message.title" bundle="${lang}"/></h1>
                    <label><fmt:message key="inter.contacts.message.name.label" bundle="${lang}"/></label>
                    <input class="a_i_f_text" type="text" placeholder="<fmt:message key="inter.contacts.message.name.input" bundle="${lang}"/>">
                    <label><fmt:message key="inter.contacts.message.email.label" bundle="${lang}"/></label>
                    <input class="a_i_f_text" type="email" placeholder="<fmt:message key="inter.contacts.message.email.input" bundle="${lang}"/>">
                    <label><fmt:message key="inter.contacts.message.text.label" bundle="${lang}"/></label>
                    <textarea class="a_i_f_area" placeholder="<fmt:message key="inter.contacts.message.text.input" bundle="${lang}"/>"></textarea>
                    <button type="submit"><fmt:message key="inter.contacts.message.btn" bundle="${lang}"/></button>
                </form>
                <div class="a_i_info">
                    <h1><fmt:message key="inter.contacts.info.title" bundle="${lang}"/></h1>
                    <fmt:message key="inter.contacts.info.adress" bundle="${lang}"/><br>
                    <fmt:message key="inter.contacts.info.phone" bundle="${lang}"/>
                    <div class="b"><fmt:message key="inter.contacts.info.email" bundle="${lang}"/></div>
                    <h2><fmt:message key="inter.contacts.info.socials" bundle="${lang}"/></h2>
                    <div class="a_i_i_socials">
                        <a class="twitter" href=""><img src="http://localhost:8080/final/view/media/social/twitter.svg"></a>
                        <a class="facebook" href=""><img src="http://localhost:8080/final/view/media/social/facebook.svg"></a>
                        <a class="instagram" href=""><img src="http://localhost:8080/final/view/media/social/instagram.svg"></a>
                        <a class="pintrest" href=""><img src="http://localhost:8080/final/view/media/social/pinterest.svg"></a>
                        <a class="youtube" href=""><img src="http://localhost:8080/final/view/media/social/youtube.svg"></a>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
    </div>
</body>
</html>