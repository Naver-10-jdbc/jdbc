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

public class Write extends JFrame {

	private JPanel contentPane;
	private JTextField txt_text;

	/**
	 * Launch the application.
	 */
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
		setBounds(100, 100, 704, 568);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel board_title = new JLabel("\uAC8C\uC2DC\uD310 \uAE00\uC4F0\uAE30");
		board_title.setFont(new Font("±º∏≤", Font.PLAIN, 18));
		board_title.setBounds(64, 23, 152, 59);
		contentPane.add(board_title);
		
		JPanel panel = new JPanel();
		panel.setBounds(64, 94, 564, 232);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel label_title = new JLabel("¡¶∏Ò");
		label_title.setFont(new Font("±º∏≤", Font.PLAIN, 20));
		panel.add(label_title, BorderLayout.NORTH);
		
		txt_text = new JTextField();
		panel.add(txt_text, BorderLayout.CENTER);
		txt_text.setColumns(10);
		
		JButton btn_register = new JButton("µÓ∑œ");
		btn_register.setBounds(458, 336, 79, 33);
		contentPane.add(btn_register);
		
		JButton btn_cancel = new JButton("√Îº“");
		btn_cancel.setBounds(549, 336, 79, 33);
		contentPane.add(btn_cancel);
	}
}
