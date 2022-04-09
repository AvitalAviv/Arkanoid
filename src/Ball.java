//ID: 316125855
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Ball class
 * this creates the ball by getting the (x,y) point, color and radius.
 *
 * @author  Avital Aviv
 * @version 1.0
 */
public class Ball implements Sprite {
    private double r;
    private Point center;
    private java.awt.Color colorIn;
    private java.awt.Color colorOut;
    private Velocity velo;
    private GameEnvironment gameEnvironment;
    private static final java.awt.Color COLOR_OUTSIDE_BALL = Color.black;

    /**
     * constructor method.
     * it creates the values of the ball.
     * @param xCenter - the x value of the center point.
     * @param yCenter - the y value of the center point.
     * @param r - the size of the radius of the ball.
     * @param color - the color of the ball.
     * @param gameEnvironment - the game environment.
     */
    public Ball(double xCenter, double yCenter, double r, java.awt.Color color,
                GameEnvironment gameEnvironment) {
        this.r = r;
        this.colorIn = color;
        this.colorOut = COLOR_OUTSIDE_BALL;
        this.center = new Point(xCenter, yCenter);
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * constructor method.
     * it creates the values of the ball, but getting a point instead of (x,y) values.
     * @param center - a Point object that creates in the Point method.
     * @param r - the size of the radius of the ball.
     * @param color - the color of the ball.
     * @param gameEnvironment - the game environment.
     */
    public Ball(Point center, double r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = center;
        this.r = r;
        this.colorIn = color;
        this.colorOut = COLOR_OUTSIDE_BALL;
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * getX method.
     * @return - the x value of the point center.
     */
    public double getX() {
        return this.center.getX();
    }

    /**
     * getY method.
     * @return - the y value of the point center.
     */
    public double getY() {
        return this.center.getY();
    }

    /**
     * getSize method.
     * @return - the size of the radius of a ball.
     */
    public int getSize() {
        return (int) this.r;
    }

    /**
     * drawOn method.
     * this method drown the balls on the given surface.
     * @param surface to draw on the balls.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.colorOut);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), (int) this.r);
        surface.setColor(this.colorIn);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), (int) this.r);
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * setVelocity method.
     * creates the new velocity by getting the velocity as an object.
     * @param v is the velocity variable
     */
    public void setVelocity(Velocity v) {
        this.velo = new Velocity(v.getDx(), v.getDy());
    }

    /**
     * setVelocity method.
     * setting the new velocity by the dx,dy values.
     * @param dx x value of the velocity
     * @param dy y value of the velocity
     */
    public void setVelocity(double dx, double dy) {
         this.velo = new Velocity(dx, dy);
    }

    /**
     * getVelocity method.
     * get the velocity that was set in the other methods.
     * @return velocity
     */
    public Velocity getVelocity() {
        return new Velocity(velo.getDx(), velo.getDy());
    }

    /**
     * setY method.
     * setting new Y value to the ball.
     * @param x the x value of the center of the ball.
     * @param y the y value of the center of the ball.
     */
    public void setCenter(double x, double y) {
        this.center = new Point(x, y);
    }

    /**
     * moveOneStep method.
     * the method that make the ball move and to change its direction according to the hit method.
     * it checks the collision point before applying the new point to the ball.
     */
    public void moveOneStep() {

        /* creating the line from the ball's center point, to the next point according to the velocity */
        Line oneStepLine = new Line(this.center, new Point(this.center.getX() + this.getVelocity().getDx(),
                this.center.getY() + this.getVelocity().getDy()));

        /* saving the collision information if it occurs */
        CollisionInfo info = this.gameEnvironment.getClosestCollision(oneStepLine);

        /* if there is collision with an object in this move */
        if (info != null) {

            /* set intersection point */
            Point intersectPoint = info.collisionPoint();

            /* put the ball in a point right before the collision */
            this.center = new Point(intersectPoint.getX() - this.velo.getDx(),
                    intersectPoint.getY() - this.velo.getDy());

            /* applying the new velocity according to the collision side */
            this.setVelocity(info.collisionObject().hit(this, info.collisionPoint(), this.getVelocity()));
        }

        /* if there is no collision in this move */
        this.center = getVelocity().applyToPoint(this.center);
    }

    /**
     * setSize method.
     * setting the size of the ball by this method.
     * @param radius the radius size of the ball
     */
    public void setSize(int radius) {
        this.r = radius;
    }

    /**
     * addToGame method.
     * adding the ball to the game.
     * @param gameLevel prameter.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    /**
     * removeFromGame method.
     * removing the ball from the game.
     * @param gameLevel the game.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}
