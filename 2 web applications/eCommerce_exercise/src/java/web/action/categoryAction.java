package web.action;

import javax.servlet.http.*;
import model.CategoryModel;
import web.ViewManager;

public class categoryAction implements Action {

    CategoryModel categoryModel;

    public categoryAction(CategoryModel categoryModel){
        this.categoryModel = categoryModel;
    }

    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        
    }
}