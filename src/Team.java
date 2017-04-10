package src;

import java.util.ArrayList;

/**
 * Created by travisroser on 4/10/17.
 */
public class Team {

    private String teamName;
    private ArrayList<Season> seasonList = new ArrayList<Season>();
    private Sorts sorts = new Sorts();

    Team(){

    }

    public void setTeamName( String name ){teamName = name;}
    public String getTeamName(){return teamName;}
    public ArrayList<Season> getSeasonList(){return seasonList;}
    public void addSeasonToTeam( Season newSeason ){
        seasonList.add(newSeason);
        //sort season?
    }

}
