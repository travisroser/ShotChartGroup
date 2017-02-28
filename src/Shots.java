package src;

/**
 * Created by travisroser on 2/28/17.
 */
public class Shots {

    private int xCoordinate;
    private int yCoordinate;
    private boolean missOrMake; // 0 => miss   1 => make

    Shots(){

    }

    //GETTERS AND SETTERS
    public void setxCoordinate( int x ){xCoordinate = x;}
    public int getxCoordinate(){return xCoordinate;}
    public void setyCoordinate( int y ){yCoordinate = y;}
    public int getyCoordinate(){return yCoordinate;}
    public void setMissOrMake( boolean result ){missOrMake = result;}
    public boolean getmissOrMake(){return missOrMake;}

}
