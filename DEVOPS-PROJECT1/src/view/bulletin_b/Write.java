package view.bulletin_b;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import db.MySqlDBManager;
import view.logn.Session;

public class Write extends JFrame {

	private JPanel contentPane;
	private JTextField txt_file;
	private JTextField txt_title;
	private JTextArea textArea;

	public Write() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 568);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel board_title = new JLabel("\uAC8C\uC2DC\uD310 \uAE00\uC4F0\uAE30");
		board_title.setBounds(64, 21, 152, 59);
		board_title.setFont(new Font("굴림", Font.PLAIN, 22));
		contentPane.add(board_title);

		JButton btn_register = new JButton("등록");
		btn_register.setBounds(435, 423, 79, 33);
		contentPane.add(btn_register);

		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title = txt_title.getText();
				String contents = textArea.getText();

				// 이미지 파일 가져오기
				byte[] picture = null;
				try {
					File selectedFile = new File(txt_file.getText());
					FileInputStream fis = new FileInputStream(selectedFile);
					picture = fis.readAllBytes();
					fis.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				String userId = Session.getInstance().getUserId();
				
				// 프로시저 호출
				insertBoard(title, contents, picture, userId);

				JOptionPane.showMessageDialog(null, "등록되었습니다!", "알림", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		JButton btn_cancel = new JButton("취소");
		btn_cancel.setBounds(526, 423, 79, 33);
		contentPane.add(btn_cancel);

		btn_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JButton btn_img = new JButton("사진 등록");
		btn_img.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int result = fileChooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					txt_file.setText(selectedFile.getAbsolutePath());
				}
			}
		});
		btn_img.setBounds(74, 366, 86, 27);
		contentPane.add(btn_img);

		JPanel panel = new JPanel();
		panel.setBounds(64, 94, 541, 262);
		contentPane.add(panel);

		JLabel label_title = new JLabel("\uC81C\uBAA9");
		label_title.setFont(new Font("굴림", Font.PLAIN, 15));
		label_title.setHorizontalAlignment(JLabel.CENTER);

		txt_title = new JTextField();
		txt_title.setFont(new Font("굴림", Font.PLAIN, 15));
		txt_title.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
												.addComponent(label_title, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(txt_title, GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE))
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 528, GroupLayout.PREFERRED_SIZE))
								.addContainerGap())
		);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(txt_title, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_title, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE))
		);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		panel.setLayout(gl_panel);

		txt_file = new JTextField();
		txt_file.setEditable(false);
		txt_file.setBounds(170, 366, 435, 27);
		contentPane.add(txt_file);
		txt_file.setColumns(10);

		setVisible(true);
	}

	// 프로시저를 호출하여 게시글을 등록하는 메서드
	private void insertBoard(String title, String contents, byte[] picture, String user_id) {
		Connection conn = null;
		CallableStatement cstmt = null;

		try {
			conn = MySqlDBManager.getInstance();

			// 프로시저 호출
			cstmt = conn.prepareCall("{CALL insert_board(?, ?, ?, ?)}");
			cstmt.setString(1, title);
			cstmt.setString(2, contents);
			
			if (picture != null) {
				cstmt.setBytes(3, picture); // 이미지가 존재하는 경우
			} else {
				
				cstmt.setNull(3, Types.BLOB); // 이미지가 존재하지 않는 경우
			}			cstmt.setString(4, user_id);
			cstmt.executeUpdate();
			Board.loadBoardData();
			dispose();
		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {
			try {
				if (cstmt != null) cstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				new Write();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}