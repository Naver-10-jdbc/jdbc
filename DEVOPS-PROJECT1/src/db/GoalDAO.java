package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.exceptions.RSAException;
import com.mysql.cj.protocol.a.LocalDateTimeValueEncoder;

import model.GraphXY;
import view.logn.Session;

public class GoalDAO {
	PreparedStatement pstmt;
	Connection conn;
	ResultSet rs;
	
	// �ش� �������� ���� ü�� �Է��ߴ��� Ȯ��(�Է������� true, �������� false)
	public boolean IsEntered_Weight_Today() {
		try {
			conn=MySqlDBManager.getInstance();
			String sql ="select count(*) from goal where user_id=? AND goal_date=?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,Session.getInstance().getUserId());	//user_id�� �ٲٱ�.
			pstmt.setString(2,LocalDate.now().toString());
			rs = pstmt.executeQuery();
			rs.next();
			if(rs.getInt("count(*)")>=1) return true;
			else return false;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("IsEntered_Weight_Today ���� from GoalDAO");
			return false;
		} finally {
			MySqlDBManager.disconnect(conn,pstmt, rs);
		}
	}
	
	// ������ ������ �� �־��ֱ�.
	public boolean insert_Weight(int weight){ //�����۵�,ID���� ������ �ٲ��ֱ�.
		try {
			conn=MySqlDBManager.getInstance();
			String sql ="insert into goal(user_id,goal_weight,goal_date) values(?,?,?);";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,Session.getInstance().getUserId());	//user_id�� �ٲٱ�.
			pstmt.setInt(2, weight);
			pstmt.setString(3, LocalDate.now().toString());
			int rows = pstmt.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("insert_weight ���� from GoalDAO");
			return false;
		} finally {
			MySqlDBManager.disconnect(conn,pstmt, rs);
		}
	}
	
	//�׷��� �׸������� �ش� ������ ������ ������ ��������
	public List<GraphXY> select_Weight(int month){ //�����۵�.
		try {
			 conn=MySqlDBManager.getInstance();
			 String sql ="select goal_weight,date_format(goal_date,'%d')as weightday FROM goal WHERE user_id=? order by 2";
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1,Session.getInstance().getUserId());	
			 rs = pstmt.executeQuery();
			 List<GraphXY> list=new ArrayList<GraphXY>();
			 while(rs.next()) {
				 list.add(new GraphXY(rs.getInt("weightday"), rs.getInt("goal_weight")));
				 System.out.println(rs.getInt("weightday")+" / "+ rs.getInt("goal_weight")+" from GoalDAO" );
			 }
			 return list;
		} catch (Exception e) {
			System.out.println("���ܹ߻� select_Weight from GoalDAO");
			return null;
		} finally {
			MySqlDBManager.disconnect(conn,pstmt, rs);
		}
	}
	
	// �ش� �������� ���� ��� �ߴ��� ����Ȯ��(������� true, �������� false)
	public boolean IsEntered_Exercise_Today() {
		try {
			conn=MySqlDBManager.getInstance();
			String sql ="select goal_exercise from goal where user_id=? AND goal_date=?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,Session.getInstance().getUserId());	//user_id�� �ٲٱ�.
			pstmt.setString(2,LocalDate.now().toString());
			rs = pstmt.executeQuery();
			rs.next();
			if(rs.getString("goal_exercise")==null)return false;
			else return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("IsEntered_Exercise_Today() ���� from GoalDAO");
			return false;
		} finally {
			MySqlDBManager.disconnect(conn,pstmt, rs);
		}
	}
}
