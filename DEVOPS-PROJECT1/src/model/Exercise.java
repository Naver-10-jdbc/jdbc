package model;

public class Exercise { 
	String name;
	int parts;
	int level;
	String detail;
	byte[] img;
	int time; 
	
	public Exercise(String name,String detail) {
		this.name=name;
		this.detail=detail;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getParts() {
		return parts;
	}
	public void setParts(int parts) {
		this.parts = parts;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public byte[] getImg() {
		return img;
	}
	public void setImg(byte[] img) {
		this.img = img;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
}
