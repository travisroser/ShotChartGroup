package src;

/**
 * Created by travisroser on 2/28/17.
 */
public class Shots {

    private String xCoordinate;
    private String yCoordinate;
    private String missOrMake; // 0 => miss   1 => make

    Shots(){

    }

    //GETTERS AND SETTERS
    public void setxCoordinate( String x ){xCoordinate = x;}
    public String getxCoordinate(){return xCoordinate;}
    public void setyCoordinate( String y ){yCoordinate = y;}
    public String getyCoordinate(){return yCoordinate;}
    public void setMissOrMake( String result ){missOrMake = result;}
    public String getmissOrMake(){return missOrMake;}

}
