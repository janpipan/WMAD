<%-- 
    Document   : cart
    Created on : Oct 16, 2023, 11:00:16 AM
    Author     : entel
--%>

<%@page import="cart.ShoppingCartItem"%>
<%@page import="cart.ShoppingCart"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Cart</title>
    </head>
    <body>
        <h1>Cart</h1>
        
        <%
            ShoppingCart sc = (ShoppingCart) request.getSession().getAttribute("cart");

        %>
                
        <p><%=sc.getNumberOfItems()%></p>
        
        <a href="clearcart.do">Clear cart</a>
        
        <br>
        
        <a href="init.do">Return to store</a>
        
        <br>
        
        <a href="checkout.do">Proced to checkout</a>
        
        <br>
        
        <table width="50%" border="1" cellpadding="3" cellspacing="0">
        
        <tr>
            <th style="text-align:center;">Item</th>
            <th style="text-align:center;">Description</th>
            <th style="text-align:center;">Price</th>
            <th style="text-align:center;">Quantity</th>
        </tr>
        
        <%
        
            for(ShoppingCartItem item : sc.getItems()){

        %>
        
            <tr> 
                <font size="2" face="Verdana">

            <td width="14%" valign="center" align="middle">
                <img src="img/products/<%=item.getProduct().getName()%>.png" alt="alt"/>
            </td>
            <td width="14%" valign="center" align="middle">
                <b><%=item.getProduct().getName()%></b>
                <br>
                <%=item.getProduct().getDescription()%>
            </td>
            <td width="14%" valign="center" align="middle">
                <%=item.getProduct().getPrice()%>
            </td>
            <td width="14%" valign="center" align="middle">
                <form type="submit" method="post" action="updatecart.do">
                    <input type="hidden" name="productId" value="<%=item.getProduct().getId()%>">
                    <input name="quantity" value="<%=item.getQuantity()%>"></input>
                    <button type="submit">Update</button>
                </form>
            </td>

                </font> 
            </tr>
 
        <% } %>
    
    
    

    </table>
    </body>
</html>
