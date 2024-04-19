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
	 String user_id=Session.getInstance().getUserId();
	public ArrayList<PersonalDiet>[] select_PerDiet(){
		ArrayList<PersonalDiet>[]week_arr=new ArrayList[8];
		for(int i=0; i<8; i++) week_arr[i]=new ArrayList<PersonalDiet>();
		try {
			 conn=MySqlDBManager.getInstance();
			 System.out.println("conn");
			 String sql = "SELECT * FROM personal_diet WHERE user_id=?";
			 pstmt = conn.prepareStatement(sql);
			 System.out.println("pstmt");
			 pstmt.setString(1,user_id); //바꾸기
			 rs = pstmt.executeQuery();
			 System.out.println("rs");
			 while(rs.next()) {
				// System.out.println(rs.getString("diet_name")+" "+rs.getInt("weekend"));
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
	
	public String[] select_TodayPerDiet(int day){
		String today_diet_srr[]=new String[9]; 
		for(int i=0; i<today_diet_srr.length; i++) today_diet_srr[i]=" "; //가져올 데이터가 없을 경우 대비
		try {
			 conn=MySqlDBManager.getInstance();
			 String sql = "SELECT * FROM personal_diet WHERE user_id=? AND weekend=?";
			 pstmt = conn.prepareStatement(sql);
			 
			 if(day==7) {	//식단은 요일 1(일)~6(월)
				 day=1; 
			 }else day+=1;
			 pstmt.setString(1,user_id); //바꾸기
			 pstmt.setInt(2,day); //바꾸기
			 rs = pstmt.executeQuery();
			 int idx=0;
			 //System.out.println();
			 while(rs.next()) {
				 //System.out.println(rs.getString("diet_name")+"from PersonalDietDAO(today)");
				 today_diet_srr[idx++]=rs.getString("diet_name");
			 }
			 return today_diet_srr;
		} catch (Exception e) {
			System.out.println("PersonalDietDAO 예외 발생(today)");
			return null;
		} finally {
			MySqlDBManager.disconnect(conn,pstmt, rs);
		}
		
	}
	
	public String getDietComment(String dietName) {
	    try {
	        conn = MySqlDBManager.getInstance();
	        String sql = "SELECT diet_comment FROM diet WHERE diet_name = ?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, dietName);
	        rs = pstmt.executeQuery();
	        
	        if (rs.next()) {
	            return rs.getString("diet_comment");
	            //return이 실행되면서 diet_name에 대한 diet_comment값을 받아온다
	        } else {
	        	System.out.println("해당되는 diet_comment값이 없습니다");
	            return null; // 해당하는 diet_comment가 없을 경우 처리
	        }
	    } catch (Exception e) {
	        System.out.println("getDietComment 예외발생:" + e.getMessage());
	        return null;
	    } finally {
	        MySqlDBManager.disconnect(conn, pstmt, rs);
	    }
	}

}
