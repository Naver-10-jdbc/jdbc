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
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;

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
		setBounds(100, 100, 700, 1300);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(97, 476, 479, 50);
		contentPane.add(scrollPane);
		
		JLabel label_comment = new JLabel("\uB313\uAE00");
		scrollPane.setRowHeaderView(label_comment);
		label_comment.setFont(new Font("±¼¸²", Font.PLAIN, 16));
		
		JTextArea txt_comment = new JTextArea();
		scrollPane.setViewportView(txt_comment);
		
		JButton btn_register = new JButton("\uB4F1\uB85D");
		btn_register.setBounds(489, 537, 87, 23);
		contentPane.add(btn_register);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(97, 80, 479, 232);
		contentPane.add(scrollPane_1);
		
		JLabel push_title = new JLabel("\uC81C\uBAA9\uBD88\uB7EC\uC624\uAE30");//ÀÛ¼ºÀÚ°¡ ¾´ ±Û Á¦¸ñ ¹Þ¾Æ¿À±â
		push_title.setFont(new Font("±¼¸²", Font.PLAIN, 25));
		scrollPane_1.setColumnHeaderView(push_title);
		
		JLabel push_write = new JLabel("\uAE00 \uBD88\uB7EC\uC624\uAE30");
		push_write.setFont(new Font("±¼¸²", Font.PLAIN, 16));
		scrollPane_1.setViewportView(push_write);
		
		JButton btn_cancel = new JButton("\u2190");
		btn_cancel.setBounds(10, 10, 50, 50);
		contentPane.add(btn_cancel);
		
		JButton btn_img = new JButton("\uC774\uBBF8\uC9C0 \uBD88\uB7EC\uC624\uAE30");
		btn_img.setBounds(97, 322, 159, 144);
		contentPane.add(btn_img);
		
		JPanel comment_1 = new JPanel();
		comment_1.setBounds(99, 571, 480, 64);
		contentPane.add(comment_1);
		GridBagLayout gbl_comment_1 = new GridBagLayout();
		gbl_comment_1.columnWidths = new int[]{260, 215, 0};
		gbl_comment_1.rowHeights = new int[]{15, 0, 0};
		gbl_comment_1.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_comment_1.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		comment_1.setLayout(gbl_comment_1);
		
		JLabel label_writer_1 = new JLabel("\uC791\uC131\uC790, \uB0A0\uC9DC");
		GridBagConstraints gbc_label_writer_1 = new GridBagConstraints();
		gbc_label_writer_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_writer_1.anchor = GridBagConstraints.WEST;
		gbc_label_writer_1.gridx = 0;
		gbc_label_writer_1.gridy = 0;
		comment_1.add(label_writer_1, gbc_label_writer_1);
		
		JButton btn_com_1 = new JButton("\uC0AD\uC81C");
		btn_com_1.setFont(new Font("±¼¸²", Font.PLAIN, 12));
		btn_com_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btn_com_1 = new GridBagConstraints();
		gbc_btn_com_1.anchor = GridBagConstraints.EAST;
		gbc_btn_com_1.insets = new Insets(0, 0, 5, 0);
		gbc_btn_com_1.gridx = 1;
		gbc_btn_com_1.gridy = 0;
		comment_1.add(btn_com_1, gbc_btn_com_1);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_3 = new GridBagConstraints();
		gbc_scrollPane_3.gridwidth = 2;
		gbc_scrollPane_3.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_3.gridx = 0;
		gbc_scrollPane_3.gridy = 1;
		comment_1.add(scrollPane_3, gbc_scrollPane_3);
		
		JTextArea push_com_1 = new JTextArea();
		scrollPane_3.setViewportView(push_com_1);
		
		JPanel comment_2 = new JPanel();
		comment_2.setBounds(99, 634, 480, 64);
		contentPane.add(comment_2);
		GridBagLayout gbl_comment_2 = new GridBagLayout();
		gbl_comment_2.columnWidths = new int[]{260, 215, 0};
		gbl_comment_2.rowHeights = new int[]{18, 36, 0};
		gbl_comment_2.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_comment_2.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		comment_2.setLayout(gbl_comment_2);
		
		JLabel label_writer_2 = new JLabel("\uC791\uC131\uC790,\uB0A0\uC9DC");
		GridBagConstraints gbc_label_writer_2 = new GridBagConstraints();
		gbc_label_writer_2.anchor = GridBagConstraints.WEST;
		gbc_label_writer_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_writer_2.gridx = 0;
		gbc_label_writer_2.gridy = 0;
		comment_2.add(label_writer_2, gbc_label_writer_2);
		
		JButton btn_com_2 = new JButton("\uC0AD\uC81C");
		btn_com_2.setFont(new Font("Gulim", Font.PLAIN, 12));
		btn_com_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btn_com_2 = new GridBagConstraints();
		gbc_btn_com_2.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btn_com_2.insets = new Insets(0, 0, 5, 0);
		gbc_btn_com_2.gridx = 1;
		gbc_btn_com_2.gridy = 0;
		comment_2.add(btn_com_2, gbc_btn_com_2);
		
		JScrollPane scrollPane_3_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_3_1 = new GridBagConstraints();
		gbc_scrollPane_3_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_3_1.gridwidth = 2;
		gbc_scrollPane_3_1.gridx = 0;
		gbc_scrollPane_3_1.gridy = 1;
		comment_2.add(scrollPane_3_1, gbc_scrollPane_3_1);
		
		JTextArea push_com_2 = new JTextArea();
		scrollPane_3_1.setViewportView(push_com_2);
		
		JPanel comment_3 = new JPanel();
		comment_3.setBounds(99, 697, 480, 64);
		contentPane.add(comment_3);
		GridBagLayout gbl_comment_3 = new GridBagLayout();
		gbl_comment_3.columnWidths = new int[]{260, 215, 0};
		gbl_comment_3.rowHeights = new int[]{15, 0, 0};
		gbl_comment_3.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_comment_3.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		comment_3.setLayout(gbl_comment_3);
		
		JLabel label_writer_3 = new JLabel("\uC791\uC131\uC790, \uB0A0\uC9DC");
		GridBagConstraints gbc_label_writer_3 = new GridBagConstraints();
		gbc_label_writer_3.anchor = GridBagConstraints.WEST;
		gbc_label_writer_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_writer_3.gridx = 0;
		gbc_label_writer_3.gridy = 0;
		comment_3.add(label_writer_3, gbc_label_writer_3);
		
		JButton btn_com_3 = new JButton("\uC0AD\uC81C");
		GridBagConstraints gbc_btn_com_3 = new GridBagConstraints();
		gbc_btn_com_3.anchor = GridBagConstraints.EAST;
		gbc_btn_com_3.insets = new Insets(0, 0, 5, 0);
		gbc_btn_com_3.gridx = 1;
		gbc_btn_com_3.gridy = 0;
		comment_3.add(btn_com_3, gbc_btn_com_3);
		
		JScrollPane scrollPane_3_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_3_2 = new GridBagConstraints();
		gbc_scrollPane_3_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_3_2.gridwidth = 2;
		gbc_scrollPane_3_2.gridx = 0;
		gbc_scrollPane_3_2.gridy = 1;
		comment_3.add(scrollPane_3_2, gbc_scrollPane_3_2);
		
		JTextArea push_com_3 = new JTextArea();
		scrollPane_3_2.setViewportView(push_com_3);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setBounds(445, 47, 131, 23);
		contentPane.add(splitPane);
		
		JButton btn_alter = new JButton("\uC218\uC815");
		btn_alter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		splitPane.setLeftComponent(btn_alter);
		
		JButton btn_del = new JButton("\uC0AD\uC81C");
		splitPane.setRightComponent(btn_del);
	}
}
