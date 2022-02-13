package cantroler;

import model.User;
import service.DbService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(value = "/cabinet")
public class Cabinet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        Cookie [] cookies = req.getCookies();
        String username = "";
        if (cookies!=null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Appauth")){
                  username = cookie.getValue();
                  break;
                }
                
            }
        }
        DbService dbService = new DbService();
        User cookebayuser = dbService.cookebayuser(new User(username));
        if (cookebayuser==null){
            Cookie cookie = new Cookie("Appauth","");
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
            resp.sendRedirect("/");
        }
        if (cookebayuser!=null) {
            printWriter.write("<h1 tyle=\"text-align: center\">Cabinetga xush kelibsiz</h1>");
            printWriter.write("<h1 tyle=\"text-align: center\">" + cookebayuser.getFristName() + " " + cookebayuser.getLastName() + " sizning tefon nomeringiz" + cookebayuser.getPhoneNumber());
        }
    }

}

