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
import Com.entity.Hostels;

@WebServlet("/update")
public class UpdateHostelServelet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			String name = req.getParameter("title");
			String location = req.getParameter("location");
			String category = req.getParameter("category");
			String status = req.getParameter("Rooms avalible");
			String desc = req.getParameter("desc");

			Hostels h = new Hostels();
			h.setId(id);
			h.setName(name);
			h.setDescription(desc);
			h.setLocation(location);
			h.setCategory(category);
			h.setStatus(status);

			HttpSession session = req.getSession();

			hostelDAO dao = new hostelDAO(DBConnect.getConn());
			boolean f = dao.updateHostel(h);
			if (f) {
				session.setAttribute("succMsg", "job Update Sucessfully..");
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
