package view.mypg;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HeightDialog extends JDialog {
    private JButton myHeight;
    private JLabel myBMI;

	public HeightDialog(MyPage parentFrame, JButton heightButton, JLabel bmiLabel) {
		super(parentFrame, "키 정보 입력", true);
        myHeight = heightButton;
        myBMI = bmiLabel;

        setSize(200, 100);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(0, 2));

        JLabel heightLabel = new JLabel("키(cm):");
        JTextField heightField = new JTextField();
        panel.add(heightLabel);
        panel.add(heightField);

        JButton okButton = new JButton("확인");
        okButton.addActionListener(new OkButtonListener(heightField));

        JButton cancelButton = new JButton("취소");
        cancelButton.addActionListener(new CancelButtonListener());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
	}



	private class OkButtonListener implements ActionListener {
        private JTextField textField;

        public OkButtonListener(JTextField textField) {
            this.textField = textField;
        }

        public void actionPerformed(ActionEvent e) {
            String input = textField.getText();
            if (input.isEmpty() || !isNumeric(input)) {
                JOptionPane.showMessageDialog(HeightDialog.this, "올바른 숫자를 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
            } else {
                myHeight.setText(input);
                myBMI.setText(updateBMI());
                dispose();
            }
        }
        
     // BMI 업데이트 메소드 정의
        private String updateBMI() {
        	MyPage myPage = new MyPage();
            // 사용자 키 가져오기
            String weight = myPage.getmyWeightText(); // MyPage 클래스의 인스턴스를 직접 사용하여 키 정보를 가져옴
            double myWeightNum = Double.parseDouble(weight);
            String myHeightText = textField.getText(); // 여기서 변경된 키 값을 가져옴
            double myHeightNum = Double.parseDouble(myHeightText);
            String MyBMICal = String.format("%.1f", myWeightNum / ((myHeightNum / 100) * (myHeightNum / 100)));
            return MyBMICal;
        }
    }

    private class CancelButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }

    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}