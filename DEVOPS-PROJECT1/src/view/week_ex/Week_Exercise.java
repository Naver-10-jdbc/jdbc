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

        // 상단 패널
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        contentPane.add(panel, BorderLayout.NORTH);
        panel.setLayout(new GridBagLayout());

        // 뒤로가기 버튼
        JButton btn_back = new JButton("←");
        btn_back.setPreferredSize(new Dimension(50, 50));
        btn_back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 뒤로가기 버튼 액션
            }
        });
        panel.add(btn_back, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));

        // 제목 레이블
        JLabel lblNewLabel = new JLabel("4월 1주차 운동");
        lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));
        panel.add(lblNewLabel, new GridBagConstraints(1, 0, 2, 1, 0, 0, GridBagConstraints.WEST,
                GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));

        // 설명 텍스트 패널
        JTextPane textPane = new JTextPane();
        textPane.setFont(new Font("굴림", Font.PLAIN, 16));
        textPane.setText("운동 소요시간 = (1set 1분 운동, 1분 휴식) x 5set");
        panel.add(textPane, new GridBagConstraints(1, 1, 3, 1, 0, 0, GridBagConstraints.WEST,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 5), 0, 0));

        
        // 메인 패널
        JPanel week_exe_main = new JPanel(new GridLayout(7, 1));
        contentPane.add(week_exe_main, BorderLayout.CENTER);

        // 요일별 운동 추천
        // 요일 및 운동 정보 배열
        String[][] exerciseInfo = {
                {"월요일", "하체 운동", "스쿼트 3세트 x 10회\n런지 3세트 x 10회"},
                {"화요일", "상체 운동", "푸시업 3세트 x 10회\n풀업 3세트 x 10회"},
                {"수요일", "코어 운동", "플랭크 3세트 x 30초\n레그레이즈 3세트 x 10회"},
                {"목요일", "유산소 운동", "조깅 30분"},
                {"금요일", "하체 운동", "데드리프트 3세트 x 10회\n레그프레스 3세트 x 10회"},
                {"토요일", "휴식", "쉬는 날입니다"},
                {"일요일", "스트레칭", "전신 스트레칭 10분"}
        };

     // 각 요일별 운동 정보를 패널에 추가
        for (int i = 0; i < exerciseInfo.length; i++) {
            JPanel day_exe = new JPanel();
            day_exe.setLayout(new GridLayout(1, 2)); // 2열의 그리드 레이아웃 설정
            day_exe.setBackground(Color.WHITE);
            day_exe.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // 외곽선 검은색으로 설정
            day_exe.setPreferredSize(new Dimension(500, 400)); // 패널 크기 지정

            // 1열에 운동 정보 추가
            JPanel exercisePanel = new JPanel(new GridLayout(3, 1));
            JLabel dayLabel = new JLabel(exerciseInfo[i][0]);
            exercisePanel.add(dayLabel);
            JLabel exerciseLabel = new JLabel(exerciseInfo[i][1]);
            exercisePanel.add(exerciseLabel);
            JTextArea descriptionArea = new JTextArea(exerciseInfo[i][2]);
            descriptionArea.setLineWrap(true); // 텍스트가 영역을 벗어날 경우 자동으로 줄 바꿈
            descriptionArea.setWrapStyleWord(true); // 단어 단위 줄 바꿈
            descriptionArea.setEditable(false); // 텍스트 편집 비활성화
            exercisePanel.add(descriptionArea);
            day_exe.add(exercisePanel);

            // 두 번째 열에 운동 정보와 '운동하기' 버튼 추가
            JPanel buttonPanel = new JPanel(new BorderLayout()); // BorderLayout으로 변경
            buttonPanel.add(exercisePanel, BorderLayout.CENTER); // exercisePanel 추가
            JButton exerciseButton = new JButton("운동하기");
            buttonPanel.add(exerciseButton, BorderLayout.EAST); // 버튼을 동쪽에 추가
            day_exe.add(buttonPanel); // buttonPanel을 day_exe에 추가

            week_exe_main.add(day_exe);
        }
        setVisible(true);
    }
}
