package view.mypg;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.*;

public class MyPage extends JFrame {

	private JPanel contentPane;
	
	private JPanel head;
	private JButton btn_back;
	private JLabel title;
	private JButton btn_inbody,btn_target;
	private JPanel now, nowbody, bodyprofile;
	private JButton bodyimg;
	private JLabel heght, weight, bmi, skmu, bodyfat, cid, myWeight, MyBMI, mySkmu, myBodyfat, myCID, profilelabel;
	private JPanel wish;
	private JLabel wishlabel;
	private JPanel wishbody;
	private JButton wishbodyimg;
	private JPanel wishbodyprofile;
	private JLabel wishweight, myWishWeihgt, wishskmu, myWishskmu, wishbodyfat, myWishbodyfat, wishsleep, sleepTime;
	private JPanel wishLevel;
	private JLabel height_unit, weight_unit, skmu_unit, bodyfat_unit, cid_unit, wishweight_unit, wishWeihgt_unit, wishbodyfat_unit, sleepTime_unit;
	private JPanel wishCID_data;
	private JLabel wishCID_title, whisCid;
	private JPanel wishexeLevel_data;
	private JLabel wishexeLevel_title, exeLevel;
	private JButton myHeghit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyPage frame = new MyPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	private boolean isNumeric(String str) {
	    return str.matches("-?\\d+(\\.\\d+)?");
	}
	
	// GridBagLayout�� ������Ʈ�� �߰��ϴ� �޼ҵ�
	private void addGridBagComponent(Container container, Component component, int gridx, int gridy, int anchor) {
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridx = gridx;
	    gbc.gridy = gridy;
	    gbc.anchor = anchor;
	    container.add(component, gbc);
	}
	
	public MyPage() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 630);
		setTitle("MyPage");
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		////////head////////////////////////////////////////////////////////////////
		head = new JPanel();
		head.setBackground(new Color(255, 255, 255));
		contentPane.add(head, BorderLayout.NORTH);
		GridBagLayout gbl_head = new GridBagLayout();
		gbl_head.columnWidths = new int[]{61, 188, 76, 139, 97, 85, 0};
		gbl_head.rowHeights = new int[]{50, 0};
		gbl_head.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_head.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		head.setLayout(gbl_head);
		
		btn_back = new JButton("��");		//����, ���� �� ����
		btn_back.setBackground(new Color(255, 255, 255));
		btn_back.setPreferredSize(new Dimension(50, 50));
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btn_back = new GridBagConstraints();
		gbc_btn_back.insets = new Insets(0, 0, 0, 5);
		gbc_btn_back.gridx = 0;
		gbc_btn_back.gridy = 0;
		head.add(btn_back, gbc_btn_back);
		
		title = new JLabel("My Page");
		title.setFont(new Font("����", Font.BOLD, 20));
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.insets = new Insets(0, 0, 0, 5);
		gbc_title.gridx = 2;
		gbc_title.gridy = 0;
		head.add(title, gbc_title);
		
		btn_inbody = new JButton("�ιٵ� �Է�");
		btn_inbody.setBackground(new Color(255, 255, 255));
		//�ιٵ� �Է� ���̾�α�â ����
		btn_inbody.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // ���̾�α� ����
		        JDialog dialog = new JDialog();
		        dialog.setTitle("�ιٵ� ����");
		        dialog.setSize(280, 300);
		        dialog.setResizable(false);
		        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		        dialog.setLocationRelativeTo(null); // ȭ�� �߾ӿ� ��ġ

		        // �г� ����
		        JPanel panel = new JPanel();
		        panel.setLayout(new GridLayout(6, 1));

		        // ü�� �Է� �г�
		        JPanel weightPanel = new JPanel(new BorderLayout());
		        weightPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5)); // ���� ����
		        JTextField weightField = new JTextField();
		        weightPanel.add(weightField, BorderLayout.CENTER);
		        weightPanel.add(new JLabel("kg", SwingConstants.CENTER), BorderLayout.EAST);
		        panel.add(new JLabel("ü�� �Է�", SwingConstants.CENTER));
		        panel.add(weightPanel);

		        // ��ݱٷ� �Է� �г�
		        JPanel skmuPanel = new JPanel(new BorderLayout());
		        skmuPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5)); // ���� ����
		        JTextField skmuField = new JTextField();
		        skmuPanel.add(skmuField, BorderLayout.CENTER);
		        skmuPanel.add(new JLabel("kg", SwingConstants.CENTER), BorderLayout.EAST);
		        panel.add(new JLabel("��ݱٷ� �Է�", SwingConstants.CENTER));
		        panel.add(skmuPanel);

		        // ü���淮 �Է� �г�
		        JPanel bodyfatPanel = new JPanel(new BorderLayout());
		        bodyfatPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5)); // ���� ����
		        JTextField bodyfatField = new JTextField();
		        bodyfatPanel.add(bodyfatField, BorderLayout.CENTER);
		        bodyfatPanel.add(new JLabel("kg", SwingConstants.CENTER), BorderLayout.EAST);
		        panel.add(new JLabel("ü���淮 �Է�", SwingConstants.CENTER));
		        panel.add(bodyfatPanel);

		        // CID ���� ��ư
		        JPanel radioPanel = new JPanel();
		        radioPanel.setLayout(new GridLayout(1, 3));
		        radioPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5)); // ���� ����
		        JRadioButton cidC = new JRadioButton("C");
		        JRadioButton cidI = new JRadioButton("I");
		        JRadioButton cidD = new JRadioButton("D");
		        ButtonGroup cidGroup = new ButtonGroup();
		        cidGroup.add(cidC);
		        cidGroup.add(cidI);
		        cidGroup.add(cidD);
		        radioPanel.add(cidC);
		        radioPanel.add(cidI);
		        radioPanel.add(cidD);
		        panel.add(new JLabel("CID ���� ����", SwingConstants.CENTER));
		        panel.add(radioPanel);

		        // Ȯ�� ��ư
		        JButton okButton = new JButton("Ȯ��");
		        okButton.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		                // �Է°� Ȯ��
		                if (weightField.getText().isEmpty() || !isNumeric(weightField.getText()) ||
		                    skmuField.getText().isEmpty() || !isNumeric(skmuField.getText()) ||
		                    bodyfatField.getText().isEmpty() || !isNumeric(bodyfatField.getText()) ||
		                    (!cidC.isSelected() && !cidI.isSelected() && !cidD.isSelected())) {
		                    JOptionPane.showMessageDialog(dialog, "�Է°��� �ٽ� Ȯ���Ͽ� ��� �Է����ּ���", "���", JOptionPane.WARNING_MESSAGE);
		                } else {
		                    // �Է°� ó��
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

		                    // �о�� ������ �� ������Ʈ
		                    myWeight.setText(weightValue);
		                    mySkmu.setText(skmuValue);
		                    myBodyfat.setText(bodyfatValue);
		                    myCID.setText(cidValue);

		                    // ���̾�α� �ݱ�
		                    dialog.dispose();
		                }
		            }
		        });

		        // ��� ��ư
		        JButton cancelButton = new JButton("���");
		        cancelButton.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		                dialog.dispose();
		            }
		        });

		        // ��ư �г� ����
		        JPanel buttonPanel = new JPanel();
		        buttonPanel.add(okButton);
		        buttonPanel.add(cancelButton);

		        // �г� �߰�
		        dialog.getContentPane().add(panel, BorderLayout.CENTER);
		        dialog.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		        // ���̾�α� ǥ��
		        dialog.setVisible(true);
		    }
		});


		
		GridBagConstraints gbc_btn_inbody = new GridBagConstraints();
		gbc_btn_inbody.anchor = GridBagConstraints.WEST;
		gbc_btn_inbody.insets = new Insets(0, 0, 0, 5);
		gbc_btn_inbody.gridx = 4;
		gbc_btn_inbody.gridy = 0;
		head.add(btn_inbody, gbc_btn_inbody);
		
		btn_target = new JButton("��ǥ �Է�");
		btn_target.setBackground(new Color(255, 255, 255));
		// ��ǥ �Է� ���̾�α�â ����
		btn_target.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	// ���̾�α� â ����
		    	JDialog dialog = new JDialog();
		    	dialog.setTitle("��ǥ ������Ʈ");
		    	dialog.setSize(400, 500);
		    	dialog.setResizable(false);
		    	dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		    	dialog.setLocationRelativeTo(null); // ȭ�� �߾ӿ� ǥ��

		    	// �г� ����
		    	JPanel panel = new JPanel(new GridBagLayout());

		    	// GridBagConstraints ����
		    	GridBagConstraints gbc = new GridBagConstraints();
		    	gbc.anchor = GridBagConstraints.WEST; // ���� ����
		    	gbc.insets = new Insets(5, 5, 5, 5); // ���� ����

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
		    	confirmButton.addActionListener(new ActionListener() {
		    	    public void actionPerformed(ActionEvent e) {
		    	        // �Է°� Ȯ��
		    	        if (weightField.getText().isEmpty() || !isNumeric(weightField.getText()) ||
		    	            skmuField.getText().isEmpty() || !isNumeric(skmuField.getText()) ||
		    	            bodyfatField.getText().isEmpty() || !isNumeric(bodyfatField.getText()) ||
		    	            sleepField.getText().isEmpty() || !isNumeric(sleepField.getText()) ||
		    	            (!cidC.isSelected() && !cidI.isSelected() && !cidD.isSelected()) ||
		    	            (!exeHigh.isSelected() && !exeMid.isSelected() && !exeLow.isSelected())) {
		    	            JOptionPane.showMessageDialog(dialog, "�Է°��� �ٽ� Ȯ���Ͽ� ��� �Է����ּ���", "���", JOptionPane.WARNING_MESSAGE);
		    	        } else {
		    	            // �Է°� ó��
		    	            myWishWeihgt.setText(weightField.getText());
		    	            myWishskmu.setText(skmuField.getText());
		    	            myWishbodyfat.setText(bodyfatField.getText());
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
		    	            dialog.dispose(); // ���̾�α� â �ݱ�
		    	        }
		    	    }
		    	});

		    	// ��� ��ư
		    	JButton cancelButton = new JButton("���");
		    	cancelButton.addActionListener(new ActionListener() {
		    	    public void actionPerformed(ActionEvent e) {
		    	        dialog.dispose(); // ���̾�α� â �ݱ�
		    	    }
		    	});

		    	// Ȯ��, ��� ��ư �г�
		    	JPanel buttonPanel = new JPanel();
		    	buttonPanel.add(confirmButton);
		    	buttonPanel.add(cancelButton);

		    	// �г� �߰�
		    	dialog.getContentPane().add(panel, BorderLayout.CENTER);
		    	dialog.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		    	// ���̾�α� ǥ��
		    	dialog.setVisible(true);
		    }
		});


		GridBagConstraints gbc_btn_target = new GridBagConstraints();
		gbc_btn_target.anchor = GridBagConstraints.WEST;
		gbc_btn_target.gridx = 5;
		gbc_btn_target.gridy = 0;
		head.add(btn_target, gbc_btn_target);
		
		/////////main ���� ������//////////////////////////////////////////////////////////////
		now = new JPanel();
		now.setBackground(new Color(255, 255, 255));
		contentPane.add(now, BorderLayout.CENTER); 
		GridBagLayout gbl_now = new GridBagLayout();
		gbl_now.columnWidths = new int[]{182, 100, 71, 104, 0};
		gbl_now.rowHeights = new int[]{34, 225, 0};
		gbl_now.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_now.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		now.setLayout(gbl_now);
		
		profilelabel = new JLabel("������");
		profilelabel.setFont(new Font("����", Font.BOLD, 14));
		GridBagConstraints gbc_profilelabel = new GridBagConstraints();
		gbc_profilelabel.anchor = GridBagConstraints.EAST;
		gbc_profilelabel.insets = new Insets(0, 0, 5, 5);
		gbc_profilelabel.gridx = 0;
		gbc_profilelabel.gridy = 0;
		now.add(profilelabel, gbc_profilelabel);
		
		//���� BMI�� �ش��ϴ� �� �̹��� ����
		nowbody = new JPanel();
		nowbody.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_nowbody = new GridBagConstraints();
		gbc_nowbody.anchor = GridBagConstraints.NORTHWEST;
		gbc_nowbody.insets = new Insets(0, 0, 0, 5);
		gbc_nowbody.gridx = 1;
		gbc_nowbody.gridy = 1;
		now.add(nowbody, gbc_nowbody);
		
		bodyimg = new JButton("");
		bodyimg.setBackground(new Color(255, 255, 255));
		bodyimg.setPreferredSize(new Dimension(90, 215)); // �ʺ�� ���� ����
		ImageIcon icon = new ImageIcon(MyPage.class.getResource("03_male_overweight.png")); //�̹��� ����
		Image image = icon.getImage().getScaledInstance(90, 215, Image.SCALE_SMOOTH); // �̹��� ũ�� ����
		bodyimg.setIcon(new ImageIcon(image));
		nowbody.add(bodyimg);

		//���� �ιٵ� ���� ����
		bodyprofile = new JPanel();
		bodyprofile.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_bodyprofile = new GridBagConstraints();
		gbc_bodyprofile.anchor = GridBagConstraints.WEST;
		gbc_bodyprofile.gridx = 3;
		gbc_bodyprofile.gridy = 1;
		now.add(bodyprofile, gbc_bodyprofile);
		
		GridBagLayout gbl_bodyprofile = new GridBagLayout();
		gbl_bodyprofile.columnWidths = new int[]{50, 50, 0, 0};
		gbl_bodyprofile.rowHeights = new int[]{15, 0, 0, 0, 0, 0, 0};
		gbl_bodyprofile.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_bodyprofile.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		bodyprofile.setLayout(gbl_bodyprofile);
		
		heght = new JLabel("Ű");
		heght.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_heght = new GridBagConstraints();
		gbc_heght.anchor = GridBagConstraints.NORTHWEST;
		gbc_heght.insets = new Insets(0, 0, 5, 5);
		gbc_heght.gridx = 0;
		gbc_heght.gridy = 0;
		bodyprofile.add(heght, gbc_heght);
		
		myHeghit = new JButton("170.2");
		myHeghit.setBackground(new Color(255, 255, 255));
		myHeghit.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // ���̾�α� ����
		        JDialog dialog = new JDialog();
		        dialog.setTitle("Ű ���� �Է�");
		        dialog.setSize(200, 100);
		        dialog.setResizable(false);
		        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		        dialog.setLocationRelativeTo(null); // ȭ�� �߾ӿ� ��ġ

		        // �г� ����
		        JPanel panel = new JPanel(new GridLayout(0, 2));

		        // Ű �Է� �� �� �ʵ�
		        JLabel heightLabel = new JLabel("Ű(cm):");
		        JTextField heightField = new JTextField();
		        panel.add(heightLabel);
		        panel.add(heightField);

		        // Ȯ�� ��ư
		        JButton okButton = new JButton("Ȯ��");
		        okButton.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		                // �Է°� Ȯ��
		                String input = heightField.getText();
		                if (input.isEmpty() || !isNumeric(input)) {
		                    JOptionPane.showMessageDialog(dialog, "�ùٸ� ���ڸ� �Է����ּ���.", "����", JOptionPane.ERROR_MESSAGE);
		                } else {
		                    // �Է°��� myHeghit�� ����
		                    myHeghit.setText(input);
		                    dialog.dispose(); // ���̾�α� �ݱ�
		                }
		            }
		        });

		        // ��� ��ư
		        JButton cancelButton = new JButton("���");
		        cancelButton.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		                dialog.dispose(); // ���̾�α� �ݱ�
		            }
		        });

		        // ��ư �г� ����
		        JPanel buttonPanel = new JPanel();
		        buttonPanel.add(okButton);
		        buttonPanel.add(cancelButton);

		        // ���̾�α׿� �г� �߰�
		        dialog.getContentPane().add(panel, BorderLayout.CENTER);
		        dialog.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		        // ���̾�α� ǥ��
		        dialog.setVisible(true);
		    }
		});
		
		myHeghit.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_myHeghit = new GridBagConstraints();
		gbc_myHeghit.anchor = GridBagConstraints.EAST;
		gbc_myHeghit.insets = new Insets(0, 0, 5, 5);
		gbc_myHeghit.gridx = 1;
		gbc_myHeghit.gridy = 0;
		bodyprofile.add(myHeghit, gbc_myHeghit);
		
		height_unit = new JLabel("cm");
		GridBagConstraints gbc_height_unit = new GridBagConstraints();
		gbc_height_unit.insets = new Insets(0, 0, 5, 0);
		gbc_height_unit.gridx = 2;
		gbc_height_unit.gridy = 0;
		bodyprofile.add(height_unit, gbc_height_unit);
		
		weight = new JLabel("������");
		weight.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_weight = new GridBagConstraints();
		gbc_weight.insets = new Insets(0, 0, 5, 5);
		gbc_weight.anchor = GridBagConstraints.NORTHWEST;
		gbc_weight.gridx = 0;
		gbc_weight.gridy = 1;
		bodyprofile.add(weight, gbc_weight);
		
		myWeight = new JLabel("70.2");
		GridBagConstraints gbc_myWeight = new GridBagConstraints();
		gbc_myWeight.anchor = GridBagConstraints.EAST;
		gbc_myWeight.insets = new Insets(0, 0, 5, 5);
		gbc_myWeight.gridx = 1;
		gbc_myWeight.gridy = 1;
		bodyprofile.add(myWeight, gbc_myWeight);
		
		weight_unit = new JLabel("kg");
		GridBagConstraints gbc_weight_unit = new GridBagConstraints();
		gbc_weight_unit.insets = new Insets(0, 0, 5, 0);
		gbc_weight_unit.gridx = 2;
		gbc_weight_unit.gridy = 1;
		bodyprofile.add(weight_unit, gbc_weight_unit);
		
		bmi = new JLabel("BMI");
		GridBagConstraints gbc_bmi = new GridBagConstraints();
		gbc_bmi.anchor = GridBagConstraints.WEST;
		gbc_bmi.insets = new Insets(0, 0, 5, 5);
		gbc_bmi.gridx = 0;
		gbc_bmi.gridy = 2;
		bodyprofile.add(bmi, gbc_bmi);
		
		MyBMI = new JLabel("24.2");
		GridBagConstraints gbc_MyBMI = new GridBagConstraints();
		gbc_MyBMI.anchor = GridBagConstraints.EAST;
		gbc_MyBMI.insets = new Insets(0, 0, 5, 5);
		gbc_MyBMI.gridx = 1;
		gbc_MyBMI.gridy = 2;
		bodyprofile.add(MyBMI, gbc_MyBMI);
		
		skmu = new JLabel("��ݱٷ�");
		GridBagConstraints gbc_skmu = new GridBagConstraints();
		gbc_skmu.anchor = GridBagConstraints.WEST;
		gbc_skmu.insets = new Insets(0, 0, 5, 5);
		gbc_skmu.gridx = 0;
		gbc_skmu.gridy = 3;
		bodyprofile.add(skmu, gbc_skmu);
		
		mySkmu = new JLabel("19.5");
		GridBagConstraints gbc_mySkmu = new GridBagConstraints();
		gbc_mySkmu.anchor = GridBagConstraints.EAST;
		gbc_mySkmu.insets = new Insets(0, 0, 5, 5);
		gbc_mySkmu.gridx = 1;
		gbc_mySkmu.gridy = 3;
		bodyprofile.add(mySkmu, gbc_mySkmu);
		
		skmu_unit = new JLabel("kg");
		GridBagConstraints gbc_skmu_unit = new GridBagConstraints();
		gbc_skmu_unit.insets = new Insets(0, 0, 5, 0);
		gbc_skmu_unit.gridx = 2;
		gbc_skmu_unit.gridy = 3;
		bodyprofile.add(skmu_unit, gbc_skmu_unit);
		
		bodyfat = new JLabel("ü���淮");
		GridBagConstraints gbc_bodyfat = new GridBagConstraints();
		gbc_bodyfat.anchor = GridBagConstraints.WEST;
		gbc_bodyfat.insets = new Insets(0, 0, 5, 5);
		gbc_bodyfat.gridx = 0;
		gbc_bodyfat.gridy = 4;
		bodyprofile.add(bodyfat, gbc_bodyfat);
		
		myBodyfat = new JLabel("19.5");
		GridBagConstraints gbc_myBodyfat = new GridBagConstraints();
		gbc_myBodyfat.anchor = GridBagConstraints.EAST;
		gbc_myBodyfat.insets = new Insets(0, 0, 5, 5);
		gbc_myBodyfat.gridx = 1;
		gbc_myBodyfat.gridy = 4;
		bodyprofile.add(myBodyfat, gbc_myBodyfat);
		
		bodyfat_unit = new JLabel("kg");
		GridBagConstraints gbc_bodyfat_unit = new GridBagConstraints();
		gbc_bodyfat_unit.insets = new Insets(0, 0, 5, 0);
		gbc_bodyfat_unit.gridx = 2;
		gbc_bodyfat_unit.gridy = 4;
		bodyprofile.add(bodyfat_unit, gbc_bodyfat_unit);
		
		cid = new JLabel("CID ����");
		GridBagConstraints gbc_cid = new GridBagConstraints();
		gbc_cid.anchor = GridBagConstraints.WEST;
		gbc_cid.insets = new Insets(0, 0, 0, 5);
		gbc_cid.gridx = 0;
		gbc_cid.gridy = 5;
		bodyprofile.add(cid, gbc_cid);
		
		myCID = new JLabel("C");
		GridBagConstraints gbc_myCID = new GridBagConstraints();
		gbc_myCID.insets = new Insets(0, 0, 0, 5);
		gbc_myCID.anchor = GridBagConstraints.EAST;
		gbc_myCID.gridx = 1;
		gbc_myCID.gridy = 5;
		bodyprofile.add(myCID, gbc_myCID);
		
		cid_unit = new JLabel("����");
		GridBagConstraints gbc_cid_unit = new GridBagConstraints();
		gbc_cid_unit.gridx = 2;
		gbc_cid_unit.gridy = 5;
		bodyprofile.add(cid_unit, gbc_cid_unit);
		
		//////main ��ǥ////////////////////////////////////////////////////////
		wish = new JPanel();
		wish.setBackground(new Color(255, 255, 255));
		contentPane.add(wish, BorderLayout.SOUTH);
		GridBagLayout gbl_wish = new GridBagLayout();
		gbl_wish.columnWidths = new int[]{182, 100, 71, 104, 0};
		gbl_wish.rowHeights = new int[]{34, 49, 120, 0};
		gbl_wish.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_wish.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		wish.setLayout(gbl_wish);
		
		wishlabel = new JLabel("��ǥ");
		wishlabel.setFont(new Font("����", Font.BOLD, 14));
		GridBagConstraints gbc_wishlabel = new GridBagConstraints();
		gbc_wishlabel.anchor = GridBagConstraints.EAST;
		gbc_wishlabel.insets = new Insets(0, 0, 5, 5);
		gbc_wishlabel.gridx = 0;
		gbc_wishlabel.gridy = 0;
		wish.add(wishlabel, gbc_wishlabel);
		
		//��ǥ�ϴ� CID�� ����� ����
		wishLevel = new JPanel();
		wishLevel.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_wishLevel = new GridBagConstraints();
		gbc_wishLevel.anchor = GridBagConstraints.WEST;
		gbc_wishLevel.gridheight = 2;
		gbc_wishLevel.insets = new Insets(0, 0, 5, 0);
		gbc_wishLevel.gridx = 3;
		gbc_wishLevel.gridy = 0;
		wish.add(wishLevel, gbc_wishLevel);
		
		GridBagLayout gbl_wishLevel = new GridBagLayout();
		gbl_wishLevel.columnWidths = new int[]{83, 76, 0};
		gbl_wishLevel.rowHeights = new int[]{0, 0, 36, 0};
		gbl_wishLevel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_wishLevel.rowWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
		wishLevel.setLayout(gbl_wishLevel);
	
		GridBagConstraints gbc_whisCid = new GridBagConstraints();
		gbc_whisCid.anchor = GridBagConstraints.NORTHWEST;
		gbc_whisCid.insets = new Insets(0, 0, 0, 5);
		gbc_whisCid.gridx = 0;
		gbc_whisCid.gridy = 0;

		
		GridBagConstraints gbc_exeLevel = new GridBagConstraints();
		gbc_exeLevel.anchor = GridBagConstraints.NORTHWEST;
		gbc_exeLevel.gridx = 2;
		gbc_exeLevel.gridy = 0;
		
		wishCID_data = new JPanel();
		GridBagConstraints gbc_wishCID_data = new GridBagConstraints();
		gbc_wishCID_data.gridheight = 3;
		gbc_wishCID_data.insets = new Insets(0, 0, 0, 5);
		gbc_wishCID_data.fill = GridBagConstraints.BOTH;
		gbc_wishCID_data.gridx = 0;
		gbc_wishCID_data.gridy = 0;
		wishLevel.add(wishCID_data, gbc_wishCID_data);
		wishCID_data.setLayout(new BorderLayout(0, 0));
		
		wishCID_title = new JLabel("CID ��ǥ");
		wishCID_title.setHorizontalAlignment(SwingConstants.CENTER);
		wishCID_data.add(wishCID_title);
		
		whisCid = new JLabel("D");
		whisCid.setHorizontalAlignment(SwingConstants.CENTER);
		wishCID_data.add(whisCid, BorderLayout.SOUTH);
		
		wishexeLevel_data = new JPanel();
		GridBagConstraints gbc_wishexeLevel_data = new GridBagConstraints();
		gbc_wishexeLevel_data.gridheight = 3;
		gbc_wishexeLevel_data.fill = GridBagConstraints.BOTH;
		gbc_wishexeLevel_data.gridx = 1;
		gbc_wishexeLevel_data.gridy = 0;
		wishLevel.add(wishexeLevel_data, gbc_wishexeLevel_data);
		wishexeLevel_data.setLayout(new BorderLayout(0, 0));
		
		wishexeLevel_title = new JLabel("�����");
		wishexeLevel_title.setHorizontalAlignment(SwingConstants.CENTER);
		wishexeLevel_data.add(wishexeLevel_title, BorderLayout.CENTER);
		
		exeLevel = new JLabel("��");
		exeLevel.setHorizontalAlignment(SwingConstants.CENTER);
		wishexeLevel_data.add(exeLevel, BorderLayout.SOUTH);
		
		//��ǥ�ϴ� BMI�� �ش��ϴ� �� �̹��� ����
		wishbody = new JPanel();
		wishbody.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_wishbody = new GridBagConstraints();
		gbc_wishbody.gridheight = 2;
		gbc_wishbody.anchor = GridBagConstraints.NORTHWEST;
		gbc_wishbody.insets = new Insets(0, 0, 0, 5);
		gbc_wishbody.gridx = 1;
		gbc_wishbody.gridy = 1;
		wish.add(wishbody, gbc_wishbody);
		
		wishbodyimg = new JButton("");
		wishbodyimg.setBackground(new Color(255, 255, 255));
		wishbodyimg.setPreferredSize(new Dimension(90, 215));
		ImageIcon icon2 = new ImageIcon(MyPage.class.getResource("02.male_normal.png")); //��ư�� �̹��� ����
		Image image2 = icon2.getImage().getScaledInstance(90, 215, Image.SCALE_SMOOTH);
		wishbodyimg.setIcon(new ImageIcon(image2));
		wishbody.add(wishbodyimg);

		//��ǥ �ιٵ� ���� ����
		wishbodyprofile = new JPanel();
		wishbodyprofile.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_wishbodyprofile = new GridBagConstraints();
		gbc_wishbodyprofile.anchor = GridBagConstraints.NORTHWEST;
		gbc_wishbodyprofile.gridx = 3;
		gbc_wishbodyprofile.gridy = 2;
		wish.add(wishbodyprofile, gbc_wishbodyprofile);
		
		GridBagLayout gbl_wishbodyprofile = new GridBagLayout();
		gbl_wishbodyprofile.columnWidths = new int[]{50, 50, 0, 0};
		gbl_wishbodyprofile.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_wishbodyprofile.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_wishbodyprofile.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		wishbodyprofile.setLayout(gbl_wishbodyprofile);
		
		wishweight = new JLabel("������");
		wishweight.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_wishweight = new GridBagConstraints();
		gbc_wishweight.anchor = GridBagConstraints.NORTHWEST;
		gbc_wishweight.insets = new Insets(0, 0, 5, 5);
		gbc_wishweight.gridx = 0;
		gbc_wishweight.gridy = 0;
		wishbodyprofile.add(wishweight, gbc_wishweight);
		
		myWishWeihgt = new JLabel("70.2");
		GridBagConstraints gbc_myWishWeihgt = new GridBagConstraints();
		gbc_myWishWeihgt.anchor = GridBagConstraints.EAST;
		gbc_myWishWeihgt.insets = new Insets(0, 0, 5, 5);
		gbc_myWishWeihgt.gridx = 1;
		gbc_myWishWeihgt.gridy = 0;
		wishbodyprofile.add(myWishWeihgt, gbc_myWishWeihgt);
		
		wishweight_unit = new JLabel("kg");
		GridBagConstraints gbc_wishweight_unit = new GridBagConstraints();
		gbc_wishweight_unit.insets = new Insets(0, 0, 5, 0);
		gbc_wishweight_unit.gridx = 2;
		gbc_wishweight_unit.gridy = 0;
		wishbodyprofile.add(wishweight_unit, gbc_wishweight_unit);
		
		wishskmu = new JLabel("��ݱٷ�");
		GridBagConstraints gbc_wishskmu = new GridBagConstraints();
		gbc_wishskmu.anchor = GridBagConstraints.WEST;
		gbc_wishskmu.insets = new Insets(0, 0, 5, 5);
		gbc_wishskmu.gridx = 0;
		gbc_wishskmu.gridy = 1;
		wishbodyprofile.add(wishskmu, gbc_wishskmu);
		
		myWishskmu = new JLabel("19.5");
		GridBagConstraints gbc_myWishskmu = new GridBagConstraints();
		gbc_myWishskmu.anchor = GridBagConstraints.EAST;
		gbc_myWishskmu.insets = new Insets(0, 0, 5, 5);
		gbc_myWishskmu.gridx = 1;
		gbc_myWishskmu.gridy = 1;
		wishbodyprofile.add(myWishskmu, gbc_myWishskmu);
		
		wishWeihgt_unit = new JLabel("kg");
		GridBagConstraints gbc_wishWeihgt_unit = new GridBagConstraints();
		gbc_wishWeihgt_unit.insets = new Insets(0, 0, 5, 0);
		gbc_wishWeihgt_unit.gridx = 2;
		gbc_wishWeihgt_unit.gridy = 1;
		wishbodyprofile.add(wishWeihgt_unit, gbc_wishWeihgt_unit);
		
		wishbodyfat = new JLabel("ü���淮");
		GridBagConstraints gbc_wishbodyfat = new GridBagConstraints();
		gbc_wishbodyfat.anchor = GridBagConstraints.WEST;
		gbc_wishbodyfat.insets = new Insets(0, 0, 5, 5);
		gbc_wishbodyfat.gridx = 0;
		gbc_wishbodyfat.gridy = 2;
		wishbodyprofile.add(wishbodyfat, gbc_wishbodyfat);
		
		myWishbodyfat = new JLabel("19.5");
		GridBagConstraints gbc_myWishbodyfat = new GridBagConstraints();
		gbc_myWishbodyfat.anchor = GridBagConstraints.EAST;
		gbc_myWishbodyfat.insets = new Insets(0, 0, 5, 5);
		gbc_myWishbodyfat.gridx = 1;
		gbc_myWishbodyfat.gridy = 2;
		wishbodyprofile.add(myWishbodyfat, gbc_myWishbodyfat);
		
		wishbodyfat_unit = new JLabel("kg");
		GridBagConstraints gbc_wishbodyfat_unit = new GridBagConstraints();
		gbc_wishbodyfat_unit.insets = new Insets(0, 0, 5, 0);
		gbc_wishbodyfat_unit.gridx = 2;
		gbc_wishbodyfat_unit.gridy = 2;
		wishbodyprofile.add(wishbodyfat_unit, gbc_wishbodyfat_unit);
		
		wishsleep = new JLabel("��ǥ ����ð�");
		GridBagConstraints gbc_wishsleep = new GridBagConstraints();
		gbc_wishsleep.anchor = GridBagConstraints.WEST;
		gbc_wishsleep.insets = new Insets(0, 0, 0, 5);
		gbc_wishsleep.gridx = 0;
		gbc_wishsleep.gridy = 3;
		wishbodyprofile.add(wishsleep, gbc_wishsleep);
		
		sleepTime = new JLabel("7");
		GridBagConstraints gbc_sleepTime = new GridBagConstraints();
		gbc_sleepTime.insets = new Insets(0, 0, 0, 5);
		gbc_sleepTime.anchor = GridBagConstraints.EAST;
		gbc_sleepTime.gridx = 1;
		gbc_sleepTime.gridy = 3;
		wishbodyprofile.add(sleepTime, gbc_sleepTime);
		
		sleepTime_unit = new JLabel("�ð�");
		GridBagConstraints gbc_sleepTime_unit = new GridBagConstraints();
		gbc_sleepTime_unit.gridx = 2;
		gbc_sleepTime_unit.gridy = 3;
		wishbodyprofile.add(sleepTime_unit, gbc_sleepTime_unit);
	}

}
