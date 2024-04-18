package service;

import view.diet.Diet;

public class DietService {
	public void Exceute() {
		System.out.println("DietService È£Ãâ");
		Thread thread=new Thread(()->{
			new Diet();
		});
		thread.start();
	}
}
