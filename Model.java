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

                if (xloc < 0 || xloc > frameWidth - imgWidth) {
                    d = Direction.SOUTHEAST;
                }
                if (yloc < 0 || yloc > frameHeight - imgHeight) {
                    d = Direction.NORTHWEST;
                }

                break;

            case SOUTHEAST:

                if (xloc < 0 || xloc > frameWidth - imgWidth) {
                    d = Direction.SOUTHWEST;
                }
                if (yloc < 0 || yloc > frameHeight - imgHeight) {
                    d = Direction.NORTHEAST;
                }

                break;

            case NORTHEAST:

                if (xloc < 0 || xloc > frameWidth - imgWidth) {
                    d = Direction.NORTHWEST;
                }
                if (yloc < 0 || yloc > frameHeight - imgHeight) {
                    d = Direction.SOUTHEAST;
                }

                break;

            case NORTHWEST:

                if (xloc < 0 || xloc > frameWidth - imgWidth) {
                    d = Direction.NORTHEAST;
                }
                if (yloc < 0 || yloc > frameHeight - imgHeight) {
                    d = Direction.SOUTHWEST;
                }

                break;
        }

        xloc += xIncr;
        yloc += yIncr;
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