<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
<%@ page import="entities.Product" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="https://fonts.googleapis.com/css2?family=Varela+Round&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="http://localhost:8080/final/view/css/main.css">
	<link rel="stylesheet" href="http://localhost:8080/final/view/css/menu.css">
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/admin.css">
	<fmt:setLocale value="${lang}"/>
	<fmt:setBundle basename="resources" var="lang"/>
	<title><fmt:message key="inter.admin_catalog.title" bundle="${lang}"/></title>
</head>
<body>
	<jsp:include page="admin_header.jsp"/>
    <div class="body">
        <div class="folder">
            <table>
                <tr>
                    <th>
                    	<div class="hamburger-menu">
			                <input id="menu__toggle" type="checkbox" />
			                <label class="menu__btn" for="menu__toggle">
			                    <span></span>
			                </label>
			                <form class="menu__box">
			                	<% String sort = request.getParameter("sort"); %>
			                	<label class="filter_label"><fmt:message key="inter.admin_catalog.filter.sort" bundle="${lang}"/></label>
			                	<div>
		                            <input type="radio" id="huey" name="sort" value="a-z" <% if (sort == null || sort.equals("a-z")) out.print("checked"); %>>
		                        	<label for="huey"><fmt:message key="inter.admin_catalog.filter.sort.a" bundle="${lang}"/></label>
		                        </div>
		                        <div>
		                            <input type="radio" id="huey" name="sort" value="z-a" <% if (sort != null && sort.equals("z-a")) out.print("checked"); %>>
		                            <label for="huey"><fmt:message key="inter.admin_catalog.filter.sort.b" bundle="${lang}"/></label>
		                        </div>
		                        <div>
		                            <input type="radio" id="huey" name="sort" value="l-h" <% if (sort != null && sort.equals("l-h")) out.print("checked"); %>>
		                            <label for="huey"><fmt:message key="inter.admin_catalog.filter.sort.c" bundle="${lang}"/></label>
		                        </div>
		                        <div>
		                            <input type="radio" id="huey" name="sort" value="h-l" <% if (sort != null && sort.equals("h-l")) out.print("checked"); %>>
		                            <label for="huey"><fmt:message key="inter.admin_catalog.filter.sort.d" bundle="${lang}"/></label>
		                        </div>
		                        <div>
		                            <input type="radio" id="huey" name="sort" value="new" <% if (sort != null && sort.equals("new")) out.print("checked"); %>>
		                            <label for="huey"><fmt:message key="inter.admin_catalog.filter.sort.e" bundle="${lang}"/></label>
		                        </div>
		                        <div>
		                            <input type="radio" id="huey" name="sort" value="old" <% if (sort != null && sort.equals("old")) out.print("checked"); %>>
		                            <label for="huey"><fmt:message key="inter.admin_catalog.filter.sort.f" bundle="${lang}"/></label>
		                        </div>
		                        
		                        <% String gender = request.getParameter("gender"); %>
		                        <label class="filter_label"><fmt:message key="inter.admin_catalog.filter.gender" bundle="${lang}"/></label>
		                        <div>
		                            <input type="radio" id="huey" name="gender" value="male" <% if (gender != null && gender.equals("male")) out.print("checked"); %>>
		                            <label for="huey"><fmt:message key="inter.admin_catalog.filter.gender.a" bundle="${lang}"/></label>
		                        </div>
		                        <div>
		                            <input type="radio" id="huey" name="gender" value="female" <% if (gender != null && gender.equals("female")) out.print("checked"); %>>
		                            <label for="huey"><fmt:message key="inter.admin_catalog.filter.gender.b" bundle="${lang}"/></label>
		                        </div>
		                        <div>
		                            <input type="radio" id="huey" name="gender" value="unisex" <% if (gender == null || gender.equals("unisex")) out.print("checked"); %>>
		                            <label for="huey"><fmt:message key="inter.admin_catalog.filter.gender.c" bundle="${lang}"/></label>
		                        </div>
		                        
		                        <label class="filter_label"><fmt:message key="inter.admin_catalog.filter.producer" bundle="${lang}"/></label>
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
		                        
		                 		<label class="filter_label"><fmt:message key="inter.admin_catalog.filter.category" bundle="${lang}"/></label>
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
		                        
		                        <label class="filter_label"><fmt:message key="inter.admin_catalog.filter.price" bundle="${lang}"/></label>
		                        <div>
		                        	<fmt:message key="inter.admin_catalog.filter.price.from" bundle="${lang}"/>
		                        	<input class="price" type="number" name="bot" value="<%= (request.getParameter("bot") == null) ? "0" : request.getParameter("bot") %>">
		                        	<fmt:message key="inter.admin_catalog.filter.price.to" bundle="${lang}"/>
		                        	<input class="price" type="number" name="top" value="<%= (request.getParameter("top") == null) ? "100" : request.getParameter("top") %>">
		                        </div>
		                        
		                        <input class="button menu_btn" type="submit" value="Filter">
			                </form>
		        		</div>
                    </th>
                    <th><fmt:message key="inter.admin_catalog.name" bundle="${lang}"/></th>
                    <th><fmt:message key="inter.admin_catalog.category" bundle="${lang}"/></th>
                    <th><fmt:message key="inter.admin_catalog.gender" bundle="${lang}"/></th>
                    <th><fmt:message key="inter.admin_catalog.producer" bundle="${lang}"/></th>
                    <th><fmt:message key="inter.admin_catalog.number" bundle="${lang}"/></th>
                    <th><fmt:message key="inter.admin_catalog.price" bundle="${lang}"/></th>
                    <th><fmt:message key="inter.admin_catalog.photo" bundle="${lang}"/></th>
                    <th></th>
                    <th></th>
                </tr>
				<c:forEach var="prod" items="${products}">
					<tr>
	                    <td>${prod.getId()}</td>
	                    <td>${prod.getName()}</td>
	                    <td>${prod.getCategory()}</td>
	                    <td>${prod.getGender()}</td>
	                    <td>${prod.getProducer()}</td>
	                    <td>${prod.getNumber()}</td>
	                    <td>${prod.getPrice()}</td>
	                    <td><a class="link" href="http://localhost:8080/final/view/media/${prod.getCategory()}/${prod.getPhoto()}">${prod.getPhoto()}</a></td>
	                    <td><a class="link" href="http://localhost:8080/final/server/admin_product?id=${prod.getId()}"><fmt:message key="inter.admin_catalog.edit" bundle="${lang}"/></a></td>
	                    <td><form action="http://localhost:8080/final/server/delete_product?id=${prod.getId()}" method="POST"><input type="submit" class="link" value="<fmt:message key="inter.admin_catalog.delete" bundle="${lang}"/>"></input></form></td>
                	</tr>
                </c:forEach>
                <tr>
                	<th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th><a class="link" href="http://localhost:8080/final/server/create_product"><fmt:message key="inter.admin_catalog.create" bundle="${lang}"/></a></th>
                </tr>
            </table>
        </div>
    </div>
</body>
</html>