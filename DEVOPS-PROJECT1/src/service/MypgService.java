package service;

import view.diet.Diet;
import view.mypg.MyPage;

public class MypgService {
	public void Exceute() {
		System.out.println("DietService ȣ��");
		Thread thread=new Thread(()->{
			new MyPage();
		});
		thread.start();
	}
}
