package view.mypg;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import db.MyPageDAO;
import db.UsersData;
import view.logn.Session;

public class HeightDialog extends JDialog {
    private JButton myHeight;
    private JLabel myBMI;
    String user_id = Session.getInstance().getUserId();
    
    //Mypage 175
	public HeightDialog(MyPage parentFrame, JButton heightButton, JLabel bmiLabel) {
		super(parentFrame, "Ű ���� �Է�", true);
		MyPage myPage =parentFrame;
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
        
        
        //okButton.addActionListener(new OkButtonListener(heightField));
        
        okButton.addActionListener(new ActionListener() {	
			private BMIimg bmiimg;
			private UsersData usersData;
			private JButton myBMIicon, myBMIicon2;
			private String wishWeight, wishBMI;
			private double wishBMIValue;
			private JLabel myWishBMI;

			@Override
			public void actionPerformed(ActionEvent e) {
				String input = heightField.getText();
				UpdateBMI updateBMI = new UpdateBMI();
				if (input.isEmpty() || !isNumeric(input)) {
					JOptionPane.showMessageDialog(HeightDialog.this, "�ùٸ� ���ڸ� �Է����ּ���.", "����", JOptionPane.ERROR_MESSAGE);
				} else {
					//������ BMI ���
					myHeight.setText(input);
					parentFrame.setmyHeightText(input);
					myBMI.setText(updateBMI.updateBMI(parentFrame));
					MyPageDAO myPageDAO = new MyPageDAO();
					myPageDAO.updateUserHeight(user_id, Double.parseDouble(input));
					
					//�̹��� ������ ���� ��ǥ BMI ���
					wishWeight = myPage.getMyWishWeihgt().getText();
					if(wishWeight==null) {
						wishBMIValue=0;
					}else {
						wishBMIValue=Double.parseDouble(wishWeight);
					}
					wishBMI = String.format("%.1f", wishBMIValue / ((Double.parseDouble(input)/ 100) * (Double.parseDouble(input)/100)));
					
					myWishBMI = myPage.getMyWishBMI();
		            myWishBMI.setText(wishBMI);
					
					//bmi�� �´� �̹��� ��������
                    bmiimg = new BMIimg();
	                usersData=new MyPageDAO().loginData();
	                //������ BMI
	                String resource = bmiimg.bmi_img(usersData.getUser_gender(), Double.parseDouble(updateBMI.updateBMI(parentFrame)));
	                //��ǥ BMI
	                String resource2 = bmiimg.bmi_img(usersData.getUser_gender(), Double.parseDouble(wishBMI));
	                
	                
	                // ���ο� �̹��� ���� �ε�
	                ImageIcon newIcon = new ImageIcon(MyPage.class.getResource(resource));
	                ImageIcon newIcon2 = new ImageIcon(MyPage.class.getResource(resource2));
	                
	                // �̹��� ũ�� ����
	                Image image = newIcon.getImage().getScaledInstance(90, 215, Image.SCALE_SMOOTH);
	                Image image2 = newIcon2.getImage().getScaledInstance(90, 215, Image.SCALE_SMOOTH);

	                // ������ �̹����� ImageIcon ����
	                ImageIcon resizedIcon = new ImageIcon(image);
	                ImageIcon resizedIcon2 = new ImageIcon(image2);

	                // ��ư�� �̹��� ����
	                myBMIicon = myPage.getBodyimg();
	                myBMIicon.setIcon(resizedIcon);
	                
	                myBMIicon2 = myPage.getWishbodyimg();
	                myBMIicon2.setIcon(resizedIcon2);
					
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

        JPanel buttonPanel = new JPanel();
       
        buttonPanel.add(cancelButton);
        buttonPanel.add(okButton);
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
	}


    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}