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
        setLayout(new BorderLayout()); // 제목열 추가를 위해 보드로 함(각 식단위에 텍스트 줌)

        // 식단표 들어가는 영역
        JPanel main = new JPanel();
        main.setLayout(new GridLayout(7, 4, 5, 5)); // 
        main.setBackground(Color.WHITE); // main 패널의 배경 하얀색

        // 요일
        String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        // 식사시간
        String[] mealTimes = {"Breakfast", "Lunch", "Dinner"};

        // 탄,단,식이섬유 버튼 추가
        for (String day : daysOfWeek) {
            JLabel dayLabel = new JLabel(day);
            dayLabel.setHorizontalAlignment(SwingConstants.CENTER); 
            main.add(dayLabel); 

            for (String mealTime : mealTimes) {
                JPanel mealPanel = new JPanel();
                mealPanel.setLayout(new BorderLayout());
                mealPanel.setBorder(new LineBorder(Color.BLACK));

                JLabel mealLabel = new JLabel(mealTime);
                mealLabel.setHorizontalAlignment(SwingConstants.CENTER); 
                mealPanel.add(mealLabel, BorderLayout.NORTH); 

                JPanel buttonPanel = new JPanel(new GridLayout(3, 1)); 
                buttonPanel.setBackground(Color.WHITE); 
                
                JButton carbButton = new JButton("Carbohydrate");
                JButton proteinButton = new JButton("Protein");
                JButton fiberButton = new JButton("Fiber");
                
                // 버튼 배경도 하얗게
                carbButton.setBackground(Color.WHITE);
                proteinButton.setBackground(Color.WHITE);
                fiberButton.setBackground(Color.WHITE);

                carbButton.addActionListener(this);
                proteinButton.addActionListener(this);
                fiberButton.addActionListener(this);

                buttonPanel.add(carbButton);
                buttonPanel.add(proteinButton);
                buttonPanel.add(fiberButton);

                mealPanel.add(buttonPanel, BorderLayout.CENTER); // 버튼들 중앙배치

                main.add(mealPanel); 
            }
        }
        add(main, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String mealDetails = button.getText(); // 버튼의 텍스트 가져오기
        JOptionPane.showMessageDialog(this, "Details: " + mealDetails); // 추후 음식 이미지+설명이 들어가는 Dialog로 수정!
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Diet gui = new Diet();
                gui.setVisible(true);
            }
        });
    }
}