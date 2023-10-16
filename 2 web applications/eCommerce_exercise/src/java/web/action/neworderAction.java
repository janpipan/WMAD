package web.action;


import cart.ShoppingCart;
import javax.servlet.http.*;
import model.CategoryModel;
import model.ProductModel;
import web.ViewManager;

public class neworderAction implements Action {

    CategoryModel categoryModel;
    ProductModel productModel;

    public neworderAction(CategoryModel categoryModel, ProductModel productModel){
        this.categoryModel = categoryModel;
        this.productModel = productModel;
    }

    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        
        ShoppingCart sc = (ShoppingCart) req.getSession().getAttribute("shoppingCart");
        if (sc == null){
            sc = new ShoppingCart();
        }
        sc.addItem(productModel.retrieveById(Integer.parseInt(req.getParameter("productId"))));
        req.getSession().setAttribute("cart", sc);
        
        ViewManager.nextView(req, resp, "/view/cart.jsp");
    }
}