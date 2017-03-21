package src;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        StoreData newData = new StoreData();                    //creates all chart data using arg as data text file input
        Court newCourt = new Court( newData.getSeasonsList() ); //creates GUI

    }
}