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
	
	// �ش� �������� ���� ��ߴ��� Ȯ��(������� true, �������� false), �����۵�
	public boolean IsExercise_Today() {
		try {
			conn=MySqlDBManager.getInstance();
			String sql ="select count(*) from checked_exercise where user_id=? AND checked_date=?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,Session.getInstance().getUserId());	//user_id�� �ٲٱ�.
			pstmt.setString(2,LocalDate.now().toString());
			rs = pstmt.executeQuery();
			rs.next();
			if(rs.getInt("count(*)")>=1) return true;
			else return false;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("IsExercise_Today() ���� from checkedExerciseDAO");
			return false;
		} finally {
			MySqlDBManager.disconnect(conn,pstmt, rs);
		}
	}
	
	// ���ó�¥�� �������� �����ϱ�.(��ߴٴ� �ǹ�), �����۵�
		public boolean insert_Exercising_Today(){ //�����۵�,ID���� ������ �ٲ��ֱ�.
			try {
				conn=MySqlDBManager.getInstance();
				String sql ="insert into checked_exercise(user_id,checked_date) values(?,?);";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,Session.getInstance().getUserId());	//user_id�� �ٲٱ�.
				pstmt.setString(2,LocalDate.now().toString());
				int rows = pstmt.executeUpdate();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("insert_Exercising_Today() ���� from checkedExerciseDAO ");
				return false;
			} finally {
				MySqlDBManager.disconnect(conn,pstmt, rs);
			}
		}
}
