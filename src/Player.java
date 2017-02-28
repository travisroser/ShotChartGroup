package src;

/**
 * Created by travisroser on 2/28/17.
 */

import java.util.*;

public class Player {

    private String playerName;
    private int playerNumber;
    private ArrayList<Shots> shotsList = new ArrayList<Shots>();

    Player(){

    }

    //GETTERS AND SETTERS
    public void setPlayerName( String name ){playerName = name;}
    public String getPlayerName(){return playerName;}

}