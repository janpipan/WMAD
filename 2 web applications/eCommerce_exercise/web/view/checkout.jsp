<%-- 
    Document   : checkout
    Created on : Oct 17, 2023, 3:42:59 PM
    Author     : entel
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="cart.ShoppingCartItem"%>
<%@page import="cart.ShoppingCart"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Expires" CONTENT="0">
        <meta http-equiv="Cache-Control" CONTENT="no-cache">
        <meta http-equiv="Pragma" CONTENT="no-cache">
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link href="css/styles.css" rel="stylesheet">
        <title>Check Out</title>
    </head>
    <body>
        <%
            ShoppingCart sc = (ShoppingCart) request.getSession().getAttribute("cart");
            Double total = sc.getTotal();
            DecimalFormat format = new DecimalFormat("#0.00");
        %>
        
        <h2>Check out</h2>
        
        <div>
            <a href="init.do">Return to store</a>
        </div>
        
        
        <div>
            <img src="img/cart.gif" alt="cart"></img>
            <a href="viewcart.do">View Cart</a>
        </div>
        
        
        <div class="container">
            <table class="itemTable" border="1" cellpadding="3" cellspacing="0">
        
                <tr>
                    <th style="text-align:center;">Item</th>
                    <th style="text-align:center;">Description</th>
                    <th style="text-align:center;">Price</th>
                    <th style="text-align:center;">Quantity</th>
                    <th style="text-align:center;">Total</th>
                </tr>

                <%

                    for(ShoppingCartItem item : sc.getItems()){

                %>

                    <tr> 
                        

                    <td valign="center" align="middle">
                        <img src="img/products/<%=item.getProduct().getName()%>.png" alt="alt"/>
                    </td>
                    <td valign="center" align="middle">
                        <b><%=item.getProduct().getName()%></b>
                        <br>
                        <%=item.getProduct().getDescription()%>
                    </td>
                    <td valign="center" align="middle">
                        <%=item.getProduct().getPrice()%> €
                    </td>
                    <td valign="center" align="middle">
                        <%=item.getQuantity()%>
                    </td>
                    <td>
                        <%=item.getProduct().getPrice()*item.getQuantity()%> €
                    </td>

                        
                    </tr>

                <% } %>


                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>
                        <b>Total</b>
                    </td>
                    <td>
                        <%=format.format(total)%>
                    </td>
                </tr>

            </table>
        </div>
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
