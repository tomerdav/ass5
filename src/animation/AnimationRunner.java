package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * AnimationRunner is a class that deals with the graphic and timing of
 * Animation types.
 * @author Tomer Davidor
 * @author Guy Blachar
 */
public class AnimationRunner {
    private GUI gui;
    private Sleeper sleeper;
    private static final int FPS = 30;

    /**
     * Constructor.
     * @param gui the GUI to use
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.sleeper = new Sleeper();
    }

    /**
     * Runs the animation.
     * @param animation an Animation instance to draw the graphics for
     */
    public void run(Animation animation) {
        // shouldStop() is in charge of stopping condition.
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            // doOneFrame(DrawSurface) is in charge of the logic.
            animation.doOneFrame(d);
            // Display and timing
            this.gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long miliSecondsLeftToSpare = 1000 / AnimationRunner.FPS - usedTime;
            if (miliSecondsLeftToSpare > 0) {
                this.sleeper.sleepFor(miliSecondsLeftToSpare);
            }
        }
    }
}
