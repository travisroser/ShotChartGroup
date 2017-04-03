package src;

/**
 * Created by travisroser on 2/28/17.
 */

import java.util.*;

public class Season {

    private String seasonID;
    private ArrayList<Game> gamesList = new ArrayList<Game>();
    private Sorts sorts = new Sorts();

    Season(){

    }

    //GETTERS AND SETTERS
    public void setSeasonID( String id ){seasonID = id;}
    public String getSeasonID(){return seasonID;}
    public void addGametoSeason( Game newGame ){
        gamesList.add(newGame);
        sorts.sortGamesList(gamesList);
    }
    public ArrayList<Game> getGamesList(){return sorts.sortGamesList(gamesList);}

    //Prints a string representing the array list of games
    public String toString() {
        String result = "[";

        for(int i = 0; i < getGamesList().size(); i++) {
            result += getGamesList().get(i).toString();
            if(i != getGamesList().size() - 1) {
                result += ",";
            }
        }
        result += "]";
        return result;
    }

}
