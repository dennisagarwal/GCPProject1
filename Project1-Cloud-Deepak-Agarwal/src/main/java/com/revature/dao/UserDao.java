package com.revature.dao;

import com.revature.model.User;
import com.revature.utility.ConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public UserDao() {
    }

    public User getUserByUsernameAndPassword(String username, String password) throws SQLException {

        try(Connection con = ConnectionUtility.getConnection()){
          String sql = "SELECT users.id, users.username, users.password, user_role.role, users.first_name, users.last_name, " +
                  " users.email FROM users " +
                  "INNER JOIN user_role " +
                  "ON users.user_role_id = user_role.id " +
//                  "WHERE users.username = ? AND users.password = ?";
            "WHERE users.username = ? AND users.password = crypt(?, users.password)";

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1,username);
            pstmt.setString(2,password);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                int userId = rs.getInt("id");
                String un = rs.getString("username");
//                String pw = rs.getString("password");

                String fn = rs.getString("first_name");
                String ln = rs.getString("last_name");
                String em = rs.getString("email");
                String role = rs.getString("role");


//                return new User(userId, un, pw, role,fn,ln,em);
                return new User(userId, un,  fn,ln,em,role);
            }
            return null;
        }
    }
}
