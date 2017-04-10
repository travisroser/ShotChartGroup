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
            JMenu menu, gamesMenu, playersMenu;
            JMenuItem menuItem;
            JRadioButtonMenuItem rbMenuItem;
            JCheckBoxMenuItem cbMenuItem;

            StoreData data = new StoreData();

            ArrayList<Team> newlist = data.getTeamsList();

            //Create the menu bar.
            menuBar = new JMenuBar();

            //Build the first menu.
            menu = new JMenu("Seasons");

            //loop through all seasons
            for(int i = 0; i < data.getSeasonsList().size(); i++) {
                //Set a variable for this season
                Season season = data.getSeasonsList().get(i);
                //Get the games for this season
                ArrayList<Game> games = season.getGamesList();

                menu.addSeparator();

                gamesMenu = new JMenu(Integer.toString(i));
                gamesMenu.setMnemonic(KeyEvent.VK_S);

                //Loop through and add all games
                for(int j = 0; j < games.size(); j++) {
                    final Game game = games.get(j);

                    gamesMenu.addSeparator();

                    playersMenu = new JMenu("Game " + game.getGameID() + " from " + game.getGameDate());
                    playersMenu.setMnemonic(KeyEvent.VK_S);

                    //Add all players to the games list
                    ArrayList<Player> players = game.getGameRoster();
                    for(int k = 0; k < players.size(); k++) {
                        final Player player = players.get(k);

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
                menu.add(gamesMenu);
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
