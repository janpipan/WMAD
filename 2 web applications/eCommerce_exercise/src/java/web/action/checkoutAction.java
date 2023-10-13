package web.action;

import javax.servlet.http.*;
import model.CategoryModel;
import web.ViewManager;

public class checkoutAction implements Action {

    CategoryModel categoryModel;

    public checkoutAction(CategoryModel categoryModel){
        this.categoryModel = categoryModel;
    }

    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        
    }
}