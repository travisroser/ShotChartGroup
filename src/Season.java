package src;

/**
 * Created by travisroser on 2/28/17.
 */

import java.util.*;

public class Season {

    private String seasonID;
    private ArrayList<Game> gamesList = new ArrayList<Game>();

    Season(){

    }

    //GETTERS AND SETTERS
    public void setSeasonID( String id ){seasonID = id;}
    public String getSeasonID(){return seasonID;}
    public void addGametoSeason( Game newGame ){gamesList.add(newGame);}
    public ArrayList<Game> getGamesList(){return gamesList;}

}
