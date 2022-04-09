//ID: 316125855

import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Block class.
 * the implementation of the methods in the interface Collidable.
 * @author  Avital Aviv
 * @version 1.0
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rect;
    private java.awt.Color colorIn;
    private java.awt.Color colorOut;
    private java.util.List<HitListener> hitListeners;

    /**
     * constructor method.
     * it creates the velocity object.
     *
     * @param rect the rectangle object.
     * @param color the color of the object.
     */
    public Block(Rectangle rect, java.awt.Color color) {
        this.rect = rect;
        this.colorIn = color;
        this.colorOut = Color.BLACK;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * constructor method.
     * it creates the velocity object.
     * @param rect the rectangle object.
     * @param colorIn the color of the object.
     * @param colorOut the color of the border of the object.
     */
    public Block(Rectangle rect, java.awt.Color colorIn, java.awt.Color colorOut) {
        this.rect = rect;
        this.colorIn = colorIn;
        this.colorOut = colorOut;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * constructor method.
     * it creates the velocity object.
     *
     * @param surf the surface.
     */
    public void drawOn(DrawSurface surf) {
        surf.setColor(this.colorIn);
        surf.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
        surf.setColor(this.colorOut);
        surf.drawRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());

    }

    @Override
    public void timePassed() {
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        int flag = 0;
        if ((collisionPoint.getX() > rect.getUpperLeft().getX()
                && collisionPoint.getX() < rect.getUpperRight().getX())
                || (collisionPoint.getX() > rect.getDownLeft().getX()
                && collisionPoint.getX() < rect.getDownRight().getX())) {
            currentVelocity.setDy(currentVelocity.getDy() * (-1));
            flag = 1;
        }
        if ((collisionPoint.getY() < rect.getDownLeft().getY()
                && collisionPoint.getY() > rect.getUpperLeft().getY())
                || (collisionPoint.getY() < rect.getDownRight().getY()
                && collisionPoint.getY() > rect.getUpperRight().getY())) {
            currentVelocity.setDx(currentVelocity.getDx() * (-1));
            flag = 1;
        }
        if (flag != 1) {
            currentVelocity.setDy(currentVelocity.getDy() * (-1));
            currentVelocity.setDx(currentVelocity.getDx() * (-1));
        }
        this.notifyHit(hitter);
        return currentVelocity;
       }

    /**
     * addToGame method.
     * adding the object to the Sprite and Colloidable collections.
     * @param gameLevel the game.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }

    /**
     * removeFromGame method.
     * removing the object from the Sprite and Colloidable collections.
     * @param gameLevel the game.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * notifyHit method.
     * make a copy of the listeners and then make another copy to iterate over them,
     * and then notify that a hit occurs to all the hit listeners.
     * @param hitter the ball that hit the block.
     */
    public void notifyHit(Ball hitter) {

        /* Make a copy of the hitListeners before iterating over them. */
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        List<HitListener> iterations = new ArrayList<>(listeners);

        /* Notify all listeners about a hit event */
        for (HitListener hl : iterations) {
            hl.hitEvent(this, hitter);
        }
    }
}
