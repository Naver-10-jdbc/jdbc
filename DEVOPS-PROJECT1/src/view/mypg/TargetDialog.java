package view.mypg;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import db.MyPageDAO;
import db.UsersData;
import view.logn.Session;

public class TargetDialog extends JDialog {
    private JLabel myWishWeight, myWishSkmu, myWishBodyfat, whisCid, exeLevel, sleepTime, myBMI;
    JTextField weightField, skmuField, bodyfatField, sleepField;
    private JRadioButton cidC, cidI, cidD;
    private int exeLevelNum;
    private String cidSelect;
    private JLabel wishCID, myWishBMI;
    String user_id = Session.getInstance().getUserId();
    
    public TargetDialog(MyPage parentFrame) {
        super(parentFrame, "��ǥ ������Ʈ", true);
        MyPage myPage =parentFrame;

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
        weightField = new JTextField(10);
        weightPanel.add(new JLabel("ü�� �Է�       "));
        weightPanel.add(weightField);
        weightPanel.add(new JLabel("kg"));
        addGridBagComponent(panel, weightPanel, 0, 0, GridBagConstraints.WEST);

        // ��ݱٷ� �Է� �г�
        JPanel skmuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        skmuField = new JTextField(10);
        skmuPanel.add(new JLabel("��ݱٷ� �Է�"));
        skmuPanel.add(skmuField);
        skmuPanel.add(new JLabel("kg"));
        addGridBagComponent(panel, skmuPanel, 0, 1, GridBagConstraints.WEST);

        // ü���淮 �Է� �г�
        JPanel bodyfatPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bodyfatField = new JTextField(10);
        bodyfatPanel.add(new JLabel("ü���淮 �Է�"));
        bodyfatPanel.add(bodyfatField);
        bodyfatPanel.add(new JLabel("kg"));
        addGridBagComponent(panel, bodyfatPanel, 0, 2, GridBagConstraints.WEST);

        // ����ð� �Է� �г�
        JPanel sleepPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sleepField = new JTextField(10);
        sleepPanel.add(new JLabel("����ð� �Է�"));
        sleepPanel.add(sleepField);
        sleepPanel.add(new JLabel("�ð�"));
        addGridBagComponent(panel, sleepPanel, 0, 3, GridBagConstraints.WEST);

        // CID ���� ��ư
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ButtonGroup cidGroup = new ButtonGroup();
        cidC = new JRadioButton("C");
        cidI = new JRadioButton("I");
        cidD = new JRadioButton("D");
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
        confirmButton.addActionListener(new ActionListener() {

			private BMIimg bmiimg;
			private UsersData usersData;
			private JButton myBMIicon;

			@Override
			public void actionPerformed(ActionEvent e) {
				String weightValue = weightField.getText();
                String skmuValue = skmuField.getText();
                String bodyfatValue = bodyfatField.getText();
                String sleepValue = sleepField.getText();
				
	            // �Է°� Ȯ��
	            if (weightValue.isEmpty() || !isNumeric(weightValue) ||
	            	skmuValue.isEmpty() || !isNumeric(skmuValue) ||
	            	bodyfatValue.isEmpty() || !isNumeric(bodyfatValue) ||
	            	sleepValue.isEmpty() || !isNumeric(sleepValue) ||
	                (!cidC.isSelected() && !cidI.isSelected() && !cidD.isSelected()) ||
	                (!exeHigh.isSelected() && !exeMid.isSelected() && !exeLow.isSelected())) {
	                JOptionPane.showMessageDialog(TargetDialog.this, "�Է°��� �ٽ� Ȯ���Ͽ� ��� �Է����ּ���", "���", JOptionPane.WARNING_MESSAGE);
	            } else {
	                // �Է°� ó��
	            	
	            	myWishWeight = myPage.getMyWishWeihgt();
	            	myWishWeight.setText(weightValue);
	            	myWishSkmu = myPage.getMyWishskmu();
	            	myWishSkmu.setText(skmuValue);
	            	myWishBodyfat = myPage.getMyWishbodyfat();
	            	myWishBodyfat.setText(bodyfatValue);
	            	
	            	wishCID = myPage.getWishCid();
	                if (cidC.isSelected()) {
	                	wishCID.setText("C");
	                    cidSelect="C";
	                } else if (cidI.isSelected()) {
	                	wishCID.setText("I");
	                    cidSelect="I";
	                } else {
	                	wishCID.setText("D");
	                    cidSelect="D";
	                }
	                
	                exeLevel = myPage.getExeLevel();
	                if (exeHigh.isSelected()) {
	                    exeLevel.setText("��");
	                    exeLevelNum=2;
	                } else if (exeMid.isSelected()) {
	                    exeLevel.setText("��");
	                    exeLevelNum=1;
	                } else {
	                    exeLevel.setText("��");
	                    exeLevelNum=0;
	                }
	                
	                sleepTime= myPage.getSleepTime();
	                sleepTime.setText(sleepValue);
	                
	                MyPageDAO myPageDAO = new MyPageDAO();
	                myPageDAO.updateTarget(user_id, Double.parseDouble(weightValue), Double.parseDouble(skmuValue), Double.parseDouble(bodyfatValue), Double.parseDouble(sleepValue), cidSelect, exeLevelNum);
	                
	                // BMI ���
                    double heightValue = Double.parseDouble(parentFrame.getmyHeightText());
                    String myBMICal = String.format("%.1f", Double.parseDouble(weightField.getText()) / ((heightValue / 100) * (heightValue / 100)));
	                
	                myWishBMI = myPage.getMyWishBMI();
	                myWishBMI.setText(myBMICal);
	                
	                //bmi�� �´� �̹��� ��������
                    bmiimg = new BMIimg();
	                usersData=new MyPageDAO().loginData();
	                String resource = bmiimg.bmi_img(usersData.getUser_gender(), Double.parseDouble(myBMICal));
	                
	                // ���ο� �̹��� ���� �ε�
	                ImageIcon newIcon = new ImageIcon(MyPage.class.getResource(resource));
	                // �̹��� ũ�� ����
	                Image image = newIcon.getImage().getScaledInstance(90, 215, Image.SCALE_SMOOTH);

	                // ������ �̹����� ImageIcon ����
	                ImageIcon resizedIcon = new ImageIcon(image);

	                // ��ư�� �̹��� ����
	                myBMIicon = myPage.getWishbodyimg();
	                myBMIicon.setIcon(resizedIcon);
	                
	                dispose(); // ���̾�α� â �ݱ�
	            }
			}
		});
        
        // ��� ��ư
        JButton cancelButton = new JButton("���");
        cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});

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


    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
   
}

