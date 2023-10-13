package web.action;

import javax.servlet.http.*;
import model.CategoryModel;
import web.ViewManager;

public class updatecartAction implements Action {

    CategoryModel categoryModel;

    public updatecartAction(CategoryModel categoryModel){
        this.categoryModel = categoryModel;
    }

    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("categories", categoryModel.retrieveAll());
        ViewManager.nextView(req, resp, "/view/init.jsp");
    }
}