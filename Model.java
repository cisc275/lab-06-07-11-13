/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 *  detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/

public class Model{
	int x;
	int y;
	Direction direct;
	final int XIncr;
	final int YIncr;
	int switchX = 0;
	int switchY = 0;
	final static int frameWidth = 500;
	final static int frameHeight = 300;
	final static int imgWidth = 165;
	final static int imgHeight = 165;
	int thisXIncr;
	int thisYIncr;
	
	public Model(int width, int height, int imageWidth, int imageHeight) {
		x = 0;
		y = 0;
		XIncr = 8;
		YIncr = 2;
		direct = Direction.SOUTHEAST;
		thisXIncr = 8;
		thisYIncr = 2;		
	}
	
	public void updateLocationAndDirection() {
		x+=thisXIncr;
		y+=thisYIncr;
    	if(x>(frameWidth - imgWidth) || x<-20) {
    		thisXIncr = (-1)*thisXIncr;
    	}
    	if(y>(frameHeight - imgHeight - 45) || y<-20) {
    		thisYIncr = (-1)*thisYIncr;
    	}
    	
    	//change direction 
    	if(thisYIncr<0 && thisXIncr>0) {
    		direct = Direction.SOUTHEAST;
    	}
    	if(thisYIncr>0 && thisXIncr<0) {
    		direct = Direction.NORTHWEST;
    	}
    	if(thisYIncr<0 && thisXIncr<0) {
    		direct = Direction.SOUTHWEST;
    	}
    	if(thisYIncr>0 && thisXIncr>0) {
    		direct = Direction.NORTHEAST;

    	}
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


	public Direction getDirect() {
		
		return direct;
	}


	public void setDirect(Direction e) {
		direct = e;
	}
	public void setXIncr(int x) {
		thisXIncr = x;
	}
	
	public void setYIncr(int y) {
		thisYIncr = y;
	}
	
	public int getXIncr() {
		return thisXIncr;
	}
	
	public int getYIncr() {
		return thisYIncr;
	}
	
	
}