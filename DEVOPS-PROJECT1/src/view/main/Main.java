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
   //¿îµ¿ÇÏÀÚ ·¹ÀÌ¾Æ¿ô
   JPanel panel1;
   JLabel l11;
   JButton btn11,btn12;
   //¸ñÇ¥ ´Þ¼ºµµ ·¹ÀÌ¾Æ¿ô
   JPanel panel2;
   JLabel l21;
   //¿À´ÃÀÇ ¹Ì¼Ç ·¹ÀÌ¾Æ¿ô
   JPanel panel3;
   JLabel l31,l32,l33,l34;
   JButton btn31,btn32,btn33,btn34,btn35;
   //¿À´ÃÀÇ ½Ä´Ü ÃßÃµ
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
	   //ÀÎÁõ °Ô½ÃÆÇ
	   btn31.addActionListener((e)->{
		   System.out.println("ÀÎÁõ°Ô½ÃÆÇ");
	       new Board();  
	   });
	   //Ã¼Áß
	   btn32.addActionListener(l->{
		  
	   });
	   //½Ä´Ü
	   btn33.addActionListener(l->{
		   new Diet();
	   });
	   //¿îµ¿
	   btn34.addActionListener(l->{
		   new Week_Exercise();
	   });
	   //¼ö¸é
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
      l11=new JLabel("¿îµ¿ÇÏÀÚ");
      l11.setBounds(300,1,100,50);
      l11.setFont(new Font("¸¼Àº °íµñ",Font.BOLD,h1_font_size));
      
      btn11=new JButton("Mypage");   
      btn11.setFont(new Font("¸¼Àº °íµñ",Font.BOLD,15));
      btn11.setBounds(right_padding,1,100,50);      
      btn11.setOpaque(false);
      btn11.setBackground(Color.white);
      btn11.setBorderPainted(false);      
      add(btn11);
      add(l11);
   }
   private void Init_Panel2() { //panel2.setBounds(0, 100, 704, 200);
      l21=new JLabel("¸ñÇ¥ ´Þ¼ºµµ");
      l21.setBounds(left_padding,100,h2_width_size,h2_height_size); //x,y,width,height
      l21.setFont(new Font("¸¼Àº °íµñ",Font.BOLD,h2_font_size));
      
      /*double[] xData=new double[] {0.0,1.0,2.0};
      double[] yData=new double[] {0.0,1.0,2.0};
      XYChart chart=QuickChart.getChart("sample","x","y","y(x)",xData,yData);
      */
      add(l21);
      
      
   }
   private void Init_Panel3() {
      l31=new JLabel("¿À´ÃÀÇ ¹Ì¼Ç");
      l31.setFont(new Font("¸¼Àº °íµñ",Font.BOLD,h2_font_size));
      l31.setBounds(left_padding,300,h2_width_size,h2_height_size); //x,y,width,height
      btn31=new JButton("ÀÎÁõ °Ô½ÃÆÇ");
      btn31.setFont(new Font("¸¼Àº °íµñ",Font.BOLD,18));
      btn31.setBounds(right_padding,320,150,40);

      add(l31);
      add(btn31);
        
        //ÁßÃ¸ ·¹ÀÌ¾Æ¿ô
        l32=new JLabel("3/27");
        l33=new JLabel("¼ö¿äÀÏ");
        l34=new JLabel("¾ó¸¶ ¾È³²¾Ò¾î¿ä! È­ÀÌÆÃ!");
        l32.setFont(new Font("¸¼Àº °íµñ",Font.BOLD,15));
        l33.setFont(new Font("¸¼Àº °íµñ",Font.BOLD,15));
        l34.setFont(new Font("¸¼Àº °íµñ",Font.BOLD,15));
        l32.setBounds(46,20, 100, 50);
        l33.setBounds(40,40, 100, 50);
        l34.setBounds(200,80, 200, 50);
        

        btn32=new JButton("Ã¼Áß");
        btn33=new JButton("½Ä´Ü");
        btn34=new JButton("¿îµ¿");
        btn35=new JButton("¼ö¸é");
 
        btn32.setBounds(150,20, 70, 65);
        btn32.setFont(new Font("¸¼Àº °íµñ",Font.BOLD,13));
        btn33.setBounds(230,20, 70, 65);
        btn33.setOpaque(true);
        btn33.setFont(new Font("¸¼Àº °íµñ",Font.BOLD,13));
        
        btn34.setBounds(310,20, 70, 65);
        btn34.setFont(new Font("¸¼Àº °íµñ",Font.BOLD,13));
       
        btn35.setBounds(390,20, 70, 65);
        btn35.setFont(new Font("¸¼Àº °íµñ",Font.BOLD,13));
                
        panel3.add(l32);
        panel3.add(l33);
        panel3.add(l34);
        panel3.add(btn32);
        panel3.add(btn33);
        panel3.add(btn34);
        panel3.add(btn35);
   }
   private void Init_Panel4() {
      l41=new JLabel("¿À´ÃÀÇ ½Ä´Ü ÃßÃµ");
      l41.setBounds(left_padding,500,h2_width_size,h2_height_size);
      l41.setFont(new Font("¸¼Àº °íµñ",Font.BOLD,h2_font_size));
      
        String header[] = {"3/27 ¼ö","¾ÆÄ§","Á¡½É","Àú³á"};
        String contents[][] = {
            {"½Ä´Ü","À½½Ä1,À½½Ä2,À½½Ä3","À½½Ä1,À½½Ä2,À½½Ä3","À½½Ä1,À½½Ä2,À½½Ä3"},
            {"ÃÑ Ä®·Î¸®","100kcal","99kcal","100kcal"},
        };
        DefaultTableModel model = new DefaultTableModel(contents, header);
        JTable table = new JTable(model);
        table.setRowHeight(45);
        JScrollPane scrollpane = new JScrollPane(table);
        panel4.add(scrollpane);
        add(l41);
   }
}