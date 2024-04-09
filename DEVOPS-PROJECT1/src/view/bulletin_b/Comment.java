package view.bulletin_b;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Comment extends JFrame {

    private JPanel contentPane;

    public Comment() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 1300);
        setLocationRelativeTo(null);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(97, 80, 479, 232);
        contentPane.add(scrollPane_1);

        JLabel push_title = new JLabel("오늘의 운동");//작성자가 쓴 글 제목 받아오기mmm
        push_title.setFont(new Font("굴림", Font.PLAIN, 25));
        scrollPane_1.setColumnHeaderView(push_title);

        JTextArea push_write = new JTextArea();
        scrollPane_1.setViewportView(push_write);
        push_write.setEditable(false); // 편집 불가능하게 설정

        JButton btn_back = new JButton("←");
        btn_back.setBounds(10, 10, 50, 50);
        contentPane.add(btn_back);

        btn_back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JButton btn_img = new JButton("사진 등록");
        btn_img.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        // 이미지를 읽어옴
                        Image img = ImageIO.read(selectedFile);
                        // 이미지의 비율을 유지하면서 버튼 크기에 맞게 조절
                        Image scaledImg = img.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
                        // 이미지를 표시할 JLabel 생성
                        JLabel label = new JLabel(new ImageIcon(scaledImg));
                        // JLabel을 포함하는 패널 생성
                        JPanel panel = new JPanel();
                        panel.add(label);
                        // 팝업 창으로 이미지 표시
                        JOptionPane.showMessageDialog(null, panel, "이미지", JOptionPane.PLAIN_MESSAGE);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
     
        btn_img.setBounds(477, 322, 99, 23);
        contentPane.add(btn_img);

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
 
        
        
        JButton btn_del = new JButton("삭제");
        splitPane.setRightComponent(btn_del);

        JScrollPane push_comment = new JScrollPane();
        push_comment.setBounds(97, 368, 479, 177);
        contentPane.add(push_comment);

        JTextArea textArea = new JTextArea();
        push_comment.setViewportView(textArea);
        textArea.setEditable(false);

        // 입력 패널
        JPanel inputPanel = new JPanel(new BorderLayout());
        JTextField textField = new JTextField();
        JButton registerButton = new JButton("등록");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String comment = textField.getText();
                if (!comment.isEmpty()) {
                    addComment(comment, textArea);
                    textField.setText(""); // 입력 필드 초기화
                }
            }
        });
        inputPanel.add(textField, BorderLayout.CENTER);
        inputPanel.add(registerButton, BorderLayout.EAST);
        inputPanel.setBounds(97, 555, 479, 50);
        contentPane.add(inputPanel);

        setVisible(true);
    }

    private void addComment(String comment, JTextArea textArea) {
        textArea.append(comment + "\n"); // 댓글 추가
    }
}