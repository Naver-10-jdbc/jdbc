package view.mypg;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

public class UpdateBMI {

	private String myBMICal;
	
	public UpdateBMI() {}
	
	//�ٲ� ����
	public String updateBMI(MyPage mypage) {
		//MyPage myPage = new MyPage();
		MyPage myPage =mypage;
        // ����� Ű ��������
        String weight = myPage.getmyWeightText(); // MyPage Ŭ������ �ν��Ͻ��� ���� ����Ͽ� ������ ������ ������
        double myWeightNum = Double.parseDouble(weight);
        String myHeightText = myPage.getmyHeightText(); // ���⼭ ����� Ű ���� ������
        double myHeightNum = Double.parseDouble(myHeightText);
        String myBMICal = String.format("%.1f", myWeightNum / ((myHeightNum / 100) * (myHeightNum / 100)));
        return myBMICal;
    }

	public String getMyBMICal() {
		return myBMICal;
	}

	public void setMyBMICal(String myBMICal) {
		this.myBMICal = myBMICal;
	}
		
}