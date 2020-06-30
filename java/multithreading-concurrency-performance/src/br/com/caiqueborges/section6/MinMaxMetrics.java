package br.com.caiqueborges.section6;

public class MinMaxMetrics {

    // Add all necessary member variables
    private volatile long minimum;
    private volatile long maximum;

    /**
     * Initializes all member variables
     */
    public MinMaxMetrics() {
        this.maximum = 0;
        this.minimum = 0;
    }

    /**
     * Adds a new sample to our metrics.
     */
    public void addSample(long newSample) {

        synchronized (this) {
            this.minimum = Math.min(newSample, this.minimum);
            this.maximum = Math.max(newSample, this.maximum);
        }

    }

    /**
     * Returns the smallest sample we've seen so far.
     */
    public long getMin() {
        return this.minimum;
    }

    /**
     * Returns the biggest sample we've seen so far.
     */
    public long getMax() {
        return this.maximum;
    }
}
