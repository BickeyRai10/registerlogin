package com.registerlogin.user.dao;

import com.registerlogin.user.model.UserModel;
import com.registerlogin.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    public boolean isEmailExists(String email) {
        String sql = "Select * from user where email=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Error while searching user" + e.getMessage());
            return false;
        }
    }

    public boolean insertUser(UserModel user) {
        String sql = "Insert into user (name,email,password) VALUES(?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            int row = ps.executeUpdate();
            return row > 0;
        } catch (Exception e) {
            System.out.println("Error while adding user" + e.getMessage());
            return false;
        }
    }

    public UserModel getUSerDetail(String email) {
        String sql = "SELECT * FROM user WHERE email=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                UserModel user = new UserModel();
                int id = rs.getInt("id");
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                return user;
            }
            return null;
        } catch (Exception e) {
            System.out.println("error while fetching user");
            return null;
        }
    }
}

