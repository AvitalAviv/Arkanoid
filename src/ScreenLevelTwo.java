//ID: 316125855
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * ScreenLevelTwo class.
 * the animation background of level two in the game.
 * @author  Avital Aviv
 * @version 1.0
 */
public class ScreenLevelTwo implements Sprite {
    private DrawSurface surface;

    /**
     * constructor method.
     * @param d the drawsurface of the game.
     */
    public ScreenLevelTwo(DrawSurface d) {
        this.surface = d;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(255, 255, 153));

        /* drawing the lines from point (100,100) */
        for (int i = 1; i < 75; i++) {
            d.drawLine(100, 100, i * 10, 200);
        }

        /* creating the sun by three circles */
        d.setColor(new Color(255, 255, 153));
        d.fillCircle(100, 100, 60);
        d.setColor(new Color(255, 255, 0));
        d.fillCircle(100, 100, 55);
        d.setColor(new Color(255, 204, 0));
        d.fillCircle(100, 100, 50);
    }

    @Override
    public void timePassed() {

    }
}
