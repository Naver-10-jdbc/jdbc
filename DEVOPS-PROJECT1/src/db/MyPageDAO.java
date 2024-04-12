package db;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import view.logn.Session;

public class MyPageDAO {
	
	public UsersData loginData() {
        Connection conn = null;
        PreparedStatement stmt = null;

        String user_id = Session.getInstance().getUserId();

        try {
            conn = MySqlDBManager.getInstance();

            // SQL 孽府 累己
            String sql = "select * from users where user_id =?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user_id);
 
            // 孽府 角青
            ResultSet rs = stmt.executeQuery();
            UsersData  usersData=new UsersData ();;
            if(rs.next()) {
            	usersData.setUser_name(rs.getString(3));
            	usersData.setUser_height(rs.getString(6));
            	usersData.setUser_weight(rs.getString(7));
            	usersData.setUser_gender(rs.getString(8));
            }
            rs.close();
            return usersData;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
	}
}
