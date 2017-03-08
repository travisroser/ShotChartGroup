package src;

/**
 * Created by travisroser on 2/28/17.
 */

import java.util.*;

public class Player {

    private String firstName;
    private String lastName;
    private String playerNumber;
    private ArrayList<Shots> shotsList = new ArrayList<Shots>();

    Player(){

    }

    //GETTERS AND SETTERS
    public void addShot( Shots newShot ){shotsList.add(newShot);}
    public ArrayList<Shots> getShotsList(){return shotsList;}
    public void setPlayerNumber( String num ){playerNumber = num;}
    public String getPlayerNumber(){return playerNumber;}
    public void setFirstName( String name ){firstName = name;}
    public String getFirstName(){return firstName;}
    public void setLastName( String name ){lastName = name;}
    public String getLastName(){return lastName;}

}