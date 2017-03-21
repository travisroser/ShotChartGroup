package src;

/**
 * Created by kyleclark on 3/19/17.
 */

import javax.swing.JApplet;
import java.awt.*;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import java.util.ArrayList;
import java.util.Scanner;

public class Court extends JApplet{
   private  ArrayList<Season> seasons;
    private  ArrayList<Game> games;
    private  ArrayList<Player> players;
    private Scanner scanner;

    public Court( ArrayList<Season> newSeasonsList ){ //this is what will contain all of the data

//        scanner = new Scanner(System.in); // reads in the file name
//        System.out.println("Please enter a file name: "); // user enters name manually
//        String filename = scanner.next();
//        StoreData newChart = new StoreData(filename);

        AddSeasonMenu window = new AddSeasonMenu();
        window.setBounds(415, 30, 300, 300); // Size
        window.setVisible(true);
    }

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

    public class AddSeasonMenu extends JFrame {
        private JMenuBar menuBar = new JMenuBar(); // Window menu bar
        private JMenuItem newSeason;

        public AddSeasonMenu() {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setJMenuBar(menuBar);
            JMenu seasonMenu = new JMenu("Seasons");

            for(int index = 0; index < seasons.size(); index++){
                newSeason = seasonMenu.add(seasons.get(index).toString());
            }

            /*newItem = fileMenu.add("New");
            openItem = fileMenu.add("Open");
            closeItem = fileMenu.add("Close");
            fileMenu.addSeparator();
            saveItem = fileMenu.add("Save");
            saveAsItem = fileMenu.add("Save As...");
            fileMenu.addSeparator();
            printItem = fileMenu.add("Print");
            elementMenu.add(lineItem = new JRadioButtonMenuItem("Line", true));
            elementMenu.add(rectangleItem = new JRadioButtonMenuItem("Rectangle", false));
            elementMenu.add(circleItem = new JRadioButtonMenuItem("Circle", false));
            ButtonGroup types = new ButtonGroup();
            types.add(lineItem);
            types.add(rectangleItem);
            types.add(circleItem);
            elementMenu.addSeparator();
            elementMenu.add(redItem = new JCheckBoxMenuItem("Red", false));
            elementMenu.add(yellowItem = new JCheckBoxMenuItem("Yellow", false));*/
            menuBar.add(seasonMenu);
        }
    }
}



