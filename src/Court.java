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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

public class Court extends JApplet{
   private  ArrayList<Season> seasons;
    private  ArrayList<Game> games;
    private  ArrayList<Player> players;
    private Scanner scanner;
    JMenu seasonsMenu, gamesMenu, playersMenu;
    boolean firstSeasonsClick, firstGamesClick;

    public Court( ArrayList<Season> newSeasonsList ){ //this is what will contain all of the data
        seasons = newSeasonsList;
        firstSeasonsClick = firstGamesClick = true;
        AddSeasonMenu window = new AddSeasonMenu();
        window.setBounds(415, 30, 300, 300); // Size
        window.setVisible(true);
        this.setVisible(true);
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
            seasonsMenu = new JMenu("Seasons");


            for(int index = 0; index < seasons.size(); index++)
                seasonsMenu.add(seasons.get(index).getSeasonID());

           seasonsMenu.addActionListener(new SeasonListener());
           menuBar.add(seasonsMenu);
        }

        class SeasonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                if(!firstSeasonsClick)
                    menuBar.remove(gamesMenu);

                if(!firstGamesClick)
                    menuBar.remove(playersMenu);

                gamesMenu = new JMenu("Games");
                int season = e.getID();
                System.out.println("season is " + season);
                games = seasons.get(seasons.indexOf(season)).getGamesList();
                for(int index = 0; index < games.size(); index++)
                    gamesMenu.add(games.get(index).getGameID());

                gamesMenu.addActionListener(new GameListener());
                menuBar.add(gamesMenu);

                firstSeasonsClick = false;
                firstGamesClick = true;
            }
        }

        class GameListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                if(!firstGamesClick)
                    menuBar.remove(playersMenu);

                playersMenu = new JMenu("Players");
                int game = e.getID();
                players = games.get(games.indexOf(game)).getGameRoster();
                for(int index = 0; index < players.size(); index++)
                    playersMenu.add(players.get(index).getLastName() + ", " + players.get(index).getFirstName());

                menuBar.add(playersMenu);
                firstGamesClick = false;
            }
        }

    }
}



