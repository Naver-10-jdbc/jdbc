package view.daily_ex;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Daily_Exercise extends JFrame {
	//·¹ÀÌ¾Æ¿ô1
	JPanel panel1;
	JLabel tv_day,tv_exer1,tv_exer2,tv_exer3,tv_exer4;
	JLabel tv_exer_time;
	//·¹ÀÌ¾Æ¿ô2
	JPanel panel2;
	JLabel tv_panel2_h1;
	ImageIcon img1,img2,img3,img4;
	JLabel tv_h1_exer1,tv_h1_exer2,tv_h1_exer3,tv_h1_exer4;
	JLabel tv_h2_exer1,tv_h2_exer2,tv_h2_exer3,tv_h2_exer4;
	JButton start_btn;
	
	public Daily_Exercise() {
		Init_Layout_Whole();
		Init_Layout1();
		Init_Layout2();
		Event_Listener();
		Init_Jframe();
		setVisible(true);
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
	private void Init_Layout1() {
		/* ¿äÀÏ + ¿îµ¿ Á¾·ù*/
		int left_padding=50;
		tv_day=new JLabel("¿ù¿äÀÏ");
		tv_day.setFont(new Font("¸¼Àº °íµñ",Font.BOLD,18));
		tv_day.setBounds(50,50,100,30); 
		panel1.add(tv_day);	
		int top_padding=80;
		int text_size=15;		
		int width=50;
		int height=20;
		tv_exer1=new JLabel("Á¾·ù1,");
		tv_exer1.setFont(new Font("¸¼Àº °íµñ",Font.BOLD,text_size));
		tv_exer1.setBounds(left_padding,top_padding,width,height); 
		panel1.add(tv_exer1);
		tv_exer2=new JLabel("Á¾·ù2,");
		tv_exer2.setFont(new Font("¸¼Àº °íµñ",Font.BOLD,text_size));
		tv_exer2.setBounds(left_padding+50,top_padding,width,height); 
		panel1.add(tv_exer2);
		tv_exer3=new JLabel("Á¾·ù3,");
		tv_exer3.setFont(new Font("¸¼Àº °íµñ",Font.BOLD,text_size));
		tv_exer3.setBounds(left_padding+100,top_padding,width,height); 
		panel1.add(tv_exer3);
		tv_exer4=new JLabel("Á¾·ù4");
		tv_exer4.setFont(new Font("¸¼Àº °íµñ",Font.BOLD,text_size));
		tv_exer4.setBounds(left_padding+150,top_padding,width,height); 
		panel1.add(tv_exer4);
		/*ÃÑ ¿îµ¿ ½Ã°£*/
		tv_exer_time=new JLabel("ÃÑ ¿îµ¿½Ã°£ 15:00");
		tv_exer_time.setFont(new Font("¸¼Àº °íµñ",Font.BOLD,text_size));
		tv_exer_time.setBounds(left_padding,top_padding+30,130,height); 
		panel1.add(tv_exer_time);
	}
	private void Init_Layout2() {
		int left_padding_img=65;
		int left_padding_text=140;
		int img_size=50;
		int img_size_label=120;
		int img_bottom_padding=85;
		int tv_width=200;
		int tv_h1_height=30;
		int tv_h1_font_size=15;
		int tv_h2_height=20;
		int tv_h2_font_size=13;
		
		tv_panel2_h1=new JLabel("¿À´ÃÀÇ ¿îµ¿!");
		tv_panel2_h1.setFont(new Font("¸¼Àº °íµñ",Font.BOLD,18));
		tv_panel2_h1.setBounds(left_padding_img,0,150,30); 
		panel2.add(tv_panel2_h1);
		
		JLabel lb1=new JLabel("");
		lb1.setBounds(left_padding_img,0,img_size_label,img_size_label);		
		ImageIcon icon = new ImageIcon(Daily_Exercise.class.getResource("02.male_normal.png")); //ÀÌ¹ÌÁö »ðÀÔ
		Image image1 = icon.getImage().getScaledInstance(img_size,img_size, Image.SCALE_SMOOTH); // ÀÌ¹ÌÁö Å©±â Á¶Àý
		lb1.setIcon(new ImageIcon(image1)); 
		panel2.add(lb1);
		tv_h1_exer1=new JLabel("ÀÚ¼¼ÀÌ¸§[10ºÐ]");
		tv_h1_exer1.setBounds(left_padding_text,35,tv_width,tv_h1_height);
		tv_h1_exer1.setFont(new Font("¸¼Àº °íµñ",Font.BOLD,tv_h1_font_size));
		panel2.add(tv_h1_exer1);
		tv_h2_exer1=new JLabel("¿îµ¿ ¹æ¹ý °£·« ¼³¸í");
		tv_h2_exer1.setBounds(left_padding_text,60,tv_width,tv_h2_height);
		tv_h2_exer1.setFont(new Font("¸¼Àº °íµñ",Font.BOLD,tv_h2_font_size));
		panel2.add(tv_h2_exer1);
		
		JLabel lb2=new JLabel("");
		lb2.setBounds(left_padding_img,img_bottom_padding,img_size_label,img_size_label);		
		ImageIcon icon2 = new ImageIcon(Daily_Exercise.class.getResource("02.male_normal.png")); //ÀÌ¹ÌÁö »ðÀÔ
		Image image2 = icon.getImage().getScaledInstance(img_size,img_size, Image.SCALE_SMOOTH); // ÀÌ¹ÌÁö Å©±â Á¶Àý
		lb2.setIcon(new ImageIcon(image2)); 
		panel2.add(lb2);
		tv_h1_exer2=new JLabel("ÀÚ¼¼ÀÌ¸§[10ºÐ]");
		tv_h1_exer2.setBounds(left_padding_text,35+img_bottom_padding,tv_width,tv_h1_height);
		tv_h1_exer2.setFont(new Font("¸¼Àº °íµñ",Font.BOLD,tv_h1_font_size));
		panel2.add(tv_h1_exer2);
		tv_h2_exer2=new JLabel("¿îµ¿ ¹æ¹ý °£·« ¼³¸í");
		tv_h2_exer2.setBounds(left_padding_text,60+img_bottom_padding,tv_width,tv_h2_height);
		tv_h2_exer2.setFont(new Font("¸¼Àº °íµñ",Font.BOLD,tv_h2_font_size));
		panel2.add(tv_h2_exer2);
		
		JLabel lb3=new JLabel("");
		lb3.setBounds(left_padding_img,img_bottom_padding*2,img_size_label,img_size_label);		
		ImageIcon icon3 = new ImageIcon(Daily_Exercise.class.getResource("02.male_normal.png")); //ÀÌ¹ÌÁö »ðÀÔ
		Image image3 = icon.getImage().getScaledInstance(img_size,img_size, Image.SCALE_SMOOTH); // ÀÌ¹ÌÁö Å©±â Á¶Àý
		lb3.setIcon(new ImageIcon(image3)); 
		panel2.add(lb3);
		tv_h1_exer3=new JLabel("ÀÚ¼¼ÀÌ¸§[10ºÐ]");
		tv_h1_exer3.setBounds(left_padding_text,35+img_bottom_padding*2,tv_width,tv_h1_height);
		tv_h1_exer3.setFont(new Font("¸¼Àº °íµñ",Font.BOLD,tv_h1_font_size));
		panel2.add(tv_h1_exer3);
		tv_h2_exer3=new JLabel("¿îµ¿ ¹æ¹ý °£·« ¼³¸í");
		tv_h2_exer3.setBounds(left_padding_text,60+img_bottom_padding*2,tv_width,tv_h2_height);
		tv_h2_exer3.setFont(new Font("¸¼Àº °íµñ",Font.BOLD,tv_h2_font_size));
		panel2.add(tv_h2_exer3);	
		
		JLabel lb4=new JLabel("");
		lb4.setBounds(left_padding_img,img_bottom_padding*3,img_size_label,img_size_label);		
		ImageIcon icon4 = new ImageIcon(Daily_Exercise.class.getResource("02.male_normal.png")); //ÀÌ¹ÌÁö »ðÀÔ
		Image image4 = icon.getImage().getScaledInstance(img_size,img_size, Image.SCALE_SMOOTH); // ÀÌ¹ÌÁö Å©±â Á¶Àý
		lb4.setIcon(new ImageIcon(image4)); 
		panel2.add(lb4);
		tv_h1_exer4=new JLabel("ÀÚ¼¼ÀÌ¸§[10ºÐ]");
		tv_h1_exer4.setBounds(left_padding_text,35+img_bottom_padding*3,tv_width,tv_h1_height);
		tv_h1_exer4.setFont(new Font("¸¼Àº °íµñ",Font.BOLD,tv_h1_font_size));
		panel2.add(tv_h1_exer4);
		tv_h2_exer4=new JLabel("¿îµ¿ ¹æ¹ý °£·« ¼³¸í");
		tv_h2_exer4.setBounds(left_padding_text,60+img_bottom_padding*3,tv_width,tv_h2_height);
		tv_h2_exer4.setFont(new Font("¸¼Àº °íµñ",Font.BOLD,tv_h2_font_size));
		panel2.add(tv_h2_exer4);
				
		start_btn=new JButton("¿îµ¿½ÃÀÛ");
		start_btn.setBounds(120,350,100,40);
		panel2.add(start_btn);
	}
	private void Event_Listener() {
		start_btn.addActionListener((i)->{
			new Dialog_Timer();
		});
	}
}