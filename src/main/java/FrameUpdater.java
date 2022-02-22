import javax.swing.SwingUtilities;

public class FrameUpdater extends Thread {
    private final long timeBetweenUpdates;
    private GameConstraints gc;

    public FrameUpdater(GameConstraints gc, int fps) {
        this.gc = gc;
        this.timeBetweenUpdates = Math.floorDiv(1_000_000_000, fps);
    }

    @Override
    public void run() {
        final long startTime = System.nanoTime();

        while (!isInterrupted()) {
            long currentTime = System.nanoTime();
            long timeAtNextUpdate = currentTime + timeBetweenUpdates;

            // notify the update method how many milliseconds have elapsed
            // since we started this loop
            gc.update((int)((currentTime - startTime) / 1_000_000));

            // this could end up running at the same time as the
            // update() method above, which could lead to some
            // concurrency issues!
            SwingUtilities.invokeLater(() -> gc.repaint());

            // sleep until within a millisecond of the target time
            // to avoid taking up all cpu and allow other things to run
            sleepRemaining(timeAtNextUpdate - 1_000_000);

            // busy wait the last ms to hit the time more exact
            while (System.nanoTime() < timeAtNextUpdate) {
                // let other threads have a go at it if they so wish
                Thread.yield(); /// något annat än yield????
            }
        }
    }

    private void sleepRemaining(long sleepToNanos) {
        long totalNanosLeft = sleepToNanos - System.nanoTime();
        long millisLeft = totalNanosLeft / 1_000_000;
        int nanosLeft = (int)(totalNanosLeft - millisLeft * 1_000_000);

        if (millisLeft > 0 || nanosLeft > 0) {
            try {
                Thread.sleep(millisLeft, nanosLeft);
            }
            catch (InterruptedException ex) {
                interrupt();
            }
        }
    }
}
