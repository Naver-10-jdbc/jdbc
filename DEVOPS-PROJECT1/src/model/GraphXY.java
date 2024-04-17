package model;

import java.security.KeyStore.PrivateKeyEntry;

public class GraphXY {
	private int x;
	private int y;
	public GraphXY(int x,int y) {
		this.x=x;
		this.y=y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}
