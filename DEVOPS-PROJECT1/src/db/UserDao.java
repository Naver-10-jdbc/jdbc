package db;

import java.sql.Connection;
import java.sql.PreparedStatement;

import model.User;

public class UserDao {
    private static final Connection CONNECTION = MySqlDBManager.getInstance();
    
    //ȸ�� ���Խ�
    public static int Save(String id,String pw) {
       String sql = "INSERT INTO user(user_id,user_pw) VALUES (?, ?)"; 
       try {
          PreparedStatement psmt=CONNECTION.prepareStatement(sql);
         // ������ ����
          return psmt.executeUpdate();
      } catch (Exception e) {
         return -1;
         // TODO: handle exception
      }
    }
}