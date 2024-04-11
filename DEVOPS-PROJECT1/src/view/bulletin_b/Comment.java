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


    private int selectedBoardId; // 선택된 게시글의 ID 저장


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

        // 게시글 정보를 표시할 컴포넌트들
        JLabel titleLabel = new JLabel();
        titleLabel.setFont(new Font("굴림", Font.PLAIN, 25));
        titleLabel.setBounds(100, 80, 479, 30);
        contentPane.add(titleLabel);

        JTextArea contentTextArea = new JTextArea();
        contentTextArea.setEditable(false);
        JScrollPane contentScrollPane = new JScrollPane(contentTextArea);
        contentScrollPane.setBounds(97, 120, 479, 200);
        contentPane.add(contentScrollPane);

        // 이전 버튼
        JButton btn_back = new JButton("←");
        btn_back.setBounds(10, 10, 50, 50);
        contentPane.add(btn_back);

        // 이전 버튼 클릭 시 창 닫기
        btn_back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // 수정
        JSplitPane splitPane = new JSplitPane();
        splitPane.setBounds(445, 47, 131, 23);
        contentPane.add(splitPane);

        JButton btn_alter = new JButton("수정");
        btn_alter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 수정 기능 구현
            }
        });
        splitPane.setLeftComponent(btn_alter);

        // 삭제
        JButton btn_del = new JButton("삭제");
        splitPane.setRightComponent(btn_del);

        // 삭제 버튼 클릭 시 게시글 삭제
        btn_del.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 확인 다이얼로그 표시
                int option = JOptionPane.showConfirmDialog(null, "게시글을 삭제하시겠습니까?", "게시글 삭제", JOptionPane.YES_NO_OPTION);

                // 확인 버튼을 클릭한 경우에만 게시글 삭제 진행
                if (option == JOptionPane.YES_OPTION) {
                    deleteBoard(selectedBoardId); // 선택된 게시글 삭제
                    dispose(); // 창 닫기
                }
            }
        });

        // commentTable 초기화
        commentTable = new JTable();
        JScrollPane commentScrollPane = new JScrollPane(commentTable);
        commentScrollPane.setBounds(97, 330, 479, 170);
        contentPane.add(commentScrollPane);

        // 입력 패널
        JPanel inputPanel = new JPanel(new BorderLayout());
        JTextField textField = new JTextField();

        JButton registerButton = new JButton("등록");
        registerButton.setBounds(460, 510, 116, 30);
        contentPane.add(registerButton);

        // 등록 버튼 클릭 시 댓글 등록
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String comment = textField.getText();
                if (!comment.isEmpty()) {
                    addComment(selectedBoardId, comment, commentTable); // 댓글 작성자 추가
                    textField.setText(""); // 입력 필드 초기화
                }
            }
        });

        inputPanel.add(textField, BorderLayout.CENTER);
        inputPanel.add(registerButton, BorderLayout.EAST);
        inputPanel.setBounds(97, 510, 479, 50);
        contentPane.add(inputPanel);

        setVisible(true);

        // 선택된 게시글의 정보를 가져와서 표시
        showBoardDetails(selectedBoardId, titleLabel, contentTextArea, commentTable);

        // 게시글의 댓글 목록을 표시
        loadComments(selectedBoardId, commentTable);
    }



    // 선택된 게시글의 상세 정보를 가져와서 표시하는 메서드
    private void showBoardDetails(int boardId, JLabel titleLabel, JTextArea contentTextArea, JTable commentTable) {
        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;

        try {
            // 데이터베이스 연결
            conn =MySqlDBManager.getInstance();

            // 프로시저 호출
            cstmt = conn.prepareCall("{CALL select_board(?)}");
            cstmt.setInt(1, boardId);
            rs = cstmt.executeQuery();

            // 결과가 있을 경우에만 정보 표시
            if (rs.next()) {
                titleLabel.setText(rs.getString("title")); // 제목 표시
                contentTextArea.setText(rs.getString("contents")); // 내용 표시

                // 이미지 표시
                byte[] imageData = rs.getBytes("picture");
                if (imageData != null) {
                    ImageIcon imageIcon = new ImageIcon(imageData);
                    Image image = imageIcon.getImage();
                    Image scaledImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                    imageIcon = new ImageIcon(scaledImage);
                    imageLabel = new JLabel(imageIcon); // 이미지 레이블 전역 변수로 변경
                    imageLabel.setBounds(97, 560, 200, 200);
                    contentPane.add(imageLabel); // contentPane에 이미지 레이블 추가
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

    // 게시글의 댓글을 가져와서 테이블에 표시하는 메서드
    private void loadComments(int boardId, JTable commentTable) {
        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;

        try {
            // 데이터베이스 연결
            conn =MySqlDBManager.getInstance();

            // 프로시저 호출
            cstmt = conn.prepareCall("{CALL select_comments(?)}");
            cstmt.setInt(1, boardId);
            rs = cstmt.executeQuery();

            // 테이블 모델 생성
            DefaultTableModel model = new DefaultTableModel(new String[]{"날짜", "댓글", "작성자"}, 0);

            // 결과셋에서 데이터 읽어와 모델에 추가
            while (rs.next()) {
                String cmt_date = rs.getString("cmt_date");
                String comment = rs.getString("comment");
                String user_id = rs.getString("user_id");
                model.addRow(new Object[]{cmt_date, comment, user_id});
            }

            // 테이블에 모델 설정
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

    // 댓글을 추가하는 메서드
    private void addComment(int boardId, String comment, JTable commentTable) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // 데이터베이스 연결
            conn = MySqlDBManager.getInstance();

            // SQL 쿼리 작성
            String sql = "INSERT INTO comments (board_id, comment) VALUES (?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardId);
            pstmt.setString(2, comment);

            // 쿼리 실행
            pstmt.executeUpdate();


            // 댓글 테이블 갱신
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

    // 게시글 삭제 메서드
    private void deleteBoard(int boardId) {
        Connection conn = null;
        CallableStatement stmt = null;

        try {
            // 데이터베이스 연결
            conn = MySqlDBManager.getInstance();;

            // 프로시저 호출
            stmt = conn.prepareCall("{CALL delete_board(?)}");
            stmt.setInt(1, boardId);
            stmt.executeUpdate(); // 프로시저 실행

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
