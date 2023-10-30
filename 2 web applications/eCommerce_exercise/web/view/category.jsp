<%@ page import="entity.Category" %>
<%@ page import="entity.Product"  %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

    <head>
        <meta http-equiv="Expires" CONTENT="0">
        <meta http-equiv="Cache-Control" CONTENT="no-cache">
        <meta http-equiv="Pragma" CONTENT="no-cache">
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link href="css/styles.css" rel="stylesheet">
        <%
            Category selectedCategory = (Category) request.getAttribute("category");
            String selectedCategoryName = selectedCategory.getName();
            String title = selectedCategoryName.substring(0,1).toUpperCase() + selectedCategoryName.substring(1);
        %>
        <title>
            <%=title%>
        </title>
    </head>
    
    <body>

        <h2>Products of <%=selectedCategoryName%></h2>

        <div>
            <img src="img/cart.gif" alt="cart"></img>
            <a href="viewcart.do">View Cart</a>
        </div>


        <div class="container" style="flex-direction: column;" >
            <table class="itemTable">
                <tr>
                    <% 
                        List<Category> categories = (List<Category>)request.getAttribute("categories");
                        for (Category category : categories) {
                    %>
                    <td style="text-align: center;">
                        <a href="category.do?categoryid=<%=category.getId()%>"><%=category.getName()%></a>
                    </td>
                    <%
                        }
                    %>
                </tr>
            </table>
            <table class="itemTable" border="1" cellpadding="3" cellspacing="0">
        
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

                    <td valign="center" align="middle">
                        <img src="img/products/<%=product.getName()%>.png" alt="alt"/>
                    </td>
                    <td valign="center" align="middle">
                        <b><%=product.getName()%></b>
                        <br>
                        <%=product.getDescription()%>
                    </td>
                    <td valign="center" align="middle">
                        <%=product.getPrice()%> €
                    </td>
                    <td valign="center" align="middle">
                        <form type="submit" method="post" action="neworder.do">
                            <button name="productId" value="<%=product.getId()%>">Add to cart</button>
                        </form>
                    </td>


                        
                    </tr>

                <% } %>



            </table>
        </div>
        
    

    </body>