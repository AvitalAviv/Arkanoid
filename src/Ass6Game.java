//ID: 316125855

import biuoop.GUI;
import java.util.ArrayList;
import java.util.List;

/**
 * Ass5Game class
 * the class that calls for the Game class to start the game.
 * @author  Avital Aviv
 * @version 1.0
 */
public class Ass6Game {

    /**
     * main method.
     * thats the method that creates the game object, initialize the game and runs it.
     * @param args not get any parameters.
     */
    public static void main(String[] args) {
        List<LevelInformation> listOfLevels = new ArrayList<>();
        LevelInformation level1 = new LevelOne();
        LevelInformation level2 = new LevelTwo();
        LevelInformation level3 = new LevelThree();
        LevelInformation level4 = new LevelFour();
        List<LevelInformation> defaultRun = new ArrayList<>();
        defaultRun.add(level1);
        defaultRun.add(level2);
        defaultRun.add(level3);
        defaultRun.add(level4);

        /* create the list from the arguments */
        for (String s : args) {
            if (s.equals("1")) {
                listOfLevels.add(level1);
            }
            if (s.equals("2")) {
                listOfLevels.add(level2);
            }
            if (s.equals("3")) {
                listOfLevels.add(level3);
            }
            if (s.equals("4")) {
                listOfLevels.add(level4);
            }
        }
        GUI gui = new GUI("GAME", 800, 600);
        AnimationRunner animationRunner = new AnimationRunner(gui);
        GameFlow gameFlow = new GameFlow(animationRunner, gui.getKeyboardSensor(), gui);

        /* if the list from the arguments is empty, it runs a default list of levels */
        if (listOfLevels.isEmpty()) {
            gameFlow.runLevels(defaultRun);
        }
        if (args.length == 0) {
            gameFlow.runLevels(defaultRun);
        }
        gameFlow.runLevels(listOfLevels);
        gui.close();
    }
}
