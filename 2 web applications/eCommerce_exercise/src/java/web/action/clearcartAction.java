package web.action;

import cart.ShoppingCart;
import javax.servlet.http.*;
import web.ViewManager;

public class clearcartAction implements Action {


    public clearcartAction(){
    }

    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        ShoppingCart sc = (ShoppingCart) req.getSession().getAttribute("cart");
        sc.clear();
        ViewManager.nextView(req, resp, "/view/cart.jsp");
    }
}