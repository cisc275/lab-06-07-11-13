import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Do not modify this file without permission from your TA.
 **/
public class Controller {

    private Model model;
    private View view;
    Direction modelDir;
    int tempXIncr;
    int tempYIncr;

    public Controller() {
        view = new View();
        model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());

        view.b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!view.pauseBool) {
                    tempXIncr = model.getXIncr();
                    tempYIncr = model.getYIncr();
                    model.setXIncr(0);
                    model.setYIncr(0);
                } else {
                    model.setXIncr(tempXIncr);
                    model.setYIncr(tempYIncr);
                }
                view.pauseBool = !view.pauseBool;
            }
        });
        view.frame.addKeyListener(new KeyListener() {
        	public void keyPressed(KeyEvent e) {
        		System.out.println(e.getKeyCode());
        		if (e.getKeyChar() == 'f') {
        			view.fireBool = true;
        		}
        		
        		else if (e.getKeyChar() == 'j') {
        			view.jumpBool = true;
        		}
        		
        		if (e.getKeyCode() == 37) {
        			model.setDirect(Direction.NORTHWEST);
        		}
        		else if (e.getKeyCode() == 38) {
        			model.setDirect(Direction.NORTHEAST);
        		}
        		else if (e.getKeyCode() == 39) {
        			model.setDirect(Direction.SOUTHEAST);
        		}
        		else if (e.getKeyCode() == 40) {
        			model.setDirect(Direction.SOUTHWEST);
        		}
        	}

			@Override
			public void keyTyped(KeyEvent e) {
				//In place just to have all required methods
			}

			@Override
			public void keyReleased(KeyEvent e) {
				//In place just to have all required methods
			}
        });
        
    }

    //run the simulation
    public void start() {
        for (int i = 0; i < 5000; i++) {
            //increment the x and y coordinates, alter direction if necessary
            model.updateLocationAndDirection();
            //update the view
            view.update(model.getX(), model.getY(), model.getDirect());
        }
    }

}