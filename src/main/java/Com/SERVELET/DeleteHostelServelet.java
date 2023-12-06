package Com.SERVELET;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Com.DB.DBConnect;
import Com.dao.hostelDAO;


@WebServlet("/delete")
public class DeleteHostelServelet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(req.getParameter("id"));

			hostelDAO dao = new hostelDAO(DBConnect.getConn());
			boolean f = dao.deleteHostels(id);
			HttpSession session = req.getSession();

			if (f) {
				session.setAttribute("succMsg", "job Delete Sucessfully..");
				resp.sendRedirect("ViewHostel.jsp");
			} else {
				session.setAttribute("succMsg", "something wrong on server");
				resp.sendRedirect("ViewHostel.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
