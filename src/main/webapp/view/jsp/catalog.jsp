<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="entities.Product" %>
<%@ page import="DAO.DAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.util.Arrays" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Varela+Round&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/main.css">
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/catalog.css">
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/menu.css">
    <script src="../main.js"></script>
    <title>Catalog</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div class="body">
        <div class="blank"></div>
        <div class="navigation">
            <a href="http://localhost:8080/final/server/home">Home</a>
            <img src="http://localhost:8080/final/view/media/5f8f84f3d4d23a31c1f2fcae_arrow-right-mini-icon.svg">
            Catalog
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
	                	<label class="filter_label">Sorted by:</label>
	                	<div>
                            <input type="radio" id="huey" name="sort" value="a-z" <% if (sort == null || sort.equals("a-z")) out.print("checked"); %>>
                        	<label for="huey">Name (A-Z)</label>
                        </div>
                        <div>
                            <input type="radio" id="huey" name="sort" value="z-a" <% if (sort != null && sort.equals("z-a")) out.print("checked"); %>>
                            <label for="huey">Name (Z-A)</label>
                        </div>
                        <div>
                            <input type="radio" id="huey" name="sort" value="l-h" <% if (sort != null && sort.equals("l-h")) out.print("checked"); %>>
                            <label for="huey">Price (Low-High)</label>
                        </div>
                        <div>
                            <input type="radio" id="huey" name="sort" value="h-l" <% if (sort != null && sort.equals("h-l")) out.print("checked"); %>>
                            <label for="huey">Price (High-Low)</label>
                        </div>
                        <div>
                            <input type="radio" id="huey" name="sort" value="new" <% if (sort != null && sort.equals("new")) out.print("checked"); %>>
                            <label for="huey">Novelty (New-Old)</label>
                        </div>
                        <div>
                            <input type="radio" id="huey" name="sort" value="old" <% if (sort != null && sort.equals("old")) out.print("checked"); %>>
                            <label for="huey">Novelty (Old-New)</label>
                        </div>
                        
                        <% String gender = request.getParameter("gender"); %>
                        <label class="filter_label">Gender:</label>
                        <div>
                            <input type="radio" id="huey" name="gender" value="male" <% if (gender != null && gender.equals("male")) out.print("checked"); %>>
                            <label for="huey">Male</label>
                        </div>
                        <div>
                            <input type="radio" id="huey" name="gender" value="female" <% if (gender != null && gender.equals("female")) out.print("checked"); %>>
                            <label for="huey">Female</label>
                        </div>
                        <div>
                            <input type="radio" id="huey" name="gender" value="unisex" <% if (gender == null || gender.equals("unisex")) out.print("checked"); %>>
                            <label for="huey">Unisex</label>
                        </div>
                        
                        <label class="filter_label">Producer:</label>
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
                        
                 		<label class="filter_label">Category:</label>
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
                        
                        <label class="filter_label">Price:</label>
                        <div>
                        	From:
                        	<input class="price" type="number" name="bot" value="<%= (request.getParameter("bot") == null) ? "0" : request.getParameter("bot") %>">
                        	To:
                        	<input class="price" type="number" name="top" value="<%= (request.getParameter("top") == null) ? "100" : request.getParameter("top") %>">
                        </div>
                        
                        <input class="button menu_btn" type="submit" value="Filter">
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
        			<a <%= backward %>>Backward</a>
        			<p><%= pageNum + "/" + totalNum %></p>
        			<a <%= forward %>>Forward</a>
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