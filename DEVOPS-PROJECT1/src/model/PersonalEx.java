package model;

public class PersonalEx {
	private String nameEx;
	private String userId;
	private int weekday;
	
	public PersonalEx(String nameEx,String userId,int weekday) {
		this.nameEx=nameEx;
		this.userId=userId;
		this.weekday=weekday;
	}
	
	public String getNameEx() {
		return nameEx;
	}
	public void setNameEx(String nameEx) {
		this.nameEx = nameEx;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getWeekday() {
		return weekday;
	}
	public void setWeekday(int weekday) {
		this.weekday = weekday;
	}
}