package db;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import view.logn.Session;

public class MyPageDAO {
	Connection conn = null;
	PreparedStatement stmt = null;
	String user_id = Session.getInstance().getUserId();
	
	public UsersData loginData() {
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
            	usersData.setUser_name(rs.getString(4));
            	usersData.setUser_height(rs.getString(7));
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
	public boolean updateUserHeight(String user_id, double height) {
        try {
            conn = MySqlDBManager.getInstance();

            // SQL 孽府 累己
            String sql = new StringBuilder()
                    		.append("UPDATE users SET ")
                    		.append("user_height= ? ")
                    		.append("WHERE user_id= ?")
                    		
                    		.toString();
            
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, height);
            stmt.setString(2, user_id);
            
 
            // 孽府 角青
            int update = stmt.executeUpdate();
            System.out.println("诀单捞飘 荐: "+update);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
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
	public InbodyData inbodyData() {
        try {
            conn = MySqlDBManager.getInstance();

            // SQL 孽府 累己
            String sql = "select * from inbody where user_id =?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user_id);
 
            // 孽府 角青
            ResultSet rs = stmt.executeQuery();
            InbodyData  inbodyData=new InbodyData();;
            if(rs.next()) {
            	inbodyData.setInbody_cid(rs.getString(2));
            	inbodyData.setInbody_weight(rs.getString(3));
            	inbodyData.setInbody_muscle(rs.getString(4));
            	inbodyData.setInbody_bodyfat(rs.getString(5));
            }
            rs.close();
            return inbodyData;
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
	public boolean updateInbody(String user_id, String cid, double weight, double muscle, double bodyfat) {
        try {
            conn = MySqlDBManager.getInstance();

            // SQL 孽府 累己
            String sql = new StringBuilder()
                    		.append("UPDATE inbody SET ")
                    		.append("inbody_cid= ?, ")
                    		.append("inbody_weight= ?, ")
                    		.append("inbody_muscle= ?, ")
                    		.append("inbody_bodyfat= ? ")
                    		.append("WHERE user_id= ?")
                    		.toString();
            
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cid);
            stmt.setDouble(2, weight);
            stmt.setDouble(3, muscle);
            stmt.setDouble(4, bodyfat);
            stmt.setString(5, user_id);
 
            // 孽府 角青
            int update = stmt.executeUpdate();
            System.out.println("诀单捞飘 荐: "+update);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
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
	public boolean updateTarget(String user_id,double weight, double muscle, double bodyfat, double sleep,  String cid,  int level) {
        try {
            conn = MySqlDBManager.getInstance();

            // SQL 孽府 累己
            String sql = new StringBuilder()
                    		.append("UPDATE target SET ")
                    		.append("target_cid= ?, ")
                    		.append("target_weight= ?, ")
                    		.append("target_muscle= ?, ")
                    		.append("target_bodyfat= ?, ")
                    		.append("target_sleep= ?, ")
                    		.append("targrt_level= ? ")
                    		.append("WHERE user_id= ?")
                    		.toString();
            
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cid);
            stmt.setDouble(2, weight);
            stmt.setDouble(3, muscle);
            stmt.setDouble(4, bodyfat);
            stmt.setDouble(5, sleep);
            stmt.setInt(6, level);
            stmt.setString(7, user_id);
 
            // 孽府 角青
            int update = stmt.executeUpdate();
            System.out.println("诀单捞飘 荐: "+update);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
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
	public TargetData targetData() {
        try {
            conn = MySqlDBManager.getInstance();

            // SQL 孽府 累己
            String sql = "select * from target where user_id =?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user_id);
 
            // 孽府 角青
            ResultSet rs = stmt.executeQuery();
            TargetData  targetData=new TargetData();;
            if(rs.next()) {
            	targetData.setTarget_cid(rs.getString(2));
            	targetData.setTarget_weight(rs.getString(3));
            	targetData.setTarget_muscle(rs.getString(4));
            	targetData.setTarget_bodyfat(rs.getString(5));
            	targetData.setTarget_sleep(rs.getString(6));
            	targetData.setTargrt_level(rs.getString(7));
            }
            rs.close();
            return targetData;
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
