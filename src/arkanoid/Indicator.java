package arkanoid;

import biuoop.DrawSurface;

import core.Sprite;

import java.util.LinkedList;
import java.util.List;

/**
 * A class of the bar above the screen.
 * @author Tomer Davidor
 * @author Guy Blachar
 */
public class Indicator implements Sprite {
    private List<Sprite> indicators;
    private LevelNameDisplayer displayer;

    /**
     * Constructor.
     * @param displayer the displayer of the level's name
     */
    public Indicator(LevelNameDisplayer displayer) {
        this.indicators = new LinkedList<Sprite>();
        this.displayer = displayer;
    }

    /**
     * Add an indicator to the game.
     * @param indicator the indicator to add
     */
    public void addIndicator(BaseIndicator indicator) {
        this.indicators.add(indicator);
    }

    /**
     * Change the level's name.
     * @param levelName the name of the new level
     */
    public void setLevelName(String levelName) {
        this.displayer.setLevelName(levelName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawOn(DrawSurface d) {
        this.displayer.drawOn(d);
        for (Sprite indicator : this.indicators) {
            indicator.drawOn(d);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void timePassed() {
        return;
    }

}
