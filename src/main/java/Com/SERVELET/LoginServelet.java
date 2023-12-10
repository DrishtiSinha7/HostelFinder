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
import Com.dao.HostelAdministrationDAO;
import Com.entity.User;
import Com.entity.HostelAdministration;

@WebServlet("/login")
public class LoginServelet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String email = req.getParameter("email");
            String plainPassword = req.getParameter("password");
            String role = req.getParameter("role");

            // Hashing the password using MD5
            String hashedPassword = hashPasswordMD5(plainPassword);

            HttpSession session = req.getSession();

            if ("admin@gmail.com".equals(email) && "admin@121".equals(plainPassword)) {
                User adminUser = new User();
                adminUser.setRole("admin");
                session.setAttribute("userobj", adminUser);
                resp.sendRedirect("admin.jsp");
            } else {
                if ("user".equals(role)) {
                    UserDAO userDAO = new UserDAO(DBConnect.getConn());
                    User user = userDAO.login(email, hashedPassword, role);

                    if (user != null) {
                        session.setAttribute("userobj", user);
                        resp.sendRedirect("home.jsp");
                    } else {
                        session.setAttribute("succMsg", "Invalid Email & Password");
                        resp.sendRedirect("login.jsp");
                    }
                } else if ("hostelAdministration".equals(role)) {
                    HostelAdministrationDAO hostelAdminDAO = new HostelAdministrationDAO(DBConnect.getConn());
                    HostelAdministration hostelAdmin = hostelAdminDAO.login(email, hashedPassword, role);

                    if (hostelAdmin != null) {
                        session.setAttribute("userobj", hostelAdmin);
                        resp.sendRedirect("adminHome.jsp"); // Change to the appropriate page for Hostel Administration
                    } else {
                        session.setAttribute("succMsg", "Invalid Email & Password");
                        resp.sendRedirect("login.jsp");
                    }
                } else {
                    session.setAttribute("succMsg", "Invalid Role");
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
