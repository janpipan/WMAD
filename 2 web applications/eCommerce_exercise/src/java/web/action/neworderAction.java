package web.action;

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
        req.setAttribute("categories", categoryModel.retrieveAll());
        ViewManager.nextView(req, resp, "/view/init.jsp");
    }
}