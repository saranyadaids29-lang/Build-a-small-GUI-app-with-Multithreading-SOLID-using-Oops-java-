import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class TimerTask implements Runnable {
    private JLabel label;
    private boolean running = true;
    private int seconds = 0;

    public TimerTask(JLabel label) {
        this.label = label;
    }

    public void stop() {
        running = false;
    }

    public void reset() {
        seconds = 0;
    }
    public void run() {
        try {
            while (running) {
                Thread.sleep(1000);
                seconds++;

                SwingUtilities.invokeLater(() -> {
                    label.setText("Time: " + seconds + " sec");
                });
            }
        } catch (InterruptedException e) {
            System.out.println("Timer interrupted");
        }
    }
}
public class TimerApp {

    private TimerTask task;
    private Thread thread;

    public TimerApp() {
        JFrame frame = new JFrame("Multithreaded Timer");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JLabel label = new JLabel("Time: 0 sec");

        JButton start = new JButton("Start");
        JButton stop = new JButton("Stop");
        JButton reset = new JButton("Reset");

        frame.add(label);
        frame.add(start);
        frame.add(stop);
        frame.add(reset);

        start.addActionListener(e -> {
            task = new TimerTask(label);
            thread = new Thread(task);
            thread.start();
        });

        stop.addActionListener(e -> {
            if (task != null) task.stop();
        });

        reset.addActionListener(e -> {
            if (task != null) task.reset();
            label.setText("Time: 0 sec");
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new TimerApp();
    }
}
