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

@WebServlet("/update_profile")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String qua = req.getParameter("qua");
            String email = req.getParameter("email");
            String plainPassword = req.getParameter("ps");

            // Hash the password using MD5
            String hashedPassword = hashPasswordMD5(plainPassword);

            UserDAO dao = new UserDAO(DBConnect.getConn());

            User u = new User();
            u.setId(id);
            u.setName(name);
            u.setQualification(qua);
            u.setPassword(hashedPassword); // Store hashed password in the database
            u.setEmail(email);

            boolean f = dao.updateUser(u);
            HttpSession session = req.getSession();
            if (f) {
                session.setAttribute("succMsg", "Profile Updated Successfully");
                resp.sendRedirect("home.jsp");
            } else {
                session.setAttribute("succMsg", "Something went wrong on the server");
                resp.sendRedirect("home.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging purposes
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
