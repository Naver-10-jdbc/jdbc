package view.logn;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.CardLayout;

public class Sign extends JFrame {

	private JPanel contentPane;
	private JTextField txt_name;
	private JTextField txt_age;
	private JTextField txt_id;
	private JTextField txt_pw;
	private JTextField txt_check_pw;
	private JTextField txt_email;
	private final ButtonGroup genderGroup = new ButtonGroup();
	private JTextField txt_height;
	private JTextField txt_weight;


	public Sign() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 531, 728);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel txt_sign = new JLabel("È¸¿ø°¡ÀÔ");
		txt_sign.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		txt_sign.setBounds(235, 37, 79, 39);
		contentPane.add(txt_sign);
		
		JLabel label_name = new JLabel("ÀÌ¸§");
		label_name.setFont(new Font("±¼¸²", Font.PLAIN, 15));
		label_name.setBounds(98, 89, 364, 44);
		contentPane.add(label_name);
		
		txt_name = new JTextField();
		txt_name.setFont(new Font("±¼¸²", Font.PLAIN, 15));
		txt_name.setBounds(209, 96, 220, 35);
		contentPane.add(txt_name);
		txt_name.setColumns(10);
		
		JLabel labe_age = new JLabel("³ªÀÌ");
		labe_age.setFont(new Font("±¼¸²", Font.PLAIN, 15));
		labe_age.setBounds(98, 143, 364, 44);
		contentPane.add(labe_age);
		
		txt_age = new JTextField();
		txt_age.setFont(new Font("±¼¸²", Font.PLAIN, 15));
		txt_age.setColumns(10);
		txt_age.setBounds(209, 150, 220, 35);
		contentPane.add(txt_age);
		
		JLabel label_id = new JLabel("¾ÆÀÌµð");
		label_id.setFont(new Font("±¼¸²", Font.PLAIN, 15));
		label_id.setBounds(98, 197, 364, 44);
		contentPane.add(label_id);
		
		txt_id = new JTextField();
		txt_id.setFont(new Font("±¼¸²", Font.PLAIN, 15));
		txt_id.setColumns(10);
		txt_id.setBounds(209, 204, 220, 35);
		contentPane.add(txt_id);
		
		JLabel label_pw = new JLabel("ºñ¹Ð¹øÈ£");
		label_pw.setFont(new Font("±¼¸²", Font.PLAIN, 15));
		label_pw.setBounds(98, 251, 364, 44);
		contentPane.add(label_pw);
		
		txt_pw = new JTextField();
		txt_pw.setFont(new Font("±¼¸²", Font.PLAIN, 15));
		txt_pw.setColumns(10);
		txt_pw.setBounds(209, 258, 220, 35);
		contentPane.add(txt_pw);
		
		JLabel label_check_pw = new JLabel("ºñ¹Ð¹øÈ£ È®ÀÎ");
		label_check_pw.setFont(new Font("±¼¸²", Font.PLAIN, 15));
		label_check_pw.setBounds(98, 305, 364, 44);
		contentPane.add(label_check_pw);
		
		txt_check_pw = new JTextField();
		txt_check_pw.setFont(new Font("±¼¸²", Font.PLAIN, 15));
		txt_check_pw.setColumns(10);
		txt_check_pw.setBounds(209, 312, 220, 35);
		contentPane.add(txt_check_pw);
		
		JLabel label_email = new JLabel("ÀÌ¸ÞÀÏ");
		label_email.setFont(new Font("±¼¸²", Font.PLAIN, 15));
		label_email.setBounds(98, 359, 364, 44);
		contentPane.add(label_email);
		
		txt_email = new JTextField();
		txt_email.setFont(new Font("±¼¸²", Font.PLAIN, 15));
		txt_email.setColumns(10);
		txt_email.setBounds(209, 366, 220, 35);
		contentPane.add(txt_email);
		
		JLabel label_gender = new JLabel("¼ºº°");
		label_gender.setFont(new Font("±¼¸²", Font.PLAIN, 15));
		label_gender.setBounds(98, 517, 331, 44);
		contentPane.add(label_gender);
		
		JRadioButton radio_M = new JRadioButton("³²ÀÚ");
		radio_M.setFont(new Font("±¼¸²", Font.PLAIN, 15));
		radio_M.setBounds(209, 528, 60, 23);
		contentPane.add(radio_M);
		
		JRadioButton radio_W = new JRadioButton("¿©ÀÚ");
		radio_W.setFont(new Font("±¼¸²", Font.PLAIN, 15));
		radio_W.setBounds(319, 528, 60, 23);
		contentPane.add(radio_W);
		
		//¶óµð¿À ¹öÆ° °°Àº ±×·ì¿¡ Ãß°¡ÇØ¼­ ÇÑ¹ø¿¡ ÇÏ³ª¸¸ Å¬¸¯µÇ°Ô
		genderGroup.add(radio_M);
        genderGroup.add(radio_W);
        
        JLabel label_height = new JLabel("Å°");
        label_height.setFont(new Font("±¼¸²", Font.PLAIN, 15));
        label_height.setBounds(98, 413, 331, 44);
        contentPane.add(label_height);
        
        JLabel label_weight = new JLabel("Ã¼Áß");
        label_weight.setFont(new Font("±¼¸²", Font.PLAIN, 15));
        label_weight.setBounds(98, 467, 331, 44);
        contentPane.add(label_weight);
        
        txt_height = new JTextField();
        txt_height.setFont(new Font("±¼¸²", Font.PLAIN, 15));
        txt_height.setColumns(10);
        txt_height.setBounds(209, 418, 220, 35);
        contentPane.add(txt_height);
        
        txt_weight = new JTextField();
        txt_weight.setFont(new Font("±¼¸²", Font.PLAIN, 15));
        txt_weight.setColumns(10);
        txt_weight.setBounds(209, 472, 220, 35);
        contentPane.add(txt_weight);
        
        JButton btn_sign = new JButton("°¡ÀÔ");
        btn_sign.setBounds(98, 591, 148, 39);
        contentPane.add(btn_sign);
        
        JButton btn_cancel = new JButton("Ãë¼Ò");
        btn_cancel.setBounds(281, 591, 148, 39);
        contentPane.add(btn_cancel);
        
        setVisible(true);
	}
}
