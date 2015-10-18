package GameComponents;

public class Robot {
	private String color;
	private TwoTuple location;
	
	public Robot(String col, TwoTuple loc){
		this.color = col;
		this.location = loc;
	}
	
	public String getColor(){
		return color;
	}
	
	public int getRow(){
		return location.getAValue();
	}
	
	public int getCol(){
		return location.getBValue();
	}
	
	public void setColor(String col){
		this.color = col;
	}
	
	public TwoTuple getLocation(){
		return location;
	}
	
	public void setLocation(TwoTuple loc){
		this.location = loc;
	}
	
	public String getFirstLetter(){
		return color.substring(0,1);
	}
	
	public String toString(){
		return color + " robot located at row " + location.getAValue()+ ", column " + location.getBValue();
	}

}
