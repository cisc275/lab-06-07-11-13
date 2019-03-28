import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                    modelDir = model.getDirect();
                } else {
                    model.setXIncr(tempXIncr);
                    model.setYIncr(tempYIncr);
                }
                view.pauseBool = !view.pauseBool;
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