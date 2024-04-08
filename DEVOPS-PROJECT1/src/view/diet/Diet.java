package view.diet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;

public class Diet extends JFrame implements ActionListener {
    public Diet() {
        setTitle("주간식단");
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout()); // 제목열 추가를 위해 보드로 함(각 식단위에 텍스트 줌)

        // 화면 중앙배치
        setLocationRelativeTo(null);
        
     // 패널 for the NORTH position
        JPanel head = new JPanel(new BorderLayout()); // head 패널에 BorderLayout 사용
        head.setBackground(Color.WHITE);

        // WEST 위치에 btn_back 버튼 추가
        JButton btn_back = new JButton("←");
        btn_back.setPreferredSize(new Dimension(50, 50));
        btn_back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // 뒤로가기 버튼 액션(창닫기)
            }
        });
        head.add(btn_back, BorderLayout.WEST); // 버튼을 WEST 위치에 추가

        // 가운데에 titleLabel을 추가
        JLabel titleLabel = new JLabel("주간식단");
        titleLabel.setFont(new Font("굴림", Font.BOLD, 20));

        // 가운데 패널을 생성하여 titleLabel을 추가하여 중앙에 위치하도록 함
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.add(titleLabel);

        // head 패널의 가운데에 centerPanel을 추가
        head.add(centerPanel, BorderLayout.CENTER);

        // head 패널을 프레임에 추가
        add(head, BorderLayout.NORTH);

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

                JButton mealLabel = new JButton(mealTime);
                mealLabel.setHorizontalAlignment(SwingConstants.CENTER); 
                mealLabel.setBackground(Color.orange); // 배경색 하얀색으로 설정
                mealLabel.addActionListener(this); // ActionListener 추가
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
        setVisible(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String buttonText = button.getText();

     // 클릭된 버튼이 meal time 버튼인 경우
        if (buttonText.equals("Breakfast") || buttonText.equals("Lunch") || buttonText.equals("Dinner")) {
            // 버튼을 누를 때마다 해당 패널의 버튼 활성화 상태 변경
            JPanel mealPanel = (JPanel) button.getParent();
            for (Component component : mealPanel.getComponents()) {
                if (component instanceof JPanel) {
                    JPanel buttonPanel = (JPanel) component;
                    for (Component btn : buttonPanel.getComponents()) {
                        if (btn instanceof JButton) {
                            JButton mealButton = (JButton) btn;
                            mealButton.setEnabled(!mealButton.isEnabled());
                            // 버튼이 비활성화 상태인 경우 배경색을 회색으로 변경
                            if (!mealButton.isEnabled()) {
                                mealButton.setBackground(Color.LIGHT_GRAY);
                            } else {
                                mealButton.setBackground(Color.WHITE); // 활성화된 버튼의 배경색은 다시 하얀색으로 변경
                            }
                        }
                    }
                }
            }
        }
     // 클릭된 버튼이 탄수화물, 단백질, 식이섬유 버튼인 경우
        else {
            String mealDetails = button.getText(); // 버튼의 텍스트 가져오기
            // 아래 경로는 실제 이미지 파일의 경로로 수정해야 합니다.
            ImageIcon icon = new ImageIcon("sweetpotato.png"); // 이미지 아이콘 생성
            JLabel imageLabel = new JLabel(icon); // 이미지 레이블 생성
            JLabel detailsLabel = new JLabel(mealDetails); // 상세 정보 레이블 생성
            JLabel textLabel = new JLabel("이 음식에 대한 설명이 들어가는 영역입니다"); // 설명 레이블 생성
            JPanel panel = new JPanel(new BorderLayout()); // 패널에 BorderLayout 설정

            // 이미지, 상세 정보, 설명 레이블을 패널에 추가
            panel.add(imageLabel, BorderLayout.NORTH);
            panel.add(detailsLabel, BorderLayout.CENTER);
            panel.add(textLabel, BorderLayout.SOUTH);

            JOptionPane.showMessageDialog(this, panel, "Meal Details", JOptionPane.PLAIN_MESSAGE); // 메시지 다이얼로그로 띄우기
        }
    }
}
