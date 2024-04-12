package view.mypg;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TargetDialog extends JDialog {
    private JLabel myWishWeight, myWishSkmu, myWishBodyfat, whisCid, exeLevel, sleepTime;

    public TargetDialog(JFrame parentFrame, JLabel wishWeight, JLabel wishSkmu, JLabel wishBodyfat, JLabel cidLabel, JLabel exeLabel, JLabel sleepLabel) {
        super(parentFrame, "��ǥ ������Ʈ", true);
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

        // ü�� �Է� �г�
        JPanel weightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField weightField = new JTextField(10);
        weightPanel.add(new JLabel("ü�� �Է�       "));
        weightPanel.add(weightField);
        weightPanel.add(new JLabel("kg"));
        addGridBagComponent(panel, weightPanel, 0, 0, GridBagConstraints.WEST);

        // ��ݱٷ� �Է� �г�
        JPanel skmuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField skmuField = new JTextField(10);
        skmuPanel.add(new JLabel("��ݱٷ� �Է�"));
        skmuPanel.add(skmuField);
        skmuPanel.add(new JLabel("kg"));
        addGridBagComponent(panel, skmuPanel, 0, 1, GridBagConstraints.WEST);

        // ü���淮 �Է� �г�
        JPanel bodyfatPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField bodyfatField = new JTextField(10);
        bodyfatPanel.add(new JLabel("ü���淮 �Է�"));
        bodyfatPanel.add(bodyfatField);
        bodyfatPanel.add(new JLabel("kg"));
        addGridBagComponent(panel, bodyfatPanel, 0, 2, GridBagConstraints.WEST);

        // ����ð� �Է� �г�
        JPanel sleepPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField sleepField = new JTextField(10);
        sleepPanel.add(new JLabel("����ð� �Է�"));
        sleepPanel.add(sleepField);
        sleepPanel.add(new JLabel("�ð�"));
        addGridBagComponent(panel, sleepPanel, 0, 3, GridBagConstraints.WEST);

        // CID ���� ��ư
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ButtonGroup cidGroup = new ButtonGroup();
        JRadioButton cidC = new JRadioButton("C");
        JRadioButton cidI = new JRadioButton("I");
        JRadioButton cidD = new JRadioButton("D");
        cidGroup.add(cidC);
        cidGroup.add(cidI);
        cidGroup.add(cidD);
        radioPanel.add(new JLabel("CID ���� ����"));
        radioPanel.add(cidC);
        radioPanel.add(cidI);
        radioPanel.add(cidD);
        addGridBagComponent(panel, radioPanel, 0, 4, GridBagConstraints.WEST);

        // ����� ���� ���� ��ư
        JPanel exePanel = new JPanel(new GridBagLayout());
        exePanel.setBorder(BorderFactory.createTitledBorder("����� ����"));

        // GridBagConstraints ����
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST; // ���� ����
        gbc.insets = new Insets(5, 5, 5, 5); // ���� ����

        // ������ư1
        JRadioButton exeHigh = new JRadioButton("���̵� ��");
        JLabel exeHighLabel = new JLabel("��� ��� ���� �ϴ� ���! ���� ��� �غ��ô�");
        addGridBagComponent(exePanel, exeHigh, 0, 0, GridBagConstraints.WEST);
        addGridBagComponent(exePanel, exeHighLabel, 0, 1, GridBagConstraints.WEST);

        // ������ư2
        JRadioButton exeMid = new JRadioButton("���̵� ��");
        JLabel exeMidLabel = new JLabel("������ ��� �ϰ� ��� ���. ������ ��� �սô�");
        addGridBagComponent(exePanel, exeMid, 0, 2, GridBagConstraints.WEST);
        addGridBagComponent(exePanel, exeMidLabel, 0, 3, GridBagConstraints.WEST);

        // ������ư3
        JRadioButton exeLow = new JRadioButton("���̵� ��");
        JLabel exeLowLabel = new JLabel("��� ��� ���ϴ� ���. ��ġ�� �ʰ� ��� �սô�");
        addGridBagComponent(exePanel, exeLow, 0, 4, GridBagConstraints.WEST);
        addGridBagComponent(exePanel, exeLowLabel, 0, 5, GridBagConstraints.WEST);

        ButtonGroup exeGroup = new ButtonGroup();
        exeGroup.add(exeHigh);
        exeGroup.add(exeMid);
        exeGroup.add(exeLow);

        addGridBagComponent(panel, exePanel, 0, 5, GridBagConstraints.WEST); // ����� ���� �г� �߰�

        // Ȯ�� ��ư
        JButton confirmButton = new JButton("Ȯ��");
        confirmButton.addActionListener(new ConfirmButtonListener(weightField, skmuField, bodyfatField, sleepField, cidC, cidI, cidD, exeHigh, exeMid, exeLow));

        // ��� ��ư
        JButton cancelButton = new JButton("���");
        cancelButton.addActionListener(new CancelButtonListener());

        // Ȯ��, ��� ��ư �г�
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        // �г� �߰�
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
            // �Է°� Ȯ��
            if (weightField.getText().isEmpty() || !isNumeric(weightField.getText()) ||
                skmuField.getText().isEmpty() || !isNumeric(skmuField.getText()) ||
                bodyfatField.getText().isEmpty() || !isNumeric(bodyfatField.getText()) ||
                sleepField.getText().isEmpty() || !isNumeric(sleepField.getText()) ||
                (!cidC.isSelected() && !cidI.isSelected() && !cidD.isSelected()) ||
                (!exeHigh.isSelected() && !exeMid.isSelected() && !exeLow.isSelected())) {
                JOptionPane.showMessageDialog(TargetDialog.this, "�Է°��� �ٽ� Ȯ���Ͽ� ��� �Է����ּ���", "���", JOptionPane.WARNING_MESSAGE);
            } else {
                // �Է°� ó��
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
                    exeLevel.setText("��");
                } else if (exeMid.isSelected()) {
                    exeLevel.setText("��");
                } else {
                    exeLevel.setText("��");
                }
                sleepTime.setText(sleepField.getText());
                dispose(); // ���̾�α� â �ݱ�
            }
        }
    }

    private class CancelButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            dispose(); // ���̾�α� â �ݱ�
        }
    }

    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
   
}

