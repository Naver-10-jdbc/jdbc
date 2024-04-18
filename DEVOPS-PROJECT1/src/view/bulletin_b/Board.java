package view.bulletin_b;

import db.MySqlDBManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


public class Board extends JFrame {

	private JPanel contentPane;
	private static JTable table;
	


	public Board() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 568);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 이전 버튼
		JButton btn_back = new JButton("←");
		btn_back.setBackground(new Color(255, 255, 255));
		btn_back.setBounds(10, 10, 50, 50);
		contentPane.add(btn_back);
		
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});

		// 인증 게시판
		JLabel label_board = new JLabel("인증게시판");
		label_board.setFont(new Font("굴림", Font.PLAIN, 20));
		label_board.setBounds(307, 35, 115, 24);
		contentPane.add(label_board);

		// 테이블 생성
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(97, 95, 551, 287);
		contentPane.add(scrollPane);

		table = new JTable() {
			@Override
		    public boolean isCellEditable(int row, int column) {
	        // 모든 셀을 편집할 수 없도록 비활성화
	        return false;
			}
	    };
		table.setBackground(new Color(192, 192, 192));
		scrollPane.setViewportView(table);

		// 글쓰기 버튼
		JButton btn_add = new JButton("글쓰기");
		btn_add.setBounds(569, 406, 79, 33);
		contentPane.add(btn_add);

		// 글쓰기 버튼 액션
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Write();
			}
		});

		// 이전 버튼 액션
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		setVisible(true);

		// 게시판 데이터 가져오기
		loadBoardData();


		// 테이블에 마우스 클릭 이벤트 리스너 추가
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) { // 행이 선택된 경우
					int boardId = (int) table.getValueAt(selectedRow, 0); // 선택된 행의 첫 번째 열 값(게시글 ID) 가져오기
					// 상세 페이지로 이동하는 메서드 호출
					showDetailPage(boardId);
				}
			}
		});
	}

	// 게시판 데이터 가져오기 메서드
	public static void loadBoardData() {
		Connection conn = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		try {
			// 데이터베이스 연결
			conn = MySqlDBManager.getInstance();

			// 프로시저 호출
			cstmt = conn.prepareCall("{CALL select_all_board()}");
			rs = cstmt.executeQuery();

			// 테이블 모델 생성
			DefaultTableModel model = new DefaultTableModel();
			model.setColumnIdentifiers(new String[]{"번호", "제목", "작성자", "작성시간"});

			// 결과셋에서 데이터 읽어와 모델에 추가
			while (rs.next()) {
				Object[] rowData = new Object[]{
						rs.getInt("board_id"),
						rs.getString("title"),
						rs.getString("user_id"),
						rs.getTimestamp("board_date")
				};
				model.addRow(rowData);
			}

			// 테이블에 모델 설정
			table.setModel(model);

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				MySqlDBManager.disconnect(conn, cstmt, rs);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	// 상세 페이지로 이동하는 메서드
	private void showDetailPage(int boardId) {
		// 선택된 게시글의 ID를 매개변수로 전달하여 Comment 클래스의 인스턴스 생성
		new Comment(boardId);
	}
}

