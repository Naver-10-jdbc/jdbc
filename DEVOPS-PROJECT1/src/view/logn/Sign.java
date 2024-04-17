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


		JLabel txt_sign = new JLabel("ȸ������");
		txt_sign.setFont(new Font("����", Font.PLAIN, 20));
		txt_sign.setBounds(235, 37, 79, 39);
		contentPane.add(txt_sign);

		JLabel label_name = new JLabel("�̸�");
		label_name.setFont(new Font("����", Font.PLAIN, 15));
		label_name.setBounds(98, 89, 364, 44);
		contentPane.add(label_name);

		txt_name = new JTextField();
		txt_name.setFont(new Font("����", Font.PLAIN, 15));
		txt_name.setBounds(209, 96, 220, 35);
		contentPane.add(txt_name);
		txt_name.setColumns(10);

		JLabel labe_age = new JLabel("����");
		labe_age.setFont(new Font("����", Font.PLAIN, 15));
		labe_age.setBounds(98, 143, 364, 44);
		contentPane.add(labe_age);

		txt_age = new JTextField();
		txt_age.setFont(new Font("����", Font.PLAIN, 15));
		txt_age.setColumns(10);
		txt_age.setBounds(209, 150, 220, 35);
		contentPane.add(txt_age);

		JLabel label_id = new JLabel("���̵�");
		label_id.setFont(new Font("����", Font.PLAIN, 15));
		label_id.setBounds(98, 197, 364, 44);
		contentPane.add(label_id);

		txt_id = new JTextField();
		txt_id.setFont(new Font("����", Font.PLAIN, 15));
		txt_id.setColumns(10);
		txt_id.setBounds(209, 204, 220, 35);
		contentPane.add(txt_id);

		JLabel label_pw = new JLabel("��й�ȣ");
		label_pw.setFont(new Font("����", Font.PLAIN, 15));
		label_pw.setBounds(98, 251, 364, 44);
		contentPane.add(label_pw);

		txt_pw = new JPasswordField();
		txt_pw.setFont(new Font("����", Font.PLAIN, 15));
		txt_pw.setBounds(209, 258, 220, 35);
		contentPane.add(txt_pw);

		JLabel label_check_pw = new JLabel("��й�ȣ Ȯ��");
		label_check_pw.setFont(new Font("����", Font.PLAIN, 15));
		label_check_pw.setBounds(98, 305, 364, 44);
		contentPane.add(label_check_pw);

		txt_check_pw = new JPasswordField();
		txt_check_pw.setFont(new Font("����", Font.PLAIN, 15));
		txt_check_pw.setBounds(209, 312, 220, 35);
		contentPane.add(txt_check_pw);

		JLabel label_email = new JLabel("�̸���");
		label_email.setFont(new Font("����", Font.PLAIN, 15));
		label_email.setBounds(98, 359, 364, 44);
		contentPane.add(label_email);

		txt_email = new JTextField();
		txt_email.setFont(new Font("����", Font.PLAIN, 15));
		txt_email.setColumns(10);
		txt_email.setBounds(209, 366, 220, 35);
		contentPane.add(txt_email);

		JLabel label_gender = new JLabel("����");
		label_gender.setFont(new Font("����", Font.PLAIN, 15));
		label_gender.setBounds(98, 517, 331, 44);
		contentPane.add(label_gender);

		JRadioButton radio_M = new JRadioButton("����");
		radio_M.setFont(new Font("����", Font.PLAIN, 15));
		radio_M.setBounds(209, 528, 60, 23);
		contentPane.add(radio_M);

		JRadioButton radio_W = new JRadioButton("����");
		radio_W.setFont(new Font("����", Font.PLAIN, 15));
		radio_W.setBounds(319, 528, 60, 23);
		contentPane.add(radio_W);

		//���� ��ư ���� �׷쿡 �߰��ؼ� �ѹ��� �ϳ��� Ŭ���ǰ�
		genderGroup.add(radio_M);
		genderGroup.add(radio_W);

		JLabel label_height = new JLabel("Ű");
		label_height.setFont(new Font("����", Font.PLAIN, 15));
		label_height.setBounds(98, 413, 331, 44);
		contentPane.add(label_height);

		JLabel label_weight = new JLabel("ü��");
		label_weight.setFont(new Font("����", Font.PLAIN, 15));
		label_weight.setBounds(98, 467, 331, 44);
		contentPane.add(label_weight);

		txt_height = new JTextField();
		txt_height.setFont(new Font("����", Font.PLAIN, 15));
		txt_height.setColumns(10);
		txt_height.setBounds(209, 418, 220, 35);
		contentPane.add(txt_height);

		txt_weight = new JTextField();
		txt_weight.setFont(new Font("����", Font.PLAIN, 15));
		txt_weight.setColumns(10);
		txt_weight.setBounds(209, 472, 220, 35);
		contentPane.add(txt_weight);

		JButton btn_sign = new JButton("����");
		btn_sign.setBounds(98, 591, 148, 39);
		contentPane.add(btn_sign);

		JButton btn_cancel = new JButton("���");
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
				String gender = radio_M.isSelected() ? "M" : "W"; // ���� ���ÿ� ���� �� ����


				if (!isValidNumber(age)) {
					JOptionPane.showMessageDialog(Sign.this,"���̴� ������ �Է��ؾ� �մϴ�.");
					return;
				}

				if (!isValidNumber(height)) {
					JOptionPane.showMessageDialog(Sign.this,"Ű�� ���� �Ǵ� �Ǽ��� �Է��ؾ� �մϴ�.");
					return;
				}

				if (!isValidNumber(weight)) {
					JOptionPane.showMessageDialog(Sign.this,"ü���� ���� �Ǵ� �Ǽ��� �Է��ؾ� �մϴ�.");
					return;
				}

				if (!password.equals(confirmPassword)) {
					JOptionPane.showMessageDialog(Sign.this,"��й�ȣ�� ��й�ȣ Ȯ���� ��ġ���� �ʽ��ϴ�.");
					return;
				}

				if (!validatePassword(password)) {
					JOptionPane.showMessageDialog(Sign.this,"��й�ȣ�� ��ȿ���� �ʽ��ϴ�.");
					return;
				}

				if (radio_M.isSelected()) {
					gender = "M";
				} else if (radio_W.isSelected()) {
					gender = "W";
				} else {
					// ������ ���õ��� �ʾ��� �� ó��
					JOptionPane.showMessageDialog(Sign.this,"������ �������ּ���.");
					return;
				}
				// ����� ������ �����ͺ��̽��� ����
				UserDao users = new UserDao();
				if(users.insertUser(txt_id.getText(), password, txt_name.getText(), txt_email.getText(), Integer.parseInt(age), Double.parseDouble(height), gender, Double.parseDouble(weight))) {
					System.out.println("������");
					JOptionPane.showMessageDialog(Sign.this,"ȸ�������� �Ϸ�Ǿ����ϴ�.");
					dispose();
				}else JOptionPane.showMessageDialog(Sign.this,"���̵� �ߺ��Դϴ�.");
				
				
				
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