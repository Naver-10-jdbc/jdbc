package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.exceptions.RSAException;

import model.GraphXY;
import view.logn.Session;

public class GoalDAO {
	PreparedStatement pstmt;
	Connection conn;
	ResultSet rs;
	public boolean insert_Weight(int weight){ //정상작동,ID값만 다음에 바꿔주기.
		try {
			conn=MySqlDBManager.getInstance();
			String sql ="insert into goal(user_id,goal_weight) values(?,?);";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,Session.getInstance().getUserId());	//user_id값 바꾸기.
			pstmt.setInt(2, weight);
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
				 //System.out.println(rs.getInt("weightday")+" / "+ rs.getInt("goal_weight") );
			 }
			 return list;
		} catch (Exception e) {
			System.out.println("예외발생 from GoalDAO");
			return null;
		} finally {
			MySqlDBManager.disconnect(conn,pstmt, rs);
		}
	}
}
