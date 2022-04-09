//ID: 316125855

import biuoop.DrawSurface;

/**
 * Sprite interface.
 *
 * @author  Avital Aviv
 * @version 1.0
 */
public interface Sprite {
    // draw the sprite to the screen

    /**
     * drawOn method.
     * it creates the velocity object.
     * @param d the surface
     */
    void drawOn(DrawSurface d);
    // notify the sprite that time has passed

    /**
     * timePassed method.
     * it creates the velocity object.
     */
    void timePassed();
}
