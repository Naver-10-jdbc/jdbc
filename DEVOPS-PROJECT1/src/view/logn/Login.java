package view.logn;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txt_id;
	private JPasswordField txt_pw;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 704, 568);
		setLocationRelativeTo(null);
		//setResizable(false); //화면 크기 조정 못하게
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel txt_login = new JLabel("로그인");
		txt_login.setFont(new Font("굴림", Font.PLAIN, 26));
		txt_login.setBounds(319, 42, 79, 63);
		contentPane.add(txt_login);
		
		JLabel label_id = new JLabel("아이디");
		label_id.setLabelFor(label_id);
		label_id.setFont(new Font("굴림", Font.PLAIN, 18));
		label_id.setBounds(103, 138, 412, 42);
		contentPane.add(label_id);
		
		txt_id = new JTextField();
		txt_id.setBounds(201, 140, 314, 42);
		contentPane.add(txt_id);
		txt_id.setColumns(10);
		
		JLabel label_pw = new JLabel("비밀번호");
		label_pw.setFont(new Font("굴림", Font.PLAIN, 18));
		label_pw.setBounds(103, 204, 443, 42);
		contentPane.add(label_pw);
		
		JButton btn_login = new JButton("로그인");
		btn_login.setFont(new Font("굴림", Font.PLAIN, 25));
		btn_login.setBounds(558, 138, 120, 108);
		contentPane.add(btn_login);
		
		txt_pw = new JPasswordField();
		txt_pw.setBounds(201, 206, 314, 42);
		contentPane.add(txt_pw);
		
		JButton btn_sign = new JButton("회원가입");
		btn_sign.setFont(new Font("굴림", Font.PLAIN, 25));
		btn_sign.setBounds(284, 337, 170, 42);
		contentPane.add(btn_sign);
		
		JButton btn_cancel = new JButton("X");
		btn_cancel.setBounds(629, 10, 49, 42);
		contentPane.add(btn_cancel);
	}
}