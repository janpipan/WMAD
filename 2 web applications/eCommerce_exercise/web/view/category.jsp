<%@ page import="entity.Category" %>
<%@ page import="entity.Product"  %>
<%@ page import="java.util.List" %>

    <head>
        <meta http-equiv="Expires" CONTENT="0">
        <meta http-equiv="Cache-Control" CONTENT="no-cache">
        <meta http-equiv="Pragma" CONTENT="no-cache">
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>eCommerce Sample</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        
    </head>
    
    <body>

        <h2>Welcome to the online home of our virtual grocery</h2>

        <h3> Our unique home delivery service brings you fresh organic produce,
        dairy, meats, breads and other delicious and healthy items direct
        to your doorstep. </h3>


    <table width="50%" border="1" cellpadding="3" cellspacing="0">
        
        <tr>
            <th style="text-align:center;">Item</th>
            <th style="text-align:center;">Description</th>
            <th style="text-align:center;">Price</th>
            <th style="text-align:center;">Cart</th>
        </tr>
        
        <%
            List<Product> products = (List<Product>)request.getAttribute("products");

            for(Product product : products){
        %>
                
            <tr> 
                <font size="2" face="Verdana">

            <td width="14%" valign="center" align="middle">
                <img src="img/products/<%=product.getName()%>.png" alt="alt"/>
            </td>
            <td width="14%" valign="center" align="middle">
                <b><%=product.getName()%></b>
                <br>
                <%=product.getDescription()%>
            </td>
            <td width="14%" valign="center" align="middle">
                <%=product.getPrice()%>
            </td>


                </font> 
            </tr>
 
        <% } %>
    
    
    

    </table>

    </body>