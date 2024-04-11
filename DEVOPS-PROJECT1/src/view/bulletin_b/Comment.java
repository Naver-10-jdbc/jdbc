package view.bulletin_b;

import db.MySqlDBManager;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

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
        contentScrollPane.setBounds(97, 120, 479, 200);
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

        // ����
        JSplitPane splitPane = new JSplitPane();
        splitPane.setBounds(445, 47, 131, 23);
        contentPane.add(splitPane);

        JButton btn_alter = new JButton("����");
        btn_alter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // ���� ��� ����
            }
        });
        splitPane.setLeftComponent(btn_alter);

        // ����
        JButton btn_del = new JButton("����");
        splitPane.setRightComponent(btn_del);

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

        // commentTable �ʱ�ȭ
        commentTable = new JTable();
        JScrollPane commentScrollPane = new JScrollPane(commentTable);
        commentScrollPane.setBounds(97, 330, 479, 170);
        contentPane.add(commentScrollPane);

        // �Է� �г�
        JPanel inputPanel = new JPanel(new BorderLayout());
        JTextField textField = new JTextField();

        JButton registerButton = new JButton("���");
        registerButton.setBounds(460, 510, 116, 30);
        contentPane.add(registerButton);

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

        inputPanel.add(textField, BorderLayout.CENTER);
        inputPanel.add(registerButton, BorderLayout.EAST);
        inputPanel.setBounds(97, 510, 479, 50);
        contentPane.add(inputPanel);

        setVisible(true);

        // ���õ� �Խñ��� ������ �����ͼ� ǥ��
        showBoardDetails(selectedBoardId, titleLabel, contentTextArea, commentTable);

        // �Խñ��� ��� ����� ǥ��
        loadComments(selectedBoardId, commentTable);
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
                    imageLabel.setBounds(97, 560, 200, 200);
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
    private void loadComments(int boardId, JTable commentTable) {
        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;

        try {
            // �����ͺ��̽� ����
            conn =MySqlDBManager.getInstance();

            // ���ν��� ȣ��
            cstmt = conn.prepareCall("{CALL select_comments(?)}");
            cstmt.setInt(1, boardId);
            rs = cstmt.executeQuery();

            // ���̺� �� ����
            DefaultTableModel model = new DefaultTableModel(new String[]{"��¥", "���", "�ۼ���"}, 0);

            // ����¿��� ������ �о�� �𵨿� �߰�
            while (rs.next()) {
                String cmt_date = rs.getString("cmt_date");
                String comment = rs.getString("comment");
                String user_id = rs.getString("user_id");
                model.addRow(new Object[]{cmt_date, comment, user_id});
            }

            // ���̺� �� ����
            commentTable.setModel(model);

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

            // SQL ���� �ۼ�
            String sql = "INSERT INTO comments (board_id, comment) VALUES (?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardId);
            pstmt.setString(2, comment);

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
        CallableStatement stmt = null;

        try {
            // �����ͺ��̽� ����
            conn = MySqlDBManager.getInstance();;

            // ���ν��� ȣ��
            stmt = conn.prepareCall("{CALL delete_board(?)}");
            stmt.setInt(1, boardId);
            stmt.executeUpdate(); // ���ν��� ����

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
