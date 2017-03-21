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

public class Court extends JFrame{
    private  ArrayList<Season> seasons;
    private  ArrayList<Game> games;
    private  ArrayList<Player> players;
    private Scanner scanner;
    boolean firstSeasonsClick, firstGamesClick;

    public Court( ArrayList<Season> newSeasonsList ){ //this is what will contain all of the data
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocation(250, 40);
        setTitle("Basketball Court");
        setVisible(true);
        setFocusable(true);
        seasons = newSeasonsList;
        firstSeasonsClick = firstGamesClick = true;
        AddMenus window = new AddMenus();
        window.setBounds(750, 40, 300, 300); // Size
        window.setName("UofA Basketball Game Info");
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

    public class AddMenus extends JFrame {
        private JMenuBar menuBar; // Window menu bar
        private JMenu seasonsMenu, gamesMenu, playersMenu;

        public AddMenus() {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            menuBar = new JMenuBar();
            setTitle("UofA Basketball");
            seasonsMenu = new JMenu("Seasons");


            for(int index = 0; index < seasons.size(); index++) {
                JMenuItem season = new JMenuItem(seasons.get(index).getSeasonID());
                season.addActionListener(new SeasonListener());
                seasonsMenu.add(season);
            }

            menuBar.add(seasonsMenu);
            setJMenuBar(menuBar);
        }

            public class SeasonListener implements ActionListener {
                public void actionPerformed(ActionEvent e) {
                System.out.println("SeasonListener working");
                if(!firstSeasonsClick) {
                    menuBar.remove(gamesMenu);
                    setJMenuBar(menuBar);
                }
                if(!firstGamesClick) {
                    menuBar.remove(playersMenu);
                    setJMenuBar(menuBar);
                }
                gamesMenu = new JMenu("Games");
                int index = 1;
                String season = e.paramString();
                while(!season.contains("cmd=" + index))
                    index++;
                season = "" + index;
                System.out.println("season is " + season);
                games = seasons.get(index-1).getGamesList();
                for(index = 0; index < games.size(); index++){
                    JMenuItem game = new JMenuItem(games.get(index).getGameID());
                    game.addActionListener(new GameListener());
                    gamesMenu.add(game);
                }

                menuBar.add(gamesMenu);
                setJMenuBar(menuBar);
                firstSeasonsClick = false;
                firstGamesClick = true;
            }
        }

       public class GameListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                if(!firstGamesClick) {
                    menuBar.remove(playersMenu);
                    setJMenuBar(menuBar);
                }
                playersMenu = new JMenu("Players");
                int index = 1;
                String game = e.paramString();
                while(!game.contains("cmd=" + index))
                    index++;
                game = "" + index;
                System.out.println("game is " + game);
                players = games.get(index-1).getGameRoster();
                players = games.get(games.indexOf(game)).getGameRoster();
                for(index = 0; index < players.size(); index++){
                    JMenuItem player = new JMenuItem(players.get(index).getLastName() + ", " + players.get(index).getFirstName());
                    player.addActionListener(new PlayerListener());
                    playersMenu.add(player);
                }

                menuBar.add(playersMenu);
                setJMenuBar(menuBar);
                firstGamesClick = false;
            }
        }

        public class PlayerListener implements ActionListener {
            public void actionPerformed(ActionEvent e){

            }
        }

    }
}