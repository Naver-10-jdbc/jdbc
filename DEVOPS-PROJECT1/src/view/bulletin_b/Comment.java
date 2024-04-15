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
        contentScrollPane.setBounds(97, 120, 260, 200);
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

        // commentTable 초기화
        commentTable = new JTable(){
        	@Override
            public boolean isCellEditable(int row, int column) {
                // 모든 셀을 편집할 수 없도록 비활성화
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
                        JMenuItem deleteMenuItem = new JMenuItem("삭제");
                        deleteMenuItem.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int selectedRow = commentTable.getSelectedRow();
                                if (selectedRow >= 0 && selectedRow < commentTable.getRowCount()) {
                                    String fullComment = (String) commentTable.getValueAt(selectedRow, 0);
                                    String[] commentParts = fullComment.split(": ");
                                    String userId = commentParts[0].substring(commentParts[0].indexOf("]") + 2); // 댓글 작성자의 ID 가져오기
                                    String comment = commentParts[1];
                                    String loggedInUserId = Session.getInstance().getUserId(); // 현재 로그인한 사용자의 ID 가져오기

                                    // 현재 로그인한 사용자의 ID와 댓글 작성자의 ID를 비교하여 동일한 경우에만 삭제 허용
                                    if (loggedInUserId.equals(userId)) {
                                        int option = JOptionPane.showConfirmDialog(null, "댓글을 삭제하시겠습니까?", "댓글 삭제", JOptionPane.YES_NO_OPTION);
                                        if (option == JOptionPane.YES_OPTION) {
                                            deleteComment(selectedRow, selectedBoardId, commentTable);
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "다른 사용자의 댓글은 삭제할 수 없습니다.", "권한 없음", JOptionPane.ERROR_MESSAGE);
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

    
        // 입력 패널
        JPanel inputPanel = new JPanel(new BorderLayout());
        JTextField textField = new JTextField();

        JButton registerButton = new JButton("등록");
        inputPanel.add(textField, BorderLayout.CENTER);
        inputPanel.add(registerButton, BorderLayout.EAST);
        inputPanel.setBounds(97, 530, 479, 50);
        contentPane.add(inputPanel);

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

        

        setVisible(true);

        // 선택된 게시글의 정보를 가져와서 표시
        showBoardDetails(selectedBoardId, titleLabel, contentTextArea, commentTable);

        // 게시글의 댓글 목록을 표시
        loadComments(selectedBoardId, commentTable);
        
                // 삭제
                JButton btn_del = new JButton("삭제");
                btn_del.setBounds(502, 49, 77, 21);
                contentPane.add(btn_del);
                
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
                    imageLabel.setBounds(350, 120, 200, 200);
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
 // 게시글의 댓글을 가져와서 테이블에 표시하는 메서드
    private void loadComments(int boardId, JTable commentTable) {
        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;

        try {
            // 데이터베이스 연결
            conn = MySqlDBManager.getInstance();

            // 프로시저 호출
            cstmt = conn.prepareCall("{CALL select_comments(?)}");
            cstmt.setInt(1, boardId);
            rs = cstmt.executeQuery();

         // 테이블 모델 생성
            DefaultTableModel model = new DefaultTableModel(new String[]{"댓글"}, 0);

            // 결과셋에서 데이터 읽어와 모델에 추가
            while (rs.next()) {
                String cmt_date = rs.getString("cmt_date");
                String comment = rs.getString("comment");
                String user_id = rs.getString("user_id");
                // 한 행에 날짜, 작성자, 댓글 추가
                String fullComment = "[" + cmt_date + "] " + user_id + ": " + comment;
                model.addRow(new Object[]{fullComment});
            }


            // 테이블에 모델 설정
            commentTable.setModel(model);

            // 행의 높이 조정하여 한 행에 모든 데이터가 보이도록 설정
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

 // 댓글을 추가하는 메서드
    private void addComment(int boardId, String comment, JTable commentTable) {
        Connection conn = null;
        
        PreparedStatement pstmt = null;

        try {
            // 데이터베이스 연결
            conn = MySqlDBManager.getInstance();

            // 현재 로그인된 사용자의 ID 가져오기
            String userId = Session.getInstance().getUserId(); // 이 부분은 세션 클래스나 로그인 관련 클래스에 따라 다를 수 있습니다.

            // SQL 쿼리 작성
            String sql = "INSERT INTO comment (board_id, comment, user_id) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardId);
            pstmt.setString(2, comment);
            pstmt.setString(3, userId); // 사용자 ID 추가

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
        PreparedStatement pstmtDeleteComments = null;
        PreparedStatement pstmtDeleteBoard = null;

        try {
            // 데이터베이스 연결
            conn = MySqlDBManager.getInstance();

            // 게시글 작성자의 ID를 가져오는 SQL 작성
            String sqlGetAuthorId = "SELECT user_id FROM board WHERE board_id = ?";
            PreparedStatement pstmtGetAuthorId = conn.prepareStatement(sqlGetAuthorId);
            pstmtGetAuthorId.setInt(1, boardId);
            ResultSet rs = pstmtGetAuthorId.executeQuery();

            // 게시글 작성자의 ID 가져오기
            String authorId = null;
            if (rs.next()) {
                authorId = rs.getString("user_id");
            }

            // 현재 로그인된 사용자의 ID 가져오기
            String loggedInUserId = Session.getInstance().getUserId(); // 이 부분은 세션 클래스나 로그인 관련 클래스에 따라 다를 수 있습니다.

            // 게시글 작성자의 ID와 로그인한 사용자의 ID가 일치하는 경우에만 삭제 진행
            if (authorId != null && authorId.equals(loggedInUserId)) {
                // 댓글 삭제 SQL 작성
                String sqlDeleteComments = "DELETE FROM comment WHERE board_id = ?";
                pstmtDeleteComments = conn.prepareStatement(sqlDeleteComments);
                pstmtDeleteComments.setInt(1, boardId);

                // 댓글 삭제 실행
                pstmtDeleteComments.executeUpdate();

                // 게시글 삭제 SQL 작성
                String sqlDeleteBoard = "DELETE FROM board WHERE board_id = ?";
                pstmtDeleteBoard = conn.prepareStatement(sqlDeleteBoard);
                pstmtDeleteBoard.setInt(1, boardId);

                // 게시글 삭제 실행
                pstmtDeleteBoard.executeUpdate();
                showBoardDetails(boardId, imageLabel, null, commentTable);
                Board.loadBoardData();
            } else {
                // 작성자의 ID와 로그인한 사용자의 ID가 일치하지 않는 경우에는 삭제를 거부
                JOptionPane.showMessageDialog(null, "게시글 작성자만 삭제할 수 있습니다.", "권한 없음", JOptionPane.ERROR_MESSAGE);
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
            // 데이터베이스 연결
            conn = MySqlDBManager.getInstance();

            // 선택된 행에서 댓글 데이터 가져오기
            String fullComment = (String) commentTable.getValueAt(row, 0);
            String[] commentParts = fullComment.split(": ");
            // 변경된 부분: 작성자 ID와 댓글 내용을 분리할 때 ":"를 기준으로 분리하여야 함
            String userId = commentParts[0].substring(commentParts[0].indexOf("]") + 2); // 댓글 작성자의 ID 가져오기
            String comment = commentParts[1]; // 댓글 내용 가져오기

            // SQL 쿼리 작성
            // 변경된 부분: 댓글 내용과 작성자 ID를 모두 확인하여 삭제하도록 쿼리 수정
            String sql = "DELETE FROM comment WHERE comment = ? AND user_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, comment);
            pstmt.setString(2, userId);

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

}