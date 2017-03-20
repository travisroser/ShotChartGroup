package src;

/**
 * Created by kyleclark on 3/19/17.
 */

import javax.swing.JApplet;
import java.awt.*;

public class Court extends JApplet{
    public void paint(Graphics page) {
        final int midx = 250;
        final int midy = 423;

        setBackground (Color.orange);
        page.setColor (Color.orange);
        page.fillRect (5, 5, 500, 470);  // outer lines

        page.setColor (Color.black);
        page.translate(255,427); //CHANGES 0,0 to middle of hoop
        page.drawOval (-8, -8, 16, 16);  // DRAWS HOOP
        page.fillRect (-30, 8, 60, 1); //BACKBOARD

        //Free Throw Box
        //page.drawArc(-60, -60, 120, 120, 0, 180); //Restricted Area
        page.drawRect(-60, -142, 120, 190); //Inside Box
        page.drawArc(-60, -202, 120, 120, 0, 180); //inside Arc

        //3 point line
        page.fillRect(-213, -1, 1, 49); //Left Side
        page.fillRect(212, -1, 1, 49); //Right Side
        page.drawArc(-213, -213, 426, 426, 0, 180); //3Arc

        page.drawRect (-250, -422, 500, 470);
    }

}



