package view.bulletin_b;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;

public class Comment extends JFrame {

	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Comment frame = new Comment();
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
	public Comment() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(101, 409, 479, 79);
		contentPane.add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("\uB313\uAE00");
		scrollPane.setRowHeaderView(lblNewLabel);
		lblNewLabel.setFont(new Font("±¼¸²", Font.PLAIN, 16));
		
		JTextArea txt_comment = new JTextArea();
		scrollPane.setViewportView(txt_comment);
		
		JButton btn_register = new JButton("\uB4F1\uB85D");
		btn_register.setBounds(489, 498, 91, 23);
		contentPane.add(btn_register);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(101, 52, 479, 232);
		contentPane.add(scrollPane_1);
		
		JLabel push_title = new JLabel("\uC81C\uBAA9\uBD88\uB7EC\uC624\uAE30");//ÀÛ¼ºÀÚ°¡ ¾´ ±Û Á¦¸ñ ¹Þ¾Æ¿À±â
		push_title.setFont(new Font("±¼¸²", Font.PLAIN, 25));
		scrollPane_1.setColumnHeaderView(push_title);
		
		JLabel push_write = new JLabel("\uAE00 \uBD88\uB7EC\uC624\uAE30");
		push_write.setFont(new Font("±¼¸²", Font.PLAIN, 16));
		scrollPane_1.setViewportView(push_write);
		
		JLabel label_img = new JLabel("\uC774\uBBF8\uC9C0 \uB123\uAE30");
		label_img.setBounds(101, 294, 319, 79);
		contentPane.add(label_img);
		
		JButton btn_alter = new JButton("\uC218\uC815");
		btn_alter.setBounds(432, 335, 68, 38);
		contentPane.add(btn_alter);
		
		JButton btn_del = new JButton("\uC0AD\uC81C");
		btn_del.setBounds(512, 335, 68, 38);
		contentPane.add(btn_del);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(101, 537, 479, 79);
		contentPane.add(scrollPane_2);
		
		JLabel label_writer = new JLabel("\uC791\uC131\uC790");
		label_writer.setFont(new Font("±¼¸²", Font.PLAIN, 16));
		scrollPane_2.setRowHeaderView(label_writer);
		
		JLabel lblNewLabel_2 = new JLabel("\uB313\uAE00 \uAC00\uC838\uC624\uAE30");
		lblNewLabel_2.setFont(new Font("±¼¸²", Font.PLAIN, 16));
		scrollPane_2.setViewportView(lblNewLabel_2);
	}
}
