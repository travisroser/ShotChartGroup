package src;

/**
 * Created by kyleclark on 4/3/17.
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Info extends JPanel {
    private Player player;
    private Game game;

    public Info() {
        setPreferredSize(new Dimension(100, 500));
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
        gameLabel.setPreferredSize(new Dimension(250, 200));
        add(gameLabel);

        //Draw player name
        JLabel name = new JLabel( "#" + player.getPlayerNumber() + " " + player.getFirstName() + " " + player.getLastName());
        name.setFont(new Font("Helvetica",1,24));
        gameLabel.setPreferredSize(new Dimension(250, 300));
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

            double y = Integer.parseInt(shot.getxCoordinate()) * 4.7;
            int yCor = (int)Math.round(y);
            int xCor = (Integer.parseInt(shot.getyCoordinate()) * 5);
            double dist = (xCor)*(xCor)+(yCor * yCor);
            dist = Math.sqrt(dist);

            shotsListModel.addElement("Shot #" + (i+1) + ": " + missOrMake + " from {" + xCor + ", " + yCor + "}. Distance: " + (int)dist);
        }

        JList shotsList = new JList(shotsListModel);
        add(shotsList);

        repaint();
    }
}

