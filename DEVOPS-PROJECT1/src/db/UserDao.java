package db;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {



    // 비밀번호를 해시로 변환하는 메서드
    private String hashPassword(String password) {
        try {
            // SHA-256 해시 함수 선택
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // 비밀번호를 바이트 배열로 변환하여 해시 계산
            byte[] hash = digest.digest(password.getBytes());

            // 해시 값을 16진수 문자열로 변환
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean insertUser(String user_id, String user_pw, String user_name, String user_email, int user_age, int user_height, String user_gender, int inbody_weight) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = MySqlDBManager.getInstance();
            // 비밀번호를 해시로 변환
            String hashedPassword = hashPassword(user_pw);

            // SQL 쿼리 작성
            String sql = "CALL insert_user (?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user_id);
            stmt.setString(2, hashedPassword);
            stmt.setString(3, user_name);
            stmt.setString(4, user_email);
            stmt.setInt(5, user_age);
            stmt.setInt(6, user_height);
            stmt.setString(7, user_gender);
            stmt.setInt(8, inbody_weight);

            // 쿼리 실행
            stmt.executeUpdate();
            System.out.println("가입 성공!");
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
}
