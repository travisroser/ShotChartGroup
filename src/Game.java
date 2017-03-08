package src;

/**
 * Created by travisroser on 2/28/17.
 */

import java.util.*;

public class Game {

    private String gameID;
    private ArrayList<Player> gameRoster = new ArrayList<Player>();
    private String gameDate;
    private String gameTime;

    Game(){

    }

    //GETTERS AND SETTERS
    public void setGameTime( String time ){gameTime = time;}
    public String getGameTime(){return gameTime;}
    public void setGameDate( String date ){gameDate = date;}
    public String getGameDate(){return gameDate;}
    public void setGameID( String num ){gameID = num;}
    public String getGameID(){return gameID;}
    public void addPlayerToGameRoster( Player newPlayer ){gameRoster.add(newPlayer);}
    public ArrayList<Player> getGameRoster(){return gameRoster;}

}
