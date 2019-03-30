/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 * <p>
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class View extends JPanel {
    JFrame frame = new JFrame();
    final int forwardFrameCount = 10;
    final int fireFrameCount = 4;
    final int jumpFrameCount = 8;
    int forwardPicNum = 0;
    int firePicNum = 0;
    int jumpPicNum = 0;
    BufferedImage[][] forwardPics;
    BufferedImage[][] firePics;
    BufferedImage[][] jumpPics;

    final static int frameWidth = 600;
    final static int frameHeight = 400;
    final int imgWidth = 165;
    final int imgHeight = 165;

    int xloc;
    int yloc;
    Direction direction = Direction.SOUTHEAST;

    JButton b = new JButton("Pause");
    public boolean pauseBool = false;
    
    public boolean fireBool = false;
    public boolean jumpBool = false;

    public int getWidth() {
        return frameWidth;
    }

    public int getHeight() {
        return frameHeight;
    }

    public int getImageWidth() {
        return imgWidth;
    }

    public int getImageHeight() {
        return imgHeight;
    }

    public void update(int x, int y, Direction d) {
        this.xloc = x;
        this.yloc = y;
        this.direction = d;
        b.setBounds(0, 0, 75, 25);
        b.setVisible(true);
        frame.add(b);

        frame.repaint();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("incomplete-switch")
    public void paint(Graphics g) {
        if (!pauseBool) {
        	if (fireBool) {
        		firePicNum = (firePicNum + 1) % fireFrameCount;
        	}
        	if (jumpBool) {
        		jumpPicNum = (jumpPicNum + 1) % jumpFrameCount;
        	}
        	else {
        		forwardPicNum = (forwardPicNum + 1) % forwardFrameCount;
        	}
        }
        //Switch statement to determine which direction to use then calling the hasCollided statements to change the 'direction' (Direction)
        switch (direction) {

        case SOUTHWEST:
            if (fireBool) {
            	jumpBool = false;
            	jumpPicNum = 0;
                g.drawImage(firePics[3][firePicNum], xloc, yloc, Color.gray, this);
                if (firePicNum == fireFrameCount - 1) {
                    fireBool = false;
                }
            } else if (jumpBool) {
                fireBool = false;
                firePicNum =0;
                g.drawImage(jumpPics[3][jumpPicNum], xloc, yloc, Color.gray, this);
                if (jumpPicNum == jumpFrameCount - 1) {
                    jumpBool = false;
                }
            } else {
                g.drawImage(forwardPics[3][forwardPicNum], xloc, yloc, Color.gray, this);
            }
            break;

        case SOUTHEAST:
        	if (fireBool) {
                jumpBool = false;
                jumpPicNum = 0;
                g.drawImage(firePics[2][firePicNum], xloc, yloc, Color.gray, this);
                if (firePicNum == fireFrameCount - 1) {
                    fireBool = false;
                }
            } else if (jumpBool) {
            	fireBool = false;
            	firePicNum =0;
                g.drawImage(jumpPics[2][jumpPicNum], xloc, yloc, Color.gray, this);
                if (jumpPicNum == jumpFrameCount - 1) {
                    jumpBool = false;
                }
            } else {
                g.drawImage(forwardPics[2][forwardPicNum], xloc, yloc, Color.gray, this);
                
            }
            break;

        case NORTHEAST:
        	if (fireBool) {
        		jumpBool = false;
                jumpPicNum = 0;
                g.drawImage(firePics[0][firePicNum], xloc, yloc, Color.gray, this);
                if (firePicNum == fireFrameCount - 1) {
                    fireBool = !fireBool;
                }
            } else if (jumpBool) {
            	fireBool = false;
            	firePicNum =0;
                g.drawImage(jumpPics[0][jumpPicNum], xloc, yloc, Color.gray, this);
                if (jumpPicNum == jumpFrameCount - 1) {
                    jumpBool = !jumpBool;
                }
            } else {
                g.drawImage(forwardPics[0][forwardPicNum], xloc, yloc, Color.gray, this);
            }
            break;

        case NORTHWEST:
        	if (fireBool) {
        		jumpBool = false;
                jumpPicNum = 0;
                g.drawImage(firePics[1][firePicNum], xloc, yloc, Color.gray, this);
                if (firePicNum == fireFrameCount -1 ) {
                    fireBool = !fireBool;
                }
            } else if (jumpBool) {
            	fireBool = false;
            	firePicNum =0;
                g.drawImage(jumpPics[1][jumpPicNum], xloc, yloc, Color.gray, this);
                if (jumpPicNum == jumpFrameCount - 1) {
                    jumpBool = !jumpBool;
                }
            } else {
                g.drawImage(forwardPics[1][forwardPicNum], xloc, yloc, Color.gray, this);
            }
            break;
    }
    }

    public static void main(String[] args) {
        Controller control = new Controller();
        control.start();
    }

    public View() {
        File dir = new File("orcImages/");
        ArrayList<String> validPics = new ArrayList<>();
        forwardPics = new BufferedImage[4][10];
        int forwardIndex = 0;
        firePics = new BufferedImage[4][4];
        int fireIndex = 0;
        jumpPics = new BufferedImage [4][8];
        int jumpIndex = 0;

        //Loads all of the files into an ArrayList as long as the criteria (contains "forward") is met
        for (File f : dir.listFiles()) {
            if (f.getName().contains("forward") || f.getName().contains("fire") || f.getName().contains("jump")) {
                System.out.println(f.getPath());
                validPics.add(f.getPath());
            }
        }

        for (int i = 0; i < validPics.size(); i++) {
            if (validPics.get(i).contains("forward")) {
                BufferedImage img = createImage(validPics.get(i));
                for (int j = 0; j < forwardFrameCount; j++) {
                    forwardPics[forwardIndex][j] = img.getSubimage(imgWidth * j, 0, imgWidth, imgHeight);
                }
                forwardIndex++;
            }
            else if (validPics.get(i).contains("fire")) {
                BufferedImage img = createImage(validPics.get(i));
                for (int j = 0; j < fireFrameCount; j++) {
                    firePics[fireIndex][j] = img.getSubimage(imgWidth * j, 0, imgWidth, imgHeight);
                }
                fireIndex++;
            }
            else if (validPics.get(i).contains("jump")) {
                BufferedImage img = createImage(validPics.get(i));
                for (int j = 0; j < jumpFrameCount; j++) {
                    jumpPics[jumpIndex][j] = img.getSubimage(imgWidth * j, 0, imgWidth, imgHeight);
                }
                jumpIndex++;
            }
        }
        b.setBounds(0, 0, 75, 25);
        b.setVisible(true);
        frame.add(b);

        frame.getContentPane().add(this);
        frame.setBackground(Color.gray);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight);
        frame.setVisible(true);
        
        frame.setFocusable(true);
    }


    //Read image from file and return
    private BufferedImage createImage(String path) { //Added a path (String) parameter to accept any path as used in paint()
        BufferedImage bufferedImage;
        try {
            System.out.println();
            bufferedImage = ImageIO.read(new File(path)); //Utilizes the path name
            return bufferedImage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
