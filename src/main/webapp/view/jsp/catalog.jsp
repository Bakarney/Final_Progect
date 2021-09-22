<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="entities.Product" %>
<%@ page import="DAO.DAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.util.Arrays" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Varela+Round&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/main.css">
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/catalog.css">
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/menu.css">
    <fmt:setLocale value="${lang}"/>
	<fmt:setBundle basename="resources" var="lang"/>
    <title><fmt:message key="inter.catalog.title" bundle="${lang}"/></title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div class="body">
        <div class="blank"></div>
        <div class="navigation">
            <a href="http://localhost:8080/final/server/home"><fmt:message key="inter.catalog.nav.home" bundle="${lang}"/></a>
            <img src="http://localhost:8080/final/view/media/5f8f84f3d4d23a31c1f2fcae_arrow-right-mini-icon.svg">
            <fmt:message key="inter.catalog.nav.catalog" bundle="${lang}"/>
        </div>
        <div class="all">
            <div class="a_header">
                <div class="hamburger-menu">
	                <input id="menu__toggle" type="checkbox" />
	                <label class="menu__btn" for="menu__toggle">
	                    <span></span>
	                </label>
	                <form class="menu__box">
	                	<% String sort = request.getParameter("sort"); %>
	                	<label class="filter_label"><fmt:message key="inter.catalog.filter.sort" bundle="${lang}"/></label>
	                	<div>
                            <input type="radio" id="huey" name="sort" value="a-z" <% if (sort == null || sort.equals("a-z")) out.print("checked"); %>>
                        	<label for="huey"><fmt:message key="inter.catalog.filter.sort.a" bundle="${lang}"/></label>
                        </div>
                        <div>
                            <input type="radio" id="huey" name="sort" value="z-a" <% if (sort != null && sort.equals("z-a")) out.print("checked"); %>>
                            <label for="huey"><fmt:message key="inter.catalog.filter.sort.b" bundle="${lang}"/></label>
                        </div>
                        <div>
                            <input type="radio" id="huey" name="sort" value="l-h" <% if (sort != null && sort.equals("l-h")) out.print("checked"); %>>
                            <label for="huey"><fmt:message key="inter.catalog.filter.sort.c" bundle="${lang}"/></label>
                        </div>
                        <div>
                            <input type="radio" id="huey" name="sort" value="h-l" <% if (sort != null && sort.equals("h-l")) out.print("checked"); %>>
                            <label for="huey"><fmt:message key="inter.catalog.filter.sort.d" bundle="${lang}"/></label>
                        </div>
                        <div>
                            <input type="radio" id="huey" name="sort" value="new" <% if (sort != null && sort.equals("new")) out.print("checked"); %>>
                            <label for="huey"><fmt:message key="inter.catalog.filter.sort.e" bundle="${lang}"/></label>
                        </div>
                        <div>
                            <input type="radio" id="huey" name="sort" value="old" <% if (sort != null && sort.equals("old")) out.print("checked"); %>>
                            <label for="huey"><fmt:message key="inter.catalog.filter.sort.f" bundle="${lang}"/></label>
                        </div>
                        
                        <% String gender = request.getParameter("gender"); %>
                        <label class="filter_label"><fmt:message key="inter.catalog.filter.gender" bundle="${lang}"/></label>
                        <div>
                            <input type="radio" id="huey" name="gender" value="male" <% if (gender != null && gender.equals("male")) out.print("checked"); %>>
                            <label for="huey"><fmt:message key="inter.catalog.filter.gender.a" bundle="${lang}"/></label>
                        </div>
                        <div>
                            <input type="radio" id="huey" name="gender" value="female" <% if (gender != null && gender.equals("female")) out.print("checked"); %>>
                            <label for="huey"><fmt:message key="inter.catalog.filter.gender.b" bundle="${lang}"/></label>
                        </div>
                        <div>
                            <input type="radio" id="huey" name="gender" value="unisex" <% if (gender == null || gender.equals("unisex")) out.print("checked"); %>>
                            <label for="huey"><fmt:message key="inter.catalog.filter.gender.c" bundle="${lang}"/></label>
                        </div>
                        
                        <label class="filter_label"><fmt:message key="inter.catalog.filter.producer" bundle="${lang}"/></label>
                        <%
                        	String html = "<div>" +
                                    "<input type=\"checkbox\" id=\"huey\" name=\"producer\" value=\"%s\" %s>" + 
                                    "<label for=\"huey\">%s</label>" + 
                                	"</div>";
                            String[] producer = request.getParameterValues("producer");
                            List<String> producers = (List<String>)request.getAttribute("producers");
                            for (String i : producers) {
                            	out.println(String.format(html, i, (producer == null || Arrays.stream(producer).anyMatch(i::equals)) ? "checked" : "", i));
                            }
                        %>
                        
                 		<label class="filter_label"><fmt:message key="inter.catalog.filter.category" bundle="${lang}"/></label>
                 		<%
                        	html = "<div>" +
                                    "<input type=\"checkbox\" id=\"huey\" name=\"category\" value=\"%s\" %s>" + 
                                    "<label for=\"huey\">%s</label>" + 
                                	"</div>";
                            String[] category = request.getParameterValues("category");
                            List<String> categories = (List<String>)request.getAttribute("categories");
                            for (String i : categories) {
                            	out.println(String.format(html, i, (category == null || Arrays.stream(category).anyMatch(i::equals)) ? "checked" : "", i));
                            }
                        %>
                        
                        <label class="filter_label"><fmt:message key="inter.catalog.filter.price" bundle="${lang}"/></label>
                        <div>
                        	<fmt:message key="inter.catalog.filter.price.from" bundle="${lang}"/>
                        	<input class="price" type="number" name="bot" value="<%= (request.getParameter("bot") == null) ? "0" : request.getParameter("bot") %>">
                        	<fmt:message key="inter.catalog.filter.price.to" bundle="${lang}"/>
                        	<input class="price" type="number" name="top" value="<%= (request.getParameter("top") == null) ? "100" : request.getParameter("top") %>">
                        </div>
                        
                        <input class="button menu_btn" type="submit" value="<fmt:message key="inter.catalog.filter.btn" bundle="${lang}"/>">
	                </form>
        		</div>
        		<div class="pagin">
        			<% 
        			String forward = "http://localhost:8080/final/server/catalog?";
        			String backward;
        			if (request.getQueryString() != null) forward += request.getQueryString();
        			String pageStr = request.getParameter("page");
        			int totalNum = (Integer)request.getAttribute("numberPages");
        			int pageNum;
        			if (pageStr == null) {
        				pageNum = 1;
        				forward += (forward.endsWith("?")) ? "" : "&";
        			} else {
        				pageNum = Integer.valueOf(pageStr);
        				forward = forward.replaceAll("page=[0-9]+", "");
        				forward += (forward.endsWith("?") || forward.endsWith("&")) ? "" : "&";
        			}
        			backward = forward;
        			forward += "page=" + (pageNum + 1);
    				backward += "page=" + (pageNum - 1);
    				
    				if (pageNum <= 1)
    					backward = "class=\"dis_button\"";
    				else 
    					backward = "class=\"button\" href=\"" + backward + "\"";
    				
    				if (pageNum >= totalNum) 
    					forward = "class=\"dis_button\"";
    				else
    					forward = "class=\"button\" href=\"" + forward + "\"";
        			%>
        			<a <%= backward %>><fmt:message key="inter.catalog.page.backward" bundle="${lang}"/></a>
        			<p><%= pageNum + "/" + totalNum %></p>
        			<a <%= forward %>><fmt:message key="inter.catalog.page.forward" bundle="${lang}"/></a>
        		</div>
            </div>
            <div class="showcase">
            	<%
            		List<Product> products = (List<Product>)request.getAttribute("products");
                    int k = 0;
                    for (Product prod : products) {
                    	k++;
                    	if (k % 4 == 1)
                    		out.println("<div class=\"s_row\">");
                    	html = "<a href=\"product?id=%d\" class=\"product\" >" +
                                "<img src=\"http://localhost:8080/final/view/media/%s/%s\" >" +
                                "<div class=\"p_info\" class=\"scale\">" +
                                "<p class=\"p_i_text\">%s</p>" +
                                "<p href=\"\" class=\"p_i_price\">$%.2f</p>" +
                                "</div>" +
                            	"</a>";
                        out.println(String.format(html, prod.getId(), prod.getCategory(), prod.getPhoto(), prod.getName(), prod.getPrice()));
                    	if (k % 4 == 0)
                    		out.println("</div>");
                    }
                    if (k % 4 != 0) 
                    	out.println("</div>");
            	%>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
    </div>
</body>
</html>