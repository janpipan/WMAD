package web.action;

import cart.ShoppingCart;
import javax.servlet.http.*;
import web.ViewManager;

public class viewcartAction implements Action {


    public viewcartAction(){
    }

    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        ShoppingCart sc = (ShoppingCart) req.getSession().getAttribute("cart");
        if (sc == null){
            sc = new ShoppingCart();
        }
        req.getSession().setAttribute("cart", sc);
        
        ViewManager.nextView(req, resp, "/view/cart.jsp");
    }
}