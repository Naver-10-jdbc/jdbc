package service;

import view.diet.Diet;

public class WeekExerciseService {
	public void Exceute() {
		System.out.println("WeekExerciseService ȣ��");
		Thread thread=new Thread(()->{
			new WeekExerciseService();
		});
		thread.start();
	}
}
