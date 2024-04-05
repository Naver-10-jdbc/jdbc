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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txt_id;
	private JPasswordField txt_pw;


	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 568);
		setLocationRelativeTo(null);
		setResizable(false); //화면 크기 조정 못하게
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel txt_login = new JLabel("로그인");
		txt_login.setFont(new Font("굴림", Font.PLAIN, 26));
		txt_login.setBounds(303, 87, 79, 63);
		contentPane.add(txt_login);
		
		JLabel label_id = new JLabel("아이디");
		label_id.setLabelFor(label_id);
		label_id.setFont(new Font("굴림", Font.PLAIN, 18));
		label_id.setBounds(75, 196, 412, 42);
		contentPane.add(label_id);
		
		txt_id = new JTextField();
		txt_id.setBounds(173, 198, 314, 42);
		contentPane.add(txt_id);
		txt_id.setColumns(10);
		
		JLabel label_pw = new JLabel("비밀번호");
		label_pw.setFont(new Font("굴림", Font.PLAIN, 18));
		label_pw.setBounds(75, 262, 412, 42);
		contentPane.add(label_pw);
		//
		txt_pw = new JPasswordField();
		txt_pw.setBounds(173, 264, 314, 42);
		contentPane.add(txt_pw);
		
		JButton btn_sign = new JButton("회원가입");
		btn_sign.setFont(new Font("굴림", Font.PLAIN, 25));
		btn_sign.setBounds(255, 393, 170, 42);
		contentPane.add(btn_sign);
		btn_sign.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Sign sign = new Sign();
				sign.setVisible(true);
			}
		});
		
		
		JButton btn_cancel = new JButton("\u2190");
		btn_cancel.setBounds(10, 10, 50, 50);
		contentPane.add(btn_cancel);
		
		JButton btn_login = new JButton("\uB85C\uADF8\uC778");
		btn_login.setFont(new Font("굴림", Font.BOLD, 23));
		btn_login.setBounds(522, 196, 121, 108);
		contentPane.add(btn_login);
		
		setVisible(true);
	}
}