package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Exercise;

public class ExerciseDAO {
	public Exercise[] select_today_exercise(String[]exericse_name){
		PreparedStatement pstmt=null;
		Connection conn=null;
		ResultSet rs=null;
		try {
			 Exercise[] exercise_arr=new Exercise[4];
			 conn=MySqlDBManager.getInstance();
			 String sql = "SELECT * FROM exercise WHERE ex_name in(?,?,?,?)";
			 pstmt = conn.prepareStatement(sql);
			 for(int i=0; i<4; i++) pstmt.setString(i+1,exericse_name[i]);
			 rs = pstmt.executeQuery();
			 int idx=0;
			 while(rs.next()) {
				 exercise_arr[idx]=new Exercise(rs.getString("ex_name"),rs.getString("ex_detail"));
				 //System.out.println(exercise_arr[idx].getName()+"from ExerciseDAO");
				 idx++;
			 }
			 return exercise_arr;
		} catch (Exception e) {
			System.out.println("ExerciseDAO 예외 발생");
			return null;
		} finally {
			MySqlDBManager.disconnect(conn,pstmt, rs);
		}
	}
}
