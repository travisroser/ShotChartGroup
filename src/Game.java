package src;

/**
 * Created by travisroser on 2/28/17.
 */

import java.util.*;

public class Game {

    private int gameID;
    private ArrayList<Player> gameRoster = new ArrayList<Player>();

    Game(){

    }

    //GETTERS AND SETTERS
    public void setGameID( int num ){gameID = num;}
    public int getGameID(){return gameID;}
    public void addPlayerToGameRoster( Player newPlayer ){gameRoster.add(newPlayer);}
    public ArrayList<Player> getGameRoster(){return gameRoster;}

}
