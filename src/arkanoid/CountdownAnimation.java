package arkanoid;

import animation.Animation;

import biuoop.DrawSurface;

import java.awt.Color;

import levels.LevelInformation;

/**
 * A class for the countdown animation.
 * @author Tomer Davidor
 * @author Guy Blachar
 */
public class CountdownAnimation implements Animation {
    private long numOfMilisecs;
    private int currentDigit;
    private SpriteCollection gameScreen;
    private LevelInformation levelInfo;
    private long milisecsCapacityPerNum;
    private long milisecsPassed;
    private boolean firstFrame;
    private long start;

    /**
     * Constructor.
     *
     * @param numOfSeconds the total time to spend
     * @param countFrom the number to start from
     * @param gameScreen the game below
     * @param levelInfo the level which will begin after the countdown
     */
    public CountdownAnimation(double numOfSeconds, int countFrom,
            SpriteCollection gameScreen, LevelInformation levelInfo) {
        this.numOfMilisecs = (long) (1000 * numOfSeconds);
        this.currentDigit = countFrom;
        this.gameScreen = gameScreen;
        this.levelInfo = levelInfo;
        this.milisecsCapacityPerNum = this.numOfMilisecs / this.currentDigit;
        this.milisecsPassed = 0;
        this.firstFrame = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        // First frame maintenance
        if (this.firstFrame) {
            this.firstFrame = false;
            this.start = System.currentTimeMillis();
        }
        // Drawing
        this.levelInfo.drawBackground(d);
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.GRAY);
        d.drawText(d.getWidth() / 2, d.getHeight() / 2,
                ((Integer) this.currentDigit).toString(), 62);
        d.setColor(Color.WHITE);
        d.drawText(d.getWidth() / 2, d.getHeight() / 2,
                ((Integer) this.currentDigit).toString(), 55);
        // Timing
        this.milisecsPassed = (System.currentTimeMillis() - start);
        // Next number
        if (this.milisecsPassed >= this.milisecsCapacityPerNum) {
            this.milisecsPassed = 0;
            this.firstFrame = true;
            this.currentDigit--;
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean shouldStop() {
        return this.currentDigit == 0;
    }
}
