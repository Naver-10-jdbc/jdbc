package view.bulletin_b;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import db.MySqlDBManager;
import view.logn.Session;

public class Comment extends JFrame {

    private JPanel contentPane;

    private JTable commentTable;

    private JLabel imageLabel;


    private int selectedBoardId; // ���õ� �Խñ��� ID ����


    public Comment(int boardId) {
        selectedBoardId = boardId;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 1300);
        setLocationRelativeTo(null);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // �Խñ� ������ ǥ���� ������Ʈ��
        JLabel titleLabel = new JLabel();
        titleLabel.setFont(new Font("����", Font.PLAIN, 25));
        titleLabel.setBounds(100, 80, 479, 30);
        contentPane.add(titleLabel);

        JTextArea contentTextArea = new JTextArea();
        contentTextArea.setEditable(false);
        JScrollPane contentScrollPane = new JScrollPane(contentTextArea);
        contentScrollPane.setBounds(97, 120, 260, 200);
        contentPane.add(contentScrollPane);

        // ���� ��ư
        JButton btn_back = new JButton("��");
        btn_back.setBounds(10, 10, 50, 50);
        contentPane.add(btn_back);

        // ���� ��ư Ŭ�� �� â �ݱ�
        btn_back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // commentTable �ʱ�ȭ
        commentTable = new JTable(){
        	@Override
            public boolean isCellEditable(int row, int column) {
                // ��� ���� ������ �� ������ ��Ȱ��ȭ
                return false;
            }
        };
        JScrollPane commentScrollPane = new JScrollPane(commentTable);
        commentScrollPane.setBounds(97, 340, 479, 170);
        contentPane.add(commentScrollPane);

        commentTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int row = commentTable.rowAtPoint(e.getPoint());
                    if (row >= 0 && row < commentTable.getRowCount()) {
                        commentTable.setRowSelectionInterval(row, row);
                        JPopupMenu popupMenu = new JPopupMenu();
                        JMenuItem deleteMenuItem = new JMenuItem("����");
                        deleteMenuItem.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int selectedRow = commentTable.getSelectedRow();
                                if (selectedRow >= 0 && selectedRow < commentTable.getRowCount()) {
                                    String fullComment = (String) commentTable.getValueAt(selectedRow, 0);
                                    String[] commentParts = fullComment.split(": ");
                                    String userId = commentParts[0].substring(commentParts[0].indexOf("]") + 2); // ��� �ۼ����� ID ��������
                                    String comment = commentParts[1];
                                    String loggedInUserId = Session.getInstance().getUserId(); // ���� �α����� ������� ID ��������

                                    // ���� �α����� ������� ID�� ��� �ۼ����� ID�� ���Ͽ� ������ ��쿡�� ���� ���
                                    if (loggedInUserId.equals(userId)) {
                                        int option = JOptionPane.showConfirmDialog(null, "����� �����Ͻðڽ��ϱ�?", "��� ����", JOptionPane.YES_NO_OPTION);
                                        if (option == JOptionPane.YES_OPTION) {
                                            deleteComment(selectedRow, selectedBoardId, commentTable);
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "�ٸ� ������� ����� ������ �� �����ϴ�.", "���� ����", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            }
                        });
                        popupMenu.add(deleteMenuItem);
                        popupMenu.show(commentTable, e.getX(), e.getY());
                    }
                }
            }
        });

    
        // �Է� �г�
        JPanel inputPanel = new JPanel(new BorderLayout());
        JTextField textField = new JTextField();

        JButton registerButton = new JButton("���");
        inputPanel.add(textField, BorderLayout.CENTER);
        inputPanel.add(registerButton, BorderLayout.EAST);
        inputPanel.setBounds(97, 530, 479, 50);
        contentPane.add(inputPanel);

        // ��� ��ư Ŭ�� �� ��� ���
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String comment = textField.getText();
                if (!comment.isEmpty()) {
                    addComment(selectedBoardId, comment, commentTable); // ��� �ۼ��� �߰�
                    textField.setText(""); // �Է� �ʵ� �ʱ�ȭ
                }
            }
        });

        

        setVisible(true);

        // ���õ� �Խñ��� ������ �����ͼ� ǥ��
        showBoardDetails(selectedBoardId, titleLabel, contentTextArea, commentTable);

        // �Խñ��� ��� ����� ǥ��
        loadComments(selectedBoardId, commentTable);
        
                // ����
                JButton btn_del = new JButton("����");
                btn_del.setBounds(502, 49, 77, 21);
                contentPane.add(btn_del);
                
                        // ���� ��ư Ŭ�� �� �Խñ� ����
                        btn_del.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                // Ȯ�� ���̾�α� ǥ��
                                int option = JOptionPane.showConfirmDialog(null, "�Խñ��� �����Ͻðڽ��ϱ�?", "�Խñ� ����", JOptionPane.YES_NO_OPTION);
                
                                // Ȯ�� ��ư�� Ŭ���� ��쿡�� �Խñ� ���� ����
                                if (option == JOptionPane.YES_OPTION) {
                                    deleteBoard(selectedBoardId); // ���õ� �Խñ� ����
                                    dispose(); // â �ݱ�
                                }
                            }
                        });
    }



    // ���õ� �Խñ��� �� ������ �����ͼ� ǥ���ϴ� �޼���
    private void showBoardDetails(int boardId, JLabel titleLabel, JTextArea contentTextArea, JTable commentTable) {
        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;

        try {
            // �����ͺ��̽� ����
            conn =MySqlDBManager.getInstance();

            // ���ν��� ȣ��
            cstmt = conn.prepareCall("{CALL select_board(?)}");
            cstmt.setInt(1, boardId);
            rs = cstmt.executeQuery();

            // ����� ���� ��쿡�� ���� ǥ��
            if (rs.next()) {
                titleLabel.setText(rs.getString("title")); // ���� ǥ��
                contentTextArea.setText(rs.getString("contents")); // ���� ǥ��

                // �̹��� ǥ��
                byte[] imageData = rs.getBytes("picture");
                if (imageData != null) {
                    ImageIcon imageIcon = new ImageIcon(imageData);
                    Image image = imageIcon.getImage();
                    Image scaledImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                    imageIcon = new ImageIcon(scaledImage);
                    imageLabel = new JLabel(imageIcon); // �̹��� ���̺� ���� ������ ����
                    imageLabel.setBounds(350, 120, 200, 200);
                    contentPane.add(imageLabel); // contentPane�� �̹��� ���̺� �߰�
                }
            }

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

    // �Խñ��� ����� �����ͼ� ���̺� ǥ���ϴ� �޼���
 // �Խñ��� ����� �����ͼ� ���̺� ǥ���ϴ� �޼���
    private void loadComments(int boardId, JTable commentTable) {
        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;

        try {
            // �����ͺ��̽� ����
            conn = MySqlDBManager.getInstance();

            // ���ν��� ȣ��
            cstmt = conn.prepareCall("{CALL select_comments(?)}");
            cstmt.setInt(1, boardId);
            rs = cstmt.executeQuery();

         // ���̺� �� ����
            DefaultTableModel model = new DefaultTableModel(new String[]{"���"}, 0);

            // ����¿��� ������ �о�� �𵨿� �߰�
            while (rs.next()) {
                String cmt_date = rs.getString("cmt_date");
                String comment = rs.getString("comment");
                String user_id = rs.getString("user_id");
                // �� �࿡ ��¥, �ۼ���, ��� �߰�
                String fullComment = "[" + cmt_date + "] " + user_id + ": " + comment;
                model.addRow(new Object[]{fullComment});
            }


            // ���̺� �� ����
            commentTable.setModel(model);

            // ���� ���� �����Ͽ� �� �࿡ ��� �����Ͱ� ���̵��� ����
            commentTable.setRowHeight(60);

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

 // ����� �߰��ϴ� �޼���
    private void addComment(int boardId, String comment, JTable commentTable) {
        Connection conn = null;
        
        PreparedStatement pstmt = null;

        try {
            // �����ͺ��̽� ����
            conn = MySqlDBManager.getInstance();

            // ���� �α��ε� ������� ID ��������
            String userId = Session.getInstance().getUserId(); // �� �κ��� ���� Ŭ������ �α��� ���� Ŭ������ ���� �ٸ� �� �ֽ��ϴ�.

            // SQL ���� �ۼ�
            String sql = "INSERT INTO comment (board_id, comment, user_id) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardId);
            pstmt.setString(2, comment);
            pstmt.setString(3, userId); // ����� ID �߰�

            // ���� ����
            pstmt.executeUpdate();

            // ��� ���̺� ����
            loadComments(boardId, commentTable);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


 // �Խñ� ���� �޼���
    private void deleteBoard(int boardId) {
        Connection conn = null;
        PreparedStatement pstmtDeleteComments = null;
        PreparedStatement pstmtDeleteBoard = null;

        try {
            // �����ͺ��̽� ����
            conn = MySqlDBManager.getInstance();

            // �Խñ� �ۼ����� ID�� �������� SQL �ۼ�
            String sqlGetAuthorId = "SELECT user_id FROM board WHERE board_id = ?";
            PreparedStatement pstmtGetAuthorId = conn.prepareStatement(sqlGetAuthorId);
            pstmtGetAuthorId.setInt(1, boardId);
            ResultSet rs = pstmtGetAuthorId.executeQuery();

            // �Խñ� �ۼ����� ID ��������
            String authorId = null;
            if (rs.next()) {
                authorId = rs.getString("user_id");
            }

            // ���� �α��ε� ������� ID ��������
            String loggedInUserId = Session.getInstance().getUserId(); // �� �κ��� ���� Ŭ������ �α��� ���� Ŭ������ ���� �ٸ� �� �ֽ��ϴ�.

            // �Խñ� �ۼ����� ID�� �α����� ������� ID�� ��ġ�ϴ� ��쿡�� ���� ����
            if (authorId != null && authorId.equals(loggedInUserId)) {
                // ��� ���� SQL �ۼ�
                String sqlDeleteComments = "DELETE FROM comment WHERE board_id = ?";
                pstmtDeleteComments = conn.prepareStatement(sqlDeleteComments);
                pstmtDeleteComments.setInt(1, boardId);

                // ��� ���� ����
                pstmtDeleteComments.executeUpdate();

                // �Խñ� ���� SQL �ۼ�
                String sqlDeleteBoard = "DELETE FROM board WHERE board_id = ?";
                pstmtDeleteBoard = conn.prepareStatement(sqlDeleteBoard);
                pstmtDeleteBoard.setInt(1, boardId);

                // �Խñ� ���� ����
                pstmtDeleteBoard.executeUpdate();
                showBoardDetails(boardId, imageLabel, null, commentTable);
                Board.loadBoardData();
            } else {
                // �ۼ����� ID�� �α����� ������� ID�� ��ġ���� �ʴ� ��쿡�� ������ �ź�
                JOptionPane.showMessageDialog(null, "�Խñ� �ۼ��ڸ� ������ �� �ֽ��ϴ�.", "���� ����", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (pstmtDeleteComments != null) pstmtDeleteComments.close();
                if (pstmtDeleteBoard != null) pstmtDeleteBoard.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


    
    private void deleteComment(int row, int boardId, JTable commentTable) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // �����ͺ��̽� ����
            conn = MySqlDBManager.getInstance();

            // ���õ� �࿡�� ��� ������ ��������
            String fullComment = (String) commentTable.getValueAt(row, 0);
            String[] commentParts = fullComment.split(": ");
            // ����� �κ�: �ۼ��� ID�� ��� ������ �и��� �� ":"�� �������� �и��Ͽ��� ��
            String userId = commentParts[0].substring(commentParts[0].indexOf("]") + 2); // ��� �ۼ����� ID ��������
            String comment = commentParts[1]; // ��� ���� ��������

            // SQL ���� �ۼ�
            // ����� �κ�: ��� ����� �ۼ��� ID�� ��� Ȯ���Ͽ� �����ϵ��� ���� ����
            String sql = "DELETE FROM comment WHERE comment = ? AND user_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, comment);
            pstmt.setString(2, userId);

            // ���� ����
            pstmt.executeUpdate();

            // ��� ���̺� ����
            loadComments(boardId, commentTable);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}