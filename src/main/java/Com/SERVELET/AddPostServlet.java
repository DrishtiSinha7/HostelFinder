package Com.SERVELET;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import Com.DB.DBConnect;
import Com.dao.hostelDAO;
import Com.entity.Hostels;

@MultipartConfig
@WebServlet("/add_hostel")
public class AddPostServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String title = req.getParameter("title");
			String location = req.getParameter("location");
			String category = req.getParameter("category");
			String status = req.getParameter("Rooms available"); // corrected parameter name
			String desc = req.getParameter("desc");

			Part file = req.getPart("image");
			String imageFileName=file.getSubmittedFileName();
			System.out.println("Selected file: " + imageFileName);
			String uploadPath="C:/Users/drish/eclipse-workspace/hostelhunt-main/target/m2e-wtp/web-resources/images/"+imageFileName;
			System.out.println("upload Path: " + uploadPath);
			
			
			try (FileOutputStream fos = new FileOutputStream(uploadPath);
				     InputStream is = file.getInputStream()) {
				byte[] data=new byte[is.available()];
				is.read(data);
				fos.write(data);
				fos.close();
						
				} catch (IOException e) {
				    e.printStackTrace();
				}
			
			Hostels h = new Hostels();
			h.setName(title);
			h.setDescription(desc);
			h.setLocation(location);
			h.setStatus(status);
			h.setCategory(category);
			h.setImageurl(imageFileName); // set the image URL

			HttpSession session = req.getSession();

			hostelDAO dao = new hostelDAO(DBConnect.getConn());
			boolean f = dao.addHostels(h);
			if (f) {
				session.setAttribute("succMsg", "Hostel Post Successful");
				resp.sendRedirect("addhostel.jsp");
			} else {
				session.setAttribute("succMsg", "Something went wrong on the server");
				resp.sendRedirect("addhostel.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
