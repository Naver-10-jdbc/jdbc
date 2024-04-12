package db;

import java.sql.*;

public class MySqlDBManager {
   private static Connection connection;
   public static Connection getInstance(){
      try {
         if(connection==null || connection.isClosed()) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://192.168.0.33:3306/test";
            String id="root";
            String pwd="0124";
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

    public static void disconnect(Connection connection, CallableStatement cstmt, ResultSet rs) {
        try { rs.close(); } catch (Exception e) { }
        try { cstmt.close(); } catch (Exception e) { }
        try { connection.close(); } catch (Exception e) { }
    }
}