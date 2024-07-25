package Quiz;

public class Stopwatch {
    private long startTime;
    private long endTime;
    private boolean running;

    public Stopwatch() {
        this.running = false;
    }

    public void start() {
        this.startTime = System.currentTimeMillis();
        this.running = true;
    }

    public void stop() {
        this.endTime = System.currentTimeMillis();
        this.running = false;
    }

    public long getElapsedTime() {
        if (running) {
            return System.currentTimeMillis() - startTime;
        }
        return endTime - startTime;
    }

    public String formatTime(long timeInMillis) {
        long timeInSeconds = timeInMillis / 1000;
        long minutes = timeInSeconds / 60;
        long seconds = timeInSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
