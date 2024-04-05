package view.diet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;

public class Diet extends JFrame implements ActionListener {
    public Diet() {
        setTitle("Meal Planner");
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout()); // ���� �߰��� ���� ����� ��(�� �Ĵ����� �ؽ�Ʈ ��)

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
                        }
                    }
                }
            }
        } else {
            // Ŭ���� ��ư�� ź��ȭ��, �ܹ���, ���̼��� ��ư�� ���
            String mealDetails = button.getText(); // ��ư�� �ؽ�Ʈ ��������
            JOptionPane.showMessageDialog(this, "Details: " + mealDetails); // ���� ���� �̹���+������ ���� Dialog�� ����!
        }
    }
}
