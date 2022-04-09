//ID: 316125855

/**
 * CollisionInfo class.
 * saving all the information about collision points and the collidable object..
 * @author  Avital Aviv
 * @version 1.0
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collObject;

    /**
     * constructor method.
     * @param collPoint the collision point.
     * @param collObject the object with whom the collision occurs.
     */
    public CollisionInfo(Point collPoint, Collidable collObject) {
        this.collisionPoint = collPoint;
        this.collObject = collObject;
    }

    /**
     * collisionPoint method.
     * @return the collision point.
     */
    public Point collisionPoint() {
        if (this.collisionPoint != null) {
            return this.collisionPoint;
        }
        return null;
    }

    /**
     * collisionObject method.
     * @return the collisional object.
     */
    public Collidable collisionObject() {
        return this.collObject;
    }
}
