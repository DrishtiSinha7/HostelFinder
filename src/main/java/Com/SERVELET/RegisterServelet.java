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

@WebServlet("/add_user")
public class RegisterServelet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String plainPassword = req.getParameter("ps");
            String role = req.getParameter("role"); // Assuming you have a role parameter in your form

            // Hashing the password using MD5
            String hashedPassword = hashPasswordMD5(plainPassword);

            HttpSession session = req.getSession();

            if ("HostelAdministration".equals(role)) {
                // Set qualification to null or empty string
                String qualification = null; // or qualification = "";

                HostelAdministrationDAO hostelAdminDAO = new HostelAdministrationDAO(DBConnect.getConn());
                HostelAdministration hostelAdmin = new HostelAdministration(name, email, hashedPassword, role, qualification);
                boolean f = hostelAdminDAO.addHostelAdministration(hostelAdmin);
                UserDAO dao = new UserDAO(DBConnect.getConn());
                if (f) {
                    session.setAttribute("succMsg", "Hostel Administrator Registration Successful");
                    resp.sendRedirect("signup.jsp");
                } else {
                    session.setAttribute("errMsg", "Something went wrong while creating Hostel Administrator");
                    resp.sendRedirect("signup.jsp");
                }
            } else {
                // Default role is "user"
                String qua = req.getParameter("qua"); // Assuming qua is the qualification parameter
                UserDAO userDAO = new UserDAO(DBConnect.getConn());
                User user = new User(name, email, hashedPassword, qua, "user");
                boolean f = userDAO.addUser(user);

                if (f) {
                    session.setAttribute("succMsg", "User Registration Successful");
                    resp.sendRedirect("signup.jsp");
                } else {
                    session.setAttribute("errMsg", "Something went wrong while creating User");
                    resp.sendRedirect("signup.jsp");
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
