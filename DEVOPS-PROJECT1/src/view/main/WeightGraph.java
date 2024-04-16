package view.main;

import java.time.LocalDate;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

public class WeightGraph {
	double xData[];
	double yData[];
	public WeightGraph(double x[],double y[]) {
		this.xData=x;
		this.yData=y;
		//현재 기준, 몇월인지 출력
		String monthValue=LocalDate.now().getMonthValue()+"월";
		//그래프 띄우기
		XYChart chart=QuickChart.getChart("몸무게 기록 현황("+monthValue+")", "날짜", "몸무게", "일별", xData, yData);
		new SwingWrapper(chart).displayChart();

	}
}
