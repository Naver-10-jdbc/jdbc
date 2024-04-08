package view.daily_ex;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Dialog_Finish extends JFrame {
   JLabel tv1,tv2;
   JButton btn;
   public Dialog_Finish() {
      Init_Jframe();
      Init_View();
      Init_Listener();
      setVisible(true);
   }
   
   private void Init_Listener() {
      btn.addActionListener(e -> {
         dispose();
      });
   }
   
   private void Init_Jframe() {
      setBounds(100, 100, 300,400);
      setLayout(null);
      setTitle("Finish!");
   }
   
   private void Init_View() {
      //�ؽ�Ʈ
      int text_size=23;
      tv1=new JLabel("���� ���");
      tv1.setFont(new Font("���� ���",Font.BOLD,text_size));
      tv1.setBounds(80,100,200,25); 
      add(tv1);
      tv2=new JLabel("�Ϸ��ϼ̽��ϴ�!");
      tv2.setFont(new Font("���� ���",Font.BOLD,text_size));
      tv2.setBounds(59,130,200,25); 
      add(tv2);
      //�����ư
      btn=new JButton("����");
      btn.setBounds(90,190,95,40);
      add(btn);
   }
}