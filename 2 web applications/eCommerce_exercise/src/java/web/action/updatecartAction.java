package web.action;

import cart.ShoppingCart;
import entity.Product;
import javax.servlet.http.*;
import model.ProductModel;
import web.ViewManager;

public class updatecartAction implements Action {

    ProductModel productModel;

    public updatecartAction(ProductModel productModel){
        this.productModel = productModel;
    }

    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("updating");
        ShoppingCart sc = (ShoppingCart) req.getSession().getAttribute("cart");
        
        Product product = (Product) productModel.retrieveById(Integer.parseInt(req.getParameter("productId")));
        Integer quantity = Integer.parseInt(req.getParameter("quantity"));
        sc.update(product,quantity);
        ViewManager.nextView(req, resp, "/view/cart.jsp");
    }
}