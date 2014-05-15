package core;

/**
 * Counter class.
 * @author Tomer Davidor
 * @author Guy Blachar
 */
public class Counter {
    private int count;

    /**
     * Constructor.
     * @param value the initial value of the counter
     */
    public Counter(int value) {
        this.count = value;
    }

    /**
     * Add number to current count.
     * @param number the number to add
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * Subtract number from current count.
     * @param number the number to subtract
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * Get current count.
     * @return the current count
     */
    public int getValue() {
        return this.count;
    }
}
