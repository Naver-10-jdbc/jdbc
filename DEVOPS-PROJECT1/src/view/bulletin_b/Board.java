package view.bulletin_b;
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
	//tt
	
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
		setBounds(100, 100, 700, 568);
		setResizable(false); //화면 크기 조정 못하게
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
		setVisible(false);
		
	}
}
