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
    private Sorts sorts = new Sorts();
    private String homeORaway;

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
    public void sortGameRoster(){sorts.sortPlayersList( gameRoster );}
    public ArrayList<Player> getGameRoster(){return gameRoster;}
    public void setHomeORaway( String newData ){homeORaway = newData;}
    public String getHomeORaway(){return homeORaway;}

    //Returns a string representing the game
    public String toString() {
        String rosterString = "";
        for(int i = 0; i < gameRoster.size(); i++) {
            rosterString += gameRoster.get(i);
            if(i != gameRoster.size() - 1) {
                rosterString += ",";
            }
        }

        return "Game: {id:" + gameID + ", date: " + gameDate + ", time: " + gameTime + ", [" + rosterString + "]}";
    }
}
