package hwk3;

import java.awt.Color;
/**
 *  models a ball used in sports. e.g. tennis ball, etc.
 *
 *  @author Chris Fernandes and Neil Daterao
 *  @version 2/16/2024
 */
public class Ball implements Comparable<Ball> {

    private final double DEFAULT_DIAM = 10.0;
    private final String DEFAULT_COLOR = "yellow";
    public static final double MAX_DIAM = 50.0;

    private Color color;
    private double diameter;
  

    /**
     * default constructor
     */
    public Ball() {
        diameter = DEFAULT_DIAM;
        color = string2color(DEFAULT_COLOR);
        
    }

    /**
     * non-default constructor
     */
    public Ball(double newDiam, String newColor) {
        color = string2color(newColor);
        diameter = newDiam;
    }

    /**
     * non-default constructor
     */
    public Ball(String newColor) {
        diameter = DEFAULT_DIAM;
        color = string2color(newColor);
    }

    /**
     * convert String color to Color object
     * @param someColor color of Ball
     * @return Ball color as a Color object
     */
    private Color string2color(String someColor){
        if (someColor.equals("yellow")){
            return Color.yellow;
        }
        else if (someColor.equals("orange")){
            return Color.orange;
        }
        else if (someColor.equals("white")){
            return Color.white;
        }
        else {
            return Color.yellow;
        }
    }


    /**
     * put the given amount of air into the ball.
     * Do nothing if it overinflates the ball.
     * @param air amount in PSI; do nothing if negative
     */
    public void inflate(double air){
        if (air >= 0 && air+getDiameter() <= MAX_DIAM) {
            setDiameter(getDiameter() + air);
        }
    }

    /**
     * getter for diameter (cm)
     */
    public double getDiameter() { return diameter; }

    /**
     * getter for color
     * @return color as a string
     */
    public String getColor() {
        if (color.equals(Color.orange)){
            return "orange";
        }
        else if (color.equals(Color.yellow)){
            return "yellow";
        }
        else {
            return "white";
        }
    }

    /**
     * return the sport often used for this ball
     */
    public String getSport() {
        if (getColor().equals("yellow")){
            return "tennis";
        }
        else if (getColor().equals("orange")) {
            return "basketball";
        }
        else {
            return "catch";
        }
    }

    /**
     * setter for diameter
     * @param newDiam new diameter in cm
     */
    private void setDiameter(double newDiam) { diameter = newDiam; }

    /**
     * Two balls are equal if they have the same color and diameter.
     * @param otherContainable other ball
     * @return true if balls equal, else false
     */
    public boolean equals(Object otherContainable) {

        // if the *pointers* are the same, then
        // by golly it must be the same object!
        if (this == otherContainable) {
            return true;
        }

        // if the parameter is null or the two
        // objects are not instances of the same class,
        // they can't be equal
        else if (otherContainable == null ||
                this.getClass() != otherContainable.getClass()) {
            return false;
        }

        //I now know otherContainable is a Ball object.
        //If this Ball and otherContainable have the same
        //diameter and color, then that's my criteria for
        //them to be equal.
        else {
            Ball otherBall = (Ball)otherContainable;
            if (this.getColor().equals(otherBall.getColor()) &&
                    this.getDiameter()==otherBall.getDiameter()){
                return true;
            }
            else {
                return false;
            }
        }
    }

    /**
     * Return ball as a printable string
     * @return String version of this Ball
     */
    public String toString() {
        return getColor() +
                " ball with " + getDiameter()  +
                "cm diameter";
    }

    /**
     * 
     * @param otherBall the ball to compare to this ball
     * @return 1 if ball diameter is greater than otherBall
     * returns -1 if ball diameter is smaller than otherBall
     * returns 0 if ball diameters are equal.
     */
    public int compareTo(Ball otherBall) { 
        if (this.getDiameter() > otherBall.getDiameter()) { return 1; }
        else if (this.getDiameter() < otherBall.getDiameter()) { return -1; }
        else { 
            if (this.getDiameter() == otherBall.getDiameter()) { return 0; }
            else { return 0; }
        }
    }
}
