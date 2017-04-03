package src;

/**
 * Created by kyleclark on 4/3/17.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Sorts {
    public class GameComparator implements Comparator<Game> {
        @Override
        public int compare(Game o1, Game o2) {
            return o1.getGameID().compareTo(o2.getGameID());
        }
    }

    public class PlayerComparator implements Comparator<Player> {
        @Override
        public int compare(Player o1, Player o2) {
            String p1 = o1.getLastName()+o1.getFirstName();
            String p2 = o2.getLastName()+o2.getFirstName();
            return p1.compareTo(p2);
        }
    }

    public ArrayList<Game> sortGamesList(ArrayList<Game> input) {
        Collections.sort(input, new GameComparator());
        return input;
    }

    public ArrayList<Player> sortPlayersList(ArrayList<Player> input) {
        Collections.sort(input, new PlayerComparator());
        return input;
    }
}
