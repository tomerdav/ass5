package arkanoid;

import animation.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * A class for the GameOver screen.
 * @author Tomer Davidor
 * @author Guy Blachar
 */
public class GameOver implements Animation {
    private KeyboardSensor keyboard;
    private int finalScore;
    private boolean won;
    private boolean stop;
    private String message;
    private String smiley;
    private Color[] palette;

    /**
     * Constructor.
     * @param score the score in the game
     * @param isWin did the player win
     * @param ks the KeyBoardSensor
     */
    public GameOver(int score, boolean isWin, KeyboardSensor ks) {
        this.finalScore = score;
        this.won = isWin;
        this.keyboard = ks;
        this.stop = false;
        this.palette = new Color[2];
        if (won) {
            palette[0] = Color.YELLOW;
            palette[1] = Color.ORANGE;
            this.message = "You Win!";
            this.smiley = ":)";
        } else {
            palette[0] = Color.RED;
            palette[1] = Color.RED.darker();
            this.message = "Game over.";
            this.smiley = ":(";
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.DARK_GRAY);
        d.drawRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.BLACK);
        d.drawText(d.getWidth() / 12, d.getHeight() / 3, this.message, 103);
        d.drawText(d.getWidth() * 3 / 4 + 40, d.getHeight() / 3,
                this.smiley, 103);
        d.setColor(this.palette[0]);
        d.drawText(d.getWidth() / 12 + 6, d.getHeight() / 3, this.message, 100);
        d.drawText(d.getWidth() * 3 / 4 - 3 + 40, d.getHeight() / 3,
                this.smiley, 100);

        d.setColor(Color.BLACK);
        d.drawText(d.getWidth() / 12, d.getHeight() * 5 / 6,
                "Your final score is: ", 50);
        d.setColor(Color.GRAY);
        d.drawText(d.getWidth() * 2 / 3, d.getHeight() * 5 / 6,
                "" + this.finalScore, 75);
        d.setColor(this.palette[1]);
        d.drawText(d.getWidth() * 2 / 3 - 3, d.getHeight() * 5 / 6,
                "" + this.finalScore, 74);
        d.setColor(Color.BLACK);
        d.drawText(d.getWidth() / 12, d.getHeight() / 2,
                "-- Press SPACE to continue --", 30);
        // Space to break
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}
