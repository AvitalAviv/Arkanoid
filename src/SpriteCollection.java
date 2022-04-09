//ID: 316125855

import biuoop.DrawSurface;
import java.util.ArrayList;


/**
 * SpriteCollection class.
 * has the array list of all the sprites in the game.
 * @author  Avital Aviv
 * @version 1.0
 */
public class SpriteCollection {
    private java.util.List<Sprite> sprites;

    /**
     * SpriteCollection constructor.
     * creating the array list of sprites.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }

    /**
     * getSpritesList method.
     * @return list of Sprites objects.
     */
    public java.util.List getSpritesList() {
        return this.sprites;
    }

    /**
     * addSprite method.
     * @param s sprite in the game
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * removeSprite method.
     * @param s sprite in the game
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }

    /**
     * notifyAllTimePassed method.
     * go over all the sprites in the array and call the timePassed method.
     */
    public void notifyAllTimePassed() {
        ArrayList<Sprite> iterations = new ArrayList<Sprite>(this.sprites);
        for (int i = 0; i < iterations.size(); i++) {
            iterations.get(i).timePassed();
        }
    }

    /**
     * drawAllOn method.
     * by the method drawOn it draws all the sprites in the array.
     * @param d the surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }
}
