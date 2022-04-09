//ID: 316125855
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;

/**
 * Paddle class.
 * the class that creates all the objects in the game, initialize them and running the animation.
 * @author  Avital Aviv
 * @version 1.0
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private GameLevel gameLevel;
    private Rectangle rect;
    private java.awt.Color color;
    private java.util.List<Ball> listBalls;
    private int movingSteps;
    private static final int LEFT_BORDER = 10;
    private static final int RIGHT_BORDER = 790;
    private static final int NUM_OF_REGIONS = 5;
    private static final int TWO_PI = 360;
    private static final int DEGREES = 30;

    /**
     * Paddle constructor.
     * creating and initializing all the things is needed to create the paddle.
     * @param gameLevel the game object.
     * @param point - start point of the paddle.
     * @param width - the width size.
     * @param height - the height size.
     * @param color - the color of the paddle.
     * @param movingSteps - the moving parameter.
     */
    public Paddle(GameLevel gameLevel, Point point, double width, double height, Color color, int movingSteps) {
        this.gameLevel = gameLevel;
        this.keyboard = gameLevel.getGUI().getKeyboardSensor();
        this.rect = new Rectangle(point, width, height);
        this.listBalls = new ArrayList<Ball>();
        this.color = color;
        this.movingSteps = movingSteps;
    }

    /**
     * moveLeft method.
     * making the paddle move left by the keyboard sensor.
     */
    public void moveLeft() {
        this.rect.setUpperLeft((-1) * this.movingSteps);
        this.rect.setOtherSides();
        ballInPaddle();
    }

    /**
     * moveRight method.
     * making the paddle move right by the keyboard sensor.
     */
    public void moveRight() {
        this.rect.setUpperLeft(this.movingSteps);
        this.rect.setOtherSides();
        ballInPaddle();
    }

    /**
     * ballInPaddle method.
     * if there balls in the paddle, it puts the ball outside it.
     */
    public void ballInPaddle() {
        listBalls = gameLevel.getListBalls();
        for (Ball ball : listBalls) {
            if (ball.getX() >= this.rect.getUpperLeft().getX()
                    && ball.getX() <= this.rect.getUpperRight().getX()
                    && ball.getY() >= this.rect.getUpperLeft().getY()
                    && ball.getY() <= this.rect.getDownLeft().getY()) {
                ball.setCenter(ball.getX(), this.rect.getUpperLeft().getY() - ball.getSize());
            }
        }
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        /* get the angle from the collision region method */
        int angle = findCollisionRegion(collisionPoint);

        /* returning the new velocity */
        return Velocity.fromAngleAndSpeed(angle,
                Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2)));
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
    }

    @Override
    public void timePassed() {
        if (keyboard.isPressed(keyboard.LEFT_KEY) && this.rect.getUpperLeft().getX() != LEFT_BORDER) {
            moveLeft();
        }
        if (keyboard.isPressed(keyboard.RIGHT_KEY) && this.rect.getUpperRight().getX() != RIGHT_BORDER) {
            moveRight();
        }
    }

    /**
     * findCollisionRegion method.
     * by the collision point on the paddle that is divided into 5 regions, it returns the angle,
     * to calculate the new velocity by angle and speed.
     * @param collisionPoint given collision point.
     * @return angle according to the collision point.
     */
    public int findCollisionRegion(Point collisionPoint) {

        /* get the size paddle */
        double sizePaddle = Math.abs(this.rect.getUpperLeft().getX() - this.rect.getUpperRight().getX());

        /* deciding the paddle into 5 regions */
        double sizeRegion = sizePaddle / NUM_OF_REGIONS;

        /* check the conditions of the collision and returning the angle */
        if (collisionPoint.getX() >= this.rect.getUpperLeft().getX()
                && collisionPoint.getX() < (sizeRegion + this.rect.getUpperLeft().getX())) {
            return TWO_PI - 2 * DEGREES;
        }
        if (collisionPoint.getX() >= (sizeRegion + this.rect.getUpperLeft().getX())
                && collisionPoint.getX() < (sizeRegion * 2 + this.rect.getUpperLeft().getX())) {
            return TWO_PI - DEGREES;
        }
        if (collisionPoint.getX() >= (sizeRegion * 2 + this.rect.getUpperLeft().getX())
                && collisionPoint.getX() < (sizeRegion * 3 + this.rect.getUpperLeft().getX())) {
            return 0;
        }
        if (collisionPoint.getX() >= (sizeRegion * 3 + this.rect.getUpperLeft().getX())
                && collisionPoint.getX() < (sizeRegion * 4 + this.rect.getUpperLeft().getX())) {
            return DEGREES;
        }
        if (collisionPoint.getX() >= (sizeRegion * 4 + this.rect.getUpperLeft().getX())
                && collisionPoint.getX() <= this.rect.getUpperRight().getX()) {
            return DEGREES * 2;
        }
        return 0;
    }

    /**
     * addToGame method.
     * @param g given collision point.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

}
