package db;

import java.sql.Connection;
import java.sql.PreparedStatement;

import model.User;

public class UserDao {
    private static final Connection CONNECTION = MySqlDBManager.getInstance();
    
    //회원 가입시
    public static int Save(String id,String pw) {
       String sql = "INSERT INTO user(user_id,user_pw) VALUES (?, ?)"; 
       try {
          PreparedStatement psmt=CONNECTION.prepareStatement(sql);
         // 나머지 로직
          return psmt.executeUpdate();
      } catch (Exception e) {
         return -1;
         // TODO: handle exception
      }
    }
}