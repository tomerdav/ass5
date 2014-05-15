package arkanoid;

import animation.Animation;
import animation.AnimationRunner;

import biuoop.KeyboardSensor;

import core.Counter;

import java.util.List;

import levels.LevelInformation;

/**
 * A class that manages the flow of the game.
 * @author Tomer Davidor
 * @author Guy Blachar
 */
public class GameFlow {
    private KeyboardSensor sensor;
    private AnimationRunner runner;
    private Counter lives;
    private Counter score;
    private boolean gameWon;
    private int surfaceWidth;
    private int surfaceHeight;

    /**
     * Constructor.
     *
     * @param ks a KeyboardSensor
     * @param ar an AnimationRunner
     * @param livesAtFirst amount of lives at first
     * @param surfaceWidth the width of the GUI
     * @param surfaceHeight the height of the GUI
     */
    public GameFlow(KeyboardSensor ks, AnimationRunner ar, int livesAtFirst,
            int surfaceWidth, int surfaceHeight) {
        this.sensor = ks;
        this.runner = ar;
        this.lives = new Counter(livesAtFirst);
        this.score = new Counter(0);
        this.gameWon = false;
        this.surfaceWidth = surfaceWidth;
        this.surfaceHeight = surfaceHeight;
    }

    /**
     * Run the levels specified in a list.
     * @param levels the list of levels to run
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo,
                    this.sensor, this.runner, this.score, this.lives,
                    this.surfaceWidth, this.surfaceHeight);
            level.initialize();
            while (this.lives.getValue() > 0
                    && levelInfo.numberOfBlocksToRemove() > 0) {
                level.playOneTurn();
                // If there are still balls left, then all balls are lost
                if (levelInfo.numberOfBlocksToRemove() > 0) {
                    this.lives.decrease(1);
                }
            }
            if (this.lives.getValue() == 0) {
                break;
            }
        }

        if (this.lives.getValue() > 0) {
            this.gameWon = true;
        }
        Animation gameOver =
                new GameOver(this.score.getValue(), gameWon, this.sensor);
        this.runner.run(gameOver);
    }
}
