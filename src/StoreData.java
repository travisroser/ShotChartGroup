package src;

/**
 * Created by travisroser on 3/7/17.
 */

import java.util.*;
import java.io.*;
import java.lang.Integer;

public class StoreData {

    private ArrayList<Season> seasonsList = new ArrayList<Season>();

    StoreData(){

        File inputFile = new File("sampleData.txt");

        try {

            Scanner reader = new Scanner(inputFile);
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
                        newSeason.setSeasonID( newChars.toString().replace(",","").replace("[","").replace("]","").replace(" ","").trim() );
                    }
                    else if( dataType == 1 ){ //game ID
                        newGame.setGameID( newChars.toString().replace(",","").replace("[","").replace("]","").replace(" ","").trim() );
                    }
                    else if( dataType == 2 ){ //game date
                        newGame.setGameDate( newChars.toString().replace(",","").replace("[","").replace("]","").replace(" ","").trim() );
                    }
                    else if( dataType == 2 ){ //game date
                        newGame.setGameDate( newChars.toString().replace(",","").replace("[","").replace("]","").replace(" ","").trim() );
                    }
                    else if( dataType == 3 ){ //game time
                        newGame.setGameTime( newChars.toString().replace(",","").replace("[","").replace("]","").replace(" ","").trim() );
                    }
                    else if( dataType == 4 ){ //player firstName
                        newPlayer.setFirstName( newChars.toString().replace(",","").replace("[","").replace("]","").replace(" ","").trim() );
                    }
                    else if( dataType == 5 ){ //player lastName
                        newPlayer.setLastName( newChars.toString().replace(",","").replace("[","").replace("]","").replace(" ","").trim() );
                    }
                    else if( dataType == 6 ){ //player jerseyNumber
                        newPlayer.setPlayerNumber( newChars.toString().replace(",","").replace("[","").replace("]","").replace(" ","").trim() );
                    }
                    else if( dataType == 7 ){ //shot xCoordinate
                        newShot.setxCoordinate( newChars.toString().replace(",","").replace("[","").replace("]","").replace(" ","").trim() );
                    }
                    else if( dataType == 8 ){ //shot yCoordinate
                        newShot.setyCoordinate( newChars.toString().replace(",","").replace("[","").replace("]","").replace(" ","").trim() );
                    }
                    else if( dataType == 9 ){ //shot missORmake
                        newShot.setMissOrMake( newChars.toString().replace(",","").replace("[","").replace("]","").replace(" ","").trim() );
                    }

                    dataType++;
                    newChars.clear();

                }

                //newPlayer.addShot( newShot );
                newGame.addPlayerToGameRoster( newPlayer );
                newSeason.addGametoSeason( newGame );

                checkSeason( newSeason );
                checkGame( newSeason );
                checkPlayer( newSeason, newShot );

            }

            //done reading file

            printData();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /* BROKEN
        //Sorts the seasons list by the season IDs
        private void sortSeasonsList() {
            ArrayList<Season> temp = new ArrayList<Season>();

            //Loop through the seasonsList, pushing each order ID into an array, in order
            for(int i = 0; i < seasonsList.size(); i++) {
                //An int that holds the value of the season ID at the current index of seasonsList
                int thisID = Integer.parseInt(seasonsList.get(i).getSeasonID());

                //A loop will iterate until the current ID is greater than the previous one in the temp array
                //Because this starts at 1, it won't try to sort the first value, which we don't need to do.
                int index = 0;
                int comparisonID = 0;
                while(thisID <= comparisonID && index < temp.size()) {
                    System.out.println("Inside the while loop.");
                    //an int that holds the value of the season ID at the current index - 1 of temp
                    //this will start at 0 and continue growing until the ID is a bigger value than the one we are looking at
                    comparisonID = Integer.parseInt(temp.get(index).getSeasonID());

                    index++;
                }
                temp.add(index, seasonsList.get(i));

                System.out.println(temp.get(i).getSeasonID());
            }
        }
    */
    public ArrayList<Season> getSeasonsList(){
        return seasonsList;
    }

    public boolean checkSeason(Season currSeason){
        for( int i = 0; i < seasonsList.size(); i++ ){
            if( seasonsList.get(i).getSeasonID().matches(currSeason.getSeasonID()) ){
                return false; //if the currSeason ID matches an existing season ID then don't create a new season
            }
        }
        seasonsList.add(currSeason); //if never finds matching ID's, create new season
        return true;
    }

    public boolean checkGame(Season currSeason){
        for( int i = 0; i < seasonsList.size(); i++ ){
            if( seasonsList.get(i).getSeasonID().matches(currSeason.getSeasonID()) ) { //finds matching season
                for ( int j = 0; j < seasonsList.get(i).getGamesList().size(); j++ ) {
                    if (seasonsList.get(i).getGamesList().get(j).getGameID().matches(currSeason.getGamesList().get(0).getGameID()) ) { //finds matching game
                        return false; //if a game ID in the matching season matches the current game ID then don't create a new game in the season;
                    }
                }
                seasonsList.get(i).getGamesList().add(currSeason.getGamesList().get(0)); //if never finds matching ID's, create new game in matching season
                return true;
            }
        }
        return false; //season doesn't exist, error exists in code
    }

    public boolean checkPlayer(Season currSeason, Shots currShot){
        for( int i = 0; i < seasonsList.size(); i++ ){
            if( seasonsList.get(i).getSeasonID().matches(currSeason.getSeasonID()) ) { //finds matching season
                for ( int j = 0; j < seasonsList.get(i).getGamesList().size(); j++ ) {
                    if (seasonsList.get(i).getGamesList().get(j).getGameID().matches(currSeason.getGamesList().get(0).getGameID()) ) { //finds matching game
                        for( int k = 0; k < seasonsList.get(i).getGamesList().get(j).getGameRoster().size(); k ++ ){
                            if( seasonsList.get(i).getGamesList().get(j).getGameRoster().get(k).getFirstName().matches(currSeason.getGamesList().get(0).getGameRoster().get(0).getFirstName()) ) { //finds matching player
                                if (seasonsList.get(i).getGamesList().get(j).getGameRoster().get(k).getLastName().matches(currSeason.getGamesList().get(0).getGameRoster().get(0).getLastName()) ) { //finds matching player
                                    seasonsList.get(i).getGamesList().get(j).getGameRoster().get(k).addShot(currShot);
                                    return false; //if players name in matching season and matching game exists, then don't create a new player
                                }
                            }
                        }
                        seasonsList.get(i).getGamesList().get(j).getGameRoster().add(currSeason.getGamesList().get(0).getGameRoster().get(0)); //if never finds matching ID's, create new player in matching game of matching season
                        seasonsList.get(i).getGamesList().get(j).getGameRoster().get( seasonsList.get(i).getGamesList().get(j).getGameRoster().size() - 1 ).addShot( currShot );//add shot for new player
                        return true;
                    }
                }
            }
        }
        return false; //season or game doesn't exist, error exists in code
    }

    public void printData(){

        for( int i = 0; i < seasonsList.size(); i++ ){
            System.out.println("SEASON "+seasonsList.get(i).getSeasonID()+" :\n");
            for( int j = 0; j < seasonsList.get(i).getGamesList().size(); j++ ){
                System.out.println("    GAME "+seasonsList.get(i).getGamesList().get(j).getGameID()+", on "+seasonsList.get(i).getGamesList().get(j).getGameDate().toString()+" at "+seasonsList.get(i).getGamesList().get(j).getGameTime()+":\n");
                for( int k = 0; k < seasonsList.get(i).getGamesList().get(j).getGameRoster().size(); k++ ){
                    System.out.println("        "+seasonsList.get(i).getGamesList().get(j).getGameRoster().get(k).getLastName()+", "+seasonsList.get(i).getGamesList().get(j).getGameRoster().get(k).getFirstName()+" ("+seasonsList.get(i).getGamesList().get(j).getGameRoster().get(k).getPlayerNumber()+")\t\t\t SHOTS MADE: "+seasonsList.get(i).getGamesList().get(j).getGameRoster().get(k).getShotsMade()+" \tSHOTS MISSED: "+seasonsList.get(i).getGamesList().get(j).getGameRoster().get(k).getShotsMissed());
                }
                System.out.println();
            }
            System.out.println();
        }

    }

}

