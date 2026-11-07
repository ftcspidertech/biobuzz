package org.firstinspires.ftc.teamcode.biobuzz.utils;

public class MovingAverage {
    private final double[] buffer;
    private final int n;
    private int count;
    private boolean isFull;

    public MovingAverage(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Window size n must be greater than 0");
        }
        this.n = n;
        this.buffer = new double[n];
        this.count = 0;
        this.isFull = false;
    }

    public double next(double value) {
        count++;

        // Wrap around the circular buffer when count exceeds n
        if (count > n) {
            count = count - n;
        }

        // Set isFull to true only once, when the buffer reaches capacity for the first time
        if (!isFull && count == n) {
            isFull = true;
        }

        buffer[count - 1] = value;

        // Divide by n once the buffer is full; otherwise divide by current count
        int activeElements = isFull ? n : count;

        double sum = 0;
        for (int i = 0; i < activeElements; i++) {
            sum += buffer[i];
        }

        return sum / activeElements;
    }

    public boolean isFull() {
        return isFull;
    }
}

