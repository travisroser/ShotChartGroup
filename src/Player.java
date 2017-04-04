package src;

/**
 * Created by travisroser on 2/28/17.
 */

import java.util.*;

public class Player {

    private String firstName;
    private String lastName;
    private String playerNumber;
    private String shotNumber;
    private ArrayList<Shots> shotsList = new ArrayList<Shots>();

    Player(){

    }

    //GETTERS AND SETTERS
    public void setShotNumber( String num ){shotNumber = num;}
    public String getShotNumber(){return shotNumber;}
    public void addShot( Shots newShot ){shotsList.add(newShot);}
    public ArrayList<Shots> getShotsList(){return shotsList;}
    public void setPlayerNumber( String num ){playerNumber = num;}
    public String getPlayerNumber(){return playerNumber;}
    public void setFirstName( String name ){firstName = name;}
    public String getFirstName(){return firstName;}
    public void setLastName( String name ){lastName = name;}
    public String getLastName(){return lastName;}
    public int getShotsMade(){
        int result = 0;
        for( int i = 0; i < shotsList.size(); i++ ){
            if( shotsList.get(i).getmissOrMake() == '1' ){
                result++;
            }
        }
        return result;
    }
    public int getShotsMissed(){
        int result = 0;
        for( int i = 0; i < shotsList.size(); i++ ){
            if( shotsList.get(i).getmissOrMake() == '0' ){
                result++;
            }
        }
        return result;
    }

    public ArrayList<Shots> getShotsFromSeason( Season currSeason ){
        ArrayList<Shots> shotsList = new ArrayList<Shots>();
        for( int i = 0; i < currSeason.getGamesList().size(); i++ ){
            for( int j = 0; j < currSeason.getGamesList().get(i).getGameRoster().size(); j++ ){
                if( currSeason.getGamesList().get(i).getGameRoster().get(j).getLastName().matches(this.getLastName()) ){ // Currently only using player's last name to validate, may have to change
                    for( int k = 0; k < currSeason.getGamesList().get(i).getGameRoster().get(j).getShotsList().size(); k++ ){
                        shotsList.add( currSeason.getGamesList().get(i).getGameRoster().get(j).getShotsList().get(k) );
                    }
                }
            }
        }
        return shotsList;
    }

}