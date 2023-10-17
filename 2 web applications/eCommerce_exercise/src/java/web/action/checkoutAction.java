package web.action;

import javax.servlet.http.*;
import web.ViewManager;

public class checkoutAction implements Action {


    public checkoutAction(){
    }

    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        ViewManager.nextView(req, resp, "/view/checkout.jsp");
    }
}