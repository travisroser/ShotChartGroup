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

        JLabel title = new JLabel("Please select a Team, then a Game, then a Player", SwingConstants.CENTER);
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


        //Loop through all shots for this game and shot shots for this player
        //JLabel title = new JLabel("Shots:");
        //title.setFont(new Font("Helvetica",1,20));
        //add(title);

        DefaultListModel shotsListModel = new DefaultListModel();
        DecimalFormat df = new DecimalFormat("#.##");
        double totalDist = 0;
        int numShots = 0;
        double makeDist = 0;
        double missDist = 0;
        int numMakes = 0;
        int numMisses = 0;

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


            //calculate distance
            int xDist = yCor - 250;
            int yDist = xCor - 43;
            xDist = xDist/10;
            yDist = yDist/10;
            dist = (xDist)*(xDist)+(yDist * yDist);
            dist = Math.sqrt(dist);
            numShots = i + 1;
            //Math.round(dist,2);

            totalDist = totalDist + dist;

            if(missOrMake == "Make"){
                makeDist = makeDist + dist;
                numMakes = numMakes + 1;
            }
            else if(missOrMake == "Miss"){
                missDist = missDist + dist;
                numMisses = numMisses + 1;
            }

            if(i==0){
                shotsListModel.addElement("Note: Shot coordinates below represent distance from hoop in feet.");
            }

            shotsListModel.addElement("Shot #" + (i+1) + ": " + missOrMake + " from (" + xDist + ", " + yDist + "). Distance: " + df.format(dist) + " feet");
        }

        JList shotsList = new JList(shotsListModel);
        if( player.getShotsList().size() > 6 ) {
            shotsList.setPreferredSize(new Dimension(250, 240));
            add(new JScrollPane(shotsList));
        }
        else if( player.getShotsList().size() > 13 ){
            shotsList.setPreferredSize(new Dimension(250, 360));
            add(new JScrollPane(shotsList));
        }
        else if( player.getShotsList().size() > 20 ){
            shotsList.setPreferredSize(new Dimension(250, 480));
            add(new JScrollPane(shotsList));
        }
        else if( player.getShotsList().size() > 27 ){
            shotsList.setPreferredSize(new Dimension(250, 600));
            add(new JScrollPane(shotsList));
        }
        else{
            shotsList.setPreferredSize(new Dimension(250, 350));

            add(shotsList);
        }

        //Shots averages
        double avgDist = totalDist / numShots;
        double avgMake = makeDist / numMakes;
        double avgMiss = missDist / numMisses;

        JLabel AvgLabel = new JLabel("Average distance of shots: " + df.format(avgDist));
        AvgLabel.setFont(new Font("Helvetica",1,12));
        add(AvgLabel);

        //Draw game info
        JLabel MakeLabel = new JLabel("Average distance of makes: " + df.format(avgMake));
        MakeLabel.setFont(new Font("Helvetica",1,12));
        MakeLabel.setFont(new Font("Helvetica",1,12));
        MakeLabel.setForeground(new Color(0,150,20));
        add(MakeLabel);

        JLabel MissLabel = new JLabel("Average distance of misses: " + df.format(avgMiss));
        MissLabel.setFont(new Font("Helvetica",1,12));
        MissLabel.setForeground(Color.red);
        add(MissLabel);

        //Draw player name
        JLabel name = new JLabel( player.getFirstName() + " " + player.getLastName() + " versus " + game.getGameID() + " on " + game.getGameDate() + " (" + game.getHomeORaway() + ")");
        name.setFont(new Font("Helvetica",1,14));
        //gameLabel.setPreferredSize(new Dimension(250, 100));
        add(name, SwingConstants.CENTER);


        repaint();
    }
}