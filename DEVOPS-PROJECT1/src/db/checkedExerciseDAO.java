package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import view.logn.Session;

public class checkedExerciseDAO {
	PreparedStatement pstmt;
	Connection conn;
	ResultSet rs;
	
	// 해당 계정에서 오늘 운동했는지 확인(운동했으면 true, 안했으면 false), 정상작동
	public boolean IsExercise_Today() {
		try {
			conn=MySqlDBManager.getInstance();
			String sql ="select count(*) from checked_exercise where user_id=? AND checked_date=?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,Session.getInstance().getUserId());	//user_id값 바꾸기.
			pstmt.setString(2,LocalDate.now().toString());
			rs = pstmt.executeQuery();
			rs.next();
			if(rs.getInt("count(*)")>=1) return true;
			else return false;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("IsExercise_Today() 예외 from checkedExerciseDAO");
			return false;
		} finally {
			MySqlDBManager.disconnect(conn,pstmt, rs);
		}
	}
	
	// 오늘날짜로 유저정보 저장하기.(운동했다는 의미), 정상작동
		public boolean insert_Exercising_Today(){ //정상작동,ID값만 다음에 바꿔주기.
			try {
				conn=MySqlDBManager.getInstance();
				String sql ="insert into checked_exercise(user_id,checked_date) values(?,?);";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,Session.getInstance().getUserId());	//user_id값 바꾸기.
				pstmt.setString(2,LocalDate.now().toString());
				int rows = pstmt.executeUpdate();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("insert_Exercising_Today() 예외 from checkedExerciseDAO ");
				return false;
			} finally {
				MySqlDBManager.disconnect(conn,pstmt, rs);
			}
		}
}
