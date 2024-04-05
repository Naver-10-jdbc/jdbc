package view.week_ex;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Week_Exercise extends JFrame {

    private JPanel contentPane;



    public Week_Exercise() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 1000);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        // ��� �г�
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        contentPane.add(panel, BorderLayout.NORTH);
        panel.setLayout(new GridBagLayout());

        // �ڷΰ��� ��ư
        JButton btn_back = new JButton("��");
        btn_back.setPreferredSize(new Dimension(50, 50));
        btn_back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // �ڷΰ��� ��ư �׼�
            }
        });
        panel.add(btn_back, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));

        // ���� ���̺�
        JLabel lblNewLabel = new JLabel("4�� 1���� �");
        lblNewLabel.setFont(new Font("����", Font.BOLD, 20));
        panel.add(lblNewLabel, new GridBagConstraints(1, 0, 2, 1, 0, 0, GridBagConstraints.WEST,
                GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));

        // ���� �ؽ�Ʈ �г�
        JTextPane textPane = new JTextPane();
        textPane.setFont(new Font("����", Font.PLAIN, 16));
        textPane.setText("� �ҿ�ð� = (1set 1�� �, 1�� �޽�) x 5set");
        panel.add(textPane, new GridBagConstraints(1, 1, 3, 1, 0, 0, GridBagConstraints.WEST,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 5), 0, 0));

        
        // ���� �г�
        JPanel week_exe_main = new JPanel(new GridLayout(7, 1));
        contentPane.add(week_exe_main, BorderLayout.CENTER);

        // ���Ϻ� � ��õ
        // ���� �� � ���� �迭
        String[][] exerciseInfo = {
                {"������", "��ü �", "����Ʈ 3��Ʈ x 10ȸ\n���� 3��Ʈ x 10ȸ"},
                {"ȭ����", "��ü �", "Ǫ�þ� 3��Ʈ x 10ȸ\nǮ�� 3��Ʈ x 10ȸ"},
                {"������", "�ھ� �", "�÷�ũ 3��Ʈ x 30��\n���׷����� 3��Ʈ x 10ȸ"},
                {"�����", "����� �", "���� 30��"},
                {"�ݿ���", "��ü �", "���帮��Ʈ 3��Ʈ x 10ȸ\n���������� 3��Ʈ x 10ȸ"},
                {"�����", "�޽�", "���� ���Դϴ�"},
                {"�Ͽ���", "��Ʈ��Ī", "���� ��Ʈ��Ī 10��"}
        };

     // �� ���Ϻ� � ������ �гο� �߰�
        for (int i = 0; i < exerciseInfo.length; i++) {
            JPanel day_exe = new JPanel();
            day_exe.setLayout(new GridLayout(1, 2)); // 2���� �׸��� ���̾ƿ� ����
            day_exe.setBackground(Color.WHITE);
            day_exe.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // �ܰ��� ���������� ����
            day_exe.setPreferredSize(new Dimension(500, 400)); // �г� ũ�� ����

            // 1���� � ���� �߰�
            JPanel exercisePanel = new JPanel(new GridLayout(3, 1));
            JLabel dayLabel = new JLabel(exerciseInfo[i][0]);
            exercisePanel.add(dayLabel);
            JLabel exerciseLabel = new JLabel(exerciseInfo[i][1]);
            exercisePanel.add(exerciseLabel);
            JTextArea descriptionArea = new JTextArea(exerciseInfo[i][2]);
            descriptionArea.setLineWrap(true); // �ؽ�Ʈ�� ������ ��� ��� �ڵ����� �� �ٲ�
            descriptionArea.setWrapStyleWord(true); // �ܾ� ���� �� �ٲ�
            descriptionArea.setEditable(false); // �ؽ�Ʈ ���� ��Ȱ��ȭ
            exercisePanel.add(descriptionArea);
            day_exe.add(exercisePanel);

            // �� ��° ���� � ������ '��ϱ�' ��ư �߰�
            JPanel buttonPanel = new JPanel(new BorderLayout()); // BorderLayout���� ����
            buttonPanel.add(exercisePanel, BorderLayout.CENTER); // exercisePanel �߰�
            JButton exerciseButton = new JButton("��ϱ�");
            buttonPanel.add(exerciseButton, BorderLayout.EAST); // ��ư�� ���ʿ� �߰�
            day_exe.add(buttonPanel); // buttonPanel�� day_exe�� �߰�

            week_exe_main.add(day_exe);
        }
        setVisible(true);
    }
}
