package view.mypg;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InbodyDialog extends JDialog {
    private JTextField weightField, skmuField, bodyfatField;
    private JRadioButton cidC, cidI, cidD;
    private JLabel myWeight, mySkmu, myBodyfat, myCID;

    public InbodyDialog(JFrame parentFrame, JLabel weightLabel, JLabel skmuLabel, JLabel bodyfatLabel, JLabel cidLabel) {
        super(parentFrame, "�ιٵ� ����", true);
        myWeight = weightLabel;
        mySkmu = skmuLabel;
        myBodyfat = bodyfatLabel;
        myCID = cidLabel;

        setSize(280, 300);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // ȭ�� �߾ӿ� ��ġ

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        JPanel weightPanel = new JPanel(new BorderLayout());
        weightPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        weightField = new JTextField();
        weightPanel.add(weightField, BorderLayout.CENTER);
        weightPanel.add(new JLabel("kg", SwingConstants.CENTER), BorderLayout.EAST);
        panel.add(new JLabel("ü�� �Է�", SwingConstants.CENTER));
        panel.add(weightPanel);

        JPanel skmuPanel = new JPanel(new BorderLayout());
        skmuPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        skmuField = new JTextField();
        skmuPanel.add(skmuField, BorderLayout.CENTER);
        skmuPanel.add(new JLabel("kg", SwingConstants.CENTER), BorderLayout.EAST);
        panel.add(new JLabel("��ݱٷ� �Է�", SwingConstants.CENTER));
        panel.add(skmuPanel);

        JPanel bodyfatPanel = new JPanel(new BorderLayout());
        bodyfatPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        bodyfatField = new JTextField();
        bodyfatPanel.add(bodyfatField, BorderLayout.CENTER);
        bodyfatPanel.add(new JLabel("kg", SwingConstants.CENTER), BorderLayout.EAST);
        panel.add(new JLabel("ü���淮 �Է�", SwingConstants.CENTER));
        panel.add(bodyfatPanel);

        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new GridLayout(1, 3));
        radioPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        cidC = new JRadioButton("C");
        cidI = new JRadioButton("I");
        cidD = new JRadioButton("D");
        ButtonGroup cidGroup = new ButtonGroup();
        cidGroup.add(cidC);
        cidGroup.add(cidI);
        cidGroup.add(cidD);
        radioPanel.add(cidC);
        radioPanel.add(cidI);
        radioPanel.add(cidD);
        panel.add(new JLabel("CID ���� ����", SwingConstants.CENTER));
        panel.add(radioPanel);

        JButton okButton = new JButton("Ȯ��");
        okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (weightField.getText().isEmpty() || !isNumeric(weightField.getText()) ||
		                skmuField.getText().isEmpty() || !isNumeric(skmuField.getText()) ||
		                bodyfatField.getText().isEmpty() || !isNumeric(bodyfatField.getText()) ||
		                (!cidC.isSelected() && !cidI.isSelected() && !cidD.isSelected())) {
		                JOptionPane.showMessageDialog(InbodyDialog.this, "�Է°��� �ٽ� Ȯ���Ͽ� ��� �Է����ּ���", "���", JOptionPane.WARNING_MESSAGE);
		            } else {
		                String weightValue = weightField.getText();
		                String skmuValue = skmuField.getText();
		                String bodyfatValue = bodyfatField.getText();
		                String cidValue = "";
		                if (cidC.isSelected()) {
		                    cidValue = "C";
		                } else if (cidI.isSelected()) {
		                    cidValue = "I";
		                } else if (cidD.isSelected()) {
		                    cidValue = "D";
		                }

		                myWeight.setText(weightValue);
		                mySkmu.setText(skmuValue);
		                myBodyfat.setText(bodyfatValue);
		                myCID.setText(cidValue);

		                dispose();
		            }
				
			}
		});
        JButton cancelButton = new JButton("���");
        cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});

        panel.add(okButton);
        panel.add(cancelButton);

        add(panel);
        pack();
    }

    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}
