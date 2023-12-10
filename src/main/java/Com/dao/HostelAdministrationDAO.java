package Com.dao;

import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.PreparedStatement;

import Com.entity.HostelAdministration;

public class HostelAdministrationDAO {
    private Connection conn;

    public HostelAdministrationDAO(Connection conn) {
        super();
        this.conn = conn;
    }

    public boolean addHostelAdministration(HostelAdministration hostelAdmin) {
        boolean isSuccess = false;

        try {
            // SQL query to insert hostel administration data into the 'hosteladministration' table
            String sql = "INSERT INTO hosteladministration (name, email, password, role, qualification) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            // Setting values for the placeholders in the SQL query
            ps.setString(1, hostelAdmin.getName());
            ps.setString(2, hostelAdmin.getEmail());
            ps.setString(3, hostelAdmin.getPassword());
            ps.setString(4, "HostelAdministration");
            ps.setString(5, hostelAdmin.getQualification());

            // Execute the SQL query
            int rowsAffected = ps.executeUpdate();

            // Check if one record was successfully inserted
            if (rowsAffected == 1) {
                isSuccess = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isSuccess;
    }
    
    public HostelAdministration login(String email, String hashedPassword, String role) {
        HostelAdministration hostelAdmin = null;
        String query = "SELECT * FROM hosteladministration WHERE email = ? AND password = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, email);
            pstmt.setString(2, hashedPassword);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Assuming your HostelAdministration class has appropriate setters
                    hostelAdmin = new HostelAdministration();
                    hostelAdmin.setEmail(rs.getString("email"));
                    // Set other properties here
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hostelAdmin;
    }
}
