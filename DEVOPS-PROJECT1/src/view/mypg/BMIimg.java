package view.mypg;

import java.io.PrintStream;

import javax.swing.JLabel;

import db.MyPageDAO;
import db.UsersData;
import view.logn.Login;

public class BMIimg {
	private String bmi_img;
	private MyPageDAO myPageDAO;
	private UsersData usersData;
	private MyPage myPage;
	private String gender;
	private double bmi;
	
	public String bmi_img(String gender, double bmi) {
		this.gender = gender;
		this.bmi = bmi;
		
		if(gender.equals("M")) {
			if(bmi<18.5) {
				bmi_img="01.male.png";
			}else if(bmi<25) {
				bmi_img="02.male.png";
			}else if(bmi<30) {
				bmi_img="03.male.png";
			}else if(bmi<35) {
				bmi_img="04.male.png";
			}else {
				bmi_img="05.male.png";
			}
		}else {
			if(bmi<18.5) {
				bmi_img="06.female.png";
			}else if(bmi<25) {
				bmi_img="07.female.png";
			}else if(bmi<30) {
				bmi_img="08.female.png";
			}else if(bmi<35) {
				bmi_img="09.female.png";
			}else {
				bmi_img="10.female.png";
			}
		}
		
		return bmi_img;
	}
}
