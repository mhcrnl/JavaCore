package game;

import java.awt.BorderLayout;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class StatusBar extends JPanel {
    private static final long serialVersionUID = -272914721132546988L;
    private Calendar date;
    private static int hours;
    private static int minutes;
    private static int seconds;
    private JLabel time;
    private static JLabel timer;
    private final static int threadSleepDuration = 1000;
    private static int timerCount = 0;
    private static Thread timerThread;

    public StatusBar() {
        setBorder(new TitledBorder(BorderFactory.createTitledBorder("Time")));
        setLayout(new BorderLayout());

        date = new GregorianCalendar();
        time = new JLabel();
        add(time);
        new Thread("dateThread") {
            public void run() {
                while (true) {
                    date.setTime(new Date());
                    hours = date.get(Calendar.HOUR_OF_DAY);
                    minutes = date.get(Calendar.MINUTE);
                    seconds = date.get(Calendar.SECOND);
                    time.setText("Current time: " + Integer.toString(hours) + ":" + Integer.toString(minutes) + ":"
                            + Integer.toString(seconds));
                    try {
                        Thread.sleep(threadSleepDuration);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }.start();

        timer = new JLabel();
        add(timer, BorderLayout.LINE_END);
        timerThread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    timer.setText("Time played: " + Integer.toString(timerCount) + "s");
                    try {
                        Thread.sleep(threadSleepDuration);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    timerCount++;
                }
            }

        });
        timerThread.start();
    }

    @SuppressWarnings("deprecation")
    public static void resetTimer() {
        timerCount = -1;
        timer.setText("Time played: " + Integer.toString(timerCount) + "s");
        timerThread.resume();
    }

    @SuppressWarnings("deprecation")
    public static void timerStop() {
        timer.setText("GAME OVER");
        timerThread.suspend();
    }

    public static int getTimer() {
        return timerCount;
    }

    public static String getCurrentTimeToString() {
        return Integer.toString(hours) + ":" + Integer.toString(minutes) + ":" + Integer.toString(seconds);
    }
}
