package service;

import view.diet.Diet;
import view.mypg.MyPage;

public class MypgService {
	public void Exceute() {
		System.out.println("DietService È£Ãâ");
		Thread thread=new Thread(()->{
			new MyPage();
		});
		thread.start();
	}
}
