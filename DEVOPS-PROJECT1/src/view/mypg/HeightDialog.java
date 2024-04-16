package view.mypg;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import db.MyPageDAO;
import view.logn.Session;

public class HeightDialog extends JDialog {
    private JButton myHeight;
    private JLabel myBMI;
    String user_id = Session.getInstance().getUserId();
    
    //Mypage 175
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
        
        
        //okButton.addActionListener(new OkButtonListener(heightField));
        
        okButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				String input = heightField.getText();
				UpdateBMI updateBMI = new UpdateBMI();
				if (input.isEmpty() || !isNumeric(input)) {
					JOptionPane.showMessageDialog(HeightDialog.this, "�ùٸ� ���ڸ� �Է����ּ���.", "����", JOptionPane.ERROR_MESSAGE);
				} else {
					myHeight.setText(input);
					parentFrame.setmyHeightText(input);
					myBMI.setText(updateBMI.updateBMI(parentFrame));
					MyPageDAO myPageDAO = new MyPageDAO();
					//myPageDAO.updateUserHeight(user_id, Double.parseDouble(input));
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