package web.action;

import javax.servlet.http.*;
import model.ProductModel;
import web.ViewManager;

public class updatecartAction implements Action {

    ProductModel categoryModel;

    public updatecartAction(ProductModel categoryModel){
        this.categoryModel = categoryModel;
    }

    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        ViewManager.nextView(req, resp, "/view/init.jsp");
    }
}