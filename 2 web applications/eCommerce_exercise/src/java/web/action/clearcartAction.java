package web.action;

import javax.servlet.http.*;
import model.CategoryModel;
import web.ViewManager;

public class clearcartAction implements Action {


    public clearcartAction(){
    }

    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        ViewManager.nextView(req, resp, "/view/init.jsp");
    }
}