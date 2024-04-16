package view.daily_ex;

import java.awt.Font;
import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Dialog_Timer extends JFrame {
	   /*�ڹ� ���� ������Ʈ*/
	   JLabel lb1;
	   ImageIcon icon;
	   Image img;
	   JLabel tv_name,tv_how_set,tv_timer;
	   JButton btn;
	   /*���� ��ü*/
	   int minutes_exer[]= {15,15,10}; //�ӽ÷� ����
	   String names_exer[];
	   int how_set=0;
	   int break_time_millis=2000;//5*60000;
	   int millisecond=3000;//5*60000;
	   boolean flag=false;	//���� ���(true)���� �޽���(false)����
	   boolean flag_for_thread=false;
	   
	   /*Ÿ�̸� Ŭ����*/
	   Timer timer;
	   /*�ش� Ŭ������ ȣ���� �θ� Ŭ����*/
	   Daily_Exercise parent_frame;
	   public Dialog_Timer(int set,boolean flag,String names_exer[],Daily_Exercise parent_frame) {
	      this.parent_frame=parent_frame;
		  Init_Jframe();
	      Init_View();
	      Init_Listener();
	      how_set=set;
	      this.names_exer=names_exer;
	      if(flag) {	//��� ����
	    	  System.out.println("�����");
	    	  Timer_For_Exer();
	      }else {		//������
	    	  System.out.println("������");
	    	  Timer_For_Break();
	      } 
	      setVisible(true);   
	   }
	   
	   public void Timer_For_Exer() {
		   	  tv_name.setText(names_exer[how_set-1]);
		   	  tv_how_set.setText(how_set+" Set");
		      timer=new Timer();
		      TimerTask exer_Task=new TimerTask() {
				@Override
				public void run() {
					millisecond-=1000;
					int minute=millisecond/60000;
					int second=(millisecond-minute*60000)/1000;
					String str_min, str_sec;
					if(minute>=10) str_min=minute+"";
					else	str_min="0"+minute;
					if(second>=10) str_sec=second+"";
					else str_sec="0"+second;
					tv_timer.setText(str_min+":"+str_sec);
					System.out.println(millisecond);
					if(millisecond<=0) {
						cancel();
						dispose();
						if(how_set<4) {
							new Dialog_Timer(how_set,false,names_exer,parent_frame);
						}
						else {
							new Dialog_Finish();
							parent_frame.dispose();
						}
					}
				}
		      };
		      timer.schedule(exer_Task,0,1000);
	   }
	   
	   public void Timer_For_Break() {
		   	  tv_name.setText("�޽� �ð�!");
		   	  tv_how_set.setText(how_set+" Set");
		      timer=new Timer();
		      TimerTask exer_Task=new TimerTask() {
				@Override
				public void run() {
					break_time_millis-=1000;
					int minute=break_time_millis/60000;
					int second=(break_time_millis-minute*60000)/1000;
					String str_min, str_sec;
					if(minute>=10) str_min=minute+"";
					else	str_min="0"+minute;
					if(second>=10) str_sec=second+"";
					else str_sec="0"+second;
					tv_timer.setText(str_min+":"+str_sec);
					System.out.println(break_time_millis);
					if(break_time_millis<=0) {
						cancel();
						dispose();
						new Dialog_Timer(how_set+1,true,names_exer,parent_frame);
					}
				}
		      };
		      timer.schedule(exer_Task,0,1000);
	   }
	   
	   private void Init_Listener() {
	      btn.addActionListener((e)->{
	         dispose();
	         timer.cancel();
	         JOptionPane.showMessageDialog(null, "��� �ߴ��ϼ̽��ϴ�.");
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
	      tv_name=new JLabel("");
	      tv_name.setFont(new Font("���� ���",Font.BOLD,20));
	      tv_name.setBounds(100,170,300,25); 
	      add(tv_name);
	      //��Ʈ
	      tv_how_set=new JLabel("");
	      tv_how_set.setFont(new Font("���� ���",Font.BOLD,18));
	      tv_how_set.setBounds(120,205,80,20); 
	      add(tv_how_set);
	      //Ÿ�̸�
	      tv_timer=new JLabel("00:00");
	      tv_timer.setFont(new Font("���� ���",Font.BOLD,30));
	      tv_timer.setBounds(102,230,300,40);
	      add(tv_timer);
	      //��ư
	      btn=new JButton("� �ߴ�");
	      btn.setBounds(90,290,100,40);
	      add(btn);
	   }
	}