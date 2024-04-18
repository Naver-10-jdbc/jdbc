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

		// ���� ��ư
		JButton btn_back = new JButton("��");
		btn_back.setBackground(new Color(255, 255, 255));
		btn_back.setBounds(10, 10, 50, 50);
		contentPane.add(btn_back);
		
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});

		// ���� �Խ���
		JLabel label_board = new JLabel("�����Խ���");
		label_board.setFont(new Font("����", Font.PLAIN, 20));
		label_board.setBounds(307, 35, 115, 24);
		contentPane.add(label_board);

		// ���̺� ����
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(97, 95, 551, 287);
		contentPane.add(scrollPane);

		table = new JTable() {
			@Override
		    public boolean isCellEditable(int row, int column) {
	        // ��� ���� ������ �� ������ ��Ȱ��ȭ
	        return false;
			}
	    };
		table.setBackground(new Color(192, 192, 192));
		scrollPane.setViewportView(table);

		// �۾��� ��ư
		JButton btn_add = new JButton("�۾���");
		btn_add.setBounds(569, 406, 79, 33);
		contentPane.add(btn_add);

		// �۾��� ��ư �׼�
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Write();
			}
		});

		// ���� ��ư �׼�
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		setVisible(true);

		// �Խ��� ������ ��������
		loadBoardData();


		// ���̺� ���콺 Ŭ�� �̺�Ʈ ������ �߰�
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) { // ���� ���õ� ���
					int boardId = (int) table.getValueAt(selectedRow, 0); // ���õ� ���� ù ��° �� ��(�Խñ� ID) ��������
					// �� �������� �̵��ϴ� �޼��� ȣ��
					showDetailPage(boardId);
				}
			}
		});
	}

	// �Խ��� ������ �������� �޼���
	public static void loadBoardData() {
		Connection conn = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		try {
			// �����ͺ��̽� ����
			conn = MySqlDBManager.getInstance();

			// ���ν��� ȣ��
			cstmt = conn.prepareCall("{CALL select_all_board()}");
			rs = cstmt.executeQuery();

			// ���̺� �� ����
			DefaultTableModel model = new DefaultTableModel();
			model.setColumnIdentifiers(new String[]{"��ȣ", "����", "�ۼ���", "�ۼ��ð�"});

			// ����¿��� ������ �о�� �𵨿� �߰�
			while (rs.next()) {
				Object[] rowData = new Object[]{
						rs.getInt("board_id"),
						rs.getString("title"),
						rs.getString("user_id"),
						rs.getTimestamp("board_date")
				};
				model.addRow(rowData);
			}

			// ���̺� �� ����
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

	// �� �������� �̵��ϴ� �޼���
	private void showDetailPage(int boardId) {
		// ���õ� �Խñ��� ID�� �Ű������� �����Ͽ� Comment Ŭ������ �ν��Ͻ� ����
		new Comment(boardId);
	}
}

