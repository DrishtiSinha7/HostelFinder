package Com.SERVELET;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Com.DB.DBConnect;
import Com.dao.UserDAO;
import Com.entity.User;

@WebServlet("/login")
public class LoginServelet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            String em = req.getParameter("email");
            String plainPassword = req.getParameter("password");

            // Hashing the password using MD5
            String hashedPassword = hashPasswordMD5(plainPassword);

            User u = new User();
            HttpSession session = req.getSession();

            if ("admin@gmail.com".equals(em) && "admin@121".equals(plainPassword)) {
                u.setRole("admin");
                session.setAttribute("userobj", u);
                resp.sendRedirect("admin.jsp");
            } else {
                UserDAO dao = new UserDAO(DBConnect.getConn());
                User user = dao.login(em, hashedPassword);
                if (user != null) {
                    session.setAttribute("userobj", user);
                    resp.sendRedirect("home.jsp");
                } else {
                    session.setAttribute("succMsg", "Invalid Email & Password");
                    resp.sendRedirect("login.jsp");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Hashing method using MD5
    private String hashPasswordMD5(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        return new BigInteger(1, digest).toString(16);
    }
}
