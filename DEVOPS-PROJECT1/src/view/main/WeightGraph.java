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
		//���� ����, ������� ���
		String monthValue=LocalDate.now().getMonthValue()+"��";
		//�׷��� ����
		XYChart chart=QuickChart.getChart("������ ��� ��Ȳ("+monthValue+")", "��¥", "������", "�Ϻ�", xData, yData);
		new SwingWrapper(chart).displayChart();

	}
}
