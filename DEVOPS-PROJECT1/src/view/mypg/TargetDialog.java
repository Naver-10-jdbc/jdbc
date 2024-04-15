package view.mypg;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TargetDialog extends JDialog {
    private JLabel myWishWeight, myWishSkmu, myWishBodyfat, whisCid, exeLevel, sleepTime;

    public TargetDialog(JFrame parentFrame, JLabel wishWeight, JLabel wishSkmu, JLabel wishBodyfat, JLabel cidLabel, JLabel exeLabel, JLabel sleepLabel) {
        super(parentFrame, "목표 업데이트", true);
        myWishWeight = wishWeight;
        myWishSkmu = wishSkmu;
        myWishBodyfat = wishBodyfat;
        whisCid = cidLabel;
        exeLevel = exeLabel;
        sleepTime = sleepLabel;

        setSize(400, 600);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // 체중 입력 패널
        JPanel weightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField weightField = new JTextField(10);
        weightPanel.add(new JLabel("체중 입력       "));
        weightPanel.add(weightField);
        weightPanel.add(new JLabel("kg"));
        addGridBagComponent(panel, weightPanel, 0, 0, GridBagConstraints.WEST);

        // 골격근량 입력 패널
        JPanel skmuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField skmuField = new JTextField(10);
        skmuPanel.add(new JLabel("골격근량 입력"));
        skmuPanel.add(skmuField);
        skmuPanel.add(new JLabel("kg"));
        addGridBagComponent(panel, skmuPanel, 0, 1, GridBagConstraints.WEST);

        // 체지방량 입력 패널
        JPanel bodyfatPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField bodyfatField = new JTextField(10);
        bodyfatPanel.add(new JLabel("체지방량 입력"));
        bodyfatPanel.add(bodyfatField);
        bodyfatPanel.add(new JLabel("kg"));
        addGridBagComponent(panel, bodyfatPanel, 0, 2, GridBagConstraints.WEST);

        // 수면시간 입력 패널
        JPanel sleepPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField sleepField = new JTextField(10);
        sleepPanel.add(new JLabel("수면시간 입력"));
        sleepPanel.add(sleepField);
        sleepPanel.add(new JLabel("시간"));
        addGridBagComponent(panel, sleepPanel, 0, 3, GridBagConstraints.WEST);

        // CID 라디오 버튼
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ButtonGroup cidGroup = new ButtonGroup();
        JRadioButton cidC = new JRadioButton("C");
        JRadioButton cidI = new JRadioButton("I");
        JRadioButton cidD = new JRadioButton("D");
        cidGroup.add(cidC);
        cidGroup.add(cidI);
        cidGroup.add(cidD);
        radioPanel.add(new JLabel("CID 유형 선택"));
        radioPanel.add(cidC);
        radioPanel.add(cidI);
        radioPanel.add(cidD);
        addGridBagComponent(panel, radioPanel, 0, 4, GridBagConstraints.WEST);

        // 운동강도 수정 라디오 버튼
        JPanel exePanel = new JPanel(new GridBagLayout());
        exePanel.setBorder(BorderFactory.createTitledBorder("운동강도 수정"));

        // GridBagConstraints 설정
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST; // 왼쪽 정렬
        gbc.insets = new Insets(5, 5, 5, 5); // 여백 설정

        // 라디오버튼1
        JRadioButton exeHigh = new JRadioButton("난이도 상");
        JLabel exeHighLabel = new JLabel("평소 운동을 많이 하는 당신! 고강도 운동을 해봅시다");
        addGridBagComponent(exePanel, exeHigh, 0, 0, GridBagConstraints.WEST);
        addGridBagComponent(exePanel, exeHighLabel, 0, 1, GridBagConstraints.WEST);

        // 라디오버튼2
        JRadioButton exeMid = new JRadioButton("난이도 중");
        JLabel exeMidLabel = new JLabel("적당히 운동을 하고 사는 당신. 꾸준한 운동을 합시다");
        addGridBagComponent(exePanel, exeMid, 0, 2, GridBagConstraints.WEST);
        addGridBagComponent(exePanel, exeMidLabel, 0, 3, GridBagConstraints.WEST);

        // 라디오버튼3
        JRadioButton exeLow = new JRadioButton("난이도 하");
        JLabel exeLowLabel = new JLabel("평소 운동을 안하는 당신. 다치지 않게 운동을 합시다");
        addGridBagComponent(exePanel, exeLow, 0, 4, GridBagConstraints.WEST);
        addGridBagComponent(exePanel, exeLowLabel, 0, 5, GridBagConstraints.WEST);

        ButtonGroup exeGroup = new ButtonGroup();
        exeGroup.add(exeHigh);
        exeGroup.add(exeMid);
        exeGroup.add(exeLow);

        addGridBagComponent(panel, exePanel, 0, 5, GridBagConstraints.WEST); // 운동강도 수정 패널 추가

        // 확인 버튼
        JButton confirmButton = new JButton("확인");
        confirmButton.addActionListener(new ConfirmButtonListener(weightField, skmuField, bodyfatField, sleepField, cidC, cidI, cidD, exeHigh, exeMid, exeLow));

        // 취소 버튼
        JButton cancelButton = new JButton("취소");
        cancelButton.addActionListener(new CancelButtonListener());

        // 확인, 취소 버튼 패널
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        // 패널 추가
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

    }

    private void addGridBagComponent(JPanel panel, Component component, int gridx, int gridy, int anchor) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.anchor = anchor;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.add(component, gbc);
    }

    private class ConfirmButtonListener implements ActionListener {
        private JTextField weightField, skmuField, bodyfatField, sleepField;
        private JRadioButton cidC, cidI, cidD, exeHigh, exeMid, exeLow;

        public ConfirmButtonListener(JTextField weightField, JTextField skmuField, JTextField bodyfatField, JTextField sleepField,
                JRadioButton cidC, JRadioButton cidI, JRadioButton cidD, JRadioButton exeHigh, JRadioButton exeMid, JRadioButton exeLow) {
            this.weightField = weightField;
            this.skmuField = skmuField;
            this.bodyfatField = bodyfatField;
            this.sleepField = sleepField;
            this.cidC = cidC;
            this.cidI = cidI;
            this.cidD = cidD;
            this.exeHigh = exeHigh;
            this.exeMid = exeMid;
            this.exeLow = exeLow;
        }

        public void actionPerformed(ActionEvent e) {
            // 입력값 확인
            if (weightField.getText().isEmpty() || !isNumeric(weightField.getText()) ||
                skmuField.getText().isEmpty() || !isNumeric(skmuField.getText()) ||
                bodyfatField.getText().isEmpty() || !isNumeric(bodyfatField.getText()) ||
                sleepField.getText().isEmpty() || !isNumeric(sleepField.getText()) ||
                (!cidC.isSelected() && !cidI.isSelected() && !cidD.isSelected()) ||
                (!exeHigh.isSelected() && !exeMid.isSelected() && !exeLow.isSelected())) {
                JOptionPane.showMessageDialog(TargetDialog.this, "입력값을 다시 확인하여 모두 입력해주세요", "경고", JOptionPane.WARNING_MESSAGE);
            } else {
                // 입력값 처리
                myWishWeight.setText(weightField.getText());
                myWishSkmu.setText(skmuField.getText());
                myWishBodyfat.setText(bodyfatField.getText());
                if (cidC.isSelected()) {
                    whisCid.setText("C");
                } else if (cidI.isSelected()) {
                    whisCid.setText("I");
                } else {
                    whisCid.setText("D");
                }
                if (exeHigh.isSelected()) {
                    exeLevel.setText("상");
                } else if (exeMid.isSelected()) {
                    exeLevel.setText("중");
                } else {
                    exeLevel.setText("하");
                }
                sleepTime.setText(sleepField.getText());
                dispose(); // 다이얼로그 창 닫기
            }
        }
    }

    private class CancelButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            dispose(); // 다이얼로그 창 닫기
        }
    }

    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
   
}

