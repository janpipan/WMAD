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
        <meta http-equiv="Expires" CONTENT="0">
        <meta http-equiv="Cache-Control" CONTENT="no-cache">
        <meta http-equiv="Pragma" CONTENT="no-cache">
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link href="css/styles.css" rel="stylesheet">
        <title>Cart</title>
    </head>
    <body>
        <h1>Cart</h1>
        
        <%
            ShoppingCart sc = (ShoppingCart) request.getSession().getAttribute("cart");
            Integer numItems = sc.getNumberOfItems();
            if (numItems > 1){
        %>
           
        <div>Your shopping cart contains <%=sc.getNumberOfItems()%> items.</div>
        <%
            }else if (numItems == 1) {
        %>
            <div>Your shopping cart contains <%=sc.getNumberOfItems()%> item.</div>
        <%
            } else {
        %>
            <div>Your shopping cart is empty.</div>
        <%
            }
        %>
        
        <div>
            <a href="clearcart.do">Clear cart</a>
        </div>
        
        
        <div>
            <a href="init.do">Return to store</a>
        </div>
        
        
        <div>
            <a href="checkout.do">Proceed to checkout</a>
        </div>
        
        <div class="container">
            <table class="itemTable" border="1" cellpadding="3" cellspacing="0">
        
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
                        <form type="submit" method="post" action="updatecart.do">
                            <input type="hidden" name="productId" value="<%=item.getProduct().getId()%>">
                            <input name="quantity" value="<%=item.getQuantity()%>"></input>
                            <button type="submit">Update</button>
                        </form>
                    </td>

                        
                    </tr>

                <% } %>




            </table>
        </div>
       
        
            
    </body>
</html>
