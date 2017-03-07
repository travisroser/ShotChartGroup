package src;

/**
 * Created by travisroser on 3/7/17.
 */

import java.util.*;
import java.io.*;

public class StoreData {

    private ArrayList<Season> seasonsList;

    StoreData(String fileName){

        try (BufferedReader inputFile = new BufferedReader(new FileReader(fileName))) {

            String currLine;

            while ((currLine = inputFile.readLine()) != null) {

                //record ALL data into variables

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean checkSeason(Season currSeason){
        for( int i = 0; i < seasonsList.size(); i++ ){
            if( seasonsList.get(i).getSeasonID() == currSeason.getSeasonID() ){
                return false; //if the currSeason ID matches an existing season ID then don't create a new season
            }
        }
        seasonsList.add(currSeason); //if never finds matching ID's, create new season
        return true;
    }

    public boolean checkGame(Season currSeason){
        for( int i = 0; i < seasonsList.size(); i++ ){
            if( seasonsList.get(i).getSeasonID() == currSeason.getSeasonID() ) { //finds matching season
                for ( int j = 0; j < seasonsList.get(i).getGamesList().size(); j++ ) {
                    if (seasonsList.get(i).getGamesList().get(j).getGameID() == currSeason.getGamesList().get(0).getGameID()) { //finds matching game
                        return false; //if a game ID in the matching season matches the current game ID then don't create a new game in the season;
                    }
                }
                seasonsList.get(i).getGamesList().add(currSeason.getGamesList().get(0)); //if never finds matching ID's, create new game in matching season
                return true;
            }
        }
        return false; //season doesn't exist, error exists in code
    }

    public boolean checkPlayer(Season currSeason){
        for( int i = 0; i < seasonsList.size(); i++ ){
            if( seasonsList.get(i).getSeasonID() == currSeason.getSeasonID() ) { //finds matching season
                for ( int j = 0; j < seasonsList.get(i).getGamesList().size(); j++ ) {
                    if (seasonsList.get(i).getGamesList().get(j).getGameID() == currSeason.getGamesList().get(0).getGameID()) { //finds matching game
                        for( int k = 0; k < seasonsList.get(i).getGamesList().get(j).getGameRoster().size(); k ++ ){
                            if( seasonsList.get(i).getGamesList().get(j).getGameRoster().get(k).getPlayerName()  == currSeason.getGamesList().get(0).getGameRoster().get(0).getPlayerName() ){ //finds matching player
                                return false; //if players name in matching season and matching game exists, then don't create a new player
                            }
                        }
                        seasonsList.get(i).getGamesList().get(j).getGameRoster().add(currSeason.getGamesList().get(0).getGameRoster().get(0)); //if never finds matching ID's, create new player in matching game of matching season
                        return true;
                    }
                }
            }
        }
        return false; //season or game doesn't exist, error exists in code
    }

}
