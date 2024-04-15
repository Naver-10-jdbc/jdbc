package view.mypg;

public class UpdateBMI {

	private String myBMICal;
	
	public UpdateBMI() {}
	
	//바꾼 내용
	public String updateBMI(MyPage mypage) {
		//MyPage myPage = new MyPage();
		MyPage myPage =mypage;
        // 사용자 키 가져오기
        String weight = myPage.getmyWeightText(); // MyPage 클래스의 인스턴스를 직접 사용하여 키 정보를 가져옴
        double myWeightNum = Double.parseDouble(weight);
        String myHeightText = myPage.getmyHeightText(); // 여기서 변경된 키 값을 가져옴
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