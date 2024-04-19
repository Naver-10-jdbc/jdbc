package service;

import view.diet.Diet;
import view.week_ex.Week_Exercise;

public class WeekExerciseService {
	public void Exceute() {
		System.out.println("WeekExerciseService È£Ãâ");
		Thread thread=new Thread(()->{
			new Week_Exercise();
		});
		thread.start();
	}
}
