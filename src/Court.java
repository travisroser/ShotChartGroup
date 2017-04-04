package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/**
 * Created by Kyle Clark on 3/28/17.
 */
public class Court extends JPanel implements MouseMotionListener {
    private ArrayList<Shots> shotsToDraw = new ArrayList();
    private int mouseX, mouseY;

    public Court() {
        setPreferredSize(new Dimension(500, 500));
        this.addMouseMotionListener(this);
    }

    public void mouseMoved(MouseEvent evt){
        mouseX = evt.getPoint().x;
        mouseY = evt.getPoint().y;
    }

    public void mouseDragged(MouseEvent evt){}

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    //Setter method, adds shots that the paint method will draw onto the court
    public void setShotsToDraw(ArrayList<Shots> input) {
        System.out.println("Adding shots to draw:");
        for(int index = 0; index < input.size(); index++){
            Shots shot = input.get(index);
            System.out.print("{" + shot.getxCoordinate() + ", " +  shot.getyCoordinate() + "}");
            if(index < input.size() - 1)
                System.out.print(", ");
        }
        System.out.println();
        shotsToDraw = input;
        repaint();
    }

    public void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        final int midx = width / 2;
        final int midy = height / 2;

        //Draw the court
        setBackground (Color.white);
        g.setColor (new Color(255, 213, 94));
        g.fillRect (5, 5, 500, 470);  // outer lines

        g.setColor (Color.black);
        g.translate(255,427); //CHANGES 0,0 to middle of hoop
        g.drawOval (-8, -8, 16, 16);  // DRAWS HOOP
        g.fillRect (-30, 8, 60, 1); //BACKBOARD

        //Free Throw Box
        //g.drawArc(-60, -60, 120, 120, 0, 180); //Restricted Area
        g.drawRect(-60, -142, 120, 190); //Inside Box
        g.drawArc(-60, -202, 120, 120, 0, 180); //inside Arc

        //3 point line
        g.fillRect(-213, -1, 1, 49); //Left Side
        g.fillRect(213, -1, 1, 49); //Right Side
        g.drawArc(-213, -213, 426, 426, 0, 180); //3Arc
        g.drawRect (-250, -422, 500, 470);

        //Draw the shots
        //Paint all of the shots that are in the shotsToDraw ArrayList, if any
        for(int i = 0; i < shotsToDraw.size(); i++) {
            Shots shot = shotsToDraw.get(i);

            System.out.println("Drawing " + (shot.getmissOrMake() == '1'?"Make":"Miss") + " at: {X: " + shot.getxCoordinate() + ", Y: " + shot.getyCoordinate() + "}.");

            g.setColor(Color.black);
            g.drawOval(Integer.parseInt(shot.getxCoordinate()) - 6 , Integer.parseInt(shot.getyCoordinate()) - 6, 12, 12);  // DRAWS CIRCLE FOR A SHOT

            //Change color depending on if the shot was a miss or a make
            if(shot.getmissOrMake() == '1') {
                g.setColor(Color.GREEN); //Make
            } else {
                g.setColor(Color.RED); //MISS
            }

            g.fillOval(Integer.parseInt(shot.getxCoordinate()) - 5, Integer.parseInt(shot.getyCoordinate()) - 5, 10, 10);  // DRAWS CIRCLE FOR A SHOT
        }

    }
}
