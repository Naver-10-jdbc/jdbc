package view.diet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;

public class Diet extends JFrame implements ActionListener {
    public Diet() {
        setTitle("�ְ��Ĵ�");
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout()); // ���� �߰��� ���� ����� ��(�� �Ĵ����� �ؽ�Ʈ ��)

        // ȭ�� �߾ӹ�ġ
        setLocationRelativeTo(null);
        
     // �г� for the NORTH position
        JPanel head = new JPanel(new BorderLayout()); // head �гο� BorderLayout ���
        head.setBackground(Color.WHITE);

        // WEST ��ġ�� btn_back ��ư �߰�
        JButton btn_back = new JButton("��");
        btn_back.setPreferredSize(new Dimension(50, 50));
        btn_back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // �ڷΰ��� ��ư �׼�(â�ݱ�)
            }
        });
        head.add(btn_back, BorderLayout.WEST); // ��ư�� WEST ��ġ�� �߰�

        // ����� titleLabel�� �߰�
        JLabel titleLabel = new JLabel("�ְ��Ĵ�");
        titleLabel.setFont(new Font("����", Font.BOLD, 20));

        // ��� �г��� �����Ͽ� titleLabel�� �߰��Ͽ� �߾ӿ� ��ġ�ϵ��� ��
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.add(titleLabel);

        // head �г��� ����� centerPanel�� �߰�
        head.add(centerPanel, BorderLayout.CENTER);

        // head �г��� �����ӿ� �߰�
        add(head, BorderLayout.NORTH);

        // �Ĵ�ǥ ���� ����
        JPanel main = new JPanel();
        main.setLayout(new GridLayout(7, 4, 5, 5)); // 
        main.setBackground(Color.WHITE); // main �г��� ��� �Ͼ��

        // ����
        String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        // �Ļ�ð�
        String[] mealTimes = {"Breakfast", "Lunch", "Dinner"};

        // ź,��,���̼��� ��ư �߰�
        for (String day : daysOfWeek) {
            JLabel dayLabel = new JLabel(day);
            dayLabel.setHorizontalAlignment(SwingConstants.CENTER); 
            main.add(dayLabel); 

            for (String mealTime : mealTimes) {
                JPanel mealPanel = new JPanel();
                mealPanel.setLayout(new BorderLayout());
                mealPanel.setBorder(new LineBorder(Color.BLACK));

                JButton mealLabel = new JButton(mealTime);
                mealLabel.setHorizontalAlignment(SwingConstants.CENTER); 
                mealLabel.setBackground(Color.orange); // ���� �Ͼ������ ����
                mealLabel.addActionListener(this); // ActionListener �߰�
                mealPanel.add(mealLabel, BorderLayout.NORTH); 

                JPanel buttonPanel = new JPanel(new GridLayout(3, 1)); 
                buttonPanel.setBackground(Color.WHITE); 

                JButton carbButton = new JButton("Carbohydrate");
                JButton proteinButton = new JButton("Protein");
                JButton fiberButton = new JButton("Fiber");

                // ��ư ��浵 �Ͼ��
                carbButton.setBackground(Color.WHITE);
                proteinButton.setBackground(Color.WHITE);
                fiberButton.setBackground(Color.WHITE);

                carbButton.addActionListener(this);
                proteinButton.addActionListener(this);
                fiberButton.addActionListener(this);

                buttonPanel.add(carbButton);
                buttonPanel.add(proteinButton);
                buttonPanel.add(fiberButton);

                mealPanel.add(buttonPanel, BorderLayout.CENTER); // ��ư�� �߾ӹ�ġ

                main.add(mealPanel); 
            }
        }
        add(main, BorderLayout.CENTER);
        setVisible(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String buttonText = button.getText();

     // Ŭ���� ��ư�� meal time ��ư�� ���
        if (buttonText.equals("Breakfast") || buttonText.equals("Lunch") || buttonText.equals("Dinner")) {
            // ��ư�� ���� ������ �ش� �г��� ��ư Ȱ��ȭ ���� ����
            JPanel mealPanel = (JPanel) button.getParent();
            for (Component component : mealPanel.getComponents()) {
                if (component instanceof JPanel) {
                    JPanel buttonPanel = (JPanel) component;
                    for (Component btn : buttonPanel.getComponents()) {
                        if (btn instanceof JButton) {
                            JButton mealButton = (JButton) btn;
                            mealButton.setEnabled(!mealButton.isEnabled());
                            // ��ư�� ��Ȱ��ȭ ������ ��� ������ ȸ������ ����
                            if (!mealButton.isEnabled()) {
                                mealButton.setBackground(Color.LIGHT_GRAY);
                            } else {
                                mealButton.setBackground(Color.WHITE); // Ȱ��ȭ�� ��ư�� ������ �ٽ� �Ͼ������ ����
                            }
                        }
                    }
                }
            }
        }
     // Ŭ���� ��ư�� ź��ȭ��, �ܹ���, ���̼��� ��ư�� ���
        else {
            String mealDetails = button.getText(); // ��ư�� �ؽ�Ʈ ��������
            // �Ʒ� ��δ� ���� �̹��� ������ ��η� �����ؾ� �մϴ�.
            ImageIcon icon = new ImageIcon("sweetpotato.png"); // �̹��� ������ ����
            JLabel imageLabel = new JLabel(icon); // �̹��� ���̺� ����
            JLabel detailsLabel = new JLabel(mealDetails); // �� ���� ���̺� ����
            JLabel textLabel = new JLabel("�� ���Ŀ� ���� ������ ���� �����Դϴ�"); // ���� ���̺� ����
            JPanel panel = new JPanel(new BorderLayout()); // �гο� BorderLayout ����

            // �̹���, �� ����, ���� ���̺��� �гο� �߰�
            panel.add(imageLabel, BorderLayout.NORTH);
            panel.add(detailsLabel, BorderLayout.CENTER);
            panel.add(textLabel, BorderLayout.SOUTH);

            JOptionPane.showMessageDialog(this, panel, "Meal Details", JOptionPane.PLAIN_MESSAGE); // �޽��� ���̾�α׷� ����
        }
    }
}
