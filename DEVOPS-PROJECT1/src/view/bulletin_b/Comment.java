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

        JLabel push_title = new JLabel("������ �");//�ۼ��ڰ� �� �� ���� �޾ƿ���mmm
        push_title.setFont(new Font("����", Font.PLAIN, 25));
        scrollPane_1.setColumnHeaderView(push_title);

        JTextArea push_write = new JTextArea();
        scrollPane_1.setViewportView(push_write);
        push_write.setEditable(false); // ���� �Ұ����ϰ� ����

        JButton btn_back = new JButton("��");
        btn_back.setBounds(10, 10, 50, 50);
        contentPane.add(btn_back);

        btn_back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JButton btn_img = new JButton("���� ���");
        btn_img.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        // �̹����� �о��
                        Image img = ImageIO.read(selectedFile);
                        // �̹����� ������ �����ϸ鼭 ��ư ũ�⿡ �°� ����
                        Image scaledImg = img.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
                        // �̹����� ǥ���� JLabel ����
                        JLabel label = new JLabel(new ImageIcon(scaledImg));
                        // JLabel�� �����ϴ� �г� ����
                        JPanel panel = new JPanel();
                        panel.add(label);
                        // �˾� â���� �̹��� ǥ��
                        JOptionPane.showMessageDialog(null, panel, "�̹���", JOptionPane.PLAIN_MESSAGE);
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

        JButton btn_alter = new JButton("����");
        btn_alter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // ���� ��� ����
            }
        });
        splitPane.setLeftComponent(btn_alter);
 
        
        
        JButton btn_del = new JButton("����");
        splitPane.setRightComponent(btn_del);

        JScrollPane push_comment = new JScrollPane();
        push_comment.setBounds(97, 368, 479, 177);
        contentPane.add(push_comment);

        JTextArea textArea = new JTextArea();
        push_comment.setViewportView(textArea);
        textArea.setEditable(false);

        // �Է� �г�
        JPanel inputPanel = new JPanel(new BorderLayout());
        JTextField textField = new JTextField();
        JButton registerButton = new JButton("���");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String comment = textField.getText();
                if (!comment.isEmpty()) {
                    addComment(comment, textArea);
                    textField.setText(""); // �Է� �ʵ� �ʱ�ȭ
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
        textArea.append(comment + "\n"); // ��� �߰�
    }
}