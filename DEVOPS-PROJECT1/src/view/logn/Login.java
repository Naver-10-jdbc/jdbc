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
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.BorderLayout;

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
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btn_sign = new JButton("회원가입");
		btn_sign.setFont(new Font("굴림", Font.PLAIN, 25));
		btn_sign.setBounds(284, 337, 170, 42);
		contentPane.add(btn_sign);
		
		JButton btn_cancel = new JButton("X");
		btn_cancel.setBounds(629, 10, 49, 42);
		contentPane.add(btn_cancel);
		
		JPanel panel = new JPanel();
		panel.setBounds(99, 136, 579, 144);
		contentPane.add(panel);
		
		JLabel label_id = new JLabel("아이디");
		label_id.setLabelFor(label_id);
		label_id.setFont(new Font("굴림", Font.PLAIN, 18));
		
		txt_id = new JTextField();
		txt_id.setFont(new Font("굴림", Font.PLAIN, 18));
		txt_id.setColumns(10);
		
		JLabel label_pw = new JLabel("비밀번호");
		label_pw.setFont(new Font("굴림", Font.PLAIN, 18));
		
		txt_pw = new JPasswordField();
		txt_pw.setFont(new Font("굴림", Font.PLAIN, 18));
		
		JButton btn_login = new JButton("로그인");
		btn_login.setFont(new Font("굴림", Font.PLAIN, 25));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(48)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_pw, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_id))
					.addGap(62)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(txt_pw, 146, 146, 146)
						.addComponent(txt_id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
					.addComponent(btn_login, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_id)
						.addComponent(txt_id, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txt_pw, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_pw, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addGap(18))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(18)
					.addComponent(btn_login, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(18, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 10, 666, 511);
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel txt_login_1 = new JLabel("\uB85C\uADF8\uC778");
		txt_login_1.setFont(new Font("굴림", Font.PLAIN, 26));
		panel_1.add(txt_login_1, BorderLayout.NORTH);
	}
}