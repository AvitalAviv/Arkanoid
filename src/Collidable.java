//ID: 316125855

/**
 * Collidable interface.
 *
 * @author  Avital Aviv
 * @version 1.0
 */
public interface Collidable {

    /**
     * getCollisionRectangle method.
     * it returns the "collision shape" of the object.
     * @return the shape of the collidable object.
     */
    Rectangle getCollisionRectangle();

    /**
     * getCollisionRectangle method.
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * @param collisionPoint the intersection point that is the closest to the start point.
     * @param currentVelocity the setted velocity.
     * @param hitter the ball that was hit.
     * @return the new velocity expected after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
