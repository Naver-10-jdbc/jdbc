package view.main;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import view.bulletin_b.Board;
import view.daily_ex.Daily_Exercise;
import view.diet.Diet;
import view.mypg.MyPage;
import view.week_ex.Week_Exercise;

public class Main extends JFrame{
   final int left_padding=20;
   final int right_padding=500;
   final int h1_font_size=25;
   final int h2_font_size=20;
   final int h2_height_size=40;
   final int h2_width_size=200;
   //����� ���̾ƿ�
   JPanel panel1;
   JLabel l11;
   JButton btn11,btn12;
   //��ǥ �޼��� ���̾ƿ�
   JPanel panel2;
   JLabel l21;
   //������ �̼� ���̾ƿ�
   JPanel panel3;
   JLabel l31,l32,l33,l34;
   JButton btn31,btn32,btn33,btn34,btn35;
   //������ �Ĵ� ��õ
   JPanel panel4;
   JLabel l41;


   public Main() {
      setSize(704,750);
      setLayout(null);
      setResizable(false);
      Init_Layout();
      Init_Panel1();
      Init_Panel2();
      Init_Panel3();
      Init_Panel4();
      Event_Listener();
      
      setVisible(true);
   }
   
   private void Event_Listener() {
	   //myPage
	   btn11.addActionListener((e)->{
		   System.out.println("Mypage"); 
		   new MyPage();	        
	   });
	   //���� �Խ���
	   btn31.addActionListener((e)->{
		   System.out.println("�����Խ���");
	       new Board();  
	   });
	   //ü��
	   btn32.addActionListener(l->{
		  
	   });
	   //�Ĵ�
	   btn33.addActionListener(l->{
		   new Diet();
	   });
	   //�
	   btn34.addActionListener(l->{
		   new Week_Exercise();
	   });
	   //����
	   btn35.addActionListener(l->{
		   new Daily_Exercise();
	   });
   }
   
   private void Init_Layout() {
	   	panel3=new JPanel();
	   	panel3.setBackground(Color.white);
	   	panel3.setBounds(left_padding+30, 380, 504, 120);
	   	panel3.setLayout(null);
	   	panel3.setOpaque(true);
	   	add(panel3);

        panel4=new JPanel();
        panel4.setBounds(left_padding, 550, 504, 120);
      add(panel4);
        /* panel1=new JPanel();
      panel1.setBounds(0, 0, 704, 100);
      panel1.setLayout(null);
      
      panel2=new JPanel();
      panel2.setBackground(Color.green);
      panel2.setBounds(0, 100, 704, 200);
      panel2.setLayout(null);
      
      panel4=new JPanel();
      panel4.setBackground(Color.red);
      panel4.setBounds(0, 500, 704, 200);
      panel4.setLayout(new BorderLayout());
      
      add(panel1);   add(panel2);   add(panel3);   add(panel4);*/   
   }
   
   private void Init_Panel1() {
      l11=new JLabel("�����");
      l11.setBounds(300,1,100,50);
      l11.setFont(new Font("���� ���",Font.BOLD,h1_font_size));
      
      btn11=new JButton("Mypage");   
      btn11.setFont(new Font("���� ���",Font.BOLD,15));
      btn11.setBounds(right_padding,1,100,50);      
      btn11.setOpaque(false);
      btn11.setBackground(Color.white);
      btn11.setBorderPainted(false);      
      add(btn11);
      add(l11);
   }
   private void Init_Panel2() { //panel2.setBounds(0, 100, 704, 200);
      l21=new JLabel("��ǥ �޼���");
      l21.setBounds(left_padding,100,h2_width_size,h2_height_size); //x,y,width,height
      l21.setFont(new Font("���� ���",Font.BOLD,h2_font_size));
      
      /*double[] xData=new double[] {0.0,1.0,2.0};
      double[] yData=new double[] {0.0,1.0,2.0};
      XYChart chart=QuickChart.getChart("sample","x","y","y(x)",xData,yData);
      */
      add(l21);
      
      
   }
   private void Init_Panel3() {
      l31=new JLabel("������ �̼�");
      l31.setFont(new Font("���� ���",Font.BOLD,h2_font_size));
      l31.setBounds(left_padding,300,h2_width_size,h2_height_size); //x,y,width,height
      btn31=new JButton("���� �Խ���");
      btn31.setFont(new Font("���� ���",Font.BOLD,18));
      btn31.setBounds(right_padding,320,150,40);

      add(l31);
      add(btn31);
        
        //��ø ���̾ƿ�
        l32=new JLabel("3/27");
        l33=new JLabel("������");
        l34=new JLabel("�� �ȳ��Ҿ��! ȭ����!");
        l32.setFont(new Font("���� ���",Font.BOLD,15));
        l33.setFont(new Font("���� ���",Font.BOLD,15));
        l34.setFont(new Font("���� ���",Font.BOLD,15));
        l32.setBounds(46,20, 100, 50);
        l33.setBounds(40,40, 100, 50);
        l34.setBounds(200,80, 200, 50);
        

        btn32=new JButton("ü��");
        btn33=new JButton("�Ĵ�");
        btn34=new JButton("�");
        btn35=new JButton("����");
 
        btn32.setBounds(150,20, 70, 65);
        btn32.setFont(new Font("���� ���",Font.BOLD,13));
        btn33.setBounds(230,20, 70, 65);
        btn33.setOpaque(true);
        btn33.setFont(new Font("���� ���",Font.BOLD,13));
        
        btn34.setBounds(310,20, 70, 65);
        btn34.setFont(new Font("���� ���",Font.BOLD,13));
       
        btn35.setBounds(390,20, 70, 65);
        btn35.setFont(new Font("���� ���",Font.BOLD,13));
                
        panel3.add(l32);
        panel3.add(l33);
        panel3.add(l34);
        panel3.add(btn32);
        panel3.add(btn33);
        panel3.add(btn34);
        panel3.add(btn35);
   }
   private void Init_Panel4() {
      l41=new JLabel("������ �Ĵ� ��õ");
      l41.setBounds(left_padding,500,h2_width_size,h2_height_size);
      l41.setFont(new Font("���� ���",Font.BOLD,h2_font_size));
      
        String header[] = {"3/27 ��","��ħ","����","����"};
        String contents[][] = {
            {"�Ĵ�","����1,����2,����3","����1,����2,����3","����1,����2,����3"},
            {"�� Į�θ�","100kcal","99kcal","100kcal"},
        };
        DefaultTableModel model = new DefaultTableModel(contents, header);
        JTable table = new JTable(model);
        table.setRowHeight(45);
        JScrollPane scrollpane = new JScrollPane(table);
        panel4.add(scrollpane);
        add(l41);
   }
}