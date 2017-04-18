package src;

import javax.swing.*;
import java.awt.*;

//new
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;
import javax.swing.ImageIcon;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
// end new

public class Main {
    private static Court court = new Court();
    private static Info info = new Info();

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("U of A Basketball");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the window to display the court and menu side by side
        frame.setLayout(new GridLayout(1,2));

        //new
        //Create and set up the content pane.
        MainMenu demo = new MainMenu();
        frame.setJMenuBar(demo.createMenuBar());
        //end new

        //Add content to the window.
        frame.add(court);
        frame.add(info);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });
    }



    //new
    public static class MainMenu {
        JTextArea output;
        JScrollPane scrollPane;

        public JMenuBar createMenuBar() {
            JMenuBar menuBar;
            JMenu menu, seasonsMenu, gamesMenu, playersMenu;
            JMenuItem menuItem;
            JRadioButtonMenuItem rbMenuItem;
            JCheckBoxMenuItem cbMenuItem;

            StoreData data = new StoreData();

//            ArrayList<Team> newlist = data.getTeamsList();

            //Create the menu bar.
            menuBar = new JMenuBar();

            // Build the first menu
            menu = new JMenu("Teams");

            // loop through all the teams
            for(int i = 0; i < data.getTeamsList().size(); i++){
                //Set a variable for this team
               Team team = data.getTeamsList().get(i);
                //Get the seasons for this season
                ArrayList<Season> seasons = team.getSeasonList();

                menu.addSeparator();

                seasonsMenu = new JMenu(team.getTeamName());
                seasonsMenu.setMnemonic(KeyEvent.VK_S);

                //loop through all seasons
                for(int j = 0; j < seasons.size(); j++) {
                    //Set a variable for this season
                    Season season = seasons.get(j);
                    //Get the games for this season
                    ArrayList<Game> games = season.getGamesList();

                    menu.addSeparator();

                    gamesMenu = new JMenu(seasons.get(j).getSeasonID());
                    gamesMenu.setMnemonic(KeyEvent.VK_S);

                    //Loop through and add all games
                    for(int k = 0; k < games.size(); k++) {
                        final Game game = games.get(k);

                        gamesMenu.addSeparator();

                        playersMenu = new JMenu("Game " + game.getGameID() + " from " + game.getGameDate());
                        playersMenu.setMnemonic(KeyEvent.VK_S);

                        //Add all players to the games list
                        ArrayList<Player> players = game.getGameRoster();
                        for(int l = 0; l < players.size(); ++l) {
                            final Player player = players.get(l);

                            menuItem = new JMenuItem(new AbstractAction(player.getLastName() + ", " + player.getFirstName() + " (" + player.getPlayerNumber() + ")") {
                                public void actionPerformed(ActionEvent e) {
                                    court.setShotsToDraw(player.getShotsList());
                                    info.setData(game, player);
                                }
                            });

                            playersMenu.add(menuItem);
                        }

                        gamesMenu.add(playersMenu);
                    }
                    seasonsMenu.add(gamesMenu);
                }
                menu.add(seasonsMenu);
            }

            menuBar.add(menu);

            return menuBar;
        }

        public Container createContentPane() {
            //Create the content-pane-to-be.
            JPanel contentPane = new JPanel(new BorderLayout());
            contentPane.setOpaque(true);

            //Create a scrolled text area.
            output = new JTextArea(5, 30);
            output.setEditable(false);
            scrollPane = new JScrollPane(output);

            //Add the text area to the content pane.
            contentPane.add(scrollPane, BorderLayout.CENTER);

            return contentPane;
        }

        /**
         * Returns an ImageIcon, or null if the path was invalid.
         */
        protected ImageIcon createImageIcon(String path) {
            java.net.URL imgURL = MainMenu.class.getResource(path);
            if (imgURL != null) {
                return new ImageIcon(imgURL);
            } else {
                System.err.println("Couldn't find file: " + path);
                return null;
            }
        }
    }
}
