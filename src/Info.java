package src;

/**
 * Created by kyleclark on 4/3/17.
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Info extends JPanel {
    private Player player;
    private Game game;

    public Info() {
        setPreferredSize(new Dimension(300, 500));
        setBackground(Color.lightGray);
        setLayout(new GridLayout(0,1, 0, 0));

        JLabel title = new JLabel("Please Select a Game and Player", SwingConstants.CENTER);
        title.setFont(new Font("Helvetica",1,20));
        add(title);

        setBorder(new LineBorder(Color.BLACK));
    }

    public void setData(Game g, Player p) {
        game = g;
        player = p;
        System.out.println("Game changed to: " + g.getGameDate() + " at " + g.getGameTime());
        System.out.println("Player changed to: " + p.getFirstName() + " " + p.getLastName());
        reDraw();
    }

    private void reDraw() {
        //Clear panel
        revalidate();
        repaint();
        removeAll();

        drawPanel();
    }

    private void drawPanel() {
        //Draw game info
        JLabel gameLabel = new JLabel("Game " + game.getGameID() + " on " + game.getGameDate() + " at " + game.getGameTime());
        gameLabel.setFont(new Font("Helvetica",1,12));
        add(gameLabel);

        //Draw player name
        JLabel name = new JLabel( "#" + player.getPlayerNumber() + " " + player.getFirstName() + " " + player.getLastName());
        name.setFont(new Font("Helvetica",1,24));
        add(name, SwingConstants.CENTER);

        //Loop through all shots for this game and shot shots for this player
        JLabel title = new JLabel("Shots:");
        title.setFont(new Font("Helvetica",1,20));
        add(title);

        DefaultListModel shotsListModel = new DefaultListModel();

        for(int i = 0; i < player.getShotsList().size(); i++) {
            final Shots shot = player.getShotsList().get(i);

            String missOrMake = "Miss";
            if(shot.getmissOrMake() == '1') {
                missOrMake = "Make";
            }

            shotsListModel.addElement("Shot #1: " + missOrMake + " from {" + shot.getxCoordinate() + ", " + shot.getyCoordinate() + "}");
        }

        JList shotsList = new JList(shotsListModel);
        add(shotsList);

        repaint();
    }
}

