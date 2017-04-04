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
    private ArrayList<Season> seasonsList = new ArrayList<Season>();

    Player(){

    }

    //GETTERS AND SETTERS
    public void setSeasonsList( Season currSeason ){
        for( int i = 0; i < this.seasonsList.size(); i++ ){
            if( currSeason.getSeasonID().matches( this.seasonsList.get(i).getSeasonID() ) ){
                return;
            }
        }
        seasonsList.add( currSeason );
    }
    public ArrayList<Season> getSeasonsList(){return seasonsList;}
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
        ArrayList<Shots> shotList = new ArrayList<Shots>();
        for( int i = 0; i < currSeason.getGamesList().size(); i++ ){
            for( int j = 0; j < currSeason.getGamesList().get(i).getGameRoster().size(); j++ ){
                if( currSeason.getGamesList().get(i).getGameRoster().get(j).getLastName().matches(this.getLastName()) ){ // Currently only using player's last name to validate, may have to change
                    for( int k = 0; k < currSeason.getGamesList().get(i).getGameRoster().get(j).getShotsList().size(); k++ ){
                        shotList.add( currSeason.getGamesList().get(i).getGameRoster().get(j).getShotsList().get(k) );
                    }
                }
            }
        }
        return shotList;
    }

}