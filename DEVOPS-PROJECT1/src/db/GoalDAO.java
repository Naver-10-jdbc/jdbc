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
	
	// 해당 계정에서 오늘 체중 입력했는지 확인(입력했으면 true, 안했으면 false)
	public boolean IsEntered_Weight_Today() {
		try {
			conn=MySqlDBManager.getInstance();
			String sql ="select count(*) from goal where user_id=? AND goal_date=?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,Session.getInstance().getUserId());	//user_id값 바꾸기.
			pstmt.setString(2,LocalDate.now().toString());
			rs = pstmt.executeQuery();
			rs.next();
			if(rs.getInt("count(*)")>=1) return true;
			else return false;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("IsEntered_Weight_Today 예외 from GoalDAO");
			return false;
		} finally {
			MySqlDBManager.disconnect(conn,pstmt, rs);
		}
	}
	
	// 유저에 몸무게 값 넣어주기.
	public boolean insert_Weight(int weight){ //정상작동,ID값만 다음에 바꿔주기.
		try {
			conn=MySqlDBManager.getInstance();
			String sql ="insert into goal(user_id,goal_weight,goal_date) values(?,?,?);";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,Session.getInstance().getUserId());	//user_id값 바꾸기.
			pstmt.setInt(2, weight);
			pstmt.setString(3, LocalDate.now().toString());
			int rows = pstmt.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("insert_weight 예외 from GoalDAO");
			return false;
		} finally {
			MySqlDBManager.disconnect(conn,pstmt, rs);
		}
	}
	
	//그래프 그리기위해 해당 유저의 몸무게 데이터 가져오기
	public List<GraphXY> select_Weight(int month){ //정상작동.
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
			System.out.println("예외발생 select_Weight from GoalDAO");
			return null;
		} finally {
			MySqlDBManager.disconnect(conn,pstmt, rs);
		}
	}
	
	// 해당 계정에서 오늘 운동을 했는지 여부확인(운동했으면 true, 안했으면 false)
	public boolean IsEntered_Exercise_Today() {
		try {
			conn=MySqlDBManager.getInstance();
			String sql ="select goal_exercise from goal where user_id=? AND goal_date=?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,Session.getInstance().getUserId());	//user_id값 바꾸기.
			pstmt.setString(2,LocalDate.now().toString());
			rs = pstmt.executeQuery();
			rs.next();
			if(rs.getString("goal_exercise")==null)return false;
			else return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("IsEntered_Exercise_Today() 예외 from GoalDAO");
			return false;
		} finally {
			MySqlDBManager.disconnect(conn,pstmt, rs);
		}
	}
}
