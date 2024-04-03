import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Color;

public class Board extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Board frame = new Board();
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
	public Board() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 704, 568);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btn_back = new JButton("←");
		btn_back.setBounds(12, 10, 63, 50);
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
				{"*", "공지사항", "관리자", null, null},
			},
			new String[] {
				"번호", "작성자", "작성자", "작성시간", "좋아요"
			}
		));
		table.setFont(new Font("굴림", Font.PLAIN, 15));
		
		JButton btn_add = new JButton("추가");
		btn_add.setBounds(387, 411, 79, 33);
		contentPane.add(btn_add);
		
		JButton btn_alter = new JButton("변경");
		btn_alter.setBounds(478, 411, 79, 33);
		contentPane.add(btn_alter);
		
		JButton btn_del = new JButton("삭제");
		btn_del.setBounds(569, 411, 79, 33);
		contentPane.add(btn_del);
		setVisible(true);
	}
}
