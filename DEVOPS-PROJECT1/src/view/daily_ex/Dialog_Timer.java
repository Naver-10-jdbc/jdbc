package view.daily_ex;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Dialog_Timer extends JFrame {
   JLabel lb1;
   ImageIcon icon;
   Image img;
   JLabel tv_name,tv_how_set,tv_timer;
   JButton btn;
   public Dialog_Timer() {
      Init_Jframe();
      Init_View();
      Init_Listener();
      setVisible(true);   
   }
   
   private void Init_Listener() {
      btn.addActionListener((e)->{
         dispose();
      });
   }
   
   private void Init_Jframe() {
      setBounds(100, 100, 300,400);
      setLayout(null);
      setTitle("Exercising...");
   }
   private void Init_View() {
      //이미지
      int image_size=120;
      lb1=new JLabel("");
      lb1.setBounds(80,40,image_size,image_size);      
      icon = new ImageIcon(Daily_Exercise.class.getResource("02.male_normal.png")); //이미지 삽입
      img = icon.getImage().getScaledInstance(image_size,image_size, Image.SCALE_SMOOTH); // 이미지 크기 조절
      lb1.setIcon(new ImageIcon(img)); 
      add(lb1);
      //운동이름
      tv_name=new JLabel("운동이름");
      tv_name.setFont(new Font("맑은 고딕",Font.BOLD,20));
      tv_name.setBounds(100,170,80,25); 
      add(tv_name);
      //세트
      tv_how_set=new JLabel("1set");
      tv_how_set.setFont(new Font("맑은 고딕",Font.BOLD,18));
      tv_how_set.setBounds(120,205,80,20); 
      add(tv_how_set);
      //타이머
      tv_timer=new JLabel("00:00");
      tv_timer.setFont(new Font("맑은 고딕",Font.BOLD,30));
      tv_timer.setBounds(102,230,90,40); 
      add(tv_timer);
      //버튼
      btn=new JButton("운동 중단");
      btn.setBounds(90,290,100,40);
      add(btn);
   }
}