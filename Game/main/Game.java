package main;

import java.awt.Canvas; // blank frame
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import main.graphics.Screen;

public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;

    public static int width = 300;
    public static int height = width / 16 * 9; // 16:9 aspect ratio
    public static int scale = 3; // scales the window 3times
    private Thread thread; // subprocess
    private boolean running = false;
    private Screen screen;
    private JFrame frame;
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    public Game() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size); // setting the size of the window (Canvas)
        screen = new Screen(width, height);
        frame = new JFrame();

    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (running) {
            update();
            render();
        }
    }

    public void update() {

    }

    public void render() {
        BufferStrategy bufferStrategy = getBufferStrategy(); // returned from Canvas | Temporary storage for data
        if (bufferStrategy == null) {
            createBufferStrategy(3); // tripeBuffering 
            return;
        }
        //screen.clear();
        screen.render();
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }
        Graphics graphics = bufferStrategy.getDrawGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        graphics.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        graphics.dispose(); // disposes current graphics
        bufferStrategy.show(); // make the next available buffer visible
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.frame.setResizable(false); // Tip: generally should be set to false, otherwise a lot of errors may occur
        game.frame.setTitle("Action RPG");
        game.frame.add(game);
        game.frame.pack(); // sets the size of the main frame to be the same as the game component
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Tip: if there is no such command the program will continue running
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);
        game.start();
    }
}
