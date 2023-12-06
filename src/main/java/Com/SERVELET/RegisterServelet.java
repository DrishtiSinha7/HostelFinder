package Com.SERVELET;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Com.DB.DBConnect;
import Com.dao.UserDAO;
import Com.entity.User;

@WebServlet("/add_user")
public class RegisterServelet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            String qua = req.getParameter("qua");
            String email = req.getParameter("email");
            String plainPassword = req.getParameter("ps");

            // Hashing the password using MD5
            String hashedPassword = hashPasswordMD5(plainPassword);

            UserDAO dao = new UserDAO(DBConnect.getConn());

            User u = new User(name, email, hashedPassword, qua, "User");
            boolean f = dao.addUser(u);
            HttpSession session = req.getSession();
            if (f) {
                session.setAttribute("succMsg", "Registration Successfully");
                resp.sendRedirect("signup.jsp");
            } else {
                session.setAttribute("succMsg", "Something wrong on the server");
                resp.sendRedirect("signup.jsp");
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
