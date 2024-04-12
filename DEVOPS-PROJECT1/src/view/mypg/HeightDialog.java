package view.mypg;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HeightDialog extends JDialog {
    private JButton myHeight;
    private JLabel myBMI;

	public HeightDialog(MyPage parentFrame, JButton heightButton, JLabel bmiLabel) {
		super(parentFrame, "Ű ���� �Է�", true);
        myHeight = heightButton;
        myBMI = bmiLabel;

        setSize(200, 100);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(0, 2));

        JLabel heightLabel = new JLabel("Ű(cm):");
        JTextField heightField = new JTextField();
        panel.add(heightLabel);
        panel.add(heightField);

        JButton okButton = new JButton("Ȯ��");
        okButton.addActionListener(new OkButtonListener(heightField));

        JButton cancelButton = new JButton("���");
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
                JOptionPane.showMessageDialog(HeightDialog.this, "�ùٸ� ���ڸ� �Է����ּ���.", "����", JOptionPane.ERROR_MESSAGE);
            } else {
                myHeight.setText(input);
                myBMI.setText(updateBMI());
                dispose();
            }
        }
        
     // BMI ������Ʈ �޼ҵ� ����
        private String updateBMI() {
        	MyPage myPage = new MyPage();
            // ����� Ű ��������
            String weight = myPage.getmyWeightText(); // MyPage Ŭ������ �ν��Ͻ��� ���� ����Ͽ� Ű ������ ������
            double myWeightNum = Double.parseDouble(weight);
            String myHeightText = textField.getText(); // ���⼭ ����� Ű ���� ������
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