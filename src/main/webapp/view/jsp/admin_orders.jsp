<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="entities.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="https://fonts.googleapis.com/css2?family=Varela+Round&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="http://localhost:8080/final/view/css/main.css">
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/admin.css">
	<title>Admin orders</title>
</head>
<body>
	<jsp:include page="admin_header.jsp"/>
    <div class="body">
        <div class="folder">
            <table>
                <tr>
                    <th>ID</th>
                    <th>User id</th>
                    <th>User name</th>
                    <th>Product id</th>
                    <th>Product name<th>
                </tr>
                <% 
                	List<Order> orders = (List<Order>)request.getAttribute("orders");
                	HashMap<Integer, String> products = (HashMap<Integer, String>)request.getAttribute("products");
                	HashMap<Integer, String> users = (HashMap<Integer, String>)request.getAttribute("users");
                	for (Order order : orders) {
                		boolean firstIter = true;
                		for (Integer i : order.getCart()) {
	                		out.println("<tr>");
	                		if (firstIter) {
		                		out.println("<td rowspan=\"" + order.getCart().size() + "\">" + order.getId() + "</td>");
		                		out.println("<td rowspan=\"" + order.getCart().size() + "\">" + order.getUserId() + "</td>");
		                		out.println("<td rowspan=\"" + order.getCart().size() + "\">" + users.get(order.getUserId()) + "</td>");
	                		}
	               			out.println("<td>" + i + "</td>");
	               			out.println("<td>" + products.get(i) + "</td>");
	                		if (firstIter) {
	                			if (order.getState().equals("registrated")) {
			                		out.println("<td rowspan=\"" + order.getCart().size() + "\"><form action=\"http://localhost:8080/final/server/set_paid?id=" + order.getId() + "\" method=\"POST\"><input type=\"submit\" class=\"link\" value=\"Set Paid\"></input></form></td>");
			                		out.println("<td rowspan=\"" + order.getCart().size() + "\"><form action=\"http://localhost:8080/final/server/reject_order?id=" + order.getId() + "\" method=\"POST\"><input type=\"submit\" class=\"link\" value=\"Reject\"></input></form></td>");
	                			} else {
	                				out.println("<td rowspan=\"" + order.getCart().size() + "\">" + order.getState() + "</td>");
	                			}
	                		}
		                	out.println("</tr>");
		                	firstIter = false;
                		}
                	}
                %>
            </table>
        </div>
    </div>
</body>
</html>