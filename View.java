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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Direction d;
	final int frameCount = 10;
	int picNum = 0;
	BufferedImage[] pics;
	final static int frameWidth = 500;
	final static int frameHeight = 300;
	final static int imgWidth = 165;
	final static int imgHeight = 165;
	BufferedImage[][] pica;
	JFrame frame;
	int arrayCounter = 0;
	int column;
	int x;
	int y;
	JButton b = new JButton("Pause");
	

	public void paint(Graphics g) {
		picNum = (picNum + 1) % frameCount;
		

		// Choose correct image
		if (d.getName() == "southeast") {
			column = 1;
		}
		if (d.getName() == "northeast") {
			column = 3;
		}
		if (d.getName() == "northwest") {
			column = 5;
		}
		if (d.getName() == "southwest") {
			column = 7;
		}
	
		
		g.drawImage(pica[column][picNum+1], x, y, Color.gray, this);
	}
	
	public View() {		
		pica = new BufferedImage[8][11];
		for (Direction di : Direction.values()) {
			File f;
			f = new File("orcImages/orc_forward_" + di + ".png");
			BufferedImage im = createImage(f);
			pica[arrayCounter][0]= im;
			arrayCounter++;
		}
		
		
		
		for(int i = 0; i <8;i++) {
			
			for(int j = 0;j<frameCount;j++) {
				BufferedImage im =pica[i][0].getSubimage(imgWidth * j, 0, imgWidth, imgHeight);
				int h = j+1;
				pica[i][h] = im;
				
			}
		}

		frame = new JFrame();		
	}
	
	private BufferedImage createImage(File filo) {
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(filo);
			return bufferedImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public void update(int x, int y, Direction direct) {
		this.x = x;
		this.y = y;
		d = direct;
		b.setBounds(0, 0, 75, 25);
		b.setVisible(true);
		frame.add(b);
		
		frame.add(this);
		frame.setBackground(Color.gray);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(frameWidth, frameHeight);
		frame.setVisible(true);
		frame.repaint();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static void main(String [] args) {
		Controller c = new Controller();
		c.start();
	}

	public int getImageWidth() {
		return imgWidth;
	}

	public int getImageHeight() {
		return imgHeight;
	}
}
