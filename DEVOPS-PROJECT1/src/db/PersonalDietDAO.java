package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.PersonalDiet;
import model.PersonalEx;
import view.logn.Session;

public class PersonalDietDAO {
	 PreparedStatement pstmt;
	 Connection conn;
	 ResultSet rs;
	public ArrayList<PersonalDiet>[] select_PerDiet(){
		ArrayList<PersonalDiet>[]week_arr=new ArrayList[8];
		for(int i=0; i<8; i++) week_arr[i]=new ArrayList<PersonalDiet>();
		try {
			 conn=MySqlDBManager.getInstance();
			 System.out.println("conn");
			 String sql = "SELECT * FROM personal_diet WHERE user_id=?";
			 pstmt = conn.prepareStatement(sql);
			 System.out.println("pstmt");
			 pstmt.setString(1,"example_user9");
			 rs = pstmt.executeQuery();
			 System.out.println("rs");
			 while(rs.next()) {
				 System.out.println(rs.getString("diet_name")+" "+rs.getInt("weekend"));
				 week_arr[rs.getInt("weekend")].add(new PersonalDiet(rs.getString("diet_name"),rs.getInt("weekend"),rs.getInt("daytime")));
			 }
			 return week_arr;
		} catch (Exception e) {
			System.out.println("PersonalDietDAO 예외 발생");
			return null;
		} finally {
			MySqlDBManager.disconnect(conn,pstmt, rs);
		}
		
	}
}
