package service;

import java.util.List;

import db.GoalDAO;
import model.GraphXY;
import view.main.WeightGraph;

public class GrpahService {
	public GrpahService() {
		
	}
	public void show_graph() {
		 Thread t = new Thread(new Runnable() {
			 @Override
			 public void run() {
			  List<GraphXY>list=new GoalDAO().select_Weight(4);
			  System.out.println("ÁÂÇ¥°³¼ö:"+list.size());
			  if(list.size()==0||list==null) {
				  new WeightGraph(new double[] {0},new double[] {0});
			  }else {
				  double x[]=new double[list.size()];
				  for(int i=0; i<x.length; i++) x[i]=(double)list.get(i).getX();
				  double y[]=new double[list.size()];
				  for(int i=0; i<y.length; i++) y[i]=(double)list.get(i).getY();
				  new WeightGraph(x, y);
			  }
			}
		 });
		 t.start();
	}
}
