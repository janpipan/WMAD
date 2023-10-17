<%-- 
    Document   : checkout
    Created on : Oct 17, 2023, 3:42:59 PM
    Author     : entel
--%>

<%@page import="cart.ShoppingCart"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Check Out</title>
    </head>
    <body>
        <%
            ShoppingCart sc = (ShoppingCart) request.getSession().getAttribute("cart");
            Double total = sc.getTotal();
        %>
        <form action="https://www.paypal.com/cgi-bin/webscr" method="post">
            <input type="hidden" name="cmd" value="_xclick">
            <input type="hidden" name="business" value="janpipan@gmail.com">
            <input type="hidden" name="item_name" value="Grocery Cart">
            <input type="hidden" name="currency_code" value="EUR">
            <input type="hidden" name="amount" value="<%=total%>">
            <input type="image" name="submit" src="http://www.paypal.com/en_US/i/btn/x-click-but01.gif" alt="Pay with paypal">
        </form>
    </body>
</html>
