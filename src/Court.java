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
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

public class Court extends JFrame {
    private  ArrayList<Season> seasons;
    private  ArrayList<Game> games;
    private  ArrayList<Player> players;
    private Scanner scanner;
    private boolean firstSeasonsClick, firstGamesClick;
    private JMenuBar menuBar; // Window menu bar
    private JMenu seasonsMenu, gamesMenu, playersMenu;
    private JPanel court;

    public Court( ArrayList<Season> newSeasonsList ){ //this is what will contain all of the data
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 550);
        setLocation(250, 40);
        setTitle("Basketball Court");
        paintCourt court = new paintCourt();
        add(court);
        seasons = newSeasonsList;
        sortSeasons(seasons);

        addMenus();
        firstSeasonsClick = firstGamesClick = true;
        this.setVisible(true);
    }

    public class paintCourt extends JPanel{
        @Override
        public void paintComponent(Graphics page) {
            final int midx = 250;
            final int midy = 423;
            setSize(500, 500);

            setBackground (Color.white);
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

    public void addMenus() {
            menuBar = new JMenuBar();
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
                if(!firstSeasonsClick) {
                    menuBar.remove(gamesMenu);
                    setJMenuBar(menuBar);
                }
                if(!firstGamesClick) {
                    menuBar.remove(playersMenu);
                    setJMenuBar(menuBar);
                }

                gamesMenu = new JMenu("Games");
                String season = e.paramString();
                int beginIndex = season.indexOf("cmd")+4;
                int endIndex = beginIndex + 4;
                season = season.substring(beginIndex, endIndex);
                System.out.println("season is " + season);
                int index = 0;
                while(!seasons.get(index).getSeasonID().equals(season))
                    index++;

                games = seasons.get(index).getGamesList();
                System.out.println("Index of season " + season + " is " + index);
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
                String game = e.paramString();
                int beginIndex = game.indexOf("cmd")+4;
                int endIndex = beginIndex + 1;
                game = game.substring(beginIndex, endIndex);
                System.out.println("season is " + game);
                int index = 0;
                while(!games.get(index).getGameID().equals(game))
                    index++;

                players = games.get(index).getGameRoster();
                System.out.println("Index of game " + game + " is " + index);
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
                String player = e.paramString();
                int beginIndex = player.indexOf("cmd=")+4;
                int endIndex = player.indexOf(",", beginIndex);
                String lastName = player.substring(beginIndex, endIndex);

                beginIndex = endIndex+1;
                endIndex = player.indexOf(",", beginIndex);
                String firstName = player.substring(beginIndex, endIndex);

                player = firstName + " " + lastName;
                System.out.println("Player is " + player);

                int index = 0;
                while(!firstName.equals(players.get(index).getFirstName()) && !lastName.equals(players.get(index).getLastName()))
                    index++;

                System.out.println("Player at index " + index + " in players is " + players.get(index).getFirstName() + " " + players.get(index).getLastName());


            }
        }

    //This method takes an Arraylist of seasons and returns a sorted array list of seasons.
    private ArrayList<Season> sortSeasons(ArrayList<Season> list) {
        for(int i = 0; i < list.size(); i++) {
            System.out.print("Season: ");
            System.out.println(list.get(i));

            System.out.println(list.get(i).toString());
        }
        return null; //STUB
    }
}