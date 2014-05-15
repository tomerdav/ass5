package arkanoid;

import animation.Animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * Pause Screen.
 * @author Tomer Davidor
 * @author Guy Blachar
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Constructor.
     * @param k a KeyboarsSensor
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(150, 2 * d.getHeight() / 3,
                "Paused -- Press SPACE to continue", 32);
        // Draw the symbol of pause (|| in a circle)
        d.setColor(Color.BLUE);
        d.fillCircle(d.getWidth() / 2, d.getHeight() / 3 + 50, 80);
        d.setColor(Color.WHITE);
        d.fillRectangle(d.getWidth() / 2 - 35, d.getHeight() / 3 + 15, 25, 80);
        d.fillRectangle(d.getWidth() / 2 + 10, d.getHeight() / 3 + 15, 25, 80);
        // Space to continue
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
