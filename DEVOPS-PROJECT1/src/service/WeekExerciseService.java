package service;

import view.diet.Diet;

public class WeekExerciseService {
	public void Exceute() {
		System.out.println("WeekExerciseService È£Ãâ");
		Thread thread=new Thread(()->{
			new WeekExerciseService();
		});
		thread.start();
	}
}
