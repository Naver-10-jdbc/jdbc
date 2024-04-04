package view.bulletin_b;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Write extends JFrame {

	private JPanel contentPane;
	private JTextField txt_file;
	private JTextField txt_title;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Write frame = new Write();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Write() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 568);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel board_title = new JLabel("\uAC8C\uC2DC\uD310 \uAE00\uC4F0\uAE30");
		board_title.setBounds(64, 21, 152, 59);
		board_title.setFont(new Font("±º∏≤", Font.PLAIN, 22));
		contentPane.add(board_title);
		
		JButton btn_register = new JButton("µÓ∑œ");
		btn_register.setBounds(435, 423, 79, 33);
		contentPane.add(btn_register);
		
		JButton btn_cancel = new JButton("√Îº“");
		btn_cancel.setBounds(526, 423, 79, 33);
		contentPane.add(btn_cancel);
		
		JButton btn_file = new JButton("\uCCA8\uBD80\uD30C\uC77C");
		btn_file.setBounds(74, 366, 86, 27);
		contentPane.add(btn_file);
		
		txt_file = new JTextField();
		txt_file.setBounds(175, 369, 430, 21);
		contentPane.add(txt_file);
		txt_file.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(64, 94, 541, 262);
		contentPane.add(panel);
		
		JLabel label_title = new JLabel("\uC81C\uBAA9");
		label_title.setFont(new Font("±º∏≤", Font.PLAIN, 15));
		label_title.setHorizontalAlignment(JLabel.CENTER);
		
		txt_title = new JTextField();
		txt_title.setFont(new Font("±º∏≤", Font.PLAIN, 15));
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
							.addComponent(txt_title, GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 528, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
	}
}
