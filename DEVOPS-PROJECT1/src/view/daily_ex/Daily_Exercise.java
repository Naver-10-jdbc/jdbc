package view.daily_ex;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import db.ExerciseDAO;
import model.Exercise;
import view.logn.Login;

public class Daily_Exercise extends JFrame {
	//뒤로가기 버튼
	JButton btn_back;
	//레이아웃1
	JPanel panel1;
	JLabel tv_day,tv_exer1,tv_exer2,tv_exer3,tv_exer4;
	JLabel tv_exer_time;
	//레이아웃2
	JPanel panel2;
	JLabel tv_panel2_h1;
	ImageIcon img1,img2,img3,img4;
	JLabel tv_h1_exer1,tv_h1_exer2,tv_h1_exer3,tv_h1_exer4;
	JLabel tv_h2_exer1,tv_h2_exer2,tv_h2_exer3,tv_h2_exer4;
	JButton start_btn;
	String str_exercise[];
	// 자료구조
	Exercise[]arr;
	String exercise_name[]; 
	String weekday; //요일
	public Daily_Exercise(String str_exercise[],String weekday) {
		this.str_exercise=str_exercise;
		if(str_exercise==null) {
			System.out.println("null 발생, 화면끄기 from Daily_Exercise");
			return;
		}
		this.weekday=weekday;
		arr=new ExerciseDAO().select_today_exercise(str_exercise); 
		//exercise_name=new String[4];
		//for(int i=0; i<4; i++) exercise_name[i]=arr[i].getName();
		Init_back_btn();
		Init_Layout_Whole();
		Init_Layout1();
		Init_Layout2();
		Event_Listener();
		Init_Jframe();
		setVisible(true);
	}
	private void Event_Listener() {
		start_btn.addActionListener((i)->{
			new Dialog_Timer(1,true,arr,this);
			
		});
	}
	private void Init_Jframe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 630);
		setTitle("Do It Exercise");
		setResizable(false);
		setLayout(null);
		
		add(panel1); 
		add(panel2);
	}
	   private void Init_back_btn() {
		    btn_back = new JButton("←");		//가로, 세로 값 지정
			btn_back.setBackground(new Color(134,229,127));
			btn_back.setBounds(5, 5, 46, 46);
			btn_back.setPreferredSize(new Dimension(30, 30));
			btn_back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose(); //뒤로가기 선택 시 창꺼짐
				}
			});
			add(btn_back);
	   }
	private void Init_Layout_Whole() {
		panel1=new JPanel();
		panel1.setBackground(new Color(134,229,127));
		panel1.setBounds(0,0,700,180);
		panel1.setLayout(null);
		panel2=new JPanel();
		panel2.setBackground(Color.white);
		panel2.setBounds(0,180,700,450);	
		panel2.setLayout(null);
	}
	//이렇게 하지말고 한줄로 나열하기.
	private void Init_Layout1() {
		/* 요일 + 운동 종류*/
		int left_padding=50;
		tv_day=new JLabel(weekday);
		tv_day.setFont(new Font("맑은 고딕",Font.BOLD,18));
		tv_day.setBounds(50,50,100,30); 
		panel1.add(tv_day);	
		int top_padding=80;
		int text_size=15;		
		int width=250;
		int height=20;
		String str=arr[0].getName()+", "+arr[1].getName()+", "+arr[2].getName()+", "+arr[3].getName();
		tv_exer1=new JLabel(str);
		tv_exer1.setFont(new Font("맑은 고딕",Font.BOLD,text_size));
		tv_exer1.setBounds(left_padding,top_padding,width,height); 
		panel1.add(tv_exer1);
		/*총 운동 시간*/
		tv_exer_time=new JLabel("총 운동시간 40:00");
		tv_exer_time.setFont(new Font("맑은 고딕",Font.BOLD,text_size));
		tv_exer_time.setBounds(left_padding,top_padding+30,130,height); 
		panel1.add(tv_exer_time);
	}
	private void Init_Layout2() {
		int left_padding_img=65;
		int left_padding_text=140;
		int img_size=50;
		int img_size_label=120;
		int img_bottom_padding=85;
		int tv_width=150;
		int tv_h1_height=30;
		int tv_h1_font_size=15;
		int tv_h2_height=20;
		int tv_h2_font_size=13;
		
		tv_panel2_h1=new JLabel("오늘의 운동!");
		tv_panel2_h1.setFont(new Font("맑은 고딕",Font.BOLD,18));
		tv_panel2_h1.setBounds(left_padding_img,5,150,30); 
		panel2.add(tv_panel2_h1);
		
		JLabel lb1=new JLabel("");
		lb1.setBounds(left_padding_img,0,img_size_label,img_size_label);
		byte[] imgDate1 = arr[0].getImg();
		ImageIcon icon = new ImageIcon(imgDate1); //이미지 삽입
		Image image1 = icon.getImage().getScaledInstance(img_size,img_size, Image.SCALE_SMOOTH); // 이미지 크기 조절
		lb1.setIcon(new ImageIcon(image1)); 
		panel2.add(lb1);
		tv_h1_exer1=new JLabel(arr[0].getName());
		tv_h1_exer1.setBounds(left_padding_text,35,tv_width,tv_h1_height);
		tv_h1_exer1.setFont(new Font("맑은 고딕",Font.BOLD,tv_h1_font_size));
		panel2.add(tv_h1_exer1);
		tv_h2_exer1=new JLabel(arr[0].getDetail());
		tv_h2_exer1.setBounds(left_padding_text,60,tv_width,tv_h2_height);
		tv_h2_exer1.setFont(new Font("맑은 고딕",Font.BOLD,tv_h2_font_size));
		panel2.add(tv_h2_exer1);
		
		JLabel lb2=new JLabel("");
		lb2.setBounds(left_padding_img,img_bottom_padding,img_size_label,img_size_label);
		byte[] imgDate2 = arr[1].getImg();
		ImageIcon icon2 = new ImageIcon(imgDate2); //이미지 삽입
		Image image2 = icon2.getImage().getScaledInstance(img_size,img_size, Image.SCALE_SMOOTH); // 이미지 크기 조절
		lb2.setIcon(new ImageIcon(image2)); 
		panel2.add(lb2);
		tv_h1_exer2=new JLabel(arr[1].getName());
		tv_h1_exer2.setBounds(left_padding_text,35+img_bottom_padding,tv_width,tv_h1_height);
		tv_h1_exer2.setFont(new Font("맑은 고딕",Font.BOLD,tv_h1_font_size));
		panel2.add(tv_h1_exer2);
		tv_h2_exer2=new JLabel(arr[1].getDetail());
		tv_h2_exer2.setBounds(left_padding_text,60+img_bottom_padding,tv_width,tv_h2_height);
		tv_h2_exer2.setFont(new Font("맑은 고딕",Font.BOLD,tv_h2_font_size));
		panel2.add(tv_h2_exer2);
		
		JLabel lb3=new JLabel("");
		lb3.setBounds(left_padding_img,img_bottom_padding*2,img_size_label,img_size_label);
		byte[] imgDate3 = arr[2].getImg();
		ImageIcon icon3 = new ImageIcon(imgDate3); //이미지 삽입
		Image image3 = icon3.getImage().getScaledInstance(img_size,img_size, Image.SCALE_SMOOTH); // 이미지 크기 조절
		lb3.setIcon(new ImageIcon(image3)); 
		panel2.add(lb3);
		tv_h1_exer3=new JLabel(arr[2].getName());
		tv_h1_exer3.setBounds(left_padding_text,35+img_bottom_padding*2,tv_width,tv_h1_height);
		tv_h1_exer3.setFont(new Font("맑은 고딕",Font.BOLD,tv_h1_font_size));
		panel2.add(tv_h1_exer3);
		tv_h2_exer3=new JLabel(arr[2].getDetail());
		tv_h2_exer3.setBounds(left_padding_text,60+img_bottom_padding*2,tv_width,tv_h2_height);
		tv_h2_exer3.setFont(new Font("맑은 고딕",Font.BOLD,tv_h2_font_size));
		panel2.add(tv_h2_exer3);	
		
		JLabel lb4=new JLabel("");
		lb4.setBounds(left_padding_img,img_bottom_padding*3,img_size_label,img_size_label);	
		byte[] imgDate4 = arr[3].getImg();
		ImageIcon icon4 = new ImageIcon(imgDate4); //이미지 삽입
		Image image4 = icon4.getImage().getScaledInstance(img_size,img_size, Image.SCALE_SMOOTH); // 이미지 크기 조절
		lb4.setIcon(new ImageIcon(image4)); 
		panel2.add(lb4);
		tv_h1_exer4=new JLabel(arr[3].getName());
		tv_h1_exer4.setBounds(left_padding_text,35+img_bottom_padding*3,tv_width,tv_h1_height);
		tv_h1_exer4.setFont(new Font("맑은 고딕",Font.BOLD,tv_h1_font_size));
		panel2.add(tv_h1_exer4);
		tv_h2_exer4=new JLabel(arr[3].getDetail());
		tv_h2_exer4.setBounds(left_padding_text,60+img_bottom_padding*3,tv_width,tv_h2_height);
		tv_h2_exer4.setFont(new Font("맑은 고딕",Font.BOLD,tv_h2_font_size));
		panel2.add(tv_h2_exer4);
				
		start_btn=new JButton("운동시작");
		start_btn.setBounds(120,350,100,40);
		panel2.add(start_btn);
	}
}