package cantroler;

import model.User;
import service.DbService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("login.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        DbService dbService = new DbService();
        User login = dbService.login(new User(username, password));
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();

        if (login == null){
            printWriter.write("<h2> username yoki password xato");
        }else  printWriter.write("<h1> Tizimga xush kelipsiz "+ login.getFristName() + " " + login.getLastName());
    }
}
