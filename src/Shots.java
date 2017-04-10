package src;

/**
 * Created by travisroser on 2/28/17.
 */
public class Shots {

    private String xCoordinate;
    private String yCoordinate;
    private String missOrMake; // 0 => miss   1 => make
    private String shotNumber;
    private String gameHalf;

    Shots(){

    }

    //GETTERS AND SETTERS
    public void setShotHalf( String half ){gameHalf = half;}
    public String getShotHalf(){return gameHalf;}
    public void setShotNumber( String type ){shotNumber = type;}
    public String getShotNumber(){return shotNumber;}
    public void setxCoordinate( String x ){xCoordinate = x;}
    public String getxCoordinate(){return xCoordinate;}
    public void setyCoordinate( String y ){yCoordinate = y;}
    public String getyCoordinate(){return yCoordinate;}
    public void setMissOrMake( String result ){missOrMake = result;}
    public char getmissOrMake(){return missOrMake.charAt(0);}

}
