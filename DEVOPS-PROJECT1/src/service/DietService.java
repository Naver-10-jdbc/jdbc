package service;

import view.diet.Diet;

public class DietService {
	public void Exceute() {
		System.out.println("DietService ȣ��");
		Thread thread=new Thread(()->{
			new Diet();
		});
		thread.start();
	}
}
