package cantroler;

import model.Rezalt;
import model.User;
import service.DbService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/register")
public class Register extends HttpServlet {
    DbService dbService = new DbService();
    Rezalt rezalt = new Rezalt();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("saingup.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fristname = req.getParameter("Fristname");
        String lastName = req.getParameter("LastName");
        String userName = req.getParameter("UserName");
        String phoneNumber = req.getParameter("phoneNumber");
        String password = req.getParameter("password");
        String prepassword = req.getParameter("prepassword");
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();

        if (password.length()>=8 ){
            if (prepassword.equals(password)){
                Rezalt registration = dbService.registration(new User(fristname, lastName, userName, phoneNumber, password));
                if (registration.isSuccses()){
                    printWriter.write("<h1>"+registration.getMase()+"</h1>");
                }else printWriter.write("<h1>"+registration.getMase()+"</h1>");
            }
        }else printWriter.write("<h4>parol 8 simvildan iborat emas va boshlanishi katta harf bo'lishi kerak</h4>");

    }
}
