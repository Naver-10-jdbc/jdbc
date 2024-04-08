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
      //�̹���
      int image_size=120;
      lb1=new JLabel("");
      lb1.setBounds(80,40,image_size,image_size);      
      icon = new ImageIcon(Daily_Exercise.class.getResource("02.male_normal.png")); //�̹��� ����
      img = icon.getImage().getScaledInstance(image_size,image_size, Image.SCALE_SMOOTH); // �̹��� ũ�� ����
      lb1.setIcon(new ImageIcon(img)); 
      add(lb1);
      //��̸�
      tv_name=new JLabel("��̸�");
      tv_name.setFont(new Font("���� ���",Font.BOLD,20));
      tv_name.setBounds(100,170,80,25); 
      add(tv_name);
      //��Ʈ
      tv_how_set=new JLabel("1set");
      tv_how_set.setFont(new Font("���� ���",Font.BOLD,18));
      tv_how_set.setBounds(120,205,80,20); 
      add(tv_how_set);
      //Ÿ�̸�
      tv_timer=new JLabel("00:00");
      tv_timer.setFont(new Font("���� ���",Font.BOLD,30));
      tv_timer.setBounds(102,230,90,40); 
      add(tv_timer);
      //��ư
      btn=new JButton("� �ߴ�");
      btn.setBounds(90,290,100,40);
      add(btn);
   }
}