package Quiz;
    public class Stopwatch implements Runnable {
        private long startTime;
        private long totalTime;
        private boolean running;
        private Thread stopwatchThread;

        public Stopwatch(long minutes) {
            totalTime = minutes * 60000; // Convert minutes to milliseconds
            reset();
        }

        public void start() {
            if (!running) {
                startTime = System.currentTimeMillis();
                running = true;
                stopwatchThread = new Thread(this);
                stopwatchThread.start();
            }
        }

        public void stop() {
            if (running) {
                running = false;
            }
        }

        public void reset() {
            startTime = System.currentTimeMillis();
            running = false;
        }

        public double remainingTime() {
            if (running) {
                long elapsed = System.currentTimeMillis() - startTime;
                long remaining = totalTime - elapsed;
                return Math.max(remaining, 0); // Ensure time does not go negative
            } else {
                return totalTime; // Time left when not running
            }
        }

        @Override
        public void run() {
            while (running) {
                try {
                    Thread.sleep(1000); // Update every second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // Public method to format remaining time
        public String formatTime(double millis) {
            int minutes = (int) (millis / 60000);
            int seconds = (int) ((millis % 60000) / 1000);
            return String.format("%02d:%02d", minutes, seconds);
        }
    }

