package src;

/**
 * Created by travisroser on 3/7/17.
 */

import java.util.*;
import java.io.*;
import java.lang.Integer;

public class StoreData {

    private ArrayList<Season> seasonsList = new ArrayList<Season>();

    StoreData(String fileName){

        File inputFile = new File(fileName);

        try {

            Scanner reader = new Scanner( inputFile );
            String currLine;
            ArrayList<Character> newChars = new ArrayList<Character>();
            int dataType;

            while ( reader.hasNextLine() ){ //checking for end of file

                //record ALL data into variables

                currLine = reader.nextLine();
                dataType = 0;
                Season newSeason = new Season();
                Game newGame = new Game();
                Player newPlayer = new Player();
                Shots newShot = new Shots();

                for( int i = 0; i < currLine.length(); i++ ){ //traverse each line

                    while( currLine.charAt(i) != ' ' ){
                        newChars.add(currLine.charAt(i));
                        i++;
                        if( i == currLine.length() ){
                            break;
                        }
                    }

                    if( dataType == 0 ){ //season ID
                        newSeason.setSeasonID( newChars.toString() );
                    }
                    else if( dataType == 1 ){ //game ID
                        newGame.setGameID( newChars.toString() );
                    }
                    else if( dataType == 2 ){ //game date
                        newGame.setGameDate( newChars.toString() );
                    }
                    else if( dataType == 2 ){ //game date
                        newGame.setGameDate( newChars.toString() );
                    }
                    else if( dataType == 3 ){ //game time
                        newGame.setGameTime( newChars.toString() );
                    }
                    else if( dataType == 4 ){ //player firstName
                        newPlayer.setFirstName( newChars.toString() );
                    }
                    else if( dataType == 5 ){ //player lastName
                        newPlayer.setLastName( newChars.toString() );
                    }
                    else if( dataType == 6 ){ //player jerseyNumber
                        newPlayer.setPlayerNumber( newChars.toString() );
                    }
                    else if( dataType == 7 ){ //shot xCoordinate
                        newShot.setxCoordinate( newChars.toString() );
                    }
                    else if( dataType == 8 ){ //shot yCoordinate
                        newShot.setyCoordinate( newChars.toString() );
                    }
                    else if( dataType == 9 ){ //shot missORmake
                        newShot.setMissOrMake( newChars.toString() );
                    }

                    dataType++;
                    newChars.clear();

                }

                newPlayer.addShot( newShot );
                newGame.addPlayerToGameRoster( newPlayer );
                newSeason.addGametoSeason( newGame );

                seasonsList.add(newSeason); //just to test

            }

            //done reading file

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
                            if( seasonsList.get(i).getGamesList().get(j).getGameRoster().get(k).getFirstName()  == currSeason.getGamesList().get(0).getGameRoster().get(0).getFirstName() ) { //finds matching player
                                if (seasonsList.get(i).getGamesList().get(j).getGameRoster().get(k).getLastName() == currSeason.getGamesList().get(0).getGameRoster().get(0).getLastName()) { //finds matching player
                                    return false; //if players name in matching season and matching game exists, then don't create a new player
                                }
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
