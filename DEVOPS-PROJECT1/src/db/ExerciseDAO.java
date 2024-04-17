package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Exercise;

public class ExerciseDAO {
	/*�̽�: ���� ��õ��� ������ �̸��� ������, �����͸� 4���� �ƴ� 3���� ������ ���Ե� -> ������ 3���� �ڿ����� NULL�߻�*/
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
				 exercise_arr[idx]=new Exercise(rs.getString("ex_name"),rs.getString("ex_detail"), rs.getBytes("ex_img"));
				 //System.out.println(exercise_arr[idx].getName()+"from ExerciseDAO");
				 //System.out.println(rs.getBytes("ex_img")[0]+"from ExerciseDAO");				 
				 //System.out.println("idx:"+idx);
				 idx++;
				 
			 }
			 return exercise_arr;
		} catch (Exception e) {
			System.out.println("ExerciseDAO ���� �߻�");
			return null;
		} finally {
			MySqlDBManager.disconnect(conn,pstmt, rs);
		}
	}
}
