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
@WebServlet("/add_hostel")
public class AddPostServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String title=req.getParameter("title");
			String location=req.getParameter("location");
			String category=req.getParameter("category");
			String status=req.getParameter("Rooms avaliable");
			String  desc=req.getParameter("desc");
			
			Hostels h = new Hostels();
			h.setName(title);
			h.setDescription(desc);
			h.setLocation(location);
			h.setStatus(status);
			h.setCategory(category);
			
			HttpSession session=req.getSession();
			
			hostelDAO dao=new hostelDAO(DBConnect.getConn()); 
			boolean f=dao.addHostels(h);
			if(f) {
				session.setAttribute("succMsg","job Post Sucessfully..");
				resp.sendRedirect("addhostel.jsp");
			}else {
				session.setAttribute("succMsg","something wrong on server");
				resp.sendRedirect("addhostel.jsp");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
  
}
