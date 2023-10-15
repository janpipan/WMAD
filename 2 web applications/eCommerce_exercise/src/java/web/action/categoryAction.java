package web.action;

import javax.servlet.http.*;
import model.CategoryModel;
import model.ProductModel;
import entity.Product;
import java.util.List;
import web.ViewManager;

public class categoryAction implements Action {

    CategoryModel categoryModel;
    ProductModel productModel;

    public categoryAction(CategoryModel categoryModel, ProductModel productModel){
        this.categoryModel = categoryModel;
        this.productModel = productModel;
    }


    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("categories", categoryModel.retrieveAll());
        req.setAttribute("products", productModel.retrieveAllCategory(Integer.parseInt(req.getParameter("categoryid"))));
        ViewManager.nextView(req, resp, "/view/category.jsp");
    }
}