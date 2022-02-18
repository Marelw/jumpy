import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
    public static void main(String[] args) {
        final int[] i = {0};
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Timer ran " + ++i[0]);
            }
        };

        timer.schedule(task, 2000,60);

    }
}
