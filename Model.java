/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 * <p>
 * has methods to
 * detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/

public class Model {
    int xloc = 0;
    int yloc = 0;
    int xIncr = 8;
    int yIncr = 2;
    Direction d = Direction.SOUTHEAST;
    int frameWidth;
    int frameHeight;
    int imgWidth;
    int imgHeight;
    int xPause;
    int yPause;

    public int getxPause() {
		return xPause;
	}

	public void setxPause(int xPause) {
		this.xPause = xPause;
	}

	public int getyPause() {
		return yPause;
	}

	public void setyPause(int yPause) {
		this.yPause = yPause;
	}

	public Model(int width, int height, int imageWidth, int imageHeight) {
        this.frameWidth = width;
        this.frameHeight = height;
        this.imgWidth = imageWidth;
        this.imgHeight = imageHeight;
    }

    public void updateLocationAndDirection() {
        if (xloc < 0 || xloc > frameWidth - imgWidth) {
            xIncr = -xIncr;
        }

        if (yloc < 0 || yloc > frameHeight - imgHeight) {
            yIncr = -yIncr;
        }

        switch (d) {

        case SOUTHWEST:
        	if ((xloc < 0)&&(yloc > frameHeight - imgHeight)){
                d = Direction.NORTHEAST;
        	}
        	else if (xloc < 0 || xloc > frameWidth - imgWidth) {
                d = Direction.SOUTHEAST;
            }
            else if (yloc < 0 || yloc > frameHeight - imgHeight) {
                d = Direction.NORTHWEST;
            }

            break;

        case SOUTHEAST:
        	if ((xloc > frameWidth - imgWidth)&&(yloc > frameHeight - imgHeight)){
                d = Direction.NORTHWEST;
        	}
        	else if (xloc < 0 || xloc > frameWidth - imgWidth) {
                d = Direction.SOUTHWEST;
            }
        	else if (yloc < 0 || yloc > frameHeight - imgHeight) {
                d = Direction.NORTHEAST;
            }

            break;

        case NORTHEAST:
        	if ((xloc > frameWidth - imgWidth)&&(yloc < 0 )){
                d = Direction.SOUTHWEST;
        	}
        	else if (xloc < 0 || xloc > frameWidth - imgWidth) {
                d = Direction.NORTHWEST;
                System.out.println("NE to NW");

            }
            else if (yloc < 0 || yloc > frameHeight - imgHeight) {
                d = Direction.SOUTHEAST;

            }

            break;

        case NORTHWEST:
        	if ((xloc < 0)&&(yloc < 0 )){
                d = Direction.SOUTHEAST;
        	}
        	else if (xloc < 0 || xloc > frameWidth - imgWidth) {
                d = Direction.NORTHEAST;
            }
            else if (yloc < 0 || yloc > frameHeight - imgHeight) {
                d = Direction.SOUTHWEST;

            }

            break;
    }

    xloc += xIncr;
    yloc += yIncr;
}

    public void isFiring(boolean fire) {
    	if(fire) {
        	setX(getxPause());
        	setY(getyPause());
        }
        else {
        setxPause(getX());
    	setyPause(getY());
        }
    }
    
    public int getX() {
        return xloc;
    }

    public void setX(int x) {
        this.xloc = x;
    }

    public int getY() {
        return yloc;
    }

    public void setY(int y) {
        this.yloc = y;
    }

    public Direction getDirect() { return d; }

    public void setDirect(Direction e) {
        d = e;
    }

    public void setXIncr(int x) {
        this.xIncr = x;
    }

    public void setYIncr(int y) {
        this.yIncr = y;
    }

    public int getXIncr() {
        return this.xIncr;
    }

    public int getYIncr() {
        return this.yIncr;
    }


}