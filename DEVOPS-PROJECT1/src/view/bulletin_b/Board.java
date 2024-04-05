package view.bulletin_b;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Board extends JFrame {

	private JPanel contentPane;
	private JTable table;
	

	public Board() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 568);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btn_back = new JButton("←");
		btn_back.setBounds(10, 10, 50, 50);
		contentPane.add(btn_back);
		
		JLabel label_board = new JLabel("인증게시판");
		label_board.setFont(new Font("굴림", Font.PLAIN, 20));
		label_board.setBounds(307, 35, 115, 24);
		contentPane.add(label_board);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(97, 95, 551, 287);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(192, 192, 192));
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"*", "공지사항", "관리자", null, null},//
			},
			new String[] {
				"번호", "작성자", "작성자", "작성시간", "좋아요"
			}
		));
		table.setFont(new Font("굴림", Font.PLAIN, 15));
		
		JButton btn_add = new JButton("글쓰기");
		btn_add.setBounds(569, 406, 79, 33);
		contentPane.add(btn_add);

		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Write();
				
			}
		});
		
		setVisible(true);
	}
}
