package main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;

public class Clock extends JFrame {

    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Clock frame = new Clock();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Clock() {
        setResizable(false);
        setTitle("Clock Widget\r\n");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 350, 170);
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.desktop);
        contentPane.setForeground(SystemColor.activeCaption);
        contentPane.setBorder(new CompoundBorder());
        setContentPane(contentPane);
        contentPane.setLayout(null);

        final JLabel clock = new JLabel("");
        clock.setFont(new Font("DS-Digital", Font.BOLD, 65));
        clock.setForeground(SystemColor.textHighlight);
        clock.setHorizontalAlignment(SwingConstants.CENTER);
        clock.setBounds(10, 0, 317, 121);
        contentPane.add(clock);

        new Thread() {
            public void run() {
                while (true) {
                    Calendar calendar = new GregorianCalendar();
                    int hour = calendar.get(Calendar.HOUR);
                    int minute = calendar.get(Calendar.MINUTE);
                    int second = calendar.get(Calendar.SECOND);
                    int AM_PM = calendar.get(Calendar.AM_PM);
                    String dayOrNight;
                    if (AM_PM == 1) {
                        dayOrNight = "PM";
                    } else {
                        dayOrNight = "AM";
                    }
                    String timeString = hour + ":" + minute + ":" + second + " " + dayOrNight;

                    clock.setText(timeString);
                }
            }
        }.start();

    }
}
