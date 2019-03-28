/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/

import java.awt.Color;
        import java.awt.Graphics;
        import java.awt.image.BufferedImage;
        import java.io.File;
        import java.io.IOException;
        import java.util.ArrayList;

        import javax.imageio.ImageIO;
        import javax.swing.*;

@SuppressWarnings("serial")
public class View extends JPanel {
    static JFrame frame = new JFrame();
    final int frameCount = 10;
    int picNum = 0;
    BufferedImage[][] pics;

    final static int frameWidth = 600;
    final static int frameHeight = 400;
    final int imgWidth = 165;
    final int imgHeight = 165;

    int xloc;
    int yloc;
    Direction direction = Direction.NORTHEAST;

    JButton b = new JButton("Pause");
    public boolean pauseBool = false;

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
            picNum = (picNum + 1) % frameCount;
        }
        //Switch statement to determine which direction to use then calling the hasCollided statements to change the 'direction' (Direction)

        switch (direction) {

            case SOUTHWEST:
                g.drawImage(pics[6][picNum], xloc, yloc, Color.gray, this);
                break;

            case SOUTHEAST:
                g.drawImage(pics[2][picNum], xloc, yloc, Color.gray, this);
                break;

            case NORTHEAST:
                g.drawImage(pics[0][picNum], xloc, yloc, Color.gray, this);
                break;

            case NORTHWEST:
                g.drawImage(pics[1][picNum], xloc, yloc, Color.gray, this);
                break;
        }
    }

    public static void main(String[] args) {
        Controller control = new Controller();
        control.start();
    }

    public View(){
        File dir = new File("orcImages/");
        ArrayList<String> validPics = new ArrayList<>();
        pics = new BufferedImage[8][10];

        //Loads all of the files into an ArrayList as long as the criteria (contains "forward") is met
        for (File f : dir.listFiles()) {
            if(f.getName().contains("forward")) {
                System.out.println(f.getPath());
                validPics.add(f.getPath());
            }
        }

        for(int i = 0; i < validPics.size(); i++) {
            BufferedImage img = createImage(validPics.get(i));
            for (int j = 0; j < frameCount; j++) {
                pics[i][j] = img.getSubimage(imgWidth*j, 0, imgWidth, imgHeight);
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
    }


    //Read image from file and return
    private BufferedImage createImage(String path){ //Added a path (String) parameter to accept any path as used in paint()
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
