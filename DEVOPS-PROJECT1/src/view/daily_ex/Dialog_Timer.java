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

import model.Exercise;

public class Dialog_Timer extends JFrame {
	   /*�ڹ� ���� ������Ʈ*/
	   JLabel lb1;
	   ImageIcon icon;
	   Image img;
	   JLabel tv_name,tv_how_set,tv_timer;
	   JButton btn;
	   /*���� ��ü*/
	   int minutes_exer[]= {15,15,10}; //�ӽ÷� ����
	   Exercise arr[];
	   int how_set=0;
	   int break_time_millis=2000;//5*60000;
	   int millisecond=3000;//5*60000;
	   boolean flag=false;	//���� ���(true)���� �޽���(false)����
	   boolean flag_for_thread=false;
	   
	   /*Ÿ�̸� Ŭ����*/
	   Timer timer;
	   /*�ش� Ŭ������ ȣ���� �θ� Ŭ����*/
	   Daily_Exercise parent_frame;
	   public Dialog_Timer(int set,boolean flag,Exercise arr[],Daily_Exercise parent_frame) {
		   /*for(int i=0; i<4; i++) {
		    	  for(Byte b:arr[i].getImg()) {
		    		  System.out.println(b);
		    	  }
		      }*/
		  this.parent_frame=parent_frame;
		  this.arr=arr;	 
		  how_set=set;	
		  Init_Jframe();
	      Init_View();
	      Init_Listener();
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
		   	  //tv_name.setText(names_exer[how_set-1]);
		   tv_name.setText(arr[how_set-1].getName());	  
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
							new Dialog_Timer(how_set,false,arr,parent_frame);
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
						new Dialog_Timer(how_set+1,true,arr,parent_frame);
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
		   /*
		    byte[] imgDate2 = arr[1].getImg();
		ImageIcon icon2 = new ImageIcon(imgDate2); //�̹��� ����
		Image image2 = icon2.getImage().getScaledInstance(img_size,img_size, Image.SCALE_SMOOTH); // �̹��� ũ�� ����
		lb2.setIcon(new ImageIcon(image2)); 
		panel2.add(lb2);
		    */
	      //�̹���
	      int image_size=120;
	      lb1=new JLabel("");
	      lb1.setBounds(80,40,image_size,image_size);      
	      byte[] imgDate2 = arr[how_set-1].getImg();
	      ImageIcon icon2 = new ImageIcon(imgDate2);
	      Image image2 = icon2.getImage().getScaledInstance(image_size,image_size, Image.SCALE_SMOOTH);
	      lb1.setIcon(new ImageIcon(image2));
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