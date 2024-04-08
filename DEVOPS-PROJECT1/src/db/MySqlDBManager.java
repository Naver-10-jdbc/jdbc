package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySqlDBManager {
   private static Connection connection;
   public static Connection getInstance(){
      try {
         if(connection==null) {   
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/dev";
            String id=" ";
            String pwd=" ";
            connection=DriverManager.getConnection(url,id,pwd);
         }
         return connection;
      } catch (Exception e) {
         return null;
      }
   }
    public static void disconnect(Connection connection, PreparedStatement pstmt, ResultSet rs) {
        try { rs.close(); } catch (Exception e) { }
        try { pstmt.close(); } catch (Exception e) { }
        try { connection.close(); } catch (Exception e) { }
    }
}