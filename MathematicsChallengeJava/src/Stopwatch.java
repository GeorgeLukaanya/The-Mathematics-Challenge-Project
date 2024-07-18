package Quiz;

public class Stopwatch {
    private long startTime;
    private long endTime;
    private boolean running;
    private int durationInMinutes;

    public Stopwatch(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public void start() {
        this.startTime = System.currentTimeMillis();
        this.running = true;
    }

    public void stop() {
        this.endTime = System.currentTimeMillis();
        this.running = false;
    }

    public long remainingTime() {
        long elapsedTime = System.currentTimeMillis() - startTime;
        long totalDuration = durationInMinutes * 60 * 1000; // Duration in milliseconds
        return (totalDuration - elapsedTime) / 1000; // Remaining time in seconds
    }

    public String formatTime(long timeInSeconds) {
        long minutes = timeInSeconds / 60;
        long seconds = timeInSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
