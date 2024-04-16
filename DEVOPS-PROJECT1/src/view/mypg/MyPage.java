package view.mypg;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.*;

import db.InbodyData;
import db.MyPageDAO;
import db.TargetData;
import db.UsersData;
import view.logn.Session;

public class MyPage extends JFrame {
	
	private JLabel myWeight, mySkmu, myBodyfat, myCID;
	private JLabel wishweight, myWishWeihgt, myWishBMI, wishskmu, myWishskmu, wishbodyfat, myWishbodyfat, wishsleep, sleepTime;
	private JLabel wishCid,exeLevel;
	private String myHeightText, myWeightText, exeLevelText, myWishWeihgtText;
	private double myWishWeihgtNum, myBMI_text, myWishBMI_text;
	private JLabel myBMI;
	private Session session;
	private UsersData usersData;
	private MyPageDAO myPageDAO;
	private InbodyData inbodyData;
	private TargetData targetData;
	private BMIimg bmiimg;
	private ImageIcon icon, icon2;
	private JButton bodyimg, wishbodyimg;
	
	// GridBagLayout에 컴포넌트를 추가하는 메소드
	private void addGridBagComponent(Container container, Component component, int gridx, int gridy, int anchor) {
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridx = gridx;
	    gbc.gridy = gridy;
	    gbc.anchor = anchor;
	    container.add(component, gbc);
	}
	
	public MyPage() {
		usersData=new MyPageDAO().loginData();
		inbodyData=new MyPageDAO().inbodyData();
		targetData=new MyPageDAO().targetData();
		bmiimg = new BMIimg();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 630);
		setTitle("MyPage");
		setResizable(false);
		
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		////////head////////////////////////////////////////////////////////////////
		JPanel head = new JPanel();
		head.setBackground(new Color(255, 255, 255));
		contentPane.add(head, BorderLayout.NORTH);
		GridBagLayout gbl_head = new GridBagLayout();
		gbl_head.columnWidths = new int[]{61, 188, 76, 139, 97, 85, 0};
		gbl_head.rowHeights = new int[]{50, 0};
		gbl_head.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_head.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		head.setLayout(gbl_head);
		
		JButton btn_back = new JButton("←");		//가로, 세로 값 지정
		btn_back.setBackground(new Color(255, 255, 255));
		btn_back.setPreferredSize(new Dimension(50, 50));
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); //뒤로가기 선택 시 창꺼짐
			}
		});
		GridBagConstraints gbc_btn_back = new GridBagConstraints();
		gbc_btn_back.insets = new Insets(0, 0, 0, 5);
		gbc_btn_back.gridx = 0;
		gbc_btn_back.gridy = 0;
		head.add(btn_back, gbc_btn_back);
		
		JLabel title = new JLabel("My Page");
		title.setFont(new Font("굴림", Font.BOLD, 20));
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.insets = new Insets(0, 0, 0, 5);
		gbc_title.gridx = 2;
		gbc_title.gridy = 0;
		head.add(title, gbc_title);
		
		JButton btn_inbody = new JButton("인바디 입력");
		btn_inbody.setBackground(new Color(255, 255, 255));
		//인바디 입력 다이얼로그창 생성
		btn_inbody.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        InbodyDialog dialog = new InbodyDialog(MyPage.this);
		        dialog.setVisible(true);
		    }
		});
		
		GridBagConstraints gbc_btn_inbody = new GridBagConstraints();
		gbc_btn_inbody.anchor = GridBagConstraints.WEST;
		gbc_btn_inbody.insets = new Insets(0, 0, 0, 5);
		gbc_btn_inbody.gridx = 4;
		gbc_btn_inbody.gridy = 0;
		head.add(btn_inbody, gbc_btn_inbody);
		
		JButton btnTarget = new JButton("목표 입력");
		btnTarget.setBackground(new Color(255, 255, 255));
		//목표입력 다이얼로그창 생성
		btnTarget.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        TargetDialog dialog = new TargetDialog(MyPage.this);
		        dialog.setVisible(true);
		    }
		});

		GridBagConstraints gbcBtnTarget = new GridBagConstraints();
		gbcBtnTarget.anchor = GridBagConstraints.WEST;
		gbcBtnTarget.gridx = 5;
		gbcBtnTarget.gridy = 0;
		head.add(btnTarget, gbcBtnTarget);
		
		/////////main 현재 몸상태//////////////////////////////////////////////////////////////
		JPanel now = new JPanel();
		now.setBackground(new Color(255, 255, 255));
		contentPane.add(now, BorderLayout.CENTER); 
		GridBagLayout gbl_now = new GridBagLayout();
		gbl_now.columnWidths = new int[]{182, 100, 71, 104, 0};
		gbl_now.rowHeights = new int[]{34, 225, 0};
		gbl_now.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_now.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		now.setLayout(gbl_now);
		
		JLabel profilelabel = new JLabel("프로필");
		profilelabel.setFont(new Font("굴림", Font.BOLD, 14));
		GridBagConstraints gbc_profilelabel = new GridBagConstraints();
		gbc_profilelabel.anchor = GridBagConstraints.EAST;
		gbc_profilelabel.insets = new Insets(0, 0, 5, 5);
		gbc_profilelabel.gridx = 0;
		gbc_profilelabel.gridy = 0;
		now.add(profilelabel, gbc_profilelabel);

		//현재 인바디 정보 제공
		JPanel bodyprofile = new JPanel();
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
		
		JLabel heght = new JLabel("키");
		heght.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_heght = new GridBagConstraints();
		gbc_heght.anchor = GridBagConstraints.NORTHWEST;
		gbc_heght.insets = new Insets(0, 0, 5, 5);
		gbc_heght.gridx = 0;
		gbc_heght.gridy = 0;
		bodyprofile.add(heght, gbc_heght);
		
		JButton myHeightButton = new JButton(usersData.getUser_height());
		myHeightButton.setBackground(new Color(255, 255, 255));
		myHeightButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	HeightDialog dialog = new HeightDialog(MyPage.this, myHeightButton, myBMI); // MyPage 인스턴스를 전달하여 참조
		        dialog.setVisible(true);
		    }
		});

		myHeightButton.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_myHeight = new GridBagConstraints();
		gbc_myHeight.anchor = GridBagConstraints.EAST;
		gbc_myHeight.insets = new Insets(0, 0, 5, 5);
		gbc_myHeight.gridx = 1;
		gbc_myHeight.gridy = 0;
		bodyprofile.add(myHeightButton, gbc_myHeight);
		
		JLabel height_unit = new JLabel("cm");
		GridBagConstraints gbc_height_unit = new GridBagConstraints();
		gbc_height_unit.insets = new Insets(0, 0, 5, 0);
		gbc_height_unit.gridx = 2;
		gbc_height_unit.gridy = 0;
		bodyprofile.add(height_unit, gbc_height_unit);
		
		JLabel weight = new JLabel("몸무게");
		weight.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_weight = new GridBagConstraints();
		gbc_weight.insets = new Insets(0, 0, 5, 5);
		gbc_weight.anchor = GridBagConstraints.NORTHWEST;
		gbc_weight.gridx = 0;
		gbc_weight.gridy = 1;
		bodyprofile.add(weight, gbc_weight);
		
		myWeight = new JLabel(inbodyData.getInbody_weight());
		GridBagConstraints gbc_myWeight = new GridBagConstraints();
		gbc_myWeight.anchor = GridBagConstraints.EAST;
		gbc_myWeight.insets = new Insets(0, 0, 5, 5);
		gbc_myWeight.gridx = 1;
		gbc_myWeight.gridy = 1;
		bodyprofile.add(myWeight, gbc_myWeight);
		
		JLabel weight_unit = new JLabel("kg");
		GridBagConstraints gbc_weight_unit = new GridBagConstraints();
		gbc_weight_unit.insets = new Insets(0, 0, 5, 0);
		gbc_weight_unit.gridx = 2;
		gbc_weight_unit.gridy = 1;
		bodyprofile.add(weight_unit, gbc_weight_unit);
		
		JLabel bmi = new JLabel("BMI");
		GridBagConstraints gbc_bmi = new GridBagConstraints();
		gbc_bmi.anchor = GridBagConstraints.WEST;
		gbc_bmi.insets = new Insets(0, 0, 5, 5);
		gbc_bmi.gridx = 0;
		gbc_bmi.gridy = 2;
		bodyprofile.add(bmi, gbc_bmi);
		
		//현재 몸무게(myHeightButton) 값 가져오기
		myHeightText = myHeightButton.getText();
		double myHeightNum = Double.parseDouble(myHeightText);
		//현재 몸무게(myWeight) 값 가져오기
		myWeightText = myWeight.getText();
		double myWeightNum = Double.parseDouble(myWeightText);
		//BMI 계산
		String MyBMICal = String.format("%.1f", myWeightNum / ((myHeightNum / 100) * (myHeightNum / 100)));
				
		myBMI = new JLabel(MyBMICal);
		GridBagConstraints gbc_MyBMI = new GridBagConstraints();
		gbc_MyBMI.anchor = GridBagConstraints.EAST;
		gbc_MyBMI.insets = new Insets(0, 0, 5, 5);
		gbc_MyBMI.gridx = 1;
		gbc_MyBMI.gridy = 2;
		bodyprofile.add(myBMI, gbc_MyBMI);
		
		JLabel skmu = new JLabel("골격근량");
		GridBagConstraints gbc_skmu = new GridBagConstraints();
		gbc_skmu.anchor = GridBagConstraints.WEST;
		gbc_skmu.insets = new Insets(0, 0, 5, 5);
		gbc_skmu.gridx = 0;
		gbc_skmu.gridy = 3;
		bodyprofile.add(skmu, gbc_skmu);
		
		mySkmu = new JLabel(inbodyData.getInbody_muscle());
		GridBagConstraints gbc_mySkmu = new GridBagConstraints();
		gbc_mySkmu.anchor = GridBagConstraints.EAST;
		gbc_mySkmu.insets = new Insets(0, 0, 5, 5);
		gbc_mySkmu.gridx = 1;
		gbc_mySkmu.gridy = 3;
		bodyprofile.add(mySkmu, gbc_mySkmu);
		
		JLabel skmu_unit = new JLabel("kg");
		GridBagConstraints gbc_skmu_unit = new GridBagConstraints();
		gbc_skmu_unit.insets = new Insets(0, 0, 5, 0);
		gbc_skmu_unit.gridx = 2;
		gbc_skmu_unit.gridy = 3;
		bodyprofile.add(skmu_unit, gbc_skmu_unit);
		
		JLabel bodyfat = new JLabel("체지방량");
		GridBagConstraints gbc_bodyfat = new GridBagConstraints();
		gbc_bodyfat.anchor = GridBagConstraints.WEST;
		gbc_bodyfat.insets = new Insets(0, 0, 5, 5);
		gbc_bodyfat.gridx = 0;
		gbc_bodyfat.gridy = 4;
		bodyprofile.add(bodyfat, gbc_bodyfat);
		
		myBodyfat = new JLabel(inbodyData.getInbody_bodyfat());
		GridBagConstraints gbc_myBodyfat = new GridBagConstraints();
		gbc_myBodyfat.anchor = GridBagConstraints.EAST;
		gbc_myBodyfat.insets = new Insets(0, 0, 5, 5);
		gbc_myBodyfat.gridx = 1;
		gbc_myBodyfat.gridy = 4;
		bodyprofile.add(myBodyfat, gbc_myBodyfat);
		
		JLabel bodyfat_unit = new JLabel("kg");
		GridBagConstraints gbc_bodyfat_unit = new GridBagConstraints();
		gbc_bodyfat_unit.insets = new Insets(0, 0, 5, 0);
		gbc_bodyfat_unit.gridx = 2;
		gbc_bodyfat_unit.gridy = 4;
		bodyprofile.add(bodyfat_unit, gbc_bodyfat_unit);
		
		JLabel cid = new JLabel("CID 유형");
		GridBagConstraints gbc_cid = new GridBagConstraints();
		gbc_cid.anchor = GridBagConstraints.WEST;
		gbc_cid.insets = new Insets(0, 0, 0, 5);
		gbc_cid.gridx = 0;
		gbc_cid.gridy = 5;
		bodyprofile.add(cid, gbc_cid);
		
		myCID = new JLabel(inbodyData.getInbody_cid());
		GridBagConstraints gbc_myCID = new GridBagConstraints();
		gbc_myCID.insets = new Insets(0, 0, 0, 5);
		gbc_myCID.anchor = GridBagConstraints.EAST;
		gbc_myCID.gridx = 1;
		gbc_myCID.gridy = 5;
		bodyprofile.add(myCID, gbc_myCID);
		
		JLabel cid_unit = new JLabel("자형");
		GridBagConstraints gbc_cid_unit = new GridBagConstraints();
		gbc_cid_unit.gridx = 2;
		gbc_cid_unit.gridy = 5;
		bodyprofile.add(cid_unit, gbc_cid_unit);
		
		
		
		//현재 BMI에 해당하는 몸 이미지 제공
		JPanel nowbody = new JPanel();
		nowbody.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_nowbody = new GridBagConstraints();
		gbc_nowbody.anchor = GridBagConstraints.NORTHWEST;
		gbc_nowbody.insets = new Insets(0, 0, 0, 5);
		gbc_nowbody.gridx = 1;
		gbc_nowbody.gridy = 1;
		now.add(nowbody, gbc_nowbody);
		
		
		myBMI_text = Double.parseDouble(myBMI.getText());
		System.out.println(myBMI_text);
		System.out.println(usersData.getUser_gender());
		
		bodyimg = new JButton("");
		bodyimg.setBackground(new Color(255, 255, 255));
		bodyimg.setPreferredSize(new Dimension(90, 215)); // 너비와 높이 지정
		icon = new ImageIcon(MyPage.class.getResource(bmiimg.bmi_img(usersData.getUser_gender(), myBMI_text))); //이미지 삽입
		Image image = icon.getImage().getScaledInstance(90, 215, Image.SCALE_SMOOTH); // 이미지 크기 조절
		bodyimg.setIcon(new ImageIcon(image));
		nowbody.add(bodyimg);
		
		//////main 목표////////////////////////////////////////////////////////
		JPanel wish = new JPanel();
		wish.setBackground(new Color(255, 255, 255));
		contentPane.add(wish, BorderLayout.SOUTH);
		GridBagLayout gbl_wish = new GridBagLayout();
		gbl_wish.columnWidths = new int[]{182, 100, 71, 104, 0};
		gbl_wish.rowHeights = new int[]{34, 49, 120, 0};
		gbl_wish.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_wish.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		wish.setLayout(gbl_wish);
		
		JLabel wishlabel = new JLabel("목표");
		wishlabel.setFont(new Font("굴림", Font.BOLD, 14));
		GridBagConstraints gbc_wishlabel = new GridBagConstraints();
		gbc_wishlabel.anchor = GridBagConstraints.EAST;
		gbc_wishlabel.insets = new Insets(0, 0, 5, 5);
		gbc_wishlabel.gridx = 0;
		gbc_wishlabel.gridy = 0;
		wish.add(wishlabel, gbc_wishlabel);
		
		//목표하는 CID와 운동강도 정보
		JPanel wishLevel = new JPanel();
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
	
		GridBagConstraints gbc_wishCid = new GridBagConstraints();
		gbc_wishCid.anchor = GridBagConstraints.NORTHWEST;
		gbc_wishCid.insets = new Insets(0, 0, 0, 5);
		gbc_wishCid.gridx = 0;
		gbc_wishCid.gridy = 0;

		
		GridBagConstraints gbc_exeLevel = new GridBagConstraints();
		gbc_exeLevel.anchor = GridBagConstraints.NORTHWEST;
		gbc_exeLevel.gridx = 2;
		gbc_exeLevel.gridy = 0;
		
		JPanel wishCID_data = new JPanel();
		GridBagConstraints gbc_wishCID_data = new GridBagConstraints();
		gbc_wishCID_data.gridheight = 3;
		gbc_wishCID_data.insets = new Insets(0, 0, 0, 5);
		gbc_wishCID_data.fill = GridBagConstraints.BOTH;
		gbc_wishCID_data.gridx = 0;
		gbc_wishCID_data.gridy = 0;
		wishLevel.add(wishCID_data, gbc_wishCID_data);
		wishCID_data.setLayout(new BorderLayout(0, 0));
		
		JLabel wishCID_title = new JLabel("CID 목표");
		wishCID_title.setHorizontalAlignment(SwingConstants.CENTER);
		wishCID_data.add(wishCID_title);
		
		wishCid = new JLabel(targetData.getTarget_cid());
		wishCid.setHorizontalAlignment(SwingConstants.CENTER);
		wishCID_data.add(wishCid, BorderLayout.SOUTH);
		
		JPanel wishexeLevel_data = new JPanel();
		GridBagConstraints gbc_wishexeLevel_data = new GridBagConstraints();
		gbc_wishexeLevel_data.gridheight = 3;
		gbc_wishexeLevel_data.fill = GridBagConstraints.BOTH;
		gbc_wishexeLevel_data.gridx = 1;
		gbc_wishexeLevel_data.gridy = 0;
		wishLevel.add(wishexeLevel_data, gbc_wishexeLevel_data);
		wishexeLevel_data.setLayout(new BorderLayout(0, 0));
		
		JLabel wishexeLevel_title = new JLabel("운동강도");
		wishexeLevel_title.setHorizontalAlignment(SwingConstants.CENTER);
		wishexeLevel_data.add(wishexeLevel_title, BorderLayout.CENTER);
		
		
		if(targetData.getTargrt_level()==null) {
			exeLevelText = " ";
		}else {
			switch (Integer.parseInt(targetData.getTargrt_level())) {
			case 0:
				exeLevelText = "하";
				break;
			case 1:
				exeLevelText = "중";
				break;
			case 2:
				exeLevelText = "상";
				break;
			}
		}
		
		exeLevel = new JLabel(exeLevelText);
		exeLevel.setHorizontalAlignment(SwingConstants.CENTER);
		wishexeLevel_data.add(exeLevel, BorderLayout.SOUTH);
		
		
		//목표 인바디 정보 제공
		JPanel wishbodyprofile = new JPanel();
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
		
		wishweight = new JLabel("몸무게");
		wishweight.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_wishweight = new GridBagConstraints();
		gbc_wishweight.anchor = GridBagConstraints.NORTHWEST;
		gbc_wishweight.insets = new Insets(0, 0, 5, 5);
		gbc_wishweight.gridx = 0;
		gbc_wishweight.gridy = 0;
		wishbodyprofile.add(wishweight, gbc_wishweight);
		
		myWishWeihgt = new JLabel(targetData.getTarget_weight());
		GridBagConstraints gbc_myWishWeihgt = new GridBagConstraints();
		gbc_myWishWeihgt.anchor = GridBagConstraints.EAST;
		gbc_myWishWeihgt.insets = new Insets(0, 0, 5, 5);
		gbc_myWishWeihgt.gridx = 1;
		gbc_myWishWeihgt.gridy = 0;
		wishbodyprofile.add(myWishWeihgt, gbc_myWishWeihgt);
		
		JLabel wishweight_unit = new JLabel("kg");
		GridBagConstraints gbc_wishweight_unit = new GridBagConstraints();
		gbc_wishweight_unit.insets = new Insets(0, 0, 5, 0);
		gbc_wishweight_unit.gridx = 2;
		gbc_wishweight_unit.gridy = 0;
		wishbodyprofile.add(wishweight_unit, gbc_wishweight_unit);
		
		//bmi 영역 추가
		JLabel wishBmi = new JLabel("BMI");
		GridBagConstraints gbc_whisBmi = new GridBagConstraints();
		gbc_whisBmi.anchor = GridBagConstraints.WEST;
		gbc_whisBmi.insets = new Insets(0, 0, 5, 5);
		gbc_whisBmi.gridx = 0;
		gbc_whisBmi.gridy = 1;
		wishbodyprofile.add(wishBmi, gbc_whisBmi);
		
		//현재 몸무게(myWishWeihgt) 값 가져오기
		if(targetData.getTarget_weight()==null) {
			myWishWeihgtNum=0;
			
		}else {
			myWishWeihgtText = myWishWeihgt.getText();
			myWishWeihgtNum = Double.parseDouble(myWishWeihgtText);
		}
		//BMI 계산
		String myWishBMICal = String.format("%.1f", myWishWeihgtNum / ((myHeightNum / 100) * (myHeightNum / 100)));
				
		myWishBMI = new JLabel(myWishBMICal);
		GridBagConstraints gbc_MyWIshBMI = new GridBagConstraints();
		gbc_MyWIshBMI.anchor = GridBagConstraints.EAST;
		gbc_MyWIshBMI.insets = new Insets(0, 0, 5, 5);
		gbc_MyWIshBMI.gridx = 1;
		gbc_MyWIshBMI.gridy = 1;
		wishbodyprofile.add(myWishBMI , gbc_MyWIshBMI);
		
		
		wishskmu = new JLabel("골격근량");
		GridBagConstraints gbc_wishskmu = new GridBagConstraints();
		gbc_wishskmu.anchor = GridBagConstraints.WEST;
		gbc_wishskmu.insets = new Insets(0, 0, 5, 5);
		gbc_wishskmu.gridx = 0;
		gbc_wishskmu.gridy = 2;
		wishbodyprofile.add(wishskmu, gbc_wishskmu);
		
		myWishskmu = new JLabel(targetData.getTarget_muscle());
		GridBagConstraints gbc_myWishskmu = new GridBagConstraints();
		gbc_myWishskmu.anchor = GridBagConstraints.EAST;
		gbc_myWishskmu.insets = new Insets(0, 0, 5, 5);
		gbc_myWishskmu.gridx = 1;
		gbc_myWishskmu.gridy = 2;
		wishbodyprofile.add(myWishskmu, gbc_myWishskmu);
		
		JLabel wishWeihgt_unit = new JLabel("kg");
		GridBagConstraints gbc_wishWeihgt_unit = new GridBagConstraints();
		gbc_wishWeihgt_unit.insets = new Insets(0, 0, 5, 0);
		gbc_wishWeihgt_unit.gridx = 2;
		gbc_wishWeihgt_unit.gridy = 2;
		wishbodyprofile.add(wishWeihgt_unit, gbc_wishWeihgt_unit);
		
		wishbodyfat = new JLabel("체지방량");
		GridBagConstraints gbc_wishbodyfat = new GridBagConstraints();
		gbc_wishbodyfat.anchor = GridBagConstraints.WEST;
		gbc_wishbodyfat.insets = new Insets(0, 0, 5, 5);
		gbc_wishbodyfat.gridx = 0;
		gbc_wishbodyfat.gridy = 3;
		wishbodyprofile.add(wishbodyfat, gbc_wishbodyfat);
		
		myWishbodyfat = new JLabel(targetData.getTarget_bodyfat());
		GridBagConstraints gbc_myWishbodyfat = new GridBagConstraints();
		gbc_myWishbodyfat.anchor = GridBagConstraints.EAST;
		gbc_myWishbodyfat.insets = new Insets(0, 0, 5, 5);
		gbc_myWishbodyfat.gridx = 1;
		gbc_myWishbodyfat.gridy = 3;
		wishbodyprofile.add(myWishbodyfat, gbc_myWishbodyfat);
		
		JLabel wishbodyfat_unit = new JLabel("kg");
		GridBagConstraints gbc_wishbodyfat_unit = new GridBagConstraints();
		gbc_wishbodyfat_unit.insets = new Insets(0, 0, 5, 0);
		gbc_wishbodyfat_unit.gridx = 2;
		gbc_wishbodyfat_unit.gridy = 3;
		wishbodyprofile.add(wishbodyfat_unit, gbc_wishbodyfat_unit);
		
		wishsleep = new JLabel("목표 수면시간");
		GridBagConstraints gbc_wishsleep = new GridBagConstraints();
		gbc_wishsleep.anchor = GridBagConstraints.WEST;
		gbc_wishsleep.insets = new Insets(0, 0, 0, 5);
		gbc_wishsleep.gridx = 0;
		gbc_wishsleep.gridy = 4;
		wishbodyprofile.add(wishsleep, gbc_wishsleep);
		
		sleepTime = new JLabel(targetData.getTarget_sleep());
		GridBagConstraints gbc_sleepTime = new GridBagConstraints();
		gbc_sleepTime.insets = new Insets(0, 0, 0, 5);
		gbc_sleepTime.anchor = GridBagConstraints.EAST;
		gbc_sleepTime.gridx = 1;
		gbc_sleepTime.gridy = 4;
		wishbodyprofile.add(sleepTime, gbc_sleepTime);
		
		JLabel sleepTime_unit = new JLabel("시간");
		GridBagConstraints gbc_sleepTime_unit = new GridBagConstraints();
		gbc_sleepTime_unit.gridx = 2;
		gbc_sleepTime_unit.gridy = 4;
		wishbodyprofile.add(sleepTime_unit, gbc_sleepTime_unit);
		
		//목표하는 BMI에 해당하는 몸 이미지 제공
		JPanel wishbody = new JPanel();
		wishbody.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_wishbody = new GridBagConstraints();
		gbc_wishbody.gridheight = 2;
		gbc_wishbody.anchor = GridBagConstraints.NORTHWEST;
		gbc_wishbody.insets = new Insets(0, 0, 0, 5);
		gbc_wishbody.gridx = 1;
		gbc_wishbody.gridy = 1;
		wish.add(wishbody, gbc_wishbody);
		
		myWishBMI_text = Double.parseDouble(myWishBMI.getText());
		System.out.println(myWishBMI_text);
		
		wishbodyimg = new JButton("");
		wishbodyimg.setBackground(new Color(255, 255, 255));
		wishbodyimg.setPreferredSize(new Dimension(90, 215));
		icon2 = new ImageIcon(MyPage.class.getResource(bmiimg.bmi_img(usersData.getUser_gender(), myWishBMI_text))); //버튼에 이미지 삽입
		Image image2 = icon2.getImage().getScaledInstance(90, 215, Image.SCALE_SMOOTH);
		wishbodyimg.setIcon(new ImageIcon(image2));
		wishbody.add(wishbodyimg);

		setVisible(true);
	}
	
	

	public JButton getBodyimg() {
		return bodyimg;
	}

	public void setBodyimg(JButton bodyimg) {
		this.bodyimg = bodyimg;
	}

	public JButton getWishbodyimg() {
		return wishbodyimg;
	}

	public void setWishbodyimg(JButton wishbodyimg) {
		this.wishbodyimg = wishbodyimg;
	}
	
	public ImageIcon getIcon() {
		return icon;
	}

	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}

	public ImageIcon getIcon2() {
		return icon2;
	}

	public void setIcon2(ImageIcon icon2) {
		this.icon2 = icon2;
	}

	public JLabel getMyWeight() {
		return myWeight;
	}

	public JLabel getMySkmu() {
		return mySkmu;
	}

	public JLabel getMyBodyfat() {
		return myBodyfat;
	}

	public JLabel getMyCID() {
		return myCID;
	}

	public JLabel getWishweight() {
		return wishweight;
	}

	public JLabel getMyWishWeihgt() {
		return myWishWeihgt;
	}

	public JLabel getWishskmu() {
		return wishskmu;
	}

	public JLabel getMyWishskmu() {
		return myWishskmu;
	}

	public JLabel getWishbodyfat() {
		return wishbodyfat;
	}

	public JLabel getMyWishbodyfat() {
		return myWishbodyfat;
	}

	public JLabel getWishsleep() {
		return wishsleep;
	}

	public JLabel getSleepTime() {
		return sleepTime;
	}

	public JLabel getWishCid() {
		return wishCid;
	}

	public JLabel getExeLevel() {
		return exeLevel;
	}

	public String getMyHeightText() {
		return myHeightText;
	}

	public String getMyWeightText() {
		return myWeightText;
	}

	public JLabel getMyBMI() {
		return myBMI;
	}

	public void setMyBMI(JLabel myBMI) {
		this.myBMI = myBMI;
	}

	public JLabel getMyWishBMI() {
		return myWishBMI;
	}
	
	public void setMyWishBMI(JLabel myWishBMI) {
		this.myWishBMI = myWishBMI;
	}

	public String getmyHeightText() {
		return myHeightText;
	}
	//키 변경 시 값 데이트
	public void setmyHeightText(String myHeightText) {
		this.myHeightText=myHeightText;
	}
	
	public String getmyWeightText() {
		return myWeightText;
	}
	//몸무게 변경 시 값 데이트
	public void setmyWeightText(String myWeightText) {
		this.myWeightText=myWeightText;
	}
	public void setBMI(String bmi) {
	    myBMI.setText(bmi);
	}
	
}
