//ID: 316125855

/**
 * Point class
 * this class creates the point that is needed to creating the ball.
 * also, it calculates the distance between two points and check if two points are the same.
 *
 * @author  Avital Aviv
 * @version 1.0
 */
public class Point {
    private double x;
    private double y;

    /**
     * constructor method.
     * it creates the values of a point.
     * @param x first parameter, x value of a point.
     * @param y second parameter, y value of a point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance method.
     * this method calculates the distance between two points.
     * @param other point.
     * @return distance
     */
    public double distance(Point other) {
        double x1 = this.getX();
        double y1 = this.getY();
        double x2 = other.getX();
        double y2 = other.getY();
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

    /**
     * equals method.
     * check if two points are equal.
     * @param other point.
     * @return boolean
     */
    public boolean equals(Point other) {
        double x1 = this.getX();
        double y1 = this.getY();
        double x2 = other.getX();
        double y2 = other.getY();
        double epsilon = Math.pow(10, -7);

        /*
        check if two points are equal and if their difference is as epsilon
        */
        return ((x1 == x2 && y1 == y2) || (Math.abs(x1 - x2) < epsilon && Math.abs(y1 - y2) < epsilon));
    }
    /**
     * getX method.
     * @return x value of a point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * getY method.
     * @return - y value of a point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * setX method.
     * @param a the x value of the point it changes.
     */
    public void setX(double a) {
        this.x = a;
    }

    /**
     * setY method.
     * @param b the y value of the point it changes.
     */
    public void setY(double b) {
        this.y = b;
    }
}
