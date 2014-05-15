import animation.AnimationRunner;
import arkanoid.GameFlow;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.LinkedList;
import java.util.List;

import levels.DirectHit;
import levels.FinalFour;
import levels.Green3;
import levels.LevelInformation;
import levels.WideEasy;

/**
 * The main function -- manages a game.
 * @author Tomer Davidor
 * @author Guy Blachar
 */
public class Ass5Game {
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    /**
     * Parse the arguments of the main (levels numbers and unused Strings)
     * to a list of levels.
     * @param args the arguments of the main function (levels numbers
     * and unused Strings)
     * @return a list with the levels to run
     */
    public static List<LevelInformation> argumentsToLevels(String[] args) {
        List<LevelInformation> levels = new LinkedList<LevelInformation>();
        int index = 0;
        for (String arg : args) {
            try {
                int levelNum = Integer.parseInt(arg);
                if (levelNum == 1) {
                    levels.add(index++, new DirectHit(Ass5Game.SCREEN_WIDTH,
                            Ass5Game.SCREEN_HEIGHT));
                } else if (levelNum == 2) {
                    levels.add(index++, new WideEasy(Ass5Game.SCREEN_WIDTH,
                            Ass5Game.SCREEN_HEIGHT));
                } else if (levelNum == 3) {
                    levels.add(index++, new Green3(Ass5Game.SCREEN_WIDTH,
                            Ass5Game.SCREEN_HEIGHT));
                } else if (levelNum == 4) {
                    levels.add(index++, new FinalFour(Ass5Game.SCREEN_WIDTH,
                            Ass5Game.SCREEN_HEIGHT));
                }
            } catch (NumberFormatException e) {
                continue;
            }
        }
        return levels;
    }
    /**
     * Main function.
     * @param args the levels to run
     */
    public static void main(String[] args) {
        // Creating a list with the levels
        List<LevelInformation> levels;
        if (args.length == 0) {
            levels = new LinkedList<LevelInformation>();
            levels.add(new DirectHit(Ass5Game.SCREEN_WIDTH,
                    Ass5Game.SCREEN_HEIGHT));
            levels.add(new WideEasy(Ass5Game.SCREEN_WIDTH,
                    Ass5Game.SCREEN_HEIGHT));
            levels.add(new Green3(Ass5Game.SCREEN_WIDTH,
                    Ass5Game.SCREEN_HEIGHT));
            levels.add(new FinalFour(Ass5Game.SCREEN_WIDTH,
                    Ass5Game.SCREEN_HEIGHT));
        } else {
            levels = argumentsToLevels(args);
            if (levels.size() == 0) {
                System.out.println("Not found any levels.");
            }
        }
        // Create the AnimationRunner and the GameFlow
        GUI gui = new GUI("Arkanoid", Ass5Game.SCREEN_WIDTH,
                Ass5Game.SCREEN_HEIGHT);
        KeyboardSensor ks = gui.getKeyboardSensor();
        AnimationRunner ar = new AnimationRunner(gui);
        int livesAtFirst = 7;
        DrawSurface d = gui.getDrawSurface();
        GameFlow flow = new GameFlow(ks, ar, livesAtFirst, d.getWidth(),
                d.getHeight());
        // Run the levels
        flow.runLevels(levels);
        gui.close();
    }
}
