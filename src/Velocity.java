//ID: 316125855

/**
 * Point class
 * this class creates the point that is needed to creating the ball.
 * also, it calculates the distance between two points and check if two points are the same.
 *
 * @author  Avital Aviv
 * @version 1.0
 */
public class Velocity {
    public static final int TWO_PI = 360;
    public static final int ZERO = 0;
    private double dx;
    private double dy;

    /**
     * constructor method.
     * it creates the velocity object.
     * @param dx the value of the change in x
     * @param dy the value of the change in y
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * getDx method.
     * it returns the x values in the change of the velocity
     * @return dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * getDy method.
     * it returns the y values in the change of the velocity
     * @return dy
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * setDx method.
     * change the dx value in the velocity.
     * @param newDx new dx value.
     */
    public void setDx(double newDx) {
        this.dx = newDx;
    }

    /**
     * setDy method.
     * change the dy value in the velocity.
     * @param newDy new dy value.
     */
    public void setDy(double newDy) {
        this.dy = newDy;
    }

    /**
     * applyToPoint method.
     * applying the new velocity to the points. changing the center point.
     * @param p the center point of the ball.
     * @return new center point of the ball.
     */
    public Point applyToPoint(Point p) {

        /* adding to the x and y values the change value */
        double newX = p.getX() + this.getDx();
        double newY = p.getY() + this.getDy();

        /* returning new point */
        return new Point(newX, newY);
    }

    /**
     * fromAngleAndSpeed method.
     * calculating the speed by getting the angle and speed of the vector of the ball.
     * @param angle angle of vector
     * @param speed value of the speed
     * @return velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {

        /* it the angle is more than 360 degrees so, put it in this range */
        while (angle >= TWO_PI) {
            angle = angle - TWO_PI;
        }
        while (angle < ZERO) {
            angle = angle + TWO_PI;
        }

        /* calculating the new velocity */
        double dy = speed * (-1) * Math.cos(Math.toRadians(angle));
        double dx = speed * Math.sin(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }
}
