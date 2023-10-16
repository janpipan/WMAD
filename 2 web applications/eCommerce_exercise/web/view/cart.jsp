<%-- 
    Document   : cart
    Created on : Oct 16, 2023, 11:00:16 AM
    Author     : entel
--%>

<%@page import="cart.ShoppingCart"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Cart</h1>
        
        <%
        ShoppingCart sc = (ShoppingCart) request.getSession().getAttribute("cart");
        %>
        
        <p><%=sc.getNumberOfItems()%></p>
    </body>
</html>
