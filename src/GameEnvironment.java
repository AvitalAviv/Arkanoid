//ID: 316125855

import java.util.ArrayList;

/**
 * GameEnvironment class.
 * setting the game environment.
 * @author  Avital Aviv
 * @version 1.0
 */
public class GameEnvironment {
    private java.util.List<Collidable> collides;

    /**
     * constructor method.
     */
    public GameEnvironment() {
        this.collides = new ArrayList<Collidable>();
    }

    /**
     * getCollidesList method.
     * @return list of collides objects.
     */
    public java.util.List getCollidesList() {
        return this.collides;
    }

    /**
     * addCollidable method.
     * @param c add to a list an colloidal object.
     */
    public void addCollidable(Collidable c) {
        this.collides.add(c);
    }

    /**
     * removeCollidable method.
     * @param c remove from the list an colloidal object.
     */
    public void removeCollidable(Collidable c) {
        this.collides.remove(c);
    }

    /**
     * getClosestCollision method.
     * @param trajectory add to a list an colloidal object.
     * @return minimum point of the collision info.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {

        /* initializing an array list to create the list of collisions */
        java.util.List<CollisionInfo> listCollisions = new ArrayList<CollisionInfo>();

        /* check every collidable if has an intersection point with the trajectory line */
        java.util.List<Collidable> iterations = new ArrayList<Collidable>(this.collides);
        for (int i = 0; i < iterations.size(); i++) {
            if (this.collides.get(i).getCollisionRectangle().intersectionPoints(trajectory) != null) {
                Point closeColPoint = trajectory.closestIntersectionToStartOfLine(this.collides.get(i)
                        .getCollisionRectangle());

                /* if there is a collision point then add it to the list */
                if (closeColPoint != null) {
                    listCollisions.add(new CollisionInfo(closeColPoint, this.collides.get(i)));
                }
            }
        }

        /* check if the list is empty */
        if (listCollisions.isEmpty()) {
            return null;
        }
        CollisionInfo min = listCollisions.get(0);

        /* find the minimum collision point */
        for (int i = 1; i < listCollisions.size(); i++) {
            if (trajectory.start().distance(min.collisionPoint())
                    > trajectory.start().distance(listCollisions.get(i).collisionPoint())) {
                min = listCollisions.get(i);
            }
        }
        return min;
    }
}
