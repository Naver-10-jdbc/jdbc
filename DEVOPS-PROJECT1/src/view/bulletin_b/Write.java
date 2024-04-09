package view.bulletin_b;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class Write extends JFrame {

	private JPanel contentPane;
	private JTextField txt_file;
	private JTextField txt_title;


	public Write() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 568);
		//setLocationRelativeTo(null);
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
				JOptionPane.showMessageDialog(null, "등록되었습니다!","알림",JOptionPane.INFORMATION_MESSAGE);
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
                    try {
                        // 이미지를 읽어옴
                        Image img = ImageIO.read(selectedFile);
                        // 이미지의 비율을 유지하면서 버튼 크기에 맞게 조절
                        Image scaledImg = img.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
                        // 이미지를 표시할 JLabel 생성
                        JLabel label = new JLabel(new ImageIcon(scaledImg));
                        // JLabel을 포함하는 패널 생성
                        JPanel panel = new JPanel();
                        panel.add(label);
                        // 팝업 창으로 이미지 표시
                        JOptionPane.showMessageDialog(null, panel, "이미지", JOptionPane.PLAIN_MESSAGE);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
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
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_title, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txt_title, GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 528, GroupLayout.PREFERRED_SIZE))
					.addGap(12))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txt_title, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_title, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE))
		);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		panel.setLayout(gl_panel);
		
		setVisible(true);
	}
}
