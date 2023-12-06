package Com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.JobSheets;

import org.apache.naming.java.javaURLContextFactory;

import Com.entity.Hostels;

public class hostelDAO {

	private Connection conn;

	public hostelDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	public boolean addHostels(Hostels h) {

		boolean f = false;
		try {

			String sql = "insert into hostel(name,description,category,status,location) values(?,?,?,?,?) ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, h.getName());
			ps.setString(2, h.getDescription());
			ps.setString(3, h.getCategory());
			ps.setString(4, h.getStatus());
			ps.setString(5, h.getLocation());

			int i = ps.executeUpdate();

			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	public List<Hostels> getAllHostels() {

		List<Hostels> list = new ArrayList<Hostels>();
		Hostels h = null;

		try {

			String sql = "select * from hostel order by id desc";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				h = new Hostels();
				h.setId(rs.getInt(1));
				h.setName(rs.getString(2));
				h.setDescription(rs.getString(3));
				h.setCategory(rs.getString(4));
				h.setStatus(rs.getString(5));
				h.setLocation(rs.getString(6));
				h.setPdate(rs.getTimestamp(7) + ""); // to convert into string
				list.add(h);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Hostels> getAllHostelsForUser() {

		List<Hostels> list = new ArrayList<Hostels>();
		Hostels h = null;

		try {

			String sql = "select * from hostel where status=? order by id desc";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "avaliable");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				h = new Hostels();
				h.setId(rs.getInt(1));
				h.setName(rs.getString(2));
				h.setDescription(rs.getString(3));
				h.setCategory(rs.getString(4));
				h.setStatus(rs.getString(5));
				h.setLocation(rs.getString(6));
				h.setPdate(rs.getTimestamp(7) + ""); // to convert into string
				list.add(h);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public Hostels getHostelsById(int id) {

		Hostels h = null;

		try {

			String sql = "select * from hostel where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				h = new Hostels();
				h.setId(rs.getInt(1));
				h.setName(rs.getString(2));
				h.setDescription(rs.getString(3));
				h.setCategory(rs.getString(4));
				h.setStatus(rs.getString(5));
				h.setLocation(rs.getString(6));
				h.setPdate(rs.getTimestamp(7) + ""); // to convert into string

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return h;
	}

	public boolean updateHostel(Hostels h) {
		boolean f = false;
		try {

			String sql = "update hostel SET name=?,description=?,category=?,status=?,location=? where id=? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, h.getName());
			ps.setString(2, h.getDescription());
			ps.setString(3, h.getCategory());
			ps.setString(4, h.getStatus());
			ps.setString(5, h.getLocation());
			ps.setInt(6, h.getId());

			int i = ps.executeUpdate();

			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;

	}
	
	public boolean deleteHostels(int id) {
		boolean f=false;
		try {
			String sql="delete from hostel where id=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			int i=ps.executeUpdate();
			if (i==1) {
				f =true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	public List<Hostels> getJobsORLocationAndCate(String category, String location) {
		
		List<Hostels> list = new ArrayList<Hostels>();
		Hostels h= null;
		try {
			String sql="select * from hostel where category=? or location=? order by id DESC";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, category);
			ps.setString(2, location);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				h = new Hostels();
				h.setId(rs.getInt(1));
				h.setName(rs.getString(2));
				h.setDescription(rs.getString(3));
				h.setCategory(rs.getString(4));
				h.setStatus(rs.getString(5));
				h.setLocation(rs.getString(6));
				h.setPdate(rs.getString(7));
				list.add(h);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
public List<Hostels> getJobsAndLocationAndCate(String category, String location) {
		
		List<Hostels> list = new ArrayList<Hostels>();
		Hostels h= null;
		try {
			String sql="select * from hostel where category=? and location=? order by id DESC";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, category);
			ps.setString(2, location);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				h = new Hostels();
				h.setId(rs.getInt(1));
				h.setName(rs.getString(2));
				h.setDescription(rs.getString(3));
				h.setCategory(rs.getString(4));
				h.setStatus(rs.getString(5));
				h.setLocation(rs.getString(6));
				h.setPdate(rs.getString(7));
				list.add(h);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
