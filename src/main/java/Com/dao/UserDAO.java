package Com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Com.entity.User;

public class UserDAO {
    private Connection conn;

    public UserDAO(Connection conn) {
        super();
        this.conn = conn;
    }

    public boolean addUser(User u) {
        boolean f = false;
        try {
            String sql = "INSERT INTO user (name,email, password,qualification, role) VALUES (?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, u.getName());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getQualification());
            ps.setString(5, "user");
            int i = ps.executeUpdate();
            if (i == 1) {
                f = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    public User login(String em, String psw, String role) {
        User u = null;
        try {
            String sql = "SELECT * FROM user WHERE email=? AND password=? AND role=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, em);
            ps.setString(2, psw);
            ps.setString(3, role);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                u = new User();
                u.setId(rs.getInt(1));
                u.setName(rs.getString(2));
                u.setEmail(rs.getString(3));
                u.setPassword(rs.getString(4));
                u.setQualification(rs.getString(5));
                u.setRole(rs.getString(6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    public boolean updateUser(User u) {
        boolean f = false;
        try {
            String sql = "UPDATE user SET name=?, email=?, password=?, qualification=?, role=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, u.getName());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getQualification());
            ps.setString(5, u.getRole());
            ps.setInt(6, u.getId());

            int i = ps.executeUpdate();
            if (i == 1) {
                f = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
}
