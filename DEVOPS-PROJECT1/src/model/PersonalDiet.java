package model;

public class PersonalDiet {
	private String diet_name;
	private int personal_diet_weekend;
	private int daytime;
	
	public PersonalDiet(String diet_name,int personal_diet_weekend, int daytime) {
		this.diet_name=diet_name;
		this.personal_diet_weekend=personal_diet_weekend;
		this.daytime=daytime;
	}
	
	public String getDiet_name() {
		return diet_name;
	}
	public void setDiet_name(String diet_name) {
		this.diet_name = diet_name;
	}
	public int getPersonal_diet_weekend() {
		return personal_diet_weekend;
	}
	public void setPersonal_diet_weekend(int personal_diet_weekend) {
		this.personal_diet_weekend = personal_diet_weekend;
	}
	public int getDaytime() {
		return daytime;
	}
	public void setDaytime(int daytime) {
		this.daytime = daytime;
	}
}

