package demo.web;

import demo.spec.MessageWall;
import demo.spec.RemoteLogin;
import demo.spec.UserAccess;
import java.io.IOException;
import java.util.Hashtable;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ControllerServlet extends HttpServlet {
    
    
    

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        process(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        process(request, response);
    }

    protected void process(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        
        String view = perform_action(request);
        forwardRequest(request, response, view);
    }

    protected String perform_action(HttpServletRequest request)
        throws IOException, ServletException {
        
        
        String serv_path = request.getServletPath();
        HttpSession session = request.getSession();
        
        

        if (serv_path.equals("/login.do")) {
            RemoteLogin remoteLogin = (RemoteLogin) request.getServletContext().getAttribute("remoteLogin");
            UserAccess ua = remoteLogin.connect(request.getParameter("user"), request.getParameter("password"));
            
            if (ua != null){
                session.setAttribute("userAccess", ua);
                
                //return "/wallview";
                return "/view/wallview.jsp";
            }
            return "/error-no-user_access.html";
        } 
        
        else if (serv_path.equals("/put.do")) {
            UserAccess ua = (UserAccess)session.getAttribute("userAccess");
            if (ua != null){
                ua.put(request.getParameter("msg"));
                //return "/wallview";
                return "/view/wallview.jsp";
            }
            return "/error-not-loggedin.html";
        } 
        
        else if (serv_path.equals("/refresh.do")) {
            UserAccess ua = (UserAccess)session.getAttribute("userAccess");
            if (ua != null){
                ua.getLast();
                //return "/wallview";
                return "/view/wallview.jsp";
            }
            
            return "/error-not-loggedin.html";
        } 
        
        else if (serv_path.equals("/logout.do")) {
            if (session.getAttribute("userAccess") != null) {
                session.removeAttribute("userAccess");
            }
            return "/goodbye.html";
        } 
        
        else if (serv_path.equals("/delete.do")) {
            UserAccess ua = (UserAccess)session.getAttribute("userAccess");
            if (ua != null){
                // if user is the owner of the msg delete it and display wallview jsp
                // if someone else is the owner display the error-bad-action.html
                if (ua.delete(Integer.parseInt((String)request.getParameter("index")))){
                    return "/view/wallview.jsp";
                } else {
                    return "/error-bad-action.html";
                }
            }
            return "/error-not-loggedin.html";
        }
        
        else {
            return "/error-bad-action.html";
        }
    }

    public RemoteLogin getRemoteLogin() {
        return (RemoteLogin) getServletContext().getAttribute("remoteLogin");
    }
    public void forwardRequest(HttpServletRequest request, HttpServletResponse response, String view) 
            throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(view);
        if (dispatcher == null) {
            throw new ServletException("No dispatcher for view path '"+view+"'");
        }
        dispatcher.forward(request,response);
    }
}


