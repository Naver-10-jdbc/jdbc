package view.logn;


import db.UserDao;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static view.logn.NumberValidator.isValidNumber;
import static view.logn.PasswordValidator.validatePassword;

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

	private UserDao users;




	public Sign() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 531, 728);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);


		JLabel txt_sign = new JLabel("회원가입");
		txt_sign.setFont(new Font("굴림", Font.PLAIN, 20));
		txt_sign.setBounds(235, 37, 79, 39);
		contentPane.add(txt_sign);

		JLabel label_name = new JLabel("이름");
		label_name.setFont(new Font("굴림", Font.PLAIN, 15));
		label_name.setBounds(98, 89, 364, 44);
		contentPane.add(label_name);

		txt_name = new JTextField();
		txt_name.setFont(new Font("굴림", Font.PLAIN, 15));
		txt_name.setBounds(209, 96, 220, 35);
		contentPane.add(txt_name);
		txt_name.setColumns(10);

		JLabel labe_age = new JLabel("나이");
		labe_age.setFont(new Font("굴림", Font.PLAIN, 15));
		labe_age.setBounds(98, 143, 364, 44);
		contentPane.add(labe_age);

		txt_age = new JTextField();
		txt_age.setFont(new Font("굴림", Font.PLAIN, 15));
		txt_age.setColumns(10);
		txt_age.setBounds(209, 150, 220, 35);
		contentPane.add(txt_age);

		JLabel label_id = new JLabel("아이디");
		label_id.setFont(new Font("굴림", Font.PLAIN, 15));
		label_id.setBounds(98, 197, 364, 44);
		contentPane.add(label_id);

		txt_id = new JTextField();
		txt_id.setFont(new Font("굴림", Font.PLAIN, 15));
		txt_id.setColumns(10);
		txt_id.setBounds(209, 204, 220, 35);
		contentPane.add(txt_id);

		JLabel label_pw = new JLabel("비밀번호");
		label_pw.setFont(new Font("굴림", Font.PLAIN, 15));
		label_pw.setBounds(98, 251, 364, 44);
		contentPane.add(label_pw);

		txt_pw = new JPasswordField();
		txt_pw.setFont(new Font("굴림", Font.PLAIN, 15));
		txt_pw.setBounds(209, 258, 220, 35);
		contentPane.add(txt_pw);

		JLabel label_check_pw = new JLabel("비밀번호 확인");
		label_check_pw.setFont(new Font("굴림", Font.PLAIN, 15));
		label_check_pw.setBounds(98, 305, 364, 44);
		contentPane.add(label_check_pw);

		txt_check_pw = new JPasswordField();
		txt_check_pw.setFont(new Font("굴림", Font.PLAIN, 15));
		txt_check_pw.setBounds(209, 312, 220, 35);
		contentPane.add(txt_check_pw);

		JLabel label_email = new JLabel("이메일");
		label_email.setFont(new Font("굴림", Font.PLAIN, 15));
		label_email.setBounds(98, 359, 364, 44);
		contentPane.add(label_email);

		txt_email = new JTextField();
		txt_email.setFont(new Font("굴림", Font.PLAIN, 15));
		txt_email.setColumns(10);
		txt_email.setBounds(209, 366, 220, 35);
		contentPane.add(txt_email);

		JLabel label_gender = new JLabel("성별");
		label_gender.setFont(new Font("굴림", Font.PLAIN, 15));
		label_gender.setBounds(98, 517, 331, 44);
		contentPane.add(label_gender);

		JRadioButton radio_M = new JRadioButton("남자");
		radio_M.setFont(new Font("굴림", Font.PLAIN, 15));
		radio_M.setBounds(209, 528, 60, 23);
		contentPane.add(radio_M);

		JRadioButton radio_W = new JRadioButton("여자");
		radio_W.setFont(new Font("굴림", Font.PLAIN, 15));
		radio_W.setBounds(319, 528, 60, 23);
		contentPane.add(radio_W);

		//라디오 버튼 같은 그룹에 추가해서 한번에 하나만 클릭되게
		genderGroup.add(radio_M);
		genderGroup.add(radio_W);

		JLabel label_height = new JLabel("키");
		label_height.setFont(new Font("굴림", Font.PLAIN, 15));
		label_height.setBounds(98, 413, 331, 44);
		contentPane.add(label_height);

		JLabel label_weight = new JLabel("체중");
		label_weight.setFont(new Font("굴림", Font.PLAIN, 15));
		label_weight.setBounds(98, 467, 331, 44);
		contentPane.add(label_weight);

		txt_height = new JTextField();
		txt_height.setFont(new Font("굴림", Font.PLAIN, 15));
		txt_height.setColumns(10);
		txt_height.setBounds(209, 418, 220, 35);
		contentPane.add(txt_height);

		txt_weight = new JTextField();
		txt_weight.setFont(new Font("굴림", Font.PLAIN, 15));
		txt_weight.setColumns(10);
		txt_weight.setBounds(209, 472, 220, 35);
		contentPane.add(txt_weight);

		JButton btn_sign = new JButton("가입");
		btn_sign.setBounds(98, 591, 148, 39);
		contentPane.add(btn_sign);

		JButton btn_cancel = new JButton("취소");
		btn_cancel.setBounds(281, 591, 148, 39);
		contentPane.add(btn_cancel);

		btn_sign.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String password = txt_pw.getText();
				String confirmPassword = txt_check_pw.getText();
				String age = txt_age.getText();
				String height = txt_height.getText();
				String weight = txt_weight.getText();
				String gender = radio_M.isSelected() ? "M" : "W"; // 성별 선택에 따라 값 설정


				if (!isValidNumber(age)) {
					JOptionPane.showMessageDialog(Sign.this,"나이는 정수만 입력해야 합니다.");
					return;
				}

				if (!isValidNumber(height)) {
					JOptionPane.showMessageDialog(Sign.this,"키는 숫자 또는 실수만 입력해야 합니다.");
					return;
				}

				if (!isValidNumber(weight)) {
					JOptionPane.showMessageDialog(Sign.this,"체중은 숫자 또는 실수만 입력해야 합니다.");
					return;
				}

				if (!password.equals(confirmPassword)) {
					JOptionPane.showMessageDialog(Sign.this,"비밀번호와 비밀번호 확인이 일치하지 않습니다.");
					return;
				}

				if (!validatePassword(password)) {
					JOptionPane.showMessageDialog(Sign.this,"비밀번호가 유효하지 않습니다.");
					return;
				}

				if (radio_M.isSelected()) {
					gender = "M";
				} else if (radio_W.isSelected()) {
					gender = "W";
				} else {
					// 성별이 선택되지 않았을 때 처리
					JOptionPane.showMessageDialog(Sign.this,"성별을 선택해주세요.");
					return;
				}
				// 사용자 정보를 데이터베이스에 삽입
				UserDao users = new UserDao();
				if(users.insertUser(txt_id.getText(), password, txt_name.getText(), txt_email.getText(), Integer.parseInt(age), Double.parseDouble(height), gender, Double.parseDouble(weight))) {
					System.out.println("ㅇㅇㅇ");
					JOptionPane.showMessageDialog(Sign.this,"회원가입이 완료되었습니다.");
					dispose();
				}else JOptionPane.showMessageDialog(Sign.this,"아이디 중복입니다.");
				
				
				
			}
		});

		btn_cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		setVisible(true);
	}


}