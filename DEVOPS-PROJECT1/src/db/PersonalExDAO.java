package db;

import java.sql.PreparedStatement;
import java.util.ArrayList;

import model.PersonalEx;
import view.logn.Session;

import java.sql.*;

public class PersonalExDAO {
	PreparedStatement pstmt;
	Connection conn;
	ResultSet rs;
	public String[][] select_PerEx(){
		String Week_of_Exercise[][]=new String[8][5]; //[*][0]:요일, [*][1~4]:운동명
		Init_Week_Of_Exercise(Week_of_Exercise);
		try {
			 conn=MySqlDBManager.getInstance();
			 String sql = "SELECT * FROM personal_exercise WHERE user_id=? order by weekend";
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1,"example_user7");	//user_id값 바꾸기.
			 rs = pstmt.executeQuery();
			 int idx=1;
			 while(rs.next()) {
				 Week_of_Exercise[rs.getInt("weekend")][idx++]=rs.getString("ex_name");
				 if(idx==5) idx=1;
				// System.out.println(rs.getInt("weekend")+", "+rs.getString("ex_name"));
			 }
			 return Week_of_Exercise;
		} catch (Exception e) {
			System.out.println("PersonalExDAO 예외 발생");
			return null;
		} finally {
			MySqlDBManager.disconnect(conn,pstmt, rs);
		}
	}
    private void Init_Week_Of_Exercise(String Week_of_Exercise[][]) {
    	Week_of_Exercise[1][0]="월요일";
    	Week_of_Exercise[2][0]="화요일";
    	Week_of_Exercise[3][0]="수요일";
    	Week_of_Exercise[4][0]="목요일";
    	Week_of_Exercise[5][0]="금요일";
    	Week_of_Exercise[6][0]="토요일";
    	Week_of_Exercise[7][0]="일요일";
    }
}
