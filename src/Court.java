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
        setPreferredSize(new Dimension(510, 480));
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
        g.setColor (new Color(217, 217, 217));
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

        //draw vertical coordinate lines, 6 pixels long 15 pixes apart
        g.drawLine(0, 42, 0, 48);
        g.drawLine(0, 18, 0, 24);
        g.drawLine(0, -3, 0, 3); //origin
        g.drawLine(-3, 0, 3, 0);
        g.drawLine(0, -24, 0, -18);
        g.drawLine(0, -45, 0, -39);
        g.drawLine(0, -66, 0, -60);
        g.drawLine(0, -87, 0, -81);
        g.drawLine(0, -108, 0, -102);
        g.drawLine(0, -129, 0, -123);
        g.drawLine(0, -150, 0, -144);
        g.drawLine(0, -171, 0, -165);
        g.drawLine(0, -192, 0, -186);
        g.drawLine(0, -213, 0, -207);
        g.drawLine(0, -234, 0, -228);
        g.drawLine(0, -255, 0, -249);
        g.drawLine(0, -276, 0, -270);
        g.drawLine(0, -297, 0, -291);
        g.drawLine(0, -318, 0, -312);
        g.drawLine(0, -339, 0, -333);
        g.drawLine(0, -360, 0, -354);
        g.drawLine(0, -381, 0, -375);
        g.drawLine(0, -402, 0, -396);
        g.drawLine(0, -422, 0, -417);






        //coordinate labels
        Font font = new Font("Helvetica", Font.BOLD, 8);
        g.setFont(font);
        g.drawString("(0,0)", 5, 8);
        g.drawString("(0,-5)", -9, 56);
        g.drawString("(-21,-5)", -224, 56);
        g.drawString("(21,-5)", 203, 56);
        g.drawString("(0,21)", 1, -214);




        g.translate(-250,48); //CHANGES 0,0 to bottom left
        //Draw the shots
        //Paint all of the shots that are in the shotsToDraw ArrayList, if any
        for(int i = 0; i < shotsToDraw.size(); i++) {
            Shots shot = shotsToDraw.get(i);

            //calculate x and y coordinate of the shot from the bottom left corner
            double y = Integer.parseInt(shot.getyCoordinate()) * 4.7;
            //int yCor = (int)Math.round(y);
            double yCor = (-1)*y;
            double xCor = (Integer.parseInt(shot.getxCoordinate()) * 5);



            //calculate distance
            double xDist = 250 - xCor;
            double yDist = 48 - yCor;
            xDist = Math.round(xDist/10);
            yDist = Math.round(yDist/10);
            double dist = (xDist)*(xDist)+(yDist * yDist);
            dist = Math.sqrt(dist);

            if(i==0){
                System.out.println("Note: Shot coordinates below represent distance from hoop in feet.");
            }
            System.out.println("Drawing " + (shot.getmissOrMake() == '1'?"Make":"Miss") + " at: (X: " + xDist + ", Y: " + yDist + ").");
            System.out.println(" Distance from basket: " + dist + "feet.");


            //Change color depending on if the shot was a miss or a make
            if(shot.getmissOrMake() == '1') {
                g.setColor(new Color(0,150,20)); //Make
            } else {
                g.setColor(Color.RED); //MISS
            }
            g.drawOval( (int)xCor - 5, ((int)yCor - 5), 10, 10);  // DRAWS CIRCLE FOR A SHOT

        }

    }
}
